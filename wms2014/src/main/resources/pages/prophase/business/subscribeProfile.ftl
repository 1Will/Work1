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
<#--$Id: subscribeProfile.ftl 11279 2008-03-12 01:12:13Z mwei $ -->

<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('subscribe.title')}">
	<@ww.form namespace="'/prophase/business'" name="'subscribe'" action="'saveSubscribe'" method="'post'">
		<@ww.token name="saveSubscribeToken"/>
		<#if subscribe.id?exists>
		 <@ww.hidden name="'subscribe.id'" value="#{subscribe.id}"/>  
		 </#if>
		 <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
         <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
        <#if subscribe.status?exists>
        <@ww.hidden name="'status'" value="'${subscribe.status}'"/>
        </#if>
		<@inputTable>
		   <tr>
			 <@ww.textfield label="'${action.getText('subscribe.billNo')}'" name="'subscribe.billNo'" value="'${subscribe.billNo?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true" />	
		     <@ww.textfield label="'${action.getText('subscribe.name')}'" name="'subscribe.name'" value="'${subscribe.name?if_exists}'" cssClass="'underline'"    disabled="false" required="true"/>	
		   </tr>
		   <tr>
		   	 <@ww.select label="'${action.getText('typeStatus')}'"  name="'typeStatus'" 
	    			     listKey="value" listValue="label" value="'${subscribe.typeStatus?if_exists}'"
	                     list="results"
	                     required="false" emptyOption="true" disabled="false" required="true">
	    	 </@ww.select>
	    	 <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		     </@ww.select>
		   </tr>
		   <tr>
		   	 <@eam2008_FeeSourceTypeSelector toolingDevFlag="${toolingDevFlag?if_exists}"/>
		   </tr>
		     <#assign buyingPersonName = ''/>
				<#if subscribe.buyingPerson?exists>
				<#assign buyingPersonName = "${subscribe.buyingPerson.name}" />
			    <#elseif loginUser?exists>
			    <#assign buyingPersonName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('buy.requestpeople')}:</label></td>
	        	<td>
	        		<input type="text" name="buyingPerson.name" 
	        			class="underline"  value="${buyingPersonName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		    <a onClick="buyUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		     <#assign buyingPersonNameId = ''/>
				<#if subscribe.buyingPerson?exists>
				<#assign buyingPersonNameId = "${subscribe.buyingPerson.id}" />
				<#elseif loginUser?exists>
				<#assign buyingPersonNameId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="buyingPerson.id" value="${buyingPersonNameId}" />
		
	     			 <@pp.datePicker label="'${action.getText('buyDate')}'" name="'subscribe.subscribeDate'"
					value="'${(subscribe.subscribeDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="true" size="15"/>
		   </tr>
		   <#--
		   <tr>
		    <@ww.select label="'${action.getText('fee.source')}'"  name="'feeSource'" 
		    			     listKey="realCode" listValue="value" value="'${subscribe.feeSource?if_exists}'"
		                     list="feeSourceTypes" onchange="'changeBudgeNo()'" 
		                     emptyOption="true"   required="true">
		    	</@ww.select>
		 	  	<td id="budgetNoTitle" align="right" valign="top" style="display:none">
	 	  	        <span class="required"></span>
		 	  	<label class="label">${action.getText('subscrible.budgetNo')}:</label></td>
		        <td id="budgetNoContent" style="display:none">
		        	<input type="text" name="subscribe.budgetNo" 
		        			class="underline"  value="${subscribe.budgetNo?if_exists}"  maxlength="150" size="20"/>
			    </td>
		   </tr>
		   -->
		  <#-- <@eam2008_FeeSourceTypeSelector toolingDevFlag="${toolingDevFlag?if_exists}"/>-->
		   <tr>
	     	<#-- 我的修改 <@ww.textfield label="'${action.getText('subscribe.totalPrice')}'" name="'subscribe.totalPrice'" value="'${subscribe.totalPrice?if_exists}'" cssClass="'underline'"   readonly="true" disabled="true" />	-->
	     	<@ww.hidden name="'subscribe.status'" value="'${subscribe.status?if_exists}'"/>
	     	<@ww.select label="'${action.getText('status')}'"  name="'status'" 
	    			     listKey="value" listValue="label" value="'${subscribe.status?if_exists}'"
	                     list="status"
	                     emptyOption="true" disabled="true" required="true">
	    	 </@ww.select>
	    	 <@ww.select label="'${action.getText('subscribe.detailKind')}'" 
	                   required="true" 
	                   name="'detailKind.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allDetailKind" 
                       emptyOption="true" 
                       disabled="false">
                       <#if subscribe.detailKind?exists>
                       <@ww.param name="'value'"  value="'${subscribe.detailKind.id?if_exists}'" />
                        </#if>
                 </@ww.select>
	     	</tr>
	     	<tr>
	     		<#-- 我的修改
	  			<@ww.select label="'${action.getText('subscribe.detailKind')}'" 
	                   required="true" 
	                   name="'detailKind.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allDetailKind" 
                       emptyOption="true" 
                       disabled="false">
                       <#if subscribe.detailKind?exists>
                       <@ww.param name="'value'"  value="'${subscribe.detailKind.id?if_exists}'" />
                        </#if>
                 </@ww.select>
                 -->
	        	<#--  <@ww.select label="'${action.getText('type')}'" 
                             required="false" name="'type'" 
                             value="'${type?if_exists}'"
                             listKey="value" listValue="label"
                             list="results" 
                              emptyOption="false" 
                              disabled="false">
                   </@ww.select>	-->	  
		   </tr>
		   <tr>
		    <@ww.textarea  label="'${action.getText('subscribe.reason')}'" 
	        	         	name="'subscribe.reason'" 
	        	         	value="'${subscribe.reason?if_exists}'"  
	        	         	rows="3" cols="50" cssClass="'underline'"/>
		   		<@ww.textarea  label="'${action.getText('subscribe.comment')}'" 
	        	         	name="'subscribe.comment'" 
	        	         	value="'${subscribe.comment?if_exists}'"  
	        	         	rows="3" cols="50" cssClass="'underline'"/>
		   </tr>
		</@inputTable>
		<@buttonBar>
		
           <#if !(action.isReadOnly())>
		   	 <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return saveValidate();'"/>
		   	   <#if subscribe.id?exists>
		      	 <@redirectButton value="${action.getText('继续新建')}" url="${req.contextPath}/prophase/business/editSubscribe.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>
		      </#if>
		   </#if>
		   <#if  report?if_exists=="report">
		       <@redirectButton name="back" value="${action.getText('back')}" 
		        url="${req.contextPath}/tooling/report/listSubscribeDetailMonthReport.html"/>
		   <#else>
		      <@redirectButton name="back" value="${action.getText('back')}" 
		      url="${req.contextPath}/prophase/business/listSubscribes.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>
		   </#if>
		     
		   <#if (subscribe.id)?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_subscribePdf('#{subscribe.id}')"/>
		    <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_subscribeXls('#{subscribe.id}')"/>
	       </#if>
		</@buttonBar>
	</@ww.form>
