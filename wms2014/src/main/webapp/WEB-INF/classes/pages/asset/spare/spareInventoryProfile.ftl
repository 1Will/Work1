<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('spareInventory.title')}">
 <base target="_self">
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
	 <@ww.form name="'spareInventory'" action="'saveSpareInventory'" method="'post'">
	    <@ww.token name="saveSpareInventoryToken"/>
	     <@ww.hidden name="'matchCheckbox'" value="'${matchCheckbox?if_exists}'"/>
	     <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	     <#if spareInventory.id?exists>
	 	  <@ww.hidden name="'reportId'" value="#{spareInventory.id?if_exists}"/>
	 	  </#if>
	     <#assign itemNo = 1/>
	      <@ww.hidden name="'inventorySpareStock'" value="'${inventorySpareStock?if_exists}'"/> 
	      <@ww.hidden name="'spareIds'" value="'${spareIds?if_exists}'"/> 
	      <@ww.hidden name="'spareInventory.id'" value="'${spareInventory.id?if_exists}'"/> 
	      <@ww.hidden name="'popupPage'" value="'${popupPage?if_exists}'"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  	   <@ww.textfield label="'${action.getText('spareInventory.inventoryNo')}'" name="'spareInventory.inventoryNo'" value="'${spareInventory.inventoryNo?if_exists}'" cssClass="'underline'" disabled="true"/>
	           <@ww.textfield label="'${action.getText('spareInventory.name')}'" name="'spareInventory.name'" value="'${spareInventory.name?if_exists}'" cssClass="'underline'" required="true"/>
	        </tr>
	        <tr>
	 	  	   <@pp.datePicker label="'${action.getText('spareInventory.inventoryDateTm')}'" name="'spareInventory.inventoryDateTm'" value="'${(spareInventory.inventoryDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="true"/>
	           <td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('spareInventory.inventorytitlePeople')}:</label></td>
		 		<td>
		 			<#assign inventoryPeople=''/> 
			 		<#if spareInventory.inventoryPeople?exists>
			 		<#assign inventoryPeople="${spareInventory.inventoryPeople}"> 
			 		<#else>
			 		<#assign inventoryPeople="${loginUser.name}"> 
			 		</#if>
		 			<input type="text" name="spareInventory.inventoryPeople" class="underline"  value="${inventoryPeople}"  maxlength="150" readonly="true" required="true"/>
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
		     </tr>
		     <tr>
		 		<@ww.textarea label="'${action.getText('spareInventory.comment')}'" name="'spareInventory.comment'" value="'${spareInventory.comment?if_exists}'" cssClass="'underline'" required="false" rows="3" cols="50"/>
		 		
		 		<#--<@ww.checkbox label="'${action.getText('checkbox.match')}'"  name="'matchCheckboxbox'"  fieldValue="'true'" onclick="'updateCheckboxStatus()'" />-->
	         </tr>
		 </@inputTable> 
	 	  <@buttonBar>
	 	    <#if !(action.isReadOnly())>	
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return updateSpareIn();'"/> 
	        </#if>
	        	<#if popupPage!='T'> 
	        	<@redirectButton  value="${action.getText('back')}" url="listSpares.html?toolingDevFlag=TOOLING&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	        	</#if>
	        	<#if !(action.isReadOnly())>	
	        		<@vsubmit name="'submitRecord'" value="'${action.getText('sendsubmit')}'" />
	        	</#if>
	        	<#if spareInventory.id?exists>
	        	 	<@vbutton name="print"  class="button" value="${action.getText('print')}" onclick="open_SpareInventoryPdf('#{spareInventory.id}')"/>
	        	</#if>
	     </@buttonBar>
	     <@titleBar title="${action.getText('spareInventory.list')}"/>
	       <@listTable>
	        	     <tr>
	        	         <th><input type="checkbox" name="checkbox"  value=""></th>
	                     <th>${action.getText('spareInventory.serial')}</th>
	                     <th>${action.getText('spare.spareNo')}</th>
	                     <th>${action.getText('spare.name')}</th>
	                     <th>${action.getText('spare.enname')}</th>
	                     <th>${action.getText('spare.modelSpecs')}</th>
	                     <th>${action.getText('spare.category')}</th>
	                     <th>${action.getText('spare.danwei')}</th>
	                     <th>${action.getText('spare.actualNumber')}</th>
	                     <th>${action.getText('spare.inventoryNumber')}</th>
	                 </tr>
	                 <#if !spareInventoryIsExists?exists>
	                      <#if (spares.size()>0)>
	                            <#list spares as inventoryDetail>
	                            <tr>
	                              <td><input type="checkbox" name="checkbox"  value="#{inventoryDetail.id}"/></td>
	                              <td class="alignCenter">#{itemNo}</td>
		                          <#assign itemNo=itemNo+1 />
	                              <td class="alignLeft">${inventoryDetail.spareNo?if_exists}</td>
	                              <td class="alignLeft">${inventoryDetail.name?if_exists}</td>
	                              <td class="alignLeft">${inventoryDetail.enName?if_exists}</td>
	                              <td class="alignLeft">${inventoryDetail.modelSpecs?if_exists}</td>
	                              <#if inventoryDetail.category?exists>
	                              <td class="alignLeft">${inventoryDetail.category.value?if_exists}</td>
	                              <#else>
	                              <td></td>
	                              </#if>
	                              <#if inventoryDetail.unit?exists>
			                          <td class="alignRight">${inventoryDetail.unit.value?if_exists}</td>
			                      <#else>
			                          <td></td>
			                      </#if> 
	                              <td class="alignRight">
	                              #{inventoryDetail.stocks?if_exists}
	                              </td>
	                              <td class="alignRight"><input type="text" name="inventoryDetail.inventoryNumber" value=""  class="definedLength" /></td>
	                             </tr>  
	                            </#list>
	                      </#if>
	                 <#else>
	                      <#if (spareInventory.inventoryDetails.size()>0)>
	                           <#list spareInventory.inventoryDetails as inventoryDetail>
	                             <tr>
	                              <td><input type="checkbox" name="checkbox"  value="#{inventoryDetail.spare.id}"/></td>
	                              <td class="alignCenter">#{itemNo}</td>
		                          <#assign itemNo=itemNo+1 />
	                              <td class="alignLeft">${inventoryDetail.spare.spareNo?if_exists}</td>
	                              <td class="alignLeft">${inventoryDetail.spare.name?if_exists}</td>
	                              <td class="alignLeft">${inventoryDetail.spare.enName?if_exists}</td>
	                              <td class="alignLeft">${inventoryDetail.spare.modelSpecs?if_exists}</td>
	                              <#if inventoryDetail.spare.category?exists>
	                              <td class="alignLeft">${inventoryDetail.spare.category.value?if_exists}</td>
	                              <#else>
	                              <td></td>
	                              </#if>
	                              <#if inventoryDetail.spare.unit?exists>
	                              <td class="alignLeft">${inventoryDetail.spare.unit.value?if_exists}</td>
	                              <#else>
	                              <td></td>
	                              </#if>
	                              <td class="alignRight">#{inventoryDetail.currentSysStocks?if_exists}</td>
	                              <td class="alignRight"><input type="text" name="inventoryDetail.inventoryNumber" value="#{inventoryDetail.inventoryNum?if_exists}"  class="definedLength"/></td>
	                             </tr>                           
	                           </#list>	                      
	                      </#if>
	                 </#if>
	       </@listTable>
	      <#if !(action.isReadOnly())>	
	      <@buttonBar>
	       <#if toolingDevFlag=='TOOLING'>
	        	<@vbutton class="button" value="${action.getText('new')}" onclick="popupModalDialog('${req.contextPath}/popup/spareSelector.html?toolingDevFlag=TOOLING&inoutFlag='+'T',800,600,addNewSpareValue);"/>
	       <#else>
	       		<@vbutton class="button" value="${action.getText('new')}" onclick="popupModalDialog('${req.contextPath}/popup/spareSelector.html?toolingDevFlag=DEVICE&inoutFlag='+'T',800,600,addNewSpareValue);"/>
	       </#if> 	
	        	<@vbutton class="button" value="${action.getText('delete')}" onclick="deleteSpareValue();"/>
	     </@buttonBar>
	     </#if>
	</@ww.form>
	<script language="JavaScript" type="text/JavaScript">
	
	function open_SpareInventoryPdf(spareInventoryId) {
	  var url='${req.contextPath}/reports/spare/spareInventory.pdf?spareInventoryId=' + spareInventoryId;
	  url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
    }
	
	 window.onload=function(){
	 	<#if (spareInventory.id)?exists>
	   	<#else>
	   		<#if !(action.isReadOnly())>
   				document.forms[0].elements["submitRecord"].disabled="true";
   			</#if>
	       	a = new Date();
			var time=a.format("yyyy-MM-dd");
			document.forms["spareInventory"].elements["spareInventory.inventoryDateTm"].value=time;
		</#if>
		<#if spareInventory.submit>
			document.forms[0].elements["submitRecord"].disabled="true";
		</#if>
	  <#--
	   <#if matchCheckbox=="T">
	     document.getElementById("matchCheckboxbox").checked=true;
	   </#if>
	   -->
	    setBillInfo();
	    <#if storeOldSpareValue?exists>
	       var spareOldValue=decodeURI("${storeOldSpareValue}");
	       if(spareOldValue!=""){
	          var ary=spareOldValue.split(",");
	          for(var i=0;i<ary.length;i=i+2){
	              setSpareValue(ary[i],ary[i+1]);
	          }
	       }
	    </#if>
	 }
	 
	 function setSpareValue(spareId,spareValue){
	   	 var selector = document.getElementsByName("checkbox");
	      var valueselector = document.getElementsByName("inventoryDetail.inventoryNumber");
	       if (selector.length) {
			        for (var i = 1; i < selector.length; i++) {
			             if(selector[i].value==spareId){
			                 valueselector[i-1].value=spareValue;
			             }
			        }
		   }
	}
		 function user_OpenDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler);
	     }	
	 
	     function userSelectorHandler(result) {
	  		document.forms[0].elements["spareInventory.inventoryPeople"].value = result[1];
	  	 }
	  
	  <#--
	  <#if matchCheckbox=="T">
	  --> 
	  	  /* 设置全选checkbox的onclick响应事件 */
	   var checkboxSelector = document.getElementsByName("checkbox");
        	checkboxSelector[0].onclick = callback;
     
     
       /* 设置全选 */   	
    function callback(){
        var checkboxSelector = document.getElementsByName("checkbox");
           for ( var i = 1; i < checkboxSelector.length; i ++) {
               checkboxSelector[i].checked=checkboxSelector[0].checked;
           }
    }
    <#--
    </#if>
    -->
	  	 
	  	 	
	function setBillInfo(){
	   var billInfo="${inventoryBillInfo?if_exists}";
	     if(billInfo!=""){
	        var ary=billInfo.split(",");
	         document.getElementById("spareInventory.inventoryNo").value=ary[0];
              document.getElementById("spareInventory.name").value=ary[1];
	           document.getElementById("spareInventory.inventoryDateTm").value=ary[2];
	            document.getElementById("spareInventory.inventoryPeople").value=ary[3];
	            document.getElementById("spareInventory.comment").value=ary[4];
	        }
	}
	  	 
	  	 function deleteSpareValue(){
	        var ss="${spareInventory.id?if_exists}";
             var selector = document.getElementsByName("checkbox");
              var spareInventoryselector = document.getElementsByName("inventoryDetail.inventoryNumber");
               var sapreInventoryAry="";
               var spareAry="";
               var storeOldSpareValue=new Array();
               var spareInventoryValue=getSpareInventoryInfo();
	            if (selector.length) {
			        for (var i = 1; i < selector.length; i++) {
			            	 if (!selector[i].checked) {
			            	    spareAry+=selector[i].value+",";
			            	     sapreInventoryAry+=selector[i].value+",";
			            	      sapreInventoryAry+=spareInventoryselector[i-1].value+",";
			            	      if(spareInventoryselector[i-1].value!=""){
			            	          storeOldSpareValue.push(selector[i].value);
			            	          storeOldSpareValue.push(spareInventoryselector[i-1].value);
			            	      }
			            	 }
			            }
			    }
               spareAry=spareAry.substring(0,spareAry.lastIndexOf(","));
	           sapreInventoryAry=sapreInventoryAry.substring(0,sapreInventoryAry.lastIndexOf(","));
	           if(deleteMessage()){
	               if(ss==""){
	                 var url=encodeURI("${req.contextPath}/asset/spare/editSpareInventory.html?spareIds="+spareAry+"&billInfo="+spareInventoryValue+"&toolingDevFlag=${toolingDevFlag?if_exists}&matchCheckbox=${matchCheckbox?if_exists}&storeOldSpareValue="+storeOldSpareValue);
	                 window.location=url;
	               }else{  
	                     document.forms[0].elements["spareIds"].value=spareAry; 
		                   document.forms[0].elements["inventorySpareStock"].value=sapreInventoryAry;  
		                    spareInventory.submit();
	              }
	        }
 }
 
          function deleteMessage(){
              var selector = document.getElementsByName("checkbox");
	            var selectNumber=0;
	              if(selector.length){
	                 for (var i = 1; i < selector.length; i++) {
	                     if(selector[i].checked){
	                         selectNumber+=parseInt(1);	         
	                     }
	                }
	          if(parseInt(selectNumber)==0){
	              alert("${action.getText('spareInventory.selectSpare')}");
	               return false;
	          }else if(parseInt(selectNumber)==parseInt(selector.length)-1){
	             alert("${action.getText('spareInventory.deleteAllspare')}");
	               return false;
	          }
	       }
	       return confirmDelete("${action.getText('spareInventory.deleteMessage')}");
        
        
         }
         
         function updateCheckboxStatus(){
            var spareInventoryValue=getSpareInventoryInfo();
             alert(spareInventoryValue);
            if(document.getElementById("matchCheckboxbox").checked){
              document.getElementById("matchCheckbox").value="T";
              <#if spareInventory.id?exists>
                 window.location="${req.contextPath}/asset/spare/editSpareInventory.html?spareInventory.id=${spareInventory.id}&spareIds=${spareIds?if_exists}&billInfo="+spareInventoryValue+"&toolingDevFlag=${toolingDevFlag}&matchCheckbox=T";
              <#else>
                  window.location="${req.contextPath}/asset/spare/editSpareInventory.html?spareIds=${spareIds?if_exists}&billInfo="+spareInventoryValue+"&toolingDevFlag=${toolingDevFlag}&matchCheckbox=T";
              </#if>
            }else{
              <#if spareInventory.id?exists>
                   window.location="${req.contextPath}/asset/spare/editSpareInventory.html?spareInventory.id=${spareInventory.id}&spareIds=${spareIds?if_exists}&billInfo="+spareInventoryValue+"&toolingDevFlag=${toolingDevFlag}&matchCheckbox=F";
              <#else>
                   window.location="${req.contextPath}/asset/spare/editSpareInventory.html?spareIds=${spareIds?if_exists}&billInfo="+spareInventoryValue+"&toolingDevFlag=${toolingDevFlag}&matchCheckbox=F";
              </#if>
            }
         }
  	 
	  	 function addNewSpareValue(result){
	        var ss="${spareInventory.id?if_exists}";
	        var selector = document.getElementsByName("checkbox");
	        var spareInventoryselector = document.getElementsByName("inventoryDetail.inventoryNumber");
            var spareAry="";
            var sapreInventoryAry="";
            var storeOldSpareValue=new Array();
            var spareInventoryValue=getSpareInventoryInfo();
	        if (selector.length) {
			        for (var i = 1; i < selector.length; i++) {
			                spareAry+=selector[i].value+",";
			                sapreInventoryAry+=selector[i].value+",";
			            	sapreInventoryAry+=spareInventoryselector[i-1].value+",";
			            	if(spareInventoryselector[i-1].value!=""){
			            	  storeOldSpareValue.push(selector[i].value);
			            	   storeOldSpareValue.push(spareInventoryselector[i-1].value);
			            	}
			            }
			        result=result.substring(0,result.lastIndexOf(","));
			        if(spareAry!=""){
			            if(!filterMessage(result,spareAry)){
			                   return false;
			               }
			        }
			        var ary=result.split(",");
			        for(i=0;i<ary.length;i++){
			           if(getAddValue(ary[i],spareAry)){
			             spareAry+=ary[i]+",";
			             sapreInventoryAry+=ary[i]+",";
			             sapreInventoryAry+=0+",";
			           }
			        }  
			    } 
	        spareAry=spareAry.substring(0,spareAry.lastIndexOf(","));
	        sapreInventoryAry=sapreInventoryAry.substring(0,sapreInventoryAry.lastIndexOf(","));
	       if(ss==""){
	         var url=encodeURI("${req.contextPath}/asset/spare/editSpareInventory.html?spareIds="+spareAry+"&billInfo="+spareInventoryValue+"&toolingDevFlag=${toolingDevFlag?if_exists}&matchCheckbox=${matchCheckbox?if_exists}&storeOldSpareValue="+storeOldSpareValue+"&readOnly=${req.getParameter('readOnly')?if_exists}");
	         window.location=url;
	       }else{  
	            document.forms[0].elements["spareIds"].value=spareAry; 
		        document.forms[0].elements["inventorySpareStock"].value=sapreInventoryAry;
		        spareInventory.submit();
		   }
	 }
	 
	 function filterMessage(result,spareAry){
	   var ary=result.split(",");
	    for(i=0;i<ary.length;i++){
	       if(!getAddValue(ary[i],spareAry)){
              return confirm("${action.getText('spareInventory.filterSpare')}");
	       } 
	    }
	    return true;
	}
	  
	 function getAddValue(selectorValue,result){
	   result=result.substring(0,result.lastIndexOf(","));
	    var ary=result.split(",");
	     for(var i=0;i<ary.length;i++){
	      if(selectorValue==ary[i]){
	        result+=",";
	        return false;
	       }
	   }
	   result+=",";
	   return true;	  
	}	
	
	function getSpareInventoryInfo(){
	  var ary=new Array();
	   ary.push((document.getElementById("spareInventory.inventoryNo").value).toString());
       	ary.push((document.getElementById("spareInventory.name").value).toString());
	     ary.push((document.getElementById("spareInventory.inventoryDateTm").value).toString());
	      ary.push((document.getElementById("spareInventory.inventoryPeople").value).toString());
	      ary.push((document.getElementById("spareInventory.comment").value).toString());
	      return ary;
	}
	
	function validateInventoryRecord(){
	   var selector = document.getElementsByName("checkbox");
          var spareInventoryselector = document.getElementsByName("inventoryDetail.inventoryNumber");
          if (selector.length) {
			        for (var i = 1; i < selector.length; i++) {
			            	if(spareInventoryselector[i-1].value==""){
			            	   alert("${action.getText('spareInventory.notEmpty')}");
			            	   return false;
			            	}else{
			            	   if(!validateInventoryNumber(spareInventoryselector[i-1].value)){
			            	      return false;
			            	   }
			            	}
			            }
			    } 
	      return true;
	}
	
	function validateInventoryNumber(targetId){
	    if(isNumberBetween(targetId,0,1000000)==-1){
	       alert("${action.getText('spareInventory.notTrue')}");
	       return false;
	    }else{ 
	          if(isNumberBetween(targetId,1000000,0)==0){
	              alert("${action.getText('spareInventory.betweenNumber')}");
	              return false;
	           }
	    }
	    return true;
	
	}
	
	function validateInventoryBill(){
	   /* 验证盘点单 */
	   if(!validateInventoryBillName()){
	      return false;
	   }
	   /* 验证单盘点时间 */
	   if(!validateInventoryTime()){
	      return false;
	   }
	   /*  验证单盘点人 */
	   if(!validateInventoryPeople()){
	      return false;
	   }
	    /*  验证单盘点备注 */
	   if(!validateInventoryComment(0,250)){
	      return false;
	   }
	   return true;
	}
	
	function validateInventoryBillNo(min,max){
	   if(isNotEmpty(spareInventory,"spareInventory.inventoryNo")==true){
	        if(isValidLength(spareInventory,"spareInventory.inventoryNo",min,max)==false){
	           alert("${action.getText('spareInventory.inventoryNoLength')}"+max);
	           return false;
	        }
	     }else{
	          alert("${action.getText('spareInventory.inventoryNoEmpty')}");
	          return false;
	     }
	     return true;
	}
	
	function validateInventoryBillName(){
	   if(isNotEmpty(spareInventory,"spareInventory.name")) {
			if (!isValidLength(document.forms[0],"spareInventory.name",0,50)){
				alert("${action.getText('spareInventory.name.maxlength')}");
				return false;
		    }
		} else {
			alert("${action.getText('spareInventory.name.requiredstring')}");
			return false;
		}		
	     return true;	
	}
	
	function validateInventoryTime(){
		if(isNotEmpty(spareInventory,"spareInventory.inventoryDateTm")) {
			if(!isDate("spareInventory.inventoryDateTm")){
				alert('${action.getText('spareInventory.inventoryDateTm')}'+'${action.getText('dateFormate.error')}');
				return false; 
			}
		}else{
			alert("${action.getText('spareInventory.inventoryDateTm.requiredstring')}");
			return false;
		}
	    return true;	
	}
	
	function validateInventoryPeople(){
	    if(isNotEmpty(spareInventory,"spareInventory.inventoryPeople")==false){
	           alert("${action.getText('spareInventory.inventoryPeople')}");
	           return false;
	        }
	        return true;
	}
	
	function validateInventoryNoRecord(){
	      var selector = document.getElementsByName("checkbox");
	        if(selector.length<=1){			
			    alert("${action.getText('spareInventory.noRecord')}");
			     return false;
		    }
		    return true;
	}
	
	function validateInventoryComment(min,max){
	   if(isNotEmpty(spareInventory,"spareInventory.comment")==true){
	        if(isValidLength(spareInventory,"spareInventory.comment",min,max)==false){
	           alert("${action.getText('spareInventory.commentLength')}"+max);
	           return false;
	        }
	     }
	     return true;
	}
	
		function updateSpareIn(){
		   var selector = document.getElementsByName("checkbox");
            var spareInventoryselector = document.getElementsByName("inventoryDetail.inventoryNumber");	    
			var spareAry = new Array();
			var sapreInventoryAry=new Array();
			/* 验证记录 */
			if(!validateInventoryRecord()){
			   return false;
			}
			/*  验证盘点单 */
			if(!validateInventoryBill()){
			   return false;
			}
	    <#--
		<#if matchCheckbox=="T">
		-->
			if (selector.length) {
			        for (var i = 1; i < selector.length; i++) {
			            	spareAry.push(selector[i].value);
			            	sapreInventoryAry.push(selector[i].value);
			            	sapreInventoryAry.push(spareInventoryselector[i-1].value);
			            }
			    } 
		      document.forms[0].elements["spareIds"].value=spareAry; 
		      document.forms[0].elements["inventorySpareStock"].value=sapreInventoryAry;
		      <#--
	           </#if>
	           -->
		      return true;
		}
	
	</script>
</@htmlPage>