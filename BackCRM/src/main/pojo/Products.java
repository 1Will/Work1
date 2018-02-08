package main.pojo;


import java.util.Date;



public class Products  implements java.io.Serializable {
	private static final long serialVersionUID = 4076912356110674435L;
	private Long id;
	private Long version;
	
	private String code; //��Ʒ����
	private String name;  //��Ʒ����
	private String model; //�ͺ�
	private String standard; // ���
	private Double etcPrice; // �ɱ��ۣ�Ԫ��
	private Double salePrice; // ���ۼۣ�Ԫ��
	private String salelimit; // ���۶�ȣ�%��
	private Date launch; // �Ƴ�ʱ��
	private String remark; // ��ע
	private Long ptId; //  ��Ʒ���
	private String productSource; // ��Ʒ��Դ
	private CodeValue product_source_ID; // ��Ʒ��Դid
	private Long supplier; //��Ӧ��
	private short isNoMain ; // �Ƿ�   ��Ӫ
	private String isSaved;// ���ڲ��ҵ���0���������ύ
	private Date createdTime;
	private String creator;
	private Date lastModifiedTime;
	private String lastOperator;
	private boolean disabled;
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public Double getEtcPrice() {
		return etcPrice;
	}
	public void setEtcPrice(Double etcPrice) {
		this.etcPrice = etcPrice;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public String getSalelimit() {
		return salelimit;
	}
	public void setSalelimit(String salelimit) {
		this.salelimit = salelimit;
	}
	public Date getLaunch() {
		return launch;
	}
	public void setLaunch(Date launch) {
		this.launch = launch;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getPtId() {
		return ptId;
	}
	public void setPtId(Long ptId) {
		this.ptId = ptId;
	}
	public String getProductSource() {
		return productSource;
	}
	public void setProductSource(String productSource) {
		this.productSource = productSource;
	}
	public CodeValue getProduct_source_ID() {
		return product_source_ID;
	}
	public void setProduct_source_ID(CodeValue product_source_ID) {
		this.product_source_ID = product_source_ID;
	}
	public Long getSupplier() {
		return supplier;
	}
	public void setSupplier(Long supplier) {
		this.supplier = supplier;
	}
	 
	public short getIsNoMain() {
		return isNoMain;
	}
	public void setIsNoMain(short isNoMain) {
		this.isNoMain = isNoMain;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

   
	
	
	/*	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof Products)) {
			return false;
		}

		Products p = (Products) arg0;

		if (!p.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	
	public int hashCode() {
		return getId().hashCode();
	}    */

	
	
	
	
	
	
	
}
