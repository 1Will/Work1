package com.yongjun.tdms.presentation.webwork.action.statistics;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.statistic.DailyStatistic;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.dataStatistics.DataStatisticsManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

import net.sf.json.JSONArray;



public class DataStatisticsAction extends ValueListAction {
	private static final long serialVersionUID = 5616960016127750986L;
	private final DataStatisticsManager dataStatisticsManager;
	private PersonnelFilesManager personnelFilesManager;
	private List<DailyStatistic> dailyStatistics;
	private final GroupManager groupManager;
	private final CodeValueManager codeValueManager;
	private DepartmentManager departmentManager;
	private String result;
	private String sta;

	

	
	//sql语句
	@Override
	protected String getAdapterName() {
		
		return "dataStatisticsHQL";
	}
	
	public DataStatisticsAction(DataStatisticsManager dataStatisticsManager,PersonnelFilesManager personnelFilesManager,
			GroupManager groupManager,CodeValueManager codeValueManager,DepartmentManager departmentManager){
		this.dataStatisticsManager = dataStatisticsManager;
		this.personnelFilesManager = personnelFilesManager;
		this.groupManager = groupManager;
		this.codeValueManager = codeValueManager;
		this.departmentManager = departmentManager;
	}
	public void prepare() throws Exception {
		if(request.getParameter("data.state")!=null&&!request.getParameter("data.state").equals("")){
			this.sta=request.getParameter("data.state");
		}
	
	}
	protected Map getRequestParameterMap() {
		
		return super.getRequestParameterMap();
	}
	public List<CodeValue> getAllClassification() {
		try {
			List jpList = new ArrayList();
			 jpList = departmentManager.loadByKey("parentDept.name","军品销售");
			List<Department> mpList = departmentManager.loadByKey("parentDept.name","民品销售");
			if(mpList!=null&&mpList.size()>0){
			for(Department d:mpList){
				jpList.add(d);
			}
			}
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			jpList.add(0, cv);
			return jpList;
		} catch (Exception e) {
			e.printStackTrace();
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			List<CodeValue> list = new ArrayList();
			list.add(0, cv);
			return list;
		}
	}
	public List<CodeValue> getAllBusinessType() {
		try {
			List <CodeValue>companyNatures = new ArrayList<CodeValue>();
			String[] keys={"code","name"};
			String[] values={"210","客户属性"};
			List one =this.codeValueManager.loadByKeyArray(keys, values);// this.codeValueManager.loadByKey("code", Long.valueOf("210"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					companyNatures.addAll(list);
				}
			}
        	Iterator<CodeValue> it = companyNatures.iterator();  
            while(it.hasNext()){        
            	CodeValue temp = it.next();    
                if(temp.getName().equals("军民品")){  
                    it.remove();  
                }   
            } 
            CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			companyNatures.add(0, cv);
			return companyNatures;
		} catch (Exception e) {
			e.printStackTrace();
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			List<CodeValue> list = new ArrayList();
			list.add(0, cv);
			return list;
		}
	}
	public boolean isCustomerGroup(){
		boolean isCustomerGroup =false;
		Set<User> noticePers = groupManager.getGroupByGroupName("客户管理组").getUsers();
		User user = this.userManager.getUser();
		for (User u : noticePers) {
//			System.out.println(u.getId()+"=="+user.getId());
			if(user.getId().longValue() == u.getId().longValue()){
				isCustomerGroup = true;
			}
		}
		return isCustomerGroup;
	}
	
	public String listStatisticalBusinessType()throws Exception{
		List<List<List<Object>>> lists=new ArrayList<List<List<Object>>>();
		try {
			//获取页面参数放到方法中作为参数
			String time = "";
			String name = "";
			String date = "";
			String startTime="";
			String endTime = "";
			String businessType="";
			String classification = "";
			String tempParameter = URLDecoder.decode(request.getParameter("dataParame"), "utf-8");
			if(StringUtils.isNotBlank(tempParameter)){
				String[] parameArr = tempParameter.split(";");
				if(parameArr.length>5){
						time = parameArr[0].split("=")[1];
						name =parameArr[1].split("=")[1];
						if(parameArr[2].split("=").length == 2){
							startTime = parameArr[2].split("=")[1];
						}
						if(parameArr[3].split("=").length == 2){
							endTime = parameArr[3].split("=")[1];
						}
						businessType=parameArr[4].split("=")[1];
						classification=parameArr[5].split("=")[1];
					
				}else{
					time = parameArr[0].split("=")[1];
					name =parameArr[1].split("=")[1];
					date = parameArr[2].split("=")[1];
					businessType=parameArr[3].split("=")[1];
					classification=parameArr[4].split("=")[1];
				}
			}
			if(time.equals("month")){
				lists = this.dataStatisticsManager.listStatistilcalBusinessTypes(date,name,businessType,classification);
			}else if(time.equals("year")){
				lists = this.dataStatisticsManager.listYearStatistilcalBusinessTypes(date,name,businessType,classification);
			}else{
				lists = this.dataStatisticsManager.listSumStatistilcalBusinessTypes(startTime,endTime,name,businessType,classification);
			}
		    result =JSONArray.fromObject(lists).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String execute() throws Exception {
		return "success";
	}

	

	public List<DailyStatistic> getDailyStatistics() {
		return dailyStatistics;
	}

	public void setDailyStatistics(List<DailyStatistic> dailyStatistics) {
		this.dailyStatistics = dailyStatistics;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSta() {
		return sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	


	

}
