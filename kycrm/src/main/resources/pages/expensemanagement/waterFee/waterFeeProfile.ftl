<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('水费详细维护页面')}">
<@ww.form name="'listForm'" action="'saveWaterFee'" method="'post'">
	<@ww.token name="saveWaterFeeActionToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'waterFee.isSaved'" value="''"/>
	<#if waterFee.id?exists>
		<@ww.hidden name="'waterFee.id'" value="#{waterFee.id?if_exists}"/>
	<#else>
		<@ww.hidden name="'waterFee.id'" value=""/>
	</#if>
	
	<@inputTable>
			<tr>
				<td align="right" valign="top">
		       		<#--<span class="required">*</span>-->
		       		<label class="label">${action.getText('水费编码')}:</label>
		     	</td>
		     	<td>
					<input type="text" disabled="true" id="waterFee.code" name="waterFee.code" class="underline"  value="${waterFee.code?if_exists}" maxlength="140" size="20"/>
				</td>
				<@textfield  label="${action.getText('水费名称')}" maxlength="15"  name="waterFee.waterFeeName" value="${waterFee.waterFeeName?if_exists}"    />
			</tr>
			<tr>
				<#-- 是否为总水量 -->
				<td align="right">
					<label for="" class="label">${action.getText('园区总水量')}:</label>
				</td>
				<td align="left">
		        	<input type="radio" id="yesAllBuilding" name="waterFee.isAllBuilding" value="true" />是
		        	<input type="radio" id="noAllBuilding" name="waterFee.isAllBuilding" value="false" />否
				</td>
				<script language="javascript">
				<#if waterFee.id?exists>
					<#if waterFee.isAllBuilding>
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
				required="false"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
			<#if waterFee.id?exists>
				<script language="javascript">
					<#if waterFee.building?exists>
						getObjByName('building.id').value="${waterFee.building.id?if_exists}";
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
				name="'waterFee.starTime'" 
	   			value="'${(waterFee.starTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				
			<@textfield label="${action.getText('月数')}" maxlength="5" id="waterFee.month"  name="waterFee.month" value="#{waterFee.month?if_exists}"  required="true" onblur="getEndMon()"/>
				
			<@textfield label="${action.getText('结束时间')}" maxlength="10" id="waterFee.endTime" name="waterFee.endTime"  value="${(waterFee.endTime?string('yyyy-MM-dd'))?if_exists}"  required="true"  readonly="true" />
			<#--
			<@pp.datePicker 
				label="'${action.getText('结束时间')}'" 
				name="'waterFee.endTime'" 
	   			value="'${(waterFee.endTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			-->
		</tr>
			
		<tr>
						
			
			<@textfield  label="${action.getText('上次水示数')}" maxlength="15"  name="waterFee.lastWater" value="#{waterFee.lastWater?if_exists}"  required="true" />
			<@textfield  label="${action.getText('本次水示数')}" maxlength="15"  name="waterFee.thisWater" value="#{waterFee.thisWater?if_exists}"   required="true" />
			<@textfield  label="${action.getText('公摊水数')}" maxlength="15"  name="waterFee.shareWater" value="#{waterFee.shareWater?if_exists}"  required="false" />
		</tr>
		<tr>
			<@textfield  label="${action.getText('实用水数')}" maxlength="15"  name="waterFee.sumWater" value="#{waterFee.sumWater?if_exists}"  required="false" readonly="true" />
		</tr>
	</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
			
			<#if waterFee.isSaved?exists && waterFee.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
		    
			<#if waterFee.id?exists>
					<#if !waterFee.isAllBuilding>
						<@vbutton name="button" class ="button" value="${action.getText('计算水费')}" onclick="calculatee(#{waterFee.id?if_exists})"/>
					</#if>
			</#if>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/fee/listWaterFee.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/WaterFee.js'></script>
<script language="JavaScript" type="text/JavaScript"> 
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
		getObjByName("waterFee.isSaved").value = 1;
		return storeValidation();
	}
	//保存
	function savee(){
		getObjByName("waterFee.isSaved").value = 0;
		return storeValidation();
	}
	
	function calculatee(id){
		var flag = confirm("所有数据填写完整，将开始计算公摊");
		if(flag){
			DWREngine.setAsync(false);
			WaterFee.calculate(id,pageRefresh);
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
		var starTime = getObjByName("waterFee.starTime").value;
		var month = getObjByName("waterFee.month").value;
		if(''!=month && 0!=month && ''!=starTime && !isNaN(month)){
			var date = addmulMonth(starTime,month);
			getObjByName("waterFee.endTime").value = date.format("yyyy-MM-dd") ;
			return true;
		}else{
			getObjByName("waterFee.endTime").value ='';
			return false;
		}
	}
	
	document.onclick = function(){
		getEndMon();
		<#if !waterFee.id?exists>
			if(getObjByName("yesAllBuilding").checked){
				getObjByName('building.id').value='';
				getObjByName('building.id').disabled=true;
			}
			if(getObjByName("noAllBuilding").checked){
				getObjByName('building.id').removeAttribute('disabled');
			}
		</#if>
		var lastWater = getObjByName("waterFee.lastWater").value;
		var thisWater = getObjByName("waterFee.thisWater").value;
		var shareWater = getObjByName("waterFee.shareWater").value;
		if( (!(isNaN(lastWater) || isNaN(thisWater) || isNaN(shareWater))) && ''!= lastWater && ''!= thisWater && ''!= shareWater ){
			getObjByName("waterFee.sumWater").value = parseFloat(thisWater) + parseFloat(shareWater) - parseFloat(lastWater);
		}else{
			getObjByName("waterFee.sumWater").value ='';
		}
	}
</script>

</@htmlPage>

<#if waterFee.id?exists>
<#if !waterFee.isAllBuilding>

<ul id="beautytab">
	<li>
		<a id="waterFloorFee" class="selectedtab" onclick="activeTab(this);"  href='${req.contextPath}/fee/listWaterFloorFee.html?waterFee.id=#{waterFee.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('楼层水费详细')}</a>
	</li>
	<li>
		<a id="waterHouseFee" onclick="activeTab(this);"  href='${req.contextPath}/fee/listWaterHouseFee.html?waterFee.id=#{waterFee.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('房间水费详细')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/fee/listWaterFloorFee.html?waterFee.id=#{waterFee.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>
</#if>
