/* $Id: eam2008.js 7937 2007-10-22 08:10:33Z qsun $ */

var gw=1024;
var gh=900;
//用于申购单的变量
var sw=800;
var sh=600;
//所有转换UTF-8编码
var all = "\u6240\u6709";
function Config() {
	this.popW=1024;
	this.popH=900;
}

function Config1() {
	this.sw=1200;
	this.sh=800;
}

var Config = new Config();

//DWR全局的错误处理信息
function errorHandler_global(errorString,exception){ 
	alert(exception.message + "\n您调用的方法出现错误了！请检查配置或方法的编写！"); 
} 
DWREngine.setErrorHandler(errorHandler_global);

/***库位选择***/
var Select = function() {}
Select.setup = function(params) {
  openDialog(params["currentRowNum"]);
  /*
   * location select
   * loopNum : row num of list
  */
  function openDialog(loopNum){
    document.getElementById(params["currentRowNumHiddenName"]).value = loopNum;
    //request modelDialog page
    var url=params["url"];
    popupModalDialog(url, Config.popW, Config.popH, refresh_information);
  }
  // callback function
  function refresh_information(result) {
    var allLoctionId = document.getElementsByName(params["inputHiddenName"]);
    var allLocationName = document.getElementsByName(params["inputFieldName"]);
    var currentRowNum = document.getElementById(params["currentRowNumHiddenName"]).value;
    if (null != result) {
      allLoctionId[currentRowNum].value = result[0];
      allLocationName[currentRowNum].value = result[1];
    }
  } 
}

/*
* 工种类别弹出页面选择
*/
function eam2008_workType_OpenDialog(url) {
	popupModalDialog(url, gw, gh, refresh_workType_information);  
}
function refresh_workType_information(result) {
  	if (result) {
  		getObjByName('workType.code').value = result[1];
  		getObjByName('workType.name').value = result[2];
  		getObjByName('workType.unitPrice').value = result[3];
  		getObjByName('workType.id').value = result[0];
  	}
}
/*
 * 备件弹出页面选择  add by smzhang 2008-11-05
*/
function eam2008_spare_OpenDialog(url,backCallFunctionName,anotherParam){
    url = url + '&multi=multiSelect';
    if (null != anotherParam) {
      url = url + '&addSpareOutBillIds=' + anotherParam;
    }
	popupModalDialog(url, gw, gh, backCallFunctionName);
}
/*
 * 设备弹出页面选择
*/
function eam2008_device_OpenDialog(url,flag) {
	if (null != flag) {
    	url = url + '?flag=' + flag;
    }
	popupModalDialog(url, gw, gh, refresh_device_information);
}
/*
* 	设备领用 设备弹出页面
*/
function eam2008_deviceBorrow_OpenDialog(url,flag,oldDeviceIds) {
	url = url + '?flag=' +flag + '&oldDeviceIds=' +oldDeviceIds;
	popupModalDialog(url, gw, gh, refresh_device_information);
}

function refresh_device_information (result) {
  if (null != result) {
    getObjByName('device.name').value = result[1]; 
    getObjByName('device.deviceNo').value = result[2]; 
    if (null != getObjByName('device.department.name')) {
    	getObjByName('device.department.name').value = result[3]; 
    }
    if ( null != document.getElementById("department.id")) {
      getObjByName('department.id').value = result[9];
    }
    getObjByName('device.id').value = result[0];
    if (null != getObjByName('device.assetNo')) {
      getObjByName('device.assetNo').value = result[4];
    } 
    if (null != getObjByName('device.model')) {
      getObjByName('device.model').value = result[5];
    }
    if (null != getObjByName('device.specification')) {
      getObjByName('device.specification').value = result[6];
    }  
    if (null != getObjByName('device.category')) {
      getObjByName('device.category').value = result[7];
    }
    if (null != getObjByName('deviceCategory')) {
      getObjByName('deviceCategory').value = result[7];
    }
    if (null != getObjByName('device.supplierName')) {
      getObjByName('device.supplierName').value = result[8];
    } 
  }
}
function eam2008_device_validate(msg) {
	if (getObjByName('device.id').value=='') {
		alert(msg);
		return false;
	}
    return true;
}

/*
 * 工装弹出页面选择
*/
function eam2008_tooling_OpenDialog(url,flag) {
    url = url + '?hiddenCheckBox=true';                     //hiddenCheckBox  用来失效的checkbox
    if (null != flag) {
    	url = url + '&flag=' + flag;
    }
    //alert(url);
	popupModalDialog(url, gw, gh, refresh_tooling_information);
}

