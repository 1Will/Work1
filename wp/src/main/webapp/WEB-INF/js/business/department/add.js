function saveOrUpdate1(){
	//验证表单是否合法
	if (!$("#attr_form").form('validate')) {
		return false;
	}
	//对表单数据进行序列化
	var data = $("#attr_form :validInput").serialize();
	//获取用户选中的树的节点
	//ajax请求
	$.cajax({ 
		url: contextPath + '/department/saveOrUpdate?type=save', 
		type: 'POST',
		data: data,
		success: function(result){
			
			window.top.$("#dlg").dialog('close');
			$.messager.show({ // show error message
				title : 'success',
				msg : result.msg
			});
	    }
	});
}