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
	     <td class="title">
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
		  <td>
			   <hr align="center" size="3" style="background:#D6E9F1">
		  </td>
		 <#assign count=0>
		<#if noticeList?exists>
	  	<#list noticeList as urn>
			<tr>
				<td>
				<#if urn.readStatus == "UNREAD"&&count<4>
				   <li><a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&receviceNotice.id=${urn.id}">
				      <span title="${urn.title?if_exists}">
			           <script>
			            	var s = "${urn.title?if_exists}";
			            	document.write(s.slice(0,14)+"...");
			           </script>
	             	  </span>(${urn.receviceDate?string('yyyy-MM-dd')?if_exists})
				      </a> 未读</li>
					<#assign count=count+1>
				<#elseif count<4>
				    <li><a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&receviceNotice.id=${urn.id}">
					   	<span title="${urn.title?if_exists}">
				           <script>
				            	var s = "${urn.title?if_exists}";
				            	document.write(s.slice(0,14)+"...");
				           </script>
		             	  </span>(${urn.receviceDate?string('yyyy-MM-dd')?if_exists})
					    </a>  已读</li>
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
        <td align="right">
        <a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true" target="_blank">
        &lt;&lt;更多
        &nbsp;&nbsp;&nbsp;&nbsp;</a>
        </td>
      </#if> 
      </tr>
	  </TABLE>
    </td>
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%">
	  <tr>
	     <td class="title">
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
	  <td>
		   <hr align="center" size="3" style="background:#D6E9F1">
	  </td> 
	  <#assign count=0>
		<#if workWarnningList?exists>
	  	<#list workWarnningList as wwl>
			<tr>
				<td>
				<#if wwl.readFlag&&count<4>
				    <li><a href="${req.contextPath}/workspace/warnning/myWarnning/listWorkWarnnings.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&workWarnning.id=${wwl.id?if_exists}">
					   	<span title="${wwl.content?if_exists}">
				           <script>
				            	var s = "${wwl.content?if_exists}";
				            	document.write(s.slice(0,14)+"...");
				           </script>
		             	  </span>
					    </a> 已读</li>
				    <#assign count=count+1>
				<#elseif count<4>
				    <li><a href="${req.contextPath}/workspace/warnning/myWarnning/listWorkWarnnings.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&workWarnning.id=${wwl.id?if_exists}">
				    	<span title="${wwl.content?if_exists}">
				           <script>
				            	var s = "${wwl.content?if_exists}";
				            	document.write(s.slice(0,14)+"...");
				           </script>
		             	  </span>
					    </a> 未读</li>
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
        <td align="right">
        <a href="${req.contextPath}/workspace/warnning/myWarnning/listWorkWarnnings.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true" target="_blank">
        &lt;&lt;更多
        &nbsp;&nbsp;&nbsp;&nbsp;</a>
        </td>
      </#if>
      </tr>
	  </TABLE>
    </td>
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%">
	  <tr>
	     <td class="title">
         <span class="STYLE1">最新签单
		  </span>
        </td>
	  </tr> 
	  <td>
		   <hr align="center" size="3" style="background:#D6E9F1">
	  </td>
	    <#assign count=0>
		<#if newSignings?exists>
	  	<#list newSignings as newSign>
			<tr>
				<td>
				<#if count<4>
				    <li><a href="${req.contextPath}/contractManagement/listContractManagementAction.html?loginUser.id=#{loginUser.id?if_exists}&contractManagement.id=${newSign.id?if_exists}">
  						<span title="${newSign.contractName?if_exists}">
				           <script>
				            	var s = "${newSign.contractName?if_exists}";
				            	document.write(s.slice(0,14)+"...");
				           </script>
		             	  </span>(${newSign.ciemdinghTime?string('yyyy-MM-dd')?if_exists})
					    </a></li>
				    <#assign count=count+1>
			  	</#if>
			    </td>
			</tr>
		</#list>
		<#else>
			<tr>
				<td>
					 暂无签单信息
			    </td>
			</tr>
		</#if>
	  <tr>
	   <#if 3<count>
        <td align="right">
        <a href="${req.contextPath}/contractManagement/listContractManagementAction.html?loginUser.id=#{loginUser.id?if_exists}" target="_blank">
        &lt;&lt;更多
        &nbsp;&nbsp;&nbsp;&nbsp;</a>
        </td>
       </#if>
      </tr>
	  </TABLE>
    </td>
  </tr>
  <tr>
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%">
	  <tr>
	     <td class="title">
         <span class="STYLE1">我的团队
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <#if (myTeamSize?exists && myTeamSize > 0) >
		  		 有<strong>${myTeamSize?if_exists}</strong>名团队队员
		   <#else>
		  		 有<strong>0</strong></a>名团队队员
		   </#if>
		  </span>
        </td>
	  </tr> 
	  <td>   
		   <hr align="center" size="3" style="background:#D6E9F1">
	  </td>
  	    <#assign count=0>
	  	<#if myTeam?exists>
	  	<#list myTeam as team>
			<tr>
				<td>
				<#if count<4>
				    <li><a href="">
 						<span title="${team.pname?if_exists}">
				            	${team.pname?if_exists}
		             	  </span>
					    </a>
					    <a> 　　　　　
					    <span >
					    有 ${team.num?if_exists} 项任务
		             	  </span>
					    
					    </a></li>
				     <#assign count=count+1>
				</#if>
			    </td>
			</tr>
		</#list>
		<#else>
			<tr>
				<td>
					 暂无团队
			    </td>
			</tr>
		</#if>
	<tr>
	 <#if 3<count>
        <td align="right">
          <a href="${req.contextPath}/toDoTask/listToDoTask.html?loginUser.id=#{loginUser.id?if_exists}" target="_blank">
        &lt;&lt;更多
        &nbsp;&nbsp;&nbsp;&nbsp;</a>
        </td>
       </#if>
      </tr>
  </TABLE>
    </td>
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%">
	  <tr>
	     <td class="title">
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
	  <td>
		   <hr align="center" size="3" style="background:#D6E9F1">
	  </td> 
	 		<#assign count=0>
			<#if myProjectInfoPlan?exists>
		  	<#list myProjectInfoPlan as urn>
				<tr>
					<#if count<4>
				<td>
					    <li><a href="${req.contextPath}/projectInfo/listMyPlan.html?projectInfoPlan.id=#{urn.id}">
 						<span title="${urn.name?if_exists}">
				           <script>
				            	var s = "${urn.name?if_exists}";
				            	document.write(s.slice(0,44)+"...");
				           </script>
		             	  </span>
					    </a> <#if urn.planState?exists> ${urn.planState.name?if_exists}</#if></li>
					    <#assign count=count+1>
				    </td>
				    </#if>
				</tr>
			</#list>
		<tr>
        <td align="right">
        <a href="${req.contextPath}/projectInfo/listMyPlan.html" >
        &lt;&lt;更多
        &nbsp;&nbsp;&nbsp;&nbsp;</a>
        </td>
      </tr>
			<#else>
				<tr>
					<td>
						 暂无任务
				    </td>
				</tr>
			</#if>
	  </TABLE>
    </td>
    <td width="30%" height="50%" VALIGN="TOP" style="border: 1 solid #D6E9F1;">
	  <TABLE width="100%">
	  <tr>
	     <td class="title">
            <span class="STYLE1">我的数据
		   </span>
        </td>
	  </tr> 
	  <td>
		   <hr align="center" size="3" style="background:#D6E9F1">
	  </td> 
		<#if myDataMap?exists>
		<tr><td>本月合同:</td><td>合同数：  ${myDataMap.monthCMCount} 个</td><td>合同金额合计： ${myDataMap.monthCMMoney} 万</td></tr>
		<tr><td>本年合同:</td><td>合同数：  ${myDataMap.yearCMCount} 个</td><td>合同金额合计： ${myDataMap.yearCMMoney} 万</td></tr>
		<tr><td>本月收款:</td><td>笔数：  ${myDataMap.monthFMCount} 个</td><td>收款金额合计： ${myDataMap.monthFMMoney} 万</td></tr>
		<tr><td>本年收款:</td><td>笔数：  ${myDataMap.yearFMCount} 个</td><td>收款金额合计： ${myDataMap.yearFMMoney} 万</td></tr>
		<tr><td>本月收入:</td><td>笔数：  ${myDataMap.monthBRCount} 个</td><td>收入金额合计： ${myDataMap.monthBRMoney} 万</td></tr>
		<tr><td>本年收入:</td><td>笔数：  ${myDataMap.yearBRCount} 个</td><td>收入金额合计： ${myDataMap.yearBRMoney} 万</td></tr>
		 <tr>
        <td align="right">
        <a href="${req.contextPath}/workspace/data/listMyData.html" >
        &lt;&lt;更多
        &nbsp;&nbsp;&nbsp;&nbsp;</a>
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
