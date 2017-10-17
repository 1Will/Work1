$(function() {
            $("#contact_datagrid").datagrid({
                height: '100%',
                method: 'post',
                url: contextPath + '/contact/contactList',
                pagination : true,//分页控件
        		rownumbers : true,//行号 
                columns: [[{
                	field: 'id',
                	checkbox: true,
                	width:'5%'
                },{
                	field: 'name',
                    title: '联系人',
                    width:'15%'
                },{
                	field: 'custName',
                    title: '客户名',
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
                }]],
                onCheck : function(rowIndex,rowData){
                	var data = $('#contact_datagrid').datagrid('getChecked');
                	if(data.length==1){
                		$("#btn_editContact").linkbutton('enable');
                		$("#btn_destroyContact").linkbutton('enable');
                		
                	} else if(data.length > 1){
                		$("#btn_editContact").linkbutton('disable');
                		$("#btn_destroyContact").linkbutton('enable');
                		
                	} else {
                		$("#btn_editContact").linkbutton('disable');
                		$("#btn_destroyContact").linkbutton('disable');
                		
                	}
                },
                onUncheck : function(rowIndex,rowData){
                	var data = $('#contact_datagrid').datagrid('getChecked');
                	if(data.length==1){
                		$("#btn_editContact").linkbutton('enable');
                		$("#btn_destroyContact").linkbutton('enable');
                		
                	} else if(data.length > 1){
                		$("#btn_editContact").linkbutton('disable');
                		$("#btn_destroyContact").linkbutton('enable');
                		
                	} else {
                		$("#btn_editContact").linkbutton('disable');
                		$("#btn_destroyContact").linkbutton('disable');
                		
                	}
                },
                toolbar: '#toolbar'
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
            
        });
//查询用户信息
function searchContact() {
	$('#contact_datagrid').datagrid('load',{
		name:$("input[name='name']").val(),
		custName:$("input[name='custName']").val()
	});
	
	
}


//创建新用户
function newContact() {
	window.top.$("#dlg").dialog({
		title : '添加联系人',
		style : '{padding:0}',
		width : 900,
		height : 600,
		closed : true,
		cache : false,
		href : contextPath + '/contact/contactAdd',
		modal : true,
		onMove : function(left, top) {
			if(top < 0) {
				$(this).dialog('move',{
					left: left,
					top: 0
				});
			}
		},
		onClose : function() {
			$("#contact_datagrid").datagrid('reload');
		}
	});
	window.top.$("#dlg").dialog('open');
}

function editContact() {
	var row = $('#contact_datagrid').datagrid('getSelected');
	if (row) {
		window.top.$("#dlg").dialog({
			title : '编辑联系人',
			style : '{padding:0}',
			width : 900,
			height : 600,
			closed : true,
			cache : false,
			href: contextPath + '/contact/contactEdit?id='+row.id,
			modal : true,
			onMove : function(left, top) {
				if(top < 0) {
					$(this).dialog('move',{
						left: left,
						top: 0
					});
				}
			},
			onClose : function() {
				$("#contact_datagrid").datagrid('reload');
			}
		});
		window.top.$("#dlg").dialog('open');
	}
}

function destroyContact() {
	var data = $('#contact_datagrid').datagrid('getChecked');
	var str = new Array();
	if (data) {
		$.each(data, function(j, o) {
			str.push(o.id);
		});
	}
	if (str) {
		$.messager.confirm('Confirm', '确认删除该联系人?', function(r) {
					if (r) {
						$.post(contextPath + '/contact/delete', {
									ids : str
								}, function() {
									$('#contact_datagrid').datagrid('reload'); // reload the user data
									$.messager.show({ // show error message
										title : 'success',
										msg : '删除成功！'
									});
								});
					}
				});
	}
}

function clearForm() {
	$('#attr_form').form('clear');
}