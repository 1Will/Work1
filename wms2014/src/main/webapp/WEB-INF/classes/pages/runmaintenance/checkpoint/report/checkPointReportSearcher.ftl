<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('report.no')}'" name="'reportNo'" value="'${req.getParameter('reportNo')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('report.name')}'" name="'reportName'" value="'${req.getParameter('reportName')?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>
    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    			value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
                list="departments" emptyOption="false" disabled="false">
        </@ww.select>
    	<@pp.datePicker label="'${action.getText('report.month')}'" name="'month'"
	     					value="'${req.getParameter('month')?if_exists}'" cssClass="'underline'" size="15" 
	     					dateFormat="'%Y-%m'"/>    
    	<@pp.dateRanger label="'${action.getText('report.reportTime')}'" name="'reportTime'" 
		  value="'${req.getParameter('reportTime_start')?if_exists}|${req.getParameter('reportTime_end')?if_exists}'"
		  cssClass="'underline'" dateFormat="date"/>  
		  
    	
    </tr>
     <script language="javascript">
        function checkInvalidParms() {
	  	if (document.getElementById("department.id").value == -1) {
	  		document.getElementById("department.id").value = '';
		}
		
		strStartMsg="${action.getText('report.reportTime')}"+"${action.getText('dateFormate.error')}";
		strEndMsg="${action.getText('reportTime.order.error')}";
		if(!queryDate("reportTime_start","reportTime_end",
		    strStartMsg,strEndMsg)){
		    	return false;
		    }
		return true;
	  }
    </script>
</@inputTable>