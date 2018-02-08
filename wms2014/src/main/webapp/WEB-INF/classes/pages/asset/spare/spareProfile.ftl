<#include "../../includes/eam2008.ftl" />
<#include "../commonSpareType.ftl"/>

<style>
.extendInputLength{
    border-width: 1px;
    border-style: solid;
    border-color: white white black;
    width:70%;
    }
</style>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/loadSpareByPamrameterJs.js'></script>
<@htmlPage title="${action.getText('spare.title')}">
	 <@ww.form name="'spare'" action="'saveSpares'" method="'post'" validate="true">
	 	<@ww.token name="saveSparesToken"/>
	 	<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	 	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	  <@inputTable>
	 	  <#if spare.id?exists>
	 	     <@ww.hidden name="'spare.id'" value="#{spare.id?if_exists}"/>
	 	  </#if>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('spare.code')}'" name="'spare.spareNo'" value="'${spare.spareNo?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
	 	  		<@ww.textfield label="'${action.getText('spare.name')}'"  name="'spare.name'" value="'${spare.name?if_exists}'" cssClass="'underline'" required="true"/>
	 	  	</tr>
	 	  	<tr>
	 	  	  <#--
	 	  		<@ww.textfield label="'${action.getText('spare.en_name')}'" name="'spare.enName'" value="'${spare.enName?if_exists}'" cssClass="'underline'" />
	 	  	  -->
	 	  		<@ww.textfield label="'${action.getText('spare.modelSpecs')}'" name="'modelSpecs'" value="'${spare.modelSpecs?if_exists}'" cssClass="'underline'" required="true"/>
	 	  		<@ww.select label="'${action.getText('spare.category')}'"
	                    	name="'category.id'"
	                    	id="'category.id'"
	                   	 	listKey="id" 
	                    	listValue="name"
	                    	list="spareType" 
	                    	emptyOption="true" 
	                    	disabled="false"
	                    	required="true">
	                        <#if spare.category?exists>
                              <@ww.param name="'value'"  value="'#{spare.category.id?if_exists}'" />
                            <#else>
                              <@ww.param name="'value'"  value="" />
                            </#if>
                </@ww.select>
	 	  	</tr>
	 	  	<#--
	 	  	<tr>	
	 	  		<@ww.select label="'${action.getText('spare.category')}'"
	                    	name="'category.id'"
	                    	id="'category.id'"
	                   	 	listKey="id" 
	                    	listValue="name"
	                    	list="spareType" 
	                    	emptyOption="true" 
	                    	onchange="'spareTypeValueChange(\"category.id\",\"spareDetailType.id\")'"
	                    	disabled="false"
	                    	required="true">
	                        <#if spare.category?exists>
                              <@ww.param name="'value'"  value="'#{spare.category.id?if_exists}'" />
                            <#else>
                              <@ww.param name="'value'"  value="" />
                            </#if>
                </@ww.select>
                <@ww.select label="'${action.getText('spare.spareDetailType')}'"
	                    	name="'spareDetailType.id'"
	                    	id="'spareDetailType.id'"
	                   	 	listKey="id" 
	                    	listValue="name"
	                    	list="spareDetailType" 
	                    	emptyOption="true" 
	                    	disabled="false"
	                    	required="true">
	                        <#if spare.spareDetailType?exists>
                              <@ww.param name="'value'"  value="'#{spare.spareDetailType.id?if_exists}'" />
                            <#else>
                              <@ww.param name="'value'"  value="" />
                            </#if>
                </@ww.select>
	 	  	</tr>
	 	  	-->
	 	  	<tr> 	
	 	  	    <@ww.select label="'${action.getText('spare.unit')}'"
	                       name="'unit.id'"
	                   	   listKey="id" 
	                       listValue="value"
	                       list="SpareUnitType" 
	                       emptyOption="true" 
	                       disabled="false">
	                       <#if spare.unit?exists>
                              <@ww.param name="'value'"  value="'#{spare.unit.id?if_exists}'" />
                             <#else>
                              <@ww.param name="'value'"  value="" />
                          </#if>
	           </@ww.select>   
	           <#--
	           <@ww.textfield label="'${action.getText('spare.graphNo')}'" name="'spare.graphNo'" value="'${spare.graphNo?if_exists}'" cssClass="'underline'" />  
	           -->
	           <@ww.textfield label="'单价(元)'" name="'spare.unitPrice'" value="'${(spare.unitPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" />  
	 	  	</tr>
	 	  	<tr>
	 	  	   <@ww.textfield label="'${action.getText('spare.safe_stock')}'" name="'spare.safeStock'" value="'${spare.safeStock?if_exists}'" cssClass="'underline'" required="false"   />
	           <@ww.hidden name="'oldSpareSafeStock'" value="'${spare.safeStock?if_exists}'"/>

	           <@ww.textfield label="'${action.getText('spare.stocks')}'" name="'spare.stocks'" value="'${spare.stocks?if_exists}'" cssClass="'underline'" required="false"   />
	 	  	</tr>
	 	  	<tr>
	 	  	  <@ww.textfield label="'${action.getText('spare.orderNo')}'" name="'spare.orderNo'" value="'${spare.orderNo?if_exists}'" cssClass="'underline'" required="false"   />
	 	  	  <@factorySelector required="false" validateJs="validate"/>
	 	  	</tr>
	 	  	<tr>
	 	  	  	   <td align="right" valign="top">
	                   <span class="required"></span><label class="label">${action.getText('supplier.no')}:</label>
	                </td>
	                <td>
	                <#if supplier?exists>
	                    <input type="text" name="supplier.supplierNo" class="underline"  value="${supplier.supplierNo?if_exists}"  maxlength="150" disabled/>
		            <#else>
		                <input type="text" name="supplier.supplierNo" class="underline"  value=""  maxlength="150" disabled/>
		            </#if>
		            <a onClick='eam2008_supplier_OpenDialog("${req.contextPath}/popup/supplierSelector.html")'>
		               <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		            </a>
		           <#if supplier?exists>
		               <#assign supplierName ="${supplier.name?if_exists}" />
		           <#else>
		               <#assign supplierName ="" />
		           </#if>
		            <input type="text" style="border:0px" name="supplier.name" disabled value="${supplierName}" />
	 	          </td> 	        
	              <#if supplier?exists>
	 	              <@ww.hidden name="'supplier.id'" value="'#{supplier.id?if_exists}'"/>
	              <#else>
		              <@ww.hidden name="'supplier.id'" value=""/>
	              </#if>
	 	  	   <@ww.textfield label="'${action.getText('spare.purchase_cycle')}'" name="'spare.purchaseCycle'" value="'${spare.purchaseCycle?if_exists}'" cssClass="'underline'" required="false"  />
	           <@ww.hidden name="'oldSpareStock'" value="'${spare.stocks?if_exists}'"/>
	 	  	</tr>
	        <tr>
	        	<td align="right" valign="top"><label  for="saveSpares_spare.unit" class="label">${action.getText('spare.producingAreaType')}:</label></td>
	            <td>
		            <select name="spare.producingAreaType" id="saveSpares_spare.producingAreaType">
		             <option value=""></option>
		             <#list producingAreaType as productingAreaTypes>
		              <option value="${productingAreaTypes.value}">${productingAreaTypes.label}</option>
		             </#list>
		             </select>
	            </td>
		         <td align="right" valign="top">
		         	<label  for="saveSpares_spare.originalHabitat" class="label">${action.getText('spare.originalHabitat')}:</label>
		         </td>
		         <td>
		         	<input type="text" name="spare.originalHabitat" value="${spare.originalHabitat?if_exists}"   id="saveSpares_spare.originalHabitat" class="underline"  />
		         </td>
	        </tr>
	        <tr>
	        	<td align="right" valign="top">
                   <span class="required">*</span><label class="label">${action.getText('spare.toolingDevFlag')}:</label>
                </td>
                <td>
                	<select name="spare.toolingDevFlag" id="spare.toolingDevFlag">
                		<option value="TOOLINGDEVICE"></option>
                		<option value="TOOLING">${action.getText('toolingDevFlag.TOOLING')}</option>
                		<option value="DEVICE">${action.getText('toolingDevFlag.DEVICE')}</option>
                	</select>
                </td>
                <@ww.select label="'${action.getText('spare.propertyType')}'" 
	                     required="false" 
	                     name="'spare.propertyType'" 
	    			     listKey="value" 
	    			     listValue="label"
	                     list="spareProperty" 
	                     emptyOption="true" 
	                     disabled="false">
	                     <#if spare.propertyType?exists>
	                          <@ww.param name="'value'"  value="'${spare.propertyType?if_exists}'" />
	                     </#if>
	            </@ww.select>
            </tr>
            <tr>
	        	<td align="right" valign="top"><label class="label">${action.getText('spare.tenderPartFlag')}:</label></td>	
	    		  <td>
	    		  	<select name="spare.tenderPartFlag">
		     		<option value="N">${action.getText('no')}</option>
		     		<option value="Y">${action.getText('yes')}</option>
			      </select>
			      </td>
		          <script language="javascript">
		            <#if (spare.tenderPartFlag)>
		         	  document.forms[0].elements["spare.tenderPartFlag"].value = 'Y';
		         	<#else>
		         	  document.forms[0].elements["spare.tenderPartFlag"].value = 'N';
		         	</#if>
	              </script>
	              
		    	<td align="right" valign="top"><label class="label">${action.getText('spare.wearingPartFlag')}:</label></td>	
	    		  <td>
	    		  	<select name="spare.wearingPartFlag">
		     		<option value="N">${action.getText('no')}</option>
		     		<option value="Y">${action.getText('yes')}</option>
			      </select>
			      </td>
		          <script language="javascript">
		            <#if (spare.wearingPartFlag)>
		         	  document.forms[0].elements["spare.wearingPartFlag"].value = 'Y';
		         	<#else>
		         	  document.forms[0].elements["spare.wearingPartFlag"].value = 'N';
		         	</#if>
	              </script>
	        </tr>
	        <tr>
	        	  <td align="right" valign="top"><label class="label">${action.getText('spare.heavyRepairPartFlag')}:</label></td>	
	    		  <td>
	    		  	<select name="spare.heavyRepairPartFlag">
		     		<option value="N">${action.getText('no')}</option>
		     		<option value="Y">${action.getText('yes')}</option>
			      </select>
			      </td>
		          <script language="javascript">
		            <#if (spare.heavyRepairPartFlag)>
		         	  document.forms[0].elements["spare.heavyRepairPartFlag"].value = 'Y';
		         	<#else>
		         	  document.forms[0].elements["spare.heavyRepairPartFlag"].value = 'N';
		         	</#if>
	              </script>
	              <@ww.textfield label="'${action.getText('spare.requestNum')}'" name="'spare.requestNum'" value="'${spare.requestNum?if_exists}'" cssClass="'underline'" required="false"  disabled="true" />
	        </tr>
	        <tr>
	        	 <@ww.textfield label="'${action.getText('spare.purchaseNumInRoad')}'" name="'spare.purchaseNumInRoad'" value="'${spare.purchaseNumInRoad?if_exists}'" cssClass="'underline'" disabled="true"  />
	             <@ww.textfield label="'${action.getText('spare.planUseNumInFeature3M')}'" name="'spare.planUseNumInFeature3M'" value="'${spare.planUseNumInFeature3M?if_exists}'" cssClass="'underline'" disabled="true" />
	        </tr>
	        <tr> 
	    		<@ww.textarea label="'${action.getText('spare.content')}'" name="'spare.comment'" value="'${spare.comment?if_exists}'" cssClass="'underline'" required="false" rows="3" cols="50"/>
	        </tr>
		 </@inputTable> 
	 	  <@buttonBar>
	 	  <#if !(action.isReadOnly())>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	     </#if>
	         <#if spare.enabled?exists>
	           <#if !(spare.enabled)>
	             <@vsubmit name="'enabled'" value="'${action.getText('enabled')}'" />
	           </#if>
	         </#if>	
	         <#if toolingDevFlag ? exists>
			 <#if toolingDevFlag=='TOOLING'>	        	
	        	<@redirectButton  name="back" value="${action.getText('back')}" url="listSpares.html?toolingDevFlag=TOOLING&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	         <#elseif toolingDevFlag=='DEVICE'>
	         	<@redirectButton  name="back" value="${action.getText('back')}" url="listSpares.html?toolingDevFlag=DEVICE&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	         <#else>
	            <@redirectButton  name="back" value="${action.getText('back')}" url="listSpares.html?toolingDevFlag=TOOLINGDEVICE&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	         </#if>
	         <#else>
	            <@redirectButton  name="back" value="${action.getText('back')}" url="listSpares.html?toolingDevFlag=TOOLINGDEVICE&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	     	</#if>
	      <#--<#if spare.id?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('print')}" onclick="open_toolingSparePdf('#{spare.id}')"/>
	      </#if>-->
	     </@buttonBar>
	</@ww.form>
	
	
    <script language="JavaScript" type="text/JavaScript">
    /*
     *当该备件失效,页面所有控件,除了有效、返回、打印按钮，其他都失效
    */
    <#if spare.enabled?exists>
	  <#if !(spare.enabled)>
	    __disableAllElements__(document.forms[0], new Array("enabled", "back", "print"));
	  </#if>
	</#if>	
     window.onload = function () {
        <#if spare.id?exists>
			var url = '${req.contextPath}/spareLocation/listSpareDetail.html?spare.id=#{spare.id}';
		    document.all.frame.src = url;
			document.getElementById("spareDetail").className = "selectedtab";
		</#if>
     	<#--<#if spare.id?exists>
     		var url = '${req.contextPath}/asset/spare/listSpareDocs.html?spare.id=#{spare.id}';
		    document.all.frame.src= url;
		    document.getElementById("spareDoc").className = "selectedtab";
     	</#if>-->
     	 var selector = document.all("spare.toolingDevFlag");
         var groups=selector.options.length;  
         <#if spare.toolingDevFlag?exists>
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${spare.toolingDevFlag?if_exists}"){
               selector.options[i].selected="true";
            }
         }
         <#else>
	         <#if toolingDevFlag?exists>
     	         for (var i=0; i<groups; i++){
		            if (selector.options[i].value=="${toolingDevFlag?if_exists}"){
		               selector.options[i].selected="true";
		            }
		         }
	         </#if>
         </#if>
        var selector = document.all("category.id");
         var groups=selector.options.length;  
         
         <#if spare.category?exists>
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${spare.category.id?if_exists}"){
               selector.options[i].selected="true";
            }
         }
         </#if>
         <#--
         var selector = document.all("spareDetailType.id");
         var groups=selector.options.length;  
         <#if spare.spareDetailType?exists>
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${spare.spareDetailType.id?if_exists}"){
               selector.options[i].selected="true";
            }
         }
         </#if>
         -->
         <#if spare.unit?exists>
	         selector = document.all("unit.id");
	         groups=selector.options.length;
	         for (var i=0; i<groups; i++){
	            if (selector.options[i].value=="${spare.unit.id?if_exists}"){
	               selector.options[i].selected="true";
	            }
         	} 
         </#if> 
         
         <#if spare.propertyType?exists>
         selector = document.all("spare.propertyType");
          groups=selector.options.length; 
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${spare.propertyType?if_exists}"){
               selector.options[i].selected="true";
            }
         }
         </#if> 
         selector = document.all("spare.producingAreaType");
           groups=selector.options.length;  
           <#if spare.producingAreaType?exists>
           for (i=0; i<groups; i++){
                 if (selector.options[i].value=="${spare.producingAreaType?if_exists}"){
                     selector.options[i].selected="true";
                 }
           } 
           </#if>
        <#--
         toSortDetailTypeBySpareType();          //对备件明细类按备件大类进行排序分类
         if (-1 == document.forms[0].elements["category.id"].value) {
	       document.forms[0].elements["spareDetailType.id"].length=1;
	    } else {
	       spareTypeValueChange("category.id", "spareDetailType.id");
	       <#if spare.spareDetailType?exists>
	         document.forms[0].elements["spareDetailType.id"].value = #{spare.spareDetailType.id?if_exists};
	       </#if>
	     } 
	     -->
     }
    
    function checkSupplierDevice(){
     if(document.forms[0].elements["supplier.supplierNo"].value==""){
         alert('${action.getText('spare.supplier.selector')}');
         return false;
     }
     <#--  2009/4/11  zbzhang
     if(document.forms[0].elements["device.deviceNo"].value==""){
         alert('${action.getText('spare.device.selector')}');
         return false;
     }
      -->
         return true;
    }
   
	function eam2008_userdevice_OpenDialog(url) {
		popupModalDialog(url, 800, 600, refresh_device_information);
	}
	
	function refresh_device_information (result) {
	   document.forms[0].elements["device.name"].value = result[1]; 
	   document.forms[0].elements["device.deviceNo"].value = result[2]; 
	   document.forms[0].elements["device.id"].value = result[0]; 
	}
	function open_toolingSparePdf(id) {
			var spareId=id;
			var url='${req.contextPath}/reports/spare/toolingSpare.pdf?spareId='+spareId;
      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=screen.width,height=screen.height,left=0,top=0");
    }
    var resultData;
    function validate(){
    <#--
    	if($('show').style.display == "block"){
			$('show').style.display="none";
			var flag = event.srcElement.id.substring(6,7)-1;
			autoCopyValue(flag);
			return false;
		}
    -->
    	//if (!(''==document.forms[0].elements["spare.graphNo"].value)) {
    		//if(!isValidLength(document.forms[0], "spare.graphNo", null, 50)){
			  //  alert("${action.getText('spare.graphNo.length')}");
				//return  false;
			  //}
    	//}
    	 if (!(''==document.forms[0].elements["spare.unitPrice"].value)) {
    		if(!isDoubleNumberCheck(document.forms[0].elements["spare.unitPrice"].value)){
			    alert("${action.getText('spare.unitPrice.right')}");
				return  false;
			  }
    	}
    	var name=document.forms["spare"].elements["spare.name"].value;
        if ('' == name) {
          alert("${action.getText('spare.name.requiredstring')}");
		  return  false;
        }
    	if (!('' == name)) {
    		if(!isValidLength(document.forms[0], "spare.name", null, 50)){
			    alert("${action.getText('spare.name.length')}");
				return  false;
			  }
    	}
    	//if(isNotEmpty(spare,"spare.enName")) {
			//if (!isValidLength(document.forms[0],"spare.enName",0,50)){
				//alert("${action.getText('spare.enName.maxlength')}");
				//return false;
		   // }
		//}
		<#--
    	if (!(''==document.forms[0].elements["spare.graphNo"].value)) {
    		if(!isValidLength(document.forms[0], "spare.graphNo", null, 50)){
			    alert("${action.getText('spare.graphNo.length')}");
				return  false;
			  }
    	}
    	-->
    	//验证种类是否为空
    	if ('' == document.forms[0].elements["category.id"].value) {
    	  alert("${action.getText('category.required')}");
    	  return false;
    	}
    	//if ('' == document.forms[0].elements["spareDetailType.id"].value) {
    	 // alert("${action.getText('spareDetailType.required')}");
    	 // return false;
    	//}
    	dwr.engine.setAsync(false);
    	<#if spare.id?exists>
    	<#else>
    		//alert("name:"+$('spare.name').value+"   modelSpecs:"+$('modelSpecs').value+"   orderNo:"+$('spare.orderNo').value+"   factoryStr:"+$('factory.name').value);
	    	
	    	 var ary =new Array();
	    	ary.push($('spare.name').value);
	    	ary.push($('modelSpecs').value);
	    	ary.push($('spare.orderNo').value);
	    	ary.push($('factory.id').value);
	    	ary.push($('category.id').value);
	    	//ary.push($('spareDetailType.id').value);
	    	loadSpareByPamrameterJs.loadByKeyArry(ary,callbackSpare);
    		
    	if(resultData != "" && resultData != null){
    		alert("此备件已存在!");
    		return false;
    	}
    	</#if>
    	dwr.engine.setAsync(true);
    	
    	//if (!(''==document.forms[0].elements["specification"].value)) {
    	//	if(!isValidLength(document.forms[0], "specification", null, 50)){
		//	    alert("${action.getText('spare.specification.length')}");
		//		return  false;
		//	  }
    	//}
    	if(!(''==document.forms["spare"].elements["spare.safeStock"].value)) {
    		if(!isNumber("spare.safeStock")) {
    			alert("${action.getText('spare.safeStock.right')}");
    			return false;
    		}
    	}
    	safeStock = formatDigital(document.forms["spare"].elements["spare.safeStock"].value);
    	if(!isNumberBetween(safeStock,1000000001,0)){
    		alert("${action.getText('spare.safeStock.error')}");
    		return false;
    	}
    	
    	if(!(''==document.forms["spare"].elements["spare.stocks"].value)) {
    		if(!isNumber("spare.stocks")) {
    			alert("${action.getText('spare.stocks.right')}");
    			return false;
    		}
    	}
    	stocks = formatDigital(document.forms["spare"].elements["spare.stocks"].value);
    	if(!isNumberBetween(stocks,1000000001,0)){
    		alert("${action.getText('spare.stocks.error')}");
    		return false;
    	}
    	
    	if(!(''==document.forms["spare"].elements["spare.purchaseCycle"].value)) {
    		if(!isNumber("spare.purchaseCycle")) {
    			alert("${action.getText('spare.purchaseCycle.right')}");
    			return false;
    		}
    	}
    	stocks = formatDigital(document.forms["spare"].elements["spare.purchaseCycle"].value);
    	if(!isNumberBetween(stocks,1000001,0)){
    		alert("${action.getText('spare.purchaseCycle.error')}");
    		return false;
    	}
    	
    	if (!(''==document.forms[0].elements["spare.comment"].value)) {
    		if(!isValidLength(document.forms[0], "spare.comment", null, 250)){
			    alert("${action.getText('spare.content.length')}");
				return  false;
			  }
    	}
    	if (!(''==document.forms[0].elements["spare.originalHabitat"].value)) {
    		if(!isValidLength(document.forms[0], "spare.originalHabitat", null, 150)){
			    alert("${action.getText('spare.originalHabitat.length')}");
				return  false;
			  }
    	}
    	if ('TOOLINGDEVICE'==document.forms[0].elements["spare.toolingDevFlag"].value) {
    		alert("${action.getText('spare.toolingDevFlag.required')}");
    		return false;
    	}
    	return true;
    }
    function callbackSpare(data){
    	if(data.length>0){
    		resultData = data;
    		//alert("有数据");
	  	}else{
	  		resultData = "";
	  		//alert("无数据");
	  	}
    }
    </script>
    <#if spare.id?exists>
    	<ul id="beautytab">
		  <li><a id="spareDetail" onclick="activeTab(this);"  href="${req.contextPath}/spareLocation/listSpareDetail.html?spare.id=#{spare.id}" target="frame" >${action.getText('spareDetail')}</a></li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
    </#if>
</@htmlPage>