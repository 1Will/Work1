package main.pojo;

import java.util.Date;
/**
 * 发货�?
 * @author xcg
 *
 */
@SuppressWarnings("serial")
public class ProductionOperation implements java.io.Serializable{
	private Long id;
	private Long version;
	private String code;//�ƻ�����
	private String name;//����
	private PersonnelFiles makeUpPerson;//������
	private Date makeUpDate;//��������
	private PersonnelFiles auditingPerson;//�����
	private Date auditingDate;//�������
	private String mark;//��ע
	private String isSaved;//���ڲ��ҵ���0�������ύ
	private boolean disabled; //ʧЧ
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
	public String getCode() {
		return code;
	}
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
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PersonnelFiles getMakeUpPerson() {
		return makeUpPerson;
	}
	public void setMakeUpPerson(PersonnelFiles makeUpPerson) {
		this.makeUpPerson = makeUpPerson;
	}
	public Date getMakeUpDate() {
		return makeUpDate;
	}
	public void setMakeUpDate(Date makeUpDate) {
		this.makeUpDate = makeUpDate;
	}
	public PersonnelFiles getAuditingPerson() {
		return auditingPerson;
	}
	public void setAuditingPerson(PersonnelFiles auditingPerson) {
		this.auditingPerson = auditingPerson;
	}
	public Date getAuditingDate() {
		return auditingDate;
	}
	public void setAuditingDate(Date auditingDate) {
		this.auditingDate = auditingDate;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public ProductionOperation(String code, String name, PersonnelFiles makeUpPerson, Date makeUpDate,
			PersonnelFiles auditingPerson, Date auditingDate, String mark, String isSaved) {
		this.code = code;
		this.name = name;
		this.makeUpPerson = makeUpPerson;
		this.makeUpDate = makeUpDate;
		this.auditingPerson = auditingPerson;
		this.auditingDate = auditingDate;
		this.mark = mark;
		this.isSaved = isSaved;
	}
	public ProductionOperation() {
	}
	
	
}
