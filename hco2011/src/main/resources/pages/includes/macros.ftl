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
<#macro framePage title="${action.getText('title')}">
    <html>
        <head>
        	<meta http-equiv="Pragma" content="no-cache">
        	<meta http-equiv="Expires" content="-1">
            <title>${action.getText('name.application')}-${title}</title>
            <link rel="stylesheet" href="${req.contextPath}/stylesheets/valuelist.css" type="text/css"/>
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/default.css" type="text/css"/>
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/button.css" type="text/css"/>
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/ajax.css" type="text/css"/>
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/css.css" type="text/css"/>
	        <link rel="stylesheet" type="text/css" href="${req.contextPath}/stylesheets/dtree.css" />
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/tabbedpane.css" type="text/css"/>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/global.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/jsrsClient.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/modalDialog.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/valueList.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/prototype-1.6.0.2.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/jquery-1.4.2.min.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/dtree.js"></script>
        </head>
        <body>
            <#nested>
        </body>
    </html>
</#macro>
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
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/button.css" type="text/css"/>
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/ajax.css" type="text/css"/>
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/css.css" type="text/css"/>
	        <link rel="stylesheet" type="text/css" href="${req.contextPath}/stylesheets/dtree.css" />
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/tabbedpane.css" type="text/css"/>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/lang/global.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/global.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/jsrsClient.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/modalDialog.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/hco2011.js"></script>
	        <script type="text/javascript" src="${req.contextPath}/javascripts/valueList.js"></script>
        	<script type="text/javascript" src="${req.contextPath}/javascripts/prototype-1.6.0.2.js"></script>
        	<script type="text/javascript" src="${req.contextPath}/javascripts/jquery-1.4.2.min.js"></script>
        	<script type="text/javascript" src="${req.contextPath}/javascripts/dtree.js"></script>
        	<script type="text/javascript" src="${req.contextPath}/javascripts/check.js"></script>
        	
        	<script type='text/javascript' src='${req.contextPath}/dwr/interface/InstitutionSelectDeptJs.js'></script>
        	<script type='text/javascript' src='${req.contextPath}/dwr/interface/AreaSelectJs.js'></script>
        	<script type='text/javascript' src='${req.contextPath}/dwr/interface/CustomerList.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/engine.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/util.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/DeptCascadeDutyJs.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/ContractManagementAndBatchJs.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/ContractAndBatchJs.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/CheckSumJs.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/loginNameRepeatJs.js'></script>
       
        </head>
        <body>
            <@messageBar helpButton=helpButton title="${title}"/>
            <#nested>
        </body>
    </html>
</#macro>
<!-- 用于FrameSet的子模板页面 -->
<#macro fsPage title="${action.getText('title')}" >
<html>
	<head>
		<meta http-equiv="Pragma" content="no-cache">
    	<meta http-equiv="Expires" content="-1">
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>${action.getText('name.application')}-${title}</title>
        <base target="_self"></base>
        <link rel="stylesheet" href="${req.contextPath}/stylesheets/valuelist.css" type="text/css"/>
        <link rel="stylesheet" href="${req.contextPath}/stylesheets/default.css" type="text/css"/>
        <link rel="stylesheet" href="${req.contextPath}/stylesheets/button.css" type="text/css"/>
        <link rel="stylesheet" href="${req.contextPath}/stylesheets/ajax.css" type="text/css"/>
        <link rel="stylesheet" href="${req.contextPath}/stylesheets/css.css" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="${req.contextPath}/stylesheets/dtree.css" />
        <link rel="stylesheet" href="${req.contextPath}/stylesheets/tabbedpane.css" type="text/css"/>
        <script language="javascript" src="${req.contextPath}/javascripts/lang/global.js"></script>
        <script language="javascript" src="${req.contextPath}/javascripts/global.js"></script>
        <script language="javascript" src="${req.contextPath}/javascripts/jsrsClient.js"></script>
        <script language="javascript" src="${req.contextPath}/javascripts/modalDialog.js"></script>
        <script language="javascript" src="${req.contextPath}/javascripts/hco2011.js"></script>
        <script language="javascript" src="${req.contextPath}/javascripts/valueList.js"></script>
    	<script language="javascript" src="${req.contextPath}/javascripts/prototype-1.6.0.2.js"></script>
    	<script language="javascript" src="${req.contextPath}/javascripts/jquery-1.4.2.min.js"></script>
    	<script language="javascript" src="${req.contextPath}/javascripts/dtree.js"></script>
    	
    	<script type='text/javascript' src='${req.contextPath}/dwr/interface/InstitutionSelectDeptJs.js'></script>
    	<script type='text/javascript' src='${req.contextPath}/dwr/interface/AreaSelectJs.js'></script>
		<script type='text/javascript' src='${req.contextPath}/dwr/engine.js'></script>  
		<script type='text/javascript' src='${req.contextPath}/dwr/util.js'></script>
    </head>
    <body>
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
        <#nested>
    </body>
