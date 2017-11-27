<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('空调费详细维护页面')}">
<@ww.form name="'listForm'" action="'saveAirFee'" method="'post'">
	<@ww.token name="saveAirFeeActionToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'airFee.id'" value="'${airFee.id?if_exists}'"/>	
	<@ww.hidden name="'airFee.isSaved'" value="''"/>
	<@inputTable>
		<tr>
			<@textfield label="${action.getText('编码')}"  id="airFee.code"  name="airFee.code" value="${airFee.code?if_exists}"  disabled="true"  />
			<@textfield  label="${action.getText('空调费名称')}" maxlength="15"  name="airFee.airFeeName" value="${airFee.airFeeName?if_exists}" />
			<@ww.select label="'${action.getText('大楼')}'" 
					id="building.id" 
					name="'building.id'" 
					value="${req.getParameter('building.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allBuilding"
					required="true"
					emptyOption="false" 
					disabled="false"/>
				<#if airFee.id?exists>
					<script language="javascript">
						<#if airFee.building?exists>
							getObjByName('building.id').value="${airFee.building.id?if_exists}";
						</#if>
						getObjByName('building.id').disabled=true;
					</script>
				</#if>
		</tr>
		<tr>
			<@pp.datePicker 
				label="'${action.getText('开始时间')}'" 
				name="'starTime'" 
				value="'${(airFee.starTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				
			<@pp.datePicker 
				label="'${action.getText('结束时间')}'" 
				name="'endTime'" 
				value="'${(airFee.endTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<@textfield label="${action.getText('开放天数')}" maxlength="5" id="airFee.openDays"  name="airFee.openDays" value="${airFee.openDays?if_exists}"  required="true" />
		</tr>
			
	</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
			
			<#if airFee.isSaved?exists &&airFee.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
		    
			<#if airFee.id?exists>
				<@vbutton name="button" class ="button" value="${action.getText('计算空调费')}" onclick="calculatee(#{airFee.id?if_exists})"/>
			</#if>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/fee/listAirFee.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/AirFee.js'></script>
<script  type="text/JavaScript"> 
	<#-- 提交验证-->
	function storeValidation(){
		if(getObjByName("airFee.starTime").value==""){
			alert("${action.getText('请选择开始时间!')}");
			getObjByName("airFee.starTime").focus();
			return false;
		}
		if(getObjByName("airFee.endTime").value==""){
			alert("${action.getText('请选择结束时间!')}");
			getObjByName("airFee.endTime").focus();
			return false;
		}
		return true;
	}
	
		//提交
	function submitt(){
		getObjByName("airFee.isSaved").value = 1;
		return storeValidation();
	}
	//保存
	function savee(){
		getObjByName("airFee.isSaved").value = 0;
		return storeValidation();
	}
	
	function calculatee(id){
		var flag = confirm("所有数据填写完整，将开始计算公摊");
		if(flag){
			DWREngine.setAsync(false);
			AirFee.calculate(id,pageRefresh);
			DWREngine.setAsync(true); 
		}
	}
	
	function pageRefresh(data){
		if(data=='success'){
			alert("计算成功");
		}
		if(data=='error'){
			alert("计算错误");
		}
		location.reload();
	}
</script> 
</@htmlPage>

<#if airFee.id?exists>
<ul id="beautytab">
	<li>
		<a id="airHouseFee" onclick="activeTab(this);"  href='${req.contextPath}/fee/listAirHouseFee.html?airFee.id=#{airFee.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('房间空调费详细')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/fee/listAirHouseFee.html?airFee.id=#{airFee.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>