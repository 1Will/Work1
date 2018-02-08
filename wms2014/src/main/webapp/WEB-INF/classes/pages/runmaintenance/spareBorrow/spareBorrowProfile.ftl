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

<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareBorrowProfile.title')}">
<@ww.form namespace="'/runmaintenance/spareBorrow'"  name="'spareBorrow'" action="'saveSpareBorrow'" method="'post'">
    <@ww.token name="saveSpareInventoryBillToken"/>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
        <#if spareBorrow.status?exists>
         <@ww.hidden name="'status'" value="'${spareBorrow.status}'"/>
         </#if>
    <@inputTable>
        <#if spareBorrow.id?exists>
            <@ww.hidden name="'spareBorrow.id'" value="#{spareBorrow.id}"/>
        </#if>
        <tr>
            <@ww.textfield label="'${action.getText('spareBorrowBillNo')}'"  name="'spareBorrow.billNo'" value="'${spareBorrow.billNo?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
            <@ww.textfield label="'${action.getText('spareBorrowBillName')}'"  name="'spareBorrow.name'" value="'${spareBorrow.name?if_exists}'" cssClass="'underline'" required="true"/>
        </tr>
      
        <tr>
        	<@ww.select label="'${action.getText('department')}'" 
		                   required="true" 
		                   name="'department.id'" 
	    			       value="'${req.getParameter('department.id')?if_exists}'" 
	    			       listKey="id" 
	    			       listValue="name"
	                       list="departments" 
	                       emptyOption="true" 
	                       disabled="false">
	            </@ww.select>
           <@pp.datePicker label="'${action.getText('borrowDateTime')}'" name="'spareBorrow.borrowDate'"
					value="'${(spareBorrow.borrowDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="true" size="15"/>
       </tr>
       	<tr>
       	  <@pp.remotePicker label="'${action.getText('borrow.Person')}'" name="'spareBorrow1.borrowUser'"
                    selectorName="'userSelectorAjax'" cssClass="'underline'" value="spareBorrow.borrowUser"
                    popup="'${req.contextPath}/popup/userSelector.html'" size="15" codeName="'loginName'" required="true"/>
        <@pp.remotePicker label="'${action.getText('appor.Person')}'" name="'spareBorrow1.approvalUser'"
                    selectorName="'userSelectorAjax'" cssClass="'underline'" value="spareBorrow.approvalUser"
                    popup="'${req.contextPath}/popup/userSelector.html'" size="15" codeName="'loginName'" required="false"/>           
     
       	 </tr>
         <tr>
         <#assign status=''/>
        	<#if spareBorrow.status?exists>
		       <#if '${spareBorrow.status}' == 'NEWSTATUS'>
		       <#assign status = "${action.getText('NEWSTATUS')}"/>
		       <#elseif '${spareBorrow.status}' == 'BORROWING'>
		       <#assign status = "${action.getText('BORROWING')}"/>
		       <#elseif '${spareBorrow.status}' == 'BORROWED'>
		       <#assign status = "${action.getText('BORROWED')}"/>
        	</#if>
        	</#if>
	    		<@ww.textfield label="'${action.getText('status')}'" name="'spareBorrow.status'" value="'${status}'" cssClass="'underline'" readonly="true"/>
         	<@ww.textarea label="'${action.getText('description')}'"  name="'spareBorrow.description'" value="'${spareBorrow.description?if_exists}'" rows="'3'" cols="'50'" />
		</tr>
    </@inputTable>
    <@buttonBar>
    <#if !(action.isReadOnly())>
        <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate()'"/>
   	 </#if>
   	    <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/spareBorrow/listSpareBorrowBills.html?toolingDevFlag=${req.getParameter('toolingDevFlag')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
    	<#if (spareBorrow.id)?exists>
    	<@vbutton name="print"  class="button" value="${action.getText('pdfBorrowPrint')}" onclick="open_spareBorrowBillPdf('#{spareBorrow.id}')"/>
		<@vbutton name="print"  class="button" value="${action.getText('xlsBorrowPrint')}" onclick="open_spareBorrowBillXls('#{spareBorrow.id}')"/>
    	</#if>
    </@buttonBar>
</@ww.form>
 <script language="javascript">	
   window.onload=function(){
     <#if loginUser.department?exists>
	     document.getElementById("department.id").value = #{loginUser.department.id};
	   </#if>
	   <#if spareBorrow.department?exists>
	     document.forms[0].elements["department.id"].value=#{spareBorrow.department.id?if_exists};
	   </#if>
	   <#if spareBorrow.status?exists>
	      document.forms[0].elements["status"].value='${spareBorrow.status}';
	   </#if>
	    <#if spareBorrow.id?exists>
		   var url = 'listSpareBorrowDetails.html?spareBorrow.id=#{spareBorrow.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	       document.all.frame.src = url;
		   document.getElementById("details").className = "selectedtab";
	    </#if>
	   }
	  function validate(){
	 /**
    *   验证领用单编号
    */
   <#-- if(document.getElementById("spareBorrow.billNo").value==''){
	        alert('${action.getText('spareBorrow.billNo.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "spareBorrow.billNo", null, 50)){
				alert("${action.getText('spareBorrow.billNo.length')}");
				return  false;
			   }
	     } -->
	     	 /**
	 * 验证领用单名称
	 */    
	 if(document.getElementById("spareBorrow.name").value==''){
	        alert('${action.getText('spareBorrow.name.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "spareBorrow.name", null, 50)){
				alert("${action.getText('spareBorrow.name.length')}");
				return  false;
			   }
	    }  
	  /*
	  *验证领用部门
	  */
	  if(document.getElementById("department.id").value==''){
	        alert('${action.getText('spareBorrow.department.id.not.null')}');
	        return false;
	     }
	  /**
	    验证领用日期
	  */  
	  if(document.getElementById("spareBorrow.borrowDate").value==''){
	        alert('${action.getText('spareBorrow.borrowDate.not.null')}');
	        return false;
	      }
	    var date=document.getElementById("spareBorrow.borrowDate").value;
		if(!isDate("spareBorrow.borrowDate")){
		    alert("${action.getText('select.right.spareBorrow.borrowDate')}");
			return false;
		  }  
	     /**
		  * 验证领用人
		  */
	   if(document.getElementById("spareBorrow1.borrowUser.id").value==''){
	        alert('${action.getText('spareBorrow.borrowUser.id.not.null')}');
	        return false;
	       }  
	   /**
	    *验证用途说明
	    */    
	  if(document.getElementById("spareBorrow.description").value!=''){
		if(!isValidLength(document.forms[0], "spareBorrow.description", null, 250)){
				alert("${action.getText('spareInventoryBill.comment.length')}");
				return  false;
		}
	}
	  return true;
 }
  function open_spareBorrowBillPdf(id){
		var spareBorrowBillId = id;
		var url='${req.contextPath}/reports/spare/spareBorrowBillDetail.pdf?spareBorrowBillId='+spareBorrowBillId;  
		url = encodeURI(url);
		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	}	
	function open_spareBorrowBillXls(id){
		var spareBorrowBillId = id;
		var url='${req.contextPath}/reports/spare/spareBorrowBillDetail.xls?spareBorrowBillId='+spareBorrowBillId;  
		url = encodeURI(url);
		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	}	
 </script> 
   <#if spareBorrow.id?exists>
		<ul id="beautytab">
			<li><a id="details" onclick="activeTab(this);"  
				href="listSpareBorrowDetails.html?spareBorrow.id=#{spareBorrow.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('spareSpareBorrowDetail')}</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>