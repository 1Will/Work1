 
<@inputTable>	
   	<tr>
	 <@ww.textfield label="'${action.getText('graphNo')}'" name="'graphNo'" value="'${req.getParameter('graphNo')?if_exists}'" cssClass="'underline'" />	 
	<@ww.textfield label="'${action.getText('name')}'" name="'dtlName'" value="'${req.getParameter('dtlName')?if_exists}'" cssClass="'underline'" />	 
     	<@ww.select label="'单据类型'" 
	                   name="'detailKind.id'" 
	                   value="'${req.getParameter('detailKind.id')?if_exists}'"
    			       listKey="id" 
    			       listValue="value"
                       list="allDetailKind" 
                       emptyOption="true" 
                       disabled="false" >
                      
    </@ww.select>    
	</tr>
   <tr>
   
   	<@ww.textfield label="'${action.getText('型号11')}'" name="'model'" value="'${req.getParameter('model')?if_exists}'" cssClass="'underline'" />
   	 <@ww.textfield label="'${action.getText('生产厂家')}'" name="'factory'" value="'${req.getParameter('factory')?if_exists}'" cssClass="'underline'" />	
     	
     
	    
	 <@ww.select label="'状态'" 
         required="false" 
         name="'status'" 
         value="'${status?if_exists}'"
         listKey="value" 
         listValue="label"
         list="customerTypes" 
         emptyOption="false" 
         disabled="false">
     </@ww.select>
         
   </tr>

  <tr>
  <#--
    <@ww.select label="'种类'" 
	                 required="false" 
	                 name="'spareType.id'" 
		    		 value="'${req.getParameter('spareType.id')?if_exists}'" 
		    		 listKey="id"
		    		 listValue="name"
		             list="spareType" 
		             emptyOption="false" 
		             onchange="'spareTypeCascadeDWR(\"spareType.id\",\"spareDetailType.id\",\"${action.getText('所有')}\")'"
		             disabled="false">
	</@ww.select> 
	<@ww.select label="'明细种类'" 
	                 required="false" 
	                 name="'spareDetailType.id'" 
		    		 value="'${req.getParameter('spareDetailType.id')?if_exists}'" 
		    		 listKey="id"
		    		 listValue="name"
		             list="spareDetailType" 
		             emptyOption="false" 
		             disabled="false">
	</@ww.select> 
	-->
	 <@ww.select label="'种类'" 
	                 required="false" 
	                 name="'spareType.id'" 
		    		 value="'${req.getParameter('spareType.id')?if_exists}'" 
		    		 listKey="id"
		    		 listValue="name"
		             list="spareType" 
		             emptyOption="false" 
		             disabled="false">
	 </@ww.select> 
	 <@ww.textfield label="'${action.getText('subscribe.collectNo')}'" name="'collectCode'" value="'${req.getParameter('collectCode')?if_exists}'" cssClass="'underline'" />	 
     <@ww.textfield label="'${action.getText('subscribe.collectName')}'" name="'collectName'" value="'${req.getParameter('collectName')?if_exists}'" cssClass="'underline'" />	
  </tr>
	<#-- 申购汇总单-->
	<tr>
		 <@pp.dateRanger 
	           label="'${action.getText('subscribe.collectDate')}'" 
	           name="'collectDate'" 
		       value="'${req.getParameter('collectDate_start')?if_exists}|${req.getParameter('collectDate_end')?if_exists}'"
		       cssClass="'underline'" 
		       dateFormat="date"/> 
		<@ww.select label="'${action.getText('subscriber.department')}'" 
	                 required="false" 
	                 name="'department.id'" 
		    		 value="'${req.getParameter('department.id')?if_exists}'" 
		    		 listKey="id"
		    		 listValue="name"
		             list="departments" 
		             emptyOption="false" 
		             disabled="false">
		</@ww.select>  
	    <@pp.dateRanger 
	           label="'${action.getText('subscribeDate')}'" 
	           name="'subscribeDate'" 
		       value="'${req.getParameter('subscribeDate_start')?if_exists}|${req.getParameter('subscribeDate_end')?if_exists}'"
		       cssClass="'underline'" 
		       dateFormat="date"/> 
	   
	</tr>
</@inputTable>	
<script language="javascript" type="text/JavaScript">

    <#--
     <#if req.getParameter('spareType.id')?exists>
        $('spareType.id').value = "${req.getParameter('spareType.id')?if_exists}";
		    DWREngine.setAsync(false); 
			//回调种类的值后触发DWR 级联明细种类  
			spareTypeCascadeDWR("spareType.id","spareDetailType.id","${action.getText('所有')}")
			//重新设置为异步方式
			DWREngine.setAsync(true);
	  </#if>	
	
	    var selector = document.all("spareDetailType.id");
	    var groups = selector.options.length;
	    for (i=0; i<groups; i++) {
	    <#if req.getParameter('spareDetailType.id')?exists>
	     if (selector.options[i].value == "${req.getParameter('spareDetailType.id')?if_exists}") {
	      selector.options[i].selected="true";
	     }
	    </#if>
	   } 
	 -->		
         
  		var spareTypeSelector = document.all("spareType.id");
	    var groups = spareTypeSelector.options.length;
	    for (i=0; i<groups; i++) {
	    <#if req.getParameter('spareType.id')?exists>
	     if (spareTypeSelector.options[i].value == "${req.getParameter('spareType.id')?if_exists}") {
	      spareTypeSelector.options[i].selected="true";
	     }
	    </#if>
        }
		var selector = document.all("department.id");
	    var groups = selector.options.length;
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
 
  <#--
	  var selector = document.all("collDepartment.id");
	  groups = selector.options.length;
	
	  for (i=0; i<groups; i++) {
	    <#if req.getParameter('collDepartment.id')?exists>
	    if (selector.options[i].value == "${req.getParameter('collDepartment.id')?if_exists}") {
	      selector.options[i].selected="true";
	    }
	    </#if>
	  }-->
  
      var selector = document.all("detailKind.id");
	  groups = selector.options.length;
	
	  for (i=0; i<groups; i++) {
	    <#if req.getParameter('detailKind.id')?exists>
	    if (selector.options[i].value == "${req.getParameter('detailKind.id')?if_exists}") {
	      selector.options[i].selected="true";
	    }
	    <#else>
	       selector.options[0].value=-1;
	       selector.options[0].text="所有";
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
	  }
  
  
  
  
function checkInvalidParms(){
  
   
	  if (document.getElementById("department.id").value == -1) {
	     document.getElementById("department.id").value = '';
	  }
	  <#--
	  if ($('collDepartment.id').value == -1) {
	     $('collDepartment.id').value = '';
	  }-->
	  if ($('spareType.id').value == -1) {
	     $('spareType.id').value = '';
	  }   
	 // if ($('spareDetailType.id').value == -1) {
	    // $('spareDetailType.id').value = '';
	 // }
	  if ($('detailKind.id').value == -1) {
	     $('detailKind.id').value = '';
	  }  
	  
	  if ($('status').value == -1) {
	     $('status').value = '';
	  }    
}     
 </script>
 
  <#include "../../asset/commonSpareType.ftl"/>