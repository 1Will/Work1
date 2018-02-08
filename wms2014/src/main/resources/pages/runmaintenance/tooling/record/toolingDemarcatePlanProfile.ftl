<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingDemarcatePlanEdit.title')}">
    <@ww.form namespace="'/runmaintenance/tooling/record'" name="'toolingDemarcatePlan'" action="'saveToolingDemarcatePlan'" method="'post'" validate="true">
        <@ww.token name="saveToolingDemarcatePlanToken"/>
        <#if toolingDemarcatePlan.id?exists>
          <@ww.hidden name="'toolingDemarcatePlan.id'" value="${toolingDemarcatePlan.id?if_exists}"/>
        </#if>
        <input type="hidden" name="allToolingDemarcateManager" value=""/>
        <input type="hidden" name="allThisDemarcateDateTm" value=""/>
        <input type="hidden" name="managerCurrentLoopNum" value=""/>
        <input type="hidden" name="newDemarcateRuleIds" value=""/>
        <input type="hidden" name="allDemarcateDetailComment" value=""/>
        <input type="hidden" name="allDemarcateResult" value=""/>
        <input type="hidden" name="addOrUpdateDetail" value=""/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('demarcate.planNo')}'" name="'toolingDemarcatePlan.planNo'" value="'${toolingDemarcatePlan.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('demarcate.planName')}'" name="'toolingDemarcatePlan.planName'" value="'${toolingDemarcatePlan.planName?if_exists}'" cssClass="'underline'" required="true" />
        	</tr>
        	<tr>
                <@pp.datePicker label="'${action.getText('demarcate.planStartTime')}'" name="'toolingDemarcatePlan.planStartTime'"
	     					value="'${(toolingDemarcatePlan.planStartTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
        		<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		        value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		                    list="departments" emptyOption="true" disabled="false">
		        </@ww.select> 	
        	</tr>
        	<tr>
	        	<@ww.textarea  label="'${action.getText('comment')}'" 
	        	         name="'toolingDemarcatePlan.comment'" 
	        	         value="'${toolingDemarcatePlan.comment?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="false" required="false"/>
	        <#if toolingDemarcatePlan.id?exists>
	        	<td align="right" valign="top"></td>
            	<td>
		 			<input type="checkbox" name="enterDemarcateResult" value="check" onclick="changeDemarcateResultStatus()"/>${action.getText('enter.implement')}
		 		</td>
		 	</#if>	        	
        	</tr>
        	<tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
	        	<#assign assessorName = ''/>
				<#if toolingDemarcatePlan.assessor?exists>
				 <#assign assessorName = "${toolingDemarcatePlan.assessor.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('demarcate.assessor')}:</label></td>
	        	<td>
	        		<input type="text" name="assessor.name" 
	        			class="underline"  value="${assessorName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="assessor_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign assessorId = ''/>
				<#if toolingDemarcatePlan.assessor?exists>
				 	<#assign assessorId = "${toolingDemarcatePlan.assessor.id}" />
				</#if>
				<input type="hidden" name="assessor.id" value="${assessorId}" />

	        	<#assign organizerName = ''/>
				<#if toolingDemarcatePlan.organizer?exists>
				 <#assign organizerName = "${toolingDemarcatePlan.organizer.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('demarcate.organizer')}:</label></td>
	        	<td>
	        		<input type="text" name="organizer.name" 
	        			class="underline"  value="${organizerName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="organizer_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign organizerId = ''/>
				<#if toolingDemarcatePlan.organizer?exists>
				 	<#assign organizerId = "${toolingDemarcatePlan.organizer.id}" />
				</#if>
				<input type="hidden" name="organizer.id" value="${organizerId}" />
            </tr>
            <tr>
	        	<#assign ratifierName = ''/>
				<#if toolingDemarcatePlan.ratifier?exists>
				 <#assign ratifierName = "${toolingDemarcatePlan.ratifier.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('demarcate.ratifier')}:</label></td>
	        	<td>
	        		<input type="text" name="ratifier.name" 
	        			class="underline"  value="${ratifierName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="ratifier_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign ratifierId = ''/>
				<#if toolingDemarcatePlan.ratifier?exists>
				 	<#assign ratifierId = "${toolingDemarcatePlan.ratifier.id}" />
				</#if>
				<input type="hidden" name="ratifier.id" value="${ratifierId}" />            
            </tr>
        </@inputTable>
        <@buttonBar>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	   
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/tooling/record/listToolingDemarcatePlans.html"/>
	      <#--
	      <input type="button" name="back" value="${action.getText('back')}" class="button" onclick="return backButtonValidate();"/>
	      -->
	    </@buttonBar>
       <#--
           以下用于显示所有标定详细的列表
       -->
        <#assign loopNum = 0/>  
        <#assign demarcateResultIdentity = 'demarcateResult' + '${loopNum}'/> 
        <#assign thisDemarcateDatetmIdentityName = 'demarcate.thisDateTm' + '${loopNum}'/> 
        <#assign thisDemarcateDatetmImgIdentity = 'toolingDemarcatePlan_' + '${thisDemarcateDatetmIdentityName}' +' _trigger'/>
         <@list title="${action.getText('ToolingDemarcatePlanDetail.list')}" 
            includeParameters="" 
        	fieldMap="" >
        	<input type="hidden" name="demarcateDetailIds" value="#{object.id}"/>
        	<input type="hidden" name="hiddenToolingName" value="${object.tooling.name}"/>
        	<@vlh.checkbox property="id" name="demarcatePlanIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('tooling.no')}" property="tooling.deviceNo" sortable="desc"/>
            <@vcolumn title="${action.getText('tooling.graphNo')}" property="tooling.graphNo" sortable="desc"/>
            <@vcolumn title="${action.getText('tooling.name')}" property="tooling.name" sortable="desc"/>
            <@vcolumn title="${action.getText('tooling.madeDepartment')}" property="tooling.supplier.name"/>
            <@vcolumn title="${action.getText('department')}" property="tooling.department.name"/>
            <@vcolumn title="${action.getText('tooling.Manager')}">
	        	<#assign managerName = ''/>
				<#if object.manager?exists>
				 <#assign managerName = "${object.manager.name}" />
				</#if>
	        		<input type="text" name="manager.name" 
	        			class="underline"  value="${managerName}"  maxlength="150" size="10" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="slectManager(${loopNum});">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        <#assign managerId = ''/>
				<#if object.manager?exists>
				 	<#assign managerId = "${object.manager.id}" />
				</#if>
				<input type="hidden" name="manager.id" value="${managerId}" /> 
				<#assign loopNum = loopNum +1/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.thisDemarcateTime')}">
              <#assign thisDemarcateDateTm = ''/>
              <#if object.thisDemarcateDateTm?exists>
                <#assign thisDemarcateDateTm = "${(object.thisDemarcateDateTm?string('yyyy-MM-dd'))}"/>
              </#if>
               <@eam2008_dataPicker inputName="${thisDemarcateDatetmIdentityName}" inputId="${thisDemarcateDatetmIdentityName}" inputValue="${thisDemarcateDateTm}" imgId="${thisDemarcateDatetmImgIdentity}" formName="toolingDemarcatePlan"/>
              <#--
		      <input type="text"
                     name="${thisDemarcateDatetmIdentityName}"
                     size="10"                    
                     value="${thisDemarcateDateTm}"                     
                     id="${thisDemarcateDatetmIdentityName}"                        
                     class="underline"     disabled  />                    
              <img id="${thisDemarcateDatetmImgIdentity}" src="/eam2008/images/calendar/calendar.gif" align=absMiddle border=0 />
              <script language="javascript">
                Calendar.setup({
                               formName       :    "toolingDemarcatePlan",
                               inputField     :    "${thisDemarcateDatetmIdentityName}",
                               button         :    "${thisDemarcateDatetmImgIdentity}",
                               ifFormat       :    "%Y-%m-%d",
                               showsTime      :    false,
                               timeFormat     :    "24",
                               showOthers     :    true
                              });
              </script>
              -->
             <#assign thisDemarcateDatetmIdentityName = 'demarcate.thisDateTm'+'${loopNum}'/> 
             <#assign thisDemarcateDatetmImgIdentity = 'toolingDemarcatePlan_' + '${thisDemarcateDatetmIdentityName}' +' _trigger'/>         
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.demarcateCycle')}" property="tooling.demarcateCycle"/>
            <@vcolumn title="${action.getText('demarcate.result')}">
		      <select name="${demarcateResultIdentity}" id="saveToolingDemarcatePlan_demarcateStatus" disabled="true">       
		        <option value="" ></option>       
		        <option value="PASS">${action.getText('demarcate.pass')}</option>       
		        <option value="NOPASS">${action.getText('demarcate.noPass')}</option>              
		      </select>
		      <script language="javascript">
		        <#if object.demarcateResult?exists>
		          document.forms[0].elements["${demarcateResultIdentity}"].value='${object.demarcateResult?if_exists}';
		        </#if>
		      </script>
		      <#assign demarcateResultIdentity = 'demarcateResult' + '${loopNum}'/> 
            </@vcolumn>
            <@vcolumn title="${action.getText('demarcate.comment')}">
              <input type="text" name="demarcateComment" value="${object.comment?if_exists}"  class="underline"  />
            </@vcolumn>
        </@list>
        <#if !first>
          <@buttonBar>
	        	<@vbutton class="button" value="${action.getText('new')}" onclick="popupModalDialog('${req.contextPath}/popup/demarceteRuleSelector.html',800,600,addNewDemarcateRule);"/>
	        	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('tooling.demarcatePlanDetail')}?" />
                <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                  <@ww.param name="'onclick'" value="'return confirmDeletes(\"demarcatePlanIds\", \"${confirmMessage}\")'"/>
                  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
                </@vsubmit>
	     </@buttonBar>  
	    </#if> 

      <select name="demarcateRuleIds" multiple="multiple" style="display:none" />
	 </@ww.form>
	 <script language="javascript">
	   window.onload = function() {
	     <#if (toolingDemarcatePlan.department)?exists>
		   document.forms[0].elements["department.id"].value = '${(toolingDemarcatePlan.department.id)?if_exists}';
		 </#if>
	   }
	   <#--
	   function backButtonValidate() {
	     if (${loopNum} != 0) {
	       if (!managerAndThisDemarcateDateTmValidate()) { 
	         return false;
	       }
	     }
	     location='${req.contextPath}/runmaintenance/tooling/record/listToolingDemarcatePlans.html';
	     return true;
	   }
	   -->
	   function assessor_OpenDialog() {
	     var url = "${req.contextPath}/popup/userSelector.html";
		 popupModalDialog(url, 800, 600, assessorSelectorHandler);
	   }
	   function assessorSelectorHandler(result) {
	     document.forms["toolingDemarcatePlan"].elements["assessor.id"].value = result[0];
		 document.forms["toolingDemarcatePlan"].elements["assessor.name"].value = result[1];
	   }
	   function organizer_OpenDialog() {
	     var url = "${req.contextPath}/popup/userSelector.html";
		 popupModalDialog(url, 800, 600, organizerSelectorHandler);
	   }
	   function organizerSelectorHandler() {
	   	 document.forms["toolingDemarcatePlan"].elements["organizer.id"].value = result[0];
		 document.forms["toolingDemarcatePlan"].elements["organizer.name"].value = result[1];
	   }
	   function ratifier_OpenDialog() {
	     var url = "${req.contextPath}/popup/userSelector.html";
		 popupModalDialog(url, 800, 600, ratifierSelectorHandler);
	   }
	   function ratifierSelectorHandler() {
	   	 document.forms["toolingDemarcatePlan"].elements["ratifier.id"].value = result[0];
		 document.forms["toolingDemarcatePlan"].elements["ratifier.name"].value = result[1];
	   }
	   /*
	    * 回调函数，用于获取通过弹出页面选择的值
	    * result : 存放选择的值
	   */
	   function addNewDemarcateRule(result) {
	     var ruleIds = result.substring(0,result.lastIndexOf(","));
         document.forms[0].elements["newDemarcateRuleIds"].value = ruleIds;
         document.forms[0].elements["addOrUpdateDetail"].value = "ADD_DETAIL";       //添加列表字段的标识，ADD_DETAIL表示添加   
         document.forms[0].submit();
	   }
	   /*
	    * 点击保存按钮时，验证页面字段是否为空、是否长度溢出、获取计划详细列表的字段的值
	   */
	   function customize_validate() {
	     var toolingDemarcatePlanId = document.forms[0].elements["toolingDemarcatePlan.id"].value;
	     if (!planStartTime()) {
	       return false;
	     }
	     if ('' == document.forms[0].elements["assessor.id"].value) {
	       alert("${action.getText("select.assessor")}");
	       return false;
	     }
	     if ('' == document.forms[0].elements["organizer.id"].value) {
	       alert("${action.getText("select.organizer")}");
	       return false;
	     }
	     if ('' == document.forms[0].elements["ratifier.id"].value) {
	       alert("${action.getText("select.ratifier")}");
	       return false;
	     }
         if (${loopNum} != 0){
	        if (!managerAndThisDemarcateDateTmValidate()) { 
	          return false;
	        }
	        retrieveToolingManagerText();                      //获取列表中所有负责人的字段的值
	        retrieveThisDemarcateDateTmText();                 //获取列表中所有本次点检日期的值
	        if (document.forms[0].elements["enterDemarcateResult"].checked == true) {
	          retrieveDemarcateResultText();                       //获取列表中验证结果字段的值
	        }
	        retrieveDemarcateDetailCommentText();              //获取列表中备注字段的值

	        document.forms[0].elements["addOrUpdateDetail"].value = "UPDATE_DETAIL";      //更新列表字段的标识，UPDATE_DETAIL表示更新   
	     }
	     return true;
	   }
	   function manager_OpenDialog() {
	     var url = "${req.contextPath}/popup/userSelector.html";
		 popupModalDialog(url, 800, 600, managerSelectorHandler);
	   }
	   function managerSelectorHandler(result) {
	     var allManagerId = document.getElementsByName("manager.id");
         var allManagerName = document.getElementsByName("manager.name");
         var currentRowNum = document.forms["toolingDemarcatePlan"].elements["managerCurrentLoopNum"].value;
         allManagerId[currentRowNum].value = result[0];
         allManagerName[currentRowNum].value = result[1];
	   }
	   function slectManager(managerLoopNum) {
	     document.forms["toolingDemarcatePlan"].elements["managerCurrentLoopNum"].value = managerLoopNum;
	     manager_OpenDialog();
	   }
	   /*
	    * 获取列表中负责人字段所有的值
	   */
       function retrieveToolingManagerText() {
          var allManager = document.getElementsByName("manager.id");
          var allDemarcateDetailId = document.getElementsByName("demarcateDetailIds");
          var ary = new Array();
          for (var i=0; i<allDemarcateDetailId.length; i++) {
            ary.push(allDemarcateDetailId[i].value);
            ary.push(allManager[i].value);
          }
          document.forms[0].elements["allToolingDemarcateManager"].value=ary;
       }
       /*
        * 获取列表中本次标定日期的所有值
       */
       function retrieveThisDemarcateDateTmText() {
         var allDemarcateDetailId = document.getElementsByName("demarcateDetailIds");
         var ary = new Array();
         for (var i=0; i<allDemarcateDetailId.length; i++) {
          var thisDemarcateDateTmTagName = 'demarcate.thisDateTm';
           thisDemarcateDateTmTagName = thisDemarcateDateTmTagName + i; 
           ary.push(allDemarcateDetailId[i].value);
           ary.push(document.forms[0].elements[thisDemarcateDateTmTagName].value);
         }
         document.forms[0].elements["allThisDemarcateDateTm"].value = ary;
       }
       
       /*
        * 获取列表中备注的所有值
       */
       function retrieveDemarcateDetailCommentText() {
         var allDemarcateDetailComment = document.getElementsByName("demarcateComment");
         var allDemarcateDetailId = document.getElementsByName("demarcateDetailIds");
         var ary = new Array();
         for (var i=0; i<allDemarcateDetailId.length; i++) {
           if ('' != allDemarcateDetailComment[i].value) {
             ary.push(allDemarcateDetailId[i].value);
             ary.push(allDemarcateDetailComment[i].value);
           }
         }
         document.forms[0].elements["allDemarcateDetailComment"].value = ary;
       }
       /*
        *验证列表中负责人和本次标定时间是否都有值
       */
       function managerAndThisDemarcateDateTmValidate() {
        var allDemarcateDetailId = document.getElementsByName("demarcateDetailIds");
         var allManager = document.getElementsByName("manager.id");
         var allToolingName = document.getElementsByName("hiddenToolingName");
         
         for (var i=0,j=0; (i<allManager.length)||(j<allToolingName.length); i++,j++) {
           var thisDemarcateDateTmTagName = 'demarcate.thisDateTm';
           if ('' == allManager[i].value) {
             alert("${action.getText('input.tooling')} " + allToolingName[j].value + " ${action.getText('tooling.Manager')}")
             return false;
           }
           thisDemarcateDateTmTagName = thisDemarcateDateTmTagName + i;
           if ('' == document.forms[0].elements[thisDemarcateDateTmTagName].value) {
             alert("${action.getText('input.tooling')} " + allToolingName[j].value + " ${action.getText('tooling.preDemarcateTime')}");
             return false;
           }
         }
         return true;
       }
       /*
        * 获取列表中验证结果的所有值
       */
       function retrieveDemarcateResultText() {
         var allDemarcateDetailId = document.getElementsByName("demarcateDetailIds");
         var ary = new Array();
         for (var i=0; i<allDemarcateDetailId.length; i++) {
           var demarcateResultTagName = 'demarcateResult';
           demarcateResultTagName = demarcateResultTagName + i; 
          <#-- if ('' != document.forms[0].elements[demarcateResultTagName].value) {-->
             ary.push(allDemarcateDetailId[i].value);
             ary.push(document.forms[0].elements[demarcateResultTagName].value);
        <#--   }-->
         }
         document.forms[0].elements["allDemarcateResult"].value = ary;
       }
       /*
        *改变列表中标定结果的字段的输入状态
       */
       function changeDemarcateResultStatus() {
         var allDemarcateResult = document.getElementsByName("demarcateResult");
         var allDemarcateDetailId = document.getElementsByName("demarcateDetailIds");
         if (document.forms[0].elements["enterDemarcateResult"].checked == true) {
           for (var i=0; i<allDemarcateDetailId.length; i++) {
             var demarcateResultTagName = 'demarcateResult';
             demarcateResultTagName = demarcateResultTagName + i; 
             document.forms[0].elements[demarcateResultTagName].disabled = false;
           }
         } else {
           for (var i=0; i<allDemarcateDetailId.length; i++) {
             var demarcateResultTagName = 'demarcateResult';
             demarcateResultTagName = demarcateResultTagName + i; 
             document.forms[0].elements[demarcateResultTagName].disabled = true;
           }
         }
       }
       /*
        *验证标定计划是否为空、格式是否为日期型
       */
       function planStartTime() {
         if ('' == document.forms[0].elements["toolingDemarcatePlan.planStartTime"].value) {
           alert("${action.getText('select.demarcate.planStartTime')}");
           return false;
         }
         if (!isDate("toolingDemarcatePlan.planStartTime")) {
	       alert("${action.getText('demarcate.planStartTime')}" + "," +"${action.getText('dateFormate.error')}");
	       return false;
	     }
	     return true;
       }
	 </script>
</@htmlPage>