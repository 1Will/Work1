<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('电费详细维护页面')}">
<@ww.form name="'listForm'" action="'saveElectricFee'" method="'post'">
	<@ww.token name="saveElectricFeeActionToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'electricFee.isSaved'" value="''"/>
	<#if electricFee.id?exists>
		<@ww.hidden name="'electricFee.id'" value="#{electricFee.id?if_exists}"/>
		<#if electricFee.building?exists>
			<#if electricFee.building.aeMeter?exists>
				<@ww.hidden name="'aRate'" value="#{electricFee.building.aeMeter.rate?if_exists}"/>
			</#if>
			<#if electricFee.building.beMeter?exists>
				<@ww.hidden name="'bRate'" value="#{electricFee.building.beMeter.rate?if_exists}"/>
			</#if>
		</#if>
	<#else>
		<@ww.hidden name="'electricFee.id'" value=""/>
		<@ww.hidden name="'aRate'" value=""/>
		<@ww.hidden name="'bRate'" value=""/>
	</#if>
	
	<@inputTable>
			<tr>
				<td align="right" valign="top">
		       		<#--<span class="required">*</span>-->
		       		<label class="label">${action.getText('电费编码')}:</label>
		     	</td>
		     	<td>
					<input type="text" disabled="true" id="electricFee.code" name="electricFee.code" class="underline"  value="${electricFee.code?if_exists}" maxlength="140" size="20"/>
				</td>
				
				<@textfield  label="${action.getText('电费名称')}" maxlength="15"  name="electricFee.electricFeeName" value="${electricFee.electricFeeName?if_exists}"    />
				
			</tr>
			<tr>
				<#-- 是否为总电量 -->
				<td align="right">
					<label for="" class="label">${action.getText('是否为总电量')}:</label>
				</td>
				<td align="left">
		        	<input type="radio" id="yesAllBuilding" name="electricFee.isAllBuilding" value="true" />是
		        	<input type="radio" id="noAllBuilding" name="electricFee.isAllBuilding" value="false" />否
				</td>
				<script language="javascript">
				<#if electricFee.id?exists>
					<#if electricFee.isAllBuilding>
							getObjByName("yesAllBuilding").checked = true;
					<#else>
							getObjByName("noAllBuilding").checked = true;
					</#if>
				<#else>
						getObjByName("noAllBuilding").checked = true;
				</#if>
			</script>
			
				<@ww.select label="'${action.getText('大楼')}'"  
					id="building.id" 
					name="'building.id'" 
					value="${req.getParameter('building.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allBuilding"
					required="true"
					emptyOption="false" 
					onchange="'getRate()'"
					disabled="false">
				</@ww.select>
				<#if electricFee.id?exists>
					<script language="javascript">
						<#if electricFee.building?exists>
							getObjByName('building.id').value="${electricFee.building.id?if_exists}";
						</#if>
							getObjByName('building.id').disabled=true;
							getObjByName('yesAllBuilding').disabled=true;
							getObjByName('noAllBuilding').disabled=true;
					</script>
				</#if>
			</tr>
			<tr>
				<@pp.datePicker 
					label="'${action.getText('开始时间')}'" 
					name="'electricFee.starTime'" 
		   			value="'${(electricFee.starTime?string('yyyy-MM-dd'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					readonly="true"
					dateFormat="'%Y-%m-%d'"
					maxlength="10"/>
					
				<@textfield label="${action.getText('月数')}" maxlength="5" id="electricFee.month"  name="electricFee.month" value="#{electricFee.month?if_exists}"  required="true" onblur="getEndMon()"/>
				
				<@textfield label="${action.getText('结束时间')}" maxlength="10" id="electricFee.endTime" name="electricFee.endTime"  value="${(electricFee.endTime?string('yyyy-MM-dd'))?if_exists}"  required="true"  readonly="true" />
				<#--
				<@pp.datePicker 
					label="'${action.getText('结束时间')}'" 
					name="'electricFee.endTime'" 
		   			value="'${(electricFee.endTime?string('yyyy-MM-dd'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					readonly="true"
					dateFormat="'%Y-%m-%d'"
					maxlength="10"/>
				-->
		</tr>
			
			<tr>
				<@textfield  label="${action.getText('A上次电示数')}" maxlength="15"  name="electricFee.lastElectricA" value="#{electricFee.lastElectricA?if_exists}"  required="false" />
				<@textfield  label="${action.getText('A本次电示数')}" maxlength="15"  name="electricFee.thisElectricA" value="#{electricFee.thisElectricA?if_exists}"   required="false" />
				<@textfield  label="${action.getText('A实用电数')}" maxlength="15"  name="electricFee.sumElectricA" value="#{electricFee.sumElectricA?if_exists}"  required="false" readonly="true" />
			</tr>
			<tr>
				<@textfield  label="${action.getText('B上次电示数')}" maxlength="15"  name="electricFee.lastElectricB" value="#{electricFee.lastElectricB?if_exists}"  required="false" />
				<@textfield  label="${action.getText('B本次电示数')}" maxlength="15"  name="electricFee.thisElectricB" value="#{electricFee.thisElectricB?if_exists}"   required="false" />
				<@textfield  label="${action.getText('B实用电数')}" maxlength="15"  name="electricFee.sumElectricB" value="#{electricFee.sumElectricB?if_exists}"  required="false" readonly="true" />
			</tr>
			<tr>
				<@textfield  label="${action.getText('实用电数')}" maxlength="15"  name="electricFee.sumElectric" value="#{electricFee.sumElectric?if_exists}"  required="false" readonly="true" />
			</tr>
	</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
			
			<#if electricFee.isSaved?exists && electricFee.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
		    
			<#if electricFee.id?exists>
				<#if !electricFee.isAllBuilding>
					<@vbutton name="button" class ="button" value="${action.getText('计算电费')}" onclick="calculatee(#{electricFee.id?if_exists})"/>
				</#if>
			</#if>
		</#if>
		
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/fee/listElectricFee.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/ElectricFee.js'></script>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/Rate.js'></script>
<script language="JavaScript" type="text/JavaScript"> 
	function getRate(){
		var id = getObjByName('building.id').value;
		DWREngine.setAsync(false);
		Rate.loadRate(id,setRate);
		DWREngine.setAsync(true);
	}
	function setRate(date){
		getObjByName('aRate').value = date[0];
		getObjByName('bRate').value = date[1];
	}
	<#-- 提交验证-->
	function storeValidation(){
		
		if(getObjByName("noAllBuilding").checked && getObjByName("building.id").value==""){
			alert("${action.getText('请选择大楼!')}");
			getObjByName("building.id").focus();
			return false;
		}
		return true;
	}
	
	//提交
	function submitt(){
		getObjByName("electricFee.isSaved").value = 1;
		return storeValidation();
	}
	//保存
	function savee(){
		getObjByName("electricFee.isSaved").value = 0;
		return storeValidation();
	}
	
	function calculatee(id){
		var flag = confirm("所有数据填写完整，将开始计算公摊");
		if(flag){
			DWREngine.setAsync(false);
			ElectricFee.calculate(id,pageRefresh);
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
	
	function getEndMon(){
	var starTime = getObjByName("electricFee.starTime").value;
	var month = getObjByName("electricFee.month").value;
	if(''!=month && 0!=month && ''!=starTime && !isNaN(month)){
		var date = addmulMonth(starTime,month);
		getObjByName("electricFee.endTime").value = date.format("yyyy-MM-dd") ;
		return true;
	}else{
		getObjByName("electricFee.endTime").value ='';
		return false;
	}
}
	
	document.onclick = function(){
		getEndMon();
		<#if !electricFee.id?exists>
		if(getObjByName("yesAllBuilding").checked){
			getObjByName('building.id').value='';
			getObjByName('building.id').disabled=true;
		}
		if(getObjByName("noAllBuilding").checked){
			getObjByName('building.id').removeAttribute('disabled');
		}
		</#if>
		var lastElectricA = getObjByName("electricFee.lastElectricA").value;
		var thisElectricA = getObjByName("electricFee.thisElectricA").value;
		if(''!=lastElectricA && ''!=thisElectricA && (!(isNaN(lastElectricA) || isNaN(thisElectricA)))){
			getObjByName("electricFee.sumElectricA").value = parseFloat(thisElectricA) - parseFloat(lastElectricA);
		}else{
			getObjByName("electricFee.sumElectricA").value ='';
		}
		
		var lastElectricB = getObjByName("electricFee.lastElectricB").value;
		var thisElectricB = getObjByName("electricFee.thisElectricB").value;
		if(''!=lastElectricB && ''!=thisElectricB && (!(isNaN(lastElectricB) || isNaN(thisElectricB)))){
			getObjByName("electricFee.sumElectricB").value = parseFloat(thisElectricB) - parseFloat(lastElectricB);
		}else{
			getObjByName("electricFee.sumElectricB").value = '';
		}
		var sumElectricA = getObjByName("electricFee.sumElectricA").value;
		var sumElectricB = getObjByName("electricFee.sumElectricB").value;
		<#if electricFee.id?exists>
			<#if !electricFee.isAllBuilding>
				var aRate = getObjByName("aRate").value;
				var bRate = getObjByName("bRate").value;
				if(''!=sumElectricA && ''!=sumElectricB && (!(isNaN(sumElectricA) || isNaN(sumElectricB)))){
					getObjByName("electricFee.sumElectric").value =(bRate * parseFloat(sumElectricB)) +(aRate * parseFloat(sumElectricA));
				}else{
					getObjByName("electricFee.sumElectric").value = '';
				}
			<#else>
				if(''!=sumElectricA && ''!=sumElectricB && (!(isNaN(sumElectricA) || isNaN(sumElectricB)))){
					getObjByName("electricFee.sumElectric").value =parseFloat(sumElectricB) +parseFloat(sumElectricA);
				}else{
					getObjByName("electricFee.sumElectric").value = '';
				}
			</#if>
		<#else>
			if(''!=sumElectricA && ''!=sumElectricB && (!(isNaN(sumElectricA) || isNaN(sumElectricB)))){
				getObjByName("electricFee.sumElectric").value =parseFloat(sumElectricB) +parseFloat(sumElectricA);
			}else{
				getObjByName("electricFee.sumElectric").value = '';
			}
		</#if>
	}
</script>

</@htmlPage>

<#if electricFee.id?exists>
<#if !electricFee.isAllBuilding>

<ul id="beautytab">
	<li>
		<a id="electricFloorFee" class="selectedtab" onclick="activeTab(this);"  href='${req.contextPath}/fee/listElectricFloorFee.html?electricFee.id=#{electricFee.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('楼层电费详细')}</a>
	</li>
	<li>
		<a id="electricHouseFee" onclick="activeTab(this);"  href='${req.contextPath}/fee/listElectricHouseFee.html?electricFee.id=#{electricFee.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('房间电费详细')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/fee/listElectricFloorFee.html?electricFee.id=#{electricFee.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>
</#if>
