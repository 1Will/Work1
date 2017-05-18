/* $Id: hco2011.js 7937 2011-1-25 08:10:33Z hmguan $ */

var gw=800;
var gh=600;
function Config() {
	this.popW=800;
	this.popH=600;
}

var Config = new Config();

var inst_Id = "";
var dept_Id = "";
var m = "";
var is_new = "false";
///**采用dwr技术实现单位部门级联
//* 调用函数时候
//* instId 单位 下拉框名字 为 instId
//* deptId 部门 下拉框名字 为 deptId
//* msg 显示 所有 或 空
//* isNew 判断是 查询还是维护页面，返回值为boolean值
//* "true"查询，允许有失效的部门，"false"维护页面，不允许失效的部门
//* mfzhang 2009-11-5
//* modify by wliu 2009-12-23
//*/
function InstitutionSelectDeptDWR(instId,deptId,msg,isNew){
	inst_Id = instId;
	dept_Id = deptId;
	m = msg;
	is_new = isNew;
	var institutionId = getObjByName(inst_Id).value;
	if("-1" == institutionId){
		DWRUtil.removeAllOptions(dept_Id);
		getObjByName(dept_Id).options.add(new Option(m,-1));
	}else if(null != institutionId && "" != institutionId && "-1" != institutionId){
		InstitutionSelectDeptJs.InstitutionSelectDept(institutionId ,is_new , callBackOld);
	}

}
function callBackOld(data){
	if(null != data ){
	 	DWRUtil.removeAllOptions(dept_Id);
	 	getObjByName(dept_Id).options.add(new Option(m,-1));
		for(var i =0 ;i<data.length;i++){
//			if(i == 0){
//				getObjByName(dept_Id).options.add(new Option(m,-1));
//				//document.all(dept_Id).options[i].value = -1;
//				getObjByName(dept_Id).options.add(new Option(data[i][1],data[i][0]));
//			}else{
				getObjByName(dept_Id).options.add(new Option(data[i][1],data[i][0]));
//			}
		}	  
	}
}
//
//------------------------------------------------------------------------------
//
///**
//* GET DEPT BY INST IN USER
//* USE IN SEARCH OPERATE
//* MODIFY WLIU
//*/
//function DeptsByInstIdInUserDWR(instId,deptId,msg,flag){
//	inst_Id = instId;
//	dept_Id = deptId;
//	m = msg;
//	var institutionId = getObjByName(inst_Id).value;
//	DeptsByInstIdInUserJs.getDeptsByInstIdInUser(institutionId,flag,callBack);
//}
//function callBack(data){
//	if(""!= data ){
// 		DWRUtil.removeAllOptions(dept_Id);
//		getObjByName(dept_Id).options.add(new Option(m,0));
//		document.all(dept_Id).options[0].value = -1;
//		dwr.util.addOptions(dept_Id,data,"id","name");
//	}else{
//		getObjByName(dept_Id).options.add(new Option(m,0));
//		document.all(dept_Id).options[0].value = -1;
//	}
//}

//------------------------------------------------------------------------------

/**
 * @function 区域信息(国家，省份，城市)级联
 * @author wliu
 * @date 2009-12-11
 
 * @param child_Id		下级下拉列表标识
 * @param area_msg		下拉列表首行信息 
 * @param areaId		当前下拉列表值信息
 * @param current_id	标识当前下拉列表  
 * @param province_id	省份下拉列表名称
 * @param city_id		城市下拉列表名称
  
 * @param country		国家下拉列表名称参数
 * @param province		省份下拉列表名称参数
 * @param city			城市下拉列表名称参数
 * @param current		标识当前下拉列表名称参数
 * @param msg			下拉列表首行信息参数
 * @param flag			标识查询或编辑参数
 */
