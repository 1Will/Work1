<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('楼层详细')}">
	<@ww.form namespace="'/house'" name="'listForm'" action="'saveFloor'" method="'post'">
		<@ww.token name="saveFloorToken"/>
		<@inputTable>
			<#if floor.id?exists>
                <@ww.hidden  id="floor.id"  name="'floor.id'" value="#{floor.id?if_exists}"/>
            <#else>
                <@ww.hidden id="floor.id"  name="'floor.id'" value=""/>
            </#if>
            <#if building?exists>
                <@ww.hidden  id="building.id"  name="'building.id'" value="#{building.id?if_exists}"/>
            </#if>
			<tr>
			<@ww.textfield label="'${action.getText('楼层编码')}'" name="'floor.code'" value="'${floor.code?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('楼层名称')}'" name="'floor.name'" value="'${floor.name?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.select label="'${action.getText('大楼')}'" 
				id="building.id" 
				name="'building.id'" 
				value="${req.getParameter('building.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allBuilding"
				required="true"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
			<#if floor.id?exists>
				<script language="javascript">
					getObjByName('building.id').value="${floor.building.id?if_exists}";
				</script>
			</#if>
			<tr>	
				<#-- 是否有水表 -->
				<td align="right">
					<label for="" class="label">${action.getText('是否有水表')}:</label>
				</td>
				<td align="left">
		        	<input type="radio" id="yesWater" name="floor.hasWaterMeter" value="true" />是
		        	<input type="radio" id="noWater" name="floor.hasWaterMeter" value="false" />否
				</td>
				<script language="javascript">
				<#if floor.id?exists>
					<#if floor.hasWaterMeter>
							getObjByName("yesWater").checked = true;
					<#else>
							getObjByName("noWater").checked = true;
					</#if>
				<#else>
						getObjByName("yesWater").checked = true;
				</#if>
			</script>
			
			<#-- 是否有电表 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否有电表')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesElectric" name="floor.hasElectricMeter" value="true" />是
			        	<input type="radio" id="noElectric" name="floor.hasElectricMeter" value="false" />否
					</td>
					<script language="javascript">
					<#if floor.id?exists>
						<#if floor.hasElectricMeter>
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
			        	<input type="radio" id="yesAir" name="floor.hasAirMeter" value="true" />是
			        	<input type="radio" id="noAir" name="floor.hasAirMeter" value="false" />否
					</td>
					<script language="javascript">
					<#if floor.id?exists>
						<#if floor.hasAirMeter>
								getObjByName("yesAir").checked = true;
						<#else>
								getObjByName("noAir").checked = true;
						</#if>
					<#else>
							getObjByName("yesAir").checked = true;
					</#if>
				</script>
			</tr>
			<tr>
			
				<#-- 是否有网络 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否有网络')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesNet" name="floor.hasNetMeter" value="true" />是
			        	<input type="radio" id="noNet" name="floor.hasNetMeter" value="false" />否
					</td>
					<script language="javascript">
					<#if floor.id?exists>
						<#if floor.hasNetMeter>
								getObjByName("yesNet").checked = true;
						<#else>
								getObjByName("noNet").checked = true;
						</#if>
					<#else>
							getObjByName("yesNet").checked = true;
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
		    		<#if floor.aeMeter?exists>
		    			var aeMeter ='${floor.aeMeter.id?if_exists}';
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
		    		<#if floor.beMeter?exists>
		    			var beMeter ='${floor.beMeter.id?if_exists}';
		    			getObjByName('beMeter.id').value = beMeter;
		    		</#if>
		    	</script>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('水表示数')}'" name="'floor.waterdisplay'"  cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('a电表示数')}'" name="'floor.aedisplay'"  cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('b电表示数')}'" name="'floor.bedisplay'"  cssClass="'underline'"/>
			</tr>
		</@inputTable>
		<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"></@vsubmit>
        </#if>
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/house/listFloor.html?readOnly=${req.getParameter('readOnly')?if_exists}" /> 
        
	</@buttonBar>
	</@ww.form>
	<script language="javascript">
	function savee(){
		if(getObjByName('floor.code').value==''){
			alert('请输入大楼编码');
			getObjByName('floor.code').focus();
			return false;
		}
		
		if(getObjByName('floor.name').value==''){
			alert('请输入大楼名称');
			getObjByName('floor.name').focus();
			return false;
		}
		return true;
	}
	</script>
</@htmlPage>