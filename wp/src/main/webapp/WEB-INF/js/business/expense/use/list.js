$(function() {
            $("#info_datagrid").datagrid({
                height: '100%',
                method: 'post',
                url: contextPath + '/use/useList',
                pagination : true,//分页控件
        		rownumbers : true,//行号 
                columns: [[{
                	field: 'id',
                	checkbox: true,
                	width:'5%'
                },{
                	field: 'apply',
                    title: '费用编码',
                    width:'20%',
                    formatter:formatterA
                },{
                	field: 'apply11',
                    title: '费用名称',
                    width:'20%',
                    formatter:function(value, row, index){
                    	
                    	return row.apply.expensename;
                    }
                },{
                	field: 'userperson',
                    title: '使用人',
                    width:'15%'
                },{
                	field: 'usedmoney',
                    title: '使用金额',
                    width:'15%'
                }]],
                onCheck : function(rowIndex,rowData){
                	var data = $('#info_datagrid').datagrid('getChecked');
                	if(data.length==1){
                		$("#btn_editInfo").linkbutton('enable');
                		$("#btn_destroyInfo").linkbutton('enable');
                		
                	} else if(data.length > 1){
                		$("#btn_editInfo").linkbutton('disable');
                		$("#btn_destroyInfo").linkbutton('enable');
                		
                	} else {
                		$("#btn_editInfo").linkbutton('disable');
                		$("#btn_destroyInfo").linkbutton('disable');
                		
                	}
                },
                onUncheck : function(rowIndex,rowData){
                	var data = $('#info_datagrid').datagrid('getChecked');
                	if(data.length==1){
                		$("#btn_editInfo").linkbutton('enable');
                		$("#btn_destroyInfo").linkbutton('enable');
                		
                	} else if(data.length > 1){
                		$("#btn_editInfo").linkbutton('disable');
                		$("#btn_destroyInfo").linkbutton('enable');
                		
                	} else {
                		$("#btn_editInfo").linkbutton('disable');
                		$("#btn_destroyInfo").linkbutton('disable');
                		
                	}
                },
                toolbar: '#toolbar'
            });
          //设置分页控件 
            var p = $('#info_datagrid').datagrid('getPager'); 
            $(p).pagination({ 
                pageSize: 10,//每页显示的记录条数，默认为10 
                pageList: [5,10,15],//可以设置每页记录条数的列表 
                beforePageText: '第',//页数文本框前显示的汉字 
                afterPageText: '页    共 {pages} 页', 
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
            }); 
            
        });
//查询用户信息
function searchinfo() {
	$('#info_datagrid').datagrid('load',{
		userperson:$("input[name='usePerson']").val()
	});
	
	
}


//创建新用户
function newUse() {
	
	window.top.$("#dlg").dialog({
		title : '添加费用使用',
		style : '{padding:0}',
		width : 900,
		height : 600,
		closed : true,
		cache : false,
		href : contextPath + '/use/useAdd',
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
			$("#info_datagrid").datagrid('reload');
		}
	});
	window.top.$("#dlg").dialog('open');
}

function editUse() {
	var row = $('#info_datagrid').datagrid('getSelected');
	if (row) {
		window.top.$("#dlg").dialog({
			title : '编辑费用使用',
			style : '{padding:0}',
			width : 900,
			height : 600,
			closed : true,
			cache : false,
			href: contextPath + '/use/useEdit?id='+row.id,
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
				$("#info_datagrid").datagrid('reload');
			}
		});
		window.top.$("#dlg").dialog('open');
	}
}

function destroyUse() {
	var data = $('#info_datagrid').datagrid('getChecked');
	var str = new Array();
	if (data) {
		$.each(data, function(j, o) {
			str.push(o.id);
		});
	}
	if (str) {
		$.messager.confirm('Confirm', '确认删除该信息?', function(r) {
					if (r) {
						$.post(contextPath + '/use/delete', {
									ids : str
								}, function() {
									$('#info_datagrid').datagrid('reload'); // reload the user data
									$.messager.show({ // show error message
										title : 'success',
										msg : '删除成功！'
									});
								});
					}
				});
	}
}

function formatterA(value,row,index){
	return value.code;
} 

