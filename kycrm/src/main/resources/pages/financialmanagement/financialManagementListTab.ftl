<#include "../includes/hco2011.ftl" />
<@framePage title="">
	<@ww.form name="'listFrom'" namespace="'/financialManagement'" action="'searchFinancialManagement'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchFinancialManagementToken"/>
   <@list title="" 
            includeParameters="financialManagement.saleman|financialManagement.code|projectInfo.name|financialManagement.payee|financialManagement.contractManagement|financialManagement.collectionDate_start|financialManagement.collectionDate_end
        	|customerInfo.code|financialManagement.customerInfo|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:financialManagement.saleman|financialManagement.code|projectInfo.name|financialManagement.payee|financialManagement.contractManagement|customerInfo.code|financialManagement.customerInfo
        	,date:financialManagement.collectionDate_start|financialManagement.collectionDate_end">
            
     <#--
               
            <@vcolumn title="${action.getText('financialManagement.contractManagement')}" property="contractManagement.contractName" sortable="desc">
            	<@vlh.attribute name="width" value="12%" />
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('financialManagement.code')}" property="code" sortable="desc">
                <a href="javascript:financialManagement_OpenDialog('#{object.id}')">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('financialManagement.customerInfo')}" property="customerInfo.name" sortable="desc">
     			<@vlh.attribute name="width" value="10%" />
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('financialManagement.contractManagement.projectInfo.name')}" property="contractManagement.project.name" sortable="desc">
     			<@vlh.attribute name="width" value="10%" />
     			<@alignLeft/>
            </@vcolumn>
      -->      
            <@vcolumn title="${action.getText('financialManagement.batch')}" property="batch.name" sortable="desc">
            	<a href="javascript:financialManagement_OpenDialog('#{object.id}')">${object.batch.name}</a>
            	<@alignCenter/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('financialManagement.sumReceivable')}" property="sumReceivable" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.trueSum')}" property="trueSum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.totalSum')}" property="totalSum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financialManagement.withoutGotSum')}" property="withoutGotSum" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <#assign invoice=""/>
            <#if (object.invoice)=='0'>
            	<#assign invoice="${action.getText('是')}">
            <#else>
           	 	<#assign invoice="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('financialManagement.invoice')}" sortable="desc">
            	${invoice?if_exists}
            	<@alignLeft/>
            </@vcolumn>
                <@vcolumn title="${action.getText('financialManagement.payee')}" property="payee.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
              <@vcolumn title="${action.getText('financialManagement.collectionDate')}" property="collectionDate" format="yyyy-MM-dd" sortable="desc">
              <@vlh.attribute name="width" value="9%" />
            	<@alignLeft/><#-- attributes="width:110;"-->
            </@vcolumn>
        </@list>
    </@ww.form>
    <script>
    function financialManagement_OpenDialog(id){
	    var url = "${req.contextPath}/financialManagement/editFinancialManagement.html?financialManagement.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	    openNewWindow(url);
    }
     <#-- 
    function newFinancialManagement_OpenDialog(){
	    var url = "${req.contextPath}/financialManagement/editFinancialManagement.html?contractManagement.id=${req.getParameter('contractManagement.id')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	    openNewWindow(url);
    }
    -->
    </script>
</@framePage>