package main.pojo;


  public class Data  implements java.io.Serializable {
	private static final long serialVersionUID = 4076912356110674435L;
	private Long id;
	private Long version;
	
	private   String year;//��
	private   String month;//��
	private   long contractManagementNum;//��ͬ����
	private double contractManagementMoney=Double.valueOf(0.0D);;//��ͬ���
	private long financialManagementNum;//�տ�����
	private double financialManagementMoney=Double.valueOf(0.0D);;//�տ���
	private long billingRecordNum;//��Ʊ����
	private double billingRecordMoney=Double.valueOf(0.0D);;//��Ʊ���
	private long shouldDaily;//�ձ�Ӧд����
	private long actualDaily;//�ձ�ʵд����
	private long shouldWeekly;//�ܱ�Ӧд����
	private long actualWeekly;//�ܱ�ʵд����
	private long Monthly;//�±�����0δд1��д
	private long projectNum;//��Ŀ��
	private long backvisitNum;//�طô���
	private PersonnelFiles personnelFiles; //������
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public long getContractManagementNum() {
		return contractManagementNum;
	}
	public void setContractManagementNum(long contractManagementNum) {
		this.contractManagementNum = contractManagementNum;
	}
	public double getContractManagementMoney() {
		return contractManagementMoney;
	}
	public void setContractManagementMoney(double contractManagementMoney) {
		this.contractManagementMoney = contractManagementMoney;
	}
	public long getFinancialManagementNum() {
		return financialManagementNum;
	}
	public void setFinancialManagementNum(long financialManagementNum) {
		this.financialManagementNum = financialManagementNum;
	}
	public double getFinancialManagementMoney() {
		return financialManagementMoney;
	}
	public void setFinancialManagementMoney(double financialManagementMoney) {
		this.financialManagementMoney = financialManagementMoney;
	}
	public long getBillingRecordNum() {
		return billingRecordNum;
	}
	public void setBillingRecordNum(long billingRecordNum) {
		this.billingRecordNum = billingRecordNum;
	}
	public double getBillingRecordMoney() {
		return billingRecordMoney;
	}
	public void setBillingRecordMoney(double billingRecordMoney) {
		this.billingRecordMoney = billingRecordMoney;
	}
	public long getShouldDaily() {
		return shouldDaily;
	}
	public void setShouldDaily(long shouldDaily) {
		this.shouldDaily = shouldDaily;
	}
	public long getActualDaily() {
		return actualDaily;
	}
	public void setActualDaily(long actualDaily) {
		this.actualDaily = actualDaily;
	}
	public long getShouldWeekly() {
		return shouldWeekly;
	}
	public void setShouldWeekly(long shouldWeekly) {
		this.shouldWeekly = shouldWeekly;
	}
	public long getActualWeekly() {
		return actualWeekly;
	}
	public void setActualWeekly(long actualWeekly) {
		this.actualWeekly = actualWeekly;
	}
	public long getMonthly() {
		return Monthly;
	}
	public void setMonthly(long monthly) {
		Monthly = monthly;
	}
	public long getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(long projectNum) {
		this.projectNum = projectNum;
	}
	public long getBackvisitNum() {
		return backvisitNum;
	}
	public void setBackvisitNum(long backvisitNum) {
		this.backvisitNum = backvisitNum;
	}
	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}
	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}
	
	
	
	
	
	
	
}
