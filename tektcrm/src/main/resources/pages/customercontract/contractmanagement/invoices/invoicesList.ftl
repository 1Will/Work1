<#include "../../../includes/hco2011.ftl" />

<@framePage title="${action.getText('invoices.title_1')}">
	<@ww.form name="'listForm'" namespace="'/contractManagement'" action="'searchInvoices'" method="'post'">
		<@ww.token name="searchInvoicesToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			
			<@ww.hidden name="'contractManagement.id'" value="'#{contractManagement.id?if_exists}'"/>
			<@ww.hidden name="'productList.id'" value="'#{productList.id?if_exists}'"/>
			<#assign itemNo=1/>
 	        <@list title="" 
        includeParameters="contractManagement.id|invoices.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="invoicesIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('numb')}" property="id" >
              <a href="javascript:editInvoices_OpenDialog_update('#{object.id}');">#{itemNo}</a> 
            <@alignLeft attributes="width:80;"/>
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('invoices.deliveryNum')}" property="deliveryNum"   >
            <@alignLeft attributes="width:100;"/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('invoices.deliveryDate')}" property="deliveryDate"  format="yyyy-MM-dd">
            <@alignLeft attributes="width:150;"/>
            </@vcolumn>
             <@vcolumn title="${action.getText('deliveryPerson.name')}" property="deliveryPerson.name"   >
             <@alignLeft attributes="width:100;"/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('invoices.deliveryCount')}" property="deliveryCount"   >
            <@alignRight attributes="width:100;"/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractManagement.contractName')}" property="contractManagement.contractName"   >
            <@alignLeft attributes="width:100;"/>
            </@vcolumn>
        </@list>
         <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="button" value="${action.getText('new')}" onclick="editInvoices_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"invoicesIds\", \"${confirmMessage}\");'"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
</@framePage>
<script>
   window.onload=function(){
   //判断是否删除，如果删除同时刷新父页面
   var flag=getCookieValue("invoiceFlag");
   if(flag=="flag"){
     self.parent.location.reload();
     document.cookie="invoiceFlag=''";
   }
   }
   //获取Cookie中字段的值
   function getCookieValue(name) {
    var strCookie=document.cookie;
    var arrCookie=strCookie.split(";");
    for(var i=0;i<arrCookie.length;i++){
        var c=arrCookie[i].split("=");
        if(c[0]==name){
            return c[1];
        }
    }
    return "";
}
	function editInvoices_OpenDialog(){
	   var url= "${req.contextPath}/contractManagement/editInvoices.html?readOnly=${req.getParameter('readOnly')?if_exists}&contractManagement.id=#{contractManagement.id?if_exists}&productList.id=#{productList.id?if_exists}";
	  popupModalDialog(url, 800, 600);
	 }
	 
	 function editInvoices_OpenDialog_update(id){
	   var url= "${req.contextPath}/contractManagement/editInvoices.html?readOnly=${req.getParameter('readOnly')?if_exists}&contractManagement.id=#{contractManagement.id?if_exists}&invoices.id="+id+"&productList.id="+"#{productList.id?if_exists}";
	  popupModalDialog(url, 800, 600);
	 }
	 
</script>