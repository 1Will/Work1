package com.yongjun.tdms.model.base.house;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.meter.ElectricMeter;

public class Floor extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2582643373170911485L;
	private String name;
	private String code;
	private Building building;
	private boolean hasWaterMeter;// 是否有水表
	private float waterMeterNum;// 水表数量
	private boolean hasElectricMeter;// 是否有电表
	private float electricMeterNum;// 电表数量
	private boolean hasAirMeter;// 是否有空调
	private float airMeterNum;// 空调表数量
	private boolean hasNetMeter;// 是否有网络
	private ElectricMeter aeMeter;
	private ElectricMeter beMeter;
	private Double waterdisplay=0D;//水表示数
	private Double aedisplay=0D;//a电表示数
	private Double bedisplay=0D;//b电表示数
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public Building getBuilding() {
		return building;
	}

	public boolean isHasWaterMeter() {
		return hasWaterMeter;
	}

	public float getWaterMeterNum() {
		return waterMeterNum;
	}

	public boolean isHasElectricMeter() {
		return hasElectricMeter;
	}

	public float getElectricMeterNum() {
		return electricMeterNum;
	}

	public boolean isHasAirMeter() {
		return hasAirMeter;
	}

	public float getAirMeterNum() {
		return airMeterNum;
	}

	public boolean isHasNetMeter() {
		return hasNetMeter;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setHasWaterMeter(boolean hasWaterMeter) {
		this.hasWaterMeter = hasWaterMeter;
	}

	public void setWaterMeterNum(float waterMeterNum) {
		this.waterMeterNum = waterMeterNum;
	}

	public void setHasElectricMeter(boolean hasElectricMeter) {
		this.hasElectricMeter = hasElectricMeter;
	}

	public void setElectricMeterNum(float electricMeterNum) {
		this.electricMeterNum = electricMeterNum;
	}

	public void setHasAirMeter(boolean hasAirMeter) {
		this.hasAirMeter = hasAirMeter;
	}

	public void setAirMeterNum(float airMeterNum) {
		this.airMeterNum = airMeterNum;
	}

	public void setHasNetMeter(boolean hasNetMeter) {
		this.hasNetMeter = hasNetMeter;
	}

	public ElectricMeter getAeMeter() {
		return aeMeter;
	}

	public ElectricMeter getBeMeter() {
		return beMeter;
	}

	public void setAeMeter(ElectricMeter aeMeter) {
		this.aeMeter = aeMeter;
	}

	public void setBeMeter(ElectricMeter beMeter) {
		this.beMeter = beMeter;
	}

	public Double getWaterdisplay() {
		return waterdisplay;
	}

	public Double getAedisplay() {
		return aedisplay;
	}

	public Double getBedisplay() {
		return bedisplay;
	}

	public void setWaterdisplay(Double waterdisplay) {
		this.waterdisplay = waterdisplay;
	}

	public void setAedisplay(Double aedisplay) {
		this.aedisplay = aedisplay;
	}

	public void setBedisplay(Double bedisplay) {
		this.bedisplay = bedisplay;
	}

}
