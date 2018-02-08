<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('discardBillNo')}'" name="'discardBillNo'" value="'${req.getParameter('discardBillNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('discardBillName')}'" name="'discardBillName'" value="'${req.getParameter('discardBillName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		
		<tr>
		     <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
	        <@ww.textfield label="'${action.getText('reportUser')}'" name="'reportName'" value="'${req.getParameter('reportName')?if_exists}'" cssClass="'underline'" />
	    </tr>  
	    <tr>
	      <@pp.dateRanger label="'${action.getText('reportDate')}'" name="'reportDate'" 
		       value="'${req.getParameter('reportDate_start')?if_exists}|${req.getParameter('reportDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
           <@eam2008_onlySearchInvalid_checkBox/>
	    </tr>
</@inputTable>
<script language="javascript">
	<#if first>
		    <#if loginUser.department?exists>
		      document.getElementById("department.id").value = #{loginUser.department.id};
		    </#if>
		  </#if>
 	var selector = document.all("department.id");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    
    </#if>
 } 
 function checkInvalidParms() {
   beginDateMsg="${action.getText('reportDate')}" + "${action.getText('dateFormate.error')}";
	   if(!queryDate("reportDate_start","reportDate_end", beginDateMsg,null)){
	     return false;
	}
 if (document.getElementById("department.id").value == -1) {
	  document.getElementById("department.id").value = '';
	} 
	 return true;
 }
  </script>	