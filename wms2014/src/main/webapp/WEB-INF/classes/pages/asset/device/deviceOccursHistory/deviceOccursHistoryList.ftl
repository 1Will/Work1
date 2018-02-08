<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->
<#-- $Id: deviceOccursHistoryList.ftl 2009-09-25 09:30:50Z wliu $-->

<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('device.occursHistory.title')}">

<@ww.hidden name="'deviceId'" value="'${req.getParameter('deviceId')?if_exists}'"/>
	<#-- 采购信息 -->
    <div class="tabStyle" style="background-color:#d4e8f1">
    	<div style="width:100px;text-align:left;float:left;">&nbsp;&nbsp;<img src="${req.contextPath}/images/icon/tablogo.gif"/>&nbsp;${action.getText('device.purchaseInfo')}</div>
    	<div style="width:30px;float:right">
    		<a id="purchaseInfo" href="#" onclick="occursHistorySubmit('purchaseInfoImg',this,'pi',0,'purchaseInfoFrame');" target="purchaseInfoFrame" >
    		<img alt="${action.getText('open')}" name="purchaseInfoImg" src="${req.contextPath}/images/icon/maximize.gif" style="cursor:pointer;border:none;vertical-align:bottom;"/>
    		</a>
    	</div>
    </div>
    <div id="pi" style="height:250px;width:100%;display:none;">
		<iframe name="purchaseInfoFrame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" width="100%" height="100%">
		</iframe>
    </div>
	<#-- 验收信息 -->
	<div class="tabStyle" style="background-color:#d4e8f1">
    	<div style="width:100px;text-align:left; float:left;">&nbsp;&nbsp;<img src="${req.contextPath}/images/icon/tablogo.gif"/>&nbsp;${action.getText('device.acceptInfo')}</div>
    	<div style="width:30px;float:right">
    		<a id="acceptInfo" href="#" onclick="occursHistorySubmit('acceptInfoImg',this,'ai',1,'acceptInfoFrame');" target="acceptInfoFrame" >
    		<img alt="${action.getText('open')}" name="acceptInfoImg" src="${req.contextPath}/images/icon/maximize.gif" style="cursor:pointer;border:none;vertical-align:bottom;"/>
    		</a>
    	</div>
    </div>
    <div id="ai" style="height:250px;width:100%;display:none;">
		<iframe name="acceptInfoFrame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" width="98%" height="100%">
		</iframe>
    </div>
	<#-- 故障记录 -->
	<div class="tabStyle" style="background-color:#d4e8f1">
    	<div style="width:100px;text-align:left; float:left;">&nbsp;&nbsp;<img src="${req.contextPath}/images/icon/tablogo.gif"/>&nbsp;${action.getText('device.failureRecord')}</div>
    	<div style="width:30px;float:right">
    		<a id="failureRecord" href="#" onclick="occursHistorySubmit('failureRecordImg',this,'fr',2,'failureRecordFrame');" target="failureRecordFrame" >
    		<img alt="${action.getText('open')}" name="failureRecordImg" src="${req.contextPath}/images/icon/maximize.gif" style="cursor:pointer;border:none;vertical-align:bottom;"/>
    		</a>
    	</div>
    </div>
    <div id="fr" style="height:250px;width:100%;display:none;">
		<iframe name="failureRecordFrame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" width="98%" height="100%">
		</iframe>
    </div>
	<#-- 大项修 � -->
	<div class="tabStyle" style="background-color:#d4e8f1">
    	<div style="width:100px;text-align:left; float:left;">&nbsp;&nbsp;<img src="${req.contextPath}/images/icon/tablogo.gif"/>&nbsp;${action.getText('device.yearRepair')}</div>
    	<div style="width:30px;float:right">
    		<a id="yearRepair" href="#" onclick="occursHistorySubmit('yearRepairImg',this,'yr',3,'yearRepairFrame');" target="yearRepairFrame" >
    		<img alt="${action.getText('open')}" name="yearRepairImg" src="${req.contextPath}/images/icon/maximize.gif" style="cursor:pointer;border:none;vertical-align:bottom;"/>
    		</a>
    	</div>
    </div>
    <div id="yr" style="height:250px;width:100%;display:none;">
		<iframe name="yearRepairFrame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" width="98%" height="100%">
		</iframe>
    </div>
	<#-- 预防性维修 � -->
	<div class="tabStyle" style="background-color:#d4e8f1">
    	<div style="width:100px;text-align:left; float:left;">&nbsp;&nbsp;<img src="${req.contextPath}/images/icon/tablogo.gif"/>&nbsp;${action.getText('device.preRepair')}</div>
    	<div style="width:30px;float:right">
    		<a id="preRepair" href="#" onclick="occursHistorySubmit('preRepairImg',this,'pr',4,'preRepairFrame');" target="preRepairFrame" >
    		<img alt="${action.getText('open')}" name="preRepairImg" src="${req.contextPath}/images/icon/maximize.gif" style="cursor:pointer;border:none;vertical-align:bottom;"/>
    		</a>
    	</div>
    </div>
    <div id="pr" style="height:250px;width:100%;display:none;">
		<iframe name="preRepairFrame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" width="98%" height="100%">
		</iframe>
    </div>
	<#-- 故障维修 -->
	<div class="tabStyle" style="background-color:#d4e8f1">
    	<div style="width:100px;text-align:left; float:left;">&nbsp;&nbsp;<img src="${req.contextPath}/images/icon/tablogo.gif"/>&nbsp;${action.getText('device.faultRepair')}</div>
    	<div style="width:30px;float:right">
    		<a id="faultRepair" href="#" onclick="occursHistorySubmit('faultRepairImg',this,'frr',5,'faultRepairFrame');" target="faultRepairFrame" >
    		<img alt="${action.getText('open')}" name="faultRepairImg" src="${req.contextPath}/images/icon/maximize.gif" style="cursor:pointer;border:none;vertical-align:bottom;"/>
    		</a>
    	</div>
    </div>
    <div id="frr" style="height:250px;width:100%;display:none;">
		<iframe name="faultRepairFrame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" width="98%" height="100%">
		</iframe>
    </div>
	<#-- 保养记录 -->
	<div class="tabStyle" style="background-color:#d4e8f1">
    	<div style="width:100px;text-align:left; float:left;">&nbsp;&nbsp;<img src="${req.contextPath}/images/icon/tablogo.gif"/>&nbsp;${action.getText('device.maintenanceRecord')}</div>
    	<div style="width:30px;float:right">
    		<a id="maintenanceRecord" href="#" onclick="occursHistorySubmit('maintenanceRecordImg',this,'mr',6,'maintenanceRecordFrame');" target="maintenanceRecordFrame" >
    		<img alt="${action.getText('open')}" name="maintenanceRecordImg" src="${req.contextPath}/images/icon/maximize.gif" style="cursor:pointer;border:none;vertical-align:bottom;"/>
    		</a>
    	</div>
    </div>
    <div id="mr" style="height:250px;width:100%;display:none;">
		<iframe name="maintenanceRecordFrame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" width="98%" height="100%">
		</iframe>
    </div>
	<#-- 润滑记录 -->
    <div class="tabStyle" style="background-color:#d4e8f1">
    	<div style="width:100px;text-align:left; float:left;">&nbsp;&nbsp;<img src="${req.contextPath}/images/icon/tablogo.gif"/>&nbsp;${action.getText('device.lubricationRecord')}</div>
    	<div style="width:30px;float:right">
    		<a id="lubricationRecord" href="#" onclick="occursHistorySubmit('lubricationRecordImg',this,'lr',7,'lubricationRecordFrame');" target="lubricationRecordFrame" >
    		<img alt="${action.getText('open')}" name="lubricationRecordImg" src="${req.contextPath}/images/icon/maximize.gif" style="cursor:pointer;border:none;vertical-align:bottom;"/>
    		</a>
    	</div>
    </div>
    <div id="lr" style="height:250px;width:100%;display:none;">
		<iframe name="lubricationRecordFrame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" width="98%" height="100%">
		</iframe>
    </div>
