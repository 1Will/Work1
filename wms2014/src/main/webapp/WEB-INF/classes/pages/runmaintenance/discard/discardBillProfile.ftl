<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('dicardBill.maintain')}">
  <@ww.form namespace="'/runmaintenance/discard'" name="'discard'" action="'saveDiscardBill'" method="'post'">
    <@ww.token name="saveMigrateToken"/>
     <@ww.hidden name="'isTouch'" value='false'/>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
      <@inputTable>
        <#if discardBill.id?exists>
          <@ww.hidden name="'discardBill.id'" value="${discardBill.id?if_exists}"/>
        </#if>
            <tr>
              <@ww.textfield label="'${action.getText('discardBill.billNo')}'" name="'discardBill.billNo'"   value="'${discardBill.billNo?if_exists}'" cssClass="'underline'"  disabled="true" readonly="true"/>
              <@ww.textfield label="'${action.getText('discardBill.discardBillName')}'" name="'discardBill.discardBillName'"   value="'${discardBill.discardBillName?if_exists}'" cssClass="'underline'"  required="true"/>
            </tr>
            <tr>
               <tr> 
                  <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		 value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		           list="departments" emptyOption="false" disabled="false">
		          </@ww.select>
		           <#assign reportUserName = ''/>
					<#if discardBill.reportUser?exists>
					 <#assign reportUserName = "${discardBill.reportUser.name}" />
			         <#elseif loginUser?exists>
			          <#assign reportUserName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('discard.User')}:</label></td>
	        	<td>
	        		<input type="text" name="reportUser.name" 
	        			class="underline"  value="${reportUserName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		    <a onClick="reportUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		        <#assign reportUserId = ''/>
				<#if discardBill.reportUser?exists>
				 <#assign reportUserId = "${discardBill.reportUser.id}" />
				  <#elseif loginUser?exists>
				  <#assign reportUserId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="reportUser.id" value="${reportUserId}" />
          </tr>
        <tr>
		  <@pp.datePicker label="'${action.getText('reportDate')}'" name="'discardBill.reportDate'"
	     				  value="'${(discardBill.reportDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				  required="true" maxlength="10"/>
	     <@ww.select label="'${action.getText('discardCater')}'"  name="'discardCategory'"  
	    			     listKey="id"  listValue="value" list="discardCategorys"  emptyOption="true"  disabled="false">
	    </@ww.select>				  
        </tr>
        <tr>
          <@ww.textarea  label="'${action.getText('discardReason')}'" 
	        	         name="'discardBill.discardReason'" 
	        	         value="'${discardBill.discardReason?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'"/>
        </tr>
      <#if discardBill.id?exists>
       <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('discard.status')}:</label></td>
            	<td>
		 			<input type="checkbox" name="discardAgree"/>
		 		</td>
		 		<@pp.datePicker label="'${action.getText('discard.descardDatetime')}'" name="'discardBill.discardDate'"
	     		value="'${(discardBill.discardDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
            </tr>
        </#if>
          </@inputTable>
      <@buttonBar>
        <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick= "'return discard_Vlidate();'"/>
	    </#if>
	    <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/discard/listDiscardBillDevice.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	    
	    <#if discardBill.id?exists>
	    
	     	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_devicediscardPdf('#{discardBill.id}')"/>
	      	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_devicediscardXls('#{discardBill.id}')"/>
	   
	    </#if>
	   
	  </@buttonBar>
    </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
   window.onload = function() {
        <#if discardBill.department?exists>
         document.getElementById("department.id").value = #{discardBill.department.id};
        <#elseif loginUser.department?exists>
          document.getElementById("department.id").value = #{loginUser.department.id};
        </#if>
        <#if discardBill.discardCategory?exists>
	       document.forms[0].elements["discardCategory"].value = ${discardBill.discardCategory.id?if_exists};
	   </#if>
	   <#if discardBill.id?exists>
	      var url = 'listDiscardDetail.html?discardBill.id=${discardBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
	          document.all.frame.src = url;
		      document.getElementById("repairItem").className = "selectedtab";
	   </#if>
	   <#if discardBill.id?exists && discardBill.discardAgree>
	      document.forms[0].elements["discardAgree"].checked =true;
	   </#if>
    }
  
       /*
       * 检查人选择
      */
        function reportUser_OpenDialog() {
        var url = "${req.contextPath}/popup/userSelector.html";
    	popupModalDialog(url, 800, 600, discardSelectorHandler);
      }
      function discardSelectorHandler(result) {
         if (null != result) {
	       document.forms[0].elements["reportUser.name"].value=result[1];
	       document.forms[0].elements["reportUser.id"].value=result[0];
	     }      
      }
      function discard_Vlidate() { 
        if(document.getElementById("discardBill.discardBillName").value==''){
	        alert('${action.getText('discardBill.discardBillName.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "discardBill.discardBillName", null, 50)){
				alert("${action.getText('discardBill.discardBillName.Maxlength')}");
				return  false;
			   }
	     }
	  <#-- if(document.getElementById("discardBill.reportDate").value==''){
	        alert('${action.getText('discardBill.reportDate.not.null')}');
	        return false;
	      }
	    var date=document.getElementById("discardBill.reportDate").value;
		if(!isDate("migrate.requestDate")){
		    alert("${action.getText('select.right.discardBill.reportDate')}");
			return false;
		  }
		if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.discardBill.reportDate')}");
		    return false;
		  }  
	   if(document.getElementById("discardBill.discardReason").value!=''){
		if(!isValidLength(document.forms[0], "discardBill.discardReason", null, 250)){
				alert("${action.getText('discardBill.discardReason.length')}");
				return  false;
			   }
			}-->
		if(!validateTime_checkDate()){
			return false;
		}
		if (null != document.forms["discard"].elements["discardAgree"]) {
           if(document.forms["discard"].elements["discardAgree"].checked){ //当所选报废的设备不存在的时候 提示确认保存设备无效
              if(document.forms["discard"].elements["isTouch"].value =="false"){
                 alert("${action.getText('choose.discardDev')}");
                   return false;
              }
           }
         }
        return true;
      }
      /*
       * 打印触发
      */
      function open_devicediscardXls(id){
         var discardBillid=formatDigital(id);
       
         var url='${req.contextPath}/reports/discard/deviceDiscardReportList.xls?discardBillid='+discardBillid;  
         url = encodeURI(url);
         window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
       function open_devicediscardPdf(id){
         var discardBillid=formatDigital(id);
       
         var url='${req.contextPath}/reports/discard/deviceDiscardReportList.pdf?discardBillid='+discardBillid;  
         url = encodeURI(url);
         window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
      /*
       * 验证检查时间的必须输入项，和格式
      */
      function validateTime_checkDate(){
		if($('discardBill.reportDate').value ==""){
			alert("${action.getText('discardBill.reportDate.not.null')}");
			return false;
		}
		if(!isDate("discardBill.reportDate")){
			alert("${action.getText('select.right.discardBill.reportDate')}");
			return false;
		}
		return true;
	  }
    </script>
</@htmlPage>
  <#if discardBill.id?exists>
  	  <ul id="beautytab">
	    	<li><a id="repairItem" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/discard/listDiscardDetail.html?discardBill.id=${discardBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('repairItem')}</a></li>
	    	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
    </#if>