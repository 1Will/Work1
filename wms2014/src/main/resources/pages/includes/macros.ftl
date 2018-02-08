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
<#--$Id: macros.ftl 10470 2008-01-18 09:44:44Z qsun $ -->

<#include "./taglib.ftl" />

<#macro spacer width=1 height=1><img src="${req.contextPath}/images/spacer.gif" width="#{width}" align="absbottom" height="#{height}" border="0"></#macro>

<#macro htmlPage title="${action.getText('title')}" helpButton=true>
    <html>
        <head>
        	<meta http-equiv="Pragma" content="no-cache">
        	<meta http-equiv="Expires" content="-1">
        	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
            <title>${action.getText('name.application')}-${title}</title>
            <base target="_self"></base>
            <link rel="stylesheet" href="${req.contextPath}/stylesheets/valuelist.css" type="text/css"/>
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/default.css" type="text/css"/>
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/tabbedpane.css" type="text/css"/>
	        <link rel="stylesheet" type="text/css" href="${req.contextPath}/stylesheets/dtree.css" />
	        
	        <script type='text/javascript' src='${req.contextPath}/dwr/engine.js'></script>  
			<script type='text/javascript' src='${req.contextPath}/dwr/util.js'></script>
			
	        <script language="javascript" src="${req.contextPath}/javascripts/global.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/jsrsClient.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/modalDialog.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/eam2008.js" charset="utf-8"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/valueList.js"></script>
        	<script language="javascript" src="${req.contextPath}/javascripts/prototype-1.6.0.2.js"></script>
        	<script language="javascript" src="${req.contextPath}/javascripts/dtree.js"></script>
        	
        	<script type='text/javascript' src='${req.contextPath}/dwr/interface/CreateGraphNoJs.js'></script>
        	<script type='text/javascript' src='${req.contextPath}/dwr/interface/FilialeSelectDeptJs.js'></script>
        	<script type='text/javascript' src='${req.contextPath}/dwr/interface/WareCascadeRegionalJs.js'></script>
        	<script type='text/javascript' src='${req.contextPath}/dwr/interface/SpareInBillJs.js'></script>
        	<script type='text/javascript' src='${req.contextPath}/dwr/interface/findWareHouseJs.js'></script>
        	
			
        </head>
        <body>
            <@messageBar helpButton=helpButton title="${title}"/>
            <#nested>
        </body>
    </html>
</#macro>

<#macro messageBar helpButton=true title="Title">
    <table border="0" cellpadding="0" width="100%">
      <#assign hasErrors = actionErrors?exists && actionErrors.size() gt 0 />
      <#assign hasMessages = actionMessages?exists && actionMessages.size() gt 0 />
      <#assign messageNumber = 0 />
        <tr>
            	<td align="left" rowspan="2" valign="bottom" nowrap><img src="${req.contextPath}/images/title/title_form.jpg"  />
                	<font size="2"><b>&nbsp;&nbsp;${title}</b></font>
                </td>
            <td align="center">
                <#if hasErrors>
                    <#assign messageNumber = messageNumber + actionErrors.size()/>
                    <font id="errorMessage" color="red">${actionErrors.get(0)}</font>
                </#if>
                <#if hasMessages>
                    <#assign messageNumber = messageNumber + actionMessages.size()/>
                    <#if !hasErrors>
                        <font id="errorMessage" color="blue">${actionMessages.get(0)}</font>
                    </#if>
                </#if>
                <#if messageNumber gt 1>
                    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 onClick="toggleVisibility('errorMessages');" />
                 </#if>
            </td>
            <td align="right" valign="top">
                <#if helpButton>
                    <a href="javascript: print()"><img border="0" align="absbottom" src="${req.contextPath}/images/icon/print.gif" title="${action.getText('print')}" width="13" height="13"/></a>
                    <a><img border="0" align="absbottom" src="${req.contextPath}/images/icon/help.gif" title="${action.getText('help')}" width="13" height="13"/></a>
                </#if>
            </td>
        </tr>
                <tr >
                    <#if messageNumber gt 1>
                    <td id="errorMessages" style="position:absolute; visibility: hidden;" align="center">
                         <#if hasErrors>
                             <ul>
                                 <font color="red">
                                 <#list actionErrors as error>
                                     <li>${error}</li>
                                 </#list>
                                 </font>
                             </ul>
                         </#if>
                         <#if hasMessages>
                             <ul>
                                 <font color="blue">
                                 <#list actionMessages as message>
                                     <li>${message}</li>
                                 </#list>
                                 </font>
                             </ul>
                         </#if>
                     </td>
                     <#else>
                     <td></td>
                     </#if>
                     <td>&nbsp;</td>
                 </tr>
    </table>
    <hr color="#2E3192" height="2"/>
</#macro>


<#--
说明：日期域datePickerRanger
属性：anothername ----- js验证别名; label ---- 标签名称; name ---- 输入域名称; value ---- 输入域值;
	 required ----- 是否为必填项。true-必填项 | false-非必填项；readonly ---- 只读;
	 disabled ----- 是否可用; type ---- 输入域类型; dateFormat ---- 格式化类型;
	 flag ---- 判断当前控件时属于两个输入域，还是一个输入域，默认值为true表示属于一个输入域，false表示两个输入域
