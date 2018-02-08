
<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('workwarndetail.search')}">
	<@ww.form name="'listForm'" namespace="'/workspace/warnning/myWarnning'" action="'searchWorkWarnningDetail'" method="'post'">
    <#-- 
		<@ww.token name="searchworkWarnningDetailToken"/>
		<@ww.hidden name="'workWarnningId'" value="${req.getParameter('workWarnningId')?if_exists}"/>
		<@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
                <@ww.textfield label="'${action.getText('remaindays')}'" name="'remaindays'" value="'${req.getParameter('remaindays')?if_exists}'" cssClass="'underline'"/>
            </tr>
        </@inputTable>
        <@buttonBar>
			<@vsubmit name="'search'" value="'${action.getText('search')}'" />
			<@vbutton name="'close'" class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
        </@buttonBar>
        <@list title="${action.getText('workwarndetail.list')}" includeParameters="workWarnningId|name|remaindays|onlyInvalid|onlyValid" fieldMap="like:name|remaindays" >
    	<@vcolumn title="${action.getText('name')}" property="name" sortable="asc" >
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('warnDate')}" property="warnDate" format="yyyy-MM-dd" sortable="asc">
            <@alignCenter/>
        </@vcolumn>
        <@vcolumn title="${action.getText('remaindays')}" property="remaindays" sortable="asc">
            <#if (object.remaindays > 0)>
            	<#if (type = "回访提醒")>
                <font color="red"> #{object.remaindays} 小时</font>
                <#else>
                <font color="red"> #{object.remaindays} 天</font>
                </#if>
            <#else>
               <font color="gray">已过期</font>
            </#if>
            <@alignRight/>
        </@vcolumn>
    </@list>
    -->
    
        <@inputTable>
            <#if workWarnning.id?exists>
                <@ww.hidden name="'workWarnning.id'" value="#{workWarnning.id}"/>
            </#if>
		  <tr>

		    <td align="right" valign="top">
		       <label class="label"></label>
		    </td>
		    <td align="center" style="FONT－FAMILY: 宋体;font-size:20pt;font-weight:900">
		      ${workWarnning.type?if_exists}
		    </td>
		  </tr>
		  <tr>
		  </tr>
		  <tr>
		  </tr>
		  <tr>
	       	<td align="right" valign="top">
		       <label class="label"></label>
		    </td>
		    <td align="left" style="FONT－FAMILY: 宋体;font-size:10pt;font-weight:200">
	       		<pre>${workWarnning.content?if_exists}</pre>
	        </td>
		  </tr>
        </@inputTable>
        <@ww.hidden name="'origFileName'" value=""/>
        <@buttonBar>
        	<@vbutton name="close" value="${action.getText('关闭')}" class="button" onclick="closeThis();"/>
        </@buttonBar>
        
        
    </@ww.form>
</@htmlPage>