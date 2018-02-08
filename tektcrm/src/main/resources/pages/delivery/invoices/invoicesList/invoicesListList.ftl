
<#include "../../../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'invoicesList'" action="'searchInvoicesList'" namespace="'/delivery'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchInvoicesListToken"/>
    	<@ww.hidden name="'invoices.id'" value="'${req.getParameter('invoices.id')?if_exists}'"/>
    	<@ww.hidden name="'customerInfo.id'" value="'${req.getParameter('customerInfo.id')?if_exists}'"/>
    	<@ww.hidden name="'saveStr'" value="''"/>
    	<@ww.hidden name="'refreashFlag'" value="'${req.getParameter('refreashFlag')?if_exists}'"/>
    	 <#assign itemNo=1/>
        <@list title="${action.getText('发货单明细列表')}" 
            includeParameters="contractManagement.id|invoicesList|readOnly|onlyInvalid|onlyValid|invoices.id" 
        	fieldMap="like:" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="invoicesListIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>     
            </#if>   
           	 <@alignCenter/>
           	  <@vcolumn title="${action.getText('序号')}" property="id"  >
                  #{itemNo}
                  <@ww.hidden name="isSaved" value="'${object.isSaved?if_exists}'"/>
	            <@alignLeft attributes="width:20;"/>
	            </@vcolumn >
	            <#assign itemNo=itemNo + 1/>
	        <@vcolumn title="${action.getText('合同编号')}"  property="productList.contractManagement.code" >
            	<@alignLeft/>
         	</@vcolumn>
         	<@vcolumn title="${action.getText('合同名称')}"  property="productList.contractManagement.contractName" >
            	<@alignLeft/>
         	</@vcolumn>
         	<#if object.products?exists>
         		<@vcolumn title="${action.getText('产品名称')}" property="products.name" >
	            	<@alignLeft/>
	         	</@vcolumn> 
	         	<@vcolumn title="${action.getText('产品型号')}" property="products.model" >
	         		<@alignLeft/>
	         	</@vcolumn>
	         	<#else>
	         	<@vcolumn title="${action.getText('产品名称')}" property="productList.product.name" >
	            	<@alignLeft/>
	         	</@vcolumn> 
	         	<@vcolumn title="${action.getText('产品型号')}" property="productList.product.model" >
	         		<@alignLeft/>
	         	</@vcolumn>
         	</#if>
            <@vcolumn title="${action.getText('合同数量')}" property="productList.count" >
            	<@alignRight/>
         	</@vcolumn> 
         	<#--	  
         	<@vcolumn title="${action.getText('已发数量')}" property="finishedSum" >
            	<@alignRight/>
         	</@vcolumn>
         	-->
         	<#if object.products?exists>
         		<#--
	         	<@vcolumn title="${action.getText('未发数量')}">
	         		<@alignRight/>
	         	</@vcolumn>
	         	-->
	         	<@vcolumn title="${action.getText('本次数量')}" property="currentSum" >
            	<@ww.hidden value="#{object.currentSum?if_exists}"/>
            	<@ww.hidden value="no"/>
	            <@alignRight/>
	         	</@vcolumn> 
	         	<@vcolumn title="${action.getText('单价')}" property="products.salePrice">
	     			<@alignRight/>
	            </@vcolumn>
	            <#else>
	            <#--
            	<@vcolumn title="${action.getText('未发数量')}" property="restSum" >
	         		<@alignRight/>
	         	</@vcolumn>
	         	-->
	         	<@vcolumn title="${action.getText('本次数量')}" property="currentSum" >
            	<@ww.hidden value="#{object.currentSum?if_exists}"/>
            	<@ww.hidden value="#{object.productList.count - object.productList.deliveryedCount?if_exists}"/>
            	<@alignRight/>
         	</@vcolumn> 
	         	<@vcolumn title="${action.getText('单价')}" property="productList.unitPrice">
	     			<@alignRight/>
	            </@vcolumn>
            </#if>
         	 <@vcolumn title="${action.getText('发货金额')}" property="deliveryPrice" >
            	<@alignRight/>
         	</@vcolumn>
         	 <@vcolumn title="${action.getText('备注')}" property="mark" >
         	 	<@ww.hidden value="'${object.mark?if_exists}'"/>
            	<@alignLeft attributes="width:100;"/>
         	</@vcolumn>
         	 <@vcolumn title="${action.getText('状态')}">
            	<@alignLeft/>
         	</@vcolumn>
        </@list>
        
        <#if !(action.isReadOnly())>
        <@buttonBar>
        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		  	<@vbutton class="button" value="${action.getText('new')}" onclick="newInvoicesList()"/>
		  	<@vbutton class="button" value="${action.getText('从合同选择产品')}" onclick="contractManagement_OpenDialog()"/>
	  		<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('productList.confirmMessage')}?" />	 
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'" onclick="'return addRefreshFlag();'">
               <@ww.param name="'onclick'" value="'return confirmDeletes(\"invoicesListIds\", \"确认删除所选的发货单明细\");'"/>
            </@vsubmit>
		</@buttonBar>	
		</#if>
   </@ww.form>
   <script type='text/javascript' src='${req.contextPath}/dwr/interface/invoicesListNew.js'></script>
   <script language="javascript">
   //判断是否需要刷新上层 页面
   if(getObjByName('refreashFlag').value != ""){
   		self.parent.location.reload()
   }
   
   window.onload = function(){
   		var oTable = document.getElementById('vltable');
   		var oTbody = oTable.getElementsByTagName('tbody')[0];
   		
   		for(var i = 1;i < oTbody.children.length;i++){
   			var isSaved = oTbody.children[i].children[1].children[0].value;
   			if(isSaved == '0'){
   				var val0 = oTbody.children[i].children[oTbody.children[i].children.length-5].children[1].value;
   				oTbody.children[i].children[oTbody.children[i].children.length-2].innerHTML = "<input size='10' value='' type='text'/>";
   				var val2 = oTbody.children[i].children[oTbody.children[i].children.length-5].children[0].value;
   				oTbody.children[i].children[oTbody.children[i].children.length-5].innerHTML = "<input size='3' onchange='checkNull(this)' value='' type='text'/><input type='hidden' value='"+val0+"'/><input type='hidden' value='"+val2+"'/>";
   				oTbody.children[i].children[oTbody.children[i].children.length-1].innerHTML = '新建';
   			}else{
   				var val0 = oTbody.children[i].children[oTbody.children[i].children.length-5].children[1].value;
   				var val1 = oTbody.children[i].children[oTbody.children[i].children.length-2].children[0].value;
   				oTbody.children[i].children[oTbody.children[i].children.length-2].innerHTML = "<input size='10' value='"+val1+"' type='text'/>";
   				var val2 = oTbody.children[i].children[oTbody.children[i].children.length-5].children[0].value;
   				oTbody.children[i].children[oTbody.children[i].children.length-5].innerHTML = "<input size='3' onchange='checkNull(this)' value='"+val2+"' type='text'/><input type='hidden' value='"+val0+"'/><input type='hidden' value='"+val2+"'/>";
   				oTbody.children[i].children[oTbody.children[i].children.length-1].innerHTML = '已发';
   			}
   		}
   }
   
   function savee(){
   		var oTable = document.getElementById('vltable');
   		var oTbody = oTable.getElementsByTagName('tbody')[0];
   		var saveStr = "";
   		for(var i = 1;i < oTbody.children.length;i++){
   			/*var isSaved = oTbody.children[i].children[1].children[0].value;
   			if(isSaved == '0'){
   				
   			}*/
			var invoicesListId = oTbody.children[i].children[0].children[0].value;
			var mark = oTbody.children[i].children[oTbody.children[i].children.length-2].children[0].value;
			var inCurrentSum;
			var currentSum;
			var restSum;
			var thisLine;
			if(isIE()){
				var pos1 = oTbody.children[i].childNodes.length-5; 
   				inCurrentSum = oTbody.children[i].childNodes[pos1].children[0].value;
   				restSum = oTbody.children[i].childNodes[pos1].children[1].value;
   				currentSum = oTbody.children[i].childNodes[pos1].children[2].value;
   				/*var pos2 = oTbody.children[i].childNodes.length-5;
   				restSum = oTbody.children[i].childNodes[pos2].innerText.replace(/(^\s*)|(\s*$)/g, "");*/
   				thisLine = oTbody.children[i].children[pos1].childNodes[0];
			}else{
				var pos1 = oTbody.children[i].children.length-5; 
   				inCurrentSum = oTbody.children[i].children[pos1].children[0].value;
   				restSum = oTbody.children[i].children[pos1].children[1].value;
   				currentSum = oTbody.children[i].children[pos1].children[2].value;
   				/*var pos2 = oTbody.children[i].children.length-5;
   				restSum = oTbody.children[i].children[pos2].innerText.replace(/(^\s*)|(\s*$)/g, "");*/
   				thisLine = oTbody.children[i].children[pos1].children[0];
			}
			
			if(inCurrentSum == ''){
				alert('请填写本次数量!');
				thisLine.focus();
				return false;
			}
			if(isNaN(currentSum)){
				alert('请输入合法数字!');
				thisLine.focus();
				return false;
			}
			if(restSum != 0 && restSum != ""){
				if(parseInt(inCurrentSum) > parseInt(currentSum) + parseInt(restSum)){
   					alert('本次数量应小于未发数量!');
   					thisLine.focus();
   					return false;
   				}
			}
			saveStr += invoicesListId+","+inCurrentSum+","+mark+";"
   		} 
   		//保存数据
   		getObjByName('saveStr').value = saveStr;
   		getObjByName('refreashFlag').value = "refreashFlag";
   		return true;	
   }
	   
    //从合同选择发货单
    function contractManagement_OpenDialog(){
	   var url = "${req.contextPath}/productList/listProductList_invoices.html?invoicesList=invoicesList&invoices.id=${req.getParameter('invoices.id')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=${req.getParameter('customerInfo.id')?if_exists}";
	   popupModalDialog(url, 900, 600, creatorSelector2Handler);
	   //window.open(url);
	 }
	function creatorSelector2Handler(result) {
		if (null != result) {
			var resultStr = "";
			for(var i=0;i<result.length;i++){
				if(i != result.length-1){
					resultStr += result[i]+",";
				}else{
					resultStr += result[i];
				}
			}
			resultStr += ",productList";
			//获取到productList的值
			var invoiceId = getObjByName('invoices.id').value;
			DWREngine.setAsync(false); 
	    	invoicesListNew.saveInvoicesList(resultStr,invoiceId);
			//重新设置为异步方式
			DWREngine.setAsync(true); 
		}
		self.location = self.location+"&invoices.id=${req.getParameter('invoices.id')?if_exists}&customerInfo.id=${req.getParameter('customerInfo.id')?if_exists}";
	}
	//编辑发货单明细
	/*function editProductList(v){
      var url='${req.contextPath}/productList/editProductList.html?productList.id='+'&contractManagementid=${req.getParameter('contractManagement.id')?if_exists}'+"&readOnly=${req.getParameter('readOnly')?if_exists}";
      //popupModalDialog(url,800,600);
      openNewWindow(url);
      if(isIE()){self.location.reload();};
	}*/
	 //新建发货单明细（从产品选择）
	function newInvoicesList(){
      var url='${req.contextPath}/productsManager/listProducts.html?readOnly=${req.getParameter('readOnly')?if_exists}&productCheckBox=productCheckBox';
      popupModalDialog(url,800,600,newInvoicesListHandler);
      //openNewWindow(url);
      if(isIE()){self.location.reload();};
	}
	function newInvoicesListHandler(result){
		console.log(result);
		if (null != result) {
			var resultStr = "";
			for(var i=0;i<result.length;i++){
				if(i != result.length-1){
					resultStr += result[i]+",";
				}else{
					resultStr += result[i];
				}
			}
			resultStr += ",product";
			//获取到product的值
			var invoiceId = getObjByName('invoices.id').value;
			DWREngine.setAsync(false); 
	    	invoicesListNew.saveInvoicesList(resultStr,invoiceId);
			//重新设置为异步方式
			DWREngine.setAsync(true); 
		}
		self.location = self.location+"&invoices.id=${req.getParameter('invoices.id')?if_exists}&customerInfo.id=${req.getParameter('customerInfo.id')?if_exists}";
	}
	//增加刷新上层窗口flag
	function addRefreshFlag(){
		getObjByName('refreashFlag').value = "refreashFlag";
	}
	//检测非空
	function checkNull(obj){
		if(obj.value == ""){
			setTimeout(function(){
				obj.value = "";
				obj.focus();
			},0);
			alert("请填写本次数量!");
			return false;
		}
   		var oTr = obj.parentNode.parentNode;
		var inCurrentSum;
		var currentSum;
		var restSum;
		if(isIE()){
			var pos1 = oTr.childNodes.length-5; 
			inCurrentSum = oTr.childNodes[pos1].children[0].value;
			currentSum = oTr.childNodes[pos1].children[2].value;
			restSum = oTr.childNodes[pos1].children[1].value;
		}else{
			var pos1 = oTr.children.length-5; 
			inCurrentSum =oTr.children[pos1].children[0].value;
			currentSum = oTr.children[pos1].children[2].value;
			restSum = oTr.children[pos1].children[1].value;
		}
		if(isNaN(currentSum)){
			setTimeout(function(){
				obj.value = "";
				obj.focus();
			},0);
			alert('请输入合法数字!');
			return false;
		}
		if(restSum != 0 && restSum != ""){
			if(parseInt(inCurrentSum) > parseInt(currentSum) + parseInt(restSum)){
				setTimeout(function(){
					obj.value = "";
					obj.focus();
				},0);
				alert('本次数量应小于未发数量!');
				return false;
			}
		}
	}
</script>
</@framePage>