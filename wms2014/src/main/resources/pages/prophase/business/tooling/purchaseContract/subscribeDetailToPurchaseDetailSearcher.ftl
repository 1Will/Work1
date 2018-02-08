<@inputTable>	
	<tr>
		<@ww.textfield label="'${action.getText('subscribe.billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('subscribe.name')}'" name="'subscribe.name'" value="'${req.getParameter('subscribe.name')?if_exists}'" cssClass="'underline'" />	
	</tr>
	<tr>
	    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select> 
		<@ww.textfield label="'${action.getText('graphNo')}'" name="'graphNo'" value="'${req.getParameter('graphNo')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('name')}'" name="'subscribeDetail.name'" value="'${req.getParameter('subscribeDetail.name')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('model')}'" name="'model'" value="'${req.getParameter('model')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
	<@ww.textfield label="'${action.getText('specification')}'" name="'specification'" value="'${req.getParameter('specification')?if_exists}'"  cssClass="'underline'" />
		<@ww.select label="'${action.getText('category')}'" required="false" name="'category.id'" 
             	listKey="id" listValue="value" list="categorys" 
             	emptyOption="false">
		    </@ww.select>
	</tr>
	<tr>
	    <@pp.dateRanger label="'${action.getText('DeliveryDate')}'" name="'requireDate'" 
		       value="'${req.getParameter('requireDate_start')?if_exists}|${req.getParameter('requireDate_end')?if_exists}'"
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
      getObjByNameRe("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
  } 
      
    var selector1 = document.all("category.id");
    groups1 = selector1.options.length;
    for (i=0; i<groups1; i++) {
    <#if req.getParameter('category.id')?exists>
    if (selector1.options[i].value == "${req.getParameter('category.id')?if_exists}") {
      selector1.options[i].selected="true";
    }
    </#if>
    }
function checkInvalidParms(){
  //查询页面的日期验证格式
    beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
    if(!queryDate("requireDate_start","requireDate_end",
	      beginDateMsg,null)){
	     return false;
	}
	  if (getObjByNameRe("department.id").value == -1) {
	  getObjByNameRe("department.id").value = '';
	}  
	if (getObjByNameRe("category.id").value == -1) {
	  getObjByNameRe("category.id").value = '';
	} 
	}    
 </script>