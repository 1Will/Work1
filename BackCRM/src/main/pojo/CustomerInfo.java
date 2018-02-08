package main.pojo;


import java.util.Date;


public class CustomerInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code; // ���� 
	private Long version; //�汾
	private Integer disabled; //ʧЧ
	
	private String customerName;   //�ͻ�����
	private Date setupTime; //��˾����ʱ�� 
	private Date archiveTime; //�浵���� 
	private CodeValue customerType;//�ͻ�״̬
	private Long companyNature;//��ҵ����
	private Long industry;// ��ҵ
	private Long step;//�ͻ��ȼ� 
	private Long country;// ����
	private Long province;//ʡ��
	private Long city;//����
	private Long resource;//��Ϣ��Դ
	private String web; //��˾��վ
	private String address;        //��ַ
	private String businessScope; //ҵ��Χ 
	private Long backVisitSum = 0L; //�طô���
	private String keyContacter;   //��Ҫ��ϵ��
	private String sex; //�Ա�
	private String dept; //����
	private String duty; //ְ��
	private String mobilePhone;
	private String phone;
	private String effectDescribe; //ӡ������ 
	private String advisoryContent; //��ѯ���� 
	private String parlorDept; //��������
	
	private String saleman; //ҵ��Ա !!�����ȡ 
	private Long businessmanId; //ҵ��ԱId �ǿ�
	
	private Date lastModifiedTime; //������ʱ�� ��̨����
	private Date createdTime; //��ʼ����ʱ�� ��̨����
	private Long state; //�̶�ֵ
	private String creatorName;//��ʼ������
	private String lastOperator;//����޸���
	private Double registeredCapital; //ע���ʱ� 
	private Integer personCount; //��˾����
	private Long unconnect;
   //δʹ���ֶ�
	private String legalPerson ; //���˴���
	private String email; //
	private String qq; //
	private String isSaved;
	private String isPartner; //�Ƿ�Ϊ������� 
	private Float cusInfoIntegrity; //�ͻ���Ϣ������
	private Date nearstBackvisitDate; //���ط�ʱ�� 
	private Department classiFication; //����Ƭ�� 
	private CodeValue businessType; //ҵ������  ��Ʒ ��Ʒ ����Ʒ
	
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
	
	public String getKeyContacter() {
		return keyContacter;
	}
	
	public void setKeyContacter(String keyContacter) {
		this.keyContacter = keyContacter;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}


	public CustomerInfo(Long gid, String gname,Date time,CodeValue codeValue,String keyString ) {
		this.id = gid;
		this.customerName = gname;
		this.archiveTime= time;
		this.customerType= codeValue;
		this.keyContacter= keyString;
	}
	
	public CustomerInfo() {
    }

	public Date getSetupTime() {
		return setupTime;
	}

	public void setSetupTime(Date setupTime) {
		this.setupTime = setupTime;
	}

	public String getEffectDescribe() {
		return effectDescribe;
	}

	public void setEffectDescribe(String effectDescribe) {
		this.effectDescribe = effectDescribe;
	}

	public String getAdvisoryContent() {
		return advisoryContent;
	}

	public void setAdvisoryContent(String advisoryContent) {
		this.advisoryContent = advisoryContent;
	}

	public String getParlorDept() {
		return parlorDept;
	}

	public void setParlorDept(String parlorDept) {
		this.parlorDept = parlorDept;
	}

	public String getSaleman() {
		return saleman;
	}

	public void setSaleman(String saleman) {
		this.saleman = saleman;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public CodeValue getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CodeValue customerType) {
		this.customerType = customerType;
	}

	public Long getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(Long companyNature) {
		this.companyNature = companyNature;
	}

	public Long getIndustry() {
		return industry;
	}

	public void setIndustry(Long industry) {
		this.industry = industry;
	}

	public Long getStep() {
		return step;
	}

	public void setStep(Long step) {
		this.step = step;
	}

	public Long getCountry() {
		return country;
	}

	public void setCountry(Long country) {
		this.country = country;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getResource() {
		return resource;
	}

	public void setResource(Long resource) {
		this.resource = resource;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public Long getBusinessmanId() {
		return businessmanId;
	}

	public void setBusinessmanId(Long businessmanId) {
		this.businessmanId = businessmanId;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getLastOperator() {
		return lastOperator;
	}

	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}

	public Double getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(Double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public Long getUnconnect() {
		return unconnect;
	}

	public void setUnconnect(Long unconnect) {
		this.unconnect = unconnect;
	}
    
	public Long getBackVisitSum() {
		return backVisitSum;
	}
	public void setBackVisitSum(Long backVisitSum) {
		this.backVisitSum = backVisitSum;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
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

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	public String getIsPartner() {
		return isPartner;
	}

	public void setIsPartner(String isPartner) {
		this.isPartner = isPartner;
	}

	public Float getCusInfoIntegrity() {
		return cusInfoIntegrity;
	}

	public void setCusInfoIntegrity(Float cusInfoIntegrity) {
		this.cusInfoIntegrity = cusInfoIntegrity;
	}

	public Date getNearstBackvisitDate() {
		return nearstBackvisitDate;
	}

	public void setNearstBackvisitDate(Date nearstBackvisitDate) {
		this.nearstBackvisitDate = nearstBackvisitDate;
	}



	public Department getClassiFication() {
		return classiFication;
	}


	public void setClassiFication(Department classiFication) {
		this.classiFication = classiFication;
	}


	public CodeValue getBusinessType() {
		return businessType;
	}

	public void setBusinessType(CodeValue businessType) {
		this.businessType = businessType;
	} 
    
	 
	
	
}