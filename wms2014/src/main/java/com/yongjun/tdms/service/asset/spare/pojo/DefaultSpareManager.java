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
package com.yongjun.tdms.service.asset.spare.pojo;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.HibernateException;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.device.DeviceSpareDao;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.asset.spare.SpareInOutHistoryDao;
import com.yongjun.tdms.dao.base.codevalue.SpareDetailTypeDao;
import com.yongjun.tdms.dao.base.codevalue.SpareTypeDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.dao.prophase.supplier.SupplierDao;
import com.yongjun.tdms.model.asset.spare.QueryUnMatchSpare;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.log.SysLog;
import com.yongjun.tdms.model.base.log.SysLogType;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.base.log.SysLogManager;

/**
 * @author qs
 * @version $Id: DefaultSpareManager.java 11295 2008-03-12 12:27:38Z wzou $
 */

public class DefaultSpareManager extends BaseManager implements SpareManager {
	private final SpareDao spareDao;

	private final DeviceSpareDao deviceSpareDao;

	private final SpareInOutHistoryDao spareInOutHistoryDao;

	private final SequenceManager sequenceManager;

	private final SysLogManager sysLogManager;

	private final SubscribeDao subscribeDao;

	private final SpareTypeDao spareTypeDao;

	private final SpareDetailTypeDao spareDetailTypeDao;

	private final SupplierDao supplierDao;

	public DefaultSpareManager(SpareDao spareDao,
			SpareInOutHistoryDao spareInOutHistoryDao,
			SequenceManager sequenceManager, DeviceSpareDao deviceSpareDao,
			SysLogManager sysLogManager, SubscribeDao subscribeDao,
			SpareTypeDao spareTypeDao, SpareDetailTypeDao spareDetailTypeDao,
			SupplierDao supplierDao) {
		this.spareDao = spareDao;
		this.spareInOutHistoryDao = spareInOutHistoryDao;
		this.sequenceManager = sequenceManager;
		this.deviceSpareDao = deviceSpareDao;
		this.sysLogManager = sysLogManager;
		this.subscribeDao = subscribeDao;
		this.spareTypeDao = spareTypeDao;
		this.spareDetailTypeDao = spareDetailTypeDao;
		this.supplierDao = supplierDao;
	}

	public List<Spare> loadAllSpares(Long[] spareIds) {
		return this.spareDao.loadAllSpares(spareIds);
	}

	public Spare loasSpare(Long spareId) {
		return this.spareDao.loasSpare(spareId);
	}

	private long countSpareInOutValue(Spare spare) {
		long totalValue = 0;
		String returnString = spareInOutHistoryDao.getTotalSpareNumberBySpare(
				spare.getId(), true);
		if (returnString != null) {
			totalValue = Long.valueOf(returnString);
		}
		returnString = spareInOutHistoryDao.getTotalSpareNumberBySpare(spare
				.getId(), false);
		if (returnString != null) {
			totalValue -= Long.valueOf(returnString);
		}
		return totalValue;
	}

	public void invalidateSpare(List<Spare> spares) {
		for (Spare spare : spares) {

			spare.setDisabled(true);
			this.spareDao.storeSpare(spare);
		}
	}

	public void enabledAllSpare(Collection<Spare> spares) throws Exception {
		for (Spare spare : spares) {
			if (spare.getModelSpecs() != null) {
				List listModel = spareDao.getSpareCollentionByModel(spare
						.getModelSpecs());
				if (listModel.size() > 1) {
					throw new Exception();
				}

			}
			if (spare.getSpecification() != null) {
				List listSpec = spareDao.getSpareCollentionBySpec(spare
						.getSpecification());
				if (listSpec.size() > 1) {
					throw new Exception();
				}
			}

			spare.setDisabled(false);
			this.spareDao.storeSpare(spare);
		}
	}

