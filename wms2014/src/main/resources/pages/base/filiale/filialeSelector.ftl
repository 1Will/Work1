<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('组织区域选择')}">
<table class="defaultLook">
<input type="hidden" id="userId" name="userId" value="#{user.id}"/>
<input type="hidden" id="ids" name="ids" value=""/>
<a id="reload" href="${req.contextPath}/filialeSelector/filialeSelector.html?userId=#{user.id}" style="display:none"></a>
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
					<#--加入组织区域-->
			var du = new dTree('du','../javascripts','addForm', 'addFilialeToken');
			du.add(0,-1,'组织区域');
			var idFlag;
			<#assign setp=0/>
			<#list allFiliale as filiale>
				<#if setp==0>
						du.add(#{filiale.id},0,'${filiale.name}',#{filiale.id},'fil');
						var nodeId = #{filiale.id}+#{count};
						<#if (filiale.departments.size()>0)>
						  <#if allDepartment?exists>
							<#list allDepartment as dept>
								<#if dept.filiale.id == filiale.id>
									du.add(nodeId,#{filiale.id},'${dept.name}',#{dept.id},'dept');
									nodeId++;
								</#if>
							</#list>
						  </#if>
							idFlag = nodeId;
						</#if>
				<#else>
						du.add(#{filiale.id},0,'${filiale.name}',#{filiale.id},'fil');
						<#if (filiale.departments.size()>0)>
						   <#if allDepartment?exists>
							<#list allDepartment as dept>
								<#if dept.filiale.id == filiale.id>
									du.add(idFlag,#{filiale.id},'${dept.name}',#{dept.id},'dept');
									idFlag++;
								</#if>
							</#list>
						   </#if>					
						</#if>
				</#if>
				<#assign setp=setp+1/>
			</#list>
			document.write(du);
		</SCRIPT>
		</div>
  	</td>
  	<td width="20%">
  		<input name="join" type="button" value="${action.getText('加入组织区域')}&gt;" size="15" onclick="addFilialeAndDept()"></br>
  		</br>
        <input name="leave" type="button" value="&lt;${action.getText('退出组织区域')}" size="15" onclick="quitFilialeAndDept()"></br>
  	</td>
  	<td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
  		<p><a href="javascript: d.openAll();">展开全部</a> | <a href="javascript: d.closeAll();">关闭全部</a></p>
  		<div id="" style="width:200px;height:250px;overflow:auto;border: 2px inset #FFFFFF;margin-left:10px">
 		<SCRIPT LANGUAGE="JavaScript">
		 			<#--退出组织区域-->
			var d = new dTree('d','../javascripts','qiutForm', 'qiutFilialeToken');
			d.add(0,-1,'组织区域');
			var idFlag1;
			<#assign setp=0/>
			<#list filiales as filiale1>
				<#if setp==0>
					d.add(#{filiale1.id},0,'${filiale1.name}',#{filiale1.id},'fils');
					var nodeId = #{filiale1.id}+#{count};
					<#list departments as dept>
						<#if dept.filiale.id==filiale1.id>
							d.add(nodeId,#{filiale1.id},'${dept.name}',#{dept.id},'depts');
							nodeId++;
						</#if>	
					</#list>
					idFlag1 = nodeId;
				<#else>
					d.add(#{filiale1.id},0,'${filiale1.name}',#{filiale1.id},'fils');
					<#list departments as dept>
						<#if dept.filiale.id==filiale1.id>
							d.add(idFlag1,#{filiale1.id},'${dept.name}',#{dept.id},'depts');
							idFlag1++;
						</#if>	
					</#list>
				</#if>
				<#assign setp=setp+1/>
			</#list>
			document.write(d);
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
		function addFilialeAndDept(){
			var ids = new Array();
			var form = getObjByNameRe("addForm");
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
			var url="${req.contextPath}/filialeSelector/addFilialeAndDept.html";
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
		
		function quitFilialeAndDept(){
			var ids = new Array();
			var form = getObjByNameRe("qiutForm");
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
			var url="${req.contextPath}/filialeSelector/quitFilialeAndDept.html";
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