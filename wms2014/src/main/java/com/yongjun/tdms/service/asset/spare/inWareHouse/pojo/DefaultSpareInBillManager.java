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
package com.yongjun.tdms.service.asset.spare.inWareHouse.pojo;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.HibernateException;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.asset.spare.inWareHouse.SpareInBillDao;
import com.yongjun.tdms.dao.asset.spare.inWareHouse.SpareInBillDetailViewDao;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDetailDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.spare.ProducingAreaType;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SparePropertyType;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailStatus;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailView;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillStatus;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDtlStatus;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;

/**
 * <p>Title:DefaultSpareInBillManager
 * <p>Description:备件入库服务控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class DefaultSpareInBillManager extends BaseManager implements
		SpareInBillManager {
	
	private final SpareInBillDao spareInBillDao;
	private final SequenceManager spareInBillSequenceManager;
	private final SpareDao spareDao;
	private final SpareInBillDetailViewDao spareInBillDetailViewDao;
	private Spare spare;
	private final PurchaseBillDetailDao purchaseBillDetailDao;
	private final PurchaseBillDetailManager purchaseBillDetailManager;
	public DefaultSpareInBillManager(SpareInBillDao spareInBillDao,
									 SequenceManager spareInBillSequenceManager,
									 SpareDao spareDao,
									 SpareInBillDetailViewDao spareInBillDetailViewDao,
									 PurchaseBillDetailDao purchaseBillDetailDao,
									 PurchaseBillDetailManager purchaseBillDetailManager){
		this.spareInBillDao = spareInBillDao;
		this.spareInBillSequenceManager = spareInBillSequenceManager;
		this.spareDao = spareDao;
		this.spareInBillDetailViewDao = spareInBillDetailViewDao;
		this.purchaseBillDetailDao=purchaseBillDetailDao;
		this.purchaseBillDetailManager=purchaseBillDetailManager;
	}
	//编号自动生成
	public void storeSpareInBill(SpareInBill spareInBill) {
		if(spareInBill.isNew()){
			spareInBill.setCode((String)spareInBillSequenceManager.generate("-"));
		}
		this.spareInBillDao.storeSpareInBill(spareInBill);
	}

	public SpareInBill loadSpareInBill(Long spareInBillId) {
		return this.spareInBillDao.loadSpareInBill(spareInBillId);
	}

	public List<SpareInBill> loadAllSpareInBill(Long[] spareInBillIds) {
		return this.spareInBillDao.loadAllSpareInBill(spareInBillIds);
	}

	public void deleteSpareInBill(SpareInBill spareInBill) {
		spareInBillDao.deleteSpareInBill(spareInBill);
	}
    /**
     * 删除入库单
     */
	public void deleteAllSpareInBill(List<SpareInBill> list) {
		spareInBillDao.deleteAllSpareInBill(list);
	}
	
	//	失效入库单时，修改备件台帐中总的库存
	public void disabledAllSpareInBill(List<SpareInBill> spareInBills)throws Exception {
		for(SpareInBill inBill : spareInBills){
			//如果备件入库单的状态为“入库中“或者为”已入库” 则失效失败
			if(inBill.getStatus().equals(SpareInBillStatus.INWAREHOUSEED)||inBill.getStatus().equals(SpareInBillStatus.INWAREHOUSEING)){
				throw new Exception();
			}
			if(inBill.getDetail().size()>0){
				for(SpareInBillDetail detail :inBill.getDetail()){
					if(detail.getNumber()!=null){
						spare = detail.getSpare();
						spare.setStocks(spare.getStocks()-detail.getNumber());		
						spareDao.storeSpare(spare);
					}
		//当失效入库单的时候，如果此入库单明细存在从采购单明细COPY的，还原此入库明细中包含的采购单明细的状态为“已验收”或者为”未验收“
					if(detail.getPoDetail()!=null){
						  if(detail.getPoDetail().getId()!=null){
							  //还原采购单明细中的状态时，要先判断一下入库单明细中状态有没有值
							  if(detail.getStatus()!=null){
								  if(detail.getStatus().equals("INSPECTED")){
									  detail.getPoDetail().setStatus(PurchaseBillDtlStatus.INSPECTED);    
								  }else{
									  detail.getPoDetail().setStatus(PurchaseBillDtlStatus.NEW); 
								  }
							  }
							  purchaseBillDetailDao.storePurchaseBillDetail(detail.getPoDetail());  
							  purchaseBillDetailManager.accordingSpareInWarehouseDetailChangePurchaseBillStatus(detail.getPoDetail());
						  }
					   }
				}
			}
			inBill.setDisabled(true);
			spareInBillDao.storeSpareInBill(inBill);
		}
	}
	//有效入库单时，修改备件台帐中的总的库存
	public void enabledAllSpareInBill(List<SpareInBill> spareInBills) {
		for(SpareInBill inBill : spareInBills){
			if(inBill.getDetail().size()>0){
				for(SpareInBillDetail detail :inBill.getDetail()){
					if(detail.getNumber()!=null){
						spare = detail.getSpare();
						spare.setStocks(spare.getStocks()+detail.getNumber());		
						spareDao.storeSpare(spare);
					}
           //当有效入库单的时候，如果此入库单明细存在从采购单明细COPY的，还原此入库明细中包含的采购单明细的状态为“已入库”
				   if(detail.getPoDetail()!=null){
					   if(detail.getPoDetail().getId()!=null){
						   detail.getPoDetail().setStatus(PurchaseBillDtlStatus.INSPECTED);
							purchaseBillDetailDao.storePurchaseBillDetail(detail.getPoDetail());
							purchaseBillDetailManager.accordingSpareInWarehouseDetailChangePurchaseBillStatus(detail.getPoDetail());
					   }
				   }
				}
			}
			inBill.setDisabled(false);
			spareInBillDao.storeSpareInBill(inBill);
		}		
	}
	/**
	 * 打印报表调用次方法
	 */
	public List<SpareInBillDetailView> loadSpareInBillDetailView(Long id) {
		List<SpareInBillDetailView> spareInBillDetailViewList = this.spareInBillDetailViewDao.loadSpareInBillDetail(id);
		return spareInBillDetailViewList;
	}
	
	/**
	 * 将入库单视图中相同部门、相同库位的相同备件备件进行整理，将数量累加，金额累加，统计出库前后的数量。
	 * 
	 * @param spareInBillDetailViewList
	 * @return spareInBillDetailViewListDisposed
	 */
	private List<SpareInBillDetailView> disposeSpareInBillDetailViewList(List<SpareInBillDetailView> spareInBillDetailViewList){
		return null;
	}
	
	public List<SpareInBillDetailView> loadSpareInBillDetailView(String[] array) throws HibernateException {
		return this.spareInBillDetailViewDao.loadSpareInBillDetailView(array);
	}
    //给据入库单明细的状态改变出库单的状态
	public void  accordingSpareInBillDetailStatusUpdateSpareInStatus(SpareInBill spareInBill){
		  //标识已经入库的循环次数
		  int 	inedLoop=0;
		  //标识未入库的循环次数
		  int unInLoop=0;
		  for(SpareInBillDetail dtl:spareInBill.getDetail()){
			   if(dtl.getInstatus().equals(SpareInBillDetailStatus.NEW)){
				   unInLoop++;
				   continue;
			   }else{
				   inedLoop++;
				   continue;
			   }
		  }
		  if(unInLoop==spareInBill.getDetail().size()){
			  spareInBill.setStatus(SpareInBillStatus.NEWSTATUS);
			  spareInBillDao.storeSpareInBill(spareInBill);
		  }else if(inedLoop==spareInBill.getDetail().size()){
			  spareInBill.setStatus(SpareInBillStatus.INWAREHOUSEED);
			  spareInBillDao.storeSpareInBill(spareInBill);
		  }else{
			  spareInBill.setStatus(SpareInBillStatus.INWAREHOUSEING);
			  spareInBillDao.storeSpareInBill(spareInBill);
		  }
	  }
	
	public Double showTotalPrice(String[] array) {
		return spareInBillDao.showTotalPrice(array);
	}
	
	public Double showOldTotalPrice(String[] array) {
		return spareInBillDao.showOldTotalPrice(array);
	}
	
	/**
	 * @function 打印验收单后，改变标识为true
	 * @author wliu
	 * @param spareInBillId 入库单标识
	 */
	public void saveAcceptanceList(String spareInBillId){
		Long sib_id = Long.valueOf(spareInBillId);
		SpareInBill sib = this.spareInBillDao.loadSpareInBill(sib_id);
		sib.setAcceptanceList(true);
		this.spareInBillDao.storeSpareInBill(sib);
	}
	public List<SpareInBill> loadByKey(String key, Object value) throws DaoException {
		return spareInBillDao.loadByKey(key, value);
	}
	public void storeAllSpareInBillDetail(List<SpareInBillDetail> list) {
		// TODO Auto-generated method stub
		
		if(list!=null){
			for(SpareInBillDetail inDetail:list){
				//根据旧件编码查询是否存在
			Spare spare = this.spareDao.loadByNameAndModel(inDetail.getName(),inDetail.getSpare().getModelSpecs());	//this.spareDao.loadBySpareNo(inDetail.getCode());
			
			if(spare==null){//不存在。那么将入库前数量设为0并且向旧件库明细台账插入一条新的记录
				inDetail.setStocksBeforeInOrOut(0l);
				Spare xjspare  = inDetail.getSpare();
				Spare jjspare = new Spare();
				jjspare.setToolingDevFlag(xjspare.getToolingDevFlag());
				jjspare.setModelSpecs(xjspare.getModelSpecs());
				jjspare.setPropertyType(xjspare.getPropertyType());
				jjspare.setProducingAreaType(xjspare.getProducingAreaType());
				jjspare.setDisabled(false);
				jjspare.setTenderPartFlag(true);
				jjspare.setWearingPartFlag(true);
				jjspare.setHeavyRepairPartFlag(true);
				SpareType st= new SpareType();
				st.setId(32l);
				jjspare.setCategory(st);
				jjspare.setSpareDetailType(xjspare.getSpareDetailType());
				jjspare.setUnit(xjspare.getUnit());
				jjspare.setSupplier(xjspare.getSupplier());
				jjspare.setFactory(xjspare.getFactory());
				jjspare.setSafeStock(Long.valueOf(0));
				jjspare.setStocks(Long.valueOf(0));
				jjspare.setOldSpare("0");
				jjspare.setName(xjspare.getName());
//				String spareNo  = generateSpareNo(jjspare);
				jjspare.setSpareNo(inDetail.getSpare().getSpareNo());
				this.spareDao.storeSpare(jjspare);
				inDetail.setCode(inDetail.getSpare().getSpareNo());
				inDetail.setSpare(jjspare);
			}else{//如果存在那么就直接插入
				inDetail.setSpare(spare);
				
			}
				this.spareInBillDao.storeSpareInBillDetail(inDetail);
			}
		}
	}
	
	public void storeFalseSpareInBillDetail(SpareInBillDetail detail) {
		// TODO Auto-generated method stub
				detail.setStocksBeforeInOrOut(0l);
				//模拟插入一条备件
				Spare xjspare = this.spareDao.loadByFirst();
				if(xjspare!=null){
				xjspare.setSpareNo("");
				xjspare.setName("");
				xjspare.setModelSpecs("");
				detail.setSpare(xjspare);
				this.spareInBillDao.storeSpareInBillDetail(detail);
				}
	}
	
	/**
	 * 生成备件编码：按照规则生成，例如：SBD00-00021 SBD：表示备件类别 00：表示型号类别，即明细分类
	 * 00021：顺序码，从00001~99999顺序增加
	 * 
	 * @param spare
	 * @return String 备件编码
	 */
	private String generateSpareNo(Spare spare) {
		String categoryCode = "", // 类别代码
		detailCategoryCode = ""; // 明细类别代码
		if (null != spare.getCategory()) {
			categoryCode ="JJ"; //spare.getCategory().getCode();
		}
//		if (null != spare.getSpareDetailType()) {
//			detailCategoryCode = spare.getSpareDetailType().getCode();
//		}
		/*
		 * 如果已存在备件编码的备件，更改了备件类别或分细分类，则修改备件编码：
		 * 1）如果新备件类别、新明细分类加上老的顺序码在备件中不存在，就使用改编码
		 * 2）如果新备件类别、新明细分类加上老的顺序码在备件中已存在，则重新生成
		 */
		if (null != spare.getSpareNo()) {
			String[] spareNoBySeparated = spare.getSpareNo().split("-");
			//if (spareNoBySeparated[0].equals(categoryCode + detailCategoryCode)) {
			if (spareNoBySeparated[0].equals(categoryCode)) {
				return spare.getSpareNo();
			} 
			
//			else {
//				//spareNoBySeparated[0] = categoryCode + detailCategoryCode;
//				spareNoBySeparated[0] = categoryCode;
//				String newSpareNo = (new StringBuffer()).append(
//						spareNoBySeparated[0]).append("-").append(
//						spareNoBySeparated[1]).toString();
//				if (null == this.spareDao.getSpareNumBySpareNo(newSpareNo)) {
//					return newSpareNo;
//				}
//
//			}
		}
//		String maxSpareNo = this.spareDao.getMaxSpareNoBySpareCode(categoryCode
//				+ detailCategoryCode + "-_____");
		String maxSpareNo = this.spareDao.getMaxSpareNoBySpareCode(categoryCode
		 + "-______");
		return parseAndCalculateSpareNo(categoryCode, detailCategoryCode,
				maxSpareNo);

	}

	/**
	 * 生产备件编码
	 * 
	 * @param categoryCode
	 *            类别代码
	 * @param detailCategoryCode
	 *            明细类别代码
	 * @param maxSpareNo
	 *            最大备件代码
	 * @return String 最大备件编码
	 */
	private String parseAndCalculateSpareNo(String categoryCode,
			String detailCategoryCode, String maxSpareNo) {
		String pattern = "000000";
		Format formatter = NumberFormat.getIntegerInstance();
		formatter = new DecimalFormat(pattern);
		if (null == maxSpareNo) {
			Long n = NumberUtils.createLong(pattern) + 1L;
//			return (new StringBuffer()).append(categoryCode).append(
//					detailCategoryCode).append("-").append(formatter.format(n))
//					.toString();
			return (new StringBuffer()).append(categoryCode).append("-").append(formatter.format(n))
					.toString();
		}
		logger.debug("spare category code is : [" + categoryCode + "]");
		String spareNoSequenceCode = maxSpareNo.substring((maxSpareNo
				.indexOf("-") + 1));
		logger.debug("spare no sequence code is : [" + spareNoSequenceCode
				+ "]");
		Long n = NumberUtils.createLong(spareNoSequenceCode) + 1L;
//		return (new StringBuffer()).append(categoryCode).append(
//				detailCategoryCode).append("-").append(formatter.format(n))
//				.toString();
		return (new StringBuffer()).append(categoryCode).append("-").append(formatter.format(n))
				.toString();
	}
	
	
}
