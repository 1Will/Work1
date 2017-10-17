$(function() {
            $("#plan_datagrid").datagrid({
                height: '100%',
                method: 'post',
                url: contextPath + '/returnPlan/planList',
                pagination : true,//分页控件
        		rownumbers : true,//行号 
                columns: [[{
                	field: 'id',
                	checkbox: true,
                	width:'5%'
                },{
                	field: 'code',
                    title: '合同编码',
                    width:'10%'
                },{
                	field: 'contractname',
                    title: '合同名称',
                    width:'10%'
                },{
                	field: 'customCode',
                    title: '客户编码',
                    width:'10%'
                },{
                	field: 'custoName',
                    title: '客户名称',
                    width:'10%'
                },{
                	field: 'phone',
                    title: '联系电话',
                    width:'10%'
                },{
                	field: 'planDate',
                    title: '计划日期',
                    width:'10%',
                    formatter:formatterB
                },{
                	field: 'isOrNot',
                    title: '是否到款',
                    width:'7%',
                    formatter:formatterA
                },{
                	field: 'billingOrIs',
                    title: '是否开票',
                    width:'7%',
                    formatter:formatterA
                },{
                	field: 'yingfu',
                    title: '应付金额(元)',
                    width:'10%'
                },{
                	field: 'batch',
                    title: '批次',
                    width:'10%'
                }]],
                onCheck : function(rowIndex,rowData){
                	var data = $('#plan_datagrid').datagrid('getChecked');
                	if(data.length==1){
                		$("#btn_editContract").linkbutton('enable');
                		$("#btn_destroyContract").linkbutton('enable');
                		
                	} else if(data.length > 1){
                		$("#btn_editContract").linkbutton('disable');
                		$("#btn_destroyContract").linkbutton('enable');
                		
                	} else {
                		$("#btn_editContract").linkbutton('disable');
                		$("#btn_destroyContract").linkbutton('disable');
                		
                	}
                },
                onUncheck : function(rowIndex,rowData){
                	var data = $('#plan_datagrid').datagrid('getChecked');
                	if(data.length==1){
                		$("#btn_editContract").linkbutton('enable');
                		$("#btn_destroyContract").linkbutton('enable');
                		
                	} else if(data.length > 1){
                		$("#btn_editContract").linkbutton('disable');
                		$("#btn_destroyContract").linkbutton('enable');
                		
                	} else {
                		$("#btn_editContract").linkbutton('disable');
                		$("#btn_destroyContract").linkbutton('disable');
                		
                	}
                },
                toolbar: '#toolbar'
            });
          //设置分页控件 
            var p = $('#plan_datagrid').datagrid('getPager'); 
            $(p).pagination({ 
                pageSize: 10,//每页显示的记录条数，默认为10 
                pageList: [5,10,15],//可以设置每页记录条数的列表 
                beforePageText: '第',//页数文本框前显示的汉字 
                afterPageText: '页    共 {pages} 页', 
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
            }); 
            
        });
//查询用户信息
function searchPlan() {
	$('#plan_datagrid').datagrid('load',{
		name:$("input[name='name']").val()
	});
	
	
}

function clearForm() {
	$('#attr_form').form('clear');
}

function formatterA(value,row,index){
	if(value==1)
    return '是'; 
	else 
	return '否';
} 

function formatterB(value,row,index){
	var d = new Date(value);
	return d.toLocaleDateString();
}



//创建新用户
function newPlan() {
	window.top.$("#dlg").dialog({
		title : '添加回款计划',
		style : '{padding:0}',
		width : 900,
		height : 600,
		closed : true,
		cache : false,
		href : contextPath + '/returnPlan/planAdd',
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
			$("#plan_datagrid").datagrid('reload');
		}
	});
	window.top.$("#dlg").dialog('open');
}

function editPlan() {
	var row = $('#plan_datagrid').datagrid('getSelected');
	if (row) {
		window.top.$("#dlg").dialog({
			title : '编辑回款计划',
			style : '{padding:0}',
			width : 900,
			height : 600,
			closed : true,
			cache : false,
			href: contextPath + '/returnPlan/planEdit?id='+row.id,
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
				$("#plan_datagrid").datagrid('reload');
			}
		});
		window.top.$("#dlg").dialog('open');
	}
}

function destroyPlan() {
	var data = $('#plan_datagrid').datagrid('getChecked');
	var str = new Array();
	if (data) {
		$.each(data, function(j, o) {
			str.push(o.id);
		});
	}
	if (str) {
		$.messager.confirm('Confirm', '确认删除该信息?', function(r) {
					if (r) {
						$.post(contextPath + '/returnPlan/delete', {
									ids : str
								}, function() {
									$('#plan_datagrid').datagrid('reload'); // reload the user data
									$.messager.show({ // show error message
										title : 'success',
										msg : '删除成功！'
									});
								});
					}
				});
	}
}

