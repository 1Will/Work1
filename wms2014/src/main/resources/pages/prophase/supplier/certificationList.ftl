<#include "../../includes/eam2008.ftl" />
<@framePage title="">
  <@ww.form namespace="'/prophase/supplier'" name="'cerfication'" action="'searchCerfications'" method="'post'">
  <@ww.token name="cerficationsToken"/>
  <#if supplier.id?exists>
		<@ww.hidden name="'supplier.id'" value="'#{supplier.id}'"/>
  </#if>
    <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <#assign itemNo=1/>
         <@list title="" excel=false setupTable=false
        	includeParameters="supplier.id" 
        	fieldMap="like:" >
            <@vlh.checkbox property="id" name="cerficationIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
              <#if !(action.isReadOnly())>
                <a href="#" onclick="open_productDialog(#{supplier.id},#{object.id});return false;">#{itemNo}</a>
              <#else>
                #{itemNo}
              </#if>
                <@alignCenter />
            </@vcolumn>
            <#assign itemNo = itemNo + 1/>
              <@vcolumn title="${action.getText('cerfications.name')}" property="name">
              <@alignLeft />
              </@vcolumn>
           </@list>
	     <#if !first>
           <#if !(action.isReadOnly())>
              <@buttonBar>
	            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_productDialog(#{supplier.id}, null)"/>
	            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('supplier.cerfications')}?" />
	                 
	            	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	            		<@ww.param name="'onclick'" value="'return confirmDeletes(\"cerficationIds\", \"${confirmMessage}\");'"/>
	            	     <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            	</@vsubmit>
	         
              </@buttonBar>
           </#if>
         </#if>
              
	     <script language="javascript">
	         function open_productDialog(supplierId, cerfiticationId) {
	      		var url = '${req.contextPath}/prophase/supplier/editSupplierCerfitication.html?supplier.id=' + supplierId;	 
	      		if (cerfiticationId != null) {
	      			url = url + "&cerfitication.id=" + cerfiticationId;
	      		}
	      		popupModalDialog(url, 650,300);
	      		self.location.reload();
	      	 }
	    </script>
      
  </@ww.form>
  
</@framePage>
