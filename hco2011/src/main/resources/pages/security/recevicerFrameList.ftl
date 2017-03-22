<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
 
 <#include "../includes/macros.ftl" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
	  <link rel="stylesheet" href="${req.contextPath}/stylesheets/css.css" type="text/css"/>
	  <link rel="stylesheet" href="${req.contextPath}/stylesheets/button.css" type="text/css"/>
	  <script language="javascript" src="${req.contextPath}/javascripts/global.js"></script>
	  <script language="javascript" src="${req.contextPath}/javascripts/modalDialog.js"></script>
	  <script language="javascript" src="${req.contextPath}/javascripts/controlType.js"></script>
	  <script language="javascript" type="text/javascript">
	    //确认选择的用户组或用户
	    function confirmRecevicer() {
	      var selectedRecevicers = document.getElementById("selectedRecevicers").value;
	      if (selectedRecevicers == "") {
	        window.close();
	      } else {
	        selectedRecevicers = selectedRecevicers.substring(0,selectedRecevicers.lastIndexOf(","));
	        returnDialog(selectedRecevicers,false);
	      }
	      return true;
	    }
		
		function resetRecevices() {
		  document.getElementById("selectedRecevicers").value = "";
		  document.getElementById("recevicers").value = "";
		  tagObj = parent.frames["mainFrame"].document.getElementsByTagName("input");    //获取mainFrame页面所有input对象
		  for (i=0; i<tagObj.length; i++) {
		    if (tagObj[i].type=='checkbox') {      //判断是否为checkbox对象
		      tagObj[i].checked = false;
		    }
		  }
		  return true;
		}
	  </script>
	</head>
	<body>
	   <@inputTable>
	   	  <input type="hidden" id ="selectedRecevicers" name="selectedRecevicers" value=""/>
	      <td>
	        <textarea name="recevicers"
                      cols="60"
                      rows="5"
                      id="recevicers" disabled></textarea>
	      </td>
        </@inputTable>
        <@buttonBar>
	       <@htmlButton name="choose" value="确定" onclick= "return confirmRecevicer();"/>
	       <@htmlButton name="submit"  style="width:50px;" value="重置" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"   onclick="return resetRecevices();"/>
	    </@buttonBar>
	</body>
</html>