<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('sapreInBill.title')}">
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
	 <@ww.form name="'spareInBill'" action="'saveSpareIn'" method="'post'">
	 	  <@ww.token name="saveSpareInToken"/>
	 	  <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	 	  <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	  <#if spareInBill.id?exists>
	 	  <@ww.hidden name="'reportId'" value="#{spareInBill.id?if_exists}"/>
	 	  </#if>
	 	  <#assign itemNo = 1/> 
	 	   <#assign sum = 1/> 
	 	  <@inputTable>
	 	 	<@ww.hidden name="'spareIdAndValueString'" value="'${spareIdAndValueString?if_exists}'"/>
	 	  	<@ww.hidden name="'spareIdString'" value="'${spareIdString?if_exists}'"/>
	 	  	<@ww.hidden name="'popupPage'" value="'${popupPage?if_exists}'"/>
	 	  <#if spareInBill.id?exists>
	 	  <@ww.hidden name="'spareInBill.id'" value="#{spareInBill.id?if_exists}"/>
	 	  </#if>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('spareInBill.billNo')}'" name="'spareInBill.billNo'" value="'${spareInBill.billNo?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
	 	  		<@ww.textfield label="'${action.getText('spareInOutBill.name')}'" name="'spareInBill.name'" value="'${spareInBill.name?if_exists}'" cssClass="'underline'" required="true"/>
	 	    </tr>
	 	  	<tr> 
	 	  		<@pp.datePicker label="'${action.getText('spareInBill.inoutDateTm')}'" name="'spareInBill.inoutDateTm'" value="'${(spareInBill.inoutDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="true"/>
	 	  		<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('spareInBill.inoutPeople')}:</label></td>
		 		<td>
		 		<#assign inPepole=''/> 
		 		<#if spareInBill.inoutPeople?exists>
		 		<#assign inPepole="${spareInBill.inoutPeople}"> 
		 		<#else>
		 		<#assign inPepole="${loginUser.name}"> 
		 		</#if>
		 			<input type="text" name="spareInBill.inoutPeople" class="underline"  value="${inPepole}"  maxlength="150" readonly="true"/>
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
	 	  	</tr>
	       	<tr>
	           <@ww.textarea label="'${action.getText('spareInBill.comment')}'" name="'spareInBill.comment'" value="'${spareInBill.comment?if_exists}'" cssClass="'underline'" required="false" rows="3" cols="50"/>
	        </tr>
		 </@inputTable> 
	 	  <@buttonBar>
	 	     <#if !(action.isReadOnly())>	
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return updateSpareIn();'" />  
	         </#if>
	        	<#if popupPage!='T'>
	        		<#if toolingDevFlag=='TOOLING'>
	        	    	<@redirectButton  value="${action.getText('back')}" url="listSpares.html?toolingDevFlag=TOOLING&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	        	    <#else>
	        	     	<@redirectButton  value="${action.getText('back')}" url="listSpares.html?toolingDevFlag=DEVICE&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	        	    </#if>
	        	</#if>
	        	<#if !(action.isReadOnly())>	
	        		<@vsubmit name="'submitRecord'" value="'${action.getText('sendsubmit')}'" />
	        	</#if>   	
	        	<#if spareInBill.id?exists>
	        	  <@vbutton name="print"  class="button" value="${action.getText('print')}" onclick="open_SpareInPdf('#{spareInBill.id}')"/>
	        	</#if>
	     </@buttonBar>
	     <@titleBar title="${action.getText('sapres.list')}"/>
	     <@listTable>
	        	     <tr>
	        	         <th><input type="checkbox" name="checkbox"  value=""></th>
	                     <th>${action.getText('spareInBill.serial')}</th>
	                     <th>${action.getText('spareInBill.spareNo')}</th>
	                     <th>${action.getText('spareInBill.name')}</th>
	                     <th>${action.getText('spareInBill.enname')}</th>
	                     <th>${action.getText('spareInBill.modelSpecs')}</th>
	                     <th>${action.getText('spareInBill.unit')}</th>
	                     <th>${action.getText('spareInBill.category')}</th>
	                     <th>${action.getText('spareInBill.number')}</th>
	                     <th>${action.getText('spareInBill.unit_price')}</th>
	                     <th>${action.getText('spareInBill.sumFee')}</th>
	                 </tr>
	               <#if !historyValue?exists>
	                   <#if (spares.size()>0)>
		                  <#list spares as spareDetail>
		                       <tr>
		                          <td><input type="checkbox" name="checkbox"  value="#{spareDetail.id}"/></td>
		                          <td class="alignCenter">#{itemNo}</td>
		                          <#assign itemNo=itemNo+1 />
		                          <td class="alignLeft">${spareDetail.spareNo?if_exists}<input type="hidden" name="spareDetail.spareNo" value="${spareDetail.spareNo?if_exists}"  /></td>
		                          <td class="alignLeft">${spareDetail.name?if_exists}</td>
		                          <td class="alignLeft">${spareDetail.enName?if_exists}</td>
		                          <td class="alignLeft">${spareDetail.modelSpecs?if_exists}</td>
		                       <#if spareDetail.unit?exists>   
		                          <td class="alignLeft">${spareDetail.unit.value?if_exists}</td>
		                       <#else>
		                          <td></td>   
		                       </#if>
		                       <#if spareDetail.category?exists>
		                          <td class="alignLeft">${spareDetail.category.value?if_exists}</td>
		                       <#else>
		                          <td></td>
		                       </#if>
		                          <#if spareDetail.number?exists>
		                          <td class="alignRight">#{spareDetail.number?if_exists}</td>
		                          <#else>
		                          <td class="alignLeft">
		                            <input type="text" name="spareDetail.value" value=""  class="definedLength"  onchange="changValue()"/>
		                            <@ww.hidden name="'spareDetail.hiddenvalue'" value=""/>
		                          </td>
		                          </#if>
		                          <td class="alignRight">#{spareDetail.unitPrice?if_exists}<input type="hidden" name="spareDetail.unitPrice" value="#{spareDetail.unitPrice?if_exists}"  class="underline" disabled="true" readonly="true" /></td>
		                          <td class="alignRight"><input type="text" name="spareInBill.sumFee" value=""  class="noBorderLine" disabled="true" readonly="true"/></td>
		                      </tr>
		                 </#list>
		             </#if>
		        <#else>
		             <#if (spareInBill.spareInOutHistory.size()>0)>
		                <#list spareInBill.spareInOutHistory as spareDetail>
		                     <tr>
		                          <td><input type="checkbox" name="checkbox"  value="#{spareDetail.spare.id}"/></td>
		                          <td class="alignCenter">#{itemNo}</td>
		                          <#assign itemNo=itemNo+1 />
		                          <td class="alignLeft">${spareDetail.spare.spareNo?if_exists}<input type="hidden" name="spareDetail.spareNo" value="${spareDetail.spare.spareNo?if_exists}"  /></td>
		                          <td class="alignLeft">${spareDetail.spare.name?if_exists}</td>
		                          <td class="alignLeft">${spareDetail.spare.enName?if_exists}</td>
		                          <td class="alignLeft">${spareDetail.spare.modelSpecs?if_exists}</td>
			                      <#if spareDetail.spare.unit?exists>
		                          	  		<td class="alignRight">${spareDetail.spare.unit.value?if_exists}</td>
		                          <#else>
			                          <td></td>
			                      </#if>
			                      <#if spareDetail.spare.category?exists>
			                          <td class="alignLeft">${spareDetail.spare.category.value?if_exists}</td>
			                      <#else>
			                          <td></td>
			                      </#if>
		                      	  
		                          <#assign sum=sum*spareDetail.number/> 
		                          <td class="alignRight">
		                          <input type="text" name="spareDetail.value" value="#{spareDetail.number?if_exists}"  class="definedLength"  onchange="changValue()"/>
		                          <@ww.hidden name="'spareDetail.hiddenvalue'" value="'#{spareDetail.number?if_exists}'"/>
		                          </td>
		                          <#assign sum=sum*spareDetail.spare.unitPrice/> 
		                          <td class="alignRight"><input type="text" name="spareDetail.unitPrice" value="#{spareDetail.spare.unitPrice?if_exists}"  class="noBorderLine" disabled="true" readonly="true" /></td>                        
		                          <td class="alignRight"><input type="text" name="spareInBill.sumFee" value="#{sum}"  class="noBorderLine" disabled="true" readonly="true"/></td>
		                          <#assign sum=1 />
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
	       <#if !historyValue?exists> 
	            <input type="button" class="button" name="delete" value="${action.getText('delete')}" onclick="deleteSpareValue()" <#if (spares.isEmpty())>disabled</#if> />
	       <#else>
	            <input type="button" class="button" name="delete" value="${action.getText('delete')}" onclick="deleteSpareValue()" <#if (spareInBill.spareInOutHistory.isEmpty())>disabled</#if> />
	       </#if>	
	     </@buttonBar>
	     </#if>
	</@ww.form>
	<script language="JavaScript" type="text/JavaScript">
	
	window.onload=function(){
	<#if (spareInBill.id)?exists>
   	<#else>
   		<#if !(action.isReadOnly())>
   			document.forms[0].elements["submitRecord"].disabled="true";
   		</#if>
       	a = new Date();
		var time=a.format("yyyy-MM-dd");
		document.forms["spareInBill"].elements["spareInBill.inoutDateTm"].value=time;
	</#if>
	
	<#if spareInBill.submit>
		document.forms[0].elements["submitRecord"].disabled="true";
	</#if>
	
	   var selector = document.getElementsByName("checkbox");
	    var valueselector = document.getElementsByName("spareDetail.value"); 
	     var priceselector = document.getElementsByName("spareDetail.unitPrice"); 
	       setBillInfo();
	      if (selector.length) {
	             for (var i = 1; i < selector.length; i++){
	               valueselector[i-1].value=delMaxValue(valueselector[i-1].value);
	                priceselector[i-1].value=delMaxValue(priceselector[i-1].value);
	             }
	      }
	    <#if storeOldSpareValue?exists>
	       var spareOldValue="${storeOldSpareValue}";
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
	      var valueselector = document.getElementsByName("spareDetail.value");
	       if (selector.length) {
			        for (var i = 1; i < selector.length; i++) {
			             if(selector[i].value==spareId){
			                 valueselector[i-1].value=spareValue;
			             }
			        }
		   }
	}
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
	function setBillInfo(){
	   var billInfo=decodeURI("${billInfo?if_exists}");
	     if(billInfo!=""){
	        var ary=billInfo.split(",");
	         getObjByNameRe("spareInBill.billNo").value=ary[0];
	         getObjByNameRe("spareInBill.name").value=ary[1];
              getObjByNameRe("spareInBill.inoutDateTm").value=ary[2];
	           getObjByNameRe("spareInBill.inoutPeople").value=ary[3];
	            getObjByNameRe("spareInBill.comment").value=ary[4];
	        }
	}
	function delMaxValue(inputValue){
	   var ary=inputValue.split(",");
	     var returnValue="";
	      for(var i=0;i<ary.length;i++){
            returnValue+=ary[i];
	    }
	      return returnValue;
	}
	
	function checkInStockData(){
	  var selector = document.getElementsByName("checkbox");
	  if(selector.length<=1){
	     return false;
	  }
	     return true;
	}
	
	function changValue(){
	var number;
	var unitPrice;
	var sumFee;
	var selector = document.getElementsByName("checkbox");
	var unitSelector=document.getElementsByName("spareDetail.unitPrice");
	var valueselector = document.getElementsByName("spareDetail.value");
	var feeSelector = document.getElementsByName("spareInBill.sumFee");
	if (selector.length) {
	        for (var i = 1; i < selector.length; i++) {
	                if(valueselector[i-1].value!=""){
	                if(isNumber(valueselector[i-1].value)==-1){
	            	      alert("${action.getText('spareInBill.notNumber')}");
	            	       return false;
	            	         }
	            	    else if(isNumber(valueselector[i-1].value)==0){
	            	       alert("${action.getText('spareInBill.inputNumber')}");
	            	         return false;
	            	    }else
	            	        {
	            	             number=valueselector[i-1].value;
	            	             unitPrice=unitSelector[i-1].value;
	            	             sumFee=number*unitPrice;
	            	             feeSelector[i-1].value=sumFee;
	            	       }
	            	}
	            }
	    } 
	}
	
	function validateTime(){
		if(document.forms[0].elements["spareInBill.inoutDateTm"].value ==""){
			alert("${action.getText('spareInBill.nullMessage')}");
			return false;
		} 
        var   s   =document.forms[0].elements["spareInBill.inoutDateTm"].value;
        if(!(s=='')){
        		var regu="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        		var re =new RegExp(regu);
        		if (!re.test(s)){
        			alert("${action.getText('please.input.spareInBill.inoutDateTm')}");
        			return false;
        		}
        		else{
        		   if(!validateProcTime(s)){
        		     alert("${action.getText('please.input.spareInBill.inoutDateTm')}");
        		      return false;
        		   }
        		}
        	}
		     return true;
	}
	
	  function validateProcTime(validateTime){
                    var timeYear=validateTime.substring(0,validateTime.indexOf('-')).length;
                    var timeDay=validateTime.substring(validateTime.lastIndexOf('-')+1,validateTime.length).length;
                    if(timeYear>4)
                    {
                        return false;
                    }
                    if(timeDay>2)
                    {
                        return false;
                    }
                    return true;

     }
	
	
	function deleteSpareValue(){
	  var spareInBillId="${spareInBill.id?if_exists}";
      var selector = document.getElementsByName("checkbox");
      var valueselector = document.getElementsByName("spareDetail.value");
      var valueHiddenselector = document.getElementsByName("spareDetail.hiddenvalue");
      var result="";
      var valueAry="";
      var storeOldSpareValue=new Array();
      var returnValue=transformInStockValue();
 		if (selector.length) {
		        for (var i = 1; i < selector.length; i++) {
		          if (!selector[i].checked) {
		             if(valueselector[i-1].value==""||!isNumberBetweenBoolen(valueselector[i-1].value,100000,0)){
		                 valueselector[i-1].value=valueHiddenselector[i-1].value;
		                
		              }
		        	   result+=selector[i].value+",";
		        	   valueAry+=selector[i].value+",";
		        	   valueAry+=valueselector[i-1].value+",";
		        	   if(valueselector[i-1].value!=""){
		        	   storeOldSpareValue.push(selector[i].value);
		        	   storeOldSpareValue.push(valueselector[i-1].value);
		        	  }
		         }
       }
	}
     result=result.substring(0,result.lastIndexOf(","));
     valueAry=valueAry.substring(0,valueAry.lastIndexOf(","));
     if(deleteStockValue()){
	    if(spareInBillId==""){
	       window.location=encodeURI("${req.contextPath}/asset/spare/editSpareIn.html?spareIds="+result+"&billInfo="+returnValue+"&toolingDevFlag=${toolingDevFlag?if_exists}&storeOldSpareValue="+storeOldSpareValue);
	    }else{    
		     document.forms[0].elements["spareIdString"].value=result; 
		     document.forms[0].elements["spareIdAndValueString"].value=valueAry;
		     spareInBill.submit();
	    }
	}
 }
	
	 /*  删除时判断值的记录  */
	function deleteStockValue(){
	   var selector = document.getElementsByName("checkbox");
	    var selectNumber=0;
	     if(selector.length){
	          for (var i = 1; i < selector.length; i++) {
	             if(selector[i].checked){
	                selectNumber+=parseInt(1);	         
	             }
	          }
	          if(parseInt(selectNumber)==0){
	             alert("${action.getText('spareInBill.selectspareInBill')}");
	               return false;
	          }
	          else if(parseInt(selectNumber)==parseInt(selector.length)-1){
	             <#if spareInBill.id?exists>
	                 alert("${action.getText('spareInBill.deleteNoStockData')}");
	                 return false;
	             </#if>
	          }
	       }
	       return confirmDelete("${action.getText('spares.deletecomfirmMessage')}");
	}
	
	function transformInStockValue(){
	  var ary=new Array();
	   ary.push(getObjByNameRe("spareInBill.billNo").value);
	   ary.push(getObjByNameRe("spareInBill.name").value);
       	ary.push(getObjByNameRe("spareInBill.inoutDateTm").value);
	         ary.push(getObjByNameRe("spareInBill.inoutPeople").value);
	            ary.push(getObjByNameRe("spareInBill.comment").value);
	              return ary;
	}
	
   function addNewSpareValue(result){
    if(result==null){
       return ;
    }
	   var spareInBillId="${spareInBill.id?if_exists}";
	   var selector = document.getElementsByName("checkbox");
	   var valueselector = document.getElementsByName("spareDetail.value");
	   var valueHiddenselector = document.getElementsByName("spareDetail.hiddenvalue");
       var valueAry="";
       var spareAry="";
       var storeOldSpareValue=new Array();
       var returnValue=transformInStockValue();
	  if (selector.length) {
			        for (var i = 1; i < selector.length; i++) {
			               if(valueselector[i-1].value==""||!isNumberBetweenBoolen(valueselector[i-1].value,100000,0)){
			                  valueselector[i-1].value=valueHiddenselector[i-1].value;
			               }
			                spareAry+=selector[i].value+",";
			                valueAry+=selector[i].value+",";
			            	valueAry+=valueselector[i-1].value+",";
			            	if(valueselector[i-1].value!=""){
			            	  storeOldSpareValue.push(selector[i].value);
			            	   storeOldSpareValue.push(valueselector[i-1].value);
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
			             valueAry+=ary[i]+",";
			             valueAry+=1+",";
			           }
			        }  
			    } 
	 valueAry=valueAry.substring(0,valueAry.lastIndexOf(","));
	 spareAry=spareAry.substring(0,spareAry.lastIndexOf(","));
	  if(spareInBillId==""){
	    window.location=encodeURI("${req.contextPath}/asset/spare/editSpareIn.html?spareIds="+spareAry+"&billInfo="+returnValue+"&toolingDevFlag=${toolingDevFlag?if_exists}&storeOldSpareValue="+storeOldSpareValue+"&readOnly=${req.getParameter('readOnly')?if_exists}");
	 }else{
	    document.forms[0].elements["spareIdString"].value=spareAry; 
		document.forms[0].elements["spareIdAndValueString"].value=valueAry;  
		spareInBill.submit();
	 }
	}
	function filterMessage(result,spareAry){
	   var ary=result.split(",");
	    for(i=0;i<ary.length;i++){
	       if(!getAddValue(ary[i],spareAry)){
              return confirm("${action.getText('spareInBill.filterSpare')}");
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
	 function user_OpenDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler);
	 }	
	 
	function userSelectorHandler(result) {
	  		document.forms[0].elements["spareInBill.inoutPeople"].value = result[1];
	}
	  	
	function checkInputState(){
	        var selector = document.getElementsByName("checkbox");
	        var valueselector = document.getElementsByName("spareDetail.value");
	        if (selector.length) {
			        for (var i = 1; i < selector.length; i++) {
			            	if(valueselector[i-1].value==""){
			            	   return false;
			            	}
			            }
			    } 
			    return true;
	}
	function checkInputNumber(){
	      var selector = document.getElementsByName("checkbox");
	        var valueselector = document.getElementsByName("spareDetail.value");
	          var spareNoselector = document.getElementsByName("spareDetail.spareNo");
	        if (selector.length) {
			        for (var i = 1; i < selector.length; i++) {
			            	if(isNumber(valueselector[i-1].value)==-1){
			            	   alert("${action.getText('spareInBill.spare')}"+spareNoselector[i-1].value+"${action.getText('spareInBill.notNumber')}");
			            	   return false;
			            	}else if(isNumber(valueselector[i-1].value)==0){
			            	   alert("${action.getText('spareInBill.spare')}"+spareNoselector[i-1].value+"${action.getText('spareInBill.inputNumber')}");
			            	   return false;
			            	}else{
			            	    return true;
			            	  }
			            }
			    } 
			    return true;
	}
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
	function updateSpareIn(){
	             /*判断是否有备件*/
	        if(!checkInStockData()){
	            alert("${action.getText('spareInBill.selectStockData')}");
	            return false;
	        }
	           /*判断是备件数量*/
	        if(!checkInputState()){
	           alert("${action.getText('spareInBill.checkInputState')}");
	           return false;
	        }
	         /*判断是备件形式是否正确*/
	        if(!checkInputNumber()){
	           return false;
	        }
	        /* 判断入库单名称 */
	        if(isNotEmpty(spareInBill,"spareInBill.name")) {
				if (!isValidLength(document.forms[0],"spareInBill.name",0,50)){
					alert("${action.getText('spareInBill.name.maxlength')}");
					return false;
			    }
			} else {
				alert("${action.getText('spareInBill.name.requiredstring')}");
				return false;
			}
	        
	        
	        /*判断是时间*/  
	         
	          if(!validateTime()){
	           return false;
	        }
	        
	        /*入库入*/
	        	        
	        if(document.forms[0].elements["spareInBill.inoutPeople"].value==""){
	           alert("${action.getText('spareInBill.inoutPeople.required')}");
	           return false;
	        }
	        /* 备注 */
	        if(document.forms[0].elements["spareInBill.comment"].value.length>150){
	              alert("${action.getText('spareInBill.comment.maxLength')}");
	               return false;
	           }
            var selector = document.getElementsByName("checkbox");
            var valueselector = document.getElementsByName("spareDetail.value");
			if (!selector) {
			            alert("${action.getText('spareInBill.notInStock')}");
				        return false;
				    }			    
			var ary = new Array();
			var valueAry=new Array();
			if (selector.length) {
			        for (var i = 1; i < selector.length; i++) {
			            	ary.push(selector[i].value);
			            	valueAry.push(selector[i].value);
			            	valueAry.push(valueselector[i-1].value);
			            }
			    } 
		      document.forms[0].elements["spareIdString"].value=ary; 
		      document.forms[0].elements["spareIdAndValueString"].value=valueAry; 
		      return true;
	}
	function open_SpareInPdf(spareInBillId) {
	  var url='${req.contextPath}/reports/spare/spareInBill.pdf?spareInBillId=' + spareInBillId;
	  url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
    }
	</script> 
</@htmlPage>