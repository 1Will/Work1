package main.pojo;

import java.util.Date;
/**
 * ������
 * @author xcg
 *
 */
@SuppressWarnings("serial")
public class ProductionOperationDetail implements java.io.Serializable{
	private Long id;
	private Long version;
	private ProductionOperation productionOperation;
	private Products product;//��Ʒ
	private CodeValue unit;//��λ
	private int deliverNum;//��������
	private CodeValue testType;//���鷽ʽ
	private CodeValue proStatu;//ִ��״̬//����or�쳣or ���
	private String proStatuString;//��Ʒ״̬
	private ContractManagement contractManagement;//��ͬ
	private Date deliverDate;//�����ڵ�
	private Date technologyDate;//�������Ͻڵ�
	private Date purchaseDate;//�ɹ��ڵ�
	private Date makeDate;//����ڵ�
	private Date testDate;//����ڵ�
	public Products getProduct() {
		return product;
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
	public void setProduct(Products product) {
		this.product = product;
	}
	public CodeValue getUnit() {
		return unit;
	}
	public void setUnit(CodeValue unit) {
		this.unit = unit;
	}
	public int getDeliverNum() {
		return deliverNum;
	}
	public void setDeliverNum(int deliverNum) {
		this.deliverNum = deliverNum;
	}
	public CodeValue getTestType() {
		return testType;
	}
	public void setTestType(CodeValue testType) {
		this.testType = testType;
	}
	public ContractManagement getContractManagement() {
		return contractManagement;
	}
	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public Date getTechnologyDate() {
		return technologyDate;
	}
	public void setTechnologyDate(Date technologyDate) {
		this.technologyDate = technologyDate;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	
	
	public String getProStatuString() {
		return proStatuString;
	}
	public void setProStatuString(String proStatuString) {
		this.proStatuString = proStatuString;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public ProductionOperationDetail(ProductionOperation productionOperation,Products product, CodeValue unit, int deliverNum, CodeValue testType,
			String proStatuString, ContractManagement contractManagement, Date deliverDate, Date technologyDate,
			Date purchaseDate, Date makeDate, Date testDate) {
		this.productionOperation=productionOperation;
		this.product = product;
		this.unit = unit;
		this.deliverNum = deliverNum;
		this.testType = testType;
		this.proStatuString = proStatuString;
		this.contractManagement = contractManagement;
		this.deliverDate = deliverDate;
		this.technologyDate = technologyDate;
		this.purchaseDate = purchaseDate;
		this.makeDate = makeDate;
		this.testDate = testDate;
	}
	public ProductionOperationDetail() {
	}
	public ProductionOperation getProductionOperation() {
		return productionOperation;
	}
	public void setProductionOperation(ProductionOperation productionOperation) {
		this.productionOperation = productionOperation;
	}
	
	public CodeValue getProStatu() {
		return proStatu;
	}
	public void setProStatu(CodeValue proStatu) {
		this.proStatu = proStatu;
	}
}
