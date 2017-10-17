
function saveOrUpdate(){
	//验证表单是否合法
	if (!$("#attr_form").form('validate')) {
		return false;
	}
	//对表单数据进行序列化
	var data = $("#attr_form :validInput").serialize();
	//ajax请求
	$.cajax({ 
		url: contextPath + '/record/saveOrUpdate', 
		type: 'POST',
		data: data,
		success: function(result){
			
			window.location.href = contextPath + "/record";
	    }
	});
}