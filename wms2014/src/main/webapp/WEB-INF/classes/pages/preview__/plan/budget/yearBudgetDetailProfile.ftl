<#include "/pages/includes/eam2008.ftl" />
<@htmlPage title="预算明细维护">
     <@ww.form  name="'yearBudget'" action="''" method="'post'" validate="true">   
         <@inputTable>
            <tr>
                <td align="right" valign="top"><span class="required">*</span><label class="label">预算分类:</label></td>
		 		<td>
		 			<input type="text" name="code" class="underline"  value="维修与保养"  maxlength="150" disabled/>
		 			<a onClick='code_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
                <@ww.textfield label="'名称'" name="'area.code'" value="'维修与保养'" cssClass="'underline'" required="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'金额'" name="'area.code'" value="600000" cssClass="'underline'" required="true" />
                 <@ww.textarea label="'申报原因'" 
					         name="'reason'" 
					         value="'模具日益磨损，模具的一些自身配件需加工更换，部分模具需整改，送外加工等重'" rows="'3'" cols="'60'"
							 />
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
	 		document.getElementById("yearBudgetDetail").className = "selectedtab";
	 	}
	 	
	 	function code_OpenDialog() {
	      		var url = '${req.contextPath}/popup/budgetCodeSelector.html';	      		
	      		popupModalDialog(url, 800,400);  
	      	}
	 </script>
     <ul id="beautytab">
			<li><a id="yearBudgetDetail"   onclick="activeTab(this);" href='${req.contextPath}/preview/plan/budget/listYearBudgetToolDetail.html' target="frame" class="selectedtab">预算采购明细列表</a></li>
     </ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>