</html>
</#macro>
<!-- 自定义结束 -->

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
<#if (titleKey?length>0)>
	<#assign titleKeyValue="${titleKey}"/>
<#else>
	<#assign titleKeyValue="${title}"/>
</#if>
    <#if (action.isVisible(title))>
		<@vlh.column title="${title?if_exists}" property="${property?if_exists}" format="${format?if_exists}" sortable="${sortable?if_exists}" emphasisPattern="${emphasisPattern?if_exists}" titleKey="${titleKeyValue?if_exists}" sum="${sum?if_exists}" attributes="style{${attributes?if_exists};}" default="${default?if_exists}">
			<#--<@vlh.attribute name="bgcolor" value="#ffffff"></@vlh.attribute>-->
			<#nested>
		</@vlh.column>
    </#if>
</#macro>

<#macro vsubmit id="" onclick="" cssClass=""  cssStyle="" label="" labelposition="" theme="" template="" name="" value="" align="" disabled="false">
<#if (action.hasPermission(name))>
	<#if (onclick?length>0)>
		<@ww.submit name="${name}" value="${value}"  onclick="${onclick}" disabled="${disabled}">
			<#nested>
		</@ww.submit>
	<#else>
		<@ww.submit name="${name}" value="${value}" disabled="${disabled}">
			<#nested>
		</@ww.submit>
	</#if>
</#if>
</#macro>

<#macro htmlSubmit name="" size="" value="" style="" class="btn1_mouseout" onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'" onclick="" disabled="">
<#if (action.hasPermission(name))>
	<#if (onclick?length>0)>
	  <#if disabled=="true">
		<input type="submit" name="${name}"  size="${size}" style="${style}" value="${value}" class="${class}" onmouseover="${onmouseover}" onmouseout="${onmouseout}" onclick="${onclick}" disabled/>
	  <#else>
	  	<input type="submit" name="${name}" size="${size}" style="${style}" value="${value}" class="${class}" onmouseover="${onmouseover}" onmouseout="${onmouseout}" onclick="${onclick}"/>
	  </#if>
	<#else>
	  <#if disabled=="true">
		<input type="submit" name="${name}"  size="${size}" style="${style}" value="${value}" class="${class}" onmouseover="${onmouseover}" onmouseout="${onmouseout}" disabled/>
	  <#else>
	  	<input type="submit" name="${name}"  size="${size}" style="${style}" value="${value}" class="${class}" onmouseover="${onmouseover}" onmouseout="${onmouseout}"/>
	  </#if>
	  
	</#if>
</#if>
</#macro>

<#macro htmlButton name="" value="" style=""  class="" onmouseover="" onmouseout="" onclick="" disable="">
	<#assign disableTiger = ''/>
	<#if "${disable}"='false'>
	<#assign disableTiger = ''/>	
	<#elseif "${disable}"='true'>
	<#assign disableTiger = 'disabled'/>
	</#if>
<#if (action.hasPermission(name))>
	<input type="button" name="${name}"  style="${style}"  value="${value}" class="${class}" onmouseover="${onmouseover}" onmouseout="${onmouseout}" onclick="${onclick}" ${disableTiger?if_exists}/>
</#if>
</#macro>

<#macro htmlRedirectButton value url name="" style="" class="btn1_mouseout" onmouseover="" onmouseout="">
    <input type="button" name="${name}" value="${value}" class="${class}" onclick="location='${url}';" style="${style}" onmouseover="${onmouseover}" onmouseout="${onmouseout}"/>
