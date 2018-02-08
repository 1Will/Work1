
<@inputTable>	
    <@ww.hidden name="'onlyRead'" value=""/>
    <@ww.hidden name="'onlyUnRead'" value="true"/>
    <#if loginUser?exists>
      <@ww.hidden name="'loginUser.id'" value="#{loginUser.id}"/>
    </#if>
	<tr>
		<@ww.textfield label="'${action.getText('warnning.type')}'" name="'type'" value="'${req.getParameter('type')?if_exists}'" cssClass="'underline'"  />
		<@pp.dateRanger label="'${action.getText('warnning.warnningDate')}'" name="'warnningDate'" 
			            value="'${req.getParameter('warnningDate_start')?if_exists}|${req.getParameter('warnningDate_end')?if_exists}'"
			            cssClass="'underline'" maxlength="10"/>  
	</tr>
	<tr>
        <@ww.checkbox label="'${action.getText('onlyRead')}'" name="'OnlyReadFlag'" value="'false'" fieldValue="'true'"/>
	</tr>	
	<script language="javascript">
        function checkInvalidParms() {
          if (getObjByNameRe("OnlyReadFlag").checked) {
            getObjByNameRe("onlyUnRead").value = "";
            getObjByNameRe("onlyRead").value = "true";
          } else {
            getObjByNameRe("onlyUnRead").value = "true";
            getObjByNameRe("onlyRead").value = "";
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