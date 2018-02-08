package com.yongjun.tdms.model.workReport.apply;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workflow.Flow;

public class ApplyCar extends BaseInfoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dept;//申请单位
	private String code;//申请编码
	private PersonnelFiles applyPerson;//申请人
	private CodeValue type;//行车类别：长途、市内
	private int numOfPeople;//乘车人数
	private String waitPlace;//候车地点
	private String destination;//目的地
	private String reason;//用车事由
	private String contacter;//联系人
	private String phone;//联系电话
	private Date time_start ;//用车时间——start
	private Date time_end;//用车时间——end
	private Flow flow;//流程
	private CodeValue status;//状态
	private String attachment;//附件文件路径-----------暂不处理
	private CodeValue carType;//车辆类型
	private int carCount;//用车数量
	private String isSaved;//是否提交
	
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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public CodeValue getCarType() {
		return carType;
	}

	public void setCarType(CodeValue carType) {
		this.carType = carType;
	}

	public int getCarCount() {
		return carCount;
	}

	public void setCarCount(int carCount) {
		this.carCount = carCount;
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

	public CodeValue getType() {
		return type;
	}

	public void setType(CodeValue type) {
		this.type = type;
	}

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public String getWaitPlace() {
		return waitPlace;
	}

	public void setWaitPlace(String waitPlace) {
		this.waitPlace = waitPlace;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getTime_start() {
		return time_start;
	}

	public void setTime_start(Date time_start) {
		this.time_start = time_start;
	}

	public Date getTime_end() {
		return time_end;
	}

	public void setTime_end(Date time_end) {
		this.time_end = time_end;
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
		 .append("具体内容是：【从"+DateUtil.getDate(this.getTime_start(), "yyyy-MM-dd HH:mm:ss")+"到"+DateUtil.getDate(this.getTime_end(), "yyyy-MM-dd HH:mm:ss"))
		 .append("期间内用车")
		 .append("】用车原因为：【"+this.getReason()+"】");
		return sb.toString();
	}

}
