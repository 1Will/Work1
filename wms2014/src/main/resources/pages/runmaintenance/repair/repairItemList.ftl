<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->
<#-- $Id: -->

<#include "../../includes/eam2008.ftl" />

<@framePage title="${action.getText('item.title')}">
     <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
     <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
     <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
     <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
     <@ww.form name="'repairItem'" action="'searchPreRepairPlanItems'" method="'post'">
     <@ww.token name="searchPreRepairPlanItemsToken"/>
     	<#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			<@ww.hidden name="'repairPlanOrProcDetail.id'" value="'#{repairPlanOrProcDetail.id?if_exists}'"/>
     		<#else>
     			<@ww.hidden name="'preRepairPlanDetail.id'" value="'#{preRepairPlanDetail.id?if_exists}'"/>
     		</#if>
     	</#if>
       <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
       <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
       <@ww.hidden name="'currentRowNum'" value=""/>
       <@ww.hidden name="'allProcExecPeople'" value=""/>
       <@ww.hidden name="'allRepairItemProcCompleteDate'" value=""/>
       <@ww.hidden name="'allRepairItemComment'" value=""/>
       <@ww.hidden name="'allRepairItemId'" value=""/>
       <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	   <#assign itemNo=1/>
	   <#assign loopNum=0/>
	   <#assign listNoRecords = true/>
	   <#assign procCompleteDateIdentityName = 'procCompleteDate' + '${itemNo}'/>
	   <#assign procCompleteDateImgIdentity = 'repairItem_' + '${procCompleteDateIdentityName}' + '_trigger'/>
	   <@list title="" excel=false setupTable=false 
	          includeParameters="preRepairPlanDetail.id|readOnly|repairPlanOrProcDetail.id|planProcFlag|preYearFlag" 
        	  fieldMap="like:" >
         <@ww.hidden name="'repairItemIds'" value="'#{object.id}'"/>
         <#if planProcFlag?exists>
           <#if (planProcFlag=='PLAN')>
	         <@vlh.checkbox property="id" name="itemIds">
		       <@vlh.attribute name="width" value="30" />
		     </@vlh.checkbox>
		   </#if>
		 </#if>
		 <@vcolumn title="${action.getText('repairItem.serialNo')}">
	       <#if planProcFlag?exists>
             <#if (planProcFlag=='PLAN')>
               <#if preYearFlag?exists>
			     <#if preYearFlag=='YEAR'>
			       <a href="#" onclick="item_openDialog(#{repairPlanOrProcDetail.id},#{object.id});return false">${itemNo}</a>
				 <#else>
				   <a href="#" onclick="item_openDialog(#{preRepairPlanDetail.id},#{object.id});return false">${itemNo}</a>
			     </#if>
			   </#if>
             <#else>
               ${itemNo}  
             </#if>
           </#if>
	       <@alignCenter/>
	     </@vcolumn>
	     <#assign itemNo = itemNo+1/>
	     <@vcolumn title="${action.getText('repairItem.position')}" property="position">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairItem.content')}" property="content">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairItem.aimRequire')}" property="aimRequire">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairItem.execPeople')}" property="execPeopleString">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairItem.planCompleteDate')}" property="planCompleteDate" format="yyyy-MM-dd">
	       <@alignCenter/>
         </@vcolumn>
         <#if planProcFlag?exists>
         <#if (planProcFlag=='PROC')>
         	<@vcolumn title="${action.getText('repairItem.procExecPeople')}">
	             <input type="text" name="procExecPeopleString" 
			      class="underline"  value="${object.procExecPeopleString?if_exists}"  maxlength="50" size="15"/>
			      <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            </@vcolumn>
         <#--
	     <@vcolumn title="${action.getText('repairItem.procExecPeople')}">
	       <#assign procExecPeopleName = ''/>
		     <#if object.procExecPeople?exists>
			   <#assign procExecPeopleName = "${object.procExecPeople.name}" />
			 </#if>
    		 <input type="text" name="procExecPeople.name" 
    			    class="underline"  value="${procExecPeopleName}"  maxlength="150" size="10" disabled="false"/>
    		 <label id=""></label>
    		 <a onClick="slectProcExecPeople(${loopNum});">
        		 <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 style="display:inline"/>
        	 </a>
	         <#assign procExecPeopleId = ''/>
			 <#if object.procExecPeople?exists>
			 	<#assign procExecPeopleId = "${object.procExecPeople.id}" />
			 </#if>
			 <input type="hidden" name="procExecPeople.id" value="${procExecPeopleId}" /> 
			 <#assign loopNum=loopNum+1/>
	     </@vcolumn>
	     -->
	     <@vcolumn title="${action.getText('repairItem.procCompleteDate')}">
		   <#assign procCompleteDate = ''/>
		   <#if object.procCompleteDate?exists>
		     <#assign procCompleteDate = "${(object.procCompleteDate?string('yyyy-MM-dd'))}"/>
		   </#if>
		   <@eam2008_dataPicker inputName="${procCompleteDateIdentityName}" inputId="${procCompleteDateIdentityName}" inputValue="${procCompleteDate}" imgId="${procCompleteDateImgIdentity}" formName="repairItem" />
	       <#assign procCompleteDateIdentityName = 'procCompleteDate' + '${itemNo}'/>
		   <#assign procCompleteDateImgIdentity = 'repairItem_' + '${procCompleteDateIdentityName}' + '_trigger'/>
	     </@vcolumn>
	     <@vcolumn title="${action.getText('repairItem.comment')}">
	       <input type="text" name="comment" 
			      class="underline"  value="${object.comment?if_exists}"  maxlength="250"/>
	     </@vcolumn>
	     </#if>
	     </#if>
       </@list>
       <#if !first>
         <#if planProcFlag?exists>
	       <#if planProcFlag=='PLAN'>
	         <@buttonBar>
	     	   <#if preYearFlag?exists>
			     <#if preYearFlag=='YEAR'>
			     <#if !(action.isReadOnly())>
			       <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="item_openDialog(#{repairPlanOrProcDetail.id},null);"/>
			       </#if> 
			     <#elseif preYearFlag=='PRE'>
			      <#if !(action.isReadOnly())>
			       <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="item_openDialog(#{preRepairPlanDetail.id},null);"/>
			     </#if>
			     </#if>
			   </#if>
			   
             <#if !(action.isReadOnly())>
		       <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('item')}?" />
		       <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		         <@ww.param name="'onclick'" value="'return confirmDeletes(\"itemIds\", \"${confirmMessage}\");'"/>
		         <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		       </@vsubmit>
		       </#if>
	         </@buttonBar>
	       <#else>
	         <@buttonBar>
	         <#if !(action.isReadOnly())>
	      	   <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'">
	      	     <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	      	   </@vsubmit>
	      	   </#if>
	      	 </@buttonBar>
           </#if>
         </#if>
       </#if>
     </@ww.form>
     <script language="javascript">
	   function item_openDialog(DetailId,repairItemId) {
	   	 <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			 var url = '${req.contextPath}/popup/editRepairItem.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProcDetail.id='+DetailId;
     		<#elseif preYearFlag=='PRE'>
     			 var url = '${req.contextPath}/popup/editRepairItem.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlanDetail.id='+DetailId;
     		</#if>
     	 </#if>
	     if (null !=repairItemId) {
	       url = url + '&repairItem.id=' +repairItemId;
	     }
	     url = url + '&planProcFlag=' + document.forms[0].elements["planProcFlag"].value + '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;
	     popupModalDialog(url,800,600);
	     <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			self.location.href='${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanItems.html?repairPlanOrProcDetail.id='+DetailId + 
     			'&planProcFlag=' + '${planProcFlag?if_exists}'+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;
     		<#elseif preYearFlag=='PRE'>
     			self.location.href='${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanItems.html?preRepairPlanDetail.id='+DetailId + 
     			'&planProcFlag=' + '${planProcFlag?if_exists}'+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;
     		</#if>
     	 </#if>
	     
	   }
	   /*
	    * 实际执行人
	   */
	   <#--
	   function slectProcExecPeople(loopNum) {
	     document.forms[0].elements["currentRowNum"].value = loopNum;
	     procExecPeople_OpenDialog();
	   }
	   function procExecPeople_OpenDialog() {
	     var url = "${req.contextPath}/popup/userSelector.html";
		 popupModalDialog(url, 800, 600, procExecPeopleSelectorHandler);
	   }
	   function procExecPeopleSelectorHandler(result) {
	     var allProcExecPeopleId = document.getElementsByName("procExecPeople.id");
         var allProcExecPeopleName = document.getElementsByName("procExecPeople.name");
         var currentRowNum = document.forms[0].elements["currentRowNum"].value;
         if (null != result) {
           allProcExecPeopleId[currentRowNum].value = result[0];
           allProcExecPeopleName[currentRowNum].value = result[1];
         }
	   }
	   -->
	   function validate() {
	     retrieveRepairItemIdText();
	     retrieveRepairItemProcExecPeopleText();
	     retrieveRepairItemProcCompleteDateText();
	     retrieveRepairItemCommentText();
	     return true;
	   }
	   
	   /*
	    * 获取列表中实际执行人的所有值（获取字符串内容）
	   */
	   function retrieveRepairItemProcExecPeopleText() {
         var allProcExecPeople = document.getElementsByName("procExecPeopleString");
         var allRepairItemId = document.getElementsByName("repairItemIds");
         var ary = new Array();
         for (var i=0; i<allRepairItemId.length; i++) {
           if ('' != allProcExecPeople[i].value) {
             ary.push(allRepairItemId[i].value);
             ary.push(allProcExecPeople[i].value);
           }
         }
         document.forms[0].elements["allProcExecPeople"].value=ary;
       }
	   
	   <#--实际执行人已经更改为输入框，不在使用以下方法
	   /*
	    * 获取列表中实际执行人的所有值（获取id）
	   */
	   function retrieveRepairItemProcExecPeopleText() {
         var allProcExecPeople = document.getElementsByName("procExecPeople.id");
         var allRepairItemId = document.getElementsByName("repairItemIds");
         var ary = new Array();
         for (var i=0; i<allRepairItemId.length; i++) {
           if ('' != allProcExecPeople[i].value) {
             ary.push(allRepairItemId[i].value);
             ary.push(allProcExecPeople[i].value);
           }
         }
         document.forms[0].elements["allProcExecPeople"].value=ary;
       }
       -->
       /*
        * 获取列表中实际完成日期的所有值
       */
       function retrieveRepairItemProcCompleteDateText() {
         var allRepairItemId = document.getElementsByName("repairItemIds");
         var ary = new Array();
         for (var i=0; i<allRepairItemId.length; i++) {
           var procCompleteDateIdentityName = 'procCompleteDate';
           procCompleteDateIdentityName = procCompleteDateIdentityName + (i+1); 
           if ('' != document.forms[0].elements[procCompleteDateIdentityName].value) {
             ary.push(allRepairItemId[i].value);
             ary.push(document.forms[0].elements[procCompleteDateIdentityName].value);
           }
         }
         document.forms[0].elements["allRepairItemProcCompleteDate"].value = ary;
       }
       
       /*
        * 获取列表中的备注的所有值
       */
       function retrieveRepairItemCommentText() {
         var allRepairItemComment = document.getElementsByName("comment");
         var allRepairItemId = document.getElementsByName("repairItemIds");
         var ary = new Array();
         for (var i=0; i<allRepairItemId.length; i++) {
           if ('' != allRepairItemComment[i].value) {
             ary.push(allRepairItemId[i].value);
             ary.push(allRepairItemComment[i].value);
           }
         }
         document.forms[0].elements["allRepairItemComment"].value = ary;
       }
       /*
        * 获取列表中维修明细的ID
       */
       function retrieveRepairItemIdText() {
	     var allRepairItemId = document.getElementsByName("repairItemIds");
	     var ary = new Array();
	     for (var i=0; i<allRepairItemId.length; i++) {
	       ary.push(allRepairItemId[i].value);
	     }
	     document.forms[0].elements["allRepairItemId"].value = ary;
       }
	 </script>
</@framePage>