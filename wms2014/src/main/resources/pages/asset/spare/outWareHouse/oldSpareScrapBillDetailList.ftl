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
<#--$Id: spareOutBillDetailList.ftl 11328 2008-09-25 09:39:30Z smzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<script type='text/javascript' src='${req.contextPath}/dwr/interface/findSpareLocationStockJs.js'></script>
 <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
<@framePage title="${action.getText('spareOutBillDetail.title')}">	
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
	<@ww.form namespace="'/spare'" name="'listForm'" action="'seacherOldSpareScrapOutBillDetail'" method="'post'">
		<@ww.token name="saveSpareOutBillDetailToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		 <@ww.hidden name="'addSpareOutBillIds'" value=""/>
	     <@ww.hidden name="'addSpareOutBill'" value=""/>
	     <@ww.hidden name="'saveAllDetail'" value=""/>
	     <@ww.hidden name="'addSpareBorrowDetailIds'" value=""/>
		 <@ww.hidden name="'spareBorrowSelector'" value=""/>
		 <@ww.hidden name="'allSpareLocationIds'" value=""/>
		 
		 <@ww.hidden name="'allborrowerPeople'" value=""/>
		 <@ww.hidden name="'allNewOrOld'" value=""/>
		 <@ww.hidden name="'allOutDate'" value=""/>
		 <@ww.hidden name="'allComment'" value=""/>
		 <@ww.hidden name="'allOutPeopleIds'" value=""/>
		 <@ww.hidden name="'allNumber'" value="" />
		 <@ww.hidden name="'allDetailIds'" value="" />
		 <@ww.hidden name="'allSheBei'" value="" />
		 <@ww.hidden name="'allBanZu'" value="" />
		  
		 <input type="hidden" name="valueAry" value=""/>
	     <@ww.hidden name="'toolingDevFlag'" value="'${req.getParameter('toolingDevFlag')?if_exists}'"/>
		<#if spareOutBill.id?exists>
		<@ww.hidden name="'spareOutBill.id'" value="#{spareOutBill.id}"/>
		<@ww.hidden name="'warehouse'" value="'${req.getParameter('warehouse')?if_exists}'"/>
		<@ww.hidden name="'inwarehouse'" value="'${req.getParameter('inwarehouse')?if_exists}'"/>
		</#if>
		<#assign itemNo=1/>
		
		<#assign requestDateIdentityName = 'outDate' + '${itemNo}'/>
		<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
		
		<#assign peopleIdentityName = 'outPeopleName'+'${itemNo}'/>
		<#assign peopleIdentityId= 'outPeopleId'+'${itemNo}'/>
		<#assign imgIdentityName = 'img'+'${itemNo}'/>
		<#assign newOrOldIdentity = 'newOrOld'+'${itemNo}'>
		<#assign newIdentity = 'new'+'${itemNo}'>
		<#assign oldIdentity = 'old'+'${itemNo}'>
		<#assign newText = 'newT'+'${itemNo}'>
		<#assign oldText = 'oldT'+'${itemNo}'>
		
		<@list title=""   includeParameters="spareOutBill.id" fieldMap="like:">
			<@vlh.checkbox property="id" name="spareOutBillDetailIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
          <#--  <input type="hidden" name="spareLocationId" value="#{object.spareLocationId.id}"/>-->
            <@vcolumn title="项目号">
                ${itemNo}  
                <@alignCenter/>
            </@vcolumn>
             <#assign itemNo = itemNo + 1/>
             <@vcolumn title="备件编码" property="code" >
              	<@alignLeft />
             </@vcolumn>
             <@vcolumn title="备件名称" property="name" >
              	<@alignLeft />
             </@vcolumn>
             <@vcolumn title="型号" property="spare.modelSpecs" >
              	<@alignLeft />
             </@vcolumn>
            
            <#if spareOutBill.warehouse?exists && spareOutBill.warehouse.storageGrade.id==208?if_exists>
             <#--
	            <@vcolumn title="${action.getText('订货号')}" property="spare.orderNo">
	            	<@alignLeft />
	            </@vcolumn>
	            <@vcolumn title="${action.getText('生产厂家')}" property="spare.factory.name"/>
	          -->
	            <@vcolumn title="${action.getText('spareOutBillDetail.category')}" property="spare.category.name" >
	              	<@alignLeft />
	            </@vcolumn>
	            <#--
               <@vcolumn title="${action.getText('明细种类')}" property="spare.spareDetailType.name" >
              	<@alignLeft />
               </@vcolumn> 
               -->
             </#if>
             <@vcolumn title="单位" property="spare.unit.value" >
              	<@alignLeft />
             </@vcolumn>
             
             
            <#if spareOutBill.warehouse?exists && spareOutBill.warehouse.storageGrade.id==208?if_exists>
              <@vcolumn title="${action.getText('spareOutBillDetail.warehouse')}" property="location.warehouse.name" >
              	<@alignLeft />
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutBillDetail.regional')}" property="location.regional.name" >
              	<@alignLeft />
             </@vcolumn>
	     	  <@vcolumn title="${action.getText('spareOutBillDetail.location')}" property="location.code" >
	      	      <@alignLeft />
	          </@vcolumn> 
             </#if>
                         
             <#assign number = ''/>
             <#if '${object.number}'=='0'>
             	<#assign number = ''/>
             <#else>
             	<#assign number = '${object.number}'/>
             </#if>
            
			<#if object.status?exists>
				<#if '${object.status}' == 'OUTWAREHOUSEED'>
                	<@vcolumn title="报废数量" format="${action.getText('currencyFormat')}">
            			<input type="text" name="number" class="underline"  value="${number}" onchange="changValue()" maxlength="20" size="7" style="text-align:right" disabled/>
            			<@alignRight/>
             		</@vcolumn>
				</#if>
				<#if '${object.status}' == 'NEW'>
					<@vcolumn title="报废数量" format="${action.getText('currencyFormat')}">
						<input type="text" name="number" class="underline"  value="${number}" onchange="changValue()" maxlength="20" size="7" style="text-align:right"/>
            			<@alignRight/>
             		</@vcolumn>
           		 </#if>   
            </#if>
            <#--二级库库显示设备班组信息 -->
         <#if spareOutBill.warehouse?exists && spareOutBill.warehouse.storageGrade.id==209?if_exists>
             <@vcolumn title="报废人" property="" >
                	 <input type="text" name="borrowerPeople" class="underline"  value="${object.borrowerPeople?if_exists}"  maxlength="20" size="10" />
               
             </@vcolumn>   
            
	         	<#-- 使用设备  报废单不存在
	            <@vcolumn title="使用设备" >
	           	 <input type="text" name="shebei" class="underline"  value="${object.shebei?if_exists}" onchange="" maxlength="20" size="7" style="text-align:right"/>
					<@alignLeft />
	            </@vcolumn>   
	            -->  
	            <#-- 所属班组    报废单不存在
	            <@vcolumn title="所属班组" >
	            <input type="text" name="banzu" class="underline"  value="${object.banzu?if_exists}" onchange="" maxlength="20" size="7" style="text-align:right"/>
					<@alignLeft />
	            </@vcolumn> 
	            -->
	            
	        <@vcolumn title="备注" property="" >
                 <input type="text" name="comment" class="underline"  value="${object.comment?if_exists}"  maxlength="100"   />
             </@vcolumn>       
            <@vcolumn title="出库人" >
              	<@peopleSelectorInList/>
              	<#assign peopleIdentityName = 'outPeopleName'+'${itemNo}'/>
		        <#assign peopleIdentityId= 'outPeopleId'+'${itemNo}'/>
             </@vcolumn>   
             
	              <@vcolumn title="出库日期">
	            	<#assign requirementDate = ''/>
				    		<#if object.outDate?exists>
					          <#assign requirementDate = "${(object.outDate?string('yyyy-MM-dd'))}"/>
					        </#if>
				    		<@eam2008_dataPicker inputName="${requestDateIdentityName}" 
				    			inputId="${requestDateIdentityName}" 
				    			inputValue="${requirementDate}" 
				    			imgId="${requestDateImgIdentity}" 
				    			formName="listForm"
				    			disable="false"/>
				    		<#assign requestDateIdentityName = 'outDate' + '${itemNo}'/>
							<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
			
	              </@vcolumn>
	           <#else>
	             <@vcolumn title="进库数量" property="inNumber" >
	              	<@alignRight />
	             </@vcolumn> 
               </#if> 
             <@vcolumn title="单价(元)" format="${action.getText('currencyFormat')}">
                   ${(object.unitPrice?string('#,###,##0.00'))?if_exists}<input type="hidden" name="unitPrice" value="#{object.unitPrice?if_exists}"  class="underline" disabled="true" readonly="true" />
              	<@alignRight/>
             </@vcolumn>
             
             <@vcolumn title="金额(元)" format="${action.getText('currencyFormat')}">
                       <input type="text" name="totalPrice" 
			    		      class="noBorderLine"  value="${(object.totalPrice?string('#,###,##0.00'))?if_exists}" disabled="true" maxlength="250" size="7"/>
              	<@alignRight />
             </@vcolumn>
             <input type="hidden" name="lockedStocks" value="${object.spareLocation.lockedStocks}" />
             <@vcolumn title="已占库存" property="spareLocation.lockedStocks" >	
          		<@alignRight />
             </@vcolumn>
             <input type="hidden" name="stocksBeforeInOrOut" value="${object.spareLocation.spare.disableStocks}" />
             <@vcolumn title="出库前库存 " property="stocksBeforeInOrOut" >	
          		<@alignRight />
             </@vcolumn>
             
              <@vcolumn title="不可用库存" property="spareLocation.spare.disableStocks" >	
          		<@alignRight />
             </@vcolumn>
             
            <#if spareOutBill.warehouse?exists && spareOutBill.warehouse.storageGrade.id==208?if_exists>
	            <@vcolumn title="${action.getText('spareOutBillDetail.stocksAfterInOrOut')}" property="stocksAfterInOrOut" >
	             <@alignRight />
	             </@vcolumn>
           </#if>
              <@ww.hidden name="'hiddenStocks'" value="${object.stocksAfterInOrOut?if_exists}"/>
                  
              <#assign spareOutDtlStatus = ''/>
            <#if object.status?exists>
              <#if '${object.status}' == 'NEW'>
               <#assign spareOutDtlStatus = "新建"/>
               </#if>
              <#if '${object.status}' == 'OUTWAREHOUSEED'>
              <#assign spareOutDtlStatus = "${action.getText('OUTWAREHOUSEED')}"/>
            </#if>
            </#if>
               <@vcolumn title="状态" attributes="width='50'">
                <input type="hidden"  name="outDelStatus" value="${object.status}"/>
        		   ${spareOutDtlStatus}
                 <@alignLeft/>
            </@vcolumn>
             <div style="display:none">
            	  <input   name= "status" type= "checkbox"  value= "${object.status}" style="visibility:hidden"/> 
            </div>
		</@list>
        <@buttonBar>
        	<#if !first>
        	<#if !(action.isReadOnly())>
        	<@vbutton name="'new'"  class="button" value="从旧件库明细台帐选择" onclick="open_spareDtlDialog()"/>	       	
            <#--
            <@vbutton name="'selectSpareBorrowDtl'"  class="button" value="${action.getText('spareBorrowDtl')}" onclick="open_spareBorrowDtlDialog()"/>
            -->
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('spareOutBillDetail')}?" />
           	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitDetailIds()'">
           		<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
           	</@vsubmit>
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return deleteSpareValue(\"spareOutBillDetailIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
            </#if>
        </@buttonBar>
	</@ww.form>
<script language="JavaScript" type="text/JavaScript">
	window.onload=function(){
	
	 	<#if spareOutBill.status!='NEWSTATUS'>			
      		// document.forms[0].elements["'new'"].disabled="true";      	 	
      	 	// document.forms[0].elements["save"].disabled="true";
      	 	// document.forms[0].elements["delete"].disabled="true";
        </#if>
        var selector = document.getElementsByName("spareOutBillDetailIds");//获取所有id
      		var status = document.getElementsByName("status");//获取所有状态
      		
			var st="";	
		    var spareIds = "";
		    if (selector.length) {
		        for (i = 0; i < selector.length; i++) {		        
		            if (status[i].value=='NEW') {
		            	 document.forms[0].elements["save"].disabled=null;
      	 				 document.forms[0].elements["delete"].disabled=null; 
		            }
		        }
		    }		
    <#--    
		//如果仓库级别是一级库则不允许修改班组和设备
		if(208==parent.getObjByNameRe("storageGrade.id").value){
			var  detailIdSelector= document.getElementsByName("spareOutBillDetailIds"); 
			if (detailIdSelector.length) {			
		  		for (var i = 0; i < detailIdSelector.length; i++){
			  		if(detailIdSelector.length>1){
			  			document.forms[0].elements["shebei"][i].disabled="true";
						document.forms[0].elements["banzhu"][i].disabled="true";	
			  		}
			  		if(detailIdSelector.length==1){
			  			document.forms[0].elements["shebei"].disabled="true";
						document.forms[0].elements["banzhu"].disabled="true";						
			  		}		  		
		  		}
	  		}
		}-->
	
		//修改父窗口的,获得父窗口的控件，在想其中加入事件。  
		<#if !valueListNoRecords>
			var tag = parent.getObjByNameRe("warehouse.id");
			var tag1 = parent.getObjByNameRe("storageGrade.id");
			
			var psave = parent.getObjByNameRe("save");
			psave.disabled="true";
			
			tag.onmousemove=function(){this.setCapture();}
			tag.onmouseout=function(){this.releaseCapture();}
			tag.onfocus=function(){this.blur();}
			
			tag1.onmousemove=function(){this.setCapture();}
			tag1.onmouseout=function(){this.releaseCapture();}
			tag1.onfocus=function(){this.blur();}
		<#else>	
		  var psave = parent.getObjByNameRe("save");
			psave.disabled=null;
	    </#if>
	
        <#if req.getParameter('errorFlag')?has_content>
   	       alert("${action.getText('delete.spareOutDtl.failure')}");
        </#if>
		//改变父页面的总金额
	 	<#if spareOutBill.id?exists>
            parent.getObjByNameRe("spareOutBill.totalPrice").value = '${(spareOutBill.totalPrice?string('#,###,##0.00'))?if_exists}';
      	</#if>
		var selector = document.getElementsByName("spareOutBillDetailIds");
	
		//document.forms[0].elements["save"].disabled="true";
		//如果没有出库明细，“发送提醒”按钮状态改为不可用
		<#--parent.document.forms[0].elements["submitRecord"].disabled="true";-->
		<#--
		<#if !(action.isReadOnly())>
		<#if spareOutBill.submit==true>
		      parent.document.forms[0].elements["submitRecord"].disabled=false;
		     <#if spareOutBill.detail.size()==0>
		  	  parent.document.forms[0].elements["submitRecord"].disabled=true;
	    </#if>
      </#if>
      </#if>
      -->
	  <#if spareOutBill.status?exists>
   		    <#assign spareOutStatus = ''/>
   			<#if '${spareOutBill.status}' == 'NEWSTATUS'>
   			<#assign spareOutStatus = "${action.getText('NEWSTATUS')}"/>
   			<#elseif '${spareOutBill.status}' == 'OUTWAREHOUSEING'>
   			<#assign spareOutStatus = "${action.getText('OUTWAREHOUSEING')}"/>
   			<#elseif '${spareOutBill.status}' == 'OUTWAREHOUSEED'>
   			<#assign spareOutStatus = "${action.getText('OUTWAREHOUSEED')}"/>
   			</#if>
   			parent.document.forms["spareoutBill"].elements["spareOutBill.status"].value = '${spareOutStatus}';
      </#if>	
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
	
	
	  <#if !(action.isReadOnly()) && spareOutBill.status!='NEWSTATUS'>	
             var selector = document.getElementsByName("spareOutBillDetailIds");
             var statuSelector = document.getElementsByName("outDelStatus");
             var num=0;
             if(selector.length){
              for(var i=0;i<selector.length;i++){
                 // var id = selector[i].value;
                 // var statu = getObjByName(id).value;
                 var statu = statuSelector[i].value;
                  if('NEW'==statu){
                     num++;
                  }else{
                       var inputAry = statuSelector[i].parentNode.parentNode.getElementsByTagName("input");
                       for(var a=0;a<inputAry.length;a++){
                         inputAry[a].disabled="true";
                       }
                     
                  }
             }
          }	
   
    </#if>
	
	
 
	
	function setSpareValue(spareId,spareNumberValue,spareTotalPriceValue){
   	  var selector = document.getElementsByName("spareOutBillDetailIds");
	  var allNumberValue = document.getElementsByName("number");
	  var allTotalPriceValue = document.getElementsByName("totalPrice");
       if (selector.length) {
	        for (var i = 0; i < selector.length; i++) {
	             if(selector[i].value==spareId){
	                 allNumberValue[i].value=spareNumberValue;
	                 allTotalPriceValue[i].value=spareTotalPriceValue;
	             }
	        }
	   }
	}
	
 	a = new Date();
	var time=a.format("yyyy-MM-dd");
	 
	function open_spareBorrowDtlDialog(){
	   var url = '${req.contextPath}/runmaintenance/spareBorrow/spareBorrowSelector.html';
	   popupModalDialog(url, gw, gh, refresh_spareBorrowDtl_information);
	}
	function refresh_spareBorrowDtl_information(result){
		callBackSpareOutBillDetailValue();
	   if(null!=result){
	    var spareBorrowDetailIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addSpareBorrowDetailIds"].value = spareBorrowDetailIds;
        document.forms[0].elements["spareBorrowSelector"].value = "spareBorrow";
        document.forms[0].submit();
	   }
	}
	function open_yjhxDialog(){
	var url = '';
		var depId = "";
		//inoutFlag的值为'F'的时候是出库标识
		<#if spareOutBill.department?exists>
		var depId = '${spareOutBill.department.id}';
	    </#if>
		if(1==document.forms[0].elements["warehouse"].value && 2==document.forms[0].elements["inwarehouse"].value){	
			url = '${req.contextPath}/spareLocation/spareLocationSearcherCommon.html?inOutFlag=out&toolingDevFlag=TOOLINGDEVICE&spareBillId=#{spareOutBill.id}&roleWarehouseId=#{spareOutBill.warehouse.id}&deptid='+depId;						
		}else{		
			url = '${req.contextPath}/spareLocation/spareLocationSearcherCommon.html?inOutFlag=out&toolingDevFlag=TOOLINGDEVICE&spareBillId=#{spareOutBill.id}&roleWarehouseId=#{spareOutBill.warehouse.id}&warehouse.id=#{spareOutBill.warehouse.id}';	
		}
		eam2008_spare_OpenDialog(url,refresh_multi_spare_information,null);
	}
	function open_spareDtlDialog() {
		var url = '';
		var depId = "";
		//inoutFlag的值为'F'的时候是出库标识
		<#if spareOutBill.department?exists>
		var depId = '${spareOutBill.department.id}';
	    </#if>
		if(1==document.forms[0].elements["warehouse"].value && 2==document.forms[0].elements["inwarehouse"].value){	
			url = '${req.contextPath}/spareLocation/oldSpareLocationSearcherCommon.html?inOutFlag=out&toolingDevFlag=TOOLINGDEVICE&spareBillId=#{spareOutBill.id}&roleWarehouseId=#{spareOutBill.warehouse.id}&deptid='+depId;						
		}else{		
			url = '${req.contextPath}/spareLocation/oldSpareLocationSearcherCommon.html?inOutFlag=out&toolingDevFlag=TOOLINGDEVICE&spareBillId=#{spareOutBill.id}&roleWarehouseId=#{spareOutBill.warehouse.id}&warehouse.id=#{spareOutBill.warehouse.id}';	
		}
		eam2008_spare_OpenDialog(url,refresh_multi_spare_information,null);
	}

	function open_yjhxlDialog() {
		var url = '';
		var depId = "";
		//inoutFlag的值为'F'的时候是出库标识
		<#if spareOutBill.department?exists>
		var depId = '${spareOutBill.department.id}';
	    </#if>
		if(1==document.forms[0].elements["warehouse"].value && 2==document.forms[0].elements["inwarehouse"].value){	
			url = '${req.contextPath}/spareLocation/spareLocationSearcherCommon.html?inOutFlag=out&toolingDevFlag=TOOLINGDEVICE&spareBillId=#{spareOutBill.id}&roleWarehouseId=#{spareOutBill.warehouse.id}&deptid='+depId;						
		}else{		
			url = '${req.contextPath}/spareLocation/spareLocationSearcherCommon.html?inOutFlag=out&toolingDevFlag=TOOLINGDEVICE&spareBillId=#{spareOutBill.id}&roleWarehouseId=#{spareOutBill.warehouse.id}&warehouse.id=#{spareOutBill.warehouse.id}';	
		}
		eam2008_spare_OpenDialog(url,refresh_multi_spare_information,null);
	}
        	
    function refresh_multi_spare_information(result){
    	callBackSpareOutBillDetailValue();
	    if (null != result) {
	    var addSpareOutBillIds = result.substring(0, result.lastIndexOf(","));
	    document.forms[0].elements["addSpareOutBillIds"].value =addSpareOutBillIds;
	    document.forms[0].elements["addSpareOutBill"].value = "addSpareOutBill";
	    document.forms[0].submit();
	    }
	    
 	}
 	
 	
 		// 
	function retrieveSpareLocationIdText(){
	    var detailIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
	    var spareLocationIdSelector = document.getElementsByName("spareLocationId"); 
	     var ary = new Array();
	    if(spareLocationIdSelector.length){
	         for (var i=0; i<detailIdSelector.length; i++) {
	            if('' != spareLocationIdSelector[i].value){
	                ary.push(detailIdSelector[i].value);
	                ary.push(spareLocationIdSelector[i].value);
	            }
	            
           }
	    
	    }
        document.forms[0].elements["allSpareLocationIds"].value=ary;
	}
	
	
	//获取所有明细的id 的值
 	function getAllSpareOutBillDetailText(){
 	   var detailIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
 	   var length = detailIdSelector.length;
 	   var ary = new Array();
 	   
 	   if(length){
 	       var id = "";
 	       if(length==1){
 	          id = detailIdSelector[0].value;
 	          id = id+",";
 	          ary.push(id);
 	       }else{
 	          for(var i =0;i<length;i++){
 	               id = detailIdSelector[i].value;
 	               ary.push(id);
 	          }
 	       }
 	           
 	      
 	  }
 	   document.forms[0].elements["allDetailIds"].value=ary;
 	   
 	}
 	
 	//转换3位以上的数字，去除逗号
 	 function replaceComma(data){
        var result = "";
        var ary = data.split(",");
        for(var i =0;i<ary.length;i++){
           result = result+ary[i];
        }
        return result;
     }
 	
 	//获取出库数量的数据
 	
 	function getAllNumberText(){
 	   var detailIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
 	   var numberSelector = document.getElementsByName("number");
 	   var length = detailIdSelector.length;
 	   var ary = new Array();
 	   if(length){
 	      for(var i =0;i<length;i++){
 	        if(""!= numberSelector[i].value){
 	            ary.push(detailIdSelector[i].value);
	            ary.push(replaceComma(numberSelector[i].value));
 	        } 
 	      }
 	   }
 	   document.forms[0].elements["allNumber"].value=ary;
 	}
 	
 	//获取领料人的数据
 	
 	function getBorrowerPeopleText(){
 	   var detailId = document.getElementsByName("spareOutBillDetailIds"); 
 	   var borrower = document.getElementsByName("borrowerPeople");
 	   var length = detailId.length;
 	   var ary = new Array();
 	   if(length){
 	      for(var i =0;i<length;i++){
 	        if(""!= borrower[i].value){
 	            ary.push(detailId[i].value);
	            ary.push(borrower[i].value);
 	        }else{
 	            alert("第"+(i+1)+"行请填领料人！");
		  	    return false;
 	        }
 	      }
 	   }
 	   document.forms[0].elements["allborrowerPeople"].value=ary;
 	}
 	
 	 	 
 	//获取以旧换新数据
 	function getAllNewOrOldText(){
 	   var detailIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
 	   //var newOrOldSelector = document.getElementsByName("newOrOld");
 	   var newOrOldTag = 'newOrOld';
 	   var length = detailIdSelector.length;
 	   var ary = new Array();
 	   if(length){
 	      for(var i =0;i<length;i++){
 	         ary.push(detailIdSelector[i].value);
 	          
 	          var newOrOldSelector = document.getElementsByName(newOrOldTag+(i+1));
 	          for(var v =0;v<newOrOldSelector.length;v++){
 	             if(newOrOldSelector[v].checked){
 	                ary.push(newOrOldSelector[v].value);
 	             }
 	          }
 	      }
 	   }
 	   document.forms[0].elements["allNewOrOld"].value=ary;
 	}
 	
 	 	 	 
       
	 //获取申购单明细出库日期的所有值
    function getAllOutDateText(){
		 var detailIdSelector = document.getElementsByName("spareOutBillDetailIds");
		 var ary = new Array();
		 for (var i=0; i<detailIdSelector.length; i++) {
		  	var outDateTagName='outDate';
		  	outDateTagName = outDateTagName + (i+1);
		  	if ('' != document.forms["listForm"].elements[outDateTagName].value) {
		   
		  	   if(!isDate(outDateTagName)){
		  	       alert("第"+(i+1)+"行日期格式不正确，正确格式2010-12-01！");
	  	           return false;
		  	   }
	           	ary.push(detailIdSelector[i].value);
	           	ary.push(getObjByName(outDateTagName).value);
	           	
	     	}else{
	     	     alert("第"+(i+1)+"行，请选择出库日期！");
	     	     return false;
	     	}
		 }
		document.forms[0].elements["allOutDate"].value=ary; 
    }
 	  
 	  
 	   	 
 	//获取所有备注的值
 	function getCommentText(){
 	   var detailIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
 	   var commentSelector = document.getElementsByName("comment");
 	   var length = detailIdSelector.length;
 	   var ary = new Array();
 	   if(length){
 	      for(var i =0;i<length;i++){
 	        if(""!= commentSelector[i].value){
 	            ary.push(detailIdSelector[i].value);
	            ary.push(commentSelector[i].value);
 	        }
 	      }
 	   }
 	   document.forms[0].elements["allComment"].value=ary;
 	}
 	
 	//获取所出库人的值
 	function getOutPeopleText(){
 	   var detailIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
 	   var outPeopleIdTag = 'outPeopleId';
 	   var length = detailIdSelector.length;
 	   var ary = new Array();
 	   if(length){
 	      for(var i =0;i<length;i++){
 	        if(""!= getObjByName(outPeopleIdTag+(i+1)).value){
 	            ary.push(detailIdSelector[i].value);
	            ary.push(getObjByName(outPeopleIdTag+(i+1)).value);
 	        }else{
 	            alert("第"+(i+1)+"行，请选择入库人！");
 	            return false;
 	        }
 	      }
 	   }
 	   document.forms[0].elements["allOutPeopleIds"].value=ary;
 	}
 	
 	//取得所有设备的值
    function getSheBeiText(){
      var detailIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
      var shebeiSelector =  document.getElementsByName("shebei");
      var length = detailIdSelector.length;
 	  var ary = new Array();
 	  if(length){
 	     for(var i=0;i<length;i++){
 	        if("" !=shebeiSelector[i].value){
 	           ary.push(detailIdSelector[i].value);
 	           ary.push(shebeiSelector[i].value);
 	        }
 	     }
 	  }
      document.forms[0].elements["allSheBei"].value=ary;
    }
    
    //取得所有班组的值
    function getBanzuText(){
      var detailIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
      var banzuSelector =  document.getElementsByName("banzu");
      var length = detailIdSelector.length;
 	  var ary = new Array();
 	  if(length){
 	     for(var i=0;i<length;i++){
 	        if("" !=banzuSelector[i].value){
 	           ary.push(detailIdSelector[i].value);
 	           ary.push(banzuSelector[i].value);
 	        }
 	     }
 	  }
      document.forms[0].elements["allBanZu"].value=ary;
    }
 	
 	
 	//保存信息
    var black="";
 	function submitDetailIds(){
 	  
	  	var detailIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
	    var  number = document.getElementsByName("number");
	  	var statuSelector = document.getElementsByName("outDelStatus");
	  	
	    /* 判断是否输入了出存数量 */
	    if(!checkInputState()){
	       return false;
	    }
	   	/* 判断出库数量是否比总库存量大 */
	    if(!checkStockValue()){
	       return false;
	    }
	    <#if spareOutBill.warehouse?exists && spareOutBill.warehouse.storageGrade.id==209>
	        //验证领料人
		    if(false == getBorrowerPeopleText()){
		        return false;
		    }
		    //验证出库人
		    if(false == getOutPeopleText()){
		        return false;
		    }
		    //验证出库日期
		    if(false == getAllOutDateText()){
		      return false;
		    }
		    if(false == getAllNewOrOldText()){
		        return false;
		    }
		     //数量
		    getAllNumberText();
		    //明细id
		    getAllSpareOutBillDetailText();
		    //备注
		    getCommentText();
		    getBanzuText();
	        getSheBeiText();
	        
	    <#else>
	      //明细id
		    getAllSpareOutBillDetailText();
	       //数量
		    getAllNumberText();
	    </#if>
		   
	  	/*
	  	 *	allNumbers表示所有备件的出库数量,用双#号分割，‘##’，其目的是备注中可能出现逗号，造成数据错误！
	  	*/
	  	var allNumbers = "";
	  	var allDetailId="";
	  	var status="";
	  	var countNEW=0;//计算有多少 新建明细
	  	var ary = new Array();
	  	var detailIdAndNum = "";
	  	var  allDetailIdAry =  new Array();
	  	if (detailIdSelector.length) {
	  		for (var i = 0; i < detailIdSelector.length; i++){
	  		    if("NEW"==statuSelector[i].value){
	  		       countNEW = (countNEW+1);
	  		       allDetailIdAry.push(detailIdSelector[i].value);
	  			<#-- 
	  			  allNumbers += detailIdSelector[i].value+"##";
	              allNumbers += formatDigital(number[i].value)+"##";
	  		     
	  		       ary.push(detailIdSelector[i].value);
	  		       ary.push(number[i].value);-->
	  		    }
	  		  
	  			
	  		}
  		}
  	   
  		if(0==countNEW){
  		    alert("出库单已全部出库！");
  		   return false;
  		}else{
  		      DWREngine.setAsync(false); 
  		            findSpareLocationStockJs.getSpareLocationStock(ary,backStock);
  		           if(black == "true"){
  		             return false;
  		          }
  		      DWREngine.setAsync(true);
  		    <#if spareOutBill.warehouse?exists && spareOutBill.warehouse.storageGrade.id==208>   
	  		    document.forms[0].elements["addSpareOutBillIds"].value=allDetailIdAry;  		
		    	<#-- allNumbers = allNumbers.substring(0,allNumbers.lastIndexOf("##"));
		    	getObjByNameRe("saveAllDetail").value=allNumbers;-->
	  	    </#if>
	  	    return true;
  		}
  		
  		
    }
   
   function backStock(data){
    	var detlIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
	    var  numSelector = document.getElementsByName("number");
	    var statuSelector = document.getElementsByName("outDelStatus");
	    var  loop = ${itemNo}-1;
	    var sumNum = 0;
	    var backNum = 0;
	    var rowValue="";
       if("" != data){
          var ary = data.split(",");
          if("iterance"==ary[0]){//[iterance]是判断 重复的标志 其后加的是 重复的明细的Id 和明细的总库存
	        for(var v=0;v<ary.length;v++){
	           backNum = ary[ary.length-1];//最后一位是该重复明细的当前总库存
	           for(var r=0;r<detlIdSelector.length;r++){
	              if(ary[v] == detlIdSelector[r].value ){
	                  sumNum = sumNum+parseInt(numSelector[r].value);//是同一明细的累加其 出库数量
	                  rowValue = rowValue+(r+1)+"，";
	                                                                 
	              }
	           }
	        }
	        if(parseInt(sumNum)>parseInt(backNum)){
                alert("第"+rowValue +"行重复选择，并且出库数量大于总库存数量！总库存数量为 "+backNum);
                black = "true";
             }
	      }
        
        for(var i=0;i<ary.length;i=i+2){
           var detailId = ary[i];
           var backNum = ary[i+1];
           for(var j=0;j<detlIdSelector.length;j++){
              if(detlIdSelector[j].value==detailId){
                 if(statuSelector[j].value=="NEW"){
                     if(parseInt(numSelector[j].value)>parseInt(backNum)){
                        if(parseInt(backNum)==0){
                            alert(" 第 "+(j+1)+" 行已没有库存，或者在其他地方已经出库！");
                        }else{
                           alert("  第 "+(j+1)+" 行，已在其他地方出库，此次最多可出 "+backNum+" 条库存");
                        }
                       
                        black = "true";
                     }
                 }
                 
              }
           }
        }
      }
            
   }
	        
   function checkStockValue(){
	   var selector = document.getElementsByName("spareOutBillDetailIds");
	   var numberselector = document.getElementsByName("number");
	   var beforeValueselector = document.getElementsByName("stocksBeforeInOrOut");
	   var lockedStocksSelector = document.getElementsByName("lockedStocks");
   	   if (selector.length) {
          for (var i = 1; i < selector.length+1; i++) {
            if(numberselector[i-1].value!=""){
               if(parseInt(formatDigital(numberselector[i-1].value))>(parseInt(formatDigital(beforeValueselector[i-1].value)-parseInt(formatDigital(lockedStocksSelector[i-1].value))))){
               	//alert("${action.getText('di')}"+(i)+"${action.getText('spareOutBill.lowStock')}");
            	alert("${action.getText('di')}"+(i)+"行，报废数量大于不可用库存，请重新输入");
            	return false;
            	}
             }else{
            	return true;
             }
           }
		    return true;
		}else{
		 	return false;
		}
 	}
     		
 		/* 判断是否输入了出存数量 */
 		function checkInputState(){
	        var selector = document.getElementsByName("spareOutBillDetailIds");
	        var valueselector = document.getElementsByName("number");   //出库数量
	        var beforeInOrOutNumber = document.getElementsByName("stocksBeforeInOrOut");  //出库前数量
	    
	        if (selector.length) {
		        for (var i = 1; i < selector.length+1; i++) {
		          if (valueselector[i-1].value!="" && beforeInOrOutNumber[i-1].value == 0) {
		            if (formatDigital(valueselector[i-1].value) > 0) {		            	
		               // alert("${action.getText('di')}"+(i)+"${action.getText('spareOutBill.lowStock')}");
				       alert("${action.getText('di')}"+(i)+"行，报废数量大于不可用库存，请重新输入");
				        return false;
		            }
		          }
		        
		            if(valueselector[i-1].value!=""){	
		            	if (!isDoubleNumberCheck(valueselector[i-1].value)){   			//验证输入的入库数量的格式
		            			
				         		alert("${action.getText('di')}"+(i)+"${action.getText('spareOutBill.formate.error')}");
				         		return false;
		       				}else if(!isNumberBetweenBoolen(valueselector[i-1].value, 1000000001, -1000000000)){		//验证输入的入库数量的范围
		       					
			       				alert("${action.getText('di')}"+(i)+"${action.getText('spareOutBill.number.maxLength')}");
			       				return false;
		       				}
		            }else if(valueselector[i-1].value==""){	
		            	   alert("${action.getText('di')}"+(i)+"${action.getText('spareOutBill.number.isnull')}");
		            	   return false;
		            	}
		         }
			 } 
		    return true;
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
		//改变金额
	    function changValue(){
	  		/* 判断是否输入的出存数量为整数 */
	        if(!checkInputNumber()){
	           return false;
	        }
		}
		/* 判断出存数量是否比总库存量大 */
	  	function checkInputNumber(){
	  		var number;
	  		var unitPrice;
	  		var sumFee;
	  		var selector = document.getElementsByName("spareOutBillDetailIds");
	  		var unitSelector=document.getElementsByName("unitPrice");
	  		var valueselector = document.getElementsByName("number");
	  		var totalPriceSelector = document.getElementsByName("totalPrice");
	       	beforeValueselector = document.getElementsByName("stocksBeforeInOrOut");
	       	if (selector.length) {
		        for (var i = 1; i < selector.length+1; i++) {
		           if(valueselector[i-1].value!=""){
		                var beforeValue = beforeValueselector[i-1].value;
		                beforeValue= replaceComma(beforeValue);
	            	  	if (!isDoubleNumberCheck(valueselector[i-1].value)){   			//验证输入的入库数量的格式
			         		alert("${action.getText('di')}"+(i)+"${action.getText('spareOutBill.formate.error')}");
			         		return false;
	       				}else if (!isNumberBetweenBoolen(valueselector[i-1].value, 1000000001, -1000000000)){		//验证输入的入库数量的范围
		       				alert("${action.getText('di')}"+(i)+"${action.getText('spareOutBill.number.maxLength')}");
		       				return false;
	       				}else if (parseInt(valueselector[i-1].value)>parseInt(beforeValue)){
               				//alert("${action.getText('di')}"+(i)+"${action.getText('spareOutBill.lowStock')}");
            				alert("${action.getText('di')}"+(i)+"行，报废数量大于不可用库存，请重新输入");
            				return false;
            			}else {
	       					number=valueselector[i-1].value;
			         		unitPrice=unitSelector[i-1].value;
			         	    sumFee=toMoney((number*unitPrice),2);
			         		totalPriceSelector[i-1].value=sumFee;
	       				}
	               }
		        }
		        return true;
			}			         
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
       
		
   //删除按钮
   function deleteSpareValue(boxName,message){
       	var detailIdSelector = document.getElementsByName("spareOutBillDetailIds"); 
	  	var statuSelector = document.getElementsByName("outDelStatus");
	    
	  	
	  callBackSpareOutBillDetailValue();
	  if(confirmDeletes(boxName, message)==true){
		return true;
	  }else{
	  	return false;
	  }
	  
   }
	function callBackSpareOutBillDetailValue(){
		var selector = document.getElementsByName("spareOutBillDetailIds");
        var allNumberValue = document.getElementsByName("number");
        var allTotalPriceValue = document.getElementsByName("totalPrice");
        var valueAry ="";
        if(selector.length){
        	for (var i=0;i<selector.length;i++){
        		if (!selector[i].checked) {
        			 valueAry+=selector[i].value+"##";
        			 valueAry+=allNumberValue[i].value+"##";
        			 valueAry+=allTotalPriceValue[i].value+"##";
        		}
        	}
        }
        valueAry=valueAry.substring(0,valueAry.lastIndexOf("##"));
        document.forms[0].elements["valueAry"].value=valueAry;
      
	}
	
<#--
function isNumber(targetId) {
    var num = targetId;
    s = new String(num);
    var regu = "^[0-9]+$";
    var re = new RegExp(regu);
    if (s.search(re) != -1) {
        if(parseInt(s)>=10000000||parseInt(s)<=0){
        return 0;
        }else{
          return 1;
        }
    } else {
        return -1;
    }
}
-->

</script>
</@framePage>