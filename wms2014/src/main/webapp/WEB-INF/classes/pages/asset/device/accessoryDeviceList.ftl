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
<#include "../../includes/eam2008.ftl" />

<#-- $Id: accessoryDeviceList.ftl 11246 2008-03-10 11:35:30Z wzou $ -->
<@framePage title="">
     <@ww.form name="'accessoryDevice'" action="'searchAccessoryDevices'" method="'post'">
     <@ww.token name="searchAccessoryDevicesToken"/>
     	<@ww.hidden name="'device.id'" value="'#{device.id}'"/>
	    <#assign itemNo=1/>
	    <@list title="" excel=false setupTable=false includeParameters="device.id" fieldMap="like:" >
            <@vlh.checkbox property="id" name="accessoryDeviceIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('serialNo')}">
              <#if !(action.isReadOnly())>
                <a href="#" onclick="open_detailDialog(#{device.id}, #{object.id});return false;">#{itemNo}</a>
              <#else>
                #{itemNo}
              </#if>
            	<@alignCenter />
            </@vcolumn>
            <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('accessoryDevice.name')}" property="name">
            	<@alignLeft />
            </@vcolumn>
        </@list>
        	<#if !first>
        	<#if !(action.isReadOnly())>
	        	<@buttonBar>
	        		<@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_detailDialog(#{device.id}, null)"/>
		            <#if (device.accessoryDevices.size()>0)>
		             <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('accessoryDevice')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		            		<@ww.param name="'onclick'" value="'return confirmDeletes(\"accessoryDeviceIds\", \"${confirmMessage}\");'"/>
		            </@vsubmit>
		            </#if>
		        </@buttonBar>
		     </#if>
            </#if>
	    <script language="javascript">
	      	function open_detailDialog(deviceId, accessoryDeviceId) {
		      		var url = '${req.contextPath}/asset/device/editAccessoryDevice.html?device.id='+deviceId;	      		
		      		if (accessoryDeviceId != null) {
		      			url = url + "&accessoryDevice.id=" + accessoryDeviceId;
		      		}
		      		popupModalDialog(url,650,300);
		      		self.location.reload();
		      	}
	    </script>
     </@ww.form>
</@framePage>