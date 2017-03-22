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
	<@ww.form name="'listForm'" action="'editConArchInfo_'" method="'post'">
		<@ww.token name="editConArchInfoToken"/>
		<@inputTable>
			<tr>
				<@ww.textfield label="'姓名'" name="'name'" value="'张凤仙'" cssClass="'underline'" required="true"/>
				<@ww.select 
		    		label="'性别'"
					required="false"
					name="'sex'" 
					value="selectedType" 
					headerKey="1"
					headerValue="selectedType"
				    list="{
						'男',
						'女'
						}"
			    	emptyOption="false" 
			    	disabled="false"/>
				<td align="right" valign="top">
		       		<span class="required">*</span>
		       		<label class="label">客户名称:</label>
	     		</td>
	     		<td>
			   		 <input type="text" name="customerName" 
				      class="underline"  value="合肥矿物集团"  maxlength="140" size="20" />
			       	 <a onClick="scoreCustomer_OpenDialog();">
		    	     	<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0"/>
	    	  		 </a>
        		</td>
				
			</tr>
			<tr>
				<@ww.textfield label="'职务'" name="'duty'" value="'程序员'" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'办公电话'" name="'telphone'" value="'0551-4588766'" cssClass="'underline'" required="false"/>
				<@ww.select 
		    		label="'部门'"
					required="false"
					name="'department'" 
					value="selectedType" 
					headerKey="1"
					headerValue="selectedType"
				    list="{
						'研发部',
						'市场部'
						}"
			    	emptyOption="false" 
			    	disabled="false"/>
			</tr>
			<tr>
				<@ww.textfield label="'家庭电话'" name="'homePhone'" value="'0551-7855546'" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'传真'" name="'fax'" value="'444455557'" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'QQ号码'" name="'qq'" value="'490499428'" cssClass="'underline'" required="false"/>
			</tr>
			<tr>
				<@ww.textfield label="'MSN号码'" name="'msn'" value="'154521422'" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'Email'" name="'email'" value="'490499428@qq.com'" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'手机'" name="'mobilePhone'" value="'1590561678'" cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'民族'" name="'national'" value="'汉'" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'学校'" name="'school'" value="'铁四局中学'" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'专业'" name="'professional'" value="'软件开发'" cssClass="'underline'" required="false"/>
			</tr>
			<tr>
				<@ww.textfield label="'兴趣'" name="'interests'" value="'打篮球'" cssClass="'underline'" required="false"/>
				<@ww.select 
		    		label="'性格'"
					required="false"
					name="'mettle'" 
					value="selectedType" 
					headerKey="1"
					headerValue="selectedType"
				    list="{
						'内向',
						'外向'
						}"
			    	emptyOption="false" 
			    	disabled="false"/>
				<@pp.datePicker 
					label="'生日'" 
					name="'birth'" 
		   			value="'2007-05-09'"
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
						'熟悉',
						'陌生'
						}"
				    emptyOption="false" 
				    disabled="false"/>
				 
				<@ww.textfield label="'邮编'" name="'postCode'" value="'236587'" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'家庭住址'" name="'address'" value="'合肥铁四局'" cssClass="'underline'" required="false"/>
			</tr>
			<tr>
				<@ww.select 
		    		label="'籍贯'"
					required="false"
					name="'mettle'" 
					value="selectedType" 
					headerKey="1"
					headerValue="selectedType"
				    list="{
						'安徽省',
						'江苏省'
						}"
			    	emptyOption="false" 
			    	disabled="false"/>
				<@ww.textarea label="'备注'" name="'comment'" value="'本人甚好'" rows="'3'" cols="'30'"/>
			</tr>
		</@inputTable>
		<@buttonBar>
			<@vsubmit name="'save'" value="'${'保存'}'"/>
			<@redirectButton value="${'返回'}" url="${req.contextPath}/contactArchivesManager/listContactArchivesInfo_.html"/>
    	</@buttonBar>
    </@ww.form>
<script type="text/javascript">
    function scoreCustomer_OpenDialog(){
	   var url = "${req.contextPath}/contactArchivesManager/selectCustomer_.html";
	   popupModalDialog(url, 800, 600);
	 }
</script>
</@htmlPage>