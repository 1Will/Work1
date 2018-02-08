<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('device.operateCert')}">
	<@ww.form namespace="'/asset/device'" name="'operateCert'" action="'listOperateCert'" method="'post'">
		 <@ww.token name="searchlistOperateCertToken"/>
		 <input type="hidden" name="device.id" value="#{device.id}"/>
		<@list title="" excel=false setupTable=false
        		includeParameters="device.id" 
        		fieldMap="like:device.id" >
        		<@vlh.checkbox property="id" name="operateCertIds">
		            	<@vlh.attribute name="width" value="30" />
		        </@vlh.checkbox>
	            <@vcolumn title="${action.getText('operateCert.code')}" property="code" sortable="desc">
	              	<a href="#" onclick="open_new(#{device.id},#{object.id})">${object.code}</a>
	              	<@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('operateCert.name')}" property="name" sortable="desc">
	            	<@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('operateCert.userCode')}" property="userCode">
                 <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('operateCert.userName')}" property="userName">
	                 <@alignLeft/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('operateCert.examDate')}" property="examDate" format="yyyy-MM-dd">
	                 <@alignCenter/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('operateCert.examScore')}" property="examScore">
	                 <@alignRight/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('operateCert.examiner')}" property="examiner">
	                 <@alignLeft/>
	            </@vcolumn>
        </@list>
        <@buttonBar>
	         	<#assign confirmMessage = "${action.getText('operateCert.deleted')}" />
	            <@vbutton name="'new'" class="button" value="${action.getText('new')}" onclick="open_new(#{device.id},null);"/>
	         	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return validateDelete(confirmDeletes(\"operateCertIds\", \"${confirmMessage}\"), checkInvalidParms())'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            	</@vsubmit>
	    </@buttonBar>
	</@ww.form>
	<script language="javascript">
	    	function open_new(deviceId,operateCertId) {
	    		var url = '${req.contextPath}/popup/editOperateCerts.html?device.id='+deviceId;
	    		if (operateCertId != null) {
	    			 url = url + "&operateCert.id=" + operateCertId;
	    		}
	    		popupModalDialog(url, 800, 600);
	    		self.location.reload();
	    	}
	    	function validateDelete(delFun, checkFun) {
		        if (delFun) {
		        	checkFun;
		         	return true;
		         }
		        return false;
		    }
	 </script>
</@framePage>
