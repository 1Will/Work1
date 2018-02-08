<@inputTable>
		<tr> 
		    <@ww.textfield label="'${action.getText('notice.sender')}'" name="'sender'" value="'${req.getParameter('sender')?if_exists}'" cssClass="'underline'" /> 
          	<@pp.dateRanger label="'${action.getText('notice.receviceDate')}'" name="'receviceDate'" 
                    value="'${req.getParameter('receviceDate_start')?if_exists}|${req.getParameter('receviceDate_end')?if_exists}'"
                    cssClass="'underline'" maxlength="10"/>
		</tr>
		<tr>
		 <@ww.select label="'${action.getText('notice.status')}'" 
                             required="false" name="'readStatus'" 
                             value="'${readStatus?if_exists}'"
                             listKey="value" listValue="label"
                             list="status" 
                              emptyOption="false" 
                              disabled="false">
          </@ww.select>
		<@eam2008_onlySearchInvalid_checkBox/>
		</tr>

</@inputTable>
<script language="javascript" type="text/JavaScript">
var selector = document.all("readStatus");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('readStatus')?exists>
    if (selector.options[i].value == "${req.getParameter('readStatus')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
function checkInvalidParms() {
      if (getObjByNameRe("readStatus").value == -1) {
          getObjByNameRe("readStatus").value = '';
        }
       var startSendDate = getObjByNameRe("receviceDate_start").value;
       var endSendDate = getObjByNameRe("receviceDate_end").value;
       var strStartSendDate = new String(startSendDate);
       var strEndSendDate = new String(endSendDate);
        	if (strStartSendDate!='') {
	        	if (!isDate("receviceDate_start")) {
	        	  alert("${action.getText('recevice.start')}" + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
	        }
	        if (strEndSendDate!='') {
	            if (!isDate("receviceDate_end")) {
	        	  alert("${action.getText('recevice.start')}"  + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
	        }
   }
  </script> 