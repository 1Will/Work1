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

<#-- $Id: ceProfile.ftl 2009-11-27 14:49:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />
<@htmlPage title="咨询登记管理维护">
<@ww.form name="'listForm'" action="'saveCEInfo_'" method="'post'">
	<@ww.token name="saveCEInfoToken"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'客户名称'" name="'name'" value="''" cssClass="'underline'" required="true"/>
			<@ww.select 
	    		label="'客户类型'"
				required="false"
				name="'customerType'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'所有',
					'潜在',
					'意向',
					'已签'
					}"
			    emptyOption="false" 
			    disabled="false"/>
			<@ww.textfield label="'联系人'" name="'contactPerson'" value="''" cssClass="'underline'" required="true"/>
		</tr>
		<tr>
			<@ww.select 
	    		label="'行业'"
				required="false"
				name="'industryType'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'所有',
					'IT行业',
					'金融业',
					'服务业',
					'养殖业'
					}"
			    emptyOption="false" 
			    disabled="false"/>
			    
			<@ww.select 
	    		label="'企业性质'"
				required="false"
				name="'enterprisePropertyType'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'所有',
					'国营',
					'民营'
					}"
			    emptyOption="false" 
			    disabled="false"/>
			    
			<@ww.textfield label="'企业法人'" name="'legalPerson'" value="''" cssClass="'underline'"/>
		</tr>
		<tr>
			<@ww.select 
	    		label="'国家'"
				required="false"
				name="'national'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'所有',
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
				required="false"
				name="'provinceType'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'所有',
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
				required="false"
				name="'cityType'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'所有',
					'合肥市',
					'马鞍山市',
					'芜湖市',
					'蚌埠市',
					'阜阳市'
					}"
			    emptyOption="false" 
			    disabled="false"/>
		</tr>
		<tr>
			<@ww.select 
	    		label="'部门'"
				required="false"
				name="'dept'" 
				value="selectedDept" 
				headerKey="1"
				headerValue="selectedDept"
			    list="{
			    	'所有',
					'软件研发部',
					'网络事业部',
					'系统集成部',
					'电子安防部'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		    
		    <@ww.select 
	    		label="'职位'"
				required="false"
				name="'duty'" 
				value="selectedDuty" 
				headerKey="1"
				headerValue="selectedDuty"
			    list="{
			    	'所有',
					'部门经理',
					'项目组组长',
					'配置组组长',
					'ORACLE DBA',
					'程序员'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		    	
		    <@ww.textfield label="'电话'" name="'telphone'" value="''" cssClass="'underline'"/>
		</tr>
		<tr>
			<@ww.textfield label="'手机'" name="'mobilePhone'" value="''" cssClass="'underline'"/>
			<@ww.textfield label="'传真'" name="'fax'" value="''" cssClass="'underline'"/>
			<@ww.textfield label="'Email'" name="'email'" value="''" cssClass="'underline'"/>
		</tr>
		<tr>
			<@ww.textfield label="'QQ号码'" name="'qq'" value="''" cssClass="'underline'"/>
			<@ww.textfield label="'信息来源'" name="'infoScourse'" value="''" cssClass="'underline'"/>
			<@ww.textfield label="'地址'" name="'address'" value="''" cssClass="'underline'"/>
		</tr>
		<tr>
			<@ww.textfield label="'客服人员'" name="'customerService'" value="''" cssClass="'underline'" required="true"/>
			<@pp.datePicker 
				label="'咨询时间'" 
				name="'consultationTime'" 
	   			value="''"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"/>
			<@ww.select 
	    		label="'是否回访'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'所有',
					'是',
					'否'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		</tr>
		<tr>
			<@ww.textarea label="'咨询内容'" name="'content'" value="''" rows="'3'" />
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("content").cols =width;
			</script>
		</tr>
		<tr>
			<@ww.textarea label="'备注'" name="'comment'" value="''" rows="'3'" />
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("comment").cols =width;
			</script>
		</tr>
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/CEInfoManager_/listCEInfo_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>