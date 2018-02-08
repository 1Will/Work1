<@inputTable>
		<@ww.hidden name="'agreeStatus'" value=""/>
        <@ww.hidden name="'dissentStatus'" value=""/>
        <@ww.hidden name="'toolingDevFlag'" value=""/>
		<tr>
			<@ww.textfield label="'${action.getText('discardNo')}'" name="'discardNo'" value="'${req.getParameter('discardNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
    		<@ww.textfield label="'${action.getText('device.no')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'"/>
    		<@ww.textfield label="'${action.getText('device.name')}'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
	    </tr>
		<tr>
		    <@ww.textfield label="'${action.getText('device.specification')}'" name="'specification'" value="'${req.getParameter('specification')?if_exists}'" cssClass="'underline'"/>
	        <@pp.dateRanger label="'${action.getText('applyDatetime')}'" name="'applyDatetime'" 
		       value="'${req.getParameter('applyDatetime_start')?if_exists}|${req.getParameter('applyDatetime_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
	    </tr>  
	    <tr>
	        <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
		   <td align="right" valign="top"><label class="label">${action.getText('discard.status')}:</label></td>
           <td>
             <select name="discard.status" >       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('agreeStatus')}</option>       
               <option value="false">${action.getText('dissentStatus')}</option>              
            </select>
           </td>
           <@eam2008_onlySearchInvalid_checkBox/>
	    </tr>
</@inputTable>
<script language="javascript">
  document.getElementById("toolingDevFlag").value = 'false';

  var selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  
  selector = document.all("discard.status");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('discard.status')?exists>
    if (selector.options[i].value == "${req.getParameter('discard.status')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  } 
  
    function checkInvalidParms() {
	if (document.getElementById("department.id").value == -1) {
	  document.getElementById("department.id").value = '';
	}
	if (document.getElementById("discard.status").value == 'true') {
	  document.getElementById("agreeStatus").value = 'true';
	} else if (document.getElementById("discard.status").value == 'false') {
	   document.getElementById("dissentStatus").value = 'false';
	}
    strStartMsg="${action.getText('dateFormate.error')}";
	strEndMsg="${action.getText('applyDatetime.order.error')}";
    if(!queryDate("applyDatetime_start","applyDatetime_end",
    strStartMsg,strEndMsg)){
    	return false;
    }
	return true;
  }
  
  <#--function changeDisabledButton() {
    if (document.getElementById("includeDisabled").checked) {
      document.getElementById("delete").value="${action.getText('enabled')}";
    } else {
      document.getElementById("delete").value="${action.getText('disabled')}";
    }
  }-->
  </script>	