package com.yongjun.tdms.presentation.webwork.action.base.area;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.area.AreaManager;


@Deprecated
public class ListAreaAction extends ValueListAction {
	private static final long serialVersionUID = 5616960016127750986L;
	private final AreaManager areaManager;
    private List areas;
    
    private boolean invalid;

    public boolean getInvalid() {
    	return invalid;
    }

    public void setInvalid(boolean invalid) {
    	this.invalid = invalid;
    }
    
    public List getAreas() {
        return areas;
    }

    public void setAreas(List areas) {
        this.areas = areas;
    }
    
    public ListAreaAction(AreaManager areaManager) {
        this.areaManager = areaManager;
    }

    public String execute() throws Exception{
        if (this.isDelete()){
            delete();
        }
        return SUCCESS;
    }

    public void prepare()
            throws Exception {
        if(this.areas==null && this.hasIds("areaIds")){
            this.areas=this.areaManager.loadAllAreas(this.getIds("areaIds"));
        }
    }

    protected String getAdapterName() {
        return "areas";
    }

    private void delete() {
        this.areaManager.deleteAllArea(this.areas);
        this.addActionMessage(this.getText("area.delete.success"));
    }
    
    @SuppressWarnings("unchecked")
    protected Map getRequestParameterMap() {
  	  Map map = super.getRequestParameterMap();
  	  if (invalid) {
  		  map.put("includeInvalid", "placeholder");
  	  }
  	  return map;
    }
}

