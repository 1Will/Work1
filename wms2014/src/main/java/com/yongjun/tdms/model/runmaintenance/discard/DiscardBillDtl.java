package com.yongjun.tdms.model.runmaintenance.discard;

import java.util.Date;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceFinanceInfo;
import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;

public class DiscardBillDtl  extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{
	private static final long serialVersionUID = 1L;
	//设备编号
	private String devBillNo;
	//型号
	private String model;
	//规格
	private String specification;
	//设备类别
	private String deviceCategory;
	//设备原值
	private Double origPrice;
	 //净残值比率
	private Double netValue;               
	//报废单所关联的设备财务信息
	private DeviceFinanceInfo info;
	//制造日期
	private Date manufactureDate;
    //设备报废明细所关联的设备
	private DeviceCard deviceCard;
    //使用年限
	private Integer useYear;
	//备注 
	private String memo;
	//设备报废明细所关联的设备报废单 
	private DiscardBill discardBill;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getDeviceCategory() {
		return deviceCategory;
	}
	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}
	public DeviceFinanceInfo getInfo() {
		return info;
	}
	public void setInfo(DeviceFinanceInfo info) {
		this.info = info;
	}
	public Date getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	public Double getOrigPrice() {
		return origPrice;
	}
	public void setOrigPrice(Double origPrice) {
		this.origPrice = origPrice;
	}
	public Integer getUseYear() {
		return useYear;
	}
	public void setUseYear(Integer useYear) {
		this.useYear = useYear;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public DiscardBill getDiscardBill() {
		return discardBill;
	}
	public void setDiscardBill(DiscardBill discardBill) {
		this.discardBill = discardBill;
	}
	public DeviceCard getDeviceCard() {
		return deviceCard;
	}
	public void setDeviceCard(DeviceCard deviceCard) {
		this.deviceCard = deviceCard;
	}
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {return true;}
		if (o instanceof DiscardBillDtl) {
			return true;
		}
		return false;
	}
	public String getDevBillNo() {
		return devBillNo;
	}
	public void setDevBillNo(String devBillNo) {
		this.devBillNo = devBillNo;
	}
	public Double getNetValue() {
		return netValue;
	}
	public void setNetValue(Double netValue) {
		this.netValue = netValue;
	}
	
}
