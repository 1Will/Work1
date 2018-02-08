<@inputTable>
 		<tr>
			<@ww.textfield label="'${action.getText('feedbackNo')}'" name="'feedbackNo'" value="'${req.getParameter('feedbackNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('feedbackName')}'" name="'feedbackName'" value="'${req.getParameter('feedbackName')?if_exists}'" cssClass="'underline'"/>
		</tr>
 		<tr>
    		<@ww.textfield label="'${action.getText('device.no')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'"/>
    		<@ww.textfield label="'${action.getText('device.name')}'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
	    </tr>
	    <tr>
        	<@pp.dateRanger label="'${action.getText('feedbackDatetime')}'" name="'feedbackDatetime'" 
		       value="'${req.getParameter('feedbackDatetime_start')?if_exists}|${req.getParameter('feedbackDatetime_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
		    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
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
  }
  
  function checkInvalidParms() {
  if (document.getElementById("department.id").value == -1) {
  		document.getElementById("department.id").value = '';
	}
	strStartMsg="${action.getText('dateFormate.error')}";
	strEndMsg="${action.getText('feedbackDatetime.order.error')}";
	if(!queryDate("feedbackDatetime_start","feedbackDatetime_end",
	    strStartMsg,strEndMsg)){
	    	return false;
	    }
	return true;
  }
  </script>	