	public void storeSpare(Spare spare, String spareLogValue) {
		if (spareLogValue != null) {
			String commentString;
			String[] ary = spareLogValue.split(",");
			SysLog sysLog;
			for (int i = 0; i < ary.length; i = i + 3) {
				if (spare.getId().equals(Long.valueOf(ary[i]))) {
					if (!Long.valueOf(ary[i + 1]).equals(spare.getSafeStock())) {
						sysLog = new SysLog();
						commentString = spare.getName() + ","
								+ Long.valueOf(ary[i + 1]) + "->"
								+ spare.getSafeStock() + ","
								+ spare.getLastOperator() + ","
								+ spare.getLastModifiedTime();
						sysLog.setContent(commentString);
						sysLog.setType(SysLogType.SPARE_SAFESTOCK);
						this.sysLogManager.write(sysLog);
					}
					if (!Long.valueOf(ary[i + 2]).equals(spare.getStocks())) {
						sysLog = new SysLog();
						commentString = spare.getName() + ","
								+ Long.valueOf(ary[i + 2]) + "->"
								+ spare.getStocks() + ","
								+ spare.getLastOperator() + ","
								+ spare.getLastModifiedTime();
						sysLog.setContent(commentString);
						sysLog.setType(SysLogType.SPARE_STOK);
						this.sysLogManager.write(sysLog);
					}
					storeSpare(spare);
				}
			}
		}
	}

	public void storeSpare(Spare spare) {
		if (spare.isNew()) {

//			String spareNo = (String) sequenceManager.generate("-");
//			String spareTypeNo = spare.getCategory().getCode();
//			String spareDtlTypeNo = spare.getSpareDetailType().getCode();
//			String spareNoByTypeAndDtlTypeNo = spareTypeNo + spareDtlTypeNo
//					+ spareNo;
//			spare.setSpareNo(spareNoByTypeAndDtlTypeNo);

			if (spare.getSafeStock() == null) {
				spare.setSafeStock(0L); // 初始化安全库存
			}
			if (spare.getStocks() == null) {
				spare.setStocks(0L); // 初始化总库存
			}
		}
//		String spareNo = generateSpareNo(spare); // 生成备件编码 -- 备件编码改为手动生成，所以在这不需要自动生成
//		spare.setSpareNo(spareNo);
		
		this.spareDao.storeSpare(spare);

		// if (spare.getDevice()!=null) {
		// //设置备件与资产(设备/工装)的关联
		// DeviceSpare deviceSpare =
		// deviceSpareDao.GetDeviceSpareBySpareId(spare.getId(),spare.getDevice().getId());
		// if(deviceSpare==null){
		// deviceSpare=new DeviceSpare();
		// }
		// deviceSpare.setAsset(spare.getDevice());
		// deviceSpare.setSpare(spare);
		// this.deviceSpareDao.storeDeviceSpare(deviceSpare);
		// }
	}

