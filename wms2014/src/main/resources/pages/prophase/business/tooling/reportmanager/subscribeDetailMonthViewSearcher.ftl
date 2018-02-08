 
<@inputTable>	
	<tr>
	    <@ww.select label="'${action.getText('view.department')}'" 
	                required="false" 
	                name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" 
		    		listKey="id" 
		    		listValue="name"
		            list="departments" 
		            emptyOption="false" 
		            disabled="false">
		</@ww.select> 
		
	  <@pp.datePicker label="'${action.getText('月份')}'" 
	                name="'month'"
					value="'${req.getParameter('month')?if_exists}'" 
					cssClass="'underline'" 
					dateFormat="'%Y-%m'"
					size="15"/>	
		
	</tr>
</@inputTable>	
<script language="javascript" type="text/JavaScript">
    window.onload=function(){
    <#if viewDate_start?exists>
      getObjByName("viewDate_start").value = "${viewDate_start?if_exists}";
      getObjByName("viewDate_end").value = "${viewDate_end?if_exists}";
     </#if>
    }
	var selector = document.all("department.id");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
	    <#if req.getParameter('department.id')?exists>
		    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
		         selector.options[i].selected="true";
		    }
	    </#if>
    }
    
  function checkInvalidParms(){
  
       if(-1==getObjByName("department.id").value){
          getObjByName("department.id").value="";
       }
      
     
      return true;  
  }    
 </script>