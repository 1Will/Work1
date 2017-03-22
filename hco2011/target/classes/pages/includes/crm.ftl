<#include "./macros.ftl" />
<#macro crm_onlySearchInvalid_checkBox>
  <@ww.hidden name="'onlyValid'" value="true"/>
  <@ww.hidden name="'onlyInvalid'" value=""/>
  <@ww.checkbox label="'${action.getText('仅查询失效')}'" name="'onlyDisabled'" value="'false'" fieldValue="'value'" onblur="'changValidOrInvalidStatus()'"/>      
  <script language="javascript">
    function changValidOrInvalidStatus(){
      if (getObjByName('onlyDisabled').checked) {
        getObjByName('onlyInvalid').value = "true";
        getObjByName('onlyValid').value = "";
      } else {
        getObjByName('onlyInvalid').value = "";
        getObjByName('onlyValid').value = "true";
      }
    }
   <#if (action.isOnlyInvalid())>
     getObjByName('onlyDisabled').checked=true;
     changValidOrInvalidStatus();
   </#if>
  </script>
</#macro>

<#macro crm_disabledOrEnabled_button message="" boxName="" jsFunctionName="" other="test">
  <#if (action.isOnlyInvalid())>
  	<#assign confirmMessage1 = "${action.getText('confirm.valid')}${message?if_exists}?" />
	<@vsubmit name="'enabled'" value="'${action.getText('enabled')}'">
	  <@ww.param name="'onclick'" value="'return validateInvalid(confirmValids(\"${boxName?if_exists}\", \"${confirmMessage1}\"),${jsFunctionName});'"/>
	  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	</@vsubmit>
    <#assign confirmMessage = "${action.getText('confirm.delete')}${message?if_exists}?" />
	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	  <@ww.param name="'onclick'" value="'return validateInvalid(confirmDeletes(\"${boxName?if_exists}\", \"${confirmMessage}\"),${jsFunctionName});'"/>
	  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	</@vsubmit>
  <#else>
    <#assign confirmMessage1 = "${action.getText('confirm.inValid')}${message?if_exists}?" />
	<@vsubmit name="'disabled'" value="'${action.getText('disabled')}'">
	  <@ww.param name="'onclick'" value="'return validateInvalid(confirmInvalids(\"${boxName?if_exists}\", \"${confirmMessage1}\"),${jsFunctionName});'"/>
	  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	</@vsubmit>
  </#if>
  <script language="javascript">
	function validateInvalid(delFun, checkFun) {
      if (delFun) {
        checkFun;
        return true;
      }
      return false;
   }
  </script>
</#macro>