-->
<#--date-->
<#macro datePickerRanger anothername="" label="" name="" value="" valueStart="" valueEnd="" 
						 cssClass="underline" dateFormat="%Y-%m-%d" required="false" maxlength="10" 
						 disabled="false" flag="true">
	<#if "${flag}"=='false'>
		<#--拥有两个输入域的日期控件-->
		<@pp.dateRanger label="'${label}'" name="'${name}'" 
	            value="'${req.getParameter('${valueStart}')?if_exists}|${req.getParameter('${valueEnd}')?if_exists}'" cssClass="'${cssClass}'"
	             dateFormat="'${dateFormat}'"  required="${required}" maxlength="${maxlength}" disabled="${disabled}"/>
	<#elseif ("${flag}"=='true')>
		<#--拥有一个输入域的日期控件-->
		<@pp.datePicker label="'${label}'" name="'${name}'" 
		   			value="'${value}'" cssClass="'${cssClass}'" 
					dateFormat="'${dateFormat}'" required="${required}" maxlength="${maxlength}" disabled="${disabled}"/>
    </#if>
	<script language="javascript">
		function dateCheck_${anothername}(){
			<#if "${flag}"=='false'>
				return dateCheckRanger('${name}','${label}','${valueStart}','${valueEnd}','${dateFormat}');
			<#elseif ("${flag}"=='true')>
				return dateCheckPicker(${required},'${name}','${label}','${dateFormat}');
			</#if>
		}
	</script>
</#macro>


<#macro inputTable>
    <@table class="inputTable">
        <#nested>
    </@table>
</#macro>
<#macro listTable>
    <@table class="defaultLook">
        <#nested>
    </@table>
</#macro>
<#macro table class>
    <tr class="input">
        <td>
            <table class="${class}" name="listTable">
                <#nested>
            </table>
        </td>
    </tr>
</#macro>

<#macro buttonBar>
    <tr class="input">
        <td>
            <#nested>
        </td>
    </tr>
</#macro>

<#macro titleBar title="Title">
    <tr class="input">
        <td class="title">
            ${title}
        </td>
    </tr>
</#macro>

<#macro oneLine colspan=4>
    <tr>
       <td colspan="#{colspan}">
           <table class="wwFieldTable">
               <tr>
                   <#nested>
               </tr>
           </table>
       </td>
    </tr>
</#macro>

<#macro oneColumn rowspan=2>
   <td rowspan="#{rowspan}" colspan="2">
       <table class="wwFieldTable">
           <tr>
               <#nested>
           </tr>
       </table>
   </td>
</#macro>
<#macro vcolumn title="" property="" format="" sortable="" groupKey="" adapterProperty="" emphasisPattern="" titleKey="" sum="" attributes="" default="">
<#--下面添加的style主要完成备注类型的换行工作-->
<@vlh.attribute name="style" value="word-wrap:break-word; word-break:break-all;"/>
<#if (titleKey?length>0)>
	<#assign titleKeyValue="${titleKey}"/>
<#else>
	<#assign titleKeyValue="${title}"/>
</#if>
    <#if (action.isVisible(title))>
		<@vlh.column title="${title?if_exists}" property="${property?if_exists}" format="${format?if_exists}" sortable="${sortable?if_exists}" emphasisPattern="${emphasisPattern?if_exists}" titleKey="${titleKeyValue?if_exists}" sum="${sum?if_exists}" attributes="${attributes?if_exists}" default="${default?if_exists}">
			<#nested>
		</@vlh.column>
    </#if>
</#macro>

<#macro vsubmit id="" onclick="" cssClass="" cssStyle="" label="" labelposition="" theme="" template="" name="" value="" align="" >
<#if (action.hasPermission(name))>
	<#if (onclick?length>0)>
		<@ww.submit name="${name}" value="${value}" onclick="${onclick}" cssStyle="cursor:pointer">
			<#nested>
		</@ww.submit>
	<#else>
		<@ww.submit name="${name}" value="${value}" cssStyle="cursor:pointer">
			<#nested>
		</@ww.submit>
	</#if>
</#if>
</#macro>

<#macro vbutton type="button" class=""  name="" value="" onclick="">
<#if (action.hasPermission(name))>
	<input type="${type}" class="${class}"  name="${name}" value="${value}" onclick="${onclick}" style="cursor:pointer" />
</#if>
</#macro>
<#macro vdisabledbutton type="button" class="" disabled="" name="" value="" onclick="">
<#if (action.hasPermission(name))>
	<input type="${type}" class="${class}" disabled="${disabled}" name="${name}" value="${value}" onclick="${onclick}" style="cursor:pointer" />
</#if>
</#macro>

