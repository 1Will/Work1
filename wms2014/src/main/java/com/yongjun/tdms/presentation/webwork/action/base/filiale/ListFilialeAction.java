package com.yongjun.tdms.presentation.webwork.action.base.filiale;

import java.util.List;

import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.filiale.FilialeManager;

/**
 * <p>Title: ListFilialeAction
 * <p>Description: 分公司页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author mfzhang@yj-technology.com
 * @version 
 */
public class ListFilialeAction extends ValueListAction {
	private static final long serialVersionUID = -916683653048006812L;
	private final FilialeManager filialeManager;
	private List<Filiale> filiales;
	
	public ListFilialeAction(FilialeManager filialeManager){
		this.filialeManager = filialeManager;
	}
	
	/**
	 * 获取页面参数 <b>filialeIds</b>，如果得到，就根据ID获取分公司；
	 */
	public void prepare() throws Exception {
		if (null == this.filiales && this.hasIds("filialeIds")) {
			this.filiales = this.filialeManager.loadAllFiliale(this.getIds("filialeIds"));
		}
	}

	/**
	 * 页面执行，如果选择了失效就调用 <b>disabled</b>函数处理。
	 * 选择了有效就调用<b>endable</b>函数处理。
	 */
	public String execute() {
		if(this.isDisabled()){
			this.disabled();
		}
		if(this.isEnable()){
			this.enabled();
		}
		return SUCCESS;	
	}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "filiale";
	}
	/**
	 * 选择的分公司失效
	 * @return
	 */
    private String disabled()
    {
    	this.filialeManager.disableAllFiliales(this.filiales);
    	this.addActionMessage(this.getText("filiales.disable.success"));
        return SUCCESS;
    }
    /**
     * 选择的分公司有效
     * @return
     */
    public String enabled() {
    	this.filialeManager.enabledAllFiliales(this.filiales);
    	this.addActionMessage(this.getText("filiales.enabled.success"));
		return SUCCESS;
	}
	/**
	 * 删除选择的分公司
	 */
	public void delete() {
		this.filialeManager.deleteAllFiliale(this.filiales);
		this.addActionMessage(this.getText("filiales.delete.success"));
	}
	/**
	 * 获得选择的分公司列表
	 * @return
	 */
	public List<Filiale> getFiliales() {
		return filiales;
	}

	public void setFiliales(List<Filiale> filiales) {
		this.filiales = filiales;
	}
	
}
