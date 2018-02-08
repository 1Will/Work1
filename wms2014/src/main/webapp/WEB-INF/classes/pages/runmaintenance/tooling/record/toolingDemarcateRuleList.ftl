<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingDemarcateRuleSearch.title')}">
  <@ww.form namespace="'/runmaintenance/tooling/record'" name="'toolingDemarcateRule'" action="'searchToolingDemarcateRules'" method="'post'">
  <@ww.token name="searchToolingFaultBillsToken"/>
  	     <#include "toolingDemarcateRuleSearcher.ftl"/>
  	     <input type="hidden" name="alterToolingDemacrateCycle" value=""/>
  	     <input type="hidden" name="alterToolingManager" value=""/>
  	     <input type="hidden" name="managerCurrentLoopNum" value=""/>
         <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
         </@buttonBar> 
         <#assign managerLoopNum = 0/>  
         <@list title="${action.getText('demarcateRule.list')}" 
            includeParameters="toolingNo|toolingName|toolingGraphNo|department.id" 
        	fieldMap="like:toolingNo|toolingName|toolingGraphNo" >
        	<input type="hidden" name="toolingIds" value="#{object.id}"/>
            <@vcolumn title="${action.getText('tooling.no')}" property="deviceNo" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.graphNo')}" property="graphNo" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.name')}" property="name" sortable="desc">
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name">
                 <@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('tooling.Manager')}" property="borrower"/>
            -->
            <@vcolumn title="${action.getText('tooling.Manager')}">
	        	<#assign managerName = ''/>
				<#if object.manager?exists>
				 <#assign managerName = "${object.manager.name}" />
				</#if>
	        		<input type="text" name="manager.name" 
	        			class="underline"  value="${managerName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="slectManager(${managerLoopNum});">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        <#assign managerId = ''/>
				<#if object.manager?exists>
				 	<#assign managerId = "${object.manager.id}" />
				</#if>
				<input type="hidden" name="manager.id" value="${managerId}" /> 
				<#assign managerLoopNum = managerLoopNum +1/>				
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.preDemarcateTime')}" property="preDemarcateTime" format="yyyy-MM-dd">
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('tooling.demarcateCycle')}">
              <input type="text" name="demarcateCycle" value="${object.demarcateCycle?if_exists}" id="demarcateCycle" class="alignRightInput" />
              <input type="hidden" name="hiddenToolingName" value="${object.name?if_exists}"/>
               <@alignLeft/>
            </@vcolumn>
        </@list>  
	    <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('tooling.faultBill')}?" />
            <@vsubmit name="'save'" value="'${action.getText('save')}'">
                <@ww.param name="'onclick'" value="'return demarcateCycleValidate();'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
	 <script language="JavaScript" type="text/JavaScript"> 
	   /*
	    * 验证标定周期是否输入值
	    *
	   */
       function demarcateCycleValidate() {
         var allDemarcateCycle = document.getElementsByName("demarcateCycle");
         var allToolingName = document.getElementsByName("hiddenToolingName");
         for (var i=0,j=0; (i<allDemarcateCycle.length)||(j<allToolingName.length); i++,j++) {
           if ('' == allDemarcateCycle[i].value) {
             alert("${action.getText('input.tooling')} " + allToolingName[j].value + " ${action.getText('tooling.demarcateCycle')}");
             return false;
           }
         }
         retrieveDemarcateCycleText();
         retrieveToolingManagerText();
         checkInvalidParms();
         return true;
       }
       
       /*
        * 把toolingIds标签和demarcateCycle标签的值存入数组中，在把数组赋给alterToolingDemacrateCycle标签
        *
       */
       function retrieveDemarcateCycleText() {
          var allDemarcateCycle = document.getElementsByName("demarcateCycle");
          var allToolingId = document.getElementsByName("toolingIds");
          var ary = new Array();
          for (var i=0; i<allToolingId.length; i++) {
            ary.push(allToolingId[i].value);
            ary.push(allDemarcateCycle[i].value);
          }
          document.forms[0].elements["alterToolingDemacrateCycle"].value=ary;
         
       }
       function retrieveToolingManagerText() {
          var allManager = document.getElementsByName("manager.id");
          var allToolingId = document.getElementsByName("toolingIds");
          var ary = new Array();
          for (var i=0; i<allToolingId.length; i++) {
            ary.push(allToolingId[i].value);
            ary.push(allManager[i].value);
          }
          document.forms[0].elements["alterToolingManager"].value=ary;
         
       }
	   function manager_OpenDialog() {
	     var url = "${req.contextPath}/popup/userSelector.html";
		 popupModalDialog(url, 800, 600, managerSelectorHandler);
	   }
	   function managerSelectorHandler(result) {
	     var allManagerId = document.getElementsByName("manager.id");
         var allManagerName = document.getElementsByName("manager.name");
         var currentRowNum = document.forms["toolingDemarcateRule"].elements["managerCurrentLoopNum"].value;
         allManagerId[currentRowNum].value = result[0];
         allManagerName[currentRowNum].value = result[1];
	   }
	   function slectManager(managerLoopNum) {
	     document.forms["toolingDemarcateRule"].elements["managerCurrentLoopNum"].value = managerLoopNum;
	     manager_OpenDialog();
	   }
	   
	</script>
  </@ww.form>
</@htmlPage>