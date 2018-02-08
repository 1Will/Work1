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
<@htmlPage title="${action.getText('spareWareHouse.title')}">
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
	<@ww.form name="'listForm'"  action="'searchSpareWareHouseOfLessMinStocks'" method="'post'">
	<@ww.token name="searchSpareWareHouseToken"/>
 		<#include "./spareWareHouseSearcher.ftl"/>
 		<input type="hidden" name="allSpareWareHouseIds" value=""/>
 		<input type="hidden" name="allSpareWareHouseMinStocks" value=""/>
 		<@ww.hidden name="'pagingPage'" value=""/>
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'"  onclick="'return checkInvalidParms();'"/>
		</@buttonBar>

        <@list title="${action.getText('spareWareHouse.list')}" 
                   includeParameters="id|warehouse.id|category.code|spareDetailType.id|spareNo|spareName|modelSpecs|onlyLessMinStock|onlyEmptyMinStock" 
                   fieldMap="like:id|spareNo|spareName|modelSpecs" > 
                   
            <@vlh.checkbox title="" name="spareWareHouseIds" property="id" >
	                 <@vlh.attribute name="width" value="30"/>
            </@vlh.checkbox>

            <#--
            <@ww.hidden name="'spareWareHouseIds'" value="#{object.id?if_exists}"/>
            -->
            <@vcolumn title="${action.getText('spareNo')}" property="spare.spareNo" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spareName')}" property="spare.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.modelSpecs')}" property="spare.modelSpecs" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            
            <#if object.wareHouse?exists>
				<#assign warehouseName="${object.wareHouse.name}"/>
			<#else>
				<#assign warehouseName=""/>
			</#if>
            <@vcolumn title="${action.getText('warehouseCode')}" property="wareHouse" sortable="desc">
     			${warehouseName}
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.category')}" property="spare.category.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('spare.spareDetailType')}" property="spare.spareDetailType.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('minStocks')}" property="minStocks" sortable="desc">
              	<@alignRight/>
            </@vcolumn> 
            <@vcolumn title="${action.getText('stocks')}" property="stocks" sortable="desc">
              	<@alignRight/>
            </@vcolumn>   
       	</@list> 
   		<@buttonBar>

   		<#if !first>
	  		<@vsubmit name="'choose'" value="'${action.getText('choose')}'" >
	  			<@ww.param name="'onclick'" value="'return confirmSelects(\"spareWareHouseIds\");'"/>
	        	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	    	</@vsubmit>
	    	<#--
	        <@vsubmit name="'choose'" value="'${action.getText('选择并关闭')}'" >
	  			<@ww.param name="'onclick'" value="'return confirmSelects(\"spareWareHouseIds\");'"/>
	        	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	    	</@vsubmit>-->
	    	
	    </#if>

		</@buttonBar>     
	</@ww.form>
</@htmlPage>
<script language="javascript">
     function checkInvalidParms(){
           if (document.getElementById("category.code").value==-1){
              document.getElementById("category.code").value='';
           }
           //if (document.getElementById("spareDetailType.id").value==-1){
             // document.getElementById("spareDetailType.id").value='';
           //}
           if ($("warehouse.id").value==-1){
              $("warehouse.id").value='';
           }
           return true;
     }
     function confirmSelects(boxname) {
  		if (!hasChecked(boxname)) {
  			alert("${action.getText('spare.noSelect')}");
  			return false;
  		}
  		var selector = document.getElementsByName(boxname);
  		//var Stocks = document.getElementsByName("spareLocation.hiddenStocks");
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