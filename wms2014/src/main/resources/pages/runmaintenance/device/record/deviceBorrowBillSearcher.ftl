<@inputTable>
        <@ww.hidden name="'returnStatus'" value=""/>
        <@ww.hidden name="'borrowStatus'" value=""/>
		<tr>
			<@ww.textfield label="'${action.getText('billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('billName')}'" name="'billName'" value="'${req.getParameter('billName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
	 		<@ww.textfield label="'${action.getText('device.no')}'" name="'toolingNo'" value="'${req.getParameter('toolingNo')?if_exists}'" cssClass="'underline'" />
	 	<#--	<@ww.textfield label="'${action.getText('device.graphNo')}'" name="'toolingGraphNo'" value="'${req.getParameter('toolingGraphNo')?if_exists}'" cssClass="'underline'"/>
	 	-->
	 	    <@ww.textfield label="'${action.getText('device.name')}'" name="'toolingName'" value="'${req.getParameter('toolingName')?if_exists}'" cssClass="'underline'"/>
	 	</tr>
	 	<tr>
    	    <@pp.dateRanger label="'${action.getText('borrowDateTime')}'" name="'borrowTime'" 
		                    value="'${req.getParameter('borrowTime_start')?if_exists}|${req.getParameter('borrowTime_end')?if_exists}'"
		                     cssClass="'underline'" maxlength="10"/> 
		   <td align="right" valign="top"><label  for="searchToolingBorrowBills_toolingStatus.code" class="label">${action.getText('borrowBill.status')}:</label></td>
           <td>
             <select name="borrowBill.status" id="searchToolingBorrowBills_toolingStatus.code">       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('tooling.returned')}</option>       
               <option value="false">${action.getText('tooling.borrowing')}</option>              
            </select>
           </td>
      <@eam2008_onlySearchInvalid_checkBox/>     
	     </tr>	
</@inputTable>
<script language="javascript">
  selector = document.all("borrowBill.status");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('borrowBill.status')?exists>
    if (selector.options[i].value == "${req.getParameter('borrowBill.status')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  } 
  function checkInvalidParms() {

	if (getObjByNameRe("borrowBill.status").value == 'true') {
	 getObjByNameRe("returnStatus").value = 'true';
	} else if (getObjByNameRe("borrowBill.status").value == 'false') {
	  getObjByNameRe("borrowStatus").value = 'false';
	}
    //验证计划开始日期格式
    beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("borrowTime_start","borrowTime_end",
	    beginDateMsg,null)){
	  return false;
	}	
	return true;
  }

</script>  
	