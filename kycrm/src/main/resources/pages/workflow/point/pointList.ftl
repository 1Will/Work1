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

<#-- $Id: pointList.ftl 2010-04-06 wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" action="'searchPoint'" method="'post'">
		<@ww.token name="searchPointToken"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'flow.id'" value="'${req.getParameter('flow.id')?if_exists}'"/>
		<@ww.hidden name="'flow'" value="'${req.getParameter('flow')?if_exists}'"/>
		<#assign itemNo=1/>
        <@list title="${action.getText('节点列表')}" excel=false setupTable=false
        	   includeParameters="flow.id" 
        	   fieldMap="" >
        	   	<#if !(action.isReadOnly())>
            <@vlh.checkbox property="id" name="pointIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            </#if>
            
			<@vcolumn title="${action.getText('序号')}" property="myNum">
			<a href="###" onclick="openPage('editPoint.html?point.id=#{object.id}&flow.id=${req.getParameter('flow.id')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}')">
			#{object.myNum}</a>
				<@alignCenter />
			</@vcolumn>
			
			<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('节点编码')}" property="code" >
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('节点名称')}" property="name" >
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('负责人')}" property="personnelFiles.name" >
            	<@alignLeft/>
            </@vcolumn>
            <#--
             <@vcolumn title="${action.getText('上一步骤')}" property="previousPoint" >
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('下一步骤')}" property="rearPoint" >
            	<@alignLeft/>
            </@vcolumn>
            -->
            <#-- 定义是否启动变量 -->
            <#if object.openOrNot == 0>
				<#assign openOrNot="${action.getText('是')}"/>
			<#else>
				<#assign openOrNot="${action.getText('否')}"/>
			</#if>
			
            <@vcolumn title="${action.getText('是否启用')}" property="openOrNot" >
	            ${openOrNot}
	            <@alignLeft/>
            </@vcolumn>
            
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
		    <@vbutton class="button" value="${action.getText('new')}" onclick="newPoint()"/>
		  	<#--<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('流程节点明细')}?" />	 
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	               <@ww.param name="'onclick'" value="'return confirmDeletes(\"pointIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>-->
	            <#--
	            <@crm_disabledOrEnabled_Nodelete_button message="${action.getText('流程节点明细')}" boxName="pointIds" jsFunctionName="checkInvalidParms()"/>-->
		</@buttonBar>	
		</#if>
		</#if>
    </@ww.form>
<script language="javascript">

//window.onload=function(){
//}

//失去焦点隐藏导航层
function onBlur(op){
	getObjByName(op).style.display="none";
}
function openPage(url)
{
    //popupModalDialog(url, 800, 600);
    openNewWindow(url);
    if(isIE()){self.location.reload();};
}

function newPoint()
{
    var url = '${req.contextPath}/point/editPoint.html?flow.id='+${req.getParameter('flow.id')?if_exists};
    openNewWindow(url);
    //popupModalDialog(url, 800, 600);
    if(isIE()){self.location.reload();};
}
</script>
</@framePage>