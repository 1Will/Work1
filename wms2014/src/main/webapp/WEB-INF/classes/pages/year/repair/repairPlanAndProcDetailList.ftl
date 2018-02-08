<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('repairPlanAndProcDetailList.title')}">
   <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
   <@ww.form namespace="'/year/repair'" name="'yearRepairPlanOrProcDetail'" action="'saveYearRepairPlanOrProcDetails'" method="'post'">
     <@ww.token name="saveYearRepairPlanOrProcDetailsToken"/>
     <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
     <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
     <@ww.hidden name="'allYearRepairPlanDetailId'" value="''"/>
     <@ww.hidden name="'allYearRepairPlanDetailDepartment'" value="''"/>
     <@ww.hidden name="'allYearRepairPlanDetailExternalHelp'" value="''"/>
     <@ww.hidden name="'allYearRepairPlanDetailTechnicalLevel'" value="''"/>
     <@ww.hidden name="'allYearRepairPlanDetailCategory'" value="''"/>
     <@ww.hidden name="'allYearRepairPlanDetailPlanRepairDate'" value="''"/>
     <@ww.hidden name="'allYearRepairPlanDetailPlanEndDate'" value="''"/>
     <@ww.hidden name="'allYearRepairPlanDetailPlanBeginDate'" value="''"/>
     <@ww.hidden name="'allProcExePeople'" value="''"/>
     <@ww.hidden name="'allProcExecResult'" value="''"/>
     <@ww.hidden name="'currentRowNum'" value="''"/>
     <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <#if repairPlanOrProc.id?exists>
       <@ww.hidden name="'repairPlanOrProc.id'" value="#{repairPlanOrProc.id}"/>
     </#if>
     <#assign detailListTitle=''/>
	 <#if planProcFlag?exists>
       <#if (planProcFlag=='PLAN')>
	     <#assign detailListTitle="${action.getText('yearRepairPlanDetail.list')}"/>
	   <#else>
		 <#assign detailListTitle = "${action.getText('yearRepairProcDetail.list')}"/>
	   </#if>
     </#if>
     <#assign itemNo=1/>
	 <#assign loopNum=0/>
	 <#assign departmentIdentityName = 'department' + '${itemNo}'/>
	 <#assign categoryIdentityName = 'category' + '${itemNo}'/>
	 <#assign technicalLevelIdentityName = 'technicalLevel' + '${itemNo}'/>
	 <#assign planBeginDateIdentityName = 'repairPlanOrProcDetail.planBeginDate' + '${itemNo}'/>
	 <#assign planBeginDateImgIdentity = 'repairPlanOrProcDetail_' + '${planBeginDateIdentityName}' + '_trigger'/>
     <#assign planEndDateIdentityName = 'repairPlanOrProcDetail.planEndDate' + '${itemNo}'/>
	 <#assign planEndDateImgIdentity = 'repairPlanOrProcDetail_' + '${planEndDateIdentityName}' + '_trigger'/>
	 <#assign planRepairDateIdentityName = 'repairPlanOrProcDetail.planRepairDate' + '${itemNo}'/>
	 <#assign planRepairDateImgIdentity = 'repairPlanOrProcDetail_' + '${planRepairDateIdentityName}' + '_trigger'/>
     <#assign execResultIdentityName = 'execResult' + '${itemNo}'/>
     <#assign externalHelpIdentityName = 'externalHelp' + '${itemNo}'/>
     <@list title="" excel=false setupTable=false
        	includeParameters="repairPlanOrProc.id|readOnly" 
        	fieldMap="like:" >
       <input type="hidden" name="detailIds" value="#{object.id}"/>
       <#if planProcFlag?exists>
         <#if (planProcFlag=='PLAN')>
	       <@vlh.checkbox property="id" name="repairPlanOrProcDetailIds">
		     <@vlh.attribute name="width" value="30" />
		   </@vlh.checkbox>
		 </#if>
	   </#if>
	   <@vcolumn title="${action.getText('repairPlanOrProcDetail.serialNo')}">
	     <a href="#" onclick="return planDetailItem_openDialog(${itemNo},#{repairPlanOrProc.id},#{object.id});"/>${itemNo}</a>
	     <@alignCenter/>
	   </@vcolumn>
	   <#assign itemNo = itemNo+1/>
	   <@vcolumn title="${action.getText('repairPlanOrProcDetail.workNo')}" property="workNo">
	     <@alignLeft/>
	   </@vcolumn>
       <@vcolumn title="${action.getText('repairPlanOrProcDetail.deviceNo')}" property="asset.deviceNo">
	     <@alignLeft/>
       </@vcolumn>
       <@vcolumn title="${action.getText('repairPlanOrProcDetail.deviceName')}" property="asset.name">
	     <@alignLeft/>
       </@vcolumn>
       <@vcolumn title="${action.getText('repairPlanOrProcDetail.content')}" property="content">
	     <@alignLeft/>
       </@vcolumn>
       <#if planProcFlag?exists>
         <#if (planProcFlag=='PLAN')>
	       <@vcolumn title="${action.getText('repairPlanOrProcDetail.department')}">
	       <@vlh.attribute name="style" value="VERTICAL-ALIGN:bottom"/>
		     <select name="${departmentIdentityName}">
			   <@ww.iterator value="departments" id="department">
			     <option value="<@ww.property value="id"/>"><@ww.property value="name"/></option>
			   </@ww.iterator>
		     </select>
	         <script language="javascript">
               <#if object.department?exists>
                 document.forms[0].elements["${departmentIdentityName}"].value='${object.department.id?if_exists}';
               </#if>
             </script>
			 <#assign departmentIdentityName = 'department' + '${itemNo}'/>
		   </@vcolumn>
		   <@vcolumn title="${action.getText('repairPlanOrProcDetail.isExternalHelp')}">
	        <select name="${externalHelpIdentityName}">
     		  <option value="N">${action.getText('isExternalHelp.no')}</option>
     		  <option value="Y">${action.getText('isExternalHelp.yes')}</option>
	        </select>
            <script language="javascript">
              <#if (object.externalHelpFlag)>
         	    document.forms[0].elements["${externalHelpIdentityName}"].value = 'Y';
         	  <#else>
         	    document.forms[0].elements["${externalHelpIdentityName}"].value = 'N';
         	  </#if>
            </script>
			<#assign externalHelpIdentityName = 'externalHelp' + '${itemNo}'/>
		   </@vcolumn>
		 <#else>
		   <@vcolumn title="${action.getText('repairPlanOrProcDetail.department')}" property="department.name">
		     <@alignLeft/>
           </@vcolumn>
           <#assign externalHelpFlag=''/>
	       <#if object.externalHelpFlag?string == 'true'>
             <#assign externalHelpFlag="${action.getText('isExternalHelp.yes')}"/>
	       <#else>
	         <#assign externalHelpFlag="${action.getText('isExternalHelp.no')}"/>
	       </#if>
	       <@vcolumn title="${action.getText('repairPlanOrProcDetail.isExternalHelp')}">
		      ${externalHelpFlag}
	          <@alignLeft/>
	       </@vcolumn>
	     </#if>
	   </#if>
	   <#--
	   <#if planProcFlag?exists>
         <#if (planProcFlag=='PLAN')>
           <@vcolumn title="${action.getText('repairPlanOrProcDetail.technicalLevel')}">
	         <select name="${technicalLevelIdentityName}">
		       <@ww.iterator value="technicalLevels" id="technicalLevel">
		         <option value="<@ww.property value="id"/>"><@ww.property value="value"/></option>
		       </@ww.iterator>
	         </select>
	         <script language="javascript">
               <#if object.technicalLevel?exists>
                 document.forms[0].elements["${technicalLevelIdentityName}"].value='${object.technicalLevel.id?if_exists}';
               </#if>
             </script>
             <#assign technicalLevelIdentityName = 'technicalLevel' + '${itemNo}'/>
	       </@vcolumn>
		 <#else>
		   <@vcolumn title="${action.getText('repairPlanOrProcDetail.technicalLevel')}" property="technicalLevel.value">
		     <@alignLeft/>
		   </@vcolumn>
         </#if>
       </#if>
       -->
       <#assign complexCoefficient=''/>
       <#if (object.machineFlag) && (object.electricalFlag)>
          <#assign complexCoefficient="${action.getText('repairPlanOrProcDetail.machine')}" + ',' + "${action.getText('repairPlanOrProcDetail.electrical')}"/>
       <#elseif object.machineFlag>
         <#assign complexCoefficient="${action.getText('repairPlanOrProcDetail.machine')}"/>
       <#elseif object.electricalFlag>
         <#assign complexCoefficient="${action.getText('repairPlanOrProcDetail.electrical')}"/>
       </#if>
       <@vcolumn title="${action.getText('repairPlanOrProcDetail.technicalLevel')}">
         ${complexCoefficient}
	     <@alignLeft/>
	   </@vcolumn>
	   <#if planProcFlag?exists>
         <#if (planProcFlag=='PLAN')>
           <@vcolumn title="${action.getText('repairPlanOrProcDetail.category')}">
	         <select name="${categoryIdentityName}">
		       <@ww.iterator value="categorys" id="category">
		         <option value="<@ww.property value="id"/>"><@ww.property value="value"/></option>
		       </@ww.iterator>
	         </select>
	         <script language="javascript">
               <#if object.category?exists>
                 document.forms[0].elements["${categoryIdentityName}"].value='${object.category.id?if_exists}';
               </#if>
             </script>
             <#assign categoryIdentityName = 'category' + '${itemNo}'/>
	       </@vcolumn>
		 <#else>
		   <@vcolumn title="${action.getText('repairPlanOrProcDetail.category')}" property="category.value">
		     <@alignLeft/>
		   </@vcolumn>
         </#if>
       </#if>
       <#if planProcFlag?exists>
         <#if (planProcFlag=='PLAN')>
		   <@vcolumn title="${action.getText('repairPlanOrProcDetail.planRepairDate')}">
		      <#assign planRepairDate = ''/>
              <#if object.planRepairDate?exists>
                <#assign planRepairDate = "${(object.planRepairDate?string('yyyy-MM'))}"/>
              </#if>
		      <@eam2008_dataPicker inputName="${planRepairDateIdentityName}" inputId="${planRepairDateIdentityName}" inputValue="${planRepairDate}" imgId="${planRepairDateImgIdentity}" formName="repairPlanOrProcDetail" dateFormate="%Y-%m"/>
              <#assign planRepairDateIdentityName = 'repairPlanOrProcDetail.planRepairDate' + '${itemNo}'/>
	          <#assign planRepairDateImgIdentity = 'repairPlanOrProcDetail_' + '${planRepairDateIdentityName}' + '_trigger'/>
		   </@vcolumn>
		 <#else>
		   <@vcolumn title="${action.getText('repairPlanOrProcDetail.procRepairDate')}">
		      <#assign procRepairDate = ''/>
              <#if object.procRepairDate?exists>
                <#assign procRepairDate = "${(object.procRepairDate?string('yyyy-MM'))}"/>
              </#if>
		      <@eam2008_dataPicker inputName="${planRepairDateIdentityName}" inputId="${planRepairDateIdentityName}" inputValue="${procRepairDate}" imgId="${planRepairDateImgIdentity}" formName="repairPlanOrProcDetail" dateFormate="%Y-%m"/>
              <#assign planRepairDateIdentityName = 'repairPlanOrProcDetail.planRepairDate' + '${itemNo}'/>
	          <#assign planRepairDateImgIdentity = 'repairPlanOrProcDetail_' + '${planRepairDateIdentityName}' + '_trigger'/>
		   </@vcolumn>
		 </#if>
		</#if>
		<#if planProcFlag?exists>
          <#if (planProcFlag=='PLAN')>
            <@vcolumn title="${action.getText('repairPlanOrProcDetail.planBeginDate')}">
            <#assign planBeginDate = ''/>
            <#if object.planBeginDate?exists>
              <#assign planBeginDate = "${(object.planBeginDate?string('yyyy-MM-dd'))}"/>
            </#if>
              <@eam2008_dataPicker inputName="${planBeginDateIdentityName}" inputId="${planBeginDateIdentityName}" inputValue="${planBeginDate}" imgId="${planBeginDateImgIdentity}" formName="repairPlanOrProcDetail"/>
              <#assign planBeginDateIdentityName = 'repairPlanOrProcDetail.planBeginDate' + '${itemNo}'/>
	          <#assign planBeginDateImgIdentity = 'repairPlanOrProcDetail_' + '${planBeginDateIdentityName}' + '_trigger'/>
            </@vcolumn>
	      <#else>
            <@vcolumn title="${action.getText('repairPlanOrProcDetail.procBeginDate')}">
            <#assign procBeginDate = ''/>
            <#if object.procBeginDate?exists>
              	<#assign procBeginDate = "${(object.procBeginDate?string('yyyy-MM-dd'))}"/>
            </#if>
              <@eam2008_dataPicker inputName="${planBeginDateIdentityName}" inputId="${planBeginDateIdentityName}" inputValue="${procBeginDate}" imgId="${planBeginDateImgIdentity}" formName="repairPlanOrProcDetail" />
              <#assign planBeginDateIdentityName = 'repairPlanOrProcDetail.planBeginDate' + '${itemNo}'/>
	          <#assign planBeginDateImgIdentity = 'repairPlanOrProcDetail_' + '${planBeginDateIdentityName}' + '_trigger'/>
            </@vcolumn>
	       </#if>
	     </#if>
	     <#if planProcFlag?exists>
           <#if (planProcFlag=='PLAN')>
            <@vcolumn title="${action.getText('repairPlanOrProcDetail.planEndDate')}">
            <#assign planEndDate = ''/>
            <#if object.planEndDate?exists>
              <#assign planEndDate = "${(object.planEndDate?string('yyyy-MM-dd'))}"/>
            </#if>
              <@eam2008_dataPicker inputName="${planEndDateIdentityName}" inputId="${planEndDateIdentityName}" inputValue="${planEndDate}" imgId="${planEndDateImgIdentity}" formName="repairPlanOrProcDetail"/>
              <#assign planEndDateIdentityName = 'repairPlanOrProcDetail.planEndDate' + '${itemNo}'/>
	          <#assign planEndDateImgIdentity = 'repairPlanOrProcDetail_' + '${planEndDateIdentityName}' + '_trigger'/>
            </@vcolumn>
	       <#else>
            <@vcolumn title="${action.getText('repairPlanOrProcDetail.procEndDate')}">
            <#assign procEndDate = ''/>
            <#if object.procEndDate?exists>
              <#assign procEndDate = "${(object.procEndDate?string('yyyy-MM-dd'))}"/>
            </#if>
              <@eam2008_dataPicker inputName="${planEndDateIdentityName}" inputId="${planEndDateIdentityName}" inputValue="${procEndDate}" imgId="${planEndDateImgIdentity}" formName="repairPlanOrProcDetail"/>
              <#assign planEndDateIdentityName = 'repairPlanOrProcDetail.planEndDate' + '${itemNo}'/>
	          <#assign planEndDateImgIdentity = 'repairPlanOrProcDetail_' + '${planEndDateIdentityName}' + '_trigger'/>
            </@vcolumn>
	       </#if>
	     </#if>
	     <#if planProcFlag?exists>
           <#if (planProcFlag=='PROC')>
             <@vcolumn title="${action.getText('repairPlanOrProcDetail.procExecPeople')}">
            	<#assign procExecPeopleIdentity = 'procExecPeople' + '${itemNo}'/>
	        	<#assign procExecPeopleName = ''/>
				<#if object.procExecPeople?exists>
				 <#assign procExecPeopleName = "${object.procExecPeople.name}" />
				<#elseif object.execPeople?exists>
				  <#assign procExecPeopleName="${object.execPeople.name}"/>
				</#if>
        		<input type="text" name="execPeople.name" 
        			class="underline"  value="${procExecPeopleName}"  maxlength="150" size="10" disabled="false"/>
        		<label id=""></label>
	    		<a onClick="slectExecPeople(${loopNum});">
	        		<img id="${procExecPeopleIdentity}"src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 style="display:inline"/>
	        	</a>
		        <#assign procExecPeopleId = ''/>
				<#if object.procExecPeople?exists>
				 	<#assign procExecPeopleId = "${object.procExecPeople.id}" />
				<#else>
				
				</#if>
				<input type="hidden" name="execPeople.id" value="${procExecPeopleId}" /> 
				<#assign loopNum = loopNum +1/>
             </@vcolumn>
           </#if>
         </#if>
	         <@vcolumn title="${action.getText('repairPlanOrProcDetail.planDetailAllFee')}" property="planDetailAllFee">
		       <@alignRight/>
		     </@vcolumn>
		 <#if planProcFlag?exists>
           <#if (planProcFlag=='PROC')>
             <@vcolumn title="${action.getText('repairPlanOrProcDetail.procDetailAllFee')}" property="procDetailAllFee">
        	   <@alignRight/>
        	 </@vcolumn>
        	 <@vcolumn title="${action.getText('repairPlanOrProcDetail.result')}">
			      <select name="${execResultIdentityName}" valign="center">
				    <@ww.iterator value="procResults" id="procResult">
				      <option value="<@ww.property value="value"/>"><@ww.property value="label"/></option>
				    </@ww.iterator>
			      </select>
			      <script language="javascript">
		            <#if object.procResult?exists>
		              document.forms[0].elements["${execResultIdentityName}"].value='${object.procResult?if_exists}';
		            </#if>
		          </script>
		          <#assign execResultIdentityName = 'execResult' + '${itemNo}'/>
			 </@vcolumn>
           </#if>
         </#if>
     </@list>
     <#if !first>
	   <@buttonBar>
	     <#if planProcFlag?exists>
           <#if (planProcFlag=='PLAN')>
           <#if !(action.isReadOnly())>
		     <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="planDetailItem_openDialog(null,#{repairPlanOrProc.id},null);"/>
		        <@vsubmit name="'save'" value="'${action.getText('save')}'">
                   <@ww.param name="'onclick'" value="'return customize_validate();'"/>
                   <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
                 </@vsubmit>
		     <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('repairPlanAndProcDetail')}?" />
		     <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		        	<@ww.param name="'onclick'" value="'return confirmDeletes(\"repairPlanOrProcDetailIds\", \"${confirmMessage}\")'"/>
		            <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		     </@vsubmit>
		     </#if>
		     <#else>
		       <#if !(action.isReadOnly())>
                <@vsubmit name="'save'" value="'${action.getText('save')}'">
                   <@ww.param name="'onclick'" value="'return customize_validate();'"/>
                   <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
                 </@vsubmit>
                </#if>
		   </#if>
		 </#if>
		  
         <#--
		 <@vbutton name="print"  class="button" value="${action.getText('print')}" onclick="open_preRepairPlanListPdf('#{repairPlanOrProc.id}')"/>
	     -->
	   </@buttonBar>
	 </#if>
   </@ww.form>
   	<script language="javascript">
   	  window.onload = function() {
   	    <#if req.getParameter('errorFlag')?has_content>
          alert("${action.getText('delete.yearRepairPlanDetail.failure')}");
        </#if>  
        <#if repairPlanOrProc.id?exists>
          if (document.forms[0].elements["planProcFlag"].value == 'PLAN') {
            parent.document.getElementById("repairPlanOrProc.planAllFee").value = '${repairPlanOrProc.planAllFee?if_exists}';
          } else {
            parent.document.getElementById("repairPlanOrProc.procAllFee").value = '${repairPlanOrProc.procAllFee?if_exists}';
          }
        </#if>
      }
   	  /*
       * 打开维修详细页面
      */
      function planDetailItem_openDialog(loopNum,repairPlanOrProcId,repairPlanOrProcDetailId) {
        var url = '${req.contextPath}/popup/editRepairPlanAndProcDetailItem.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProc.id=' + repairPlanOrProcId;
        if (repairPlanOrProcDetailId != null) {
          url = url + '&repairPlanOrProcDetail.id=' + repairPlanOrProcDetailId;
        }
        url = url + '&planProcFlag=' + document.forms[0].elements["planProcFlag"].value;
        url = url + '&toolingDevFlag=' + document.forms[0].elements["toolingDevFlag"].value;
	    popupModalDialog(url, 1024,768);
	    //self.location.reload(); 
	    var reloadUrl = '${req.contextPath}/year/repair/listYearRepairPlanOrProcDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProc.id=' + repairPlanOrProcId + '&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}';
	    self.location.href = reloadUrl;
	    return false;
	  }
	  /*
       * 执行人选择
      */
      function slectExecPeople(loopNum) {
         document.forms["yearRepairPlanOrProcDetail"].elements["currentRowNum"].value = loopNum;
	     execPeople_OpenDialog();
      }
      function execPeople_OpenDialog() {
        var url = "${req.contextPath}/popup/userSelector.html";
		popupModalDialog(url, 800, 600, execPeopleSelectorHandler);
      }
      function execPeopleSelectorHandler(result) {
        var allExecPeopleId = document.getElementsByName("execPeople.id");
        var allExecPeopleName = document.getElementsByName("execPeople.name");
        var currentRowNum = document.forms["yearRepairPlanOrProcDetail"].elements["currentRowNum"].value;
        if (null != result) {
          allExecPeopleId[currentRowNum].value = result[0];
          allExecPeopleName[currentRowNum].value = result[1];
        }
      }
	  function customize_validate() {
	    if (0 != document.getElementsByName("detailIds").length) { 
	      if (document.forms["yearRepairPlanOrProcDetail"].elements["planProcFlag"].value == 'PLAN') {
	        retrieveYearRepairPlanDetailIdText();                                 //获取明细列表中所有的明细ID
	        retrieveYearRepairPlanDetailDepartmentText();                          //获取计划明细列表中承修单位的所有值
	        retrieveExternalHelpText();                                            //获取计划明细列表中是否外协的所有值
	       // retrieveYearRepairPlanDetailTechnicalLevelText();                      //获取计划明细列表中技术等级的所有值
	        retrieveYearRepairPlanDetailCategoryText();                            //获取计划明细列表中种类的所有值
	        retrieveYearRepairPlanDetailPlanRepairDateText();                      //获取计划明细列表中计划修理日期的所有值
	        retrieveYearRepairPlanDetailPlanBeginDateText();                       //获取计划明细列表中计划开工时间的所有值
	        retrieveYearRepairPlanDetailPlanEndDateText();                        //获取计划明细列表中计划完工时间的所有值
	      } else {
	        retrieveYearRepairPlanDetailIdText();                                 //获取明细列表中所有的明细ID
	        retrieveYearRepairPlanDetailProcExePeopleText();                      //获取实施明细列表中执行人的所有值
	        retrieveYearRepairPlanDetailPlanRepairDateText();                      //获取计划明细列表中计划修理日期的所有值
	        retrieveYearRepairPlanDetailPlanBeginDateText();                      //获取计划明细列表中实际开工时间的所有值
	        retrieveYearRepairPlanDetailPlanEndDateText();                         //获取计划明细列表中实际完工时间的所有值
	        retrievePreRepairProcexecResultText();                                 //获取计划明细列表中执行结果的所有值
	      }
	    }
	    return true;
	  }
	 /*
      * 获取明细列表中所有的明细ID
	 */
	 function retrieveYearRepairPlanDetailIdText() {
	   var allYearRepairPlanDetailId = document.getElementsByName("detailIds");
	   var ary = new Array();
	   for (var i=0; i<allYearRepairPlanDetailId.length; i++) {
	     ary.push(allYearRepairPlanDetailId[i].value);
	   }
	   document.forms["yearRepairPlanOrProcDetail"].elements["allYearRepairPlanDetailId"].value = ary;
     }
	 /*
	  * 获取计划明细列表中承修单位的所有值
	 */
	 function retrieveYearRepairPlanDetailDepartmentText() {
       var allYearRepairPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allYearRepairPlanDetailId.length; i++) {
         var departmentTagName = 'department';
         departmentTagName = departmentTagName + (i+1); 
         if(-1 != document.forms["yearRepairPlanOrProcDetail"].elements[departmentTagName].value) {
           ary.push(allYearRepairPlanDetailId[i].value);
           ary.push(document.forms["yearRepairPlanOrProcDetail"].elements[departmentTagName].value);
         }
       }
       document.forms["yearRepairPlanOrProcDetail"].elements["allYearRepairPlanDetailDepartment"].value = ary;
     }
     /*
      * 获取计划明细列表中是否外协的所有值
     */
     function retrieveExternalHelpText() {
       var allYearRepairPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allYearRepairPlanDetailId.length; i++) {
         var externalHelpTagName = 'externalHelp';
         externalHelpTagName = externalHelpTagName + (i+1); 
         if(-1 != document.forms["yearRepairPlanOrProcDetail"].elements[externalHelpTagName].value) {
           ary.push(allYearRepairPlanDetailId[i].value);
           ary.push(document.forms["yearRepairPlanOrProcDetail"].elements[externalHelpTagName].value);
         }
       }
       document.forms["yearRepairPlanOrProcDetail"].elements["allYearRepairPlanDetailExternalHelp"].value = ary;
     }
     /*
	  * 获取计划明细列表中技术等级的所有值
	 */
	 function retrieveYearRepairPlanDetailTechnicalLevelText() {
       var allYearRepairPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allYearRepairPlanDetailId.length; i++) {
         var technicalLevelTagName = 'technicalLevel';
         technicalLevelTagName = technicalLevelTagName + (i+1); 
         if(-1 != document.forms["yearRepairPlanOrProcDetail"].elements[technicalLevelTagName].value) {
           ary.push(allYearRepairPlanDetailId[i].value);
           ary.push(document.forms["yearRepairPlanOrProcDetail"].elements[technicalLevelTagName].value);
         }
       }
       document.forms["yearRepairPlanOrProcDetail"].elements["allYearRepairPlanDetailTechnicalLevel"].value = ary;
     }
     /*
	  * 获取计划明细列表中种类的所有值
	 */
	 function retrieveYearRepairPlanDetailCategoryText() {
       var allYearRepairPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allYearRepairPlanDetailId.length; i++) {
         var categoryTagName = 'category';
         categoryTagName = categoryTagName + (i+1); 
         if(-1 != document.forms["yearRepairPlanOrProcDetail"].elements[categoryTagName].value) {
           ary.push(allYearRepairPlanDetailId[i].value);
           ary.push(document.forms["yearRepairPlanOrProcDetail"].elements[categoryTagName].value);
         }
       }
       document.forms["yearRepairPlanOrProcDetail"].elements["allYearRepairPlanDetailCategory"].value = ary;
     }
     /*
	  * 获取计划明细列表中计划修理日期的所有值
	 */
	 function retrieveYearRepairPlanDetailPlanRepairDateText() {
       var allYearRepairPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allYearRepairPlanDetailId.length; i++) {
         var planRepairDateTagName = 'repairPlanOrProcDetail.planRepairDate';
         planRepairDateTagName = planRepairDateTagName + (i+1);
         if ('' != document.forms["yearRepairPlanOrProcDetail"].elements[planRepairDateTagName].value) {
           ary.push(allYearRepairPlanDetailId[i].value);
           ary.push(document.forms["yearRepairPlanOrProcDetail"].elements[planRepairDateTagName].value);
         }
       }
       document.forms["yearRepairPlanOrProcDetail"].elements["allYearRepairPlanDetailPlanRepairDate"].value = ary;
     }
     /*
      * 获取计划明细列表中计划开工时间的所有值
     */
     function retrieveYearRepairPlanDetailPlanBeginDateText() {
       var allYearRepairPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allYearRepairPlanDetailId.length; i++) {
         var planBeginDateTagName = 'repairPlanOrProcDetail.planBeginDate';
         planBeginDateTagName = planBeginDateTagName + (i+1); 
         if ('' != document.forms["yearRepairPlanOrProcDetail"].elements[planBeginDateTagName].value) {
           ary.push(allYearRepairPlanDetailId[i].value);
           ary.push(document.forms["yearRepairPlanOrProcDetail"].elements[planBeginDateTagName].value);
         }
       }
       document.forms["yearRepairPlanOrProcDetail"].elements["allYearRepairPlanDetailPlanBeginDate"].value = ary;
     }
     /*
      * 获取计划明细列表中计划完工时间的所有值
     */
     function retrieveYearRepairPlanDetailPlanEndDateText() {
       var allYearRepairPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allYearRepairPlanDetailId.length; i++) {
         var planEndDateTagName = 'repairPlanOrProcDetail.planEndDate';
         planEndDateTagName = planEndDateTagName + (i+1); 
         if ('' != document.forms["yearRepairPlanOrProcDetail"].elements[planEndDateTagName].value) {
           ary.push(allYearRepairPlanDetailId[i].value);
           ary.push(document.forms["yearRepairPlanOrProcDetail"].elements[planEndDateTagName].value);
         }
       }
       document.forms["yearRepairPlanOrProcDetail"].elements["allYearRepairPlanDetailPlanEndDate"].value = ary;
     }
     /*
      * 获取实施明细列表中的执行人的所有值
     */
    function retrieveYearRepairPlanDetailProcExePeopleText() {
       var allProcExePeople = document.getElementsByName("execPeople.id");
       var allYearRepairPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allYearRepairPlanDetailId.length; i++) {
         if ('' != allProcExePeople[i].value) {
           ary.push(allYearRepairPlanDetailId[i].value);
           ary.push(allProcExePeople[i].value);
         }
       }
       document.forms["yearRepairPlanOrProcDetail"].elements["allProcExePeople"].value=ary;
     }
     /*
      * 获取实施明细列表中的执行结果的所有值
     */
     function retrievePreRepairProcexecResultText() {
       var allYearRepairPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allYearRepairPlanDetailId.length; i++) {
         var execResultTagName = 'execResult';
         execResultTagName = execResultTagName + (i+1); 
         ary.push(allYearRepairPlanDetailId[i].value);
         ary.push(document.forms["yearRepairPlanOrProcDetail"].elements[execResultTagName].value);
       }
       document.forms["yearRepairPlanOrProcDetail"].elements["allProcExecResult"].value = ary; 
     }
	</script>
</@framePage>