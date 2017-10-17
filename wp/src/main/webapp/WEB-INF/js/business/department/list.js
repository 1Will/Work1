$(function() {
	$("#organization_tree").tree({
		animate : true,
		lines : true,
		url : contextPath + '/department/departmentLoad',
		method : 'post',
		onContextMenu : onContextMenu,
		onClick : function(node) {
			loadRight(node.id);
		}
	});
	$("#organization-center").tabs({
		title : "部门管理页面",
		border : false,
		fit : true
	});
});

function onContextMenu(e, row) {
	e.preventDefault();
	$(this).tree('select', row.target);
	$('#mm').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
}

function loadRight(id) {
	url = "<iframe src='"
			+ contextPath
			+ "/department/departmentDetail?id="
			+ id
			+ "' frameborder='0' scrolling='no' style='width:100%;height:98%;' />";
	$("#organization-center").tabs('close', '部门管理页面');
	$("#organization-center").tabs('add', {
		title : '部门管理页面',
		content : url,
		closable : true,
		fit : true
	});
}

// 鼠标右键菜单功能开始

function departmentAdd(id) {
	var node = $('#organization_tree').tree('getSelected');
	id = node.id;
	window.top.$("#dlg").dialog({
		title : '新增部门',
		style : '{padding:0}',
		width : 900,
		height : 600,
		closed : true,
		cache : false,
		href : contextPath + "/department/departmentAdd?parentId=" + id,
		modal : true,
		onMove : function(left, top) {
			if (top < 0) {
				$(this).dialog('move', {
					left : left,
					top : 0
				});
			}
		},
		onClose : function() {
			$("#organization_tree").tree('reload', node.target);
		}
	});
	window.top.$("#dlg").dialog('open');
}

function departmentDelete() {
	var node = $('#organization_tree').tree('getSelected');
	var node_parent = $('#organization_tree').tree('getParent', node.target);
	$.ajax({
		url : contextPath + "/department/" + node.id + "/deleteOne",
		type : 'get',
		
		success : function(result) {
			$("#organization_tree").tree('reload', node_parent.target);
			$.messager.show({ // show error message
				title : 'success',
				msg : result.msg
			});
		}
	});
}
// 鼠标右键菜单功能结束

