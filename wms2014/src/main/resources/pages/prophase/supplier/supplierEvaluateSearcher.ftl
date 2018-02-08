<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('supplierNo')}'" name="'supplierNo'" value="'${req.getParameter('supplierNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('supplieName')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
		   
		</tr>
	 	<tr> 
           <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		  </@ww.select> 
		       <@ww.select label="'${action.getText('supplier.level')}'" 
                     required="false" 
                     name="'level.id'" 
    			     value="'${req.getParameter('level.id')?if_exists}'" 
    			     listKey="id" 
    			     listValue="value"
                     list="supplierLevel" 
                     emptyOption="false" 
                     disabled="false">
        </@ww.select>
		<tr>
		<@ww.textfield label="'${action.getText('supplier.people')}'" name="'evaluateUser.name'" value="'${req.getParameter('evaluateUser.name')?if_exists}'" cssClass="'underline'" />
		 <td align="right" valign="top"><label  for="averageminute" class="label">${action.getText('supplier.grade')}:</label></td>
		      <td>
		        <input type="text" name="averageminute_min" size="10" maxlength="10" value="${req.getParameter('averageminute_min')?if_exists}" id="averageminute_min" class="underline" />
		        --
                <input type="text" name="averageminute_max" size="10"  maxlength="10" value="${req.getParameter('averageminute_max')?if_exists}" id="averageminute_max" class="underline"/>
		</tr>
		<tr>
		 <@pp.dateRanger label="'${action.getText('supplierDate')}'" name="'evaluatedate'" 
		       value="'${req.getParameter('evaluatedate_start')?if_exists}|${req.getParameter('evaluatedate_end')?if_exists}'"
		    cssClass="'underline'" dateFormat="date"/> 
		
		 <@pp.datePicker label="'${action.getText('evaluate.year')}'" name="'supplierEvaluateYear'"
	     						value="'${req.getParameter('supplierEvaluateYear')?if_exists}'" cssClass="'underline'" size="15" dateFormat="'%Y'"  maxlength="4"/>
        <@eam2008_onlySearchInvalid_checkBox/>
       </tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
 var selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
    <#if first>
    <#if loginUser.department?exists>
      getObjByNameRe("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
  }
  function checkInvalidParms() {
   if (getObjByNameRe("department.id").value == -1) {
	  getObjByNameRe("department.id").value = '';
	}
	if (getObjByNameRe("level.id").value==-1){
      getObjByNameRe("level.id").value='';
    }
    //验证评定分数的格式
    if ('' != getObjByNameRe("averageminute_min").value) {
      if (!isDoubleNumber("averageminute_min")) {
        alert("${action.getText('averageminute.not.digital')}");
        return false;
      }
    }
	if ('' != getObjByNameRe("averageminute_max").value) {
	  if (!isDoubleNumber("averageminute_max")) {
        alert("${action.getText('averageminute.not.digital')}");
        return false;
      }
	}

    //验证评定时间格式
	planCreatedMsg="${action.getText('supplierDate')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("evaluatedate_start","evaluatedate_end",
	    planCreatedMsg,null)){
	  return false;
	}
	return true;
  }
   function receiveUser_OpenDialog() {
	   var url = "${req.contextPath}/popup/userSelector.html";
	   popupModalDialog(url, 800, 600,managerSelectorHandler);
	 }
	 function managerSelectorHandler(result) {
	   if (null != result) {
	     document.forms[0].elements["evaluateUser.name"].value=result[1];
		 document.forms[0].elements["evaluateUser.id"].value=result[0];
	   }             	
     }
</script>