<#-- $Id: productionLineProfile.ftl 10243 2008-01-08 03:18:30Z wzou $ -->
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingDiscardEdit.title')}">
    <@ww.form namespace="'/base/productionLine'" name="'productionLine'" action="'saveProductionLine'" method="'post'" validate="true">
        <@ww.token name="saveProductionLineToken"/>
        <#if productionLine.id?exists>
        	<@ww.hidden name="'productionLine.id'" value="#{productionLine.id}"/>
        </#if>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('productionLine.code')}'" name="'productionLine.code'" value="'${productionLine.code?if_exists}'" cssClass="'underline'" required="true" />
	 	  	  	<@ww.textfield label="'${action.getText('productionLine.name')}'" name="'productionLine.name'" value="'${productionLine.name?if_exists}'" cssClass="'underline'" required="true" />
        		<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    			 listKey="id" listValue="name"
		                list="departments" emptyOption="true" disabled="false" required="true">
		    	</@ww.select>
        	</tr>
        </@inputTable>
        <@buttonBar>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/base/productionLine/listProductionLines.html"/>
	    </@buttonBar>
		<script language="JavaScript" type="text/JavaScript">
			window.onload = function () {
				<#if productionLine.department?exists>
		     		getObjByName('department.id').value=#{productionLine.department.id?if_exists};
		     	</#if>
	     	}
	     	function validate(){
	     		if(!productionLine_code()){  
		  			return false;
		  		}
		  		if(!productionLine_name()){  
		  			return false;
		  		}
		     	if (getObjByName('department.id').value == '') {
			      alert("${action.getText('select.department.name')}");
			      return false;
			    }

		    }
		    /*
		    *验证生产线编号最大字符数为50个字符
		    *验证生产线编号是否为空
		    */
		    function productionLine_code(){
				var name=getObjByName('productionLine.code').value;
					if(!(name=='')){
						if(!isValidLength(document.forms[0], "productionLine.code", null, 50)){
					  		alert("${action.getText('productionLine.code.length')}");
					  		return  false;
					  	}
				  	}else{
				  		alert('${action.getText('productionLine.code.requiredstring')}');
				  		return false;
				  	}
				return true;
			}
		    /*
		    *验证生产线名称最大字符数为50个字符
		    *验证生产线名称是否为空
		    */
		    function productionLine_name(){
				var name=getObjByName('productionLine.name').value;
					if(!(name=='')){
						if(!isValidLength(document.forms[0], "productionLine.name", null, 50)){
					  		alert("${action.getText('productionLine.name.length')}");
					  		return  false;
					  	}
				  	}else{
						alert('${action.getText('productionLine.name.requiredstring')}');	
						return false;			  	
				  	}
				return true;
			}
		</script>
    </@ww.form>
</@htmlPage>