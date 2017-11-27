<#include "../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'"  action="'searchCustomerNews'" namespace="'/customerRelationship'" method="'post'">
		<@ww.token name="searchCustomerNewsToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        
        <#if customerInfoId?exists>
			<@ww.hidden name="'customerInfo.id'" value="#{customerInfoId}"/>
		</#if>
		
        <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false includeParameters="" 
		fieldMap="like:" >
			<@vlh.checkbox property="id" name="customerNewsIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
			<@vcolumn title="${action.getText('number')}">
				<#if !(action.isReadOnly())>
				<a href="" onclick="uploadEdit(#{object.id})">#{itemNo}</a>
				<#else>
				#{itemNo}
				</#if>
			<@alignLeft />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
			<@vcolumn title="${action.getText('customerNews.title')}" property="title">
				<@alignLeft/>
            </@vcolumn>
            <#--
			<@vcolumn title="${action.getText('customerProduct.pictureName')}" property="pictureName">
				<a href="downloadCustomrProduct.html?customerProduct.id=#{object.id}" title="${action.getText('download')}">${object.pictureName}</a>
			<@alignLeft attributes="width:500;"/>
			</@vcolumn> 
			-->
			 <@vcolumn title="${action.getText('customerNews.content')}" property="content">
				<@alignLeft/>
            </@vcolumn>
		</@list>
		<@buttonBar>
			<@vbutton name="'upload'"  class="button" value="${action.getText('upload')}" onclick="upload()"/>
            <#assign confirmMessage = "${action.getText('delete.msg')}" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"customerNewsIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
        </@buttonBar>
    </@ww.form>
    
<script type="text/javascript">
function upload(){
      var url = '${req.contextPath}/customerRelationship/editCustomerNews.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId}';
	  openNewWindow(url);
	  if(isIE()){self.location.reload();};
	}
function uploadEdit(caId){
	   var url = "${req.contextPath}/customerRelationship/editCustomerNews.html?customerNews.id="+caId+"&readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId}";
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	}	
</script>
</@framePage>