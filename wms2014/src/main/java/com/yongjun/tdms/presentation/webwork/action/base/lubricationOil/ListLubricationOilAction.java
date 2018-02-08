package com.yongjun.tdms.presentation.webwork.action.base.lubricationOil;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.lubricationOil.LubricationOil;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.lubricationOil.LubricationOilManager;

public class ListLubricationOilAction extends ValueListAction{
	private static final long serialVersionUID = 7277611258514990029L;
	private final LubricationOilManager lubricationOilManager;
	private final CodeValueManager codeValueManager;
	private List<LubricationOil> lubricationOils;
	private boolean includeDisabled;
	
	public ListLubricationOilAction(LubricationOilManager lubricationOilManager,
			CodeValueManager codeValueManager) {
		this.lubricationOilManager = lubricationOilManager;
		this.codeValueManager = codeValueManager;
	}
	
	public void prepare() {
		if (null == this.lubricationOils) {
			if (this.hasId("lubricationOilIds")) {
				this.lubricationOils = this.lubricationOilManager.loadAllLubricationOils(this.getIds("lubricationOilIds"));
			}
		}
	}
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return enabled();
		}
		return SUCCESS;
	}
	
	public String disabled() {
		this.lubricationOilManager.disabledAllLubricationOils(lubricationOils);
		this.addActionMessage(this.getText("lubricationOil.disabled.success"));
		return SUCCESS;
	}
	
	public String enabled() {
		this.lubricationOilManager.enabledAllLubricationOils(lubricationOils);
		this.addActionMessage(this.getText("enabled.lubricationOil.success"));
		return SUCCESS;
	}
	
//	private boolean isDisabled() {
//		return this.hasKey("disabled");
//	}
	@Override
	protected String getAdapterName() {
		return "lubricationOils";
	}
	
	public List getCategorys() {
		return this.codeValueManager.createSelectCodeValues(this.getText("select.option.all"),CodeConstants.LUBRICATION_OIL_TYPE);
	}

	public boolean isIncludeDisabled() {
		return includeDisabled;
	}

	public void setIncludeDisabled(boolean includeDisabled) {
		this.includeDisabled = includeDisabled;
	}


}
