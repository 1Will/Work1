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
	<tr>
 		<@ww.textfield label="'${action.getText('customerInfo.code')}'" name="'customerInfo.code'" value="'${req.getParameter('customerInfo.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('customerInfo.name')}'" name="'customerInfo.name'" value="'${req.getParameter('customerInfo.name')?if_exists}'" cssClass="'underline'" />		
		<@ww.select label="'${action.getText('customerInfo.type')}'" 
			name="'type.id'" 
			value="'${req.getParameter('type.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allTypes"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	</tr>
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
		<@ww.select label="'${action.getText('customerInfo.city')}'" 
			name="'city.id'" 
			value="'${req.getParameter('city.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allCity"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
	</tr>
	<tr>
		<@ww.select label="'${action.getText('customerInfo.industry')}'" 
			name="'industry.id'" 
			value="'${req.getParameter('industry.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allIndustrys"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<@ww.select label="'${action.getText('customerInfo.companyNature')}'" 
			name="'companyNature.id'" 
			value="'${req.getParameter('companyNature.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allCompanyNatures"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<@ww.textfield label="'${action.getText('customerInfo.keyContacter')}'" name="'customerInfo.keyContacter'" value="'${req.getParameter('customerInfo.keyContacter')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
		//客户类别
		var typeSele=document.all("type.id");
	    var typeGroups=typeSele.options.length;
	    for(i=0;i<typeGroups;i++){
			<#if req.getParameter('type.id')?exists>
	        if(typeSele.options[i].value=="${req.getParameter('type.id')?if_exists}"){
	           typeSele.options[i].selected="true";
	        }
			</#if>
	    }
		//国家
		var countrySele=document.all("country.id");
	    var countryGroups=countrySele.options.length;
	    for(i=0;i<countryGroups;i++){
			<#if req.getParameter('country.id')?exists>
	        if(countrySele.options[i].value=="${req.getParameter('country.id')?if_exists}"){
	           countrySele.options[i].selected="true";
	        }
			</#if>
	    }
 		<#if req.getParameter('country.id')?exists>
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",1,"${action.getText('select.option.all')}","search");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
	    </#if>
	    //省份
		var provinceSele=document.all("province.id");
	    var provinceGroups=provinceSele.options.length;
	    for(i=0;i<provinceGroups;i++){
			<#if req.getParameter('province.id')?exists>
	        if(provinceSele.options[i].value=="${req.getParameter('province.id')?if_exists}"){
	           provinceSele.options[i].selected="true";
	        }
			</#if>
	    }
	    <#if req.getParameter('province.id')?exists>
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",2,"${action.getText('select.option.all')}","search");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
	    </#if>
	    //城市
		var citySele=document.all("city.id");
	    var cityGroups=citySele.options.length;
	    for(i=0;i<cityGroups;i++){
			<#if req.getParameter('city.id')?exists>
	        if(citySele.options[i].value=="${req.getParameter('city.id')?if_exists}"){
	           citySele.options[i].selected="true";
	        }
			</#if>
	    }
	    //行业
		var industrySele=document.all("industry.id");
	    var industryGroups=industrySele.options.length;
	    for(i=0;i<industryGroups;i++){
			<#if req.getParameter('industry.id')?exists>
	        if(industrySele.options[i].value=="${req.getParameter('industry.id')?if_exists}"){
	           industrySele.options[i].selected="true";
	        }
			</#if>
	    }
	    //企业性质
		var companyNatureSele=document.all("companyNature.id");
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
		}else if(getObjByName('type.id').value !== -1){
			
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
		return true;
    }
</script>