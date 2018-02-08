<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('deparment.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('department.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>   
		   <@ww.select label="'${action.getText('filiale')}'" 
                           name="'filiale.id'" 
                           value="'${req.getParameter('filiale.id')?if_exists}'"
                           listKey="id" listValue="name"
                           list="filiales" emptyOption="false" 
                           disabled="false" required="false">
               </@ww.select>


    </tr>
     <tr>
      <@eam2008_onlySearchInvalid_checkBox/> 
    </tr>
     <script language="javascript">
       selector = document.all("filiale.id");
         var groups=selector.options.length;  
        for (i=0; i<groups; i++){
            <#if req.getParameter('filiale.id')?exists>
            if (selector.options[i].value=="${req.getParameter('filiale.id')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
        }  
     	function checkInvalidParms() {
     		if (getObjByName('filiale.id').value == -1) {
     			getObjByName('filiale.id').value='';
     		}
     		return true;
     	}
     </script>
    
</@inputTable>