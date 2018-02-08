<@inputTable>
        <@ww.hidden name="'returnStatus'" value=""/>
        <@ww.hidden name="'borrowStatus'" value=""/>
		<tr>
			<@ww.textfield label="'${action.getText('billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('billName')}'" name="'billName'" value="'${req.getParameter('billName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
	 		<@ww.textfield label="'${action.getText('tooling.no')}'" name="'toolingNo'" value="'${req.getParameter('toolingNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('tooling.name')}'" name="'toolingName'" value="'${req.getParameter('toolingName')?if_exists}'" cssClass="'underline'"/>
	 	</tr>
	 	<tr>
	 		<@ww.textfield label="'${action.getText('tooling.graphNo')}'" name="'toolingGraphNo'" value="'${req.getParameter('toolingGraphNo')?if_exists}'" cssClass="'underline'"/>
    	    <@pp.dateRanger label="'${action.getText('borrowDateTime')}'" name="'borrowTime'" 
		       value="'${req.getParameter('borrowTime_start')?if_exists}|${req.getParameter('borrowTime_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
		</tr>
		<tr>
	    	<@ww.select label="'${action.getText('toolingCategory')}'" required="false" name="'toolingType.id'" 
	    			value="'${req.getParameter('toolingType.id')?if_exists}'" listKey="id" listValue="value"
	                list="toolingTypes" emptyOption="false" disabled="false"  onchange="'toolTypeValueChange()'">
	        </@ww.select> 
		    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
		    <#--
		    <@ww.select label="'${action.getText('toolingState')}'" required="false" name="'toolingStatus.code'" 
    			    value="'${req.getParameter('toolingStatus.id')?if_exists}'" listKey="code" listValue="value"
                    list="toolingStatus" emptyOption="false" disabled="false">
           </@ww.select> 
           -->
           <td align="right" valign="top"><label  for="searchToolingBorrowBills_toolingStatus.code" class="label">状态:</label></td>
           <td>
             <select name="borrowBill.status" id="searchToolingBorrowBills_toolingStatus.code">       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('tooling.returned')}</option>       
               <option value="false">${action.getText('tooling.borrowing')}</option>              
            </select>
           </td>
	    </tr>  	
</@inputTable>
<script language="javascript">
  var selector = document.all("toolingType.id");
  var groups=selector.options.length;  
  for (i=0; i<groups; i++){
    <#if req.getParameter('toolingType.id')?exists>
    if (selector.options[i].value=="${req.getParameter('toolingType.id')?if_exists}"){
      selector.options[i].selected="true";
    }
    </#if>
  }  
 
  selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
    
  } 
  
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
    var startDate = document.getElementById("borrowTime_start").value;
    var endDate = document.getElementById("borrowTime_end").value;
    var strStartDate = new String(startDate);
    var strEndDate = new String(endDate);
    if (document.getElementById("toolingType.id").value == -1) {
	  document.getElementById("toolingType.id").value = '';
	}
	if (document.getElementById("department.id").value == -1) {
	  document.getElementById("department.id").value = '';
	}
	if (document.getElementById("borrowBill.status").value == 'true') {
	  document.getElementById("returnStatus").value = 'true';
	} else if (document.getElementById("borrowBill.status").value == 'false') {
	   document.getElementById("borrowStatus").value = 'false';
	}
    if (strStartDate!='') {
	  if (!validateDateFormate(strStartDate,"${action.getText('borrowDateTime')}")) {
	    return false;
	  }
	}
	if (strEndDate!='') {
	  if (!validateDateFormate(strEndDate,"${action.getText('borrowDateTime')}")) {
	    return false;
	  }
	}
	if ((strStartDate!='' && strEndDate!='')) {
	  if (strStartDate > strEndDate) {
	    alert("${action.getText('borrowDateTime.order.error')}");
	    return false;
	  }
	}
	return true;
  }
  
  function validateDateFormate(strDate, validateObjectMessage) {    //验证时间格式
    var dateFormate = /^(?:[123][0-9]\d{2})\-(?:0?[1-9]{1}|1([0-2])?)\-(?:0?[1-9]{0,1}|([12][0-9]){1,2}|(3[0-1]){1,2})$/
    if (!dateFormate.test(strDate)) {
      if ('' == validateObjectMessage) {
        alert("${action.getText('dateFormate.error')}");
      } else {
        alert(validateObjectMessage + "," + "${action.getText('dateFormate.error')}");
      }
      return false;
    }
    return true;
  }
</script>  
	