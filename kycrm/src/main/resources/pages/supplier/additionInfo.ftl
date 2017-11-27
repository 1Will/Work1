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


<@framePage title="">
	<@ww.form namespace="'/supplierManager'" name="'listForm'" action="'saveAdditionInfo'" method="'post'">
		<@ww.token name="saveAdditionInfoToken"/>
		<@inputTable>
			<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<#if supplier.id?exists>
                <@ww.hidden name="'supplier.id'" value="#{supplier.id}"/>
            </#if>
			<tr>
				<@ww.textfield label="'${action.getText('supplier.maorContact')}'" name="'supplier.maorContact'" 
							   value="'${supplier.maorContact?if_exists}'" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'${action.getText('supplier.phone')}'" name="'supplier.phone'" 
							   value="'${supplier.phone?if_exists}'" cssClass="'underline'" required="false"/>
				<@ww.textfield label="'${action.getText('supplier.mobile')}'" name="'supplier.mobile'" 
							   value="'${supplier.mobile?if_exists}'" cssClass="'underline'" required="false"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('supplier.fex')}'" name="'supplier.fex'" 
							   value="'${supplier.fex?if_exists}'" cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('supplier.email')}'" name="'supplier.email'" 
							   value="'${supplier.email?if_exists}'" cssClass="'underline'" required="false" />
				<@ww.textfield label="'${action.getText('supplier.qq')}'" name="'supplier.qq'" 
							   value="'${supplier.qq?if_exists}'" cssClass="'underline'"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('supplier.homeSite')}'" name="'supplier.homeSite'" 
							   value="'${supplier.homeSite?if_exists}'" cssClass="'underline'"/>	
				<@pp.datePicker 
					label="'${action.getText('supplier.createDate')}'" 
					name="'supplier.createDate'" 
		   			value="'${(supplier.createDate?string('yyyy-MM-dd'))?if_exists}'"
					cssClass="'underline'" 
					required="false"
					dateFormat="'%Y-%m-%d'"
					maxlength="10"/>	
				<@ww.textfield label="'${action.getText('supplier.postCode')}'" name="'supplier.postCode'" 
							   value="'${supplier.postCode?if_exists}'" cssClass="'underline'"/>											
			</tr>
			<tr>
				<td align="right" valign="top">
		       		<label class="label">${action.getText('supplier.address')}:</label>
		     	</td>
				<td colspan="8">
					<input type="text" name="supplier.address" value="${supplier.address?if_exists}" class="underline" maxlength="120" size="120" />
				</td>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('supplier.businessLicense')}'" name="'supplier.businessLicense'" 
							   value="'${supplier.businessLicense?if_exists}'" cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('supplier.taxNo')}'" name="'supplier.taxNo'" 
							   value="'${supplier.taxNo?if_exists}'" cssClass="'underline'" required="false" />
				<@ww.textfield label="'${action.getText('supplier.turnover')}'" name="'supplier.turnover'" 
							   value="'${supplier.turnover?if_exists}'"  cssClass="'underline'" />
			</tr>			
			<tr>
				<@ww.textfield label="'${action.getText('supplier.bank')}'" name="'supplier.bank'" 
							   value="'${supplier.bank?if_exists}'" cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('supplier.bankName')}'" name="'supplier.bankName'" 
							   value="'${supplier.bankName?if_exists}'" cssClass="'underline'" required="false" />
				<@ww.textfield label="'${action.getText('supplier.bankAccount')}'" name="'supplier.bankAccount'" 
							   value="'${supplier.bankAccount?if_exists}'" cssClass="'underline'"/>
			</tr>				
			<tr>
	        	<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('supplier.afterSaleService')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="supplier.afterSaleService" rows="4" >${supplier.afterSaleService?if_exists}</textarea>
		        </td>
		        <script language="javascript">
					var width=document.body.clientWidth/9;
					getObjByName("supplier.afterSaleService").cols =width;
				</script>
		    </tr>			
			<tr>
	        	<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('supplier.qos')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="supplier.qos" rows="4"  >${supplier.qos?if_exists}</textarea>
		        </td>
		        <script language="javascript">
					var width=document.body.clientWidth/9;
					getObjByName("supplier.qos").cols =width;
				</script>
		    </tr>
		</@inputTable>
		<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'">
            </@vsubmit>
        </#if>
	</@buttonBar>
	</@ww.form>
