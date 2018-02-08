<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('申购单汇总维护')}">
	
     <@ww.form  name="'subscribeCollectBill'" namespace="'/devicesubscribeSummary'" action="'saveSubscribeCollectBill'" method="'post'">
       <@ww.token name="saveSubscribeCollectBillToken"/>
       <#if subscribeCollectBill.id?exists>
		 <@ww.hidden name="'subscribeCollectBill.id'" value="#{subscribeCollectBill.id}"/>  
		 </#if>
     	<#if subscribeCollectBill.billStatus?exists>
        <@ww.hidden name="'status'" value="'${subscribeCollectBill.billStatus}'"/>
          <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        </#if>
         <@inputTable>
             <tr>
              	 <@ww.textfield label="'${action.getText('汇总单编码')}'" name="'subscribeCollectBill.code'" value="'${subscribeCollectBill.code?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true" />	
		   		  <@ww.textfield label="'${action.getText('汇总单名称')}'" name="'subscribeCollectBill.name'" value="'${subscribeCollectBill.name?if_exists}'" cssClass="'underline'"    disabled="false" required="true"/>	
              </tr>
           <tr>
              <#assign collectPersonName = ''/>
				<#if subscribeCollectBill.collectPerson?exists>
				<#assign collectPersonName = "${subscribeCollectBill.collectPerson.name}" />
			    <#elseif loginUser?exists>
			    <#assign collectPersonName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('汇总人')}:</label></td>
	        	<td>
	        		<input type="text" name="collectPerson.name" 
	        			class="underline"  value="${collectPersonName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
	    		    <a onClick="buyUser_OpenDialog();">
	        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	        	    </a>
		        </td>
		     <#assign collectPersonNameId = ''/>
				<#if subscribeCollectBill.collectPerson?exists>
				<#assign collectPersonNameId = "${subscribeCollectBill.collectPerson.id}" />
				<#elseif loginUser?exists>
				<#assign collectPersonNameId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="collectPerson.id" value="${collectPersonNameId}" />
               <@ww.select label="'${action.getText('department')}'" required="true" name="'collectDept.id'" 
		    		value="'${req.getParameter('collectDept.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		     </@ww.select>
           </tr>
           <tr>
             <@pp.datePicker label="'${action.getText('汇总日期')}'" name="'subscribeCollectBill.collectDate'"
					value="'${(subscribeCollectBill.collectDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="true" size="15"/>
                <@ww.select label="'${action.getText('状态')}'"  name="'billStatus'" 
	    			     listKey="value" listValue="label" value="'${subscribeCollectBill.billStatus?if_exists}'"
	                     list="status"
	                     emptyOption="true" disabled="true" required="true">
	    	 </@ww.select>	    	 
           </tr>
	    	  <tr>
		   		<@ww.textarea  label="'${action.getText('备注')}'" 
	        	         	name="'subscribeCollectBill.comment'" 
	        	         	value="'${subscribeCollectBill.comment?if_exists}'"  
	        	         	rows="3" cols="50" cssClass="'underline'"/>
		   </tr>
         </@inputTable>
         
      <@buttonBar>
		   <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return saveValidate();'"/>
		   <@redirectButton name="" value="${action.getText('back')}" url="${req.contextPath}/devicesubscribeSummary/listSubscribeCollectBill.html"/>
		  <#if (subscribeCollectBill.id)?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_subscribeCollectBillPdf('#{subscribeCollectBill.id}')"/>
		    <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_subscribeCollectBillXls('#{subscribeCollectBill.id}')"/>
	       </#if>
         </@buttonBar>
         
     </@ww.form>
   
      <script type="text/javascript">
      window.onload = function()  {  
    	<#if subscribeCollectBill.billStatus!='NEW'>
      	 	 document.forms[0].elements["save"].disabled="true";      	 	
        </#if>
   	   <#if subscribeCollectBill.collectDept?exists>
	       document.forms[0].elements["collectDept.id"].value=#{subscribeCollectBill.collectDept.id?if_exists};
	     <#elseif loginUser.department?exists>
	       getObjByNameRe("collectDept.id").value = #{loginUser.department.id};
	     </#if>
	     
	      <#if subscribeCollectBill.id?exists>
			 var url = '${req.contextPath}/devicesubscribeSummary/listSubscribeCollectBillDetails.html?subscribeCollectBill.id=#{subscribeCollectBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}';		
			  document.all.frame.src = url;
		      getObjByNameRe("details").className = "selectedtab";
	     </#if>
	   
	     <#if subscribeCollectBill.billStatus?exists>
         	getObjByNameRe("billStatus").value = '${subscribeCollectBill.billStatus}';
         </#if>
       }
        function buyUser_OpenDialog() {
	    	var url = "${req.contextPath}/popup/userSelector.html";
	    	popupModalDialog(url, 800, 600, buyUserSelectorHandler);
		}  
		function buyUserSelectorHandler(result) {
			if(null != result) {
			   document.forms["subscribeCollectBill"].elements["collectPerson.id"].value = result[0];
			   document.forms["subscribeCollectBill"].elements["collectPerson.name"].value = result[1];
			} 
	 	 }
	  
	  function saveValidate(){	 		 	
	  	//验证申购单名称
	     if(document.forms["subscribeCollectBill"].elements["subscribeCollectBill.name"].value==''){	     
	        alert('${action.getText('input.subscribeCollectBill.name')}');
	        return false;
	     }	   
            /**验证日期 */
	      if(document.forms["subscribeCollectBill"].elements["subscribeCollectBill.collectDate"].value==''){
           alert('${action.getText('input.subscribeCollectBill.date')}');
           return false;	     
	      }  
	      var date=document.forms["subscribeCollectBill"].elements["subscribeCollectBill.collectDate"].value;
	      if(!isDate("subscribeCollectBill.collectDate")){
	        alert('${action.getText('select.right.subscribeDate')}');
	        return false; 
	      }
	      if(document.forms["subscribeCollectBill"].elements["collectDept.id"].value=='-1'){//如果当前用户不属于任何部门，把部门Id至为空
             getObjByNameRe("collectDept.id").value='';
             return true;	     
	      }  
	  	return true;
	  }
	  
	     function open_subscribeCollectBillPdf(id){
	       var subscribeCollectBillid = id;
	       var url='${req.contextPath}/reports/subscribeCollectBill/subscribeCollectBill.pdf?subscribeCollectBillid='+subscribeCollectBillid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     }	
	   function open_subscribeCollectBillXls(id){
	       var subscribeCollectBillid = id;
	       var url='${req.contextPath}/reports/subscribeCollectBill/subscribeCollectBill.xls?subscribeCollectBillid='+subscribeCollectBillid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     } 
      </script>
	<#if subscribeCollectBill.id?exists>
		<ul id="beautytab">
			<li><a id="details" onclick="activeTab(this);"  
				href="listSubscribeCollectBillDetails.html?subscribeCollectBill.id=#{subscribeCollectBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('申购汇总单明细')}</a>
			</li>
		</ul>
		<iframe name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>