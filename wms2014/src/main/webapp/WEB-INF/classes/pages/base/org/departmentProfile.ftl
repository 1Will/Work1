
<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('department.title1')}">
     <@ww.form  name="'department'" action="'saveDepartment'" method="'post'" validate="true">
       <@ww.token name="saveDepartmentProfileToken"/>
         <@inputTable>
            <#if department.id?exists>
                <@ww.hidden name="'department.id'" value="#{department.id}"/>
            </#if>
            <@ww.hidden name="'department.version'" value="#{department.version?if_exists}"/>
             <tr>
                 <@ww.textfield label="'${action.getText('deparment.code')}'" name="'department.code'" value="'${department.code?if_exists}'" cssClass="'underline'" required="true"/>
                 <@ww.textfield label="'${action.getText('department.name')}'" name="'department.name'" value="'${department.name?if_exists}'" cssClass="'underline'" required="true"/>
             </tr>
             <tr>
             <@ww.select label="'${action.getText('filiale')}'" required="false" name="'filiale.id'" 
		    		value="'${req.getParameter('filiale.id')?if_exists}'" listKey="id" listValue="name"
		            list="filiales" emptyOption="true" disabled="false"  required="true">
		    </@ww.select>
                  <@ww.textfield label="'${action.getText('department.leader')}'" name="'department.leader'" value="'${department.leader?if_exists}'"  cssClass="'underline'"/>      
             </tr>
             <tr>
             	<@ww.textfield label="'${action.getText('department.tel')}'" name="'department.tel'" value="'${department.tel?if_exists}'"  cssClass="'underline'"/>
             	<@ww.textfield label="'排序号'" name="'department.sortIdx'" value="'${department.sortIdx?if_exists}'"  cssClass="'underline'"/>
             </tr>
         </@inputTable>
  <script language="javascript">
  window.onload=function(){
  	   <#if  department.id?exists>
       <#if department.filiale?exists>
            $('filiale.id').value=#{department.filiale.id};              
      </#if>
      </#if>
  }
  selector = document.all("filiale.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('filiale.id')?exists>
    if (selector.options[i].value == "${req.getParameter('filiale.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
	function checkInvalidParms() {
		if (document.getElementById("filiale.id").value == -1) {
		  document.getElementById("filiale.id").value = '';
		}
		return true;
	}
</script>
         
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation()'"/>
	         <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/department/listDepartments.html"/>
         </@buttonBar>
          <@titleBar title="${action.getText('department.ProductionLine')}"/>
           <@listTable>
            <tr>
                <th></th>
                <th>${action.getText('productionLine.code')}</th>
                <th>${action.getText('productionLine.name')}</th>
            </tr>
            <#list department.productionLines as productionLines>
                <tr>
                    <td width="8%">
                        <input type="checkbox" name="productionLineIds" value="#{productionLines.id}" width="30" />
                    </td>
                    <td>${productionLines.code}</td>
                    <td>${productionLines.name}</td>
                </tr>
            </#list>
        </@listTable>
        <@buttonBar>
            <select name="productionLine.id">
                <#list unJoinedProductionLines as unJoinedProductionLines>
                    <option value="#{unJoinedProductionLines.id}">${unJoinedProductionLines.name}</option>
                </#list>
            </select>
            <@vsubmit name="'join'" value="'${action.getText('join')}'">
                <@ww.param name="'disabled'" value="${unJoinedProductionLines.isEmpty()?string}"/>
            </@vsubmit>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('productionLine')}?" />
            <@vsubmit name="'leave'" value="'${action.getText('leave')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"productionLineIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${department.productionLines.isEmpty()?string}"/>
            </@vsubmit>
        </@buttonBar>
     </@ww.form>
     <script>
       function storeValidation(){
        if(document.getElementById("department.code").value==''){
	        alert('${action.getText('department.code.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "department.code", null, 50)){
				alert("${action.getText('department.code.length')}");
				return  false;
			   }
			 }
		if(document.getElementById("department.name").value==''){
	        alert('${action.getText('department.name.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "department.name", null, 50)){
				alert("${action.getText('department.name.length')}");
				return  false;
			   }   
			}  
		 if(document.getElementById("filiale.id").value==''){
	        alert('${action.getText('filiale.not.null')}');
	        return false;
	     } else{
	        if(!isValidLength(document.forms[0], "filiale.id", null, 50)){
				alert("${action.getText('filiale.id.length')}");
				return  false;
			   }   
			}   
		if(document.getElementById("department.tel").value!=''){
	        if(!isValidLength(document.forms[0], "department.tel", null, 50)){
				alert("${action.getText('department.tel.length')}");
				return  false;
			   }  
		}
		if (!isNumber("department.sortIdx")){   //验证格式
           alert("请输入排序号为数字");
           return false;
        }
         <#--  //验证部门代码
           if(!validationDepartmentCode(0,50)){
              return false;
           }
           //验证部门名称
           if(!validationDepartmentName(0,50)){
              return false;
           }
           //验证部门负责人
           if(!validationDepartmentLeader(0,50)){
              return false;
           }
           //验证部门电话
           if(!validationDepartmentTel(0,50)){
              return false;
           }-->
           return true;
      
     }  
       <#--function validationDepartmentCode(min,max){
           if(isNotEmpty(department,"department.code")==true){
	        if(isValidLength(department,"department.code",min,max)==false){
	           alert("${action.getText('department.code.maxLength')}"+max);
	           return false;
	        }
	     }else{
	          alert("${action.getText('department.code.maxLength')}");
	          return false;
	     }
	     return true;
       }
       
       function validationDepartmentName(min,max){
           if(isNotEmpty(department,"department.name")==true){
	        if(isValidLength(department,"department.name",min,max)==false){
	           alert("${action.getText('department.name.maxLength')}"+max);
	           return false;
	        }
	     }else{
	          alert("${action.getText('department.name.requiredstring')}");
	          return false;
	     }
	     return true;
       }
       
       function validationDepartmentLeader(min,max){
           if(isNotEmpty(department,"department.leader")==true){
	        if(isValidLength(department,"department.leader",min,max)==false){
	           alert("${action.getText('department.leader.maxLength')}"+max);
	           return false;
	        }
	     }else{
	          alert("${action.getText('department.leader.requiredstring')}");
	          return false;
	     }
	     return true;
       }
       
       function validationDepartmentTel(min,max){
           if(isNotEmpty(department,"department.tel")==true){
	        if(isValidLength(department,"department.tel",min,max)==false){
	           alert("${action.getText('department.tel.maxLength')}"+max);
	           return false;
	        }
	     }
	     return true;
       }
       -->
     </script>
</@htmlPage>
