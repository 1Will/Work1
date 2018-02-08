<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('invoices.title')}">
<@ww.form namespace="'/contractManagement'" name="'invoices'" action="'saveInvoices'" method="'post'">
	<@ww.token name="saveInvoicesToken"/>
    <@inputTable>
    	<#if invoices.id?exists>
    		<@ww.hidden id="invoices.id" name="'invoices.id'" value="'#{invoices.id}'"/>
    		<@ww.hidden name="'contractManagement.id'" value="'#{invoices.contractManagement.id?if_exists}'"/>
    		<@ww.hidden name="'productList.id'" value="'#{invoices.productList.id?if_exists}'"/>
    	<#else>
    	    <@ww.hidden id="invoices.id" name="'invoices.id'" value=""/>
    		<@ww.hidden name="'contractManagement.id'" value="'#{contractManagement.id?if_exists}'"/>
    		<@ww.hidden name="'productList.id'" value="'#{productList.id?if_exists}'"/>
    	</#if>
    	<#if invoices.deliveryPerson?exists>
			<@ww.hidden id="deliveryPerson.id" name="'deliveryPerson.id'" value="#{invoices.deliveryPerson.id?if_exists}"/>
		<#else>
			<@ww.hidden id="deliveryPerson.id" name="'deliveryPerson.id'" value=""/>
		</#if>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	
		<tr>
		<@textfield id="deliveryNum" label="${action.getText('invoices.deliveryNum')}" maxlength="10"  name="invoices.deliveryNum"  value="${invoices.deliveryNum?if_exists}"  required="true"   anothername="checkDeliveryNum" disabled="true"/>
	    
			<@pp.datePicker 
				label="'${action.getText('invoices.deliveryDate')}'" 
				name="'invoices.deliveryDate'" 
	   			value="'${(invoices.deliveryDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<script language="javascript">
				<#if invoices.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("invoices.deliveryDate").value==''){
						getObjByName("invoices.deliveryDate").value = date.format("yyyy-MM-dd");
					}
				</#if>
		   </script>
		   </tr>
		   <tr>
		   <!--发货人-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('deliveryPerson.name')}:</label>
	     	</td>
	     	<td>
	     	<#if invoices.deliveryPerson?exists>
				<input type="text"  name="invoices.deliveryPerson.name" class="underline"  value="<#if invoices.deliveryPerson?exists>${invoices.deliveryPerson.name?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
		     <#else>
				<input type="text"  name="invoices.deliveryPerson.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
		    </#if>
	     	
		   		<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		   
		   
		   <@textfield id="deliveryCount" label="${action.getText('invoices.deliveryCount')}" maxlength="10"  name="invoices.deliveryCount"  value="${invoices.deliveryCount?if_exists}"  required="true"   anothername="checkDeliveryCount" onchange="dwr_null()"/>
	    
		   </tr>
			
			
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		
		<#-- 继续新建按钮    -->
			<#if invoices.id?exists>
			<@vbutton class="button" name="button" value="${action.getText('newNext')}" onclick="editInvoices_OpenDialog();"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" 
				url="${req.contextPath}/contractManagement/editInvoices.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			 getObjByName("newNext").disabled="true";
			</script>
			</#if>
			
		<@vbutton class="button" name="button" value="${action.getText('close')}" onclick="close_OK();"/>
    </@buttonBar>

</@ww.form>
 <script type='text/javascript' src='${req.contextPath}/dwr/interface/DeliveryCount.js'></script>
<script type="text/javascript">
     var b=true;
	function editInvoices_OpenDialog(){
	 var itemNo=getObjByName("contractManagement.id").value;
	 var itemNo_1=getObjByName("productList.id").value;
	 var url ="${req.contextPath}/contractManagement/editInvoices.html?contractManagement.id="+itemNo+"&productList.id="+itemNo_1;
	 window.location.href=url;
	}
	function close_OK(){
	self.opener.parent.location.reload();
	window.close();
	}
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
		    getObjByName("deliveryPerson.id").value=result[0];
			getObjByName("invoices.deliveryPerson.name").value=result[2];
		}
	}
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
	   if('' == getObjByName('invoices.deliveryDate').value){
	    alert("${action.getText('please.choose.deliveryDate')}");
			getObjByName('invoices.deliveryDate').focus();
     		return false;
	   }
	
		/* 验证发货人*/
		if('' == getObjByName('invoices.deliveryPerson.name').value){
			alert("${action.getText('please.chose.deliveryPerson')}");
			getObjByName('invoices.deliveryPerson.name').focus();
     		return false;
		}
		if('' == getObjByName('invoices.deliveryCount').value){
			alert("${action.getText('please.input.number')}");
			getObjByName('invoices.deliveryCount').focus();
     		return false;
		}else{
		   var count=getObjByName('invoices.deliveryCount').value;
		   var re = /^[0-9]*[1-9][0-9]*$/ ; 
		   if(!re.test(count)){
		      getObjByName('invoices.deliveryCount').value='';
		      alert("${action.getText('please.input.ture.number')}");
		   } 
           return re.test(count);  
		}
		if(!b){
		return false;
		}
		return true;
		
	}
    
    function dwr_null(){
    if (getObjByName('invoices.deliveryCount').value!=''){
    	var count = getObjByName('invoices.deliveryCount').value;
    	var id=getObjByName('productList.id').value;
    	var iId=-1;
    	if(getObjByName('invoices.id').value!=""){
    	   iId=getObjByName('invoices.id').value;
    	}else{
		   var count=getObjByName('invoices.deliveryCount').value;
		   var re = /^[0-9]*[1-9][0-9]*$/ ; 
		   if(!re.test(count)){
		      getObjByName('invoices.deliveryCount').value='';
		      alert("${action.getText('please.input.ture.number')}");
		      return re.test(count);
		   } 
		}
    	<!-- 通过日期在数据库中查询数据，异步方式 -->
		DWREngine.setAsync(false);
		DeliveryCount.deliveryCountIsOrNot(count,id,iId,deliveryCount);
		DWREngine.setAsync(true);
		}
    }
	function deliveryCount(data){
	   if(!data){
	     alert('${action.getText('you.input.tolarger.number')}');
	     getObjByName('invoices.deliveryCount').value='';
	     b=false;
	   }else{
	     b=true;
	   }
    }
</script>
</@htmlPage>
