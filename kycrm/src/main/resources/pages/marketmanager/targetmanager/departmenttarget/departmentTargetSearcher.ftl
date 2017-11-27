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

<#include "../../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
 		<@ww.select label="'${action.getText('计划类型')}'" 
			name="'deparmentTarget.planType'" 
			value="'${req.getParameter('deparmentTarget.planType')?if_exists}'"
			listKey="key"
			listValue="value"
			list="planType"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<@ww.textfield  id="year" maxlength="4" label="'${action.getText('年份')}'" name="'deparmentTarget.year'" value="'${req.getParameter('deparmentTarget.year')?if_exists}'" cssClass="'underline'" />
		<@ww.select label="'${action.getText('季度')}'" 
			name="'deparmentTarget.quarter'" 
			value="'${req.getParameter('deparmentTarget.quarter')?if_exists}'"
			listKey="key"
			listValue="value"
			list="quarterMap"
			emptyOption="false" 
			disabled="false">
		</@ww.select>

	</tr>
	<tr>
		<@ww.select label="'${action.getText('月份')}'" 
			name="'deparmentTarget.month'" 
			value="'${req.getParameter('deparmentTarget.month')?if_exists}'"
			listKey="top"
			listValue="top"
			list="month"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
		
	function checkInvalidParms(){
	if(jgetObjByName("#year").val()!=""){
			if(!isNumber("year")){
					alert("年份是数字(整数)!");
					jgetObjByName("#year").focus();
					return false;
				}

		}		
		return true;
    }
    
    <#-- 计划类型，季度，月份的关系显示-->
    function objDisabled(){
    		if(jgetObjByName("select[name='deparmentTarget.planType']").get(0).value == "" || jgetObjByName("select[name='deparmentTarget.planType']").get(0).value == 1){
				jgetObjByName(jgetObjByName("select[name='deparmentTarget.quarter']").get(0)).attr("disabled",true); 
				jgetObjByName(jgetObjByName("select[name='deparmentTarget.month']").get(0)).attr("disabled",true); 
    		}
    		if(jgetObjByName("select[name='deparmentTarget.planType']").get(0).value == 2){
	    		jgetObjByName(jgetObjByName("select[name='deparmentTarget.quarter']").get(0)).attr("disabled",false); 
	    		jgetObjByName(jgetObjByName("select[name='deparmentTarget.month']").get(0)).attr("disabled",true); 
    		
    		}
    		if(jgetObjByName("select[name='deparmentTarget.planType']").get(0).value == 3){
	    		jgetObjByName(jgetObjByName("select[name='deparmentTarget.quarter']").get(0)).attr("disabled",true); 
	    		jgetObjByName(jgetObjByName("select[name='deparmentTarget.month']").get(0)).attr("disabled",false); 
    		}
    }
    <#-- 注册事件-->
    function addEvent(){
    	jgetObjByName(jgetObjByName("select[name='deparmentTarget.planType']").get(0)).change(function(){
    		objDisabled();
    	});
    }
    <#-- 赋值 -->
    function assignValue(){
    	<#if req.getParameter('deparmentTarget.planType')?exists>
		    jgetObjByName("select[name='deparmentTarget.planType']").get(0).value="${req.getParameter('deparmentTarget.planType')?if_exists}";
	    </#if>
	     <#if req.getParameter('deparmentTarget.quarter')?exists>
		    jgetObjByName("select[name='deparmentTarget.quarter']").get(0).value="${req.getParameter('deparmentTarget.quarter')?if_exists}";
	    </#if>
	     <#if req.getParameter('companyTarget.month')?exists>
		    jgetObjByName("select[name='deparmentTarget.month']").get(0).value="${req.getParameter('deparmentTarget.month')?if_exists}";
	    </#if>
    }
    
    jgetObjByName(function(){
	    assignValue();
	    objDisabled();
	    addEvent();
    });
   
</script>