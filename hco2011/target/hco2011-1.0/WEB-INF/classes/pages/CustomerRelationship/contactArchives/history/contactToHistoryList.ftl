<#include "../../../includes/hco2011.ftl" />

<@framePage> 
<@ww.form name="'listForm'" action="'searchContactToHistory'" method="'post'">
<@ww.token name="searchContactToHistoryToken"/>
	  <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
      <#if personnelFileId?exists>
      	<@ww.hidden name="'cr.id'" value="#{contactId?if_exists}"/>
      </#if>
      <#assign itemNo=1/>
      <@list title="" includeParameters="cr.id" fieldMap="like:" >
	        <@vcolumn title="${action.getText('work.no')}" >
            	#{itemNo}
            	<@alignCenter/>
         	</@vcolumn>
         	<#assign itemNo=itemNo + 1/>
         <@vcolumn title="${action.getText('lastOperator')}" property="lastOperator" >
            	<@alignLeft/>
         </@vcolumn>
          <@vcolumn title="${action.getText('lastModifiedTime')}"  format="yyyy-MM-dd HH:mm:ss" property="lastModifiedTime" >
            	<@alignCenter/>
         </@vcolumn>
         <@vcolumn title="${action.getText('conment')}" property="conment" >
         	<@alignLeft/>
         </@vcolumn>
        </@list>
	 </@ww.form>
</@framePage>