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

<#-- $Id: customerInfoSearcher.ftl 2009-12-10 15:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@inputTable>
	
	<!--重新排版wclin 11.7.6____________________________________________________________________________________-->
	<tr>
	<!--客户编码-->
	<@ww.textfield label="'${action.getText('customerInfo.code')}'" name="'customerInfo.code'" value="'${req.getParameter('customerInfo.code')?if_exists}'" cssClass="'underline'" />
	<!--客户名称-->	
	<@ww.textfield label="'${action.getText('customerInfo.name')}'" name="'customerInfo.name'" value="'${req.getParameter('customerInfo.name')?if_exists}'" cssClass="'underline'" />		
	</tr>
	<tr>
	<!--主要联系人-->
	<@ww.textfield label="'${action.getText('customerInfo.keyContacter')}'" name="'customerInfo.keyContacter'" value="'${req.getParameter('customerInfo.keyContacter')?if_exists}'" cssClass="'underline'" />
	<!--业务员-->
	<@ww.textfield label="'${action.getText('customerInfo.salesman')}'" name="'customerInfo.salesman'" value="'${req.getParameter('customerInfo.salesman')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
	<!--客户状态-->
	<@ww.select label="'${action.getText('customerInfo.type')}'" 
			name="'type.id'" 
			value="'${req.getParameter('type.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allTypes"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	<!--客户等级-->
	<@ww.select label="'${action.getText('customerInfo.step')}'" 
			name="'step.id'" 
			value="'${req.getParameter('step.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allSteps"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	</tr>
	<tr>
	<!--行业-->
	<@ww.select label="'${action.getText('customerInfo.industry')}'" 
			name="'industry.id'" 
			value="'${req.getParameter('industry.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allIndustrys"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<!--企业性质-->
		<@ww.select label="'${action.getText('customerInfo.companyNature')}'" 
			name="'companyNature.id'" 
			value="'${req.getParameter('companyNature.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allCompanyNatures"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	</tr>
	<tr>
	<!--国家-->
	<@ww.select label="'${action.getText('customerInfo.country')}'" 
			name="'country.id'" 
			value="'${req.getParameter('country.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allCountrys"
			emptyOption="false" 
			disabled="false"
			onchange="'areaCascadeDWR(\"country.id\",\"province.id\",\"city.id\",1,\"${action.getText('select.option.all')}\",\"search\")'">
		</@ww.select>
		<!--省份-->
		<@ww.select label="'${action.getText('customerInfo.province')}'" 
			name="'province.id'" 
			value="'${req.getParameter('province.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allProvince"
			emptyOption="false" 
			disabled="false"
			onchange="'areaCascadeDWR(\"country.id\",\"province.id\",\"city.id\",2,\"${action.getText('select.option.all')}\",\"search\")'">
		</@ww.select>
	</tr>
	<tr>
	<!--城市-->
	<@ww.select label="'${action.getText('customerInfo.city')}'" 
			name="'city.id'" 
			value="'${req.getParameter('city.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allCity"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	<!---->
	
	<td align="right">
			<laber class="label">告警状态:</laber>
			</td>
			<td align="left">
				<select id="customerInfo.state" name="customerInfo.state">
    				<option value="">所有</option>
    				<option value="正常状态">正常状态</option>
    				<option value="初步告警">初步告警</option>
    				<option value="中度告警">中度告警</option>
    				<option value="高度告警">高度告警</option>
    				<option value="严重告警">严重告警</option>
				</select>
			</td>
	</tr>
	<tr>
	<!--仅查询失效-->
	<@crm_onlySearchInvalid_checkBox />
	<!---->
	</tr>
</@inputTable>
<script language="javascript">
		var typeSele= getObjByName("type.id");
	    var typeGroups=typeSele.options.length;
	    for(i=0;i<typeGroups;i++){
			<#if req.getParameter('type.id')?exists>
	        if(typeSele.options[i].value=="${req.getParameter('type.id')?if_exists}"){
	           typeSele.options[i].selected="true";
	        }
			</#if>
	    }
	    //客户等级
	    
			<#if req.getParameter('step.id')?exists>
				getObjByName('step.id').value='${req.getParameter('step.id')}';
			</#if>
		var countrySele= getObjByName("country.id");
	    var countryGroups=countrySele.options.length;
	    for(i=0;i<countryGroups;i++){
			<#if req.getParameter('country.id')?exists>
	        if(countrySele.options[i].value=="${req.getParameter('country.id')?if_exists}"){
	           countrySele.options[i].selected="true";
	        }
			</#if>
	    }
	    <#if req.getParameter('country.id')?exists>
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",1,"${action.getText('select.option.all')}","search");
	    	DWREngine.setAsync(true);
	    </#if>
		var provinceSele= getObjByName("province.id");
	    var provinceGroups=provinceSele.options.length;
	    for(i=0;i<provinceGroups;i++){
			<#if req.getParameter('province.id')?exists>
	        if(provinceSele.options[i].value=="${req.getParameter('province.id')?if_exists}"){
	           provinceSele.options[i].selected="true";
	        }
			</#if>
	    }
	    <#if req.getParameter('province.id')?exists>
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",2,"${action.getText('select.option.all')}","search");
	    	DWREngine.setAsync(true);
	    </#if>
		var citySele= getObjByName("city.id");
	    var cityGroups=citySele.options.length;
	    for(i=0;i<cityGroups;i++){
			<#if req.getParameter('city.id')?exists>
	        if(citySele.options[i].value=="${req.getParameter('city.id')?if_exists}"){
	           citySele.options[i].selected="true";
	        }
			</#if>
	    }
		var industrySele= getObjByName("industry.id");
	    var industryGroups=industrySele.options.length;
	    for(i=0;i<industryGroups;i++){
			<#if req.getParameter('industry.id')?exists>
	        if(industrySele.options[i].value=="${req.getParameter('industry.id')?if_exists}"){
	           industrySele.options[i].selected="true";
	        }
			</#if>
	    }
		var companyNatureSele= getObjByName("companyNature.id");
	    var companyNatureGroups=companyNatureSele.options.length;
	    for(i=0;i<companyNatureGroups;i++){
			<#if req.getParameter('companyNature.id')?exists>
	        if(companyNatureSele.options[i].value=="${req.getParameter('companyNature.id')?if_exists}"){
	           companyNatureSele.options[i].selected="true";
	        }
			</#if>
	    }
	    
	function checkInvalidParms(){
		
		if (getObjByName('type.id').value==-1){
			getObjByName('type.id').value='';
		}
		if (getObjByName('country.id').value==-1){
			getObjByName('country.id').value='';
		}
		if (getObjByName('province.id').value==-1){
			getObjByName('province.id').value='';
		}
		if (getObjByName('city.id').value==-1){
			getObjByName('city.id').value='';
		}
		if (getObjByName('industry.id').value==-1){
			getObjByName('industry.id').value='';
		}
		if (getObjByName('companyNature.id').value==-1){
			getObjByName('companyNature.id').value='';
		}
		if (getObjByName('step.id').value==-1){
			getObjByName('step.id').value='';
		}
		return true;
    }
</script>