<#include "../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" action="'searchApplicationDoc'" method="'post'">
		<@ww.token name="searchApplicationDocToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if advisory?exists>
		<#if advisory.id?exists>
            <@ww.hidden name="'advisory.id'" value="#{advisory.id}"/>
        </#if>
        </#if>
        <#if backVisit?exists>
        <#if backVisit.id?exists>
            <@ww.hidden name="'backVisit.id'" value="#{backVisit.id}"/>
        </#if>
        </#if>
        <#if projectInfo?exists>
        <#if projectInfo.id?exists>
            <@ww.hidden name="'projectInfo.id'" value="#{projectInfo.id}"/>
        </#if>
        </#if>
        
        <#if contractManagement?exists>
        <#if contractManagement.id?exists>
            <@ww.hidden name="'contractManagement.id'" value="#{contractManagement.id}"/>
        </#if>
        </#if>
        
        <#if financialManagement?exists>
        <#if financialManagement.id?exists>
            <@ww.hidden name="'financialManagement.id'" value="#{financialManagement.id}"/>
        </#if>
        </#if>
        
        <#if products?exists>
        <#if products.id?exists>
            <@ww.hidden name="'products.id'" value="#{products.id}"/>
        </#if>
        </#if>
        
        <#if supplier?exists>
        <#if supplier.id?exists>
            <@ww.hidden name="'supplier.id'" value="#{supplier.id}"/>
        </#if>
        </#if>
        <#if contractAdministrator?exists>
        <#if contractAdministrator.id?exists>
            <@ww.hidden name="'contractAdministrator.id'" value="#{contractAdministrator.id}"/>
        </#if>
        </#if>
        <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false includeParameters="advisory.id|id|applicationDoc.id|projectInfo.id" fieldMap="like:" >
			<@vlh.checkbox property="id" name="applicationDocIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
			<@vcolumn title="${action.getText('applicationDoc.fileNo')}">
				<#if !(action.isReadOnly())>
				<a href="" onclick="uploadEdit(#{object.id})">#{itemNo}</a>
				<#else>
				#{itemNo}
				</#if>
			<@alignCenter />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
			<@vcolumn title="${action.getText('applicationDoc.name')}" property="name">
				<a href="downloadApplicationDoc.html?applicationDoc.id=#{object.id}" title="${action.getText('download')}">${object.name}</a>
			<@alignLeft attributes="width:500;"/>
			</@vcolumn>  
			<@vcolumn title="${action.getText('applicationDoc.creator')}" property="creatorName" >
			<@alignLeft/>
			</@vcolumn>
			<@vcolumn title="${action.getText('applicationDoc.uploadDate')}"  property="uploadDate" format="yyyy-MM-dd">
        		<@alignCenter />
      		</@vcolumn>
		</@list>
		<#if !(action.isReadOnly())>
		<@buttonBar>
			<@vbutton name="'upload'"  class="button" value="${action.getText('upload')}" onclick="upload()"/>
            <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('applicationDoc.info')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"applicationDocIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
    </@ww.form>
    
<script type="text/javascript">
function upload(){
		<#if advisory?exists>
		<#if advisory.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?advisory.id='+#{advisory.id};
			//popupModalDialog(url,750,500);
			openNewWindow(url);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
		<#if backVisit?exists>
			<#if backVisit.id?exists>
				url= '${req.contextPath}/applicationDocManager/editApplicationDoc.html?backVisit.id='+#{backVisit.id};
       			//popupModalDialog(url,750,500);
       			openNewWindow(url);
				if(isIE()){self.location.reload();};
       		</#if>
       	</#if>
       		
		<#if projectInfo?exists>
        	<#if projectInfo.id?exists>
				var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?projectInfo.id='+#{projectInfo.id};
       			//popupModalDialog(url,750,500);
       			openNewWindow(url);
				if(isIE()){self.location.reload();};
       		</#if>
		</#if>
		
		<#if contractManagement?exists>
        	<#if contractManagement.id?exists>
				var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?contractManagement.id='+#{contractManagement.id};
       			//popupModalDialog(url,750,500);
       			openNewWindow(url);
				if(isIE()){self.location.reload();};
       		</#if>
		</#if>
		
		<#if financialManagement?exists>
        	<#if financialManagement.id?exists>
				var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?financialManagement.id='+#{financialManagement.id};
       			//popupModalDialog(url,750,500);
       			openNewWindow(url);
				if(isIE()){self.location.reload();};
       		</#if>
		</#if>
		
		<#if products?exists>
        	<#if products.id?exists>
				var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?products.id='+#{products.id};
       			//popupModalDialog(url,750,500);
       			openNewWindow(url);
				if(isIE()){self.location.reload();};
       		</#if>
		</#if>
		
		<#if supplier?exists>
        <#if supplier.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?supplier.id=#{supplier.id}';
			//popupModalDialog(url,750,500);
			openNewWindow(url);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
		<#if contractAdministrator?exists>
		<#if contractAdministrator.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?contractAdministrator.id='+#{contractAdministrator.id};
			//popupModalDialog(url,750,500);
			openNewWindow(url);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
	}
function uploadEdit(id){
		<#if advisory?exists>
		<#if advisory.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?advisory.id='+#{advisory.id}+'&applicationDoc.id='+id;
			openNewWindow(url);
			//popupModalDialog(url,750,500);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
		
		<#if supplier?exists>
        <#if supplier.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?supplier.id='+#{supplier.id}+'&applicationDoc.id='+id;
			//popupModalDialog(url,750,500);
			openNewWindow(url);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
		
		<#if backVisit?exists>
        <#if backVisit.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?backVisit.id='+#{backVisit.id}+'&applicationDoc.id='+id;
			//popupModalDialog(url,750,500);
			openNewWindow(url);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
		
		<#if projectInfo?exists>
        <#if projectInfo.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?projectInfo.id='+#{projectInfo.id}+'&applicationDoc.id='+id;
			//popupModalDialog(url,750,500);
			openNewWindow(url);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
		
		<#if contractAdministrator?exists>
		<#if contractAdministrator.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?contractAdministrator.id='+#{contractAdministrator.id}+'&applicationDoc.id='+id;
			//popupModalDialog(url,750,500);
			openNewWindow(url);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
		
		<#if contractManagement?exists>
		<#if contractManagement.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?contractManagement.id='+#{contractManagement.id}+'&applicationDoc.id='+id;
			//popupModalDialog(url,750,500);
			openNewWindow(url);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
		
		<#if financialManagement?exists>
		<#if financialManagement.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?financialManagement.id='+#{financialManagement.id}+'&applicationDoc.id='+id;
			//popupModalDialog(url,750,500);
			openNewWindow(url);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
		
		<#if products?exists>
		<#if products.id?exists>
			var url = '${req.contextPath}/applicationDocManager/editApplicationDoc.html?products.id='+#{products.id}+'&applicationDoc.id='+id;
			//popupModalDialog(url,750,500);
			openNewWindow(url);
			if(isIE()){self.location.reload();};
		</#if>
		</#if>
	}	
</script>
</@framePage>