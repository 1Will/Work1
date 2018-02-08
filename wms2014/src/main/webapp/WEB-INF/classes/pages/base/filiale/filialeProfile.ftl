
<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('filialeEdit.title')}">
     <@ww.form  name="'filiale'" action="'saveFiliale'" method="'post'" validate="true">
       <@ww.token name="saveFilialeProfileToken"/>
         <@inputTable>
            <#if filiale.id?exists>
                <@ww.hidden name="'filiale.id'" value="#{filiale.id}"/>
            </#if>
            <@ww.hidden name="'filiale.version'" value="#{filiale.version?if_exists}"/>
         <#-- 隐藏域解决之丢失问题的方法 
            <@ww.hidden name="'filiale.codeHidden'" value="'${filiale.code?if_exists}'" />
            <@ww.hidden name="'filiale.nameHidden'" value="'${filiale.name?if_exists}'" />
            <@ww.hidden name="'filiale.leaderHidden'" value="'${filiale.leader?if_exists}'" />
            <@ww.hidden name="'filiale.telHidden'" value="'${filiale.tel?if_exists}'" />
            <@ww.hidden name="'filiale.addressHidden'" value="'${filiale.address?if_exists}'" />
            <@ww.hidden name="'filiale.commentHidden'" value="'${filiale.comment?if_exists}'" />
          --> 
             <tr>
            
                 <@ww.textfield label="'${action.getText('filiale.code')}'" name="'filiale.code'" value="'${filiale.code?if_exists}'" cssClass="'underline'" required="true"/>
		   <#-- Ajax 验证用户名，未采用
             	<@pp.remotePicker label="'${action.getText('filiale.code')}'" name="'filiale.code'"
                    selectorName="'filialeSelectorAjax'" cssClass="'underline'" value="filiale" 
                    size="20" required="true"/>
              -->
                 <@ww.textfield label="'${action.getText('filiale.name')}'" name="'filiale.name'" value="'${filiale.name?if_exists}'" cssClass="'underline'" required="true"/>
             </tr>
             <tr>
             	<@ww.textfield label="'${action.getText('filiale.leader')}'" name="'filiale.leader'" value="'${filiale.leader?if_exists}'" cssClass="'underline'" required="true"/>
             	<@ww.textfield label="'${action.getText('filiale.tel')}'" name="'filiale.tel'" value="'${filiale.tel?if_exists}'" cssClass="'underline'" />
             </tr>
             <tr>
             	<@ww.textfield label="'${action.getText('filiale.postCode')}'" name="'filiale.postCode'" value="'${filiale.postCode?if_exists}'" cssClass="'underline'" />
             	<@ww.textfield label="'${action.getText('filiale.taxId')}'" name="'filiale.taxId'" value="'${filiale.taxId?if_exists}'" cssClass="'underline'" />
             </tr>
             <tr>
             	<@ww.textfield label="'${action.getText('filiale.bank')}'" name="'filiale.bank'" value="'${filiale.bank?if_exists}'" cssClass="'underline'" />
             	<@ww.textfield label="'${action.getText('filiale.bankAccount')}'" name="'filiale.bankAccount'" value="'${filiale.bankAccount?if_exists}'" cssClass="'underline'" />
             </tr>
             <tr>
             	<@ww.textarea label="'${action.getText('filiale.address')}'" name="'filiale.address'" value="'${filiale.address?if_exists}'" rows='3' cols='50' cssClass="'underline'" />	
                <#--  <@ww.textfield label="'${action.getText('filiale.comment')}'" name="'filiale.comment'" value="'${filiale.comment?if_exists}'"  cssClass="'underline'"/>-->
                <@ww.textarea label="'${action.getText('filiale.comment')}'" name="'filiale.comment'" value="'${filiale.comment?if_exists}'" rows='3' cols='50'  cssClass="'underline'"/>
             </tr>
         </@inputTable>
         
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation()'"/>
	         <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/filiale/listFiliales.html"/>
         </@buttonBar>
     </@ww.form>
     <script>
     window.onload=function(){
//     alert("测试开始：");
//     alert(document.forms[ "filiale" ]["filiale.code.code"].value);
     }
       function storeValidation(){
        if($('filiale.code').value==''){
	        alert('${action.getText('filiale.code.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "filiale.code", null, 50)){
					alert("${action.getText('filiale.code.length')}");
					return  false;
			   }
		}
		
		if($('filiale.name').value==''){
	        alert('${action.getText('filiale.name.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "filiale.name", null, 50)){
				alert("${action.getText('filiale.name.length')}");
				return  false;
				}   
			} 
		
		if($("filiale.leader").value==''){
	        alert('${action.getText('filiale.leader.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "filiale.leader", null, 50)){
				alert("${action.getText('filiale.leader.length')}");
				return  false;
			   }   
		 }  

		 //校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”
         //国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
         var pattern =/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
             if($('filiale.tel').value !="")
             {
                 if(!pattern.exec($('filiale.tel').value))
                 {
                  alert('请输入正确的电话号码:电话号码格式为国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"');
                  return false;
                 }
             }

		if($('filiale.address').value != ''){
	        if(!isValidLength(document.forms[0], "filiale.address", null, 255)){
				alert("${action.getText('filiale.address.length')}");
				return  false;
			   }  
		}  
		if($('filiale.comment').value!=''){
	        if(!isValidLength(document.forms[0], "filiale.comment", null, 255)){
				alert("${action.getText('filiale.comment.length')}");
				return  false;
			   }  
			}
		//验证邮编
		if($('filiale.postCode').value != ''){
	        if(!isValidLength(document.forms[0], "filiale.postCode", null, 50)){
				alert("${action.getText('filiale.postCode.length')}");
				return  false;
			   }  
		}
		//验证税号
		if($('filiale.taxId').value != ''){
	        if(!isValidLength(document.forms[0], "filiale.taxId", null, 50)){
				alert("${action.getText('filiale.taxId.length')}");
				return  false;
			   }  
		}
		//验证开户行
		if($('filiale.bank').value != ''){
	        if(!isValidLength(document.forms[0], "filiale.bank", null, 50)){
				alert("${action.getText('filiale.bank.length')}");
				return  false;
			   }  
		}
		//验证银行帐号
		if($('filiale.bankAccount').value != ''){
	        if(!isValidLength(document.forms[0], "filiale.bankAccount", null, 50)){
				alert("${action.getText('filiale.bankAccount.length')}");
				return  false;
			   }  
		}   
          return true;
     }  
     
     </script>
</@htmlPage>
