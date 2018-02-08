<@inputTable>
	<@ww.hidden name="'invalid'" value="true"/>
	<@ww.hidden name="'unSubmit'" value=""/>
    <tr>
        <@ww.textfield label="'${action.getText('plan.no')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('plan.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>

    </tr>
    <tr>
    	<@ww.textfield label="'${action.getText('device.no')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('device.name')}'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>    
    	<@pp.dateRanger label="'${action.getText('plan.scheduleTime')}'" name="'scheduleTime'" 
		  value="'${req.getParameter('scheduleTime_start')?if_exists}|${req.getParameter('scheduleTime_end')?if_exists}'"
		  cssClass="'underline'" dateFormat="date"/>  
		  
    	<@ww.select label="'${action.getText('device.department')}'" required="false" name="'department.id'" 
    			value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
                list="departments" emptyOption="false" disabled="false">
        </@ww.select>
        <@ww.select label="'${action.getText('state')}'" required="false" name="'docState.code'" 
    			value="'${req.getParameter('docState.code')?if_exists}'" listKey="code" listValue="value"
                list="docStates" emptyOption="false" disabled="false">
        </@ww.select>
    </tr>
     <script language="javascript">
         window.onload = function () {
    	 var selector = document.all("department.id");
         var groups=selector.options.length;  
         for (i=0; i<groups; i++){
            <#if req.getParameter('department.id')?exists>
            if (selector.options[i].value=="${req.getParameter('department.id')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
         }
        }
        function checkInvalidParms() {	
        	var time_start=document.getElementById("scheduleTime_start").value;	
        	var time_end=document.getElementById("scheduleTime_end").value;
        	var s1=new String(time_start);
        	var s2=new String(time_end);

        	
        	if (document.getElementById("department.id").value==-1) {
        		document.getElementById("invalid").value=true;
        		document.getElementById("department.id").value='';
        	} else {
        		document.getElementById("invalid").value=false;
        	}
        	if (document.getElementById("docState.code").value==-1 ||
        		document.getElementById("docState.code").value == "DOC_UNSUBMIT") {
        		document.getElementById("docState.code").value='';
        		document.getElementById("unSubmit").value='true';
        	} 
        	if (s1!='') {
        		if (!validateDateFormate(s1, "${action.getText('plan.scheduleTime')}")) {
        			return false;
        		}
        	}
        	if (s2!='') {
        		if (!validateDateFormate(s2, "${action.getText('plan.scheduleTime')}")) {
        			return false;
        		}
        	}
         if ((time_start!='' && time_end!='')) {
	        	if (time_start > time_end) {
	        		alert("${action.getText('scheduleTime_start.scheduleTime_end')}");
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