<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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

<#--$Id: codeValueProfile.ftl 11326 2008-03-15 06:48:54Z wzou $ -->

<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('codevalue.title')}">
     <@ww.form  name="'code'" action="'saveCode'" method="'post'" validate="true">
       <@ww.token name="saveCodeToken"/>
       	 	<#if type.id?exists>
                <@ww.hidden name="'type.id'" value="#{type.id}"/>
            </#if>
            <@ww.hidden name="'code.version'" value=""/>
         <@inputTable>
             <tr>
                 <@ww.textfield label="'${action.getText('code.type')}'" name="'type.code'" value="'${type.code?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
                 <@ww.textfield label="'${action.getText('code.name')}'" name="'type.value'" value="'${type.value?if_exists}'" cssClass="'underline'" required="true"/>
             </tr>             
         </@inputTable>
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate()'"/>	          
	         <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/codevalue/listCodeValues.html"/>
         </@buttonBar>
         <#if type.id?exists>
         <@titleBar title="${action.getText('childcode.list')}"/>
         	<#assign itemNo = 1 />
	        <@listTable>
	            <tr>
	            	<th></th>
	                <th>${action.getText('itemNo')}</th>
	                <th>${action.getText('code.code')}</th>
	                <th>${action.getText('code.value')}</th>	                
	            </tr>
	            <#if (type.values.size()>0)>
	     	   	  <#assign listNoRecords = false/>
		          <#list Details as object>
		            <tr>
		              <td><input type="checkbox" name="codeValueDetailIds" value="#{object.id}"/></td>
		              <td>#{itemNo}</td>
		              <#assign itemNo = itemNo + 1 />
		              <td style="text-align:left">${object.code?if_exists}</td>
		              <td style="text-align:left">${object.value?if_exists}</td>
		            </tr>
		          </#list>
		        </#if>
	      </@listTable>
	      <@buttonBar>
	      	 <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="openDetail(#{type.id});"/>
	         <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('codeValueDetail')}" />	          
	         <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"codeValueDetailIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>	   
         </@buttonBar>
         </#if>
     </@ww.form>
     <script language="javascript">
     	function openDetail(codeValueId) {
     		var url = '${req.contextPath}/base/codevalue/editCodeValueDetail.html?type.id='+codeValueId;
     		popupModalDialog(url,650,300);
     		self.location.reload();
     	}
     	function validate(){

     		/*
     		*验证编码名称是否为空，长度是否溢出
     		*/
     		if('' == $('type.value').value){
     			alert("${action.getText('editCodeVale.name.requiredstring')}");
     			return false;
     		}else{
     			if(!isValidLengthValue($('type.value').value,0,30)){
     				alert("${action.getText('editCodeVale.name.maxLength')}");
     				return false;
     			}
     		}
     		
     	}
     </script>
</@htmlPage>
