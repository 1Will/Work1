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

<#-- $Id: userSelector.ftl 11122 2008-11-06 12:54:35Z zsmuig $ -->


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>申购明细维护页面</title>
	</head>
	<frameset rows="400,*" framespacing="0" frameborder="no" border="0">
	   <#if !fromDetail>
		<frame src="editSubscribeDetail.html?subscribe.id=#{subscribe.id}&toolingDevFlag=${toolingDevFlag?if_exists}" name="topFrame" scrolling="yes">-->
	   <#else>
		<frame src="editSubscribeDetail.html?subscribe.id=#{subscribe.id}&toolingDevFlag=${toolingDevFlag?if_exists}&subscribeDtl.id=#{subscribeDtl.id}" name="topFrame" scrolling="yes">
	   </#if>	
		<frame src="listSparesFrame.html?first=true" name="buttomFrame" scrolling="yes">
	</frameset>
     <noframes>    
     <body>
     </body>
     </noframes>
</html>