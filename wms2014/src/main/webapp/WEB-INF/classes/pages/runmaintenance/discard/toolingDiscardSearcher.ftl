<@inputTable>
		<@ww.hidden name="'agreeStatus'" value=""/>
        <@ww.hidden name="'dissentStatus'" value=""/>
        <@ww.hidden name="'toolingDevFlag'" value=""/>
		<tr>
			<@ww.textfield label="'${action.getText('discardNo')}'" name="'discardNo'" value="'${req.getParameter('discardNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
	 		<@ww.textfield label="'${action.getText('tooling.no')}'" name="'toolingNo'" value="'${req.getParameter('toolingNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('tooling.name')}'" name="'toolingName'" value="'${req.getParameter('toolingName')?if_exists}'" cssClass="'underline'"/>
	 	</tr>
	 	<tr>
	 		<@ww.textfield label="'${action.getText('tooling.graphNo')}'" name="'toolingGraphNo'" value="'${req.getParameter('toolingGraphNo')?if_exists}'" cssClass="'underline'"/>
    	    <@ww.select label="'${action.getText('toolingCategory')}'" required="false" name="'toolingType.id'" 
	    			value="'${req.getParameter('toolingType.id')?if_exists}'" listKey="id" listValue="value"
	                list="toolingTypes" emptyOption="false" disabled="false">
	        </@ww.select> 
		</tr>
		<tr>
	        <@pp.dateRanger label="'${action.getText('applyDatetime')}'" name="'applyDatetime'" 
		       value="'${req.getParameter('applyDatetime_start')?if_exists}|${req.getParameter('applyDatetime_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date" maxlength="10"/> 
		    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false" >
		    </@ww.select>
		</tr>
		<tr>
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
  document.getElementById("toolingDevFlag").value = 'true';
  var selector = document.all("toolingType.id");
  
  <#if toolingDevFlag>
	  var groups=selector.options.length;
	  for (i=0; i<groups; i++){
	    <#if req.getParameter('toolingType.id')?exists>
	    if (selector.options[i].value=="${req.getParameter('toolingType.id')?if_exists}"){
	      selector.options[i].selected="true";
	    }
	    </#if>
	  }  
 </#if>
  selector = document.all("department.id");
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
  
  selector = document.all("discard.status");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('discard.status')?exists>
    if (selector.options[i].value == "${req.getParameter('discard.status')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  } 
   <#if first>
    <#if loginUser.department?exists>
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
  
  function checkInvalidParms() {
    if (document.getElementById("toolingType.id").value == -1) {
	  document.getElementById("toolingType.id").value = '';
	}
    
	if (document.getElementById("department.id").value == -1) {
	  document.getElementById("department.id").value = '';
	}
	if (document.getElementById("discard.status").value == 'true') {
	  document.getElementById("agreeStatus").value = 'true';
	} else if (document.getElementById("discard.status").value == 'false') {
	   document.getElementById("dissentStatus").value = 'false';
	}

		//验证编制日期格式
	applyDatetimeMsg="${action.getText('applyDatetime')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("applyDatetime_start","applyDatetime_end",
	    applyDatetimeMsg,null)){
	  return false;
	}

	strStartMsg="${action.getText('select.right.sealedDateTm')}";
	strEndMsg="${action.getText('select.right.sealedDateTm')}";
    if(!queryDate("applyDatetime_start","applyDatetime_end",
    strStartMsg,strEndMsg)){
    	return false;
    }
	return true;

  }
 <#-- function changeDisabledButton() {
    if (document.getElementById("includeDisabled").checked) {
      document.getElementById("delete").value="${action.getText('enabled')}";
    } else {
      document.getElementById("delete").value="${action.getText('disabled')}";
    }
  }-->

  </script>	