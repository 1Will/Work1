
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.unused;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.pluto.spring.util.SubReportHelper;
import com.yongjun.tdms.service.runmaintenance.unused.UnusedManager;
public class ListDeviceUnusedRepaortController extends JasperReportsController {
	private  UnusedManager unusedManger;
	HttpServletRequest request;
	String billNo,name;
	
	SubReportHelper subReportHelper;
	Date subscribeDate_start,subscribeDate_end;
	List unusedList = new ArrayList();
	List result;
	public ListDeviceUnusedRepaortController(UnusedManager unusedManger){
		this.unusedManger=unusedManger;
	}
	
//	TODO: 移动到共通代码中
	static String trim(String str) {
		return StringUtils.clean(str);
	}

	/**
	 * 通过页面获取所提交的查询字段，保存到数组queryInfo中，在Dao中拼SQL语句，进行复合查询。
	 * @return Map类型的model对象
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		//System.out.println("sdfsd");
		String department_id = "" ;
		String status = "";
		if (RequestUtils.getStringParameter(httpservletrequest,
				"department.id", StringUtils.EMPTY).trim().equals("-1") == false) {
			department_id = RequestUtils.getStringParameter(httpservletrequest,
					"department.id", StringUtils.EMPTY).trim();
		}
		if (RequestUtils.getStringParameter(httpservletrequest,
				"status", StringUtils.EMPTY).trim().equals("-1") == false) {
			status = RequestUtils.getStringParameter(httpservletrequest,
					"status", StringUtils.EMPTY).trim();
		}
		String[] queryInfo = new String[] {
				trim(RequestUtils.getStringParameter(httpservletrequest,
						"code", StringUtils.EMPTY)),
				trim(RequestUtils.getStringParameter(httpservletrequest,
						"name", StringUtils.EMPTY)),
				trim(RequestUtils.getStringParameter(httpservletrequest,
						"asset.deviceNo", StringUtils.EMPTY)),
						department_id,
				trim(RequestUtils.getStringParameter(httpservletrequest,
						"asset.name", StringUtils.EMPTY)),
				trim(RequestUtils.getStringParameter(httpservletrequest,
						"unUseDate_start", StringUtils.EMPTY)),		
				trim(RequestUtils.getStringParameter(httpservletrequest,
						"unUseDate_end", StringUtils.EMPTY)),
				trim(RequestUtils.getStringParameter(httpservletrequest,
						"usedAprDate_start", StringUtils.EMPTY)),
				trim(RequestUtils.getStringParameter(httpservletrequest,
					    "usedAprDate_end", StringUtils.EMPTY)),
					    status,
		        trim(RequestUtils.getStringParameter(httpservletrequest,
				        "disabled", StringUtils.EMPTY)),
                 RequestUtils.getStringParameter(httpservletrequest,
                		 "toolingDevFlag", StringUtils.EMPTY)
		        };	
                 System.out.println("数组的长度"+queryInfo.length);
                 for(int i=0;i<queryInfo.length;i++){
                	 System.out.println(""+queryInfo[i]);
                 }
		result = unusedManger.Query(queryInfo);
		Map model = new HashMap();
		//subReportHelper = new SubReportHelper();
		//subReportHelper.setSubReportDatas(result);
		model.put("reportData", result);
		return model;
	}
}
