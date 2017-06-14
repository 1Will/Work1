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

<#-- $Id: productProfile.ftl 2009-12-15 11:09:35Z mfzhnag $ -->
<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('supplierProfile.title')}">
	<@ww.form namespace="'/supplierManager'" name="'listForm'" action="'saveSupplierAction'" method="'post'">
		<@ww.token name="saveSupplierActionToken"/>
		<@inputTable>
			<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<#if supplier.id?exists>
                <@ww.hidden name="'supplier.id'" value="#{supplier.id}"/>
            </#if>
            <@ww.hidden name="'supplier.supplierTypeId'" value="'${req.getParameter('supplier.supplierTypeId')?if_exists}'"/>
            <@ww.hidden name="'supplier.tradeTypeId'" value="'${req.getParameter('supplier.tradeTypeId')?if_exists}'"/>
            <@ww.hidden name="'supplier.companyTypeId'" value="'${req.getParameter('supplier.companyTypeId')?if_exists}'"/>
            <@ww.hidden name="'supplier.countryId'" value="'${req.getParameter('supplier.countryId')?if_exists}'"/>
            <@ww.hidden name="'supplier.provinceId'" value="'${req.getParameter('supplier.provinceId')?if_exists}'"/>
            <@ww.hidden name="'supplier.cityId'" value="'${req.getParameter('supplier.cityId')?if_exists}'"/>
            
			<tr>
				<@ww.textfield label="'${action.getText('supplier.supplierNo')}'" name="'supplier.supplierNo'" 
							   value="'${supplier.supplierNo?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>			
				<@ww.textfield label="'${action.getText('supplier.name')}'" name="'supplier.name'" 
							   value="'${supplier.name?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('supplier.enName')}'" name="'supplier.enName'" 
							   value="'${supplier.enName?if_exists}'" cssClass="'underline'"/>
			</tr>
			<tr>
				<@ww.select label="'${action.getText('supplier.supplierType')}'"
					required="true"
					name="'supplier.supplierType'" 
					value="'${req.getParameter('supplier.supplierType')?if_exists}}'" 
					listKey="id"
					listValue="name"
				    list="allSupplierType"
			    	emptyOption="false" 
			    	disabled="false"/>	
				<@ww.select 
		    		label="'${action.getText('supplier.tradeType')}'"
					required="true"
					name="'supplier.tradeType'" 
					value="'${req.getParameter('supplier.tradeType')?if_exists}'" 
					listKey="id"
					listValue="name"
				    list="allTradeType"
			    	emptyOption="false" 
			    	disabled="false"/>
				<@ww.select label="'${action.getText('supplier.companyType')}'"
					required="true"
					name="'supplier.companyType'" 
					value="'${req.getParameter('supplier.companyType')?if_exists}'" 
					listKey="id"
					listValue="name"
				    list="allCompanyType"
			    	emptyOption="false" 
			    	disabled="false"/>			
			</tr>
			<tr>
				<@ww.select label="'${action.getText('supplier.country')}'"
					required="true"
					name="'supplier.country'" 
					value="'${req.getParameter('supplier.country')?if_exists}'" 
					listKey="id"
					listValue="name"
				    list="allCountry"
			    	emptyOption="false" 
			    	disabled="false"
			    	onchange="'areaCascadeDWR(\"supplier.country\",\"supplier.province\",\"supplier.city\",1,\"${action.getText('')}\",\"edit\")'"/>
				<@ww.select 
		    		label="'${action.getText('supplier.province')}'"
					required="true"
					name="'supplier.province'" 
					value="'${req.getParameter('supplier.province')?if_exists}'" 
					listKey="id"
					listValue="name"
				    list="allProvince"
			    	emptyOption="false" 
			    	disabled="false"
			    	onchange="'areaCascadeDWR(\"supplier.country\",\"supplier.province\",\"supplier.city\",2,\"${action.getText('')}\",\"edit\")'"/>
				<@ww.select label="'${action.getText('supplier.city')}'"
					required="false"
					name="'supplier.city'" 
					value="'${req.getParameter('supplier.city')?if_exists}'" 
					listKey="id"
					listValue="name"
				    list="allCity"
			    	emptyOption="true" 
			    	disabled="false"/>	
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('supplier.legalPerson')}'" name="'supplier.legalPerson'" 
							   value="'${supplier.legalPerson?if_exists}'" cssClass="'underline'"/>
				<@textfield label="${action.getText('supplier.registeredFunds')}" name="supplier.registeredFunds" anothername="registeredFunds" value="#{supplier.registeredFunds?if_exists}" required="false"/> 
				<@textfield label="${action.getText('supplier.employeeNum')}" name="supplier.employeeNum" anothername="employeeNum" value="#{supplier.employeeNum?if_exists}" required="false"/> 
			</tr>
			<tr>
	        	<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('supplier.ManagingScope')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="supplier.ManagingScope" rows="4" cols="150" >${supplier.managingScope?if_exists}</textarea>
		        </td>
		    </tr>
		</@inputTable>
		<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'">
            </@vsubmit>
        </#if>
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/supplierManager/listSupplier.html"/>
	</@buttonBar>
	</@ww.form>
