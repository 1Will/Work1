
<script language="javascript">

    /*
     * 定义全局变量,用来存放备件大类和明细类
    */
	var spareDetailTypeNumberForString = #{spareDetailType.size()};
	var spareDetailTypeNumberForInteger = new Number(spareDetailTypeNumberForString);    
	var detailTypeForSameSpareType = new Array(spareDetailTypeNumberForInteger.valueOf());    //用来放相同备件大类的明细类的二维数组
	var detailTypeForSortSpareType = new Array();      //detailTypeForSortSpareType变量存放detailTypeForSameSpareType数组的元素
	for(var i=0; i<detailTypeForSameSpareType.length; i++) {
	  detailTypeForSameSpareType[i] = new Array();   
	}

	/*
	 * 把同一个备件大类的明细类放在一个数组里
	*/
	function toSortDetailTypeBySpareType() {     
	  var arrayCount = 0;      //对detailTypeForSameSpareType数组计数
	  <#if (spareType.size()>0)>
	  <#list spareType as type>
	    var count = 0;                
	  	var flag = 0;       //标识作用，当flag=1时，把detailTypeForSameSpareType二维数组存入detailTypeForSortSpareType中
	  	<#if (spareDetailType.size()>0)>
	  	<#list spareDetailType as detailType>
	  	  <#if detailType.spareType?exists>
	  	    if ('#{type.id}' == '#{detailType.spareType.id}' && arrayCount<detailTypeForSameSpareType.length) {
	  		  if (count == 0) {	  								
	  		    detailTypeForSameSpareType[arrayCount].push(['#{type.id}', '']);
	  			detailTypeForSameSpareType[arrayCount].push(['#{detailType.id}', '${detailType.name}']);
	  			count++;
	  			flag = 1;
	  		  } else {
	  			  detailTypeForSameSpareType[arrayCount].push(['#{detailType.id}', '${detailType.name}']);
	  			  flag = 1;
	  		  }
	  		} 
	  	  </#if>
	  	</#list>
	  	</#if>
	  	if (flag == 1) {
	  	  detailTypeForSortSpareType.push(detailTypeForSameSpareType);
	  	  arrayCount++;
	  	}
	 </#list>
	 </#if>
	}
	/*
	 * 处理备件大类选择框的事件
	*/
	function spareTypeValueChange(spareId, spareDetailTypeId) {
	  var selectedSpareId = document.getElementById(spareId).value;
	  if ('-1' != selectedSpareId) {
	    for (var i = 0; i < detailTypeForSortSpareType.length; i++) {
	      var type = detailTypeForSortSpareType[i];
	      deleteAllDetailType(spareId, spareDetailTypeId);          //删除备件明细类下拉框原有的值
	      for (var j = 0; j < type.length; j ++) {
	  	    var detailType = type[j];
	  	    for (var k=0; k<detailType.length; k++) {
		      if (selectedSpareId == detailType[0][0] ) {          //当选择的备件大类与明细类所属备件大类值相同时，创建该备件大类的备件明细类下拉框
		  	    createDetailTypeSelect(detailType, spareDetailTypeId);        //创建备件明细类下拉框
		  	    return ;
		  	  } 
	  	    }
	  	  }
	   }
	  } else {
	    document.getElementById(spareDetailTypeId).value = -1;
	    document.getElementById(spareDetailTypeId).length=1;
	  }
	}
	/*
	 * 给备件明细类的选择框赋值
	*/
	function createDetailTypeSelect(ary, spareDetailTypeId) {     
	  var obj = document.getElementById(spareDetailTypeId);
	  for (var j=1; j < ary.length; j ++) {
	    var opt = new Option(ary[j][1], ary[j][0]);
		eval("obj.options[obj.options.length]=opt");
	  }
	}
	/*
	 * 删除备件明细类的选择框的值
	*/  	
	function deleteAllDetailType(spareId, spareDetailTypeId) {     
      document.getElementById(spareDetailTypeId).value='';
	  var obj = document.getElementById(spareDetailTypeId);
	  for (var i=obj.options.length; i>0; i--) {
	    obj.options[i] = null;
	  }
	}
</script>