package com.yongjun.tdms.model.asset.spare.inWareHouse;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
/**
 * <p>Title: SpareInBill
 * <p>Description: 入库单</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: SpareInBill.java 29443 2010-12-14 10:31:19Z zbzhang $
 */
public class SpareInBill extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 7930477126670975299L;
	
	//入库单号
	private String code;
	//入库单名称
	private String name;
	//入库日期
	private Date inDate;
	//备注
	private String comment;
	//是否‘发送提醒’标识
	private boolean submit = false;
	//供应商
	private Supplier supplier;
	//入库人
	private User inPeople;
	//验收人
	private User checkPeople;
	//入仓库
	private Warehouse warehouse;	
	//总金额
	private Double totalPrice=0.0;
	//关联的入库单明细
	private Set<SpareInBillDetail> detail = new HashSet<SpareInBillDetail>();
    //入库单状态
	private SpareInBillStatus status=SpareInBillStatus.NEWSTATUS;
    //标识是否打印过验收单
	private boolean acceptanceList = false;
	
	//---------新增字段--------
    //仓库级别
	private CodeValue storageGrade;
   //	来源仓库
	private Warehouse outWarehouse;
	//出库单(冗余)
	private SpareOutBill spareOutBill;
	
	private String oldSpare;
	private String outDetailids;
    
	@Override
	public int hashCode() {
		return code.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this){return true;}
		if (o instanceof SpareInBill) {return true;}
		
		SpareInBill bill = (SpareInBill)o;
		if (bill.getCode().equals(this.code)) {
			return true;
		}
		return false;
	}

	public User getCheckPeople() {
		return checkPeople;
	}

	public void setCheckPeople(User checkPeople) {
		this.checkPeople = checkPeople;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public User getInPeople() {
		return inPeople;
	}

	public void setInPeople(User inPeople) {
		this.inPeople = inPeople;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}

	public Set<SpareInBillDetail> getDetail() {
		return detail;
	}

	public void setDetail(Set<SpareInBillDetail> detail) {
		this.detail = detail;
	}
	
	public String getDomainModelProperty() {
		return getProperties().getProperty("spare_in_bill");
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public SpareInBillStatus getStatus() {
		return status;
	}

	public void setStatus(SpareInBillStatus status) {
		this.status = status;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Boolean getAcceptanceList() {
		return acceptanceList;
	}

	public void setAcceptanceList(Boolean acceptanceList) {
		this.acceptanceList = acceptanceList;
	}

	public CodeValue getStorageGrade() {
		return storageGrade;
	}

	public void setStorageGrade(CodeValue storageGrade) {
		this.storageGrade = storageGrade;
	}

	public Warehouse getOutWarehouse() {
		return outWarehouse;
	}

	public void setOutWarehouse(Warehouse outWarehouse) {
		this.outWarehouse = outWarehouse;
	}

	public SpareOutBill getSpareOutBill() {
		return spareOutBill;
	}

	public void setSpareOutBill(SpareOutBill spareOutBill) {
		this.spareOutBill = spareOutBill;
	}

	public String getOldSpare() {
		return oldSpare;
	}

	public void setOldSpare(String oldSpare) {
		this.oldSpare = oldSpare;
	}

	public String getOutDetailids() {
		return outDetailids;
	}

	public void setOutDetailids(String outDetailids) {
		this.outDetailids = outDetailids;
	}
	

	 
}
