
<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->
<#-- $Id: -->

<#include "../../includes/eam2008.ftl" />

<@framePage title="${action.getText('sapre.title')}">
	<style type="text/css">
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
        width:85%;
    }
  </style>
  <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>
     <@ww.form name="'spare'" action="'searchToolingDevSpares'" method="'post'">
     <@ww.token name="searchlistToolingDevSparesToken"/>
     	<@ww.hidden name="'toolingDev.id'" value="'#{toolingDev.id?if_exists}'"/>
     	<@ww.hidden name="'addSpares'" value="''"/>
     	<@ww.hidden name="'addSpareIds'" value="''"/>
     	<@ww.hidden name="'toolingDevFlag'" value="'${req.getParameter('toolingDevFlag')?if_exists}'"/>
     	<@ww.hidden name="'saveSpareIds'" value="''"/>
     	<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
     	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
	    	<#assign itemNo=1/>
	    	<#assign lastReplaceTimeIdentityName = 'spare.Date' + '${itemNo}'/>
	    	<#assign lastReplaceTimeImgIdentity = 'spare_' + '${lastReplaceTimeIdentityName}' + '_trigger'/>
	    	<@list title="" excel=false setupTable=false 
     	           includeParameters="toolingDev.id|toolingDevFlag|eamAuthentication|readOnly"  fieldMap="like:" >
     	      <#if object.spare?exists>
	              <input type="hidden" name="hiddenSpareIds" value="#{object.spare.id}"/>
	            </#if>
     	      <@vlh.checkbox property="id" name="spareIds">
                <@vlh.attribute name="width" value="30" />
              </@vlh.checkbox>
              <@vcolumn title="${action.getText('spare.serialNo')}">
                	#{itemNo}
                <@alignCenter />
              </@vcolumn>
              <#assign itemNo = itemNo + 1/>
              <@vcolumn title="${action.getText('spare.spareNo')}" property="spare.spareNo">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('spare.chName')}" property="spare.name">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('spare.enName')}" property="spare.enName">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('spare.specific')}" property="spare.modelSpecs">
                 <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('spare.specification')}" property="spare.specification">
                <@alignLeft />
              </@vcolumn>
              <#if (object.spare.tenderPartFlag?string)=='true'>
	              <#assign tenderPartFlagStatus="${action.getText('YES')}"/>
	            <#else>
	              <#assign tenderPartFlagStatus="${action.getText('NO')}"/>
	            </#if>
              <@vcolumn title="${action.getText('spare.tenderPartFlag')}" >
              	${tenderPartFlagStatus}
                <@alignLeft />
              </@vcolumn>
              <#if (object.spare.wearingPartFlag?string)=='true'>
	              <#assign tenderWearingPartFlag="${action.getText('YES')}"/>
	            <#else>
	              <#assign tenderWearingPartFlag="${action.getText('NO')}"/>
	            </#if>
              <@vcolumn title="${action.getText('spare.wearingPartFlag')}" >
              	${tenderWearingPartFlag}
                <@alignLeft />
              </@vcolumn>
              <#if (object.spare.heavyRepairPartFlag?string)=='true'>
	              <#assign tenderHeavyRepairPartFlag="${action.getText('YES')}"/>
	            <#else>
	              <#assign tenderHeavyRepairPartFlag="${action.getText('NO')}"/>
	            </#if>
              <@vcolumn title="${action.getText('spare.heavyRepairPartFlag')}" >
              	${tenderHeavyRepairPartFlag}
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('spare.category')}" property="spare.category.value">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('spare.number')}">
              	<input type="text" name="spare.number" class="definedLength"  value="${object.number?if_exists}" maxlength="9"/>
                <@alignRight />
              </@vcolumn>
              <@vcolumn title="${action.getText('spare.safeStock')}" property="spare.safeStock">
                <@alignRight />
              </@vcolumn>
              <@vcolumn title="${action.getText('spare.allStock')}" property="spare.stocks">
                <@alignRight />
              </@vcolumn>
              <@vcolumn title="${action.getText('spare.installPlace')}" >
              	<input type="text" name="deviceSpare.installSpace" class="definedLength"  value="${object.installPlace?if_exists}" maxlength="250"/>
                <@alignRight />
              </@vcolumn>
              
              <@vcolumn title="${action.getText('spare.lastReplaceTime')}">
	              <#assign lastReplaceTime = ''/>
	              <#if object.lastReplaceTime?exists>
			          <#assign lastReplaceTime = "${(object.lastReplaceTime?string('yyyy-MM-dd'))}"/>
			      </#if>
			      <@eam2008_dataPicker inputName="${lastReplaceTimeIdentityName}" inputId="${lastReplaceTimeIdentityName}" inputValue="${lastReplaceTime}" imgId="${lastReplaceTimeImgIdentity}" formName="spare"/>
				    <#assign lastReplaceTimeIdentityName = 'spare.Date' + '${itemNo}'/>
	    			<#assign lastReplaceTimeImgIdentity = 'spare_' + '${lastReplaceTimeIdentityName}' + '_trigger'/>
				    <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
              </@vcolumn>
              <#assign flag="${req.getParameter('toolingDevFlag')?if_exists}"/>
              <#if flag?exists>
			      <#if (flag=='DEVICE')>
		              <@vcolumn title="${action.getText('device.spare.amount')}">
		              	<input type="text" name="spare.amount" class="definedLength"  value="${object.amount?if_exists}"  maxlength="9"/>
		                <@alignRight />
		              </@vcolumn>
		          <#else>
					  <@vcolumn title="${action.getText('tooling.spare.amount')}">
		              	<input type="text" name="spare.amount" class="definedLength"  value="${object.amount?if_exists}"  maxlength="9"/>
		                <@alignRight />
		              </@vcolumn>
	          	</#if>
	          </#if>
              <@vcolumn title="${action.getText('spare.useTime')}" property="useTime">
                <@alignRight />
              </@vcolumn>
     	    </@list>
     	    <#if !first>
     	    <#if !(action.isReadOnly()) || '${eamAuthentication?if_exists}' == 'Write'>
     	    <@buttonBar>
	            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="spare_openDialog();"/>
	            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('spare')}?" />
	            <#if (toolingDev.spares.size()>0)>
	            	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitSpareIds()'"/>
	            	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	            		<@ww.param name="'onclick'" value="'return confirmDeletes(\"spareIds\", \"${confirmMessage}\");'"/>
	            	</@vsubmit>
	            </#if>
            </@buttonBar>
            </#if>
            </#if>
     </@ww.form>
     <script language="javascript">
	   <@eam2008_LockPageIfNeed/>
			 /*
	         function open_spareDialog(deviceId, spareId) {
	      		var url = '${req.contextPath}/popup/editSpare.html?device.id=' + deviceId;	 
	      		//alert(url);     		
	      		if (spareId != null) {
	      			url = url + "&spare.id=" + spareId;
	      			//alert(url);
	      		}
	      		popupModalDialog(url, 650,300);
	      		self.location.reload();
	      	 }
	      	 */
	     /*
		  *当该设备失效时,锁定页面所有控件
		 */
		<#if toolingDev.enabled?exists>
	      <#if !(toolingDev.enabled)>
	             __disableAllElements__(document.forms[0], new Array(""));
	      </#if>
	    </#if>
	     function addNewSpare(result) {
	       if (result != undefined) {
	         spareIds = result.substring(0, result.lastIndexOf(","));
	         document.forms[0].elements["addSpareIds"].value = spareIds;
	         document.forms[0].elements["addSpares"].value = "addSpares";
	         document.forms[0].submit();
	       }
	      }
	      
	 function validateInstallPlace(installPlaceValue,min,max){
	    if(isNotEmptyValue(installPlaceValue)==true){
	        if(!isValidLengthValue(installPlaceValue,min,max)){
	           alert("${action.getText('installPlaceValue.length')}"+max);
	           return false;
	        }
	     }
	     return true;
	  }
	      function spare_openDialog() {
	        var url = '${req.contextPath}/popup/spareSelector.html?toolingDevFlag=${req.getParameter('toolingDevFlag')?if_exists}&&inoutFlag='+'T';
	        <#if (toolingDev.spares.size()>0)>
	          var oldSpareIds = document.getElementsByName("hiddenSpareIds");
	          var ary = new Array();
	          for (var i=0 ; i<oldSpareIds.length; i++) {
	            ary.push(oldSpareIds[i].value);
	          }
	          url = url + '&oldSpareIds=' + ary;
	        </#if>
	        popupModalDialog(url,800,600,addNewSpare); 
	      }
	      
	      function submitSpareIds(){
	         var spareIdSelector=document.getElementsByName("spareIds");
	         var installSpaceSelector=document.getElementsByName("deviceSpare.installSpace");
	         var number = document.getElementsByName("spare.number");
	         var amount = document.getElementsByName("spare.amount");
	         var spareIds="";
	         if (spareIdSelector.length) {
	             for (var i = 0; i < spareIdSelector.length; i++){
	               if(!validateInstallPlace(installSpaceSelector[i].value,0,150)){
	                   return false;
	               }
	                spareIds+=spareIdSelector[i].value+",";
	                spareIds+=installSpaceSelector[i].value+",";
					if (number[i].value != null && number[i].value != ""){
		                if(!validateNum(number[i].value)){
		                    return false;
		                }
	                }
	                spareIds+=formatDigital(number[i].value)+",";
	                if (amount[i].value != null && amount[i].value != ""){
		                if(!validateAmount(amount[i].value)){
		                    return false;
		                }
	                }
	                spareIds+=formatDigital(amount[i].value)+",";
	                spareIds+=document.forms["spare"].elements["spare.Date"+[i+1]].value+" ,";
	                //alert(spareIds);
	             }
	         }
	         spareIds=spareIds.substring(0,spareIds.lastIndexOf(","));
	         getObjByNameRe("saveSpareIds").value=spareIds;
	         return true;
	      }
		     function validateNum(number){
			      number = formatDigital(number);
			      var control = isNumberBetween(number,1000000001,0);
				  if(control!=1){
		              alert("${action.getText('select.right.number')}");
		              return false;
		          }
				  return true;
			  }
			  function validateAmount(amount){
			      amount = formatDigital(amount);
			      var control = isNumberBetween(amount,1000000001,0);
				  if(control!=1){
				  	  if(document.forms[0].elements["toolingDevFlag"].value == 'DEVICE'){
		              	alert("${action.getText('select.right.device.amount')}");
		              }else{
		              	alert("${action.getText('select.right.tooling.amount')}");
		              }
		              return false;
		          }
				  return true;
			  }
	    </script>
</@framePage>