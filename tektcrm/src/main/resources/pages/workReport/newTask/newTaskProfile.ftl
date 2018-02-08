<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('日程详细')}">
     <@ww.form action="'saveNewTask'" namespace="'/workReport'" method="'post'" >
       <@ww.token name="saveNewTaskToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       <@ww.hidden name="'isSaved'" value=""/>
         <#if newTask.id?exists>
                <@ww.hidden name="'newTask.id'" value="#{newTask.id?if_exists}"/>
         </#if>
         
         <#if newTask.doString?exists>
                <@ww.hidden name="'doString'" value="'${newTask.doString?if_exists}'"/>
         <#else>
                <@ww.hidden name="'doString'" value="'${json?if_exists}'"/>
         </#if>
         
         <#if newTask.copyString?exists>
                <@ww.hidden name="'copyString'" value="'${newTask.copyString?if_exists}'"/>
         <#else>
                <@ww.hidden name="'copyString'" value="'${json?if_exists}'"/>
         </#if>
         
        <@inputTable>
    	<tr>
    		<@ww.textfield label="'${action.getText('日程编码')}'" name="'newTask.code'"  cssClass="'underline'"  disabled = "true" /><td></td>
    		<@ww.textfield label="'${action.getText('日程名称')}'" name="'newTask.name'"  cssClass="'underline'"  required="true"/><td></td>
    	</tr>
		<tr id="date">
			<#include "time.ftl"/>
			<script language="JavaScript" type="text/JavaScript">
				var tds = getObjByName("date").getElementsByTagName("td");
				tds[1].setAttribute("style","width: 170px");
				tds[4].setAttribute("style","width: 170px");
			</script>
		</tr>
		<tr>
        	<@select 
        		label="${action.getText('日程状态')}" 
				name="state.id" 
				id="state.id" 
				value="${req.getParameter('state.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allState"
				required="true"
				emptyOption="false" 
				disabled="true">
			</@select>
		</tr>
		<tr>
			<@textarea id="newTask.content" name="newTask.content" label="${action.getText('任务内容')}" value="${newTask.content?if_exists}" rows="4" />
			<script language="JavaScript" type="text/JavaScript">
				var width=document.body.clientWidth/9;
				getObjByName("newTask.content").cols =width;
			</script>
    	</tr>
    	
		<tr><td colspan="6"><hr/></td></tr>
		
		<tr>
				<td align="right" valign="top">执行人:</td><td></td><td></td>
		</tr>
		<tr>
				
				<td align="right" valign="top">
					<label class="label">${action.getText('选择人')}:</label>
				</td>
				<td>
					<input type="text" name="usersDo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
					<a onClick="user_doString();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td><td></td>
				
				<td align="right" valign="top">
					<label class="label">${action.getText('选择组')}:</label>
				</td>
				<td>
					<input type="text" name="groupIdsDo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
					<a onClick="group_doString();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
				<td></td>
				
		</tr>
		<tr>
				
				<td align="right" valign="top">
					<label class="label">${action.getText('选择部门')}:</label>
				</td>
				<td>
					<input type="text" name="deptIdsDo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
					<a onClick="dept_doString();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
		</tr>
		
		<tr><td colspan="6"><hr/></td></tr>
		
		<tr>
				<td align="right" valign="top">抄送人:</td><td></td><td></td>
		</tr>
		<tr>
				<td align="right" valign="top">
					<label class="label">${action.getText('选择人')}:</label>
				</td>
				<td>
					<input type="text" name="usersCopy" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
					<a onClick="user_copyString();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td><td></td>
				
				<td align="right" valign="top">
					<label class="label">${action.getText('选择组')}:</label>
				</td>
				<td>
					<input type="text" name="groupIdsCopy" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
					<a onClick="group_copyString();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
				<td></td>
				
			</tr>
			<tr>
				
				<td align="right" valign="top">
					<label class="label">${action.getText('选择部门')}:</label>
				</td>
				<td>
					<input type="text" name="deptIdsCopy" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
					<a onClick="dept_copyString();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
		</tr>
		
		<tr><td colspan="6"><hr/></td></tr>
		<tr><td align="right" valign="top">提醒时间(可多选):</td></tr>
		<tr>
			<td></td>
			<td align="left" valign="top">
				<input type="checkbox" name="time" value="5" />提前5分钟
			</td>
			<td align="left" valign="top" style="width: 170px;">
				<input type="checkbox" name="time" value="10" />提前10分钟
			</td>
			<td align="left" valign="top">
				<input type="checkbox" name="time" value="30" />提前30分钟
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="left" valign="top">
				<input type="checkbox" name="time" value="60" />提前1小时
			</td>
			<td align="left" valign="top" style="width: 170px;">
				<input type="checkbox" name="time" value="180" />提前3小时
			</td>
			<td align="left" valign="top">
				<input type="checkbox" name="time" value="1440" />提前1天
			</td>
		</tr>
		
		</@inputTable>
		<@buttonBar>
			<#if !(action.isReadOnly())>
				<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
				<#if newTask.isSaved?exists && newTask.isSaved=='0' >
					<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
				<#else>
					<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
				</#if>
				
				<#if  (newTask.state?exists && newTask.state.code =='30601') || (newTask.state?exists && newTask.state.code =='30602')>
					<@vsubmit name="'complete'" value="'${action.getText('完成')}'" onclick="'return true;'"/>
				<#else>
					<input type="button" class="button" value="${action.getText('完成')}"  disabled="true"/>
				</#if>
				
			</#if>
			<@redirectButton value="${action.getText('back')}" url="listNewTask.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
		</@buttonBar>	
		</@ww.form>

