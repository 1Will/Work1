<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('房源详细')}">
	<@ww.form namespace="'/house'" name="'listForm'" action="'saveHouse'" method="'post'">
		<@ww.token name="saveHouseToken"/>
		<@inputTable>
			<#if house.id?exists>
                <@ww.hidden id="house.id"  name="'house.id'" />
            </#if>
            <#if house.customerInfo?exists>
                <@ww.hidden id="customerInfo.id"  name="'customerInfo.id'" value="#{house.customerInfo.id?if_exists}"/>
            <#else>
                <@ww.hidden id="customerInfo.id"  name="'customerInfo.id'" value=""/>
            </#if>
			<tr>
	           	<@ww.textfield label="'${action.getText('房源编码')}'" name="'house.code'" cssClass="'underline'" required="true"/>
				<@ww.select label="'${action.getText('大楼')}'" 
					id="building.id" 
					name="'building.id'" 
					value="${req.getParameter('building.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allBuilding"
					required="true"
					emptyOption="false" 
					onchange="'loadFloor()'"
					disabled="false"/>

				<@ww.select label="'${action.getText('楼层')}'" 
					id="floor.id" 
					name="'floor.id'" 
					value="${req.getParameter('floor.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allFloor"
					required="true"
					emptyOption="false" 
					disabled="false"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('房源名称')}'" name="'house.name'"  cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('面积(平方)')}'" name="'house.square'"  cssClass="'underline'" required="true" onblur ="'getSum();'" />
				<@ww.textfield label="'${action.getText('单价(元/平米月)')}'" name="'house.price'"  cssClass="'underline'" required="true" onblur ="'getSum();'"/>
			</tr>
			<tr>
			
			<#-- 是否主营 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否主营')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesMain" name="house.isMain" value="true" />是
			        	<input type="radio" id="noMain" name="house.isMain" value="false" />否
					</td>
					<script language="javascript">
					<#if house.id?exists>
						<#if house.isMain>
								getObjByName("yesMain").checked = true;
						<#else>
								getObjByName("noMain").checked = true;
						</#if>
					<#else>
							getObjByName("yesMain").checked = true;
					</#if>
				</script>
				
				<@ww.textfield label="'${action.getText('层高(米)')}'" name="'house.height'"  cssClass="'underline'" />
				<@ww.textfield label="'${action.getText('总价(元)')}'" name="'house.total'"  cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<#-- 是否有水表 -->
				<td align="right">
					<label for="" class="label">${action.getText('是否有水表')}:</label>
				</td>
				<td align="left">
		        	<input type="radio" id="yesWater" name="house.hasWaterMeter" value="true" />是
		        	<input type="radio" id="noWater" name="house.hasWaterMeter" value="false" />否
				</td>
				<script language="javascript">
				<#if house.id?exists>
					<#if house.hasWaterMeter>
							getObjByName("yesWater").checked = true;
					<#else>
							getObjByName("noWater").checked = true;
					</#if>
				<#else>
						getObjByName("yesWater").checked = true;
				</#if>
			</script>
			<#-- 是否有物业 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否有物业')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesProperty" name="house.hasProperty" value="true" />是
			        	<input type="radio" id="noProperty" name="house.hasProperty" value="false" />否
					</td>
					<script language="javascript">
					<#if house.id?exists>
						<#if house.hasProperty>
								getObjByName("yesProperty").checked = true;
						<#else>
								getObjByName("noProperty").checked = true;
						</#if>
					<#else>
							getObjByName("yesProperty").checked = true;
					</#if>
				</script>
			<#-- 是否有电表 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否有电表')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesElectric" name="house.hasElectricMeter" value="true" />是
			        	<input type="radio" id="noElectric" name="house.hasElectricMeter" value="false" />否
					</td>
					<script language="javascript">
					<#if house.id?exists>
						<#if house.hasElectricMeter>
								getObjByName("yesElectric").checked = true;
						<#else>
								getObjByName("noElectric").checked = true;
						</#if>
					<#else>
							getObjByName("yesElectric").checked = true;
					</#if>
				</script>

							
			</tr>
			<tr>
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
		    	<@ww.select 
	    		label="'${action.getText('电费类型')}'"
					required="false"
					name="'eType.id'" 
					value="${req.getParameter('eType.id')?if_exists}" 
					listKey="id"
					listValue="name"
				    list="allEType"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
				
			</tr>
			<tr>
				<#-- 是否有网络 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否有网络')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesNet" name="house.hasNetMeter" value="true" />是
			        	<input type="radio" id="noNet" name="house.hasNetMeter" value="false" />否
					</td>
					<script language="javascript">
					<#if house.id?exists>
						<#if house.hasNetMeter>
								getObjByName("yesNet").checked = true;
						<#else>
								getObjByName("noNet").checked = true;
						</#if>
					<#else>
							getObjByName("yesNet").checked = true;
					</#if>
				</script>
			<@ww.textfield label="'${action.getText('端口数')}'" name="'house.netNum'"  cssClass="'underline'" required="true"/>
			<@ww.textfield label="'${action.getText('网络费率')}'" name="'house.netfee'"  cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<#-- 是否有空调 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否有空调')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesAir" name="house.hasAirMeter" value="true" />是
			        	<input type="radio" id="noAir" name="house.hasAirMeter" value="false" />否
					</td>
					<script language="javascript">
					<#if house.id?exists>
						<#if house.hasAirMeter>
								getObjByName("yesAir").checked = true;
						<#else>
								getObjByName("noAir").checked = true;
						</#if>
					<#else>
							getObjByName("yesAir").checked = true;
					</#if>
				</script>
				
				<@ww.textfield label="'${action.getText('空调表数')}'" name="'house.airNum'"  cssClass="'underline'" />
				
				<@ww.select 
	    		label="'${action.getText('房源状态')}'"
					required="false"
					name="'state.id'" 
					value="${req.getParameter('state.id')?if_exists}" 
					listKey="id"
					listValue="name"
				    list="allState"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
			</tr>
			
			
			<tr>
			<@ww.select 
					label="'${action.getText('来源')}'"
					required="false"
					name="'source.id'" 
					value="${req.getParameter('source.id')?if_exists}" 
					listKey="id"
					listValue="name"
				    list="allSource"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
				
				<@ww.select 
	    		label="'${action.getText('房源类别')}'"
					required="false"
					name="'category.id'" 
					value="${req.getParameter('category.id')?if_exists}" 
					listKey="id"
					listValue="name"
				    list="allCategory"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
		    	<td align="right" valign="top">
					<label class="label">${action.getText('供应商')}:</label>
				</td>
				<td>
				<#if house.customerInfo?exists>
					<input type="text" name="customerInfo.name" class="underline"  value="${house.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="customerInfo.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
					<a onClick="customerInfo_OpenDialog();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
				
		    </tr>
		    <tr>
				<@ww.select 
	    		label="'${action.getText('朝向')}'"
					required="false"
					name="'orientation.id'" 
					value="${req.getParameter('orientation.id')?if_exists}" 
					listKey="id"
					listValue="name"
				    list="allOrientation"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
				<@ww.select 
	    		label="'${action.getText('装修')}'"
					required="false"
					name="'renovation.id'" 
					value="${req.getParameter('renovation.id')?if_exists}" 
					listKey="id"
					listValue="name"
				    list="allRenovation"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
		    	<#-- 是否有公摊电费 -->
				<td align="right">
						<label for="" class="label">${action.getText('是否有公摊电费')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesEShare" name="house.hasEShare" value="true" />是
			        	<input type="radio" id="noEShare" name="house.hasEShare" value="false" />否
					</td>
					<script language="javascript">
					<#if house.id?exists>
						<#if house.hasEShare>
								getObjByName("yesEShare").checked = true;
						<#else>
								getObjByName("noEShare").checked = true;
						</#if>
					<#else>
							getObjByName("yesEShare").checked = true;
					</#if>
				</script>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('水表示数')}'" name="'house.waterdisplay'"  cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('a电表示数')}'" name="'house.aedisplay'"  cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('b电表示数')}'" name="'house.bedisplay'"  cssClass="'underline'"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('空调表总示数')}'" name="'house.airdisplay'"  cssClass="'underline'"/>
			</tr>
			<tr>
				<@textarea id="house.outline" name="house.outline" label="${action.getText('说明')}" value="${house.outline?if_exists}" rows="4" cols="160"/>
			</tr>
			
		</@inputTable>
		<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"></@vsubmit>
        </#if>
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/house/listHouse.html?readOnly=${req.getParameter('readOnly')?if_exists}" /> 
        
	</@buttonBar>
	</@ww.form>
	<script type='text/javascript' src='${req.contextPath}/dwr/interface/Floors.js'></script>
	<script type='text/javascript'>
	function savee(){
		if(getObjByName('house.code').value==''){
			alert('请输入房源编码');
			getObjByName('house.code').focus();
			return false;
		}
		
		if(getObjByName('house.name').value==''){
			alert('请输入房源名称');
			getObjByName('house.name').focus();
			return false;
		}
		
		if(getObjByName('house.square').value==''){
			alert('请输入房源面积');
			getObjByName('house.square').focus();
			return false;
		}
		
		if(getObjByName('house.price').value==''){
			alert('请输入房源单价');
			getObjByName('house.price').focus();
			return false;
		}
		return true;
	}
	
	function getSum(){
		var square = getObjByName("house.square").value;
		var price = getObjByName("house.price").value;
		getObjByName("house.total").value =toDecimal(square * price);
	}
	document.onclick=function(){
		getSum();
	}
	
	
	function loadFloor(){
		var id = getObjByName('building.id').value;
		DWREngine.setAsync(false);
		DWRUtil.removeAllOptions("floor.id");
		Floors.loadFloor(id,setFloor);
		DWREngine.setAsync(true);
	}
	
	function setFloor(result){
		if(result !=null){
			for(var i =0;i<result.length;i++ ){
				getObjByName("floor.id").options.add(new Option(result[i].name , result[i].id));
			}
		}
	}
	
		//弹出供应商模态窗体
	function customerInfo_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html?isPartner=1&readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, setCustomerInfo);
	 }
	 //获得模态窗体返回值
	function setCustomerInfo(result) {
		if (null != result) {
		 	getObjByName("customerInfo.id").value = result[0];	
		 	getObjByName("customerInfo.name").value = result[1];
		}
	}
	
	</script>
	
	<script type='text/javascript'>
		<#if house.eType?exists>
			getObjByName("eType.id").value="#{house.eType.id?if_exists}";
		</#if>
		
		<#if house.category?exists>
			getObjByName("category.id").value="#{house.category.id?if_exists}";
		</#if>
		
		<#if house.state?exists>
			getObjByName("state.id").value="#{house.state.id?if_exists}";
		</#if>
		
		<#if house.orientation?exists>
			getObjByName("orientation.id").value="#{house.orientation.id?if_exists}";
		</#if>
		
		<#if house.renovation?exists>
			getObjByName("renovation.id").value="#{house.renovation.id?if_exists}";
		</#if>
		
		<#if house.source?exists>
			getObjByName("source.id").value="#{house.source.id?if_exists}";
		</#if>
		
		<#if house.building?exists>
			getObjByName('building.id').value="#{house.building.id?if_exists}";
			loadFloor();
		</#if>
		
		<#if house.beMeter?exists>
			getObjByName('beMeter.id').value = '#{house.beMeter.id?if_exists}';
		</#if>
		
		<#if house.aeMeter?exists>
			getObjByName('aeMeter.id').value = '#{house.aeMeter.id?if_exists}';
		</#if>
		
		<#if house.floor?exists>
			getObjByName('floor.id').value="#{house.floor.id?if_exists}";
		</#if>
		
	</script>
</@htmlPage>
<#if house.id?exists>
<ul id="beautytab">
	<li>
		<a id="contractInfo" onclick="activeTab(this);" href='${req.contextPath}/contractManagement/listContractManagementByCustomerAction.html?house.id=#{house.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('合同列表')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/contractManagement/listContractManagementByCustomerAction.html?house.id=#{house.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="50%"/>
</#if>