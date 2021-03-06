<@inputTable>
		<@ww.hidden name="'toolingDevFlag'" value=""/>
		<tr>
			<@ww.textfield label="'${action.getText('accidentBillNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('accidentBillName')}'" name="'billName'" value="'${req.getParameter('billName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
	 		<@ww.textfield label="'${action.getText('device.no')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('device.name')}'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
	 	</tr>
	 	<tr>
    	    <@pp.dateRanger label="'${action.getText('accidentOccurDateTime')}'" name="'accidentOccurDateTm'" 
		       value="'${req.getParameter('accidentOccurDateTm_start')?if_exists}|${req.getParameter('accidentOccurDateTm_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
		    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
	    </tr>  	
</@inputTable>
<script language="javascript">
  getObjByNameRe("toolingDevFlag").value = 'DEVICE';
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
	if (getObjByNameRe("department.id").value == -1) {
	  getObjByNameRe("department.id").value = '';
	}
	strStartMsg="${action.getText('dateFormate.error')}";
	strEndMsg="${action.getText('accidentOccurDateTime.order.error')}";
    if(!queryDate("accidentOccurDateTm_start","accidentOccurDateTm_end",
    strStartMsg,strEndMsg)){
    	return false;
    }
	return true;
  }
</script>  
	