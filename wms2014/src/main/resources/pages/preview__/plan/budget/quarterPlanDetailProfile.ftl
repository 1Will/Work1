<#include "/pages/includes/eam2008.ftl" />
<@htmlPage title="计划明细维护">
     <@ww.form  name="'yearBudget'" action="''" method="'post'" validate="true">   
         <@inputTable>
            <tr>
                <td align="right" valign="top"><span class="required">*</span><label class="label">计划分类:</label></td>
		 		<td>
		 			<input type="text" name="code" class="underline"  value="模具复制"  maxlength="150" disabled/>
		 			<a onClick='code_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
                <@ww.textfield label="'计划名称'" name="'area.code'" value="'模具复制'" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'金额'" name="'area.code'" value="100000" cssClass="'underline'" required="true" />
            </tr>
         </@inputTable> 
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" />	          
	         <@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
         </@buttonBar>
     </@ww.form>
       <script language="JavaScript" type="text/JavaScript"> 
		window.onload = function () {
	 		document.all.frame.src='${req.contextPath}/preview/plan/budget/listYearBudgetToolDetail.html';
	 		getObjByNameRe("yearBudgetDetail").className = "selectedtab";
	 	}
	 	
	 	function code_OpenDialog() {
	      		var url = '${req.contextPath}/popup/budgetCodeSelector.html';	      		
	      		popupModalDialog(url, 800,400);  
	      	}
	 </script>
     <ul id="beautytab">
			<li><a id="yearBudgetDetail"   onclick="activeTab(this);" href='${req.contextPath}/preview/plan/budget/listYearBudgetToolDetail.html' target="frame" class="selectedtab">预算详细列表</a></li>
     </ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>
