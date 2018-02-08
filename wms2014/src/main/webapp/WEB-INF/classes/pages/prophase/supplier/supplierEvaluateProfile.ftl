
<#include "../../includes/eam2008.ftl" />
<#assign inventoryEditTitle = ''/>
<#if toolingDevFlag?exists>
  <#if toolingDevFlag == 'DEVICE'>
    <#assign inventoryEditTitle = "${action.getText('supplierEvaluate.edit')}"/>
  <#else>
   <#assign inventoryEditTitle = "${action.getText('toolingsupplierEvaluate.edit')}"/>
  </#if>
</#if>
<@htmlPage title="${inventoryEditTitle}">
    <@ww.form namespace="'/prophase/supplier'" name="'evaluate'" action="'saveSupplierEvaluate'" method="'post'">
        <@ww.token name="saveSupplierEvaluateToken"/>
        <@inputTable>
            <#if supplierEvaluate.id?exists>
                <@ww.hidden name="'supplierEvaluate.id'" value="#{supplierEvaluate.id}"/>
            </#if>
           <#-- <#if supplier.id?exists>
            <@ww.hidden name="'supplier.id'" value="#{supplier.id}"/>
            </#if>-->
             <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
             <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
          <tr>
				<@eam2008_SupplierSelectorLessTr toolingDevFlag="${toolingDevFlag?if_exists}"/>
				<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
			    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
			            list="departments" emptyOption="true" disabled="false"   required="false">
			    </@ww.select> 
		  </tr>
	 	  <tr> 
			    <#assign evaluatePeopleName = ''/>
				<#if supplierEvaluate.evaluateUser?exists>
				 <#assign evaluatePeopleName = "${supplierEvaluate.evaluateUser.name}" />
				 <#elseif loginUser?exists>
				   <#assign evaluatePeopleName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('inventory.people')}:</label></td>
	        	<td>
	        		<input type="text" name="evaluateUser.name" 
	        			class="underline"  value="${evaluatePeopleName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		    <a onClick="receiveUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		        <#assign evaluatePeopleId = ''/>
				<#if supplierEvaluate.evaluateUser?exists>
				 <#assign evaluatePeopleId = "${supplierEvaluate.evaluateUser.id}" />
				  <#elseif loginUser?exists>
				  <#assign evaluatePeopleId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="evaluateUser.id" value="${evaluatePeopleId}" />
				<@pp.datePicker label="'${action.getText('evaluate.year')}'" name="'supplierEvaluateYear'"
	     						value="'${(supplierEvaluate.unitYear?string('yyyy'))?if_exists}'" cssClass="'underline'" size="15" dateFormat="'%Y'"  maxlength="4"/>
		    </tr>
			<tr>
			   <@pp.datePicker label="'${action.getText('evaluate.time')}'" name="'supplierEvaluate.evaluatedate'"
	     						value="'${(supplierEvaluate.evaluatedate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
			   <@ww.textfield label="'${action.getText('supplier.evaluategrade')}'" name="'evaluateMinute'" value="'${supplierEvaluate.evaluateMinute?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true" />	
			</tr>
			<tr>
	            <#assign level=''/>
			 <#if supplierEvaluate.level?exists>
			   <#assign level='${supplierEvaluate.level.value?if_exists}'/>
			 </#if>
			 <@ww.textfield label="'${action.getText('supplier.level')}'" name="'level.id'" value="'${level}'" cssClass="'underline'"   readonly="true" disabled="true" />	
	         <#if supplierEvaluate.level?exists>
	           <input type="hidden" name="evaluateLevelId" value="#{supplierEvaluate.level.id}" />
	         </#if> 
			</tr>
        </@inputTable>
        <@buttonBar>
          <#if !(action.isReadOnly())> 
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateDate();'"/>
          </#if>
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/prophase/supplier/listSupplierEvaluate.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
            <#if supplierEvaluate.id?exists>
              <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_supplierEvaluatePdf('#{supplierEvaluate.id}')"/>
	          <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_supplierEvaluateXls('#{supplierEvaluate.id}')"/>
            </#if>
        </@buttonBar>
    </@ww.form>
     <script language="JavaScript" type="text/JavaScript">
     window.onload = function() {
       <#if supplierEvaluate.id?exists>
		 document.all.frame.src='${req.contextPath}/prophase/supplier/listsupplierEvaluateDetail.html?supplierEvaluate.id=#{supplierEvaluate.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
	     document.getElementById("repairItem").className = "selectedtab";
	   <#else>
	   //新建时默认为当前日期
	     a = new Date();
		 var time=a.format("yyyy-MM-dd");
		 document.forms[0].elements["supplierEvaluate.evaluatedate"].value=time;
		 var time = new Date();
         var year = time.getYear();
		 document.forms[0].elements["supplierEvaluateYear"].value=year; 
	   </#if>
	  <#if loginUser.department?exists>
	     document.getElementById("department.id").value = #{loginUser.department.id};
	   </#if>
	   <#if supplierEvaluate.department?exists>
	     document.getElementById("department.id").value = #{supplierEvaluate.department.id};
	   </#if>
	 }
	    function open_supplierEvaluatePdf(id){
        var supplierEvaluateid=id;
         var url='${req.contextPath}/reports/supplierEvaluate/supplierEvaluateReport.pdf?supplierEvaluateid='+supplierEvaluateid;  
         url = encodeURI(url);
         window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
       function open_supplierEvaluateXls(id){
         var supplierEvaluateid=id;
         var url='${req.contextPath}/reports/supplierEvaluate/supplierEvaluateReport.xls?supplierEvaluateid='+supplierEvaluateid;  
         url = encodeURI(url);
         window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
     function receiveUser_OpenDialog() {
	    var url = "${req.contextPath}/popup/userSelector.html";
	    popupModalDialog(url, 800, 600,managerSelectorHandler);
	 }
	 function managerSelectorHandler(result) {
	   if (null != result) {
	     document.forms[0].elements["evaluateUser.name"].value=result[1];
		 document.forms[0].elements["evaluateUser.id"].value=result[0];
	   }             	
     }
     function validateDate(){
        if(document.getElementById("supplier.supplierNo").value==''){
	        alert('${action.getText('supplier.supplierNo')}');
	        return false;
	      }
	      //验证日期
	    var date=document.getElementById("supplierEvaluate.evaluatedate").value;
		if(document.getElementById("supplierEvaluate.evaluatedate").value!=''&&!isDate("supplierEvaluate.evaluatedate")){
		    alert("${action.getText('select.right.supplierEvaluate.evaluatedate')}");
			return false;
		  }
		if(document.getElementById("supplierEvaluate.evaluatedate").value!=''&&!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.evaluatedate')}");
		    return false;
		  }
		 //验证年度
		 if(document.getElementById("supplierEvaluateYear").value==''){
	      alert("${action.getText('supplierEvaluateyear.not.null')}");
	       return false;
	      }  
		 var year =document.getElementById("supplierEvaluateYear").value;
		 if(isNaN(parseInt(year))){
		    alert("${action.getText('select.right.supplierEvaluateYear')}");
			return false;
		 }
	       return true;
     }
     </script>
     <#if supplierEvaluate.id?exists>
  	  <ul id="beautytab">
	    	<li><a id="repairItem" onclick="activeTab(this);"  href="${req.contextPath}/prophase/supplier/listsupplierEvaluateDetail.html?supplierEvaluate.id=${supplierEvaluate.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('evaluateItem')}</a></li>
	    	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
    </#if>
  </@htmlPage>

