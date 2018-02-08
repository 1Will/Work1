<#include "../../../includes/macros2.ftl" />
<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="保养实施维护 ">

<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='listMaintenanceProcDetail.html';
 		document.getElementById("ProcItems").className = "selectedtab";
 	}
 	
	function activeTab(obj) {
	    var sfEls = document.getElementById("beautytab").getElementsByTagName("li");
	    for (var i=0; i<sfEls.length; i++) {
	    	//alert(sfEls[i].getElementsByTagName("a")[0].id == obj.id);
	      if (sfEls[i].getElementsByTagName("a")[0].id == obj.id) {
	        sfEls[i].getElementsByTagName("a")[0].className = "selectedtab";
	      } else {
	        sfEls[i].getElementsByTagName("a")[0].className = '';
	      }
	    }
	}
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',null,300);
	}
	function tooling_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/toolingSelector.html',600,350);
	}

	
	function user_OpenDialog1() {    //负责人
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler1);
	  	}	
	function userSelectorHandler1(result) {
	  		//document.forms["MaintenanceProc"].elements["manager1.id"].value  = result[0];
	  		document.forms["MaintenanceProc"].elements["manager1.name"].value = result[1];
	  	}

	function user_OpenDialog2() {    //负责人
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler2);
	  	}	
	function userSelectorHandler2(result) {
	  		//document.forms["MaintenanceProc"].elements["manager2.id"].value  = result[0];
	  		document.forms["MaintenanceProc"].elements["manager2.name"].value = result[1];
	  	}
	  	
	function chk_checkbox() {
		if(document.forms["MaintenanceProc"].elements["checkbox"].checked==true){
			document.forms["MaintenanceProc"].elements["Proc.start.date"].disabled=false; 
			document.forms["MaintenanceProc"].elements["Allfee"].disabled=false; 
			document.forms["MaintenanceProc"].elements["result"].disabled=false; 
			document.forms["MaintenanceProc"].elements["project"].disabled=false; 
			}
		else{
			document.forms["MaintenanceProc"].elements["Proc.start.date"].disabled=true; 
			document.forms["MaintenanceProc"].elements["Allfee"].disabled=true; 
			document.forms["MaintenanceProc"].elements["result"].disabled=true; 
			document.forms["MaintenanceProc"].elements["project"].disabled=true; 
			}
	}   	
</script>

    <@ww.form namespace="'/base/area'" name="'MaintenanceProc'" action="''" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
         <@buttonBar>
        	<input type="button" class="button" name="selectRule" value="选择保养计划" />
    	</@buttonBar>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'保养实施编号'" name="'area.code'" value="'jac-wx-025'" cssClass="'underline'" disabled="true" readonly="true" />
	 	  	  	<@ww.textfield label="'保养实施名称'" name="'area.code'" value="" cssClass="'underline'" required="true" disabled="true"/>
        	</tr>
        	<tr>
        		<@ww.textfield label="'保养计划编号'" name="'area.code'" value="'jac-wx-025'" cssClass="'underline'" disabled="true" required="true" readonly="true" />
	 	  	  	<@ww.textfield label="'保养计划名称'" name="'area.code'" value="" cssClass="'underline'" disabled="true"/>
        	</tr>
        	<@toolingSelector/>
            <tr>
            	<td align="right" valign="top"><span class="required">*</span><label class="label">负责人:</label></td>
            	<td>
		 			<input type="text" name="manager1.name" class="underline"  value=""  maxlength="150" disabled="false"/>
		 			<a onClick='user_OpenDialog1()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
		 		<@ww.textfield label="'工序'" name="'working.procedure'" value="" cssClass="'underline'" required="false" disabled="true"/>
		 	</tr>
		 	<tr>
		 		<@ww.textfield label="'保养周期（天）'" name="'cycle'" value="" cssClass="'underline'" required="true" disabled="true"/>
		 		<@ww.textfield label="'保养部门'" name="'cycle'" value="" cssClass="'underline'" required="true" disabled="true"/>
            </tr>
            <tr>
            	<@ww.textarea label="'维护与保养内容'" 
					         name="'state'" 
					         value="" rows="'3'" cols="'50'" 
					         disabled="true"
							 />
			</tr>
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
		 	  	<td align="right" valign="top"><span class="required"></span><label class="label">状态:</label></td>
            	<td>
		 			<input type="checkbox" name="checkbox" value="true" onclick="chk_checkbox()"/>已实施
		 		</td>
		 	</tr>
            <tr>
            	<@pp.datePicker label="'实施执行日期'" name="'Proc.start.date'"
	     			value="" cssClass="'underline'" size="15" required="true" disabled="true"/>
 
               <@ww.textfield label="'实际费用汇总（元）'" name="'Allfee'" value="" cssClass="'underline'" required="false" disabled="true"/>
            </tr>
             <tr>
            	<@ww.textarea label="'实施结果'" 
					         name="'result'" 
					         value="" rows="'3'" cols="'50'" 
					         disabled="true"
							 />
				<@ww.textarea label="'修理方案'" 
					         name="'project'" 
					         value="" rows="'3'" cols="'50'" 
							 disabled="true"
							 />
			</tr>
            
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
	       	<tr>
	    		<@eam2008_RecordLog creator="sa" createdTime="2007-01-01" 
		 			   	  lastOperator="sa" lastModifiedTime="2007-01-01"/>
	 	  	</tr> 
	 	  	<tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
	 	  		<@ww.textfield label="'审核说明'" name="'approv.comment'" value="" cssClass="'underline'" />
		 		<@ww.textfield label="'状态'" name="'state'" value="'" cssClass="'underline'" readonly="true"/>       
        	</tr>
        	<tr>
        		<td align="right" valign="top"><span class="required">*</span><label class="label">审核人:</label></td>
            	<td>
		 			<input type="text" name="manager2.name" class="underline"  value=""  maxlength="150" disabled="false"/>
		 			<a onClick='user_OpenDialog2()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
		 		<@ww.select label="'最终审核人'"
	                    	name="area.code"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    		'',
	                    		'sa',
	                    		'bor'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        		/>
        	</tr>
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url=""/>	
       		<@redirectButton value="${action.getText('submit')}" url="#"/>	
        	<@redirectButton value="${action.getText('back')}" url="listMaintenanceProc.html"/>	
        </@buttonBar>
    </@ww.form>
    
    <ul id="beautytab">
		<li><a id="ProcItems" onclick="activeTab(this);" href="listMaintenanceProcDetail.html" target="frame" class="selectedtab">保养实施明细</a></li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	
</@htmlPage>