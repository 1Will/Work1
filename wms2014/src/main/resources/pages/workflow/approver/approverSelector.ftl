<#--$Id: approverSelector.ftl 7922 2007-10-22 02:36:30Z qsun $ -->
<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('approver.title')}">
<base target="_self">
    <@ww.form name="'listForm'" action="'approverSelector'" method="'post'">
        <@ww.token name="searchApproverToken"/>      
        <@listTable>
	      	<tr>
	      		<th><input type="checkbox"  
	      				onclick="for(i=0; i < this.form.elements.length; i++) {if (this.form.elements[i].name=='approverIds') {this.form.elements[i].checked = this.checked;}};updateCheckboxStatus();"/>
	      		</th>
	      		<th>${action.getText('user.id')}</th>
	      		<th>${action.getText('user.name')}</th>
	         </tr>
	         <#if (approvers?exists && approvers.size()>0)>
	         	<#list approvers as appr>
	         		<tr>
	         			<td><input type="checkbox" name="approverIds" value="#{appr.id},${appr.name}" onclick="javascript:updateCheckboxStatus();"/></td>
	         			<td>${appr.loginName?if_exists}</td>
	         			<td>${appr.name?if_exists}</td>
		             </tr>
		     	  </#list>
		      </#if>
	      </@listTable>	
	      <@buttonBar>
	      	<@vbutton name="choose" class="button" value="${action.getText('choose')}" onclick="javascript:chooseApprovers();"/>
	      	<@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
	      </@buttonBar>
	      <script language="javascript">
	      window.onload=function(){
	      document.forms[0].elements["choose"].disabled=true;
	      }
	      function updateCheckboxStatus(){
	      var checkedNumber=0;
	      for(i=0; i < document.forms[0].elements.length; i++) {
	          if (document.forms[0].elements[i].name=='approverIds' && document.forms[0].elements[i].checked){
	           checkedNumber=checkedNumber+1;
	          }
	          if(checkedNumber!=0){
	            document.forms[0].elements["choose"].disabled=false;
	          }
	          else{
	            document.forms[0].elements["choose"].disabled=true;
	          }
	      }
	   }
	      	function chooseApprovers() {
	      		var ary = new Array();
	      		for(i=0; i < document.forms[0].elements.length; i++) {
	      			if (document.forms[0].elements[i].name=='approverIds' && document.forms[0].elements[i].checked) {
	      				var tmp = document.forms[0].elements[i].value.split(",");
	      				var a = new Array(tmp[0], tmp[1]);
	      				ary.push(a);
	      			}
	      		}
	      		returnDialog(ary);
	      	}
	      </script>
    </@ww.form>
</@htmlPage>