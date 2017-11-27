<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('房源编辑')}">
<@ww.form name="'listForm'" namespace="'/productList'" action="'saveHouseList'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveHouseListToken"/>
	<#if houseList.id?exists>
		<@ww.hidden name="'houseList.id'" value="#{houseList.id?if_exists}"/>
	<#else>
		<@ww.hidden name="'houseList.id'" />
	</#if>
	
	<@ww.hidden id="contractManagement.id" name="'contractManagement.id'" value="#{contractManagement.id?if_exists}"/>
	<#if houseList.house?exists>
		<@ww.hidden id="house.id" name="'house.id'" value="#{houseList.house.id?if_exists}"/>
	<#else>
		<@ww.hidden id="house.id" name="'house.id'" value=""/>
	</#if>
	<@inputTable>
		<tr>
			<td align="right" valign="top">
	       		<label class="label"><font color="red">*</font>${action.getText('房间编码')}:</label>
	     	</td>
	     	<td>
	     		<#if houseList.house?exists>
		   			<input type="text" id="house.code" name="house.code" class="underline"  value="${houseList.house.code?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="house.code" name="house.code" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="houses_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@ww.textfield label="'${action.getText('房间名称')}'" id="house.name" name="'houseList.house.name'" cssClass="'underline'"  readonly="true"/>
			<@ww.textfield label="'${action.getText('房间面积')}'" maxlength="5"  id="house.square" name="'houseList.house.square'" cssClass="'underline'"  readonly="true"/>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('单价(元/平米月)')}'" id="house.price" name="'houseList.house.price'" cssClass="'underline'"  readonly="true"/>
			<@ww.textfield label="'${action.getText('房租(元/平米月)')}'" maxlength="5"  id="houseList.rent" name="'houseList.rent'" cssClass="'underline'" onchange="getDiscount()" required="true"/>
			<@ww.textfield label="'${action.getText('服务费(元/平米月)')}'" maxlength="5"  id="houseList.service" name="'houseList.service'" cssClass="'underline'" required="true" onchange="getDiscount()" required="true"/>
		</tr>
		<tr>
			<@ww.textfield id="houseList.price" label="'${action.getText('价格(元/月)')}'"  name="'houseList.price'" cssClass="'underline'"  readonly="true" onchange="getDiscount()" />
			<@ww.textfield id="discount" label="'${action.getText('折扣率(%)')}'" name="'houseList.discount'" cssClass="'underline'"   readonly="true" />
			<@ww.textfield id="houseList.sum" label="'${action.getText('总金额')}'" name="'houseList.sum'" cssClass="'underline'"  readonly="true" />
		</tr>
		<tr>	
			<@pp.datePicker 
				label="'${action.getText('开始时间')}'" 
				name="'houseList.startTime'" 
	   			value="'${(houseList.startTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				
			<@ww.textfield label="'${action.getText('月数')}'"  id="houseList.month"  name="'houseList.month'"  required="true" cssClass="'underline'" onblur="getEndMon()"/>
			<@textfield label="${action.getText('结束时间')}" maxlength="10" id="houseList.endTime" name="houseList.endTime"  value="${(houseList.endTime?string('yyyy-MM-dd'))?if_exists}"  required="false"  readonly="true" />
		</tr>
		<tr>
			<@textarea id="houseList.remark" name="houseList.remark"  label="${action.getText('说明')}" value="${houseList.remark?if_exists}" rows="4" />
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("houseList.remark").cols =width;
			</script>
		</tr>
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@vbutton class="button" value="${action.getText('close')}" onclick="closeAndRefresh();"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 

function getEndMon(){
	var startTime = getObjByName("houseList.startTime").value;
	var month = getObjByName("houseList.month").value;
	if(''!=month && ''!=startTime && !isNaN(month)){
		var date = addmulMonth(startTime,month);
		getObjByName("houseList.endTime").value = date.format("yyyy-MM-dd") ;
		return true;
	}else{
		getObjByName("houseList.endTime").value ='';
		getObjByName("houseList.sum").value ='';
		return false;
	}
}

