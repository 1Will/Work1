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
<#-- $Id: supplierSelector.ftl 11157 2008-02-29 03:39:18Z mwei $ -->

<#include "../../includes/eam2008.ftl" />
<#include "../../includes/macros.ftl" />
<script type='text/javascript' src='${req.contextPath}/dwr/interface/CreateGraphNoJs.js'></script>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/SpareDetailSelectJs.js'></script>

<@htmlPage title="${action.getText('subcribeBillDtl.title')}">
<base target="_self">
<@ww.form name="'listForm'" namespace="'/prophase/business'"action="'searchSubscribeBill'" method="'post'">
	<@ww.token name="searchSubscribeBillToken"/>
    <#include "subscribeDetailToPurchaseDetailSearcher.ftl" />
    <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
    <@ww.hidden name="'purchaseType'" value="'${req.getParameter('purchaseType')?if_exists}'"/>
    <@ww.hidden name="'buyUserId'" value="'${req.getParameter('buyUserId')?if_exists}'"/>
    <@ww.hidden name="'purchaseBill.id'" value="'${req.getParameter('purchaseBill.id')?if_exists}'"/>
    <@ww.hidden name="'allSubscribeDetailCategory'" value=""/>
    <@ww.hidden name="'allSubscribeDtlDetailCategory'" value=""/>
    <@ww.hidden name="'allSubscribeDetailName'" value=""/>
    <@ww.hidden name="'allSubscribeDetailModel'" value=""/>

	<@ww.hidden name="'addSubscribeIds'" value=""/>
	<@ww.hidden name="'addSubscribe'" value=""/>
    
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
     
     <#assign itemNo=1/>
     <#assign itemId=''/>
     <#assign categoryIdentityName = 'category' + '${itemNo}'/>
     <#assign detailCategoryIdentityName = 'detailCategory' + '${itemNo}'/>
     <#assign nameIdentityName = 'name' + '${itemNo}'/>
     <#assign modelIdentityName = 'model' + '${itemNo}'/>
     <#assign factoryIdentityName = 'factory' + '${itemNo}'/>
     <#assign factoryIdentityId = 'factoryId' + '${itemNo}'/>
     <#assign orderNoIdentityName = 'orderNo' + '${itemNo}'/>
     <#assign imgIdentityName = 'img' + '${itemNo}'/>		   	
     
 <@list title=""  
		includeParameters="name|toolingDevFlag|graphNo|department.id|status|dtlName|specification|subscribeDate_start|subscribeDate_end|collectDate_start|collectDate_end|collectCode|collectName|collectDate|factory|spareDetailType.id|spareType.id|purchaseType|detailKind.id" 
		fieldMap="like:dtlName|graphNo|model|factory">
           <#assign itemNo = itemNo + 1/>
           <#--
           <#assign itemId=itemId+'#{object.detailCategory.id}'+','/>
           -->
            <@vlh.checkbox property="id" name="subscribeDtlIds" >
                <@vlh.attribute name="width" value="30" />
                <@vlh.attribute name="onclick" value="return checkStatus('#{object.id?if_exists}','${object.status?if_exists}','${object.graphNo?if_exists}');" />
            </@vlh.checkbox>
        
              <#--图号-->
             <@vcolumn title="${action.getText('graphNo')}" property="graphNo" attributes="width='85'">
	    		   <@vlh.attribute name="style" value="width:100"/>
	    		   <@vlh.attribute name="id" value="#{object.id?if_exists}"/>
            </@vcolumn>
             <#--品名-->
            <@vcolumn title="${action.getText('name')}" >
              
              
            	<input type="text" name="${nameIdentityName}" 
	    		       class="underline" 
	    		       style="text-align:left;width:130;"
	    		       value="${object.name?if_exists}"
	    		       title="${object.name?if_exists}"  
	    		       maxlength="50" size="7" />
	    	  
	    	   
	    	<#assign nameIdentityName = 'name' + '${itemNo}'/>
            </@vcolumn>
            
             <#--规格型号-->
            <@vcolumn title="${action.getText('型号')}" >
              
                  <input type="text" name="${modelIdentityName}" 
	    		       class="underline" 
	    		       style="text-align:left;width:100;"
	    		       value="${object.model?if_exists}" 
	    		       title="${object.model?if_exists}" 
	    		       maxlength="50" size="7" />
	     
	    	  
	    	  <#assign modelIdentityName = 'model' + '${itemNo}'/>	       
            </@vcolumn>
            
             <#--订货号 -->
             <@vcolumn title="${action.getText('订货号')}">
             
            	 
                  <input type="text" name="${orderNoIdentityName}" 
	    		       class="underline" 
	    		       style="text-align:right;width:60;"
	    		       value="${object.orderNo?if_exists}" 
	    		       title="${object.orderNo?if_exists}" 
	    		       maxlength="50" size="7" />
	    	  
	    	   <#assign orderNoIdentityName = 'orderNo' + '${itemNo}'/>	   
            </@vcolumn>
            
            
            <#--生产厂家 -->
            <@vcolumn title="${action.getText('生产厂家')}">
               <@factorySelectorInList size="12"/>
            </@vcolumn>
            
            <#--种类 -->
           <@vcolumn title="${action.getText('subscribe.category')}" >
             
				      <select name="${categoryIdentityName}">
 
					    <@ww.iterator value="allSpareType" id="category">
					      <option value="<@ww.property value="id"/>">
					      	<@ww.property value="name"/>
					      </option>
					    </@ww.iterator>
				      </select>
				      
				      <script language="javascript">
			            <#if object.category?exists>
			              document.forms[0].elements["${categoryIdentityName}"].value='${object.category.id?if_exists}';
			            </#if>
			          </script>
			          <#assign categoryIdentityName = 'category' + '${itemNo}'/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('subscribe.category')}" >
             
				      <select name="${categoryIdentityName}"  onchange="fn('${categoryIdentityName}','${detailCategoryIdentityName}','${action.getText('')}')">
 
					    <@ww.iterator value="allSpareType" id="category">
					      <option value="<@ww.property value="id"/>">
					      	<@ww.property value="name"/>
					      </option>
					    </@ww.iterator>
				      </select>
				      
				      <script language="javascript">
			            <#if object.category?exists>
			              document.forms[0].elements["${categoryIdentityName}"].value='${object.category.id?if_exists}';
			            </#if>
			          </script>
			          <#assign categoryIdentityName = 'category' + '${itemNo}'/>
            </@vcolumn>
            -->
             <#--明细种类 -->
             <#--
             <@vcolumn title="${action.getText('subscribe.detailCategory')}" >
				      <select name="${detailCategoryIdentityName}" >
					    <@ww.iterator value="spareDetailType" id="detailCategory">
					      <option value="<@ww.property value="id"/>">
					      	<@ww.property value="name"/>
					      </option>
					    </@ww.iterator>
				      </select>
				      
				      <script language="javascript">
			            <#if object.detailCategory?exists>
			              document.forms[0].elements["${detailCategoryIdentityName}"].value='${object.detailCategory.id?if_exists}';
			            </#if>
			          </script>
			          <#assign detailCategoryIdentityName = 'detailCategory' + '${itemNo}'/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('操作')}">
            	 <@vbutton name="'new'"  class="button" value="${action.getText('创建台账')}" onclick="return createGraphNo('#{object.id?if_exists}','${object.graphNo?if_exists}','${itemNo}');"/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('subscribe.collectNo')}" property="subscribeCollectBill.code">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('subscribe.collectName')}" property="subscribeCollectBill.name">
            	<@alignLeft />
            </@vcolumn>
            <#--
             <@vcolumn title="${action.getText('subscribe.collectDept')}" property="subscribeCollectBill.collectDept.name">
            	<@alignLeft />
            </@vcolumn>-->
           
            
            <@vcolumn title="${action.getText('amount')}" property="amount">
            	<@alignRight />
            </@vcolumn>
            
             <@vcolumn title="${action.getText('subscribe.collectDate')}" property="subscribeCollectBill.collectDate" format="yyyy-MM-dd">
            	<@alignCenter/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('subscribe.billNo')}" property="subscribe.billNo">
            	<@alignLeft />
            </@vcolumn>-->
            <@vcolumn title="${action.getText('subscribe.name')}" property="subscribe.name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('subscriber.department')}" property="subscribe.department.name">
            	<@alignLeft />
            </@vcolumn>
              
           
            
            <@vcolumn title="${action.getText('subscribeDate')}" property="subscribe.subscribeDate" format="yyyy-MM-dd">
            	<@alignCenter/>
            </@vcolumn>
           
            
             <@vcolumn title="${action.getText('comment')}" property="comment" attributes="width='85'">
            	<@alignLeft />
            </@vcolumn>
             
          
		</@list>
		<#if !first>
       <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"subscribeDtlIds\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <@vsubmit name="'choose'" value="'${action.getText('选择并关闭')}'" onclick="'return confirmSelectsAndColse(\"subscribeDtlIds\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
	</#if>
