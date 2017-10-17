package com.github.wp.business.pojo.product;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.github.wp.system.pojo.BasePojo;

@Entity
@Table(name = "PRODUCT_PRODUCTS")
@Where(clause="EFFECTFLAG='E'")
public class Product extends BasePojo{

	/** {field's description} */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String name;
	private String model;
	private String standard;
	private Float etcprice;
	private Float saleprice;
	private String salelimit;
	private Timestamp launch;
	private String remark;
	private String productsource;
	private String supplierId;
	private String isnomain;
	private String productSourceId;
	private String ptId;
	private String disabled;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "model")
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(name = "standard")
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	@Column(name = "etcprice")
	public Float getEtcprice() {
		return etcprice;
	}
	public void setEtcprice(Float etcprice) {
		this.etcprice = etcprice;
	}
	
	@Column(name = "saleprice")
	public Float getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(Float saleprice) {
		this.saleprice = saleprice;
	}
	
	@Column(name = "salelimit")
	public String getSalelimit() {
		return salelimit;
	}
	public void setSalelimit(String salelimit) {
		this.salelimit = salelimit;
	}
	
	@Column(name = "launch")
	public Timestamp getLaunch() {
		return launch;
	}
	public void setLaunch(Timestamp launch) {
		this.launch = launch;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "productsource")
	public String getProductsource() {
		return productsource;
	}
	public void setProductsource(String productsource) {
		this.productsource = productsource;
	}
	
	@Column(name = "supplier_id")
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@Column(name = "isnomain")
	public String getIsnomain() {
		return isnomain;
	}
	public void setIsnomain(String isnomain) {
		this.isnomain = isnomain;
	}
	
	@Column(name = "product_source_id")
	public String getProductSourceId() {
		return productSourceId;
	}
	public void setProductSourceId(String productSourceId) {
		this.productSourceId = productSourceId;
	}
	
	@Column(name = "pt_id")
	public String getPtId() {
		return ptId;
	}
	public void setPtId(String ptId) {
		this.ptId = ptId;
	}
	
	@Column(name = "disabled")
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
}
