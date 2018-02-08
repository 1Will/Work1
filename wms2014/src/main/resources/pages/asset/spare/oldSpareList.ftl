<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/eam2008.ftl" />
<#include "../commonSpareType.ftl"/>
 <#assign spareTitle=""/>
 <#if toolingDevFlag?exists>
   <#if toolingDevFlag=='TOOLINGDEVICE'>
       <#assign spareTitle="${action.getText('spare.accountMaintanence')}"/>
       <#else>
        <#assign spareTitle="${action.getText('spare.accountMaintanence')}"/>
   </#if>
 </#if>
<@htmlPage title="${spareTitle}">
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
	 <@ww.form name="'spares'" action="'searchOldSpares'" method="'post'">
         <@ww.token name="searchSparesToken"/>
         <@ww.hidden name="'strValueIds'" value=""/>
         <@ww.hidden name="'spareLogValueIds'" value=""/>
         <@ww.hidden name="'flag'" value="false"/>
         <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       <#-- <@ww.hidden name="'spareAccountRead'" value="'${req.getParameter('spareAccountRead')?if_exists}'"/>-->
	 	  <#include "./oldSpareSearcher.ftl"/>
	       <#assign itemNo = 0/>
        <@buttonBar>
        	<@vsubmit value="'${action.getText('search')}'"  onclick="'return checkInvalidParms();'"/>
        	<#if !(action.isReadOnly())>
	           <#if toolingDevFlag=='TOOLING'>	
	           		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/asset/spare/editOldSpares.html?toolingDevFlag=TOOLING"/>
	           <#elseif (toolingDevFlag=='DEVICE')>
	           		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/asset/spare/editOldSpares.html?toolingDevFlag=DEVICE"/>
	        	<#else>
	        		<@redirectButton value="${action.getText('new')}" url="editOldSpares.html?toolingDevFlag=TOOLINGDEVICE"/>
	           </#if>
	        <#else>
	        <#--
	           <@redirectButton value="${action.getText('new')}" url="editOldSpares.html?toolingDevFlag=TOOLINGDEVICE"/>
            -->
        	<#--<input type="button" name="" value="${action.getText('spare.inputStock')}" class="button" onclick="spareIn(true);"/>
        	<input type="button" name="" value="${action.getText('spare.outputStock')}" class="button" onclick="spareIn(false);"/>
        	<input type="button" name="" value="${action.getText('spare.spareInventory')}" class="button" onclick="spareInventory();"/>-->
          </#if>	
        </@buttonBar>      
        <@list title="${action.getText('spare.list')}" 
                   includeParameters="id|readOnly|modelSpecs|category.code|putPostion|spareDetailType.id|department.id|checkbox|spareNo|spareName|spareEnName|toolingDevFlag|disabledSpare|selectFlag|onlyValid|onlyInvalid|spare.custos" 
                   fieldMap="like:id|modelSpecs|spareNo|spareName|putPostion|toolingDevFlag|spareEnName|selectFlag" >          
                <#if toolingDevFlag?exists>
                <#if (toolingDevFlag)=='TOOLINGDEVICE'>
                   <@vlh.checkbox title="" name="spareIds" property="id" >
		                 <@vlh.attribute name="width" value="30"/>
	               </@vlh.checkbox>
                <#else>
	                <@vlh.checkbox title="" name="spareIds" property="id" >
		                 <@vlh.attribute name="width" value="30"/>
	               </@vlh.checkbox>
               </#if>
               </#if>
             <#if object.disabled>
                <@vcolumn title="备件编码" property="spareNo" sortable="desc">
                ${object.spareNo}
               	<@alignLeft/>
                </@vcolumn>
             <#else>
	             <@vcolumn title="备件编码" property="spareNo" sortable="desc">
	                <a href="editOldSpares.html?spare.id=#{object.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.spareNo}</a>
	            	<@alignLeft/>
	             </@vcolumn>
             </#if> 
              <@vcolumn title="备件名称" property="name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.modelSpecs')}" property="modelSpecs" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.category')}" property="category.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('spare.spareDetailType')}" property="spareDetailType.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('spare.unit')}" property="unit.value" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
           
            <@vcolumn title="当前总库存">${object.stocks?if_exists}
            	<@ww.hidden name="'spare.hiddenStocks'" value="${object.stocks?if_exists}"/>
            	<@alignRight/>
            </@vcolumn>
             
            <@vcolumn title="可用库存">${object.stocks?if_exists-object.disableStocks?if_exists}
            	<@alignRight/>
            </@vcolumn>
               <@vcolumn title="不可用库存">${object.disableStocks?if_exists}
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="库存明细"  property="">
             	<a href="#" onclick="open_selectDialog('${object.spareNo}');">查看</a>
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.orderNo')}"  property="orderNo" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.factory')}"  property="factory.name" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="供应商"  property="supplier.name" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>

            <#--
            <#if toolingDevFlag?exists>
				<#if (toolingDevFlag=='DEVICE')>
		            <@vcolumn title="${action.getText('spare.device')}" property="device.name">
		            	<@alignLeft/>
		            </@vcolumn>
		        <#elseif (toolingDevFlag=='TOOLING')>
		        	<@vcolumn title="${action.getText('spare.tooling')}" property="device.name">
		            	<@alignLeft/>
		            </@vcolumn>
            	</#if>
			</#if>
			-->
            <@vcolumn title="入库历史">
                 <a href="#" onclick="spareHistory_openDialog(#{object.id}, '${toolingDevFlag?if_exists}', 'IN');">查看</a>
                 <@alignCenter/>
            </@vcolumn>
            <@vcolumn title="出库历史">
                 <a href="#" onclick="spareHistory_openDialog(#{object.id}, '${toolingDevFlag?if_exists}', 'OUT');">查看</a>
                 <@alignCenter/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('spare.spareInventoryHistory')}">
                 <a href="#" onclick="spareHistory_openDialog(#{object.id}, '${toolingDevFlag?if_exists}', 'INVENTORY');">查看</a>
                 <@alignLeft/>
            </@vcolumn>
			-->
        </@list>
       
  <script> 
        //库存明细
       	function open_selectDialog(spareNo) {
	 		var url = '${req.contextPath}/asset/spare/listOldSpareDetailSearcherCommon.html?readOnly=true&spareNo='+spareNo;
	 		popupModalDialog(url,1024,900);
	 	} 
        function spareHistory_openDialog(spareId, flag, inOut) {
        	var url = "${req.contextPath}/spare";
        	
        	if (inOut == 'IN') {
        		url += "/listOldSpareInWareHouseHistory.html";
        	} else if (inOut == 'OUT') {
        		url += "/listOldSpareOutWareHouseHistory.html";
        	} else if (inOut == 'INVENTORY') {
        		url += "/listOldSpareInventoryBillHistory.html";
        	} else {
        		return ;
        	}
        	
        	url += "?spare.id=" + spareId;
        	
        	disablepopupModalDialog(url,Config.popW, Config.popH);
        }
        
       
        function disablepopupModalDialog(url,w,h){
           if(getObjByNameRe("flag").value=="true"){
                 return false;
           }
           popupModalDialog(url,w,h);
           checkInvalidParms();
          // spares.submit()
        }
        
        function changeValue(){
          getObjByNameRe("flag").value="true";  
        }
        
        //打印报表
        function open_spareListPdf(toolingDevFlag){
        	var s=document.forms[0].elements["spareName"].value;
        	//打印所有报表
        	//var url='${req.contextPath}/reports/spare/';
	        if(toolingDevFlag=='TOOLINGDEVICE'){
	        	//url = url +'spareAllList.pdf?';
	        	var url='${req.contextPath}/reports/spare/spareAllList.pdf?spareNo='+(document.forms[0].elements["spareNo"].value)+
		       	'&spareName='+(document.forms[0].elements["spareName"].value) +
	        	'&modelSpecs='+ (document.forms[0].elements["modelSpecs"].value)+
	        	'&category.code='+ (document.forms[0].elements["category.code"].value)+
	        	'&toolingDevFlag='+(document.forms[0].elements["toolingDevFlag"].value);
	        //打印属于TOOLING的报表
	        }else if(toolingDevFlag=='TOOLING'){
	        	//url = url +'spareToolingList.pdf?';
	        	var url='${req.contextPath}/reports/spare/spareToolingList.pdf?spareNo='+document.forms[0].elements["spareNo"].value+
		       	'&spareName='+(document.forms[0].elements["spareName"].value) +
	        	'&modelSpecs='+ (document.forms[0].elements["modelSpecs"].value)+
	        	'&category.code='+ (document.forms[0].elements["category.code"].value)+
	        	'&toolingDevFlag='+(document.forms[0].elements["toolingDevFlag"].value);
	        	alert(url);
	        //打印属于DEVICE的报表
	        }else{
	        	//url = url +'spareDeviceList.pdf?';
	        	var url='${req.contextPath}/reports/spare/spareDeviceList.pdf?spareNo='+document.forms[0].elements["spareNo"].value+
		       	'&spareName='+(document.forms[0].elements["spareName"].value) +
	        	'&modelSpecs='+ (document.forms[0].elements["modelSpecs"].value)+
	        	'&category.code='+ (document.forms[0].elements["category.code"].value)+
	        	'&toolingDevFlag='+(document.forms[0].elements["toolingDevFlag"].value);	 
	        	alert(url);      	
	        }
        	url = encodeURI(url);
      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
        }
        
        function open_spareListXls(toolingDevFlag){
        var s=document.forms[0].elements["spareName"].value;
        	//打印所有报表
        	//var url='${req.contextPath}/reports/spare/';
	        if(toolingDevFlag=='TOOLINGDEVICE'){
	        	//url = url +'spareAllList.pdf?';
	        	var url='${req.contextPath}/reports/spare/spareAllList.pdf?spareNo='+(document.forms[0].elements["spareNo"].value)+
		       	'&spareName='+(document.forms[0].elements["spareName"].value) +
	        	'&modelSpecs='+ (document.forms[0].elements["modelSpecs"].value)+
	        	'&category.code='+ (document.forms[0].elements["category.code"].value)+
	        	'&toolingDevFlag='+(document.forms[0].elements["toolingDevFlag"].value);
	        //打印属于TOOLING的报表
	        }else if(toolingDevFlag=='TOOLING'){
	        	//url = url +'spareToolingList.pdf?';
	        	var url='${req.contextPath}/reports/spare/spareToolingList.pdf?spareNo='+document.forms[0].elements["spareNo"].value+
		       	'&spareName='+(document.forms[0].elements["spareName"].value) +
	        	'&modelSpecs='+ (document.forms[0].elements["modelSpecs"].value)+
	        	'&category.code='+ (document.forms[0].elements["category.code"].value)+
	        	'&toolingDevFlag='+(document.forms[0].elements["toolingDevFlag"].value);
	        	alert(url);
	        //打印属于DEVICE的报表
	        }else{
	        	//url = url +'spareDeviceList.pdf?';
	        	var url='${req.contextPath}/reports/spare/spareDeviceList.pdf?spareNo='+document.forms[0].elements["spareNo"].value+
		       	'&spareName='+(document.forms[0].elements["spareName"].value) +
	        	'&modelSpecs='+ (document.forms[0].elements["modelSpecs"].value)+
	        	'&category.code='+ (document.forms[0].elements["category.code"].value)+
	        	'&toolingDevFlag='+(document.forms[0].elements["toolingDevFlag"].value);	 
	        	alert(url);      	
	        }
        	url = encodeURI(url);
      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
        }
        function confirmagainDeletes(inputValue,checkValue){
            if(inputValue){
               checkValue;
               return true;
            }
            return false;
        } 
        
        function confirmSetsafeStock(boxName, message) {
           if (hasChecked(boxName)) {
               return confirmDelete(message);
             } else {    	
               alert("${action.getText('noSettingStcok')}");
               return false;
               }
        }
        
        
      function rollon(){
        if(window.event.srcElement.tagName=="A"||window.event.srcElement.tagName=="IMG"){
         if(getObjByNameRe("flag").value=="true"){
           if(confirm("${action.getText('noSaveSpare')}")){
	     	 	return true;
	     	 }else {
	     	 	return false;
	     	 }
           }
           
        }
      }
      

