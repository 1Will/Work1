<#include "../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'"  action="'searchCustomerProduct'" namespace="'/customerRelationship'" method="'post'">
		<@ww.token name="searchCustomerProductToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        
        <#if customerInfoId?exists>
			<@ww.hidden name="'customerInfo.id'" value="#{customerInfoId}"/>
		</#if>
		
        <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false includeParameters="" 
		fieldMap="like:" >
			<@vlh.checkbox property="id" name="customerProductIds">
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
			<@vcolumn title="${action.getText('customerProduct.productName')}" property="productName">
				<@alignLeft/>
            </@vcolumn>
            <#--
			<@vcolumn title="${action.getText('customerProduct.pictureName')}" property="pictureName">
				<a href="downloadCustomrProduct.html?customerProduct.id=#{object.id}" title="${action.getText('download')}">${object.pictureName}</a>
			<@alignLeft attributes="width:500;"/>
			</@vcolumn> 
			-->
			 <@vcolumn title="${action.getText('customerProduct.productDescribe')}" property="productDescribe">
				<@alignLeft/>
            </@vcolumn>
		</@list>
		<@buttonBar>
			<@vbutton name="'upload'"  class="button" value="${action.getText('upload')}" onclick="upload()"/>
            <#assign confirmMessage = "${action.getText('delete.msg')}" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"customerProductIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
        </@buttonBar>
    </@ww.form>
    
<script type="text/javascript">
function upload(){
      var url = '${req.contextPath}/customerRelationship/editCustomerProduct.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId}';
	  openNewWindow(url);
	  if(isIE()){self.location.reload();};
	}
function uploadEdit(caId){
	   var url = "${req.contextPath}/customerRelationship/editCustomerProduct.html?customerProdust.id="+caId+"&readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId}";
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	}	
</script>
</@framePage>