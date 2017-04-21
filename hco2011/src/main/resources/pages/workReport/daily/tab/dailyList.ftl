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
<#include "../../../includes/hco2011.ftl" />
<@framePage>
<@ww.form action="'searchDaily'" namespace="'/workReport'" method="'post'">
     <@ww.token name="'dailyManager'"/>
     <#assign number=1/>
     <@ww.hidden name="'weekly.id'" value="${req.getParameter('weekly.id')?if_exists}"/>
     <@ww.hidden name="'org.id'" value="${req.getParameter('org.id')?if_exists}"/>
     <@ww.hidden name="'rapporteur.id'" value="${req.getParameter('rapporteur.id')?if_exists}"/>
	 <@list title="" includeParameters="weekly.id|org.id|rapporteur.id" fieldMap="" >
	        <@vlh.checkbox property="id" name="dailyIds">
	            <@vlh.attribute name="width" value="5%" />
	        </@vlh.checkbox>
	        <@vcolumn title="${action.getText('daily.no')}">
	        <@vlh.attribute name="width" value="5%" />
	         <a href="###" onclick="editDaily('#{object.id}')">${number}</a>
            <#assign number=number+1 />
            <@alignCenter/>
	        </@vcolumn>
	        <@vcolumn title="${action.getText('daily.currentDate')}" format="yyyy-MM-dd" property="currentDate" >
            	<@vlh.attribute name="width" value="11%" />
            	<@alignCenter/>
         	</@vcolumn>
         	<@vcolumn title="${action.getText('daily.week')}" property="weekDate" >
         	    <@vlh.attribute name="width" value="6%" />
         	    ${action.getText('daily.week')}${object.weekDate}
         	<@alignLeft/>
         	</@vcolumn>
            <@vcolumn title="${action.getText('daily.rapporteur')}" property="rapporteur.name" >
            	<@vlh.attribute name="width" value="6%" />
            	<@alignLeft/>
         	</@vcolumn> 
	         <@vcolumn title="${action.getText('daily.workContext')}" property="workContext" >
	            <@vlh.attribute name="width" value="50%" />
	            <@alignLeft/>
	         </@vcolumn>
	         <#--
	         <@vcolumn title="${action.getText('daily.questions')}" property="questions" >
	            <@vlh.attribute name="width" value="10%" />
	            <@alignLeft/>
	         </@vcolumn>
	         <@vcolumn title="${action.getText('daily.solutions')}" property="solutions" >
	            <@vlh.attribute name="width" value="10%" />
	            <@alignLeft/>
	         </@vcolumn>
	         <@vcolumn title="${action.getText('daily.tomorrowPlan')}" property="tomorrowPlan" >
	            <@vlh.attribute name="width" value="10%" />
	            <@alignLeft/>
	         </@vcolumn>
	         <@vcolumn title="${action.getText('daily.leaderIdea')}" property="leaderIdea" >
	            <@vlh.attribute name="width" value="10%" />
	            <@alignLeft/>
	         </@vcolumn>
	         -->
        </@list>
        <@buttonBar>
		  <@vbutton class="button" value="${action.getText('new')}" onclick="newDaily()"/>
		  	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('daily')}?" />	 
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	               <@ww.param name="'onclick'" value="'return confirmDeletes(\"dailyIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
		</@buttonBar>
</@ww.form>
<script language="javascript">
  //打开编辑日报模态窗口
  function editDaily(v){
      var url='${req.contextPath}/workReport/editDaily.html?daily.id='+v+'&weekly.id='+${req.getParameter('weekly.id')?if_exists};
      popupModalDialog(url,900,600);
      if(isIE()){self.location.reload();};
  }
  //打开新建日报模态窗口
  function newDaily(){
  	  var url = '${req.contextPath}/workReport/editDaily.html?weekly.id='+${req.getParameter('weekly.id')?if_exists};
	  popupModalDialog(url,900,600);
	  if(isIE()){self.location.reload();};
  }
</script>
</@framePage>