<script language="javascript">
	var requestUrls = new Array();
	requestUrls[0] = "${req.contextPath}/deviceOccursHistory/listPurchaseInfo.html?deviceId=${req.getParameter('deviceId')?if_exists}";
	requestUrls[1] = "${req.contextPath}/deviceOccursHistory/listAcceptInfo.html?deviceId=${req.getParameter('deviceId')?if_exists}";
	requestUrls[2] = "${req.contextPath}/deviceOccursHistory/listFailureRecord.html?deviceId=${req.getParameter('deviceId')?if_exists}";
	requestUrls[3] = "${req.contextPath}/deviceOccursHistory/listYearRepair.html?deviceId=${req.getParameter('deviceId')?if_exists}";
	requestUrls[4] = "${req.contextPath}/deviceOccursHistory/listPreRepair.html?deviceId=${req.getParameter('deviceId')?if_exists}";
	requestUrls[5] = "${req.contextPath}/deviceOccursHistory/listFaultRepair.html?deviceId=${req.getParameter('deviceId')?if_exists}";
	requestUrls[6] = "${req.contextPath}/deviceOccursHistory/listMaintenanceRecord.html?deviceId=${req.getParameter('deviceId')?if_exists}";
	requestUrls[7] = "${req.contextPath}/deviceOccursHistory/listLubricationRecord.html?deviceId=${req.getParameter('deviceId')?if_exists}";
	
	function occursHistorySubmit(op,cp,wp,up,fp){
		if($(op).alt == "${action.getText('open')}"){
			$(op).alt="${action.getText('close')}";
			$(op).src="${req.contextPath}/images/icon/minimize.gif";
			cp.href=requestUrls[up];
			$(wp).style.display="block";
		}else if($(op).alt == "${action.getText('close')}"){
			$(op).alt="${action.getText('open')}";
			$(op).src="${req.contextPath}/images/icon/maximize.gif";
			$(wp).style.display="none";
		}
	}
	
</script>
</@htmlPage>
