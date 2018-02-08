<@inputTable>
	<@ww.hidden name="'invalid'" value="false"/>
	<@ww.hidden name="'includeValid'" value="true"/>
    <tr>
        <@ww.textfield label="'${action.getText('toolingNo')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('toolingName')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
    </tr>
	<tr>
       <@ww.textfield label="'${action.getText('toolingDrawNo')}'" name="'graphNo'" value="'${req.getParameter('graphNo')?if_exists}'" cssClass="'underline'"/>
       <@ww.select label="'${action.getText('productName')}'" required="false" name="'product.id'" 
		    	  value="'${req.getParameter('product.id')?if_exists}'" listKey="id" listValue="name"
		          list="products" emptyOption="false" disabled="false">
	   </@ww.select>
	</tr>
    <tr>
    	<@ww.select label="'${action.getText('toolingCategory')}'" required="false" name="'toolingType.id'" 
    			value="'${req.getParameter('toolingType.id')?if_exists}'" listKey="id" listValue="value"
                list="toolingTypes" emptyOption="false" disabled="false"  onchange="'toolTypeValueChange()'">
        </@ww.select>
        <@ww.select label="'${action.getText('category')}'" required="false" name="'toolingTypeDetail.id'" 
    			value="'${req.getParameter('toolingTypeDetail.id')?if_exists}'" listKey="id" listValue="name"
                list="toolingTypeDetails" emptyOption="false" disabled="false">
        </@ww.select>
    </tr>
    <tr>
     <@ww.textfield label="'${action.getText('managerPep')}'" name="'manager'" value="'${req.getParameter('manager')?if_exists}'" cssClass="'underline'"/>
      <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    			  value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
                  list="departments" emptyOption="false" disabled="false">
      </@ww.select>
      </tr>
    <tr>
      <@ww.select label="'${action.getText('toolingState')}'" required="false" name="'toolingStatus.code'" 
    			  value="'${req.getParameter('toolingStatus.id')?if_exists}'" listKey="code" listValue="value"
                  list="toolingStatus" emptyOption="false" disabled="false">
      </@ww.select> 
      <#if req.getParameter('hiddenCheckBox')?has_content>
      <#else>
      	 <@eam2008_onlySearchInvalid_checkBox/>
      </#if>
     </tr>
	</@inputTable>
	<script language="JavaScript" type="text/JavaScript"> 
	          
      var detailTypeNumberForString = getObjByNameRe("toolingTypeDetail.id").length;
	  var detailTypeNumberForInteger = new Number(detailTypeNumberForString);    
	  var detailTypeForSameToolType =new Array(detailTypeNumberForInteger.valueOf());    //用来放生产线值的数组
	  var detailTypeForSortToolType = new Array();      //lineForSortDept变量放lineForSameDept数组的元素
	  for(var i=0; i<detailTypeForSameToolType.length; i++) {
	    detailTypeForSameToolType[i] = new Array();   
	  }
	  
	  var selector = document.all("toolingType.id");
      var groups=selector.options.length;  
      for (i=0; i<groups; i++){
        <#if req.getParameter('toolingType.id')?exists>
        if (selector.options[i].value=="${req.getParameter('toolingType.id')?if_exists}"){
          selector.options[i].selected="true";
        }
        </#if>
      }  
      selector = document.all("toolingTypeDetail.id");
      groups = selector.options.length;
      for (i=0; i<groups; i++) {
        <#if req.getParameter('toolingTypeDetail.id')?exists>
          if (selector.options[i].value == "${req.getParameter('toolingTypeDetail.id')?if_exists}") {
            selector.options[i].selected="true";
          }
        </#if>
      }  
      selector = document.all("department.id");
      groups = selector.options.length;
      for (i=0; i<groups; i++) {
        <#if req.getParameter('department.id')?exists>
          if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
            selector.options[i].selected="true";
          }
        </#if>
      }
      selector = document.all("product.id");
      groups = selector.options.length;
      for (i=0; i<groups; i++) {
        <#if req.getParameter('product.id')?exists>
          if (selector.options[i].value == "${req.getParameter('product.id')?if_exists}") {
            selector.options[i].selected="true";
          }
        </#if>
      } 
      
      selector = document.all("toolingStatus.code");
      groups = selector.options.length;
      for (i=0; i<groups; i++) {
        <#if req.getParameter('toolingStatus.code')?exists>
          if (selector.options[i].value == "${req.getParameter('toolingStatus.code')?if_exists}") {
            selector.options[i].selected="true";
          }
        </#if>
      }
      <#--
      if (-1 != document.forms[0].elements["toolingType.id"].value) {
      		toSortDetailTypeByToolType();
		 	toolTypeValueChange();
	  }
	  -->
	  //下面的方法执行了两次，有点累赘，需要修改
	  selector = document.all("toolingTypeDetail.id");
      groups = selector.options.length;
      for (i=0; i<groups; i++) {
        <#if req.getParameter('toolingTypeDetail.id')?exists>
          if (selector.options[i].value == "${req.getParameter('toolingTypeDetail.id')?if_exists}") {
            selector.options[i].selected="true";
          }
        </#if>
      }
	  function checkInvalidParms() {
			if (getObjByNameRe("toolingType.id").value == -1) {
				getObjByNameRe("toolingType.id").value = '';
			}
			if (getObjByNameRe("toolingTypeDetail.id").value == -1) {
				getObjByNameRe("toolingTypeDetail.id").value = '';
			}
			if (getObjByNameRe("department.id").value == -1) {
				getObjByNameRe("department.id").value = '';
			}
			if (getObjByNameRe("product.id").value == -1) {
				getObjByNameRe("product.id").value = '';
			}
		    if (getObjByNameRe("toolingStatus.code").value == -1) {
				getObjByNameRe("toolingStatus.code").value = '';
			}
			//<#if req.getParameter('hiddenCheckBox')?has_content>
			//<#else>
			//  if (getObjByNameRe("includeDisabled").checked) {
			//    getObjByNameRe("includeValid").value="";
			//    getObjByNameRe("includeDisabled").value='true';
			//  }
			//</#if>
			return true;
		}
		
	  function toSortDetailTypeByToolType() {      //把同一个部门的生产线放在一个数组里
	    var arrayCount = 0;      //对lineForSameDept数组计数
	    <#if (toolingTypes.size()>0)>
	    <#list toolingTypes as toolType>
	      var count = 0;          
	  	  var flag = 0;
	  	<#if (toolingTypeDetails.size()>0)>
	  	<#list toolingTypeDetails as detailType>
	  	  <#if detailType.toolingType?exists>
	  	    if ('#{toolType.id}' == '#{detailType.toolingType.id}' && arrayCount<detailTypeForSameToolType.length) {
	  		  if (count == 0) {	  								
	  		    detailTypeForSameToolType[arrayCount].push(['#{toolType.id}', '']);
	  			detailTypeForSameToolType[arrayCount].push(['#{detailType.id}', '${detailType.name}']);
	  			count++;
	  			flag = 1;
	  		  } else {
	  			  detailTypeForSameToolType[arrayCount].push(['#{detailType.id}', '${detailType.name}']);
	  			  flag = 1;
	  			}
	  		} 
	  	  </#if>
	  	</#list>
	  	</#if>
	  	if (flag == 1) {
	  	  detailTypeForSortToolType.push(detailTypeForSameToolType);
	  	  arrayCount++;
	  	}
	 </#list>
	 </#if>
	}
	
	function toolTypeValueChange() {      //处理部门选择框的事件
	  var selectedToolTypeId = getObjByNameRe("toolingType.id").value;
	  if (selectedToolTypeId != -1) {
		  for (var i = 0; i < detailTypeForSortToolType.length; i++) {
		    var toolType = detailTypeForSortToolType[i];
		    deleteAllDetailTypes();
		    for (var j = 0; j < toolType.length; j ++) {
		  	  var detailType = toolType[j];
			  	  for (var k=0; k<toolType.length; k++) {
					    if (selectedToolTypeId == detailType[0][0] ) {
					  	  createDetailTypeSelect(detailType);
					  	  return ;
					  	} 
			  	  }
		  	}
		  }
		 }else {
	       getObjByNameRe("toolingTypeDetail.id").value = -1;
		   getObjByNameRe("toolingTypeDetail.id").length=1;
		 }
	}
	
	function createDetailTypeSelect(ary) {     //给生产线的选择框赋值
	  var obj = getObjByNameRe("toolingTypeDetail.id");
	  for (var j=1; j < ary.length; j ++) {
	    var opt = new Option(ary[j][1], ary[j][0]);
		eval("obj.options[obj.options.length]=opt");
	  }
	}
	
   function deleteAllDetailTypes() {     //删除生产线的选择框的值
      getObjByNameRe("toolingTypeDetail.id").value='';
	  var obj = getObjByNameRe("toolingTypeDetail.id");
	  for (var i=obj.options.length; i>0; i--) {
	    obj.options[i] = null;
	  }
	}
	</script>