</#macro>

<#macro vbutton type="button" class="" name="" value="" onclick="" disable="">
	<#assign disableTiger = ''/>
	<#if "${disable}"='false'>
	<#assign disableTiger = ''/>	
	<#elseif "${disable}"='true'>
	<#assign disableTiger = 'disabled'/>
	</#if>
<#if (action.hasPermission(name))>
	<input type="${type}" class="${class}" name="${name}" value="${value}" onclick="${onclick}" ${disableTiger?if_exists}/>
</#if>
</#macro>

<#macro imagbutton type="image"  src="" class="" name="" value="" onclick="">
<#if (action.hasPermission(name))>
	<input type="${type}" class="${class}" src="${src}" name="${name}" value="${value}" onclick="${onclick}" />
</#if>
</#macro>

<#macro list title value="valueList" url="?" configName="defaultLook" includeParameters="" fieldMap="" excel=true setupTable=true showSummary=true>
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
                    <@vlh.attribute name="onmouseover" value="javascript:toggle1(this);"></@vlh.attribute>
                    <@vlh.attribute name="onmouseout" value="javascript:mouseout1(this);"></@vlh.attribute>
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
                        <#if showSummary>
                         <@vlh.paging pages=10 showSummary=true >${page}&nbsp;</@vlh.paging>
                        <#else>
                         <@vlh.paging pages=10 showSummary=false>${page}&nbsp;</@vlh.paging>
                        </#if>
                        </td>
                        <td align="right">
                            <#if !valueListNoRecords>
                            	<#if setupTable>
                            	<a href="javascript:popupModalDialog('${req.contextPath}/popup/setupTable.html?page=${req.servletPath}&tableName=${value}')">
                            		<img title="${action.getText('set.table.param')}" src="${req.contextPath}/images/icon/edit.gif" border="0"/>
                            	</a>
                                </#if>
                                <#if excel>
                                    <@vlh.filter url="${req.requestURI}/exportTableExcel.html?pageUrl=${req.requestURI}&format=EXCEL&tableName=${value}&pagingPage=${pagingPage?if_exists}&exportAll=${action.isExportAll()?string}&">
                                      <img title="${action.getText('export.excel')}" src="${req.contextPath}/images/icon/excel.png" border="0"/>
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
说明：输入域textfield
属性：anothername ----- js验证别名; label ---- 标签名称; name ---- 输入域名称; value ---- 输入域值;
	 required ----- 是否为必填项。true-必填项 | false-非必填项；readonly ---- 只读;
	 disabled ----- 是否可用; type ---- 输入域类型
-->
<#-- textfield -->
<#macro textfield id="" anothername="" label="" name="" value="" cssClass="underline" required="false" readonly="false"
				  disabled="false" size="" maxlength="20"  type="S" cssStyle="" labelposition="" onchange="" onclick="" ondblclick="" onmousedown="" 
				  onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" 
				  onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" >
	<@ww.textfield id="${id}" label="'${label}'" name="'${name}'" value="'${value}'" cssClass="'${cssClass}'" required="${required}" 
				   readonly="${readonly}" disabled="${disabled}" size="${size}" maxlength="${maxlength}" cssStyle="'${cssStyle}'" labelposition="'${labelposition}'" 
				   onclick="'${onclick}'" onblur="'${onblur}'" onchange="'${onchange}'" onselect="'${onselect}'"/>
	<script language="javascript">
		function textfieldCheck_${anothername}(){
			return textfieldCheck(${required},'${name}','${type}','${label}');
		}
	</script>
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

<#--
说明：选择域select
属性：anothername ----- js验证别名; label ---- 标签名称; name ---- 输入域名称; value ---- 输入域值;
	 required ----- 是否为必填项。true-必填项 | false-非必填项；readonly ---- 只读;
	 disabled ----- 是否可用; type ---- 输入域类型; dateFormat ---- 格式化类型;
