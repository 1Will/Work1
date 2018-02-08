<@inputTable>
		<tr>
		    <@ww.textfield label="'${action.getText('DEVICEassetNo')}'" name="'assetNo'" value="'${req.getParameter('assetNo')?if_exists}'" cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('DEVICEassetName')}'" name="'assetName'" value="'${req.getParameter('assetName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr> 
            <@ww.select label="'${action.getText('department')}'" 
                        name="'department.id'" 
                        value="'${req.getParameter('department.id')?if_exists}'"
                        listKey="id" listValue="name"
                        list="departments" emptyOption="false" 
                        disabled="false" required="false">
            </@ww.select>
    	    <@pp.dateRanger label="'${action.getText('brockenDate')}'" name="'month'" 
		                    value="'${req.getParameter('month_start')?if_exists}|${req.getParameter('month_end')?if_exists}'"
		                    cssClass="'underline'" dateFormat="'%Y-%m'" maxlength="7"/> 
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
  }
  <#if first>
    <#if loginUser.department?exists>
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
  function checkInvalidParms() {
    if (-1 == document.getElementById("department.id").value) {
		document.getElementById("department.id").value = '';
    }
    <#--
	//验证编制日期格式
	planCreatedMsg="${action.getText('checkDate')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("month_start","month_end",
	    planCreatedMsg,null)){
	  return false;
	}
	-->
  }
</script>