</@ww.form>

  <script language="javascript">
        <#--自定义一个trim函数--> 
    String.prototype.Trim = function() {
	        return this.replace(/(^\s*)|(\s*$)/g, "");
	}
<#--
       var length = ${itemNo};
       var categoryId='${itemId}';
       for(var i=1;i<length;i++){
           var tagName ='category'+i;
           var tagNameDetail = 'detailCategory'+i;
           fn(tagName,tagNameDetail);
           document.forms[0].elements[tagNameDetail].value=parseInt(categoryId.split(",")[i-1]);
       }
    
   function fn(category,detailCategory){
   
	 DWREngine.setAsync(false); 
	//回调种类的值后触发DWR 级联明细种类  
	spareTypeCascadeDWR(category,detailCategory,"${action.getText('')}")
	//重新设置为异步方式
	DWREngine.setAsync(true);
  }
  -->
 

   	 
     var name = '';
     var itemNos='';
    function createGraphNo(id,graphNo,itemNo){
      // var graphNo = getObjByName(id).innerHTML;//如果是空格返回 &nbsp;
       var graphNo = getObjByName(id).innerText;//如果是空格返回 空格
       itemNos=itemNo;
       name=id;
       <#--
       if(graphNo.Trim() != ''){
           alert("已创建台账！");
           return false;
       }else{-->
       
        //验证页面字段值
        if(false == validateText(itemNo)){
            return false;
        }
         
        var allName='';
        var allModel='';
        var allCategory='';
        var allDetailCategory='';
        var allFactory='';
        var allOrderNo='';
        var allValues=new Array();
        
          //获得申购单明细的  品名
	    allName = retrieveSubscribeDtlNameText(itemNo);
	    //获得申购单明细的  规格型号
	    allModel = retrieveSubscribeDtlModelText(itemNo);
	     //获得申购单明细的 订货号
	    allOrderNo = retrieveSubscribeDtlOrderNoText(itemNo)
	     //获得申购单明细的  生产厂家
	    allFactory = retrieveSubscribeDtlFactoryText(itemNo);
         //获得申购单明细的 种类
        allCategory = retrieveSubscribeDtlCategoryText(itemNo);
         //获得申购单明细的 明细种类
       // allDetailCategory =  retrieveSubscribeDtlDetailCategoryText(itemNo);
       
        allValues.push(encodeURI(id));
        allValues.push(encodeURI(allName));//对中文值进行转码（在java类用URLDecoder.decode(ary[0], "UTF-8")转码）
        allValues.push(encodeURI(allModel));
        allValues.push(encodeURI(allOrderNo));
        allValues.push(encodeURI(allFactory));
        allValues.push(encodeURI(allCategory)); 
        //allValues.push(encodeURI(allDetailCategory));
        
        //dwr 调用 java 类方法并返回图号显示在页面
        CreateGraphNoJs.createGraphNo(allValues,getSpareNo);
          
          return true;
       }
  <#--  }-->
    
  
	   
    
    
    //返回图号在页面显示 并 灰化 修改过的信息
    function getSpareNo(spareNo){
       
       if(null!=spareNo && ''!=spareNo){
		    getObjByName(name).value=spareNo;
		    getObjByName(name).innerHTML=spareNo;
		   <#--
		     document.forms["listForm"].elements['name'+(itemNos-1)].disabled="true";
		     document.forms["listForm"].elements['model'+(itemNos-1)].disabled="true";
		     document.forms["listForm"].elements['factory'+(itemNos-1)].disabled="true";
		     document.forms["listForm"].elements['img'+(itemNos-1)].disabled="true";
		     document.forms["listForm"].elements['orderNo'+(itemNos-1)].disabled="true";
		     document.forms["listForm"].elements['category'+(itemNos-1)].disabled="true";
		     document.forms["listForm"].elements['detailCategory'+(itemNos-1)].disabled="true";-->
       }else{
             alert("创建台账出错！");
       }
       
      
    }
    //判断状态
    function checkStatus(id,status,graphNo){
       var code =getObjByName(id).innerText;
       //如果 code 的值为空 ，则有2个空格
      if('  '==code && ''==graphNo){
         alert("请先创建台账，再选择！");
         return false;
      }
    
    
     
      
   }
   
   //验证页面字段值
   function validateText(itemNo){
       //验证 品名
	   if(false == retrieveSubscribeDtlNameText(itemNo)){
	       return false;
	   }
	   //验证  规格型号
	   if(false == retrieveSubscribeDtlModelText(itemNo)){
	       return false;
	   }
	     //验证 种类
       if(false == retrieveSubscribeDtlCategoryText(itemNo)){
	       return false;
	   } 
       
         //验证 明细种类
      // if(false == retrieveSubscribeDtlDetailCategoryText(itemNo)){
	      // return false;
	 //  }
	   return true;
      
   }
   
   
   
   
   	 //获得申购单明细的  品名
	function retrieveSubscribeDtlNameText(itemNo){
         var ary = '';
         var TagName = 'name';
         TagName= TagName + (itemNo-1); 
         ary = document.forms["listForm"].elements[TagName].value
         if(''==ary.Trim()){
            alert("请填品名！");
            return false;
         }else{
             return ary;
         }
        
	}
	 //获得申购单明细的  规格型号
	function retrieveSubscribeDtlModelText(itemNo){
	    var ary = '';
        var TagName = 'model';
        TagName= TagName + (itemNo-1); 
        ary = document.forms["listForm"].elements[TagName].value;
        if(''==ary.Trim()){
           alert("请填规格型号！");
           return false;
        }else{
          return ary;
        }
	}
	
	 //获得申购单明细的 生产厂家
	function retrieveSubscribeDtlFactoryText(itemNo){
        var TagName = 'factoryId';
        TagName= TagName + (itemNo-1); 
        return document.forms["listForm"].elements[TagName].value;
        
        
	}
	 //获得申购单明细的 订货号
	function retrieveSubscribeDtlOrderNoText(itemNo){
        var TagName = 'orderNo';
        TagName= TagName + (itemNo-1); 
        return  document.forms["listForm"].elements[TagName].value;
	}
   
   	//获得申购单明细的 种类
	function retrieveSubscribeDtlCategoryText(itemNo){
       var ary = '';
       var TagName = 'category';
       TagName= TagName + (itemNo-1); 
       ary=document.forms["listForm"].elements[TagName].value;
        if(''== ary || '-1' == ary) {
            alert("请选择种类！");
            return false;
         }else{
           return ary;
         }
       
	}
	
	 //获得申购单明细的 明细种类
	 <#--
	function retrieveSubscribeDtlDetailCategoryText(itemNo){
	    var ary ='';
        var TagName = 'detailCategory';
        TagName= TagName + (itemNo-1); 
        ary = document.forms["listForm"].elements[TagName].value;
        if(''==ary || '-1'== ary){ 
            alert("请选择明细种类！");
            return false;       
        }else{
            return ary;
        }
       
	}
	-->
 
   
  
    function confirmSelects(boxname) {
       if (!hasChecked(boxname)) {
          alert("选择不能为空！");
	     	return false;
	   }
	   var selecter = document.all(boxname);
	    if(selecter.length){
	      for(var i=0;i<selecter.length;i++){
	        if(selecter[i].checked){
	           var spid = selecter[i].value;
	             if('  '==getObjByName(spid).innerText){
	                 alert("第 "+(i+1)+" 行，请先创建台账，再选择！");
	                 return false;
	             }
	        }
	      }
	    
	    }
	   
	     
	    chooseSubscribes(boxname);
	    checkInvalidParms();
	     return true;
	  
  }
   
 
	    	      	
   function chooseSubscribes(boxname) {
	     var selector = document.getElementsByName(boxname);
	     if (!selector) {
	       return false;
	     }
		 var subscribeDtlIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   subscribeDtlIds += selector[i].value + ",";
			 }
		   }
		 }
		 //returnDialog(subscribeDtlIds,false);
		 //returnDialogNoClose(subscribeDtlIds,true);
		 if (null!=subscribeDtlIds){
	 		var addSubscribeIds = subscribeDtlIds.substring(0, subscribeDtlIds.lastIndexOf(","));
	        document.forms[0].elements["addSubscribeIds"].value =addSubscribeIds;
	        document.forms[0].elements["addSubscribe"].value = "addSubscribes";
		 }
	   }
	   
	   function confirmSelectsAndColse(boxname){
		   if (!hasChecked(boxname)) {
	          alert("选择不能为空！");
		     	return false;
		   }
	   var selecter = document.all(boxname);
	    if(selecter.length){
	      for(var i=0;i<selecter.length;i++){
	        if(selecter[i].checked){
	           var spid = selecter[i].value;
	             if('  '==getObjByName(spid).innerText){
	                 alert("第 "+(i+1)+" 行，请先创建台账，再选择！");
	                 return false;
	             }
	        }
	      }
	    
	    }
	   
	     
	    chooseSubscribesAndClose(boxname);
	    return true;
	   }
	   
	   function chooseSubscribesAndClose(boxname){
	   	 var selector = document.getElementsByName(boxname);
	     if (!selector) {
	       return false;
	     }
		 var subscribeDtlIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   subscribeDtlIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(subscribeDtlIds,false);
	   }
  </script>
</@htmlPage>