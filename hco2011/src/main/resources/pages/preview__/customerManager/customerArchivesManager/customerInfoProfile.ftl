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

<#-- $Id: customerInfoProfile.ftl 2009-11-26 9:05:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />

<@htmlPage title="客户档案管理维护">
<@ww.form name="'listForm'" action="'saveCustomerInfo_'" method="'post'">
	<@ww.token name="saveCustomerInfoToken"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'客户编码'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'客户名称'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" required="true"/>
			<@ww.select 
	    		label="'客户类型'"
				required="true"
				name="'customerType'" 
				value="${req.getParameter('customerType')?if_exists}" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'潜在',
					'意向'
					}"
			    emptyOption="false" 
			    disabled="false"/>
		</tr>
		<tr>
			<@ww.select 
	    		label="'行业'"
				required="true"
				name="'industryType'" 
				value="${req.getParameter('industryType')?if_exists}" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'IT',
					'金融',
					'服务'
					}"
			    emptyOption="false" 
			    disabled="false"/>
			    
			<@ww.select 
	    		label="'企业性质'"
				required="true"
				name="'enterprisePropertyType'" 
				value="${req.getParameter('enterprisePropertyType')?if_exists}" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'国企',
					'民营'
					}"
			    emptyOption="false" 
			    disabled="false"/>
	
			<@ww.textfield label="'企业法人'" name="'legalPerson'" value="''" cssClass="'underline'" required="true"/>
		</tr>
		<tr>
			<@ww.select 
	    		label="'国家'"
				required="true"
				name="'nationType'" 
				value="${req.getParameter('nationType')?if_exists}" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'中国',
					'俄罗斯',
					'美国',
					'英国',
					'法国'
					}"
			    emptyOption="false" 
			    disabled="false"/>
			
			<@ww.select 
	    		label="'省份'"
				required="true"
				name="'provinceType'" 
				value="${req.getParameter('provinceType')?if_exists}" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'安徽省',
					'广东省',
					'福建省',
					'上海市',
					'北京市'
					}"
			    emptyOption="false" 
			    disabled="false"/>
			
			<@ww.select 
	    		label="'城市'"
				required="true"
				name="'cityType'" 
				value="${req.getParameter('cityType')?if_exists}" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'合肥市',
					'马鞍山市',
					'芜湖市',
					'安庆市',
					'阜阳市'
					}"
			    emptyOption="false" 
			    disabled="false"/>
		</tr>
		<tr>
			<@ww.textfield label="'主要联系人'" name="'keyPerson'" value="'${req.getParameter('keyPerson')?if_exists}'" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'联系电话'" name="'telphone'" value="'${req.getParameter('telphone')?if_exists}'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'传真号码'" name="'fax'" value="'${req.getParameter('fax')?if_exists}'" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
			<@pp.datePicker label="'创建日期'" 
				name="'createdDate'" 
	   			value="'${req.getParameter('createdDate')?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"/>
			<@ww.textfield label="'注册资本'" name="'registeredFunds'" value="'${req.getParameter('registeredFunds')?if_exists}'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'员工人数'" name="'personCount'" value="'${req.getParameter('personCount')?if_exists}'" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">业务员:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="salesman" 
			      class="underline"  value=""  maxlength="140" size="20" disabled="true"/>
		       <a onClick="salesman_OpenDialog();">
	    	     <img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0"/>
	    	   </a>
            </td>
			
			<@ww.textfield label="'邮编'" name="'postCode'" value="'${req.getParameter('postCode')?if_exists}'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'详细地址'" name="'address'" value="'${req.getParameter('address')?if_exists}'" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
			<@ww.textarea label="'经营范围'" name="'businessScope'" value="'${req.getParameter('businessScope')?if_exists}'" rows="'3'" cols="'30'"/>
		</tr>
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/customerBasicInfoManager_/listCustomerInfo_.html"/>
    </@buttonBar>
</@ww.form>
<script>
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url = "${req.contextPath}/customerBasicInfoManager_/listSalesMan_.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
	   		document.forms[0].elements["salesman"].value = result[1];		 	
		}
	}
</script>
</@htmlPage>