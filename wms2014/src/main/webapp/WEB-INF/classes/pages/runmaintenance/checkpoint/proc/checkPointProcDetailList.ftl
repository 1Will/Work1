<#include "../../../includes/eam2008.ftl" />

<@framePage title="${action.getText('attachTool.title')}">
     <@ww.form name="'checkPointProcDetail'" action="'searchCheckPointProcsDetail'" method="'post'">
     <@ww.token name="searchCheckPointProcsDetailToken"/>
     	<#if proc.id?exists>
	      <@ww.hidden name="'proc.id'" value="#{proc.id}"/>	  
	      <#assign itemNo = 1/>  
	      <#assign feeSum = 0/> 
	    </#if>
	         <#if proc.id?exists>	         
	         <@titleBar title="${action.getText('checkPointProcDetail.list')}"/>
	             <@listTable>
	        	     <tr>
	                     <th>${action.getText('checkPointProcDetail.serial')}</th>
	                     <th>${action.getText('planDetail.part')}</th>
	                     <th>${action.getText('planDetail.content')}</th>
	                     <th>${action.getText('planDetail.method')}</th>
	                     <th>${action.getText('planDetail.tool')}</th>
	                     <th>${action.getText('planDetail.rule')}</th>
	                     <th>${action.getText('planDetail.comment')}</th>
	                     <th>${action.getText('checkPointProcDetail.result')}</th>
	                     <th>${action.getText('procDetail.planDetail.exceptionDescription')}</th>
	                     <th>${action.getText('checkPointProc.fee')}</th>
	                     <th>${action.getText('checkPointProc.executefee')}</th>
	                 </tr>
	               <#if (proc.procDetails.size()>0)>
		           <#list proc.procDetails as procDetail>
		                <tr>
		                    <td>
		                    	<#if !docSubmitted>
		                    		<a href="#" onclick="open_detailDialog(#{proc.id},#{procDetail.id});return false;">#{itemNo}</a></td>
		                    	<#else>
		                    		#{itemNo}
		                    	</#if>
		                    <#assign itemNo=itemNo+1 />
		                    <td>${procDetail.planDetail.part?if_exists}</td>
		                    <td>${procDetail.planDetail.content?if_exists}</td>
		                    <td>${procDetail.planDetail.method?if_exists}</td>
		                    <td>${procDetail.planDetail.tool?if_exists}</td>
		                    <td>${procDetail.planDetail.rule?if_exists}</td>
		                    <td>${procDetail.planDetail.comment?if_exists}</td>
		                    <td>
		                    	${action.getText(procDetail.checkResult)}
		                    </td>
		                    <td>${procDetail.comment?if_exists}</td>
		                    <#if procDetail.planDetail.fee?exists>
		                    <td>${procDetail.planDetail.fee?if_exists}</td>
		                    <#else>
		                    <td>0</td>
		                    </#if>
		                    <td>${procDetail.fee?if_exists}</td>
		                    <#if procDetail.fee?exists>
		                       <#assign feeSum = feeSum + procDetail.fee/>
		                    </#if>
		                </tr>
		             </#list>
		           </#if>
	             </@listTable>
	             <@ww.hidden name="'proc.feeSum'" value="#{feeSum}"/>
         </#if>
         
         <script language="javascript">
          window.onload = function () {
	        	var fee = document.forms[0].elements["proc.feeSum"];
	        		parent.document.forms[0].elements["proc.actualExpenseCollection"].value = fee.value;	        	
	        }
         
	     function open_detailDialog(procId, detailId) {
	      		var url = '${req.contextPath}/runmaintenance/checkPoint/editCheckPointProcDetail.html?proc.id='+procId;	      		
	      		if (detailId != null) {
	      			url = url + "&detail.id=" + detailId;
	      		}
	      		popupModalDialog(url, 800,400);  	
	      		self.location.reload();
	      	}
	    </script>
         
	 </@ww.form>
</@framePage>