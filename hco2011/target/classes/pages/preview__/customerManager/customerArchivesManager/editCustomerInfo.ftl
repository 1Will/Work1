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

<#-- $Id: editCustomerInfo.ftl 2009-11-26 9:26:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户档案管理维护">
<@ww.form name="'listForm'" action="'editCustomerInfo_'" method="'post'">
	<@ww.token name="editCustomerInfoToken"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'客户编码'" name="'code'" value="'customer_001'" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'客户名称'" name="'name'" value="'安徽实德养殖基地'" cssClass="'underline'" required="true"/>
			<@ww.select 
	    		label="'客户类型'"
				required="true"
				name="'customerType'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'熟悉',
					'陌生'
					}"
			    emptyOption="false" 
			    disabled="false"/>
		</tr>
		<tr>
			<@ww.select 
	    		label="'行业'"
				required="true"
				name="'industryType'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'养殖业',
			    	'IT行业',
					'金融业',
					'服务业'
					}"
			    emptyOption="false" 
			    disabled="false"/>
			    
			<@ww.select 
	    		label="'企业性质'"
				required="true"
				name="'enterprisePropertyType'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'民营',
					'国营'
					}"
			    emptyOption="false" 
			    disabled="false"/>
	
			<@ww.textfield label="'企业法人'" name="'legalPerson'" value="'李**'" cssClass="'underline'" required="true"/>
		</tr>
		<tr>
			<@ww.select 
	    		label="'国家'"
				required="true"
				name="'nationType'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
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
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
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
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
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
			<@ww.textfield label="'主要联系人'" name="'keyPerson'" value="'刘伟'" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'联系电话'" name="'telphone'" value="'1234567890'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'传真号码'" name="'fax'" value="'1234567890'" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
			<@pp.datePicker label="'创建日期'" 
				name="'createdDate'" 
	   			value="'2009-11-26'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"/>
			<@ww.textfield label="'注册资本'" name="'registeredFunds'" value="'100(万)'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'员工人数'" name="'personCount'" value="'50'" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">业务员:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="salesman" 
			      class="underline"  value="小朱"  maxlength="140" size="20" disabled="true"/>
		       <a onClick="salesman_OpenDialog();">
	    	     <img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0"/>
	    	   </a>
            </td>
			
			<@ww.textfield label="'邮编'" name="'postCode'" value="'230001'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'详细地址'" name="'address'" value="'安徽省合肥市高新区'" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
			<@ww.textarea label="'经营范围'" name="'businessScope'" value="'应用程序软件开发'" rows="'3'" cols="'30'"/>
		</tr>
	</@inputTable>
	<@buttonBar>
            <@vsubmit name="'save'" value="'${'保存'}'"/>
            <@redirectButton value="${'返回'}" url="${req.contextPath}/customerBasicInfoManager_/listCustomerInfo_.html"/>
    </@buttonBar>
</@ww.form>
<script>
	window.onload = function () {
			document.all.frame.src='${req.contextPath}/customerBasicInfoManager_/listAdditionalInfo_.html';
 	}	

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
<ul id="beautytab">
	<li>
		<a id="additionalInformation" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/customerBasicInfoManager_/listAdditionalInfo_.html' target="frame" >附加信息</a>
	</li>
	<li>
		<a id="contactInformation" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/customerBasicInfoManager_/listContactInfo_.html' target="frame" >联系人信息</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
