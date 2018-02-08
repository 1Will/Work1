<@inputTable>
	 <tr>
        <@ww.textfield label="'${action.getText('document.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
    	<@ww.select label="'${action.getText('document.category')}'"  name="'category.id'"  listKey="id" listValue="value"
    			 value="${req.getParameter('category.id')?if_exists}"
                list="categorys" emptyOption="false" disabled="false">
        </@ww.select>
     </tr>
     <script language="javascript">
       selector = document.all("category.id");
         var groups=selector.options.length;  
        for (i=0; i<groups; i++){
            <#if req.getParameter('category.id')?exists>
            if (selector.options[i].value=="${req.getParameter('category.id')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
        }  
     	function checkInvalidParms() {
     		if (getObjByNameRe("category.id").value == -1) {
     			getObjByNameRe("category.id").value='';
     		}
     	}
     </script>
</@inputTable>