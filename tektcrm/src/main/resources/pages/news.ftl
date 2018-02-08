<style type="text/css">
<!--
.STYLE1 {
	font-family: "楷体_GB2312";
	font-weight: bold;
	color: #000000;
	font-size: 12px;
}
.STYLE2 {
	border: 1
	color: #FFDADA;
}
-->
</style>

<table width="100%" height="70%" border="1" cellspacing="20" cellpadding="0" style="border: 1 solid #D6E9F1;">
  <tr>
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%" >
	  <tr>
	     <td class="title" colspan ="3">
            <span class="STYLE1">通知、公告
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <#if (unReadNoticeSize?exists && unReadNoticeSize > 0) >
		   有<a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true">
		   <strong>
		   ${unReadNoticeSize?if_exists}
		   </strong></a>条未读
		   <#else>
		   有<strong>0</strong></a>条未读
		   </#if>
		   </span>
        </td>
	  </tr>
		  <td colspan = "3">
			   <hr align="center" size="3" style="background:#D6E9F1" >
		  </td>
		 <#assign count=0>
		<#if noticeList?exists>
	  	<#list noticeList as urn>
			<tr>
				<td>
				<#if urn.readStatus == "UNREAD"&&count<4>
				   
				     <li/>
				     <a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&receviceNotice.id=${urn.id}">
				      <span title="${urn.title?if_exists}">
			           <script>
			            	var s = "${urn.title?if_exists}";
			            	if(s.length<15){
			            	document.write(s.slice(0,15));
			            	}
			            	if(s.length>15){
							   s = s.substring(0,15);
							   document.write(s.slice(0,15)+"...");
							}
			           </script>
	             	  </span>
	             	  </a> 
	             	  </td>
	             	  <td align="left">
	             	  (${urn.receviceDate?string('yyyy-MM-dd')?if_exists})
	             	  </td>
				      <td align="right">未读
					<#assign count=count+1>
				</#if>
			    </td>
			</tr>
		</#list>
		<#else>
			<tr>
				<td>
					 暂无通知公告
			    </td>
			</tr>
		</#if>
      <tr>
      <#if 3<count>
        <td/>
        <td/>
        <td align="right" colspan ="3">
        <a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true" >
        &lt;&lt;更多
        </a>
        </td>
      </#if> 
      </tr>
	  </TABLE>
    </td>
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%">
	  <tr>
	     <td class="title" colspan ="3">
            <span class="STYLE1">我的提醒
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <#if (numberOfUnRead?exists && numberOfUnRead > 0) >
		   有<a href="${req.contextPath}/workspace/warnning/myWarnning/listWorkWarnnings.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true">
		   <strong>
		   ${numberOfUnRead?if_exists}
		   </strong></a>条未读
		   <#else>
		   有<strong>0</strong></a>条未读
		   </#if>
		   </span>
        </td>
	  </tr> 
	  <td colspan = "3">
		   <hr align="center" size="3" style="background:#D6E9F1" >
	  </td> 
	  <#assign count=0>
		<#if workWarnningList?exists>
	  	<#list workWarnningList as wwl>
				<#if count<4>
					<tr>
					<td>
				    <li/>
				    <a href="${req.contextPath}/workspace/warnning/myWarnning/listWorkWarnnings.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&workWarnning.id=#{wwl.id?if_exists}">
				    	<span title="${wwl.content?if_exists}">
				           <script>
				            	var s = "${wwl.name?if_exists}"==""?"${wwl.type?if_exists}":"${wwl.name?if_exists}";
				            	if(s.length<15){
			            	       document.write(s.slice(0,15));
			            	     }
				            	if(s.length>=15){
								   s = s.substring(0,15);
								   document.write(s.slice(0,15)+"...");
								}
				           </script>
		             	  </span>
		             	  </a>
		      
		             	  </td>
					      <td align="right">未读
				    <#assign count=count+1>
				</#if>
			    </td>
			</tr>
		</#list>
		<#else>
			<tr>
				<td>
					 暂无提醒内容
			    </td>
			</tr>
		</#if>
	  <tr>
	  <#if 3<count>
        <td align="right" colspan ="3">
        <a href="${req.contextPath}/workspace/warnning/myWarnning/listWorkWarnnings.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true" >
        &lt;&lt;更多
        </a>
        </td>
      </#if>
      </tr>
	  </TABLE>
    </td>
    
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%">
	  <tr>
	     <td class="title" colspan ="2">
         <span class="STYLE1">最新签单
		  </span>
        </td>
	  </tr> 
	  <td colspan ="2">
		   <hr align="center" size="3" style="background:#D6E9F1">
	  </td>
	    <#assign count=0>
		<#if newSignings?exists>
	  	<#list newSignings as newSign>
				<#if count<4>
				  <tr>
				    <td>
					   <li/>
					    <a href="${req.contextPath}/contractManagement/listContractManagementAction.html?loginUser.id=#{loginUser.id?if_exists}&contractManagement.id=${newSign.id?if_exists}">
	  						<span title="${newSign.contractName?if_exists}">
	  						<script>
			            	var s = "${newSign.contractName?if_exists}";
			            	if(s.length<15){
			            	document.write(s.slice(0,15));
			            	}
			            	if(s.length>=15){
							   s = s.substring(0,15);
							   document.write(s.slice(0,15)+"...");
							}
			               </script>
			               </span>
						  </a>
						  </td>
						 <td align="right">
						  (${newSign.ciemdinghTime?string('yyyy-MM-dd')?if_exists})
				    <#assign count=count+1>
				    </td>
			    </tr>
			  	</#if>
		</#list>
		<#else>
			<tr>
				<td>
					 暂无签单信息
			    </td>
			</tr>
		</#if>
	  
	   <#if 3<count>
	   <tr>
	   <td/>
        <td align="right" colspan ="2">
        <a href="${req.contextPath}/contractManagement/listContractManagementAction.html?loginUser.id=#{loginUser.id?if_exists}" >
        &lt;&lt;更多
        </a>
        </td>
        </tr>
        </#if>
       
	  </TABLE>
    </td>
    
  </tr>
  
  <tr>
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%">
	  <tr>
	     <td class="title" colspan ="3">
         <span class="STYLE1">我的团队
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		合同金额|收款金额|收入金额(本月)
		  </span>
        </td>
	  </tr> 
	  <td colspan ="2">   
		   <hr align="center" size="3" style="background:#D6E9F1" >
	  </td>
  	    <#assign count=0>
	  	<#if myTeam?exists>
	  	<#list myTeam as team>
	  	    <#if count<4>  
			<tr>
				<td>
				 <li/>
				 <a href="${req.contextPath}/workspace/data/listData.html?personnelFiles.code=${team.personnelFiles.code?if_exists}">
				   <span title="${team.personnelFiles.name?if_exists}">
				        ${team.personnelFiles.name?if_exists}
				   </span>
				</a>
				</td>
				<td >
				     ${team.contractManagementMoney?if_exists}元|${team.financialManagementMoney?if_exists}元|${team.billingRecordMoney?if_exists}元
		        </td>
			    <#assign count=count+1>
			</tr>
			</#if>
		</#list>
		<#else>
			<tr>
				<td>
					 暂无团队
			    </td>
			</tr>
		</#if>
		<#if 3<count>
		<tr>
        <td align="right" colspan ="3">
          <a href="${req.contextPath}/workspace/data/listData.html">
        &lt;&lt;更多
        </a>
        </td>
       </tr>
       </#if>
  </TABLE>
    </td>
    
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%">
	  <tr>
	     <td class="title" colspan ="3">
            <span class="STYLE1">我的任务
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   有<a href="${req.contextPath}/projectInfo/listMyPlan.html"  >
		   <strong>
		   ${myPlanSize?if_exists}
		   </strong></a>条任务
		   </span>
        </td>
	  </tr> 
	  <td colspan ="3">
		   <hr align="center" size="3" style="background:#D6E9F1" >
	  </td> 
	 		<#assign count=0>
	 		<#assign myPlanS='${myPlanSize?if_exists}'>
			<#if myProjectInfoPlan?exists && myPlanS!='0'>
		  	<#list myProjectInfoPlan as urn>
				<tr>
					<#if count<4>
					  <td>
					  <li/>
					   <a href="${req.contextPath}/projectInfo/listMyPlan.html?projectInfoPlan.id=#{urn.id}">
 						<span title="${urn.name?if_exists}">
				           <script>
			            	var s = "${urn.name?if_exists}";
			            	if(s.length<15){
			            	document.write(s.slice(0,15));
			            	}
			            	if(s.length>=15){
							   s = s.substring(0,15);
							   document.write(s.slice(0,15)+"...");
							}
			           </script>
			           </span>
				      </a>
				     </td>      
				     <td>
				     	<#if urn.endDate?exists>
		             	${urn.endDate?string('yyyy-MM-dd')?if_exists}
		             	</#if>
					 </td>    
					 <td align="right">
					    <#if urn.planState?exists> ${urn.planState.name?if_exists}</#if>
					    <#assign count=count+1>
				    </td>
				    </#if>
				</tr>
			</#list>
			<#else>
				<tr>
					<td>
						 暂无任务
				    </td>
				</tr>
			</#if>
		<tr>
		<#if 3<count>
	        <td align="right" colspan ="3">
	        <a href="${req.contextPath}/projectInfo/listMyPlan.html" >
	        &lt;&lt;更多
	        </a>
	        </td>
	        </tr>
        </#if>
	  </TABLE>
    </td>
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%">
	  <tr>
	     <td class="title" colspan ="3">
            <span class="STYLE1">我的数据
		   </span>
        </td>
	  </tr> 
	  <td colspan ="3">
		   <hr align="center" size="3" style="background:#D6E9F1">
	  </td> 
		<#if myDataMap?exists>
		<tr><td>本月合同:</td><td align="right">合同数：<a href="${req.contextPath}/contractManagement/listContractManagementAction.html">  ${myDataMap.monthCMCount}</a> 个</td><td align="right">合同金额合计： ${myDataMap.monthCMMoney}元 </td></tr>
		<tr><td>本年合同:</td><td align="right">合同数： <a href="${req.contextPath}/contractManagement/listContractManagementAction.html">   ${myDataMap.yearCMCount}</a> 个</td><td align="right">合同金额合计： ${myDataMap.yearCMMoney} 元</td></tr>
		<tr><td>本月收款:</td><td align="right">笔数：  <a href="${req.contextPath}/financialManagement/listFinancialManagement.html">  ${myDataMap.monthFMCount}</a> 个</td><td align="right">收款金额合计： ${myDataMap.monthFMMoney}元 </td></tr>
		<tr><td>本年收款:</td><td align="right">笔数： <a href="${req.contextPath}/financialManagement/listFinancialManagement.html">   ${myDataMap.yearFMCount}</a> 个</td><td align="right">收款金额合计： ${myDataMap.yearFMMoney}元 </td></tr>
		<tr><td>本月收入:</td><td align="right">笔数：<a href="${req.contextPath}/contractManagement/listBillingRecord.html">    ${myDataMap.monthBRCount}</a> 个</td><td align="right">收入金额合计： ${myDataMap.monthBRMoney}元 </td></tr>
		<tr><td>本年收入:</td><td align="right">笔数：<a href="${req.contextPath}/contractManagement/listBillingRecord.html">    ${myDataMap.yearBRCount}</a> 个</td><td align="right">收入金额合计： ${myDataMap.yearBRMoney} 元</td></tr>
		 <tr>
        <td align="right" colspan ="3">
        <a href="${req.contextPath}/workspace/data/listMyData.html" >
        &lt;&lt;更多
        </a>
        </td>
      </tr>
		</#if>
	  </TABLE>
    </td>
  </tr>
</table>
<script>
/*
	function changeColor(){ 
		var color="#f00|#0f0|#00f|#880|#808|#088|yellow|green|blue|gray"; 
		color=color.split("|"); 
		if(document.getElementById("notice") != null){
			document.getElementById("notice").style.color=color[parseInt(Math.random() * color.length)]; 
		}
		if(document.getElementById("workwarn") != null){
			document.getElementById("workwarn").style.color=color[parseInt(Math.random() * color.length)]; 
		}
		if(document.getElementById("todotask") != null){
			document.getElementById("todotask").style.color=color[parseInt(Math.random() * color.length)]; 
		}
		if(document.getElementById("daily") != null){
			document.getElementById("daily").style.color=color[parseInt(Math.random() * color.length)]; 
		}
		if(document.getElementById("task") != null){
			document.getElementById("task").style.color=color[parseInt(Math.random() * color.length)]; 
		}
		if(document.getElementById("news") != null){
			document.getElementById("news").style.color=color[parseInt(Math.random() * color.length)]; 
		}
	} 
	setInterval("changeColor()",1000); */
</script>
