package com.ahctx.reward.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 用户表
 *
 */
@TableName(value = "registeruser")
public class RegisterUser implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	@TableId
	private Long id;

	/** 驾驶人姓名 */
	private String nameOfDriver;

    /** 驾驶证号 */
	private String driverNumber;
	
	/** 手机号码 */
	private String phoneNumber;
	
	/** 车牌号 */
	private String plateNumber;
	
	/** 车辆类型 */
	private String vehicleType;
	
	/** 车辆登记日期 */
	private Date vehicleTime;
	
	/** 车辆登记日期 */
	private String vehicleTimeString;
	
	/** 车驾号 */
	private String frameNumber;

	/** 创建时间 */
	private Timestamp crTime;//最近修改时间
	
	/** 地市标示 */
	private String domain;
	
	private String areaCode;
	
	private String validateTrue;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameOfDriver() {
		return nameOfDriver;
	}

	public void setNameOfDriver(String nameOfDriver) {
		this.nameOfDriver = nameOfDriver;
	}

	public String getDriverNumber() {
		return driverNumber;
	}

	public void setDriverNumber(String driverNumber) {
		this.driverNumber = driverNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Date getVehicleTime() {
		return vehicleTime;
	}

	public void setVehicleTime(Date vehicleTime) {
		this.vehicleTime = vehicleTime;
	}

	public String getVehicleTimeString() {
		return vehicleTimeString;
	}

	public void setVehicleTimeString(String vehicleTimeString) {
		this.vehicleTimeString = vehicleTimeString;
	}

	public String getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	public Timestamp getCrTime() {
		return crTime;
	}

	public void setCrTime(Timestamp crTime) {
		this.crTime = crTime;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getValidateTrue() {
		return validateTrue;
	}

	public void setValidateTrue(String validateTrue) {
		this.validateTrue = validateTrue;
	}

}