function eam2008_multi_select_tooling_OpenDialog(url,flag) {
    url = url + '?hiddenCheckBox=true&multi=multiSelect';                     //hiddenCheckBox  用来失效的checkbox
	if (null != flag) {
    	url = url + '&flag=' + flag;
    }
	popupModalDialog(url, gw, gh, refresh_multi_tooling_information);
}

function eam2008_multi_select_device_OpenDialog(url,backCallFunctionName,anotherParam,flag) {   //触发多选设备
    url = url + '?multi=multiSelect';
    if (null != flag) {
    	url = url + '&flag=' + flag;
    }
    if (null != anotherParam) {
      url = url + '&oldDeviceIds=' + anotherParam;
    }
	popupModalDialog(url, gw, gh, backCallFunctionName);
}

function refresh_tooling_information (result) {
  if (null != result) {
    getObjByName('tooling.name').value = result[1]; 
    getObjByName('tooling.deviceNo').value = result[2]; 
    getObjByName('tooling.graphNo').value = result[3]; 
    getObjByName('tooling.id').value = result[0]; 
    if ( null != getObjByName('tooling.department')) {
      getObjByName('tooling.department').value = result[4];
    }
   if ( null != getObjByName('department.id')) {
      getObjByName('department.id').value = result[11];
    }
    if (null != getObjByName('tooling.totalOutput')) {
      getObjByName('tooling.totalOutput').value = result[5];
    }
    if (null != getObjByName('tooling.usedQuota')) {
      getObjByName('tooling.usedQuota').value = result[6];
    }
    if (null != getObjByName('tooling.toolingType')) {
      getObjByName('tooling.toolingType').value = result[7];
    }
    if (null != getObjByName('tooling.usedDate')) {
      getObjByName('tooling.usedDate').value = result[8];
    }
    if (null != getObjByName('tooling.model')) {
      getObjByName('tooling.model').value = result[9];
    }
    if (null != getObjByName('tooling.specification')) {
      getObjByName('tooling.specification').value = result[10];
    }
  }
}

function eam2008_tooling_validate(msg) {
	if (getObjByName('tooling.id').value=='') {
		alert(msg);
		return false;
	}
    return true;
}

/*
 * 润滑油弹出页面选择
*/
function eam2008_lubricationOil_OpenDialog(url) {
    popupModalDialog(url, gw, gh, refresh_lubricationOil_information);
}

function refresh_lubricationOil_information(result) {
	if (null != result) {
    	getObjByName('lubricationOil.code').value = result[1]; 
    	getObjByName('lubricationOil.name').value = result[2]; 
    	getObjByName('lubricationOil.measureUnit').value = result[3]; 
    	getObjByName('lubricationOil.id').value= result[0];
	}
}

/*
 * 供应商弹出页面选择
*/
function eam2008_supplier_OpenDialog(url) {
	popupModalDialog(url, gw, gh, refresh_supplier_information);
}
function refresh_supplier_information (result) {
   getObjByName('supplier.id').value = result[0]; 
   getObjByName('supplier.name').value = result[1]; 
   getObjByName('supplier.supplierNo').value = result[2];
   if(null!=getObjByName('supplier.supplierName')){
       getObjByName('supplier.supplierName').value = result[3];
   }
   if(null!=getObjByName('supplier.telphone')){
       getObjByName('supplier.telphone').value = result[4];
   }
}

/*
 * 预算明细弹出页面选择
*/
function eam2008_budgetDetail_OpenDialog(url) {
	popupModalDialog(url, gw, gh, refresh_budgetDetail_information);
}
function refresh_budgetDetail_information(result) {
  if (result) {
    getObjByName('budgetDetail.id').value = result[0];
    getObjByName('budgetDetail.budgetNo').value = result[1];
    getObjByName('budgetDetail.name').value = result[2];
  }
}
function eam2008_budgetDetail_sourceType_validate(msg) {
	if (getObjByName('sourceType').value=='') {
		alert(msg);
		return false;
	}
    return true;
}
function eam2008_budgetDetail_validate(msg) {
	if (getObjByName('sourceType').value=='IN_BUDGET') {
	  if ('' == getObjByName('budgetDetail.id').value) {
		alert(msg);
		return false;
	  }
	}
    return true;
}

function eam2008_hiddenButtonElements(form, excepts) 
{
   var elements = form.elements; 
   for (var i = 0; i < elements.length; i ++) {
      if (elements[i].type.toUpperCase() == "BUTTON" || elements[i].type.toUpperCase() == "SUBMIT" ) {
         if (__priv_containE(excepts, elements[i].name)) {
            elements[i].style.display = "inline"; 
            continue; 
         }
         elements[i].style.display = "none"; 
      }
   }
}

