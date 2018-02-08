<#include "../../includes/macros2.ftl" />
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="工装标定维护">

<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='listDemarcateHistories.html';
 		getObjByNameRe("planItems").className = "selectedtab";
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
	function tooling_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/toolingSelector.html',600,350);
	}
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html', null, 300);
	}
	
	 function user_OpenDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler);
	  	}	
	function userSelectorHandler(result) {
	  		//document.forms["changeLog"].elements["manager.id"].value  = result[0];
	  		document.forms["demarcate"].elements["manager.name"].value = result[1];
	  	}
</script>

    <@ww.form namespace="'/base/area'" name="'demarcate'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
			 <@ww.select label="'工装类别'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'模具', 
	                    			'夹具', 
	                    			'工位器具',
	                    			'检具',
	                    			'辅具'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>  	
		</tr>
            <@toolingSelector/>
             <tr>
            	<@ww.textfield label="'所在位置'"  name="'area.code'" value="" cssClass="'underline'" readonly="" required="false"/>
            	<td align="right" valign="top"><span class="required">*</span><label class="label">负责人:</label></td>
            	<td>
		 			<input type="text" name="manager.name" class="underline"  value=""  maxlength="150" disabled="false"/>
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
            </tr>
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            	<@ww.textfield label="'制造单位'"  name="'area.code'" value="" cssClass="'underline'" readonly="" required="false"/>
            	<@ww.textfield label="'使用单位'"  name="'area.code'" value="" cssClass="'underline'" readonly="" required="false"/>
            </tr>
            <tr>
            	 <@ww.textfield label="'标定周期'" name="'period'" value="" cssClass="'underline'"  required="false"/>
				 <@ww.select label="'标定结果'"
	                    	name="result"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'合格', 
	                    			'不合格'
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>  
				 				 
            </tr>
            <tr>
            	 <@pp.datePicker label="'本次标定日期'" name="'demarcate.time1'"
	     			value="" cssClass="'underline'" size="15" required="true"/>
	     		 <@pp.datePicker label="'下次标定日期'" name="'demarcate.time2'"
	     			value="" cssClass="'underline'" size="15" required="true"/>
	     	</tr>   
	     	<tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
           	<tr>
	    		<@eam2008_RecordLog creator="sa" createdTime="2007-01-01" 
		 			   	  lastOperator="sa" lastModifiedTime="2007-01-01"/>
	 	  	</tr> 
	 	  	
        </@inputTable>
        
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="listChangeLogs.html"/>
        	<@redirectButton value="${action.getText('back')}" url="listDemarcates.html"/>	
        </@buttonBar>
    </@ww.form>
    
    <ul id="beautytab">
		<li><a id="planItems" onclick="activeTab(this);" href="editDemarcatePlan.html" target="frame" class="selectedtab">变更历史记录</a></li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	
</@htmlPage>