<script language="javascript">
 	window.onload = function () {
 	<#if supplier.id?exists >
		 var url = 'editAdditionInfo.html?supplier.id=#{supplier.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
	     document.all.frame.src = url;
//		 document.getElementById("editAdditionInfo").className = "selectedtab";
 	</#if>
 	
		//国家
		<#if supplier.country?exists>
			getObjByName('supplier.country').value='${supplier.country.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("supplier.country","supplier.province","supplier.city",1,"${action.getText('')}","edit");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
	    </#if>
	    //省份
		<#if supplier.province?exists>
			getObjByName('supplier.province').value='${supplier.province.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("supplier.country","supplier.province","supplier.city",2,"${action.getText('')}","edit");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
	    </#if>
	    //城市
		<#if supplier.city?exists>
			getObjByName('supplier.city').value='${supplier.city.id?if_exists}';
		</#if> 	
	 }
 
 	<#if supplier.supplierType?exists>
 		getObjByName('supplier.supplierType').value = ${supplier.supplierType.id};
 	</#if>
 	<#if supplier.tradeType?exists>
 		getObjByName('supplier.tradeType').value = ${supplier.tradeType.id};
 	</#if>
  	<#if supplier.companyType?exists>
 		getObjByName('supplier.companyType').value = ${supplier.companyType.id};
 	</#if>
  	<#if supplier.supplierType?exists>
 		getObjByName('supplier.supplierType').value = ${supplier.supplierType.id};
 	</#if>	
 	<#-- 重复提交时候保存值
 	if(0 == getObjByName('supplier.supplierType').value){
 		getObjByName('supplier.supplierType').value = getObjByName('supplier.supplierTypeId').value;
 	}
 	if(0 == getObjByName('supplier.tradeType').value){
 		getObjByName('supplier.tradeType').value = getObjByName('supplier.tradeTypeId').value;
 	}
 	if(0 == getObjByName('supplier.companyType').value){
 		getObjByName('supplier.companyType').value = getObjByName('supplier.companyTypeId').value;
 	}
 	if(0 == getObjByName('supplier.country').value){
 		getObjByName('supplier.country').value = getObjByName('supplier.countryId').value;
 	}
  	if('' == getObjByName('supplier.province').value){
 		getObjByName('supplier.province').value = getObjByName('supplier.provinceId').value;
 	}
  	if(0 == getObjByName('supplier.city').value){
 		getObjByName('supplier.city').value = getObjByName('supplier.cityId').value;
 	}
 	-->
 function storeValidation(){
 	//供应商编号
 	<#--
		if(getObjByName('supplier.supplierNo').value == ""){
			alert('${action.getText('supplier.supplierNo.notnull')}');
			return false;
		}
		if(!isValidLength(document.forms[0], "supplier.supplierNo", null, 20)){
			alert('${action.getText('supplier.supplierNo.length')}');
			return  false;
		}
		-->
	//供应商姓名（中文）
	    var name = getObjByName('supplier.name').value;
		if(name==""){
			alert('${action.getText('supplier.name.notnull')}');
			getObjByName('supplier.name').focus()
			return false;
		}else{
			if(!isValidLength(document.forms[0], "supplier.name", null, 50)){
				alert('${action.getText('supplier.name.length')}');
				return  false;
			}
            var regu = "^[\u4e00-\u9fa5]+$";
            var re = new RegExp(regu);
            if (name.search(re) == -1) {
              alert("${action.getText("user.name.Invalid")}");
              getObjByName('supplier.name').value=""
              getObjByName('supplier.name').focus()
              return false;
            } 
		}
	//供应商姓名（英文）
	<#--
		if(getObjByName('supplier.enName').value==""){
			alert('${action.getText('supplier.enName.notnull')}');
			return false;
		}
	-->
        var name = getObjByName('supplier.enName').value;
        if(name!=''){
	        var regu = "^\\w+$" ;
	        var re = new RegExp(regu);
	        if (name.search(re) == -1) {
	       	  alert("${action.getText("supplier.enName.Invalid")}");
	       	  getObjByName('supplier.enName').value=""
	       	  getObjByName('supplier.enName').focus();
	          return false;
	        }
			if(!isValidLength(document.forms[0], "supplier.enName", null, 50)){
				alert('${action.getText('supplier.enName.length')}');
				getObjByName('supplier.enName').value=""
	       	    getObjByName('supplier.enName').focus();
				return  false;
			}	
		}
	//供应商类别
		if(getObjByName('supplier.supplierType').value == 0){
			alert('${action.getText('supplier.supplierType.notnull')}');
			getObjByName('supplier.supplierType').focus();
			return false;
		}	
	//行业
		if(getObjByName('supplier.tradeType').value==0){
			alert('${action.getText('supplier.tradeType.notnull')}');
			getObjByName('supplier.tradeType').focus();
			return false;
		}
	//公司性质
		if(getObjByName('supplier.companyType').value==0){
			alert('${action.getText('supplier.companyType.notnull')}');
			getObjByName('supplier.companyType').focus()
			return false;
		}
	//国家
		if(getObjByName('supplier.country').value==0){
			alert('${action.getText('supplier.country.notnull')}');
			getObjByName('supplier.country').focus();
			return false;
		}	
	//省份
		if(getObjByName('supplier.province').value==-1){
			alert('${action.getText('supplier.province.notnull')}');
			getObjByName('supplier.province').focus();
			return false;
		}	
	//城市
	<#--
		if(getObjByName('supplier.city').value==0){
			alert('${action.getText('supplier.city.notnull')}');
			return false;
		}
	-->
	//法人代表
		if(!isValidLength(document.forms[0], "supplier.legalPerson", null, 20)&& !getObjByName('supplier.legalPerson').value==''){
			alert('${action.getText('supplier.legalPerson.length')}');
			getObjByName('supplier.legalPerson').value="";
			getObjByName('supplier.legalPerson').focus();
			return  false;
		}	
	//注册资金
	if( !getObjByName('supplier.registeredFunds').value == ''){
		if(isNaN(formatDigital(getObjByName('supplier.registeredFunds').value)) ){
				alert('${action.getText('supplier.registeredFunds.format')}');
				getObjByName('supplier.registeredFunds').value="";
				getObjByName('supplier.registeredFunds').focus();
				return  false;
		}else {
			if(!isDoubleNumberBetweenBoolean(formatDigital(getObjByName('supplier.registeredFunds').value), 1000000001, -1)){
				alert('${action.getText('supplier.registeredFunds.format')}');
				getObjByName('supplier.registeredFunds').value="";
				getObjByName('supplier.registeredFunds').focus();
				return false;
			}
		}
	}
	//员工人数
        if(!textfieldCheck_employeeNum()){
			getObjByName('supplier.employeeNum').focus();
			return false;
		}
	//经营范围
		if(!isValidLength(document.forms[0], "supplier.ManagingScope", null, 250)&& !getObjByName('supplier.ManagingScope').value == ''){
			alert('${action.getText('supplier.ManagingScope.length')}');
			getObjByName('supplier.ManagingScope').value="";
			getObjByName('supplier.ManagingScope').focus();
			return  false;
		}							
 	return true;
 }	
 	
</script>
	
    <#if supplier.id?exists>
		<ul id="beautytab">
			<li><a id="editAdditionInfo"  onclick="activeTab(this);" class="selectedtab" 
				href="${req.contextPath}/supplierManager/editAdditionInfo.html?supplier.id=#{supplier.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('additionInfo')}</a>
			</li>
			<li><a id="listContactInfo" onclick="activeTab(this);"  
				href="${req.contextPath}/supplierManager/listContactInfo.html?supplier.id=#{supplier.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('contactInfo')}</a>
			</li>
			<li><a id="listApplicationDoc" onclick="activeTab(this);"  
				href="${req.contextPath}/applicationDocManager/listApplicationDoc.html?supplier.id=#{supplier.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('Attachments')}</a>
			</li>
			<li><a id="listSupplierAptitudes" onclick="activeTab(this);"  
				href="${req.contextPath}/supplierAptitudes/listSupplierAptitudes.html?supplier.id=#{supplier.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('Aptitudes')}</a>
			</li>
			<li><a id="listSupplierProducts" onclick="activeTab(this);"  
				href="${req.contextPath}/productsManager/listSupplierProducts.html?supplier.id=#{supplier.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('productInfo')}</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	 </#if>
</@htmlPage>

 