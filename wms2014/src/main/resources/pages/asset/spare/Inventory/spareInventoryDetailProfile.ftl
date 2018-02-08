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

<#include "../../../includes/eam2008.ftl" />
<script type='text/javascript' src='${req.contextPath}/dwr/interface/SpareDetailSelectJs.js'></script>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/findLocationByRegionalJs.js'></script> 
<@htmlPage title="${action.getText('盘点明细')}">
<base target="_self">
	<@ww.form name="'saveSpareInventoryDetail'" action="'saveInventoryBillDetail'" method="'post'" validate="true">
		<@ww.token name="saveSpareInventoryDetailToken"/>
		
		 <#if spareInventoryBill.id?exists>
			<@ww.hidden name="'spareInventoryBill.id'" value="#{spareInventoryBill.id}"/>
		</#if>
		
		<#if spareInventoryDetail.id?exists>
		  <@ww.hidden name="'spareInventoryDetail.id'" value="#{spareInventoryDetail.id}"/>
		  <@ww.hidden name="'spareLocation.id'" value="#{spareInventoryDetail.spareLocation.id}"/>
		<#else>  
		   <@ww.hidden name="'spareLocation.id'" value=""/>
		</#if> 
		<input type="hidden" name="strName" value="${spareInventoryDetail.name?if_exists}"/>
		<#---->
		
		<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@inputTable>
			<tr>
				<@ww.textfield label="'${action.getText('code')}'" name="'spareInventoryDetail.code'" value="'${spareInventoryDetail.code?if_exists}'"  cssClass="'underline'" readonly="true"/>
			    <@ww.textfield label="'${action.getText('name')}'" name="'spareInventoryDetail.name'" value="'${spareInventoryDetail.name?if_exists}'"  cssClass="'underline'" required="true"  maxlength="20" onchange="'autoCompleteSpare()'"/>
			</tr>
			<tr>
	        	<@ww.textfield label="'${action.getText('orderNo')}'" name="'spareInventoryDetail.orderNo'" value="'${spareInventoryDetail.orderNo?if_exists}'" cssClass="'underline'" disabled="false" required="false" maxlength="20" />
	        	<@ww.textfield label="'${action.getText('factory')}'" name="'spareInventoryDetail.factoryName'" value="'${spareInventoryDetail.factoryName?if_exists}'" cssClass="'underline'" disabled="false" required="false" onchange="'autoCompleteSpare()'"/>
	        	<#if factory?exists>
			 		<@ww.hidden name="'factory.id'" value="${factory.id?if_exists}"/>
			  	<#else>
					<@ww.hidden name="'factory.id'" value=""/>
			  	</#if>
	         
	        </tr>
			<tr>
			   <@ww.textfield label="'${action.getText('model')}'" name="'spareInventoryDetail.model'" 
					   value="'${spareInventoryDetail.model?if_exists}'" required="true"  
					   cssClass="'underline'"
					   maxlength="50"
					   onchange="'autoCompleteSpare()'" />
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
		             <#if spareInventoryDetail.category?exists>
                          <@ww.param name="'value'"  value="'#{spareInventoryDetail.category.id?if_exists}'" />
                      </#if>
		       </@ww.select>
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
		             <#if spareInventoryDetail.category?exists>
                          <@ww.param name="'value'"  value="'#{spareInventoryDetail.category.id?if_exists}'" />
                      </#if>
		       </@ww.select>
		       -->
			</tr>
			<tr>
			<#--
				<@ww.select label="'${action.getText('detailCategory')}'" 
					required="true" 
					name="'detailCategory.id'" 
		            listKey="id" 
		            listValue="name"
		            list="allDetailCategory" 
		            emptyOption="true" 
		            disabled="false"
		            required="true" 
		            onchange="'autoCompleteSpare()'">
		       </@ww.select>
		      -->
		       <@ww.select label="'${action.getText('unit')}'" 
				     required="false" 
				     name="'calUnit.id'" 
		             listKey="id" 
		             listValue="value" 
		             list="calUnits" 
		             emptyOption="true" 
		             disabled="false" 
		             required="true">
		      </@ww.select>
		          	      <#--单价-->
			 <@ww.textfield label="'${action.getText('unitPrice')}'" name="'spareInventoryDetail.unitPrice'" 
				   value="'${spareInventoryDetail.unitPrice?if_exists}'" cssClass="'underline'" 
				   disabled="false" 
				   required="false" 
				   maxlength="10"
				   readonly="true"
				   onchange="'changeTotalPrice();'"/>	
    	    </tr>
    	    <tr>
    	       <@ww.textfield label="'${action.getText('equipmentName')}'" name="'spareInventoryDetail.equipmentName'" value="'${spareInventoryDetail.equipmentName?if_exists}'" cssClass="'underline'" disabled="false" required="false" maxlength="30"/>
    	       <@ww.textfield label="'${action.getText('equFactoryName')}'" name="'spareInventoryDetail.equFactoryName'" value="'${spareInventoryDetail.equFactoryName?if_exists}'" cssClass="'underline'" disabled="false" required="false" maxlength="30" />
    	    </tr>
	        <tr> 
	       <#--	<@ww.textfield label="'${action.getText('buyeAmount')}'" name="'subscribeDtl.buyeAmount'" value="'${subscribeDtl.buyeAmount?if_exists}'" cssClass="'underline'" disabled="true" required="false" maxlength="30"/>
	       		
	       		<@pp.datePicker label="'${action.getText('交货日期')}'" name="'subscribeDtl.requireDate'"
	     				  value="'${(subscribeDtl.requireDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				  required="false" maxlength="10"/>-->
	     	  <#-- 库存级别 -->
  		     <@ww.select label="'${action.getText('storageGrade')}'" 
	                   name="'storageGrade.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allStorageGrade" 
                       emptyOption="true" 
                       disabled="true"
                       required="true"
                       onchange="'wareHouseCascadeDWR(\"storageGrade.id\",\"warehouse.id\",${loginUser.id?if_exists},\"${action.getText('')}\");'">
                         <#if spareInventoryDetail.storageGrade?exists>
                          <@ww.param name="'value'"  value="'#{spareInventoryDetail.storageGrade.id?if_exists}'" />
                       </#if>
                       
             </@ww.select>  
	     		       	<#-- 仓库 -->
       	 	<@ww.select 
				label="'${action.getText('warehouse')}'" 
				name="'warehouse.id'" 
				required="true" 
				value="'${req.getParameter('warehouse')?if_exists}'" 
				listKey="id" 
				listValue="name"
				list="allWarehouses" 
				disabled="true"
				onchange="'WareCascadeRegionalDWR(\"warehouse.id\",\"regional.id\",\"${action.getText('')}\",\"edit\")'">
			</@ww.select>
	        </tr>
	    
	        <tr>

			
			<#-- 库区 -->
			<@ww.select 
				label="'${action.getText('regional')}'" 
				name="'regional.id'" 
				required="true" 
				value="" 
				listKey="id" 
				listValue="code"
				list="allRegionals"
				onchange="'WareCascadeLocationDWR(\"regional.id\",\"location.id\",\"${action.getText('')}\")'"
				disabled="false">
			</@ww.select>
				        <#-- 库位 -->
			<@ww.select 
				label="'${action.getText('location')}'" 
				name="'location.id'" 
				required="true" 
				value="" 
				listKey="id" 
				listValue="name"
				list="allLocations"
				disabled="false">
			</@ww.select>
	        </tr>
	       
	        <tr>
	        	<@ww.textfield label="'盘点前数量'" name="'spareInventoryDetail.actualNumber'" value="'${spareInventoryDetail.actualNumber?if_exists}'" cssClass="'underline'" readonly="true" required="false" />
	            <@ww.textfield label="'盘点前金额'" name="'spareInventoryDetail.actualTotalPrice'" value="'${(spareInventoryDetail.actualTotalPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'"  required="false" readonly="true"/>
	        </tr>
	        <tr>
	           
	            <@ww.textfield label="'盘点数量'" name="'spareInventoryDetail.inventoryNum'" 
	                value="'${spareInventoryDetail.inventoryNum?if_exists}'" 
	                cssClass="'underline'" 
	                onchange="'changeActualTotalPrice();'" 
	                maxlength="10"
	                required="true" />
	            <@ww.textfield label="'盘点金额'" name="'spareInventoryDetail.inventoryTotalPrice'" value="'${(spareInventoryDetail.inventoryTotalPrice?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" disabled="true" required="false" />
	       </tr>
	       <tr>
	            <@ww.textfield label="'差异'" name="'spareInventoryDetail.different'" value="'${spareInventoryDetail.different?if_exists}'" cssClass="'underline'" disabled="true" required="false" />
	            <@ww.textfield label="'差额'" name="'spareInventoryDetail.marginPrice'" value="'${spareInventoryDetail.marginPrice?if_exists}'" cssClass="'underline'" disabled="true" required="false" />
	       </tr>
	        <tr>
	       
	        <@ww.textarea label="'${action.getText('comment')}'" 
					         name="'spareInventoryDetail.comment'" 
					         value="'${spareInventoryDetail.comment?if_exists}'" 
					         rows="'3'" 
					         cols="'60'"  />   
	        </tr>
		</@inputTable>
		<@buttonBar>
		   
	      <#if !(action.isReadOnly())>
			   <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return subscribeValidate();'"/>
		  </#if>
	      <#if spareInventoryDetail.id?exists>
			  <@hrefButton value="${action.getText('继续新建')}" url="${req.contextPath}/spare/editInventoryBillDetail.html?spareInventoryBill.id=#{spareInventoryBill.id}&toolingDevFlag=${toolingDevFlag?if_exists}"/>	
		  </#if>
		     <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
	
	</@ww.form> 
	
 <script language="javascript">
        <#--品名有引号在 <@ww.textfield/> 显示不出来 放入影藏标签 在取值 麻烦！-->
        
        window.onload=function(){
           var name =  getObjByName("strName").value
	       if(""!= name){
	          getObjByName("spareInventoryDetail.name").value=name;
	       }
       }
      
	    <#--仓库级别级联仓库-->
	   <#if spareInventoryDetail.storageGrade?exists>
          getObjByName('storageGrade.id').value = #{spareInventoryDetail.storageGrade.id?if_exists};
          DWREngine.setAsync(false); 
	     //回调 仓库
	      wareHouseCascadeDWR("storageGrade.id","warehouse.id",#{loginUser.id?if_exists},"${action.getText('')}")
	     //重新设置为异步方式
	      DWREngine.setAsync(true);
        </#if>
        	//仓库
		<#if spareInventoryDetail.warehouse?exists>
			getObjByName('warehouse.id').value='#{spareInventoryDetail.warehouse.id?if_exists}';
	    <#else>
	    	<#if req.getParameter('warehouse.id')?exists>
	    		getObjByName('warehouse.id').value='#{req.getParameter('warehouse.id')?if_exists}';
	    	</#if>
	    </#if> 
	
	
	 	 <#--仓库级联库区-->
     	<#if spareInventoryDetail.warehouse?exists>
			getObjByName('warehouse.id').value='#{spareInventoryDetail.warehouse.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	//获得仓库的值后触发DWR 级联库区 
			WareCascadeRegionalDWR("warehouse.id","regional.id","${action.getText('')}","edit")
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
	 
      </#if>
        //库区
	  <#if spareInventoryDetail.regional?exists>
		    getObjByName('regional.id').value='#{spareInventoryDetail.regional.id}';
		<#elseif req.getParameter('regional.id')?exists>
		    getObjByName('regional.id').value='#{req.getParameter('regional.id')?if_exists}';
	  </#if>
	  <#--库区级联库位-->
	  <#if spareInventoryDetail.location?exists>
			 
			getObjByName('regional.id').value='#{spareInventoryDetail.regional.id}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	//获得仓库的值后触发DWR 级联库区 
			WareCascadeLocationDWR("regional.id","location.id","${action.getText('')}")
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
      </#if>
	  <#if spareInventoryDetail.location?exists>
		    getObjByName('location.id').value='#{spareInventoryDetail.location.id}';
	  <#elseif req.getParameter('location.id')?exists>
		    getObjByName('location.id').value='#{req.getParameter('location.id')?if_exists}';
	  </#if>
	  
         <#--种类 级联明细种类-->
         <#--
	   <#if spareInventoryDetail.category?exists>
     		getObjByName("category.id").value = #{spareInventoryDetail.category.id?if_exists};
     		DWREngine.setAsync(false); 
    		//回调种类的值后触发DWR 级联明细种类  
    		spareTypeCascadeDWR("category.id","detailCategory.id","${action.getText('')}")
    		//重新设置为异步方式
    		DWREngine.setAsync(true);
       </#if>
       -->
       //明细种类
       <#--
       <#if spareInventoryDetail.spareDetailType?exists>
         		getObjByName("detailCategory.id").value = #{spareInventoryDetail.spareDetailType.id?if_exists};
       </#if>
       -->
       //单位
	   	<#if spareInventoryDetail.unit?exists>
         	getObjByName("calUnit.id").value = #{spareInventoryDetail.unit.id?if_exists};
       </#if>
      
      
           // 转换金额的 “,”号(3,333.00)->(3333.00)
       function replaceComma(data){
          var result = "";
          if(0 == data){
             result =0;
          }else{
             var ary = data.split(",");
             for(var i =0;i<ary.length;i++){
               result = result+ary[i];
            }
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
			   var result="";
			 
			   if(s == Math.abs(s)){
			      for(i = 0; i < l.length; i ++ ){   
			          t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
			      } 
			    result = t.split("").reverse().join("") + "." + r;   
			   }else{
			       
			     for(i = 0; i < l.length-1; i ++ ){   
			          t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length-1 ? "," : "");   
			     } 
			     result = t.split("").reverse().join("") + "." + r;  
			     result = "-"+result;
			   
			   }
			   return result;   
      } 
	  
 
	  //改变单价 change 事件
	  function changeTotalPrice(){
	      var price = getObjByName("spareInventoryDetail.unitPrice").value; //单价
	      var beforeNum = getObjByName("spareInventoryDetail.actualNumber").value;//盘点前数量
	      var num = getObjByName("spareInventoryDetail.inventoryNum").value;//盘点数量
	      if("" != beforeNum){
	          beforeNum = replaceComma(beforeNum);
	      }else{
	          beforeNum=0
	      }
	      if("" != price){
	         price = replaceComma(price);
	      }else{
	          price =0;
	      }
	      if(""!=num){
	         num = replaceComma(num);
	      }else{
	         num =0;
	      }
	      if(isDoubleNumber("spareInventoryDetail.unitPrice")){
	          getObjByName("spareInventoryDetail.inventoryTotalPrice").value=toMoney((price*num),2);//盘点金额
	          getObjByName("spareInventoryDetail.actualTotalPrice").value=toMoney((parseFloat(price)*parseInt(beforeNum)),2);//盘点前金额
	          //差额
		     getObjByName("spareInventoryDetail.marginPrice").value=toMoney(((price*num)-(parseFloat(price)*parseInt(beforeNum))),2);
	      }
	     
	  
	  }
	 
	 
		//盘点数量change 事件	
	   function changeActualTotalPrice(){
	       var price = getObjByName("spareInventoryDetail.unitPrice").value; //单价
	       var num = getObjByName("spareInventoryDetail.inventoryNum").value;//盘点数量
	       
	       var beforePrice = getObjByName("spareInventoryDetail.actualTotalPrice").value;//盘点前金额
	       var beforeNum = getObjByName("spareInventoryDetail.actualNumber").value;//盘点前数量
	       
	       if("" == beforeNum){
	          beforeNum=0;
	       } 
	       if(""==beforePrice){
	          beforePrice=0;
	       }else if(0!=beforePrice){
	           beforePrice = replaceComma(beforePrice);
	       }
	       if("" == price){
	          price = 0;
	       }else if(0!=price){
	          price = replaceComma(price);
	       }
	       
	       if("" != num && isDoubleNumber("spareInventoryDetail.inventoryNum")){
	               num = parseInt(replaceComma(num));
	               
	               getObjByName("spareInventoryDetail.inventoryTotalPrice").value=toMoney((price*num),2);//盘点金额
	       
			       //差额
			       getObjByName("spareInventoryDetail.marginPrice").value=toMoney(((price*num)-beforePrice),2);
			        
			       //差异
			       getObjByName("spareInventoryDetail.different").value=(parseInt(num)-parseInt(replaceComma(beforeNum)));
		   }
	   }
  
	  //验证字段格式
	  function subscribeValidate(){
	  	  if(getObjByNameRe("spareInventoryDetail.code").value!=''){//验证图号
	        if(!isValidLength(document.forms[0],"spareInventoryDetail.code",null,50)){
	          alert('${action.getText('spareInventoryDetail.graphNo.length')}');
	          getObjByName("spareInventoryDetail.code").focus();
	          return false;
	         }
	      }
	      if(getObjByNameRe("spareInventoryDetail.name").value==''){//验证品名是否为空
              alert('${action.getText('input.proudctName')}');
              getObjByName("spareInventoryDetail.name").focus();
              return false;	       
	      }
	     if(getObjByNameRe("spareInventoryDetail.name").value!=''){//如果品名不为空,验证品名的输入长度
	        if(!isValidLength(document.forms[0],"spareInventoryDetail.name",null,50)){
	          alert('${action.getText('spareInventoryDetail.name.length')}');
	          getObjByName("spareInventoryDetail.name").focus();
	          return false;
	         }
	     }
	  
	     if(getObjByNameRe("spareInventoryDetail.model").value==''){//验证规格是否为空
               alert('${action.getText('input.spareInventoryDetail.specification')}');
               getObjByName("spareInventoryDetail.model").focus();
               return false;
	     }
	     if(getObjByNameRe("spareInventoryDetail.model").value!=''){//验证规格的输入长度
	        if(!isValidLength(document.forms[0],"spareInventoryDetail.model",null,50)){
	           alert('${action.getText('spareInventoryDetail.specification.length')}');
	          getObjByName("spareInventoryDetail.model").focus();
	          return false;
	         }
	     }
	     if(getObjByNameRe("category.id").value=='' || getObjByNameRe("category.id").value=='-1'){//验证种类是否为空
              alert('${action.getText('input.spareInventoryDetail.category')}');
              getObjByName("category.id").focus();
              return false;
	     }
	     <#--
	     if(getObjByNameRe("detailCategory.id").value=='' || getObjByNameRe("detailCategory.id").value=='-1'){//验证明细种类是否为空
              alert('${action.getText('input.spareInventoryDetail.detailCategory')}');
              getObjByName("detailCategory.id").focus();
              return false;
	      }
	      -->
	      if(getObjByNameRe("calUnit.id").value=='' || getObjByNameRe("calUnit.id").value=='-1'){//验证单位是否为空
              alert('${action.getText('input.spareInventoryDetail.calUnit')}');
              getObjByName("calUnit.id").focus();
              return false;
	      }
	     
	      <#--验证单价是否为空,以及格式 
         if((""==getObjByName("spareInventoryDetail.unitPrice").value)||(0 == getObjByName("spareInventoryDetail.unitPrice").value)) {
                alert("请填入单价");
                getObjByName("spareInventoryDetail.unitPrice").focus();
			    return false;
		 }else if (!isDoubleNumber("spareInventoryDetail.unitPrice")){
			     alert("${action.getText('spareInventoryDetail.unitPrice.isNotNumber')}");
			     getObjByName("spareInventoryDetail.unitPrice").focus();
			     return false;
		 }-->
           
         <#--验证仓库级别-->
         if("" == getObjByName("storageGrade.id").value || -1==getObjByName("storageGrade.id").value){
            alert("请选择仓库级别");
            getObjByName("storageGrade.id").focus();
            return false;
         }
         <#--验证仓库-->
         if("" == getObjByName("warehouse.id").value || -1==getObjByName("warehouse.id").value){
            alert("请选择仓库");
            getObjByName("warehouse.id").focus();
            return false;
         }
         <#--验证库区-->
         if("" == getObjByName("regional.id").value || -1==getObjByName("regional.id").value){
            alert("请选择库区");
            getObjByName("regional.id").focus();
            return false;
         }
         <#--验证库位-->
         if("" == getObjByName("location.id").value || -1==getObjByName("location.id").value){
            alert("请选择库位 ");
            getObjByName("location.id").focus();
            return false;
         }
         <#--验证盘点数量-->
         if("" == getObjByName("spareInventoryDetail.inventoryNum").value){
             alert("请填写盘点数量");
             getObjByName("spareInventoryDetail.inventoryNum").focus();
             return false;
         }else if(!isDoubleNumber("spareInventoryDetail.inventoryNum")){
            alert("盘点数量格式不正确");
            getObjByName("spareInventoryDetail.inventoryNum").focus();
            return false;
         }
       
	     if(getObjByNameRe("spareInventoryDetail.comment").value!=''){//如果备注的长度不为空  验证备注的长度
	         if(!isValidLength(document.forms[0],"spareInventoryDetail.comment",null,250)){
	          alert('${action.getText('text.subscribeDtl.comment.length')}');
	          getObjByName("spareInventoryDetail.comment").focus();
	          return false;
	         }
	     }
	    return true;
	  }		
	  
	  //根据品名、规格型号、种类、明细类自动补全备件信息
	  function autoCompleteSpare(){
	      
	      //获取品名
	      var spareName = getObjByName('spareInventoryDetail.name').value;
	      //获取规格型号
	      var model = getObjByName('spareInventoryDetail.model').value;
	      var orderNo = getObjByName('spareInventoryDetail.orderNo').value;
	      //获取种类
	      var categoryId = getObjByName('category.id').value;
	      //获取明细种类
	      //var spareDetailTypeId= getObjByName('detailCategory.id').value;
	      //获取生产厂家
	      var factoryName = getObjByName('spareInventoryDetail.factoryName').value;
	      //工装或设备标识
	      var toolingDevFlag = getObjByName('toolingDevFlag').value;
	      var storageGrade = getObjByName('storageGrade.id').value;
	      var warehouse = getObjByName('warehouse.id').value;
	      var url = "${req.contextPath}/spare/searchSpareLocationToFrames.html?name=" + spareName
	                 + "&model=" + model + "&orderNo="+orderNo+"&category.id=" + categoryId + "&factory.name="+factoryName;
	      url = url + '&toolingDevFlag=' + toolingDevFlag;
	      url = url + '&storageGrade.id=' + storageGrade + '&warehouse.id=' + warehouse;
	      url = encodeURI(url);
	 
	      parent.frames["buttomFrame"].document.forms[0].action=url;
	      parent.frames["buttomFrame"].document.forms[0].submit(); 
	  }
	</script>
</@htmlPage>