<#include "../../includes/macros2.ftl" />
<@htmlPage title="工装领用维护">

<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='listRequisitionLogHistories.html';
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
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',null,300);
	}
	function tooling_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/toolingSelector.html',600,350);
	}
	function eam2008_device_OpenDialog(url) {
		popupModalDialog(url, 800, 600, refresh_device_information);
	}
	function refresh_device_information (result) {
	   document.forms[0].elements["device.name"].value = result[1]; 
	   //document.forms[0].elements["device.deviceNo"].value = result[2]; 
	}
	function chk_checkbox() {
	if(document.forms["requisitionLog"].elements["checkbox"].checked==true){
		document.forms["requisitionLog"].elements["state"].disabled=false; 
		document.forms["requisitionLog"].elements["content"].disabled=false; 
		document.forms["requisitionLog"].elements["repair.estimate.time0"].disabled=false; 
		document.forms["requisitionLog"].elements["repair.estimate.time1"].disabled=false; 
		}
	else{
		document.forms["requisitionLog"].elements["state"].disabled=true; 
		document.forms["requisitionLog"].elements["content"].disabled=true;
		document.forms["requisitionLog"].elements["repair.estimate.time0"].disabled=true; 
		document.forms["requisitionLog"].elements["repair.estimate.time1"].disabled=true; 
		}
	} 
	
	function user_OpenDialog() {    //验收人
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler);
	  	}	
	function userSelectorHandler(result) {
	  		//document.forms["requisitionLog"].elements["manager.id"].value  = result[0];
	  		document.forms["requisitionLog"].elements["manager.name"].value = result[1];
	  	}
	
	function user_OpenDialog1() {    //验收人
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler1);
	  	}	
	function userSelectorHandler1(result) {
	  		//document.forms["requisitionLog"].elements["manager1.id"].value  = result[0];
	  		document.forms["requisitionLog"].elements["manager1.name"].value = result[1];
	  	}
	  	
	function user_OpenDialog2() {	//保管员
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler2);
	  	}	
	function userSelectorHandler2(result) {
	  		//document.forms["requisitionLog"].elements["manager2.id"].value  = result[0];
	  		document.forms["requisitionLog"].elements["manager2.name"].value = result[1];
	  	}
</script>

    <@ww.form namespace="'/base/area'" name="'requisitionLog'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'领用单编号'" name="'area.code'" value="'0001'" cssClass="'underline'" disabled="true" readonly="true" />
	 	  	  	<@ww.textfield label="'领用单名称'" name="'area.code'" value="" cssClass="'underline'" required="true" />
        	</tr>
        	<@toolingSelector/>
        	 <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            	<td align="right" valign="top"><span class="required">*</span><label class="label">领用人:</label></td>
            	<td>
		 			<input type="text" name="manager.name" class="underline"  value=""  maxlength="150" disabled="false"/>
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
                <@pp.datePicker label="'领用日期'" name="'repair.estimate.time2'"
	     			value="" cssClass="'underline'" size="15" required="true"/>
            </tr>
            <tr>
            	<td align="right" valign="top"><span class="required">*</span><label class="label">使用设备:</label></td>
            	<td>
	            	<input type="text" name="device.name" class="underline"  value=""  maxlength="150" disabled/>
			 		<a onClick='eam2008_device_OpenDialog("${req.contextPath}/popup/deviceSelector.html")'>
		 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		 			</a>
		 		</td>
            	<@ww.textfield label="'生产数量(件、台)'"  name="'area.code'" value="" cssClass="'underline'" required="false"/>
            </tr>
             <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
		 	  <tr>
		 	  	<td align="right" valign="top"><span class="required"></span><label class="label">领用状态:</label></td>
            	<td>
		 			<input type="checkbox" name="checkbox" value="true" onclick="chk_checkbox()"/>已归还
		 		</td>
		 	  </tr>
            <tr>
            	<@ww.textarea label="'产品尾件状况'" 
					         name="'state'" 
					         value="" rows="'3'" cols="'50'" 
					         disabled="true"
							 />
				<@ww.textarea label="'维修保养内容'" 
					         name="'content'" 
					         value="" rows="'3'" cols="'50'" 
					         disabled="true"
							 />
			</tr>
            <tr>
            	<td align="right" valign="top"><span class="required">*</span><label class="label">验收人:</label></td>
            	<td>
		 			<input type="text" name="manager1.name" class="underline"  value=""  maxlength="150" disabled="false"/>
		 			<a onClick='user_OpenDialog1()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
                <@pp.datePicker label="'验收日期'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15" required="false" disabled="true"/>
            </tr>
            <tr>
            	<td align="right" valign="top"><span class="required">*</span><label class="label">保管员:</label></td>
            	<td>
		 			<input type="text" name="manager2.name" class="underline"  value=""  maxlength="150" disabled="false"/>
		 			<a onClick='user_OpenDialog2()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
                <@pp.datePicker label="'入库日期'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15" required="false" disabled="true"/>
            </tr>          
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="listRequisitionLogs.html"/>	
        	<@redirectButton value="${action.getText('back')}" url="listRequisitionLogs.html"/>	
        </@buttonBar>
    </@ww.form>
    
    <ul id="beautytab">
		<li><a id="planItems" onclick="activeTab(this);" href="listRequisitionLogHistories.html" target="frame" class="selectedtab">领用历史记录</a></li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	
</@htmlPage>