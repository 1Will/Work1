package com.yongjun.tdms.service.runmaintenance.spareBorrow.pojo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.runmaintenance.spareBorrow.SpareBorrowDao;
import com.yongjun.tdms.dao.runmaintenance.spareBorrow.SpareBorrowDetailDao;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrow;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetail;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetailStatus;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowStatus;
import com.yongjun.tdms.service.runmaintenance.spareBorrow.SpareBorrowDetailManager;

public class DefaultSpareBorrowDetailManager implements SpareBorrowDetailManager{
    private final SpareBorrowDetailDao  spareBorrowDetailDao;
    private final SpareDao spareDao;
    private final SpareBorrowDao  spareBorrowDao;
    public DefaultSpareBorrowDetailManager(SpareBorrowDetailDao  spareBorrowDetailDao,SpareDao spareDao,
    		SpareBorrowDao  spareBorrowDao){
    	this.spareBorrowDetailDao=spareBorrowDetailDao;
    	this.spareDao=spareDao;
    	this.spareBorrowDao=spareBorrowDao;
    }
	public SpareBorrowDetail loadSpareBorrowDetail(Long id) {
	
		return spareBorrowDetailDao.loadSpareBorrowDetail(id);
	}
	public void storeSpareBorrowDetail(SpareBorrowDetail spareBorrowDetail) {
      this.spareBorrowDetailDao.storeSpareBorrowDetail(spareBorrowDetail);
		
	}
	public List<SpareBorrowDetail> loadAllSpareBorrowDetails(Long[] ids) {
		
		return this.spareBorrowDetailDao.loadAllSpareBorrowDetails(ids);
	}
	public void deleteSpareBorrowDetail(SpareBorrowDetail spareBorrowDetail) {
		this.spareBorrowDetailDao.deleteSpareBorrowDetail(spareBorrowDetail);
	}
	public void deleteAllSpareBorrowDetails(List<SpareBorrowDetail> spareBorrowDetails) {
		this.spareBorrowDetailDao.deleteAllSpareBorrowDetails(spareBorrowDetails);
	}
	//从备件台帐copy到备件领用单
	public void spareAccountCopyToSpareBorrow(SpareBorrow spareBorrow, String spareAccountids) {
		String[] addSpareAccountId = null;
		if(null != spareAccountids){
			addSpareAccountId = spareAccountids.split(",");
		}
		for(int i=0;i<addSpareAccountId.length;i++){
			SpareBorrowDetail detail = new SpareBorrowDetail();
			Spare spare = spareDao.loasSpare(Long.valueOf(addSpareAccountId[i]));
			detail.setSpare(spare);
			detail.setSpareBorrow(spareBorrow);
		
			copySpareAccountToSpareBorrowDtl(spare,detail);				
			this.spareBorrowDetailDao.storeSpareBorrowDetail(detail);
		}
	}
   public void copySpareAccountToSpareBorrowDtl(Spare spare,SpareBorrowDetail detail ){
		if(spare.getSpareNo()!=null){						//复制编号
			detail.setSpareNo(spare.getSpareNo());
		}else{
			detail.setSpareNo(null);
		}
		if(spare.getName()!=null){
			detail.setSpareName(spare.getName());				//复制名称
		}else{
			detail.setSpareName(null);
		}
		if(spare.getModelSpecs()!=null){
			detail.setModel(spare.getModelSpecs());
		}else{
			detail.setModel(null);
		}
		if(spare.getSpecification()!=null){               //复制规格
			detail.setSpecification(spare.getSpecification());
		}else{
			detail.setSpecification(null);
		}
		if(spare.getUnit()!=null){            //复制单位
			detail.setUnit(spare.getUnit().getValue());
		}else{
			detail.setUnit(null);
		}
		if(spare.getUnitPrice()!=null){
			detail.setPrice(spare.getUnitPrice());		//复制单价
		}else{
			detail.setPrice(Double.valueOf(0.0));
		}
		
   }
public void storespareAccountCopyToSpareBorrow(SpareBorrow spareBorrow,String allSpareBorrowNumber, String allSpareBorrowBillDtlId ) {
	String[] dtlIdArr = null;
	String[] dtlNumberArr = null;
	if(null!=allSpareBorrowBillDtlId){
		dtlIdArr = allSpareBorrowBillDtlId.split(",");    			
	}
	if(null!=allSpareBorrowNumber){
		dtlNumberArr = allSpareBorrowNumber.split(",");  	
	}
	updateSpareBorrowDetail(dtlIdArr,dtlNumberArr);	
	//根据备件领用单明细的状态改变备件领用单的状态
	accordingSpareBorrowDetailStatusUpdateSpareBorrowStatus(spareBorrow);
 }

