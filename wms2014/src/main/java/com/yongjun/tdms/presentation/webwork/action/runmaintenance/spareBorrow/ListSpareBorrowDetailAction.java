package com.yongjun.tdms.presentation.webwork.action.runmaintenance.spareBorrow;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrow;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetail;
import com.yongjun.tdms.service.runmaintenance.spareBorrow.SpareBorrowDetailManager;
import com.yongjun.tdms.service.runmaintenance.spareBorrow.SpareBorrowManager;
/**
 * <p>Title: ListSpareBorrowDetailAction
 * <p>Description: 备件领用页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ListSpareBorrowDetailAction 9149 2007-12-09 06:29:38Z xschen $
 */
public class ListSpareBorrowDetailAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
    private final SpareBorrowManager spareBorrowManager;     //备件领用单业务接口访问类
    private final SpareBorrowDetailManager  spareBorrowDetailManager; //备件领用单明细业务接口访问类
	private String addSpareAccountIds;					//添加新的入库明细时，从备件台帐选择的ID集合
	private String allSpareBorrowBillDtlId;
	private String allSpareBorrowNumber;
    private SpareBorrow  spareBorrow;
    private String toolingDevFlag; 
    private List<SpareBorrowDetail> spareBorrowDtls;
    public ListSpareBorrowDetailAction(SpareBorrowManager spareBorrowManager,
    		SpareBorrowDetailManager  spareBorrowDetailManager){
    	this.spareBorrowManager=spareBorrowManager;
    	this.spareBorrowDetailManager=spareBorrowDetailManager;
    }
	public void prepare(){
		
		if(this.hasId("spareBorrow.id")){
			this.spareBorrow = spareBorrowManager.loadSpareBorrow(this.getId("spareBorrow.id"));
		}
		if(this.hasIds("spareBorrowDtlIds")){
			this.spareBorrowDtls=spareBorrowDetailManager.loadAllSpareBorrowDetails(this.getIds("spareBorrowDtlIds"));
		}
		//获得所有从备件台帐选择的备件ids
		if(null==this.addSpareAccountIds){
			if(!StringUtils.isEmpty(request.getParameter("addSpareDetailIds"))){
				this.addSpareAccountIds = request.getParameter("addSpareDetailIds");
			}
		}
		if(this.hasId("toolingDevFlag")){
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		
		}
		//System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+request.getParameter("errorFlag"));
		this.setFirst(false);
	}
	@Override
	public String execute() throws Exception {
		if(this.isAddSpareAcount()){					//判断页面的信息是否从备件台帐而来
			return saveAddSpareToSpareBorrowBillDetail();
		}
		if(this.isSaveDetail()){						//保存入库单明细信息
			return saveSpareBorrowDetail();
		}
		if(this.isDelete()){
			return delete();
		}
		return SUCCESS;
	}
	public String delete(){
		try{
			spareBorrowDetailManager.deleteSpareBorrowDtl(spareBorrowDtls,spareBorrow);
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		this.addActionMessage(this.getText("spareBorrowDetails.delete.success"));
		return SUCCESS;
	}
	public String saveSpareBorrowDetail(){
		//获得所有备件领用单明细的ID
		if(!StringUtils.isEmpty(request.getParameter("allSpareBorrowDtlIds"))){
			this.allSpareBorrowBillDtlId = request.getParameter("allSpareBorrowDtlIds");
		}
		//获得所有入库单明细的入库数量
		if(!StringUtils.isEmpty(request.getParameter("allSpareBorrowAmountValue"))){
			this.allSpareBorrowNumber = request.getParameter("allSpareBorrowAmountValue");
		}
		if(null!=allSpareBorrowNumber||null!=allSpareBorrowBillDtlId){
			this.spareBorrowDetailManager.storespareAccountCopyToSpareBorrow(spareBorrow,allSpareBorrowNumber,allSpareBorrowBillDtlId);
		}
		return SUCCESS;
	}
	/**
	 * 判断页面是否保存入库单明细
	 * @return true | false
	 */
	private boolean isSaveDetail() {
		if (!StringUtils.isEmpty(request.getParameter("save"))){
		   if(this.hasKey("save")){
			  return true;
		   }
		}
		return false;
	}
	/**
	 * 判断页面是不是备件台帐选择而来
	 * @return true | false
	 */
	private boolean isAddSpareAcount(){
		if (!StringUtils.isEmpty(request.getParameter("spareAccountSelector"))) {
			if (request.getParameter("spareAccountSelector").equals("spareAccount"))
				return true;
		}
		return false;
	}
	public String saveAddSpareToSpareBorrowBillDetail(){
		this.spareBorrowDetailManager.spareAccountCopyToSpareBorrow(spareBorrow,addSpareAccountIds);
		return SUCCESS;
	}
	/**
	 * 将spareInBill.id的值放入到spareInBillDtl的valuelist中去
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("spareBorrow.id",this.getId("spareBorrow.id"));
		return map;
	}
	@Override
	protected String getAdapterName() {
		
		return "spareBorrowDtls";
	}
	public List<SpareBorrowDetail> getSpareBorrowDtls() {
		return spareBorrowDtls;
	}
	public void setSpareBorrowDtls(List<SpareBorrowDetail> spareBorrowDtls) {
		this.spareBorrowDtls = spareBorrowDtls;
	}
	public SpareBorrow getSpareBorrow() {
		return spareBorrow;
	}
	public void setSpareBorrow(SpareBorrow spareBorrow) {
		this.spareBorrow = spareBorrow;
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
