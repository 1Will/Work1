<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('preRepairPlanDetailList.title')}">
   <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
	<@ww.form namespace="'/runmaintenance/repair/plan'" name="'preRepairPlanDetail'" action="'searchPreRepairPlanDetails'" method="'post'">
		 <@ww.token name="searchPreRepairPlanDetailsToken"/>
		 <@ww.hidden name="'addcheckListIds'" value=""/>
		 <@ww.hidden name="'addcheckList'" value=""/>
		 <@ww.hidden name="'addBrockenRateListIds'" value=""/>
		 <@ww.hidden name="'addBrockenRateList'" value=""/>
		 <@ww.hidden name="'addeasilydamagedSpartIds'" value=""/>
		 <@ww.hidden name="'easilydamagedSpartList'" value=""/>
		 <@ww.hidden name="'preRepairRuleIds'" value=""/>
		 <@ww.hidden name="'copy'" value=""/>
		 <@ww.hidden name="'preRepairRuleSelector'" value=""/>
		 <#if preRepairPlan?exists>
		 <#if preRepairPlan.id?exists>
         	<@ww.hidden name="'preRepairPlan.id'" value="#{preRepairPlan.id}"/>
         </#if>
         </#if>
         <@ww.hidden name="'preRepairPlanDetailHistoryIds'" value=""/>
         <#assign toolingDevNo=""/>
         <#assign toolingDevName=""/>
         <#if toolingDevFlag?exists>
           <#if toolingDevFlag=='DEVICE'>
             <#assign toolingDevNo="${action.getText('PreRepairPlanDetail.deviceNo')}"/>
             <#assign toolingDevName="${action.getText('PreRepairPlanDetail.deviceName')}"/>
           <#else>
             <#assign toolingDevNo="${action.getText('PreRepairPlanDetail.toolingNo')}"/>
             <#assign toolingDevName="${action.getText('PreRepairPlanDetail.toolingName')}"/>
           </#if>
         </#if>
         <input type="hidden" name="allPreRepairPlanDetailDutyDepartment" value=""/>
         <input type="hidden" name="allPreRepairPlanDetailExternalHelp" value=""/>
         <input type="hidden" name="allPreRepairPlanDetailDutyPeople" value=""/>
         <input type="hidden" name="allPreRepairPlanDetailExecPeople" value=""/>
         <input type="hidden" name="allPreRepairPlanDetailEstimateExecDate" value=""/>
         <input type="hidden" name="allPreRepairPlanDetailId" value=""/>
         <input type="hidden" name="allPreRepairProcExecResult" value=""/>
         <input type="hidden" name="allPreRepairPlanDetailDutyRepairLevel" value=""/>
         
		 <input type="hidden" name="currentRowNum" value=""/>
		 <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
		 <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#assign itemNo=1/>
		<#assign loopNum=0/>
		<#assign estimateExecDateIdentityName = 'preRepairPlanDetail.estimateExecDate' + '${itemNo}'/>
		<#assign estimateExecDateImgIdentity = 'preRepairPlanDetail_' + '${estimateExecDateIdentityName}' + '_trigger'/>
		<#assign departmentIdentityName = 'department' + '${itemNo}'/>
		<#assign externalHelpIdentityName = 'externalHelp' + '${itemNo}'/>
		<#assign execResultIdentityName = 'execResult' + '${itemNo}'/>
		<#assign repairLevelIdentityName = 'repairLevel' + '${itemNo}'/>
		<#assign dutyPeopleIdentity = 'dutyPeople' + '${loopNum}'/>
		<#assign execPeopleIdentity = 'execPeople' + '${loopNum}'/>
		<#assign procExecPeopleIdentity = 'procExecPeople' + '${loopNum}'/>
		<#assign detailListTitle=''/>
		<#if planProcFlag?exists>
          <#if (planProcFlag=='PLAN')>
		    <#assign detailListTitle="${action.getText('preRepairPlanDetail.list')}"/>
	      <#else>
		    <#assign detailListTitle = "${action.getText('preRepairProcDetail.list')}"/>
	      </#if>
        </#if>
        <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<@list title="" excel=false setupTable=false
        		includeParameters="preRepairPlan.id|readOnly|preRepairProc.id|planProcFlag|toolingDevFlag" 
        		fieldMap="like:" >
        		<input type="hidden" name="detailIds" value="#{object.id}"/>
        		<#if planProcFlag?exists>
                <#if (planProcFlag=='PLAN')>
	        		<@vlh.checkbox property="id" name="preRepairPlanDetailIds" <#--attributes="onclick=\"callBack();\""-->>
		            	<@vlh.attribute name="width" value="30" />
		            </@vlh.checkbox>
		         </#if>
		         </#if>
	            <@vcolumn title="${action.getText('PreRepairPlanDetail.serialNo')}">
	              <#if planProcFlag?exists>
        	        <#if (planProcFlag=='PLAN')>
                      <#if preRepairPlan.job?exists>
                        ${itemNo}
                      <#else>
                       <a href="#" onclick="return planDetailItem_openDialog(${loopNum},#{preRepairPlan.id},#{object.id});"/>${itemNo}</a>
                      </#if>
                    <#else>
                       <a href="#" onclick="return planDetailItem_openDialog(${loopNum},#{preRepairPlan.id},#{object.id});"/>${itemNo}</a>
                    </#if>
                  </#if>
	              <@alignCenter/>
	            </@vcolumn>
	            <#assign itemNo = itemNo+1/>
	            <@vcolumn title="${toolingDevNo}" property="asset.deviceNo">
	            <@alignLeft/>
                </@vcolumn>
                <#--
	            <@vcolumn title="${toolingDevName}" property="asset.name">
	            <@alignLeft/>
                </@vcolumn>
                -->
                <@vcolumn title="${toolingDevName}">
                  <#if object.asset?exists>
                    ${object.asset.name?if_exists}
                  <#else>
                    ${object.toolingName?if_exists}
                  </#if>
	            <@alignLeft/>
                </@vcolumn>
	            <#if toolingDevFlag?exists>
                <#if toolingDevFlag=='TOOLING'>
                  <@vcolumn title="${action.getText('PreRepairPlanDetail.toolingGraphNo')}" property="asset.graphNo">
                  <@alignLeft/>
                </@vcolumn>
                </#if>
                </#if>
                <#--
	            <@vcolumn title="${action.getText('PreRepairPlanDetail.position')}" property="position">
	            <@alignLeft/>
                </@vcolumn>
                -->
	            <@vcolumn title="${action.getText('PreRepairPlanDetail.content')}" property="content" attributes="width='150'">
	            <@alignLeft/>
                </@vcolumn>
        	    <#if planProcFlag?exists>
        	    <#if (planProcFlag=='PLAN')>
	        	    <@vcolumn title="${action.getText('PreRepairPlanDetail.department')}">
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
				    <@vcolumn title="${action.getText('PreRepairPlanDetail.isExternalHelp')}">
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
			      	<@vcolumn title="${action.getText('PreRepairPlanDetail.department')}" property="department.name">
			      	  <@alignLeft/>
                    </@vcolumn>
                    <#assign externalHelpFlag=''/>
			        <#if object.externalHelpFlag?string == 'true'>
			    	  <#assign externalHelpFlag="${action.getText('isExternalHelp.yes')}"/>
			        <#else>
			          <#assign externalHelpFlag="${action.getText('isExternalHelp.no')}"/>
			        </#if>
			        <@vcolumn title="${action.getText('PreRepairPlanDetail.isExternalHelp')}">
			          ${externalHelpFlag}
			        <@alignLeft/>
			    </@vcolumn>
			    </#if>
			    </#if>
			    <#if planProcFlag?exists>
        	    <#if (planProcFlag=='PLAN')>
        	    	<@vcolumn title="${action.getText('PreRepairPlanDetail.repairLevel')}">
				      <select name="${repairLevelIdentityName}">
					    <@ww.iterator value="repairLevels" id="repairLevel">
					      <option value="<@ww.property value="id"/>"><@ww.property value="value"/></option>
					    </@ww.iterator>
				      </select>
				      <script language="javascript">
			            <#if object.repairLevel?exists>
			              document.forms[0].elements["${repairLevelIdentityName}"].value='${object.repairLevel.id?if_exists}';
			            </#if>
			          </script>
			          <#assign repairLevelIdentityName = 'repairLevel' + '${itemNo}'/>
				    </@vcolumn>
				    <@vcolumn title="${action.getText('PreRepairPlanDetail.dutyPeople')}">
	                 <input type="text" name="dutypeople" 
	    		      class="underline"  value="${object.dutypeople?if_exists}"  maxlength="50" size="15"/>
	    		      <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	                </@vcolumn>
				    <#--
				    <@vcolumn title="${action.getText('PreRepairPlanDetail.dutyPeople')}">
				    <#assign dutyPeopleIdentity = 'dutyPeople' + '${loopNum}'/>
		        	<#assign dutyPeopleName = ''/>
					<#if object.dutyPeople?exists>
					 <#assign dutyPeopleName = "${object.dutyPeople.name}" />
					</#if>
		        		<input type="text" name="dutyPeople.name" 
		        			class="underline"  value="${dutyPeopleName}"  maxlength="150" size="10" disabled="false"/>
		        		<label id=""></label>
			    		<a onClick="slectDutyPeople(${loopNum});">
			        		<img id="${dutyPeopleIdentity}" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 style="display:inline"/>
			        	</a>
			        <#assign dutyPeopleId = ''/>
					<#if object.dutyPeople?exists>
					 	<#assign dutyPeopleId = "${object.dutyPeople.id}" />
					</#if>
					<input type="hidden" name="dutyPeople.id" value="${dutyPeopleId}" /> 
	                </@vcolumn>-->
                <#else>
                    <@vcolumn title="${action.getText('PreRepairPlanDetail.repairLevel')}" property="repairLevel.value">
                      <@alignLeft/>
                    </@vcolumn>
                  	<@vcolumn title="${action.getText('PreRepairPlanDetail.dutyPeople')}" property="dutypeople">
                  	  <@alignLeft/>
                  	</@vcolumn>
                </#if>
                </#if>
                <#if planProcFlag?exists>
        	    <#if (planProcFlag=='PLAN')>
        	    <@vcolumn title="${action.getText('PreRepairPlanDetail.execPeople')}">
	                 <input type="text" name="planExecPeople" 
	    		      class="underline"  value="${object.planExecPeople?if_exists}"  maxlength="50" size="15"/>
	    		      <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	                </@vcolumn>
	                <#--<@vcolumn title="${action.getText('PreRepairPlanDetail.execPeople')}">
	                <#assign execPeopleIdentity = 'execPeople' + '${loopNum}'/>
		        	<#assign execPeopleName = ''/>
					<#if object.execPeople?exists>
					 <#assign execPeopleName = "${object.execPeople.name}" />
					</#if>
		        		<input type="text" name="execPeople.name" 
		        			class="underline"  value="${execPeopleName}"  maxlength="150" size="10" disabled="false"/>
		        		<label id=""></label>
			    		<a onClick="slectExecPeople(${loopNum});">
			        		<img id="${execPeopleIdentity}"src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 style="display:inline"/>
			        	</a>
			        <#assign execPeopleId = ''/>
					<#if object.execPeople?exists>
					 	<#assign execPeopleId = "${object.execPeople.id}" />
					</#if>
					<input type="hidden" name="execPeople.id" value="${execPeopleId}" /> 
	                </@vcolumn>-->
	            <#else>
	            	<#--<@vcolumn title="${action.getText('PreRepairProcDetail.execPeople')}">
	            	<#assign procExecPeopleIdentity = 'procExecPeople' + '${loopNum}'/>
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
	                </@vcolumn>-->
	                <#--<#assign procExecPeopleName = ''/>
					<#if object.practiceExecPeople?exists>
					 <#assign procExecPeopleName = "${object.practiceExecPeople}" />
					<#elseif object.planExecPeople?exists>
					  <#assign procExecPeopleName="${object.planExecPeople}"/>
					</#if>-->
	                <@vcolumn title="${action.getText('PreRepairProcDetail.execPeople')}">
	                 <input type="text" name="planExecPeople" 
	    		      class="underline"  value="${object.practiceExecPeople?if_exists}"  maxlength="50" size="15"/>
	    		      <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	                </@vcolumn>
                </#if>
                </#if>
                <#if planProcFlag?exists>
        	    <#if (planProcFlag=='PLAN')>
	                <@vcolumn title="${action.getText('PreRepairPlanDetail.planEstimateFinishDate')}">
	                <#assign estimateExecDate = ''/>
	                <#if object.planEstimateFinishDate?exists>
	                  <#assign estimateExecDate = "${(object.planEstimateFinishDate?string('yyyy-MM-dd'))}"/>
	                </#if>
	                  <@eam2008_dataPicker inputName="${estimateExecDateIdentityName}" inputId="${estimateExecDateIdentityName}" inputValue="${estimateExecDate}" imgId="${estimateExecDateImgIdentity}" formName="preRepairPlanDetail"/>
	                  <#assign estimateExecDateIdentityName = 'preRepairPlanDetail.estimateExecDate' + '${itemNo}'/>
			          <#assign estimateExecDateImgIdentity = 'preRepairPlanDetail_' + '${estimateExecDateIdentityName}' + '_trigger'/>
	                </@vcolumn>
	            <#else>
	                <@vcolumn title="${action.getText('PreRepairProcDetail.estimateExecDate')}">
	                <#assign procEstimateExecDate = ''/>
	                <#if object.procEstimateFinishDate?exists>
	                  	<#assign procEstimateExecDate = "${(object.procEstimateFinishDate?string('yyyy-MM-dd'))}"/>
	                </#if>
	                  <@eam2008_dataPicker inputName="${estimateExecDateIdentityName}" inputId="${estimateExecDateIdentityName}" inputValue="${procEstimateExecDate}" imgId="${estimateExecDateImgIdentity}" formName="preRepairPlanDetail" />
	                  <#assign estimateExecDateIdentityName = 'preRepairPlanDetail.estimateExecDate' + '${itemNo}'/>
			          <#assign estimateExecDateImgIdentity = 'preRepairPlanDetail_' + '${estimateExecDateIdentityName}' + '_trigger'/>
	                </@vcolumn>
	            </#if>
	            </#if>
	            <@vcolumn title="${action.getText('preRepairPlan.procAllFee')}" property="planAllFee" format="#,###,##0.00"><@alignRight/></@vcolumn>
	            <#--
	            <#if planProcFlag?exists>
        	    <#if (planProcFlag=='PLAN')>
					<@vcolumn title="${action.getText('PreRepairPlanDetail.comment')}">
						<input type="text" name="preRepairPlanDetailComment" value="${object.comment?if_exists}"  class="underline"/>
					</@vcolumn>
			    <#else>
			    	<@vcolumn title="${action.getText('PreRepairPlanDetail.comment')}" property="comment"/>
				</#if>
				</#if>
			    -->     
			    <#if planProcFlag?exists>
        	    <#if (planProcFlag=='PROC')>
        	    	<@vcolumn title="${action.getText('PreRepairProcDetail.allFee')}" property="procAllFee" format="#,###,##0.##">
        	    	  <@alignRight/>
        	    	</@vcolumn>
        	    	<@vcolumn title="${action.getText('PreRepairProcDetail.result')}">
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
				<#assign loopNum=loopNum+1/>
        </@list>
        <#if !first>
			<@buttonBar>
			    <#if planProcFlag?exists>
        	    <#if (planProcFlag=='PLAN')>
        	    <#if !(action.isReadOnly())>
		    	<@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="planDetailItem_openDialog(null,#{preRepairPlan.id},null);"/>
		        <@vsubmit name="'save'" value="'${action.getText('save')}'">
		          <@ww.param name="'onclick'" value="'return customize_validate();'"/>
		          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		        </@vsubmit>
		        <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('preRepairPlanDetail')}?" />
		        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		        	<@ww.param name="'onclick'" value="'return confirmDeletes(\"preRepairPlanDetailIds\", \"${confirmMessage}\")'"/>
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
		        <#assign deptId=-1/>
		        <#if preRepairPlan.department?exists>
		          <#assign deptId = preRepairPlan.department.id/>
		        </#if>
		        <#if planProcFlag?exists>
        	      <#if (planProcFlag=='PLAN')>
        	      <#if !(action.isReadOnly())>
		            <@vbutton name="copyButton" class="button" value="${action.getText('preRepairPlanDetailHistory.copy')}" onclick="return preRepairPlanDetailHistory_OpenDialog('${toolingDevFlag?if_exists}','#{deptId}','#{preRepairPlan.id}');"/>
		            </#if>
		            <#if toolingDevFlag?exists>
                    <#if toolingDevFlag=='DEVICE'>
                    <#if !(action.isReadOnly())>
		              <@vbutton name="'brockenRateSelector'"  class="button" value="${action.getText('brockenRateSelector')}" onclick="open_brockenRateDialog()"/>
		          	  <@vbutton name="preRepairRuleSelectorButton"  class="button" value="${action.getText('preRepairRuleSelector')}" onclick="open_preRepairRuleSelectorDialog()"/>
		              <@vbutton name="'new'"  class="button" value="${action.getText('easilydamagedSpart')}" onclick="open_easilydamagedSpartDialog()"/>
                    </#if>
                    </#if>
		            </#if>
		            <#if !(action.isReadOnly())>
		            <@vbutton name="'usualCheckChoose'"  class="button" value="${action.getText('usualCheckChoose')}" onclick="open_uausalcheckDialog()"/>
		            </#if>
		          </#if>
		        </#if>
		    </@buttonBar>
	    </#if>
	</@ww.form>
	<script language="javascript">
	  	window.onload = function() {
	  	  <#if req.getParameter('errorFlag')?has_content>
            alert("${action.getText('delete.preRepairPlanDetail.failure')}");
          </#if> 
           <#if preRepairPlan.id?exists>
              if (document.forms[0].elements["planProcFlag"].value == 'PLAN') {
                parent.getObjByNameRe("preRepairPlan.planAllFee").value = '${preRepairPlan.planAllFee?if_exists}';
              } else {
                parent.getObjByNameRe("preRepairPlan.procAllFee").value = '${preRepairPlan.procAllFee?if_exists}';
                <#--
                parent.getObjByNameRe("preRepairPlan.procFinishStatus").value = '${preRepairPlan.procFinishStatus?if_exists}';
                
                <#assign procStatus=''/>
		        <#if preRepairPlan.procResult?exists>
		          <#if preRepairPlan.procResult == 'EXECUTED'>
		            <#assign procStatus="${action.getText('preRepairProc.executed')}"/>
		          <#elseif preRepairPlan.procResult == 'EXECUTEING'>
		            <#assign procStatus="${action.getText('preRepairProc.executeing')}"/>
		          <#else>
		            <#assign procStatus="${action.getText('preRepairProc.nonExecute')}"/>
		          </#if>
		        </#if>
		        parent.getObjByNameRe("preRepairPlan.procResult").value='${procStatus}';
		        -->
              }
            </#if>
          }
          <#--
		  <#if planProcFlag?exists>
        	<#if (planProcFlag=='PLAN')>
              <#if preRepairPlan.job?exists>
                __disableAllElements__(document.forms[0], new Array("print"));
                disabledPreRepairPlanDetailEstimateExecDate();
              </#if>
            </#if>
          </#if>
          -->
      /*
      *  从故障率选择
      */   
      function open_brockenRateDialog(){
        var url = '${req.contextPath}/runmaintenance/repair/plan/listBrockenRateChooses.html?toolingDevFlag=${toolingDevFlag?if_exists}';
        choose_brockenRate(url,choose_brockenRate_information)
      }
      function choose_brockenRate_information(reslut){
                if (null != result) {
		        var addBrockenRateListIds = result.substring(0, result.lastIndexOf(","));
		        document.forms[0].elements["addBrockenRateListIds"].value =addBrockenRateListIds;
		        document.forms[0].elements["addBrockenRateList"].value = "addBrockenRateLists";
		        document.forms[0].submit();
		      }
        } 
          
     /*
      *从日常检查选择
      */
     function open_uausalcheckDialog(){
        var url = '${req.contextPath}/runmaintenance/repair/plan/listuauslCheckchooses.html?toolingDevFlag=${toolingDevFlag?if_exists}';
        choose_uauslCheck(url,choose_uauslCheck_information)
      }   
      function choose_uauslCheck_information(reslut){
                if (null != result) {
		        var addcheckListIds = result.substring(0, result.lastIndexOf(","));
		        document.forms[0].elements["addcheckListIds"].value =addcheckListIds;
		        document.forms[0].elements["addcheckList"].value = "addcheckLists";
		        document.forms[0].submit();
		      }
        } 
        
        
      /*
       * 从易损件选择
       *
       */  
       
      function open_easilydamagedSpartDialog(){
        var url = '${req.contextPath}/runmaintenance/repair/plan/listdamagedSpartchooses.html?toolingDevFlag=${toolingDevFlag?if_exists}';
        choose_easilydamagedSpart(url,choose_easilydamagedSpart_information)
      }   
      function choose_easilydamagedSpart_information(reslut){
                if (null != result) {
		        var addeasilydamagedSpartIds = result.substring(0, result.lastIndexOf(","));
		        document.forms[0].elements["addeasilydamagedSpartIds"].value =addeasilydamagedSpartIds;
		        document.forms[0].elements["easilydamagedSpartList"].value = "easilydamagedSpartLists";
		        document.forms[0].submit();
		      }
        } 
       
	  /*
	   * 负责人选择
	  */
      <#--function slectDutyPeople(loopNum) {
         document.forms["preRepairPlanDetail"].elements["currentRowNum"].value = loopNum;
	     dutyPeople_OpenDialog();
      }
      function dutyPeople_OpenDialog() {
        var url = "${req.contextPath}/popup/userSelector.html";
		popupModalDialog(url, 800, 600, dutyPeopleSelectorHandler);
      }
      function dutyPeopleSelectorHandler(result) {
        var allDutyPeopleId = document.getElementsByName("dutyPeople.id");
        var allDutyPeopleName = document.getElementsByName("dutyPeople.name");
        var currentRowNum = document.forms["preRepairPlanDetail"].elements["currentRowNum"].value;
        if (null != result) {
          allDutyPeopleId[currentRowNum].value = result[0];
          allDutyPeopleName[currentRowNum].value = result[1];
        }
      }
      /*
       * 执行人选择
      */
      function slectExecPeople(loopNum) {
         document.forms["preRepairPlanDetail"].elements["currentRowNum"].value = loopNum;
	     execPeople_OpenDialog();
      }
      function execPeople_OpenDialog() {
        var url = "${req.contextPath}/popup/userSelector.html";
		popupModalDialog(url, 800, 600, execPeopleSelectorHandler);
      }
      function execPeopleSelectorHandler(result) {
        var allExecPeopleId = document.getElementsByName("execPeople.id");
        var allExecPeopleName = document.getElementsByName("execPeople.name");
        var currentRowNum = document.forms["preRepairPlanDetail"].elements["currentRowNum"].value;
        if (null != result) {
          allExecPeopleId[currentRowNum].value = result[0];
          allExecPeopleName[currentRowNum].value = result[1];
        }
      }-->
      /*
       * 打开维修详细页面
      */
      function planDetailItem_openDialog(loopNum,preRepairPlanId,preRepairPlanDetailId) {
        var url = '${req.contextPath}/popup/editPreRepairPlanDetailItem.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlan.id=' + preRepairPlanId;
        if (preRepairPlanDetailId != null) {
          url = url + '&preRepairPlanDetail.id=' + preRepairPlanDetailId;
        }
        url = url + '&planProcFlag=' + document.forms[0].elements["planProcFlag"].value;
        url = url + '&toolingDevFlag=' + document.forms[0].elements["toolingDevFlag"].value;
	    popupModalDialog(url, 1024,768);
	    var reloadUrl = '${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlan.id=' + preRepairPlanId + '&planProcFlag=${planProcFlag?if_exists}&toolingDevFlag=${toolingDevFlag?if_exists}';
	    self.location.href = reloadUrl; 
	    return false;
	  }
	 /*
	  *点击保存按钮的验证
	 */
	 function customize_validate() {
	   if (0 != document.getElementsByName("detailIds").length) {    //判断明细列表是否有记录
	     if (document.forms["preRepairPlanDetail"].elements["planProcFlag"].value == 'PLAN') {
	       if (!preRepairPlanDetailDepartmentAndDutyPeopleValidate()) {           //验证计划明细列表中责任单位和责任人是否输入
		     return false;
		   }
		   retrievePreRepairPlanDetailDepartmentText();                       //获取计划明细列表中责任单位的所有值
		   retrievePreRepairPlanDetailDutyPeopleText();                       //获取计划明细列表中责任人的所有值
	       retrievePreRepairPlanDetailRepairLevelText();                      //获取计划明细列表中维修等级的所有值 
	       retrieveExternalHelpText();                                        //获取计划明细列表中是否外协的所有值
	     } else {
	       retrievePreRepairProcexecResultText();                            //获取计划或实施明细列表中的执行结果的所有值                      
	     }
	     retrievePreRepairPlanDetailIdText();                               //获取计划明细列表中所有的明细ID
		 retrievePreRepairPlanDetailExePeopleText();                       //获取计划明细列表中执行人的所有值，或者获取实施明细列表中实际执行人的所有值
		 retrievePreRepairPlanDetailEstimateExecDateText();                //获取计划明细列表中计划预计执行时间的所有值，或者获取实施明细列表中实际执行时间的所有值
	   }
	   return true;	 
	 }
	 /*
      * 获取明细列表中所有的明细ID
	 */
	 function retrievePreRepairPlanDetailIdText() {
	   var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
	   var ary = new Array();
	   for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
	     ary.push(allPreRepairPlanDetailDetailId[i].value);
	   }
	   document.forms["preRepairPlanDetail"].elements["allPreRepairPlanDetailId"].value = ary;
     }
     /*
	  * 获取计划明细列表中责任单位的所有值
	 */
	 function retrievePreRepairPlanDetailDepartmentText() {
       var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         var departmentTagName = 'department';
         departmentTagName = departmentTagName + (i+1); 
         if(-1 != document.forms["preRepairPlanDetail"].elements[departmentTagName].value) {
           ary.push(allPreRepairPlanDetailDetailId[i].value);
           ary.push(document.forms["preRepairPlanDetail"].elements[departmentTagName].value);
         }
       }
       document.forms["preRepairPlanDetail"].elements["allPreRepairPlanDetailDutyDepartment"].value = ary;
     }
     /*
      * 获取计划明细列表中是否外协的所有值
     */
     function retrieveExternalHelpText() {
       var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         var externalHelpTagName = 'externalHelp';
         externalHelpTagName = externalHelpTagName + (i+1); 
         if(-1 != document.forms["preRepairPlanDetail"].elements[externalHelpTagName].value) {
           ary.push(allPreRepairPlanDetailDetailId[i].value);
           ary.push(document.forms["preRepairPlanDetail"].elements[externalHelpTagName].value);
         }
       }
       document.forms["preRepairPlanDetail"].elements["allPreRepairPlanDetailExternalHelp"].value = ary;
     }
     /*
      *  获取计划明细列表中维修等级的所有值
     */
     function retrievePreRepairPlanDetailRepairLevelText() {
       var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         var repairLevelTagName = 'repairLevel';
         repairLevelTagName = repairLevelTagName + (i+1); 
         if(-1 != document.forms["preRepairPlanDetail"].elements[repairLevelTagName].value) {
           ary.push(allPreRepairPlanDetailDetailId[i].value);
           ary.push(document.forms["preRepairPlanDetail"].elements[repairLevelTagName].value);
         }
       }
       document.forms["preRepairPlanDetail"].elements["allPreRepairPlanDetailDutyRepairLevel"].value = ary;
     }
     /*
	  * 获取明细列表中责任人的所有值
	 */
	 function retrievePreRepairPlanDetailDutyPeopleText() {
       <#--var allDutyPeople = document.getElementsByName("dutypeople");
       var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         if ('' != allDutyPeople[i].value) {
           ary.push(allPreRepairPlanDetailDetailId[i].value);
           ary.push(allDutyPeople[i].value);
         }
       }
       document.forms["preRepairPlanDetail"].elements["allPreRepairPlanDetailDutyPeople"].value=ary;-->
       
        var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
         var allDutyPeople=document.getElementsByName("dutypeople");
        
         var ary = new Array();
         for(var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
             if ('' != allDutyPeople[i].value) {
             ary.push(allPreRepairPlanDetailDetailId[i].value);
             ary.push(allDutyPeople[i].value);
         }
       }
      document.forms["preRepairPlanDetail"].elements["allPreRepairPlanDetailDutyPeople"].value=ary;
     }
     /*
      * 获取计划明细列表中执行人的所有值，或者获取实施明细列表中的所有值
     */
     function retrievePreRepairPlanDetailExePeopleText() {
     
       var allExecPeople = document.getElementsByName("planExecPeople");
       var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         if ('' != allExecPeople[i].value) {
          
           ary.push(allPreRepairPlanDetailDetailId[i].value);
           ary.push(allExecPeople[i].value);
         }
       }
       document.forms["preRepairPlanDetail"].elements["allPreRepairPlanDetailExecPeople"].value=ary;
     }
     /*
      * 获取计划明细列表中计划预计执行时间的所有值，或者获取实施明细列表中的实际执行时间的所有值
     */
     function retrievePreRepairPlanDetailEstimateExecDateText() {
       var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         var estimateExecDateTagName = 'preRepairPlanDetail.estimateExecDate';
         estimateExecDateTagName = estimateExecDateTagName + (i+1); 
         if ('' != document.forms["preRepairPlanDetail"].elements[estimateExecDateTagName].value) {
           ary.push(allPreRepairPlanDetailDetailId[i].value);
           ary.push(document.forms["preRepairPlanDetail"].elements[estimateExecDateTagName].value);
         }
       }
       document.forms["preRepairPlanDetail"].elements["allPreRepairPlanDetailEstimateExecDate"].value = ary;
     }
     /*
      * 失效计划明细列表中计划完工日期的时间选择
     */
     function disabledPreRepairPlanDetailEstimateExecDate() {
       var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         var estimateExecDateTagName = 'preRepairPlanDetail.estimateExecDate';
         estimateExecDateTagName = estimateExecDateTagName + (i+1); 
         var x = document.forms[0].name + "_" + estimateExecDateTagName + "_trigger";
         document.forms[0].elements[x].disabled= true;
       }
     }
     /*
      * 获取实施明细列表中的执行结果的所有值
     */
     function retrievePreRepairProcexecResultText() {
       var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         var execResultTagName = 'execResult';
         execResultTagName = execResultTagName + (i+1); 
         ary.push(allPreRepairPlanDetailDetailId[i].value);
         ary.push(document.forms["preRepairPlanDetail"].elements[execResultTagName].value);
       }
       document.forms["preRepairPlanDetail"].elements["allPreRepairProcExecResult"].value = ary; 
     }
     /*
      * 验证明细列表中责任单位和责任人是否输入
     */
     function preRepairPlanDetailDepartmentAndDutyPeopleValidate() {
       var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var allDutyPeople = document.getElementsByName("dutypeople");
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         var departmentTagName = 'department';
         departmentTagName = departmentTagName + (i+1); 
         if(-1 == document.forms["preRepairPlanDetail"].elements[departmentTagName].value) {
           alert("${action.getText('input')} " + (i+1) + " ${action.getText('row')}" + " ${action.getText('PreRepairPlanDetail.department')}")
           return false;
         }
         if ('' == allDutyPeople[i].value) {
           alert("${action.getText('请输入')}"+"${action.getText('PreRepairPlanDetail.dutyPeople')}");
           return false;
         }
       }
       return true;
     }
	 //点击"复制历史明晰"按钮,触发历史明细选择
	 function preRepairPlanDetailHistory_OpenDialog(toolingDevFlag, deptId, preRepairPlanId) {
	   var url = '${req.contextPath}/popup/preRepairPlanDetailHistorySelector.html?toolingDevFlag=' + toolingDevFlag;
	   if (null != deptId || '-1' != deptId) {
	     url = url + '&department.id=' + deptId;
	   }
	   if (null != preRepairPlanId) {
	     url = url + '&preRepairPlan.id=' + preRepairPlanId;
	   }
       popupModalDialog(url, 800, 600, refresh_preRepairPlanDetail_information);
       return true;	 
	 }
	 function refresh_preRepairPlanDetail_information(result) {
	   if (null != result) {
	     var preRepairPlanDetailHistoryIds = result.substring(0, result.lastIndexOf(","));
	     document.forms[0].elements["preRepairPlanDetailHistoryIds"].value=preRepairPlanDetailHistoryIds;
	     document.forms[0].elements["copy"].value = "copy";
	     document.forms[0].submit();
	   }
	 }
	 /*
      *从预维标准选择
      */
     function open_preRepairRuleSelectorDialog(){
        var url = '${req.contextPath}/popup/preRepairRuleSelector.html';
        popupModalDialog(url, 800, 600,choose_preRepairRule_information)
      }   
      function choose_preRepairRule_information(reslut){
        if (null != result) {
          var preRepairRuleIds = result.substring(0, result.lastIndexOf(","));
          document.forms[0].elements["preRepairRuleIds"].value =preRepairRuleIds;
          document.forms[0].elements["preRepairRuleSelector"].value = "preRepairRuleSelector";
          document.forms[0].submit();
        }
      } 
	</script>
</@framePage>
