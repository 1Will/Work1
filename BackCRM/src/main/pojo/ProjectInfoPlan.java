package main.pojo;

import java.util.Date;

public class ProjectInfoPlan implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private ProjectInfo projectInfo;//��Ŀ
	private ContractManagement contractManagement; // ��ͬ
	private ProductionOperationDetail productionOperationDetail;//������Ӫ�ƻ���ϸ
	private String name;//��������
	private CodeValue planState;//����״̬
	private Date startDate;//Ԥ�ƿ�ʼʱ��
	private Date endDate;//Ԥ�ƽ���ʱ��
	private Date createTime;//����ʱ��
	private PersonnelFiles personnelFiles;//������
	private String assist;//Э����
	private String outline;//��Ҫ˵��
	private int percentt;//�ٷֱ�
	private int priority;//���ȼ�

	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof ProjectInfoPlan)) {
			return false;
		}

		ProjectInfoPlan contact = (ProjectInfoPlan) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return 0;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public String getAssist() {
		return assist;
	}

	public void setAssist(String assist) {
		this.assist = assist;
	}

	public int getPercentt() {
		return percentt;
	}

	public void setPercentt(int percentt) {
		this.percentt = percentt;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CodeValue getPlanState() {
		return planState;
	}

	public void setPlanState(CodeValue planState) {
		this.planState = planState;
	}
    
    
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public ProductionOperationDetail getProductionOperationDetail() {
		return productionOperationDetail;
	}

	public void setProductionOperationDetail(ProductionOperationDetail productionOperationDetail) {
		this.productionOperationDetail = productionOperationDetail;
	}

    
   
    
	
	
}
