package main.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import main.dao.DataDao;
import main.pojo.Data;
import main.pojo.PersonnelFiles;
import main.service.DataService;

public class DataServiceImpl implements DataService {
         
private DataDao dataDao;

@Override
public void storeData(Data data) {
	dataDao.storeData(data);
}

public void storeData(PersonnelFiles pf, HashMap map) {
	   SimpleDateFormat sfMonth = new SimpleDateFormat("yyyy��MM��");
	   SimpleDateFormat sfYear = new SimpleDateFormat("yyyy��"); 
	   String year =sfYear.format(new Date());
	   String month =sfMonth.format(new Date());
	   if(map.get("date")!=null){
		   year =sfYear.format((Date)map.get("date"));
		   month =sfMonth.format((Date)map.get("date"));
	   }
	   /*  String[] key ={"month","personnelFiles.code"};
	       String[] value={month,pf.getCode()};
	     List<Data>  datas =this.dataDao.loadByKeyArray(key, value);
	   */
	   
	  List<Data>  datas =this.dataDao.loadByMonthAndCode(month, pf.getCode());
	  Data data =null;
	  if(datas!=null&&datas.size()>0){
		  data =datas.get(0);
		  if((Long)map.get("submitNum")==1){
			  if(((String)map.get("type")).equals("10001")){//�طô�������
 			  long num =data.getBackvisitNum();
 			  data.setBackvisitNum(data.getBackvisitNum()+1);
 		  }
 		  if(((String)map.get("type")).equals("10002")){//�ձ�ʵд��������
 			  data.setActualDaily(data.getActualDaily()+1);
 		  }
 		  if(((String)map.get("type")).equals("10006")){//��ͬ�������Ӻ�ͬ�������
 			  data.setContractManagementMoney(data.getContractManagementMoney()+Double.parseDouble(map.get("thisMoney")+""));
     		  data.setContractManagementNum(data.getContractManagementNum()+1);
 		  }
 		  if(((String)map.get("type")).equals("10007")){//�տ���������տ�������
 			  data.setFinancialManagementMoney(data.getFinancialManagementMoney()+Double.parseDouble(map.get("thisMoney")+""));
     		  data.setFinancialManagementNum(data.getFinancialManagementNum()+1);
 		  }
 		  if(((String)map.get("type")).equals("10013")){//��Ʊ�������ӿ�Ʊ�������
 			  data.setBillingRecordMoney(data.getBillingRecordMoney()+Double.parseDouble(map.get("thisMoney")+""));
     		  data.setBillingRecordNum(data.getBillingRecordNum()+1);
 		  }
 		  if(((String)map.get("type")).equals("10003")){//��Ŀ������
 			  data.setProjectNum(data.getProjectNum()+1);
 		  }
 		  if(((String)map.get("type")).equals("10011")){//�ܱ�ʵд��������
 			  data.setActualWeekly(data.getActualWeekly()+1);
 		  }
		  }else if((Long)map.get("submitNum") >1) {
 		  if(((String)map.get("type")).equals("10006")){//��ͬ�������Ӻ�ͬ�������
 			  data.setContractManagementMoney(data.getContractManagementMoney()+Double.parseDouble(map.get("thisMoney")+"")-Double.parseDouble(map.get("lastMoney")+""));
 		  }
 		  if(((String)map.get("type")).equals("10007")){//�տ���������տ�������
 			  data.setFinancialManagementMoney(data.getFinancialManagementMoney()+Double.parseDouble(map.get("thisMoney")+"")-Double.parseDouble(map.get("lastMoney")+""));
 		  }
 		  if(((String)map.get("type")).equals("10013")){//��Ʊ�������ӿ�Ʊ�������
 			  data.setBillingRecordMoney(data.getBillingRecordMoney()+Double.parseDouble(map.get("thisMoney")+"")-Double.parseDouble(map.get("lastMoney")+""));
 		  }
		}
		  
	  }else {
		  data = new Data();
		  data.setYear(year);//������
		  data.setMonth(month);//�����·�
		  if(((String)map.get("type")).equals("10001")){//�طô�������
			  data.setBackvisitNum(1l);
		  }
		  if(((String)map.get("type")).equals("10002")){//�ձ�ʵд��������
			  data.setActualDaily(1l);
		  }
		  if(((String)map.get("type")).equals("10006")){//��ͬ�������Ӻ�ͬ�������
			  data.setContractManagementMoney(Double.parseDouble(map.get("thisMoney")+""));
 		  data.setContractManagementNum(1l);
		  }
		  if(((String)map.get("type")).equals("10007")){//�տ���������տ�������
			  data.setFinancialManagementMoney(Double.parseDouble(map.get("thisMoney")+""));
 		  data.setFinancialManagementNum(1l);
		  }
		  if(((String)map.get("type")).equals("10013")){//��Ʊ�������ӿ�Ʊ�������
			  data.setBillingRecordMoney(Double.parseDouble(map.get("thisMoney")+""));
 		  data.setBillingRecordNum(1l);
		  }
		  if(((String)map.get("type")).equals("10003")){//��Ŀ������
			  data.setProjectNum(1l);
		  }
		  if(((String)map.get("type")).equals("10011")){//�ܱ�ʵд��������
			  data.setActualWeekly(1l);
		  }
		  data.setPersonnelFiles(pf);//���淢����
		  data.setShouldDaily(0l);//����Ӧд�ձ�����
		  data.setShouldWeekly(0l);//���� Ӧд�ܱ���
		  data.setMonthly(0);//�±���ʱû����
		 
		  
	}
	  this.dataDao.storeData(data);
	   
}

@Override
public Object loadAllDataByYear(HashMap map) {
	return dataDao.loadAllDataByYear(map);
}

@Override
public Session getSuperSession() {
	return dataDao.getSuperSession();
}



public DataDao getDataDao() {
	return dataDao;
}

public void setDataDao(DataDao dataDao) {
	this.dataDao = dataDao;
}
	



	
}
