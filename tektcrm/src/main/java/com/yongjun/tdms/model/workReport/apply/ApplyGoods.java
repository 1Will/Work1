package com.yongjun.tdms.model.workReport.apply;
import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workflow.Flow;

public class ApplyGoods extends BaseInfoEntity{
	private String dept;//申请单位
	private String code;//申请编码
	private PersonnelFiles applyPerson;//申请人
	private CodeValue purpose;//用途（招待，馈赠）
	private CodeValue goodsName;//物品名称
	private int goodsCount;//物品数量
	private CodeValue unit;//单位：件，瓶，盒，套
	private String bussinessUnit;//业务单位（对方单位）
	private Flow flow;//流程
	private CodeValue status;//状态
	private String isSaved;//是否提交
	private String remark;//备注
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public PersonnelFiles getApplyPerson() {
		return applyPerson;
	}

	public void setApplyPerson(PersonnelFiles applyPerson) {
		this.applyPerson = applyPerson;
	}

	public CodeValue getPurpose() {
		return purpose;
	}

	public void setPurpose(CodeValue purpose) {
		this.purpose = purpose;
	}


	public CodeValue getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(CodeValue goodsName) {
		this.goodsName = goodsName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CodeValue getUnit() {
		return unit;
	}

	public void setUnit(CodeValue unit) {
		this.unit = unit;
	}

	public String getBussinessUnit() {
		return bussinessUnit;
	}

	public void setBussinessUnit(String bussinessUnit) {
		this.bussinessUnit = bussinessUnit;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public CodeValue getStatus() {
		return status;
	}

	public void setStatus(CodeValue status) {
		this.status = status;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		 sb.append("部门为【"+this.getApplyPerson().getDept().getName()+"】的【"+this.getApplyPerson().getName()+"】在时间为【"+DateUtil.getDate(this.getCreatedTime(), "yyyy年MM月dd日")+"】提出了类型为【"+this.getFlow().getName())
		 .append("】的流程，")
		 .append("具体内容是：【"+this.getPurpose()+".."+this.getBussinessUnit()+",数量为："+this.getGoodsName().getName()+" "+this.getGoodsCount()+this.getUnit())
		 .append("】");
		return sb.toString();
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
}

