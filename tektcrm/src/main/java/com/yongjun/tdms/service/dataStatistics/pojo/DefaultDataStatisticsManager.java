package com.yongjun.tdms.service.dataStatistics.pojo;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.statistic.DailyStatisticDao;
import com.yongjun.tdms.dao.workspace.data.DataDao;
import com.yongjun.tdms.model.statistic.DailyStatistic;
import com.yongjun.tdms.model.workspace.data.Data;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.dataStatistics.DataStatisticsManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;

public class DefaultDataStatisticsManager extends BaseManager implements DataStatisticsManager{
	
	private final DailyStatisticDao dailyStatisticDao;
	private final DataDao dataDao;
	private final CodeValueManager codeValueManager;
    private final DepartmentManager departmentManager;
public List<DailyStatistic> loadAllDailyStatistic(Long[] paramArrayOfLong) {
		
		return this.dailyStatisticDao.loadAllDailyStatistic(paramArrayOfLong);
	}
	public DefaultDataStatisticsManager(DailyStatisticDao dailyStatisticDao,DataDao dataDao,CodeValueManager codeValueManager,
			DepartmentManager departmentManager) {
		this.dailyStatisticDao = dailyStatisticDao;
		this.dataDao = dataDao;
		this.codeValueManager = codeValueManager;
        this.departmentManager = departmentManager;
	}
	

	public DailyStatistic loadDailyStatistic(Long paramLong) {
		// TODO Auto-generated method stub
		return this.dailyStatisticDao.loadDailyStatistic(paramLong);
	}

	public List<DailyStatistic> loadAllDailyStatistic() {
		// TODO Auto-generated method stub
		return this.dailyStatisticDao.loadAllDailyStatistic();
	}

	public List<DailyStatistic> loadByKey(String paramString, Object paramObject) throws DaoException {
		// TODO Auto-generated method stub
		return this.dailyStatisticDao.loadByKey(paramString, paramObject);
	}

	public List<DailyStatistic> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		// TODO Auto-generated method stub
		return this.dailyStatisticDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

	public void storeDailyStatistic(DailyStatistic dailyStatistic) {
		// TODO Auto-generated method stub
		
	}
	
	public String loadAllDailyStatisticByContion(String flag) {
		// TODO Auto-generated method stub
		List<DailyStatistic> dates = new ArrayList<DailyStatistic>();
		List<Object[]> tempList = new ArrayList<Object[]>();
		if("MONTH".equals(flag)){
			//查每月
			tempList = this.dailyStatisticDao.loadAllDailyStatisticByContion();
		}else if("DAY".equals(flag)){
			//查每天
			tempList = this.dailyStatisticDao.loadAllDailyStatisticDayByContion();
		}
		if(tempList!=null&&tempList.size()>0){
			for(Object[] a:tempList){
				DailyStatistic ds = new DailyStatistic();
				ds.setDailyNum((Long)a[0]);
				ds.setDailyReplyNum((Long)a[1]);
				ds.setCustomerNum((Long)a[2]);
				ds.setContactArchivesNum((Long)a[3]);
				ds.setBackVisitNum((Long)a[4]);
				ds.setBackVisitReplyNum((Long)a[5]);
				ds.setProjectNum((Long)a[6]);
				ds.setProjectPlanNum((Long)a[7]);
				ds.setProjectPlanChangeNum((Long)a[8]);
				ds.setProjectPlanFinishNum((Long)a[9]);
				ds.setContractManagementNum((Long)a[10]);
				ds.setContractManagementMoney((Double)a[11]);
				ds.setContractManagementPlanNum((Long)a[12]);
				ds.setContractManagementPlanChangeNum((Long)a[13]);
				ds.setContractManagementPlanFinishNum((Long)a[14]);
				ds.setFinancialManagementNum((Long)a[15]);
				ds.setFinancialManagementMoney((Double)a[16]);
				ds.setBillingRecordNum((Long)a[17]);
				ds.setBillingRecordMoney((Double)a[18]);
				ds.setBusinessTravelNum((Long)a[19]);
				ds.setLeaveNum((Long)a[20]);
				ds.setOvertimeNum((Long)a[21]);
				ds.setDnDate(a[22]+"");
				dates.add(ds);
				
			}
		}
		
		String json =JSONArray.fromObject(dates).toString();
		
		return json;
	}
	
