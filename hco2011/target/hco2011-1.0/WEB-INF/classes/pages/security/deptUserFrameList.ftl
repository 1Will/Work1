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

<#include "../includes/macros.ftl" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<link rel="stylesheet" href="${req.contextPath}/stylesheets/css.css" type="text/css"/>
		<script language="javascript" type="text/javascript">
	    //设置部门是否选中
	    function setDeptValue(checkBox) {
			//获取隐藏已经被选中的部门或用户，是以Id1:name1,Id2:name2字符串组合一起的
			var selectedRecevicers = parent.frames["buttomFrame"].document.getElementById("selectedRecevicers").value;
	      	//获取显示的部门名或用户名
	      	var selectedNames = parent.frames["buttomFrame"].document.getElementById("recevicers").value;
	      	if (checkBox.checked) {
	        	//获取刚被选中的部门ID
	        	var selectedRecevicer = document.getElementById("hidden"+checkBox.name).value;
	        	var temp = selectedRecevicer + "_dept" + ":" + checkBox.value;
	        	selectedRecevicers = selectedRecevicers + temp + ",";
	        	//重新设置隐藏的选中的部门或用户
	        	parent.frames["buttomFrame"].document.getElementById("selectedRecevicers").value = selectedRecevicers;
	        	//加上刚选中的部门名
	        	selectedNames = selectedNames + checkBox.value + ",";
	        	//重新设置显示的值
	        	parent.frames["buttomFrame"].document.getElementById("recevicers").value = selectedNames;
	      	} else {
	        	var unSelectedRecevicer = document.getElementById("hidden"+checkBox.name).value;
	        	var temp =  unSelectedRecevicer + "_dept" + ":" + checkBox.value;
	        	//重新设置隐藏的选中的组或用户
	        	parent.frames["buttomFrame"].document.getElementById("selectedRecevicers").value = filterUnselected(selectedRecevicers,temp);
	        	parent.frames["buttomFrame"].document.getElementById("recevicers").value = filterUnselected(selectedNames,checkBox.value);
	      		}
	    	}
	    //设置用户是否选中
	    function setUserValue(checkBox) {
			//获取隐藏已经被选中的部门或用户，是以Id1:name1,Id2:name2字符串组合一起的
			var selectedRecevicers = parent.frames["buttomFrame"].document.getElementById("selectedRecevicers").value;
	      	//获取显示的部门名或用户名
	      	var selectedNames = parent.frames["buttomFrame"].document.getElementById("recevicers").value;
	      	if (checkBox.checked) {
	        	//获取刚被选中的部门ID
	        	var selectedRecevicer = document.getElementById("hidden"+checkBox.name).value;
	        	var temp = selectedRecevicer + "_user" + ":" + checkBox.value;
	        	selectedRecevicers = selectedRecevicers + temp + ",";
	        	//重新设置隐藏的选中的部门或用户
	        	parent.frames["buttomFrame"].document.getElementById("selectedRecevicers").value = selectedRecevicers;
	        	//加上刚选中的用户名
	        	selectedNames = selectedNames + checkBox.value + ",";
	        	//重新设置显示的值
	        	parent.frames["buttomFrame"].document.getElementById("recevicers").value = selectedNames;
	      	} else {
	        	var unSelectedRecevicer = document.getElementById("hidden"+checkBox.name).value;
	        	var temp =  unSelectedRecevicer + "_user" + ":" + checkBox.value;
	        	//重新设置隐藏的选中的组或用户
	        	parent.frames["buttomFrame"].document.getElementById("selectedRecevicers").value = filterUnselected(selectedRecevicers,temp);
	        	parent.frames["buttomFrame"].document.getElementById("recevicers").value = filterUnselected(selectedNames,checkBox.value);
	      		}
	    }
	    //从选中的部门或用户列表删除为选中的部门或用户
	    function filterUnselected(seletedRecevices,unSelectedRecevicer) {
			seletedRecevices = seletedRecevices.substring(0,seletedRecevices.lastIndexOf(","));
	      	var seletedReceviceArray = seletedRecevices.split(",");
	      	var temp = "";
	      	for (var i=0; i<seletedReceviceArray.length; i++) {
	        	if (seletedReceviceArray[i] == unSelectedRecevicer) {
	          		continue;
	        	}
	        temp = temp + seletedReceviceArray[i] + ",";
	      	}
			return temp;
		}
	    function setCheckedForDeptOrUser() {
			//获取隐藏已经被选中的部门或用户，是以Id1:name1,Id2:name2字符串组合一起的
	      	var selectedRecevicers = parent.frames["buttomFrame"].document.getElementById("selectedRecevicers").value;
	      	selectedRecevicers = selectedRecevicers.substring(0,selectedRecevicers.lastIndexOf(","));
	      	var seletedReceviceArray = selectedRecevicers.split(",");
	      	for (var i=0; i<seletedReceviceArray.length; i++) {
	        	var temp = seletedReceviceArray[i].split(":");
	        	if (document.getElementById(temp[0]) == null || 
					typeof(document.getElementById(temp[0])) != 'object' || 
	             	typeof(document.getElementById(temp[0])) == 'undefined') {
	              	continue;
	        	} 
			document.getElementById(temp[0]).checked = true;
			}
		}
	    window.onload = function() {
			if (parent.frames["buttomFrame"].document.getElementById("selectedRecevicers") != null) {
			setCheckedForDeptOrUser();	      
	      	}
	    }
		</script>
	</head>
	<body>
		<table cellSpaceing=0 cellPadding=0 width="100%" border=0>
	    <thead align="center" bgcolor="#D6E9F1">
			<tr>
	        	<td>部门列表</td>
	        	<td>用户列表</td>
	      	</tr>
	    </thead>
	    <tbody>
			<tr>
				<td width="40%">
	        	<table>
	        		<#if depts?exists>
			  		<#list depts as dept>
			    		<tr>
			      			<td>
			        		<input type="hidden" name="hidden#{dept.id?if_exists}_dept" value="#{dept.id?if_exists}"/>
			        		<input type="checkbox" name="#{dept.id?if_exists}_dept" value="${dept.name?if_exists}" onclick="setDeptValue(this);"/>
			        		<label for="fristSendFlag" class="label">${dept.name?if_exists}</label>  
			      			</td>
			    		</tr>
			  		</#list>
			 		</#if>
	        	</table>
	        	</td>
	        	<td>
	          	<table>
	         	<#if users?exists>
				  	<#list users?chunk(2) as towUsers>
						<tr>
					  		<#list towUsers as user>
					    	<td>
					      		<input type="hidden" name="hidden#{user.id?if_exists}_user" value="#{user.id?if_exists}"/>
					      		<input type="checkbox" name="#{user.id?if_exists}_user" value="${user.name?if_exists}" onclick="setUserValue(this);"/>
			              		<label for="fristSendFlag" class="label">${user.name?if_exists}</label>  
					    	</td>
							</#list>
						</tr>
					</#list>
					</#if>
				</table>
	        	</td>
			</tr>
		</tbody>
	  </table>
	</body>
</html>