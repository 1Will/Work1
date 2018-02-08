<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="工装卡片维护">
<script language="JavaScript" type="text/JavaScript"> 

 	window.onload = function () {
	    		//var url = '../../preview/tool/editToolExtInfo.html';
				document.all.frame.src= '../../tool/base/listToolEnclosure.html';
				getObjByNameRe("extInfo").className = "selectedtab";
				}
	function supplier_OpenDialog() {
			popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,400);
		}	
	function product_OpenDialog() {
			popupModalDialog('${req.contextPath}/popup/listProducts.html',600,400);
		}
	function peopleMainManager_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/maintenanceManagerSelector.html',null, 300);
	}
	</script>
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
	        <tr>
	        	<@ww.textfield label="'工装编号'" name="'area.code'" value=""  required="true" cssClass="'underline'" />
	        	<@ww.textfield label="'工装图号'" name="'area.code'" value=""  required="true" cssClass="'underline'" />
	        	<@ww.textfield label="'工装名称'" name="'area.code'" value="" required="true" cssClass="'underline'"/>
	        </tr>
	        <tr>
	       		<td align="right" valign="top"><label class="label"><font color="red">*</font>产品名称:</label></td>
		 		<td>
		 			<input type="text" name="deviceExtInfo.madeIn" class="underline"  value=""  maxlength="150" disabled="true"/>
		 			<a onClick='product_OpenDialog();'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
	 			</td>
	       		<@ww.textfield label="'使用定额'" name="'name'" required="false" value="''" cssClass="'underline'"/>
	       		<@pp.datePicker label="'下发时间'" name="'cardCreatedTime'"
		        	value="" cssClass="'underline'" size="15" required="true"/>	
	        </tr>
	        <tr>
	      		 <@ww.select label="'工装类别'"
		                     name="'toolCategory'"
		                   	 headerKey="1" 
		                     headerValue="Select Month"
		                    list="{
		                    		'',
		                    		'模具', 
		                    		'夹具', 
		                    		'工位器具',
		                    		'检具',
		                    		'辅具'
		                    	  }"
		                    value="selectedDevice"
		                    onchange="'setcity()'"
		                    	
		        	/> 
		        	
		        	 <td align="right" valign="middle">  <label id="cate" for="aa">分类:</label></td>
            		<td>
            			<select id="aa" name="category" >
					        <option value="所有" ></option>
            			</select>
            		</td>
		        	<@ww.select label="'使用单位'"
		                    name="device"
		                   	headerKey="1" 
		                    headerValue="Select Month"
		                    list="{
		                    			
		                    		'焊冲一厂', 
		                    		'总装一厂'
		                    	  }"
		                    value="selectedDevice"
		                    	
		        	/> 
 
	        </tr>
		    <tr>
		       	<td colspan="8"><HR align="middle" size="1" width="95%" color="#999999" noshade/></td><td></td>
		    </tr>
	        <tr>
	        	<td align="right" valign="top"><label class="label">制造单位:</label></td>
		 		<td>
		 			<input type="text" name="deviceExtInfo.madeIn" class="underline"  value=""  maxlength="150" disabled="true"/>
		 			<a onClick='supplier_OpenDialog();'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
	 				<@ww.hidden name="'country.name'" value=""/>
		 		</td>
		 		<@ww.textfield readonly="false" label="'制造者'" name="'deviceExtInfo.material'" value="''"  cssClass="'underline'"/> 
	            <@pp.datePicker label="'制造日期'" name="'deviceExtInfo.madeDate'"
	     							value="'${(deviceExtInfo.madeDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	        </tr>
	        <tr>
	        	<td align="right" valign="top"><label class="label">设计者:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
	            <@pp.datePicker label="'设计日期'" name="'deviceExtInfo.designDate'"
	     							value="''" cssClass="'underline'" size="15" />
	         	<@pp.datePicker label="'完工日期'" name="'deviceExtInfo.endDate'"
	     							value="''" cssClass="'underline'" size="15" />
	        </tr>
	        <tr>
	        	<td align="right" valign="top"><label class="label">检验员:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <@pp.datePicker label="'检验日期'" name="'deviceExtInfo.checkDate0'"
	     							value="''" cssClass="'underline'" size="15" />
	        	<@pp.datePicker label="'验收日期'" name="'deviceExtInfo.checkDate'"
	     							value="''" cssClass="'underline'" size="15" />
	        </tr>
	        <tr>
	        	<@ww.textfield readonly="true" label="'累计产量'" name="'deviceExtInfo.opCondition'" value="'200'"  cssClass="'underline'" disabled="true"/> 
	        	<@ww.textarea  label="'检验员意见'" name="'deviceExtInfo.opCondition'" value="'正常'"  rows="3" cols="50" cssClass="'underline'" disabled="false"/>
			</tr>
			<tr>
		       	<td colspan="8"><HR align="middle" size="1" width="95%" color="#999999" noshade/></td><td></td>
		    </tr>
		    <tr>
	            <@ww.textfield readonly="false" label="'放架编号'" name="'deviceExtInfo.opCondition'" value="''"  cssClass="'underline'"/> 
	        	<@pp.datePicker label="'报废日期'" name="'deviceExtInfo.feeDate'"
	     							value="''" cssClass="'underline'" size="15" />
	     		 <@ww.textfield readonly="false" label="'状态'" name="'deviceExtInfo.opCondition'" value="'正常'"  cssClass="'underline'" disabled="true"/> 					
	        </tr>
  		</@inputTable>
         <@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/tool/base/listToolAccounts.html"/>
        </@buttonBar>

    </@ww.form>
	<script language="JavaScript" type="text/JavaScript"> 
		function setcity() { 
		
				switch (document.forms[0].elements["toolCategory"].value) { 
				
				
				case '模具' : 
				
				var labels = new Array("","冲头","冲套","固定座"); 
				
				var values = new Array("sjz","cz","ts"); 
				//document.getElementsByTagName("label").text="模具类别";
				getObjByNameRe("cate").innerText="模具分类:"
				
				break; 
				
				case '夹具' : 
				
				var labels = new Array("","A","B","C"); 
				
				var values = new Array("jn","qd","yt"); 
				//document.getElementsByTagName("label").text="夹具类别";
				getObjByNameRe("cate").innerText="夹具分类:"
				break;
				
				case '' : 
				
				var labels = new Array(""); 
				
				var values = new Array("jn","qd","yt"); 
				//document.getElementsByTagName("label").text="夹具类别";
				getObjByNameRe("cate").innerText="分类:"
				break;
				
				case '工位器具' : 
				
				var labels = new Array(""); 
				
				var values = new Array(""); 
				//document.getElementsByTagName("label").text="夹具类别";
				getObjByNameRe("cate").innerText="工位器具分类:"
				break;
				
				case '检具' : 
				
				var labels = new Array(""); 
				
				var values = new Array(""); 
				//document.getElementsByTagName("label").text="夹具类别";
				getObjByNameRe("cate").innerText="检具分类:"
				break;
			 
			 
				case '辅具' : 
				
				var labels = new Array(""); 
				
				var values = new Array(""); 
				//document.getElementsByTagName("label").text="夹具类别";
				getObjByNameRe("cate").innerText="辅具分类:"
				break;
			 
				
		
		} 
		document.forms[0].elements["category"].options.length = 0; 
		
		// 从数组中添加内容 

		for(var i = 0; i < labels.length; i++) { 
		//alert(document.getElementsByTagName("label").for.value);
		//alert(document.all("saveArea_toolCategory").getElementsByTagName("label").text);
		document.forms[0].elements["category"].add(document.createElement("OPTION")); 
		
		document.forms[0].elements["category"].options[i].text=labels[i]; 
		
		document.forms[0].elements["category"].options[i].value=values[i]; 

		} 

	}


	</script>
   <ul id="beautytab">
	  <li><a id="extInfo" onclick="activeTab(this);"  class="selectedtab" href="listToolEnclosure.html" target="frame" >主要附件</a></li>
	   <li><a id="attachTool" onclick="activeTab(this);"  href="listToolSpares.html" target="frame">备品备件</a></li>
	  <li><a id="accessoryDevice" onclick="activeTab(this);"  href="listToolDocs.html" target="frame">技术资料</a></li>		 
	  <li><a id="financeInfo" onclick="activeTab(this);"  href="listRequestitionLogs.html" target="frame">领用记录</a></li>
	  <li><a id="deviceDoc"   onclick="activeTab(this);" href="listChangeLogsHistorys.html" target="frame" >变更记录</a></li>
	  <li><a id="error"   onclick="activeTab(this);" href="${req.contextPath}/preview/device/acclog/listAccidentLogHistories.html" target="frame" >事故记录</a></li>
	  <li><a id="accident"   onclick="activeTab(this);" href="${req.contextPath}/preview/tool/errlog/listErrLogHistories.html" target="frame" >故障记录</a></li>
	  <li><a id="biaoding"   onclick="activeTab(this);" href="${req.contextPath}/preview/tool/demarcates/listDemarcateHistories.html" target="frame" >标定记录</a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>