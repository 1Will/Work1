
<@inputTable>		
	<tr>
		<#-- <@docTypeSelector0/> -->
		<@ww.select label="'${action.getText('doc.type')}'"  name="'docType.id'"  listKey="id" listValue="name"
    			 value="${req.getParameter('docType.id')?if_exists}"
                list="docTypes" emptyOption="false" disabled="false">
        </@ww.select>
		<@ww.textfield label="'${action.getText('doc.no')}'" name="'docNo'" value="'${req.getParameter('docNo')?if_exists}'" cssClass="'underline'"  />
		<@ww.textfield label="'${action.getText('doc.name')}'" name="'docName'" value="'${req.getParameter('docName')?if_exists}'" cssClass="'underline'"  />
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('doc.submittor')}'" name="'creator'" value="'${req.getParameter('creator')?if_exists}'" cssClass="'underline'"  />
		<@pp.dateRanger label="'${action.getText('doc.submittedTime')}'" name="'createdTime'" 
			value="'${req.getParameter('createdTime_start')?if_exists}|${req.getParameter('createdTime_end')?if_exists}'"
			cssClass="'underline'" dateFormat="date"/>  
		<@ww.select label="'${action.getText('state')}'" required="false" name="'docState.code'" 
    			value="'${req.getParameter('docState.id')?if_exists}'" listKey="code" listValue="value"
                list="docStates" emptyOption="false" disabled="false">
        </@ww.select>
	</tr>	
	<tr>

	</tr>
	
	<script language="javascript">
     <#--   
    	 var selector = document.all("department.id");
         var groups=selector.options.length;  
         for (i=0; i<groups; i++){
            <#if req.getParameter('department.id')?exists>
            if (selector.options[i].value=="${req.getParameter('department.id')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
         }
         -->
         selector = document.all("docType.id");
         var groups=selector.options.length;  
         for (i=0; i<groups; i++){
            <#if req.getParameter('docType.id')?exists>
            if (selector.options[i].value=="${req.getParameter('docType.id')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
         }
         
         selector = document.all("docState.code");
         var groups = selector.options.length;
         for (i=0; i<groups; i++) {
         	<#if req.getParameter('docState.code')?exists>
         		if (selector.options[i].value=="${req.getParameter('docState.code')?if_exists}") {
         			selector.options[i].selected="true";
         		}
         	</#if>
         }

        function checkInvalidParms() {		   
			var startDate = document.getElementById("createdTime_start").value;
        	var endDate = document.getElementById("createdTime_end").value;
        	var strStartDate = new String(startDate);
        	var strEndDate = new String(endDate);
        	
        	if (document.getElementById("docType.id").value==-1) {
        		document.getElementById("docType.id").value='';
        	} 
        	if (document.getElementById("docState.code").value==-1) {
        		document.getElementById("docState.code").value='';
        	}
        	if (strStartDate!="") {
	        	if (!validateDateFormate(strStartDate,"${action.getText('doc.submittedTime')}")) {
	        		return false;
	        	}
	        }
	        if (strEndDate!="") {
	        	if (!validateDateFormate(strEndDate,"${action.getText('doc.submittedTime')}")) {
	        		return false;
	        	}
	        }
	        if ((strStartDate!='' && strEndDate!='')) {
	        	if (strStartDate > strEndDate) {
	        		alert("${action.getText('cardCreatedTime.order.error')}");
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
</@inputTable>