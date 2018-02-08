<#include "../../../includes/macros.ftl" />
<@htmlPage title="${action.getText('checkPiontPlan.title')}">
<base target="_self">

	 <@ww.form name="'listForm'" action="'searchCheckPointPlanSelector'" method="'post'">
	 	<@ww.token name="searchCheckPointPlanSelectorToken"/>
	 	<#include "checkPointPlanSearcher.ftl"/>
	 	  <#assign feeSum = 0/> 
        <@buttonBar> 
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>       
        </@buttonBar>
          <@list title="${action.getText('checkPointPlan.list')}" 
         		includeParameters="id|rule.ruleNo|name|department.id|scheduleTime_start|scheduleTime_end" 
         		fieldMap="like:id|rule.ruleNo|namedepartment.id,date:scheduleTime_start|scheduleTime_end" >
            <@vcolumn title="${action.getText('plan.no')}" property="planNo" sortable="desc">
                <a href="javascript: returnDialog(new Array('#{object.id?if_exists}','${object.planNo?if_exists}',
                																 '${object.name?if_exists}','${object.device.deviceNo?if_exists}', 
                																 '${object.device.name?if_exists}','${object.device.id?if_exists}', 
                																 '${object.device.department.name?if_exists}','${object.device.boughtDate?if_exists}',
                																 '${object.scheduleTime?if_exists}','${object.content?if_exists}',
                																 '${object.comment?if_exists}','${object.request?if_exists}',
                																 '${object.manager.name}', '#{object.manager.id}',<#if (object.planDetails.size()>0)>
		                                                                                                                                <#list object.planDetails as planDetail>
		                                                                                                                                  <#assign feeSum=feeSum+planDetail.fee />
		                                                                                                                                 </#list>
		                                                                                                                          </#if>'#{feeSum}'));">${object.planNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('plan.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('device.no')}" property="device.deviceNo" />
            <@vcolumn title="${action.getText('device.name')}" property="device.name" />
            <@vcolumn title="${action.getText('plan.scheduleTime')}" property="scheduleTime" format="yyyy-MM-dd" />
            <@vcolumn title="${action.getText('plan.content')}" property="content" />
            <@vcolumn title="${action.getText('plan.request')}" property="request" />
            <#--
            <@vcolumn title="${action.getText('createDate')}" property="createdTime" format="yyyy-MM-dd" />
            <@vcolumn title="${action.getText('creator')}" property="creator" />
            -->
            <@vcolumn title="${action.getText('device.department')}" property="device.department.name" />
            <@vcolumn title="${action.getText('plan.status')}" property="job.docState.value"/>
        </@list>
	      <script language="javascript">
	      window.onload=function(){
	      document.forms[0].elements["docState.code"].disabled=true;
	      document.forms[0].elements["docState.code"].value = 'DOC_APPROVED';
	      }
		    function checkInvalidParms(){		    		
		          if (document.getElementById("department.id").value==-1) {
		          	document.getElementById("invalid").value=true;
		          	document.getElementById("department.id").value='';
		          } else {
		          	document.getElementById("invalid").value=false;
		          }
		          return true;
		      }
		 </script>
      </@ww.form>
</@htmlPage>