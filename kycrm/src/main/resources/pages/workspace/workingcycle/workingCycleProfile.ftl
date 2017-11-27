<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('workingCycle.title')}">
 <@ww.form namespace="'/workingCycle'" name="'WorkingCycle'" action="'saveWorkingCycle'" method="'post'" validate="true">
 <@ww.token name="saveWorkingCycleToken"/>
    <@inputTable>
        <#if workingCycle.id?exists>
            <@ww.hidden name="'workingCycle.id'" value="#{workingCycle.id}"/>
        </#if>
       <#include "time.ftl"/>
    </@inputTable>
     <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" />
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/workingCycle/listWorkingCycle.html"/>
      </@buttonBar>
 </@ww.form>
 <script>
 	window.onload=function (){
	        var upStartHourSelector = document.all("upStartHour");
			var upStartHourGroups = upStartHourSelector.options.length;
			var sh='${upStartHour?if_exists}';
			
			for (i=0; i<upStartHourGroups; i++) {
			    if (upStartHourSelector.options[i].value == sh) {
					upStartHourSelector.options[i].selected="true";
			    }
			}
			
			var upStartMinuteSelector = document.all("upStartMinute");
			var upStartMinuteGroups = upStartMinuteSelector.options.length;
			var sm='${upStartMinute?if_exists}';
			
			for (i=0; i<upStartMinuteGroups; i++) {
				    if (upStartMinuteSelector.options[i].value == sm) {
					upStartMinuteSelector.options[i].selected="true";
				    }
			}
			
			
			var upEndHourSelector = document.all("upEndHour");
			var upEndHourGroups = upEndHourSelector.options.length;
			var sh='${upEndHour?if_exists}';
			
			for (i=0; i<upEndHourGroups; i++) {
			    if (upEndHourSelector.options[i].value == sh) {
					upEndHourSelector.options[i].selected="true";
			    }
			}
			
			var upEndMinuteSelector = document.all("upEndMinute");
			var upEndMinuteGroups = upEndMinuteSelector.options.length;
			var sm='${upEndMinute?if_exists}';
			
			for (i=0; i<upEndMinuteGroups; i++) {
				    if (upEndMinuteSelector.options[i].value == sm) {
					upEndMinuteSelector.options[i].selected="true";
				    }
			}
			
			
			var downEndHourSelector = document.all("downEndHour");
			var downEndHourGroups = downEndHourSelector.options.length;
			var eh='${downEndHour?if_exists}';
			
			for (i=0; i<downEndHourGroups; i++) {
				    if (downEndHourSelector.options[i].value == eh) {
						downEndHourSelector.options[i].selected="true";
				    }
			}
			var downEndMinuteSelector = document.all("downEndMinute");
			var downEndMinuteGroups = downEndMinuteSelector.options.length;
			var em='${downEndMinute?if_exists}';
			
			for (i=0; i<downEndMinuteGroups; i++) {
				    if (downEndMinuteSelector.options[i].value == em) {
						downEndMinuteSelector.options[i].selected="true";
				    }
			}
			
			var downStartHourSelector = document.all("downStartHour");
			var downStartHourGroups = downStartHourSelector.options.length;
			var eh='${downStartHour?if_exists}';
			
			for (i=0; i<downStartHourGroups; i++) {
				    if (downStartHourSelector.options[i].value == eh) {
						downStartHourSelector.options[i].selected="true";
				    }
			}
			var downStartMinuteSelector = document.all("downStartMinute");
			var downStartMinuteGroups = downStartMinuteSelector.options.length;
			var em='${downStartMinute?if_exists}';
			
			for (i=0; i<downStartMinuteGroups; i++) {
				    if (downStartMinuteSelector.options[i].value == em) {
						downStartMinuteSelector.options[i].selected="true";
				    }
			}
		
	    }
 </script>
</@htmlPage>