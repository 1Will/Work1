
<#include "../../includes/hco2011.ftl" />
<@framePage title="">
	<@ww.form name="'listFrom'" namespace="'/contractManagement'" action="'searchReturnPlanByCustomer'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchReturnPlanToken"/>
		<@ww.hidden name="'contractManagement.id'" value="'${contractManagementId?if_exists}'"/>
		<#assign returnName='replaceWord'>
        <@list title="" 
            includeParameters="contractManagement.code|returnPlan.contractManagement|returnPlan.paytime_start|returnPlan.paytime_end|returnPlan.planDate_start|
            returnPlan.planDate_end|customerInfo.code|customerInfo.name|contractManagement.id|readOnly|onlyInvalid|onlyValid|customerInfo.id|contactArchives.id|rent.id|" 
        	fieldMap="like:,date:" >
    	<#if !(action.isReadOnly())>
        	<@vlh.checkbox property="id" name="returnPlanIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>     
        </#if>   
       	 <@alignCenter/>
      <#if req.getParameter('contractManagement.id')?exists>
      	    <@vcolumn title="${action.getText('returnPlan.batch')}" property="batch.name" sortable="desc">
            	<a href="javascript:contract_OpenDialog('#{object.id}')">${object.batch.name}</a>
            	<@alignRight/>
            </@vcolumn>
      <#else>
            <@vcolumn title="${action.getText('contractManagement.code')}" property="contractManagement.code" sortable="desc">
                <a href="javascript:contract_OpenDialog('#{object.id}')"><#if object.contractManagement?exists>${object.contractManagement.code}</#if></a>
                <@vlh.attribute name="width" value="115" />
				<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('returnPlan.contractManagement')}" property="contractManagement.contractName" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.code')}" property="customerInfo.code" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('customerInfo.name')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('returnPlan.contactArchives')}" property="contactArchives.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('returnPlan.phone')}" property="contactArchives.mobilePhone" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('returnPlan.batch')}" property="batch.name" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
      </#if>
            <@vcolumn title="${action.getText('returnPlan.planDate')}" property="planDate" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft attributes="width:100;"/>
            	<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('收款方式')}" property="payment.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            -->
            
            <@vcolumn title="${action.getText('returnPlan.sum')}" property="sum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('负责人')}" property="chargMan.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('房间编码')}" property="house" sortable="desc">
				<#assign returnName=returnName +'replaceWord'>
				<@ww.hidden name="'${returnName}'" value="'${object.house?if_exists}'"/>
	            <span title="${object.house?if_exists}">
		            <script>
		            	var s = getObjByName('${returnName}').value;
		            	s=s.replace(/[\r\n]/g, "");
		            	if(s.length>18){
		            	document.write(s.slice(0,18)+"...");
		            	}else{
		            	document.write(s);
		            	}
		            </script>
	            </span>
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('returnPlan.factSum')}" property="factSum" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('returnPlan.paytime')}" property="paytime" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/><#-- attributes="width:110;"-->
            </@vcolumn>
           	<@vcolumn title="${action.getText('计划状态')}" property="planState.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
           	<@vcolumn title="${action.getText('进度(%)')}" property="percentt" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <#-- 
             <#assign isOrNot=""/>
            <#if (object.isOrNot)=='0'>
            	<#assign isOrNot="${action.getText('是')}">
            <#else>
           	 	<#assign isOrNot="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('returnPlan.isOrNot')}"sortable="desc">
            	${isOrNot?if_exists}
            	<@alignLeft/>
            </@vcolumn>
             <#assign notOrIs=""/>
            <#if (object.notOrIs)=='0'>
            	<#assign notOrIs="${action.getText('是')}">
            <#else>
           	 	<#assign notOrIs="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('returnPlan.notOrIs')}" sortable="desc">
            	${notOrIs?if_exists}
            	<@alignLeft/>
            </@vcolumn>
              <#assign billingOrNot=""/>
            <#if (object.billingOrNot)=='0'>
            	<#assign billingOrNot="${action.getText('是')}">
            <#else>
           	 	<#assign billingOrNot="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('returnPlan.billingOrNot')}" property="billingOrNot" sortable="desc">
            	${billingOrNot?if_exists}
            	<@alignLeft/>
            </@vcolumn>
			-->
  
             <@vcolumn title="${action.getText('returnPlan.payee')}" property="payee.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('开票金额')}" property="billMoney" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
             <@vcolumn title="${action.getText('开票日期')}" property="billDate" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if req.getParameter('contractManagement.id')?exists>
        <#if !(action.isReadOnly())>
		    <@buttonBar>
		    <@vbutton class="button" value="${action.getText('new')}" onclick="openReturnPlan()"/>
		    <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('收款计划')}?" />	 
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
               <@ww.param name="'onclick'" value="'return confirmDeletes(\"returnPlanIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
			</@buttonBar>
        </#if>
	 	</#if>
    </@ww.form>
</@framePage>
<script language="javascript">
	function contract_OpenDialog(id){
			 var url = "${req.contextPath}/contractManagement/editReturnPlan.html?returnPlan.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	  		 //popupModalDialog(url, 1100, 600);
	  		 openNewWindow(url);
	 }
	 
	 function openReturnPlan(){
	 	url="${req.contextPath}/contractManagement/editReturnPlan.html?contractManagement.id=${req.getParameter('contractManagement.id')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	 	openNewWindow(url);
	 }
</script>