package com.yongjun.tdms.model.expensemanagement.waterFee;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.house.Building;

public class WaterFee extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private boolean isAllBuilding;//是否是所有楼
	private Building building;
	private double lastWater =0D;//上次水表指数
	private double thisWater =0D;//本次次水表指数
	private double sumWater =0D;//累积水表指数
	private double shareWater=0D;//公摊水数
	private double sumFee =0D;//总水费
	private Date starTime;//开始时间
	private Date endTime;//结束时间
	private int month;
	private String waterFeeName;//水费名称
	private String isSaved;// 提交判断

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

	public boolean getIsAllBuilding() {
		return isAllBuilding;
	}
	
	public Building getBuilding() {
		return building;
	}

	public double getLastWater() {
		return lastWater;
	}

	public double getThisWater() {
		return thisWater;
	}

	public double getSumWater() {
		return sumWater;
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

	public void setLastWater(double lastWater) {
		this.lastWater = lastWater;
	}

	public void setThisWater(double thisWater) {
		this.thisWater = thisWater;
	}

	public void setSumWater(double sumWater) {
		this.sumWater = sumWater;
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

	public double getShareWater() {
		return shareWater;
	}

	public void setShareWater(double shareWater) {
		this.shareWater = shareWater;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getWaterFeeName() {
		return waterFeeName;
	}

	public void setWaterFeeName(String waterFeeName) {
		this.waterFeeName = waterFeeName;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

}
