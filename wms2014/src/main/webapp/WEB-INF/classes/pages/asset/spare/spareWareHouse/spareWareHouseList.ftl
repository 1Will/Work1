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
	<@ww.form name="'listForm'"  action="'searchSpareWareHouse'" method="'post'">
	<@ww.token name="searchSpareWareHouseToken"/>
 		<#include "./spareWareHouseSearcher.ftl"/>
 		<input type="hidden" name="allSpareWareHouseIds" value=""/>
 		<input type="hidden" name="allSpareWareHouseMinStocks" value=""/>
 		<@ww.hidden name="'pagingPage'" value=""/>
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'"  onclick="'return checkInvalidParms();'"/>
		</@buttonBar>
        <@list title="${action.getText('spareWareHouse.list')}" 
                   includeParameters="id|warehouse.id|category.code|spareDetailType.id|spareNo|spareName|modelSpecs|onlyLessMinStock" 
                   fieldMap="like:id|spareNo|spareName|modelSpecs" > 
                   <#--
            <@vlh.checkbox title="" name="spareWareHouseIds" property="id" >
	                 <@vlh.attribute name="width" value="30"/>
            </@vlh.checkbox>
            -->
            <@ww.hidden name="'spareWareHouseIds'" value="#{object.id?if_exists}"/>
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
            <@vcolumn title="${action.getText('minStocks')}" property="minStocks">
	            	<a href="#" style="display:none">${object.minStocks?if_exists}</a>
	             	<input type="text" name="minStocks" value="${object.minStocks?if_exists}" id="minStocks" class="definedLength" style="text-align:right"/>
	             	<@ww.hidden name="'hiddenMinStocks'" value="${object.minStocks?if_exists}"/>
	             	<@alignRight/>
	        </@vcolumn>
            <@vcolumn title="${action.getText('stocks')}" property="stocks" sortable="desc">
              	<@alignRight/>
            </@vcolumn>  
            <@vcolumn title="入库历史">
                 <a href="#" onclick="spareHistory_openDialog(#{object.spare.id}, '#{object.wareHouse.id}', 'IN');">查看</a>
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="出库历史">
                 <a href="#" onclick="spareHistory_openDialog(#{object.spare.id}, '#{object.wareHouse.id}', 'OUT');">查看</a>
                 <@alignCenter/>
            </@vcolumn> 
       	</@list> 
        <#if !first>
        	<@buttonBar>
		        	<@vsubmit name="'save'" value="'设置安全库存'" onclick="'return submitDetailIds()'">
		            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
		            <#--
            	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_spareDetailPdf()"/>
		    	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_spareDetailXls()"/>
		    	-->
        	</@buttonBar>
        </#if>       
	</@ww.form>
</@htmlPage>
<script language="javascript">

        function spareHistory_openDialog(spareId, warehouseId, inOut) {
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
        	
        	url += "?spare.id=" + spareId+"&warehouse.id="+warehouseId;
        	 popupModalDialog(url,Config.popW, Config.popH);
        }
     function checkInvalidParms(){
           if (document.getElementById("category.code").value==-1){
              document.getElementById("category.code").value='';
           }
          // if (document.getElementById("spareDetailType.id").value==-1){
              //document.getElementById("spareDetailType.id").value='';
          // }
           if ($("warehouse.id").value==-1){
              $("warehouse.id").value='';
           }
           return true;
     }
     //获取备件库总台帐ID
	function retrieveSpareWareHouseIdText(){
		var allSpareWareHouseId = document.getElementsByName("spareWareHouseIds");
        var ary = new Array();
        for (var i=0; i<allSpareWareHouseId.length; i++) {
          ary.push(allSpareWareHouseId[i].value);
        }
         //alert( "retrieveSpareWareHouseIdText" + ary);
        document.forms[0].elements["allSpareWareHouseIds"].value = ary;
       // alert(document.forms[0].elements["allSpareWareHouseIds"].value);
	}
    //获取备件库总台帐安全库存
    function retrieveSpareWareHouseMinStocksText(){
        var spareWareHouseIds = document.getElementsByName("spareWareHouseIds");
        var allMinStocksValue = document.getElementsByName("minStocks");
        var ary = new Array();
       for (var i=0; i<spareWareHouseIds.length; i++) {
          if ('' != allMinStocksValue[i].value) {
           var number = parseInt(formatDigital(allMinStocksValue[i].value));
           ary.push(spareWareHouseIds[i].value);
           ary.push(number);
         }
       }
     //  alert( "retrieveSpareWareHouseMinStocksText" + ary);
      document.forms[0].elements["allSpareWareHouseMinStocks"].value=ary;
      // alert( document.forms[0].elements["allSpareWareHouseMinStocks"].value);
    }
    	//保存安全库存
	function submitDetailIds(){
	//alert("submitDetailIds");
	  if(0!=document.getElementsByName("spareWareHouseIds").length){
	 // alert(document.getElementsByName("spareWareHouseIds").length);
	  	 if(informationValidate()==true){				//保存时信息安全验证
	  	// alert("informationValidate");
		  	 retrieveSpareWareHouseIdText();
		  	 retrieveSpareWareHouseMinStocksText();
		  	 checkInvalidParms();
		  	 var page = '${pagingPage?if_exists}';
		  	 document.getElementById("pagingPage").value = page;
		  	 return true;
	  	 }else{
	  	 	return false;
	  	 }
	  }
	}
	    //保存时信息安全验证
    function informationValidate(){
         
    	var spareWareHouseIds = document.getElementsByName("spareWareHouseIds");
	    var allMinStocksValue = document.getElementsByName("minStocks");
	 
	    
	    for(var i=0;i<spareWareHouseIds.length;i++){
		    //验证入库数量
		     var number = allMinStocksValue[i].value;
             if (''!=number&&!isNumberCheck(number)){   			//验证输入的入库数量的格式
	         		alert("${action.getText('di')} "+(i+1)+" ${action.getText('row')}"+ ","+"${action.getText('spareInBill.format.error')}");
	         		allMinStocksValue[i].focus();
	         		return false;
   			}
   		}
	   return true;
    }
</script>