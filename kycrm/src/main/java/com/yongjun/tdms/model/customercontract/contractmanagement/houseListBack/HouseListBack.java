package com.yongjun.tdms.model.customercontract.contractmanagement.houseListBack;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;

public class HouseListBack extends BaseInfoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContractManagement contractManagement;
	private House house;
	private Double backRent;//房租金额、
	private Double waterEleFee;//水电费
	private Double allFee;//总金额
	private Double eleLastA;//电表A上次示数
	private Double eleNowA;//电表A本次示数
	private Double allEleA;//电表A用电量
	private Double eleLastB;//电表B上次示数
	private Double eleNowB;//电表B本次示数
	private Double allEleB;//电表B用电量
	private Double  allEle;//总电量
	private Double  shareEle;//公摊电量
	private Double allEleFee;//总电费
	
	private Double waterLast;//水表上次示数
	private Double waterNow;//水表本次示数
	private Double  shareWater;//公摊水量
	private Double allWater;//总用水量
	private Double waterPrice;//水费单价
	private Double waterFee;//总水费
	
	private Double airLast;//空调上次示数
	private Double airNow;//空调本次示数
	private Double allAir;//空调使用量
	private Double airPrice;//空调基准费
	private Double airFee;//总空调费
	
	private int netNum;//网络端口数
	private Double netPrice;//网络费率
	private Double netFee;//网络费
	
	private Double propertyFee;//物业费
	
	private Date startTime; //退房日期
	private Date endTime;//结束时间
	
	private int days;//天数
	private CodeValue backType;//退房类型
	private CodeValue state;//状态
	private String remark;//退房说明
	@Override
	public boolean equals(Object paramObject) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public House getHouse() {
		return house;
	}

	public CodeValue getBackType() {
		return backType;
	}

	public void setBackType(CodeValue backType) {
		this.backType = backType;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public CodeValue getState() {
		return state;
	}

	public void setState(CodeValue state) {
		this.state = state;
	}

	public Double getBackRent() {
		return backRent;
	}

	public void setBackRent(Double backRent) {
		this.backRent = backRent;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getWaterEleFee() {
		return waterEleFee;
	}

	public void setWaterEleFee(Double waterEleFee) {
		this.waterEleFee = waterEleFee;
	}

	public Double getAllFee() {
		return allFee;
	}

	public void setAllFee(Double allFee) {
		this.allFee = allFee;
	}

	public Double getEleLastA() {
		return eleLastA;
	}

	public void setEleLastA(Double eleLastA) {
		this.eleLastA = eleLastA;
	}

	public Double getEleNowA() {
		return eleNowA;
	}

	public void setEleNowA(Double eleNowA) {
		this.eleNowA = eleNowA;
	}

	public Double getAllEleA() {
		return allEleA;
	}

	public void setAllEleA(Double allEleA) {
		this.allEleA = allEleA;
	}

	public Double getEleLastB() {
		return eleLastB;
	}

	public void setEleLastB(Double eleLastB) {
		this.eleLastB = eleLastB;
	}

	public Double getEleNowB() {
		return eleNowB;
	}

	public void setEleNowB(Double eleNowB) {
		this.eleNowB = eleNowB;
	}

	public Double getAllEleB() {
		return allEleB;
	}

	public void setAllEleB(Double allEleB) {
		this.allEleB = allEleB;
	}

	public Double getAllEle() {
		return allEle;
	}

	public void setAllEle(Double allEle) {
		this.allEle = allEle;
	}

	public Double getShareEle() {
		return shareEle;
	}

	public void setShareEle(Double shareEle) {
		this.shareEle = shareEle;
	}

	public Double getAllEleFee() {
		return allEleFee;
	}

	public void setAllEleFee(Double allEleFee) {
		this.allEleFee = allEleFee;
	}

	public Double getWaterLast() {
		return waterLast;
	}

	public void setWaterLast(Double waterLast) {
		this.waterLast = waterLast;
	}

	public Double getWaterNow() {
		return waterNow;
	}

	public void setWaterNow(Double waterNow) {
		this.waterNow = waterNow;
	}

	public Double getShareWater() {
		return shareWater;
	}

	public void setShareWater(Double shareWater) {
		this.shareWater = shareWater;
	}

	public Double getAllWater() {
		return allWater;
	}

	public void setAllWater(Double allWater) {
		this.allWater = allWater;
	}

	public Double getWaterPrice() {
		return waterPrice;
	}

	public void setWaterPrice(Double waterPrice) {
		this.waterPrice = waterPrice;
	}

	public Double getWaterFee() {
		return waterFee;
	}

	public void setWaterFee(Double waterFee) {
		this.waterFee = waterFee;
	}

	public Double getAirLast() {
		return airLast;
	}

	public void setAirLast(Double airLast) {
		this.airLast = airLast;
	}

	public Double getAirNow() {
		return airNow;
	}

	public void setAirNow(Double airNow) {
		this.airNow = airNow;
	}

	public Double getAllAir() {
		return allAir;
	}

	public void setAllAir(Double allAir) {
		this.allAir = allAir;
	}

	public Double getAirPrice() {
		return airPrice;
	}

	public void setAirPrice(Double airPrice) {
		this.airPrice = airPrice;
	}

	public Double getAirFee() {
		return airFee;
	}

	public void setAirFee(Double airFee) {
		this.airFee = airFee;
	}

	public int getNetNum() {
		return netNum;
	}

	public void setNetNum(int netNum) {
		this.netNum = netNum;
	}

	public Double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}

	public Double getNetFee() {
		return netFee;
	}

	public void setNetFee(Double netFee) {
		this.netFee = netFee;
	}

	public Double getPropertyFee() {
		return propertyFee;
	}

	public void setPropertyFee(Double propertyFee) {
		this.propertyFee = propertyFee;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}


}