	/**
	 * 创建台账dwr
	 */
	public String createGraphNo(String[] ary) {
		String name = null;
		String model = null;
		String orderNo = "";
		String strFactoryId = null;
		Long   factoryId = null;
		String category = null;
		String detailCategory = null;
		String spareNo = null;
		Supplier factory = null;
		try {

			long sid = Long.parseLong(URLDecoder.decode(ary[0], "UTF-8"));
			SubscribeDetail sub = this.subscribeDao.loadSubscribeDetail(sid);// 根据id
																				// 查找申购单明细
			if (null != sub) {
				  // Spare spare = sub.getSpare();// 根据申购单明细查找相应的备件
				     
					//spareNo  = this.getSpareNoBySpare(spare); // 生成备件编码
					//if(null == spareNo){
                       
						name = URLDecoder.decode(ary[1], "UTF-8");// 转码
						model = URLDecoder.decode(ary[2], "UTF-8");
						orderNo = URLDecoder.decode(ary[3], "UTF-8");
						category = URLDecoder.decode(ary[5], "UTF-8");
						//detailCategory = URLDecoder.decode(ary[6], "UTF-8");
						strFactoryId = URLDecoder.decode(ary[4], "UTF-8");
						
						name = name.trim();
						model = model.trim();
						category = category.trim();
						//detailCategory = detailCategory.trim();
						strFactoryId = strFactoryId.trim();
						
						
						if(null ==orderNo){
							orderNo = "";
						}else if(!"".equals(orderNo)){
							orderNo = orderNo.trim();
						}
						
						if(!"".equals(strFactoryId.trim()) && null != strFactoryId){
						    factoryId = Long.parseLong(strFactoryId);
							factory = this.supplierDao.loadSupplier(factoryId);

						}
						
						//String[] keyName= {name,model,orderNo,strFactoryId,category,detailCategory};
						String[] keyName= {name,model,orderNo,strFactoryId,category};
						Spare spare = this.listSpareByArry(keyName);
					    if(null != spare){
					    	// int cId = spare.getCategory().getId().intValue();
					    	// int sId = spare.getSpareDetailType().getId().intValue();
					    	 
					    	// if(( cId == Integer.parseInt(category)) && (sId == Integer.parseInt(detailCategory))){
					    			spareNo  = this.getSpareNoBySpare(spare); // 生成备件编码
								    if(null == spareNo){
										spare = new Spare();
										spare.setName(name);
										spare.setModelSpecs(model);
										spare.setCategory(this.spareTypeDao.loadSpareType(Long.parseLong(category)));
										//spare.setSpareDetailType(this.spareDetailTypeDao.loadSpareDetailType(Long.parseLong(detailCategory)));
										spare.setToolingDevFlag(sub.getCategory().getToolingDevFlag());
										spareNo = generateSpareNo(spare); // 生成备件编码
										spare.setSpareNo(spareNo);
				                        spare.setOrderNo(orderNo);
				                        if(null != factory){
				                        	 spare.setFactory(factory);
				 	                         spare.setFactoryStr(factory.getName());
				                        }
				                    
										this.spareDao.storeSpare(spare);
										
									}
					    	// }else{
					    		  
					    	// }
					    	
					    }else{
					    	spare = new Spare();
							spare.setName(name);
							spare.setModelSpecs(model);
							spare.setCategory(this.spareTypeDao.loadSpareType(Long.parseLong(category)));
							//spare.setSpareDetailType(this.spareDetailTypeDao.loadSpareDetailType(Long.parseLong(detailCategory)));
							spare.setToolingDevFlag(sub.getCategory().getToolingDevFlag());
							spareNo = generateSpareNo(spare); // 生成备件编码
							spare.setSpareNo(spareNo);
	                        spare.setOrderNo(orderNo);
	                        if(null != factory){
	                        	 spare.setFactory(factory);
	 	                         spare.setFactoryStr(factory.getName());
	                        }
	                    
							this.spareDao.storeSpare(spare);
							
					    	
					    }
				 
					 	 
					
				  // }
					 
					sub.setSpare(spare);
					sub.setGraphNo(spareNo);
					sub.setCategory(spare.getCategory());
					//sub.setDetailCategory(spare.getSpareDetailType());
					sub.setModel(spare.getModelSpecs());
					sub.setName(spare.getName());
					sub.setOrderNo(spare.getOrderNo());
				    if(null != factory){
				    	sub.setFactory(spare.getFactory());
					}
					
					
					this.subscribeDao.storeSubscribeDetail(sub);

					
				}

			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return spareNo;

	}
	/**
	 * 查找备件 zzb
	 * @param keyName
	 * @param keyValue
	 * @return
	 */
	public Spare listSpareByArry(String[] keyName){
		Spare spare = null;
		try {
			List<Spare> list = this.spareDao.loadByKeyArry(keyName);
			if(null != list && list.size()>0){
				spare = list.get(0);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return spare;
	}
	/**
	 * 查找备件编号，条件 备件不为空 
	 * 如果有编号则返回，没有则新建编号
	 * 如果备件为空 返回 null
	 * @param spare
	 * @return
	 */
	private String getSpareNoBySpare(Spare spare){
		String spareNo = null;
		if(null != spare && null!=spare.getId()){
			spareNo = spare.getSpareNo();
			if(null == spareNo || "".equals(spareNo)){
//				spareNo = this.generateSpareNo(spare);- 备件编码改为手动生成，所以在这不需要自动生成
//				spare.setSpareNo(spareNo);
				this.spareDao.storeSpare(spare);
			}
		}
		return spareNo;
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
			categoryCode = spare.getCategory().getCode();
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

	public void storeSpare(String strValue, String sysLogValue) {
		String[] ary = strValue.split(",");
		Spare spare = new Spare();
		for (int i = 0; i < ary.length; i = i + 2) {
			spare = this.spareDao.loasSpare(Long.valueOf(ary[i]));
			spare.setSafeStock(Long.valueOf(ary[i + 1]));
			storeSpare(spare, sysLogValue);
		}
	}

	public void storeSpare(String strValue) {
		String[] ary = strValue.split(",");
		Spare oldSpare;
		Spare spare = new Spare();
		for (int i = 0; i < ary.length; i = i + 2) {
			spare = this.spareDao.loasSpare(Long.valueOf(ary[i]));
			oldSpare = spare;
			spare.setSafeStock(Long.valueOf(ary[i + 1]));
			if (oldSpare.getSafeStock() != spare.getSafeStock()) {
				SysLog sysLog = new SysLog();
				String commentString = spare.getName()
						+ oldSpare.getSafeStock() + "->" + spare.getSafeStock()
						+ spare.getLastOperator() + spare.getLastModifiedTime();
				sysLog.setContent(commentString);
				sysLog.setType(SysLogType.SPARE_SAFESTOCK);
				this.sysLogManager.write(sysLog);

			}
			this.spareDao.storeSpare(spare);
		}
	}

	public List<QueryUnMatchSpare> queryUnMatchSpare() {
		List<Spare> spares = spareDao.loadAllSpares();
		List<QueryUnMatchSpare> spareInventoryDetails = new ArrayList<QueryUnMatchSpare>();
		long actualValue;
		for (Spare spare : spares) {
			actualValue = countSpareInOutValue(spare);
			if (actualValue != spare.getStocks()) {
				QueryUnMatchSpare spareInventoryDetail = new QueryUnMatchSpare();
				spareInventoryDetail.setSpare(spare);
				spareInventoryDetail.setInventoryNum(actualValue);
				spareInventoryDetail.setCurrentSysStocks(spare.getStocks());
				spareInventoryDetails.add(spareInventoryDetail);
			}
		}
		return spareInventoryDetails;
	}

	public void deleteSpare(Spare spare) {
		this.spareDao.deleteSpare(spare);
	}

	public void deleteAllSpare(Collection<Spare> spares) {
		this.spareDao.deleteAllSpare(spares);
	}

	@SuppressWarnings("unchecked")
	public List Query(String[] queryInfo) throws HibernateException {
		return spareDao.Query(queryInfo);
	}

	public List getSpareCollectionByModelSpares(String modelSpare)
			throws Exception {
		return this.spareDao.getSpareCollectionByModelSpares(modelSpare);
	}

	public List getSpareCollectionBySpecification(String specification)
			throws Exception {

		return this.spareDao.getSpareCollectionBySpecification(specification);

	}

	public List getSpareCollentionByModel(String modelSpare) throws Exception {
		return spareDao.getSpareCollentionByModel(modelSpare);
	}

	public List getSpareCollentionBySpec(String specification) throws Exception {
		return spareDao.getSpareCollentionBySpec(specification);
	}

	public Integer getSpareNumByModelAndName(String name, String modelSpare) {
		return this.spareDao.getSpareNumByModelAndName(name, modelSpare);
	}

	public List<Spare> loadByKey(String key, Object value) throws DaoException {
		return this.spareDao.loadByKey(key, value);
	}
	public Spare loadBySpareNo(String SpareNo) {
		return this.spareDao.loadBySpareNo(SpareNo);
	}

	/**
	 * 根据备件名称、型号、订货号、生产厂家获取备件
	 * @param name 备件名称
	 * @param modelSpare  备件型号
	 * @param orderNo  订货号
	 * @param factoryName  生产厂家
	 * @return 匹配备件
	 */
	public Spare loadByPamrameter(String name, String modelSpecs, String orderNo, String factoryName) {
		return this.spareDao.loadByPamrameter(name, modelSpecs, orderNo, factoryName);
	}

	public List<Spare> loadByKeyArry(String[] value) throws DaoException {
		return this.spareDao.loadByKeyArry(value);
	}
	
 

}
