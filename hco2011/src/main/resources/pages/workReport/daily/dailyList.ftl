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

<#-- $Id: dailyList.ftl 2010-02-25 zsz $ -->
<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('daily.search')}">
<@ww.form action="'listDaily'" namespace="'/workReport'" method="'post'">
     <@ww.token name="'searchDaily'"/>
    <#-- <@ww.hidden name="'org.id'" value="${orgId?if_exists}"/>-->
	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 <#include "dailySearch.ftl"/>
     <@buttonBar>
		<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
     </@buttonBar>
	 <@list title="${action.getText('daily.list')}" includeParameters="currentDate|rapporteur.name|inst.id|dept.id|duty.id" fieldMap="" >
	        
	        <@vcolumn title="${action.getText('daily.currentDate')}" format="yyyy-MM-dd" property="currentDate" sortable="desc">
            	<a href="###" onclick="editDaily('#{object.id}','#{object.weekly.id}')">${object.currentDate?string('yyyy-MM-dd')}</a>
            	<@vlh.attribute name="width" value="11%" />
            	<@alignCenter/>
         	</@vcolumn>
         	<@vcolumn title="${action.getText('daily.week')}" property="weekDate" sortable="desc">
         	     <@vlh.attribute name="width" value="6%" />
         	     ${action.getText('daily.week')}${object.weekDate}
         	     <@alignLeft/>
         	</@vcolumn>
            <@vcolumn title="${action.getText('daily.rapporteur')}" property="rapporteur.name" sortable="desc">
            	<@vlh.attribute name="width" value="6%" />
            	<@alignLeft/>
         	</@vcolumn> 
	         <@vcolumn title="${action.getText('daily.inst')}" property="inst.name" sortable="desc">
	            	<@vlh.attribute name="width" value="7%" />
	            	<@alignLeft/>
	         </@vcolumn>
	         <@vcolumn title="${action.getText('daily.dept')}" property="dept.name" sortable="desc">
	            	<@vlh.attribute name="width" value="7%" />
	            	<@alignLeft/>
	         </@vcolumn>
	         <@vcolumn title="${action.getText('daily.duty')}" property="duty.name" sortable="desc">
	            	<@vlh.attribute name="width" value="7%" />
	            	<@alignLeft/>
	         </@vcolumn> 
	         <@vcolumn title="${action.getText('daily.workContext')}" property="workContext" sortable="desc" >
	            	<@vlh.attribute name="width" value="50%" />
	            	<@alignLeft/>
	         </@vcolumn>
	         <#--
	         <@vcolumn title="${action.getText('daily.questions')}" property="questions" sortable="desc">
	            	<@vlh.attribute name="width" value="10%" />
	            	<@alignLeft/>
	         </@vcolumn>
	         <@vcolumn title="${action.getText('daily.solutions')}" property="solutions" sortable="desc">
	            	<@vlh.attribute name="width" value="10%" />
	            	<@alignLeft/>
	         </@vcolumn>
	         <@vcolumn title="${action.getText('daily.tomorrowPlan')}" property="tomorrowPlan" sortable="desc">
	            	<@vlh.attribute name="width" value="10%" />
	            	<@alignLeft/>
	         </@vcolumn>
	         <@vcolumn title="${action.getText('daily.leaderIdea')}" property="leaderIdea" sortable="desc">
	            	<@vlh.attribute name="width" value="10%" />
	            	<@alignLeft/>
	         </@vcolumn>
	         -->
	         </@list>
</@ww.form>
<script>
  //打开编辑日报模态窗口
  function editDaily(d,w){
      var url='${req.contextPath}/workReport/editDaily.html?daily.id='+d+'&weekly.id='+w;
      popupModalDialog(url,800,600);
      self.location.reload();
  }
</script>
</@htmlPage>