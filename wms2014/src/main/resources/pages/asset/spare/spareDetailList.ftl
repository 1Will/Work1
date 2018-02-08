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
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('title')}">
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
	<@ww.form name="'listForm'"  action="'searchSpareDetailCommon'" method="'post'">
	<@ww.token name="searchLocationToken"/>
 		<#include "./spareDetailSearcher.ftl"/>
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'"  onclick="'return checkInvalidParms();'"/>
		</@buttonBar>
        <@list title="${action.getText('spare.detail.list')}" 
                   includeParameters="id|warehouse.id|regional.id|locationCode|department.id|category.code|spareNo|spareName|spareEnName|modelSpecs|onlyHasStocks" 
                   fieldMap="like:id|locationCode|spareNo|spareName|spareEnName|modelSpecs" > 
            <@vlh.checkbox title="" name="spareLocationIds" property="id" >
	                 <@vlh.attribute name="width" value="30"/>
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('spareNo')}" property="spare.spareNo" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('备件名称')}" property="spare.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <#--<@vcolumn title="${action.getText('spare.spareEnName')}" property="spare.enName" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>-->
            <@vcolumn title="${action.getText('spare.modelSpecs')}" property="spare.modelSpecs" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            
            <#if object.warehouse?exists>
				<#assign warehouseName="${object.warehouse.name}"/>
			<#else>
				<#assign warehouseName=""/>
			</#if>
            <@vcolumn title="${action.getText('warehouseCode')}" property="warehouse" sortable="desc">
     			${warehouseName}
     			<@alignLeft/>
            </@vcolumn>
            
            <#if object.regional?exists>
				<#assign regionalName="${object.regional.name}"/>
			<#else>
				<#assign regionalName=""/>
			</#if>
            <@vcolumn title="${action.getText('regionalCode')}" property="regional" sortable="desc">
     			${regionalName}
     			<@alignLeft/>
            </@vcolumn>

            <@vcolumn title="${action.getText('locationCode')}" property="location.code" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="desc">
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
            <#--
            <@vcolumn title="${action.getText('spare.supplier')}" property="spare.supplier.name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('unit')}" property="unitPrice" sortable="desc" format="${action.getText('currencyFormat')}">
              	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('stocks')}" property="stocks" sortable="desc">
              	<@alignRight/>
            </@vcolumn>
             <@vcolumn title="安全库存" property="safeStocks">
              	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="占用库存" property="lockedStocks">
              	<@alignRight/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('unit')}">
             <input type="text" name="unit" 
	    		class="noBorderLine"  value="${(object.unitPrice?string('#,###,##0.00'))?if_exists}"  maxlength="50" size="8"style="text-align:right" disabled/>           	
             <@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('stocks')}">
             <input type="text" name="stocks" 
	    		class="noBorderLine"  value="${object.stocks?if_exists}"  maxlength="50" size="8"style="text-align:right" disabled/>           	
             <@alignRight />
             </@vcolumn>
             -->
             <#assign unitPrice =(object.unitPrice)/>
             <#assign stocks =(object.stocks)/>
             <#assign totalPrice =unitPrice*stocks/>
             <@vcolumn title="${action.getText('totalPrice')}" property="">
                ${(totalPrice?string('#,###,##0.00'))?if_exists}
             <@alignRight />
             </@vcolumn>
             <#--
            <@vcolumn title="${action.getText('spare.inStockHistory')}">
                 <a href="#" onclick="spareHistory_openDialog(#{object.spare.id}, 'IN');">${action.getText('spare.inOutStock')}</a>
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.outStockHistory')}">
                 <a href="#" onclick="spareHistory_openDialog(#{object.spare.id}, 'OUT');">${action.getText('spare.inOutStock')}</a>
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.spareInventoryHistory')}">
                 <a href="#" onclick="spareHistory_openDialog(#{object.spare.id}, 'INVENTORY');">${action.getText('spare.inOutStock')}</a>
                 <@alignLeft/>
            </@vcolumn>     
            -->     
       	</@list> 
        <#if !first>
        	<@buttonBar>
            	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_spareDetailPdf()"/>
		    	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_spareDetailXls()"/>
        	</@buttonBar>
        </#if>       
	</@ww.form>
</@htmlPage>
<#include "../commonSpareType.ftl"/>
<script language="javascript">
        //打印pdf 
        function open_spareDetailPdf(){
     		var url='${req.contextPath}/reports/spare/spareDetailReport.pdf?spareNo='+document.forms[0].elements["spareNo"].value+
	       	  '&spareName='+(document.forms[0].elements["spareName"].value) +
	       	  '&modelSpecs='+ (document.forms[0].elements["modelSpecs"].value)+
        	  '&locationCode='+ (document.forms[0].elements["locationCode"].value)+
        	  '&department.id='+ (document.forms[0].elements["department.id"].value)+
        	  '&category.code='+ (document.forms[0].elements["category.code"].value)+
        	  '&onlyHasStocks='+ (document.forms[0].elements["onlyHasStocks"].value);
			url = encodeURI(url);
			window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
		}       
		//打印xls
        function open_spareDetailXls(){
            var longinUserId="";
            <#if loginUser?exists>
                longinUserId=#{loginUser.id?if_exists}
            </#if>
     		var url='${req.contextPath}/reports/spare/spareDetailReport.xls?spareNo='+document.forms[0].elements["spareNo"].value+
	       	  '&spareName='+(document.forms[0].elements["spareName"].value) +
	       	  '&modelSpecs='+ (document.forms[0].elements["modelSpecs"].value)+
        	  '&locationCode='+ (document.forms[0].elements["locationCode"].value)+
        	  '&regionalId='+ (document.forms[0].elements["regional.id"].value)+
        	  '&warehouseId='+ (document.forms[0].elements["warehouse.id"].value)+
        	  '&department.id='+ (document.forms[0].elements["department.id"].value)+
        	  '&category.code='+ (document.forms[0].elements["category.code"].value)+
        	  '&onlyHasStocks='+ (document.forms[0].elements["onlyHasStocks"].value)+
        	  '&longinUserId='+longinUserId;
			url = encodeURI(url);
			window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
		}
        function spareHistory_openDialog(spareId, inOut) {
        	var url = "${req.contextPath}/spare";
        	
        	if (inOut == 'IN') {
        		url += "/listSpareInWareHouseHistory.html";
        	} else if (inOut == 'OUT') {
        		url += "/listSpareOutWareHouseHistory.html";
        	} else if (inOut == 'INVENTORY') {
        		url += "/listSpareInventoryBillHistory.html";
        	} else {
        		return ;
        	}
        	
        	url += "?spare.id=" + spareId;
        	
        	popupModalDialog(url,Config.popW, Config.popH);
        }
        
     function checkInvalidParms(){
           if (getObjByNameRe("category.code").value==-1){
              getObjByNameRe("category.code").value='';
           }
          // if (getObjByNameRe("spareDetailType.id").value==-1){
            //  getObjByNameRe("spareDetailType.id").value='';
          // }
           if (getObjByNameRe("department.id").value==-1){
              getObjByNameRe("department.id").value='';
           }
           if (getObjByName("warehouse.id").value==-1){
              getObjByName("warehouse.id").value='';
           }
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
  		<#if '${inOutFlag?if_exists}' == 'out'>
  		if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		       if (selector[i].checked&&Stocks[i].value==0) {
		           alert("${action.getText('di')}"+(i+1)+" ${action.getText('please.select.spare')}");
		           return false;
		        }
		    }
		}
		</#if> 
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