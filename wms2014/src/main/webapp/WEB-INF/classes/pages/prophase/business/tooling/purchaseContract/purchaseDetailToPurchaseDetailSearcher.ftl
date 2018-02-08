<@inputTable>	
	<tr>
		<@ww.textfield label="'${action.getText('pchoose.billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('pchoose.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" />	
	</tr>
	<tr>
	    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select> 
		<@ww.textfield label="'${action.getText('graphNo')}'" name="'graphNo'" value="'${req.getParameter('graphNo')?if_exists}'" cssClass="'underline'" />
	    
	</tr>
	<tr>
	<@ww.textfield label="'${action.getText('name')}'" name="'dtlName'" value="'${req.getParameter('dtlName')?if_exists}'" cssClass="'underline'" />	 
	<@ww.textfield label="'${action.getText('model')}'" name="'model'" value="'${req.getParameter('model')?if_exists}'" cssClass="'underline'" />	
	
	</tr>
	<tr>
	<@ww.textfield label="'${action.getText('specification')}'" name="'specification'" value="'${req.getParameter('specification')?if_exists}'"  cssClass="'underline'" />
	    <@pp.dateRanger label="'${action.getText('DeliveryDate')}'" name="'reqDeliveryDate'" 
		       value="'${req.getParameter('reqDeliveryDate_start')?if_exists}|${req.getParameter('reqDeliveryDate_end')?if_exists}'"
		cssClass="'underline'" dateFormat="date"/> 
	</tr>
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
  <#if first>
    <#if loginUser.department?exists>
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
 } 
function checkInvalidParms(){
  //查询页面的日期验证格式
    beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
    if(!queryDate("reqDeliveryDate_start","reqDeliveryDate_end",
	      beginDateMsg,null)){
	     return false;
	}
	  if (document.getElementById("department.id").value == -1) {
	  document.getElementById("department.id").value = '';
	}  
	}    
 </script>