/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.asset.spare.outWareHouse.pojo;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.asset.spare.inWareHouse.SpareInBillDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDetailViewDao;
import com.yongjun.tdms.dao.runmaintenance.spareBorrow.SpareBorrowDetailDao;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetailStatus;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetailView;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillStatus;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetailStatus;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillManager;
import com.yongjun.tdms.service.runmaintenance.spareBorrow.SpareBorrowDetailManager;
/**
 * <p>Title: DefaultSpareOutBillManager
 * <p>Description: 备件出库业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhang@yj-technology.com
 * @see com.yongjun.tdms.service.asset.spare.outWareHouse.pojo.DefaultSpareOutBillManager
 * @version $Id:$
 */
public class DefaultSpareOutBillManager implements SpareOutBillManager {
	private final SpareOutBillDao spareOutBillDao;
	private final SequenceManager spareOutBillSequenceManager;
	private final SpareOutBillDetailViewDao spareOutBillDetailViewDao;
	private final SpareBorrowDetailDao spareBorrowDetailDao;
	private final SpareBorrowDetailManager  spareBorrowDetailManager;
	private final SpareInBillDao spareInBillDao;
	public DefaultSpareOutBillManager(SpareOutBillDao spareOutBillDao,SequenceManager spareOutBillSequenceManager,
			SpareOutBillDetailViewDao spareOutBillDetailViewDao,SpareBorrowDetailDao spareBorrowDetailDao,
			SpareBorrowDetailManager  spareBorrowDetailManager,
			SpareInBillDao spareInBillDao){
		this.spareOutBillDao = spareOutBillDao;
		this.spareOutBillSequenceManager = spareOutBillSequenceManager;
		this.spareOutBillDetailViewDao=spareOutBillDetailViewDao;
		this.spareBorrowDetailDao=spareBorrowDetailDao;
		this.spareBorrowDetailManager=spareBorrowDetailManager;
		this.spareInBillDao = spareInBillDao;
	}
	public List<SpareOutBill> loadAllSpareOutBill(Long[] spareOutBillIds) {
		return spareOutBillDao.loadAllSpareOutBill(spareOutBillIds);
	}

	public SpareOutBill loadSpareOutBill(Long spareOutBillId) {
		return spareOutBillDao.loadSpareOutBill(spareOutBillId);
	}

	public void storeSpareOutBill(SpareOutBill spareOutBill) {
//		if(spareOutBill.isNew()){
//			spareOutBill.setCode((String)spareOutBillSequenceManager.generate("-"));
//		}
		spareOutBillDao.storeSpareOutBill(spareOutBill);
	}

	public void deleteSpareOutBill(SpareOutBill spareOutBill) {
		SpareInBill spareInBill = this.spareInBillDao.loadSpareInBillBySpareOutBillId(spareOutBill.getId());
		if (null!=spareInBill){
			this.spareInBillDao.deleteSpareInBill(spareInBill);
		}
		spareOutBillDao.deleteSpareOutBill(spareOutBill);
	}

