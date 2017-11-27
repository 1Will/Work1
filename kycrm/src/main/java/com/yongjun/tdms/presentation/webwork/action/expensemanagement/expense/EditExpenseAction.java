package com.yongjun.tdms.presentation.webwork.action.expensemanagement.expense;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFee;
import com.yongjun.tdms.model.expensemanagement.expense.Expense;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFee;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.expensemanagement.airFee.AirFeeManager;
import com.yongjun.tdms.service.expensemanagement.airFee.AirHouseFeeManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFeeManager;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricHouseFeeManager;
import com.yongjun.tdms.service.expensemanagement.expense.ExpenseManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFeeManager;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterHouseFeeManager;

public class EditExpenseAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final BuildingManager buildingManager;
	private final FloorManager floorManager;
	private final HouseManager houseManager;
	private final AirFeeManager airFeeManager;
	private final CodeValueManager codeValueManager;
	private final HouseListManager houseListManager;
	private final AirHouseFeeManager airHouseFeeManager;
	private final ExpenseManager expenseManager;
    private final CustomerInfoManager customerInfoManager;
    private final WaterHouseFeeManager waterHouseFeeManager;
    private final ElectricHouseFeeManager electricHouseFeeManager;
    private final WaterFeeManager waterFeeManager;
    private final ElectricFeeManager electricFeeManager;
    private final ProductListManager productListManager;
    private final ContactArchivesManager contactArchivesManager;
    private final ReturnPlanManager returnPlanManager ;
	public DecimalFormat format = new DecimalFormat("0.00");
	private Expense expense;

	public EditExpenseAction(BuildingManager buildingManager, FloorManager floorManager, HouseManager houseManager,
			AirFeeManager airFeeManager, CodeValueManager codeValueManager, HouseListManager houseListManager,
			AirHouseFeeManager airHouseFeeManager,ExpenseManager expenseManager,CustomerInfoManager customerInfoManager,
			WaterHouseFeeManager waterHouseFeeManager,ElectricHouseFeeManager electricHouseFeeManager,WaterFeeManager waterFeeManager,
			ElectricFeeManager electricFeeManager,ProductListManager productListManager,ContactArchivesManager contactArchivesManager
			,ReturnPlanManager returnPlanManager) {
		this.buildingManager = buildingManager;
		this.floorManager = floorManager;
		this.houseManager = houseManager;
		this.airFeeManager = airFeeManager;
		this.codeValueManager = codeValueManager;
		this.houseListManager = houseListManager;
		this.airHouseFeeManager = airHouseFeeManager;
		this.expenseManager = expenseManager;
		this.customerInfoManager=customerInfoManager;
		this.waterHouseFeeManager=waterHouseFeeManager;
		this.electricHouseFeeManager=electricHouseFeeManager;
		this.waterFeeManager=waterFeeManager;
		this.electricFeeManager=electricFeeManager;
		this.productListManager=productListManager;
		this.contactArchivesManager=contactArchivesManager;
		this.returnPlanManager=returnPlanManager;
	}

	public void prepare() throws Exception {
		if (hasId("expense.id")) {
			this.expense = this.expenseManager.loadExpense(getId("expense.id"));
		} else {
			this.expense = new Expense();
		}
	}

	public String save() {
		boolean isNew = this.expense.isNew();
		try {
			this.expenseManager.storeExpense(this.expense);
			if (isNew) {
				addActionMessage(getText("expense.add.success"));
				return NEW;
			}
			addActionMessage(getText("expense.edit.success"));
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage(getText("expense.edit.error"));
			return ERROR;
		}
	}
	public int autoWaterAndEleFee(){
//    查询所有的客户
		try {
			List<CustomerInfo> customerInfos=this.customerInfoManager.loadByKey("disabled", "0");
			if(customerInfos!=null&&customerInfos.size()>0){
				//查询数据库中的Expense最后一次结束时间
				 Date date_last_=this.expenseManager.loadLastEndTime();
				 if (date_last_!=null) {
					return save_AllExcepense(date_last_,customerInfos,1);
				}else {
					//数据库中没有Expense数据（第一次计算水电费）
					List <WaterFee> waterFees=this.waterFeeManager.loadWaterFee();
					System.out.println(waterFees.get(0).getStarTime());
					if(waterFees!=null){
						 return save_AllExcepense(waterFees.get(0).getStarTime(),customerInfos,0);
					}
				}
			}
			
		} catch (DaoException e) {
			e.printStackTrace();
		} 
		
		return 3;//客户不存在
	}
	public int save_AllExcepense(Date date_last_,List<CustomerInfo>customerInfos,int count ){//count 如果数据库中没有数据开始时间以水电费起始时间为准，如果有数据，开始时间为数据库中结束时间加一天
		 DecimalFormat numFormat = new DecimalFormat("0.00");
		 List<Expense>expenses=new ArrayList<Expense>();
		 Map <Long, String>map=new HashMap<Long, String>();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 WaterFee waterFlag=this.waterFeeManager.loadByStartTime(date_last_);
		 ElectricFee eleFlag=this.electricFeeManager.loadByStartTime(date_last_);
		 if(waterFlag!=null&&eleFlag!=null &&sdf.format(waterFlag.getEndTime()).equals(sdf.format(eleFlag.getEndTime()))){
			 for(CustomerInfo cus:customerInfos){
			 Expense expense=new Expense();
			 double sumFee=0;
			 expense.setStartTime(nextDate(date_last_,0,count));
			 expense.setEndTime(waterFlag.getEndTime());
			 double waterMoney=this.waterHouseFeeManager.loadSumFeeByCusId(cus.getId(), date_last_);
			 double electricMoney=this.electricHouseFeeManager.loadSumFeeByCusId(cus.getId(),date_last_);
			 double airMoney=this.airHouseFeeManager.loadSumFeeByCusId(cus.getId(), date_last_);
			 sumFee=waterMoney+electricMoney+airMoney;
			 expense.setWaterMoney(Double.parseDouble(numFormat.format(waterMoney)));//水费
			 expense.setElectricMoney(Double.parseDouble(numFormat.format(electricMoney)));//电费
			 expense.setAirMoney(Double.parseDouble(numFormat.format(airMoney)));//空调费 
			 if(sumFee!=0){
				//每个客户对应的所有物业费
				 List <ProductList> propertyFeeLists_=this.productListManager.loadProductListsByTime(cus.getId(),"物业" ,date_last_);
				 double propertyFee=0.0;
				 if(propertyFeeLists_!=null && propertyFeeLists_.size()>0){
					 for(int i=0;i<propertyFeeLists_.size();i++){
						 boolean a=propertyFeeLists_.get(i).getStartTime().before(date_last_);//物业费开始时间在数据库的上次水电费结束时间之前
						 boolean b=waterFlag.getEndTime().before(propertyFeeLists_.get(i).getEndTime());//物业费结束时间在数据库的本次水电费结束时间之后
						 boolean c=sdf.format(propertyFeeLists_.get(i).getStartTime()).equals(sdf.format(date_last_));//物业费开始时间在数据库的上次水电费结束时间一致
						 boolean d=sdf.format(propertyFeeLists_.get(i).getEndTime()).equals(sdf.format(waterFlag.getEndTime()));//物业费结束时间在数据库的本次水电费结束时间一致
						 if((a&&b)||(a&&d)||(b&&c)||(c&&d)){
							 propertyFee+=(propertyFeeLists_.get(i).getCount()*propertyFeeLists_.get(i).getUnitPrice()*waterFlag.getMonth());
						 }
						 if((a&&!b)||(a&&!d)||(!b&&c)||(c&&!d)){
							 for(int j=1;j<=waterFlag.getMonth();j++){
								 boolean e=nextDate(waterFlag.getEndTime(),-j,0).before(propertyFeeLists_.get(i).getEndTime());
								 boolean f=sdf.format(propertyFeeLists_.get(i).getEndTime()).equals(sdf.format(nextDate(waterFlag.getEndTime(),-j,0)));
								 if(e||f){
									 propertyFee+=(propertyFeeLists_.get(i).getCount()*propertyFeeLists_.get(i).getUnitPrice()*j++);
									 break;
								 }
							 }
						 }
						 
						 if((!a&&b)||(!a&&d)||(b&&!c)||(!c&&d)){
							 for(int j=1;j<=waterFlag.getMonth();j++){
								 boolean e=propertyFeeLists_.get(i).getStartTime().before(nextDate(date_last_,j,0));//物业费开始时间在数据库的上次水电费结束时间之前
								 boolean f=sdf.format(propertyFeeLists_.get(i).getStartTime()).equals(sdf.format(nextDate(date_last_,j,0)));//物业费开始时间在数据库的上次水电费结束时间一致
								 if(e||f){
									 propertyFee+=(propertyFeeLists_.get(i).getCount()*propertyFeeLists_.get(i).getUnitPrice()*j++);
									 break;
								 }
							 }
						 }
						if(!a&&!b&&!c&&!d){
							int month= (propertyFeeLists_.get(i).getEndTime().getYear() - propertyFeeLists_.get(i).getStartTime().getYear()) * 12 + propertyFeeLists_.get(i).getEndTime().getMonth() - propertyFeeLists_.get(i).getStartTime().getMonth();
							propertyFee+=(propertyFeeLists_.get(i).getCount()*propertyFeeLists_.get(i).getUnitPrice()*month++);
						}
					 }
					 sumFee+=propertyFee;
				 }
				 expense.setPropertyMoney(Double.parseDouble(numFormat.format(propertyFee)));
				 //每个客户对应的所有网络费
				 List <ProductList> netFeeLists_=this.productListManager.loadProductListsByTime(cus.getId(),"网络" ,date_last_);
				 double netFee=0.0;
				 if(netFeeLists_!=null && netFeeLists_.size()>0){
					 for(int i=0;i<netFeeLists_.size();i++){
						 boolean a=netFeeLists_.get(i).getStartTime().before(date_last_);
						 boolean b=waterFlag.getEndTime().before(netFeeLists_.get(i).getEndTime());
						 boolean c=sdf.format(netFeeLists_.get(i).getStartTime()).equals(sdf.format(date_last_));
						 boolean d=sdf.format(netFeeLists_.get(i).getEndTime()).equals(sdf.format(waterFlag.getEndTime()));
						 if((a&&b)||(a&&d)||(b&&c)||(c&&d)){
							 netFee+=(netFeeLists_.get(i).getCount()*netFeeLists_.get(i).getUnitPrice()*waterFlag.getMonth());
						 }
						 if((a&&!b)||(a&&!d)||(!b&&c)||(c&&!d)){
							 for(int j=1;j<=waterFlag.getMonth();j++){
								 boolean e=nextDate(waterFlag.getEndTime(),-j,0).before(netFeeLists_.get(i).getEndTime());
								 boolean f=sdf.format(netFeeLists_.get(i).getEndTime()).equals(sdf.format(nextDate(waterFlag.getEndTime(),-j,0)));
								 if(e||f){
									 netFee+=(netFeeLists_.get(i).getCount()*netFeeLists_.get(i).getUnitPrice()*j++);
									 break;
								 }
							 }
						 }
						 
						 if((!a&&b)||(!a&&d)||(b&&!c)||(!c&&d)){
							 for(int j=1;j<=waterFlag.getMonth();j++){
								 boolean e=netFeeLists_.get(i).getStartTime().before(nextDate(date_last_,j,0));//物业费开始时间在数据库的上次水电费结束时间之前
								 boolean f=sdf.format(netFeeLists_.get(i).getStartTime()).equals(sdf.format(nextDate(date_last_,j,0)));//物业费开始时间在数据库的上次水电费结束时间一致
								 if(e||f){
									 netFee+=(netFeeLists_.get(i).getCount()*netFeeLists_.get(i).getUnitPrice()*j++);
									 break;
								 }
							 }
						 }
						if(!a&&!b&&!c&&!d){
							int month= (netFeeLists_.get(i).getEndTime().getYear() - netFeeLists_.get(i).getStartTime().getYear()) * 12 + netFeeLists_.get(i).getEndTime().getMonth() - netFeeLists_.get(i).getStartTime().getMonth();
							netFee+=(netFeeLists_.get(i).getCount()*netFeeLists_.get(i).getUnitPrice()*month++);
						}
					 }
					 sumFee+=netFee;
				 }
				 expense.setNetMoney(Double.parseDouble(numFormat.format(netFee)));
				 //设置客户数量为1
				 expense.setSumCustomer(1);
				 String [] paramArrayOfString={"contractManagement.customerInfo", "isuse"};
				 Object []paramArrayOfObject={cus.getId(),1};
				 List<HouseList> houseLists;
				try {
					houseLists = this.houseListManager.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
					if(houseLists!=null){
						double sumSquare=0;
						String houseCode="";
						for(HouseList h:houseLists){
							sumSquare+=h.getHouse().getSquare();
							if("".equals(houseCode)){
								houseCode+=h.getHouse().getCode();
							}else{
								houseCode+=","+h.getHouse().getCode();
							}
						 }
						 map.put(cus.getId(),houseCode);//将房间编码存入map中
						 expense.setSumSquare(Double.parseDouble(numFormat.format(sumSquare)));
						 expense.setSumHouse(houseLists.size());
					}
					expense.setSum(Double.parseDouble(numFormat.format(sumFee)));//总金额
					expense.setCustomerInfo(cus);
					expenses.add(expense);
				} catch (DaoException e) {
					e.printStackTrace();
				}
			 }
			 }   
			 
			 if(expenses!=null && expenses.size()>0){
				 try {
				//保存父类水电费
				 SimpleDateFormat code_sdf=new SimpleDateFormat("yyyyMMdd");
				 DecimalFormat df=new DecimalFormat("000");
				 int codeNum=1;
				 String codeAll=code_sdf.format(nextDate(date_last_,0,count))+"-"+code_sdf.format(waterFlag.getEndTime());
				 double waterMoney=0;
				 double electricMoney=0;
				 double airMoney=0;
				 double propertyFee=0;
				 double netFee=0;
				 double sumSquare = 0;
				 int    sumHouse=0;
				 for(Expense e:expenses){
					 waterMoney+=e.getWaterMoney();
					 electricMoney+=e.getElectricMoney();
					 airMoney+=e.getAirMoney();
					 propertyFee+=e.getPropertyMoney();
					 netFee+=e.getNetMoney();
					 sumSquare+=e.getSumSquare();
					 sumHouse+=e.getSumHouse();
				 }
				 double sum=waterMoney+electricMoney+airMoney+propertyFee+netFee;
				 Expense ex_all=new Expense();
				 ex_all.setStartTime(nextDate(date_last_,0,1));
				 ex_all.setEndTime(waterFlag.getEndTime());
				 ex_all.setCode(codeAll);
				 ex_all.setAirMoney(Double.parseDouble(numFormat.format(airMoney)));
				 ex_all.setWaterMoney(Double.parseDouble(numFormat.format(waterMoney)));
				 ex_all.setElectricMoney(Double.parseDouble(numFormat.format(electricMoney)));
				 ex_all.setPropertyMoney(Double.parseDouble(numFormat.format(propertyFee)));
				 ex_all.setNetMoney(Double.parseDouble(numFormat.format(netFee)));
				 ex_all.setSumCustomer(expenses.size());
				 ex_all.setSumHouse(sumHouse);
				 ex_all.setSumSquare(Double.parseDouble(numFormat.format(sumSquare)));
				 ex_all.setSum(Double.parseDouble(numFormat.format(sum)));
				 this.expenseManager.storeExpense(ex_all);
				 Expense parentEP=this.expenseManager.loadExpensebyMaxId();//查询父类id
				 //保存子类水电费
				 for(Expense e:expenses){
					 String codeOne=code_sdf.format(nextDate(date_last_,0,count))+"-"+code_sdf.format(waterFlag.getEndTime())+"-"+df.format(codeNum);
					 e.setCode(codeOne);
					 e.setParentEP(parentEP);
					 this.expenseManager.storeExpense(e);
					 codeNum++;
					 //保存该子类水电费对应的收款单
					 ReturnPlan returnPlan =new ReturnPlan();
					 returnPlan.setCustomerInfo(e.getCustomerInfo());
					 returnPlan.setBatch((CodeValue) this.codeValueManager.loadByKey("code", "033301").get(0));//收款计划默认第一批
					 returnPlan.setPlanDate(e.getEndTime());
					 returnPlan.setSum(e.getSum());
					 returnPlan.setMold((CodeValue) this.codeValueManager.loadByKey("code", "23702").get(0));//收款类型为水电
					 //查找联系人
					 String [] keys={"name","customerName.id"};
					 Object [] values={e.getCustomerInfo().getKeyContacter(),e.getCustomerInfo().getId()}; 
					 List <ContactArchives> contactArchives=this.contactArchivesManager.loadByKeyArray(keys, values);
					 if(contactArchives!=null && contactArchives.size()>0){
						 returnPlan.setContactArchives(contactArchives.get(0));
					 }
					 returnPlan.setPlanState((CodeValue) this.codeValueManager.loadByKey("code", "21301").get(0));//收款状态为待收款
					 returnPlan.setPayment((CodeValue) this.codeValueManager.loadByKey("code", "04600").get(0));//收款方式为转账
					 returnPlan.setDisabled(false);
					 returnPlan.setIsBill("1");
					 returnPlan.setBillingOrNot("0");
					 returnPlan.setIsOrNot("1");
					 returnPlan.setHouse(map.get(e.getCustomerInfo().getId()));
					 this.returnPlanManager.storeReturnPlan(returnPlan);
				 }
				 } catch (DaoException e1) {
						e1.printStackTrace();
					}
			 }
			 return 0;//水电费新建成功
		 }else if(waterFlag==null&&eleFlag==null){
			 return 1;//水费或者电费不存在 
		 }else {
			return 2;//水费和电费结束时间不一致
		}
	}
	
	//当前时间加一天，加一个月
	public static Date nextDate(Date date,int month,int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
	/*//获取当前时间的下几个月1号
	public static Date nextMonthFirstDate(Date date,int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }*/
	//获取本月最后一天
	/*public static Date lastDay(int month){
		Calendar calendar = Calendar.getInstance();  
		calendar.add(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}*/

	
	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}
}
