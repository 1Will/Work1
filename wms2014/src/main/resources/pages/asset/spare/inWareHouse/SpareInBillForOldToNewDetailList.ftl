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
	<@ww.form namespace="'/spare'" name="'listForm'" action="'searchSpareInBillForOldToNewDetails'" method="'post'">
		<@ww.token name="searchSpareInBillDetailToken"/>
		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<@ww.hidden name="'addSpareDetailIds'" value=""/>
		<@ww.hidden name="'spareAccountSelector'" value=""/>
		<@ww.hidden name="'addPurBillDetailIds'" value=""/>
		<@ww.hidden name="'purBillDetailSelector'" value=""/>
		<@ww.hidden name="'addSpareBorrowDetailIds'" value=""/>
		<@ww.hidden name="'spareBorrowSelector'" value=""/>
		<@ww.hidden name="'allLocationCodeValue'" value=""/>
		
		<@ww.hidden name="'addSpareIds'" value=""/>
		<@ww.hidden name="'spareSelector'" value="" />
		
		<@ww.hidden name="'addSpareOutBillDtlIds'" value="" />
		<@ww.hidden name="'spareOutBillDtlSelector'" value="" />
		<@ww.hidden name="'notFather'" id="notFather" value="" />
		 <input type="hidden" name="isOld" value="true"/>
	    <input type="hidden" name="currentRowNum" value=""/>
		<input type="hidden" name="allSpareInBillDtlIds" value=""/>
		<input type="hidden" name="allSpareInBillDtlNumberValue" value=""/>
		<input type="hidden" name="allSpareInBillDtlNameValue" value=""/>
		<input type="hidden" name="allSpareInBillDtlModelValue" value=""/>
		<input type="hidden" name="allSpareInBillDtlDisableValue" value=""/>
		 
		<input type="hidden" name="allSpareInBillDtlTaxPriceValue" value=""/> 
		<input type="hidden" name="allSpareInBillDtlCommentValue" value=""/>
		<input type="hidden" name="valueAry" value=""/>
		<input type="hidden" name="allSpareInBillDetailDepartment" value=""/>
		<input type="hidden" name="allSpareInBillDetailRegional" value=""/>
		<input type="hidden" name="addFalse" id="addFalse" value=""/>
	 
		
		<@ww.hidden name="'flag'" value="'${req.getParameter('flag')?if_exists}'"/>
		<@ww.hidden name="'toolingDevFlag'" value="'${req.getParameter('toolingDevFlag')?if_exists}'"/>
		<@ww.hidden name="'roleWarehouseId'" value="'${req.getParameter('roleWarehouseId')?if_exists}'"/> 
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if spareInBill.id?exists>
		<@ww.hidden name="'spareInBill.id'" value="#{spareInBill.id}"/>
		</#if>
		<#assign itemNo=1/>
		<#assign loopNum=0/>
		
		<#assign arrivalNo=1/>
		
		<#assign locationIdentity = 'locationNum' + '${loopNum}'/>  
		<#assign departmentIdentityName = 'department' + '${itemNo}'/>
		<#assign warehouseIdentityName = 'warehouse' + '${itemNo}'/>
		<#assign regionalIdentityName = 'regional' + '${itemNo}'/>
		<#assign arrivalNumIdentityName = 'arrivalNum' + '${arrivalNo}'/>
		
		 <@list title=""  includeParameters="spareInBill.id" fieldMap="" >
		      <#assign arrivalNo = arrivalNo + 1/>
			<input type="hidden" name="detailIds" value="#{object.id}"/>
			<input type="hidden" name="#{object.id}" value="${object.instatus}"/>
			<input type="hidden" name="maxNumber" value="${object.number?if_exists}"/>
			<@vlh.checkbox property="id" name="spareInBillDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
           	<@vcolumn title="项目号">
                ${itemNo}
                <#assign itemNo = itemNo + 1/>
             <@alignCenter />
           </@vcolumn>
            <input type="hidden" name="spareNo" value="${object.code?if_exists}"/>
            <@vcolumn title="备件编码" property="code">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="备件名称(中文)" >
            <input type="text" name="name" 
	    		  class="noBorderLine"  value="${object.name?if_exists}"  
	    		  maxlength="55" size="8" style="text-align:left" />           	
            	<@alignLeft />
            </@vcolumn>
           
            <@vcolumn title="型号" >
            	<input type="text" name="spare.modelSpecs" 
	    		  class="noBorderLine"  value="${object.spare.modelSpecs?if_exists}"  
	    		  maxlength="55" size="8" style="text-align:left" />           	
            	<@alignLeft />
            </@vcolumn>
           
             <@vcolumn title="种类" property="spare.category.name">
            	<@alignLeft />
            </@vcolumn>
            
              <@vcolumn title="单位" property="spare.unit.value">
            	<@alignLeft />
             </@vcolumn>
			
          
            <#--采购数量-->
            <#if (object.poDetail.amount)?exists>
              <@vcolumn title="${action.getText('purchaseNumber')}">
                <input type="text" name="purchaseNumber" 
	    		  class="noBorderLine"  value="${object.purchaseNum?if_exists}"  
	    		  maxlength="50" size="8"style="text-align:right" disabled/>           	
                <@alignRight />
             </@vcolumn>
           <#elseif object.spareOutBillDtl?exists><#--出库单-->
             <@vcolumn title="出库数量" >
              <input type="text" name="purchaseNumber" 
	    		  class="noBorderLine"  value="${object.spareOutBillDtl.number?if_exists}"  
	    		  maxlength="50" size="8"style="text-align:right" disabled/>           	
                <@alignRight />
             </@vcolumn>
            <#else><#--出库单为一级库入二级库-->
               <@vcolumn title="出库数量" >
                  <input type="text" name="purchaseNumber" 
	    		  class="noBorderLine"  value="${object.number?if_exists}"  
	    		  maxlength="50" size="8"style="text-align:right" disabled/>           	
                <@alignRight />
             </@vcolumn>
           </#if>
             
            <#--入库数量--> 
            <#if object.instatus?exists>
              <#if '${object.instatus}' == 'INWAREHOUSED'>
                  <@vcolumn title="入库数量">
             	    <input type="text" name="spareInNumber" class="underline"  value="${object.number?if_exists}"  
             	     maxlength="50" size="8" onchange="changValue()" style="text-align:right" disabled/>
            	   <@alignRight />
                 </@vcolumn>
               </#if>
              <#if '${object.instatus}' == 'NEW'>
               <@vcolumn title="入库数量">
                    <#if object.poDetail?exists> 
                        <input type="hidden" name="arrivalAmount"  value="${object.poDetail.arrivalAmount?if_exists}" />
                        <#--
                    <#elseif object.spareOutBillDtl?exists>
                         <input type="hidden" name="arrivalAmount"   value="${object.spareOutBillDtl.number?if_exists}" />-->
                    <#else>
                         <input type="hidden" name="arrivalAmount"   value="" />
                    </#if>
                        
                      <input type="text" name="spareInNumber" class="underline" 
                             value="${object.number?if_exists}"  
             	             maxlength="50" size="8" 
             	             onchange="return changeTotalPrice(#{object.id})"
             	       <#--   
             	           <#if object.poDetail?exists>
             	              onchange="return chickArrailNum()" 
             	              onclick="return chickArrailNum()"
             	           </#if>-->
             	         style="text-align:right"/>   
                 <@alignRight />
            </@vcolumn>
            </#if>
            </#if>
            
           <#--单价 -->
           <#if notFather >
	            <@vcolumn title="单价" format="${action.getText('currencyFormat')}">
	            	<input type="text" 
		            	name="spareIn.taxPrice" 
		            	value="${(object.taxPrice?string('#,###,##0.00'))?if_exists}"  
		            	class="noBorderLine"  size="10"  
		            	onchange="return changeTotalPrice(#{object.id})"
		            	style="text-align:right" 
		            	style="width:60;overflow-x:visible;overflow-y:visible;"/>
		            	<@alignRight />
	            </@vcolumn>
            <#else>
	           <@vcolumn title="单价" format="${action.getText('currencyFormat')}">
	            	<input type="text" 
		            	name="spareIn.taxPrice" 
		            	value="${(object.taxPrice?string('#,###,##0.00'))?if_exists}"  
		            	class="underLine"  size="10"  
		            	onchange="return changeTotalPrice(#{object.id})"
		            	style="text-align:right" 
		            	style="width:60;overflow-x:visible;overflow-y:visible;"/>
		            	<@alignRight />
	            </@vcolumn>
            </#if>   
                     
            <@vcolumn title="金额(元) " format="${action.getText('currencyFormat')}">
            	<input type="text" name="totalPrice" value="${(object.totalPrice?string('#0.00'))?if_exists}"  
	            	class="noBorderLine" size="10" readonly="true" 
	            	style="text-align:right" style="width:60;overflow-x:visible;overflow-y:visible;"/>
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="入库前库存">
            	${object.stocksBeforeInOrOut?if_exists}<input type="hidden" name="stocksBeforeInOrOut" value="#{object.stocksBeforeInOrOut?if_exists}"  class="underline" disabled="true" readonly="true" />
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="入库后库存" property="stocksAfterInOrOut">
            	<@alignRight />
            </@vcolumn>
             <@vcolumn title="是否可用">
            	 <input type="radio" name="yesOrNo${itemNo}" id = "yes${itemNo}"  <#if object.disableSpare?exists><#if (object.disableSpare == 'Y')> checked </#if> </#if> value="Y">是 </input>
	                <input type="radio" name="yesOrNo${itemNo}" id ="no${itemNo}"   <#if object.disableSpare?exists><#if (object.disableSpare == 'N')> checked </#if> </#if> value="N">否</input>
            </@vcolumn>
            <@vcolumn title="备注">
            	 <input type="text" name="comment" 
	    		   class="underline"  value="${object.comment?if_exists}"  maxlength="250" size="10"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            </@vcolumn>
            <#if object.instatus?exists>
              <#if '${object.instatus}' == 'INWAREHOUSED'>
               <#assign spareInDtlStatus = "${action.getText('INWAREHOUSED')}"/>
               </#if>
              <#if '${object.instatus}' == 'NEW'>
              <#assign spareInDtlStatus = "${action.getText('NEW')}"/>
            </#if>
            </#if>
               <@vcolumn title="状态" attributes="width='50'">
           ${spareInDtlStatus}
                 <@alignLeft/>
            </@vcolumn>
               <#assign loopNum=loopNum+1/>
		</@list>
        <@buttonBar>
        	<#if !first>
        	<#if !(action.isReadOnly())>
        	<@vbutton name="'selectSpare'"  class="button" value="从旧件备件库台账选择"  onclick="open_spareDialog()"/>
          
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitDetailIds()'">
            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            
             <@vsubmit name="new" value="'${action.getText('new')}'" onclick="'return changeAddFalse();'">
            </@vsubmit>
            
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('spareInBillDetail')}?" />
           <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"spareInBillDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
            </#if>
        </@buttonBar>
	</@ww.form>
