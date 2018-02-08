<@inputTable>
        <@ww.hidden name="'maintenanceType_one'" value=""/>
        <@ww.hidden name="'maintenanceType_two'" value=""/>
        <tr>
			<@ww.textfield label="'${action.getText('maintenance.planNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('maintenance.planName')}'" name="'planName'" value="'${req.getParameter('planName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
		    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
		    
		    <@pp.datePicker label="'${action.getText('maintenance.scheduleDate')}'" name="'month'" 
		       		value="'${req.getParameter('month')?if_exists}'" cssClass="'underline'" size="15" 
	     			dateFormat="'%Y-%m'"/>
						
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('maintenance.writer')}'" name="'writer'" value="'${req.getParameter('writer')?if_exists}'" cssClass="'underline'" />
	    	<@pp.dateRanger label="'${action.getText('maintenance.makeDate')}'" name="'makeDate'" 
		       value="'${req.getParameter('makeDate_start')?if_exists}|${req.getParameter('makeDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/>
		</tr>
		<#if planProcFlag?exists>
			    <#if (planProcFlag=='PROC')> 
					<tr>
						<@ww.textfield label="'${action.getText('maintenance.reporter')}'" name="'reporter'" value="'${req.getParameter('reporter')?if_exists}'" cssClass="'underline'" />
				    	<@pp.dateRanger label="'${action.getText('maintenance.reportDate')}'" name="'reportDate'" 
					       value="'${req.getParameter('reportDate_start')?if_exists}|${req.getParameter('reportDate_end')?if_exists}'"
					       cssClass="'underline'" dateFormat="date"/>
					</tr>
				</#if>
		</#if>
		<tr>
			<@eam2008_onlySearchInvalid_checkBox/>
        </tr>
	</@inputTable> 
	<script language="javascript">
	  var selector = document.all("department.id");
	  groups = selector.options.length;
	  for (i=0; i<groups; i++) {
	    <#if req.getParameter('department.id')?exists>
	    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
	      selector.options[i].selected="true";
	    }
	    </#if>
	    <#if first>
             <#if loginUser.department?exists>
                 document.getElementById("department.id").value = #{loginUser.department.id};
             </#if>
        </#if>
	  }
	  
	  function checkInvalidParms() {
	  	if (document.getElementById("department.id").value == -1) {
	  		document.getElementById("department.id").value = '';
		}
		if(isNotEmpty(ListMaintenance,"makeDate_start")) {
			if (!isDate("makeDate_start")) {
	        	  alert("${action.getText('maintenance.makeDate')}"  + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
        }
        if(isNotEmpty(ListMaintenance,"makeDate_end")) {
			if (!isDate("makeDate_end")) {
	        	  alert("${action.getText('maintenance.makeDate')}"  + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
        }
        <#if planProcFlag?exists>
			    <#if (planProcFlag=='PROC')>
			        if(isNotEmpty(ListMaintenance,"reportDate_start")) {
						if (!isDate("reportDate_start")) {
				        	  alert("${action.getText('maintenance.reportDate')}"  + "${action.getText('dateFormate.error')}");
				        	  return false;
				        	}
			        }
			        if(isNotEmpty(ListMaintenance,"reportDate_end")) {
						if (!isDate("reportDate_end")) {
				        	  alert("${action.getText('maintenance.reportDate')}"  + "${action.getText('dateFormate.error')}");
				        	  return false;
				        	}
			        }
        	</#if>
		</#if>
		
		return true;
	  }
	  
	 </script>	