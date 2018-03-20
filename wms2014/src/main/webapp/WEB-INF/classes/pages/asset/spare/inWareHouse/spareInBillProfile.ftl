﻿<#--
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
<#--$Id: purchaseOrderProfile.ftl 11328 2008-09-25 09:39:30Z smzhang $ -->

<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareInBillProfile.title')}">
<@ww.form namespace="'/spare'"  name="'spareInBillProfile'" action="'saveSpareInBill'" method="'post'">
    <@ww.token name="saveSpareInBillToken"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <@ww.hidden name="'acceptanceList'" value="'${(spareInBill.acceptanceList?string)?if_exists}'"/>
      <@ww.hidden name="'notFather'" value=""/>
       <#if spareInBill.status?exists>
         <@ww.hidden name="'status'" value="'${spareInBill.status}'"/>
       </#if>
    <@inputTable>
        <#if spareInBill.id?exists>
            <@ww.hidden name="'spareInBill.id'" value="#{spareInBill.id}"/>
        </#if>
        <tr>
            <@ww.textfield label="'${action.getText('spareInBill.code')}'"  name="'spareInBill.code'" value="'${spareInBill.code?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
            <@ww.textfield label="'${action.getText('spareInBill.name')}'"  name="'spareInBill.name'" value="'${spareInBill.name?if_exists}'" cssClass="'underline'" required="true"/>
        </tr>
        
        <tr>
        <td colspan="4">
        <hr align="middle" size="1" width="95%" color="#999999" noshade /> 
        </td>
        </tr>
        <tr>
          
        <#-- 库存级别 -->
  		 <@ww.select label="'${action.getText('storageGrade')}'" 
	                   required="true" 
	                   name="'storageGrade.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allStorageGrade" 
                       emptyOption="true" 
                       disabled="false"
                       onchange="'wareHouseCascadeDWR(\"storageGrade.id\",\"warehouse.id\",${loginUser.id?if_exists},\"${action.getText('')}\");removeSupper();'">
                       <#if spareInBill.storageGrade?exists>
                          <@ww.param name="'value'"  value="'${spareInBill.storageGrade.id?if_exists}'" />
                       </#if>
         </@ww.select>
         
       	 	<#-- 入仓库 -->
   	 	<@ww.select 
					label="'${action.getText('warehouse')}'" 
					required="true" 
					name="'warehouse.id'" 
					value="'${req.getParameter('warehouse.id')?if_exists}'" 
					listKey="id" 
					listValue="name"
					list="allWarehouse" 
					emptyOption="true" 
					disabled="false" >
				
		</@ww.select>      
		 
        </tr>    
        <tr>
        	<#--如果是 一级库存 显示 供应商-->
           <@pp.remotePicker label="'${action.getText('supplier.no')}'" 
                    name="'supplier'"
                    id="supper"
                    selectorName="'supplierSelectorAjax'" 
                    cssClass="'underline'" 
                    value="spareInBill.supplier"
                    popup="'${req.contextPath}/popup/supplierAllSelector.html'" 
                    size="15" 
                    codeName="'supplierNo'" 
                    required="true"/>
             <#--如果是 二级库存影藏供应商 显示出仓库 --> 
                  
         <@ww.select 
					label="'${action.getText('来源仓库')}'" 
					required="true" 
					id="input"
					name="'outWarehouse.id'" 
					value="'${req.getParameter('outWarehouse.id')?if_exists}'" 
					listKey="id" 
					listValue="name"
					list="allOutWarehouse" 
					emptyOption="true" 
					disabled="false" 
					onchange="'getOutWarehouseId();'">
				
		</@ww.select>                
                
			<@pp.datePicker label="'${action.getText('inDate')}'" 
			        name="'spareInBill.inDate'"
					value="'${(spareInBill.inDate?string('yyyy-MM-dd'))?if_exists}'"
					cssClass="'underline'" 
					required="true" 
                    size="15"/>
           
         
       	 </tr>
       	 <tr>
		   <@pp.remotePicker label="'${action.getText('inPeople')}'" 
                    name="'spareIn.inPeople'"
                    selectorName="'userSelectorAjax'" 
                    cssClass="'underline'" 
                    value="spareInBill.inPeople"
                    popup="'${req.contextPath}/popup/userSelector.html'" 
                    size="15" 
                    onchange="'changePeoplew();'"
                    codeName="'loginName'" 
                    required="true"/>
                   
			<@pp.remotePicker label="'${action.getText('checkPeople')}'" 
			        name="'spareIn.checkPeople'"
                    selectorName="'userSelectorAjax'" 
                    cssClass="'underline'" 
                    value="spareInBill.checkPeople"
                    popup="'${req.contextPath}/popup/userSelector.html'" 
                    size="15" 
                    codeName="'loginName'"/>

       	 </tr>
			<@ww.textfield label="'${action.getText('spareInBill.totalPrice')}'"  name="'spareInBill.totalPrice'" value="'${spareInBill.totalPrice?if_exists}'" cssClass="'underline'" disabled="true"/>				    	 
            <#assign status=''/>
        	<#if spareInBill.status?exists>
		       <#if '${spareInBill.status}' == 'NEWSTATUS'>
		       <#assign status = "${action.getText('NEWSTATUS')}"/>
		       <#elseif '${spareInBill.status}' == 'INWAREHOUSEING'>
		       <#assign status = "${action.getText('INWAREHOUSEING')}"/>
		       <#elseif '${spareInBill.status}' == 'INWAREHOUSEED'>
		       <#assign status = "${action.getText('INWAREHOUSED')}"/>
        	</#if>
        	</#if>
	    		<@ww.textfield label="'${action.getText('status')}'" name="'spareInBillStatus'" value="'${status}'" cssClass="'underline'" readonly="true"/>	
       	 <tr>
        </tr>
         <tr>
        	    <@ww.textarea label="'${action.getText('comment')}'"  name="'spareInBill.comment'" value="'${spareInBill.comment?if_exists}'" rows="'3'" cols="'50'" />
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
	        <@vsubmit name="'save'" value="'${action.getText('save')}'"  onclick="'return checkInput()'"/>
   	    	<#if (spareInBill.id)?exists>
	   	    	<@vbutton name="saveContinue"  class="button" value="${action.getText('saveContinue')}" onclick="return encodeUrl() "/>
	   	    </#if>
   	    </#if>
   	    <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/spare/listSpareInBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
		<#if (spareInBill.id)?exists>
		<#--
	   	    <@blurbutton name="pdfPrint" class="button" value="${action.getText('spareInBillButtonManager')}" onclick="pdfButtonManager('excel')" onblur="onBlur('pdf')"/>
	   	    <@blurbutton name="excelPrint" class="button" value="${action.getText('acceptanceInBillButtonManager')}" onclick="excelButtonManager('pdf')" onblur="onBlur('excel')"/>
	   	    <#if !(action.isReadOnly())>
				<@vsubmit name="'submitRecord'" value="'${action.getText('sendsubmit')}'" />
			</#if>
			<div id="pdf" style="display:none;position:absolute;left:150px;top:245px;width:18px;">
				<@vbutton name="print" class="button" value="${action.getText('pdfPrintRuKu')}" onclick="open_spareInBillDetailPdf('#{spareInBill.id}')"/>
				<@vbutton name="print" class="button" value="${action.getText('xlsPrintRuKu')}" onclick="open_spareInBillDetailXls('#{spareInBill.id}')"/>
		   	</div>
		   	<div id="excel" style="display:none;position:absolute;left:240px;top:245px;width:18px;">
				<@vbutton name="print"  class="button" value="${action.getText('pdfYanShouPrint')}" onclick="open_spareInBillDetailYanShouPdf('#{spareInBill.id}')"/>
				<@vbutton name="print"  class="button" value="${action.getText('xlsYanShouPrint')}" onclick="open_spareInBillDetailYanShouXls('#{spareInBill.id}')"/>
		   	</div>
		  -->
		  <#if spareInBill.storageGrade.id==209?if_exists>
		    <@vbutton name="print" class="button" value="入库单打印PDF" onclick="open_twoSpareInBillDetailPdf('#{spareInBill.id}')"/>
			<@vbutton name="print" class="button" value="入库单导出XLS" onclick="open_twoSpareInBillDetailXls('#{spareInBill.id}')"/>
		  <#else>
		  	<@vbutton name="print" class="button" value="入库单打印PDF" onclick="open_spareInBillDetailPdf('#{spareInBill.id}')"/>
			<@vbutton name="print" class="button" value="入库单导出XLS" onclick="open_spareInBillDetailXls('#{spareInBill.id}')"/>
		    <@vbutton name="print"  class="button" value="验收单打印PDF" onclick="open_spareInBillDetailYanShouPdf('#{spareInBill.id}')"/>
			<@vbutton name="print"  class="button" value="验收单导出XLS" onclick="open_spareInBillDetailYanShouXls('#{spareInBill.id}')"/>
		  </#if>
		</#if> 	
   	</@buttonBar>
