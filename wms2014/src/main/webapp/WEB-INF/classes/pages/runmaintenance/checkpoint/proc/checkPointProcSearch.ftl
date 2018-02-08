<@inputTable>
<@ww.hidden name="'invalid'" value="true"/>
<@ww.hidden name="'unSubmit'" value=""/>
	 		<tr>
	 			<@ww.textfield label="'${action.getText('checkPointProc.number')}'" name="'procNo'" value="'${req.getParameter('procNo')?if_exists}'" cssClass="'underline'" />
	 			<@ww.textfield label="'${action.getText('checkPointProc.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" />
	 		</tr>
	 		<tr>
	 			<@ww.textfield label="'${action.getText('checkPointPlan.number')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
	 			<@ww.textfield label="'${action.getText('checkPointPlan.name')}'" name="'planName'" value="'${req.getParameter('planName')?if_exists}'" cssClass="'underline'" />
	        </tr>
	        <tr>
	 			<@ww.textfield label="'${action.getText('device.number')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'" />
	 			<@ww.textfield label="'${action.getText('device.name')}'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'" />
	        </tr>
	        <tr>
		<@pp.dateRanger label="'${action.getText('checkPointProc.time')}'" name="'procExecTime'" 
		    value="'${req.getParameter('procExecTime_start')?if_exists}|${req.getParameter('procExecTime_end')?if_exists}'"
		     cssClass="'underline'" dateFormat="date"/>       	
	        	<@ww.textfield label="'${action.getText('checkPoint.manager')}'" name="'checker'" value="" cssClass="'underline'" />
	     </tr>
	     <tr>
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
         var selector = document.all("docState.code");
         var groups=selector.options.length;  
         for (i=0; i<groups; i++){
            <#if req.getParameter('docState.code')?exists>
            if (selector.options[i].value=="${req.getParameter('docState.code')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
         }  
        }
        
         function checkInvalidParms(){
            var time_start=document.getElementById("procExecTime_start").value;	
        	var time_end=document.getElementById("procExecTime_end").value;
        	var s1=new String(time_start);
        	var s2=new String(time_end);
        	if(!(s1=='')){
        		var regu="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        		var re =new RegExp(regu);
        		if (!re.test(s1)){
        			alert("${action.getText('please.input.procExecTime_start')}");
        			return false;
        		}
        		else{
        		   if(!validateProcTime(s1)){
        		     alert("${action.getText('please.input.procExecTime_start')}");
        		      return false;
        		   }
        		}
        	}
        	if(!(s2=='')){
        		var regu="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        		var re =new RegExp(regu);
        		if (!re.test(s2)){
        			alert("${action.getText('please.input.procExecTime_end')}");
        			return false;
        		}
        		else{
        		   if(!validateProcTime(s2)){
        		     alert("${action.getText('please.input.procExecTime_end')}");
        		      return false;
        		   }
        		}
        	}
        	if(((s1=='')&&(s2!=''))||((s2=='')&&(s1!=''))){
        	   if(s1==''){
        	    alert("${action.getText('procExecTime_start.notNull')}");
        	    }
        	   if(s2==''){
        	    alert("${action.getText('procExecTime_end.notNull')}");
        	   }
        	   return false;
        	   }
        	if(time_start>time_end){
        		alert("${action.getText('procExecTime_start.procExecTime_end')}");
        		return false;
        		}
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
		          return true;
		      }
		      
		      function validateProcTime(validateTime){
                    var timeYear=validateTime.substring(0,validateTime.indexOf('-')).length;
                    var timeDay=validateTime.substring(validateTime.lastIndexOf('-')+1,validateTime.length).length;
                    if(timeYear>4)
                    {
                        return false;
                    }
                    if(timeDay>2)
                    {
                        return false;
                    }
                    return true;

               }
               
    </script> 	
</@inputTable> 