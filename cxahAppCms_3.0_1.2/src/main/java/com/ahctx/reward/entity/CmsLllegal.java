package com.ahctx.reward.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 违章处理表(CMS_LLLEGAL)
 *
 */
@TableName(value = "cms_lllegal")
public class CmsLllegal implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 编号 */
	@TableId
	private Long id;

	/** 名称 */
	private String name;

	/** 电话 */
	private String tel;

	/** 地址 */
	private String addr;

	/** 纬度 */
	private Double latitude;

	/** 经度 */
	private Double longitude;

	/** 省份 */
	private Long provice;

	/** 城市 */
	private Long city;

	/** 上午上班时间 */
	private String worktimeAM;

	/** 下午上班时间 */
	private String worktimePM;

	/** 周工作时间 */
	private String worktimeWeek;

	/** 创建用户 */
	private String createUser;

	/** 创建时间 */
	private Date createTime;

	/** 修改用户 */
	private String modifyUser;

	/** 修改时间 */
	private Date modifyTime;

	/** 版本 */
	private Integer version;

	/** 删除标记（0：正常  1：逻辑删除） */
	private String deleteFlag;

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

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getProvice() {
		return this.provice;
	}

	public void setProvice(Long provice) {
		this.provice = provice;
	}

	public Long getCity() {
		return this.city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public String getWorktimeAM() {
		return this.worktimeAM;
	}

	public void setWorktimeAM(String worktimeAM) {
		this.worktimeAM = worktimeAM;
	}

	public String getWorktimePM() {
		return this.worktimePM;
	}

	public void setWorktimePM(String worktimePM) {
		this.worktimePM = worktimePM;
	}

	public String getWorktimeWeek() {
		return this.worktimeWeek;
	}

	public void setWorktimeWeek(String worktimeWeek) {
		this.worktimeWeek = worktimeWeek;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}