function eam2008_setWFApproversInfo(ary, form, finalAprId, handler) {
   var a = ary.split("#"); 
   if (ary.length == 0) {
      return ; 
   }
   if (a.length != 0) {
      var _ary = new Array(); 
      for (var i = a.length - 1; i >= 0; i--) {
         if ('' == a[i]) {
            continue; 
         }
         var x = a[i].split(":"); 
         if (x[1] == 'F') {
            selector = form.elements[finalAprId]; 
            var groups = selector.options.length; 
            for (var j = 0; j < groups; j++) {
               if (selector.options[j].value == x[0]) {
                  selector.options[j].selected = "true"; 
               }
            }
            continue; 
         }
         else {
            var tmpAry = new Array(x[0], x[1]); 
            _ary.push(tmpAry); 
         }
      }
      _ary.reverse(); 
      handler(_ary); 
  }
}
//申购单弹出页面

function choose_subscribeBillDetail(url){
    Config1();
	popupModalDialog(url, sw, sh, choose_subscribeBill_Detail_information);
}
function choose_quarterPlan(url){
	popupModalDialog(url, gw, gh, choose_quarter_Plan_information);
}
	
function choose_uauslCheck(url){
	popupModalDialog(url, gw, gh, choose_uauslCheck_information);
}

function choose_brockenRate(url){
	popupModalDialog(url, gw, gh, choose_brockenRate_information);
}
function choose_easilydamagedSpart(url){
	popupModalDialog(url, gw, gh, choose_easilydamagedSpart_information);
}
//-----------用Ajax实现分公司联动部门 js代码------------------------------------
//全局变量  
var req=null;   

//分公司下拉框改变时出发此函数
function filialeSelectDept()   
{  
    var filiale_id=$F("filiale.id"); 
    if(filiale_id==''){
   	filiale_id = '0';
    }
//将 项目路径写死为 eam2008 函数将该为 function filialeSelectDept() 不需要传参
    var url="/eam2008/base/department/filialeSelectDept.html?filiale_id="+escape(filiale_id);
//将 contextPath 传入进来 函数将改为 function filialeSelectDept(contextPath) 需要传参
 //	var url = contextPath+"/base/department/filialeSelectDept.html?filiale_id="+escape(filiale_id);
  
    //创建 window.XMLHttpRequest对象
    if(window.XMLHttpRequest)   
    {   
        req=new XMLHttpRequest();   
    }else if(window.ActiveXObject)   
    {   
        req=new ActiveXObject("Microsoft.XMLHTTP");   
    }   
       
    if(req)   
    {   //发送请求
        req.open("GET",url,true);  
        req.onreadystatechange=callback;   
        req.send(null);   
    }   
}   
//回调函数，根据请求的状态进行相应的处理   
function callback()   
{   
   if (req){
	    if(req.readyState == 4)   
	    {  
	        if(req.status == 200)   
	        {  
	        	 //请求发送成功，正常响应
	            parseMessage();   
	        }else{   
	            alert("Not able to retrieve description"+req.statusText);   
	        }   
	    }   
	}
}   
//对xml数据进行读取   
function parseMessage()   
{  
    var xmlDoc=req.responseXML.documentElement;   
    var xSel=xmlDoc.getElementsByTagName('select');  
    var select_root=getObjByName("department.id");  
    select_root.options.length=0; 
    for(var i=0;i<xSel.length;i++)   
    {  
        var xValue=xSel[i].childNodes[0].firstChild.nodeValue;   
        var xText=xSel[i].childNodes[1].firstChild.nodeValue;   
        var option=new Option(xText,xValue);  
        try{   
            select_root.add(option);   
        }catch(e){   
        }   
    }   
}   


/**采用dwr技术实现分公司联动
* 调用函数时候
* 分公司 下拉框名字 为 filiale.id
* 部门   下拉框名字 为 department.id	
*/
function filialeSelectDeptDWR(){
	var filialeId = getObjByName('filiale.id').value;
		//alert("filialeId" + filialeId);
	FilialeSelectDeptJs.filialeSelectDept(filialeId , callBack);
}
function callBack(data){
	if(null != data ){
	 	removeSelectOptions("department.id");
	 	//alert(data.length);
		for(var i =0 ;i<data.length;i++){
			if(i == 0){
				getObjByName("department.id").options.add(new Option(all,0));
				document.all("department.id").options[i].value = -1;
			}else{
				getObjByName("department.id").options.add(new Option(data[i][1],data[i][0]));
				//alert( " " + i + "ge " + data[0][0]);
			}
		}	  
	}
}

