package com.yongjun.tdms.model.expensemanagement.airFee;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.house.House;

public class AirHouseFee extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private House house;
	private Date starTime;
	private Date endTime;
	private double lastAir =0D;//上次空调表指数
	private double thisAir =0D;//本次次空调表指数
	private double sumAir =0D;//累积空调表指数
	private double sumFee =0D;//总空调费
	private int openDays;
	private int factDay;//实际天数
	private int useDay;//使用天数
	private AirFee airFee;
	private CustomerInfo customerInfo;
	private String outLine;//备注
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public House getHouse() {
		return house;
	}

	public Date getStarTime() {
		return starTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public double getLastAir() {
		return lastAir;
	}

	public double getThisAir() {
		return thisAir;
	}

	public double getSumAir() {
		return sumAir;
	}

	public double getSumFee() {
		return sumFee;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setLastAir(double lastAir) {
		this.lastAir = lastAir;
	}

	public void setThisAir(double thisAir) {
		this.thisAir = thisAir;
	}

	public void setSumAir(double sumAir) {
		this.sumAir = sumAir;
	}

	public void setSumFee(double sumFee) {
		this.sumFee = sumFee;
	}

	public int getFactDay() {
		return factDay;
	}

	public int getUseDay() {
		return useDay;
	}

	public void setFactDay(int factDay) {
		this.factDay = factDay;
	}

	public void setUseDay(int useDay) {
		this.useDay = useDay;
	}

	public int getOpenDays() {
		return openDays;
	}

	public void setOpenDays(int openDays) {
		this.openDays = openDays;
	}

	public AirFee getAirFee() {
		return airFee;
	}

	public void setAirFee(AirFee airFee) {
		this.airFee = airFee;
	}

	public String getOutLine() {
		return outLine;
	}

	public void setOutLine(String outLine) {
		this.outLine = outLine;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
		
}