var child_Id = "";
var area_msg = "";
var areaId = "";
var current_id = "";
var province_id = "";
var city_id = "";
function areaCascadeDWR(country,province,city,current,msg,flag){
	area_msg = msg;
	current_id = current;
	province_id = province;
	city_id = city;
	if(flag == "edit"){
		if(current_id == 1){
			areaId = getObjByName(country).value;
			child_Id = province_id;
			if(areaId == ""){
				DWRUtil.removeAllOptions(province_id);
				DWRUtil.removeAllOptions(city_id);
			}else{
				AreaSelectJs.loadAreaKeyProperty(areaId, areaCallBack);
			}
		}else if(current_id == 2){
			areaId = getObjByName(province_id).value;
			child_Id = city_id;
			if(areaId == -1){
				DWRUtil.removeAllOptions(city_id);
			}else{
				AreaSelectJs.loadAreaKeyProperty(areaId, areaCallBack);
			}
		}
	}else if(flag == "search"){
		if(current_id == 1){
			areaId = getObjByName(country).value;
			child_Id = province_id;
			if(areaId == -1){
				DWRUtil.removeAllOptions(province_id);
				getObjByName(province_id).options.add(new Option(area_msg,-1));
				DWRUtil.removeAllOptions(city_id);
				getObjByName(city_id).options.add(new Option(area_msg,-1));
			}else{
				AreaSelectJs.loadAreaKeyProperty(areaId, areaCallBack);
			}
		}else if(current_id == 2){
			areaId = getObjByName(province_id).value;
			child_Id = city_id;
			if(areaId == -1){
				DWRUtil.removeAllOptions(city_id);
				getObjByName(city_id).options.add(new Option(area_msg,-1));
			}else{
				AreaSelectJs.loadAreaKeyProperty(areaId, areaCallBack);
			}
		}
	}
}

function areaCallBack(data){
	if("" == data){
		if(current_id == 1){
			DWRUtil.removeAllOptions(province_id);
			DWRUtil.removeAllOptions(city_id);
			getObjByName(province_id).options.add(new Option(area_msg,-1));
			getObjByName(city_id).options.add(new Option(area_msg,-1));
		}else if(current_id == 2){
			DWRUtil.removeAllOptions(city_id);
			getObjByName(city_id).options.add(new Option(area_msg,-1));
		}
		DWRUtil.removeAllOptions(child_Id);
		getObjByName(child_Id).options.add(new Option(area_msg,-1));
	}else if(null != data){
		if(data.length > 0){
			DWRUtil.removeAllOptions(child_Id);
			for(var i =0 ;i<data.length;i++){
				if(i == 0){
					getObjByName(child_Id).options.add(new Option(area_msg,-1));
					getObjByName(child_Id).options[i].value = -1;
					getObjByName(child_Id).options.add(new Option(data[i][1],data[i][0]));
				}else{
					getObjByName(child_Id).options.add(new Option(data[i][1],data[i][0]));
				}
			}
		}
	}
}
/**
 * @function 部门级联职位
 * @author wliu
 * @date 2010-02-06
 * @param deptId 部门下拉列表标识
 * @param dutyId 需要改变列表值的 职位下拉列表标识
 * @param orgId 当前组织标识
 * @param msg 下拉列表首行信息
 * @param flag 标识查询或者维护
 */
var duty_Dept_Id = "";
var duty_Id = "";
function DutyCascadeDWR(deptId,dutyId,orgId,msg,flag){
	duty_Dept_Id = deptId;
	duty_Id = dutyId;
	message = msg;
	var deparmentId = getObjByName(duty_Dept_Id).value;
	if(deparmentId != -1 && deparmentId != ""){
		DeptCascadeDutyJs.loadDutiesByDept(deparmentId,orgId,flag,dutyCallBack);
	}else{
		DWRUtil.removeAllOptions(duty_Id);
		getObjByName(duty_Id).options.add(new Option(message,-1));
		//document.all(duty_Id).options[0].value = -1;
	}
}
function dutyCallBack(data){
	if(""!= data ){
 		DWRUtil.removeAllOptions(duty_Id);
//		getObjByName(duty_Id).options.add(new Option(message,0));
//		document.all(duty_Id).options[0].value = -1;
//		dwr.util.addOptions(duty_Id,data,"id","name");
 		getObjByName(duty_Id).options.add(new Option(message,-1));
		for(var i =0 ;i<data.length;i++){
//			if(i == 0){
//				getObjByName(duty_Id).options.add(new Option(message,-1));
//				getObjByName(duty_Id).options[i].value = -1;
//				getObjByName(duty_Id).options.add(new Option(data[i][1],data[i][0]));
//			}else{
				getObjByName(duty_Id).options.add(new Option(data[i].name,data[i].id));
//			}
		}
		
	}else{
		DWRUtil.removeAllOptions(duty_Id);
		getObjByName(duty_Id).options.add(new Option(message,-1));
//		getObjByName(duty_Id).options[i].value = -1;
	}
}


