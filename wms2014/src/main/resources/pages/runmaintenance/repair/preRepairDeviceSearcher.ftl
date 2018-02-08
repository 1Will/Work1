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
	      getObjByNameRe("department.id").value = #{loginUser.department.id};
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
	    if (getObjByNameRe("department.id").value==-1) {
          getObjByNameRe("department.id").value='';
        }
		if (getObjByNameRe("category.id").value==-1) {
			getObjByNameRe("category.id").value='';
		} 
		if (getObjByNameRe("device.managementLevel").value == -1) {
		  getObjByNameRe("device.managementLevel").value='';
		}
	    if (getObjByNameRe("device.standard").value == 'true') {
		  getObjByNameRe("yesStandard").value = 'true';
		} else if (getObjByNameRe("device.standard").value == 'false') {
		   getObjByNameRe("noStandard").value = 'false';
		}
		if (getObjByNameRe("device.emphasis").value == 'true') {
		  getObjByNameRe("yesEmphasis").value = 'true';
		} else if (getObjByNameRe("device.emphasis").value == 'false') {
		   getObjByNameRe("noEmphasis").value = 'false';
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