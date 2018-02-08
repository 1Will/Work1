<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: purchaseBillDetails.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../includes/eam2008.ftl" />

<@framePage title="${action.getText('purchaseBillDetails.title')}">	
	<@ww.form name="'listForm'" action="'sercherDiscardBillBillDtl'" method="'post'">
		<@ww.token name="sercherDiscardBillBillDtlsToken"/>
		<@ww.hidden name="'addDeviceIds'" value=""/>
	    <@ww.hidden name="'addDevice'" value=""/>
	    <input type="hidden" name="allmemoInfo" value=""/>
	    <input type="hidden" name="allDiscardDeviceId" value=""/>
	    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if discardBill.id?exists>
		<@ww.hidden name="'discardBill.id'" value="'#{discardBill.id}'"/>
		</#if>
		<@list title="" excel=false setupTable=false includeParameters="discardBill.id" fieldMap="like:discardBill.id">
			<@vlh.checkbox property="id" name="discardBillDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             <input type="hidden" name="hiddenDeviceIds" value="#{object.deviceCard.id}"/>
            <@vcolumn title="${action.getText('deviceCard.deviceNo')}" property="devBillNo" >
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('model')}" property="model" >
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('specification')}" property="specification">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('origPrice')}" property="origPrice" >
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('manufactureDate')}" property="manufactureDate" format="yyyy-MM-dd" >
            	<@alignCenter />
            </@vcolumn>
            <@vcolumn title="${action.getText('useYear')}" property="useYear" >
            	<@alignRight />
            </@vcolumn>
              <@vcolumn title="${action.getText('memo')}">
            	 <input type="text" name="memo" 
	    		   class="underline"  value="${object.memo?if_exists}"  maxlength="250" size="15"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            </@vcolumn>
		</@list>
        <@buttonBar>
        	<#if !first>
        	<#if !(action.isReadOnly())>
        	<@vbutton value="${action.getText('new')}" class="button" onclick="multi_select_device_openDialog()"/> 
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('discardBillDtlIds')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"discardBillDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
             <@vsubmit name="'save'" value="'${action.getText('save')}'">
		       <@ww.param name="'onclick'" value="'return customize_validate();'"/>
		       <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		     </@vsubmit>
		     </#if>
            </#if>
        </@buttonBar>
  <script language="javascript">
     window.onload=function(){ 
       <#if !valueListNoRecords>
       	   <#--控制"确认报废"的checkbox-->
           parent.getObjByNameRe("isTouch").value="true";
       </#if>
     }
     //多选设备到报废单明细中
	
    function multi_select_device_openDialog() {
     var flag='DeviceDiscard';
     <#--var flag='discard';-->
     var url = '${req.contextPath}/popup/deviceSelector.html';
     <#if !valueListNoRecords>
	   var oldDeviceIds = document.getElementsByName("hiddenDeviceIds");
	   var ary = new Array();
	   for (var i=0; i<oldDeviceIds.length; i++) {
	     ary.push(oldDeviceIds[i].value);
	   }
	   eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,ary,flag);
	 <#else>
	   eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,null,flag);
	 </#if>
    }
    function refresh_multi_device_information(reslut) {
      if (null != result) {
        var addDeviceIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addDeviceIds"].value = addDeviceIds;
        document.forms[0].elements["addDevice"].value = "addDevices";
        document.forms[0].submit();
      }
    }
    function customize_validate(){
    
      retrieveDiscardBillDtlIdsText();//获得所有报废单明细的Ids
      retrieveDiscardBillDtlMemoText();//获得所有报废明细的备注
      return true;
    }
  function   retrieveDiscardBillDtlIdsText(){
     var allDiscardDeviceId=document.getElementsByName("discardBillDtlIds"); 
      var ary=new Array();
            for (var i=0; i<allDiscardDeviceId.length; i++) {
             ary.push(allDiscardDeviceId[i].value);
            }
   document.forms[0].elements["allDiscardDeviceId"].value = ary;
    }
  function  retrieveDiscardBillDtlMemoText(){
       var allDiscardDeviceId = document.getElementsByName("discardBillDtlIds");
         var allmemoInfo=document.getElementsByName("memo");
         var ary = new Array();
         for(var i=0; i<allDiscardDeviceId.length; i++) {
             if ('' != allmemoInfo[i].value) {
             ary.push(allDiscardDeviceId[i].value);
             ary.push(allmemoInfo[i].value);
         }
       }
     document.forms[0].elements["allmemoInfo"].value=ary;
    
  }  
 </script>
</@ww.form>
	 
</@framePage>