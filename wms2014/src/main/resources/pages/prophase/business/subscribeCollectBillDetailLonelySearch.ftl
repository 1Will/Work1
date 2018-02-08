<@inputTable>
    <tr>
       <@ww.textfield label="'名称'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
       <@ww.textfield label="'型号'" name="'modelSpace'" value="'${req.getParameter('modelSpace')?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>
       <@ww.select label="'申购部门'" 
                   required="false" 
                   name="'dept.id'" 
		    	   value="'${req.getParameter('dept.id')?if_exists}'" 
		    	   listKey="id" 
		    	   listValue="name"
		           list="departments" 
		           emptyOption="false" 
		           disabled="false">
		</@ww.select> 
        <@pp.dateRanger label="'申购日期'" 
                     name="'subDate'" 
		             value="'${req.getParameter('subDate_start')?if_exists}|${req.getParameter('subDate_end')?if_exists}'"
		             cssClass="'underline'" 
		             dateFormat="date"/>   
       
	</tr> 
    <tr>
       <@ww.textfield label="'采购数量'" name="'purNum'" value="'${req.getParameter('purNum')?if_exists}'" cssClass="'underline'"/>
       
       <@pp.dateRanger label="'采购日期'" 
                     name="'purDate'" 
		             value="'${req.getParameter('purDate_start')?if_exists}|${req.getParameter('purDate_end')?if_exists}'"
		             cssClass="'underline'" 
		             dateFormat="date"/>   
  
    </tr>
       
    <tr>
       <@ww.select label="'${action.getText('状态')}'" 
                  required="false" 
                  name="'billStatus'" 
		    	  value="'${billStatus?if_exists}'" 
		    	  listKey="value" 
		    	  listValue="label"
		          list="status" 
		          emptyOption="false" 
		          disabled="false">
	 </@ww.select>
    </tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
	var selector = document.all("dept.id");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
      <#if req.getParameter('dept.id')?exists>
        if (selector.options[i].value == "${req.getParameter('dept.id')?if_exists}") {
             selector.options[i].selected="true";
       }
     </#if>
    
	}
    
  	var statusSelector = document.all("billStatus");
  	statusGroups = statusSelector.options.length;
  	for (i=0; i<statusGroups; i++) {
    <#if req.getParameter('billStatus')?exists>
    	if (statusSelector.options[i].value == "${req.getParameter('billStatus')?if_exists}") {
         	statusSelector.options[i].selected="true";
    	}
    </#if>
    
    
    }
    
    function checkInvalidParms(){
        <#--自定义一个trim函数--> 
        String.prototype.Trim = function() {
	         return this.replace(/(^\s*)|(\s*$)/g, "");
	    }
	         
       if (getObjByName("dept.id").value == -1) {
  	        getObjByName("dept.id").value = '';
       } 
       if (getObjByName("billStatus").value == -1) {
	  	    getObjByName("billStatus").value = '';
	   }
	  
	  
	   if(getObjByName("subDate_start").value!=""){
          if(!validateTime(getObjByName("subDate_start").value.Trim())){
               alert("${action.getText('subscribe.start_EndTimeMistake')}");
               return false;
          }
       }
       if(getObjByName("subDate_end").value!=""){
         if(!validateTime(getObjByName("subDate_end").value.Trim())){
               alert("${action.getText('subscribe.start_EndTimeMistake')}");
               return false;
          }
       }
      
       if(getObjByName("purDate_start").value!=""){
          if(!validateTime(getObjByName("purDate_start").value.Trim())){
               alert("采购日期格式错误，正确格式yyyy-MM-dd");
               return false;
          }
       }
       if(getObjByName("purDate_end").value!=""){
         if(!validateTime(getObjByName("purDate_end").value.Trim())){
               alert("采购日期格式错误，正确格式yyyy-MM-dd");
               return false;
          }
       }
      
   
      return true;
    }
</script>