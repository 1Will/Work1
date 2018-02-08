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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;

import com.yongjun.pluto.dao.security.UserDao;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.asset.spare.inWareHouse.SpareInBillDetailDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDetailDao;
import com.yongjun.tdms.dao.asset.spare.sparelocation.SpareLocationDao;
import com.yongjun.tdms.dao.runmaintenance.spareBorrow.SpareBorrowDetailDao;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetailStatus;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillStatus;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetail;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetailStatus;
import com.yongjun.tdms.service.asset.spare.location.LocationManager;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillDetailManager;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillManager;
import com.yongjun.tdms.service.asset.spare.spareLocation.SpareLocationManager;
import com.yongjun.tdms.service.asset.spare.spareWareHouse.SpareWareHouseManager;
import com.yongjun.tdms.service.runmaintenance.spareBorrow.SpareBorrowDetailManager;
/**
 * <p>Title: DefaultSpareOutBillDetailManager
 * <p>Description: 备件出库明细业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhang@yj-technology.com
 * @see com.yongjun.tdms.service.asset.spare.outWareHouse.pojo.DefaultSpareOutBillDetailManager
 * @version $Id:$
 */
public class DefaultSpareOutBillDetailManager implements
		SpareOutBillDetailManager {
	
	private final SpareOutBillDetailDao spareOutBillDetailDao;
	private final SpareDao spareDao;
	private final SpareOutBillManager spareOutBillManager;
	private final SpareBorrowDetailDao  spareBorrowDetailDao;
	private final SpareBorrowDetailManager spareBorrowDetailManager;
	private final SpareLocationManager spareLocationManager;
	private final SpareLocationDao spareLocationDao;
	private final LocationManager locationManager;
	private final SpareInBillDetailDao spareInBillDetailDao;
	private final SpareOutBillDao spareOutBillDao;
	private final UserDao userDao;
	private final SpareWareHouseManager spareWareHouseManager; 
	
	
	public DefaultSpareOutBillDetailManager(SpareOutBillDetailDao spareOutBillDetailDao,
			SpareDao spareDao,SpareOutBillManager spareOutBillManager,
			SpareBorrowDetailDao  spareBorrowDetailDao,
			SpareBorrowDetailManager spareBorrowDetailManager,
			SpareLocationManager spareLocationManager,
			SpareLocationDao spareLocationDao,
			LocationManager locationManager,
			SpareInBillDetailDao spareInBillDetailDao,
			SpareOutBillDao spareOutBillDao,
			UserDao userDao,
			SpareWareHouseManager spareWareHouseManager){
		this.spareOutBillDetailDao = spareOutBillDetailDao;
		this.spareDao = spareDao;
		this.spareOutBillManager=spareOutBillManager;
		this.spareBorrowDetailDao=spareBorrowDetailDao;
		this.spareBorrowDetailManager=spareBorrowDetailManager;
		this.spareLocationManager = spareLocationManager;
		this.spareLocationDao = spareLocationDao;
		this.locationManager = locationManager;
		this.spareInBillDetailDao=spareInBillDetailDao;
		this.spareOutBillDao =spareOutBillDao;
		this.userDao = userDao;
		this.spareWareHouseManager = spareWareHouseManager;
	}
	
	public List<SpareOutBillDetail> loadAllSpareOutBillDetail(
			Long[] spareOutBillDetailIds) {
		return spareOutBillDetailDao.loadAllSpareOutBillDetail(spareOutBillDetailIds);
	}

	public SpareOutBillDetail loadSpareOutBillDetail(Long spareOutBillDetailId) {
		return spareOutBillDetailDao.loadSpareOutBillDetail(spareOutBillDetailId);
	}

	public void storeSpareOutBillDetail(SpareOutBillDetail spareOutBillDetail) {
		
		spareOutBillDetailDao.storeSpareOutBillDetail(spareOutBillDetail);
	}

	public void deleteSpareOutBillDetail(SpareOutBillDetail spareOutBillDetail) {
		spareOutBillDetailDao.deleteSpareOutBillDetail(spareOutBillDetail);
	}
	/**
	 * 将选中的备件台帐添加到出库明细中
	 */
	public void addSpareOutBillDetail(String ids,SpareOutBill spareOutBill,User LoginUser){
		String [] spareOutBillDetailIds = null;

		SpareLocation spareLocation = null;
		if(ids != null){
			//被选中备件台帐的ID集合
			spareOutBillDetailIds = ids.split(",");
		}
		for(int i = 0 ; i < spareOutBillDetailIds.length ; i++){
			//根据每个ID获得每个备件
			//spare = spareManager.loasSpare(Long.valueOf(spareOutBillDetailIds[i]));
			spareLocation = spareLocationManager.loadSpareLocation(Long.valueOf(spareOutBillDetailIds[i]));
			SpareOutBillDetail spareOutBillDetail = new SpareOutBillDetail();
			spareOutBillDetail.setCode(spareLocation.getSpare().getSpareNo());
			spareOutBillDetail.setName(spareLocation.getSpare().getName());
			
			if(null!=spareLocation){
				if(null != spareLocation.getLocationCode()){
					spareOutBillDetail.setLocationCode(spareLocation.getLocationCode());
				} 
				
				if(null != spareLocation.getLocation()){
					spareOutBillDetail.setLocation(spareLocation.getLocation());
					spareOutBillDetail.setWarehouse(spareLocation.getWarehouse());
					spareOutBillDetail.setRegional(spareLocation.getRegional());
				} 
				
				if(null != spareLocation.getDepartment()){
					spareOutBillDetail.setDepartment(spareLocation.getDepartment());   //设置出库部门
				} 
				spareOutBillDetail.setSpareLocation(spareLocation);
				
				spareOutBillDetail.setStocksBeforeInOrOut(spareLocation.getStocks());
				spareOutBillDetail.setUnitPrice(spareLocation.getUnitPrice());
				spareOutBillDetail.setSpare(spareLocation.getSpare());
				
				
			}
			 
			
			spareOutBillDetail.setNumber(new Long(0));
			spareOutBillDetail.setStocksAfterInOrOut(new Long(0));
			spareOutBillDetail.setTotalPrice(new Double(0));
			spareOutBillDetail.setSpareOutBill(spareOutBill);
			spareOutBillDetail.getSpareOutBill().setSubmit(true);
			spareOutBillDetail.setOutDate(new Date());
			spareOutBillDetail.setOutPeople(LoginUser);
			spareOutBillDetail.setNewOrOld(false);
			
		 
			
			this.storeSpareOutBillDetail(spareOutBillDetail);
			//同步更新出库单状态
			this.updateSpareOutBillStatus(spareOutBill);
            //调用给据出库单明细的状态改变出库单的状态
			spareOutBillManager.accordingSpareOutBillDetailStatusUpdateSpareOutStatus(spareOutBillDetail.getSpareOutBill());
		}
	}
	/**
	 * 将选中的备件台帐添加到出库明细中
	 */
	public void addAutoSpareOutBillDetail(String ids,SpareOutBill spareOutBill,User LoginUser){
		String [] spareOutBillDetailIds = null;

		SpareLocation spareLocation = null;
		if(ids != null){
			//被选中备件台帐的ID集合
			spareOutBillDetailIds = ids.split(",");
		}
		for(int i = 0 ; i < spareOutBillDetailIds.length ; i++){
			//根据每个ID获得每个备件
			//spare = spareManager.loasSpare(Long.valueOf(spareOutBillDetailIds[i]));
			spareLocation = spareLocationManager.loadSpareLocation(Long.valueOf(spareOutBillDetailIds[i]));
			SpareOutBillDetail spareOutBillDetail = new SpareOutBillDetail();
			spareOutBillDetail.setCode(spareLocation.getSpare().getSpareNo());
			spareOutBillDetail.setName(spareLocation.getSpare().getName());
			
			if(null!=spareLocation){
				if(null != spareLocation.getLocationCode()){
					spareOutBillDetail.setLocationCode(spareLocation.getLocationCode());
				} 
				
				if(null != spareLocation.getLocation()){
					spareOutBillDetail.setLocation(spareLocation.getLocation());
					spareOutBillDetail.setWarehouse(spareLocation.getWarehouse());
					spareOutBillDetail.setRegional(spareLocation.getRegional());
				} 
				
				if(null != spareLocation.getDepartment()){
					spareOutBillDetail.setDepartment(spareLocation.getDepartment());   //设置出库部门
				} 
				spareOutBillDetail.setSpareLocation(spareLocation);
				
				spareOutBillDetail.setStocksBeforeInOrOut(spareLocation.getStocks());
				spareOutBillDetail.setUnitPrice(spareLocation.getUnitPrice());
				spareOutBillDetail.setSpare(spareLocation.getSpare());
				
				
			}
			 
			
			spareOutBillDetail.setNumber(spareLocation.getSpare().getDisableStocks());
			spareOutBillDetail.setBorrowerPeople(spareOutBill.getOutPeople().getName());
			spareOutBillDetail.setStocksAfterInOrOut(new Long(0));
			spareOutBillDetail.setTotalPrice(new Double(0));
			spareOutBillDetail.setSpareOutBill(spareOutBill);
			spareOutBillDetail.getSpareOutBill().setSubmit(true);
			spareOutBillDetail.setOutDate(new Date());
			spareOutBillDetail.setOutPeople(LoginUser);
			spareOutBillDetail.setNewOrOld(false);
			
		 
			
			this.storeSpareOutBillDetail(spareOutBillDetail);
			//同步更新出库单状态
			this.updateSpareOutBillStatus(spareOutBill);
            //调用给据出库单明细的状态改变出库单的状态
			spareOutBillManager.accordingSpareOutBillDetailStatusUpdateSpareOutStatus(spareOutBillDetail.getSpareOutBill());
		}
	}
	/**
	 * 修改保存出库明细中
	 */
	 public void saveSpareOutBillDetail(String allDetailIds,
			 String allNumber,
			 String allborrowerPeople,
			 String allNewOrOld,
			 String allOutDate,
			 String allComment,
			 String allOutPeopleIds,
			 String allSheBei,
			 String allBanZu){
		 String [] strAllDetailIds = null;
		 String [] strAllNumbers = null;
		 String [] strAllborrowerPeoples = null;
		 String [] strAllNewOrOlds = null;
		 String [] strAllOutDates = null;
		 String [] strAllComments = null;
		 String [] strAllOutPeoples  = null;
		 String [] strAllSheBei = null;
		 String [] strAllBanZu = null;
		 
		//将传来的字符串拆分，保存到数组中
		if(null != allDetailIds){
			  strAllDetailIds = allDetailIds.split(",");
		}
		if(null != allNumber){
			  strAllNumbers = allNumber.split(",");
		}
	    if(null !=allborrowerPeople ){
	    	 strAllborrowerPeoples = allborrowerPeople.split(",");
	    }
	    if(null !=allNewOrOld){
	    	  strAllNewOrOlds = allNewOrOld.split(",");
	    }
		if(null != allOutDate){
			strAllOutDates = allOutDate.split(",");
		}
		if(null != allComment){
			strAllComments = allComment.split(",");
		}
		if(null != allOutPeopleIds){
			strAllOutPeoples = allOutPeopleIds.split(",");
		}
		if(null != allSheBei){
			strAllSheBei = allSheBei.split(",");
		}
		if(null != allBanZu){
			strAllBanZu = allBanZu.split(",");
		}
		
		
		
		
		this.updateAllSpareOutBillDetals(strAllDetailIds,strAllNumbers, strAllborrowerPeoples, strAllNewOrOlds, 
				strAllOutDates, strAllComments, strAllOutPeoples,strAllSheBei,strAllBanZu);

	}
	 
	 /**
		 * 修改保存出库明细中 包含了设备编号和备件用途
		 */
		 public void saveSpareOutBillDetail(String allDetailIds,
				 String allNumber,
				 String allborrowerPeople,
				 String allNewOrOld,
				 String allOutDate,
				 String allComment,
				 String allOutPeopleIds,
				 String allSheBei,
				 String allBanZu,String allSheBeiNo,String allUseTypes){
			 String [] strAllDetailIds = null;
			 String [] strAllNumbers = null;
			 String [] strAllborrowerPeoples = null;
			 String [] strAllNewOrOlds = null;
			 String [] strAllOutDates = null;
			 String [] strAllComments = null;
			 String [] strAllOutPeoples  = null;
			 String [] strAllSheBei = null;
			 String [] strAllBanZu = null;
			 String [] strAllSheBeiNo = null;
			 String [] strAllUseTypes = null;
			 
			//将传来的字符串拆分，保存到数组中
			if(null != allDetailIds){
				  strAllDetailIds = allDetailIds.split(",");
			}
			if(null != allNumber){
				  strAllNumbers = allNumber.split(",");
			}
		    if(null !=allborrowerPeople ){
		    	 strAllborrowerPeoples = allborrowerPeople.split(",");
		    }
		    if(null !=allNewOrOld){
		    	  strAllNewOrOlds = allNewOrOld.split(",");
		    }
			if(null != allOutDate){
				strAllOutDates = allOutDate.split(",");
			}
			if(null != allComment){
				strAllComments = allComment.split(",");
			}
			if(null != allOutPeopleIds){
				strAllOutPeoples = allOutPeopleIds.split(",");
			}
			if(null != allSheBei){
				strAllSheBei = allSheBei.split(",");
			}
			if(null != allBanZu){
				strAllBanZu = allBanZu.split(",");
			}
			
			if(null != allSheBeiNo){
				strAllSheBeiNo = allSheBeiNo.split(",");
			}
			if(null != allUseTypes){
				strAllUseTypes = allUseTypes.split(",");
			}
			
			
			
			
			this.updateAllSpareOutBillDetals(strAllDetailIds,strAllNumbers, strAllborrowerPeoples, strAllNewOrOlds, 
					strAllOutDates, strAllComments, strAllOutPeoples,strAllSheBei,strAllBanZu,strAllSheBeiNo,strAllUseTypes);

		}
	 /**
		 * 修改保存出库明细中
		 */
		 public void saveSpareOutBillDetailHaveDisable(String allDetailIds,
				 String allNumber,
				 String allborrowerPeople,
				 String allNewOrOld,
				 String allOutDate,
				 String allComment,
				 String allOutPeopleIds,
				 String allSheBei,
				 String allBanZu){
			 String [] strAllDetailIds = null;
			 String [] strAllNumbers = null;
			 String [] strAllborrowerPeoples = null;
			 String [] strAllNewOrOlds = null;
			 String [] strAllOutDates = null;
			 String [] strAllComments = null;
			 String [] strAllOutPeoples  = null;
			 String [] strAllSheBei = null;
			 String [] strAllBanZu = null;
			 
			//将传来的字符串拆分，保存到数组中
			if(null != allDetailIds){
				  strAllDetailIds = allDetailIds.split(",");
			}
			if(null != allNumber){
				  strAllNumbers = allNumber.split(",");
			}
		    if(null !=allborrowerPeople ){
		    	 strAllborrowerPeoples = allborrowerPeople.split(",");
		    }
		    if(null !=allNewOrOld){
		    	  strAllNewOrOlds = allNewOrOld.split(",");
		    }
			if(null != allOutDate){
				strAllOutDates = allOutDate.split(",");
			}
			if(null != allComment){
				strAllComments = allComment.split(",");
			}
			if(null != allOutPeopleIds){
				strAllOutPeoples = allOutPeopleIds.split(",");
			}
			if(null != allSheBei){
				strAllSheBei = allSheBei.split(",");
			}
			if(null != allBanZu){
				strAllBanZu = allBanZu.split(",");
			}
			
			
			
			
			this.updateAllSpareOutBillDetalsHaveDisable(strAllDetailIds,strAllNumbers, strAllborrowerPeoples, strAllNewOrOlds, 
					strAllOutDates, strAllComments, strAllOutPeoples,strAllSheBei,strAllBanZu);

		}
	/**
	 * 保存修改出库单明细
	 * @param strAllNumbers 所有出库数量
	 * @param strAllborrowerPeoples 所有领料人
	 * @param strAllNewOrOlds  所有以旧换新
	 * @param strAllOutDates 所有出库日期
	 * @param strAllComments 所有备注
	 * @param strAllOutPeoples 所有出库人
	 */
	private void updateAllSpareOutBillDetals(
			String [] strAllDetailIds,
			String [] strAllNumbers,
			String [] strAllborrowerPeoples,
			String [] strAllNewOrOlds,
			String [] strAllOutDates ,
			String [] strAllComments ,
			String [] strAllOutPeoples,
			String [] strAllSheBei,
			String [] strAllBanZu){
		

		SpareOutBillDetail detail = new SpareOutBillDetail();
		int spareOutBillDetailNum = 0;
		Long number;
		while(strAllDetailIds != null && spareOutBillDetailNum<strAllDetailIds.length){
			detail = spareOutBillDetailDao.loadSpareOutBillDetail(Long.parseLong(strAllDetailIds[spareOutBillDetailNum]));
			if (detail.getStatus().equals(SpareOutBillDetailStatus.OUTWAREHOUSEED)) {
				spareOutBillDetailNum++;
				continue;
			}
			//保存出库数量
			if(null != strAllNumbers){
				Map idsAndOutSpareNumber = new HashMap<Long, Long>();
				for(int i=0;i<strAllNumbers.length; i=i+2){
					if(strAllNumbers[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						number = Long.valueOf(strAllNumbers[i+1]);
						detail.setNumber(number);
						//总价的计算
						detail.setTotalPrice(detail.getUnitPrice()*number);
						//出库后库存量的计算，（此处不能用明细表中的数据）。
						detail.setStocksAfterInOrOut(spareLocationDao.loadSpareLocation(detail.getSpareLocation().getId()).getStocks()-number);
						//出库前库存量的计算，（此处也不能用明细表中的数据）。
						detail.setStocksBeforeInOrOut(spareLocationDao.loadSpareLocation(detail.getSpareLocation().getId()).getStocks());
						SpareLocation spareLocation = detail.getSpareLocation();
						if(null != spareLocation){
							this.updateSpareLocationStock(spareLocation, detail);
						}else{
							this.setLocationStock(detail.getLocation(), detail);
						}
						
						break;
					}
				}
			}
			//保存领料人
			if(null != strAllborrowerPeoples){
				for(int i=0;i<strAllborrowerPeoples.length; i=i+2){
					if(strAllborrowerPeoples[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						detail.setBorrowerPeople(strAllborrowerPeoples[i+1]);
						break;
					}
				}
			}
//			//保存以旧换新
//			if(null != strAllNewOrOlds){
//				for(int i=0;i<strAllNewOrOlds.length; i=i+2){
//					if(strAllNewOrOlds[i].equals(strAllDetailIds[spareOutBillDetailNum])){
//						String str = strAllNewOrOlds[i+1];
//						detail.setNewOrOld(Boolean.valueOf(str));
//						break;
//					}
//				}
//			}
			detail.setNewOrOld(Boolean.valueOf("false"));
			//保存出库日期
			if(null != strAllOutDates){
				for(int i=0;i<strAllOutDates.length; i=i+2){
					if(strAllOutDates[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						 detail.setOutDate(DateUtil.toDate(strAllOutDates[i+1],"yyyy-MM-dd"));
						break;
					}
				}
			}
			//保存备注
			if(null != strAllComments){
				for(int i=0;i<strAllComments.length; i=i+2){
					if(strAllComments[i].equals(strAllDetailIds[spareOutBillDetailNum])){
					   if(null != strAllComments[i+1]){
						   detail.setComment(strAllComments[i+1]);
					   }
						break;
					}
				}
			}
			//保存出库人
			if(null != strAllOutPeoples){
				for(int i=0;i<strAllOutPeoples.length; i=i+2){
					if(strAllOutPeoples[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						 detail.setOutPeople(this.userDao.loadUser(Long.valueOf(strAllOutPeoples[i+1])));
						break;
					}
				}
			}//设备
			if(null != strAllSheBei){
				for(int i=0;i<strAllSheBei.length;i=i+2){
					if(strAllSheBei[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						if(null != strAllSheBei[i+1]){
							detail.setShebei(strAllSheBei[i+1]);
						}
						
						break;
					}
				}
				
			}
			//班组
			if(null != strAllBanZu){
				for(int i=0;i<strAllBanZu.length;i=i+2){
					if(strAllBanZu[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						if(null !=strAllBanZu[i+1]){
							detail.setBanzu(strAllBanZu[i+1]);
						}
						   break;
                         
					}
				}
				
			}
			
			detail.setStatus(SpareOutBillDetailStatus.OUTWAREHOUSEED);
			detail.getSpareOutBill().setSubmit(true);
			this.spareOutBillDetailDao.storeSpareOutBillDetail(detail);
			   //更新出库单总金额
			 updateSpareOutBill_totalPrice(detail.getSpareOutBill());
			//同步更新 出库单 状态
			this.updateSpareOutBillStatus(detail.getSpareOutBill());
			 //更新库位状态
			 locationManager.updateLocationStatusToNoUse(detail.getLocation());
			spareOutBillDetailNum++;
			
		}
		
		
         
		
		 	
		 
		/**	spareOutBillManager.accordingSpareOutBillDetailStatusUpdateSpareOutStatus(spareOutBillDetail.getSpareOutBill());
			
			//如果出库单明细中存在从领用单明细的对象 在改变此领用单明细的对象的状态
			if(spareOutBillDetail.getDtl()!=null){
				spareOutBillDetail.getDtl().setStatus(SpareBorrowDetailStatus.BORROWED);
				spareBorrowDetailDao.storeSpareBorrowDetail(spareOutBillDetail.getDtl());
				//在改变备件领用单明细的状态的同时   也改变备件领用单的状态
				spareBorrowDetailManager.updateSpareBorrowStatusBySpareBorrowDtlStatus(spareOutBillDetail.getDtl());
			}
			
			spareOutBillManager.storeSpareOutBill(spareOutBillDetail.getSpareOutBill());
			//如果出库单明细中的记录是从备件领用单明细选择过来的  那么保存出库单明细之后把出库数量写回此出库单明细所关联的备件领用单明细的实际出库数量
			if(spareOutBillDetail.getDtl()!=null){
				spareOutBillDetail.getDtl().setTotalStock(spareOutBillDetail.getNumber());
				this.spareBorrowDetailDao.storeSpareBorrowDetail(spareOutBillDetail.getDtl());
			}
		 */
		
		
		
		
		
	}
	/**
	 * 
	 * @param strAllDetailIds
	 * @param strAllNumbers
	 * @param strAllborrowerPeoples
	 * @param strAllNewOrOlds
	 * @param strAllOutDates
	 * @param strAllComments
	 * @param strAllOutPeoples
	 * @param strAllSheBei
	 * @param strAllBanZu
	 * @param strAllSheBeiNo 设备编码
	 * @param strAllUseTypes 备件用途
	 */
	private void updateAllSpareOutBillDetals(
			String [] strAllDetailIds,
			String [] strAllNumbers,
			String [] strAllborrowerPeoples,
			String [] strAllNewOrOlds,
			String [] strAllOutDates ,
			String [] strAllComments ,
			String [] strAllOutPeoples,
			String [] strAllSheBei,
			String [] strAllBanZu,String [] strAllSheBeiNo,
			String [] strAllUseTypes){
		

		SpareOutBillDetail detail = new SpareOutBillDetail();
		int spareOutBillDetailNum = 0;
		Long number;
		while(strAllDetailIds != null && spareOutBillDetailNum<strAllDetailIds.length){
			detail = spareOutBillDetailDao.loadSpareOutBillDetail(Long.parseLong(strAllDetailIds[spareOutBillDetailNum]));
			if (detail.getStatus().equals(SpareOutBillDetailStatus.OUTWAREHOUSEED)) {
				spareOutBillDetailNum++;
				continue;
			}
			//保存出库数量
			if(null != strAllNumbers){
				Map idsAndOutSpareNumber = new HashMap<Long, Long>();
				for(int i=0;i<strAllNumbers.length; i=i+2){
					if(strAllNumbers[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						number = Long.valueOf(strAllNumbers[i+1]);
						detail.setNumber(number);
						//总价的计算
						detail.setTotalPrice(detail.getUnitPrice()*number);
						//出库后库存量的计算，（此处不能用明细表中的数据）。
						detail.setStocksAfterInOrOut(spareLocationDao.loadSpareLocation(detail.getSpareLocation().getId()).getStocks()-number);
						//出库前库存量的计算，（此处也不能用明细表中的数据）。
						detail.setStocksBeforeInOrOut(spareLocationDao.loadSpareLocation(detail.getSpareLocation().getId()).getStocks());
						SpareLocation spareLocation = detail.getSpareLocation();
						if(null != spareLocation){
							this.updateSpareLocationStock(spareLocation, detail);
						}else{
							this.setLocationStock(detail.getLocation(), detail);
						}
						
						break;
					}
				}
			}
			//保存领料人
			if(null != strAllborrowerPeoples){
				for(int i=0;i<strAllborrowerPeoples.length; i=i+2){
					if(strAllborrowerPeoples[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						detail.setBorrowerPeople(strAllborrowerPeoples[i+1]);
						break;
					}
				}
			}
//			//保存以旧换新
//			if(null != strAllNewOrOlds){
//				for(int i=0;i<strAllNewOrOlds.length; i=i+2){
//					if(strAllNewOrOlds[i].equals(strAllDetailIds[spareOutBillDetailNum])){
//						String str = strAllNewOrOlds[i+1];
//						detail.setNewOrOld(Boolean.valueOf(str));
//						break;
//					}
//				}
//			}
			detail.setNewOrOld(Boolean.valueOf("false"));
			//保存出库日期
			if(null != strAllOutDates){
				for(int i=0;i<strAllOutDates.length; i=i+2){
					if(strAllOutDates[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						 detail.setOutDate(DateUtil.toDate(strAllOutDates[i+1],"yyyy-MM-dd"));
						break;
					}
				}
			}
			//保存备注
			if(null != strAllComments){
				for(int i=0;i<strAllComments.length; i=i+2){
					if(strAllComments[i].equals(strAllDetailIds[spareOutBillDetailNum])){
					   if(null != strAllComments[i+1]){
						   detail.setComment(strAllComments[i+1]);
					   }
						break;
					}
				}
			}
			//保存出库人
			if(null != strAllOutPeoples){
				for(int i=0;i<strAllOutPeoples.length; i=i+2){
					if(strAllOutPeoples[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						 detail.setOutPeople(this.userDao.loadUser(Long.valueOf(strAllOutPeoples[i+1])));
						break;
					}
				}
			}//设备
			if(null != strAllSheBei){
				for(int i=0;i<strAllSheBei.length;i=i+2){
					if(strAllSheBei[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						if(null != strAllSheBei[i+1]){
							detail.setShebei(strAllSheBei[i+1]);
						}
						
						break;
					}
				}
				
			}
			//设备编号
			if(null != strAllSheBeiNo){
				for(int i=0;i<strAllSheBeiNo.length;i=i+2){
					if(strAllSheBeiNo[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						if(null != strAllSheBeiNo[i+1]){
							detail.setShebeiNo(strAllSheBeiNo[i+1]);
						}
						
						break;
					}
				}
				
			}
			//备件用途
			if(null != strAllUseTypes){
				for(int i=0;i<strAllUseTypes.length;i=i+2){
					if(strAllUseTypes[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						if(null != strAllUseTypes[i+1]){
							detail.setUseTypes(strAllUseTypes[i+1]);
						}
						
						break;
					}
				}
				
			}
			
			
			//班组
			if(null != strAllBanZu){
				for(int i=0;i<strAllBanZu.length;i=i+2){
					if(strAllBanZu[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						if(null !=strAllBanZu[i+1]){
							detail.setBanzu(strAllBanZu[i+1]);
						}
						   break;
                         
					}
				}
				
			}
			
			detail.setStatus(SpareOutBillDetailStatus.OUTWAREHOUSEED);
			detail.getSpareOutBill().setSubmit(true);
			this.spareOutBillDetailDao.storeSpareOutBillDetail(detail);
			   //更新出库单总金额
			 updateSpareOutBill_totalPrice(detail.getSpareOutBill());
			//同步更新 出库单 状态
			this.updateSpareOutBillStatus(detail.getSpareOutBill());
			 //更新库位状态
			 locationManager.updateLocationStatusToNoUse(detail.getLocation());
			spareOutBillDetailNum++;
			
		}
		
		
         
		
		 	
		
		
		
	}
	
	/**
	 * 保存修改出库单明细
	 * @param strAllNumbers 所有出库数量
	 * @param strAllborrowerPeoples 所有领料人
	 * @param strAllNewOrOlds  所有以旧换新
	 * @param strAllOutDates 所有出库日期
	 * @param strAllComments 所有备注
	 * @param strAllOutPeoples 所有出库人
	 */
	private void updateAllSpareOutBillDetalsHaveDisable(
			String [] strAllDetailIds,
			String [] strAllNumbers,
			String [] strAllborrowerPeoples,
			String [] strAllNewOrOlds,
			String [] strAllOutDates ,
			String [] strAllComments ,
			String [] strAllOutPeoples,
			String [] strAllSheBei,
			String [] strAllBanZu){
		

		SpareOutBillDetail detail = new SpareOutBillDetail();
		int spareOutBillDetailNum = 0;
		Long number;
		while(strAllDetailIds != null && spareOutBillDetailNum<strAllDetailIds.length){
			detail = spareOutBillDetailDao.loadSpareOutBillDetail(Long.parseLong(strAllDetailIds[spareOutBillDetailNum]));
			if (detail.getStatus().equals(SpareOutBillDetailStatus.OUTWAREHOUSEED)) {
				spareOutBillDetailNum++;
				continue;
			}
			//保存出库数量
			if(null != strAllNumbers){
				Map idsAndOutSpareNumber = new HashMap<Long, Long>();
				for(int i=0;i<strAllNumbers.length; i=i+2){
					if(strAllNumbers[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						number = Long.valueOf(strAllNumbers[i+1]);
						detail.setNumber(number);
						//总价的计算
						detail.setTotalPrice(detail.getUnitPrice()*number);
						//出库后库存量的计算，（此处不能用明细表中的数据）。
						detail.setStocksAfterInOrOut(spareLocationDao.loadSpareLocation(detail.getSpareLocation().getId()).getStocks()-number);
						//出库前库存量的计算，（此处也不能用明细表中的数据）。
						detail.setStocksBeforeInOrOut(spareLocationDao.loadSpareLocation(detail.getSpareLocation().getId()).getStocks());
						SpareLocation spareLocation = detail.getSpareLocation();
						if(null != spareLocation){
							this.updateSpareLocationStockHaveDisable(spareLocation, detail);
						}else{
							this.setLocationStockHaveDisable(detail.getLocation(), detail);
						}
						
						break;
					}
				}
			}
			//保存领料人
			if(null != strAllborrowerPeoples){
				for(int i=0;i<strAllborrowerPeoples.length; i=i+2){
					if(strAllborrowerPeoples[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						detail.setBorrowerPeople(strAllborrowerPeoples[i+1]);
						break;
					}
				}
			}
//			//保存以旧换新
//			if(null != strAllNewOrOlds){
//				for(int i=0;i<strAllNewOrOlds.length; i=i+2){
//					if(strAllNewOrOlds[i].equals(strAllDetailIds[spareOutBillDetailNum])){
//						String str = strAllNewOrOlds[i+1];
//						detail.setNewOrOld(Boolean.valueOf(str));
//						break;
//					}
//				}
//			}
			detail.setNewOrOld(Boolean.valueOf("false"));
			//保存出库日期
			if(null != strAllOutDates){
				for(int i=0;i<strAllOutDates.length; i=i+2){
					if(strAllOutDates[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						 detail.setOutDate(DateUtil.toDate(strAllOutDates[i+1],"yyyy-MM-dd"));
						break;
					}
				}
			}
			//保存备注
			if(null != strAllComments){
				for(int i=0;i<strAllComments.length; i=i+2){
					if(strAllComments[i].equals(strAllDetailIds[spareOutBillDetailNum])){
					   if(null != strAllComments[i+1]){
						   detail.setComment(strAllComments[i+1]);
					   }
						break;
					}
				}
			}
			//保存出库人
			if(null != strAllOutPeoples){
				for(int i=0;i<strAllOutPeoples.length; i=i+2){
					if(strAllOutPeoples[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						 detail.setOutPeople(this.userDao.loadUser(Long.valueOf(strAllOutPeoples[i+1])));
						break;
					}
				}
			}//设备
			if(null != strAllSheBei){
				for(int i=0;i<strAllSheBei.length;i=i+2){
					if(strAllSheBei[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						if(null != strAllSheBei[i+1]){
							detail.setShebei(strAllSheBei[i+1]);
						}
						
						break;
					}
				}
				
			}
			//班组
			if(null != strAllBanZu){
				for(int i=0;i<strAllBanZu.length;i=i+2){
					if(strAllBanZu[i].equals(strAllDetailIds[spareOutBillDetailNum])){
						if(null !=strAllBanZu[i+1]){
							detail.setBanzu(strAllBanZu[i+1]);
						}
						   break;
                         
					}
				}
				
			}
			
			detail.setStatus(SpareOutBillDetailStatus.OUTWAREHOUSEED);
			detail.getSpareOutBill().setSubmit(true);
			this.spareOutBillDetailDao.storeSpareOutBillDetail(detail);
			   //更新出库单总金额
			 updateSpareOutBill_totalPrice(detail.getSpareOutBill());
			//同步更新 出库单 状态
			this.updateSpareOutBillStatus(detail.getSpareOutBill());
			 //更新库位状态
			 locationManager.updateLocationStatusToNoUse(detail.getLocation());
			spareOutBillDetailNum++;
			
		}
		
		
         
		
		 	
		 
		/**	spareOutBillManager.accordingSpareOutBillDetailStatusUpdateSpareOutStatus(spareOutBillDetail.getSpareOutBill());
			
			//如果出库单明细中存在从领用单明细的对象 在改变此领用单明细的对象的状态
			if(spareOutBillDetail.getDtl()!=null){
				spareOutBillDetail.getDtl().setStatus(SpareBorrowDetailStatus.BORROWED);
				spareBorrowDetailDao.storeSpareBorrowDetail(spareOutBillDetail.getDtl());
				//在改变备件领用单明细的状态的同时   也改变备件领用单的状态
				spareBorrowDetailManager.updateSpareBorrowStatusBySpareBorrowDtlStatus(spareOutBillDetail.getDtl());
			}
			
			spareOutBillManager.storeSpareOutBill(spareOutBillDetail.getSpareOutBill());
			//如果出库单明细中的记录是从备件领用单明细选择过来的  那么保存出库单明细之后把出库数量写回此出库单明细所关联的备件领用单明细的实际出库数量
			if(spareOutBillDetail.getDtl()!=null){
				spareOutBillDetail.getDtl().setTotalStock(spareOutBillDetail.getNumber());
				this.spareBorrowDetailDao.storeSpareBorrowDetail(spareOutBillDetail.getDtl());
			}
		 */
		
		
		
		
		
	}
	/**
	 * 根据出库的数量设置库位库存
	 */
	private void setLocationStock(Location location,SpareOutBillDetail spareOutBillDetail){
		SpareLocation spareLocation;
		Long deptId = null;
		Long locationId = null;
		if(spareOutBillDetail.getStatus() == SpareOutBillDetailStatus.NEW){
			//根据LocationId和SpareId获得spareLocation
			if (null != spareOutBillDetail.getDepartment()) {
				deptId =spareOutBillDetail.getDepartment().getId();
			}
			if (null != spareOutBillDetail.getLocation()) {
				locationId = spareOutBillDetail.getLocation().getId();
			}
			if(spareOutBillDetail.getNumber()>0){
				spareLocation = spareLocationDao.getTop1SpareLocationByDeptAndLocationAndSpare(deptId,locationId,spareOutBillDetail.getSpare().getId(),false);
			}else{
				spareLocation = spareLocationDao.getTop1SpareLocationByDeptAndLocationAndSpare(deptId,locationId,spareOutBillDetail.getSpare().getId(),true);
			}
			
			//设置库位库存量
			spareLocation.setStocks(spareOutBillDetail.getStocksAfterInOrOut());
			Long allStocks = spareLocationDao.getSumBySpare(spareOutBillDetail.getSpare().getId());
			Spare spare = spareOutBillDetail.getSpare();
			//更新备件总库存
			spare.setStocks(allStocks);
			this.spareDao.storeSpare(spare);
			
            //更新备件库总台帐库存
			this.spareWareHouseManager.storeOrUpdateSpareWareHouse(spareLocation.getSpare(), spareLocation.getWarehouse(), spareOutBillDetail.getNumber());
		}
		
	}
	/**
	 * 根据出库的数量设置库位库存
	 */
	private void setLocationStockHaveDisable(Location location,SpareOutBillDetail spareOutBillDetail){
		SpareLocation spareLocation;
		Long deptId = null;
		Long locationId = null;
		if(spareOutBillDetail.getStatus() == SpareOutBillDetailStatus.NEW){
			//根据LocationId和SpareId获得spareLocation
			if (null != spareOutBillDetail.getDepartment()) {
				deptId =spareOutBillDetail.getDepartment().getId();
			}
			if (null != spareOutBillDetail.getLocation()) {
				locationId = spareOutBillDetail.getLocation().getId();
			}
			if(spareOutBillDetail.getNumber()>0){
				spareLocation = spareLocationDao.getTop1SpareLocationByDeptAndLocationAndSpare(deptId,locationId,spareOutBillDetail.getSpare().getId(),false);
			}else{
				spareLocation = spareLocationDao.getTop1SpareLocationByDeptAndLocationAndSpare(deptId,locationId,spareOutBillDetail.getSpare().getId(),true);
			}
			
			//设置库位库存量
			spareLocation.setStocks(spareOutBillDetail.getStocksAfterInOrOut());
			Long allStocks = spareLocationDao.getSumBySpare(spareOutBillDetail.getSpare().getId());
			Spare spare = spareOutBillDetail.getSpare();
			if(spare.getDisableStocks()!=null&&spare.getDisableStocks()>=spareOutBillDetail.getNumber()){
				spare.setDisableStocks(spare.getDisableStocks()-spareOutBillDetail.getNumber());
			}
			//更新备件总库存
			spare.setStocks(allStocks);
			
			this.spareDao.storeSpare(spare);
			
            //更新备件库总台帐库存
			this.spareWareHouseManager.storeOrUpdateSpareWareHouseHaveDisabe(spareLocation.getSpare(), spareLocation.getWarehouse(), spareOutBillDetail.getNumber());
		}
		
	}
	
	/**
	 * 根据出库的数量设置 备件、备件库台账的库存
	 * @param spareLocation 备件库台账
	 * @param spareOutBillDetail 出库单明细
	 */
	private void updateSpareLocationStock(SpareLocation spareLocation ,SpareOutBillDetail spareOutBillDetail){
	  
		if((SpareOutBillDetailStatus.NEW).equals(spareOutBillDetail.getStatus())){
			//if (spareLocation.getWarehouse().getStorageGrade().getId()==208){
			if(null!=spareOutBillDetail.getSpareOutBill().getInWarehouse()){
				//设置被锁定的库存
				if (null!=spareLocation.getLockedStocks()){
					spareLocation.setLockedStocks(spareLocation.getLockedStocks()+spareOutBillDetail.getNumber());
				} else {
					spareLocation.setLockedStocks(spareOutBillDetail.getNumber());
				}
				this.spareLocationDao.storeSpareLocation(spareLocation);
			} else{
               //设置库位库存量
				spareLocation.setStocks(spareOutBillDetail.getStocksAfterInOrOut());
				Long allStocks = this.spareLocationDao.getSumBySpare(spareOutBillDetail.getSpareLocation().getSpare().getId());
				Spare spare = spareOutBillDetail.getSpare();
				//更新备件总库存
				spare.setStocks(allStocks);
				this.spareDao.storeSpare(spare);
				this.spareLocationDao.storeSpareLocation(spareLocation);
				//更新备件库总台帐库存
				this.spareWareHouseManager.storeOrUpdateSpareWareHouse(spareLocation.getSpare(), spareLocation.getWarehouse(), spareOutBillDetail.getNumber());
			}
			
			
		}
		
	}

	/**
	 * 根据出库的数量设置 备件、备件库台账的库存
	 * @param spareLocation 备件库台账
	 * @param spareOutBillDetail 出库单明细
	 */
	private void updateSpareLocationStockHaveDisable(SpareLocation spareLocation ,SpareOutBillDetail spareOutBillDetail){
	  
		if((SpareOutBillDetailStatus.NEW).equals(spareOutBillDetail.getStatus())){
			//if (spareLocation.getWarehouse().getStorageGrade().getId()==208){
			if(null!=spareOutBillDetail.getSpareOutBill().getInWarehouse()){
				//设置被锁定的库存
				if (null!=spareLocation.getLockedStocks()){
					spareLocation.setLockedStocks(spareLocation.getLockedStocks()+spareOutBillDetail.getNumber());
				} else {
					spareLocation.setLockedStocks(spareOutBillDetail.getNumber());
				}
				this.spareLocationDao.storeSpareLocation(spareLocation);
			} else{
               //设置库位库存量
				spareLocation.setStocks(spareOutBillDetail.getStocksAfterInOrOut());
				Long allStocks = this.spareLocationDao.getSumBySpare(spareOutBillDetail.getSpareLocation().getSpare().getId());
				Spare spare = spareOutBillDetail.getSpare();
				//更新备件总库存
				spare.setStocks(allStocks);
				if(spare.getDisableStocks()!=null&&spare.getDisableStocks()>=spareOutBillDetail.getNumber()){
					spare.setDisableStocks(spare.getDisableStocks()-spareOutBillDetail.getNumber());
				}
				
				this.spareDao.storeSpare(spare);
				this.spareLocationDao.storeSpareLocation(spareLocation);
				//更新备件库总台帐库存
				this.spareWareHouseManager.storeOrUpdateSpareWareHouseHaveDisabe(spareLocation.getSpare(), spareLocation.getWarehouse(), spareOutBillDetail.getNumber());
			}
			
			
		}
		
	}
	
	
	//根据出库单明细更改出库单总金额
	private void updateSpareOutBill_totalPrice(SpareOutBill spareOutBill) {
		Double totalPrice = NumberUtils.DOUBLE_ZERO;
		for(SpareOutBillDetail detail : spareOutBill.getDetail()){
			if(detail.getTotalPrice()!=null){
				totalPrice += detail.getTotalPrice();
			}
		}
		spareOutBill.setTotalPrice(totalPrice);
		spareOutBillManager.storeSpareOutBill(spareOutBill);
	}
	/**
	 * 更新出库单的状态
	 * @param spareOutBill 出库单
	 */
	public void updateSpareOutBillStatus(SpareOutBill spareOutBill){
		int newNum =0;
		int outNum =0;
		Set<SpareOutBillDetail> set = spareOutBill.getDetail();
		if(null != set && set.size()>0){
			int size = set.size();
			for(SpareOutBillDetail detail : set){
				if(SpareOutBillDetailStatus.NEW.equals(detail.getStatus())){
					newNum++;
				}
				if(SpareOutBillDetailStatus.OUTWAREHOUSEED.equals(detail.getStatus())){
					outNum++;
				}
			}
			if(size == newNum){
				spareOutBill.setStatus(SpareOutBillStatus.NEWSTATUS);
			}else if(size == outNum){
				spareOutBill.setStatus(SpareOutBillStatus.OUTWAREHOUSEED);
			}else{
				spareOutBill.setStatus(SpareOutBillStatus.OUTWAREHOUSEING);
			}
			this.spareOutBillDao.storeSpareOutBill(spareOutBill);
			
		}
	}
	
	
	
	public void deleteOutBillDtlShootOfException(List<SpareOutBillDetail> spareOutBillDetails)throws Exception{
		for(SpareOutBillDetail detail:spareOutBillDetails){
			if(detail.getStatus().equals(SpareOutBillDetailStatus.OUTWAREHOUSEED)){
				throw new Exception();
			}
		}
	}
	public void deleteAllSpareOutBillDetail(List<SpareOutBillDetail> spareOutBillDetails,SpareOutBill spareOutBill) throws Exception{
        deleteOutBillDtlShootOfException(spareOutBillDetails);
		spareOutBillDetailDao.deleteAllSpareOutBillDetail(spareOutBillDetails);
		//删除出库单明细的状态为“已出库”抛出的异常

        //删除出库明细的时候要将备件在台帐中的数量给加上
		for(SpareOutBillDetail spareOutBillDetail : spareOutBillDetails){
			//如果出库单数量为空时，不去修改备件台帐总库存
			/*			
  			if(spareOutBillDetail.getName()!=null){
				Spare spare = spareOutBillDetail.getSpare();
				spareOutBillDetail.getSpare().setStocks(spareOutBillDetail.getStocksBeforeInOrOut());
				spareManager.storeSpare(spare);
			}
			*/
			//将出库单中关联的出库单明细移除
			spareOutBill.getDetail().remove(spareOutBillDetail);
			//更改提醒按钮状态
			spareOutBillDetail.getSpareOutBill().setSubmit(true);
			spareOutBillManager.storeSpareOutBill(spareOutBillDetail.getSpareOutBill());
			//如果出库单明细中存在从备件领用单明细选择的记录 那么删除出库单明细时改变备件领用单明细的状态 同时也改变备件领用单的状态
			if(spareOutBillDetail.getDtl()!=null){
				spareOutBillDetail.getDtl().setStatus(SpareBorrowDetailStatus.UNBORROW);
				spareOutBillDetail.getDtl().setTotalStock(null);
				spareBorrowDetailManager.updateSpareBorrowStatusBySpareBorrowDtlStatus(spareOutBillDetail.getDtl());
			}
            //调用给据出库单明细的状态改变出库单的状态
			spareOutBillManager.accordingSpareOutBillDetailStatusUpdateSpareOutStatus(spareOutBillDetail.getSpareOutBill());
		}
		//循环删除入库单明细总金额
		this.updateSpareOutBill_totalPrice(spareOutBill);
	}
	
	public void storeSpareBorrowToSpareInBillDtl(SpareOutBill spareOutBill, String spareBorrowIds) {
		String [] allSpareBorrowIds=null;
		if(null != spareBorrowIds){
			allSpareBorrowIds = spareBorrowIds.split(",");
		}
		for(int i=0;i<allSpareBorrowIds.length;i++){
			SpareOutBillDetail detail = new SpareOutBillDetail();
			SpareBorrowDetail spareBorrowDtl = spareBorrowDetailDao.loadSpareBorrowDetail(Long.valueOf(allSpareBorrowIds[i]));
			detail.setSpare(spareBorrowDtl.getSpare());
			detail.setDtl(spareBorrowDtl);
			detail.setSpareOutBill(spareOutBill);
            //copy备件领用单明细到备件入库单明细
			copyPurBillDtlToSpareInBillDtl(spareBorrowDtl,detail);
			this.spareOutBillDetailDao.storeSpareOutBillDetail(detail);
           //调用给据出库单明细的状态改变出库单的状态
			spareOutBillManager.accordingSpareOutBillDetailStatusUpdateSpareOutStatus(detail.getSpareOutBill());
			detail.setDtl(spareBorrowDtl);
			this.spareOutBillDetailDao.storeSpareOutBillDetail(detail);
		}
		
	}
	
	

	public void copyPurBillDtlToSpareInBillDtl(SpareBorrowDetail spareBorrowDtl,SpareOutBillDetail detail){
		if(spareBorrowDtl.getSpare()!=null){
			if(spareBorrowDtl.getSpareNo()!=null){						//复制编号
				detail.setCode(spareBorrowDtl.getSpareNo());
			}else{
				detail.setCode(null);
			}
			if(spareBorrowDtl.getSpareName()!=null){							//复制名称
				detail.setName(spareBorrowDtl.getSpareName());				
			}else{
				detail.setName(null);
			}
			if(spareBorrowDtl.getPrice()!=null){
				detail.setUnitPrice(spareBorrowDtl.getPrice());  //复制单价
			}else{
				detail.setUnitPrice(Double.valueOf(0.0));
			}
			if(spareBorrowDtl.getAmount()!=null){          //复制数量
				detail.setNumber(spareBorrowDtl.getAmount());
			}else{
				detail.setNumber(Long.valueOf(0));
			}
			if(spareBorrowDtl.getTotalPrice()!=null){    //复制总价
				detail.setTotalPrice(spareBorrowDtl.getTotalPrice());
			}else{
				detail.setTotalPrice(Double.valueOf(0.0));
			}
		}
		if(spareBorrowDtl.getSpare()!=null){
			detail.setStocksBeforeInOrOut(spareBorrowDtl.getSpare().getStocks());
		}else{
			detail.setStocksBeforeInOrOut(new Long(0));
		}
		detail.setStocksAfterInOrOut(new Long(0));

	}
	public void storeOutBillDtlToInBillDtl(SpareInBill spareInBill, String addSpareOutBillIds) {
		// TODO Auto-generated method stub
		
	}

	public List<Integer> loadSpareLocations(String warehouseid) {
		// TODO Auto-generated method stub
		return this.spareOutBillDetailDao.loadSpareLocations(warehouseid);
	}

	public Integer loadSpareOutBillDetailCount(String outBillId) {
		// TODO Auto-generated method stub
		return this.spareOutBillDetailDao.loadSpareOutBillDetailCount(outBillId);
	}
}
