<#--$Id: deviceList.ftl 6962 2007-09-07 04:01:04Z qsun $ -->
<#include "../includes/macros.ftl" />
<@htmlPage title="${action.getText('deviceSelector.title')}">
	 <@ww.form name="'listForm'" namespace="'/popup'" action="'searchSelectDevices'" method="'post'">
	 	<@ww.token name="searchSelectDevicesToken"/>
	 	<#include "../asset/device/deviceSearcher.ftl"/>
	 	<@buttonBar> 
	 		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 

	 		<@vsubmit name="'close'" value="'${action.getText('close')}'" onclick="'window.close();'"/>
	 	</@buttonBar> 
	 	<@list title="${action.getText('device.list')}" 
        	includeParameters="id|deviceNo|name|cardCreatedTime_start|cardCreatedTime_end|invalid|department.id" 
        	fieldMap="like:id|deviceNo|name,date:cardCreatedTime_start|cardCreatedTime_end" >
            <@vlh.checkbox property="id" name="deviceIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('device.no')}" property="deviceNo"sortable="desc">
            </@vcolumn>
            <@vcolumn title="${action.getText('device.assetno')}" property="assetNo" sortable="desc"/>
            <@vcolumn title="${action.getText('device.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('device.model')}" property="model" sortable="desc"/>
            <@vcolumn title="${action.getText('device.specification')}" property="specification" sortable="desc"/>
            <@vcolumn title="${action.getText('device.category')}"  property="category" sortable="desc"/> 
            <@vcolumn title="${action.getText('device.department')}"  property="department.name" sortable="desc"/>
            <@vcolumn title="${action.getText('device.installPlace')}" property="installPlace" sortable="desc"/>
            <@vcolumn title="${action.getText('device.cardCreatedTime')}" property="cardCreatedTime" format="yyyy-MM-dd" sortable="desc"/>
        </@list>
    
        <@buttonBar>
        	
        
        	<#if !first>
	 			<@vsubmit name="'select'" value="'${action.getText('select')}'">
	 				<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	 			</@vsubmit>
	 		</#if>
        	<#--
			<@vsubmit name="'close'" value="'${action.getText('close')}'" >
				<@ww.param name="'onclick'" value="'window.close();'"/>
			</@vsubmit>
			-->
        </@buttonBar>
        <script language="javascript">
		    function checkInvalidParms(){		    		
		          if (getObjByNameRe("department.id").value==-1) {
		          	getObjByNameRe("invalid").value=true;
		          	getObjByNameRe("department.id").value='';
		          } else {
		          	getObjByNameRe("invalid").value=false;
		          }
		          return true;
		      }
		 </script>
	 </@ww.form>
</@htmlPage>