package com.baomidou.springmvc.model.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 系统用户表
 *
 */
@TableName("sys_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private Long id;

	/** 用户名 */
	private String name;

	/** 用户年龄 */
	private Integer age;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}