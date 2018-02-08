 
<@inputTable>	
<#--
	<tr>
		<@ww.textfield label="'${action.getText('subscribe.billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('subscribe.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" />	
	</tr>
	<tr>
	    
		<@ww.textfield label="'${action.getText('buying.Person')}'" name="'buyingPerson.name'" value="'${req.getParameter('buyingPerson.name')?if_exists}'" cssClass="'underline'" />
	</tr>-->
	<tr>
	    <@ww.select label="'${action.getText('subscribes.department')}'" 
	                required="false" 
	                name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" 
		    		listKey="id" 
		    		listValue="name"
		            list="departments" 
		            emptyOption="false" 
		            disabled="false">
		</@ww.select> 
		
	 <#--   <@pp.datePicker label="'${action.getText('年月')}'" 
	                name="'subscribeDate'"
					value="'${req.getParameter('subscribeDate')?if_exists}'" 
					cssClass="'underline'" 
					dateFormat="'%Y-%m'"
					size="15"/>	-->
					 
	   <@pp.dateRanger label="'${action.getText('subscribeDate')}'" 
	            name="'subscribeDate'" 
		       value="'${req.getParameter('subscribeDate_start')?if_exists}|${req.getParameter('subscribeDate_end')?if_exists}'"
		       cssClass="'underline'" 
		       dateFormat="date"/>    
		
	</tr>
	<#--<tr>
 
		<@ww.select label="'${action.getText('单据类型')}'" 
	                   name="'detailKind.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allDetailKind" 
                       emptyOption="true" 
                       disabled="false"
                       value="'${req.getParameter('detailKind.id')?if_exists}'" />
                       
        <@ww.select label="'${action.getText('subscribes.status')}'" 
             required="false" 
             name="'status'" 
    		 value="'${status?if_exists}'" 
    		 listKey="value" 
    		 listValue="label"
             list="subStatus"
            emptyOption="false" 
            disabled="false">
            
		</@ww.select>
                       
                  
	 </tr>
	 <tr>

		<@eam2008_onlySearchInvalid_checkBox/>
	</tr>-->
</@inputTable>	
<script language="javascript" type="text/JavaScript">
  
	var selector = document.all("department.id");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
	    <#if req.getParameter('department.id')?exists>
		    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
		         selector.options[i].selected="true";
		    }
	    </#if>
    }
    <#--
    <#if first>
    <#if loginUser.department?exists>
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
  	</#if>
	}
	
  	var typeStatusSelector = document.all("typeStatus");
  	typeStatusGroups = typeStatusSelector.options.length;
  	for (i=0; i<typeStatusGroups; i++) {
    <#if req.getParameter('typeStatus')?exists>
    	if (typeStatusSelector.options[i].value == "${req.getParameter('typeStatus')?if_exists}") {
      	typeStatusSelector.options[i].selected="true";
    	}
    </#if>
    }
    
  	var statusSelector = document.all("status");
  	statusGroups = statusSelector.options.length;
  	for (i=0; i<statusGroups; i++) {
    <#if req.getParameter('status')?exists>
    	if (statusSelector.options[i].value == "${req.getParameter('status')?if_exists}") {
      	statusSelector.options[i].selected="true";
      	
    	}
    </#if>
    }
    
    
    var detailKindSelector = document.all("detailKind.id");
  	var length = detailKindSelector.options.length;
  	detailKindSelector.options[0].value=-1;
  	detailKindSelector.options[0].text="所有";
  	for (i=0; i<length; i++) {
    <#if req.getParameter('detailKind.id')?exists>
    	if (detailKindSelector.options[i].value == "${req.getParameter('detailKind.id')?if_exists}") {
      	detailKindSelector.options[i].selected="true";
      	
    	}
    </#if>
    }
    -->

    
  function checkInvalidParms(){
  
  
      var date_start = $("subscribeDate_start").value;
      var date_end = $("subscribeDate_end").value;
      date_start = date_start.Trim();
      date_end = date_end.Trim();
      
	  if(""==date_start || !validateTime(date_start)){
              alert("请输入正确的时间格式，正确格式2011-01-01");
              $("subscribeDate_start").focus();
              return false;
       }
     
	  if(""==date_end || !validateTime(date_end)){
              alert("请输入正确的时间格式，正确格式2011-01-01");
              $("subscribeDate_end").focus();
              return false;
       }
       if(""!=date_start && ""!=date_end){
          if(!isDateLessThenOtherDate(date_start,date_end)){
          alert("日期范围不正确");
          $("subscribeDate_end").focus();
          return false;
       }
       
       }
       
       
       if(-1==$("department.id").value){
          $("department.id").value="";
       }
      
     
      return true;  
  }    
 </script>