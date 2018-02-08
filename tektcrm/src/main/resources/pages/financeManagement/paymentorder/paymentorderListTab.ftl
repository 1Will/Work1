<#include "../../includes/hco2011.ftl" />

<@framePage title="${action.getText('')}">
	<@ww.form name="'listForm'" action="'searchPaymentorderAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchPaymentorderActionToken"/>
        <@list title="${action.getText('')}" 
            includeParameters="paymentorder.code|paymentorder.supplier.name|projectInfo.id|paymentorder.produceType.id|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:paymentorder.code|paymentorder.supplier.name" >
          	<@vcolumn title="${action.getText('paymentorder.code')}" property="code" sortable="desc" >
                <a href="javascript:paymentorder_OpenDialog(#{object.id?if_exists})">${object.code?if_exists}</a>
				<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('paymentorder.supplier')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('合同名称')}"  >
              <#if object.contractManagement?exists>
              <a href="javascript:contractManagement_OpenDialog(#{object.contractManagement.id?if_exists})">
             ${object.contractManagement.contractName}
             </a>
             </#if>
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('项目名称')}" >
             <#if object.projectInfo?exists>
             <a href="javascript:projectInfo_OpenDialog(#{object.projectInfo.id?if_exists})">
             ${object.projectInfo.name}
             </a>
             </#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('paymentorder.produceType')}" property="produceType.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('paymentorder.totalMoney')}" property="totalMoney" sortable="desc">
	            	#{object.totalMoney?if_exists}            	
     			<@alignRight/>
            </@vcolumn>
        </@list>
    </@ww.form>
</@framePage>
<script>
	
	//弹出付款单窗体
	function paymentorder_OpenDialog(id){
	   var url = "${req.contextPath}/paymentorder/editPaymentorderAction.html?paymentorder.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	   openNewWindow(url);
	 }
	
	  //弹出项目信息窗体
	function projectInfo_OpenDialog(id){
	   var url = "${req.contextPath}/projectInfo/editProjectInfo.html?projectInfo.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&openFlag=openFlag";
	   openNewWindow(url);
	 }
	   //弹出合同信息窗体
	function contractManagement_OpenDialog(id){
	   var url = "${req.contextPath}/contractManagement/editContractManagementAction.html?contractManagement.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	   openNewWindow(url);
	 }
	 
</script>
