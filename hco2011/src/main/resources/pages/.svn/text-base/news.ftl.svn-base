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
         <span class="STYLE1">待审批项
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <#if (toDoTaskSize?exists && toDoTaskSize > 0) >
		  		 有<strong>${toDoTaskSize?if_exists}</strong>条未批
		   <#else>
		  		 有<strong>0</strong></a>条未批
		   </#if>
		  </span>
        </td>
	  </tr> 
	  <td>   
		   <hr align="center" size="3" style="background:#D6E9F1">
	  </td>
  		<tr>
  			<td><font color="green">任务审批:</font>
	        </td>
  		</tr>
  	    <#assign count=0>
	  	<#if toDoTasks?exists>
	  	<#list toDoTasks as toDoTask>
			<tr>
				<td>
				<#if count<4>
				    <li>有<a href="${req.contextPath}/toDoTask/listToDoTask.html?loginUser.id=#{loginUser.id?if_exists}&task.id=${toDoTask.id?if_exists}">
 						<span title="${toDoTask.name?if_exists}">
				           <script>
				            	var s = "${toDoTask.name?if_exists}";
				            	document.write(s.slice(0,14)+"...");
				           </script>
		             	  </span>
					    </a>的待办审批</li>
				     <#assign count=count+1>
				</#if>
			    </td>
			</tr>
		</#list>
		<#else>
			<tr>
				<td>
					 暂无任务审批
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
            <span class="STYLE1">待办任务
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <#if (taskSize?exists && taskSize > 0) >
		   有<a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true"  target="_blank">
		   <strong>
		   ${taskSize?if_exists}
		   </strong></a>条任务
		   <#else>
		   有<strong>0</strong></a>条任务
		   </#if>
		   </span>
        </td>
	  </tr> 
	  <td>
		   <hr align="center" size="3" style="background:#D6E9F1">
	  </td> 
	 		<#assign count=0>
			<#if taskList?exists>
		  	<#list taskList as urn>
				<tr>
					<td>
					<#if urn.readStatus == "UNREAD"&&count<4>
					    <li><a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&receviceNotice.id=${urn.id}">
 						<span title="${urn.title?if_exists}">
				           <script>
				            	var s = "${urn.title?if_exists}";
				            	document.write(s.slice(0,14)+"...");
				           </script>
		             	  </span>
					    </a>  未读</li>
					    <#assign count=count+1>
					<#elseif count<4>
					    <li><a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&receviceNotice.id=${urn.id}">
 						<span title="${urn.title?if_exists}">
				           <script>
				            	var s = "${urn.title?if_exists}";
				            	document.write(s.slice(0,14)+"...");
				           </script>
		             	  </span>
					    </a>  已读</li>
					    <#assign count=count+1>
					</#if>
				    </td>
				</tr>
			</#list>
			<#else>
				<tr>
					<td>
						 暂无待办任务
				    </td>
				</tr>
			</#if>
	<tr>
		<#if 3<count>
        <td align="right">
        <a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true"  target="_blank">
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
            <span class="STYLE1">公司新闻
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <#if (newsSize?exists && newsSize > 0) >
		   有<a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&first=false">
		   <strong>
		   ${newsSize?if_exists}
		   </strong></a>条新闻
		   <#else>
		   有<strong>0</strong></a>条新闻
		   </#if>
		   </span>
        </td>
	  </tr> 
	  <td>
		   <hr align="center" size="3" style="background:#D6E9F1">
	  </td> 
	    <#assign count=0>
		<#if newsList?exists>
	  	<#list newsList as urn>
			<tr>
				<td>
				<#--
	  	<marquee direction="up" height=100 scrollamount=2 onmouseover="this.stop()" 
		onmouseout="this.start()"> 
		-->
				<#if urn.readStatus == "UNREAD"&&count<4>
				    <li><a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&receviceNotice.id=${urn.id}">
 						<span title="${urn.title?if_exists}">
				           <script>
				            	var s = "${urn.title?if_exists}";
				            	document.write(s.slice(0,14)+"...");
				           </script>
		             	  </span>(${urn.receviceDate?string('yyyy-MM-dd')?if_exists})
					    </a>  未读</li>
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
	<#--	</MARQUEE> -->
			    </td>
			</tr>
		</#list>
		<#else>
			<tr>
				<td>
					 <div id="blink">暂无公司新闻</div>
			    </td>
			</tr>
		</#if>
	 <tr>
		<#if 3<count>
        <td align="right">
         <a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&first=false"  target="_blank">
        &lt;&lt;更多
        &nbsp;&nbsp;&nbsp;&nbsp;</a>
        </td>
        </#if>
      </tr>
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
