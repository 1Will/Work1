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

<#include "../includes/hco2011.ftl" />
<@htmlPage title="${pageTitle?if_exists}${action.getText('维护页面')}">
     <@ww.form  name="'listForm'" action="'saveProductionOperation'" namespace="'/productionOperation'" method="'post'" >
         <@ww.token name="saveProductionOperationToken"/>
    		<@ww.hidden name="'isSaved'" value=""/>
    		<#if productionOperation.makeUpPerson?exists>
    		 <@ww.hidden name="'makeUpPerson.id'" value="#{productionOperation.makeUpPerson.id?if_exists}"/>
    		 <#else>
    		 <@ww.hidden name="'makeUpPerson.id'" value=""/>
    		 </#if>
    		 <#if productionOperation.auditingPerson?exists>
    		  <@ww.hidden name="'auditingPerson.id'" value="#{productionOperation.auditingPerson.id?if_exists}"/>
    		  <#else>
    		  <@ww.hidden name="'auditingPerson.id'" value=""/>
    		  </#if>
    		<#if productionOperation.id?exists>
				<@ww.hidden name="'productionOperation.id'" value="#{productionOperation.id?if_exists}"/>
			</#if>
			<input type="hidden" name="managerType" id="managerType" value="${managerType?if_exists}">
         <@inputTable>
             <tr>
                 <@ww.textfield label="'${pageTitle?if_exists}${action.getText('编码')}'" name="'productionOperation.code'" value="'${productionOperation.code?if_exists}'" cssClass="'underline'" required="true"/>
                 <@ww.textfield label="'${pageTitle?if_exists}${action.getText('名称')}'" name="'productionOperation.name'" value="'${productionOperation.name?if_exists}'" cssClass="'underline'" required="true"/>
             </tr> 
             <tr>
            <!--编制人-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('编制人')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="makeUpPerson.name" class="underline"  value="<#if productionOperation.makeUpPerson?exists>${productionOperation.makeUpPerson.name?if_exists}<#else>${userManager.user.name}</#if>" maxlength="140" size="20" disabled="true"/>
				<a onClick="makeUpPerson_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
             
              <@pp.datePicker label="'${action.getText('编制日期')}'" 
	            		   name="'productionOperation.makeUpDate'"
	     				   value="'${(productionOperation.makeUpDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   maxlength="10"/>
	     		<script language="javascript">
					var date = new Date();
					if(getObjByName("productionOperation.makeUpDate").value==''){
						getObjByName("productionOperation.makeUpDate").value = date.format("yyyy-MM-dd");
					}
		  	   </script>
             </tr>
              <tr>
              <!--编制人-->
			<td align="right" valign="top">
	       		<span class="required"></span>
	       		<label class="label">${action.getText('审核人')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="auditingPerson.name" class="underline"  value="<#if productionOperation.auditingPerson?exists>${productionOperation.auditingPerson.name?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
				<a onClick="auditingPerson_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
             
              <@pp.datePicker label="'${action.getText('审核日期')}'" 
	            		   name="'productionOperation.auditingDate'"
	     				   value="'${(productionOperation.deliveryDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" 
	     				   maxlength="10"/>
	     		<script language="javascript">
					var date = new Date();
					if(getObjByName("productionOperation.auditingDate").value==''){
						getObjByName("productionOperation.auditingDate").value = date.format("yyyy-MM-dd");
					}
		  	   </script>
             </tr>
            
             <tr>
             	<!--备注-->
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('备注')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="productionOperation.mark" rows="4" cols="150" >${productionOperation.mark?if_exists}</textarea>
		        </td>
				<!---->
             </tr>
         </@inputTable>
         <@buttonBar>
         	<#if !(action.isReadOnly())>
		         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		       
		        <#if productionOperation.isSaved?exists &&productionOperation.isSaved=='0' >
		            <@vsubmit name="'save'" value="'${action.getText('refer')}'" onclick="'return submitt();'"  >
		            </@vsubmit>
		        <#else>
		            <@vsubmit name="'save'" value="'${action.getText('refer')}'" onclick="'return submitt();'"  disabled="true">
		            </@vsubmit>
		        </#if>	
					<#-- 继续新建按钮   -->
					<#if productionOperation.id?exists>
					<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/productionOperation/editProductionOperation.html"/>
					<#else>
					<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/productionOperation/editProductionOperation.html"/>
					<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
					</script>
					</#if>
		     </#if>
	         <#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag'>
			<!-- 关闭按钮 -->
			<@vbutton name="close" value="'${action.getText('close')}'" class="button" onclick="closeThis();"/>
	  		<#else>
			<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/productionOperation/listProductionOperation.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	  		</#if>
	  		
         </@buttonBar>	
     </@ww.form>
     <script type="text/javascript">
   //弹出编制人查询模态窗体
	function makeUpPerson_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, makeUpPersonSelectorHandler);
	   //window.open(url);
	 }
	 //获得编制人模态窗体返回值
	function makeUpPersonSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["makeUpPerson.id"].value = result[0];
	   		document.forms[0].elements["makeUpPerson.name"].value = result[2];		 	
		}
	}
	  //弹出编制人查询模态窗体
	function auditingPerson_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, auditingPersonSelectorHandler);
	   //window.open(url);
	 }
	 //获得编制人模态窗体返回值
	function auditingPersonSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["auditingPerson.id"].value = result[0];
	   		document.forms[0].elements["auditingPerson.name"].value = result[2];		 	
		}
	}
		function savee(){
	     getObjByName('isSaved').value="0";
     	 return check();
	     
	}
	
	function submitt(){
		getObjByName('isSaved').value="1";
		return check();
	}
	
	function  check(){
	if(getObjByName("productionOperation.code").value==''){
	alert("请输入计划编码");
	return false;
	}
	
	if(getObjByName("productionOperation.name").value==''){
	alert("请输入计划名称");
	return false;
	}
	if(getObjByName("makeUpPerson.id").value==''){
	alert("请输入编制人");
	return false;
	}
	if(getObjByName("productionOperation.makeUpDate").value==''){
	alert("请输入编制日期");
	return false;
	}
	
	
	return true;
	
	
	}
	
    </script>
</@htmlPage>
<#if productionOperation.id?exists>
<ul id="beautytab">
	<li>
        <a id="productionOperationDetail" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/productionOperation/listProductionOperationDetail.html?productionOperation.id=#{productionOperation.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >计划详细</a>
	</li>
</ul>
	<iframe name="frame" frameborder="0.5" src="${req.contextPath}/productionOperation/listProductionOperationDetail.html?productionOperation.id=#{productionOperation.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>