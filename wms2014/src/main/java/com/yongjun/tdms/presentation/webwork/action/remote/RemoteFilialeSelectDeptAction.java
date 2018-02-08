package com.yongjun.tdms.presentation.webwork.action.remote;

import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.webwork.ServletActionContext;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.service.base.filiale.FilialeManager;
/**
 * 
 * <p>Title: RemoteFilialeSelectDeptAction
 * <p>Description: 分公司联动部门 ajax实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhang@yj-technology.com
 * @version $
 */
public class RemoteFilialeSelectDeptAction extends PrepareAction {

	private static final long serialVersionUID = 1L;
	private String filiale_id;
	private Collection<Department> departments;
	private  final FilialeManager filialeManager;
	
	public RemoteFilialeSelectDeptAction(FilialeManager filialeManager) {
        this.filialeManager = filialeManager;
    }

	public String getFiliale_id() {
		return filiale_id;
	}

	public void setFiliale_id(String filiale_id) {
		this.filiale_id = filiale_id;
	}
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
//		建立ajax请求的响应
		HttpServletResponse response= ServletActionContext.getResponse(); 
		//设置返回数据类型为xml格式 
		 response.setContentType("text/xml");   
	        response.setHeader("Cache-Control","no-cache");   
	        String xml_start="<selects>";   
	        String xml_end="</selects>";   
	        String xml="";   
	        // 获得分公司ID
			Long fifialeId = Long.parseLong(filiale_id);
			//分公司ID为-1，即下拉框值为“所有”时，部门下拉框也为“所有”
	        if(fifialeId==-1){
	        	xml+="<select><value>-1</value><text>所有</text></select>";
	        }else if(fifialeId == 0){
	        	xml+="<select><value>-1</value><text> </text></select>";
	        }else{
	        	//获得该分公司的所属部门，以XML格式保存
	        	departments = filialeManager.loadFiliale(fifialeId).getDepartments();
		        for(Department department:departments){
		        	xml+="<select><value>"+department.getId()+"</value><text>"+department.getName()+"</text></select>";   
				}
	        }
	        String last_xml=xml_start+xml+xml_end;   
	        PrintWriter out = response.getWriter(); 
	        out.print(last_xml); 
//	        关闭流 
	       out.close();  

		return SUCCESS;
	}

}