<script>
<#if newTask?exists>
	<#if newTask.state?exists>
		getObjByName('state.id').value = '#{newTask.state.id?if_exists}';
	</#if>
	<#if newTask.time?exists>
		var time = '${newTask.time?if_exists}';
		var times =time.split(",");
		var tag = document.getElementsByName('time');
		for(var i = 0; i < tag.length; i++){
			if(times.indexOf(tag[i].value)>=0){
				tag[i].checked="checked";
			}
		} 
	</#if>
</#if>

			var doString = getObjByName("doString").value;
			var obj = eval("("+doString+")");
			var a =obj.userIds.split(",").length;
			if('' == obj.userIds){a = 0;}
			var b =obj.groupIds.split(",").length;
			if('' == obj.groupIds){b = 0;}
			var c =obj.deptIds.split(",").length;
			if('' == obj.deptIds){c = 0;}
			getObjByName("usersDo").value = "已选择"+a+"人" ;
			getObjByName("groupIdsDo").value = "已选择"+b+"个组" ;
			getObjByName("deptIdsDo").value = "已选择"+c+"个部门" ;
			
			var copyString = getObjByName("copyString").value;
			var copyobj = eval("("+copyString+")");
			var d =copyobj.userIds.split(",").length;
			if('' == copyobj.userIds){d = 0;}
			var e =copyobj.groupIds.split(",").length;
			if('' == copyobj.groupIds){e = 0;}
			var f =copyobj.deptIds.split(",").length;
			if('' == copyobj.deptIds){f = 0;}
			getObjByName("usersCopy").value = "已选择"+d+"人" ;
			getObjByName("groupIdsCopy").value = "已选择"+e+"个组" ;
			getObjByName("deptIdsCopy").value = "已选择"+f+"个部门" ;
	
		function submitt(){
			getObjByName('isSaved').value="1";
			return storeValidation();
		}
		function savee(){
			getObjByName('isSaved').value="0";
		 	return storeValidation();
		}
	
		function storeValidation(){
			if(getObjByName('newTask.name').value==''){
			alert('请输入日程名称');
			getObjByName('newTask.name').focus();
			return false;
		}
		
		
		var startTime = getObjByName('newTask.startTime').value;
		if(startTime ==''){
			alert('请选择开始时间');
			return false;
		}
		
		var endTime = getObjByName('newTask.endTime').value;
		if(endTime ==''){
			alert('请选择结束时间');
			return false;
		}
		
		var startHour = getObjByName("startHour").value;
		var startMinute = getObjByName("startMinute").value;
		var start = startTime.replace(/-/g,"")+startHour+startMinute;
		var endHour = getObjByName("endHour").value;
		var endMinute = getObjByName("endMinute").value;
		var end = endTime.replace(/-/g,"")+endHour+endMinute;
		if(parseFloat(end)<=parseFloat(start)){
			alert('时间区间有误，请重新选择！');
			return false;
		}
		
		getObjByName("state.id").removeAttribute("disabled");
		return true;
	}
	
	//用户的选择
        function user_doString() {
          var url = "${req.contextPath}/popup/userSelector.html?multipleSelect=T";
          var doString = getObjByName("doString").value;
          var obj = eval("("+doString+")");
	      if (null != obj.userIds) {
	        url = url + '&uIds=' + obj.userIds;
	      }
	      popupModalDialog(url, 800, 600, userDo);
        }
        
	function userDo(result) {
		if (null != result) {
			var n =result.split(",").length;
			if(result == ''){
				n=0;
			}
			var doString = getObjByName("doString").value;
			var obj = eval("("+doString+")");
			obj.userIds = result;
			var last= JSON.stringify(obj);
			getObjByName("doString").value = last ;
			getObjByName("usersDo").value = "已选择"+n+"人" ;
		}
	}
	
	//组的选择
        function group_doString() {
          var url = "${req.contextPath}/communication/groups/listGroupsWindow.html";
          var doString = getObjByName("doString").value;
          var obj = eval("("+doString+")");
	      if (null != obj.groupIds) {
	        url = url + '?gIds=' + obj.groupIds;
	      }
	      popupModalDialog(url, 800, 600, groupDo);
        }
        
	function groupDo(result) {
		if (null != result) {
			var n =result.split(",").length;
			if(result == ''){
				n=0;
			}
			var doString = getObjByName("doString").value;
			var obj = eval("("+doString+")");
			obj.groupIds = result;
			var last= JSON.stringify(obj);
			getObjByName("doString").value = last ;
			getObjByName("groupIdsDo").value = "已选择"+n+"个组" ;
		}
	}
	
	//部门的选择
        function dept_doString() {
          var url = "${req.contextPath}//base/department/listDepartmentsWindow.html";
          var doString = getObjByName("doString").value;
          var obj = eval("("+doString+")");
	      if (null != obj.deptIds) {
	        url = url + '?dIds=' + obj.deptIds;
	      }
	      popupModalDialog(url, 800, 600, deptDo);
        }
        
	function deptDo(result) {
		if (null != result) {
			var n =result.split(",").length;
			if(result == ''){
				n=0;
			}
			var doString = getObjByName("doString").value;
			var obj = eval("("+doString+")");
			obj.deptIds = result;
			var last= JSON.stringify(obj);
			getObjByName("doString").value = last ;
			getObjByName("deptIdsDo").value = "已选择"+n+"个部门" ;
		}
	}
	
	//用户的选择
        function user_copyString() {
          var url = "${req.contextPath}/popup/userSelector.html?multipleSelect=T";
          var copyString = getObjByName("copyString").value;
          var obj = eval("("+copyString+")");
	      if (null != obj.userIds) {
	        url = url + '&uIds=' + obj.userIds;
	      }
	      popupModalDialog(url, 800, 600, userCopy);
        }
        
	function userCopy(result) {
		if (null != result) {
			var n =result.split(",").length;
			if(result == ''){
				n=0;
			}
			var copyString = getObjByName("copyString").value;
			var obj = eval("("+copyString+")");
			obj.userIds = result;
			var last= JSON.stringify(obj);
			getObjByName("copyString").value = last ;
			getObjByName("usersCopy").value = "已选择"+n+"人" ;
		}
	}
	
	//组的选择
        function group_copyString() {
          var url = "${req.contextPath}/communication/groups/listGroupsWindow.html";
          var copyString = getObjByName("copyString").value;
          var obj = eval("("+copyString+")");
	      if (null != obj.groupIds) {
	        url = url + '?gIds=' + obj.groupIds;
	      }
	      popupModalDialog(url, 800, 600, groupCopy);
        }
        
	function groupCopy(result) {
		if (null != result) {
			var n =result.split(",").length;
			if(result == ''){
				n=0;
			}
			var copyString = getObjByName("copyString").value;
			var obj = eval("("+copyString+")");
			obj.groupIds = result;
			var last= JSON.stringify(obj);
			getObjByName("copyString").value = last ;
			getObjByName("groupIdsCopy").value = "已选择"+n+"个组" ;
		}
	}
	
	//部门的选择
        function dept_copyString() {
          var url = "${req.contextPath}//base/department/listDepartmentsWindow.html";
          var copyString = getObjByName("copyString").value;
          var obj = eval("("+copyString+")");
	      if (null != obj.deptIds) {
	        url = url + '?dIds=' + obj.deptIds;
	      }
	      popupModalDialog(url, 800, 600, deptCopy);
        }
        
	function deptCopy(result) {
		if (null != result) {
			var n =result.split(",").length;
			if(result == ''){
				n=0;
			}
			var copyString = getObjByName("copyString").value;
			var obj = eval("("+copyString+")");
			obj.deptIds = result;
			var last= JSON.stringify(obj);
			getObjByName("copyString").value = last ;
			getObjByName("deptIdsCopy").value = "已选择"+n+"个部门" ;
		}
	}
</script>
</@htmlPage>