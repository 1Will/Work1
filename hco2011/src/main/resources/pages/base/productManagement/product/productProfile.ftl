
<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('products.info.profile')}">
	<@ww.form namespace="'/productsManager'" name="'listForm'" action="'saveProductsAction'" method="'post'">
		<@ww.token name="saveProductsActionToken"/>
		<@inputTable>
			<#if products.id?exists>
                <@ww.hidden name="'products.id'" value="#{products.id?if_exists}"/>
            </#if>
            <#if products.supplier?exists>
                <@ww.hidden id="supplier.name"  name="'supplier.id'" value="#{products.supplier.id?if_exists}"/>
            <#else>
                <@ww.hidden id="supplier.name"  name="'supplier.id'" value=""/>
            </#if>
            <@ww.hidden id="'isSaved'" name="'isSaved'" value="''"/>
            <@ww.hidden name="'products.ptId'" value="'${req.getParameter('products.ptId')?if_exists}'"/>
            <@ww.hidden name="'products.psId'" value="'${req.getParameter('products.psId')?if_exists}'"/>
            <@ww.hidden name="'products.supplierId'" value="'${req.getParameter('products.supplierId')?if_exists}'"/>
            <@ww.hidden name="'isNoM'" value="1"/>
			<tr>
	           	<@ww.textfield label="'${action.getText('products.code')}'" name="'products.code'" value="'${products.code?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('products.name')}'" name="'products.name'" value="'${products.name?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('products.model')}'" name="'products.model'" value="'${products.model?if_exists}'" cssClass="'underline'" required="true"/>
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('products.standard')}'" name="'products.standard'" value="'${products.standard?if_exists}'" cssClass="'underline'" required="true"/>
				<@ww.textfield label="'${action.getText('products.etcPrice')}'" name="'products.etcPrice'" value="'${products.etcPrice?if_exists}'" cssClass="'underline'"/>
				<@ww.textfield label="'${action.getText('products.salePrice')}'" name="'products.salePrice'" value="'${products.salePrice?if_exists}'" cssClass="'underline'"/>
			</tr>
			
			<tr>
				<@ww.textfield label="'${action.getText('products.salelimit')}'" name="'products.salelimit'" value="'${products.salelimit?if_exists}'" cssClass="'underline'"/>
			    <@ww.select label="'${action.getText('products.pt.id')}'" 
		           name="'pt.id'" 
			       value="'${req.getParameter('pt.id')?if_exists}'" 
			       listKey="id" 
			       listValue="name"
		           list="allType" 
		           emptyOption="false" 
		           disabled="false"
		           required="true">
		           </@ww.select>
		          <#--
			     <script language="javascript">
			     	<#if products.pt?exists>
			     		getObjByName('pt.id').value = ${products.pt.id};
			     	</#if>
				</script>
				-->
				<@ww.select 
		    		label="'${action.getText('products.productSource')}'"
					required="true"
					name="'product_source_ID.id'" 
					value="${req.getParameter('product_source_ID.id')?if_exists}" 
					listKey="id"
					listValue="name"
				    list="allProductSource"
			    	emptyOption="false" 
			    	disabled="false">
			    	</@ww.select>
			    <script language="javascript">
			     	<#if products.product_source_ID?exists>
			     		getObjByName('product_source_ID.id').value = '${products.product_source_ID.id}';
			     	</#if>
				</script>
			</tr>
			<tr>
			
				<td align="right" valign="top">
		       		<label class="label">${action.getText('供应商')}:</label>
		     	</td>
				<td>
					<#if products.supplier?exists>
						<input type="text" id="supplier.name"  name="supplier.name" class="underline"  value="${products.supplier.name}" maxlength="140" size="20" disabled="true"/>
		            <#else>
						<input type="text" id="supplier.name"  name="supplier.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
		            </#if>
					<a onClick="supplier_OpenDialog();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
					
					<td align="right"><label for="" class="label">${action.getText('products.isNoMain')}:</label></td>
			        <td align="left">
			        	<input type="radio" id="products.isNoMain1" name="products.isNoMain" value=false checked>是
			        	<input type="radio" id="products.isNoMain2" name="products.isNoMain" value=true>否
			        	<script language="javascript">
				     		if(${(products.isNoMain?string)?if_exists}==true){
				     			getObjByName('products.isNoMain1').checked = true;
				     		}else if(${(products.isNoMain?string)?if_exists}==false){
				     			getObjByName('products.isNoMain2').checked = true;
				     		}
						</script>
					</td>
					<@pp.datePicker 
						label="'${action.getText('products.launch')}'" 
						name="'products.launch'" 
			   			value="'${(products.launch?string('yyyy-MM-dd'))?if_exists}'"
						cssClass="'underline'" 
						required="false"
						dateFormat="'%Y-%m-%d'"
						maxlength="10"/>
					<script language="javascript">
						<#if products.id?exists>
						<#else>
							var date = new Date();
							if(getObjByName("products.launch").value==''){
								getObjByName("products.launch").value = date.format("yyyy-MM-dd");
							}
						</#if>
					</script>
			</tr>
			<tr>
		    	<td align="right" valign="top">
		    		<label class="label">${action.getText('products.remark')}:</label>
		    	</td>
		        <td colspan="10">
		        	<textarea name="products.remark" rows="4" cols="150" >${products.remark?if_exists}</textarea>
		        </td>
	   		</tr>
		</@inputTable>
		<@buttonBar>
        <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'">
            </@vsubmit>
        </#if>
        
        <#if products.isSaved?exists &&products.isSaved=='0' >
	    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
	    <#else>
	    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
	    </#if>
        
        <#if backFlag?exists>
        <input type="button" value="关闭" onclick="window.close()">
            <#else>
             <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/productsManager/listProducts.html" /> 
            </#if>
	</@buttonBar>
	</@ww.form>