var cm_Id = "";
var batch_Id = "";
var m = "";
var is_new = "false";
var sum ="";
var tSum="";
var toSum="";
var wSum="";
var trSum="";
///**采用dwr技术实现单位部门级联
//* 调用函数时候
//* instId 单位 下拉框名字 为 instId
//* deptId 部门 下拉框名字 为 deptId
//* msg 显示 所有 或 空
//* isNew 判断是 查询还是维护页面，返回值为boolean值
//* "true"查询，允许有失效的部门，"false"维护页面，不允许失效的部门
//* mfzhang 2009-11-5
//* modify by wliu 2009-12-23
//*/
function ContractManagementAndBatchDWR(cmId,batchId,sumReceivable,trueSum,totalSum,withoutGotSum,sums,msg,isNew){
	cm_Id = cmId;
	batch_Id = batchId;
	m = msg;
	sum=sumReceivable;
	tSum=trueSum;
	toSum=totalSum;
	wSum=withoutGotSum;
	is_new = isNew;
	trSum=sums;
	var contractManagementId = getObjByName(cm_Id).value;
	var batchsId = getObjByName(batch_Id).value;
	if(null != contractManagementId && "" != contractManagementId && null != batchsId && "" != batchsId){
		ContractManagementAndBatchJs.contractManagementAndBatch(contractManagementId+"",batchsId+"",isNew,callBackCm);
	}
}
function callBackCm(data){
	if(null != data && data.size()>0){
		getObjByName(sum).value=data[0][0];
		getObjByName(tSum).value=data[0][0];
		getObjByName(toSum).value=data[0][1];
		getObjByName(wSum).value=data[0][2];
	}else{
		getObjByName(sum).value=0.0;
		getObjByName(tSum).value=0.0;
	}
	
}

var cn_id="";
var bh_id="";
//由合同的id级联出回款计划的批次
function ContractAndBatchDWR(cmId,batchId,msg,isNew){
	cn_id = cmId;
	bh_id = batchId;
	m = msg;
	is_new = isNew;
	var contractManagementId = getObjByName(cn_id).value;
	if(null != contractManagementId && "" != contractManagementId ){
		ContractAndBatchJs.contractAndBatch(contractManagementId,is_new , bacthCallBack);
	}

}

function bacthCallBack(data){
	if(null != data ){
	 	DWRUtil.removeAllOptions(bh_id);
		for(var i =0 ;i<data.length;i++){
			if(i == 0){
				getObjByName(bh_id).options.add(new Option(m,-1));
				document.all(bh_id).options[i].value = -1;
				getObjByName(bh_id).options.add(new Option(data[0][1],data[0][0]));
			}else{
				getObjByName(bh_id).options.add(new Option(data[i][1],data[i][0]));
			}
		}	  
	}
}

var cd_id="";
var sum_value="";
var div="";
//验证预付款金额
function checkSumDWR(cmd,sum,id,msg,isNew){
	cd_id=cmd;
	sum_value=sum;
	div =id;
	var contractManagementId = getObjByName(cd_id).value;
	var sums = getObjByName(sum).value;
	if(null != contractManagementId && "" != contractManagementId ){
		CheckSumJs.checkSum(contractManagementId,sums,is_new,callBackSum);
	}
}
function callBackSum(data){
	if(null != data && data.size()>0){
		var a =data[0];
		if(a =='unuser'){
			alert("应付金额大于总金额")
			getObjByName(sum_value).value="";
			getObjByName(sum_value).focus();
			return false;
		}
	}
	
}

//验证登录名称是否重复 2011-05-09 add by hmguan
function  loginNameRepeat(code,loginName)
{
    var value; 
    DWREngine.setAsync(false);   
       loginNameRepeatJs.loginNameByCode(code,loginName,function callBackTest(isValid)
       {
         if(!isValid)
         {
           value=false;
           alert("用户名已存在,请重新输入!");
         }
         else
         {
          value=true;
         }
       });
       DWREngine.setAsync(true); 
       return value;
}