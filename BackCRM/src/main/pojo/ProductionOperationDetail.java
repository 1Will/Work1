package main.pojo;

import java.util.Date;
/**
 * 发货单
 * @author xcg
 *
 */
@SuppressWarnings("serial")
public class ProductionOperationDetail implements java.io.Serializable{
	private Long id;
	private Long version;
	private ProductionOperation productionOperation;
	private Products product;//产品
	private CodeValue unit;//单位
	private int deliverNum;//交付数量
	private CodeValue testType;//检验方式
	private CodeValue proStatu;//执行状态//正常or异常or 完结
	private String proStatuString;//产品状态
	private ContractManagement contractManagement;//合同
	private Date deliverDate;//交付节点
	private Date technologyDate;//技术资料节点
	private Date purchaseDate;//采购节点
	private Date makeDate;//制造节点
	private Date testDate;//厂检节点
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
