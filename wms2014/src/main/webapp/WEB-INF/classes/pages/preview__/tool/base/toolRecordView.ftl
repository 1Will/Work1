 <#include "../../../includes/eam2008.ftl" />
 <#include "/javascripts/selectAll.js" />
	<@htmlPage title="记录查看"> 
	 <@ww.form name="'listForm'" action="'listDeviceDocs'" method="'post'">
	 </@ww.form>
	 <script language="JavaScript" type="text/JavaScript"> 

 	window.onload = function () {
	    		//var url = '../../preview/tool/editToolExtInfo.html';
				document.all.frame.src= '${req.contextPath}/preview/tool/base/listRequestitionLogs.html';
				document.getElementById("financeInfo").className = "selectedtab";
				}
	</script>
	<ul id="beautytab">	 
	  <li><a id="financeInfo" onclick="activeTab(this);"  class="selectedtab" href="${req.contextPath}/preview/tool/base/listRequestitionLogs.html" target="frame">领用记录</a></li>
	  <li><a id="deviceDoc"   onclick="activeTab(this);" href="${req.contextPath}/preview/tool/base/listChangeLogsHistorys.html" target="frame" >变更记录</a></li>
		  <li><a id="error"   onclick="activeTab(this);" href="${req.contextPath}/preview/device/acclog/listAccidentLogHistories.html" target="frame" >事故记录</a></li>
	  <li><a id="accident"   onclick="activeTab(this);" href="${req.contextPath}/preview/tool/errlog/listErrLogHistories.html" target="frame" >故障记录</a></li>
	  <li><a id="biaoding"   onclick="activeTab(this);" href="${req.contextPath}/preview/tool/demarcates/listDemarcateHistories.html" target="frame" >标定记录</a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</@htmlPage>