  public void  accordingSpareBorrowDetailStatusUpdateSpareBorrowStatus(SpareBorrow spareBorrow){
	  //标识已经领用的循环次数
	  int borrowedLoop=0;
	  //标识未领用的循环次数
	  int unBorrowLoop=0;
	  for(SpareBorrowDetail dtl:spareBorrow.getDetail()){
		   if(dtl.getStatus().equals(SpareBorrowDetailStatus.UNBORROW)){
			   borrowedLoop++;
			   continue;
		   }else{
			   unBorrowLoop++;
			   continue;
		   }
	  }
	  if(borrowedLoop==spareBorrow.getDetail().size()){
		  spareBorrow.setStatus(SpareBorrowStatus.NEWSTATUS);
		  spareBorrowDao.storeSpareBorrow(spareBorrow);
	  }else if(unBorrowLoop==spareBorrow.getDetail().size()){
		  spareBorrow.setStatus(SpareBorrowStatus.BORROWED);
		  spareBorrowDao.storeSpareBorrow(spareBorrow);
	  }else{
		  spareBorrow.setStatus(SpareBorrowStatus.BORROWING);
		  spareBorrowDao.storeSpareBorrow(spareBorrow);
	  }
  }

 public void updateSpareBorrowDetail(String[] dtlIdArr,String[] dtlNumberArr){

	//备件领用明细循环的次数
	 int spareBorrowLoopNum=0;
	 while(dtlIdArr != null && spareBorrowLoopNum<dtlIdArr.length){
			SpareBorrowDetail detail = spareBorrowDetailDao.loadSpareBorrowDetail(Long.valueOf(dtlIdArr[spareBorrowLoopNum]));
			long borrowNum =0;
			if(dtlNumberArr!=null){
				   for(int i=0;i<dtlNumberArr.length;i=i+2){
					   if(dtlNumberArr[i].equals(dtlIdArr[spareBorrowLoopNum])){

						   borrowNum=Long.valueOf(dtlNumberArr[i+1]);
						   detail.setAmount(borrowNum);

						   detail.setAmount(Long.valueOf(dtlNumberArr[i+1]));

						   break;
					   }else{
						   detail.setAmount(null);
					   }
				   }
			 }
			if(detail.getAmount()!=null){
				 detail.setTotalPrice(detail.getAmount()*(detail.getPrice()));
			}
		 spareBorrowDetailDao.storeSpareBorrowDetail(detail);
		 spareBorrowDao.storeSpareBorrow(detail.getSpareBorrow());
		 spareBorrowLoopNum++;
	 }
  }
 //在备件领用单明细中提供一个当出库单明细对领用单明细进行操作改变状态的一个接口，目的是在出库单明细中”保存“或者“删除”从领用单明细选择的记录
 //从而改变领用单明细和领用单的状态
public void updateSpareBorrowStatusBySpareBorrowDtlStatus(SpareBorrowDetail spareBorrowDetail) {
	this.accordingSpareBorrowDetailStatusUpdateSpareBorrowStatus(spareBorrowDetail.getSpareBorrow());
}

public void deleteSpareBorrowDtl(List<SpareBorrowDetail> dtl, SpareBorrow spareBorrow) throws Exception {
	Set<SpareBorrowDetail>  detailes=spareBorrow.getDetail();
//	如果领用单明细的状态为“已领用”则删除失败  返回ERROR
	deleteSpareBorrowDtlStatus(dtl);

			this.spareBorrowDetailDao.deleteAllSpareBorrowDetails(dtl);
			spareBorrow.getDetail().removeAll(dtl);
		    this.accordingSpareBorrowDetailStatusUpdateSpareBorrowStatus(spareBorrow);
	
	

 }
 public void deleteSpareBorrowDtlStatus( List<SpareBorrowDetail> details)throws Exception{
	 for(SpareBorrowDetail dtl:details){
		 if(dtl.getStatus().equals(SpareBorrowDetailStatus.BORROWED)){
			 throw new Exception();
		 }
	 }
 }
}