</@framePage>
<script language="javascript">
 function storeValidation(){
 	//主要联系人
		if(!isValidLength(document.forms[0], "supplier.maorContact", null, 20)&&!getObjByName('supplier.maorContact').value == ''){
			alert('${action.getText('supplier.maorContact.length')}');
			return  false;
		}
	//电话
	<#--
		if(getObjByName('supplier.phone').value != ''){
		   var str = getObjByName('supplier.phone').value
		   var reg=/^([0-9]|[\-])+$/g ;
	       if(str.length<7 || str.length>18){
	       		alert('${action.getText('supplier.phone.error')}');
	       return false;
	       }
		}
	-->
		if(!isValidLength(document.forms[0], "supplier.phone", null, 20)&&!getObjByName('supplier.phone').value == ''){
			alert('${action.getText('supplier.phone.length')}');
			return  false;
		}	
	//手机
		<#--if(getObjByName('supplier.mobile').value == ''){
			alert('${action.getText('supplier.mobile.not.null')}');
        	return false;
		}else{-->
		<#--
		   var str = getObjByName('supplier.mobile').value
		   var reg=/^([0-9]|[\-])+$/g ;
	       if(str.length<7 || str.length>18){
	       		alert('${action.getText('supplier.mobile.error')}');
	        	return false;
	       }
	    -->
	    	<#--if(isNaN($F('supplier.mobile'))){
				alert('手机号码格式不正确!');
				getObjByName('supplier.mobile').focus();
				return false;
			}-->
			if(!isValidLength(document.forms[0], "supplier.mobile", null, 20)&&!getObjByName('supplier.mobile').value == ''){
				alert('${action.getText('supplier.mobile.length')}');
				return  false;
			}	
			
			
			
		<#--}	-->
	//传真号
	<#--
		if(getObjByName('supplier.fex').value != ''){
		   var str = getObjByName('supplier.fex').value
		   var reg=/^([0-9]|[\-])+$/g ;
	       if(str.length<7 || str.length>18){
	       		alert('${action.getText('supplier.fex.error')}');
	       return false;
	       }
		}
	-->
      	if (!isValidLengthValue(getObjByName('supplier.fex').value,0,20)&& getObjByName('supplier.fex').value != '') {
	       alert("${action.getText('supplier.fex.maxLength')}");
	       return false;
	    }		
	//Email
        if ('' != getObjByName('supplier.email').value) {
			var value = getObjByName('supplier.email').value;
            if (!value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.org)|(\.info)|(\.edu)|(\.mil)|(\.gov)|(\.biz)|(\.ws)|(\.us)|(\.tv)|(\.cc)|(\..{2,2}))$)\b/gi)) {
          		alert("${action.getText('supplier.email.email')}");
          		return false;
          	}
          	if (!isValidLengthValue(getObjByName('supplier.email').value,0,50)) {
		       alert("${action.getText('supplier.email.maxLength')}");
		       return false;
		    }
         }
     //QQ
     	if('' != getObjByName('supplier.qq').value){
     	<#--
	 		if(isNaN(getObjByName('supplier.qq').value)){
				alert('${action.getText('supplier.qq.format')}');
				return false;
			}
		-->
          	if (!isValidLengthValue(getObjByName('supplier.qq').value,0,20)) {
		       alert("${action.getText('supplier.qq.maxLength')}");
		       return false;
		    }			 
		} 
	//网址
	<#---->
	if(!isValidLength(document.forms[0], "supplier.homeSite", null, 50) && '' !=getObjByName('supplier.homeSite').value){
	   alert("${action.getText('supplier.homeSite.length')}");
	   return  false;
    }
	if(getObjByName('supplier.homeSite').value !=''){
		var urlreg=/^[A-Za-z0-9://]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/
		if (!urlreg.test(getObjByName('supplier.homeSite').value)){
			alert('${action.getText('supplier.homeSite.type')}');
			return false;
     	}  
	}	  
    //创立日期
 		if(getObjByName('supplier.createDate').value !=''){
	//	 		alert("${action.getText('supplier.createDate.not.null')}");
	//	      	return false;   
	//	}else{
		 	if(!isDate('supplier.createDate')){
				alert("${action.getText('supplier.createDate.dateFormate.error')}");
				return false;
			} 
			if(isDateLessThenCurrent(getObjByName('supplier.createDate').value)){
				alert('${action.getText('supplier.createDate.earlyError')}');
				return false;
			}
		}   
	//邮编
		if('' != getObjByName('supplier.postCode').value){
			if(!isValidLengthValue(getObjByName('supplier.postCode').value,0,20)){
				alert("${action.getText('supplier.postCode.maxLength')}");
				return false;
			}
			<#--
			if(isNaN(getObjByName('supplier.postCode').value)){
				alert('${action.getText('supplier.postCode.format')}');
				return false;
			}
			-->
		}	
	//地址
		if(!isValidLength(document.forms[0], "supplier.address", null, 50) && '' !=getObjByName('supplier.address').value){
		   alert("${action.getText('supplier.address.length')}");
		   return  false;
	    }
	//营业执照
		if(!isValidLength(document.forms[0], "supplier.businessLicense", null, 50) && '' !=getObjByName('supplier.businessLicense').value){
		   alert("${action.getText('supplier.businessLicense.length')}");
		   return  false;
	    }	
	//税号
		if(!isValidLength(document.forms[0], "supplier.taxNo", null, 50) && '' !=getObjByName('supplier.taxNo').value){
		   alert("${action.getText('supplier.taxNo.length')}");
		   return  false;
	    }	
	//营业额
	if(getObjByName('supplier.turnover').value !=''){
		if(isNaN(formatDigital(getObjByName('supplier.turnover').value))){
				alert('${action.getText('supplier.turnover.format')}');
				return  false;
		}else{
			if(!isDoubleNumberBetweenBoolean(formatDigital(getObjByName('supplier.turnover').value), 1000000001, -1)){
				alert('${action.getText('supplier.turnover.format')}');
				return false;
			}
		}
	}	
	//开户行
		if(!isValidLength(document.forms[0], "supplier.bank", null, 50) && '' !=getObjByName('supplier.bank').value){
		   alert("${action.getText('supplier.bank.length')}");
		   return  false;
	    }	
	//开户行名称
		if(!isValidLength(document.forms[0], "supplier.bankName", null, 50) && '' !=getObjByName('supplier.bankName').value){
		   alert("${action.getText('supplier.bankName.length')}");
		   return  false;
	    }
	//开户行账号
		if(!isValidLength(document.forms[0], "supplier.bankAccount", null, 50) && '' !=getObjByName('supplier.bankAccount').value){
		   alert("${action.getText('supplier.bankAccount.length')}");
		   return  false;
	    }
	//售后服务
		if(!isValidLength(document.forms[0], "supplier.afterSaleService", null, 250) && '' !=getObjByName('supplier.afterSaleService').value){
		   alert("${action.getText('supplier.afterSaleService.length')}");
		   return  false;
	    }
	//服务质量	
		if(!isValidLength(document.forms[0], "supplier.qos", null, 250) && '' !=getObjByName('supplier.qos').value){
		   alert("${action.getText('supplier.qos.length')}");
		   return  false;
	    }
 	return true;
 }	
 	
</script>