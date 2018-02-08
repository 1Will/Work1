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
<#--$Id: purchaseOrderProfile.ftl 11328 2008-09-25 09:39:30Z smzhang $ -->

<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareInventoryProfile.title')}">
<@ww.form namespace="'/spare'"  name="'spareInventoryBill'" action="'saveSpareInventoryBill'" method="'post'">
    <@ww.token name="saveSpareInventoryBillToken"/>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <@inputTable>
        <#if spareInventoryBill.id?exists>
            <@ww.hidden name="'spareInventoryBill.id'" value="#{spareInventoryBill.id}"/>
        </#if>
        <tr>
            <@ww.textfield label="'${action.getText('spareInventory.NO')}'"  name="'spareInventoryBill.inventoryNo'" value="'${spareInventoryBill.inventoryNo?if_exists}'" cssClass="'underline'" disabled="true"/>
            <@ww.textfield label="'${action.getText('spareInventory.Name')}'"  name="'spareInventoryBill.name'" value="'${spareInventoryBill.name?if_exists}'" cssClass="'underline'" required="true"/>
        </tr>
        <tr>
	  	  <#-- 库存级别 -->
  		 <@ww.select label="'${action.getText('storageGrade')}'" 
	                   name="'storageGrade.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allStorageGrade" 
                       emptyOption="true" 
                       disabled="false"
                       required="true" 
                       onchange="'wareHouseCascadeDWR(\"storageGrade.id\",\"warehouse.id\",${loginUser.id?if_exists},\"${action.getText('')}\");'">
                         <#if spareInventoryBill.storageGrade?exists>
                          <@ww.param name="'value'"  value="'#{spareInventoryBill.storageGrade.id?if_exists}'" />
                       </#if>
                       
         </@ww.select> 
            <@ww.select 
				label="'${action.getText('spareInventory.warehouse')}'" 
				required="true" 
				name="'warehouse.id'" 
				value="'${req.getParameter('warehouse.id')?if_exists}'" 
				listKey="id" 
				listValue="name"
				list="allWarehouses" 
				emptyOption="true" 
				disabled="false">
			</@ww.select>
       	 </tr>
         <tr>
           <@pp.remotePicker label="'${action.getText('spareInventory.person')}'" 
       	            name="'spareInventoryBill.inventoryPeople'"
                    selectorName="'userSelectorAjax'" 
                    cssClass="'underline'" 
                    value="spareInventoryBill.inventoryPeople"
                    popup="'${req.contextPath}/popup/userSelector.html'" 
                    size="15" codeName="'loginName'" 
                    required="true"/>
           <@pp.datePicker label="'${action.getText('spareInventory.Date')}'" 
                    name="'spareInventoryBill.inventoryDateTm'"
					value="'${(spareInventoryBill.inventoryDateTm?string('yyyy-MM-dd'))?if_exists}'" 
					cssClass="'underline'" 
					required="true" 
					size="15"/>  

        </tr>
         <tr>
          <@ww.textfield label="'${action.getText('spareInventory.totalPrice')}'"  name="'spareInventoryBill.totalPrice'" value="'${spareInventoryBill.totalPrice?if_exists}'" cssClass="'underline'" disabled="'true'"/>    
         <@ww.textarea label="'${action.getText('spareInventory.comment')}'"  name="'spareInventoryBill.comment'" value="'${spareInventoryBill.comment?if_exists}'" rows="'3'" cols="'50'" />
		</tr>
    </@inputTable>
    <@buttonBar>
       <#if !(action.isReadOnly())>
	       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return saveValidate();'"/>
	       <#if spareInventoryBill.id?exists>
	         <@redirectButton value="${action.getText('继续新建')}" 
                 url="${req.contextPath}/spare/editInventoryBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	   	   </#if>
	   	</#if>
	   	   
	    <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/spare/listSpareInventory.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	   	    
	   	 <#if spareInventoryBill.id?exists>
		   	 <@vbutton name="print"  class="button" value="${action.getText('pdfPanDianPrint')}" onclick="open_inventoryPdf('#{spareInventoryBill.id}')"/>
		     <@vbutton name="print"  class="button" value="${action.getText('xlsPanDianPrint')}" onclick="open_inventoryXls('#{spareInventoryBill.id}')"/>
		     <#--
		     <#if !(action.isReadOnly())>
		         <@vsubmit name="'submitRecord'" value="'${action.getText('sendsubmit')}'" />
		     </#if>
		     -->
       </#if>
    </@buttonBar>
</@ww.form>

