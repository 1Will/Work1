$(function() {
	var  cplx ;
	var cply;
	$.cajax({ 
		url : contextPath + '/baseinfo/product/cplxCombotree',
		type : 'get',
		data: null,
		async : false,
		success: function(result){
			cplx = result;
	}});
	$.cajax({ 
		url : contextPath + '/baseinfo/product/cplyCombotree',
		type : 'get',
		data: null,
		async : false,
		success: function(result){
			cply = result;
	}});
	$("#product_datagrid").datagrid({
		height : '100%',
		method : 'post',
		url : contextPath + '/baseinfo/product/productListDG',
		pagination : true,// 分页控件
		rownumbers : true,// 行号
		columns : [ [ {
			field : 'id',
			checkbox : true,
			width : '5%'
		}, {
			field : 'code',
			title : '产品编码',
			width : '20%'
		}, {
			field : 'name',
			title : '产品名称',
			width : '20%'
		}, {
			field : 'model',
			title : '型号',
			width : '15%'
		}, {
			field : 'standard',
			title : '规格',
			width : '20%'
		}, {
			field : 'etcprice',
			title : '成本价',
			width : '20%'
		}, {
			field : 'saleprice',
			title : '销售价',
			width : '20%'
		}, {
			field : 'salelimit',
			title : '销售制度',
			width : '20%'
		}, {
			field : 'ptId',
			title : '产品类型',
			width : '20%',
			formatter: function(value,row,index){
				return getText(cplx,value);
            }
		}, {
			field : 'productSourceId',
			title : '产品来源',
			width : '20%',
			formatter: function(value,row,index){
				return getText(cply,value);
            }
		}, {
			field : 'supplierId',
			title : '供应商',
			width : '20%'
		}, {
			field : 'isnomain',
			title : '主营',
			width : '20%',
			formatter: function(value,row,index){
    				if (row.isnomain=="1"){
    					return "是";
    				} else {
    					return "否";
    				}
                }
		} ] ],
	      onCheck : function(rowIndex,rowData){
          	var data = $('#product_datagrid').datagrid('getChecked');
          	if(data.length==1){
          		$("#btn_editProduct").linkbutton('enable');
          		$("#btn_destroyProduct").linkbutton('enable');
          	} else if(data.length > 1){
          		$("#btn_editProduct").linkbutton('disable');
          		$("#btn_destroyProduct").linkbutton('enable');
          	} else {
          		$("#btn_editProduct").linkbutton('disable');
          		$("#btn_destroyProduct").linkbutton('disable');
          	}
          },
          onUncheck : function(rowIndex,rowData){
          	var data = $('#product_datagrid').datagrid('getChecked');
          	if(data.length==1){
          		$("#btn_editProduct").linkbutton('enable');
          		$("#btn_destroyProduct").linkbutton('enable');
          	} else if(data.length > 1){
          		$("#btn_editProduct").linkbutton('disable');
          		$("#btn_destroyProduct").linkbutton('enable');
          	} else {
          		$("#btn_editProduct").linkbutton('disable');
          		$("#btn_destroyProduct").linkbutton('disable');
          	}
          },
		
		
		toolbar : '#toolbar'
	});
	// 设置分页控件
	var p = $('#product_datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});

});

//查询产品信息
function searchProduct() {
	var param = $("#attr_form input[value!='']").serialize();
	$('#product_datagrid').datagrid('load',{
		name:$("input[name='name']").val()
	});
}


//创建
function newProduct() {
	$("#dlg").dialog({
		title : '添加产品',
		style : '{padding:0}',
		width : 700,
		height : 500,
		closed : true,
		cache : false,
		href : contextPath + '/baseinfo/product/productAdd',
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
			$("#product_datagrid").datagrid('reload');
		}
	});
	$("#dlg").dialog('open');
}


function editProduct() {
	var row = $('#product_datagrid').datagrid('getSelected');
	if (row) {
		$("#dlg").dialog({
			title : '编辑产品',
			style : '{padding:0}',
			width : 700,
			height : 500,
			closed : true,
			cache : false,
			href: contextPath + '/baseinfo/product/' + row.id + '/update',
			
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
				$("#product_datagrid").datagrid('reload');
			}
		});
		$("#dlg").dialog('open');
	}
}


function destroyProduct() {
	var data = $('#product_datagrid').datagrid('getChecked');
	var str = new Array();
	if (data) {
		$.each(data, function(j, o) {
			str.push(o.id);
		});
	}
	if (str) {
		$.messager.confirm('Confirm', '确认删除该产品?', function(r) {
					if (r) {
						$.post(contextPath + '/baseinfo/product/delete', {
									id : str
								}, function() {
									$('#product_datagrid').datagrid('reload'); // reload the product data
									$.messager.show({ // show error message
										title : 'success',
										msg : '删除成功！'
									});
								});
					}
				});
	}
}

function getText(mjson,value){
	var st = "";
	$.each(mjson, function(j,o) {
		if(o.id == value){
			st= o.text;
		}
	});
	return st;
	
}

