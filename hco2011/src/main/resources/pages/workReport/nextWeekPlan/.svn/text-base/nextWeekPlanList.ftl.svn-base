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
<@framePage >
<@ww.form id="'listFrom'" name="'listFrom'" action="'searchNextWeeklyPlan'" method="'post'">
	<@ww.token name="searchNextWeeklyPlanToken"/>
     <@ww.hidden name="'weekly.id'" value="${weekly.id}"/>
	<#assign number=1/>
	<@list title="" includeParameters="weekly.id" >
		<@vlh.checkbox property="id" name="nextWeekPlanIds">
            <@vlh.attribute name="width" value="30" />
        </@vlh.checkbox>
	    <@vcolumn title="${action.getText('nextWeekPlan.number')}"  >
            <a href="#" onclick="showNextWeeklyPlan('#{object.id}')">${number}</a>
            <#assign number=number+1 />
            <@alignCenter/>
        </@vcolumn>
        <@vcolumn title="${action.getText('nextWeekPlan.currentDate')}" property="currentDate" >
        	<@vlh.attribute name="width" value="120" />
        	${(object.currentDate?string('yyyy-MM-dd'))}
        </@vcolumn>
        <@vcolumn title="${action.getText('nextWeekPlan.weekDate')}" >
       	 	<@vlh.attribute name="width" value="80" />
        	${action.getText('nextWeekPlan.weekDate')}${object.weekDate}
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('nextWeekPlan.workPlan')}" property="workPlan" >
            <@alignLeft/>
        </@vcolumn>
	</@list>
    <@buttonBar>
    	<@vsubmit value="'${action.getText('new')}'" onclick="'return newNextWeeklyPlan(\"${weekly.id}\")'"/>
	  	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('nextWeekPlan.info')}?" />	 
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
               <@ww.param name="'onclick'" value="'return confirmDeletes(\"nextWeekPlanIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
   </@buttonBar>
	</@ww.form>
	<script language="javascript">
	function showNextWeeklyPlan(id){
    	var url ='${req.contextPath}/workReport/editNextWeekPlan.html?weekly.id=${weekly.id}&nextWeekPlan.id='+id;
    	popupModalDialog(url,800,600);
//		window.open(url);  	
		self.location.reload();
    }
    function newNextWeeklyPlan(id){
     	var url = '${req.contextPath}/workReport/editNextWeekPlan.html?weekly.id='+id;
     //	window.open(url);
     	popupModalDialog(url,800,600);
        self.location.reload();
    }
     
</script>

</@framePage >
	