	//根据业务属性调用片区数据
	public List<Department> getAllClassifications(String Btype) {
		try {
		List<Department>mpList = new ArrayList<Department>();
		Long bt=Long.parseLong(Btype);
		List<CodeValue> vs =this.codeValueManager.loadByKey("id", bt.longValue());
		CodeValue c =vs.get(0);
		if(c.getCode().equals("21001")){
			mpList = departmentManager.loadByKey("parentDept.name","军品销售");
		}
		if(c.getCode().equals("21002")){
			mpList = departmentManager.loadByKey("parentDept.name","民品销售");
		}
		 
		 List<Department> departmentList = new ArrayList<Department>();
		 for(Department d: mpList){
			 Department de = new Department();
			 de.setId(d.getId());
			 de.setName(d.getName());
			 departmentList.add(de);
		 }
		return departmentList;
	} catch (Exception e) {
		e.printStackTrace();
		return new ArrayList();
	}
}
	//公司数据分析图展示
	public String loadAllMyDataByContion(String timeStart,String timeEnd, String businessType,String classification, String flag) {
		    List<Data> dates = new ArrayList<Data>();
			List<Object[]> tempList = new ArrayList<Object[]>();
			//查询所有数据
			if(businessType.equals("-1") && classification.equals("-1") && timeStart.equals("") && timeEnd.equals("")){
				if("year".equals(flag)){
					//查每年全部数据
					tempList = this.dataDao.loadAllMyDataYearByAllContion( timeStart,timeEnd,businessType,classification,flag);
				}else if("month".equals(flag)){
					//查每月全部数据
					tempList = this.dataDao.loadAllMyDataMonthByAllContion(timeStart,timeEnd,businessType,classification,flag);
				}else {
					tempList = this.dataDao.listMonthSumStatisticalBusinessTypes(timeStart,timeEnd,businessType,classification,flag);
				}
			}else {
				//根据条件查询数据
				if("year".equals(flag)){
					//查每年
					tempList = this.dataDao.loadAllMyDataYearByContion( timeStart,timeEnd,businessType,classification,flag);
				}else if("month".equals(flag)){
					//查每月
					tempList = this.dataDao.loadAllMyDataMonthByContion(timeStart,timeEnd,businessType,classification,flag);
				}else {
					tempList = this.dataDao.listMonthSumStatisticalBusinessTypes(timeStart,timeEnd,businessType,classification,flag);
				}
			}
		    
			if(tempList!=null&&tempList.size()>0){
			
				for(Object[] a:tempList){
					Data ds = new Data();
					DecimalFormat df = new DecimalFormat("#.##");
					if(flag.equals("randomTime")){
						ds.setContractManagementNum(((Long)a[0]));
						ds.setContractManagementMoney(Double.parseDouble(df.format(a[1])));
						ds.setFinancialManagementNum((Long)a[2]);
						ds.setFinancialManagementMoney(Double.parseDouble(df.format(a[3])));
						ds.setBillingRecordNum((Long)a[4]);
						ds.setBillingRecordMoney(Double.parseDouble(df.format(a[5])));
						dates.add(ds);
						
					}else{
						if(flag.equals("month")){
								ds.setMonth(a[0]+"");
							}else if(flag.equals("year")){
								ds.setYear(a[0]+"");
							}
							ds.setContractManagementNum(((Long)a[1]));
							ds.setContractManagementMoney(Double.parseDouble(df.format(a[2])));
							ds.setFinancialManagementNum((Long)a[3]);
							ds.setFinancialManagementMoney(Double.parseDouble(df.format(a[4])));
							ds.setBillingRecordNum((Long)a[5]);
							ds.setBillingRecordMoney(Double.parseDouble(df.format(a[6])));
							dates.add(ds);
						}
					
				}
			}
			
			String json =JSONArray.fromObject(dates).toString();
			return json;
	}
	
