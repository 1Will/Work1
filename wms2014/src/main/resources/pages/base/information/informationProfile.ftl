<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('MsgTitle')}">

    <@ww.form namespace="'/base/msg'" name="'msg'" action="'saveMsg'" method="'post'">
        <@ww.token name="saveMsgToken"/>
        <@inputTable>
            <#if msg.id?exists>
                <@ww.hidden name="'msg.id'" value="#{msg.id}"/>
            </#if>
           
            <tr>
                <@ww.textfield label="'${action.getText('Msg.name')}'" name="'Msg.name'" value="'${Msg.name?if_exists}'" cssClass="'underline'" required="true"/>
               
                 	<@ww.select label="'${action.getText('Msg.msgType')}'" 
                             required="false" name="'msg.msgType'" 
                             value="'${msg.msgType?if_exists}'"
                             listKey="value" listValue="label"
                             list="msgTypes" 
                              emptyOption="true" 
                              disabled="false">
                </@ww.select>
                <@ww.select label="'${action.getText('Msg.msgType')}'" 
                             required="false" name="'msg.msgStatus'" 
                             value="'${msg.msgStatus?if_exists}'"
                             listKey="value" listValue="label"
                             list="msgStatus" 
                              emptyOption="true" 
                              disabled="false">
                </@ww.select>
               
               </tr>
               <tr> 
                <@pp.datePicker label="'${action.getText('Msg.publishDate')}'" name="'accidentBill.accidentOccurDateTm'"
	     							value="'${(Msg.publishDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
               
             
		   		
		   		<#assign receiveUserName = ''/>
					<#if msg.receiveUser?exists>
					 <#assign receiveUserName = "${msg.receiveUser.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('Msg.receiveUser')}:</label></td>
	        	<td>
	        		<input type="text" name="receiveUser.name" 
	        			class="underline"  value="${receiveUserName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		
		    		    <a onClick="receiveUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        	
		        </td>
		        <#assign receiveUserId = ''/>
				<#if msg.receiveUser?exists>
				 <#assign receiveUserId = "${msg.receiveUser.id}" />
				</#if>
				<input type="hidden" name="receiveUser.id" value="${receiveUserId}" />
		   	
            </tr>
            <tr>
            <@ww.textarea  label="'${action.getText('Msg.content')}'" 
	        	         name="'msg.content'" 
	        	         value="'${msg.content?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" />
                
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/msg/listMsgs.html"/>

        </@buttonBar>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript">
    
           window.onload = function (){
           <#--
               <#if msg.msgType?exists>
	     			document.forms["msg"].elements["msg.msgType"].value=#{msg.msgType?if_exists};
	     		</#if>
	     		<#if msg.msgStatus?exists>
	     			document.forms["msg"].elements["msg.msgStatus"].value=#{msg.msgStatus?if_exists};
	     		</#if>
	     		-->
           }
		  function receiveUser_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600,managerSelectorHandler);
		  }
		 function managerSelectorHandler(result) {
		    if (null != result) {
		      document.forms[0].elements["receiveUser.name"].value=result[1];
		      document.forms[0].elements["receiveUser.id"].value=result[0];
		    }             	
		  }
		  
  
  } 
		  </script>
</@htmlPage>
	
		  