/**
*
*/
function filialeSelectDeptEditDWR(){
	var filialeId = getObjByName('filiale.id').value;
	FilialeSelectDeptJs.filialeSelectDept(filialeId , callBackEdit);
}
function callBackEdit(data){
	if(null != data ){
	 	removeSelectOptions("department.id");
		for(var i =0 ;i<data.length;i++){
			if(i == 0){
				getObjByName("department.id").options.add(new Option("",0));
				document.all("department.id").options[i].value = -1;
			}else{
				getObjByName("department.id").options.add(new Option(data[i][1],data[i][0]));
			}
		}	  
	}
}
/**
 * @function 仓库级联库区
 * @author wliu
 * @date 2010-03-25
 * @param warehouseId 仓库下拉列表标识
 * @param regionalId 需要改变列表值的 库区下拉列表标识
 * @param msg 下拉列表首行信息
 * @param flag 标识查询或者维护
 */
var warehouse_Id = "";
var regional_Id = "";
var message = "";
function WareCascadeRegionalDWR(warehouseId,regionalId,msg,flag){
	warehouse_Id = warehouseId;
	regional_Id = regionalId;
	message = msg;
	var whId = getObjByName(warehouse_Id).value;
	if(whId != -1 && whId != ""){
		WareCascadeRegionalJs.loadRegionalByWareId(whId,flag,WRCallBack);
	}else{
		removeSelectOptions(regional_Id);
		getObjByName(regional_Id).options.add(new Option(message,-1));
		getObjByName(regional_Id).options[0].value = -1;
	}
}
function WRCallBack(data){
	if(null!= data ){
	  //alert(data);
 		//removeSelectOptions(regional_Id);
		//getObjByName(regional_Id).options.add(new Option(message,0));
		//document.all(regional_Id).options[0].value = -1;
		//dwr.util.addOptions(regional_Id,data,"id","name");
		for(var i=0;i<data.length;i++){
		  var bean = data[i];
	 	  if(i == 0){
	 	    removeSelectOptions(regional_Id);
			getObjByName(regional_Id).options.add(new Option(message,-1));
			getObjByName(regional_Id).options.add(new Option(bean.name,bean.id));
		  }else{
			getObjByName(regional_Id).options.add(new Option(bean.name,bean.id));
		 }
		  
		}
	}else{
		removeSelectOptions(regional_Id);
	}
}

/**
 * @function 库区级联库位
 * @author zzb
 * @date 2010-12-23
 * @param regionalId 需要改变列表值的 库区下拉列表标识
 * @param locationId 库位下拉列表标识
 * @param msg 下拉列表首行信息
 * 
 */
var warehouse_Id = "";
var regional_Id = "";
var message = "";
function WareCascadeLocationDWR(regionalId,locationId,msg){
	regional_Id = regionalId;
	location_Id = locationId;
	message = msg;
	var regId = getObjByName(regional_Id).value;
	if(regId != -1 && regId != ""){
		findLocationByRegionalJs.findLocationByRegional(regId,LocationCallBack);
	}else{
		removeSelectOptions(regional_Id);
		getObjByName(location_Id).options.add(new Option(message,0));
		getObjByName(location_Id).options[0].value = -1;
	}
}
function LocationCallBack(data){
	if(null != data){
		if(data.length > 0){
			removeSelectOptions(location_Id);
			for(var i =0 ;i<data.length;i++){
				var bean = data[i];
				if(i == 0){
				    getObjByName(location_Id).options.add(new Option(message,""));
					getObjByName(location_Id).options.add(new Option(bean[1],bean[0]));
					getObjByName(location_Id).options.add(new Option(bean.code,bean.id));
					 
				}else{
					getObjByName(location_Id).options.add(new Option(bean[1],bean[0]));
				}
			}
		}
		else{
			removeSelectOptions(location_Id);
			getObjByName(location_Id).options.add(new Option(message,0));
		    getObjByName(location_Id).options[0].value = -1;
		}
	}
}


/**
 * @function 申购单明细 种类级联明细种类
 * @author jyang
 * @date 2010-10-15
 * @param spare_Type_Id 种类ID
 * @param spare_Detail_Type 明细种类ID
 * @param msg 下拉列表首行信息
 */
 var spare_Type_Id = "";
 var spare_Detail_Type = "";
 var messgae = "";
