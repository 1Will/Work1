/* $Id: eam2008.js 7937 2007-10-22 08:10:33Z qsun $ */

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
		for(var i =0 ;i<data.length;i++){
			if(i == 0){
				getObjByName(dept_Id).options.add(new Option(m,-1));
				document.all(dept_Id).options[i].value = -1;
			}else{
				getObjByName(dept_Id).options.add(new Option(data[i][1],data[i][0]));
			}
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
					document.all(child_Id).options[i].value = -1;
					getObjByName(child_Id).options.add(new Option(data[i][1],data[i][0]));
				}else{
					getObjByName(child_Id).options.add(new Option(data[i][1],data[i][0]));
				}
			}
		}
	}
}

/**
   根据客户姓名进行客户姓名自动补全
   @param name 标签的标识
   @param value 标签的值
*/
function showCustomer(name,value) {
    var _e = window.event;
   
  //  if ((_e.type=='keyup')&&(_e.keyCode==13)){  //回车
  //  alert("==========");
        if ("" == value) {                      
            value = dwr.util.getValue('name').Trim();
        }
        if ("" == value){
            return false;
        }
        CustomerList.loadCustomersByName(value,function(data){
                // DWRUtil.removeAllOptions('customerBox');
                // var _arry=data.rsList;
                  DWRUtil.removeAllOptions('slistBox');
                for (i=0; i<data.length;i++){
               // alert(data[i][0]+data[i][1]);
                getObjByName('slistBox').options.add(new Option(data[i][1],data[i][0]));
                }
                
                  //DWRUtil.addOptions('slistBox',data,'value','label');
                   document.all.slistBox.size=data.length==0?5:data.length;
          			 showT(name);     
          			// alert(name);       
            }
        
        )
   // }
}
	function showT(name)
	{
		var o=name;
		var m=document.getElementById('tipBox')
		m.style.display='block';
		m.style.left=getL(o) //====设置的是left属性
		//而且注意需要document.getElementById
		m.style.top=getT(o)+document.getElementById(o).offsetHeight //====设置的是top属性
		m.style.width=document.getElementById(o).offsetWidth+2;
		var _slist=document.getElementById("slistBox");
		_slist.style.width=m.style.width;
		_slist.selectedIndex=0;
		m.style.visibility=''
		_slist.focus();
	 }
	 	function getL(id){ 
		var e=document.getElementById(id);
		var l=e.offsetLeft; 
		while(e=e.offsetParent){ 
		l+=e.offsetLeft; 
	} 
	return l 
	} 
	
	function getT(id){ 
	var e=document.getElementById(id);
	var t=e.offsetTop; 
	while(e=e.offsetParent){ 
	t+=e.offsetTop; 
	} 
	return t 
	} 
	function keySetV()
{
var _e = window.event;
if (((_e.type=='keyup')&&(_e.keyCode==13))||(_e.type=='click'))  //回车
  { 
  var _value=dwr.util.getValue('slistBox');
  var _text=dwr.util.getText('slistBox');
  setCodeAndName(_text,_value);
  hide();
  }
}
function setCodeAndName(_text,_value)
{
  //dwr.util.setValue('tname',_text);
 // dwr.util.setValue('tcode',_value);
}
	function hide(){
		document.getElementById("tipBox").style.visibility='hidden' 
	} 