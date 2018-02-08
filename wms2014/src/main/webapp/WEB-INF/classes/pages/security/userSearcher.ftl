<@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('user.loginName')}'" name="'loginName'" value="'${req.getParameter('loginName')?if_exists}'" cssClass="'underline'"/>
                <@ww.textfield label="'${action.getText('user.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
            </tr>

            <tr>
            <@ww.select label="'${action.getText('filiale')}'" 
                           name="'filiale.id'" 
                           value="'${req.getParameter('filiale.id')?if_exists}'"
                           listKey="id" listValue="name"
                           list="filiales" emptyOption="false" 
                           disabled="false" required="false" 
                           onchange="'filialeSelectDeptDWR()'">
               </@ww.select>
      			
               <@ww.select label="'${action.getText('department')}'" 
                           name="'department.id'" 
                           value="'${req.getParameter('department.id')?if_exists}'"
                           listKey="id" listValue="name"
                           list="departments" emptyOption="false" 
                           disabled="false" required="false">
               </@ww.select>

            </tr>
            <tr>
<#--  --> 
                <@ww.select label="'${action.getText('user.type')}'" required="false" 
                        name="'user.type'"  listKey="value" listValue="label"
                        list="userType" emptyOption="false" disabled="false" 
                        value="'${req.getParameter('user.type')?if_exists}'">                  
      		    </@ww.select>
    		    <@ww.checkbox label="'${action.getText('excludeDisabled')}'" name="'exclude_disabled'" value="${excludedisabled?if_exists}" fieldValue="'true'"/>
            </tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
   window.onload=function(){
      if ($('hiddenexcludedisabled').value=="true"){
  			$('exclude_disabled').checked="true";
		}
  }
  
    //页面刷新时保持分公司联动部门
  <#if req.getParameter('filiale.id')?exists>
  		var selector = $("filiale.id");
	    selector.options[0].value=-1;
	    selector.options[0].text="所有";
       $('filiale.id').value = "${req.getParameter('filiale.id')?if_exists}";
       DWREngine.setAsync(false);
  		filialeSelectDeptDWR();
  		//重新设置为异步方式
	    DWREngine.setAsync(true);
	    <#else>
       	var selector = $("filiale.id");
	    selector.options[0].value=-1;
	    selector.options[0].text="所有";
  </#if>
  
  var selector = document.all("filiale.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {

    <#if req.getParameter('filiale.id')?exists>
    if (selector.options[i].value == "${req.getParameter('filiale.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  
  selector = document.all("department.id")
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  <#--   -->
  var selector = document.all("user.type");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('user.type')?exists>
    if (selector.options[i].value == "${req.getParameter('user.type')?if_exists}") {
      selector.options[i].selected="true";
    }
	
    </#if>
  }

  

  function checkInvalidParms() {
    if (-1 == document.getElementById("department.id").value) {
		document.getElementById("department.id").value = '';
    }
     if (-1 == document.getElementById("filiale.id").value) {
		document.getElementById("filiale.id").value = '';
    }
     if (-1 == document.getElementById("user.type").value) {
		document.getElementById("user.type").value = '';
    }
    
    if($('exclude_disabled').checked){
    	$('hiddenexcludedisabled').value="true";
    }else{
    	$('hiddenexcludedisabled').value="false";
    }
    
    return true;
  }
  </script>

