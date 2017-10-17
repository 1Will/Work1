<#include "../../../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" namespace="'/productsManager'" action="'searchProductsPerson'" method="'post'">
		<@ww.token name="searchProjectInfoPersonnelsToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@ww.hidden name="'products.id'" value="'${req.getParameter('products.id')?if_exists}'"/>
			<#assign itemNo=1/>
 	        <@list title="" 
        includeParameters="products.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="productsPersonIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
              <@vcolumn title="${action.getText('序号')}" property="id" >
              <a href="javascript:editProductsPerson_OpenDialog('#{object.id}');">#{itemNo}</a> 
            <@alignCenter attributes="width:130;"/>
            </@vcolumn>
            
            <#assign itemNo=itemNo + 1/>
            
             <@vcolumn title="${action.getText('开发成员')}" property="developer.name" sortable="desc">
            <@alignLeft attributes="width:150;"/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('角色')}" property="role.name" sortable="desc"  >
            <@alignLeft attributes="width:150;"/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('角色说明')}" property="explain" sortable="desc"  >
            <@alignLeft />
            </@vcolumn>
          
        </@list>
         <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="editNewProductsPerson_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('团队成员')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"productsPersonIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
</@framePage>
<script>
	function editNewProductsPerson_OpenDialog(){
	   var url= "${req.contextPath}/productsManager/editProductsPerson.html?products.id=${req.getParameter('products.id')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}";
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	function editProductsPerson_OpenDialog(id){
	   var url= "${req.contextPath}/productsManager/editProductsPerson.html?products.id=${req.getParameter('products.id')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&productsPerson.id="+id;
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
</script>