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
<#--$Id: menuFrame.ftl 7925 2007-10-22 02:37:55Z qsun $ -->

<#--<#assign menu=JspTaglibs["/WEB-INF/tld/struts-menu.tld"] />-->
<#include "../includes/macros.ftl" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${req.contextPath}/stylesheets/dtree.css" />
        <script type="text/javascript" src="${req.contextPath}/javascripts/dtree.js">
        </script>
        <STYLE type=text/css>
        BODY {
	        FONT-SIZE: 9pt; COLOR: #a0c0e0; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif, 宋体;BACKGROUND-COLOR: #FFFFFF
           }
        </STYLE>
	</head>
	<body>
	  <script type="text/javascript">
	  var d = new dTree('d','${req.contextPath}/javascripts','userForm');
	     <#--var d = new dTree('d');-->
	    d.add(0,-1,"选择用户组",'','','listGroupsFrame.html','','mainFrame','');
	    <#assign step=0/>
	    <#list groupsByGrouping as groupByGrouping>
	     <#if step==0>
	        <#list groupByGrouping as group>
	          <#--<#if (group.childGroups.size()>0)>-->
	            d.add(#{group.id},0,"${group.name?if_exists}&nbsp;&nbsp;<b>[</b>${group.code?if_exists}<b>]</b>",'','','listGroupsFrame.html?groupFrame.id=#{group.id}','','mainFrame','${req.contextPath}/javascripts/img/nolines_plus.gif');
	         <#-- <#else>
	            d.add(#{group.id},0,"${group.name?if_exists}&nbsp;&nbsp;<b>[</b>${group.code?if_exists}<b>]</b>",'','','listUserFrame.html?groupFrame.id=#{group.id}','','mainFrame','${req.contextPath}/javascripts/img/nolines_plus.gif');
	          </#if>-->
	        </#list>
	      <#else>
	        <#list groupByGrouping as group>
	          <#if (group.childGroups.size()>0)>
	            d.add(#{group.id},#{group.parentGroup.id},"${group.name?if_exists}&nbsp;&nbsp;<b>[</b>${group.code?if_exists}<b>]</b>",'','','listGroupsFrame.html?groupFrame.id=#{group.id}','','mainFrame','${req.contextPath}/javascripts/img/nolines_plus.gif');
	          <#else>
	            d.add(#{group.id},#{group.parentGroup.id},"${group.name?if_exists}&nbsp;&nbsp;<b>[</b>${group.code?if_exists}<b>]</b>",'','','listGroupsFrame.html?groupFrame.id=#{group.id}','','mainFrame','${req.contextPath}/javascripts/img/nolines_plus.gif');
	          </#if>
	        </#list>
	      </#if>
	     <#assign step=step+1/>
	    </#list>
	    document.write(d);
	  </script>
	</body>
</html>


<#--
<@htmlPage title="${action.getText('组列表')}" helpButton=false>
    <@ww.form  name="'listForm'" action="'sercherGroup'" method="'post'">
        <@ww.token name="sercherGroupToken"/>
         <#include "groupSearcher.ftl" />
        <@buttonBar>
            <@htmlSubmit value="${action.getText('search')}" onclick="return checkInvalidParms();"/>
        </@buttonBar>
        <@list title="${action.getText('group.list')}" includeParameters="name" 
        fieldMap="like:name" excel=false setupTable=false showSummary=false>
             <@vcolumn title="${action.getText('组名')}" property="name" sortable="desc">
             <a href="listUserFrame.html?groupFrame.id=#{object.id}" target="mainFrame">${object.name}</a>
            </@vcolumn>
        </@list>
       <#if !first>
        </#if>
    </@ww.form>
    
 </@htmlPage>
 -->
<#--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

     <td style="VERTICAL-ALIGN:top;text-align:left">
      <table style="BORDER-COLLAPSE:collapse" borderColor=#111111 width="97%" border="0" cellspacing="0" cellpadding="0" bgColor=#f5efe7>
        <#list groupFrames as groupFrame>
                 <tr align=middle bgColor="" height="35">
                   <a href="listUserFrame.html?groupFrame.id=#{groupFrame.id}" target="mainFrame"><td>${groupFrame.name}</td></a>
                </tr>
          </#list>
            <#--<@ww.select label="'${action.getText('组名')}'" required="false" name="'groups.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="groups" emptyOption="false" disabled="false">
		</@ww.select>-->
      <#-- </tr>
       </table>
     </td>
</html>>-->
