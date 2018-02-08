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
<#--$Id: subscribeDetailProfile.ftl 11279 2008-03-12 01:12:13Z mwei $ -->

<#include "../../includes/eam2008.ftl" />
<script type='text/javascript' src='${req.contextPath}/dwr/interface/SpareDetailSelectJs.js'></script>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/loadSpareByPamrameterJs.js'></script>
<@htmlPage title="${action.getText('subscribeDetail.title')}">
<base target="_self">
	<@ww.form name="'subscribeDtl'" action="'saveSubscribeDetail'" method="'post'" validate="true">
		<@ww.token name="saveSubscribeDtlToken"/>
		<#if subscribe.id?exists>
			<@ww.hidden name="'subscribe.id'" value="#{subscribe.id}"/>
		</#if>
		<#if subscribeDtl.id?exists>
		  <@ww.hidden name="'subscribeDtl.id'" value="#{subscribeDtl.id}"/>  
		  <@ww.hidden name="'totalPrice'" value="'${subscribeDtl.totalPrice?if_exists}'"/>
		<#else>
		  <@ww.hidden name="'totalPrice'" value=""/>
		</#if>
		<#if subscribeDtl.spare?exists>
		    <#if subscribeDtl.spare.id?exists>
		        <@ww.hidden name="'spare.id'" value="#{subscribeDtl.spare.id}"/>
		    </#if>
		     <#if subscribeDtl.spare.spareNo?exists>
		        <@ww.hidden name="'graphNoTemp'" value="${subscribeDtl.spare.spareNo}"/>
		    </#if>
		<#else>
		    <@ww.hidden name="'spare.id'" value=""/>
		      <@ww.hidden name="'graphNoTemp'" value=""/>
		</#if>
		<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@inputTable>
			<tr>
				<!--<@ww.textfield label="'${action.getText('graphNo')}'" name="'subscribeDtl.graphNo'" value="'${subscribeDtl.graphNo?if_exists}'"  cssClass="'underline'" readonly="true"/>
			   -->
			   <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">备件编码:</label>
	     	</td>
            <td>
	             <input type="text" name="subscribeDtl.graphNo" value="${subscribeDtl.graphNo?if_exists}" class="underline" onblur="autoCompleteSpare();toEditSubscribeDtl()"  >
        	 </td>
			   
			    <@ww.textfield label="'${action.getText('name')}'" name="'subscribeDtl.name'" value="'${subscribeDtl.name?if_exists}'"  cssClass="'underline'" required="true" readonly="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('specification')}'" name="'subscribeDtl.model'" value="'${subscribeDtl.model?if_exists}'"  cssClass="'underline'" required="true" onchange="'autoCompleteSpare()'" maxlength="150"/>
				<@ww.textfield label="'${action.getText('orderNo')}'" name="'subscribeDtl.orderNo'" value="'${subscribeDtl.orderNo?if_exists}'" cssClass="'underline'" disabled="false" required="false" maxlength="20" />
			</tr>
			<tr>
	        	
	        	<@ww.textfield label="'${action.getText('factory')}'" name="'subscribeDtl.factoryStr'" value="'${subscribeDtl.factoryStr?if_exists}'" cssClass="'underline'" disabled="false" required="false" onchange="'autoCompleteSpare()'"/>
	        	<#if factory?exists>
			 		<@ww.hidden name="'factory.id'" value="#{factory.id?if_exists}"/>
			  	<#else>
					<@ww.hidden name="'factory.id'" value=""/>
			  	</#if>
			  	<#--
			  	<@ww.select label="'${action.getText('category')}'" 
                     required="true" 
                     name="'category.id'" 
		             listKey="id" 
		             listValue="name"
		             list="allCategory" 
		             emptyOption="true"
		             disabled="false" 
		             required="true"
		             onchange="'spareTypeCascadeDWR(\"category.id\",\"detailCategory.id\",\"${action.getText('')}\");autoCompleteSpare()'">
		       </@ww.select>
		       -->
		      <@ww.select label="'${action.getText('category')}'" 
                     required="true" 
                     name="'category.id'" 
		             listKey="id" 
		             listValue="name"
		             list="allCategory" 
		             emptyOption="true"
		             disabled="false" 
		             required="true"
		             onchange="'autoCompleteSpare()'">
		       </@ww.select>
	        </tr>
			<tr>
			<#--
				<@ww.select label="'${action.getText('detailCategory')}'" 
					required="true" 
					name="'detailCategory.id'" 
		            listKey="id" 
		            listValue="name"
		            list="AllDetailCategory" 
		            emptyOption="true" 
		            disabled="false"
		            required="true" 
		            onchange="'autoCompleteSpare()'">
		       </@ww.select>
		       -->
		       <@ww.select label="'${action.getText('tooling.calUnit')}'" 
				     required="false" 
				     name="'calUnit.id'" 
		             listKey="id" 
		             listValue="value" 
		             list="calUnits" 
		             emptyOption="true" 
		             disabled="false" 
		             required="true">
		      </@ww.select>
		      <@ww.textfield label="'${action.getText('amount')}'" name="'subscribeDtl.amount'" value="'${subscribeDtl.amount?if_exists}'" cssClass="'underline'" disabled="false" maxlength="20" required="true" onchange="'calAllPrice()'"/>
    	    </tr>
    	    <tr>
    	    	<@ww.textfield label="'${action.getText('单价')}'" name="'subscribeDtl.unitPrice'" value="'#{subscribeDtl.unitPrice?if_exists}'" cssClass="'underline'" disabled="false" maxlength="20" required="true" onchange="'calAllPrice()'"/>
    	        <@ww.textfield label="'${action.getText('总价')}'" name="'subscribeDtl.totalPrice'" value="'${subscribeDtl.totalPrice?if_exists}'" cssClass="'underline'" disabled="true" required="false" maxlength="20"/> 
    	    </tr>
    	    <tr>
    	        <@ww.textfield label="'${action.getText('ownedEquipment')}'" name="'subscribeDtl.ownedEquipment'" value="'${subscribeDtl.ownedEquipment?if_exists}'" cssClass="'underline'" disabled="false" required="false" maxlength="30"/>
    	        <@ww.textfield label="'${action.getText('equipmentFactory')}'" name="'subscribeDtl.equFactoryStr'" value="'${subscribeDtl.equFactoryStr?if_exists}'" cssClass="'underline'" disabled="false" required="false" maxlength="30" />
    	    </tr>
    	    <tr>
    	        <@ww.textfield label="'${action.getText('所属设备总数量')}'" name="'subscribeDtl.sssbzsl'" value="'${subscribeDtl.sssbzsl?if_exists}'" cssClass="'underline'" disabled="false" required="false" maxlength="10"/>
    	        <@ww.textfield label="'${action.getText('损坏频次')}'" name="'subscribeDtl.shpc'" value="'${subscribeDtl.shpc?if_exists}'" cssClass="'underline'" disabled="false" required="false" maxlength="10"/>
    	    </tr>
	        <tr> 
	       		<@ww.textfield label="'${action.getText('buyeAmount')}'" name="'subscribeDtl.buyeAmount'" value="'${subscribeDtl.buyeAmount?if_exists}'" cssClass="'underline'" disabled="true" required="false" maxlength="30"/>
				<@ww.textfield label="'${action.getText('arrivalAmount')}'" name="'subscribeDtl.arrivalAmount'" value="'${subscribeDtl.arrivalAmount?if_exists}'" cssClass="'underline'" disabled="true" required="false" maxlength="20"/> 
	        </tr>
	    
	        <tr>
	        	<@ww.textfield label="'${action.getText('供应商')}'" name="'subscribeDtl.supplierName'" value="'${subscribeDtl.supplierName?if_exists}'" cssClass="'underline'" disabled="true" required="false" />
	        	<@ww.textfield label="'${action.getText('交货日期')}'" name="'subscribeDtl.requireDate'" value="'${subscribeDtl.requireDate?if_exists}'" cssClass="'underline'" disabled="true" required="false" />
	        </tr>
	       
	        <tr>
	        	<@ww.textfield label="'${action.getText('实到日期')}'" name="'subscribeDtl.arrivalDate'" value="'${subscribeDtl.arrivalDate?if_exists}'" cssClass="'underline'" disabled="true" required="false" />
	       		<@ww.textfield label="'${action.getText('status')}'" name="'subscribeDtl.status'" value="" cssClass="'underline'" disabled="true" required="false" />
	        </tr>
	        <tr>
	       
	        <@ww.textarea label="'${action.getText('申购理由')}'" 
					         name="'subscribeDtl.beizhu'" 
					         value="'${subscribeDtl.beizhu?if_exists}'" rows="'3'" cols="'60'" 
				 />   
	        </tr>
		</@inputTable>
		<@buttonBar>
		   
      <#if !(action.isReadOnly())>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return subscribeValidate();'"/>
		   </#if>
		    <#if subscribeDtl.id?exists>
		    <@hrefButton value="${action.getText('继续新建')}" url="${req.contextPath}/prophase/business/editSubscribeDetail.html?subscribe.id=#{subscribe.id}&toolingDevFlag=${toolingDevFlag?if_exists}"/>	
		    </#if>
		    <#--
		    <@vbutton name="'close'"  class="button" value="${action.getText('保存并关闭')}" onclick="javascript:saveAndColse();"/>-->
		    
		     <@vsubmit name="'save'" value="'${action.getText('保存并关闭')}'" onclick="'return saveAndColse();'"/>
		    <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
	
	</@ww.form>
	<script language="javascript">
	window.onload=function(){
	
	   a=new Date();
	   //var time=a.format("yyyy-MM-dd");
	  // getObjByNameRe("subscribeDtl.requireDate").value=time;
	   //种类
	   <#if subscribeDtl.category?exists>
         		document.forms[0].elements["category.id"].value = #{subscribeDtl.category.id?if_exists};
         		//DWREngine.setAsync(false); 
	    		//回调种类的值后触发DWR 级联明细种类  
	    		//spareTypeCascadeDWR("category.id","detailCategory.id","${action.getText('')}")
	    		//重新设置为异步方式
	    		//DWREngine.setAsync(true);
       </#if>
       //明细种类
       <#if subscribeDtl.detailCategory?exists>
         		document.forms[0].elements["detailCategory.id"].value = #{subscribeDtl.detailCategory.id?if_exists};
       </#if>
       //单位
	   	<#if subscribeDtl.calUnit?exists>
         		document.forms[0].elements["calUnit.id"].value = #{subscribeDtl.calUnit.id?if_exists};
       </#if>
       <#assign subscribeStatus = ''/>
       <#if '${subscribeDtl.status}' == 'NEW'>
       <#assign subscribeStatus = "'新建'"/>
       <#elseif '${subscribeDtl.status}' == 'COLLECTED'>
       <#assign subscribeStatus = "'已汇总'"/>
       <#elseif '${subscribeDtl.status}' == 'PURCHASEED'>
       <#assign subscribeStatus = "'已采购'"/>
       <#elseif '${subscribeDtl.status}' == 'INSPECTING'>
       <#assign subscribeStatus = "'入库中'"/>
       <#elseif '${subscribeDtl.status}' == 'INSPECTED'>
       <#assign subscribeStatus = "'已入库'"/>
        <#elseif '${subscribeDtl.status}' == 'NOTPURCHASEED'>
       <#assign subscribeStatus = "'不采购'"/>
       </#if>
       document.forms[0].elements["subscribeDtl.status"].value = ${subscribeStatus};
       <#if '${subscribeDtl.status}' != 'NEW'>
            document.forms[0].elements["save"].disabled ='true';
       </#if>
       <#--
   <#if subscribeDtl?exists && subscribeDtl.graphNo?if_exists!="">
      var category = getObjByNameRe("category.id");
		category.onmousemove=function(){this.setCapture();}
		category.onmouseout=function(){this.releaseCapture();}
		category.onfocus=function(){this.blur();}
		
	  var detailCategory = getObjByNameRe("detailCategory.id");
		detailCategory.onmousemove=function(){this.setCapture();}
		detailCategory.onmouseout=function(){this.releaseCapture();}
		detailCategory.onfocus=function(){this.blur();}
		
    </#if>   -->
       
	}
	
	
	
	
   //根据单价和数量,计算总价
	 function calAllPrice() {  
	   if ('' != document.forms[0].elements["subscribeDtl.unitPrice"].value && isDoubleNumber("subscribeDtl.unitPrice")) {
	     var unitPrice =formatDigital(document.forms[0].elements["subscribeDtl.unitPrice"].value);
	     if ('' != document.forms[0].elements["subscribeDtl.amount"].value && isNumber("subscribeDtl.amount")) {
	       var number =formatDigital(document.forms[0].elements["subscribeDtl.amount"].value);
	       document.forms[0].elements["totalPrice"].value =parseInt(unitPrice)*parseInt(number);
	       document.forms[0].elements["subscribeDtl.totalPrice"].value =parseInt(unitPrice)*parseInt(number);
	     } else {
	       document.forms[0].elements["totalPrice"].value = 0;
	       document.forms[0].elements["subscribeDtl.totalPrice"].value = 0;
	     }
	   }
	 }
	 <#--
	 function calAllPrice() {  
	   if ('' != document.forms[0].elements["subscribeDtl.unitPrice"].value && isDoubleNumber("subscribeDtl.unitPrice")) {
	     var unitPrice =parseFloat(formatDigital(document.forms[0].elements["subscribeDtl.unitPrice"].value));
	     if ('' != document.forms[0].elements["subscribeDtl.amount"].value && isNumber("subscribeDtl.amount")) {
	       var number =parseInt(formatDigital(document.forms[0].elements["subscribeDtl.amount"].value));
	       document.forms[0].elements["totalPrice"].value =unitPrice*number;
	       document.forms[0].elements["subscribeDtl.totalPrice"].value =unitPrice*number;
	     } else {
	       document.forms[0].elements["totalPrice"].value = 0;
	       document.forms[0].elements["subscribeDtl.totalPrice"].value = 0;
	     }
	   }
	 }-->
	  var resultData;
	  function subscribeValidate(){
	    var graphN=getObjByName('subscribeDtl.graphNo').value;
	        var graphNo=graphN.replace(/(^\s*)|(\s*$)/g,"");
	  if(graphNo==''){//验证图号是否为空
              alert('${action.getText('input.graphNo')}');
              return false;	       
	       }
	  	  if(graphNo!=''){//验证图号
	        if(!isValidLength(document.forms[0],"subscribeDtl.graphNo",null,50)){
	          alert('${action.getText('subscribeDtl.graphNo.length')}');
	          return false;
	         }
	          var spareId=getObjByName('spare.id').value;
	          if(spareId==''){
	          dwr.engine.setAsync(false);
	    	loadSpareByPamrameterJs.loadBySpareNo(graphNo,callbackSpareNoValidate);
    		if(resultData != null && resultData != ""){
    		alert("您所输入的备件编码已经存在,无法新建!");
			return false;
    		}
    		 dwr.engine.setAsync(true);
    		 }else{
    		   dwr.engine.setAsync(false);
	        var graphNoTemp=getObjByName('graphNoTemp').value;//未修改前的备件编码
    		<#if subscribeDtl.spare?exists>
		     <#if subscribeDtl.spare.spareNo?exists>
		     graphNoTemp ='${subscribeDtl.spare.spareNo}';
		    </#if>
			</#if>
	        if(graphNo!=graphNoTemp){
	        
	    	loadSpareByPamrameterJs.loadBySpareNo(graphNo,callbackSpareNoValidate);
    		if(resultData != null && resultData != ""){
    		alert("您所输入的备件编码已经存在,无法新建!");
			return false;
    		}
    		}
    		 dwr.engine.setAsync(true);
    		 
    		 
    		 }
	         
	      }
	       if(getObjByNameRe("subscribeDtl.name").value==''){//验证品名是否为空
              alert('${action.getText('input.proudctName')}');
              return false;	       
	       }
	      if(getObjByNameRe("subscribeDtl.name").value!=''){//如果品名不为空,验证品名的输入长度
	        if(!isValidLength(document.forms[0],"subscribeDtl.name",null,50)){
	          alert('${action.getText('subscribeDtl.name.length')}');
	          return false;
	         }
	      }
	      <#--
	       if(getObjByNameRe("subscribeDtl.model").value!=''){//验证型号的输入长度
	        if(!isValidLength(document.forms[0],"subscribeDtl.model",null,50)){
	          alert('${action.getText('subscribeDtl.model.length')}');
	          return false;
	         }
	      }-->
	      if(getObjByNameRe("subscribeDtl.model").value==''){//验证规格是否为空
              alert('${action.getText('input.subscribeDtl.specification')}');
              return false;
	       }
	      if(getObjByNameRe("subscribeDtl.model").value!=''){//验证规格的输入长度
	        if(!isValidLength(document.forms[0],"subscribeDtl.model",null,50)){
	          alert('${action.getText('subscribeDtl.specification.length')}');
	          return false;
	         }
	      }
	       if(getObjByNameRe("category.id").value=='' || getObjByNameRe("category.id").value=='-1'){//验证种类是否为空
              alert('${action.getText('input.subscribeDtl.category')}');
              return false;
	       }
	       <#--
	      if(getObjByNameRe("detailCategory.id").value=='' || getObjByNameRe("detailCategory.id").value=='-1'){//验证明细种类是否为空
              alert('${action.getText('input.subscribeDtl.detailCategory')}');
              return false;
	       }
	       -->
	       if(getObjByNameRe("calUnit.id").value=='' || getObjByNameRe("calUnit.id").value=='-1'){//验证单位是否为空
              alert('${action.getText('input.subscribeDtl.calUnit')}');
              return false;
	       }
	      <#-- 
	     //验证单价是否为空,以及格式
       if(isNotEmpty(document.forms[0],"subscribeDtl.unitPrice")) {
	     if (!isDoubleNumber("subscribeDtl.unitPrice")){
		     alert("${action.getText('subscribeDtl.unitPrice.isNotNumber')}");
		       return false;
	       } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["subscribeDtl.unitPrice"].value, 10000000001, 0)){  //验证范围
		     alert("${action.getText('subscribeDtl.unitPrice.maxLength')}");
		      return false;
	      }
       }-->
    //验证数量是否为空,以及格式
       var number = document.forms[0].elements["subscribeDtl.amount"].value;
       if ('' == number) {            //验证是否为空
         alert("${action.getText('input.subscribeDtl.amount')}");
         return false;
       } else if (!isDoubleNumber("subscribeDtl.amount")){   //验证格式
         alert("${action.getText('format.error')}");
         return false;
       } else if (!isNumberBetweenBoolen(number, 1000000001, 0)){ //验证范围
         alert("${action.getText('subscribeDtl.count.maxLength')}");
         return false;
       }
       			//验证计量单位
    if ('' == document.forms[0].elements["calUnit.id"].value) {
         		alert("${action.getText('calUnit.id.requried')}");
         		return false;
      		 }
	  //if(getObjByNameRe("subscribeDtl.requireDate").value==''){//验证日期
	  //       alert('${action.getText('requiredate.not.null')}');
	  //       return false; 
	  //     }
	  // var date=getObjByNameRe("subscribeDtl.requireDate").value;
	  //   if(!isDate("subscribeDtl.requireDate")){
	  //       alert('${action.getText('date.format.error')}');
	  //       return false;
	  //     }
	  //  if(!isDateLessEqualThenCurrent(date)){
	  //            alert('${action.getText('afresh.requireDate')}');
	  //            return false;
	  //      }
	    if(getObjByNameRe("subscribeDtl.beizhu").value!=''){//如果备注的长度不为空  验证备注的长度
	         if(!isValidLength(document.forms[0],"subscribeDtl.beizhu",null,250)){
	          alert('${action.getText('text.subscribeDtl.beizhu.length')}');
	          return false;
	         }
	       }
	    return true;
	  }		
	  
	  function saveAndColse(){
	    var bValue=subscribeValidate();
	    if (bValue){
	       window.close();
	       return true;
	    } else {
	       return false;
	    }
	  }
	  
	  //根据品名、规格型号、种类、明细类自动补全备件信息
	  function autoCompleteSpare(){
	  
               getObjByNameRe("subscribeDtl.name").setAttribute("readonly",false,0);
               //获取图号
	      var graphN = getObjByName('subscribeDtl.graphNo').value;
	       var graphNo=graphN.replace(/(^\s*)|(\s*$)/g,"");
	      //获取品名
	      var spareNam = getObjByName('subscribeDtl.name').value;
	       var spareName=spareNam.replace(/(^\s*)|(\s*$)/g,"");
	      //获取规格型号
	      var modelSpecs = getObjByName('subscribeDtl.model').value;
	      //获取种类
	      var categoryId = getObjByName('category.id').value;
	      //获取明细种类
	     // var spareDetailTypeId= getObjByName('detailCategory.id').value;
	      //获取生产厂家
	      var factoryName = getObjByName('subscribeDtl.factoryStr').value;
	      //工装或设备标识
	      var toolingDevFlag = getObjByName('toolingDevFlag').value;
	      var url = '${req.contextPath}/prophase/business/searchListSpareFrame.html?spareName=' + spareName
	                 + '&modelSpecs=' + modelSpecs + '&category.code=' + categoryId + '&factory.name='+factoryName+ '&spareNo='+graphNo;
	      url = url + '&toolingDevFlag=' + toolingDevFlag;
	      url = encodeURI(url);
	      <#--如果是从申购单明细维护穿过来的 fromDetail=true
	      <#if !fromDetail>
	          parent.frames["buttomFrame"].document.forms[0].action=url;
	          parent.frames["buttomFrame"].document.forms[0].submit();
	      </#if>-->
	      if (parent.frames["buttomFrame"].document.forms[0].action!='undefined' || 
	             !parent.frames["buttomFrame"].document.forms[0].action || parent.frames["buttomFrame"].document.forms[0].action!=null){
	          parent.frames["buttomFrame"].document.forms[0].action=url;
	          parent.frames["buttomFrame"].document.forms[0].submit();
	      }
	  }
	  
	   function callbackSpareNoValidate(data){
    	 if(data!=null&&data["id"]!=''){
    		resultData = data;
	  	}else{
	  	resultData="";
	  	}
    }
    
    function toEditSubscribeDtl(){
	var graphN=getObjByName('subscribeDtl.graphNo').value;//用户输入的备件编码
	var graphNo =graphN.replace(/(^\s*)|(\s*$)/g,"");
    var graphNoTemp=getObjByName('graphNoTemp').value;//未修改前的备件编码
    <#if subscribeDtl.spare?exists>
		     <#if subscribeDtl.spare.spareNo?exists>
		     graphNoTemp ='${subscribeDtl.spare.spareNo}';
		    </#if>
		</#if>
    if(graphNo==""){
     return false;
	 }
	 var spareId=getObjByName('spare.id').value;
	 if(spareId!=''){
	 if(graphNoTemp==graphNo){
	 return false;
	 } 
	 }
	loadSpareByPamrameterJs.loadBySpareNo(graphNo,callbackSpareNo);
    }
      function callbackSpareNo(data){
      if(data!=null&&data["id"]!=''){
       alert("您所输入的备件编码已经存在,无法新建!");
      }
    }
	</script>
</@htmlPage>