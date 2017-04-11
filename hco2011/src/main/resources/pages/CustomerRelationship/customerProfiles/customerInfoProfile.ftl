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

<#-- $Id: customerInfoProfile.ftl 2009-12-14 8:48:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('customerInfo.profile')}">
<@ww.form name="'listForm'" action="'saveCustomerInfo'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveCustomerInfoToken"/>
	<@ww.hidden name="'salesman.id'" value="'${req.getParameter('salesman.id')?if_exists}'"/>
	<@ww.hidden name="'salesmanName'" value="'${req.getParameter('salesmanName')?if_exists}'"/>
	<#if customerInfo.id?exists>
		<@ww.hidden name="'customerInfo.id'" value="#{customerInfo.id}"/>
	</#if>
	<@inputTable>
		<#--
		<tr>
			
			
			<@ww.textfield label="'${action.getText('customerInfo.abbreviations')}'" name="'customerInfo.abbreviations'" value="'${customerInfo.abbreviations?if_exists}'" cssClass="'underline'" />
		</tr>
		
		<tr>
			<@ww.textfield label="'${action.getText('customerInfo.fax')}'" name="'customerInfo.fax'" value="'${customerInfo.fax?if_exists}'" cssClass="'underline'" />
			
		</tr>
		<tr>
			
			<@ww.select label="'${action.getText('customerInfo.familiarityType')}'" 
				name="'familiarityType.id'" 
				value="'${req.getParameter('familiarityType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allFamiliarityTypes"
				required="true"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
			<script language="javascript">
		     	<#if customerInfo.familiarityType?exists>
		     		getObjByName('familiarityType.id').value = ${customerInfo.familiarityType.id};
		     	</#if>
			</script>
		</tr>
		-->
		<!--页面重新排版______________________________________________________________________-->
		<!--@@客户信息@@-->
	<tr>
		<!--客户编码-->
		<@ww.textfield label="'${action.getText('customerInfo.code')}'" name="'customerInfo.code'" value="'${customerInfo.code?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" />
		<!--客户姓名-->
		<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('customerInfo.name')}:</label>
	     	</td>
            <td>
	             <input type="text" name="customerInfo.name" value="${customerInfo.name?if_exists}" class="underline" onblur="toEditCustomer()" autocomplete="off">
	             <div id="show" style="background-color:#FFFFFF;border:1px solid; overflow:auto; display: none;z-index:2;position:absolute;left:43.7%;top:16%;"></div>
         </td>
	<tr>
		<!--企业性质-->
		<@ww.select label="'${action.getText('customerInfo.companyNature')}'" 
				name="'companyNature.id'" 
				value="'${req.getParameter('companyNature.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allCompanyNatures"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<!--行业-->
			<@ww.select label="'${action.getText('customerInfo.industry')}'" 
				name="'industry.id'" 
				value="'${req.getParameter('industry.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allIndustrys"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
	</tr>

	<tr>
		<!--企业法人-->
		<@ww.textfield label="'${action.getText('customerInfo.legalPerson')}'" name="'customerInfo.legalPerson'" value="'${customerInfo.legalPerson?if_exists}'" cssClass="'underline'" />
		<!--注册资本-->	
		<@ww.textfield label="'${action.getText('customerInfo.registeredCapital')}'" name="'customerInfo.registeredCapital'" value="'#{customerInfo.registeredCapital?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<!--员工人数-->
		<@ww.textfield label="'${action.getText('customerInfo.personCount')}'" name="'customerInfo.personCount'" value="'#{customerInfo.personCount?if_exists}'" cssClass="'underline'" />
		<!--创立日期-->
		<@pp.datePicker label="'${action.getText('customerInfo.setupTime')}'" 
				name="'customerInfo.setupTime'" 
	   			value="'${(customerInfo.setupTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				maxlength="10">
			</@pp.datePicker>
	</tr>
	<tr>
		<!--客户状态-->
		<@ww.select label="'${action.getText('customerInfo.type')}'" 
				name="'type.id'" 
				value="'${req.getParameter('type.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allTypess"
				required="true"
				disabled="fasle">
			</@ww.select>
		<!--客户等级-->
		<@ww.select label="'${action.getText('customerInfo.step')}'" 
				name="'step.id'" 
				value="'${req.getParameter('step.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allSteps"
				disabled="true">
			</@ww.select>
	</tr>
		<tr>
		<!--国家-->
		<@ww.select label="'${action.getText('customerInfo.country')}'" 
				name="'country.id'" 				
				id="country.id" 
				value="'${req.getParameter('country.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allCountrys"
				required="true"
				emptyOption="true" 
				disabled="false"
				onchange="'areaCascadeDWR(\"country.id\",\"province.id\",\"city.id\",1,\"${action.getText('')}\",\"edit\")'">
			</@ww.select>  
		<!--省份-->
		<@ww.select label="'${action.getText('customerInfo.province')}'" 
				name="'province.id'" 				
				id="province.id" 
				value="'${req.getParameter('province.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allProvince"
				required="true"
				emptyOption="true" 
				disabled="false"
				onchange="'areaCascadeDWR(\"country.id\",\"province.id\",\"city.id\",2,\"${action.getText('')}\",\"edit\")'">
			</@ww.select>
	</tr>
	<tr>
		<!--城市-->
		<@ww.select label="'${action.getText('customerInfo.city')}'" 
				name="'city.id'"				
				id="city.id" 
				value="'${req.getParameter('city.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allCity"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<!--存档日期-->
		<@pp.datePicker label="'${action.getText('customerInfo.archiveTime')}'" 
				name="'customerInfo.archiveTime'" 
	   			value="'${(customerInfo.archiveTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				maxlength="10">
			</@pp.datePicker>
			<script language="javascript">
			
				<#if customerInfo.archiveTime?exists>
				<#else>
					var date = new Date();
					if(getObjByName("customerInfo.archiveTime").value==''){
						getObjByName("customerInfo.archiveTime").value = date.format("yyyy-MM-dd");
					}
				</#if>
					<#if customerInfo.country?exists>
					getObjByName('country.id').value='${customerInfo.country.code?if_exists}';
				<#else>
					getObjByName('country.id').value='43';
				</#if>
				
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",1,"${action.getText('')}","edit");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
		   </script>
	</tr>
	<tr>
	 <!--公司网站-->
	    <@ww.textfield label="'${action.getText('customerInfo.web')}'" name="'customerInfo.web'" value="'${customerInfo.web?if_exists}'" cssClass="'underline'"  />
	 
	</tr>
		
	<tr>
		<!--地址-->
		<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('customerInfo.address')}:</label>
	     	</td>
			<td colspan="10">
				<input type="text" name="customerInfo.address" class="underline"  value="${customerInfo.address?if_exists}" maxlength="140" size="120" />
			</td>
		<!---->
	</tr>
	
	<tr>
		<!--企业简介-->
		<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('企业简介')}:</label>
	     	</td>
			<td colspan="10">
				<textarea name="customerInfo.businessScope" rows="3" cols="120" >${customerInfo.businessScope?if_exists}</textarea>
			</td>
			<#--
			<@ww.textarea label="'${action.getText('customerInfo.businessScope')}'" name="'customerInfo.businessScope'" value="'${customerInfo.businessScope?if_exists}'" rows="'3'" cols="'30'"/>
			-->
		<!---->
	</tr>
	 <tr><td colspan="8"><hr/></td></tr>
	 <!--@@联系人信息@@-->
	 <tr>
		<!--联系人姓名-->
		<@ww.textfield label="'${action.getText('customerInfo.keyContacter')}'" name="'customerInfo.keyContacter'" value="'${customerInfo.keyContacter?if_exists}'" cssClass="'underline'" required="true" />
		
		<!--性别-->
		<td align="right"><label for="" class="label">${action.getText('customerInfo.isOrNot')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="isOrNot0" name="isOrNot" value="0" />男
	        	<input type="radio" id="isOrNot1" name="isOrNot" value="1" />女
			</td>
		
	</tr>
	<tr>
		<!--部门-->
		<@ww.textfield label="'${action.getText('customerInfo.dept')}'" name="'customerInfo.dept'" value="'${customerInfo.dept?if_exists}'" cssClass="'underline'" />
       	<!--职位-->
       	<@ww.textfield label="'${action.getText('customerInfo.duty')}'" name="'customerInfo.duty'" value="'${customerInfo.duty?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<!--办公电话-->
		<@ww.textfield label="'${action.getText('customerInfo.telphone')}'" name="'customerInfo.telphone'" value="'${customerInfo.telphone?if_exists}'" cssClass="'underline'" required="false"/>
		<!--手机号码-->
		<@ww.textfield label="'${action.getText('customerInfo.mobilePhone')}'" name="'customerInfo.mobilePhone'" value="'${customerInfo.mobilePhone?if_exists}'" cssClass="'underline'" />
		
	</tr>
	<tr>
	    <!--传真-->
        <@ww.textfield label="'${action.getText('customerInfo.chuanzhen')}'" name="'customerInfo.chuanzhen'" value="'${customerInfo.chuanzhen?if_exists}'" cssClass="'underline'" />
		 <!--其他联系方式-->
		<@ww.textfield label="'${action.getText('customerInfo.qitalink')}'" name="'customerInfo.qitalink'" value="'${customerInfo.qitalink?if_exists}'" cssClass="'underline'" />
		
	</tr>
	<tr>
		<!--E-mail-->
		<@ww.textfield label="'${action.getText('customerInfo.email')}'" name="'customerInfo.email'" value="'${customerInfo.email?if_exists}'" cssClass="'underline'" />
		<!--QQ号码-->
		<@ww.textfield label="'${action.getText('customerInfo.qq')}'" name="'customerInfo.qq'" value="'${customerInfo.qq?if_exists}'" cssClass="'underline'" />
	</tr>
	
	<tr>
		<!--印象描述-->
		<td align="right" valign="top">
			<span class="required">*</span>
        		<label class="label">
        			${action.getText('customerInfo.enterpriseSynopsis')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="customerInfo.effectDescribe" rows="3" cols="120" >${customerInfo.effectDescribe?if_exists}</textarea>
	        </td>
		
		<!---->
	</tr>
	<tr><td colspan="8"><hr/></td></tr>
	<!--@@业务员记录@@-->
	<tr>
		<!--姓名-->
		<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('customerInfo.salesman')}:</label>
	     	</td>
	     	<td>
	     		<#if customerInfo.salesman?exists>
		   		<input type="text" name="customerInfo.salesman" class="underline"  value="${customerInfo.salesman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="customerInfo.salesman" class="underline"  value="${req.getParameter('salesmanName')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		<!--业务员部门-->
		<#--
		<@ww.select label="'${action.getText('customerInfo.departmentManager.dept')}'" 
				name="'dept.id'" 
				value="${req.getParameter('customerInfo.salesman.dept')?if_exists}"
				listKey="id"
				listValue="name"
				list="allDepts"
				emptyOption="true" 
				disabled="true">
			</@ww.select>
			-->
	<@ww.textfield label="'${action.getText('customerInfo.parlorDept')}'" name="'customerInfo.parlorDept'" value="'${customerInfo.parlorDept?if_exists}'" cssClass="'underline'"/>
		
	</tr>
	<tr>
		<!--信息来源-->
		<@ww.select label="'${action.getText('customerInfo.resource')}'" 
				name="'resource.id'" 
				value="'${req.getParameter('resource.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allResources"
				required="true"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
		<!--资料完整度-->
		<@textfield label="${action.getText('资料完整度')}" name="customerInfo.customerInfoIntegrity"  value="${customerInfo.customerInfoIntegrity?if_exists}" required="false" cssClass="underline" maxlength="20" readonly="true"/>		
	</tr>
	<tr>
		<!--咨询内容-->
		<td align="right" valign="top">
			<span class="required">*</span>
        		<label class="label">
        			${action.getText('customerInfo.advisoryContent')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="customerInfo.advisoryContent" rows="3" cols="120" >${customerInfo.advisoryContent?if_exists}</textarea>
	        </td>
	</tr>
	</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		
			<#-- 继续新建按钮   -->
			<#if customerInfo.id?exists>
			<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editCustomerInfo.html"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editCustomerInfo.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
			</#if>
		
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/customerRelationship/listCustomerInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
 


	window.onload = function () {
		<#if customerInfo.isOrNot?exists>
			<#if customerInfo.isOrNot=="0">
				getObjByName('isOrNot0').checked=true;
			<#else>
				getObjByName('isOrNot1').checked=true;
			</#if>
		<#else>
		    getObjByName('isOrNot0').checked=true;
		</#if>
		//getObjByName('show').style.width=getObjByName('customerInfo.name').offsetWidth;
		//客户类别
	    <#if customerInfo.customerType?exists>
			getObjByName('type.id').value='${customerInfo.customerType.id?if_exists}';
			<#elseif req.getParameter('type.id')?exists>
			getObjByName('type.id').value='${req.getParameter('type.id')}';
		</#if>
		//客户等级
	    <#if customerInfo.step?exists>
			getObjByName('step.id').value='${customerInfo.step.id?if_exists}';
			<#elseif req.getParameter('step.id')?exists>
			getObjByName('step.id').value='${req.getParameter('step.id')}';
		</#if>
			//信息来源
	    <#if customerInfo.resource?exists>
			getObjByName('resource.id').value='${customerInfo.resource.id?if_exists}';
			<#elseif req.getParameter('resource.id')?exists>
			getObjByName('resource.id').value='${req.getParameter('resource.id')}';
		</#if>
	    //行业
		<#if customerInfo.industry?exists>
			getObjByName('industry.id').value='${customerInfo.industry.id?if_exists}';
			<#elseif req.getParameter('industry.id')?exists>
			getObjByName('industry.id').value='${req.getParameter('industry.id')}';
		</#if>
	    //企业性质
		<#if customerInfo.companyNature?exists>
			getObjByName('companyNature.id').value='${customerInfo.companyNature.id?if_exists}';
			<#elseif req.getParameter('companyNature.id')?exists>
			getObjByName('companyNature.id').value='${req.getParameter('companyNature.id')}';
		</#if>
	    //国家
		<#if customerInfo.country?exists>
			getObjByName('country.id').value='${customerInfo.country.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",1,"${action.getText('')}","edit");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
			<#elseif req.getParameter('country.id')?exists>
			getObjByName('country.id').value='${req.getParameter('country.id')}';
			//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",1,"${action.getText('')}","edit");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
	    </#if>
	    //省份
		<#if customerInfo.province?exists>
			getObjByName('province.id').value='${customerInfo.province.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",2,"${action.getText('')}","edit");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
			<#elseif req.getParameter('province.id')?exists>
			getObjByName('province.id').value='${req.getParameter('province.id')}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",2,"${action.getText('')}","edit");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
	    </#if>
	    //城市
		<#if customerInfo.city?exists>
			getObjByName('city.id').value='${customerInfo.city.id?if_exists}';
			<#elseif req.getParameter('city.id')?exists>
			getObjByName('city.id').value='${req.getParameter('city.id')}';
		</#if>
	    //业务员
	    <#if customerInfo.salesman?exists>
			getObjByName('salesman.id').value='${customerInfo.salesman.id?if_exists}';
			<#elseif req.getParameter('salesman.id')?exists>
			getObjByName('salesman.id').value='${req.getParameter('salesman.id')}';
		</#if>
 	}
		
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["salesman.id"].value = result[0];
	   		document.forms[0].elements["customerInfo.salesman"].value = result[2];
	   		getObjByName('salesmanName').value=result[2];
	   		document.forms[0].elements["customerInfo.parlorDept"].value = result[9];
		}
	}
	
	//验证字段
	function storeValidation(){
		if(getObjByName('show').style.display == "block"){
			return false;
		}
		if(getObjByName('customerInfo.name').value != ''){
			var obj = getObjByName('customerInfo.name').value;
			var re1 = /\d/;
		 	var re2 = /\w/;
		 	var re3 = /[\u4e00-\u9fa5]/;
		 	for(var i=0; i<obj.length; i++){
		 		if(!re2.test(obj.charAt(i)) && !re3.test(obj.charAt(i))){
		 			alert("请不要输入特殊字符");
		 			getObjByName('customerInfo.name').value="";
					getObjByName('customerInfo.name').focus();
		 			return false;
		 		}
		 	}
		}
		if(getObjByName('customerInfo.name').value==''){
		    alert('${action.getText('customerInfo.name.not.null')}');
		    getObjByName('customerInfo.name').focus();
		    return false;
		 }else{
		    if(!isValidLength(document.forms[0], "customerInfo.name", null, 20)){
				alert("${action.getText('customerInfo.name.length')}");
						getObjByName('customerInfo.name').value="";
						getObjByName('customerInfo.name').focus();
				return false;
			}
		}
		//if(getObjByName('customerInfo.abbreviations').value !=''){
	     //   if(!isValidLength(document.forms[0], "customerInfo.abbreviations", null, 20)){
			//	alert("${action.getText('customerInfo.abbreviations.length')}");
			//	getObjByName('customerInfo.abbreviations').value="";
			//	getObjByName('customerInfo.abbreviations').focus();
			//	return false;
			//}
		//}
	
		if(getObjByName('type.id').value==""){
		    alert('${action.getText('customerInfo.type.not.null')}');
		    getObjByName('type.id').focus();
		    return false;
		}
		
	    if(getObjByName('industry.id').value==""){
	        alert('${action.getText('customerInfo.industry.not.null')}');
	        getObjByName('industry.id').focus();
	        return false;
	    }
	    if(getObjByName('companyNature.id').value==""){
	        alert('${action.getText('customerInfo.companyNature.not.null')}');
	        getObjByName('companyNature.id').focus();
	        return false;
	    }
	    if(getObjByName('country.id').value==""){
	        alert('${action.getText('customerInfo.country.not.null')}');
	         getObjByName('country.id').focus();
	        return false;
	    }
	    if(getObjByName('province.id').value=="-1"){
	        alert('${action.getText('customerInfo.province.not.null')}');
	        getObjByName('province.id').focus();
	        return false;
	    }
	    <#--
	    if(getObjByName('city.id').value==""){
	        alert('${action.getText('customerInfo.city.not.null')}');
	        return false;
	    }
	    -->

	    if(getObjByName('customerInfo.legalPerson').value !=''){
	        if(!isValidLength(document.forms[0], "customerInfo.legalPerson", null, 20)){
				alert("${action.getText('customerInfo.legalPerson.length')}");
				getObjByName('customerInfo.legalPerson').value="";
				getObjByName('customerInfo.legalPerson').focus();
				return false;
			}
		}
		if(getObjByName('customerInfo.registeredCapital').value !=''){
			if(!isDoubleNumberBetweenBoolean(formatDigital(getObjByName('customerInfo.registeredCapital').value), 1000000001, -1)){
				alert('${action.getText('customerInfo.registeredCapital.format')}');
				getObjByName('customerInfo.registeredCapital').value="";
				getObjByName('customerInfo.registeredCapital').focus();
				return false;
			}
		}
		if(getObjByName('customerInfo.personCount').value !=''){
			if(!isDoubleNumberBetweenBoolean(formatDigital(getObjByName('customerInfo.personCount').value), 1000000001, -1)){
				alert('${action.getText('customerInfo.personCount.format')}');
				getObjByName('customerInfo.personCount').value="";
				getObjByName('customerInfo.personCount').focus();
				return false;
			}
		}
		//创立日期
		if(getObjByName('customerInfo.setupTime').value !=''){
			if(!isDate('customerInfo.setupTime')){
				alert("${action.getText('customerInfo.setupTime.type')}");
				getObjByName('customerInfo.setupTime').value="";
				getObjByName('customerInfo.setupTime').focus();
				return false;
			}
			
		}
		//存档日期
		if(getObjByName('customerInfo.archiveTime').value !=''){
			if(!isDate('customerInfo.archiveTime')){
				alert("${action.getText('customerInfo.archiveTime.type')}");
				getObjByName('customerInfo.archiveTime').value="";
				getObjByName('customerInfo.archiveTime').focus();
				return false;
			}
			
		}
		
		<#-- 办公电话 13 zsz 2010.03.23-->
		if(getObjByName('customerInfo.telphone').value == '' && getObjByName('customerInfo.mobilePhone').value == ''){
			alert('${action.getText('advisory.officePhoneOrMobile.not.null')}');
			getObjByName('customerInfo.telphone').focus();
        	return false;
		}
        
        if(getObjByName('customerInfo.telphone').value != '')
        {
		   var str = getObjByName('customerInfo.telphone').value
	       if(str.length>20){
	       		alert('${action.getText('customerInfo.telphone.error')}');
	       		getObjByName('customerInfo.telphone').value="";
	       		getObjByName('customerInfo.telphone').focus();
	        	return false;
	       }
		}
		<#-- 手机 14 zsz 2010.03.23 -->
		if(getObjByName('customerInfo.mobilePhone').value != ''){
		   var str = getObjByName('customerInfo.mobilePhone').value
	       if(str.length>20){
	       		alert('${action.getText('customerInfo.mobilePhone.error')}');
	       		getObjByName('customerInfo.mobilePhone').value="";
	       		getObjByName('customerInfo.mobilePhone').focus();
	       		return false;
	       }
		}
		<#-- 印象描述-->
	    if(getObjByName('customerInfo.effectDescribe').value==""){
			alert('${action.getText('customerInfo.enterpriseSynopsis.not.null')}');
			getObjByName('customerInfo.effectDescribe').focus();
			return false;
		}
		<#-- 咨询内容 23 -->
	    if(getObjByName('customerInfo.advisoryContent').value==""){
			alert('${action.getText('customerInfo.advisoryContent.not.null')}');
			getObjByName('customerInfo.advisoryContent').focus();
			return false;
		}
	    if(getObjByName('customerInfo.address').value !=''){
	        if(!isValidLength(document.forms[0], "customerInfo.address", null, 50)){
				alert("${action.getText('customerInfo.address.length')}");
				getObjByName('customerInfo.address').value="";
				getObjByName('customerInfo.address').focus();
				return false;
			}
		}
	    if(getObjByName('customerInfo.keyContacter').value==''){
		    alert('${action.getText('customerInfo.keyContacter.not.null')}');
		    getObjByName('customerInfo.keyContacter').focus();
		    return false;
		 }else{
		    if(!isValidLength(document.forms[0], "customerInfo.keyContacter", null, 20)){
				alert("${action.getText('customerInfo.keyContacter.length')}");
				getObjByName('customerInfo.keyContacter').value="";
				getObjByName('customerInfo.keyContacter').focus();
				return false;
			}
		}
		<!--zsz 2010.03.23 begin-->
		if(getObjByName('customerInfo.telphone').value==''){
	     }else{
	     	if(!isValidLength(document.forms[0], "customerInfo.telphone", null, 20)){
            	alert('${action.getText('customerInfo.telphone.length')}');
            	getObjByName('customerInfo.telphone').focus();
	        	return false;
            }
		}
		if(getObjByName('customerInfo.mobilePhone').value!==""){
	        if(!isValidLength(document.forms[0], "customerInfo.mobilePhone", null, 20)){
            	alert('${action.getText('customerInfo.mobilePhone.length')}');
            	getObjByName('customerInfo.mobilePhone').value="";
            	getObjByName('customerInfo.mobilePhone').focus();
	        	return false;
            }
	     }
	     <!--zsz 2010.03.23 end-->
	    //if(getObjByName('customerInfo.fax').value!=""){
	       // if(!isValidLength(document.forms[0], "customerInfo.fax", null, 20)){
            //	alert('${action.getText('customerInfo.fax.length')}');
            //	getObjByName('customerInfo.fax').value="";
            //	getObjByName('customerInfo.fax').focus();
	        //	return false;
           // }
	    // }
	     if(getObjByName('customerInfo.email').value!=""){
	        if (!getObjByName('customerInfo.email').value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.org)|(\.info)|(\.edu)|(\.mil)|(\.gov)|(\.biz)|(\.ws)|(\.us)|(\.tv)|(\.cc)|(\..{2,2}))$)\b/gi)) {
          		alert('${action.getText('customerInfo.email.type')}');
          		getObjByName('customerInfo.email').value="";
          		getObjByName('customerInfo.email').focus();
          		return false;
          	}else{
	     		if(!isValidLength(document.forms[0], "customerInfo.email", null, 50)){
            		alert('${action.getText('contactArchives.email.length')}');
            		getObjByName('customerInfo.email').value="";
          			getObjByName('customerInfo.email').focus();
	        		return false;
	        	}
            }
	     }
	    if(getObjByName('customerInfo.salesman').value==""){
	        alert('${action.getText('customerInfo.salesman.not.null')}');
	        return false;
	    }
	     
	     if(getObjByName('customerInfo.qq').value!=""){
     		if(!isValidLength(document.forms[0], "customerInfo.qq", null, 20)){
        		alert('${action.getText('customerInfo.qq.length')}');
        		getObjByName('customerInfo.qq').value="";
        		getObjByName('customerInfo.qq').focus();
        		return false;
        	}
	     }
	     if(getObjByName('customerInfo.businessScope').value!=""){
	        if(!isValidLength(document.forms[0], "customerInfo.businessScope", null, 500)){
            	alert('${action.getText('customerInfo.businessScope.length')}');
            	getObjByName('customerInfo.businessScope').value="";
            	getObjByName('customerInfo.businessScope').focus();
	        	return false;
            }
	     }
	     return true;
	}
