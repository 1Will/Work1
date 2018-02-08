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
	<@ww.form namespace="'/spare'" name="'listForm'" action="'searchOldSpareInventoryBillDetails'" method="'post'">
		<@ww.token name="searchSpareInBillDetailToken"/>
		<@ww.hidden name="'addSpareDetailIds'" value=""/>
		<@ww.hidden name="'spareAccountSelector'" value=""/>
		<@ww.hidden name="'allSpareInventoryBillDtlIds'" value=""/>
		<@ww.hidden name="'allSpareInventoryBillDtlNumberValue'" value=""/>
		<@ww.hidden name="'allSpareInventoryBillDtlCommentValue'" value=""/>
		<@ww.hidden name="'allLocationCodeValue'" value=""/>
		 <input type="hidden" name="valueAry" value=""/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'toolingDevFlag'" value="'${req.getParameter('toolingDevFlag')?if_exists}'"/>
		
		<#if spareInventoryBill.id?exists>
		   <@ww.hidden name="'spareInventoryBill.id'" value="#{spareInventoryBill.id}"/>
		</#if>
		<#if spareInventoryBill.storageGrade.id?exists>
		   <@ww.hidden name="'storageGrade.id'" value="#{spareInventoryBill.storageGrade.id}"/>
		</#if>
		<#if spareInventoryBill.warehouse.id?exists>
		   <@ww.hidden name="'warehouse.id'" value="#{spareInventoryBill.warehouse.id}"/>
		</#if>
		
		<#assign itemNo=1/>
		<#assign loopNum=0/>
		
		 <@list title="" excel=false setupTable=false includeParameters="spareInventoryBill.id" fieldMap="">
			<@vlh.checkbox property="id" name="spareInventoryBillDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
           	<@vcolumn title="项目号">
              <a href="#" onclick="javascript: open_subscribeDtlDialog(#{spareInventoryBill.id},#{object.id})">  ${itemNo}</a>
             <@alignCenter />
           </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="备件编码" property="code">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="备件名称(中文)" property="name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="型号" property="model">
            	<@alignLeft />
            </@vcolumn>
            
             <@vcolumn title="种类" property="category.name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="单位" property="unit.value">
            	<@alignLeft />
            </@vcolumn>          
	     	 
             <@vcolumn title="单价" property="unitPrice" format="${action.getText('currencyFormat')}" >
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="盘点前数量" property="actualNumber">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="盘点前金额(元)" property="actualTotalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="盘点数量" property="inventoryNum">
              <#-- <input type="text" name="inventoryNum" value="${object.inventoryNum?if_exists}" size="10" class="underline"  style="text-align:right"/>-->
            </@vcolumn>
            <@vcolumn title="盘点金额(元)" property="inventoryTotalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="差异" property="different">
            	<@alignRight />
           </@vcolumn>
          
            <@vcolumn title="差额(元)" property="marginPrice"  format="${action.getText('currencyFormat')}">
                    <@alignRight />
            </@vcolumn>
            
            <@vcolumn title="备注" property="comment">

            </@vcolumn>
            <#assign loopNum=loopNum+1/>
		</@list>
        <@buttonBar>
        	<#if !first>
        	<#if !(action.isReadOnly())>
        	
            <@vbutton name="'new'"  class="button" value="${action.getText('新建')}" onclick="open_editSpareInventyDialog()"/>
            
            
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('spareInvenrotyDetail')}?" />
           <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return deleteSpareValue(\"spareInventoryBillDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
            </#if>
        </@buttonBar>
	</@ww.form>
</@framePage>
<script language="javascript">
	window.onload=function(){
	   
	    <#if spareInventoryBill.inventoryDetails.size()!=0>
	        parent.getObjByNameRe("save").disabled=true;
	    <#else>
	       parent.getObjByNameRe("save").disabled=null;
	    </#if>
		//改变父页面的总金额
		<#if spareInventoryBill.id?exists>
            parent.getObjByNameRe("spareInventoryBill.totalPrice").value = '${spareInventoryBill.totalPrice?if_exists}';
        </#if>
		//如果没有盘点明细，"发送提醒"按钮状态改为不可用
		var selector = document.getElementsByName("spareInventoryBillDtlIds");
	  <#if spareInventoryBill.submit==true>
		parent.document.forms[0].elements["submitRecord"].disabled=false;
		<#if spareInventoryBill.inventoryDetails.size()==0>
		parent.document.forms[0].elements["submitRecord"].disabled=true;
		</#if>
	  </#if>
		//显示原输入的信息
		<#if valueAry?exists>
	       var valueAry="${valueAry}";
	       if(valueAry!=""){
	          var ary=valueAry.split("##");
	          for(var i=0;i<ary.length;i=i+3){
	              setSpareValue(ary[i],ary[i+1],ary[i+2]);
	          }
	       }
	    </#if>
	}
	
	function setSpareValue(spareId,spareNumberValue,spareCommentValue){
   	  var selector = document.getElementsByName("spareInventoryBillDtlIds");
	  var allNumberValue = document.getElementsByName("inventoryNum");
	  var allCommentValue = document.getElementsByName("comment");
       if (selector.length) {
	        for (var i = 0; i < selector.length; i++) {
	             if(selector[i].value==spareId){
	                 allNumberValue[i].value=spareNumberValue;
	                 allCommentValue[i].value=spareCommentValue;
	             }
	        }
	   }
	}
   //从备件台帐选择
	<#--function open_selectSpareAccountDialog(){
	  var url = '${req.contextPath}/popup/spareSelector.html?inOutFlag=inventory&toolingDevFlag=TOOLINGDEVICE&spareBillId='+#{spareInventoryBill.id};
	  popupModalDialog(url, 800, 600,choose_spareAccount_information)
	}
	function choose_spareAccount_information(result){
		callBackSpareInventoryValue();
      if (null != result) {
        var spareDetailIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addSpareDetailIds"].value = spareDetailIds;
        document.forms[0].elements["spareAccountSelector"].value = "spareAccount";
        document.forms[0].submit();
      }
	}-->
	//从备件台帐明细选择
	  function open_selectSpareAccountDialog(){
	    var url = '${req.contextPath}/spareLocation/spareLocationSearcherCommon.html?inOutFlag=inventory&toolingDevFlag=TOOLINGDEVICE&spareBillId=#{spareInventoryBill.id}&roleWarehouseId=#{spareInventoryBill.warehouse.id}';
	    popupModalDialog(url, 800, 600,choose_spareAccount_information)
	}
	function choose_spareAccount_information(result){
		callBackSpareInventoryValue();
      if (null != result) {
        var spareDetailIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addSpareDetailIds"].value = spareDetailIds;
        document.forms[0].elements["spareAccountSelector"].value = "spareAccount";
        document.forms[0].submit();
      }
	}
	
	
	function open_subscribeDtlDialog(spareInventoryBillId, spareInventoryDetailId) {
        		var url = '${req.contextPath}/spare/editOldInventoryBillDetailFrame.html?spareInventoryBill.id=' + spareInventoryBillId + '&fromListDtl=true&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
        		if (null != spareInventoryDetailId) {
        			url = url+ '&spareInventoryDetail.id=' + spareInventoryDetailId;
        		}
	      		popupModalDialog(url, Config.popW, Config.popH);
	      		//self.location.reload();
	      		var reloadUrl = '${req.contextPath}/spare/listOldSpareInventoryBillDetails.html?spareInventoryBill.id=' + spareInventoryBillId+'&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	            //如果是点击项目号进入维护页面，保存当前页，已使在维护页面点击关闭，会回到当前页
	            if (null != spareInventoryDetailId) {
	            	reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
	            }
	            self.location.href = reloadUrl;
	      		return false;
    }
        	
	//新建
	function open_editSpareInventyDialog(){
	    var url = '${req.contextPath}/spare/editOldInventoryBillDetailFrame.html?inOutFlag=inventory&toolingDevFlag=TOOLINGDEVICE&spareInventoryBill.id=#{spareInventoryBill.id}&roleWarehouseId=#{spareInventoryBill.warehouse.id}&storageGrade.id=#{spareInventoryBill.storageGrade.id?if_exists}&warehouse.id=${spareInventoryBill.warehouse.id?if_exists}';
	    popupModalDialog(url, 1024, 900);//(url,宽,高)
	    self.location.reload();
	}
	function choose_spareInventy_information(result){
		callBackSpareInventoryValue();
      if (null != result) {
        var spareDetailIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addSpareDetailIds"].value = spareDetailIds;
        document.forms[0].elements["spareAccountSelector"].value = "spareAccount";
        document.forms[0].submit();
      }
	}
	
	
	//保存盘点单明细
	function submitDetailIds(){
	  if(0!=document.getElementsByName("spareInventoryBillDtlIds").length){
	  if(informationValidate()==true){
	     retrieveSpareInventoryBillDetailIdText();
	     retrieveSpareInventoryBillDetailInventoryNumText();
	     retrieveSpareInventoryBillDetailInventoryCommentText();
	    <#--retrieveSpareInBillDetailLocationNoText();-->
	  	return true;
	   }else{
	     return false;
	   }
	  }
	}
	//获得所有的盘点单明细的ids
	function retrieveSpareInventoryBillDetailIdText(){
	  var allSpareInventoryBillDtlId = document.getElementsByName("spareInventoryBillDtlIds");
        var ary = new Array();
        for (var i=0; i<allSpareInventoryBillDtlId.length; i++) {
          ary.push(allSpareInventoryBillDtlId[i].value);
        }
        document.forms[0].elements["allSpareInventoryBillDtlIds"].value = ary;
	}
		//获取入库明细的所有库位
	function retrieveSpareInBillDetailLocationText(){
	    var allSpareInBillDtlId = document.getElementsByName("spareInventoryBillDtlIds");
        var ary = new Array();
       for (var i=0; i<allSpareInBillDtlId.length; i++) {
           ary.push(allSpareInBillDtlId[i].value);
           ary.push(getObjByNameRe("location.code"+i).value);
       }
        document.forms[0].elements["allLocationCodeValue"].value=ary;
	}
	//获得所有的盘点单明细的盘点数量
	function retrieveSpareInventoryBillDetailInventoryNumText(){
	 var allSpareInventoryBillDtlId = document.getElementsByName("spareInventoryBillDtlIds");
        var allInventoryNumberValue = document.getElementsByName("inventoryNum");
        var ary = new Array();
       for (var i=0; i<allSpareInventoryBillDtlId.length; i++) {
          if ('' != allInventoryNumberValue[i].value) {
           ary.push(allSpareInventoryBillDtlId[i].value);
           ary.push(allInventoryNumberValue[i].value);
         }
       }
      document.forms[0].elements["allSpareInventoryBillDtlNumberValue"].value=ary;
      }
    //获得所有的盘点单明细的备注
    function retrieveSpareInventoryBillDetailInventoryCommentText(){
    	var allSpareInventoryBillDtlId = document.getElementsByName("spareInventoryBillDtlIds");
    	var allInventoryCommentValue = document.getElementsByName("comment");
    	var ary = new Array();
    	for (var i=0;i<allSpareInventoryBillDtlId.length; i++){
    		if(''!=allInventoryCommentValue[i].value){
    			ary.push(allSpareInventoryBillDtlId[i].value);
    			ary.push(allInventoryCommentValue[i].value);
    		}
    	}
    	document.forms[0].elements["allSpareInventoryBillDtlCommentValue"].value = ary;
    }
    //获得所有盘点明细的库位号
  <#-- function retrieveSpareInBillDetailLocationNoText(){
        var allSpareInventoryBillDtlId = document.getElementsByName("spareInventoryBillDtlIds");
    	var allInventorylocationCode = document.getElementsByName("locationCode");
    	for(var i=0;i<allSpareInventoryBillDtlId.length;i++){
    	    var locationCode = allInventorylocationCode[i].value;
    	    if(''==locationCode){
    	       alert("${action.getText('row')}"+(i+1)+"${action.getText('spareInventoryDtl.locationNo.null')}");
		    	return false;
    	    }
    	}
      
   }-->
    //验证盘点数量
    function informationValidate(){
      	var allSpareInventoryBillDtlId = document.getElementsByName("spareInventoryBillDtlIds");
	    var allinventoryNumberValue = document.getElementsByName("inventoryNum");
	    var allInventoryCommentValue = document.getElementsByName("comment");
	    	var allInventorylocationCode = document.getElementsByName("locationCode");
	     for(var i=0;i<allSpareInventoryBillDtlId.length;i++){
		    var number = allinventoryNumberValue[i].value;
		    if(''== number){
		    	alert("${action.getText('row')}"+(i+1)+"${action.getText('spareInventoryDtl.number.null')}");
		    	return false;
		    }else{
		       //验证格式
		    	if (!isDoubleNumberCheck(number)){   			
	         		alert("${action.getText('row')}"+(i+1)+"${action.getText('spareInventoryDtl.number.format.error')}");
	         		return false;
       			}else if(!isNumberBetweenBoolen(number, 1000000001, 0)){
       				alert("${action.getText('row')}"+(i+1)+"${action.getText('spareInventoryDtl.number.maxLength')}");
       				return false;
       			}
		    }
		    
		   	//验证备注有效范围
		  	if(''!= allInventoryCommentValue[i].value){
		  		var comment = allInventoryCommentValue[i].value;
		  		if (!isValidLengthCheck(document.forms[0],comment,null,250)) {
	       			alert("${action.getText('row')}"+(i+1)+"${action.getText("spareInventoryDtl.comment.maxLength")}");
		   			return false;
	     		}
		  	}
		  
    	for(var i=0;i<allSpareInventoryBillDtlId.length;i++){
    	    var locationCode = allInventorylocationCode[i].value;
    	    if(''==locationCode){
    	       alert("${action.getText('row')}"+(i+1)+"${action.getText('spareInventoryDtl.locationNo.null')}");
		    	return false;
    	    }
    	}
		 }
		  return true;
     }
    function deleteSpareValue(boxName,message){
	  //callBackSpareInventoryValue();
	  if(confirmDeletes(boxName, message)==true){
		return true;
	  }else{
	  	return false;
	  }
    }
     
     function callBackSpareInventoryValue(){
     	var selector = document.getElementsByName("spareInventoryBillDtlIds");
	  	var allNumberValue = document.getElementsByName("inventoryNum");
	  	var allCommentValue = document.getElementsByName("comment");
	  	var valueAry="";
	  if (selector.length) {
	  	for(var i=0;i<selector.length;i++){
	  	  if (!selector[i].checked) {
	        valueAry+=selector[i].value+"##";
	        valueAry+=allNumberValue[i].value+"##";
	        valueAry+=allCommentValue[i].value+"##";
	  	  }
	  	}
	  }
	  valueAry=valueAry.substring(0,valueAry.lastIndexOf("##"));
	  document.forms[0].elements["valueAry"].value=valueAry;
     }
</script>