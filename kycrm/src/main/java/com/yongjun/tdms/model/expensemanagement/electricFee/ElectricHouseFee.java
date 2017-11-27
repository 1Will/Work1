package com.yongjun.tdms.model.expensemanagement.electricFee;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.house.House;

public class ElectricHouseFee extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private House house;
	private double lastElectricA = 0D;// A上次电表指数
	private double thisElectricA = 0D;// A本次次电表指数
	private double sumElectricA = 0D;// A累积电表指数
	private double lastElectricB = 0D;// B上次电表指数
	private double thisElectricB = 0D;// B本次次电表指数
	private double sumElectricB = 0D;// B累积电表指数
	private double sumElectric = 0D;// 累积电表指数
	private double shareFee =0D;//公摊电费
	private double sumFee =0D;//总电费
	private Date starTime;//开始时间
	private Date endTime;//结束时间
	private int days;//间隔天数
	private ElectricFee electricFee;
    private String outLine;//备注
    private CustomerInfo customerInfo;
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getSumFee() {
		return sumFee;
	}

	public Date getStarTime() {
		return starTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public void setSumFee(double sumFee) {
		this.sumFee = sumFee;
	}

	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public ElectricFee getElectricFee() {
		return electricFee;
	}

	public void setElectricFee(ElectricFee electricFee) {
		this.electricFee = electricFee;
	}
	
	public double getLastElectricA() {
		return lastElectricA;
	}

	public double getThisElectricA() {
		return thisElectricA;
	}

	public double getSumElectricA() {
		return sumElectricA;
	}

	public double getLastElectricB() {
		return lastElectricB;
	}

	public double getThisElectricB() {
		return thisElectricB;
	}

	public double getSumElectricB() {
		return sumElectricB;
	}

	public void setLastElectricA(double lastElectricA) {
		this.lastElectricA = lastElectricA;
	}

	public void setThisElectricA(double thisElectricA) {
		this.thisElectricA = thisElectricA;
	}

	public void setSumElectricA(double sumElectricA) {
		this.sumElectricA = sumElectricA;
	}

	public void setLastElectricB(double lastElectricB) {
		this.lastElectricB = lastElectricB;
	}

	public void setThisElectricB(double thisElectricB) {
		this.thisElectricB = thisElectricB;
	}

	public void setSumElectricB(double sumElectricB) {
		this.sumElectricB = sumElectricB;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public double getSumElectric() {
		return sumElectric;
	}

	public void setSumElectric(double sumElectric) {
		this.sumElectric = sumElectric;
	}

	public double getShareFee() {
		return shareFee;
	}

	public void setShareFee(double shareFee) {
		this.shareFee = shareFee;
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
