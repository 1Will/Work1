<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: competitorProfile.ftl 2011-2-24  hmguan $ -->

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('title')}">
<@ww.form namespace="'/competitor'" name="'competitorProfile'" action="'saveCompetitor'" method="'post'">
	<@ww.token name="saveCompetitorToken"/>
    <@inputTable>
		<@ww.hidden name="'customerInfo.id'" value="'${req.getParameter('customerInfo.id')?if_exists}'"/>
		<@ww.hidden name="'products.id'" value="'${req.getParameter('products.id')?if_exists}'"/>
    	<#if competitor.id?exists>
    		<@ww.hidden name="'competitor.id'" value="#{competitor.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@textfield label="${action.getText('competitor.code')}" name="competitor.code" anothername="code" value="${competitor.code?if_exists}" required="false" cssClass="underline" maxlength="20" disabled="true"/>
            <@textfield label="${action.getText('competitor.companyName')}" name="competitor.companyName" anothername="companyName" value="${competitor.companyName?if_exists}" required="true" cssClass="underline" maxlength="20"/>
            <@textfield label="${action.getText('competitor.companyLegal')}" name="competitor.companyLegal" anothername="companyLegal" value="${competitor.companyLegal?if_exists}" required="true" cssClass="underline" maxlength="20"/>	
		</tr>	
	 	<tr>
            <@textfield label="${action.getText('competitor.telephone')}" name="competitor.telephone" anothername="telephone" value="${competitor.telephone?if_exists}" required="true" cssClass="underline" maxlength="20"/>
            <@textfield label="${action.getText('competitor.fax')}" name="competitor.fax" anothername="fax" value="${competitor.fax?if_exists}" required="false" cssClass="underline" maxlength="20"/>
            <@textfield label="${action.getText('competitor.website')}" name="competitor.website" anothername="website" value="${competitor.website?if_exists}" required="true" cssClass="underline" maxlength="20"/>	
		</tr>			
	 	<tr>
            <@textfield label="${action.getText('competitor.scale')}" name="competitor.scale" anothername="scale" value="${competitor.scale?if_exists}" required="false" cssClass="underline" maxlength="20"/>
		    <@select label="${action.getText('competitor.industry')}" 
		   	   anothername="selectCheckIndustry"
		       name="industry.id" 
		       value="${req.getParameter('industry.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allIndustry" 
		       emptyOption="true" 
		       disabled="false" 
		       required="true">
		    </@select>	
		    <@select label="${action.getText('competitor.nature')}" 
		   	   anothername="selectCheckNature"
		       name="nature.id" 
		       value="${req.getParameter('nature.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allNature" 
		       emptyOption="true" 
		       disabled="false" 
		       required="true">
		    </@select>
		</tr>	
	 	<tr>
            <@textfield label="${action.getText('competitor.business')}" name="competitor.business" anothername="business" value="${competitor.business?if_exists}" required="true" cssClass="underline" maxlength="20"/>
            <@textfield label="${action.getText('competitor.strategy')}" name="competitor.strategy" anothername="strategy" value="${competitor.strategy?if_exists}" required="false" cssClass="underline" maxlength="20"/>
            <@textfield label="${action.getText('competitor.targetMarket')}" name="competitor.targetMarket" anothername="targetMarket" value="${competitor.targetMarket?if_exists}" required="false" cssClass="underline" maxlength="20"/>	
		</tr>	
	 	<tr>
            <@textfield label="${action.getText('competitor.trend')}" name="competitor.trend" anothername="trend" value="${competitor.trend?if_exists}" required="false" cssClass="underline" maxlength="20"/>
		    <@select label="${action.getText('competitor.ability')}" 
		   	   anothername="selectCheckAbility"
		       name="ability.id" 
		       value="${req.getParameter('ability.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allAbility" 
		       emptyOption="true" 
		       disabled="false" 
		       required="true">
		    </@select>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('competitor.customerInfo')}:</label>
	     	</td>
	     	<td>
	     		<#if competitor.customerInfo?exists>
		   		<input type="text" name="competitor.customerInfo" class="underline"  value="${competitor.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="competitor.customerInfo" class="underline"  value="${req.getParameter('competitor.customerInfo')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>    
		</tr>	
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('competitor.products')}:</label>
	     	</td>
	     	<td>
	     		<#if competitor.products?exists>
		   		<input type="text" name="competitor.products" class="underline"  value="${competitor.products.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="competitor.products" class="underline"  value="${req.getParameter('competitor.products')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="products_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td> 
            <@textfield label="${action.getText('competitor.price')}" name="competitor.price" anothername="price" value="${competitor.price?if_exists}" required="false" cssClass="underline" maxlength="20"/>  
        </tr> 
		<tr>
			<@text label="${action.getText('competitor.address')}" name="competitor.address" value="${competitor.address?if_exists}" required="false" ></@text>
		</tr> 
		<tr>
		    <@textarea name="competitor.resource" label="${action.getText('competitor.resource')}" anothername="resource" maxLength="500" required="false" value="${competitor.resource?if_exists}"/>
		</tr>
		<tr>
		    <@textarea name="competitor.advantage" label="${action.getText('competitor.advantage')}" anothername="advantage" maxLength="500" required="false" value="${competitor.advantage?if_exists}"/>
		</tr>
		<tr>		
			<@textarea name="competitor.inferior" label="${action.getText('competitor.inferior')}" anothername="inferior" maxLength="500" required="false" value="${competitor.inferior?if_exists}"/>
   		</tr>	
		<tr>		
			<@textarea name="competitor.response" label="${action.getText('competitor.response')}" anothername="response" maxLength="500" required="false" value="${competitor.response?if_exists}"/>
   		</tr>	
		<tr>		
			<@textarea name="competitor.remark" label="${action.getText('competitor.remark')}" anothername="remark" maxLength="500" required="false" value="${competitor.remark?if_exists}"/>
   		</tr>		
    </@inputTable>
    <@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/competitor/listCompetitor.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if competitor.industry?exists>
			getObjByName('industry.id').value='${competitor.industry.id?if_exists}';
		</#if>
		<#if competitor.nature?exists>
			getObjByName('nature.id').value='${competitor.nature.id?if_exists}';
		</#if>
		<#if competitor.ability?exists>
			getObjByName('ability.id').value='${competitor.ability.id?if_exists}';
		</#if>	
	}
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandlerCustomer);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandlerCustomer(result) {
		if (null != result) {
		 	getObjByName('customerInfo.id').value = result[0];	
		 	getObjByName('competitor.customerInfo').value = result[1];
		}
	}

	//弹出产品查询模态窗体
	function products_OpenDialog(){
	   var url = "${req.contextPath}/com/listProductsWindow.html";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			document.forms[0].elements["products.id"].value = result[0];
	   		getObjByName('competitor.products').value=result[2];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
        //竞争公司名称
        if(!textfieldCheck_companyName()){
	        getObjByName('competitor.companyName').focus();
			return false;
		}
        //企业法人
        if(!textfieldCheck_companyLegal()){
	        getObjByName('competitor.companyLegal').focus();
			return false;
		}
        //联系电话  
        if(!textfieldCheck_telephone()){
	        getObjByName('competitor.telephone').focus();
			return false;
		}
        //网站  
        if(!textfieldCheck_website()){
	        getObjByName('competitor.website').focus();
			return false;
		}
		//行业 
		if(!selectCheck_selectCheckIndustry()){
			getObjByName('industry.id').focus();
		    return false;
		}
		//企业性质   
		if(!selectCheck_selectCheckNature()){
			getObjByName('nature.id').focus();
		    return false;
		}
        //主要经营  
        if(!textfieldCheck_business()){
	        getObjByName('competitor.business').focus();
			return false;
		}
		//威胁级别   
		if(!selectCheck_selectCheckAbility()){
			getObjByName('ability.id').focus();
		    return false;
		}
		//竞争客户名称  
	    if(getObjByName('competitor.customerInfo').value==""){
	        alert('${action.getText('competitor.customerInfo.not.null')}');
	        return false;
	    }
		//竞争产品  
	    if(getObjByName('competitor.products').value==""){
	        alert('${action.getText('competitor.products.not.null')}');
	        return false;
	    }
		//验证价格为double类型
		if(getObjByName('competitor.price').value!=''){
	     	if(!isDoubleNumber("competitor.price")){
				alert("${action.getText('competitor.price.must.be.double')}");
				return false;
			}
	     }
		return true;
	}
</script>
</@htmlPage>
