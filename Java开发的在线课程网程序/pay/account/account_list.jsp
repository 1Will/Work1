<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="../../public/jsp/js.jsp"%>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<link href="/public/css/base_style.css" rel="stylesheet" type="text/css" />

<SCRIPT language=javascript>
			function searchRecord(){
			  document.pageForm.action="payAccountInfoAction.do?method=list";
			  document.pageForm.submit();
			}
			function addRecord(){
			  document.pageForm.action="payInfoAction.do";
			  document.pageForm.submit();
			}
</SCRIPT>
<title>账务明细</title>
</head>
<body>
<div id="fpage_main"  class="fpage_main" >
<div class="fpage_table" >
<h2>个人中心<span>&gt;&gt;</span>个人设置<span>&gt;&gt;</span>账务明细</h2>

<div class="fpage_table_main">
<form method="post" name="pageForm" >
<table width="98%" border="0" align="center">
   <tr>
		<td valign=top align="left" height="40">
		        <table width="100%" border="0" align="center">
			<tr>
				<td>请输入单号： <input name="outtradeno" id="outtradeno" class="input"
					title="请输入单号" type="text" value="" class="input01"
					style="width: 200px;" />
				<input type="button"
					class="button_skin" value="查询" onclick="javascript:searchRecord()" />
				</td>
			</tr>
		</table>
       </td>
	</tr>
	<tr>
		<td valign=top align="left" height="40"><input
			class="button_skin" onclick="addRecord()" type="button" value="充值"
			name="selectall" /></td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table class="ftable_data" width="100%">
			<tr>
				<th width="40" scope="col">序号</th>
				<th width="130" scope="col">日期</th>
				<th width="60" scope="col">增加</th>
				<th width="60" scope="col">减少</th>
				<th width="60" scope="col">余额</th>
				<th width="70" scope="col">来源</th>
				<th width="120" scope="col">单号</th>
				<th scope="col">备注</th>
			</tr>
			<!--循环列出所有数据-->
			<logic:notEmpty name="pagelist">
				<logic:iterate id="model" name="pagelist" property="datalist"
					indexId="index">
					<tr>
						<td height="30" class="table_list"><%=index + 1%></td>
						<td class="table_list"><bean:write name="model"
							property="createdate" /></td>
						<td class="table_list"><logic:equal value="1" name="model"
							property="flag">
				                                      ￥<bean:write name="model"
								property="changemoney" />
						</logic:equal></td>
						<td class="table_list"><logic:notEqual value="1" name="model"
							property="flag">
				                                       ￥<bean:write name="model"
								property="changemoney" />
						</logic:notEqual></td>
						<td class="table_list">￥<bean:write name="model"
							property="lastmoney" /></td>
						<td class="table_list"><bean:write name="model"
							property="paytypename" /></td>
						<td class="table_list"><bean:write name="model"
							property="outtradeno" /></td>
						<td class="table_list_left"><bean:write name="model"
							property="remark" /></td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
		</td>
	</tr>
	<logic:notEmpty name="pagelist">
		<tr>
			<td class="table_list"><java2page:pager
				url="skinTkSelectInfoAction.do?method=list" name="pagelist" /> <input
				type="hidden" name="startcount" id="startcount"
				value="<bean:write name="startcount"/>" /></td>
		</tr>
	</logic:notEmpty>
</table>
</form>
</div>
<div class="remark">
	<h3>说明</h3>
	<span class="remark_span">1.您可以查询你所有的账务明细;</span>
</div>
</div>
</div>
</body>
</html>
