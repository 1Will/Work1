<@inputTable>
        <@ww.hidden name="'changingStatus'" value=""/>
        <@ww.hidden name="'changedStatus'" value=""/>
		<tr>
			<@ww.textfield label="'${action.getText('tooling.changeBillNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('tooling.changeBillName')}'" name="'billName'" value="'${req.getParameter('billName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
	 		<@ww.textfield label="'${action.getText('tooling.no')}'" name="'toolingNo'" value="'${req.getParameter('toolingNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('tooling.graphNo')}'" name="'toolingGraphNo'" value="'${req.getParameter('toolingGraphNo')?if_exists}'" cssClass="'underline'"/>
	 	</tr>
	 	<tr>
	 	    <@ww.textfield label="'${action.getText('tooling.name')}'" name="'toolingName'" value="'${req.getParameter('toolingName')?if_exists}'" cssClass="'underline'"/>
    	    <@pp.dateRanger label="'${action.getText('tooling.planCompleteTime')}'" name="'planCompleteTime'" 
		                    value="'${req.getParameter('planCompleteTime_start')?if_exists}|${req.getParameter('planCompleteTime_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/> 
		</tr>
		<tr>
    	    <@pp.dateRanger label="'${action.getText('tooling.actualCompleteTime')}'" name="'actualCompleteTime'" 
		                    value="'${req.getParameter('actualCompleteTime_start')?if_exists}|${req.getParameter('actualCompleteTime_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/> 
		    <@pp.dateRanger label="'${action.getText('toolingChange.createdTime')}'" name="'createdDateTime'" 
		                    value="'${req.getParameter('createdDateTime_start')?if_exists}|${req.getParameter('createdDateTime_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/> 
        </tr>
        <tr>   
           <td align="right" valign="top"><label  for="searchToolingChangeBills_toolingStatus.code" class="label">${action.getText('tooling.changeBillStatus')}:</label></td>
           <td>
             <select name="tooling.changeBillStatus" id="searchToolingChangeBills_toolingStatus.code">       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('changeBill.changeing')}</option>       
               <option value="false">${action.getText('changeBill.changed')}</option>              
            </select>
           </td>
         	<@eam2008_onlySearchInvalid_checkBox/>
         </tr>
</@inputTable>
<script language="javascript">
<#--
  selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  } 
  -->
  selector = document.all("tooling.changeBillStatus");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('tooling.changeBillStatus')?exists>
    if (selector.options[i].value == "${req.getParameter('tooling.changeBillStatus')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  } 
  function checkInvalidParms() {
    var startDate = getObjByNameRe("planCompleteTime_start").value;
    var endDate = getObjByNameRe("planCompleteTime_end").value;
    var strStartDate = new String(startDate);
    var strEndDate = new String(endDate);
    <#--
	if (getObjByNameRe("department.id").value == -1) {
	  getObjByNameRe("department.id").value = '';
	}
	-->
	if (getObjByNameRe("tooling.changeBillStatus").value == 'true') {
	  getObjByNameRe("changingStatus").value = 'true';
	} else if (getObjByNameRe("tooling.changeBillStatus").value == 'false') {
	   getObjByNameRe("changedStatus").value = 'false';
	}
	
    if (strStartDate!='') {
      if (!isDate("planCompleteTime_start")) {
         alert("${action.getText('tooling.planCompleteTime')}" + "," +"${action.getText('dateFormate.error')}");
         return false;
      }
	}
	if (strEndDate!='') {
      if (!isDate("planCompleteTime_end")) {
         alert("${action.getText('tooling.planCompleteTime')}" + "," +"${action.getText('dateFormate.error')}");
         return false;
      }
	}
	if ((strStartDate!='' && strEndDate!='')) {
	  if (strStartDate > strEndDate) {
	    alert("${action.getText('tooling.planCompleteTime.order.error')}");
	    return false;
	  }
	}
    strStartMsg="${action.getText('tooling.actualCompleteTime')}" + "," + "${action.getText('dateFormate.error')}";
	strEndMsg="${action.getText('actualCompleteTime.order.error')}";
    if(!queryDate("actualCompleteTime_start","actualCompleteTime_end",strStartMsg,strEndMsg)){
    	return false;
    }
	//验证编制日期格式
    beginDateMsg="${action.getText('toolingChange.createdTime')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("createdDateTime_start","createdDateTime_end",
	    beginDateMsg,null)){
	  return false;
	}	
	return true;
  }
</script>