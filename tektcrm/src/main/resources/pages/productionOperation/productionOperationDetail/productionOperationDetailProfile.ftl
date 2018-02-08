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
<@htmlPage title="${action.getText('经营计划详细页面')}">
     <@ww.form  name="'listForm'" action="'saveProductionOperationDetail'" namespace="'/productionOperation'" method="'post'" >
         <@ww.token name="saveProductionOperationDetailToken"/>
    		<@ww.hidden name="'isSaved'" value=""/>
    		<#if productionOperation.id?exists>
				<@ww.hidden name="'productionOperation.id'" value="#{productionOperation.id?if_exists}"/>
			</#if>
			<#if productionOperationDetail.id?exists>
				<@ww.hidden name="'productionOperationDetail.id'" value="#{productionOperationDetail.id?if_exists}"/>
			</#if>
			<#if productionOperationDetail.product?exists>
				<@ww.hidden name="'product.id'" value="#{productionOperationDetail.product.id?if_exists}"/>
			<#else>
			<@ww.hidden name="'product.id'" value=""/>
			</#if>
			<#if productionOperationDetail.contractManagement?exists>
				<@ww.hidden name="'contractManagement.id'" value="#{productionOperationDetail.contractManagement.id?if_exists}"/>
			<#else>
			<@ww.hidden name="'contractManagement.id'" value=""/>
			</#if>
			<#if productionOperationDetail.productList?exists>
				<@ww.hidden name="'productList.id'" value="#{productionOperationDetail.productList.id?if_exists}"/>
			<#else>
			<@ww.hidden name="'productList.id'" value=""/>
			</#if>
			
         <@inputTable>
             <tr>
             <!--产品名称-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('产品名称')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="product.name" class="underline"  value="<#if productionOperationDetail.product?exists>${productionOperationDetail.product.name?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
				<a onClick="products_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			  <!--产品型号-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('型号')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="product.model" class="underline"  value="<#if productionOperationDetail.product?exists>${productionOperationDetail.product.model?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
			</td>
			
			
			  <!--合同-->
			<td align="right" valign="top">
	       		<label class="label">${action.getText('合同')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="contractManagement.name" class="underline"  value="<#if productionOperationDetail.contractManagement?exists>${productionOperationDetail.contractManagement.contractName?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
				
				<a onClick="contractManagement_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			 
             </tr> 
             <tr>
             
             <@ww.select 
	    		label="'${action.getText('单位')}'"
				required="true"
				name="'unit.id'" 
				value="${req.getParameter('unit.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allUnit"
			     emptyOption="true"  
		    	disabled="false"/>
		    <script language="javascript">
		     	<#if productionOperationDetail.unit?exists>
		     		getObjByName('unit.id').value = ${productionOperationDetail.unit.id};
		     	</#if>
			</script>
             
                 <@ww.textfield label="'${action.getText('交付数量')}'" name="'productionOperationDetail.deliverNum'" value="'#{productionOperationDetail.deliverNum?if_exists}'" cssClass="'underline'" required="true"/>
             
            <@ww.select 
	    		label="'${action.getText('检验方式')}'"
				required="true"
				name="'testType.id'" 
				value="${req.getParameter('testType.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allTestType"
			    emptyOption="true" 
		    	disabled="false"/>
		    <script language="javascript">
		     	<#if productionOperationDetail.testType?exists>
		     		getObjByName('testType.id').value = ${productionOperationDetail.testType.id};
		     	</#if>
			</script>
			
			
             
             </tr>
              <tr>
              
               <@pp.datePicker label="'${action.getText('交付节点')}'" 
	            		   name="'productionOperationDetail.deliverDate'"
	     				   value="'${(productionOperationDetail.deliverDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   maxlength="10"/>
	     		<script language="javascript">
					var date = new Date();
					if(getObjByName("productionOperationDetail.deliverDate").value==''){
						getObjByName("productionOperationDetail.deliverDate").value = date.format("yyyy-MM-dd");
					}
		  	   </script>
		  	   
		  	   
               <@pp.datePicker label="'${action.getText('技术资料节点')}'" 
	            		   name="'productionOperationDetail.technologyDate'"
	     				   value="'${(productionOperationDetail.technologyDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   maxlength="10"/>
	     		<script language="javascript">
					var date = new Date();
					if(getObjByName("productionOperationDetail.technologyDate").value==''){
						getObjByName("productionOperationDetail.technologyDate").value = date.format("yyyy-MM-dd");
					}
		  	   </script>
		  	   
		  	    <@pp.datePicker label="'${action.getText('采购节点')}'" 
	            		   name="'productionOperationDetail.purchaseDate'"
	     				   value="'${(productionOperationDetail.purchaseDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   maxlength="10"/>
	     		<script language="javascript">
					var date = new Date();
					if(getObjByName("productionOperationDetail.purchaseDate").value==''){
						getObjByName("productionOperationDetail.purchaseDate").value = date.format("yyyy-MM-dd");
					}
		  	   </script>
               </tr>
               
                  <tr>
		  	   
		  	    <@pp.datePicker label="'${action.getText('制造节点')}'" 
	            		   name="'productionOperationDetail.makeDate'"
	     				   value="'${(productionOperationDetail.makeDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   maxlength="10"/>
	     		<script language="javascript">
					var date = new Date();
					if(getObjByName("productionOperationDetail.makeDate").value==''){
						getObjByName("productionOperationDetail.makeDate").value = date.format("yyyy-MM-dd");
					}
		  	   </script>
		  	   
               <@pp.datePicker label="'${action.getText('厂检节点')}'" 
	            		   name="'productionOperationDetail.testDate'"
	     				   value="'${(productionOperationDetail.testDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   maxlength="10"/>
	     		<script language="javascript">
					var date = new Date();
					if(getObjByName("productionOperationDetail.testDate").value==''){
						getObjByName("productionOperationDetail.testDate").value = date.format("yyyy-MM-dd");
					}
		  	   </script>
		  	   <@pp.datePicker label="'${action.getText('军检节点')}'" 
	            		   name="'productionOperationDetail.jtestDate'"
	     				   value="'${(productionOperationDetail.jtestDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   required="false"
	     				   maxlength="10"/>
	     		<script language="javascript">
					var date = new Date();
					if(getObjByName("productionOperationDetail.jtestDate").value==''){
						getObjByName("productionOperationDetail.jtestDate").value = date.format("yyyy-MM-dd");
					}
		  	   </script>
               </tr>
               <tr>
                <td align="right" valign="top">
	       		<label class="label">${action.getText('产品状态')}:</label>
	     	  </td>
	     	  <td colspan="8">
		   		<input type="text" name="productionOperationDetail.proStatuString" class="underline"  value="${productionOperationDetail.proStatuString?if_exists}"  maxlength="800" size="130" disabled="true"/>
			 </td>
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
					<#if productionOperationDetail.id?exists>
					<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/productionOperation/editProductionOperationDetail.html?productionOperation.id=#{productionOperation.id}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
					<@vbutton value="${action.getText('复制计划节点')}" class="button" onclick ="copyPlan()"/>
					<#else>
					<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/productionOperation/editProductionOperationDetail.html?productionOperation.id=#{productionOperation.id}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
					<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
					</script>
					</#if>
		     </#if>
	         <input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
	  		
         </@buttonBar>	
     </@ww.form>
     <script type='text/javascript' src='${req.contextPath}/dwr/interface/CopyProjectPerAndPlan.js'></script>
     <script type="text/javascript">
     //合同管理模态窗体
	function contractManagement_OpenDialog(){
		var url = "${req.contextPath}/productList/listProductList_invoices.html?productionOperation=productionOperation&readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 900, 600, setContractManagement);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function setContractManagement(result) {
		if (null != result) {
		    getObjByName('productList.id').value = result[0];
			getObjByName('contractManagement.id').value = result[1];
	   		getObjByName('contractManagement.name').value=result[2];
	   		getObjByName("product.id").value= result[3];
			getObjByName("product.name").value= result[4];
			getObjByName("product.model").value= result[5];
		}
	}
     
     	//弹出产品查询模态窗体
	function products_OpenDialog(){
	   var url = "${req.contextPath}/com/listProductsWindow.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			getObjByName("product.id").value= result[0];
			getObjByName("product.name").value= result[2];
			getObjByName("product.model").value= result[4];
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
	if(getObjByName("product.id").value==''){
	alert("请输入产品名称");
	return false;
	}
	if(getObjByName("unit.id").value==''){
	alert("请选择单位");
	return false;
	}
	if(getObjByName("testType.id").value==''){
	alert("请选择检验方式");
	return false;
	}
	
	if(getObjByName("productionOperationDetail.deliverDate").value==''){
	alert("请输入交付节点");
	return false;
	}
	
	
	if(getObjByName("productionOperationDetail.technologyDate").value==''){
	alert("请输入技术资料节点");
	return false;
	}
	
	
	if(getObjByName("productionOperationDetail.purchaseDate").value==''){
	alert("请输入采购节点");
	return false;
	}
	
	if(getObjByName("productionOperationDetail.makeDate").value==''){
	alert("请制造节点");
	return false;
	}
	
	if(getObjByName("productionOperationDetail.testDate").value==''){
	alert("请输入节点");
	return false;
	}
	

	
	
	return true;
	
	
	}
	
	//弹出项目列表
	function copyPlan(){
	   var url =  "${req.contextPath}/productionOperation/listProductionOperationDetailMain.html?readOnly=${req.getParameter('readOnly')?if_exists}&productionOperationDetailByCopyId=${productionOperationDetail.id?if_exists}";
	   popupModalDialog(url, 800, 600, copyPlan_);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function copyPlan_(result) {
		if (null != result) {
		    var fromProductionOperationDetail=result[0];//源资源对象
		    var toProductionOperationDetail=getObjByName('productionOperationDetail.id').value;//目标资源对象计划
		    DWREngine.setAsync(false); 
	 		CopyProjectPerAndPlan.saveProductionOperationDetailPlan(fromProductionOperationDetail,toProductionOperationDetail);
	 		DWREngine.setAsync(true); 
	 		window.location.reload(true);
		}
	}
	
    </script>
</@htmlPage>
<#if productionOperationDetail.id?exists>
<ul id="beautytab">
	<li>
		<a id="plan" class="selectedtab" onclick="activeTab(this);" href='${req.contextPath}/projectInfo/listProPlan.html?productionOperationDetail.id=#{productionOperationDetail.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >生产经营计划节点</a>
	</li>
	<li>
		<a id="exception" onclick="activeTab(this);" href='${req.contextPath}/productionOperation/listProductionOperationException.html?productionOperationDetail.id=#{productionOperationDetail.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >生成经营计划异常</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/projectInfo/listProPlan.html?productionOperationDetail.id=#{productionOperationDetail.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="yes" vspace=0 hspace=0 width="100%" height="60%"/>
</#if>
