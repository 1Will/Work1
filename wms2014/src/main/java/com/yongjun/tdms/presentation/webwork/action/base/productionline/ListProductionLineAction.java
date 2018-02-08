package com.yongjun.tdms.presentation.webwork.action.base.productionline;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.base.productionline.ProductionLineManager;

public class ListProductionLineAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final ProductionLineManager productionLineManager;
	private final DepartmentManager departmentManager;
	private List<ProductionLine> productionLines;
	
	public ListProductionLineAction(ProductionLineManager productionLineManager,
			DepartmentManager departmentManager){
		this.productionLineManager = productionLineManager;
		this.departmentManager = departmentManager;
	}
	
	public void prepare() throws Exception {
		if(this.productionLines == null && this.hasIds("productionLineIds")){
			this.productionLines = this.productionLineManager.loadAllProductionLines(
					this.getIds("productionLineIds"));
		}
	}
	
	public String execute() throws Exception {
		 if (this.isDisabled()) {
				return disabled();
			}
			if (this.isEnable()) {
				return enabled();
			}
			return SUCCESS;
	}

    private String disabled()
    {
    	productionLineManager.disableAllProductions(productionLines);
        addActionMessage(getText("productionLines.disable.success"));
        return SUCCESS;
    }
    
    public String enabled() {
		this.productionLineManager.enabledAllProductions(productionLines);
		this.addActionMessage(this.getText("productionLines.checks.success"));
		return SUCCESS;
	}

	
	public List getDepartments() {
		return this.departmentManager.createSelectDepartments(this.getText("select.option.all")); 
	}
	
	@Override
	protected String getAdapterName() {
		return "productionlines";
	}
	
}
