package com.yongjun.tdms.model.prophase.supplier;

import com.yongjun.tdms.model.BaseInfoEntity;

public class SupplierEvaluateDetail extends BaseInfoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    //评分
    private Double  grade;

    //备注
    private String coment;
    
    //分数标识
    private String gradeFlag;
    
    //所关联的供应商评估
    private SupplierEvaluate supplierevaluate;
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

	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public SupplierEvaluate getSupplierevaluate() {
		return supplierevaluate;
	}

	public void setSupplierevaluate(SupplierEvaluate supplierevaluate) {
		this.supplierevaluate = supplierevaluate;
	}

	

	public String getGradeFlag() {
		return gradeFlag;
	}

	public void setGradeFlag(String gradeFlag) {
		this.gradeFlag = gradeFlag;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

  public Double getGradezero(){
	 if("DESIGN_CAPABILITY".equals(gradeFlag.toString())){
		 return grade;
	 }
	 return null;
  }
  public Double getGradeOne(){
	  if("MAKE_CAPABILITY".equals(gradeFlag.toString())){
		  return grade;
	  }
	  return null;
  }
  public Double getGradeTwo(){
	  if("QA_CAPABILITY".equals(gradeFlag.toString())){
		  return grade;
	  } 
	  return null;
  }
  public Double getGradeThree(){
		  if("SERVICE_CAPABILITY".equals(gradeFlag.toString())){
			  return grade;
		  } 
		  return null;
  }
  public Double getGradeFour(){
	  if("BASIC_CONTROL_CAPABILITY".equals(gradeFlag.toString())){
		  return grade;
	  } 
	  return null;
}
}
