<@inputTable>	
	 <#-- <tr>
		<@ww.textfield label="'${action.getText('purchaseBill.billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('purchaseBill.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" />	
	</tr>
	
	<tr>
	  <@ww.select label="'${action.getText('department')}'" 
	                required="false" 
	                name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" 
		    		listKey="id" 
		    		listValue="name"
		            list="departments" 
		            emptyOption="false" 
		            disabled="false">
		</@ww.select> 
		<@ww.textfield label="'${action.getText('buying.Person')}'" name="'buyer.name'" value="'${req.getParameter('buyer.name')?if_exists}'" cssClass="'underline'" />
	</tr>-->
	<tr>
	<#--
	
	<@pp.datePicker label="'${action.getText('purchaseBill.purchaseDate')}'" 
	                name="'purchaseDate'"
					value="'${ req.getParameter('purchaseDate')?if_exists}'" 
					cssClass="'underline'" 
					dateFormat="'%Y-%m'"
					size="15"/>
					-->
	   <@ww.textfield label="'${action.getText('supplier.name')}'" name="'supplier.name'" value="'${req.getParameter('supplier.name')?if_exists}'" cssClass="'underline'" />
	  <@pp.dateRanger label="'${action.getText('purchaseDate')}'" 
	                  name="'purchaseDate'" 
		              value="'${req.getParameter('purchaseDate_start')?if_exists}|${req.getParameter('purchaseDate_end')?if_exists}'"
		              cssClass="'underline'" 
		              dateFormat="date"/> 				         
	
	</tr>
	<#--<tr>
	  <@ww.select label="'${action.getText('purchase.typeStatus')}'" 
           required="false" name="'typeStatus'" 
           value="'${typeStatus?if_exists}'"
           listKey="value" listValue="label"
           list="purTypeStatus" 
           emptyOption="false" 
           disabled="false">
      </@ww.select>	
      <@ww.select label="'${action.getText('purchase.state')}'" 
           required="false" name="'status'" 
           value="'${status?if_exists}'"
           listKey="value" listValue="label"
           list="purchaseStatus" 
           emptyOption="false" 
           disabled="false">
          </@ww.select>	
	</tr><tr>
	<@eam2008_onlySearchInvalid_checkBox/>
	</tr>-->
</@inputTable>	
<script language="javascript" type="text/JavaScript">
<#--
	var selector = document.all("department.id");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
   <#if first>
    <#if loginUser.department?exists>
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
  </#if> 
  } 
   var selector = document.all("typeStatus");
   groups = selector.options.length;
   for (i=0; i<groups; i++) {
    <#if req.getParameter('typeStatus')?exists>
    if (selector.options[i].value == "${req.getParameter('typeStatus')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
   var selector = document.all("status");
  groups = selector.options.length;

  for (i=0; i<groups; i++) {
    <#if req.getParameter('status')?exists>
    if (selector.options[i].value == "${req.getParameter('status')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }-->
  
   <#--自定义一个trim函数--> 
     String.prototype.Trim = function() {
         return this.replace(/(^\s*)|(\s*$)/g, "");
     }
     
   function checkInvalidParms(){
	   var date_start = $("purchaseDate_start").value;
	   var date_end= $("purchaseDate_end").value;
	   date_start = date_start.Trim();
	   date_end = date_end.Trim();
	   
	   if(date_start=="" || !validateTime(date_start)){
              alert("请输入正确的时间格式，正确格式2011-01-01");
              $("purchaseDate_start").focus();
              return false;
       }
     
	  if(date_end=="" || !validateTime(date_end)){
              alert("请输入正确的时间格式，正确格式2011-01-01");
              $("purchaseDate_end").focus();
              return false;
       }
       
       if(!isDateLessThenOtherDate(date_start,date_end)){
          alert("日期范围不正确");
           $("purchaseDate_end").focus();
          return false;
       } 
	 
      
      return true; 
	}    
 </script>