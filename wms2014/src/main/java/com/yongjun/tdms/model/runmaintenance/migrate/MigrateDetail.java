package com.yongjun.tdms.model.runmaintenance.migrate;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;

public class MigrateDetail extends BaseInfoEntity {
	private static final long serialVersionUID = 2727190654738064617L;

	//原安装地点
	private String oldInstallPlace;
	
	//新安装地点
	private String newInstallPlace;
	
	//原负责人
	private String oldManager;
	
	//新负责人
	private User newManager;
	
	//转移相关的资产[工装]|[设备]
	private DeviceCard asset;
	
	//关联的转移单
	private Migrate migrate;
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MigrateDetail)) {
			return false;
		}
		MigrateDetail migrateDetail = (MigrateDetail) o;
		if (migrateDetail.getId().equals(this.getId())) {
			return true;
		}
		return false;
	}

	public DeviceCard getAsset() {
		return asset;
	}

	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}

	public Migrate getMigrate() {
		return migrate;
	}

	public void setMigrate(Migrate migrate) {
		this.migrate = migrate;
	}

	public String getNewInstallPlace() {
		return newInstallPlace;
	}

	public void setNewInstallPlace(String newInstallPlace) {
		this.newInstallPlace = newInstallPlace;
	}

	public User getNewManager() {
		return newManager;
	}

	public void setNewManager(User newManager) {
		this.newManager = newManager;
	}

	public String getOldInstallPlace() {
		return oldInstallPlace;
	}

	public void setOldInstallPlace(String oldInstallPlace) {
		this.oldInstallPlace = oldInstallPlace;
	}

	public String getOldManager() {
		return oldManager;
	}

	public void setOldManager(String oldManager) {
		this.oldManager = oldManager;
	}

}
