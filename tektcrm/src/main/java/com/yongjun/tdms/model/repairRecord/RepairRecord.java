package com.yongjun.tdms.model.repairRecord;

import java.util.Date;

import javax.management.loading.PrivateClassLoader;

import com.opensymphony.provider.ProviderInvocationException;
import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

/** 
  * @author 创建人: xichunguang
  * @date 创建时间：2017年12月20日 下午3:54:18 
  * 维修记录实体
  */
public class RepairRecord extends BaseInfoEntity{
	private static final long serialVersionUID = 1L;
	private PersonnelFiles personnelFiles;//申请人
	private String num;         //	序号
	private Date receiveDate;	//	接收日期
	private String workNum;	//	工作单号
	private CodeValue branch;	//	兵种:海军/空军/火箭军/二炮/陆军/武警/无
	private String contactPerson;	//	联系人
	private String address;	//	用户地址
	private String airtypeNum;	//	空调型号
	private int airCount = 0;	//	台数
	private String faultDesc;	//	故障情况
	private CodeValue repirType;	//	维修类型:安装（调试）/维修（检修）/保障/其他
	private Date finishDate;	//	"完结日期"
	private String repairResult;	//	故障处理结果
	private String faultType;	//	故障分类:压缩机损坏/风机损坏/水泵损坏/系统漏氟/电控板损坏/电器损坏/系统堵/电路故障/线路故障/漏水/噪音/其他
	private String faultTypeDesc;//故障分类描述
	private double partFee = 0d;	//	备件费用
	private Date leaveDate;	//	出发时间
	private Date arriveDate;//  到达时间
	private String leavePlace;//出发地点
	private String arrivePlace;//到达地点
	private Date startDate;	//	开工日期
	private Date endDate;	//	完工日期
	private String mark;	//	备注
	private CodeValue airType; 
	private String isSaved;
	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFaultTypeDesc() {
		return faultTypeDesc;
	}

	public void setFaultTypeDesc(String faultTypeDesc) {
		this.faultTypeDesc = faultTypeDesc;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getWorkNum() {
		return workNum;
	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}

	public CodeValue getBranch() {
		return branch;
	}

	public void setBranch(CodeValue branch) {
		this.branch = branch;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAirtypeNum() {
		return airtypeNum;
	}

	public void setAirtypeNum(String airtypeNum) {
		this.airtypeNum = airtypeNum;
	}



	public String getFaultDesc() {
		return faultDesc;
	}

	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}

	public CodeValue getRepirType() {
		return repirType;
	}

	public void setRepirType(CodeValue repirType) {
		this.repirType = repirType;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getRepairResult() {
		return repairResult;
	}

	public void setRepairResult(String repairResult) {
		this.repairResult = repairResult;
	}


	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public double getPartFee() {
		return partFee;
	}

	public void setPartFee(double partFee) {
		this.partFee = partFee;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public String getLeavePlace() {
		return leavePlace;
	}

	public void setLeavePlace(String leavePlace) {
		this.leavePlace = leavePlace;
	}

	public int getAirCount() {
		return airCount;
	}

	public void setAirCount(int airCount) {
		this.airCount = airCount;
	}

	public String getArrivePlace() {
		return arrivePlace;
	}

	public void setArrivePlace(String arrivePlace) {
		this.arrivePlace = arrivePlace;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public CodeValue getAirType() {
		return airType;
	}

	public void setAirType(CodeValue airType) {
		this.airType = airType;
	}

	public RepairRecord(PersonnelFiles personnelFiles, String num, Date receiveDate, String workNum, CodeValue branch,
			String contactPerson, String address, String airtypeNum, int airCount, String faultDesc,
			CodeValue repirType, Date finishDate, String repairResult, String  faultType,String  faultTypeDesc, double partFee,
			Date leaveDate, Date arriveDate, String leavePlace, String arrivePlace, Date startDate, Date endDate,
			String mark,CodeValue airType,String isSaved) {
		this.personnelFiles = personnelFiles;
		this.num = num;
		this.receiveDate = receiveDate;
		this.workNum = workNum;
		this.branch = branch;
		this.contactPerson = contactPerson;
		this.address = address;
		this.airtypeNum = airtypeNum;
		this.airCount = airCount;
		this.faultDesc = faultDesc;
		this.repirType = repirType;
		this.finishDate = finishDate;
		this.repairResult = repairResult;
		this.faultType = faultType;
		this.faultTypeDesc=faultTypeDesc;
		this.partFee = partFee;
		this.leaveDate = leaveDate;
		this.arriveDate = arriveDate;
		this.leavePlace = leavePlace;
		this.arrivePlace = arrivePlace;
		this.startDate = startDate;
		this.endDate = endDate;
		this.mark = mark;
		this.airType=airType;
		this.isSaved=isSaved;
	}

	public RepairRecord() {
	}

	
	
	
}