	/*//展示饼型图数据
	public String loadAllMyDataByBusinessType(String startTime,String endTime,String flag) {
	     List<Object[]> tempList1 = new ArrayList<Object[]>();
	     List<Object[]> tempList2 = new ArrayList<Object[]>();
	     List<Object[]> tempList3 = new ArrayList<Object[]>();
	     tempList1=this.dataDao.loadAllMyDataByBusinessType(startTime,endTime,flag);
	     tempList2=this.dataDao.loadAllMyDataByMilitary(startTime,endTime,flag);
	     tempList3=this.dataDao.loadAllMyDataByCivilian(startTime,endTime,flag);
	     List<List<Object>> wantListList1 = new ArrayList<List<Object>>();
	     List<List<Object>> wantListList2 = new ArrayList<List<Object>>();
	     List<List<Object>> wantListList3 = new ArrayList<List<Object>>();
	     for (Object[] obj1 : tempList1) {
	    	 List<Object> wantList1 = new ArrayList<Object>();
	    	 for(int i=0;i<obj1.length;i++){
	    		if(i == 7){
	    			CodeValue codeValue = codeValueManager.loadCodeValue(Long.parseLong(obj1[i]+""));
	    			wantList1.add(codeValue.getName());
	    		}
	    		if( i == 4){
	    			wantList1.add(obj1[i]);
	    		}
	    	 }
	    	wantListList1.add(wantList1);
	     }
	 
	     for (Object[] obj2 : tempList2) {
	    	 List<Object> wantList2 = new ArrayList<Object>();
	    	 for(int i=0;i<obj2.length;i++){
	    		if( i == 4 || i == 7){
	    			wantList2.add(obj2[i]);
	    		}
	    	 }
	    	 wantListList2.add(wantList2);
	     }
	     for (Object[] obj3 : tempList3) {
	    	 List<Object> wantList3 = new ArrayList<Object>();
	    	 for(int i=0;i<obj3.length;i++){
	    		if(i == 4 || i == 7){
	    			wantList3.add(obj3[i]);
	    		}
	    	 }
	    	wantListList3.add(wantList3);
	     }
	     //拿到数据库数据拿到页面显示数据返回list
	     List<List<List<Object>>> arrayList = new ArrayList<List<List<Object>>>();
	     arrayList.add(wantListList1.subList(0, 2));
	     arrayList.add(wantListList2.subList(0, 1));
	     arrayList.add(wantListList2.subList(2, 7));
	     arrayList.add(wantListList2.subList(8, 9));
	     arrayList.add(wantListList3.subList(0, 2));
	     arrayList.add(wantListList3.subList(3, 4));
	     arrayList.add(wantListList3.subList(6, 8));
	     String json =JSONArray.fromObject(arrayList).toString();
		return json;
	}*/
	//展示部门月度分析
	public List<List<List<Object>>> listStatistilcalBusinessTypes(String date,String name,String businessType, String classification) {
		 List<Object[]> tempListOne = new ArrayList<Object[]>();
		 tempListOne=this.dataDao.listStatisticalBusinessTypes(date,businessType,classification);
		 List<List<Object>> wantListListOne  = new ArrayList<List<Object>>();
		 if(name.equals("合同数")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==1 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("合同金额")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==2 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("收款笔")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==3 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("收款金额")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==4 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("开票记录数")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==5 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("开票金额")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==6 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }
		 List<List<List<Object>>> arrayListOne = new ArrayList<List<List<Object>>>();
		 if(businessType.equals("-1")&&classification.equals("-1")){
			 arrayListOne.add(wantListListOne.subList(2, 3));
			 arrayListOne.add(wantListListOne.subList(5, 6));
			 arrayListOne.add(wantListListOne.subList(8, 13));
			 arrayListOne.add(wantListListOne.subList(15, 16));
			 arrayListOne.add(wantListListOne.subList(17, 19));
			 arrayListOne.add(wantListListOne.subList(25, 26));
			 arrayListOne.add(wantListListOne.subList(27, 28));
		 }else if(!businessType.equals("-1")&&classification.equals("-1")){
			 if(businessType.equals("537")){
				 arrayListOne.add(wantListListOne.subList(0, 1));
				 arrayListOne.add(wantListListOne.subList(2, 7));
				 arrayListOne.add(wantListListOne.subList(8,wantListListOne.size()));
			 }else{
				 arrayListOne.add(wantListListOne.subList(0, 2));
				 arrayListOne.add(wantListListOne.subList(3, 4));
				 arrayListOne.add(wantListListOne.subList(6,wantListListOne.size()));
			 }
			 
		 }else{
			 arrayListOne.add(wantListListOne.subList(0, 1));
		 }
		  return arrayListOne;
	}
	//展示部门年度分析
	public List<List<List<Object>>> listYearStatistilcalBusinessTypes(String dates, String name, String businessType, String classification) {
		 List<Object[]> tempListOne = new ArrayList<Object[]>();
		 tempListOne=this.dataDao.listYearStatisticalBusinessTypes(dates,businessType,classification);
		 List<List<Object>> wantListListOne  = new ArrayList<List<Object>>();
		 if(name.equals("合同数")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==1 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("合同金额")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==2 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("收款笔")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==3 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("收款金额")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==4 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("开票记录数")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==5 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("开票金额")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==6 || i==7){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 wantListListOne.add(listOne);
			 }
		 }
		  List<List<List<Object>>> arrayListOne = new ArrayList<List<List<Object>>>();
		  if(businessType.equals("-1")&&classification.equals("-1")){
				 arrayListOne.add(wantListListOne.subList(2, 3));
				 arrayListOne.add(wantListListOne.subList(5, 6));
				 arrayListOne.add(wantListListOne.subList(8, 13));
				 arrayListOne.add(wantListListOne.subList(15, 16));
				 arrayListOne.add(wantListListOne.subList(17, 19));
				 arrayListOne.add(wantListListOne.subList(25, 26));
				 arrayListOne.add(wantListListOne.subList(27, 28));
			 }else if(!businessType.equals("-1")&&classification.equals("-1")){
				 if(businessType.equals("537")){
					 arrayListOne.add(wantListListOne.subList(0, 1));
					 arrayListOne.add(wantListListOne.subList(2, 7));
					 arrayListOne.add(wantListListOne.subList(8,wantListListOne.size()));
				 }else{
					 arrayListOne.add(wantListListOne.subList(0, 2));
					 arrayListOne.add(wantListListOne.subList(3, 4));
					 arrayListOne.add(wantListListOne.subList(6,wantListListOne.size()));
				 }
				 
			 }else{
				 arrayListOne.add(wantListListOne.subList(0, 1));
			 }
		  return arrayListOne;
	}
	
	//任意查询中部门展示
	public List<List<List<Object>>> listSumStatistilcalBusinessTypes(String startTime, String endTime, String name, String businessType, String classification) {
		
		 List<Object[]> tempListOne = new ArrayList<Object[]>();
		 tempListOne=this.dataDao.listSumStatisticalBusinessTypes(startTime,endTime,businessType,classification);
		 List<List<Object>> wantListListOne  = new ArrayList<List<Object>>();
		 if(name.equals("合同数")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==1 ){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 listOne.add(startTime);
				 listOne.add(endTime);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("合同金额")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==2 ){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 listOne.add(startTime);
				 listOne.add(endTime);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("收款笔")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==3 ){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 listOne.add(startTime);
				 listOne.add(endTime);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("收款金额")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==4 ){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 listOne.add(startTime);
				 listOne.add(endTime);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("开票记录数")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==5 ){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 listOne.add(startTime);
				 listOne.add(endTime);
				 wantListListOne.add(listOne);
			 }
		 }else if(name.equals("开票金额")){
			 for (Object[] objOne : tempListOne) {
				 List<Object> listOne = new ArrayList<Object>();
				 for(int i=0; i<objOne.length; i++){
					 if(i==0 || i==6 ){
						 listOne.add(objOne[i]);
					 }
				 }
				 listOne.add(name);
				 listOne.add(startTime);
				 listOne.add(endTime);
				 wantListListOne.add(listOne);
			 }
		 }
		  List<List<List<Object>>> arrayListOne = new ArrayList<List<List<Object>>>();
		  if(businessType.equals("-1")&&classification.equals("-1")){
				 arrayListOne.add(wantListListOne.subList(2, 3));
				 arrayListOne.add(wantListListOne.subList(5, 6));
				 arrayListOne.add(wantListListOne.subList(8, 13));
				 arrayListOne.add(wantListListOne.subList(15, 16));
				 arrayListOne.add(wantListListOne.subList(17, 19));
				 arrayListOne.add(wantListListOne.subList(25, 26));
				 arrayListOne.add(wantListListOne.subList(27, 28));
			 }else if(!businessType.equals("-1")&&classification.equals("-1")){
				 if(businessType.equals("537")){
					 arrayListOne.add(wantListListOne.subList(0, 1));
					 arrayListOne.add(wantListListOne.subList(2, 7));
					 arrayListOne.add(wantListListOne.subList(8,wantListListOne.size()));
				 }else{
					 arrayListOne.add(wantListListOne.subList(0, 2));
					 arrayListOne.add(wantListListOne.subList(3, 4));
					 arrayListOne.add(wantListListOne.subList(6,wantListListOne.size()));
				 }
				 
			 }else{
				 arrayListOne.add(wantListListOne.subList(0, 1));
			 }
		  return arrayListOne;
	}

	

}
