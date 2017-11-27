package com.yongjun.tdms.model.base.industryType;

import com.yongjun.pluto.model.BaseInfoEntity;

/**
 * 行业信息实体类
 * @author xcg
 * @data 2017年9月29日  
 *
 */
public class Industry extends BaseInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private long pid;
	private String name;//行业名
	private long type;//行业类型
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
