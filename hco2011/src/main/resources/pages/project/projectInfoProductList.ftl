<#include "../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" namespace="'/projectInfo'" action="'searchProPro'" method="'post'">
		<@ww.token name="searchProjectInfoContractToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'saveFlag'" value="''"/>
		<@ww.hidden name="'productIds'" value="''"/>
		<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
		<#assign returnName='replaceWord'>
	<@list title="" 
        includeParameters="projectInfo.id|products.id|readOnly|onlyInvalid|onlyValid" 
        fieldMap="" >
        <#if projectInfoId?exists> 
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="projectInfoProductIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
             <@vcolumn title="${action.getText('products.code')}" property="code" sortable="desc">
              <a href="javascript:editProduct('#{object.products.id}')">${object.products.code}</a>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.name')}" property="products.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title=" ${action.getText('products.model')} " property="products.model" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title=" ${action.getText('products.standard')} " property="products.standard" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.etcPrice')}" property="products.etcPrice" format="#,###,##0.00" sortable="desc">
            <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.salePrice')}" property="products.salePrice" format="#,###,##0.00" sortable="desc">
            <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.salelimit')}" property="products.salelimit" sortable="desc">
            <@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.pt.name')}" property="products.pt.name" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('products.productSource')}" property="products.productSource" sortable="desc">
            <@alignLeft/>
            </@vcolumn>
        <#else>
        	<@vcolumn title="${action.getText('code')}" property="code" sortable="desc">
	             <a href="javascript:editProjectInfo_OpenDialog('#{object.projectInfo.id}')"
	                 title="${object.projectInfo.code}%">${object.projectInfo.code}</a>
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('name')}" property="projectInfo.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('projectInfo.customerInfoName')}" property="customer.name" sortable="desc">
            	<a href="javascript:customer_OpenDialog('#{object.projectInfo.customer.id}')"
	                 title="">${object.projectInfo.customer.name}</a>
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('projectInfo.controller')}" property="projectInfo.controller.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('outline')}" property="projectInfo.outline" sortable="desc"  >
           	<#assign returnName=returnName +'re'>
           	<@ww.hidden name="'${returnName}'" value="'${object.projectInfo.outline?if_exists}'"/>
	            <span title="${object.projectInfo.outline?if_exists}">
		            <script>
		            	var s = getObjByName('${returnName}').value;
		            	s=s.replace(/[\r\n]/g, "");
		            	document.write(s.slice(0,18)+"...");
		            </script>
	            </span>
           		<@alignLeft />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('state.name')}" property="projectInfo.state.name" sortable="desc"  >
            	<@alignCenter attributes="width:110;"/>
            </@vcolumn>
        </#if>
	</@list>
        <#if projectInfoId?exists> 
	         <#if !first>
				<@buttonBar>
					<#if !(action.isReadOnly())>
						<@vbutton class="button" name="${action.getText('选择产品')}" value="${action.getText('选择产品')}" onclick="product_OpenDialog();"/>
						<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('projectInfo.product')}?" />
			            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
			                <@ww.param name="'onclick'" value="'return confirmDeletes(\"projectInfoProductIds\", \"${confirmMessage}\");'"/>
			                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
			            </@vsubmit>
		            </#if>
				</@buttonBar>
			</#if>
		</#if>
    </@ww.form>
</@framePage>
<script>
function product_OpenDialog(){
		var  url = "${req.contextPath}/productsManager/listProducts.html?productCheckBox=productCheckBox&projectInfoId=${req.getParameter('projectInfo.id')?if_exists}";
		popupModalDialog(url, 1000, 600, creatorSelectorHandlerProduct);
	}
	function creatorSelectorHandlerProduct(result) {
		if (null != result) {
		getObjByName("productIds").value=result[0];
		getObjByName("saveFlag").value="saveFlag";
		document.listForm.submit();
		
		}
	}
	function editProduct(id){
	var  url = "${req.contextPath}/productsManager/editProducts.html?backFlag=backFlag&products.id="+id+"&&readOnly=${req.getParameter('readOnly')?if_exists}";
		popupModalDialog(url, 800, 600);
	
	}
	
	function customer_OpenDialog(id,readOnly){
	   var url = "${req.contextPath}/customerRelationship/editCustomerInfo.html?customerInfo.id="+id+"&readOnly="+readOnly+"&popWindowFlag=popWindowFlag&notNewFlag=notNewFlag";
	   openNewWindow(url);
	 }
	
	function editProjectInfo_OpenDialog(id){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProjectInfo.html?projectInfo.id="+id+"&openFlag=openFlag&readOnly=${req.getParameter('readOnly')?if_exists}";
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	
</script>