package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.accept;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;



import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillDetailManager;

public class EditCheckToolingGroupNo extends PrepareAction{
private static final long serialVersionUID = 1L;
private final DeviceCardManager deviceCardManager;
private final AcceptBillDetailManager  acceptBillDetailManager;
private final DepartmentManager departmentManager;
private List<DeviceCard> toolingList;
  public EditCheckToolingGroupNo(DeviceCardManager deviceCardManager,
		  AcceptBillDetailManager  acceptBillDetailManager,DepartmentManager departmentManager){
	  this.deviceCardManager=deviceCardManager;
	  this.acceptBillDetailManager=acceptBillDetailManager;
	  this.departmentManager=departmentManager;
  }
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 判断验收单明细中的工装图号是否在工装台帐中存在 
	 * @throws ServletException
	 * @throws IOException
	 */
    public void checkGroupNo() throws ServletException,IOException{
    	String arr=request.getParameter("ary");
    	String [] array=null;
    	if(null!=arr){
    		array=arr.split(",");
    	}
    	request.setCharacterEncoding("UTF-8");
    	PrintWriter out = null;
    	response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
    	try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println("<response>");
		//当图号在工装台帐中已经存则创建台帐失败  直接return 
		for(int i=0;i<array.length;i=i+4){
			toolingList=deviceCardManager.getToolingGroupNoByGroupNo(array[i].trim());
			if(toolingList.size()!=0){
				//利用Ajax返回给页面xml信息 array[i+2]代表返回的行数,array[i]代表返回的图号
				out.println("<status flag=\"true\">"+array[i+2]+"</status>");
				out.println("<status flag=\"true\">"+array[i]+"</status>");
				out.println("</response>");
		    	out.close();
				return ;
			}
		}
		//当图号在工装台帐中不存在 则成功创建台帐
		for(int i=0;i<array.length;i=i+4){
			toolingList=deviceCardManager.getToolingGroupNoByGroupNo(array[i].trim());
			if(toolingList.size()==0){
				DeviceCard deviceCard =new DeviceCard();
				AcceptBillDetail detail=acceptBillDetailManager.loadAcceptBillDetail(Long.valueOf(array[i+1]));
				detail.setGraphNo(array[i]);
				Department depart=this.departmentManager.loadDepartment(Long.valueOf(array[i+3]));
				detail.setDepartment(depart);
				detail.setDepartmentName(depart.getName());
				this.acceptBillDetailManager.storeAcceptBillDetailToDeviceCard(deviceCard,detail);
				out.println("<type flag=\"false\"></type>");
				
			}
		}
			out.println("</response>");
	    	out.close();
    }
}
