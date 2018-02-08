<#include "../../../includes/eam2008.ftl" />
<@framePage title="${action.getText('checkPointPlanDetail.title')}">
     <@ww.form name="'listForm'" action="'searchCheckPointPlanDetail'" method="'post'">
     <@ww.token name="saveCheckPointPlanDetailToken"/>
     	<#assign listNoRecords = true/>
	     <#if plan.id?exists>
	     <@ww.hidden name="'plan.id'" value="#{plan.id}"/>
	     <@ww.hidden name="'plan.docStatus'" value="${req.getParameter('plan.docStatus')}"/>
	      <#assign itemNo = 1/>
	      <#assign feeSum = 0/>
	      <@titleBar title="${action.getText('checkPointPlanDetail.list')}"/>
	        <@listTable>
	            <tr>
	                <th></th>
	                <th>${action.getText('planDetail.serialNo')}</th>
	                <th>${action.getText('planDetail.part')}</th>
	                <th>${action.getText('planDetail.content')}</th>
	                <th>${action.getText('planDetail.method')}</th>
	                <th>${action.getText('planDetail.tool')}</th>
	                <th>${action.getText('planDetail.rule')}</th>
	                <th>${action.getText('planDetail.fee')}</th>
	                <th>${action.getText('planDetail.comment')}</th>
	            </tr>
	     	   <#if (plan.planDetails.size()>0)>
	     	   		<#assign listNoRecords = false/>
		           <#list plan.planDetails as planDetail>
		               <tr>
		                <td>
		                   <input type="checkbox" name="checkPointPlanDetailIds" value="#{planDetail.id}"/>
		                </td>
		                <td>
		                	<#if docSubmitted | plan.docStatus==2 >
		                		#{itemNo}
		                	<#else>
		                		<a href="#" name="planDetail" onclick="open_detailDialog(#{plan.id},#{planDetail.id});return false;">#{itemNo}</a>
		                	</#if>
		                	<#assign itemNo = itemNo + 1 />
		                </td>
		                <td>${planDetail.part?if_exists}</td>
		                <td>${planDetail.content?if_exists}</td>
		                <td>${planDetail.method?if_exists}</td>
		                <td>${planDetail.tool?if_exists}</td>
		                <td>${planDetail.rule?if_exists}</td>
		                <td>${planDetail.fee?if_exists}</td>
		                <#if planDetail.fee?exists>
		                	<#assign feeSum = feeSum + planDetail.fee/>
		                </#if>
		                <td>${planDetail.comment?if_exists}</td>
		              </tr>
		     	   </#list>
		      </#if>

	      </@listTable> 
	         <@buttonBar>
	         	<input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{plan.id}, null)"/>
	            <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('selectPlanDetail')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"checkPointPlanDetailIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${listNoRecords?string}"/>
	            </@vsubmit>
        </@buttonBar>
        <@ww.hidden name="'plan.feeSum'" value="#{feeSum}"/>
	      </#if>
	      <script language="javascript">
	      	window.onload = function () {
		      	<#if docSubmitted | plan.docStatus==2 >
		      		document.forms["listForm"].elements["delete"].disabled=true;
		      		document.forms["listForm"].elements["new"].disabled=true;
		      	</#if>
		      	var fee = document.forms[0].elements["plan.feeSum"];
		      	//var parentFee=parent.document.forms[0].elements["plan.fee"].value;
		      	//alert(" "+parentFee);
	        	if (fee != undefined && fee.value != undefined) {
	        		parent.document.forms[0].elements["plan.fee"].value = (Number(fee.value));
	        	}
	      	}
	      	function open_detailDialog(planId, detailId) {
	      		var url = '${req.contextPath}/popup/editCheckPointPlanDetail.html?plan.id='+planId;	      		
	      		if (detailId != null) {
	      			url = url + "&detail.id=" + detailId;
	      		}
	      		popupModalDialog(url, 600,300);
	      		self.location.reload();
	      	}
	    </script>
     </@ww.form>
</@framePage>