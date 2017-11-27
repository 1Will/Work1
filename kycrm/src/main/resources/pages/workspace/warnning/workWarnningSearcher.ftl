
<@inputTable>	
    <@ww.hidden name="'isRead'" value="0"/>
    <#if loginUser?exists>
      <@ww.hidden name="'loginUser.id'" value="#{loginUser.id}"/>
    </#if>
	<tr>
	    <@ww.textfield label="'${action.getText('提醒名称')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"  /> 
		<@ww.textfield label="'${action.getText('warnning.type')}'" name="'type'" value="'${req.getParameter('type')?if_exists}'" cssClass="'underline'"  />
		<@pp.dateRanger label="'${action.getText('warnning.warnningDate')}'" name="'warnningDate'" 
			            value="'${req.getParameter('warnningDate_start')?if_exists}|${req.getParameter('warnningDate_end')?if_exists}'"
			            cssClass="'underline'" maxlength="10"/>  
	</tr>
	<tr>
        <@ww.checkbox label="'${action.getText('onlyRead')}'" name="'OnlyReadFlag'" value="'false'" fieldValue="'true'"/>
		<script>
			var flag = "${isRead?if_exists}";
			if(flag==1){
				getObjByName("OnlyReadFlag").checked=true;
			}else{
				getObjByName("OnlyReadFlag").checked=false;
			}
		</script>
	</tr>	
	<script language="javascript">
        function checkInvalidParms() {
          if (getObjByName("OnlyReadFlag").checked) {
			getObjByName("isRead").value = "1";
          } else {
            getObjByName("isRead").value = "0";
          }	
          //验证编制日期格式
	     planCreatedMsg="${action.getText('warnning.warnningDate')}" + "${action.getText('dateFormate.error')}";
	     if(!queryDate("warnningDate_start","warnningDate_end",
	          planCreatedMsg,null)){
	       return false;
	     }   
		 return true;
		}
    </script>
</@inputTable>