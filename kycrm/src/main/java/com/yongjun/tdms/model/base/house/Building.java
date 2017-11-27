package com.yongjun.tdms.model.base.house;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.base.meter.ElectricMeter;

public class Building extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2582643373170911485L;
	private String name;
	private String code;
	private boolean hasWaterMeter;//是否有水表
	private boolean hasElectricMeter;//是否有电表
	private boolean hasAirMeter;//是否有空调
	private boolean hasNetMeter;//是否有网络
	private CodeValue waterModel;
	private ElectricMeter aeMeter;
	private ElectricMeter beMeter;
	private Double waterPrice;//水费价格
	private Double electricCivilPrice;//民用电价格
	private Double electricIndustryPrice;//工业电价格
	private Double airPrice;//空调流量价格
	private Double airBasePrice;//空调基数价格
	private Double electricSharePrice;//公摊电价格
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

	public boolean isHasWaterMeter() {
		return hasWaterMeter;
	}

	public boolean isHasElectricMeter() {
		return hasElectricMeter;
	}

	public boolean isHasAirMeter() {
		return hasAirMeter;
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

	public void setHasWaterMeter(boolean hasWaterMeter) {
		this.hasWaterMeter = hasWaterMeter;
	}

	public void setHasElectricMeter(boolean hasElectricMeter) {
		this.hasElectricMeter = hasElectricMeter;
	}

	public void setHasAirMeter(boolean hasAirMeter) {
		this.hasAirMeter = hasAirMeter;
	}

	public void setHasNetMeter(boolean hasNetMeter) {
		this.hasNetMeter = hasNetMeter;
	}

	public CodeValue getWaterModel() {
		return waterModel;
	}

	public void setWaterModel(CodeValue waterModel) {
		this.waterModel = waterModel;
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

	public Double getWaterPrice() {
		return waterPrice;
	}

	public Double getElectricCivilPrice() {
		return electricCivilPrice;
	}

	public Double getElectricIndustryPrice() {
		return electricIndustryPrice;
	}

	public Double getAirPrice() {
		return airPrice;
	}

	public void setWaterPrice(Double waterPrice) {
		this.waterPrice = waterPrice;
	}

	public void setElectricCivilPrice(Double electricCivilPrice) {
		this.electricCivilPrice = electricCivilPrice;
	}

	public void setElectricIndustryPrice(Double electricIndustryPrice) {
		this.electricIndustryPrice = electricIndustryPrice;
	}

	public void setAirPrice(Double airPrice) {
		this.airPrice = airPrice;
	}

	public Double getElectricSharePrice() {
		return electricSharePrice;
	}

	public void setElectricSharePrice(Double electricSharePrice) {
		this.electricSharePrice = electricSharePrice;
	}

	public Double getAirBasePrice() {
		return airBasePrice;
	}

	public void setAirBasePrice(Double airBasePrice) {
		this.airBasePrice = airBasePrice;
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
