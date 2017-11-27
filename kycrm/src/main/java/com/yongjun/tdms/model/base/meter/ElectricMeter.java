package com.yongjun.tdms.model.base.meter;

import com.yongjun.pluto.model.BaseInfoEntity;

public class ElectricMeter extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String code;
	private float rate;
	
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

	public float getRate() {
		return rate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

}
