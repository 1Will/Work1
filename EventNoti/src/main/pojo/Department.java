package main.pojo;

import java.util.Date;


public class Department implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
    private String name;//��������
    private String discriminator; //������
    private String leader;//��Ӧ�쵼
    private Long version;
    private String tel;
    private Integer  disabled;//ʧЧ
    private Integer step;//����
    private Long parentId;//������
    private Long instId;// 14
    private Date createDate;//����ʱ��
    private String creator;//������
    private Date lastModifidDate;//����޸�ʱ��
    private String lastOperator;//�޸���
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscriminator() {
		return discriminator;
	}
	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getInstId() {
		return instId;
	}
	public void setInstId(Long instId) {
		this.instId = instId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getLastModifidDate() {
		return lastModifidDate;
	}
	public void setLastModifidDate(Date lastModifidDate) {
		this.lastModifidDate = lastModifidDate;
	}
	public String getLastOperator() {
		return lastOperator;
	}
	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	
    
    
    
}