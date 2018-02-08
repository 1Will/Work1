package com.yongjun.tdms.model.workReport.boardroom;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;

/**
 *会议室
 */

public class Boardroom extends BaseInfoEntity{
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private Double square;
	private CodeValue state;
	private Boolean hasProjector;//有无投影仪
	private Boolean hasNet;//有无投影仪
	private Double maxPeople;
	private String explain;
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object paramObject) {
		return false;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Double getSquare() {
		return square;
	}

	public CodeValue getState() {
		return state;
	}

	public Boolean getHasProjector() {
		return hasProjector;
	}

	public String getExplain() {
		return explain;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSquare(Double square) {
		this.square = square;
	}

	public void setState(CodeValue state) {
		this.state = state;
	}

	public void setHasProjector(Boolean hasProjector) {
		this.hasProjector = hasProjector;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Boolean getHasNet() {
		return hasNet;
	}

	public Double getMaxPeople() {
		return maxPeople;
	}

	public void setHasNet(Boolean hasNet) {
		this.hasNet = hasNet;
	}

	public void setMaxPeople(Double maxPeople) {
		this.maxPeople = maxPeople;
	}

}
