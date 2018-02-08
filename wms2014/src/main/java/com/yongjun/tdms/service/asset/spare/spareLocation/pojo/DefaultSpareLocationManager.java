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
package com.yongjun.tdms.service.asset.spare.spareLocation.pojo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.asset.spare.location.LocationDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDetailDao;
import com.yongjun.tdms.dao.asset.spare.sparelocation.SpareLocationDao;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailStatus;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.service.asset.spare.spareLocation.SpareLocationManager;

/**
 * <p>Title: DefaultSpareLocationManager
 * <p>Description: 备件明细业务逻辑实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class DefaultSpareLocationManager implements SpareLocationManager {
	
	private final SpareLocationDao spareLocationDao;
	private final LocationDao locationDao;
	private final SpareDao spareDao;
	private final SpareOutBillDetailDao spareOutBillDetailDao;
	
	public DefaultSpareLocationManager(SpareLocationDao spareLocationDao,
			LocationDao locationDao,
			SpareDao spareDao,
			SpareOutBillDetailDao spareOutBillDetailDao) {
		this.spareLocationDao = spareLocationDao;
		this.locationDao = locationDao;
		this.spareDao = spareDao;
		this.spareOutBillDetailDao = spareOutBillDetailDao;
	}
	
	public List<SpareLocation> loadAllSpareLocations(Long[] spareLocationIds) {
		return this.spareLocationDao.loadAllSpareLocations(spareLocationIds);
	}


	public SpareLocation loadSpareLocation(Long spareLocationId) {
		return this.spareLocationDao.loadSpareLocation(spareLocationId);
	}


	public void storeSpareLocation(SpareLocation spareLocation) {
		this.spareLocationDao.storeSpareLocation(spareLocation);
		this.updateInventoryAfterSpareStocks(spareLocation);
	}


	public void deleteSpareLocation(SpareLocation spareLocation) {
		this.spareLocationDao.deleteSpareLocation(spareLocation);
	}


	public void deleteAllSpareLocation(Collection<SpareLocation> spareLocations) {
		this.spareLocationDao.deleteAllSpareLocation(spareLocations);
	}

	public void storePartInfoOfSpareLocation(String allSpareLocationIdValue,
			String allUnitPriceValue,String allLocationCodeValue) {
		String [] spareLocationIdValue = null;    //用来存放spareLocation id
		String [] unitPriceValue = null;          //用来存放unitPrice
		String [] locationCodeValue = null;       //用来存放库位号
		
		if (null != allSpareLocationIdValue) {
			spareLocationIdValue = allSpareLocationIdValue.split(",");
		}
		if (null != allUnitPriceValue) {
			unitPriceValue = allUnitPriceValue.split(",");
		}
		if (null != allLocationCodeValue) {
			locationCodeValue = allLocationCodeValue.split(",");
		}
		updatePartInfoOfSpareLocation(spareLocationIdValue,unitPriceValue,locationCodeValue);
		
	}
	
	/**
	 * 更新备件明细中部分信息
	 * @param spareLocationIdValue spareLocation id 字符串数组
	 * @param unitPriceValue unitPrice字符串数组
	 * @param locationCodeValue 库位号字符串数组
	 */
	private void updatePartInfoOfSpareLocation(String [] spareLocationIdValue,
											String [] unitPriceValue,String [] locationCodeValue) {
		int loopNum = 0;
		while (null != spareLocationIdValue
				&& loopNum < spareLocationIdValue.length) {
			SpareLocation spareLocation = this.spareLocationDao
					.loadSpareLocation(Long.valueOf(spareLocationIdValue[loopNum]));
			//更新单价
			if(null != unitPriceValue){
				   for(int i=0;i<unitPriceValue.length;i=i+2){
					   if(unitPriceValue[i].equals(spareLocationIdValue[loopNum])){
						   Double unitPrice = Double.valueOf(unitPriceValue[i+1]);
						   spareLocation.setUnitPrice(unitPrice);
						   break;
					   }else{
						   spareLocation.setUnitPrice(0.00);
					   }
				   }
			 }
			//更新库位号
			if (null != locationCodeValue) {
				   for(int i=0;i<locationCodeValue.length;i=i+2){
					   if(locationCodeValue[i].equals(spareLocationIdValue[loopNum])){
						   Location location = this.locationDao.getLocationByCodeOnlyValid(locationCodeValue[i+1]);
						   if (null != location) {
							   spareLocation.setLocation(location);
							   spareLocation.setLocationCode(location.getCode()); 
							   spareLocation.setWarehouse(location.getWarehouse());
							   spareLocation.setRegional(location.getRegional());
						   } else {
							   spareLocation.setLocation(null);
							   spareLocation.setLocationCode("");
						   }
						   break;
					   }else{
						   spareLocation.setLocation(null);
						   spareLocation.setLocationCode("");
					   }
				   }
			} else {
				spareLocation.setLocation(null);
				spareLocation.setLocationCode("");
			}
			this.spareLocationDao.storeSpareLocation(spareLocation);
			loopNum++;
		}
	}

	public SpareLocation getTop1SpareLocationByDeptAndSpare(Long deptId, String spareNo) {
		// TODO Auto-generated method stub
		return spareLocationDao.getTop1SpareLocationByDeptAndSpare(deptId, spareNo);
	}
	
	public  void storeOrUpdateSpareLocationByInBillDetail(SpareInBillDetail detail) {
		/*
		 * 根据入库明细来更新或新增明细台帐，几中情况如下：
		 * 1）入库明细部门、备件、库位都不为空，如果明细有同样的记录，更新明细台帐，否则新增
		 * 2）入库明细备件、库位不为空，如果明细有同样的记录，更新明细台帐，否则新增
		 * 
		 */
		
		if(detail.getDepartment()!=null&&detail.getSpare()!=null&&detail.getLocation()!=null){
			SpareLocation spareLocation = spareLocationDao
					.getTop1SpareLocationByDeptAndLocationAndSpare(detail.getDepartment().getId(),
							detail.getLocation().getId(), detail.getSpare().getId(),false);
			if(null != spareLocation) {
				updateSpareLocationStocksByInBillDetail(detail,spareLocation);
				return;
			}
		} else if (detail.getSpare()!=null&&detail.getLocation()!=null) {
			SpareLocation spareLocation = spareLocationDao
					.getTop1SpareLocationByLocationAndSpare(detail.getLocation().getId(),detail.getSpare().getId());
			if (null != spareLocation) {
				updateSpareLocationStocksByInBillDetail(detail,spareLocation);
				return;
			}
		}
		storeSpareLocationByInBillDetail(detail);   //新增明细台帐
	}
	//包含是否可用也保存
	public  void storeOrUpdateSpareLocationByInBillDetailHaveDisabe(SpareInBillDetail detail) {
		/*
		 * 根据入库明细来更新或新增明细台帐，几中情况如下：
		 * 1）入库明细部门、备件、库位都不为空，如果明细有同样的记录，更新明细台帐，否则新增
		 * 2）入库明细备件、库位不为空，如果明细有同样的记录，更新明细台帐，否则新增
		 * 
		 */
		
		if(detail.getDepartment()!=null&&detail.getSpare()!=null&&detail.getLocation()!=null){
			SpareLocation spareLocation = spareLocationDao
					.getTop1SpareLocationByDeptAndLocationAndSpare(detail.getDepartment().getId(),
							detail.getLocation().getId(), detail.getSpare().getId(),false);
			if(null != spareLocation) {
				updateSpareLocationStocksByInBillDetail(detail,spareLocation);
				return;
			}
		} else if (detail.getSpare()!=null&&detail.getLocation()!=null) {
			SpareLocation spareLocation = spareLocationDao
					.getTop1SpareLocationByLocationAndSpare(detail.getLocation().getId(),detail.getSpare().getId());
			if (null != spareLocation) {
				updateSpareLocationStocksByInBillDetailHaveDisabe(detail,spareLocation);
				return;
			}
		}
		storeSpareLocationByInBillDetailHaveDisabe(detail);   //新增明细台帐
	}
	
	//根据入库明细更新明细台帐库存
	private void updateSpareLocationStocksByInBillDetail(SpareInBillDetail detail,SpareLocation spareLocation) {
		/*
		 * 这里注释的原因，是功能修改，因为修改前，入库明细的单价是可以修改的，但现在是不可修改。
		 * 如果是备件库里面已经存在的，则把该单价复制到入库明细中的单价上
		 */
		//spareLocation.setUnitPrice(detail.getUnitPrice());
		detail.setUnitPrice(spareLocation.getUnitPrice());
		//如果是从采购订单入库，默认情况下入库前库存为零，如果该备件是入库在已有的位置上，则更新入库前库存
		detail.setStocksBeforeInOrOut(spareLocation.getStocks());
		if (detail.getInstatus().toString().equals(SpareInBillDetailStatus.NEW.toString())) {
			spareLocation.setStocks(detail.getNumber() + spareLocation.getStocks());    //更新明细台帐中库存
		} else {
			spareLocation.setStocks(spareLocation.getStocks());    //更新明细台帐中库存
		}
		spareLocationDao.storeSpareLocation(spareLocation);
		updateSpareLocationStocks(spareLocation.getSpare().getId());   //更新t_spare的总库存  
	}
	//根据入库明细更新明细台帐库存
		private void updateSpareLocationStocksByInBillDetailHaveDisabe(SpareInBillDetail detail,SpareLocation spareLocation) {
			/*
			 * 这里注释的原因，是功能修改，因为修改前，入库明细的单价是可以修改的，但现在是不可修改。
			 * 如果是备件库里面已经存在的，则把该单价复制到入库明细中的单价上
			 */
			//spareLocation.setUnitPrice(detail.getUnitPrice());
			detail.setUnitPrice(spareLocation.getUnitPrice());
			//如果是从采购订单入库，默认情况下入库前库存为零，如果该备件是入库在已有的位置上，则更新入库前库存
			detail.setStocksBeforeInOrOut(spareLocation.getStocks());
			if (detail.getInstatus().toString().equals(SpareInBillDetailStatus.NEW.toString())) {
				spareLocation.setStocks(detail.getNumber() + spareLocation.getStocks());    //更新明细台帐中库存
			} else {
				spareLocation.setStocks(spareLocation.getStocks());    //更新明细台帐中库存
			}
			spareLocationDao.storeSpareLocation(spareLocation);
			updateSpareLocationStocksHaveDisabe(detail,spareLocation.getSpare().getId());   //更新t_spare的总库存  
		}
		
	
	//根据入库明细新增明细台帐
	private void storeSpareLocationByInBillDetail(SpareInBillDetail detail) {
		Location location=detail.getLocation();
		System.out.println("location:"+ location.getCode());
		Warehouse warehouse=location.getWarehouse();
		Regional regional=location.getRegional();
		SpareLocation spareLocation=new SpareLocation();
		spareLocation.setSpare(detail.getSpare());      //获取备件
		spareLocation.setUnitPrice(detail.getTaxPrice());  //获取单价
		spareLocation.setStocks(detail.getNumber());         //获取库存
		spareLocation.setDepartment(detail.getDepartment()); //获取部门
		spareLocation.setWarehouse(warehouse);				//插入仓库
		spareLocation.setRegional(regional);				//插入库区
		spareLocation.setLocation(detail.getLocation());     //获取库位
		spareLocation.setLocationCode(detail.getLocationCode()); //获取库位号
		spareLocation.setPreStocks(0L);           //获取期初库存
		
		//更新入库明细入库前库存和入库后库存
		detail.setStocksBeforeInOrOut(0L);
		detail.setStocksAfterInOrOut(detail.getNumber());
		spareLocationDao.storeSpareLocation(spareLocation);
		//更新t_spare的总库存
		updateSpareLocationStocks(spareLocation.getSpare().getId());
	}
	
	//根据入库明细新增明细台帐
		private void storeSpareLocationByInBillDetailHaveDisabe(SpareInBillDetail detail) {
			Location location=detail.getLocation();
			System.out.println("location:"+ location.getCode());
			Warehouse warehouse=location.getWarehouse();
			Regional regional=location.getRegional();
			SpareLocation spareLocation=new SpareLocation();
			spareLocation.setSpare(detail.getSpare());      //获取备件
			spareLocation.setUnitPrice(detail.getTaxPrice());  //获取单价
			spareLocation.setStocks(detail.getNumber());         //获取库存
			spareLocation.setDepartment(detail.getDepartment()); //获取部门
			spareLocation.setWarehouse(warehouse);				//插入仓库
			spareLocation.setRegional(regional);				//插入库区
			spareLocation.setLocation(detail.getLocation());     //获取库位
			spareLocation.setLocationCode(detail.getLocationCode()); //获取库位号
			spareLocation.setPreStocks(0L);           //获取期初库存
			
			//更新入库明细入库前库存和入库后库存
			detail.setStocksBeforeInOrOut(0L);
			detail.setStocksAfterInOrOut(detail.getNumber());
			spareLocationDao.storeSpareLocation(spareLocation);
			//更新t_spare的总库存
			updateSpareLocationStocksHaveDisabe(detail,spareLocation.getSpare().getId());
		}
    //更新t_spare的总库存
	public void updateSpareLocationStocks(Long spareId){
		Long sum=spareLocationDao.getSumBySpare(spareId);
		Spare spare=spareDao.loasSpare(spareId);
		spare.setStocks(sum);
		spareDao.storeSpare(spare);
	}
	 //更新t_spare的总库存
		public void updateSpareLocationStocksHaveDisabe(SpareInBillDetail detail,Long spareId){
			Long sum=spareLocationDao.getSumBySpare(spareId);
			Spare spare=spareDao.loasSpare(spareId);
			spare.setStocks(sum);
			if(detail.getDisableSpare()!=null&&detail.getDisableSpare().equals("N")){
				if(spare.getDisableStocks()!=null){
					spare.setDisableStocks(detail.getNumber()+spare.getDisableStocks());
				}else{
					spare.setDisableStocks(detail.getNumber());
				}
				
			}
			spareDao.storeSpare(spare);
		}

	public void updateInventoryAfterSpareStocks(SpareLocation spareLocation) {
		 Long spareid=spareLocation.getSpare().getId();
		 Long spareSum= this.spareLocationDao.getSumBySpare(spareid);
		 spareLocation.getSpare().setStocks(spareSum);
		 this.spareDao.storeSpare( spareLocation.getSpare());
		
	}

	public SpareLocation getTop1SpareLocationByDeptAndLocationAndSpare(Long deptId, Long locationId, Long spareId, boolean isIncludeStocksZeroStatus) {
		return spareLocationDao.getTop1SpareLocationByDeptAndLocationAndSpare(deptId, locationId, spareId, isIncludeStocksZeroStatus);
	}

	public SpareLocation getTop1SpareLocationByLocationIdAndSpareId(Long locationId, Long spareId) {
		// TODO Auto-generated method stub
		return spareLocationDao.getTop1SpareLocationByLocationIdAndSpareId(locationId, spareId);
	}

	public SpareLocation getTop1SpareLocationByLocationAndSpare(Long locationId, Long spareId) {
		// TODO Auto-generated method stub
		return spareLocationDao.getTop1SpareLocationByLocationAndSpare(locationId, spareId);
	}

	public List<SpareLocation> loadByKey(String key, Object value) throws DaoException {
		return spareLocationDao.loadByKey(key, value);
	}

	public String getSpareLocationStock(String[] ary) {
		String value = "";
		List<SpareLocation>list = new ArrayList<SpareLocation>();
		List<String>idList = new ArrayList<String>();
		List<String>numList = new ArrayList<String>();
		try {
			int backNum = 0;
			Long outDelId = null;
			Integer number = null;
			SpareLocation  spareLocation = null;
			SpareOutBillDetail outBillDetail = null;
			
			if(null != ary && ary.length>0){
				for(int i=0;i<ary.length;i=i+2){
					outDelId = Long.parseLong(URLDecoder.decode(ary[i], "UTF-8"));//出库单id
					number=Integer.parseInt(URLDecoder.decode(ary[i+1], "UTF-8"));//页面传过来的数量
					outBillDetail = this.spareOutBillDetailDao.loadSpareOutBillDetail(outDelId);
					
					spareLocation = this.getTop1SpareLocationByDeptAndLocationAndSpare(outBillDetail.getDepartment().getId(),outBillDetail.getLocation().getId(), outBillDetail.getSpare().getId(),true);
					//返回总库存数
					backNum = spareLocation.getStocks().intValue();
				    list.add(spareLocation);
				    idList.add(outDelId.toString());
				    numList.add(String.valueOf(backNum));
				    //返回库存在页面判断
					value += outDelId+","+backNum+",";
					
				}
				//首先判断 页面是否有重复的选项，如果有则返回
				//如果没有，再判断库存 返回库存在页面判断
				String str =  this.compSpareLocation(list, idList, numList);
				if(null != str && str.split(",").length>3){
					value = str;
				} 
				
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//System.out.println("value====="+value); 
		return value;
	}
	/**
	 * 遍历查看是否有重复的选项
	 * @param list
	 * @param idlist
	 * @param numlist
	 * @return 返回重复备件库台账的出库单明细的id 和 此备件库台账的当前总库存
	 */
	private String compSpareLocation(List<SpareLocation> list,List<String> idlist,List<String> numlist){
	    String str = "iterance,";//如果出库单明细有重复的数据 加 iterance 表示重复，优先判断是否有重复的
	    String str2="";
	    String num = null;
		SpareLocation sl1;
	    SpareLocation sl2;
		for(int i =0;i<list.size();i++){
	    	sl1 = list.get(i);
	    	for(int j =i+1;j<list.size();j++){
	    		sl2 = list.get(j);
	    		if(sl1.getId().equals(sl2.getId())){//判断是否有重复的备件库台账
	    			str2 =str2+idlist.get(j)+",";
	    			num = numlist.get(i);//重复的备件库台账的 总库存
	    			
	    		}
	    	}
	    	//如果有获得出库单明细的id 并返回
	    	if(str2.split(",").length>0){
	    		str = str+idlist.get(i)+","+str2;
				 break;
			} 
	    }
		str = str+num;
		//System.out.println("str====="+str);
		return str;
	}

	public void storePartInfoOfSpareLocation(String allSpareLocationIdValue, String allUnitPriceValue, 
			String allLocationCodeValue, String allWareHouseIdValue) {
		String [] spareLocationIdValue = null;    //用来存放spareLocation id
		String [] unitPriceValue = null;          //用来存放unitPrice
		String [] locationCodeValue = null;       //用来存放库位号
		String [] wareHouseIdValue = null;
		
		if (null != allSpareLocationIdValue) {
			spareLocationIdValue = allSpareLocationIdValue.split(",");
		}
		if (null != allUnitPriceValue) {
			unitPriceValue = allUnitPriceValue.split(",");
		}
		if (null != allLocationCodeValue) {
			locationCodeValue = allLocationCodeValue.split(",");
		}
		if (null != allWareHouseIdValue) {
			wareHouseIdValue = allWareHouseIdValue.split(",");
		}
		updatePartInfoOfSpareLocation(spareLocationIdValue,unitPriceValue,locationCodeValue,wareHouseIdValue);
		
	}
	
	/**
	 * 更新备件明细中部分信息
	 * @param spareLocationIdValue spareLocation id 字符串数组
	 * @param unitPriceValue unitPrice字符串数组
	 * @param locationCodeValue 库位号字符串数组
	 */
	private void updatePartInfoOfSpareLocation(String [] spareLocationIdValue,
											String [] unitPriceValue,String [] locationCodeValue,String [] wareHouseIdValue) {
		int loopNum = 0;
		while (null != spareLocationIdValue
				&& loopNum < spareLocationIdValue.length) {
			SpareLocation spareLocation = this.spareLocationDao
					.loadSpareLocation(Long.valueOf(spareLocationIdValue[loopNum]));
			//更新单价
			if(null != unitPriceValue){
				   for(int i=0;i<unitPriceValue.length;i=i+2){
					   if(unitPriceValue[i].equals(spareLocationIdValue[loopNum])){
						   Double unitPrice = Double.valueOf(unitPriceValue[i+1]);
						   spareLocation.setUnitPrice(unitPrice);
						   break;
					   }else{
						   spareLocation.setUnitPrice(0.00);
					   }
				   }
			 }
			//更新库位号
			if (null != locationCodeValue) {
				   for(int i=0;i<locationCodeValue.length;i=i+2){
					   if(locationCodeValue[i].equals(spareLocationIdValue[loopNum])){
						   Location location = this.locationDao.getLocationByCodeOnlyValid(locationCodeValue[i+1],Long.valueOf(wareHouseIdValue[i+1]));
						   if (null != location) {
							   spareLocation.setLocation(location);
							   spareLocation.setLocationCode(location.getCode()); 
							   spareLocation.setWarehouse(location.getWarehouse());
							   spareLocation.setRegional(location.getRegional());
						   } else {
							   spareLocation.setLocation(null);
							   spareLocation.setLocationCode("");
						   }
						   break;
					   }else{
						   spareLocation.setLocation(null);
						   spareLocation.setLocationCode("");
					   }
				   }
			} else {
				spareLocation.setLocation(null);
				spareLocation.setLocationCode("");
			}
			this.spareLocationDao.storeSpareLocation(spareLocation);
			loopNum++;
		}
	}

}
