<@inputTable>
 		<tr>
			<@ww.textfield label="'${action.getText('calibration.calibrationNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('calibration.calibrationName')}'" name="'planName'" value="'${req.getParameter('planName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
			<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
		    <@pp.datePicker label="'${action.getText('month')}'" name="'month'" 
		       		value="'${req.getParameter('month')?if_exists}'" cssClass="'underline'" size="15" 
	     			dateFormat="'%Y-%m'"/>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('calibration.writer')}'" name="'writer'" value="'${req.getParameter('writer')?if_exists}'" cssClass="'underline'" />
	    	<@pp.dateRanger label="'${action.getText('calibration.makeDate')}'" name="'makeDate'" 
		       value="'${req.getParameter('makeDate_start')?if_exists}|${req.getParameter('makeDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/>
		</tr>
		<tr>
			<#if planProcFlag?exists>
			    <#if (planProcFlag=='PLAN')>
			    	<@eam2008_onlySearchInvalid_checkBox/>
			    <#elseif (planProcFlag=='PROC')> 
					<@ww.textfield label="'${action.getText('calibration.reporter')}'" name="'reporter'" value="'${req.getParameter('reporter')?if_exists}'" cssClass="'underline'" />
			    	<@pp.dateRanger label="'${action.getText('calibration.reportDate')}'" name="'reportDate'" 
				       value="'${req.getParameter('reportDate_start')?if_exists}|${req.getParameter('reportDate_end')?if_exists}'"
				       cssClass="'underline'" dateFormat="date"/>
				</#if>
			</#if>
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
		
			<#--
		var scheduleDate_start = document.getElementById("scheduleDate_start").value;
	    var scheduleDate_end = document.getElementById("scheduleDate_end").value;
	    if (scheduleDate_start!='') {
		   if(!isDate("scheduleDate_start")){
			 alert('${action.getText('calibration.scheduleDate')}'+'${action.getText('dateFormate.error')}');
			 return false; 
		   }
		}
	    if(scheduleDate_end!='') {
		   if(!isDate("scheduleDate_end")){
			 alert('${action.getText('calibration.scheduleDate')}'+'${action.getText('dateFormate.error')}');
			 return false; 
		   }
		}
		-->
		var makeDate_start = document.getElementById("makeDate_start").value;
	    var makeDate_end = document.getElementById("makeDate_end").value;
	    if (makeDate_start!='') {
		   if(!isDate("makeDate_start")){
			 alert('${action.getText('calibration.makeDate')}'+'${action.getText('dateFormate.error')}');
			 return false; 
		   }
		}
	    if(makeDate_end!='') {
		   if(!isDate("makeDate_end")){
			 alert('${action.getText('calibration.makeDate')}'+'${action.getText('dateFormate.error')}');
			 return false; 
		   }
		}
		
		<#if planProcFlag?exists>
			 <#if (planProcFlag=='PROC')>
				var reportDate_start = document.getElementById("reportDate_start").value;
			    var reportDate_end = document.getElementById("reportDate_end").value;
			    if (reportDate_start!='') {
				   if(!isDate("reportDate_start")){
					 alert('${action.getText('calibration.reportDate')}'+'${action.getText('dateFormate.error')}');
					 return false; 
				   }
				}
			    if(reportDate_end!='') {
				   if(!isDate("reportDate_end")){
					 alert('${action.getText('calibration.reportDate')}'+'${action.getText('dateFormate.error')}');
					 return false; 
				   }
				}
				</#if>
		</#if>
		return true;
	  }
</script>	