function getSum(){
		var rent =getObjByName("houseList.rent").value;
		var service =getObjByName("houseList.service").value;
		var month =getObjByName("houseList.month").value;
		var square =getObjByName("house.square").value;
		if(''!=rent && ''!=service){
			getObjByName("houseList.price").value=toDecimal((parseFloat(service)+parseFloat(rent))*parseFloat(square));
		}
		if(''!=month && ''!=rent && ''!=service && !isNaN(month) && !isNaN(service) && !isNaN(rent)){
			getObjByName("houseList.sum").value =toDecimal( parseFloat(month)*(parseFloat(service)+parseFloat(rent))*square);
			return true;
		}else{
			getObjByName("houseList.sum").value ='';
			return false;
	}
}

function getDiscount(){
		var discount='';
		var rent =getObjByName("houseList.rent").value;
		var service =getObjByName("houseList.service").value;
		var price =getObjByName("house.price").value;
		if(!isDoubleNumber("houseList.rent")){
			//alert("${action.getText('房租不是数字')}");
			//getObjByName("houseList.rent").focus();
			return false;
		}
		if(!isDoubleNumber("houseList.service")){
			//alert("${action.getText('服务费不是数字')}");
			//getObjByName("houseList.service").focus();
			return false;
		}
		if(rent!=''&&service!=''&&price!=''){
			if(parseFloat(rent+service)==0||parseFloat(price)==0){
				discount="";
				getObjByName("discount").value=discount;
			}else{
				discount =(parseFloat(rent)+parseFloat(service))*100 /parseFloat(price);
				discount = discount.toFixed(2);
				getObjByName("discount").value=discount;
			}
		}
	}

document.onclick = function (){
	getEndMon();
	getDiscount();
	getSum();
}

	<#-- 提交验证-->
	function storeValidation(){
		if(getObjByName("house.id").value==""){
			alert("${action.getText('请选择房间')}");
		    return false;
		}
		
		var rent =getObjByName("houseList.rent").value;
		var service =getObjByName("houseList.service").value;
		var month =getObjByName("houseList.month").value;
		if(''==rent ){
			alert("${action.getText('房租不能为空')}");
			getObjByName("houseList.rent").focus();
			return false;
		}
		
		if(isNaN(rent)){
			alert("${action.getText('房租不是数字，请重新输入')}");
			getObjByName("houseList.rent").focus();
			return false;
		}
		if(''==service ){
			alert("${action.getText('综合服务费不能为空')}");
			getObjByName("houseList.service").focus();
			return false;
		}
		
		if(isNaN(service)){
			alert("${action.getText('综合服务费不是数字，请重新输入')}");
			getObjByName("houseList.service").focus();
			return false;
		}
					
		if(getObjByName("houseList.startTime").value==""){
			alert("${action.getText('请选择开始时间')}");
			getObjByName("houseList.startTime").focus();
			return false;
		}
		
		if(''==month ){
			alert("${action.getText('月份不能为空')}");
			getObjByName("houseList.sum").value ='';
			getObjByName("houseList.month").focus();
			return false;
		}
		
		if(isNaN(month)){
			alert("${action.getText('月份不是数字，请重新输入')}");
			getObjByName("houseList.sum").value ='';
			getObjByName("houseList.month").focus();
			return false;
		}
		
		if(!getEndMon()){
			return false;
		}
		if(!getSum()){
			return false;
		}
		return true;
	}
	//弹出房间查询模态窗体
	function houses_OpenDialog(){
	   var url = "${req.contextPath}/house/listHouseWindow.html?getRest=getRest&readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			getObjByName("house.id").value= result[0];
			getObjByName("house.code").value= result[1];
			getObjByName("house.name").value= result[2];
			getObjByName("house.price").value= result[3];
			getObjByName("house.square").value= result[4];
		}
	}
</script>

</@htmlPage>
