<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试ajax</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div>
		<label>省份</label> <select id="province" onchange="getCity(this.options[this.options.selectedIndex].value)"></select> 
		<label>城市</label> <select id="city" onchange="getArea(this.options[this.options.selectedIndex].value)"></select> 
		<label>县区</label> <select id="area"></select>

	</div>
</body>
<script type="text/javascript">
	window.onload = function(){
		$.ajax({
			url : 'LoadPlace?type=province',
			type : 'GET',
			dataType : 'json',
			timeout : 1000,
			cache : false,
			error : erryFunction, //错误执行方法    
			success : succFunction
		//成功执行方法    
		})
	}
	function erryFunction() {
		alert("有错误！");
	}
	function succFunction(data) {
		var json = eval(data);
		$('#province').append("<option value='0'></option>");
		for(var i =0;i<json.length;i++){
			$('#province').append("<option value='"+json[i].provinceid+"'>"+json[i].province+"</option>");
		}
	}

	function getCity(pid){
			$.ajax({
				url : 'LoadPlace?type=city&provinceid='+pid,
				type : 'GET',
				dataType : 'json',
				timeout : 1000,
				cache : false,
				error : erryFunction, //错误执行方法    
				success : setCity
			//成功执行方法    
			})
	}

	function setCity(data){
		$('#city').empty();
		$('#area').empty();
		var json = eval(data);
		$('#city').append("<option value='0'></option>");
		for(var i =0;i<json.length;i++){
			$('#city').append("<option value='"+json[i].cityid+"'>"+json[i].city+"</option>");
		}
	}
	function getArea(cid){
			$.ajax({
				url : 'LoadPlace?type=area&cityid='+cid,
				type : 'GET',
				dataType : 'json',
				timeout : 100000,
				cache : false,
				error : erryFunction, //错误执行方法    
				success : setArea
			//成功执行方法    
			})
	}

	function setArea(data){
		$('#area').empty();
		var json = eval(data);
		$('#area').append("<option value='0'></option>");
		for(var i =0;i<json.length;i++){
			$('#area').append("<option value='"+json[i].areaid+"'>"+json[i].area+"</option>");
		}
	}
</script>
</html>