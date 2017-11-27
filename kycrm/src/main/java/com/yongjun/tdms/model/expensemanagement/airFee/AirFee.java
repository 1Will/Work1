package com.yongjun.tdms.model.expensemanagement.airFee;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.house.Building;

public class AirFee extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private Building building;
	private Date starTime;
	private Date endTime;
	private int openDays;
	private int factDay;//实际天数
	private String airFeeName;//空调费名称
	private String isSaved;// 提交判断
	
	public String getAirFeeName() {
		return airFeeName;
	}

	public void setAirFeeName(String airFeeName) {
		this.airFeeName = airFeeName;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public Building getBuilding() {
		return building;
	}

	public Date getStarTime() {
		return starTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getFactDay() {
		return factDay;
	}
	
	public void setFactDay(int factDay) {
		this.factDay = factDay;
	}

	public int getOpenDays() {
		return openDays;
	}

	public void setOpenDays(int openDays) {
		this.openDays = openDays;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
		
}
