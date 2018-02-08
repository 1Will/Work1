<#include "/pages/includes/eam2008.ftl" />
<@htmlPage title="预算采购明细维护">
     <@ww.form  name="'yearBudget'" action="''" method="'post'" validate="true">   
           <@inputTable>
            <tr>
                <td align="right" valign="top"><span class="required">*</span><label class="label">种类:</label></td>
		 		<td>
		 			<input type="text" name="code" class="underline"  value="维修与保养"  maxlength="150" disabled/>
		 			<a onClick='code_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
                <@ww.textfield label="'单价'" name="'area.code'" value="800" cssClass="'underline'" required="true" />
            </tr>
            <tr>
                <@ww.textfield label="'数量'" name="'area.code'" value="40" cssClass="'underline'" required="true" />
                <@ww.textfield label="'金额'" name="'area.code'" value="32000" cssClass="'underline'" required="true" />
            </tr>
         </@inputTable> 
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" />	          
	         <@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
         </@buttonBar>
     </@ww.form>
       <script language="JavaScript" type="text/JavaScript"> 
		window.onload = function () {
	 		document.all.frame.src='${req.contextPath}/preview/plan/budget/listYearBudgetProductDetail.html';
	 		document.getElementById("yearBudgetDetail").className = "selectedtab";
	 	}
	 	
	 	function code_OpenDialog() {
	      	var url = '${req.contextPath}/popup/productCodeSelector.html';	      		
	      	popupModalDialog(url, 800,400);  
	    }
	 </script>
     <ul id="beautytab">
			<li><a id="yearBudgetDetail"   onclick="activeTab(this);" href='${req.contextPath}/preview/plan/budget/listYearBudgetProductDetail.html' target="frame" class="selectedtab">采购明细列表</a></li>
     </ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>
