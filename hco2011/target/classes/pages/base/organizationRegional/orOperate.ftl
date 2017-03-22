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

<#-- $Id: orOperate.ftl 2009-11-11 10:10:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('组织区域选择')}">
<table class="defaultLook">
<input type="hidden" id="userId" name="userId" value="#{user.id}"/>
<input type="hidden" id="ids" name="ids" value=""/>
<a id="reload" href="${req.contextPath}/organizationRegional/orSelector.html?userId=#{user.id}" style="display:none"></a>
  <tr width="100%">
	<td style="text-align:center" width="40%"><b>可选组织区域<b></td>
	<td width="20%"></td>
	<td style="text-align:center" width="40%"><b>已选组织区域</b></td>
  </tr>
  <tr width="100%">
  	<td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
  	<p><a href="javascript: du.openAll();">展开全部</a> | <a href="javascript: du.closeAll();">关闭全部</a></p>
		<div id="" style="width:200px;height:250px;overflow:auto;border: 2px inset #FFFFFF;margin-left:10px">
		<SCRIPT LANGUAGE="JavaScript">
					<#--加入组织区域
			var du = new dTree('du','../javascripts','addForm', 'addORToken');
			du.add(0,-1,'组织区域');
			var idFlag;
			<#assign setp=0/>
			<#list allInsts as inst>
				<#if setp==0>
					du.add(#{inst.id},0,'${inst.name}',#{inst.id},'fil');
					var nodeId = #{count};
					<#if (allDepts.size()>0)>
						<#list allDepts as dept>
							<#if dept.inst.id == inst.id>
								du.add(nodeId,#{inst.id},'${dept.name}',#{dept.id},'dept');
								nodeId++;
							</#if>
						</#list>
					</#if>
					idFlag = nodeId;
				<#else>
					du.add(#{inst.id},0,'${inst.name}',#{inst.id},'fil');
					<#if (allDepts.size()>0)>
						<#list allDepts as dept>
							<#if dept.inst.id == inst.id>
								du.add(idFlag,#{inst.id},'${dept.name}',#{dept.id},'dept');
								idFlag++;
								
							</#if>
						</#list>
					</#if>	
				</#if>
				<#assign setp=setp+1/>
			</#list>
			-->
			var du = new dTree('du','../javascripts','addForm','addORToken');
			du.add(0,-1,'组织区域');
			<#assign instStep=0>
			<#list allInstsSortByStep as instList>
				<#if instStep==0>
				    <#list instList as inst>
						du.add(#{inst.id},0,'${inst.name}(单位)',#{inst.id},'comment');	
				    </#list>
				<#else>
				    <#list instList as inst>
				      du.add(#{inst.id},#{inst.parentInst.id},'${inst.name}(单位)',#{inst.id},'comment');	
				    </#list>
			  	</#if>
				<#assign instStep=instStep+1/>
			</#list>
			var nodeId = #{count}+1;
			<#assign deptStep=0>
			<#list allDeptSortByStep as deptList>
				<#if deptStep==0>
				    <#list deptList as dept>
						du.add(#{dept.id}+nodeId,#{dept.inst.id},'${dept.name}(部门)',#{dept.id},'dept');
				    </#list>
				<#else>
				    <#list deptList as dept>
				      	du.add(#{dept.id}+nodeId,#{dept.parentDept.id}+nodeId,'${dept.name}(部门)',#{dept.id},'dept');
				    </#list>
			  	</#if>
				<#assign deptStep=deptStep+1/>
			</#list>
			document.write(du);
		</SCRIPT>
		</div>
  	</td>
  	<td width="20%">
  		<input name="join" type="button" value="${action.getText('加入组织区域')}&gt;" size="15" onclick="addToOrganizationRegional()"></br>
  		</br>
        <input name="leave" type="button" value="&lt;${action.getText('退出组织区域')}" size="15" onclick="quitToOrganizationRegional()"></br>
  	</td>
  	<td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
  		<p><a href="javascript: r.openAll();">展开全部</a> | <a href="javascript: r.closeAll();">关闭全部</a></p>
  		<div id="" style="width:200px;height:250px;overflow:auto;border: 2px inset #FFFFFF;margin-left:10px">
 		<SCRIPT LANGUAGE="JavaScript">
		 			<#--退出组织区域
			var r = new dTree('r','../javascripts','qiutForm', 'qiutORToken');
			r.add(0,-1,'组织区域');
			var idFlag1;
			<#assign setp=0/>
			<#list institutions as inst>
				<#if setp==0>
					r.add(#{inst.id},0,'${inst.name}',#{inst.id},'fils');
					var nodeId = #{count}+1;
					<#if (departments.size()>0)>
						<#list departments as dept>
							<#if dept.inst.id == inst.id>
								r.add(nodeId+#{dept.id},#{inst.id},'${dept.name}',#{dept.id},'depts');
								//nodeId++;
							</#if>	
						</#list>
					</#if>
					//idFlag1 = nodeId;
				<#else>
					r.add(#{inst.id},0,'${inst.name}',#{inst.id},'fils');
					<#if (departments.size()>0)>
						<#list departments as dept>
							<#if dept.inst.id==inst.id>
								r.add(idFlag1,#{inst.id},'${dept.name}',#{dept.id},'depts');
								idFlag1++;
							</#if>	
						</#list>
					</#if>
				</#if>
				<#assign setp=setp+1/>
			</#list>
			-->
			var r = new dTree('r','../javascripts','qiutForm', 'qiutORToken');
			r.add(0,-1,'组织区域');
			<#assign iStep=0/>
			<#list institutions as instList>
				<#if iStep==0>
				    <#list instList as inst>
						r.add(#{inst.id},0,'${inst.name}(单位)',#{inst.id},'comment');	
				    </#list>
				<#else>
				    <#list instList as inst>
				      	r.add(#{inst.id},#{inst.parentInst.id},'${inst.name}(单位)',#{inst.id},'comment');	
				    </#list>
			  	</#if>
				<#assign iStep=iStep+1/>
			</#list>
			var flag = #{count}+1;
			<#assign dStep=0>
			<#list departments as deptList>
				<#if dStep==0>
				    <#list deptList as dept>
						r.add(#{dept.id}+flag,#{dept.inst.id},'${dept.name}(部门)',#{dept.id},'depts');
				    </#list>
				<#else>
				    <#list deptList as dept>
				      	r.add(#{dept.id}+flag,#{dept.parentDept.id}+flag,'${dept.name}(部门)',#{dept.id},'depts');
				    </#list>
			  	</#if>
				<#assign dStep=dStep+1/>
			</#list>
			document.write(r);
		</SCRIPT> 			
  		</div>
  	</td>
  </tr>
  <tr><td colspan=3>
  <input type="button" class="MyButton" value="关闭窗口" onclick="window.close()">
  </td></tr>
</table>
</body>
<script type="text/javascript">
		function addToOrganizationRegional(){
			var ids = new Array();
			var form = document.getElementById("addForm");
			for (var i=0; i<form.elements.length; i++) {
				var element = form.elements[i];
				if (element.title == "dept" && element.type=='checkbox'){
					if( element.checked == true ){
						ids.push(element.value);
					}
				}
			}
			if(ids ==""){
				alert("请选择需要加入的组织区域");
				return false;
			}
			//alert('ids='+ids);
			getObjByName('ids').value=ids;
			
			//createxmlhttprequest();
			var url="${req.contextPath}/organizationRegional/addToOrganizationRegional.html";
			var param = "ids="+ids+"&userId="+getObjByName('userId').value;
						
			//xmlhttpreq.open("get",encodeURI(url),false);
 			//xmlhttpreq.onreadystatechange = callback;
 			//xmlhttpreq.send(null);
 			var ajaxR = new Ajax.Request(
 			             url,
 			             {
 			              method:'get',
 			              parameters:param,
 			              onComplete:callback,
 			              asynchronous:false
 			             }
 			            );
		        	//getObjByName('reload').click();
		}
		
		function quitToOrganizationRegional(){
			var ids = new Array();
			var form = document.getElementById("qiutForm");
			for (var i=0; i<form.elements.length; i++) {
				var element = form.elements[i];
				if (element.title == "depts" && element.type=='checkbox'){
					if( element.checked == true ){
						ids.push(element.value);
					}
				}
			}
			if(ids ==""){
				alert("请选择需要退出的组织区域");
				return false;
			}
			//alert('ids='+ids);
			getObjByName('ids').value=ids;
			
			//document.forms[0].submit();
			
			//createxmlhttprequest();
			var url="${req.contextPath}/organizationRegional/quitToOrganizationRegional.html";
			var param = "ids="+ids+"&userId="+getObjByName('userId').value;
			//xmlhttpreq.open("get",encodeURI(url),false);
 			//xmlhttpreq.onreadystatechange = callback;
 			//xmlhttpreq.send(null);
 			 var ajaxR = new Ajax.Request(
 			             url,
 			             {
 			              method:'get',
 			              parameters:param,
 			              onComplete:callback,
 			              asynchronous:false
 			             }
 			            );	
 					
		}
		function createxmlhttprequest(){
			 	if (window.XMLHttpRequest) {
			 		xmlhttpreq = new XMLHttpRequest();
			 	}else if (window.ActiveXObject) {
			 		try {
			 			xmlhttpreq = new ActiveXObject("Msxml2.XMLHTTP");
			 		}catch (e) {
			 			try {
			 				xmlhttpreq = new ActiveXObject("microsoft.XMLHTTP");
			 			}catch (e) {}
			 		}
			 	}
		}
		
		function callback()   
		{   
		   alert('选择成功！');
		   getObjByName('reload').click();
		}  	
</script>
</@htmlPage>