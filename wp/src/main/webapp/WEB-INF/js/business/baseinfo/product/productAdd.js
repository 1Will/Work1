$(function() {
	$("#productSourceId").combobox({
		//data:[{'id':"CPLY_ZZYF",'text':"自主研发"},{'id':"CPLY_DL",'text':"代理"},{'id':"CPLY_FW",'text':"服务"},{'id':"CPLY_WB",'text':"外包"},],
		method : 'get',
		url : contextPath + '/baseinfo/product/cplyCombotree',
		//valueField : 'id',
		//textField : 'text'
		valueField : 'id',
		textField : 'text'
		});
	
	$("#ptId").combobox({
		//data:[{'id':"CPLY_ZZYF",'text':"自主研发"},{'id':"CPLY_DL",'text':"代理"},{'id':"CPLY_FW",'text':"服务"},{'id':"CPLY_WB",'text':"外包"},],
		method : 'get',
		url : contextPath + '/baseinfo/product/cplxCombotree',
		//valueField : 'id',
		//textField : 'text'
		valueField : 'id',
		textField : 'text'
		});
	

});

$('input[name="isnomain"]').each(function(){
	var isnomian = $("#isnomianvalue").val();
	if(isnomian == this.value ){
		 $("#"+this.id).attr("checked","checked");
	}
   
});


function saveOrUpdate(){
	var data = $("#pro_form :validInput").serialize(); 
	$.cajax({ 
		url:contextPath + '/baseinfo/product/saveOrUpdate', 
		type : 'POST',
		data: data,
		success: function(result){
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
	}});
	$('#dlg').dialog('close');
}


