<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('faultRepair.title')}">
 <base target="_self">
	 <@ww.form name="'faultRepair'" action="'saveFaultRepair'" method="'post'" validate="true">
	 	  <@ww.token name="saveFaultRepairToken"/>
	 	  <#if faultRepair.id?exists>
         	<@ww.hidden name="'faultRepair.id'" value="#{faultRepair.id}"/>
          </#if>
            <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
          <#if faultBill.id?exists>
         	<@ww.hidden name="'faultBill.id'" value="#{faultBill.id}"/>
          </#if>
          <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
          <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	  <@inputTable>
	 	    <tr>
	 	      <td align="right" valign="top">	
	 	  	    <span class="required">*</span>
	 	  	    <label class="label">${action.getText('faultRepair.department')}:</label>
	 	  	  </td>
		      <td>
			    <select name="department.id" valign="center">
			      <@ww.iterator value="departments" id="department">
				    <option value="<@ww.property value="id"/>"><@ww.property value="name"/></option>
			      </@ww.iterator>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
			    <input type="checkbox" id="externalHelpFlag" name="externalHelpFlag" value="true" onclick="changExternalHelpDepartmentStatus();"/><label for="externalHelpFlag" class="label">${action.getText('faultRepair.isExternalHelp')}</label>
		      </td>
		      <@ww.textfield label="'${action.getText('faultRepair.externalHelpDepartment')}'" name="'faultRepair.externalHelpDepartment'" value="'${faultRepair.externalHelpDepartment?if_exists}'" cssClass="'underline'"  disabled="true"/>
		    </tr>
            <tr>
	          <td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('faultRepair.dutyPeople')}:</label></td>
	            <td>
	              <#assign dutyPeopleName=''/>
	              <#if faultRepair.dutyPeople?exists>
	                <#assign dutyPeopleName="${faultRepair.dutyPeople.name}"/>
	              </#if>
	        	  <input type="text" name="dutyPeople.name" 
	        			 class="underline"  value="${dutyPeopleName}"  maxlength="150" size="20" disabled="true"/>
	        	  <label id=""></label>
		    	  <a onClick="dutyPeople_OpenDialog();">
		            <img id="repairPeopleImg" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		          </a>
		          <#if faultRepair.dutyPeople?exists>
		            <@ww.hidden name="'dutyPeople.id'" value="'#{faultRepair.dutyPeople.id?if_exists}'"/>
		          <#else>
		            <@ww.hidden name="'dutyPeople.id'" value="''"/>
		          </#if>
		        </td>
				<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('faultRepair.receiver')}:</label></td>
	            <td>
	              <#assign receiverName=''/>
	              <#if faultRepair.receiver?exists>
	                <#assign receiverName="${faultRepair.receiver.name}"/>
	              </#if>
	        	  <input type="text" name="receiver.name" 
	        			 class="underline"  value="${receiverName}"  maxlength="150" size="20" disabled="true"/>
	        	  <label id=""></label>
		    	  <a onClick="receiver_OpenDialog();">
		            <img id="repairPeopleImg" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		          </a>
		          <#if faultRepair.receiver?exists>
		            <@ww.hidden name="'receiver.id'" value="'#{faultRepair.receiver.id?if_exists}'"/>
		          <#else>
		            <@ww.hidden name="'receiver.id'" value="''"/>
		          </#if>
		        </td>
            </tr>
            <tr>
              <@ww.select label="'${action.getText('faultRepair.repairLevel')}'" 
	 	  	              name="'repairLevel.id'" 
		    			  listKey="id" listValue="value"
		                  list="repairLevels" emptyOption="true" 
		                  required="false" disabled="false">
		      </@ww.select>
		      <@pp.datePicker label="'${action.getText('faultRepair.finishedDate')}'" name="'faultRepair.finishedDate'"
	                          value="'${(faultRepair.finishedDate?string('yyyy-MM-dd'))?if_exists}'" 
	                          cssClass="'underline'" size="15" required="fasle" disabled="false" maxlength="10"/>
            </tr>
            <#--
            <tr>
              <@ww.select label="'${action.getText('faultRepair.sourceType')}'"  name="'sourceType.id'" 
		    			  listKey="realCode" listValue="value"
		                  list="sourceTypes" onchange="'changeBudgeNo();'" 
		                  emptyOption="true" disabled="false"  required="true">
		      </@ww.select>
              <td id="budgetNoTitle" align="right" valign="top" style="display:none">
	 	  	 <span class="required">*</span>
		 	  	<label class="label">${action.getText('faultRepair.budgetNo')}:</label>
		 	  </td>
		      <td id="budgetNoContent" style="display:none">
		        <input type="text" name="faultRepair.budgetNo" 
		        	   class="underline"  value="${faultRepair.budgetNo?if_exists}"  maxlength="150" size="20"/>
			  </td>
            </tr>
            -->
            <@eam2008_FeeSourceTypeSelector toolingDevFlag="${toolingDevFlag?if_exists}"/>
            <tr>
              <@ww.textfield label="'${action.getText('faultRepair.allFee')}'" name="'faultRepair.allFee'" value="'${faultRepair.allFee?if_exists}'" cssClass="'underline'"  disabled="true"/>
            </tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	 	  <#if !(action.isReadOnly())>
	        	 <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	      </#if>
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	     </@buttonBar>
	 </@ww.form>
	 <script language="javascript">
	   window.onload = function() {
	    <#if faultRepair.id?exists>
          document.all.frame.src = "${req.contextPath}/runmaintenance/faultRepair/listRepairItems.html?faultRepair.id=#{faultRepair.id}&preYearFlag=FAULTREPAIR"
	      getObjByNameRe("repairItem").className = "selectedtab";
        </#if>
	     <#if faultRepair.department?exists>
	       document.forms[0].elements["department.id"].value=#{faultRepair.department.id?if_exists};
	     </#if>
	     <#if faultRepair.externalHelpFlag>
	       document.forms[0].elements["externalHelpFlag"].checked = true;
	       document.forms[0].elements["faultRepair.externalHelpDepartment"].disabled = false;
	     </#if>
	     <#if faultRepair.repairLevel?exists>
	       document.forms[0].elements["repairLevel.id"].value=#{faultRepair.repairLevel.id?if_exists};
	     </#if>

	   }
	   /*
	    * 点击外协checkbox触发
	   */
	   function changExternalHelpDepartmentStatus() {
	     if (document.forms[0].elements["externalHelpFlag"].checked) {      //当外协处于选中状态,外协单位可输入
	       document.forms[0].elements["faultRepair.externalHelpDepartment"].disabled = false;
	     } else {                                                          //当外协处于不选中状态,外协单位不可输入
	       document.forms[0].elements["faultRepair.externalHelpDepartment"].disabled = true;
	     }
	   }
	   /*
		*责任人选择
	   */
	   function dutyPeople_OpenDialog() {
	     var url = "${req.contextPath}/popup/userSelector.html";
		 popupModalDialog(url, 800, 600, dutyPeopleSelectorHandler);
	   }
	   function dutyPeopleSelectorHandler(result) {
	     if(null != result) {
		   document.forms[0].elements["dutyPeople.id"].value = result[0];
		   document.forms[0].elements["dutyPeople.name"].value = result[1];
		 } 
	   }
	   /*
	    * 验收人选择
	   */
	   function receiver_OpenDialog() {
	     var url = "${req.contextPath}/popup/userSelector.html";
		 popupModalDialog(url, 800, 600, receiverSelectorHandler);
	   }
	   function receiverSelectorHandler() {
	   	 if(null != result) {
		   document.forms[0].elements["receiver.id"].value = result[0];
		   document.forms[0].elements["receiver.name"].value = result[1];
		 } 
	   }
	   function customize_validate() {
	     //验证部门
	     if ('-1' == document.forms[0].elements["department.id"].value) {
	       alert("${action.getText('department.id.required')}");
	       return false;
	     }
	     //验证外协单位的长度
	     if (document.forms[0].elements["externalHelpFlag"].checked && document.forms[0].elements["faultRepair.externalHelpDepartment"].value != '') {
	       if (!isValidLength(document.forms[0],"faultRepair.externalHelpDepartment",0,50)) {
	         alert("${action.getText("faultRepair.externalHelpDepartment.maxLength")}");
			 return false;
	       }
	     }
	     //验证完成时间格式
	     if ('' != document.forms[0].elements["faultRepair.finishedDate"].value) {
		   if(!isDate("faultRepair.finishedDate")){
		     alert("${action.getText('select.right.faultRepair.finishedDate')}");
			 return false;
		   }
	     }
	     //验证费用来源
	     if(document.forms[0].elements["sourceType"].value==''){
	       alert("${action.getText('select.right.sourceType')}");
	        return false;
	     }
	     return true;
	   }
	 </script>
     <#if faultRepair.id?exists>
     <ul id="beautytab">
	   <li><a id="repairItem" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/faultRepair/listRepairItems.html?faultRepair.id=#{faultRepair.id}&preYearFlag=FAULTREPAIR" target="frame">${action.getText('faultRepair.repairItem')}</a></li>
	   <li><a id="spares" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/faultRepair/listRepairSpares.html?faultRepair.id=#{faultRepair.id}&planProcFlag=PROC&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=FAULTREPAIR" target="frame">${action.getText('faultRepair.spares')}</a></li>
	   <li><a id="repairManHour" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/faultRepair/listRepairManHours.html?faultRepair.id=#{faultRepair.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=FAULTREPAIR" target="frame">${action.getText('faultRepair.repairManHour')}</a></li>
	   <li><a id="repairTool" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/faultRepair/listRepairTools.html?faultRepair.id=#{faultRepair.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=FAULTREPAIR" target="frame">${action.getText('faultRepair.repairTool')}</a></li>
	   <li><a id="repairFees" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/faultRepair/listRepairFees.html?faultRepair.id=#{faultRepair.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=FAULTREPAIR" target="frame">${action.getText('faultRepair.repairFees')}</a></li>
	   <li><a id="deviceDoc"   onclick="activeTab(this);" href="${req.contextPath}/runmaintenance/faultRepair/listRepairDocs.html?faultRepair.id=#{faultRepair.id}&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}&preYearFlag=FAULTREPAIR" target="frame" >${action.getText('faultRepair.repairDoc')}</a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>  
     </#if>
</@htmlPage>