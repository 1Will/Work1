<@inputTable>
		<tr>
			<@pp.dateRanger label="'${action.getText('send.sendDate')}'" name="'sendDate'" 
                    value="'${req.getParameter('sendDate_start')?if_exists}|${req.getParameter('sendDate_end')?if_exists}'"
                    cssClass="'underline'" maxlength="10"/>
		     <@ww.select label="'${action.getText('notice.status')}'" 
                             required="false" name="'sendStatus'" 
                             value="'${sendStatus?if_exists}'"
                             listKey="value" listValue="label"
                             list="status" 
                              emptyOption="false" 
                              disabled="false">
          </@ww.select>
		</tr>
		<tr>
			<@crm_onlySearchInvalid_checkBox/>
	   </tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
var selector = document.all("sendStatus");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('sendStatus')?exists>
    if (selector.options[i].value == "${req.getParameter('sendStatus')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  
   function checkInvalidParms() {
      if (document.getElementById("sendStatus").value == -1) {
          document.getElementById("sendStatus").value = '';
        }
   
            var startSendDate = document.getElementById("sendDate_start").value;
            var endSendDate = document.getElementById("sendDate_end").value;
            var strStartSendDate = new String(startSendDate);
        	var strEndSendDate = new String(endSendDate);
            	
            
        	if (strStartSendDate!='') {
	        	if (!isDate("sendDate_start")) {
	        	  alert("${action.getText('sendDate.start')}" + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
	        }
	        if (strEndSendDate!='') {
	            if (!isDate("sendDate_end")) {
	        	  alert("${action.getText('sendDate.start')}"  + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
	        }
	    }    
  </script>