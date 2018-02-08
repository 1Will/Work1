<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('intactnessBillSearch.title')}">
  <@ww.form namespace="'/runmaintenance/intactness'" name="'intactnessList'" action="'searchIntactnesses'" method="'post'">
    <@ww.token name="searchIntactnessesToken"/>
  	<#include "intactnessSearcher.ftl"/>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <@buttonBar>        
	  <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>  
	  
       <#if !(action.isReadOnly())>
        <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/intactness/editIntactness.html?readOnly=${req.getParameter('readOnly')?if_exists}"/> 
     </#if>
    </@buttonBar>   
	 <@list title="${action.getText('intactnessBill.list')}" 
	    includeParameters="code|name|readOnly|department.id|examiner|examDate_start|examDate_end" 
		fieldMap="like:code|examiner|name,date:examDate_start|examDate_end" >
		<@vlh.checkbox property="id" name="intactnessIds">
	    	<@vlh.attribute name="width" value="30" />
	    </@vlh.checkbox>
	    <#if object.disabled>
	    <@vcolumn title="${action.getText('intactness.billNo')}" property="code" sortable="desc">
           ${object.code}
	        <@alignLeft/>
	    </@vcolumn>
	    <#else>
	     <@vcolumn title="${action.getText('intactness.billNo')}" property="code" sortable="desc">
	        <a href="editIntactness.html?intactnessBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
	        <@alignLeft/>
	    </@vcolumn>
	    </#if>
	    
	    <@vcolumn title="${action.getText('intactness.name')}" property="name" sortable="desc">
	    	<@alignLeft/>
	    </@vcolumn>
	    <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
	     	<@alignLeft/>
	    </@vcolumn>
	   	<@vcolumn title="${action.getText('intactness.examiner')}" property="examiner" sortable="desc">
	     	<@alignLeft/>
	    </@vcolumn>
	    <@vcolumn title="${action.getText('intactness.intactnessTime')}" property="examDate" format="yyyy-MM-dd" sortable="desc">
	     	<@alignCenter/>
	    </@vcolumn>
	    <@vcolumn title="${action.getText('comment')}" property="comment">
	     	<@alignLeft/>
	    </@vcolumn>
	</@list> 
    <#if !first>
	    <@buttonBar>
      <#if !(action.isReadOnly())>
			<@eam2008_disabledOrEnabled_button message="${action.getText('intactnessBill')}" boxName="intactnessIds" jsFunctionName="checkInvalidParms()"/>	   
	  </#if>  
	    </@buttonBar> 
    </#if>
  </@ww.form>
</@htmlPage>