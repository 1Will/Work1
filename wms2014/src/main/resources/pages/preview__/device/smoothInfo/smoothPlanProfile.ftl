<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="设备润滑计划维护">

<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='../../device/smoothInfo/listSmoothPlanItems.html';
 		//getObjByNameRe("ruleItems").className = "selectedtab";
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
	function checkPointRule_openDialog() {
		popupModalDialog('${req.contextPath}/popup/smoothRuleSelector.html',1024,800);
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
                <@ww.textfield label="'润滑计划名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="true"/>
            </tr>
            <@deviceSelector2 img="display"/>
             <tr>
             	<@dept1 />
             	<td align="right" valign="top"><label class="label"><font color="red">*</font>编制人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            </tr>
            <tr>
            	 <@pp.datePicker label="'编制日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true"/>
			</tr>
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/device/smoothInfo/listSmoothPlans.html"/>
        </@buttonBar>
    </@ww.form>
    
    
    
	<ul id="beautytab">
	    	<li><a href="../../device/smoothInfo/listSmoothPlanItems.html" target="frame" class="selectedtab">润滑计划明细</a></li>	    		  
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>

 
</@htmlPage>