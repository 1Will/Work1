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

<#-- $Id: userSelector.ftl 11122 2008-02-26 12:54:35Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<#include "../../commonSpareType.ftl"/>
<@htmlPage title="旧件库明细台账查询">
	 <STYLE TYPE="text/css" >
	 .displayRed{
	   font-weight: bold;
	   color: #FFFFFF;
	   background-color: #FFCC66;	 
	 }
	  .noBorderLine{
       border-width :0px;
       border-style : none;
       outline-style : none;
       width:80%;
       }
    .definedLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        width:80%;
    }
    </STYLE>
	<@ww.form name="'listForm'" action="'searchOldSpareLocationCommon'" method="'post'">
	<@ww.token name="searchLocationToken"/>
 		<#include "./oldSpareLocationSearcher.ftl"/>
 		
 		<@ww.hidden name="'roleWarehouseId'" value="'${req.getParameter('roleWarehouseId')?if_exists}'"/>
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'"  onclick="'return checkInvalidParms();'"/>
		</@buttonBar>
        <@list title="旧件明细台账列表" 
                   includeParameters="id|warehouse.id|roleWarehouseId|regional.id|locationCode|department.id|category.code|spareDetailType.id|spareNo|spareName|spareEnName|modelSpecs|onlyHasStocks|inOutFlag" 
                   fieldMap="like:id|locationCode|spareNo|spareName|spareEnName|modelSpecs" > 
            <@vlh.checkbox title="" name="spareLocationIds" property="id" >
	                 <@vlh.attribute name="width" value="30"/>
            </@vlh.checkbox>
            <@vcolumn title="备件编码" property="spare.spareNo" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('备件名称')}" property="spare.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('spare.spareEnName')}" property="spare.enName" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>-->
            <@vcolumn title="型号" property="spare.modelSpecs" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('订货号')}" property="spare.orderNo" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('生产厂家')}" property="spare.factory.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
              <@vcolumn title="种类" property="spare.category.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('spare.spareDetailType')}" property="spare.spareDetailType.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('单位')}" property="spare.unit.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            
            <#if object.warehouse?exists>
				<#assign warehouseName="${object.warehouse.name}"/>
			<#else>
				<#assign warehouseName=""/>
			</#if>
            <@vcolumn title="仓库" property="warehouse" sortable="desc">
     			${warehouseName}
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="部门" property="department.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
          
            <@vcolumn title="单价" property="unitPrice" format="${action.getText('currencyFormat')}">
              	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="库存" property="stocks">
              	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="占用库存" property="lockedStocks">
              	<@alignRight/>
            </@vcolumn>
            <@ww.hidden name="'spareLocation.hiddenStocks'" value="${object.stocks?if_exists}"/>           
       	</@list>
   		<@buttonBar>
   		<#if !first>
	  		<@vsubmit name="'choose'" value="'${action.getText('choose')}'" >
	  			<@ww.param name="'onclick'" value="'return confirmSelects(\"spareLocationIds\");'"/>
	        	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	    	</@vsubmit>
	    </#if>
		</@buttonBar>         
	</@ww.form>
</@htmlPage>
<script language="javascript">
     function checkInvalidParms(){
     		
           if (getObjByNameRe("category.code").value==-1){
              getObjByNameRe("category.code").value='';
           }
          // if (getObjByNameRe("spareDetailType.id").value==-1){
             // getObjByNameRe("spareDetailType.id").value='';
         //  }
           if (getObjByNameRe("department.id").value==-1){
              getObjByNameRe("department.id").value='';
           }
          
           <#-- 若仓库选择所有,在控制层修改为可查询当前登录用户的权限仓库
           if (getObjByName("warehouse.id").value==-1){
              getObjByName("warehouse.id").value='';
           }
           -->
           if (getObjByName("regional.id").value==-1){
              getObjByName("regional.id").value='';
           }
           return true;
     }
     
     function confirmSelects(boxname) {
  		if (!hasChecked(boxname)) {
  			alert("${action.getText('spare.noSelect')}");
  			return false;
  		}
  		var selector = document.getElementsByName(boxname);
  		var Stocks = document.getElementsByName("spareLocation.hiddenStocks");
  	<#--	<#if '${inOutFlag?if_exists}' == 'out'>
  		if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		       if (selector[i].checked&&Stocks[i].value==0) {
		           alert("${action.getText('di')}"+(i+1)+" ${action.getText('please.select.spare')}");
		           return false;
		        }
		    }
		}
		</#if> -->
  		chooseSpares(boxname);
  		return true;
      	}
	      	
      	function chooseSpares(boxname) {
      		var selector = document.getElementsByName(boxname);
		    if (!selector) {
		        return false;
		    }
		    
		    var spareIds = "";
		    if (selector.length) {
		        for (i = 0; i < selector.length; i++) {
		            if (selector[i].checked) {
		                spareIds += selector[i].value + ",";
		            }
		        }
		    }
		    returnDialog(spareIds,false);
      	}
</script>