	public void deleteAllSpareOutBill(Collection<SpareOutBill> spareOutBill) {
		spareOutBillDao.deleteAllSpareOutBill(spareOutBill);
	}
	public void disabledAllSpareOutBill(Collection<SpareOutBill> spareOutBills)throws Exception {
		for(SpareOutBill spareOutBill : spareOutBills){
			if(spareOutBill.getStatus().equals(SpareOutBillStatus.OUTWAREHOUSEED)||spareOutBill.getStatus().equals(SpareOutBillStatus.OUTWAREHOUSEING)){
				throw new Exception();
			}
            //失效出库的时候要将备件在台帐中的数量给加上
			for(SpareOutBillDetail spareOutBillDetail : spareOutBill.getDetail()){
			      spareOutBillDetail.getSpare().setStocks(spareOutBillDetail.getStocksBeforeInOrOut());
			      //如果出库单失效的记录存在从备件领用单明细选择的记录，那么把此出库单明细所关联的备件领用单明细的状态置为“未领用”
                  //同时根据备件领用单明细状态改变备件领用单的状态
			      if(spareOutBillDetail.getDtl()!=null){
			    	  spareOutBillDetail.getDtl().setStatus(SpareBorrowDetailStatus.UNBORROW);
			    	  spareBorrowDetailDao.storeSpareBorrowDetail(spareOutBillDetail.getDtl());
			    	  spareBorrowDetailManager.updateSpareBorrowStatusBySpareBorrowDtlStatus(spareOutBillDetail.getDtl());
			      }
			      
			}
			spareOutBill.setDisabled(true);
			spareOutBillDao.storeSpareOutBill(spareOutBill);
			
			//失效相关的二级库入库单
			SpareInBill spareInBill = this.spareInBillDao.loadSpareInBillBySpareOutBillId(spareOutBill.getId());
			if (null!=spareInBill){
				spareInBill.setDisabled(true);
				this.spareInBillDao.storeSpareInBill(spareInBill);
			}
			
			
		}
	}
	public void enabledAllSpareOutBill(Collection<SpareOutBill> spareOutBills) {
		for(SpareOutBill spareOutBill : spareOutBills){
           //有效出库的时候要将备件在台帐中的数量给加上
			for(SpareOutBillDetail spareOutBillDetail : spareOutBill.getDetail()){
			spareOutBillDetail.getSpare().setStocks(spareOutBillDetail.getStocksAfterInOrOut());
            //如果出库单有效的记录存在从备件领用单明细选择的记录，那么把此出库单明细所关联的备件领用单明细的状态置为“未领用”
            //同时根据备件领用单明细状态改变备件领用单的状态
			if(spareOutBillDetail.getDtl()!=null){
		    	  spareOutBillDetail.getDtl().setStatus(SpareBorrowDetailStatus.BORROWED);
		    	  spareBorrowDetailDao.storeSpareBorrowDetail(spareOutBillDetail.getDtl());
		    	  spareBorrowDetailManager.updateSpareBorrowStatusBySpareBorrowDtlStatus(spareOutBillDetail.getDtl());
		      }
			}
			spareOutBill.setDisabled(false);
			spareOutBillDao.storeSpareOutBill(spareOutBill);
			
			//有效相关的二级库入库单
			SpareInBill spareInBill = this.spareInBillDao.loadSpareInBillBySpareOutBillId(spareOutBill.getId());
			if (null!=spareInBill){
				spareInBill.setDisabled(false);
				this.spareInBillDao.storeSpareInBill(spareInBill);
			}
		}
	}
	public List<SpareOutBillDetailView> loadSpareOutBillDetail(Long outWareBillId) {
		return spareOutBillDetailViewDao.loadSpareOutBillDetail(outWareBillId);
	}
	public List<SpareOutBillDetailView> loadSpareOutBillDetail(String[] array) throws HibernateException {
		// TODO Auto-generated method stub
		return spareOutBillDetailViewDao.loadSpareOutBillDetail(array);
	}
	/**
	 * 给据出库单明细的状态改变出库单的状态
	 */
	public void  accordingSpareOutBillDetailStatusUpdateSpareOutStatus(SpareOutBill spareOutBill){
		  //标识已经出库的循环次数
		  int 	outedLoop=0;
		  //标识未出库的循环次数
		  int unOutLoop=0;
		  for(SpareOutBillDetail dtl:spareOutBill.getDetail()){
			   if(dtl.getStatus().equals(SpareOutBillDetailStatus.NEW)){
				   unOutLoop++;
				   continue;
			   }else{
				
				   outedLoop++;
				   continue;
			   }
		  }
		  if(unOutLoop==spareOutBill.getDetail().size()){
			  spareOutBill.setStatus(SpareOutBillStatus.NEWSTATUS);
			  spareOutBillDao.storeSpareOutBill(spareOutBill);
		  }else if(outedLoop==spareOutBill.getDetail().size()){
			  spareOutBill.setStatus(SpareOutBillStatus.OUTWAREHOUSEED);
			  spareOutBillDao.storeSpareOutBill(spareOutBill);
		  }else{
			  spareOutBill.setStatus(SpareOutBillStatus.OUTWAREHOUSEING);
			  spareOutBillDao.storeSpareOutBill(spareOutBill);
		  }
	  }
	  /**
     * 查询列表下所有金额
     * @param param
     * @return
     */
	public Double showOutTotalPrice(String[] param){
		return this.spareOutBillDao.showOutTotalPrice(param);
	}
	public Double showOldOutTotalPrice(String[] param) {
		return this.spareOutBillDao.showOldOutTotalPrice(param);
	}
	
	public Double showOldScrapTotalPrice(String[] param) {
		return this.spareOutBillDao.showOldScrapTotalPrice(param);
	}
	
	public Double showOldMaintainTotalPrice(String[] param) {
		return this.spareOutBillDao.showOldMaintainTotalPrice(param);
	}
	public List<SpareOutBillDetail> loadAllSpareOutBill(long spareOutBillId) {
		// TODO Auto-generated method stub
		return this.spareOutBillDao.loadAllSpareOutBill(spareOutBillId);
	}
	public List<SpareOutBillDetail> loadAllSpareOutBill(long spareOutBillId,String outDetailids) {
		// TODO Auto-generated method stub
		return this.spareOutBillDao.loadAllSpareOutBill(spareOutBillId, outDetailids);
	}

}
