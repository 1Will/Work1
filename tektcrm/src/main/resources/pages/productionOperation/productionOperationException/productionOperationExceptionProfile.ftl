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

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('经营计划异常页面')}">
     <@ww.form  name="'listForm'" action="'saveProductionOperationException'" namespace="'/productionOperation'" method="'post'" >
         <@ww.token name="saveProductionOperationExceptionToken"/>
    		<@ww.hidden name="'isSaved'" value=""/>
    		<#if productionOperationException.id?exists>
				<@ww.hidden name="'productionOperationException.id'" value="#{productionOperationException.id?if_exists}"/>
			</#if>
			<#if productionOperationDetail.id?exists>
				<@ww.hidden name="'productionOperationDetail.id'" value="#{productionOperationDetail.id?if_exists}"/>
			</#if>
			<#if productionOperationException.projectInfoPlan ?exists>
    		<@ww.hidden name="'projectInfoPlan.id'" value="#{productionOperationException.projectInfoPlan.id?if_exists}"/>
       <#else>
    	    <@ww.hidden name="'projectInfoPlan.id'" value="''"/>
	   </#if>
	   
	   <#if productionOperationException.findPersion ?exists>
    		<@ww.hidden name="'findPersion.id'" value="#{productionOperationException.findPersion.id?if_exists}"/>
       <#else>
    	    <@ww.hidden name="'findPersion.id'" value="'#{persion.id}'"/>
	   </#if>
	   
	   
	   <#if productionOperationException.solvePersion ?exists>
    		<@ww.hidden name="'solvePersion.id'" value="#{productionOperationException.solvePersion.id?if_exists}"/>
       <#else>
    	    <@ww.hidden name="'solvePersion.id'" value="''"/>
	   </#if>
         <@inputTable>
             <tr>
               <@ww.textfield label="'${action.getText('异常编码')}'" name="'productionOperationException.code'"  cssClass="'underline'" disabled="true"/>
                 <@ww.textfield label="'${action.getText('异常名称')}'" name="'productionOperationException.name'"  cssClass="'underline'" required="true" />
			
			 <td align="right" valign="top">
			 <span class="required">*</span>
	       		<label class="label">${action.getText('异常节点')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="projectInfoPlan.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				<a onClick="projectInfoPlanFirst_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
				 <script type="text/javascript">
				 <#if productionOperationException.projectInfoPlan?exists>
				 getObjByName("projectInfoPlan.name").value='${productionOperationException.projectInfoPlan.name?if_exists}';
				 </#if>
				 </script>
			</td>
			
			
			 
             </tr> 
             <tr>
             
              <td align="right" valign="top">
	       		<label class="label">${action.getText('节点负责人')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="projectInfoPlan.personnelFiles.name" class="underline"  value=""  maxlength="140" size="20" disabled="true"/>
		   		<script type="text/javascript">
				 <#if productionOperationException.projectInfoPlan?exists>
				 <#if productionOperationException.projectInfoPlan.personnelFiles?exists>
				 getObjByName("projectInfoPlan.personnelFiles.name").value='${productionOperationException.projectInfoPlan.personnelFiles.name?if_exists}';
				 </#if>
				 </#if>
				 </script>
			</td>
             
              <!--记录人人-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('提出人')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="findPersion.name" class="underline"  value="<#if productionOperationException.findPersion?exists>${productionOperationException.findPersion.name?if_exists}<#else>${persion.name}</#if>" maxlength="140" size="20" disabled="true"/>
				<a onClick="findPerson_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
             
              <@pp.datePicker label="'${action.getText('提出日期')}'" 
	            		   name="'productionOperationException.findDate'"
	     				   value="'${(productionOperationException.findDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   maxlength="10"/>
	     		<script language="javascript">
					var date = new Date();
					if(getObjByName("productionOperationException.findDate").value==''){
						getObjByName("productionOperationException.findDate").value = date.format("yyyy-MM-dd");
					}
		  	   </script>
			
             </tr>
             
             <tr>
             	<!--异常描述-->
				<td align="right" valign="top">
				<span class="required">*</span>
	        		<label class="label">
	        			${action.getText('异常描述')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="productionOperationException.describe" rows="4" cols="100" >${productionOperationException.describe?if_exists}</textarea>
		        </td>
				<!---->
             </tr>
             
             <tr>
             	<!--异常原因-->
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('异常原因')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="productionOperationException.reason" rows="4" cols="100" >${productionOperationException.reason?if_exists}</textarea>
		        </td>
				<!---->
             </tr>
             <tr>
             	<!--解决方案-->
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('解决方案')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="productionOperationException.solution" rows="4" cols="100" >${productionOperationException.solution?if_exists}</textarea>
		        </td>
				<!---->
             </tr>
             
             
             
              <tr>
                  <!--解决人-->
			<td align="right" valign="top">
	       		<label class="label">${action.getText('解决人')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="solvePersion.name" class="underline"  value="<#if productionOperationException.solvePersion?exists>${productionOperationException.solvePersion.name?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
				<a onClick="solvePerson_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
             
              <@pp.datePicker label="'${action.getText('解决日期')}'" 
	            		   name="'productionOperationException.solveDate'"
	     				   value="'${(productionOperationException.solveDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10"  
	     				   maxlength="10"/>
              
             <@ww.select 
	    		label="'${action.getText('异常状态')}'"
				required="true"
				name="'statu.id'" 
				value="${req.getParameter('statu.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allStatu"
			    emptyOption="true" 
		    	disabled="false"/>
		    <script language="javascript">
		     	<#if productionOperationException.statu?exists>
		     		getObjByName('statu.id').value = ${productionOperationException.statu.id};
		     	</#if>
			</script>
               </tr>
               
         </@inputTable>
         <@buttonBar>
         	<#if !(action.isReadOnly())>
		         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		       
		        <#if productionOperationException.isSaved?exists &&productionOperationException.isSaved=='0' >
		            <@vsubmit name="'save'" value="'${action.getText('refer')}'" onclick="'return submitt();'"  >
		            </@vsubmit>
		        <#else>
		            <@vsubmit name="'save'" value="'${action.getText('refer')}'" onclick="'return submitt();'"  disabled="true">
		            </@vsubmit>
		        </#if>	
					<#-- 继续新建按钮   -->
					<#if productionOperationException.id?exists>
					<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/productionOperation/editProductionOperationException.html?productionOperationDetail.id=#{productionOperationDetail.id}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
					<#else>
					<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/productionOperation/editProductionOperationException.html?productionOperationDetail.id=#{productionOperationDetail.id}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
					<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
					</script>
					</#if>
		     </#if>
	         <input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
	  		
         </@buttonBar>	
     </@ww.form>
     <script type="text/javascript">
     
      function projectInfoPlanFirst_OpenDialog(){ 
	    var url= "${req.contextPath}/projectInfo/listProPlan.html?readOnly=${req.getParameter('readOnly')?if_exists}&productionOperationDetail.id=#{productionOperationDetail.id?if_exists}&backCheckBox=backCheckBox";
	    popupModalDialog(url, 800, 600, creatorSelectorHandler_1);
	 }
	 	function creatorSelectorHandler_1(result) {
		if (null != result) {
			getObjByName("projectInfoPlan.id").value = result[0];
	   		getObjByName("projectInfoPlan.name").value = result[1];
	   		getObjByName("projectInfoPlan.personnelFiles.name").value = result[2];
		}
	}
     
   //弹出提出人查询模态窗体
	function findPerson_OpenDialog(){
	  var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, findPersonSelectorHandler);
	 }
	 //获得提出人模态窗体返回值
	function findPersonSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["findPersion.id"].value = result[0];
	   		document.forms[0].elements["findPersion.name"].value = result[2];		 	
		}
		}
		  //弹出解决人查询模态窗体
	function solvePerson_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, solvePersonSelectorHandler);
	 }
	 //获得解决人模态窗体返回值
	function solvePersonSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["solvePersion.id"].value = result[0];
	   		document.forms[0].elements["solvePersion.name"].value = result[2];		 	
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
	if(getObjByName("productionOperationException.name").value==''){
	alert("请输入异常名称");
	return  false;
	
	}
	if(getObjByName("projectInfoPlan.id").value==''){
	alert("请选择异常节点");
	return  false;
	
	}
	if(getObjByName("findPersion.id").value==''){
	alert("请选择提出人");
	return  false;
	
	}
	if(getObjByName("productionOperationException.findDate").value==''){
	alert("请选择提出日期");
	return  false;
	
	}
	if(getObjByName("productionOperationException.describe").value==''){
	alert("请输入异常描述");
	return  false;
	
	}
	
	if(getObjByName("statu.id").value==''){
	alert("请选择异常状态");
	return  false;
	
	}else{
	
	if(getObjByName("statu.id").text=='已解决'){
	
	if(getObjByName("productionOperationException.reason").value==''){
	alert("请输入异常原因");
	return  false;
	
	}
	
	if(getObjByName("solvePersion.id").value==''){
	alert("请选择解决人");
	return  false;
	
	}
	
	if(getObjByName("productionOperationException.solveDate").value==''){
	alert("请选择解决日期");
	return  false;
	}
	
	}
	
	}
	return true;
	}
	
	
	
	
    </script>
</@htmlPage>
