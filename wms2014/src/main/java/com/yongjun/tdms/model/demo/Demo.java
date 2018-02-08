package com.yongjun.tdms.model.demo;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;


public class Demo extends BaseInfoEntity {
	private static final long serialVersionUID = 156312446816066327L;
	Long id;
	String name;
	String password;
	Date createDateTm;
	Date updateDateTm;
	String createBy;
	String updateBy;
	
	public Demo() {
		
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDateTm() {
		return createDateTm;
	}

	public void setCreateDateTm(Date createDateTm) {
		this.createDateTm = createDateTm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDateTm() {
		return updateDateTm;
	}

	public void setUpdateDateTm(Date updateDateTm) {
		this.updateDateTm = updateDateTm;
	}

	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}
}
