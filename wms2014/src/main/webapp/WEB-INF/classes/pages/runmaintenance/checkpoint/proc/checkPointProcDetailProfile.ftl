<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('checkPointProc.detailProfileTitle')}">
 <base target="_self">  			
    <@ww.form  name="'detail'" action="'saveCheckPointProcDetail'" method="'post'" validate="true">  
     <@ww.token name="saveCheckPointProcDetailToken"/>   
     <@ww.hidden name="'proc.id'" value="'${req.getParameter('proc.id')?if_exists}'"/>
	 <@ww.hidden name="'detail.id'" value="'${req.getParameter('detail.id')?if_exists}'"/>
	  
        <@inputTable>
	     <tr>
	      <@ww.textfield label="'${action.getText('planDetail.part')}'" name="'detail.planDetail.part'" value="'${detail.planDetail.part?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" />
	      <@ww.textfield label="'${action.getText('planDetail.content')}'" name="'detail.planDetail.content'" value="'${detail.planDetail.content?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" />
	     </tr>
	     <tr>
	     <@ww.textfield label="'${action.getText('planDetail.method')}'" name="'detail.planDetail.method'" value="'${detail.planDetail.method?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" />
	      <@ww.textfield label="'${action.getText('planDetail.tool')}'" name="'detail.planDetail.tool'" value="'${detail.planDetail.tool?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" />
	     </tr>
	     <tr>
	     <@ww.textfield label="'${action.getText('planDetail.rule')}'" name="'detail.planDetail.rule'" value="'${detail.planDetail.rule?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" />
	     <#if detail.planDetail?exists>
	        <@ww.textfield label="'${action.getText('checkPointProc.fee')}'" name="'detail.NOfee'" value="'${detail.planDetail.fee?if_exists}'" cssClass="'underline'"  readonly="true" disabled="true"/>
	      <#else>
	        <@ww.textfield label="'${action.getText('checkPointProc.fee')}'" name="'detail.NOfee'" value="" cssClass="'underline'"  readonly="true" disabled="true"/>
	      </#if>
	     </tr>
	     <tr>
	     	<@ww.select id="checkType" label="'${action.getText('checkPointProc.result')}'" name="'detail.checkResult'"  value="'${detail.checkResult?if_exists}'"
	                	listKey="value" listValue="label" list="checkResults"  onchange="'showExceptionBox();'"/>
	                	
	                	<#--
	      <td align="right" valign="top"><label class="label">${action.getText('checkPointProc.result')}</label></td>		       
		        <td>
					<select id="checkType" onchange="showExceptionBox();">
						<option value="1">${action.getText('checkPointProc.standard')}</option>
						<option value="2">${action.getText('checkPointProc.exception')}</option>
					<#if detail.exceptionDescription?exists>
        					<@ww.hidden id="exbox" name="'detail.checkResult'" value="'${detail.checkResult?if_exists}'"/>
        			</#if>
					</select>
				</td>
				-->
		  <@ww.textfield label="'${action.getText('checkPointProc.executefee')}'" name="'detail.fee'" value="'${detail.fee?if_exists}'" cssClass="'underline'"  />
	      </tr>
	      <tr>
            <td align="right" vlign="middle" rowspan="3">
              <label id="descript" class="label">${action.getText('procDetail.comment')}:</label>
            </td>
            <td rowspan="3" colspan="3">
              <textarea name="detail.comment" rows="3" cols="60">${detail.comment?if_exists}</textarea>
            </td>
          </tr>
        </@inputTable>       
       	<@buttonBar>
       	    <@vsubmit name="'save'" value="'${action.getText('save')}'" />
       		<@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
        </@buttonBar>
        <script>
		window.onload = function () {
	     for(var i=0;i<2;i++)
	     {
	         if(document.getElementById("checkType").options[i].value==document.getElementById("checkType").value)
	         {
	         	document.forms[0].elements["detail.checkResult"].value=document.getElementById("checkType").options[i].value;
	            document.getElementById("checkType").options[i].selected="true";
	            if (document.getElementById("checkType").options[i].value == 'NORMAL')
	            {
	               document.getElementById("descript").innerText='${action.getText('procDetail.comment')}:';        
	            }
	            else{
	              document.getElementById("descript").innerText='${action.getText('procDetail.exceptionComment')}:';              
	            }
	         }
	     }
	 	}
 	
		function showExceptionBox() {
			var i = document.getElementById("checkType").value;
			/*if (2 == i ) {*/
			if ('EXCEPTION' == i) {
			   	document.forms[0].elements["detail.checkResult"].value="EXCEPTION";
	            document.getElementById("descript").innerText='${action.getText('procDetail.exceptionComment')}:';
	            document.forms[0].elements["detail.comment"].value='';
			} else if ('NORMAL' == i) {
			    document.forms[0].elements["detail.checkResult"].value="NORMAL";
	            document.getElementById("descript").innerText='${action.getText('procDetail.comment')}:'; 
	            document.forms[0].elements["detail.comment"].value='';
			}
		}
	  </script>
    </@ww.form>
</@htmlPage>