<script language="javascript">
	function supplier_OpenDialog(){
		var url ="${req.contextPath}/supplierManager/listSupplierWindow.html";
		popupModalDialog(url, 800, 600, setSupplier);
	}
	
	function setSupplier(data){
		getObjByName('supplier.id').value =data[0];
		getObjByName('supplier.name').value =data[2]
	}


	function saleLimit(){
		var etcPrice = getObjByName('products.etcPrice').value;
		var salePrice = getObjByName('products.salePrice').value;
		getObjByName('products.salelimit').value =((salePrice-etcPrice)/etcPrice)*100;
	}
	
	function formatNumber(num,exponent) {
		  if (exponent<1) return num;
		  var str = num.toString();
		  if (str.indexOf(".")!=-1) {
		    if (str.split(".")[1].length>=exponent) {
		      return str;
		    } else {
		      return formatNumber(str+"0",exponent);
		    }
		  } else {
		    return formatNumber(str+".0",exponent);
		  }
	}
	<#if products.pt?exists>
 		getObjByName('pt.id').value = ${products.pt.id};
 	</#if>
 	<#if products.supplier?exists>
 		getObjByName('supplier.id').value = ${products.supplier.id};
 	</#if>
	if(0 == getObjByName('pt.id').value){
 		getObjByName('pt.id').value = getObjByName('products.ptId').value;
 	}
 	if(-1 == getObjByName('product_source_ID.id').value){
 		getObjByName('product_source_ID.id').value = getObjByName('products.psId').value;
 	}
 	if(-1 == getObjByName('supplier.id').value){
 		getObjByName('supplier.id').value = getObjByName('products.supplierId').value;
 	}
 	
 	function savee(){
     	getObjByName('isSaved').value=0;
		return storeValidation();
	}
	function submitt(){
     	getObjByName('isSaved').value=1;
		return storeValidation();
	}
 	
	function storeValidation(){
		var code = getObjByName('products.code').value;
		var name = getObjByName('products.name').value;
		var model = getObjByName('products.model').value;
		var standard = getObjByName('products.standard').value;
		var etcPrice = getObjByName('products.etcPrice').value;
		var salePrice = getObjByName('products.salePrice').value;
		<#-- 编号 1-->
		if(code==""){
			alert('${action.getText('products.code.not.null')}');
			getObjByName('products.code').focus();
			return false;
		}
		if(!isValidLength(document.forms[0], "products.code", null, 20)){
			alert('${action.getText('products.code.length')}');
			code="";
			getObjByName('products.code').focus();
			return  false;
		}
		<#-- 名称 2-->
		if(name==""){
			alert('${action.getText('products.name.not.null')}');
			getObjByName('products.name').focus();
			return false;
		}
		if(!isValidLength(document.forms[0], "products.name", null, 20)){
			alert('${action.getText('products.name.length')}');
			name="";
			getObjByName('products.name').focus();
			return  false;
		}
		<#-- 型号 3-->
		if(model==""){
			alert('${action.getText('products.model.not.null')}');
			getObjByName('products.model').focus();
			return false;
		}
		if(!isValidLength(document.forms[0], "products.model", null, 20)){
			alert('${action.getText('products.model.length')}');
			model="";
			getObjByName('products.model').focus();
			return  false;
		}
		<#-- 规格 4-->
		if(standard==""){
			alert('${action.getText('products.standard.not.null')}');
			getObjByName('products.standard').focus();
			return false;
		}
		if(!isValidLength(document.forms[0], "products.standard", null, 20)){
			standard="";
			alert('${action.getText('products.standard.length')}');
			getObjByName('products.standard').focus();
			return  false;
		}
		<#-- 成本价 5-->
		if(!isDoubleNumberBetweenBoolean(formatDigital(getObjByName('products.etcPrice').value), 1000000001, -1) && getObjByName('products.etcPrice').value != ''){
			alert('${action.getText('etcPrice.format')}');
			etcPrice ="";
			getObjByName('products.etcPrice').focus();
			return false;
		}
		<#-- 销售价 6-->
		if(!isDoubleNumberBetweenBoolean(formatDigital(getObjByName('products.salePrice').value), 1000000001, -1) && getObjByName('products.salePrice').value != ''){
			alert('${action.getText('products.salePrice.format')}');
			salePrice="";
			getObjByName('products.salePrice').focus();
			return false;
		}
		<#-- 销售额度 7-->
		if(getObjByName('products.salelimit').value != '' && !isValidLength(document.forms[0], "products.salelimit", null, 20)){
       		alert('${action.getText('products.salelimit.error')}');
       		getObjByName('products.salelimit').value="";
       		getObjByName('products.salelimit').focus();
       		return false;
	    }
		<#-- 类别 8-->
		if(getObjByName('pt.id').value==0){
			alert('${action.getText('pt.id.null')}');
			getObjByName('pt.id').focus();
			return false;
		}
		<#-- 来源 9-->
		if(getObjByName('product_source_ID.id').value==-1 || getObjByName('product_source_ID.id').value==''){
			alert("${action.getText('products.productSource.not.null')}");
			getObjByName('product_source_ID.id').focus();
			return false;
		}
		<#-- 供应商 10-->
		if(getObjByName('supplier.id').value==-1){
			document.forms[0].elements["supplier.id"].value='';
		}
		
		<#-- 主营 11-->
		if (document.forms[0].elements["products.isNoMain1"].checked) {
       		document.forms[0].elements["isNoM"].value=1;
	    } else {
	        document.forms[0].elements["isNoM"].value=0;
	    }
	    <#-- 推出时间 12-->
		  if('' != document.forms["listForm"].elements["products.launch"].value){
		 	 if(!isDate('products.launch')){
				 alert("${action.getText('products.launch.dateFormate.error')}");
				 getObjByName('products.launch').value="";
				 getObjByName('products.launch').focus();
				 return false;
			 } 
		 }
		 <#-- 备注 13-->
       if(!isValidLength(document.forms[0], "products.remark", null, 250) && '' !=getObjByName('products.remark').value){
		   alert("${action.getText('products.remark.length')}");
		   getObjByName('products.remark').value="";
		   getObjByName('products.remark').focus();
		   return  false;
	   }
		return true;
	}
</script>
</@htmlPage>
<#if products.id?exists>
<ul id="beautytab">
	<li>
		<a id="project" onclick="activeTab(this);"  href='${req.contextPath}/projectInfo/listProPro.html?products.id=#{products.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('项目列表')}</a>
	</li>
	<li>
		<a id="contractInfo" onclick="activeTab(this);" href='${req.contextPath}/contractManagement/listContractManagementByCustomerAction.html?products.id=#{products.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('合同列表')}</a>
	</li>
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?products.id=#{products.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('附件资料')}</a>
	</li>
	
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/productsManager/listProductsPerson.html?products.id=#{products.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('开发团队')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/projectInfo/listProPro.html?products.id=#{products.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="50%"/>
</#if>
