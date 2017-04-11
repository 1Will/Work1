<#include "../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" action="'searchSupplierAptitudes'" method="'post'">
		<@ww.token name="searchSupplierAptitudesToken"/>
		<#if supplier?exists>
            <@ww.hidden name="'supplier.id'" value="#{supplier.id}"/>
        </#if>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false includeParameters="name|supplier.id" fieldMap="like:">
			<@vlh.checkbox property="id" name="supplierAptitudesIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('supplierAptitudes.no')}">
            	<a href="" onclick="SupplierAptitudesEdit(#{object.id})">#{itemNo}</a>
			<@alignCenter />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
			<@vcolumn title="${action.getText('supplierAptitudes.name')}" property="name">
				${object.name}
			<@alignLeft/>
			</@vcolumn>
		</@list>
		<#if !(action.isReadOnly())>
		<@buttonBar>
			<@vbutton name="'newSupplierAptitudes'"  class="button" value="${action.getText('new')}" onclick="newSupplierAptitudes(getObjByName('supplier.id').value);"/>
            <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('Aptitudes')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"supplierAptitudesIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
    </@ww.form>
    
<script type="text/javascript">
	function newSupplierAptitudes(id){
		if(getObjByName('readOnly').value==''){
			var url = '${req.contextPath}/supplierAptitudes/editSupplierAptitudes.html?supplier.id='+id;
		}else{
			var url = '${req.contextPath}/supplierAptitudes/editSupplierAptitudes.html?supplier.id='+id+'&readOnly=${req.getParameter('readOnly')?if_exists}';
		}
			popupModalDialog(url,750,500);
			if(isIE()){self.location.reload();};
	}
	function SupplierAptitudesEdit(id){
		if(getObjByName('readOnly').value==''){
			var url = '${req.contextPath}/supplierAptitudes/editSupplierAptitudes.html?supplierAptitudes.id='+id;
		}else{
			var url = '${req.contextPath}/supplierAptitudes/editSupplierAptitudes.html?supplierAptitudes.id='+id+'&readOnly=${req.getParameter('readOnly')?if_exists}';
		}
			
			popupModalDialog(url,750,500);
			if(isIE()){self.location.reload();};
	}	
</script>
</@framePage>