<@inputTable>
	<@ww.hidden name="'invalid'" value="true"/>
	<@ww.hidden name="'unSubmit'" value=""/>
    <tr>
        <@ww.textfield label="'${action.getText('rule.no')}'" name="'ruleNo'" value="'${req.getParameter('ruleNo')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('rule.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>
    	<@ww.textfield label="'${action.getText('rule.device.no')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('rule.device.name')}'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>
    	<@ww.select label="'${action.getText('checkPoint.type')}'"  name="'ruleType.id'" 
        			value="'${req.getParameter('ruleType.id')?if_exists}'" listKey="id" listValue="value"
        			list="ruleTypes" emptyOption="false" disabled="false">
       	</@ww.select>
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
         selector = document.all("ruleType.id");
         var groups=selector.options.length;  
         for (i=0; i<groups; i++){
            <#if req.getParameter('ruleType.id')?exists>
            if (selector.options[i].value=="${req.getParameter('ruleType.id')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
         }
         selector = document.all("docState.code");
         var groups=selector.options.length;  
         for (i=0; i<groups; i++){
            <#if req.getParameter('docState.code')?exists>
            if (selector.options[i].value=="${req.getParameter('docState.code')?if_exists}"){
               selector.options[i].selected="true";
            }            
            </#if>
         }
        }
        function checkInvalidParms() {		   
        	if (document.getElementById("department.id").value==-1) {
        		document.getElementById("invalid").value=true;
        		document.getElementById("department.id").value='';
        	} else {
        		document.getElementById("invalid").value=false;
        	}
        	if (document.getElementById("ruleType.id").value==-1) {
        		document.getElementById("invalid").value=true;
        		document.getElementById("ruleType.id").value='';
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
    </script>
</@inputTable>