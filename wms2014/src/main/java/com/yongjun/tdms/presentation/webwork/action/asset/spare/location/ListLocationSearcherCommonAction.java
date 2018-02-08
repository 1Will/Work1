/*
 * YONGJUN-TEACHNOLOGY
 */
package com.yongjun.tdms.presentation.webwork.action.asset.spare.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.LocationStatus;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * @author yli-JohnSon
 * @Date 2009-4-11
 */
public class ListLocationSearcherCommonAction extends ValueListAction {

	/*
	 * YONGJUN-TEACHNOLOGY
	 */
	private static final long serialVersionUID = 8257329159485530255L;
	
	public void prepare(){
	}
	
    public String execute() throws Exception{
        return SUCCESS;
    }
    
    
//	protected Map getRequestParameterMap() {
//		Map map = super.getRequestParameterMap();
//		
//		if(){
//			List<Warehouse> list = this.getAllWarehouse();
//			list.remove(0);
//			map.put("roleWarehouseIds",list);
//			map.remove("warehouse.id");
//		}
//		return map;
//	}
	
    
    
	@Override
	protected String getAdapterName() {
		return "locationSearcher";
	}
	
	
	
	public List<LabelValue> getStatus() {  //获得备件领用单状态为枚举类型的值
		LabelValue[] arrays = this.wrapEnum(LocationStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
}
