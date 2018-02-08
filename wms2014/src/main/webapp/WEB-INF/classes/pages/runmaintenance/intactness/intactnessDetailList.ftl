<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('instactnessDetailList.title')}">
  <@ww.form namespace="'/runmaintenance/intactness'" name="'intactnessDetail'" action="'saveIntactnessDetails'" method="'post'">
    <@ww.token name="saveIntactnessDetailsToken"/>
    <#if intactnessBill?exists>
      <@ww.hidden name="'intactnessBill.id'" value="#{intactnessBill.id}"/>
    </#if>
    <@ww.hidden name="'addDeviceIds'" value=""/>
    <@ww.hidden name="'addDevice'" value=""/>
    <@ww.hidden name="'allIntactnessDetailResult'" value=""/>
    <@ww.hidden name="'allComment'" value=""/>
    <@ww.hidden name="'allIntactnessDetailId'" value=""/>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <#assign itemNo = 1/>
     <#assign intactnessResultIdentityName = 'intactnessResult' + '${itemNo}'/>
    <#assign intactnessResultIdentityName = 'intactnessResult' + '${itemNo}'/>
    <@list title="" excel=false setupTable=false
           includeParameters="intactnessBill.id" 
           fieldMap="like:" >
      <input type="hidden" name="detailIds" value="#{object.id}"/>
      <@vlh.checkbox property="id" name="intactnessDetailIds">
	    <@vlh.attribute name="width" value="30" />
	  </@vlh.checkbox>
	  <@vcolumn title="${action.getText('intactnessDetail.itemNo')}">
	    ${itemNo}
	    <@alignCenter/>
      </@vcolumn>
      <#assign itemNo = itemNo+1/>
      <@vcolumn title="${action.getText('intactnessDetail.deviceNo')}" property="device.deviceNo">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('intactnessDetail.assetNo')}" property="device.assetNo">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('intactnessDetail.deviceName')}" property="device.name">
	    <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('intactnessDetail.intactnessResult')}">
        <select name="${intactnessResultIdentityName}">
	      <@ww.iterator value="intactnessResults" id="intactnessResult">
	        <option value="<@ww.property value="value"/>"><@ww.property value="label"/></option>
	      </@ww.iterator>
        </select>
        <script language="javascript">
          <#if object.result?exists>
            document.forms[0].elements["${intactnessResultIdentityName}"].value='${object.result?if_exists}';
          </#if>
        </script>
        <#assign intactnessResultIdentityName = 'intactnessResult' + '${itemNo}'/>
        <@vlh.attribute name="style" value="VERTICAL-ALIGN:text-bottom"/>
	   </@vcolumn>
	   <@vcolumn title="${action.getText('intactnessDetail.comment')}">
	        <input type="text" name="comment" 
	    		   class="underline"  value="${object.comment?if_exists}"  maxlength="250" size="15"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	   </@vcolumn>
    </@list>
    <#if !first>
	  <@buttonBar>
	  <#if !(action.isReadOnly())>
	    <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="multi_select_device_openDialog();"/>
	 	
	 	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('intactnessBill.detail')}?" />
        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
          <@ww.param name="'onclick'" value="'return confirmDeletes(\"intactnessDetailIds\", \"${confirmMessage}\")'"/>
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
        
        <@vsubmit name="'save'" value="'${action.getText('save')}'">
          <@ww.param name="'onclick'" value="'return validate();'"/>
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
        </#if> 
	  </@buttonBar>
	</#if>
  </@ww.form>
  <script>
    /*
     * 新建设备鉴定明细
    */
    function multi_select_device_openDialog() {
      var flag='DeviceIntactness';
      var url = '${req.contextPath}/popup/deviceSelector.html';
      eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,null,flag);
    }
    function refresh_multi_device_information(reslut) {
      if (null != result) {
        var addDeviceIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addDeviceIds"].value = addDeviceIds;
        document.forms[0].elements["addDevice"].value = "addDevices";
        document.forms[0].submit();
      }
    }
    function validate() {
      retrieveIntactnessDetailIdText();
      retrieveIntactnessDetailResultText();
      retrieveComment();
      return true;
    }
    /*
     * 获取鉴定明细ID的所有值
    */
    function retrieveIntactnessDetailIdText() {
	  var allIntactnessDetailId = document.getElementsByName("detailIds");
	  var ary = new Array();
	  for (var i=0; i<allIntactnessDetailId.length; i++) {
	    ary.push(allIntactnessDetailId[i].value);
	  }
	  document.forms["intactnessDetail"].elements["allIntactnessDetailId"].value = ary;
    }
    /*
     * 获取明细列表中所有的鉴定结果的值
    */
    function retrieveIntactnessDetailResultText() {
      var allIntactnessDetailId = document.getElementsByName("detailIds");
      var ary = new Array();
      for (var i=0; i<allIntactnessDetailId.length; i++) {
        var intactnessResultTagName = 'intactnessResult';
        intactnessResultTagName = intactnessResultTagName + (i+1); 
        if(-1 != document.forms["intactnessDetail"].elements[intactnessResultTagName].value) {
          ary.push(allIntactnessDetailId[i].value);
          ary.push(document.forms["intactnessDetail"].elements[intactnessResultTagName].value);
        }
      }
      document.forms["intactnessDetail"].elements["allIntactnessDetailResult"].value = ary;
     }
     /*
      * 获取明细列表中详细描述的所有值
     */
     function retrieveComment() {
       var allComment = document.getElementsByName("comment");
       var allIntactnessDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allIntactnessDetailId.length; i++) {
         if ('' != allComment[i].value) {
           ary.push(allIntactnessDetailId[i].value);
           ary.push(allComment[i].value);
         }
       }
       document.forms["intactnessDetail"].elements["allComment"].value=ary;
     }
  </script>
</@framePage>