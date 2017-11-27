package com.yongjun.tdms.model.expensemanagement.electricFee;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.house.Building;

public class ElectricFee extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private boolean isAllBuilding;//是否是所有楼
	private Building building;
	private double lastElectricA =0D;//A上次电表指数
	private double thisElectricA =0D;//A本次次电表指数
	private double sumElectricA =0D;//A累积电表指数
	private double lastElectricB =0D;//B上次电表指数
	private double thisElectricB =0D;//B本次次电表指数
	private double sumElectricB =0D;//B累积电表指数
	private double sumElectric =0D;//累积电表指数
	private double sumFee =0D;//总电费
	private int month;
	private Date starTime;//开始时间
	private Date endTime;//结束时间
    private String electricFeeName;//电费名称
    private String isSaved;// 提交判断

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}
	
	public String getElectricFeeName() {
		return electricFeeName;
	}
	
	public void setElectricFeeName(String electricFeeName) {
		this.electricFeeName = electricFeeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean getIsAllBuilding() {
		return isAllBuilding;
	}
	
	public Building getBuilding() {
		return building;
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

	public void setIsAllBuilding(boolean isAllBuilding) {
		this.isAllBuilding = isAllBuilding;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setSumFee(double waterFee) {
		this.sumFee = waterFee;
	}

	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public double getSumElectric() {
		return sumElectric;
	}

	public void setSumElectric(double sumElectric) {
		this.sumElectric = sumElectric;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

}