-->
<#--select-->
<#macro select id="" anothername="" label="" name="" value="" list="" 
			   listKey="" listValue="" required="false" emptyOption="false"
			   size="" disabled="false" headerKey="" headerValue="" onchange="">
	<@ww.select id="${id}" label="'${label}'" name="'${name}'" value="${value}" 
				list="${list}" listKey="${listKey}" listValue="${listValue}" 
				required="${required}" emptyOption="${emptyOption}"
				size="${size}" disabled="${disabled}" headerKey="${headerKey}" headerValue="${headerValue}"
				onchange="'${onchange}'"/>
	<script language="javascript">
		function selectCheck_${anothername}(){
			return selectCheck(${required},'${name}','${label}');
		}
	</script>
</#macro>

<#macro redirectButton value url name="" class="button">
    <input type="button" name="${name}" value="${value}" class="${class}" onclick="location='${url}';"/>
</#macro>

<#macro alignLeft attributes="">
	<@vlh.attribute name="style" value="text-align:left;word-wrap:break-word; word-break:break-all; overflow:auto;${attributes?if_exists}"/>
</#macro>

<#macro alignCenter attributes="">
	<@vlh.attribute name="style" value="text-align:center;word-wrap:break-word; word-break:break-all; overflow:auto;${attributes?if_exists}"/>
</#macro>

<#macro alignRight attributes="">
	<@vlh.attribute name="style" value="text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;${attributes?if_exists}"/>
</#macro>
<#macro blurbutton type="button" class=""  name="" value="" onclick="" onblur="">
<#if (action.hasPermission(name))>
	<input type="${type}" class="${class}"  name="${name}" value="${value}" onclick="${onclick}" onblur="${onblur}" style="cursor:pointer" />
</#if>
</#macro>
<#--
说明：跨列自定义输入框
属性：align ----- 名字的水平对齐方式； valing ----- 名字垂直对齐方式；labelClass ----- label标签的样式；
	 label ----- 名字；cospan ----- 跨越的列数；name ----- 输入框的name；value ----- 输入框的值；
	 class ----- 文本框的样式；maxLength ----- 输入框的长度；size ----- 可见的字符数；type ----- 需要验证的类型；
	 required ----- 是否为必填项。true-必填项 | false-非必填项；anothername ----- js验证别名；
-->
<#macro text align="right" valign="top" labelClass="label" label="" colspan="8" name="" value="" class="underline" 
		maxLength="120" size="120" type="S" required="false" anothername="">
	<tr>
		<td align="${align}" valign="${valign}">
		<#if required == "true">
			<font color="red">*</font>
		</#if>
       		<label class="${labelClass}">${label}:</label>
     	</td>
		<td colspan="${colspan}">
			<input type="text" name="${name}" value="${value}" class="${class}" maxlength="${maxLength}" size="${size}" />	
		</td>
	</tr>
	<script language="javascript">
		function textfieldCheck_${anothername}(){
			return textfieldCheck(${required},'${name}','${type}','${label}');
		}
	</script>
</#macro>

<#-- text2 -->
<#macro text2 align="right" valign="top" labelClass="label" label="" colspan="3" name="" value="" class="underline" 
		maxLength="80" size="80" type="S" required="false" anothername="" disabled="">
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


<#--
说明：跨列自定义textarea
属性：align ----- 名字的水平对齐方式； valing ----- 名字垂直对齐方式；labelClass ----- label标签的样式；
	 label ----- 名字；cospan ----- 跨越的列数；name ----- textarea标签的name；rows ----- 行的数目和尺寸；
	 cols ----- 列的数目和尺寸；value ----- textarea的值；maxLength ----- textarea的最大长度；
	 required ----- 是否为必填项。true-必填项 | false-非必填项；anothername ----- js验证别名；
-->
<#macro textarea align="right" valign="top" labelClass="label" label="" colspan="10" name="" rows="3" 
	   id="" cols="120" value="" maxLength="500" required="false" anothername="">
	<tr>
    	<td align="${align}" valign="${valign}">
    	<#if required == "true">
    		<font color="red">*</font>
    	</#if>
    		<label class="${labelClass}">
    			${label}:
    		</label>
    	</td>
        <td colspan="${colspan}">
        	<textarea id="${id}" name="${name}" rows="${rows}" cols="${cols}" >${value}</textarea>
        </td>
    </tr>
	<script language="javascript">
		function textareaCheck_${anothername}(){
			return textareaCheck(${required},'${name}','${maxLength}','${label}');
		}
	</script>
</#macro>