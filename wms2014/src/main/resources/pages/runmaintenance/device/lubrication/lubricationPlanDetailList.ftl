<#--$Id: lubricationPlanList.ftl 10243 2008-01-08 03:18:30Z wzou $ -->
<#include "../../../includes/eam2008.ftl" />

<@framePage title="${action.getText('lubricationPlan.title')}">
 <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
  <@ww.form name="'lubricationPlanDetail'" action="'searchLubricationPlanDetails'" method="'post'">
    <@ww.token name="saveWashDetailsToken"/>
    <#if lubrication.id?exists>
      <@ww.hidden name="'lubrication.id'" value="#{lubrication.id}"/>
    </#if>
    
    <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
    <@ww.hidden name="'currentRowNum'" value=""/>
    <@ww.hidden name="'allLubricationPlanDetailId'" value=""/>
    <@ww.hidden name="'allEstimateExecDate'" value=""/>
    <@ww.hidden name="'allComment'" value=""/>
    <@ww.hidden name="'allPlanExePeople'" value=""/>
    <@ww.hidden name="'allExectPeople'" value=""/>
    <@ww.hidden name="'allLubricationResult'" value=""/>
    <@ww.hidden name="'allLubricationOilQty'" value=""/>
    <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <#assign itemNo=1/>
    <#assign loopNum=0/>
    <#assign estimateExecDateIdentityName = 'lubricationPlanDetail.estimateExecDate' + '${itemNo}'/>
    <#assign estimateExecDateImgIdentity = 'lubricationPlanDetail_' + '${estimateExecDateIdentityName}' + '_trigger'/>
    <#assign lubricationResultIdentityName = 'lubricationResult' + '${itemNo}'/>
    
    <@list title="" excel=false setupTable=false
           includeParameters="lubrication.id|planProcFlag|readOnly" 
           fieldMap="like:" >
      <input type="hidden" name="detailIds" value="#{object.id}"/>
      <@vlh.checkbox property="id" name="lubricationDetailIds">
	    <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('lubricationDetail.itemNo')}">
        <#if planProcFlag?exists>
          <#if planProcFlag == 'PLAN'>
	        <a href="#" onclick="open_lubricationDetailDialog(#{lubrication.id},#{object.id});return false;">
	          ${itemNo}
	        </a>
	      <#else>
	        ${itemNo}
	      </#if>
	    </#if>
	      
	    <@alignCenter/>
      </@vcolumn>
      <#assign itemNo = itemNo+1/>
      <@vcolumn title="${action.getText('device.no')}" property="device.deviceNo">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('lubricationDetail.deviceName')}" property="device.name">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('lubricationRules.position')}" property="position">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('lubricationDetail.lubricationContent')}" property="ruleDsp">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('lubricationDetail.lubricationMethod')}" property="methodDsp">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('lubricationDetail.lubricationMeterial')}" property="lubricatingOil.name">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('lubricationDetail.lubricationMeasureUnit')}" property="lubricatingOilMeasureUnit">
	    <@alignLeft/>
      </@vcolumn>
      <#if planProcFlag?exists>
        <#if planProcFlag == 'PLAN'>
          <@vcolumn title="${action.getText('lubricationDetail.lubricationOilQty')}">
	        <input type="text" name="lubricationOilQty" 
	    	       class="underline"  value="${object.planLubricatingOilQty?if_exists}"  size="15" style="text-align:right"/>
	        <@vlh.attribute name="style" value="VERTICAL-ALIGN:right"/>
	      </@vcolumn>
	    <#else>
	      <@vcolumn title="${action.getText('lubricationDetail.lubricationOilQty')}" property="planLubricatingOilQty">
	        <@alignRight/>
          </@vcolumn>
          <@vcolumn title="${action.getText('lubricationDetail.actualLubricationOilQty')}">
	        <input type="text" name="lubricationOilQty" 
	    	       class="underline"  value="${object.actualLubricatingOilQty?if_exists}"  maxlength="10" size="15"/>
	        <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	      </@vcolumn>
	    </#if>
	  </#if>
	  <#--
      <@vcolumn title="${action.getText('lubricationDetail.dutyPeople')}" property="dutyPeople">
	    <@alignLeft/>
      </@vcolumn>
      -->
      <#if planProcFlag?exists>
        <#if planProcFlag == 'PLAN'>
          <@vcolumn title="${action.getText('lubricationDetail.planDutyPeople')}">
	        <#assign planExePeopleName = ''/>
		    <#if object.planExePeople?exists>
	          <#assign planExePeopleName = "${object.planExePeople}" />
		    </#if>
	        <input type="text" name="exePeople.name" 
	    	       class="underline"  value="${planExePeopleName}"  maxlength="150" size="10" disabled="false"/>
	        <label id=""></label>
    	    <a onClick="slectExePeople(${loopNum});">
              <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
            </a>
		    <input type="hidden" name="exePeople" value="${planExePeopleName}" /> 
		    <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	      </@vcolumn>
	      <@vcolumn title="${action.getText('lubricationDetail.planExectPeople')}">
	    	<input type="text" name="ExectPeople" 
	    	       class="underline"  value="${object.planExectPeople?if_exists}"  maxlength="50" size="10"/>
	      	<@vlh.attribute name="style" value="VERTICAL-ALIGN:left"/>
      	  </@vcolumn>
	      <@vcolumn title="${action.getText('lubricationDetail.estimateExecDate')}">
	        <#assign estimateExecDate = ''/>
	        <#if object.estimateExecDate?exists>
	          <#assign estimateExecDate = "${(object.estimateExecDate?string('yyyy-MM-dd'))}"/>
	        </#if>
	        <@eam2008_dataPicker inputName="${estimateExecDateIdentityName}" inputId="${estimateExecDateIdentityName}" inputValue="${estimateExecDate}" imgId="${estimateExecDateImgIdentity}" formName="lubricationPlanDetail" disable="false"/>
		    <#assign estimateExecDateIdentityName = 'lubricationPlanDetail.estimateExecDate' + '${itemNo}'/>
		    <#assign estimateExecDateImgIdentity = 'lubricationPlanDetail_' + '${estimateExecDateIdentityName}' + '_trigger'/>
		    <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	      </@vcolumn>
	      <@vcolumn title="${action.getText('lubricationDetail.comment')}">
	        <input type="text" name="comment" 
	    	       class="underline"  value="${object.comment?if_exists}"  maxlength="250" size="15"/>
	      <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	      </@vcolumn>
	    <#else>
          <@vcolumn title="${action.getText('lubricationDetail.actualDutyPeople')}" property="planExePeople">
			<@alignLeft/>
	      </@vcolumn>
	      <@vcolumn title="${action.getText('lubricationDetail.planExectPeople')}" property="planExectPeople">
	    	<@alignLeft/>
      	  </@vcolumn>
	      <@vcolumn title="${action.getText('lubricationDetail.actualExectPeople')}" property="actualExePeople">
	    	<input type="text" name="ExectPeople" 
	    	       class="underline"  value="${object.actualExePeople?if_exists}"  maxlength="50" size="10"/>
	      	<@vlh.attribute name="style" value="VERTICAL-ALIGN:left"/>
      	  </@vcolumn>
          <@vcolumn title="${action.getText('lubricationDetail.estimateExecDate')}" property="estimateExecDate" format="yyyy-MM-dd">
	        <@alignCenter/>
          </@vcolumn>
          <@vcolumn title="${action.getText('lubricationDetail.actualEstimateExecDate')}">
	        <#assign actualExecDate = ''/>
	        <#if object.actualExecDate?exists>
	          <#assign actualExecDate = "${(object.actualExecDate?string('yyyy-MM-dd'))}"/>
	        </#if>
	        <@eam2008_dataPicker inputName="${estimateExecDateIdentityName}" inputId="${estimateExecDateIdentityName}" inputValue="${actualExecDate}" imgId="${estimateExecDateImgIdentity}" formName="lubricationPlanDetail" disable="false"/>
		    <#assign estimateExecDateIdentityName = 'lubricationPlanDetail.estimateExecDate' + '${itemNo}'/>
		    <#assign estimateExecDateImgIdentity = 'lubricationPlanDetail_' + '${estimateExecDateIdentityName}' + '_trigger'/>
		    <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	      </@vcolumn>
	      <@vcolumn title="${action.getText('lubricationPlanDetail.result')}">
		      <select name="${lubricationResultIdentityName}" valign="center">
			    <@ww.iterator value="planStatus" id="status">
			      <option value="<@ww.property value="value"/>"><@ww.property value="label"/></option>
			    </@ww.iterator>
		      </select>
		      <script language="javascript">
	            <#if object.planStatus?exists>
	              document.forms[0].elements["${lubricationResultIdentityName}"].value='${object.planStatus?if_exists}';
	            </#if>
	          </script>
	          <#assign lubricationResultIdentityName = 'lubricationResult' + '${itemNo}'/>
	          <@vlh.attribute name="style" value="VERTICAL-ALIGN:bottom"/>
		  </@vcolumn>
          <@vcolumn title="${action.getText('lubricationDetail.comment')}" property="comment" attributes="width='100'">
	        <@alignLeft/>
          </@vcolumn>
	    </#if>
	  </#if>
	  <#assign loopNum = loopNum+1/>
    </@list>
    <@buttonBar>
      <#if planProcFlag?exists>
        <#if planProcFlag == 'PLAN'>
        <#if !(action.isReadOnly())>
	      <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_lubricationDetailDialog(#{lubrication.id},null);"/>
	    <@vsubmit name="'save'" value="'${action.getText('save')}'">
        <@ww.param name="'onclick'" value="'return validate();'"/>
        <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
      </@vsubmit>
	      <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('lubricationPlanDetail')}?" />
          <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
            <@ww.param name="'onclick'" value="'return confirmDeletes(\"lubricationDetailIds\", \"${confirmMessage}\")'"/>
            <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
          </@vsubmit> 
          </#if>
          <#else>
             <#if !(action.isReadOnly())>
               <@vsubmit name="'save'" value="'${action.getText('save')}'">
               <@ww.param name="'onclick'" value="'return validate();'"/>
               <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
               </@vsubmit>
             </#if>
	    </#if>
	  </#if>
	  
	</@buttonBar>
  </@ww.form>
  <script language="javascript">
    <#if req.getParameter('errorFlag')?has_content>
      alert("${action.getText('delete.lubricationPlanDetail.failure')}");
    </#if>
    function validate() {
      if (0 != document.getElementsByName("detailIds").length) { 
        if(validateInformation()==true){
	        if (document.forms["lubricationPlanDetail"].elements["planProcFlag"].value == 'PLAN') {
	          retrieveComment();                                        //获取润滑计划明细备注所有非空值
	        } else {
	          retrieveLubricationResultText();                          //获取计划明细中执行结果的所有非空值
	        }
	        retrieveEstimateExecDateText();                           //获取润滑计划明细计划执行日期或实际执行日期所有非空值
	        <#if planProcFlag?exists>
			<#if (planProcFlag=='PLAN')>
	        retrievePlanExePeopleText();                              //获取润滑计划明细计划执行人或实际负责人所有非空值
	      	</#if>
	      	</#if>
	      	retrieveExectPeopleText();								  //执行人
	        if (!retrieveLubricationOilQty()) {
	           return false;
	         }                          							  //获取润滑计划明细计划润滑计量或实际润滑计量的所有非空值
	        retrieveLubricationPlanDetailIdText();                    //获取润滑计划明细ID
	        return true;
	     }else{
	     return false;
	     }
      }
      
    }
    /*
     * 计划执行人选择
    */
    function slectExePeople(loopNum) {
      document.forms["lubricationPlanDetail"].elements["currentRowNum"].value = loopNum;
      exePeople_OpenDialog();
    }
    function exePeople_OpenDialog() {
      var url = "${req.contextPath}/popup/userSelector.html";
	  popupModalDialog(url, 800, 600, exePeopleSelectorHandler);
    }
    function exePeopleSelectorHandler(result) {
      var allHiddenExePeople = document.getElementsByName("exePeople");
      var allExePeopleName = document.getElementsByName("exePeople.name");
      var currentRowNum = document.forms["lubricationPlanDetail"].elements["currentRowNum"].value;
      if (null != result) {
        allHiddenExePeople[currentRowNum].value = result[1];
        allExePeopleName[currentRowNum].value = result[1];
      }
    }
    
    /*
     * 获取润滑明细ID的所有值
    */
    function retrieveLubricationPlanDetailIdText() {
	  var allLubricationPlanDetailId = document.getElementsByName("detailIds");
	  var ary = new Array();
	  for (var i=0; i<allLubricationPlanDetailId.length; i++) {
	    ary.push(allLubricationPlanDetailId[i].value);
	  }
	  document.forms["lubricationPlanDetail"].elements["allLubricationPlanDetailId"].value = ary;
    }
    /*
     * 获取计划明细列表中计划润滑时间或实际润滑时间的所有非空值
    */
    function retrieveEstimateExecDateText() {
       var allLubricationPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allLubricationPlanDetailId.length; i++) {
         var estimateExecDateTagName = 'lubricationPlanDetail.estimateExecDate';
         estimateExecDateTagName = estimateExecDateTagName + (i+1); 
         if ('' != document.forms["lubricationPlanDetail"].elements[estimateExecDateTagName].value) {
           ary.push(allLubricationPlanDetailId[i].value);
           ary.push(document.forms["lubricationPlanDetail"].elements[estimateExecDateTagName].value);
         }
       }
       document.forms["lubricationPlanDetail"].elements["allEstimateExecDate"].value = ary;
     } 
     /*
      * 获取计划明细列表中备注的所有值
     */
     function retrieveComment() {
       var allComment = document.getElementsByName("comment");
       var allLubricationPlanDetailId = document.getElementsByName("detailIds");
       var ary = "";
       for (var i=0; i<allLubricationPlanDetailId.length; i++) {
         if ('' != allComment[i].value) {
           ary +=allLubricationPlanDetailId[i].value+"##";
           ary +=allComment[i].value+"##";
         }
       }
       ary=ary.substring(0,ary.lastIndexOf("##"));
       document.forms["lubricationPlanDetail"].elements["allComment"].value=ary;
     }
     /*
      * 获取计划明细列表中计划润滑计量的所有非空值
     */
     function retrieveLubricationOilQty() {
       var allLubricationOilQty = document.getElementsByName("lubricationOilQty");
       var allLubricationPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allLubricationPlanDetailId.length; i++) {
         if ('' != allLubricationOilQty[i].value) {
                arow1=i+1;
                var arow='第'+arow1;
                ary.push(allLubricationPlanDetailId[i].value);
                ary.push(formatDigital(allLubricationOilQty[i].value));
                if (!isDoubleNumberBetweenBoolean(allLubricationOilQty[i].value,1000000001,0)) {
                   alert( arow+" ${action.getText('行格式错误')}"+"${action.getText('select.right.ProcUseNum')}");
                   return false;
               }
            }
       }
       document.forms["lubricationPlanDetail"].elements["allLubricationOilQty"].value=ary;
       return true;
     }
     /*
      * 获取计划明细列表中计划负责人的所有值
     */
     function retrievePlanExePeopleText() {
       var allExePeople = document.getElementsByName("exePeople");
       var allLubricationPlanDetailId = document.getElementsByName("detailIds");
       var ary = "";
       for (var i=0; i<allLubricationPlanDetailId.length; i++) {
         if ('' != allExePeople[i].value) {
           ary +=allLubricationPlanDetailId[i].value+"##";
           ary +=allExePeople[i].value+"##";
         }
       }
       ary=ary.substring(0,ary.lastIndexOf("##"));
       document.forms["lubricationPlanDetail"].elements["allPlanExePeople"].value=ary;
     }
     /*
      * 获取计划明细列表中计划执行人的所有值
     */

     function retrieveExectPeopleText() {
       var allExePeople = document.getElementsByName("ExectPeople");
       var allLubricationPlanDetailId = document.getElementsByName("detailIds");
       var ary = "";
       for (var i=0; i<allLubricationPlanDetailId.length; i++) {
         if ('' != allExePeople[i].value) {
           ary +=allLubricationPlanDetailId[i].value+"##";
           ary +=allExePeople[i].value+"##";
         }
       }
       ary=ary.substring(0,ary.lastIndexOf("##"));
       document.forms["lubricationPlanDetail"].elements["allExectPeople"].value=ary;
     }
     
     function validateInformation(){
       var allLubricationPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allLubricationPlanDetailId.length; i++) {
         var estimateExecDateTagName = 'lubricationPlanDetail.estimateExecDate';
         estimateExecDateTagName = estimateExecDateTagName + (i+1); 
         if ('' != document.forms["lubricationPlanDetail"].elements[estimateExecDateTagName].value) {
				if(!validateTime(document.forms[0].elements[estimateExecDateTagName].value)){
				
				<#if planProcFlag?exists>
					<#if (planProcFlag=='PLAN')>
						alert("${action.getText('row')}"+(i+1)+"${action.getText('planCreatedTime.Mistake')}");
					<#else>
						alert("${action.getText('row')}"+(i+1)+"${action.getText('actualExecDate.Mistake')}");
					</#if>
				</#if>
	               return false;
	          	}
         }
       }
       return true;     	
     }
    /*
     * 获取实施明细列表中的执行结果的所有值
    */
     function retrieveLubricationResultText() {
       var allLubricationPlanDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allLubricationPlanDetailId.length; i++) {
         var lubricationResultTagName = 'lubricationResult';
         lubricationResultTagName = lubricationResultTagName + (i+1); 
         if(-1 != document.forms["lubricationPlanDetail"].elements[lubricationResultTagName].value) {
           ary.push(allLubricationPlanDetailId[i].value);
           ary.push(document.forms["lubricationPlanDetail"].elements[lubricationResultTagName].value);
         }
       }
       document.forms["lubricationPlanDetail"].elements["allLubricationResult"].value = ary;
     }
     /*
      * 弹出明细列表的维护页面
     */
     function open_lubricationDetailDialog(lubricationId, lubricationDetailId) {
	   var url = '${req.contextPath}/runmaintenance/lubrication/editLubricationPlanDetail.html?readOnly=${req.getParameter('readOnly')?if_exists}&lubrication.id=' + lubricationId;	 
	   if (lubricationDetailId != null) {
	     url = url + "&lubricationDetail.id=" + lubricationDetailId;
	   }
	   popupModalDialog(url, 800,600);
	   self.location.reload();
	 }
  </script>
</@framePage>