</@ww.form>

<script language="javascript">
	window.onload=function(){
	    <#if spareInBill.status!='NEWSTATUS'>			
      		
      	 	 document.forms[0].elements["save"].disabled="true";
      	 	//document.forms[0].elements["saveContinue"].disabled="true";
        </#if>
		<#if spareInBill.id?exists>
		  //var grade = $("storageGrade.id");
		 // grade.onmousemove=function(){this.setCapture();}
		 // grade.onmouseout=function(){this.releaseCapture();}
		 // grade.onfocus=function(){this.blur();}
		//var warehouseId=$('outWarehouse.id').value;
	<#--
		//var url = 'listSpareInBillDetails.html?spareInBill.id=#{spareInBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}&warehouse.id=' + warehouseId;
	    //document.all.frame.src = url;
		//document.getElementById("details").className = "selectedtab";
	 -->
		<#else>
			a = new Date();
		  	var time=a.format("yyyy-MM-dd");
		  	document.forms[0].elements["spareInBill.inDate"].value=time;
		</#if>
		<#--
  		<#if !(action.isReadOnly())>
  		<#if spareInBill.id?exists>
			<#if spareInBill.submit==false>
				document.forms[0].elements["submitRecord"].disabled="true";
			<#else>
			document.forms[0].elements["submitRecord"].disabled="false";
			</#if>
  		</#if>
  		</#if>
  		-->
  		<#--
  		    <#if !spareInBill?exists && loginUser?exists>
            $('storageGrade.id').value = #{loginUser.storageGrade.id?if_exists};
  		    </#if>-->
  		
  	    <#if spareInBill.storageGrade?exists>
          $('storageGrade.id').value = #{spareInBill.storageGrade.id?if_exists};
    
           DWREngine.setAsync(false); 
	    		//回调种类的值后触发DWR 级联明细种类  
	      wareHouseCascadeDWR("storageGrade.id","warehouse.id",${loginUser.id?if_exists},"${action.getText('')}")
	    		//重新设置为异步方式
	      DWREngine.setAsync(true);
       
        </#if>
  		
  	 
  		
  		//入仓库
  		
		<#if spareInBill.warehouse?exists>
			$('warehouse.id').value='${spareInBill.warehouse.id?if_exists}';
	    <#else>
	    	<#if req.getParameter('warehouse.id')?exists>
	    		$('warehouse.id').value='${req.getParameter('warehouse.id')?if_exists}';
	    	</#if>
	    </#if> 
	    //出仓库
		<#if spareInBill.outWarehouse?exists>
			$('outWarehouse.id').value='${spareInBill.outWarehouse.id?if_exists}';
	    <#else>
	    	<#if req.getParameter('outWarehouse.id')?exists>
	    		$('outWarehouse.id').value='${req.getParameter('outWarehouse.id')?if_exists}';
	    	</#if>
	    </#if>
	    
	    //显示隐藏标签
	    <#if spareInBill.storageGrade?exists>
	        var warehouseId=$('warehouse.id').value;
            $('storageGrade.id').value= #{spareInBill.storageGrade.id};
             var url = '';
              <#if spareInBill.storageGrade.id==208?if_exists>
                   $('supper').parentNode.parentNode.firstChild.style.display="block";
	               $('supper').parentNode.style.display="block";
	         
	              $('input').parentNode.parentNode.childNodes[2].style.display="none";
	              $('input').parentNode.style.display="none";
	             <#if spareInBill.id?exists> 
	               url = "listSpareInBillDetails.html?spareInBill.id=#{spareInBill.id}&notFather=false&readOnly=${req.getParameter('readOnly')?if_exists}&warehouse=" + warehouseId;
	               $('details').href=url;
	               document.frames["frame"].location.href=url;
	               document.getElementById("details").className = "selectedtab";
	             </#if>
	           <#elseif spareInBill.storageGrade.id==209?if_exists >
	              $('supper').parentNode.parentNode.firstChild.style.display="none";
	              $('supper').parentNode.style.display="none";
	               <#if spareInBill.id?exists> 
	                url = "listSpareInBillDetails.html?spareInBill.id=#{spareInBill.id}&notFather=true&readOnly=${req.getParameter('readOnly')?if_exists}&warehouse=" + warehouseId;
	                $('details').href=url;
	                document.frames["frame"].location.href=url;
	                document.getElementById("details").className = "selectedtab";
	               </#if>
              </#if>
              
         <#else>  
            $('input').parentNode.parentNode.childNodes[2].style.display="none";
	        $('input').parentNode.style.display="none"; 
        </#if>
        
        
   
        
       //转换入库单明细选择
      // removeSupper();
		
	}
	function getOutWarehouseId(){
	  <#if spareInBill.id?exists>
	  var outWarehouseId=$('outWarehouse.id').value;
	      //  $('beautytab').style.display="none";
	      //  $('frame').style.display="none";
	        
	       
	        <#--
	         <#if spareInBill.id?exists>
	           url = "listSpareInBillDetails.html?spareInBill.id=#{spareInBill.id}&notFather=true&readOnly=${req.getParameter('readOnly')?if_exists}&outWarehouseId="+outWarehouseId;
	           $('details').href=url;
	           document.frames["frame"].location.href=url;
	         </#if>-->
	     </#if>
	}
		 //入库人change 事件
	
	 function changePeoplew(){
	  // alert("111");
		var id=""
		<#if spareInBill.id?exists>
		       id = "#{spareInBill.id}";
		</#if>
		var inPeople = $("spareIn.inPeople.id").value;
		var code = $("saveSpareInBill_spareIn.inPeople").value;
		//alert(id+"---"+inPeople+"--"+code);
    }
	
	
	function removeSupper(){
	      
	    var storage = $('storageGrade.id').value;
	      <#if spareInBill.id?exists>
	       $('beautytab').style.display="none";
	        $('frame').style.display="none";
	      </#if>  
	   <#--
	    <#if spareInBill.id?exists>
	       var url = $('details').href;
	    </#if>-->
	     var url = "";
	    if(storage=='208'){
	         $('supper').parentNode.parentNode.firstChild.style.display="block";
	         $('supper').parentNode.style.display="block";
	         
	         $('input').parentNode.parentNode.childNodes[2].style.display="none";
	         $('input').parentNode.style.display="none";
	         <#if spareInBill.id?exists>
	            url = "listSpareInBillDetails.html?spareInBill.id=#{spareInBill.id}&notFather=false&readOnly=${req.getParameter('readOnly')?if_exists}";
	            $('details').href=url;
	            document.frames["frame"].location.href=url;
	         
	         </#if>
	    }else if(storage=='209'){

	         $('supper').parentNode.parentNode.firstChild.style.display="none";
	         $('supper').parentNode.style.display="none";
	          
	         $('input').parentNode.parentNode.childNodes[2].style.display="block";
	         $('input').parentNode.style.display="block";
	      
	         <#if spareInBill.id?exists && spareInBill.outWarehouse?exists>
	           url = "listSpareInBillDetails.html?spareInBill.id=#{spareInBill.id}&notFather=true&readOnly=${req.getParameter('readOnly')?if_exists}";
	           $('details').href=url;
	           document.frames["frame"].location.href=url;
	         </#if> 
	         
	    }
   }
	
	
	
	//鐢熸垚PDF鎶ヨ〃
	function open_spareInBillDetailPdf(id){
       var spareInBillId = id;
       var url='${req.contextPath}/reports/spare/spareInBillDetail.pdf?spareInBillId='+spareInBillId;  
       url = encodeURI(url);
       window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
     }	
     //鐢熸垚xls鎶ヨ〃
     function open_spareInBillDetailXls(id){
       var spareInBillId = id;
       var url='${req.contextPath}/reports/spare/spareInBillDetail.xls?spareInBillId='+spareInBillId;  
       url = encodeURI(url);
       window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
     }
     function open_twoSpareInBillDetailXls(id){
       var spareInBillId = id;
       var url='${req.contextPath}/reports/spare/twoSpareInBillDetail.xls?spareInBillId='+spareInBillId;  
       url = encodeURI(url);
       window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
     }
     function open_twoSpareInBillDetailPdf(id){
       var spareInBillId = id;
       var url='${req.contextPath}/reports/spare/twoSpareInBillDetail.pdf?spareInBillId='+spareInBillId;  
       url = encodeURI(url);
       window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
     }
    //鎵撳嵃楠屾敹鍗�
	function open_spareInBillDetailYanShouPdf(id){
	   if('${(spareInBill.acceptanceList?string)?if_exists}' == "true"){
	      if(!confirm("${action.getText('confirmMessage')}")){
	      	 return false;
	      }
	   }
       var spareInBillId = id;
       var url='${req.contextPath}/reports/spare/spareInBillAccept.pdf?spareInBillId='+spareInBillId;  
       url = encodeURI(url);
       window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
       <#if spareInBill.id?exists>
       	//设置同步
		DWREngine.setAsync(false); 
		//异步修改入库单标识
		SpareInBillJs.saveAcceptanceList('#{spareInBill.id?if_exists}');
		//重新设置为异步方式
		DWREngine.setAsync(true); 
       </#if>
     }	
     //瀵煎嚭楠屾敹鍗�
     function open_spareInBillDetailYanShouXls(id){
       if('${(spareInBill.acceptanceList?string)?if_exists}' == "true"){
	      if(!confirm("${action.getText('confirmMessage')}")){
	      	 return false;
	      }
	   }
       var spareInBillId = id;
       var url='${req.contextPath}/reports/spare/spareInBillAccept.xls?spareInBillId='+spareInBillId;  
       url = encodeURI(url);
       window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
       <#if spareInBill.id?exists>
       	//设置同步
		DWREngine.setAsync(false); 
		//异步修改入库单标识
		SpareInBillJs.saveAcceptanceList('#{spareInBill.id?if_exists}');
		//重新设置为异步方式
		DWREngine.setAsync(true); 
       </#if> 
     }     
    //淇℃伅楠岃瘉
    function checkInput(){
    <#--
      //楠岃瘉鍏ュ簱鍗曞悕绉�
      if(document.forms[0].elements["spareInBill.code"].value==""){
         alert('${action.getText('spareInBill.code.null')}');
         return false;
      }else {
		if (!isValidLengthValue(document.forms[0].elements["spareInBill.code"].value,0,50)) {
			 alert("${action.getText('spareInBill.code.stringlength')}");
			 return false;
		}
	}
	-->
	
      //楠岃瘉鍏ュ簱鍗曞悕绉�
      if(document.forms[0].elements["spareInBill.name"].value==""){
         alert('${action.getText('spareInBill.name.null')}');
         $('spareInBill.name').focus();
         return false;
      }else {
		if (!isValidLengthValue(document.forms[0].elements["spareInBill.name"].value,0,50)) {
			 alert("${action.getText('spareInBill.name.stringlength')}");
			  $('spareInBill.name').focus();
			 return false;
		}
	  }
	 var storageGradeId = $('storageGrade.id').value;
	  if(storageGradeId==''){
	      alert("请选择仓库级别！");
	      $('storageGrade.id').focus();
	   return false;
	  }
	  
	  
      
     
      //楠岃瘉鍏ュ簱浜�
      if(document.forms[0].elements["spareIn.inPeople.id"].value==""){
         alert('${action.getText('inPeople.name.null')}');
          $('spareIn.inPeople.id').focus();
         return false;
      }
      //判断仓库
      if(document.forms[0].elements["warehouse.id"].value==""){
         alert("请选择入仓库");
         $('warehouse.id').focus();
         return false;
      }
      
      if(storageGradeId ==208 && document.forms[0].elements["supplier.id"].value==""){
         alert('${action.getText('supplier.supplierNo.null')}');
          
         return false;
      } 
       
      if(storageGradeId ==209 && $('outWarehouse.id').value==""){
         alert("请选择来源仓库！");
         $('outWarehouse.id').focus();
         return false;
      } 

    
      //楠岃瘉鏃ユ湡
	  if(document.getElementById("spareInBill.inDate").value==''){
        alert('${action.getText('spareInBill.inDate.null')}');
        $('spareInBill.inDate').focus();
        return false;
	  }
	  var date=document.getElementById("spareInBill.inDate").value;
	  if(!isDate("spareInBill.inDate")){
	    alert("${action.getText('select.right.inDate')}");
	     $('spareInBill.inDate').focus();
		return false;
	  }
	 
	   
    }
    
    //PDF璐﹀崟鎵撳嵃
    function pdfButtonManager(type){
    	var op = document.getElementById('pdf')
    	if(op.style.display=="none"){
    		op.style.display="block";
    		if(document.getElementById(type).style.display=="block"){
    			document.getElementById(type).style.display="none"
    		}
    	}else if(op.style.display=="block"){
    		op.style.display="none";
    	}
    }
    //excel琛ㄦ牸瀵煎嚭
    function excelButtonManager(type){
    	var op = document.getElementById('excel')
    	if(op.style.display=="none"){
    		op.style.display="block";
    		if(document.getElementById(type).style.display=="block"){
    			document.getElementById(type).style.display="none"
    		}
    	}else if(op.style.display=="block"){
    		op.style.display="none";
    	}
    }
    //澶卞幓鐒︾偣闅愯棌瀵艰埅灞�
    function onBlur(op){
    	document.getElementById(op).style.display="none";
    }
   //缁х画鏂板缓
    function encodeUrl(){
    <#if spareInBill.id?exists>
    	var url = "${req.contextPath}/spare/editSpareInBill.html?spareInBill.id=#{spareInBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}&saveContinue=RENEW";
    </#if>
    	return location=url;
    }
</script>
    <#if spareInBill.id?exists>
    <ul id="beautytab">
			<li>
				<a id="details" onclick="activeTab(this);" href="#" target="frame" >${action.getText('spareInBillDetail')}</a>
			</li>
	</ul>
	<iframe name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>