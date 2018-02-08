
<#include "../../includes/eam2008.ftl" />
<@framePage title="">
  <@ww.form namespace="'/prophase/supplier'" name="'cerfication'" action="'searchProducts'" method="'post'">
  <@ww.token name="searchProductsToken"/>
  <#if supplier.id?exists>
		<@ww.hidden name="'supplier.id'" value="'#{supplier.id}'"/>
		</#if>
     <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <#assign itemNo=1/>
         <@list title="" excel=false setupTable=false
        	includeParameters="supplier.id|toolingDevFlag" 
        	fieldMap="like:" >
            <@vlh.checkbox property="id" name="productIds">
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
              <@vcolumn title="${action.getText('productname')}" property="name">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('product.category')}" property="category">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('product.modelNo')}" property="modelNo">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('product.spec')}" property="spec">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('product.qos')}" property="qos">
              <@alignRight/>
              </@vcolumn>
              <@vcolumn title="${action.getText('product.preOrderDay')}" property="preOrderDay">
              <@alignRight/>
              </@vcolumn>
           </@list>
	     <#if !first>
	      <#if !(action.isReadOnly())>
              <@buttonBar>
	            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_productDialog(#{supplier.id}, null)"/>
	            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('supplier.information')}?" />
	            	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	            		<@ww.param name="'onclick'" value="'return confirmDeletes(\"productIds\", \"${confirmMessage}\");'"/>
	            	    <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            	</@vsubmit>
            </@buttonBar>
          </#if>
            </#if>      
       </@ww.form>   
	   <script language="javascript">
	         function open_productDialog(supplierId, productId) {
	      		var url = '${req.contextPath}/popup/editSupplierProduct.html?supplier.id=' + supplierId;	 
	      		if (productId != null) {
	      			url = url + "&product.id=" + productId;
	      		}
	      		popupModalDialog(url, 650,300);
	      		self.location.reload();
	      	 }
	    </script>

  
</@framePage>
