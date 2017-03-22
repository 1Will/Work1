<#include "../../includes/hco2011.ftl" />
<html>
<body>
		<table border="0" cellpadding="0" width="100%">
			<#assign hasErrors = actionErrors?exists && actionErrors.size() gt 0 />
	      	<#assign hasMessages = actionMessages?exists && actionMessages.size() gt 0 />
	      	<#assign messageNumber = 0 />
	      	<tr>
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
    </body>
</html>