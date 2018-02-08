
<#include "../../../includes/eam2008.ftl" />
<#--$Id: lubricationRuleList.ftl 10642 2008-01-22 13:12:58Z zbzhang $ -->
<@htmlPage title="${action.getText('lubricationRules.title')}">
 <base target="_self" />
 <STYLE TYPE="text/css" >
	 .displayRed{
	   font-weight: bold;
	   color: #FFFFFF;
	   background-color: #FFCC66;	 
	 }
	  .noBorderLine{
       border-width :0px;
       border-style : none;
       outline-style : none;
       width:80%;
       }
    .definedLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        width:80%;
    }
     </STYLE>
    <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
    <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
    <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
    <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>
	 <@ww.form name="'lubricationPlan'" action="'searchLubricationRuleItems'" method="'post'">
	   <@ww.token name="searchLubricationRuleItemsToken"/>
	    <@ww.hidden name="'lubricationInfo'" value="'${lubricationInfo?if_exists}'"/>
	    <#assign itemNo=1/>
	    <#assign loopNum = 0/>  
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'${action.getText('lubricationRule.device.deviceNo')}'" name="'device.deviceNo'" value="'${req.getParameter('device.deviceNo')?if_exists}'" cssClass="'underline'"/>
			 	<@ww.textfield label="'${action.getText('lubricationRule.device.name')}'" name="'device.name'" value="'${req.getParameter('device.name')?if_exists}'" cssClass="'underline'" />
	        </tr>	
        </@inputTable> 
        <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" /> 
        </@buttonBar>
        <@list title="${action.getText('lubricationRules.title')}" includeParameters="id|device.deviceNo|device.name" fieldMap="like:id|device.deviceNo|device.name" >
            <@vlh.checkbox property="id" name="lubricationRuleIds" attributes="id=f onclick=\"callBack();\"">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('serialNo')}" >
              ${itemNo}
             <#assign itemNo = itemNo+1/>
            </@vcolumn>
            <@vcolumn title="${action.getText('lubricationPlan.device.deviceNo')}" property="device.deviceNo" sortable="desc">
            </@vcolumn>
            <@vcolumn title="${action.getText('lubricationPlan.device.name')}" property="device.name" sortable="desc"/>
            <@vcolumn title="${action.getText('department')}" property="device.department.name"/>
            <@vcolumn title="${action.getText('lubricationRules.position')}" property="position" sortable="desc"/>
            <#--
            <@vcolumn title="${action.getText('lubricationRules.content')}" property="content"/>
            -->
            <@vcolumn title="${action.getText('lubricationRules.ruleDsp')}" property="ruleDsp"/>
            <@vcolumn title="${action.getText('lubricationRules.methodDsp')}" property="methodDsp"/>
            <@vcolumn title="${action.getText('lubricationRules.cycle')}" property="cycle"/>
            <@vcolumn title="${action.getText('lubricationRules.lastLubricationDate')}" property="lastLubricationDate" format="yyyy-MM-dd"/>
            <@vcolumn title="${action.getText('lubricationRules.lubricatingOil')}" property="lubricatingOil.name"/>
            <@vcolumn title="${action.getText('lubricationRules.lubricatingOilQty')}" property="lubricatingOilQty"/>
            <@vcolumn title="${action.getText('lubricationRules.dutyPeople')}" >
                  <input type="text" name="dutyPeople" value="${object.dutyPeople}" id="dutyPeople" maxlength="300" class="underline" disabled="true"/>
            </@vcolumn> 
            <@vcolumn title="${action.getText('lubricationPlan.estimateExecDate')}"> 
               <#assign estimateExecDateTM = ''/>
                 <#if object.estimateExecDate?exists>
                 <#assign estimateExecDateTM = "${(object.estimateExecDate?string('yyyy-MM-dd'))}"/>
              </#if>
                  <#assign loopNum = loopNum +1/>
                  <#assign estimateExecDateIdentityName = 'estimateExecDate'+'${loopNum}'/> 
                  <#assign estimateExecDateImgIdentity = 'lubricationPlan_' + '${estimateExecDateIdentityName}' +' _trigger'/>       
                  <input type="text"
                     name="${estimateExecDateIdentityName}"
                     size="10"                    
                     value="${estimateExecDateTM}"                     
                     id="${estimateExecDateIdentityName}"                        
                     class="underline"
                     maxlength="300"
                     disabled  />                    
              <img id="${estimateExecDateImgIdentity}" src="/eam2008/images/calendar/calendar.gif" align=absMiddle border=0 />
              <script language="javascript">
                Calendar.setup({
                               formName       :    "lubricationPlan",
                               inputField     :    "${estimateExecDateIdentityName}",
                               button         :    "${estimateExecDateImgIdentity}",
                               ifFormat       :    "%Y-%m-%d",
                               showsTime      :    false,
                               timeFormat     :    "24",
                               showOthers     :    true
                              });
              </script>
            </@vcolumn>
        </@list>
        <#if !first>
        <@buttonBar>
             <#assign confirmMessage = "${action.getText('lubricationRule.save')}${action.getText('lubricationRule')}?" />
            <@vsubmit name="'save'" value="'${action.getText('save')}'" >
                <@ww.param name="'onclick'" value="'return confirmSetLubricationValue(\"lubricationRuleIds\",\"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <input type="button" value="${action.getText('close')}" class="button" onclick="javascript:window.close();"/>
        </@buttonBar>
        </#if>
      </@ww.form>
      
      <script>
      
      function callBack() {
       	    var dutyPeopleSelector = document.getElementsByName("dutyPeople");
		    var lubricationRuleSelector = document.getElementsByName("lubricationRuleIds");
		  	var estimateExecDateName;
		  	var estimateExecDateIdentity;
		  	var estimateExecDateSelector;
            for ( var i = 0; i < lubricationRuleSelector.length; i ++) {
                   estimateExecDateName='estimateExecDate'+(parseInt(i)+parseInt(1));
                   estimateExecDateIdentity='lubricationPlan_' + estimateExecDateName +' _trigger';
	               estimateExecDateSelector=getObjByNameRe(estimateExecDateIdentity);
	               if(lubricationRuleSelector[i].checked){
	                   dutyPeopleSelector[i].disabled = false;
	                   estimateExecDateSelector.disabled=false;
	               }else{
	                   dutyPeopleSelector[i].disabled = true;
	                   estimateExecDateSelector.disabled=true;      
	               }             
                }
      }
       
      
      window.onload=function(){
          /* 禁用日期弹出框 */
           var lubricationRuleSelector = document.getElementsByName("lubricationRuleIds");
           var estimateExecDateName;
           var estimateExecDateIdentity;
           var estimateExecDateSelector;
           for(var lubricationId=0;lubricationId<lubricationRuleSelector.length;lubricationId++){
               estimateExecDateName='estimateExecDate'+(parseInt(lubricationId)+parseInt(1));
               estimateExecDateIdentity='lubricationPlan_' + estimateExecDateName +' _trigger';
               estimateExecDateSelector=getObjByNameRe(estimateExecDateIdentity);
               estimateExecDateSelector.disabled=true;
           }
      }
      
      /* 设置保存后的日期 */
      function setLubricationRuleValue(lubricationInfo){
          var lubricationRuleSelector = document.getElementsByName("lubricationRuleIds");
           var ary=lubricationInfo.split(",");
            var estimateExecDateName;
             var j=0;
             for ( var i = 0; i < lubricationRuleSelector.length; i ++) {
                if(lubricationRuleSelector[i].value==ary[j]){
                    estimateExecDateName='estimateExecDate'+(parseInt(i)+parseInt(1));
                    var estimateExecDateSelector=getObjByNameRe(estimateExecDateName);
                    estimateExecDateSelector.value=ary[j+2];
                    j=parseInt(j)+parseInt(3);
                }
           } 
      }
      
     function confirmSetLubricationValue(boxName, message) {
        if (hasChecked(boxName)) {
           return setLubricationInfo(message);
           return true;
         } else {    	
            alert("${action.getText('lubricationRule.noSelect')}");
             return false;
        }
   }
   
      function validateLength(peopleValue,min,max){
        if(isNotEmptyValue(peopleValue)==true){
	        if(isValidLengthValue(peopleValue,min,max)==false){
	           alert("${action.getText('peopeValue.length')}"+max);
	           return false;
	        }
	     }
	     return true;
     }
      
     function setLubricationInfo(message){
         var lubricationRuleSelector = document.getElementsByName("lubricationRuleIds");
          var dutyPeopleSelector = document.getElementsByName("dutyPeople");
           var estimateExecDateName;
            var returnLubricationAry="";
          if(confirm(message)){
             for ( var i = 0; i < lubricationRuleSelector.length; i ++) {
                 if(lubricationRuleSelector[i].checked){
                    estimateExecDateName='estimateExecDate'+(parseInt(i)+parseInt(1));
                     returnLubricationAry+=lubricationRuleSelector[i].value+",";
                       if(!validateLength(dutyPeopleSelector[i].value,0,50)){
                         return false;
                      }
                      returnLubricationAry+=dutyPeopleSelector[i].value+",";
                      if(getObjByNameRe(estimateExecDateName).value==""){
                         returnLubricationAry+=0+",";
                      }else{
                         returnLubricationAry+=getObjByNameRe(estimateExecDateName).value+",";
                      }                    
                 }
             }
             getObjByNameRe("lubricationInfo").value=returnLubricationAry;
             returnDialog(returnLubricationAry,false);
          }
          return false;
     }
      
	 /* 设置每个checkbox的onclick响应事件 */
        var lubricationRuleSelector = document.getElementsByName("lubricationRuleIds");
        for ( var i = 0; i < lubricationRuleSelector.length; i ++) {
        	lubricationRuleSelector[i].onclick = setSaveValue(i);
        }
      
        /* checkbox响应事件的处理函数，如果checked值为false，就关闭表格中的输入框 */
        function setSaveValue(i){
			return function(){
		  		var dutyPeopleSelector = document.getElementsByName("dutyPeople");
		  		 var lubricationRuleSelector = document.getElementsByName("lubricationRuleIds");
		  		  var estimateExecDateName='estimateExecDate'+(parseInt(i)+parseInt(1));
		  		   var estimateExecDateIdentity='lubricationPlan_' + estimateExecDateName +' _trigger';
		  		    var estimateExecDateSelector=getObjByNameRe(estimateExecDateIdentity);
		  		if (true==lubricationRuleSelector[i].checked) {
	        		dutyPeopleSelector[i].disabled = false;
	        		estimateExecDateSelector.disabled=false;
	        	}else{
	        	    dutyPeopleSelector[i].disabled = true;
	        	    estimateExecDateSelector.disabled=true;
	        	}
		 	}
		 }
		 
		 	
	      	function confirmSelects(boxname) {
	      		if (!hasChecked(boxname)) {
	      			alert("${action.getText('spare.noSelect')}");
	      			return false;
	      		}
	      		chooseLubricationRules(boxname);
	      		return true;
	      	}
	      	
	      	function chooseLubricationRules(boxname) {
	      		var selector = document.getElementsByName(boxname);
	      		var estimateExecDateName;
			    if (!selector) {
			        return false;
			    }
			    
			    var lubricationRuleIds = "";
			    if (selector.length) {
			        for (i = 0; i < selector.length; i++) {
			            if (selector[i].checked) {
			                lubricationRuleIds += selector[i].value + ",";
			                estimateExecDateName='estimateExecDate'+(parseInt(i)+parseInt(1));
			                lubricationRuleIds += getObjByNameRe(estimateExecDateName).value + ",";
			            }
			        }
			    }
			    alert(lubricationRuleIds);
			    
			    returnDialog(lubricationRuleIds,false);
	      	}
      
      </script>
</@htmlPage>