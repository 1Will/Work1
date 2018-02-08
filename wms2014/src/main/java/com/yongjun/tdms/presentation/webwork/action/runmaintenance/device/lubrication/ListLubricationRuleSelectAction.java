/**
 * 
 */
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.device.lubrication;

import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * @author Administrator
 *
 */
public class ListLubricationRuleSelectAction extends ValueListAction {
	
	private static final long serialVersionUID = 1L;
	private CodeValue type;
	private final CodeValueManager codeValueManager;
	
	public ListLubricationRuleSelectAction(CodeValueManager codeValueManager){
		this.codeValueManager = codeValueManager;
	}
	@Override
	public void prepare() throws Exception {
		type = codeValueManager.loadCodeTypeByCode(CodeConstants.LUBRICATION_METHOD);
	}

	@Override
	protected String getAdapterName() {
		return "lubricationRulesOfCodeValue";
	}
	
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("masteCodeId",type.getId());
		return map;
	}

	public CodeValue getType() {
		return type;
	}

	public void setType(CodeValue type) {
		this.type = type;
	}
}
