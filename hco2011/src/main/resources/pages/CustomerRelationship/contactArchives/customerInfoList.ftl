<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: customerInfoList.ftl 2009-12-11 8:48:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@fsPage title="${action.getText('customerInfo.title')}">
	<@ww.form name="'listForm'" action="'searchCustInfo'" method="'post'">
		<@ww.token name="searchCustomerInfoToken"/>
		<#include "./customerInfoSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'"/>
        </@buttonBar>
        <@list title="${action.getText('customerInfo.list.title')}" 
            includeParameters="customerInfo.code|customerInfo.name|type.id|country.id|province.id|city.id|industry.id|companyNature.id|customerInfo.keyContacter|onlyInvalid|onlyValid" 
        	fieldMap="like:customerInfo.code|customerInfo.name|customerInfo.keyContacter" >
        	
            <@vcolumn title="${action.getText('customerInfo.code')}" property="code" sortable="desc">
                <a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}','${object.customerType.id}','${object.customerType.name}','${object.industry.name}','${object.salesman.id}','${object.salesman.name}','${object.address?if_exists}','${object.keyContacter?if_exists}','${object.step.id?if_exists}','${object.effectDescribe?if_exists}'));">
	                    ${object.code}
                </a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.name')}" property="name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.type')}" property="customerType.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('customerInfo.country')}" property="country.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('customerInfo.province')}" property="province.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('customerInfo.city')}" property="city.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.industry')}" property="industry.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.companyNature')}" property="companyNature.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.keyContacter')}" property="keyContacter" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.telphone')}" property="telphone" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('customerInfo.mobilePhone')}" property="mobilePhone" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
    </@ww.form>
</@fsPage>