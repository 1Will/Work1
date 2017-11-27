<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('大楼详细')}">
	<@ww.form namespace="'/house'" name="'listForm'" action="'saveBuilding'" method="'post'">
		<@ww.token name="saveBuildingToken"/>
		<@inputTable>
			<#if building.id?exists>
                <@ww.hidden  id="building.id"  name="'building.id'" value="#{building.id?if_exists}"/>
            <#else>
                <@ww.hidden id="building.id"  name="'building.id'" value=""/>
            </#if>
			<tr>
	           	<@ww.textfield label="'${action.getText('大楼编码')}'" name="'building.code'" value="'${building.code?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('大楼名称')}'" name="'building.name'" value="'${building.name?if_exists}'" cssClass="'underline'" required="true"/>
				
				<#-- 是否有水表 -->
				<td align="right">
					<label for="" class="label">${action.getText('是否有水表')}:</label>
				</td>
				<td align="left">
		        	<input type="radio" id="yesWater" name="building.hasWaterMeter" value="true" />是
		        	<input type="radio" id="noWater" name="building.hasWaterMeter" value="false" />否
				</td>
				<script language="javascript">
				<#if building.id?exists>
					<#if building.hasWaterMeter>
							getObjByName("yesWater").checked = true;
					<#else>
							getObjByName("noWater").checked = true;
					</#if>
				<#else>
						getObjByName("yesWater").checked = true;
				</#if>
			</script>
			</tr>
			<tr>
			
			<#-- 是否有电表 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否有电表')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesElectric" name="building.hasElectricMeter" value="true" />是
			        	<input type="radio" id="noElectric" name="building.hasElectricMeter" value="false" />否
					</td>
					<script language="javascript">
					<#if building.id?exists>
						<#if building.hasElectricMeter>
								getObjByName("yesElectric").checked = true;
						<#else>
								getObjByName("noElectric").checked = true;
						</#if>
					<#else>
							getObjByName("yesElectric").checked = true;
					</#if>
				</script>
				
				<#-- 是否有空调 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否有空调')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesAir" name="building.hasAirMeter" value="true" />是
			        	<input type="radio" id="noAir" name="building.hasAirMeter" value="false" />否
					</td>
					<script language="javascript">
					<#if building.id?exists>
						<#if building.hasAirMeter>
								getObjByName("yesAir").checked = true;
						<#else>
								getObjByName("noAir").checked = true;
						</#if>
					<#else>
							getObjByName("yesAir").checked = true;
					</#if>
				</script>
				
				<#-- 是否有网络 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否有网络')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesNet" name="building.hasNetMeter" value="true" />是
			        	<input type="radio" id="noNet" name="building.hasNetMeter" value="false" />否
					</td>
					<script language="javascript">
					<#if building.id?exists>
						<#if building.hasAirMeter>
								getObjByName("yesNet").checked = true;
						<#else>
								getObjByName("noNet").checked = true;
						</#if>
					<#else>
							getObjByName("yesNet").checked = true;
					</#if>
				</script>
			</tr>
			<tr>
				<@ww.select 
	    		label="'${action.getText('水表模型')}'"
					required="true"
					name="'waterModel.id'" 
					value="${req.getParameter('waterModel.id')?if_exists}" 
					listKey="id"
					listValue="name"
				    list="allWaterModel"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
		    	<script language="javascript">
		    		<#if building.waterModel?exists>
		    			var waterModel ='${building.waterModel.id?if_exists}';
		    			getObjByName('waterModel.id').value = waterModel;
		    		</#if>
		    	</script>
		    	
		    	<@ww.select 
	    		label="'${action.getText('电表A')}'"
					required="false"
					name="'aeMeter.id'"
					value="${req.getParameter('aeMeter.id')?if_exists}"
					listKey="id"
					listValue="name"
				    list="allElectricMeter"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
		    	<script language="javascript">
		    		<#if building.aeMeter?exists>
		    			var aeMeter ='${building.aeMeter.id?if_exists}';
		    			getObjByName('aeMeter.id').value = aeMeter;
		    		</#if>
		    	</script>
		    	
		    	<@ww.select 
	    		label="'${action.getText('电表B')}'"
					required="false"
					name="'beMeter.id'"
					value="${req.getParameter('beMeter.id')?if_exists}"
					listKey="id"
					listValue="name"
				    list="allElectricMeter"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
		    	<script language="javascript">
		    		<#if building.beMeter?exists>
		    			var beMeter ='${building.beMeter.id?if_exists}';
		    			getObjByName('beMeter.id').value = beMeter;
		    		</#if>
		    	</script>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('水价(元/吨)')}'" name="'building.waterPrice'"  cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('民用电价(元/度)')}'" name="'building.electricCivilPrice'"  cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('工业电价(元/度)')}'" name="'building.electricIndustryPrice'"  cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('空调基数价(元/平米月)')}'" name="'building.airBasePrice'"  cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('空调流量价(元/立方)')}'" name="'building.airPrice'"  cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('电公摊(元/平米月)')}'" name="'building.electricSharePrice'"  cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('水表示数')}'" name="'building.waterdisplay'"  cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('a电表示数')}'" name="'building.aedisplay'"  cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('b电表示数')}'" name="'building.bedisplay'"  cssClass="'underline'"/>
			</tr>
			
		</@inputTable>
		<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"></@vsubmit>
        </#if>
        
        <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/house/listBuilding.html?readOnly=${req.getParameter('readOnly')?if_exists}" /> 
	</@buttonBar>
	</@ww.form>
	<script language="javascript">
	function savee(){
		if(getObjByName('building.code').value==''){
			alert('请输入大楼编码');
			getObjByName('building.code').focus();
			return false;
		}
		
		if(getObjByName('building.name').value==''){
			alert('请输入大楼名称');
			getObjByName('building.name').focus();
			return false;
		}
		
		if(getObjByName('building.waterPrice').value==''){
			alert('请输入水费价格');
			getObjByName('building.waterPrice').focus();
			return false;
		}
		
		if(getObjByName('building.electricCivilPrice').value==''){
			alert('请输入民用电费价格');
			getObjByName('building.electricCivilPrice').focus();
			return false;
		}
		
		if(getObjByName('building.electricIndustryPrice').value==''){
			alert('请输入工业电费价格');
			getObjByName('building.electricIndustryPrice').focus();
			return false;
		}
		
		if(getObjByName('building.airPrice').value==''){
			alert('请输入空调费价格');
			getObjByName('building.airPrice').focus();
			return false;
		}
		return true;
	}
	</script>
</@htmlPage>
<#--
<#if building.id?exists>
<ul id="beautytab">
	<li>
		<a id="floor" onclick="activeTab(this);" href='${req.contextPath}/house/listFloor.html?building.id=#{building.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('楼层')}</a>
	</li>
	<li>
		<a id="floor" onclick="activeTab(this);" href='${req.contextPath}/house/listHouse.html?building.id=#{building.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('房间')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/house/listFloor.html?building.id=#{building.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="50%"/>
</#if>
-->
