package com.yongjun.tdms.workflow.service;

import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: SimplePoJo.java 7614 2007-09-26 01:49:17Z qsun $
 */
public class SimplePoJo extends BaseInfoEntity {
	private static final long serialVersionUID = -3542208487500261178L;
	private Long id = -1L;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public SimplePoJo() {
		
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

}
