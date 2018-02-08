<@inputTable>	
	<tr>
	   <@ww.textfield label="'${action.getText('supplier.name')}'" 
	       name="'supplier.name'" value="'${req.getParameter('supplier.name')?if_exists}'" 
	       cssClass="'underline'" size='40'/>
	 <@pp.datePicker label="'${action.getText('月份')}'" 
	                name="'month'"
					value="'${req.getParameter('month')?if_exists}'" 
					cssClass="'underline'" 
					dateFormat="'%Y-%m'"
					size="15"/>				         
	
	</tr>
</@inputTable>	
<script language="javascript" type="text/JavaScript">
     String.prototype.Trim = function() {
         return this.replace(/(^\s*)|(\s*$)/g, "");
     }
    
    window.onload = function(){
      <#if month?exists>
       getObjByName("month").value="${month?if_exists}";
      </#if>
    }
   function checkInvalidParms(){
	 
      return true; 
	}    
 </script>