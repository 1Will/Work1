/*    */ package com.yongjun.tdms.service.workspace.data.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.workspace.data.DataDao;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workspace.data.Data;
import com.yongjun.tdms.service.workspace.data.DataManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/*    */ 
/*    */ public class DefaultDataManager extends BaseManager
/*    */   implements DataManager
/*    */ {
/*    */   private final DataDao dataDao;
/*    */ 
/*    */   public DefaultDataManager(DataDao dataDao)
/*    */   {
/* 18 */     this.dataDao = dataDao;
/*    */   }
/*    */   public List<Data> loadAllData(Long[] lbIds)
/*    */   {
/* 46 */     return this.dataDao.loadAllData(lbIds);
/*    */   }
/*    */ 
/*    */   public List<Data> loadAllData() {
/* 50 */     return this.dataDao.loadAllData();
/*    */   }
/*    */ 
/*    */   public List<Data> loadByKey(String keyName, Object keyValue) throws DaoException
/*    */   {
/* 55 */     return this.dataDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<Data> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException
/*    */   {
/* 60 */     return this.dataDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 
/*    */   public Data loadData(Long lbId) {
/* 64 */     return this.dataDao.loadData(lbId);
/*    */   }
/*    */ 
/*    */   public void storeData(Data lb) {
/* 68 */     this.dataDao.storeData(lb);
/*    */   }

           public void storeData(PersonnelFiles pf, HashMap map) throws DaoException{
        	   SimpleDateFormat sfMonth = new SimpleDateFormat("yyyy年MM月");
        	   SimpleDateFormat sfYear = new SimpleDateFormat("yyyy年"); 
        	   String year =sfYear.format(new Date());
        	   String month =sfMonth.format(new Date());
        	   if(map.get("date")!=null){
        		   year =sfYear.format((Date)map.get("date"));
        		   month =sfMonth.format((Date)map.get("date"));
        	   }
        	   String[] key ={"month","personnelFiles.code"};
        	   String[] value={month,pf.getCode()};
        	  List<Data>  datas =this.dataDao.loadByKeyArray(key, value);
        	  Data data =null;
        	  if(datas!=null&&datas.size()>0){
        		  data =datas.get(0);
        		  if((Long)map.get("submitNum")==1){
        			  if(((String)map.get("type")).equals("10001")){//回访次数增加
            			  long num =data.getBackvisitNum();
            			  data.setBackvisitNum(data.getBackvisitNum()+1);
            		  }
            		  if(((String)map.get("type")).equals("10002")){//日报实写次数增加
            			  data.setActualDaily(data.getActualDaily()+1);
            		  }
            		  if(((String)map.get("type")).equals("10006")){//合同次数增加合同金额增加
            			  data.setContractManagementMoney(data.getContractManagementMoney()+Double.parseDouble(map.get("thisMoney")+""));
                		  data.setContractManagementNum(data.getContractManagementNum()+1);
            		  }
            		  if(((String)map.get("type")).equals("10007")){//收款次数增加收款金额增加
            			  data.setFinancialManagementMoney(data.getFinancialManagementMoney()+Double.parseDouble(map.get("thisMoney")+""));
                		  data.setFinancialManagementNum(data.getFinancialManagementNum()+1);
            		  }
            		  if(((String)map.get("type")).equals("10013")){//开票次数增加开票金额增加
            			  data.setBillingRecordMoney(data.getBillingRecordMoney()+Double.parseDouble(map.get("thisMoney")+""));
                		  data.setBillingRecordNum(data.getBillingRecordNum()+1);
            		  }
            		  if(((String)map.get("type")).equals("10003")){//项目数增加
            			  data.setProjectNum(data.getProjectNum()+1);
            		  }
            		  if(((String)map.get("type")).equals("10011")){//周报实写次数增加
            			  data.setActualWeekly(data.getActualWeekly()+1);
            		  }
        		  }else if((Long)map.get("submitNum") >1) {
            		  if(((String)map.get("type")).equals("10006")){//合同次数增加合同金额增加
            			  data.setContractManagementMoney(data.getContractManagementMoney()+Double.parseDouble(map.get("thisMoney")+"")-Double.parseDouble(map.get("lastMoney")+""));
            		  }
            		  if(((String)map.get("type")).equals("10007")){//收款次数增加收款金额增加
            			  data.setFinancialManagementMoney(data.getFinancialManagementMoney()+Double.parseDouble(map.get("thisMoney")+"")-Double.parseDouble(map.get("lastMoney")+""));
            		  }
            		  if(((String)map.get("type")).equals("10013")){//开票次数增加开票金额增加
            			  data.setBillingRecordMoney(data.getBillingRecordMoney()+Double.parseDouble(map.get("thisMoney")+"")-Double.parseDouble(map.get("lastMoney")+""));
            		  }
				}
        		  
        	  }else {
        		  data = new Data();
        		  data.setYear(year);//保存年
        		  data.setMonth(month);//保存月份
        		  if(((String)map.get("type")).equals("10001")){//回访次数增加
        			  data.setBackvisitNum(1l);
        		  }
        		  if(((String)map.get("type")).equals("10002")){//日报实写次数增加
        			  data.setActualDaily(1l);
        		  }
        		  if(((String)map.get("type")).equals("10006")){//合同次数增加合同金额增加
        			  data.setContractManagementMoney(Double.parseDouble(map.get("thisMoney")+""));
            		  data.setContractManagementNum(1l);
        		  }
        		  if(((String)map.get("type")).equals("10007")){//收款次数增加收款金额增加
        			  data.setFinancialManagementMoney(Double.parseDouble(map.get("thisMoney")+""));
            		  data.setFinancialManagementNum(1l);
        		  }
        		  if(((String)map.get("type")).equals("10013")){//开票次数增加开票金额增加
        			  data.setBillingRecordMoney(Double.parseDouble(map.get("thisMoney")+""));
            		  data.setBillingRecordNum(1l);
        		  }
        		  if(((String)map.get("type")).equals("10003")){//项目数增加
        			  data.setProjectNum(1l);
        		  }
        		  if(((String)map.get("type")).equals("10011")){//周报实写次数增加
        			  data.setActualWeekly(1l);
        		  }
        		  data.setPersonnelFiles(pf);//保存发生人
        		  data.setShouldDaily(0l);//保存应写日报次数
        		  data.setShouldWeekly(0l);//保存 应写周报数
        		  data.setMonthly(0);//月报暂时没做。
        		 
        		  
			}
        	  this.dataDao.storeData(data);
        	   
           }
/*    */ 
/*    */
		public Object loadAllDataByYear(HashMap map) {
			// TODO Auto-generated method stub
			return this.dataDao.loadAllDataByYear(map);
		}
		public List<Data> loadAllDataByTeam(HashMap map) {
			// TODO Auto-generated method stub
			return this.dataDao.loadAllDataByTeam(map);
		}
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.leaveBill.pojo.DefaultLeaveBillManager
 * JD-Core Version:    0.6.2
 */