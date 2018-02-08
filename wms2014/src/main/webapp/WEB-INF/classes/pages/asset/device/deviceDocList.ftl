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

<@framePage title="${action.getText('techDoc.title')}">
     <@ww.form  name="'listForm'" action="'listDeviceDocs'" method="'post'">
		 <@ww.token name="searchlistDeviceDocsToken"/>
		 <input type="hidden" name="device.id" value="#{device.id}"/>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
		 <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false
        		includeParameters="device.id" 
        		fieldMap="like:device.id" >
        		<@vlh.checkbox property="id" name="deviceDocIds">
		            	<@vlh.attribute name="width" value="30" />
		            </@vlh.checkbox>
	            <@vcolumn title="${action.getText('serialNo')}" property="">
	              <#if !(action.isReadOnly())>
	            	<a href="#" onclick="open_uploadDialog(#{object.device.id}, #{object.id});return false;">#{itemNo}</a>
	              <#else>
	                #{itemNo}
	              </#if>
	              	<@alignCenter/>
	            </@vcolumn>
	            <#assign itemNo = itemNo+1/>
	            <@vcolumn title="${action.getText('device.doc.name')}" property="">
	            	<a  title="${action.getText('download')}" href="downloadDeviceDoc.html?doc.id=#{object.id}" >${object.name?if_exists}</a>
	            	<@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('device.doc.description')}" property="description">
                 <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('device.doc.createdTime')}" property="createdTime" format="yyyy-MM-dd">
	                 <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('device.doc.operator')}" property="creator">
	                 <@alignCenter/>
	            </@vcolumn>
        </@list>
       <#if !(action.isReadOnly()) || '${eamAuthentication?if_exists}' == 'Write'>
        <@buttonBar>
	         	<#assign confirmMessage = "${action.getText('deviceDoc.deleted')}" />
	            <@vbutton name="'upload'" class="button" value="${action.getText('upload')}" onclick="open_uploadDialog(#{device.id}, null);"/>
	         	<#if (device.deviceDoc.size()>0)>
	         	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"deviceDocIds\", \"${confirmMessage}\");'"/>
	            </@vsubmit>
	            </#if>
	    </@buttonBar>
	   </#if>
	</@ww.form>
     <script language="javascript">
			<@eam2008_LockPageIfNeed/>
			/*
			 *当该设备失效时,锁定页面所有控件
			*/
			<#if device.enabled?exists>
	          <#if !(device.enabled)>
	             __disableAllElements__(document.forms[0], new Array(""));
	          </#if>
	        </#if>
	    	function open_uploadDialog(deviceId, deviceDocId) {
	    		var url;
	    		if (null == deviceDocId) {
	    			 url = '${req.contextPath}/popup/editDeviceDoc.html?device.id='+deviceId;
	    		} else {
	    			 url = '${req.contextPath}/popup/editModifyDeviceDoc.html?device.id=' + deviceId + '&doc.id=' + deviceDocId;
	    		}
	    		popupModalDialog(url, 650, 300);
	    		self.location.reload();
	    	}
	 </script>
</@framePage>