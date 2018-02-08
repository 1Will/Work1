<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: regionalPage.ftl 2009-11-30 9:12:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<frameset rows="60,*" borderWidth="0" border="0">
		<frame src="${req.contextPath}/cascadeManager_/topFrame_.html" name="topFrame" scrolling="no">
		<frameset cols="140,*">
			<frame src="${req.contextPath}/cascadeManager_/dTreeOperate_.html" name="dTreeFrame" scrolling="yes">
	        <frame src="${req.contextPath}/cascadeManager_/listNational_.html" name="cascade" scrolling="yes">
	    </frameset>
	</frameset>

</html>