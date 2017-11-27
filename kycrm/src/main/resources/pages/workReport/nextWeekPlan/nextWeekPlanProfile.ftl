<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('nextWeekPlan.profile.title')}">
     <@ww.form  name="'listFrom'" action="'saveNextWeekPlan'" namespace="'/workReport'" method="'post'" >
     	 <@ww.token name="saveNextWeeklyPlanToken"/>
     	  <@inputTable>
     		<#if nextWeekPlan?exists>
     	  		<@ww.hidden name="'nextWeekPlan.id'" value="${nextWeekPlan.id?if_exists}"/>
     	  	</#if>
     	    <@ww.hidden name="'weekly.id'" value="${weekly.id?if_exists}"/>
     	  	<@ww.hidden name="'personId'" value=""/>
		     <tr>
		     	<@datePickerRanger
					anothername="currentDate"
					label="${action.getText('nextWeekPlan.currentDate')}"
			       	name="nextWeekPlan.currentDate"
			 		value="${(nextWeekPlan.currentDate?string('yyyy-MM-dd'))?if_exists}" 
					cssClass="underline" 
					maxlength="10" 
					required="true"
					flag="true"/>
  	 	  		<@ww.select label="'${action.getText('nextWeekPlan.weekDate')}'" 
				name="'nextWeekPlan.weekDate'"
				value="'${nextWeekPlan.weekDate?if_exists}'"
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
				<td colspan="10"></td>
			</tr>
			<tr>
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('nextWeekPlan.workPlan')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		       	<textarea name="nextWeekPlan.workPlan" rows="4"  >${nextWeekPlan.workPlan?if_exists}</textarea>
		        </td>
		        <script language="javascript">
					var width=document.body.clientWidth/9;
					getObjByName("nextWeekPlan.workPlan").cols =width;
				</script>
			</tr>				
			<tr>
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('nextWeekPlan.comment')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="nextWeekPlan.comment" rows="4"  >${nextWeekPlan.comment?if_exists}</textarea>
		        </td>
		        <script language="javascript">
					var width=document.body.clientWidth/9;
					getObjByName("nextWeekPlan.comment").cols =width;
				</script>
			</tr>
			</@inputTable>
		<@buttonBar>
		<#if !(action.isReadOnly())>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
		</#if>
	         <@vsubmit  value="'${action.getText('close')}'" onclick="'return closePage()'"/>
         </@buttonBar>	
		</@ww.form>
</@htmlPage>
<script language="javascript">
document.onclick = function (){
	var mydate = getObjByName('nextWeekPlan.currentDate').value;
    re   =   /^(\d{2,4})-(\d{1,2})-(\d{1,2})$/g;   
    if((mydate!="")&&(re.test(mydate)))   
    { 
        year1   =   mydate.replace(re,"$1");   
        month1  =   mydate.replace(re,"$2");   
        day1    =   mydate.replace(re,"$3");
        getObjByName('nextWeekPlan.weekDate').value= "日一二三四五六".charAt(new   Date(year1+'/'+month1+'/'+day1).getDay()); 
    }   
}
window.onload=function (){
<#if req.getParameter('nextWeekPlan.currentDate')?exists>
	getObjByName('nextWeekPlan.currentDate').value='${req.getParameter('nextWeekPlan.currentDate')?if_exists}';
</#if>
<#if req.getParameter('nextWeekPlan.weekDate')?exists>
	getObjByName('nextWeekPlan.weekDate').value='${req.getParameter('nextWeekPlan.weekDate')?if_exists}';
</#if>
<#if req.getParameter('nextWeekPlan.workPlan')?exists>
	getObjByName('nextWeekPlan.workPlan').value='${req.getParameter('nextWeekPlan.workPlan')?if_exists}';
</#if>
<#if req.getParameter('nextWeekPlan.comment')?exists>
	getObjByName('nextWeekPlan.comment').value='${req.getParameter('nextWeekPlan.comment')?if_exists}';
</#if>
}
 function validate(){
 		//日期
		if(!dateCheck_currentDate()){
			getObjByName('nextWeekPlan.currentDate').focus();
			return false;
		}
		if(getObjByName('nextWeekPlan.weekDate').value==""){
			alert('${action.getText('nextWeekPlan.weekDate.requested')}');
			getObjByName('nextWeekPlan.weekDate').focus();
			return false;
		}
		if(getObjByName('nextWeekPlan.workPlan').value.length>500){
			alert('${action.getText('nextWeekPlan.workPlan.alert')}')
			return false;
		}
		if(getObjByName('nextWeekPlan.comment').value.length>500){
			alert('${action.getText('nextWeekPlan.comment.alert')}')
			return false;
		}
 }
	function closePage(){
        	window.close();
        	return false;
        }
</script>