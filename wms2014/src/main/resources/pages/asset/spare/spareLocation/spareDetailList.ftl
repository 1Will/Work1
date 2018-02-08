<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: subscribeDetails.ftl 11311 2008-03-13 13:19:59Z mwei $ -->

<#include "../../../includes/eam2008.ftl" />
  <style type="text/css">
    .noBorderLine{
       border-width :0px;
       border-style : none;
       outline-style : none;
       text-align:right;
       width:80%;
       }
    .definedLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        text-align:right;
        width:80%;
    }
  </style>
  <base target="_self">
<@framePage title="${action.getText('')}">	
	<@ww.form namespace="'/spareLocation'" name="'listForm'" action="'searchSpareDetails'" method="'post'">
		<@ww.token name="searchSpareDetailsToken"/>
		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<@ww.hidden name="'allUnitPriceValue'" value=""/>
		<@ww.hidden name="'allSpareLocationIdValue'" value=""/>
		<@ww.hidden name="'allLocationCodeValue'" value=""/>
		<@ww.hidden name="'allWareHouseIdValue'" value=""/>
		<#if spare.id?exists>
		  <@ww.hidden name="'spare.id'" value="#{spare.id}"/>
		</#if>
		<#assign loopNum = 0/>
		<#assign itemNo=1/>
		<#assign departmentIdentityName = 'department' + '${itemNo}'/>
		 <@list title="" excel=false setupTable=false includeParameters="spare.id" fieldMap="">
			<input type="hidden" name="detailIds" value="#{object.id}"/>
			<#if object.warehouse?exists>
			     <input type="hidden" name="warehouseIds" value="#{object.warehouse.id}"/>
			<#else>
			     <input type="hidden" name="warehouseIds" value=""/>
			</#if>
           	<@vcolumn title="${action.getText('itemNo')}">
                ${itemNo}
                <#assign itemNo = itemNo + 1/>
             <@alignCenter />
           </@vcolumn>
          <#--
            <@vcolumn title="${action.getText('spareDtl.dept')}" property="department.name">
            	<@alignLeft />
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('spareDtl.warehouse')}" property="warehouse.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('库区')}" property="regional.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('spareDtl.location')}">
           	    <#assign LocationCode = ''/>
           	    <#assign LocationId = ''/>
				<#if object.location?exists>
				  <#assign LocationCode = "${object.location.code}" />
				  <#assign LocationId = "#{object.location.id}"/>
				</#if>
			    <@eam2008_remoteGetLocation id="${LocationId}" code="${LocationCode}" loopNum="${loopNum}" inputName="location.code" disabled="false"/>
	     	 </@vcolumn>
	     	 <#assign loopNum = loopNum + 1/>
            <@vcolumn title="${action.getText('spareDtl.unitPrice')}" property="unitPrice">
            	<input type="text" name="unitPrice" value="${(object.unitPrice?string('#,###,##0.00'))?if_exists}" 
            	       size="10" class="underline"  style="text-align:right"/>
            </@vcolumn>
             <@vcolumn title="${action.getText('spareDtl.stocks')}" property="stocks">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="占用库存" property="lockedStocks">
              	<@alignRight/>
            </@vcolumn>
		</@list>
        <@buttonBar>
	            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitDetail()'">
	            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
        </@buttonBar>
	</@ww.form>
	<script language="javascript">
	
	  /*
	   * 点击"保存"，验证单价格式，收集单价数据批量提交
	   *
	  */
	  function submitDetail() {
	    if (0 != document.getElementsByName("detailIds").length) {
	      if(informationValidate()) {
	        retrieveSpareLocationIdText();   //获取spareLocation id
	        retrieveUnitPriceText();         //获取单价
	        retrieveLocationCodeText();      //获取库位号
	        retrieveWareHouseIdText();       //获取仓库
	      } else {
	        return false;
	      }
	      return true;
	    }
	    return false;
	  }
	  
	  /*
	   * 验证单价和库存号是否合法
	  */
	  function informationValidate(){
	    var objDetailId = document.getElementsByName("detailIds");
	    var objUnitPrice = document.getElementsByName("unitPrice");
	    for(var i=0;i<objDetailId.length;i++){
		    var unitPrice = objUnitPrice[i].value;
		    /* 验证单价 */
		    if(''== unitPrice){   //验证单价是否为空
		    	alert("${action.getText('di')}"+(i+1)+"${action.getText('row')}"+ ","+"${action.getText('unitPrice.not.null.string')}");
		    	return false;
		    }else{     //验证单价格式
		    	if (!isDoubleNumberCheck(unitPrice) &&　!isNumberBetweenBoolen(number, 1000000001, 0)){   			
	         		alert("${action.getText('di')}"+(i+1)+"${action.getText('row')}"+ "," +"${action.getText('unitPrice.maxLength')}");
	         		return false;
       			}
		    }
		    /* 验证库位号是否存在 */
		    if ("false" == getObjByNameRe("hidden.location.code"+i).value) {
		      var locationCode = getObjByNameRe("location.code"+i).value;
		      alert("${action.getText('di')}"+(i+1)+"${action.getText('row')}"+ "," + locationCode + "${action.getText('location_notFound')}");
		      return false;
		    }
		 }
		  return true;
      }
      /*
       * 获取列表中spareLocation id 的所有值
      */
      function retrieveSpareLocationIdText() {
	    var objDetailId = document.getElementsByName("detailIds");
        var ary = new Array();
        for (var i=0; i<objDetailId.length; i++) {
           ary.push(objDetailId[i].value);
        }
        document.forms[0].elements["allSpareLocationIdValue"].value=ary;
      }
      /*
       * 获取列表中单价的所有值
      */
      function retrieveUnitPriceText() {
	    var objDetailId = document.getElementsByName("detailIds");
	    var objUnitPrice = document.getElementsByName("unitPrice");
        var ary = new Array();
        for (var i=0; i<objDetailId.length; i++) {
          if ('' != objUnitPrice[i].value) {
           ary.push(objDetailId[i].value);
           ary.push(formatDigital(objUnitPrice[i].value));
         }
        }
        document.forms[0].elements["allUnitPriceValue"].value=ary;
      }
      /*
       * 获取列表中库位号的所有值
      */
      function retrieveLocationCodeText() {
        var objDetailId = document.getElementsByName("detailIds");
        var ary = new Array();
        for (var i=0; i<objDetailId.length; i++) {
          ary.push(objDetailId[i].value);
          ary.push(getObjByNameRe("location.code"+i).value);
        }
        document.forms[0].elements["allLocationCodeValue"].value=ary;
      }
      /*
       * 获取列表中仓库的所有值
      */
      function retrieveWareHouseIdText() {
        var objDetailId = document.getElementsByName("detailIds");
        var warehouseIds = document.getElementsByName("warehouseIds");
        var ary = new Array();
        for (var i=0; i<objDetailId.length; i++) {
          ary.push(objDetailId[i].value);
          ary.push(formatDigital(warehouseIds[i].value));
        }
        document.forms[0].elements["allWareHouseIdValue"].value=ary;
      }
	</script>
</@framePage>
