package com.yongjun.tdms.model.prophase.business;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
@SuppressWarnings("serial")
public class MiddleCheck extends BaseInfoEntity{

 
  //检查结果
  private String resultCheck;
  //检查日期
  private Date checkDate = new Date();
  //检查内容
  private String content;
 //中途检查所关联的采购单
  private PurchaseBill purchaseBill;
  

  public MiddleCheck() {
		
	}
  public String getResultCheck() {
	return resultCheck;
  }

   public void setResultCheck(String resultCheck) {
	  this.resultCheck = resultCheck;
  }

 public Date getCheckDate() {
	 return checkDate;
  }

  public void setCheckDate(Date checkDate) {
	 this.checkDate = checkDate;
 }

 public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public PurchaseBill getPurchaseBill() {
	return purchaseBill;
}

public void setPurchaseBill(PurchaseBill purchaseBill) {
	this.purchaseBill = purchaseBill;
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

}
