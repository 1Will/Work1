<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('intactness.billNo')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('intactness.name')}'" name="'name'"  value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
    	    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		    value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		                list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
		    <@ww.textfield label="'${action.getText('intactness.examiner')}'" name="'examiner'"  value="'${req.getParameter('examiner')?if_exists}'" cssClass="'underline'"/>
		    
      	<tr>   
	    </tr>
	    <@pp.dateRanger label="'${action.getText('intactness.intactnessTime')}'" name="'examDate'" 
		                    value="'${req.getParameter('examDate_start')?if_exists}|${req.getParameter('examDate_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/> 
           		<@eam2008_onlySearchInvalid_checkBox/>
        </tr>
          
</@inputTable>
<script language="javascript">
  window.onload = function() {
	<#if first>
		<#if loginUser.department?exists>
		  document.getElementById("department.id").value = #{loginUser.department.id};
		</#if>
   </#if>
   }
  selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  } 
  
  function checkInvalidParms() {
	if (document.getElementById("department.id").value == -1) {
	  document.getElementById("department.id").value = '';
	}
	strStartMsg="${action.getText('select.right.intactnessBill.examDate')}";
	strEndMsg="${action.getText('examDate.order.error')}";
    if(!queryDate("examDate_start","examDate_end",strStartMsg,strEndMsg)){
    	return false;
    }
	return true;
  }

</script>  
	