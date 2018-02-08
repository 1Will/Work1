<#include "../../includes/macros2.ftl" />

<@htmlPage title="设备润滑实施维护">

<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='../../device/smoothInfo/listSmoothPlanImplItems.html';
 		getObjByNameRe("ruleItems").className = "selectedtab";
 	}
 	
	function activeTab(obj) {
	    var sfEls = getObjByNameRe("beautytab").getElementsByTagName("li");
	    for (var i=0; i<sfEls.length; i++) {
	    	//alert(sfEls[i].getElementsByTagName("a")[0].id == obj.id);
	      if (sfEls[i].getElementsByTagName("a")[0].id == obj.id) {
	        sfEls[i].getElementsByTagName("a")[0].className = "selectedtab";
	      } else {
	        sfEls[i].getElementsByTagName("a")[0].className = '';
	      }
	    }
	}
	
	function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',600,400);
	}
	function checkPointPlan_openDialog() {
		popupModalDialog('${req.contextPath}/popup/smoothPlanSelector.html',600,400);
	}
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html', null, 300);
	}

</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'润滑计划编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="false"/>
                <@ww.textfield label="'润滑计划名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="false"/>
            </tr>
            <tr>
                <@deviceSelector2 img="none"/>
            </tr>
             <tr>
             	<@ww.select label="'部门'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'机动部'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	disabled="true"
		                    	/>
             	<td align="right" valign="top"><label class="label"><font color="red"></font>编制人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		        </td>
            </tr>
            <tr>
	     		<@ww.textfield label="'编制日期'" name="'area.code'" value="'2007-01-23'" cssClass="'underline'" readonly="true" required="false"/>
	     	</tr>
	     	<tr>
	     	</tr>
	     	<tr>
	     	   <td align="right" valign="top"><label class="label"><font color="red">*</font>报告人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            	  <@pp.datePicker label="'报告日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
	     	</tr>
            <tr>
               <@ww.textfield label="'状态'" name="'area.code'" value="未完成" cssClass="'underline'" readonly="true" required="false"/>
	        </tr>
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/device/smoothInfo/listSmoothPlanImpls.html"/>
        </@buttonBar>
    </@ww.form>
    
    
    
	<ul id="beautytab">
	    	<li><a id="ruleItems" href="../../device/smoothInfo/listSmoothPlanImplItems.html" target="frame" class="selectedtab">设备润滑实施明细</a></li>	    		  
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>

 
</@htmlPage>