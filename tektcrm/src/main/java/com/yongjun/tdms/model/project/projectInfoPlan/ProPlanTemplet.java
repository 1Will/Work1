package com.yongjun.tdms.model.project.projectInfoPlan;


import com.yongjun.pluto.model.BaseInfoEntity;

public class ProPlanTemplet extends BaseInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String code;//计划模板编码
    private String name;//计划模板名称
    private String proplanName;//计划名称
    private int priority;// 优先级
    private String outline;// 主要说明
    
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
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

	public String getProplanName() {
		return proplanName;
	}

	public void setProplanName(String proplanName) {
		this.proplanName = proplanName;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == this) {
			return true;
		}
		if (!(arg0 instanceof ProPlanTemplet)) {
			return false;
		}

		ProPlanTemplet contact = (ProPlanTemplet) arg0;

		if (!contact.getId().equals(getId())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return 0;
	}

}
