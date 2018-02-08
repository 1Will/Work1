
<script language="javascript">
    /*
     * 定义全局变量,用来存放部门和生产线
    */
	//var productionLineNumberForString = document.forms["device"].elements["productionLine.id"].length;
	var productionLineNumberForString = #{productionLines.size()};
	var productionLineNumberForInteger = new Number(productionLineNumberForString);    
	var lineForSameDept = new Array(productionLineNumberForInteger.valueOf());    //用来放相同部门生产线值的二维数组
	var lineForSortDept = new Array();      //lineForSortDept变量存放lineForSameDept数组的元素
	for(var i=0; i<lineForSameDept.length; i++) {
	  lineForSameDept[i] = new Array();   
	}
	/*
	 * 把同一个部门的生产线放在一个数组里
	*/
	function toSortProductionLineByDepartment() {     
	  var arrayCount = 0;      //对lineForSameDept数组计数
	  <#if (allDepartments.size()>0)>
	  <#list allDepartments as dept>
	    var count = 0;                
	  	var flag = 0;       //标识作用，当flag=1时，把lineForSameDept二维数组存入lineForSortDept中
	  	<#if (productionLines.size()>0)>
	  	<#list productionLines as line>
	  	  <#if line.department?exists>
	  	    if ('#{dept.id}' == '#{line.department.id}' && arrayCount<lineForSameDept.length) {
	  		  if (count == 0) {	  								
	  		    lineForSameDept[arrayCount].push(['#{dept.id}', '']);
	  			lineForSameDept[arrayCount].push(['#{line.id}', '${line.name}']);
	  			count++;
	  			flag = 1;
	  		  } else {
	  			  lineForSameDept[arrayCount].push(['#{line.id}', '${line.name}']);
	  			  flag = 1;
	  		  }
	  		} 
	  	  </#if>
	  	</#list>
	  	</#if>
	  	if (flag == 1) {
	  	  lineForSortDept.push(lineForSameDept);
	  	  arrayCount++;
	  	}
	 </#list>
	 </#if>
	}
	/*
	 * 处理部门选择框的事件
	*/
	function departmentValueChange(departmentId, productionLineId) {      
	  var selectedDepartmentId = getObjByNameRe(departmentId).value;
	  for (var i = 0; i < lineForSortDept.length; i++) {
	    var dept = lineForSortDept[i];          
	    deleteAllLines(departmentId, productionLineId);          //删除生产线下拉框原有的值
	    for (var j = 0; j < dept.length; j ++) {
	  	  var line = dept[j];
	  	  for (var k=0; k<line.length; k++) {
		    if (selectedDepartmentId == line[0][0] ) {          //当选择的部门与生产线所属部门值相同时，创建该部门的生产线下拉框
		  	  createLineSelect(line, productionLineId);        //创建生产线下拉框
		  	  return ;
		  	} 
	  	  }
	  	}
	  }
	}
	/*
	 * 给生产线的选择框赋值
	*/
	function createLineSelect(ary, productionLineId) {     
	  var obj = getObjByNameRe(productionLineId);
	  for (var j=1; j < ary.length; j ++) {
	    var opt = new Option(ary[j][1], ary[j][0]);
		eval("obj.options[obj.options.length]=opt");
	  }
	}
	/*
	 * 删除生产线的选择框的值
	*/  	
	function deleteAllLines(departmentId, productionLineId) {     
      getObjByNameRe(productionLineId).value='';
	  var obj = getObjByNameRe(productionLineId);
	  for (var i=obj.options.length; i>0; i--) {
	    obj.options[i] = null;
	  }
	}
</script>