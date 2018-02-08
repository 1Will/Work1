<#include "../../../includes/eam2008.ftl" />
<@framePage title="${action.getText('checkPointRuleDetail.title')}">
     <@ww.form name="'listForm'" action="'searchCheckPointRuleDetail'" method="'post'">
     <@ww.token name="saveCheckPointRuleDetailToken"/>
     <#assign listNoRecords = true/>
	     <#if rule.id?exists>
	     <@ww.hidden name="'rule.id'" value="#{rule.id}"/>
	      <#assign itemNo = 1/>
	      <#assign feeSum = 0/>
	      <@titleBar title="${action.getText('checkPointruleDetail.list')}"/>
	        <@listTable>
	            <tr>
	                <th></th>
	                <th>${action.getText('ruleDetail.no')}</th>
	                <th>${action.getText('ruleDetail.part')}</th>
	                <th>${action.getText('ruleDetail.content')}</th>
	                <th>${action.getText('ruleDetail.method')}</th>
	                <th>${action.getText('ruleDetail.tool')}</th>
	                <th>${action.getText('ruleDetail.rule')}</th>
	                <th>${action.getText('ruleDetail.fee')}</th>
	                <th>${action.getText('ruleDetail.comment')}</th>
	            </tr>
	     	   <#if (rule.ruleDetails.size()>0)>
	     	   		<#assign listNoRecords = false/>
		           <#list rule.ruleDetails as ruleDetail>
		               <tr>
		                <td>
		                   <input type="checkbox" name="checkPointRuleDetailIds" value="#{ruleDetail.id}"/>
		                </td>
		                <td>
		                	<#if !docSubmitted>
		                		<a href="#" onclick="open_detailDialog(#{rule.id},#{ruleDetail.id});return false;">#{itemNo}</a></td>
		                	<#else>
		                		#{itemNo}</td>
		                	</#if>
		                <#assign itemNo = itemNo + 1 />
		                <td>${ruleDetail.part?if_exists}</td>
		                <td>${ruleDetail.content?if_exists}</td>
		                <td>${ruleDetail.method?if_exists}</td>
		                <td>${ruleDetail.tool?if_exists}</td>
		                <td>${ruleDetail.rule?if_exists}</td>
		                <td>${ruleDetail.fee?if_exists}</td>
		                <#if ruleDetail.fee?exists>
		                	<#assign feeSum = feeSum + ruleDetail.fee/>
		                </#if>
		                <td>${ruleDetail.comment?if_exists}</td>
		              </tr>
		     	   </#list>
		      </#if>
	      </@listTable> 
	         <@buttonBar>
	            <@vbutton name="new"  class="button" value="${action.getText('new')}" onclick="open_detailDialog(#{rule.id}, null)"/>
	            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('checkPointRuleDetail')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"checkPointRuleDetailIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${listNoRecords?string}"/>
	            </@vsubmit>
        </@buttonBar>
        <@ww.hidden name="'rule.feeSum'" value="#{feeSum}"/>
        
	    </#if>
	      <script language="javascript">
	      	<#if docSubmitted>
        			document.forms[0].elements["new"].disabled="true";
        			document.forms[0].elements["delete"].disabled="true";
        	</#if>
	        window.onload = function () {
	        	var fee = document.forms[0].elements["rule.feeSum"];
	        	if (fee != undefined && fee.value != undefined) {
	        		parent.document.forms[0].elements["rule.fee"].value = fee.value;
	        	}	        	
	        }
	        
	      	function open_detailDialog(ruleId, detailId) {
	      		var url = '${req.contextPath}/popup/editCheckPointRuleDetail.html?rule.id='+ruleId;	      		
	      		if (detailId != null) {
	      			url = url + "&detail.id=" + detailId;
	      		}
	      		popupModalDialog(url, 650,350);
	      		self.location.reload();
	      	}
	    </script>
     </@ww.form>
</@framePage>