function spareTypeCascadeDWR(spareTypeId,spareDetailType,msg){
	spare_Type_Id = getObjByName(spareTypeId).value;
	spare_Detail_Type = spareDetailType;
	messgae=msg;
	if(spare_Type_Id == -1 || spare_Type_Id == ""){
		removeSelectOptions(spare_Detail_Type);
		getObjByName(spare_Detail_Type).options.add(new Option(messgae,""));
	}else{
		SpareDetailSelectJs.loadSpareDetailBySpareTypeId(spare_Type_Id, spareTypeCallBack);
	}
}

/**
 * 申购单明细 种类级联明细种类 回调函数
 */
function spareTypeCallBack(data){

	if(null != data){
		if(data.length > 0){
			removeSelectOptions(spare_Detail_Type);
			for(var i =0 ;i<data.length;i++){
				if(i == 0){
					getObjByName(spare_Detail_Type).options.add(new Option(messgae,""));
					getObjByName(spare_Detail_Type).options.add(new Option(data[i][1],data[i][0]));
				}else{
					getObjByName(spare_Detail_Type).options.add(new Option(data[i][1],data[i][0]));
				}
			}
		}
		else{
			removeSelectOptions(spare_Detail_Type);
		}
	}
}



/**
 * @function 仓库级别 仓库
 * @author jyang
 * @date 2010-10-15
 * @param storage_grade_Id 仓库级别ID
 * @param wareHouse_Id 仓库ID
 * @param msg 下拉列表首行信息
 */
 var storage_grade_Id = "";
 var wareHouse_Id = "";
 var message = "";
function wareHouseCascadeDWR(storageGradeId,wareHouseId,userId,msg,isSearch){
	storage_grade_Id = getObjByName(storageGradeId).value;
	wareHouse_Id = wareHouseId;
	message=msg;
	if(storage_grade_Id == -1 || storage_grade_Id == ""){
		removeSelectOptions(wareHouse_Id);
		if(isSearch=='true') {
			getObjByName(wareHouse_Id).options.add(new Option(message,"-1"));
		} else {
			getObjByName(wareHouse_Id).options.add(new Option(message,""));
		}
	}else{
		if (isSearch=='true'){
			findWareHouseJs.loadWareHouseByUser(storage_grade_Id,userId,wareHouseBySearchCallBack);
		} else {
			findWareHouseJs.loadWareHouseByUser(storage_grade_Id,userId,wareHouseCallBack);
		}
	}
}

/**
 * 仓库级别 仓库 回调函数
 */
function wareHouseCallBack(data){

	if(null != data){
		if(data.length > 0){
			removeSelectOptions(wareHouse_Id);
			for(var i =0 ;i<data.length;i++){
				var bean = data[i];
				if(i == 0){
					getObjByName(wareHouse_Id).options.add(new Option(message,''));
					getObjByName(wareHouse_Id).options.add(new Option(bean.name,bean.id));
				}else{
					getObjByName(wareHouse_Id).options.add(new Option(bean.name,bean.id));
				}
			}
		}
		else{
			removeSelectOptions(wareHouse_Id);
		}
	}
}
/**
 * 仓库级别 仓库 回调函数,用在查询页面上
 */
function wareHouseBySearchCallBack(data){

	if(null != data){
		if(data.length > 0){
			removeSelectOptions(wareHouse_Id);
			for(var i =0 ;i<data.length;i++){
				var bean = data[i];
				if(i == 0){
					getObjByName(wareHouse_Id).options.add(new Option(message,"-1"));
					getObjByName(wareHouse_Id).options.add(new Option(bean.name,bean.id));
				}else{
					getObjByName(wareHouse_Id).options.add(new Option(bean.name,bean.id));
				}
			}
		}
		else{
			removeSelectOptions(wareHouse_Id);
		}
	}
}





/*
 * 生产厂家弹出页面选择
*/
function eam2008_factory_OpenDialog(url) {
	popupModalDialog(url, gw, gh, refresh_factory_information);
}
function refresh_factory_information (result) {
  if (null != result) {
    document.forms[0].elements["factory.supplierNo"].value = result[2]; 
    document.forms[0].elements["factory.name"].value = result[1]; 
    document.forms[0].elements["factory.id"].value = result[0]; 
  }
}


/*
 * 设备厂家弹出页面选择
*/
function eam2008_equfactory_OpenDialog(url) {
	popupModalDialog(url, gw, gh, refresh_equfactory_information);
}
function refresh_equfactory_information (result) {
  if (null != result) {
    document.forms[0].elements["equFactory.supplierNo"].value = result[2]; 
    document.forms[0].elements["equFactory.name"].value = result[1]; 
    document.forms[0].elements["equFactory.id"].value = result[0]; 
  }
}

function removeSelectOptions(tabName){
	getObjByName(tabName).options.length=0;
	
}
