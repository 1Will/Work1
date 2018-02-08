package com.yongjun.tdms.model.runmaintenance.migrate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;

public class Migrate extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = -8506886466024969249L;

	//转移编号
	private String billNo;
	
	//转移名称
	private String billName;
	
	//转移申请日期
	private Date requestDate;
	
	//转移申请人
	private User requester;
	
	//转移申请部门
	private Department requestDepartment;
	
	//转移原因
	private String migrateReason;
	
	//原所属部门
	private Department oldDepartment;
	
	//现所属部门
	private Department newDepartment;
	
	//原所属生产线
	private ProductionLine oldProductionLine;
	
	//现所属生产线
	private ProductionLine newProductionLine;
	
	//资产标识[工装]|[设备]
	private SysModel toolingDevFlag=SysModel.DEVICE;
	
	//关联的转移详细
	private Set<MigrateDetail> migrateDetail = new HashSet<MigrateDetail>();
	
	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getMigrateReason() {
		return migrateReason;
	}

	public void setMigrateReason(String migrateReason) {
		this.migrateReason = migrateReason;
	}

	public Department getNewDepartment() {
		return newDepartment;
	}

	public void setNewDepartment(Department newDepartment) {
		this.newDepartment = newDepartment;
	}

	public ProductionLine getNewProductionLine() {
		return newProductionLine;
	}

	public void setNewProductionLine(ProductionLine newProductionLine) {
		this.newProductionLine = newProductionLine;
	}

	public Department getOldDepartment() {
		return oldDepartment;
	}

	public void setOldDepartment(Department oldDepartment) {
		this.oldDepartment = oldDepartment;
	}

	public ProductionLine getOldProductionLine() {
		return oldProductionLine;
	}

	public void setOldProductionLine(ProductionLine oldProductionLine) {
		this.oldProductionLine = oldProductionLine;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Department getRequestDepartment() {
		return requestDepartment;
	}

	public void setRequestDepartment(Department requestDepartment) {
		this.requestDepartment = requestDepartment;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}
	
	@Override
	public int hashCode() {
		return billNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Migrate)) {
			return false;
		}
		Migrate migrate = (Migrate)o;
		if (this.getBillNo().equals(migrate.getBillNo())) {
			return true;
		}
		return false;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Set<MigrateDetail> getMigrateDetail() {
		return migrateDetail;
	}

	public void setMigrateDetail(Set<MigrateDetail> migrateDetail) {
		this.migrateDetail = migrateDetail;
	}

}