<script language="javascript">	
	  window.onload = function()  { 
	    
	  	 <#if subscribe.status!='NEWPURCHASE'>
	  	      
	  	      if(null!= document.forms[0].elements["save"]){
	  	         document.forms[0].elements["save"].disabled="true";
	  	      }
      	 	  
         </#if>	 
        	   
	     <#if subscribe.department?exists>
	       document.forms[0].elements["department.id"].value=#{subscribe.department.id?if_exists};
	     <#elseif loginUser.department?exists>
	       getObjByNameRe("department.id").value = #{loginUser.department.id};
	     </#if>
		 <#if subscribe.id?exists>
		 var url = '${req.contextPath}/prophase/business/listSubscribeDetails.html?subscribe.id=#{subscribe.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
		 	  document.all.frame.src = url;
		      getObjByNameRe("details").className = "selectedtab";
	     </#if>
	     <#if subscribe.typeStatus?exists>
	       document.forms[0].elements["typeStatus"].value='${subscribe.typeStatus?if_exists}';
	       <#else>	       
	        document.forms[0].elements["typeStatus"].value='SPARE';
	     </#if>
	   
	     <#if subscribe.status?exists>
         	getObjByNameRe("status").value = '${subscribe.status}';
         </#if>
          <#if subscribe.feeSource?exists> 
	   		<#else>
	   		getObjByNameRe("sourceType").value='IN_BUDGET';
	   		 getObjByName('budgetNoTitle').style.display='inline';
			 getObjByName('budgetNoContent').style.display='inline';
	    </#if>
         //单据类型
		<#if subscribe.detailKind?exists>
         getObjByNameRe("detailKind.id").value = #{subscribe.detailKind.id};
         <#else>
          getObjByNameRe("detailKind.id").value='204';
       </#if>
	   }
	    <#--
	    /*
	     * 如果费用来源是计划内,则显示预算编号字段
	     */	
		if(document.forms[0].elements["feeSource"].value=='IN_BUDGET'){
	     	  getObjByNameRe("budgetNoTitle").style.display='inline';
	     	  getObjByNameRe("budgetNoContent").style.display='inline';
			}
		}	
		
	    function changeBudgeNo() {
	        if(document.forms[0].elements["feeSource"].value=='IN_BUDGET'){
	     	  getObjByNameRe("budgetNoTitle").style.display='inline';
	     	  getObjByNameRe("budgetNoContent").style.display='inline';
		    }
		    if(document.forms[0].elements["feeSource"].value=='OUT_BUDGET'){
	     	  getObjByNameRe("budgetNoTitle").style.display='none';
	     	  getObjByNameRe("budgetNoContent").style.display='none';
		    }
		    return true;
	      }
	      -->
     /*
      * 申购人
      */	
      function buyUser_OpenDialog() {
	    var url = "${req.contextPath}/popup/userSelector.html";
	    popupModalDialog(url, 800, 600, buyUserSelectorHandler);
	  }
	  function buyUserSelectorHandler(result) {
		if(null != result) {
		   document.forms["subscribe"].elements["buyingPerson.id"].value = result[0];
		   document.forms["subscribe"].elements["buyingPerson.name"].value = result[1];
		} 
	  }
	 /*
	  * 申购单保存时的验证
	  */		  
	  function  saveValidate(){
	  	//验证申购单名称
	     if(getObjByNameRe("subscribe.name").value==''){
	        alert('${action.getText('input.subscribe.name')}');
	        return false;
	     }
	     //验证申购单名称长度
         if ('' != document.forms[0].elements["subscribe.name"].value) {
           if (!isValidLength(document.forms[0], "subscribe.name", null, 50)) {
             alert("${action.getText('subscribe.name.maxLength')}");
             return false;
           }
         }
	    //验证申购单类型
	     if(getObjByNameRe("typeStatus").value==''){
	        alert('${action.getText('input.typeStatus')}');
	        return false;
	     }
	     
       //验证费用来源
       
         if(getObjByNameRe("sourceType").value==''){
	        alert('${action.getText('select.sourceType.name')}');
	        return false;
	     }
	     
	     /**验证日期 */
	      if(getObjByNameRe("subscribe.subscribeDate").value==''){
           alert('${action.getText('input.subscribeb.date')}');
           return false;	     
	      }  
	      var date=getObjByNameRe("subscribe.subscribeDate").value;
	      if(!isDate("subscribe.subscribeDate")){
	        alert('${action.getText('select.right.subscribeDate')}');
	        return false; 
	      }
	      <#--
	      if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.subscribeDate')}");
		    return false;
		  }
		  -->
		  if(getObjByNameRe("subscribe.reason").value!=''){
		     if(!isValidLength(document.forms[0], "subscribe.reason", null, 250)){
				alert("${action.getText('subscribe.reason.length')}");
				return  false;
			   }
			}
		  if(getObjByNameRe("subscribe.comment").value!=''){
		     if(!isValidLength(document.forms[0], "subscribe.comment", null, 250)){
				alert("${action.getText('subscribe.comment.length')}");
				return  false;
			   }
			}
		  if(getObjByNameRe("department.id").value=='-1'){//如果当前用户不属于任何部门，把部门Id至为空
             getObjByNameRe("department.id").value='';
             return true;	     
	      }   	
	       return true;
	   }
	   function open_subscribePdf(id){
	       var subscribeid = id;
	       var url='${req.contextPath}/reports/subscribe/deviceSubscribesDetail.pdf?subscribeid='+subscribeid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     }	
	   function open_subscribeXls(id){
	       var subscribeid = id;
	       var url='${req.contextPath}/reports/subscribe/deviceSubscribesDetail.xls?subscribeid='+subscribeid;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	     }    
</script>
	<#if subscribe.id?exists>
		<ul id="beautytab">
			<li><a id="details" onclick="activeTab(this);"  
				href="listSubscribeDetails.html?subscribe.id=#{subscribe.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('subscribeDetail')}</a>
			</li>			
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>	

	