<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: dailyTAbProfile.ftl 2010-02-25 zsz $ -->
<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('daily.profile')}">
	<@ww.form action="'saveDaily'" namespace="'/workReport'" method="'post'">
		<@ww.token name="'saveDaily'"/>
		 <@ww.hidden name="'daily.id'" value="${req.getParameter('daily.id')?if_exists}"/>
		 <@ww.hidden name="'weekly.id'" value="'${weeklyId?if_exists}'"/>
		<@inputTable>
		  <tr>
		   <@datePickerRanger
			anothername="currentDate"
    		label="${action.getText('daily.currentDate')}"
           	name="daily.currentDate"
     		value="${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}" 
			cssClass="underline" 
			flag="true"
			required="true"/>
			<@ww.select label="'${action.getText('daily.week')}'" 
				name="'daily.week'"
				value="'${daily.weekDate?if_exists}'"
				list="{
					'',
					'一',
					'二',
					'三',
					'四',
					'五',
					'六',
					'日'
					}"
				required="true"
				/>
		   <#include "time.ftl"/>
		  </tr>
		  <tr>
		  <@textarea id="daily.workContext" name="daily.workContext" anothername="workContext" label="${action.getText('daily.workContext')}" required="true" value="${daily.workContext?if_exists}"/>
	     </tr>
		 <tr>
		    <@textarea id="daily.questions" name="daily.questions" anothername="questions" label="${action.getText('daily.questions')}" value="${daily.questions?if_exists}" />
	     </tr> 
	     <tr>
           <@textarea id="daily.solutions" name="daily.solutions" anothername="solutions" label="${action.getText('daily.solutions')}" value="${daily.solutions?if_exists}"/> 
         </tr>
	     <tr>
		   <@textarea id="daily.tomorrowPlan" name="daily.tomorrowPlan" anothername="tomorrowPlan" label="${action.getText('daily.tomorrowPlan')}" value=" ${daily.tomorrowPlan?if_exists}"/>
	    </tr>
	    <tr>
		   <@textarea id="daily.leaderIdea" name="daily.leaderIdea" anothername="leaderIdea" label="${action.getText('daily.leaderIdea')}" value="${daily.leaderIdea?if_exists}"/>
	    </tr>
	    <tr>
		  <@textarea id="daily.comment" name="daily.comment" anothername="comment" label="${action.getText('daily.comment')}" value="${daily.comment?if_exists}"/>
	    </tr>
		</@inputTable>
	<@buttonBar>
	    <@vsubmit value="'${action.getText('save')}'" onclick="'return check();'"/>
	    <@vbutton value="${action.getText('back')}" class="button" onclick="javascript:window.close();"/>
	</@buttonBar>
	</@ww.form>
	<script>
	    function check(){
	     if(!dateCheck_currentDate()){
	     getObjByName('daily.currentDate').focus();
	     return false;
	     }
	     if(!textareaCheck_workContext()){
	     getObjByName('daily.workContext').focus();
	     return false;
	     }
	     return true;
	    }
	    document.onclick = function (){
			var mydate = getObjByName('daily.currentDate').value;
		    re   =   /^(\d{2,4})-(\d{1,2})-(\d{1,2})$/g;   
		    if((mydate!="")&&(re.test(mydate)))   
		    { 
		        year1   =   mydate.replace(re,"$1");   
		        month1  =   mydate.replace(re,"$2");   
		        day1    =   mydate.replace(re,"$3");
		        getObjByName('daily.week').value="日一二三四五六".charAt(new   Date(year1+'/'+month1+'/'+day1).getDay()); 
		    }  
		    } 

	    window.onload=function (){
	        
	        
	        var startHourSelector = document.all("startHour");
			var startHourGroups = startHourSelector.options.length;
			var sh='${startHour?if_exists}';
			
			for (i=0; i<startHourGroups; i++) {
			    if (startHourSelector.options[i].value == sh) {
					startHourSelector.options[i].selected="true";
			    }
			}
			
			var startMinuteSelector = document.all("startMinute");
			startMinuteGroups = startMinuteSelector.options.length;
			var sm='${startMinute?if_exists}';
			
			for (i=0; i<startMinuteGroups; i++) {
				    if (startMinuteSelector.options[i].value == sm) {
					startMinuteSelector.options[i].selected="true";
				    }
			}
			var endHourSelector = document.all("endHour");
			var endHourGroups = endHourSelector.options.length;
			var eh='${endHour?if_exists}';
			
			for (i=0; i<endHourGroups; i++) {
				    if (endHourSelector.options[i].value == eh) {
						endHourSelector.options[i].selected="true";
				    }
			}
			var endMinuteSelector = document.all("endMinute");
			var endMinuteGroups = endMinuteSelector.options.length;
			var em='${endMinute?if_exists}';
			
			for (i=0; i<endMinuteGroups; i++) {
				    if (endMinuteSelector.options[i].value == em) {
						endMinuteSelector.options[i].selected="true";
				    }
			}
		
	    }
	    
		<#-- 注册事件-->
		function addEvent(){
			jgetObjByName("#daily\\.workContext").change(function(){
				perType();
			});	
			jgetObjByName("#daily\\.questions").change(function(){
				perType();
			});	
			jgetObjByName("#daily\\.solutions").change(function(){
				perType();
			});	
			jgetObjByName("#daily\\.tomorrowPlan").change(function(){
				perType();
			});	
			jgetObjByName("#daily\\.leaderIdea").change(function(){
				perType();
			});	
			jgetObjByName("#daily\\.comment").change(function(){
				perType();
			});	
		}
		function perType(){
			<#if perType=='0'>
				jgetObjByName("#daily\\.workContext").attr("disabled",false);
				jgetObjByName("#daily\\.questions").attr("disabled",false);
				jgetObjByName("#daily\\.solutions").attr("disabled",false);
				jgetObjByName("#daily\\.tomorrowPlan").attr("disabled",false);
				jgetObjByName("#daily\\.leaderIdea").attr("disabled",true);		
				jgetObjByName("#daily\\.comment").attr("disabled",false);
			</#if>
			<#if perType=='1'>
				jgetObjByName("#daily\\.workContext").attr("disabled",true);
				jgetObjByName("#daily\\.questions").attr("disabled",true);
				jgetObjByName("#daily\\.solutions").attr("disabled",true);
				jgetObjByName("#daily\\.tomorrowPlan").attr("disabled",true);	
				jgetObjByName("#daily\\.leaderIdea").attr("disabled",false);		
				jgetObjByName("#daily\\.comment").attr("disabled",true);
			</#if>
			<#if perType=='2'>
				jgetObjByName("#daily\\.workContext").attr("disabled",true);
				jgetObjByName("#daily\\.questions").attr("disabled",true);
				jgetObjByName("#daily\\.solutions").attr("disabled",true);
				jgetObjByName("#daily\\.tomorrowPlan").attr("disabled",true);	
				jgetObjByName("#daily\\.leaderIdea").attr("disabled",false);		
				jgetObjByName("#daily\\.comment").attr("disabled",true);
			</#if>
		}
		jgetObjByName(function(){
			perType();
			addEvent();
		});	
	</script>
</@htmlPage>