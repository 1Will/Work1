$(function() {
            $("#info_datagrid").datagrid({
                height: '100%',
                method: 'post',
                url: contextPath + '/custom/customList',
                pagination : true,//分页控件
        		rownumbers : true,//行号 
                columns: [[{
                	field: 'id',
                	checkbox: true,
                	width:'5%'
                },{
                	field: 'code',
                    title: '编码',
                    width:'15%',
                    formatter:formatA
                },{
                	field: 'name',
                    title: '名称',
                    width:'15%'
                },{
                	field: 'corporate',
                    title: '企业法人',
                    width:'15%'
                },{
                	field: 'maorContact',
                    title: '联系人',
                    width:'20%'
                },{
                	field: 'mobile',
                    title: '手机号',
                    width:'10%'
                },{
                	field: 'customerType',
                    title: '客户状态',
                    width:'20%'
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
		name:$("input[name='name']").val(),
		code:$("input[name='code']").val()
	});
	
	
}


//创建新用户
function newInfo() {
	
	window.location.href = contextPath + "/custom/customAdd";
}

function editInfo() {
	var row = $('#info_datagrid').datagrid('getSelected');
	if (row) {
		window.location.href = contextPath + "/custom/customEdit?id="+row.id;
	}
}

function destroyInfo() {
	var data = $('#info_datagrid').datagrid('getChecked');
	var str = new Array();
	if (data) {
		$.each(data, function(j, o) {
			str.push(o.id);
		});
	}
	if (str) {
		$.messager.confirm('Confirm', '确认删除该客户信息?', function(r) {
					if (r) {
						$.post(contextPath + '/custom/delete', {
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

function clearForm() {
	$('#attr_form').form('clear');
}

function formatA(value,row,index){
    return '<span data-p1='+index+' class="easyui-tooltip">' + value + '</span>';  
} 

function createTooltip(){  
    $('#info_datagrid').datagrid('getPanel').find('.easyui-tooltip').each(function(){  
        var index = parseInt($(this).attr('data-p1'));  
        $(this).tooltip({  
            content: $('<div></div>'),  
            onUpdate: function(cc){  
                var row = $('#info_datagrid').datagrid('getRows')[index];  
                var content = '<div style="color:blue;">'+row.name+'</div>';  
                cc.panel({
                    width:200,
                    content:content
                });
            },  
            position:'right'
        });  
    });  
} 