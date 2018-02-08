package com.yongjun.tdms.service.runmaintenance.discard.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.discard.DiscardBillDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBillDtl;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardBillManager;

/**
 * 
 * <p>Title: DefaultDiscardBillManager
 * <p>Description: 报废业务处理控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 * 
 */
public class DefaultDiscardBillManager implements DiscardBillManager{
  private final DiscardBillDao  discardBillDao;  //设备报废单的数据接口
  private final CodeValueManager codeValueManager;
  private final DeviceCardManager deviceCardManager; 
  private final SequenceManager sequenceManager;
  public DefaultDiscardBillManager(DiscardBillDao  discardBillDao,
		  SequenceManager sequenceManager,CodeValueManager codeValueManager,
		  DeviceCardManager deviceCardManager){
	  this.discardBillDao=discardBillDao;
	  this.sequenceManager=sequenceManager;
	  this.codeValueManager=codeValueManager;
	  this.deviceCardManager=deviceCardManager;
  }
	public DiscardBill loadDiscardBill(Long discardBillId) {
		
		return discardBillDao.loadDiscardBill(discardBillId);
	}

	public List<DiscardBill> loadAllDiscardBills(Long[] discardBillIds) {
		
		return this.discardBillDao.loadAllDiscardBills(discardBillIds);
	}

	public void storeDiscardBill(DiscardBill discardBill) {
		if(discardBill.isNew()){
			String billNo=(String)sequenceManager.generate("-");
			discardBill.setBillNo(billNo);
		}
		 this.discardBillDao.storeDiscardBill(discardBill);
		 updateDevStatus(discardBill); 
		   //根据报废单  更新设备状态
	}
	public void updateDevStatus(DiscardBill discardBill){  //根据报废单  更新设备状态
		   for(DiscardBillDtl dtl:discardBill.getDiscardBillDtl()){
			   if(discardBill.isDiscardAgree()==true){
				DeviceCard DevStatus=  dtl.getDeviceCard();
				DevStatus.setDeviceStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_DISCARD_STATUS));  
				deviceCardManager.storeDevice(DevStatus);
			   }
		   }
	}

	public void deleteDiscardBill(DiscardBill discardBill) {
		    this.discardBillDao.deleteDiscardBill(discardBill);
		
	}

	public void deleteAllDiscardBill(Collection<DiscardBill> discardBills) {
		     this.discardBillDao.deleteAllDiscardBill(discardBills);
		
	}
     //失效报废单
	public void disabledAllDiscardBills(Collection<DiscardBill> DiscardBills) {
		     for(DiscardBill oil:DiscardBills){
		    	 oil.setDisabled(true);
		    	
		    	this.discardBillDao.storeDiscardBill(oil);
		     }
		    
	}
    //有效报废单
	public void enabledAllDiscardBills(Collection<DiscardBill> DiscardBills) {
		for(DiscardBill oil:DiscardBills){
	    	 oil.setDisabled(false);
	    	this.discardBillDao.storeDiscardBill(oil);
	     }  
		  
	}


}
