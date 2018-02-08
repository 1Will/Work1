package main.pojo;

import java.util.Date;

public class ContactArchives implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long version; //�汾
	private Integer disabled; //ʧЧ
	private String contactName;  //��ϵ��
	private Long leaderId; //�ϼ��쵼
	private String mobilePhone;        //�ֻ���
	private String phone;         //�绰
	private Integer sex;       //�Ա�
	private String address;    //��ַ
	private Date   birthday;    //����
	private String honorific;   //�ƺ�
	private String duty;        //ְλ
	private String customerName;  //������˾���ƣ��ͻ����ƣ�
	private String industry;     //��ҵ
	private String dept;         //����
	private String projectName;   //��Ŀ����  δʹ��
	private String remark;   //��ע
	private String enterpriseSynopsis;//ӡ������
	private String email;
	private String qq;
	private CodeValue type; //��Ϥ�̶�
	private java.util.Date createdTime;//����ʱ��
	private String creatorName;//������
	private String lastOperator;//����޸���
	private Date   lastModifiedTime;  //����޸�ʱ��
	private Long customerId;//�ͻ�Id
	private CodeValue customerTypeId;//�ͻ�����
	private String customerInfoCode;//�ͻ����
	private String isSaved;
	private Float cusInfoIntegrity; //�ͻ���Ϣ������
	

	/*//��������δʹ��
	private String  homePhone;  //��ͥ�绰 
	private String  enterpriseSynopsis;  //ӡ������
	private String  idCard;  //���֤
	private String  weChat;  //΢�� 
	private String  postalAddress;  //ͨѶ��ַ
	private String  custType;  //�˿����� 
	*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date date) {
		this.birthday =  date;
	}
	public String getHonorific() {
		return honorific;
	}
	public void setHonorific(String honorific) {
		this.honorific = honorific;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getCustomerInfoCode() {
		return customerInfoCode;
	}
	public void setCustomerInfoCode(String customerInfoCode) {
		this.customerInfoCode = customerInfoCode;
	}

	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getEnterpriseSynopsis() {
		return enterpriseSynopsis;
	}
	public void setEnterpriseSynopsis(String enterpriseSynopsis) {
		this.enterpriseSynopsis = enterpriseSynopsis;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}

	public CodeValue getType() {
		return type;
	}
	public void setType(CodeValue type) {
		this.type = type;
	}
	public java.util.Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(java.util.Date createdTime) {
		this.createdTime = createdTime;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	

	public CodeValue getCustomerTypeId() {
		return customerTypeId;
	}
	public void setCustomerTypeId(CodeValue customerTypeId) {
		this.customerTypeId = customerTypeId;
	}
	public Long getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public Float getCusInfoIntegrity() {
		return cusInfoIntegrity;
	}
	public void setCusInfoIntegrity(Float cusInfoIntegrity) {
		this.cusInfoIntegrity = cusInfoIntegrity;
	}
    
	
    
	
}