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
<#-- $Id: contactProfile.ftl  2009-11-26 12:40:50Z wliu $-->

<#include "../../../includes/macros.ftl" />

<@htmlPage title="联系人信息维护">
	<@ww.form name="'listForm'" action="'saveContactInfo_'" method="'post'">
		<@ww.token name="saveContactInfoToken"/>
		<@inputTable>
			<tr>
				<@ww.textfield label="'姓名'" name="'name'" value="''" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'性别'" name="'sex'" value="''" cssClass="'underline'" required="true"/>
				<@ww.select 
		    		label="'部门'"
					required="false"
					name="'department'" 
					value="selectedType" 
					headerKey="1"
					headerValue="selectedType"
				    list="{
				    	'',
						'研发部',
						'市场部'
						}"
			    	emptyOption="false" 
			    	disabled="false"/>
			</tr>
			<tr>
				<@ww.textfield label="'职务'" name="'duty'" value="''" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'办公电话'" name="'telphone'" value="''" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'手机'" name="'mobilePhone'" value="''" cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'家庭电话'" name="'homePhone'" value="''" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'传真'" name="'fax'" value="''" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'QQ号码'" name="'qq'" value="''" cssClass="'underline'" required="false"/>
			</tr>
			<tr>
				<@ww.textfield label="'MSN号码'" name="'msn'" value="''" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'Email'" name="'email'" value="''" cssClass="'underline'" required="false"/>
				<@ww.select 
		    		label="'籍贯'"
					required="false"
					name="'mettle'" 
					value="selectedType" 
					headerKey="1"
					headerValue="selectedType"
				    list="{
				    	'',
						'安徽省',
						'江苏省'
						}"
			    	emptyOption="false" 
			    	disabled="false"/>
			</tr>
			<tr>
				<@ww.textfield label="'民族'" name="'national'" value="''" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'学校'" name="'school'" value="''" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'专业'" name="'professional'" value="''" cssClass="'underline'" required="false"/>
			</tr>
			<tr>
				<@ww.textfield label="'兴趣'" name="'interests'" value="''" cssClass="'underline'" required="false"/>
				<@ww.select 
		    		label="'性格'"
					required="false"
					name="'mettle'" 
					value="selectedType" 
					headerKey="1"
					headerValue="selectedType"
				    list="{
				    	'',
						'内向',
						'外向'
						}"
			    	emptyOption="false" 
			    	disabled="false"/>
				<@pp.datePicker 
					label="'生日'" 
					name="'birth'" 
		   			value="'${req.getParameter('birth')?if_exists}'"
					cssClass="'underline'" 
					dateFormat="'%Y-%m-%d'"/>
			</tr>
			<tr>
				<@ww.select 
		    		label="'类型'"
					required="true"
					name="'type'" 
					value="selectedType" 
					headerKey="1"
					headerValue="selectedType"
				    list="{
				    	'',
						'熟悉',
						'陌生'
						}"
				    emptyOption="false" 
				    disabled="false"/>
				 
				<@ww.textfield label="'邮编'" name="'postCode'" value="''" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'家庭住址'" name="'address'" value="''" cssClass="'underline'" required="false"/>
			</tr>
			<tr>
				<@ww.textarea label="'备注'" name="'comment'" value="''" rows="'3'" cols="'30'"/>
			</tr>
		</@inputTable>
		<@buttonBar>
			<@vsubmit name="'save'" value="'${'保存'}'"/>
			<@vbutton name="'back'"  class="button" value="${'返回'}" onclick="javascript:window.close();"/>
    	</@buttonBar>
    </@ww.form>
</@htmlPage>
