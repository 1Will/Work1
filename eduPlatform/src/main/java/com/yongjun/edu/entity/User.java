package com.yongjun.edu.entity;

import lombok.Data;

@Data
public class User {
	/**
	 * 
	 */
	private Long id;
	private String code;
	private Long version;
	private String name;// 姓名
	private String loginName;
	private String openId;// 微信ID
	private int enabled;// 是否离职 1 在职 0离职
	private Long deptId;
}