</@framePage>
<script language="javascript">
window.onload=function(){
   <#--
		<#if spareInBill.status!='NEWSTATUS'>
		     document.forms[0].elements["save"].disabled="true";			
      		 document.forms[0].elements["'selectSpare'"].disabled="true";
      	 	 document.forms[0].elements["'selectPurBillDtl'"].disabled="true";
      	 	 document.forms[0].elements["delete"].disabled="true";
        </#if>-->
	   //修改父窗口的控件事件,获得父窗口的控件，在想其中加入事件。 hjia 2010-5-26
		<#if !valueListNoRecords>
			var tag = parent.getObjByNameRe("warehouse.id");
			tag.onmousemove=function(){this.setCapture();}
			tag.onmouseout=function(){this.releaseCapture();}
			tag.onfocus=function(){this.blur();}
			
		    var grade = parent.getObjByNameRe("storageGrade.id");
			grade.onmousemove=function(){this.setCapture();}
			grade.onmouseout=function(){this.releaseCapture();}
			grade.onfocus=function(){this.blur();}
			
			var outWarehouse = parent.getObjByNameRe("outWarehouse.id");
			outWarehouse.onmousemove=function(){this.setCapture();}
			outWarehouse.onmouseout=function(){this.releaseCapture();}
			outWarehouse.onfocus=function(){this.blur();}
			
	    </#if>
	    
      <#if !(action.isReadOnly()) && spareInBill.status!='NEWSTATUS'>	
             var selector = document.getElementsByName("spareInBillDtlIds");
             var num=0;
             if(selector.length){
              for(var i=0;i<selector.length;i++){
                  var id = selector[i].value;
                  var statu = getObjByName(id).value;
                  if('NEW'==statu){
                     num++;
                  }else{
                       var inputAry = getObjByName(id).parentNode.parentNode.getElementsByTagName("input");
                       for(var a=0;a<inputAry.length;a++){
                         inputAry[a].disabled="true";
                       }
                     
                  }
             }
             
            
           
           }	
   
      </#if>
    
    
	  <#if req.getParameter('notFather')?has_content>
		  getObjByName('notFather').value=${req.getParameter('notFather')};
    </#if>
    
    <#if req.getParameter('errorFlag')?has_content>
		alert("${action.getText('delete.spareInDtl.failure')}");
    </#if>
	<#if spareInBill.status?exists>
   		    <#assign spareInStatus = ''/>
   			<#if '${spareInBill.status}' == 'NEWSTATUS'>
   			<#assign spareInStatus = "${action.getText('NEWSTATUS')}"/>
   			<#elseif '${spareInBill.status}' == 'INWAREHOUSEING'>
   			<#assign spareInStatus = "${action.getText('INWAREHOUSEING')}"/>
   			<#elseif '${spareInBill.status}' == 'INWAREHOUSEED'>
   			<#assign spareInStatus = "${action.getText('INWAREHOUSEED')}"/>
   			</#if>
   			parent.document.forms["spareInBillProfile"].elements["spareInBillStatus"].value = '${spareInStatus}';
   			parent.document.forms["spareInBillProfile"].elements["status"].value = '${spareInBill.status}';
   	</#if>
		 //改变父页面的总金额
		 <#if spareInBill.id?exists>
             parent.getObjByNameRe("spareInBill.totalPrice").value = '${(spareInBill.totalPrice?string('#,###,##0.00'))?if_exists}';
          </#if>
		//如果没有盘点明细，“发送提醒”按钮状态改为不可用
		var selector = document.getElementsByName("spareInBillDtlIds");
		<#--if (!selector.length) {
		parent.document.forms[0].elements["submitRecord"].disabled="true";
		}-->
		//显示原输入的信息
		<#if valueAry?exists>
	       var valueAry="${valueAry}";
	       if(valueAry!=""){
	          var ary=valueAry.split("##");
	          for(var i=0;i<ary.length;i=i+4){
	              setSpareValue(ary[i],ary[i+1],ary[i+2],ary[i+3]);
	          }
	       }
	    </#if>
	    <#--
    <#if !(action.isReadOnly())>
	<#if spareInBill.submit==true>
		parent.document.forms[0].elements["submitRecord"].disabled=false;
		<#if spareInBill.detail.size()==0>
		parent.document.forms[0].elements["submitRecord"].disabled=true;
		</#if>
	</#if>
	</#if>
	-->
	
	}
	
	function chickArrailNum(){
	   //alert(${arrivalNo});
	  // var tag = 'arrivalNum'+(${arrivalNo}-1);
	  // var purAmount = getObjByName('purchaseNumber').value;
	  // alert(purAmount);
	   
	   
	  var selector = document.getElementsByName("spareInBillDtlIds");
	  var allNumberValue = document.getElementsByName("spareInNumber");
	  var allArrivalAmountValue = document.getElementsByName("arrivalAmount");
	  var allPurchaseNumberValue = document.getElementsByName("purchaseNumber");
	  
	 // alert(allPurchaseNumberValue[1].value);
	   if(selector.length){
	      for (var i = 0; i < selector.length; i++) {
	      
		         var spareNum = allNumberValue[i].value;//入库数量
		         var arrAmount = allArrivalAmountValue[i].value;//已到货数量
		         var purNum = allPurchaseNumberValue[i].value;//采购数量
		         if('' !=arrAmount){
		             if((parseInt(spareNum)+parseInt(arrAmount))>parseInt(purNum)){
		                 alert("该明细已入库"+arrAmount+"条，此次最多入库"+(parseInt(purNum)-parseInt(arrAmount))+"条");
		               //  allNumberValue[i].value = (parseInt(purNum)-parseInt(arrAmount));
		                 
		                 document.forms[0].elements["save"].disabled="true";			
      		             document.forms[0].elements["'selectSpare'"].disabled="true";
			      	 	 document.forms[0].elements["'selectPurBillDtl'"].disabled="true";
			      	 	 document.forms[0].elements["delete"].disabled="true";
		             }else{
		                 document.forms[0].elements["save"].disabled=null;			
      		             document.forms[0].elements["'selectSpare'"].disabled=null;
			      	 	 document.forms[0].elements["'selectPurBillDtl'"].disabled=null;
			      	 	 document.forms[0].elements["delete"].disabled=null;
		             }  
		        }
		  }
		  
	   }
	   
	}
	
	
	function setSpareValue(spareId,spareNumberValue,spareTotalPriceValue,spareCommentValue){
   	  var selector = document.getElementsByName("spareInBillDtlIds");
	  var allNumberValue = document.getElementsByName("spareInNumber");
	  var allTotalPriceValue = document.getElementsByName("totalPrice");
	  var allCommentValue = document.getElementsByName("comment");
       if (selector.length) {
		        for (var i = 0; i < selector.length; i++) {
		             if(selector[i].value==spareId){
		                 allNumberValue[i].value=spareNumberValue;
		                 allTotalPriceValue[i].value=spareTotalPriceValue;
		                 allCommentValue[i].value=spareCommentValue;
		             }
		        }
	   }
	}
	
	//从备件台账选择
    function open_spareDialog(){
        var url = '${req.contextPath}/popup/spareSelector.html?isOld=true&inOutFlag=spareInBill&toolingDevFlag=TOOLINGDEVICE&spareBillId='+#{spareInBill.id};
	    popupModalDialog(url, gw, gh, refresh_spareDetail_information);
    }
    
    function refresh_spareDetail_information(){
      callBackSpareInNumberAndCommnet();
	  if(null!=result){
	     var purBillDtlIds = result.substring(0, result.lastIndexOf(","));
	     document.forms[0].elements["addSpareIds"].value = purBillDtlIds;
         document.forms[0].elements["spareSelector"].value = "fromSpare";//页面是从备件台账选择的标志
         document.forms[0].submit();
	  }
    }
    

	
	//从采购明细选择
	function open_purchaseBillDtlDialog(){
	   var url = '${req.contextPath}/spare/listPurchaseBillDetailSelector.html?spareBillId='+#{spareInBill.id};
	   popupModalDialog(url, gw, gh, refresh_purchaseBillDtl_information);
	}
	function  refresh_purchaseBillDtl_information(result){
	  callBackSpareInNumberAndCommnet();
	  if(null!=result){
	     var purBillDtlIds = result.substring(0, result.lastIndexOf(","));	    
	     document.forms[0].elements["addPurBillDetailIds"].value = purBillDtlIds;
         document.forms[0].elements["purBillDetailSelector"].value = "PurBillDetail";
         document.forms[0].submit();
	  }
	}
	
	
    	

	//从备件库台帐选择
	function open_selectSpareAccountDialog(){
	 <#if notFather >
	   <#if outWarehouseId?exists>
	       var outWarehouseId = ${outWarehouseId?if_exists};
	       if(outWarehouseId !=''){
	        var url = '${req.contextPath}/spareLocation/spareLocationSearcherCommon.html?inOutFlag=in&toolingDevFlag=TOOLINGDEVICE&spareBillId=#{spareInBill.id}&fromSpareInBillDetail=true&roleWarehouseId='+outWarehouseId;
	    } 
	   <#elseif  spareInBill.outWarehouse?exists>
	     var url = '${req.contextPath}/spareLocation/spareLocationSearcherCommon.html?inOutFlag=in&toolingDevFlag=TOOLINGDEVICE&spareBillId=#{spareInBill.id}&roleWarehouseId=#{spareInBill.outWarehouse.id}&fromSpareInBillDetail=true';
	     </#if> 
	 </#if>
	  popupModalDialog(url, 800, 600,choose_spareAccount_information)
	}
	function choose_spareAccount_information(result){
	  callBackSpareInNumberAndCommnet();
      if (null != result) {
        var spareDetailIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addSpareDetailIds"].value = spareDetailIds;
        document.forms[0].elements["spareAccountSelector"].value = "spareAccount";
        document.forms[0].elements["notFather"].value = "true";
        document.forms[0].submit();
      }
	}
    
	
    //从出库单明细选择
    function open_spareOutBillDialog(){
         //进入仓库
        var outWarehouseId = parent.getObjByNameRe("warehouse.id").value;
        //来源仓库
        var warehouseId = parent.getObjByNameRe("outWarehouse.id").value;
        var url = '${req.contextPath}/spare/openSpareOutBillDetail.html?spareBillId='+#{spareInBill.id} + '&outWarehouseId='+outWarehouseId + '&warehouse.id='+warehouseId;
	   popupModalDialog(url, gw, gh, refresh_spareOutBill_information);
    }
    
    function refresh_spareOutBill_information(){
      callBackSpareInNumberAndCommnet();
        
	  if(null!=result){
	     var purBillDtlIds = result.substring(0, result.lastIndexOf(","));
	     document.forms[0].elements["addSpareOutBillDtlIds"].value = purBillDtlIds;
         document.forms[0].elements["spareOutBillDtlSelector"].value = "fromSpareOutBillDtl";//页面是从出库单选择的标志
         document.forms[0].elements["notFather"].value = "true";
         document.forms[0].submit();
	  }
    }
    
   function changeAddFalse(){
    getObjByNameRe("addFalse").value="yes";
    }
    
	//保存入库明细
	function submitDetailIds(){
	  <#if spareInBill?exists>
	     <#if !spareInBill.inPeople?exists>
	         alert("请先保存入库人！");
	         var tag = parent.getObjByNameRe("saveSpareInBill_spareIn.inPeople");
	         tag.focus();
	         return false;
	    </#if>
	  </#if>
	
	  if(0!=document.getElementsByName("spareInBillDtlIds").length){
	  
	  	 if(informationValidate()==true){				//保存时信息安全验证
		  	 retrieveSpareInBillDetailIdText();
		  	 retrieveSpareInBillDetailNumberText();
		  	 retrieveSpareInBillDetailTaxPriceText();
		  	 retrieveSpareInBillDetailCommentText();
		  	 retrieveSpareInBillDetailNameText();
		  	 retrieveSpareInBillDetailModelText();
		  	 retrieveSpareInBillDetailDisableText();
		  	 return true;
	  	 }else{
	  	 	return false;
	  	 }
	  }
	}
	function retrieveSpareInBillDetailDisableText(){
        var allSpareInBillDtlId = document.getElementsByName("spareInBillDtlIds");
        var spareAry="";
        var ary = new Array();
       for (var i=0; i<allSpareInBillDtlId.length; i++) {
       var k=i+2;
       var  yesOrNo = document.getElementsByName("yesOrNo"+k);
       var disableSpare = '';
        for(var j=0;j<yesOrNo.length;j++){
        if(yesOrNo[j].checked){
        disableSpare =yesOrNo[j].value;
        }
        
        }
          if ('' != disableSpare) {
           ary.push(allSpareInBillDtlId[i].value);
           ary.push(disableSpare);
           }
          
       }
      document.forms[0].elements["allSpareInBillDtlDisableValue"].value=ary;
    }
	//获得入库明细的所有部门 
	function retrieveSpareInBillDetailDepartmentText()
	{
	   var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         var departmentTagName = 'department';
         departmentTagName = departmentTagName + (i+1); 
        
         if(-1 != document.forms["listForm"].elements[departmentTagName].value) {
           ary.push(allPreRepairPlanDetailDetailId[i].value);
           ary.push(document.forms["listForm"].elements[departmentTagName].value);
         } 
       }
        
       document.forms["listForm"].elements["allSpareInBillDetailDepartment"].value = ary;
	}
	<#--
	//获得入库明细的所有库区
	function retrieveSpareInBillDetailRegionalText()
	{
	   var allPreRepairPlanDetailDetailId = document.getElementsByName("detailIds");
       var ary = new Array();
       for (var i=0; i<allPreRepairPlanDetailDetailId.length; i++) {
         var regionalTagName = 'regional';
         regionalTagName = regionalTagName + (i+1); 
         if(-1 != document.forms["listForm"].elements[regionalTagName].value) {
           ary.push(allPreRepairPlanDetailDetailId[i].value);
           ary.push(document.forms["listForm"].elements[regionalTagName].value);
         }
       }
       document.forms["listForm"].elements["allSpareInBillDetailRegional"].value = ary;
	}
	-->
	//获取入库明细的所有库位
	<#--function retrieveSpareInBillDetailLocationText(){
	    var allSpareInBillDtlId = document.getElementsByName("spareInBillDtlIds");
        var ary = new Array();
       for (var i=0; i<allSpareInBillDtlId.length; i++) {
           ary.push(allSpareInBillDtlId[i].value);
           ary.push(getObjByNameRe("moveId.location.code"+i).value);
       }
        document.forms[0].elements["allLocationCodeValue"].value=ary;
	}-->
	//获得入库明细的所有ID
	function retrieveSpareInBillDetailIdText(){
		var allSpareInBillDtlId = document.getElementsByName("spareInBillDtlIds");
        var ary = new Array();
        for (var i=0; i<allSpareInBillDtlId.length; i++) {
          ary.push(allSpareInBillDtlId[i].value);
        }
        document.forms[0].elements["allSpareInBillDtlIds"].value = ary;
	}
	      /*
       * 获取列表中库位号的所有值
      */
      function retrieveLocationCodeText() {
        var objDetailId = document.getElementsByName("spareInBillDtlIds");
        var ary = new Array();
        for (var i=0; i<objDetailId.length; i++) {
          ary.push(objDetailId[i].value);
          ary.push(getObjByNameRe("location.code"+i).value);
        }
        document.forms[0].elements["allLocationCodeValue"].value=ary;
      }
	//获得入库明细的所有入库数量
    function retrieveSpareInBillDetailNumberText(){
        var allSpareInBillDtlId = document.getElementsByName("spareInBillDtlIds");
        var allNumberValue = document.getElementsByName("spareInNumber");
        var ary = new Array();
       for (var i=0; i<allSpareInBillDtlId.length; i++) {
          if ('' != allNumberValue[i].value) {
           var number = parseInt(formatDigital(allNumberValue[i].value));
           ary.push(allSpareInBillDtlId[i].value);
           ary.push(number);
         }
       }
      document.forms[0].elements["allSpareInBillDtlNumberValue"].value=ary;
    }
    
    //获得入库明细的所有备件名称
    function retrieveSpareInBillDetailNameText(){
        var allSpareInBillDtlId = document.getElementsByName("spareInBillDtlIds");
        var allNameValue = document.getElementsByName("name");
        var ary = new Array();
       for (var i=0; i<allSpareInBillDtlId.length; i++) {
          if ('' != allNameValue[i].value) {
           ary.push(allSpareInBillDtlId[i].value);
           ary.push(allNameValue[i].value);
         }
       }
      document.forms[0].elements["allSpareInBillDtlNameValue"].value=ary;
    }
    
      //获得入库明细的所有备件名称
    function retrieveSpareInBillDetailModelText(){
        var allSpareInBillDtlId = document.getElementsByName("spareInBillDtlIds");
        var allModelValue = document.getElementsByName("spare.modelSpecs");
        var ary = new Array();
       for (var i=0; i<allSpareInBillDtlId.length; i++) {
          if ('' != allModelValue[i].value) {
           ary.push(allSpareInBillDtlId[i].value);
           ary.push(allModelValue[i].value);
         }
       }
      document.forms[0].elements["allSpareInBillDtlModelValue"].value=ary;
    }
    
    
    //获得所有入库明细的单价
    function retrieveSpareInBillDetailTaxPriceText(){
        var allSpareInBillDtlId = document.getElementsByName("spareInBillDtlIds");
        var allTaxPriceValue = document.getElementsByName("spareIn.taxPrice");
        var ary= new Array();
        for (var i=0; i<allSpareInBillDtlId.length; i++) {
          if ('' != allTaxPriceValue[i].value) {
	           var taxPrice = parseFloat(formatDigital(allTaxPriceValue[i].value));
	           ary.push(allSpareInBillDtlId[i].value);
	           ary.push(taxPrice);
         }
       }
        document.forms[0].elements["allSpareInBillDtlTaxPriceValue"].value=ary;
    }
    //获得入库明细的所有备注
    function retrieveSpareInBillDetailCommentText(){
        var allSpareInBillDtlId = document.getElementsByName("spareInBillDtlIds");
        var allCommentValue = document.getElementsByName("comment");
        var spareAry="";
        var ary = new Array();
       for (var i=0; i<allSpareInBillDtlId.length; i++) {
          if ('' != allCommentValue[i].value) {
           ary.push(allSpareInBillDtlId[i].value);
           ary.push(allCommentValue[i].value);
           }
          
       }
      document.forms[0].elements["allSpareInBillDtlCommentValue"].value=ary;
    }
    //验证同一入库单中入同种备件，如果价格不同，提示入不同库位
   function spareValidate(){
      var allSpareInBillDtlId = document.getElementsByName("spareInBillDtlIds");
      var allSpareNoSelector =  document.getElementsByName("spareNo");
      var allTaxPriceSelector = document.getElementsByName("spareIn.taxPrice");
       
      var length = allSpareInBillDtlId.length;
      if(length>1){
           var spareNo="";
           var taxPrice="";
           var locationCode="";
           
        for(var v=0;v<length;v++){
           if(null != allSpareNoSelector[v]){
               spareNo = allSpareNoSelector[v].value;
           }
           if(null != allTaxPriceSelector[v]){
                taxPrice = allTaxPriceSelector[v].value;
           }
           if(null != getObjByName("location.code"+v)){
               locationCode = getObjByName("location.code"+v).value;
           }
           if(""!=spareNo && ""!=taxPrice && ""!=locationCode){
                 for(var i=v+1;i<length;i++){
		             if(spareNo == allSpareNoSelector[i].value){
		                  if(locationCode == getObjByName("location.code"+i).value){
		                     if(taxPrice!=allTaxPriceSelector[i].value){
			                     alert("第"+(v+1)+","+(i+1)+"行同种备件入库,单价不同,请选择不同的库位！");
			                     return false;
			                 }
		                  }
		             }
                 }
           
           }
        
         }
      }
   }
  
    //保存时信息安全验证
    function informationValidate(){
        var allSpareInBillDtlId = document.getElementsByName("spareInBillDtlIds");
	    var allNumberValue = document.getElementsByName("spareInNumber");
	     var allNameValue = document.getElementsByName("name");
	      var allModeValue = document.getElementsByName("spare.modelSpecs");
   		 for(var i=0;i<allSpareInBillDtlId.length;i++){
   		    //验证备件名称
   		    var bjname =allNameValue[i].value;
   		     if(''== bjname){								
		    	alert("请输入第"+(i+1)+"行的备件名称（中文）！");
		    	allNameValue[i].focus();
		    	return false;
		    }
		    //验证备件型号
		     var bjModel =allModeValue[i].value;
   		     if(''== bjModel){								
		    	alert("请输入第"+(i+1)+"行的备件型号！");
		    	allModeValue[i].focus();
		    	return false;
		    }
		    //验证入库数量
		     var number = allNumberValue[i].value;
		    if(''== number){								//验证入库数量是否为空
		    	alert("请输入第"+(i+1)+"行的入库数量！");
		    	allNumberValue[i].focus();
		    	return false;
		    }else if (!isDoubleNumberCheck(number)){   			//验证输入的入库数量的格式
	         		alert("第"+(i+1)+"行的入库数量格式不对！");
	         		allNumberValue[i].focus();
	         		return false;
   			}
   			
   			 var k=i+2;
       var  yesOrNo = document.getElementsByName("yesOrNo"+k);
       var disableSpare = '';
        for(var j=0;j<yesOrNo.length;j++){
        if(yesOrNo[j].checked){
        disableSpare =yesOrNo[j].value;
        }
        
        }
        if(''==disableSpare){
        alert("第"+(i+1)+"行备件是否可用未确认！");
        return false;
        }
	    }
	   return true;
    }
    
        // 转换金额的 “,”号(3,333.00)->(3333.00)
       function replaceComma(data){
          var result = "";
          var ary = data.split(",");
          for(var i =0;i<ary.length;i++){
             result = result+ary[i];
          }
          return result;
       }
       
       
       //把浮点数改为 金额显示
       function toMoney(s, n){   
			   n = n > 0 && n <= 20 ? n : 2;   
			   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
			   var l = s.split(".")[0].split("").reverse(),   
			   r = s.split(".")[1];   
			   t = "";   
			   for(i = 0; i < l.length; i ++ ){   
			      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
			   }   
			   return t.split("").reverse().join("") + "." + r;   
	 } 
			
			
			
			
			
			
    //单价change 事件,改变总金额
    function changeTotalPrice(pId){
       var selector = document.getElementsByName("spareInBillDtlIds");
       var priceSelector = document.getElementsByName("spareIn.taxPrice");
       var allNumberSelector = document.getElementsByName("spareInNumber");
       var totalPriceSelector = document.getElementsByName("totalPrice");
       
       var length = selector.length;
       var number="";
       var taxPrice = "";
       if(length){
           for(var i=0;i<length;i++){
              var id = selector[i].value;
              if(pId==id){
                 if(null != priceSelector[i]){
                    taxPrice = priceSelector[i].value;
                 }
                 if(null != allNumberSelector[i]){
                    number = allNumberSelector[i].value;
                    
                 }
                 if(isDoubleNumberCheck(number) && isDoubleNumberCheck(taxPrice)){
                   if("" != taxPrice && "" != number ){
                     if(null != totalPriceSelector[i]){
                        taxPrice=replaceComma(taxPrice);
                        totalPriceSelector[i].value=toMoney((number*taxPrice),2);
                     }
                    }
                 }
              }
           }
       }
       
    }
    
    
	//改变金额
    function changValue(){
      //alert("ss"+informationValidate());
      if(informationValidate()){	 
	  	  /* 判断是否输入的出存数量为整数 */
		  if(false ==checkInputNumber()){
	           return false;
	      }
      }
	}
	
	function checkInputNumber(){
	  var number;
	  var taxPrice;
	  var sumFee;
	  var selector = document.getElementsByName("spareInBillDtlIds");
	  var taxSelector=document.getElementsByName("spareIn.taxPrice");
	  var totalPriceSelector = document.getElementsByName("totalPrice");
	  var valueselector = document.getElementsByName("spareInNumber");
	  if (selector.length) {
	     var totalPrice=0;
	     for (var i = 1; i < selector.length+1; i++) {
	       if(valueselector[i-1].value!=""){
        	   if (!isDoubleNumberCheck(valueselector[i-1].value)){   			//验证输入的入库数量的格式
	         		alert("${action.getText('row')}"+(i)+"${action.getText('spareInBill.format.error')}");
	         		return false;
       			}
       			<#--
       			else if(!isNumberBetweenBoolen(valueselector[i-1].value, 1000000001, -1000000000)){		//验证输入的入库数量的范围
       				alert("${action.getText('row')}"+(i)+"${action.getText('spareInBill.number.maxLength')}");
       				return false;
       			}
       			-->
       			else{
	        	  number=valueselector[i-1].value;
	     		  taxPrice=taxSelector[i-1].value;
	     		  taxPrice=replaceComma(taxPrice);
	    		  sumFee=toMoney((number*taxPrice),2);
	    		  totalPriceSelector[i-1].value=sumFee;
	    		  totalPrice += parseFloat(sumFee);
	    		 
        	   }
	      	}
	        }
	         parent.getObjByNameRe("spareInBill.totalPrice").value=totalPrice;
	    } 
	}
	
   //删除按钮
   function deleteSpareValue(boxName,message){
      
      var selector = document.getElementsByName("spareInBillDtlIds");
      if (selector.length) {
	  	for(var i=0;i<selector.length;i++){
	  	  alert(selector[i].checked);
	  	   if (selector[i].checked) {
	  	       var val = selector[i].value;
	  	     if(confirmDeletes(boxName, message)==true){
	              if(getObjByName(val).value == 'NEW'){
	  	              callBackSpareInNumberAndCommnet();
	  	              return true;
	  	          }else{
	  	            alert("该明细已入库，不能删除！");
	  	            return false;
	  	          }
					 
		     }
		     }else{
		         alert("请选择要删除的记录！");
		    	return false;
	       }
	    }
	     
	  }
      
      
	
	  
	 
   }
   
   function callBackSpareInNumberAndCommnet(){
   	  var selector = document.getElementsByName("spareInBillDtlIds");
	  var allNumberValue = document.getElementsByName("spareInNumber");
	  var allTotalPriceValue = document.getElementsByName("totalPrice");
	  var allCommentValue = document.getElementsByName("comment");
	  var valueAry="";
	  if (selector.length) {
	  	for(var i=0;i<selector.length;i++){
	  	  if (!selector[i].checked) {
	        valueAry+=selector[i].value+"##";
	        valueAry+=allNumberValue[i].value+"##";
	        valueAry+=allTotalPriceValue[i].value+"##";
	        valueAry+=allCommentValue[i].value+"##";
	  	  }
	  	}
	  }
	  valueAry=valueAry.substring(0,valueAry.lastIndexOf("##"));
	  document.forms[0].elements["valueAry"].value=valueAry;
   }
   function open_selectDialog() {
	 		var url = '${req.contextPath}/prophase/business/listSelectBelowPart.html';
	 		popupModalDialog(url,800,600);
  } 
</script>