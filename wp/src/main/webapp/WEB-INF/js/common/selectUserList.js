$(function() {
            $("#selectUser").datagrid({
                height: '400px',
                method: 'post',
                url: contextPath + '/user/userListDG',
                pagination : true,//分页控件
        		rownumbers : true,//行号 
                columns: [[{
                	field: 'id',
                	checkbox: true,
                	width:'5%'
                },{
                	field: 'username',
                    title: '登录账号',
                    width:'15%'
                },{
                	field: 'realName',
                    title: '姓名',
                    width:'15%'
                },{
                	field: 'code',
                    title: '工号',
                    width:'15%'
                },{
                	field: 'locked',
                    title: '是否激活',
                    width:'10%',
                    formatter: function(value,row,index){
        				if (row.locked=="E"){
        					return "有效";
        				} else {
        					return "无效";
        				}
                    }
                },{
                	field: 'cellphone',
                    title: '手机',
                    width:'20%'
                },{
                	field: 'email',
                    title: '邮箱',
                    width:'20%'
                }]]
            });
          //设置分页控件 
            var p = $('#selectUser').datagrid('getPager'); 
            $(p).pagination({ 
                pageSize: 10,//每页显示的记录条数，默认为10 
                pageList: [5,10,15],//可以设置每页记录条数的列表 
                beforePageText: '第',//页数文本框前显示的汉字 
                afterPageText: '页    共 {pages} 页', 
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
            }); 
        });
//查询用户信息
function searchUser() {
	$('#selectUser').datagrid('load',{
		username:$("input[name='username1']").val(),
		realName:$("input[name='realName1']").val()
	});
	
	
}

function clearForm() {
	$('#attr_form1').form('clear');
}