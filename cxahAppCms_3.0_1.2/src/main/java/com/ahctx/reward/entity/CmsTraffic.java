package com.ahctx.reward.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 实时路况表(CMS_TRAFFIC)
 *
 */
@TableName(value = "cms_traffic")
public class CmsTraffic implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 编号 */
	@TableId
	private Long id;

	/** 地址 */
	private String addr;

	/** 纬度 */
	private Double latitude;

	/** 经度 */
	private Double longitude;

	/** 省份 */
	private Long province;

	/** 城市 */
	private Long city;

	/** 描述 */
	private String description;
	
	/** 现场照片 */
	private String scenePhotos;

	/** 开始时间 */
	private Date startTime;

	/** 结束时间 */
	private Date endTime;

	/** 状态 */
	private Long status;

	/** 路况类别 */
	private Long type;

	/** 提交用户 */
	private Long submitUser;

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

	public Long getProvince() {
		return this.province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getCity() {
		return this.city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getScenePhotos() {
		return scenePhotos;
	}

	public void setScenePhotos(String scenePhotos) {
		this.scenePhotos = scenePhotos;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getType() {
		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getSubmitUser() {
		return this.submitUser;
	}

	public void setSubmitUser(Long submitUser) {
		this.submitUser = submitUser;
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