　　 document.onclick = rollon;  

            
   
        /* 设置每个checkbox的onclick响应事件 */
        /*  
        var spareIdSelector = document.getElementsByName("spareIds");
        for ( var i = 0; i < spareIdSelector.length; i ++) {
        	spareIdSelector[i].onclick = toggleSafeStockStatus(i);
        }
        var checkboxSelector = document.forms[0].elements;
        for (var i=0;i<checkboxSelector.length;i++){
           var number=0;
           var e=checkboxSelector[i];
           if (e.type=="checkbox"){
              number+=parseInt(1);
           }
           if(number==2){
              alert("execute");
              e.onclick=userCheckboxSetter(e.checked);
           }
       }
       
       */
       
       /* 全选checkbox事件处理 */
       function callBack() {
       	var stockSet = document.getElementsByName("spare.safe_stock");
       	var spareIdSelector = document.getElementsByName("spareIds");
        var sparestatus = document.getElementsByName("status");
        for ( var i = 0; i < stockSet.length; i ++) {
        	if (sparestatus[i].value=="false") {
        		spareIdSelector[i].checked="";
        	}
        }
        /*
        for ( var i = 0; i < spareIdSelector.length; i ++) {
            alert(spareIdSelector[i].checked);
        	if(spareIdSelector[i].disabled){
        	   spareIdSelector[i].checked='';
        	}
        }
        */
        /*
        if(document.forms[0].elements["toggleSafeStockButton"].value == "${action.getText('spare.setSafeStock')}"){
        	document.forms[0].elements["toggleSafeStockButton"].value = "${action.getText('spare.saveSafeStock')}";
        } else {
        	document.forms[0].elements["toggleSafeStockButton"].value = "${action.getText('spare.setSafeStock')}";
        }
        */
       }
       
       function userCheckboxSetter(checkValue){
        var spareIdSelector = document.getElementsByName("spareIds");
        for ( var i = 0; i < spareIdSelector.length; i ++) {
        	spareIdSelector[i].checked = checkValue;
        }
       }
        
        /* checkbox响应事件的处理函数，如果checked值为false，就关闭表格中的输入框 */
        function toggleSafeStockStatus(i){
			return function(){
		  		var stockSet = document.getElementsByName("spare.safe_stock");
		  		var spareIdSelector = document.getElementsByName("spareIds");
		  		
		  		if (false == spareIdSelector[i].checked) {
	        		stockSet[i].disabled = "true";
	        	}
	            retriveSafeStockText();
	            addNewCheckBox();
		 	}
		 }
		 
		 function addNewCheckBox(){
		     var spareIdSelector = document.getElementsByName("spareIds");
		      var valueselector = document.getElementsByName("spare.safe_stock");
		     if(document.forms[0].elements["toggleSafeStockButton"].value=="${action.getText('spare.saveSafeStock')}"){
		        for ( var i = 0; i < spareIdSelector.length; i ++){
		            if(spareIdSelector[i].checked){
		               valueselector[i].disabled=false;
			            	valueselector[i].readonly=false;
		            }
		        }
		     }
		 }
		 
		 function retriveSafeStockText(){
		     var spareIdSelector = document.getElementsByName("spareIds");
		     var number=0;
             for ( var i = 0; i < spareIdSelector.length; i ++) {
        	    if(spareIdSelector[i].checked==true){
        	      number+=parseInt(1);
        	    }
        	 }
        	 if(parseInt(number)==0){
        	    document.forms[0].elements["toggleSafeStockButton"].value="${action.getText('spare.setSafeStock')}";
        	 }
        }
        
        function confirmSetStock(){
              return saveSafeStock();
        }
        
       function storeInvalidParms(){
           if (getObjByNameRe("category.code").value==-1){
              getObjByNameRe("category.code").value='';
           }
           //if (getObjByNameRe("department.id").value==-1){
              //getObjByNameRe("department.id").value='';
           //}
           getObjByNameRe("selectFlag").value="T";
           return true;
     }
		 
        function saveSafeStock(){
        	    if(!validateStockIsNumber()){
        	       return false;
        	    }
        		updateSafeStock();
        		storeInvalidParms();
        		checkInvalidParms();
        		return true;
        }
        
      /* 验证安全库存 */
             
      function validateStockIsNumber(){
         var spareIdSelector = document.getElementsByName("spareIds");
          var spareNameSelector = document.getElementsByName("spare.spareName");
           var valueselector = document.getElementsByName("spare.safe_stock");
            for (i = 0; i < spareIdSelector.length; i++) {
             if(valueselector[i].value==''){
                alert("${action.getText('spare.safe_stock.notEmpty')}");
                return false;
             }else{
                 var control=isNumberBetween(valueselector[i].value,100000001,0);
                    if(control==0){
                       alert("${action.getText('spare.safe_stock.overExtend')}");
                         return false;
                    }else{
                        if(control==-1){
                          alert("${action.getText('spare.safe_stock.notNumber')}");
                           return false;
                        }
                      <#--alert("${action.getText('spare')}"+spareNameSelector[i].value+"${action.getText('spare.notNumber')}"); -->
                    }
                  }
              }
           return true;
        }
        
        function updateSafeStock(){
               var selector = document.getElementsByName("spareIds");
               var valueselector = document.getElementsByName("spare.safe_stock");
               var hiddenSafeStock=	document.getElementsByName("hiddenSaftStock");
               var hiddenStock=	 document.getElementsByName("spare.hiddenStocks");   
		       var ary = new Array();
		       var sysLogAry=new Array();
		       if (selector.length) {
		        for (i = 0; i < selector.length; i++) {
		            	ary.push(selector[i].value);
		            	ary.push(valueselector[i].value);
		            	sysLogAry.push(selector[i].value);
		            	sysLogAry.push(hiddenSafeStock[i].value);
		            	sysLogAry.push(hiddenStock[i].value);
		            }
		       } 
		       document.forms[0].elements["strValueIds"].value=ary;
		       document.forms[0].elements["spareLogValueIds"].value=sysLogAry;
		       //alert( document.forms[0].elements["strValueIds"].value);
		      // alert( document.forms[0].elements["spareLogValueIds"].value);
        }
        
        function setSafeStock(){
    		    var selector = document.getElementsByName("spareIds");
    		    var valueselector = document.getElementsByName("spare.safe_stock");
			    if (selector.length) {
		        for (i = 0; i < selector.length; i++) {
		            if (selector[i].checked) {
		            	valueselector[i].disabled=false;
		            	valueselector[i].readonly=false;
		            }
		        }
		    } 
        }
            
        function spareInventory(){
             var selector = document.getElementsByName("spareIds");
			    if (!selector) {
			        return false;
			    }
			    
			    var ary = new Array();
		    if (selector.length) {
		        for (i = 0; i < selector.length; i++) {
		            if (selector[i].checked) {
		            	ary.push(selector[i].value);
		            }
		        }
		    } 
		     <#if toolingDevFlag=='TOOLING'>
    		   window.location='${req.contextPath}/asset/spare/editSpareInventory.html?spareIds='+ary+'&toolingDevFlag=TOOLING&readOnly=${req.getParameter('readOnly')?if_exists}';
    		<#else>
    		   window.location='${req.contextPath}/asset/spare/editSpareInventory.html?spareIds='+ary+'&toolingDevFlag=DEVICE&readOnly=${req.getParameter('readOnly')?if_exists}';
    		</#if>
        }
            
    	function spareIn(input) {
    		    var selector = document.getElementsByName("spareIds");
			    if (!selector) {
			        return false;
			    }
			    var ary = new Array();
		    if (selector.length) {
		        for (i = 0; i < selector.length; i++) {
		            if (selector[i].checked) {
		            	ary.push(selector[i].value);
		            }
		        }
		    } 
		    if(input){
		    <#if toolingDevFlag=='TOOLING'>
    		   window.location='${req.contextPath}/asset/spare/editSpareIn.html?spareIds='+ary+'&toolingDevFlag=TOOLING&readOnly=${req.getParameter('readOnly')?if_exists}';
    		<#else>
    		   window.location='${req.contextPath}/asset/spare/editSpareIn.html?spareIds='+ary+'&toolingDevFlag=DEVICE&readOnly=${req.getParameter('readOnly')?if_exists}';
    		</#if>
    		}
    		else{
    			if(ary.length){
    				var Stocks = document.getElementsByName("spare.hiddenStocks");
    				if (selector.length) {
				        for (i = 0; i < selector.length; i++) {
				            if (selector[i].checked&&Stocks[i].value==0) {
				            	alert("${action.getText('di')}"+(i+1)+" ${action.getText('please.select.spare')}");
				            	return false;
				            }
				        }
				    } 
    			}
    		   <#if toolingDevFlag=='TOOLING'>
    		    window.location='${req.contextPath}/asset/spare/editSpareOut.html?spareIds='+ary+'&inOutFlag=out&toolingDevFlag=TOOLING&readOnly=${req.getParameter('readOnly')?if_exists}';
    		<#else>
    		   window.location='${req.contextPath}/asset/spare/editSpareOut.html?spareIds='+ary+'&inOutFlag=out&toolingDevFlag=DEVICE&readOnly=${req.getParameter('readOnly')?if_exists}';
    		</#if>	
    		}
    	}
       </script>	 	
     </@ww.form>
</@htmlPage>