<#macro list title value="valueList" url="?" configName="defaultLook" includeParameters="" fieldMap="" excel=true setupTable=true>
    <@ww.hidden name="'fieldMap'" value="'${fieldMap}'"/>
    <@ww.hidden name="'first'" value="false"/>
    <#assign valueListNoRecords = true/>
    <#if !first>
        <@titleBar title="${title}"/>
        <#if includeParameters?has_content>
            <#assign includes="first|fieldMap|${includeParameters}"/>
        <#else>
            <#assign includes="*"/>
        </#if>
        <@vlh.root value="${value}" url="${url}" configName="${configName}" includeParameters="${includes}" >
        <#if .vars[value]?exists && .vars[value].valueListInfo.totalNumberOfEntries gt 0>
            <#assign valueListNoRecords = false/>
        </#if>
        <tr class="input">
            <td>
                <#if !valueListNoRecords>
                <table id="vltable" name="${value}" class="defaultLook" width="100%">
                    <@vlh.row bean="object">
                    <@vlh.attribute name="onmouseover" value=""></@vlh.attribute>
                    <@vlh.attribute name="onmouseout" value=""></@vlh.attribute>
        			<@vlh.attribute name="id" value="object-#{object.id}"></@vlh.attribute>
        			<@vlh.attribute name="align" value="center" />
                        <#nested>
                    </@vlh.row>
                </table>
                </#if>
            </td>
        </tr>
        <tr class="input">
            <td>
                <table width="100%">
                    <tr>
                        <td align="left">
                            <@vlh.paging pages=10 showSummary=true>${page}&nbsp;</@vlh.paging>
                        </td>
                        <td align="right">
                            <#if !valueListNoRecords>
                            	<#if setupTable>
                            	<a href="javascript:popupModalDialog('${req.contextPath}/popup/setupTable.html?page=${req.servletPath}&tableName=${value}')">
                            		<img title="${action.getText('set.table.param')}" src="${req.contextPath}/images/icon/edit.gif" border="0"/>
                            	</a>
                                </#if>
                                <#if excel>
                                    <@vlh.filter url="${req.requestURI}/exportTable.html?pageUrl=${req.requestURI}&format=EXCEL&tableName=${value}&pagingPage=${pagingPage?if_exists}&exportAll=${action.isExportAll()?string}&">
                                      <img title="${action.getText('export.csv')}" src="${req.contextPath}/images/icon/excel.png" border="0"/>
                                    </@vlh.filter>
                                </#if>
                            </#if>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
      </@vlh.root>
    </#if>
</#macro>

<#--
说明：输入域text2
属性：anothername ----- js验证别名; label ---- 标签名称; name ---- 输入域名称; value ---- 输入域值;
	 required ----- 是否为必填项。true-必填项 | false-非必填项；readonly ---- 只读;
	 disabled ----- 是否可用; type ---- 输入域类型
-->
<#-- text2 -->
<#macro text2 align="right" valign="top" labelClass="label" label="" colspan="3" name="" value="" class="underline" 
		maxLength="70" size="70" type="S" required="false" anothername="" disabled="">
		<td align="${align}" valign="${valign}">
		<#if required == "true">
			<font color="red">*</font>
		</#if>
       		<label class="${labelClass}">${label}:</label>
     	</td>
		<td colspan="${colspan}">
		<#if disabled=="true">
			<input type="text" name="${name}" disabled="disabled" value="${value}" class="${class}" maxlength="${maxLength}" size="${size}" />	
		<#else>
		    <input type="text" name="${name}" value="${value}" class="${class}" maxlength="${maxLength}" size="${size}" />	
		</#if>
		</td>
	
	<script language="javascript">
		function textfieldCheck_${anothername}(){
			return textfieldCheck(${required},'${name}','${type}','${label}');
		}
	</script>
</#macro>

<#macro redirectButton value url name="" class="button">
    <input type="button" name="${name}" value="${value}" class="${class}" onclick="location='${url}';" style="cursor:pointer"/>
</#macro>

<#macro hrefButton value url name="" class="button">
    <input type="button" name="${name}" value="${value}" class="${class}" onclick="openPopupWindow('${url}');" style="cursor:pointer"/>
</#macro>

<#macro alignLeft>
	<@vlh.attribute name="style" value="text-align:left"/>
</#macro>

<#macro alignCenter>
	<@vlh.attribute name="style" value="text-align:center"/>
</#macro>

<#macro alignRight>
	<@vlh.attribute name="style" value="text-align:right"/>
</#macro>

<#macro htmlButton name="" value="" style="" class="btn1_mouseout" onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'" onclick="">
<#if (action.hasPermission(name))>
	<input type="button" name="${name}"  style="${style}" value="${value}" class="${class}" onmouseover="${onmouseover}" onmouseout="${onmouseout}" onclick="${onclick}"/>
</#if>
</#macro>

<#macro blurbutton type="button" class=""  name="" value="" onclick="" onblur="">
<#if (action.hasPermission(name))>
	<input type="${type}" class="${class}"  name="${name}" value="${value}" onclick="${onclick}" onblur="${onblur}" style="cursor:pointer" />
</#if>
</#macro>
<#macro imageButton type="image" class="" name="" value="" src="">
<#if (action.hasPermission(name))>
	<input type="${type}" class="${class}" name="${name}" value="${value}" src="${src}" align="right" />
</#if>
</#macro>