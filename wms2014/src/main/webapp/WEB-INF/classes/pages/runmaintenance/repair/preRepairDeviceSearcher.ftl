	<@inputTable>
		<@ww.hidden name="'yesStandard'" value=""/>
	    <@ww.hidden name="'noStandard'" value=""/>
	    <@ww.hidden name="'yesEmphasis'" value=""/>
	    <@ww.hidden name="'noEmphasis'" value=""/>
      <tr>
	    <@ww.textfield label="'${action.getText('devicenumber')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('devicename')}'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
	  </tr>
	  <tr>
    	<@ww.select label="'${action.getText('department')}'"  name="'department.id'"  listKey="id" listValue="name"
    			 value="${req.getParameter('department.id')?if_exists}"
                list="departments" emptyOption="false" disabled="false">
        </@ww.select>
    	<@ww.select label="'${action.getText('device.category')}'"  name="'category.id'" 
    				listKey="id" listValue="name"
    			    list="deviceTypes" emptyOption="false" disabled="false">
        </@ww.select>
	  </tr>
	  <tr>
	    <td align="right" valign="top"><label class="label">${action.getText('device.emphasis')}:</label></td>
           <td>
             <select name="device.emphasis" >       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('YES')}</option>       
               <option value="false">${action.getText('NO')}</option>              
            </select>
          </td>
        <td align="right" valign="top"><label class="label">${action.getText('device.standard')}:</label></td>
           <td>
             <select name="device.standard" >       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('YES')}</option>       
               <option value="false">${action.getText('NO')}</option>              
            </select>
           </td>
	  </tr>
      <tr>
        <@ww.select label="'${action.getText('device.managementLevel')}'" 
                   required="false" name="'device.managementLevel'" 
                   value="${req.getParameter('device.managementLevel')?if_exists}"
                   listKey="value" listValue="label"
                   list="managementLevels" 
                   emptyOption="false" 
                   disabled="false">
        </@ww.select> 
    	<@pp.dateRanger label="'${action.getText('device.cardCreatedTime')}'" name="'cardCreatedTime'" 
		                value="'${req.getParameter('cardCreatedTime_start')?if_exists}|${req.getParameter('cardCreatedTime_end')?if_exists}'"
		                cssClass="'underline'" dateFormat="date"/>
     </tr>
   </@inputTable> 
   <script language="JavaScript" type="text/JavaScript">
      //部门
	  var selector = document.all("department.id");
	  groups = selector.options.length;
	  for (i=0; i<groups; i++) {
	    <#if req.getParameter('department.id')?exists>
	    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
	      selector.options[i].selected="true";
	    }
	    </#if>
	  }
	  <#if first>
	    <#if loginUser.department?exists>
	      document.getElementById("department.id").value = #{loginUser.department.id};
	    </#if>
	  </#if> 
	  //设备种类
	  selector = document.all("category.id");
	  var groups=selector.options.length;  
	  for (i=0; i<groups; i++){
	    <#if req.getParameter('category.id')?exists>
	      if (selector.options[i].value=="${req.getParameter('category.id')?if_exists}"){
	        selector.options[i].selected="true";
	      }
	    </#if>
	  }  
	  //管理级别  
	  selector = document.all("device.managementLevel");
	  var groups=selector.options.length;  
	  for (i=0; i<groups; i++){
	    <#if req.getParameter('device.managementLevel')?exists>
	      if (selector.options[i].value=="${req.getParameter('device.managementLevel')?if_exists}"){
	        selector.options[i].selected="true";
	      }
	    </#if>
	  }
	  //重点设备 
	  selector = document.all("device.emphasis");
	  var groups = selector.options.length;
	  for (i=0; i<groups; i++) {
	    <#if req.getParameter('device.emphasis')?exists>
	      if (selector.options[i].value == "${req.getParameter('device.emphasis')?if_exists}") {
		    selector.options[i].selected="true";
		  }
	    </#if>
	  }
	  //非标
	  selector = document.all("device.standard");
	  var groups = selector.options.length;
	  for (i=0; i<groups; i++) {
	    <#if req.getParameter('device.standard')?exists>
	      if (selector.options[i].value == "${req.getParameter('device.standard')?if_exists}") {
		    selector.options[i].selected="true";
		  }
	    </#if>
	  }
	  //管理级别
	  selector = document.all("device.managementLevel");
	  var groups=selector.options.length;  
	  for (i=0; i<groups; i++){
	    <#if req.getParameter('device.managementLevel')?exists>
	      if (selector.options[i].value=="${req.getParameter('device.managementLevel')?if_exists}"){
	        selector.options[i].selected="true";
	      }
	    </#if>
	  } 
	  function checkInvalidParms() {
	    if (document.getElementById("department.id").value==-1) {
          document.getElementById("department.id").value='';
        }
		if (document.getElementById("category.id").value==-1) {
			document.getElementById("category.id").value='';
		} 
		if (document.getElementById("device.managementLevel").value == -1) {
		  document.getElementById("device.managementLevel").value='';
		}
	    if (document.getElementById("device.standard").value == 'true') {
		  document.getElementById("yesStandard").value = 'true';
		} else if (document.getElementById("device.standard").value == 'false') {
		   document.getElementById("noStandard").value = 'false';
		}
		if (document.getElementById("device.emphasis").value == 'true') {
		  document.getElementById("yesEmphasis").value = 'true';
		} else if (document.getElementById("device.emphasis").value == 'false') {
		   document.getElementById("noEmphasis").value = 'false';
		}
		strStartMsg="${action.getText('dateFormate.error')}";
		strEndMsg="${action.getText('planCreatedTime.order.error')}";
		if(!queryDate("cardCreatedTime_start","cardCreatedTime_end",
		    strStartMsg,strEndMsg)){
		    	return false;
		    }
		return true;
	  }
   </script>