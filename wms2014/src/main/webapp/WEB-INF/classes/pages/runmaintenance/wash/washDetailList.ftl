<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('washDetailList.title')}">
  <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
  <@ww.form namespace="'/runmaintenance/wash'" name="'washDetail'" action="'saveWashDetails'" method="'post'">
    <@ww.token name="saveWashDetailsToken"/>
    <#if wash?exists>
      <@ww.hidden name="'wash.id'" value="#{wash.id}"/>
    </#if>
    <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
    <@ww.hidden name="'addToolingIds'" value=""/>
    <@ww.hidden name="'addTooling'" value=""/>
    <@ww.hidden name="'allWashDetailId'" value=""/>
    <@ww.hidden name="'allPlanWashDate'" value=""/> 
    <@ww.hidden name="'allDutyPeople'" value=""/> 
    <@ww.hidden name="'allSupervisePeople'" value=""/>
    <@ww.hidden name="'allProductModel'" value=""/>
    <@ww.hidden name="'allComment'" value=""/>
    <@ww.hidden name="'allWashResult'" value=""/>
    <@ww.hidden name="'currentRowNum'" value=""/>
    <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <#assign itemNo=1/>
    <#assign loopNum=0/>
    <#assign washDateIdentityName = 'washDetail.washDate' + '${itemNo}'/>
	<#assign washDateImgIdentity = 'washDetail_' + '${washDateIdentityName}' + '_trigger'/>
	<#assign productModelIdentityName = 'productModel' + '${itemNo}'/>
	<#assign washResultIdentityName = 'washResult' + '${itemNo}'/>
	<@list title="" excel=false setupTable=false
           includeParameters="wash.id|planProcFlag|readOnly" 
           fieldMap="like:" >
      <input type="hidden" name="detailIds" value="#{object.id}"/>
      <#if planProcFlag?exists>
        <#if planProcFlag == 'PLAN'>
	      <@vlh.checkbox property="id" name="washDetailIds">
		    <@vlh.attribute name="width" value="30" />
		  </@vlh.checkbox>
		</#if>
      </#if>
	  <@vcolumn title="${action.getText('washDeatil.itemNo')}">
	    ${itemNo}
	    <@alignCenter/>
      </@vcolumn>
      <#assign itemNo = itemNo+1/>
	  <@vcolumn title="${action.getText('washDetail.tooling.No')}" property="tooling.deviceNo">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('washDeatil.tooling.name')}" property="tooling.name">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('washDeatil.tooling.graphNo')}" property="tooling.graphNo">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('washDeatil.tooling.processNo')}" property="tooling.processNo">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('washDetail.productModel')}" property="tooling.product.name">
	        <@alignLeft/>
      </@vcolumn>
      <#if planProcFlag?exists>
        <#if planProcFlag == 'PLAN'>
	      <@vcolumn title="${action.getText('washDeatil.planWashDate')}">
	  	    <#assign planWashDate = ''/>
	        <#if object.planWashDate?exists>
	          <#assign planWashDate = "${(object.planWashDate?string('yyyy-MM-dd'))}"/>
	        </#if>
	        <@eam2008_dataPicker inputName="${washDateIdentityName}" inputId="${washDateIdentityName}" inputValue="${planWashDate}" imgId="${washDateImgIdentity}" formName="washDetail"/>
		    <#assign washDateIdentityName = 'washDetail.washDate' + '${itemNo}'/>
		    <#assign washDateImgIdentity = 'washDetail_' + '${washDateIdentityName}' + '_trigger'/>
		    <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	      </@vcolumn>
	    <#else>
	      <@vcolumn title="${action.getText('washDeatil.planWashDate')}" property="planWashDate" format="yyyy-MM-dd">
	        <@alignCenter/>
          </@vcolumn>
	    </#if>
	  </#if>
	  <#if planProcFlag?exists>
        <#if planProcFlag == 'PROC'>
          <@vcolumn title="${action.getText('washDeatil.procWashDate')}">
	  	    <#assign procWashDate = ''/>
	        <#if object.procWashDate?exists>
	          <#assign procWashDate = "${(object.procWashDate?string('yyyy-MM-dd'))}"/>
	        </#if>
	        <@eam2008_dataPicker inputName="${washDateIdentityName}" inputId="${washDateIdentityName}" inputValue="${procWashDate}" imgId="${washDateImgIdentity}" formName="washDetail"/>
		    <#assign washDateIdentityName = 'washDetail.washDate' + '${itemNo}'/>
		    <#assign washDateImgIdentity = 'washDetail_' + '${washDateIdentityName}' + '_trigger'/>
		    <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	      </@vcolumn>
        </#if>
      </#if>
      <#if planProcFlag?exists>
        <#if planProcFlag == 'PLAN'>
	      <@vcolumn title="${action.getText('washDetail.dutyPeople')}">
	    	<#assign dutyPeopleName = ''/>
			<#if object.dutyPeople?exists>
			  <#assign dutyPeopleName = "${object.dutyPeople.name}" />
			</#if>
	    	<input type="text" name="dutyPeople.name" 
	    		   class="underline"  value="${dutyPeopleName}"  maxlength="150" size="10" disabled="false"/>
	    	<label id=""></label>
	    	<a onClick="slectDutyPeople(${loopNum});">
	          <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	        </a>
	        <#assign dutyPeopleId = ''/>
			<#if object.dutyPeople?exists>
			 	<#assign dutyPeopleId = "${object.dutyPeople.id}" />
			</#if>
			<input type="hidden" name="dutyPeople.id" value="${dutyPeopleId}" /> 
			<@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
		  </@vcolumn>
		<#else>
		  <@vcolumn title="${action.getText('washDetail.dutyPeople')}" property="dutyPeople.name">
	        <@alignLeft/>
          </@vcolumn>
		</#if>
      </#if>
      <#if planProcFlag?exists>
        <#if planProcFlag == 'PLAN'>
		  <@vcolumn title="${action.getText('washDetail.supervisePeople')}">
	    	<#assign supervisePeopleName = ''/>
			<#if object.supervisePeople?exists>
			  <#assign supervisePeopleName = "${object.supervisePeople.name}" />
			</#if>
	    	<input type="text" name="supervisePeople.name" 
	    		   class="underline"  value="${supervisePeopleName}"  maxlength="150" size="10" disabled="false"/>
	    	<label id=""></label>
	    	<a onClick="slectSupervisePeople(${loopNum});">
	          <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	        </a>
	        <#assign supervisePeopleId = ''/>
			<#if object.supervisePeople?exists>
			 	<#assign supervisePeopleId = "${object.supervisePeople.id}" />
			</#if>
			<input type="hidden" name="supervisePeople.id" value="${supervisePeopleId}" /> 
			<@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
		  </@vcolumn>
	    <#else>
	      <@vcolumn title="${action.getText('washDetail.supervisePeople')}" property="supervisePeople.name">
	        <@alignLeft/>
          </@vcolumn>
	    </#if>
	  </#if>
	  <#assign loopNum = loopNum + 1 /> 
	  <#--
	  <#if planProcFlag?exists>
        <#if planProcFlag == 'PLAN'>
		  <@vcolumn title="${action.getText('washDetail.productModel')}">
	        <select name="${productModelIdentityName}">
		      <@ww.iterator value="productModels" id="productModel">
		        <option value="<@ww.property value="id"/>"><@ww.property value="name"/></option>
		      </@ww.iterator>
	        </select>
	        <script language="javascript">
	          <#if object.productModel?exists>
	            document.forms[0].elements["${productModelIdentityName}"].value='${object.productModel.id?if_exists}';
	          </#if>
	        </script>
	        <#assign productModelIdentityName = 'productModel' + '${itemNo}'/>
	        <@vlh.attribute name="style" value="VERTICAL-ALIGN:text-bottom"/>
	      </@vcolumn>
	    <#else>
	      <@vcolumn title="${action.getText('washDetail.productModel')}" property="productModel.name">
	        <@alignLeft/>
          </@vcolumn>
	    </#if>
	  </#if>
	  -->
	  <#if planProcFlag?exists>
        <#if planProcFlag == 'PROC'>
          <@vcolumn title="${action.getText('washDetail.result')}">
		      <select name="${washResultIdentityName}" valign="center">
			    <@ww.iterator value="washResults" id="washResult">
			      <option value="<@ww.property value="value"/>"><@ww.property value="label"/></option>
			    </@ww.iterator>
		      </select>
		      <script language="javascript">
	            <#if object.washResult?exists>
	              document.forms[0].elements["${washResultIdentityName}"].value='${object.washResult?if_exists}';
	            </#if>
	          </script>
	          <#assign washResultIdentityName = 'washResult' + '${itemNo}'/>
	          <@vlh.attribute name="style" value="VERTICAL-ALIGN:bottom"/>
		  </@vcolumn>
        </#if>
      </#if>
	  <#if planProcFlag?exists>
        <#if planProcFlag == 'PLAN'>
	      <@vcolumn title="${action.getText('washDetail.comment')}">
	        <input type="text" name="comment" 
	    		   class="underline"  value="${object.comment?if_exists}"  maxlength="250" size="15"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	      </@vcolumn>
	    <#else>
	      <@vcolumn title="${action.getText('washDetail.comment')}" property="comment">
	        <@alignLeft/>
          </@vcolumn>
	    </#if>
	  </#if>
    </@list>	
    <#if !first>
	  <@buttonBar>
	  	<#if planProcFlag?exists>
          <#if planProcFlag == 'PLAN'>
          <#if !(action.isReadOnly())>
            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="multi_select_tooling_openDialog();"/>
		     <@vsubmit name="'save'" value="'${action.getText('save')}'">
	          <@ww.param name="'onclick'" value="'return validate();'"/>
	          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	        </@vsubmit>
	        <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('washPlanDetail')}?" />
	        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	          <@ww.param name="'onclick'" value="'return confirmDeletes(\"washDetailIds\", \"${confirmMessage}\")'"/>
	          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	        </@vsubmit> 
	      </#if>
	      <#else>
	        <#if !(action.isReadOnly())>
              <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('washPlanDetail')}?" />
	          <@vsubmit name="'save'" value="'${action.getText('save')}'">
	            <@ww.param name="'onclick'" value="'return validate();'"/>
	            <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	          </@vsubmit>
            </#if>
	      </#if>
	    </#if>
	  </@buttonBar>
	</#if>
	
  </@ww.form>
  <script language="javascript">
    <#if req.getParameter('errorFlag')?has_content>
      alert("${action.getText('delete.washPlanDetail.failure')}");
    </#if>
    /*
     * 新建清洗计划明细
    */
    
    function multi_select_tooling_openDialog() {
      var flag='ToolingWash';
      var url = '${req.contextPath}/popup/toolingSelector.html';
      eam2008_multi_select_tooling_OpenDialog(url,flag);
    }
    function refresh_multi_tooling_information(reslut) {
      if (null != result) {
        var toolingIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addToolingIds"].value = toolingIds;
        document.forms[0].elements["addTooling"].value = "addToolings";
        document.forms[0].submit();
      }
    }
    /*
     * 负责人选择
    */
    function slectDutyPeople(loopNum) {
      document.forms["washDetail"].elements["currentRowNum"].value = loopNum;
      dutyPeople_OpenDialog();
    }
    function dutyPeople_OpenDialog() {
      var url = "${req.contextPath}/popup/userSelector.html";
	  popupModalDialog(url, 800, 600, dutyPeopleSelectorHandler);
    }
    function dutyPeopleSelectorHandler(result) {
      var allDutyPeopleId = document.getElementsByName("dutyPeople.id");
      var allDutyPeopleName = document.getElementsByName("dutyPeople.name");
      var currentRowNum = document.forms["washDetail"].elements["currentRowNum"].value;
      if (null != result) {
        allDutyPeopleId[currentRowNum].value = result[0];
        allDutyPeopleName[currentRowNum].value = result[1];
      }
    }
    function slectSupervisePeople(loopNum) {
      document.forms["washDetail"].elements["currentRowNum"].value = loopNum;
      supervisePeople_OpenDialog();
    }
    function supervisePeople_OpenDialog() {
      var url = "${req.contextPath}/popup/userSelector.html";
	  popupModalDialog(url, 800, 600, supervisePeopleSelectorHandler);
    }
    function supervisePeopleSelectorHandler(result) {
      var allSupervisePeopleId = document.getElementsByName("supervisePeople.id");
      var allSupervisePeopleName = document.getElementsByName("supervisePeople.name");
      var currentRowNum = document.forms["washDetail"].elements["currentRowNum"].value;
      if (null != result) {
        allSupervisePeopleId[currentRowNum].value = result[0];
        allSupervisePeopleName[currentRowNum].value = result[1];
      }
    }
    function validate() {
      if (0 != document.getElementsByName("detailIds").length) { 
	    if (document.forms["washDetail"].elements["planProcFlag"].value == 'PLAN') {
	      retrieveDutyPeopleText();                           //获取负责人的所有值
          retrieveSupervisePeopleText();                      //获取计划明细列表中监督人的所有值
         // retrieveProductModelText();                         //获取计划明细列表中产品型号的所有值
          retrieveComment();                                  //获取计划明细列表中备注的所有值
	    } else {
	      retrieveWashResultText();                           //获取清洗明细中清洗结果的所有值
	    }
	    retrieveWashDetailIdText();                           //获取清洗明细ID的所有值
        retrievePlanWashDateText();                           //获取计划明细列表中计划清洗日期的所有值
	  }
      return true;
    }
    /*
     * 获取清洗明细ID的所有值
    */
    function retrieveWashDetailIdText() {
	  var allWashDetailId = document.getElementsByName("detailIds");
	  var ary = new Array();
	  for (var i=0; i<allWashDetailId.length; i++) {
	    ary.push(allWashDetailId[i].value);
	  }
	  document.forms["washDetail"].elements["allWashDetailId"].value = ary;
    }
    /*
     * 获取计划明细列表中计划清洗日期的所有值
    */
    function retrievePlanWashDateText() {
       var allWashDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allWashDetailId.length; i++) {
         var planWashDateTagName = 'washDetail.washDate';
         planWashDateTagName = planWashDateTagName + (i+1); 
         if ('' != document.forms["washDetail"].elements[planWashDateTagName].value) {
           ary.push(allWashDetailId[i].value);
           ary.push(document.forms["washDetail"].elements[planWashDateTagName].value);
         }
       }
       document.forms["washDetail"].elements["allPlanWashDate"].value = ary;
     } 
     /*
      * 获取负责人的所有值
     */
     function retrieveDutyPeopleText() {
       var allDutyPeople = document.getElementsByName("dutyPeople.id");
       var allWashDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allWashDetailId.length; i++) {
         if ('' != allDutyPeople[i].value) {
           ary.push(allWashDetailId[i].value);
           ary.push(allDutyPeople[i].value);
         }
       }
       document.forms["washDetail"].elements["allDutyPeople"].value=ary;
     }
     /*
      * 获取计划明细列表中监督人的所有值
     */
     function retrieveSupervisePeopleText() {
       var allSupervisePeople = document.getElementsByName("supervisePeople.id");
       var allWashDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allWashDetailId.length; i++) {
         if ('' != allSupervisePeople[i].value) {
           ary.push(allWashDetailId[i].value);
           ary.push(allSupervisePeople[i].value);
         }
       }
       document.forms["washDetail"].elements["allSupervisePeople"].value=ary;
     }
     /*
      * 获取计划明细列表中产品型号的所有值
     */
     function retrieveProductModelText() {
       var allWashDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allWashDetailId.length; i++) {
         var productModelTagName = 'productModel';
         productModelTagName = productModelTagName + (i+1); 
         if(-1 != document.forms["washDetail"].elements[productModelTagName].value) {
           ary.push(allWashDetailId[i].value);
           ary.push(document.forms["washDetail"].elements[productModelTagName].value);
         }
       }
       document.forms["washDetail"].elements["allProductModel"].value = ary;
     }
     /*
      * 获取实施明细列表中的清洗结果的所有值
     */
     function retrieveWashResultText() {
       var allWashDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allWashDetailId.length; i++) {
         var washResultTagName = 'washResult';
         washResultTagName = washResultTagName + (i+1); 
         if(-1 != document.forms["washDetail"].elements[washResultTagName].value) {
           ary.push(allWashDetailId[i].value);
           ary.push(document.forms["washDetail"].elements[washResultTagName].value);
         }
       }
       document.forms["washDetail"].elements["allWashResult"].value = ary;
     }
     /*
      * 获取计划明细列表中备注的所有值
     */
     function retrieveComment() {
       var allComment = document.getElementsByName("comment");
       var allWashDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allWashDetailId.length; i++) {
         if ('' != allComment[i].value) {
           ary.push(allWashDetailId[i].value);
           ary.push(allComment[i].value);
         }
       }
       document.forms["washDetail"].elements["allComment"].value=ary;
     }
  </script>      
</@framePage>