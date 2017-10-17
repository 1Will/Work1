
function saveOrUpdate(){
	//验证表单是否合法
	if (!$("#attr_form").form('validate')) {
		return false;
	}
	//对表单数据进行序列化
	var data = $("#attr_form :validInput").serialize();
	//获取用户选中的树的节点
	//ajax请求
	$.cajax({ 
		url: contextPath + '/custom/saveOrUpdate', 
		type: 'POST',
		data: data,
		success: function(result){
			
			window.location.href = contextPath + "/custom";
	    }
	});
}


$(function() {
	
	var id = $("#customId").val();
	if(id != null){
		$("#contact_datagrid").datagrid({
            height: '270%',
            method: 'post',
            url: contextPath + '/contact/contactList?customId='+id,
            pagination : true,//分页控件
    		rownumbers : true,//行号 
    		columns: [[{
            	field: 'name',
                title: '姓名',
                width:'15%'
            },{
            	field: 'school',
                title: '学校',
                width:'15%'
            },{
            	field: 'phone',
                title: '手机号',
                width:'20%'
            },{
            	field: 'office',
                title: '职位',
                width:'10%'
            },{
            	field: 'email',
                title: '邮箱',
                width:'20%'
            }]]
        });
      //设置分页控件 
        var p = $('#contact_datagrid').datagrid('getPager'); 
        $(p).pagination({ 
            pageSize: 10,//每页显示的记录条数，默认为10 
            pageList: [5,10,15],//可以设置每页记录条数的列表 
            beforePageText: '第',//页数文本框前显示的汉字 
            afterPageText: '页    共 {pages} 页', 
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
        }); 
	}
});