<script language="javascript">

        <#if spareInventoryBill.storageGrade?exists>
          $('storageGrade.id').value = #{spareInventoryBill.storageGrade.id?if_exists};
    
          DWREngine.setAsync(false); 
	    		//回调种类的值后触发DWR 级联明细种类  
	      wareHouseCascadeDWR("storageGrade.id","warehouse.id",#{loginUser.id?if_exists},"${action.getText('')}")
	    		//重新设置为异步方式
	      DWREngine.setAsync(true);
       
        </#if>
        <#--
        	//仓库
		<#if spareInventoryBill.warehouse?exists>
			$('warehouse.id').value='#{spareInventoryBill.warehouse.id?if_exists}';
	    <#else>
	    	<#if req.getParameter('warehouse.id')?exists>
	    		$('warehouse.id').value='${req.getParameter('warehouse.id')?if_exists}';
	    	</#if>
	    </#if> 
       -->

  /**
  *页面加载时Tab
  **/
 window.onload=function(){
   <#if spareInventoryBill.id?exists>
	    var url = 'listSpareInventoryBillDetails.html?spareInventoryBill.id=#{spareInventoryBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
	    document.all.frame.src = url;
		document.getElementById("details").className = "selectedtab";
		</#if>
	<#if !spareInventoryBill.id?exists>
	  a = new Date();
	  var time=a.format("yyyy-MM-dd");
	 $("spareInventoryBill.inventoryDateTm").value=time;
	</#if>
	<#--
	<#if spareInventoryBill.id?exists>
		<#if spareInventoryBill.submit==false>
		  $("submitRecord").disabled="true";
		 <#else>
		  $("submitRecord").disabled="false";
		</#if>
	</#if>
	-->
	//仓库
	<#if spareInventoryBill.warehouse?exists>
		$('warehouse.id').value='#{spareInventoryBill.warehouse.id?if_exists}';
	<#else>
	<#if req.getParameter('warehouse.id')?exists>
	    $('warehouse.id').value='${req.getParameter('warehouse.id')?if_exists}';
	</#if>
 </#if>
	
  }
 function saveValidate(){

	 /**
	 * 验证盘点单名称
	 */    
	 if($("spareInventoryBill.name").value==''){
        alert('${action.getText('spareInventory.name.not.null')}');
        $("spareInventoryBill.name").focus();
        return false;
	 }else{
        if(!isValidLength(document.forms[0], "spareInventoryBill.name", null, 50)){
			alert("${action.getText('spareInventory.name.length')}");
			$("spareInventoryBill.name").focus();
			return  false;
		}
	 }
	    
	       //验证仓库级别
	 if($('storageGrade.id').value==''||$('storageGrade.id').value=='-1'){
	    	alert('请选择仓库级别');
	    	$('storageGrade.id').focus();
	    	return false;
	  }
	    //验证仓库
	  if($('warehouse.id').value==''||$('warehouse.id').value=='-1'){
	    	alert('${action.getText('spareInventory.warehouse.not.null')}');
	    	$('warehouse.id').focus();
	    	return false;
	  }
	 /**
	 *  验证盘点日期
	 */
	 if(document.getElementById("spareInventoryBill.inventoryDateTm").value==''){
	        alert('${action.getText('spareInventory.inventoryDateTmnot.null')}');
	        return false;
	 }
	    var date=document.getElementById("spareInventoryBill.inventoryDateTm").value;
		if(!isDate("spareInventoryBill.inventoryDateTm")){
		    alert("${action.getText('select.right.spareInventory.inventoryDateTm')}");
			return false;
		  }
		<#--if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.spareInventoryBill.inventoryDateTm')}");
		    return false;
		  }-->
		  /**
		  * 验证盘点人
		  */
	   if(document.getElementById("spareInventoryBill.inventoryPeople.id").value==''){
	        alert('${action.getText('spareInventory.inventoryPeople.not.null')}');
	        return false;
	       }  
	    /**
	    *验证备注
	    */    
	  if(document.getElementById("spareInventoryBill.comment").value!=''){
		if(!isValidLength(document.forms[0], "spareInventoryBill.comment", null, 250)){
				alert("${action.getText('spareInventory.comment.length')}");
				return  false;
		}
		
	}	
}
	function open_inventoryPdf(id){
		var inventoryId = id;
		var url='${req.contextPath}/reports/spare/spareInventoryNew.pdf?inventoryId='+inventoryId;  
		url = encodeURI(url);
		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	}	
	function open_inventoryXls(id){
		var inventoryId = id;
		var url='${req.contextPath}/reports/spare/spareInventoryNew.xls?inventoryId='+inventoryId;  
		url = encodeURI(url);
		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	}
</script>
    <#if spareInventoryBill.id?exists>
		<ul id="beautytab">
			<li><a id="details" onclick="activeTab(this);"  
				href="listSpareInventoryBillDetails.html?&spareInventoryBill.id=#{spareInventoryBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" 
				target="frame" >${action.getText('spareInventoryBillDetail')}</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>