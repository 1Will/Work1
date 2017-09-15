package com.yongjun.tdms.model.base.products;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.produttype.ProductType;
import com.yongjun.tdms.model.supplier.Supplier;

public class Products extends BaseInfoEntity implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking,
		LastModifiedTimeTracking {
	private static final long serialVersionUID = 4076912356110674435L;
	private String code;
	private String name;
	private String model;
	private String standard;
	private Double etcPrice;
	private Double salePrice;
	private String salelimit;
	private Date launch;
	private String remark;
	private ProductType pt;
	private String productSource;
	private CodeValue product_source_ID;
	private Supplier supplier;//之前的供应商，不用了
	private CustomerInfo customerInfo;//新供应商
	private boolean isNoMain = true;
	private String isSaved;// 存在并且等于0，，方可提交
	private CodeValue businessType;

	public boolean equals(Object arg0) {
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
	}

	public String getCode() {
		return this.code;
	}

	public Double getEtcPrice() {
		return this.etcPrice;
	}

	public boolean getIsNoMain() {
		return this.isNoMain;
	}

	public String getModel() {
		return this.model;
	}

	public String getName() {
		return this.name;
	}

	public String getProductSource() {
		return this.productSource;
	}

	public ProductType getPt() {
		return this.pt;
	}

	public Double getSalePrice() {
		return this.salePrice;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setEtcPrice(Double etcPrice) {
		this.etcPrice = etcPrice;
	}

	public void setIsNoMain(boolean isNoMain) {
		this.isNoMain = isNoMain;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductSource(String productSource) {
		this.productSource = productSource;
	}

	public void setPt(ProductType pt) {
		this.pt = pt;
	}

	public String getSalelimit() {
		return this.salelimit;
	}

	public void setSalelimit(String salelimit) {
		this.salelimit = salelimit;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getLaunch() {
		return this.launch;
	}

	public void setLaunch(Date launch) {
		this.launch = launch;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CodeValue getProduct_source_ID() {
		return this.product_source_ID;
	}

	public void setProduct_source_ID(CodeValue product_source_ID) {
		this.product_source_ID = product_source_ID;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public CodeValue getBusinessType() {
		return businessType;
	}

	public void setBusinessType(CodeValue businessType) {
		this.businessType = businessType;
	}
	
}