</script>

<script language="javascript">
		var lastInfo = "";
		var selectflag = 0;


       function toEditCustomer(){
       var obj = getObjByName('customerInfo.name').value;
	 	if(obj != "" && obj != null && obj){
			CustomerList.getOneCustomerByName(obj,
				{
					callback:function(data){
						if(data.length > 0){
							 if(confirm("您所输入的客户名称已经存在，是否需要跳转到编辑页面?")){
							document.location='${req.contextPath}/customerRelationship/editCustomerInfo.html?customerInfo.id='+data[0]["id"]+'&readOnly=${req.getParameter('readOnly')?if_exists}';
							 }
						}
					}			
				}
			);
	 	}
       
       } 
	 
	 function showCustomer(){
	 	var obj = getObjByName('customerInfo.name').value;
	 	if(obj != "" && obj != null && obj != lastInfo){
	 		getObjByName('industry.id').style.display="none";
			getObjByName('province.id').style.display="none";
			CustomerList.getCustomerList(
				obj,
				{
					callback:function(data){
						if(data.length > 0){
							selectflag = data.length;
							getObjByName('show').innerHTML = "";
							var topTag = document.createElement("input");
							topTag.setAttribute("type","text");
							topTag.setAttribute("id",-1);
							topTag.setAttribute("value","以下名称已存在");
							topTag.setAttribute("size","20");
							topTag.setAttribute("readOnly","readOnly");
							topTag.style.border = "0px";
							topTag.style.color = "red";
							getObjByName('show').appendChild(topTag);
							for(var i=0; i<data.length; i++){
								var tag = document.createElement("input");
								tag.setAttribute("type","text");
								var id = "option" + (i+1);
								tag.setAttribute("id",id);
							    if(data[i]["name"].length>8){
							      tag.setAttribute("value",data[i]["name"].substring(0,8)+"...");
							    }else{
							      tag.setAttribute("value",data[i]["name"]);
							    }
							    tag.setAttribute("title",data[i]["name"]);
								tag.setAttribute("size","20");
								tag.setAttribute("readOnly","readOnly");
								tag.style.border = "0px";
								tag.onmouseover=function(){
									mouseON(this);
								};
								tag.onmouseout=function(){
									mouseOUT(this);
								};
								getObjByName('show').appendChild(tag);
								getObjByName('show').appendChild(document.createElement('br'));
							}
							getObjByName('show').style.display = "block";
							var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
							if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {		
							 //if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {							 
								var textRange =getObjByName('customerInfo.name').createTextRange();//建立文本域
								textRange.moveStart('character',getObjByName('customerInfo.name').value.length);//获取文本域右侧文本
								textRange.collapse(true);//瓦解文本域
								textRange.select();
							 } else {
								getObjByName('customerInfo.name').setSelectionRange(getObjByName('customerInfo.name').value.length, getObjByName('customerInfo.name').value.length); 
								getObjByName('customerInfo.name').focus(); 
							 }

						}else{
							getObjByName('show').style.display = "none";
							getObjByName('industry.id').style.display="block";
	     					getObjByName('province.id').style.display="block";
						}
					}			
				}
			);
			lastInfo = obj;
		}else if(obj != "" && obj != null && obj == lastInfo){
			
		}else if(obj == "" || obj == null){
			getObjByName('show').style.display = "none";
			getObjByName('industry.id').style.display="block";
	     	getObjByName('province.id').style.display="block";
			lastInfo = "";
		}
	 }
	 
	 /**
	  * 鼠标移上焦点时触发事件
	  * 将焦点内容选中
	  */
	function mouseON(op){
		op.value = op.title;
		DWRUtil.selectRange(op.id, 0, op.size);
	}
	/**
	  * 鼠标移出焦点时触发事件
	  * 将焦点内容还原
	  */
	function mouseOUT(op){
		if(op.value.length>8){
			op.value = op.value.substring(0,8)+"...";
		}
	}
	/**
	 * 键盘上下键事件
	 */
	function myMethod(event){
		//alert(event.altKey);
		//alert(event.keyCode);
		
		if(event.keyCode==38){
			if(getObjByName('show').style.display == "block"){
				for(var i=selectflag; i>0; i--){
					var logo = 'option' + i;
					if(document.activeElement.id==logo){
						if(getObjByName(logo).value.length>8){
							getObjByName(logo).value = getObjByName(logo).value.substring(0,8)+"...";
						}
						var nextLogo = 'option' + (i-1);
						if(getObjByName(nextLogo) != null){
							getObjByName(nextLogo).value = getObjByName(nextLogo).title;
							DWRUtil.selectRange(nextLogo, 0, 20);
						}
						return;
					}
				}
				DWRUtil.selectRange('option1', 0, 10);
			}
		}else if(event.keyCode==40){
			if(getObjByName('show').style.display == "block"){
				for(var i=1; i<selectflag+1; i++){
					var logo = 'option' + i;
					if(document.activeElement.id==logo){
						if(getObjByName(logo).value.length>8){
							getObjByName(logo).value = getObjByName(logo).value.substring(0,8)+"...";
						}
						var nextLogo = 'option' + (i+1);
						if(getObjByName(nextLogo) != null){
							getObjByName(nextLogo).value = getObjByName(nextLogo).title;
							DWRUtil.selectRange(nextLogo, 0, 20);
						}
						return;
					}
				}
				DWRUtil.selectRange('option1', 0, 10);
			}
		}
	}
	//document.onkeyup = myMethod;

	 /**
  	 * 页面点击事件源
  	 */
  	document.onclick = function(event){
   		checkdiv(event);
	}
	
	/**
	 * 源默认触发事件
	 */
	function checkdiv(event){
	    var event  = window.event || event;
	    var obj = event.srcElement ? event.srcElement : event.target;
	    if(obj){
			getObjByName('show').style.display = "none";
			getObjByName('industry.id').style.display="block";
	     	getObjByName('province.id').style.display="block";
	    }
	}
</script>

</@htmlPage>
<#if customerInfo.id?exists>
<ul id="beautytab">
	<li>
		<a id="contactArchives" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/customerRelationship/listCA.html?customerInfo.id=#{customerInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('customerInfo.contactArchives')}</a>
	</li>
	<li>
		<a id="additionalInfo" onclick="activeTab(this);" href='${req.contextPath}/customerRelationship/editAdditionalInfo.html?customerInfo.id=#{customerInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('customerInfo.additionalInfo')}</a>
	</li>
	<li>
		<a id="backvisit" onclick="activeTab(this);" href='${req.contextPath}/backvisit/listBackVisitByContact.html?customerInfo.id=#{customerInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('customerInfo.backvisit')}</a>
	</li>
	<li>
		<a id="contractInfo" onclick="activeTab(this);" href='${req.contextPath}/contractManagement/listContractManagementByCustomerAction.html?customerInfo.id=#{customerInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >合同信息</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/customerRelationship/listCA.html?customerInfo.id=#{customerInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>
