package com.yongjun.tdms.model.expensemanagement.waterFee;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.house.Floor;

public class WaterFloorFee extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private Floor floor;
	private double lastWater =0D;//上次水表指数
	private double thisWater =0D;//本次次水表指数
	private double sumWater =0D;//累积水表指数
	private double shareWater=0D;//公摊水数
	private double sumFee =0D;//总水费
	private Date starTime;//开始时间
	private Date endTime;//结束时间
	private WaterFee waterFee;
    private String outLine;//备注
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

	public Floor getFloor() {
		return floor;
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

	public WaterFee getWaterFee() {
		return waterFee;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
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

	public void setSumFee(double sumFee) {
		this.sumFee = sumFee;
	}

	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setWaterFee(WaterFee waterFee) {
		this.waterFee = waterFee;
	}

	public double getShareWater() {
		return shareWater;
	}

	public void setShareWater(double shareWater) {
		this.shareWater = shareWater;
	}

	public String getOutLine() {
		return outLine;
	}

	public void setOutLine(String outLine) {
		this.outLine = outLine;
	}


}
