<#include "../../includes/hco2011.ftl" />
  <@inputTable>
  	<@ww.hidden name="'isSuperSys'" value="'${req.getParameter('isSuperSys')?if_exists}'"/>
  	<tr>
		<@textfield label="${action.getText('周名称')}" name="week.name" value="" cssClass="underline"/>
		<td valign="middle" align="right">
			<input type="checkbox" name = "year" value="year">
		</td>
		<td valign="middle" align="left">
			<lable>查询今年</lable>
		</td>
		<script>
		   <#if year?exists>
		     getObjByName('year').checked=true;
		   </#if>
		</script>
		
		<td valign="middle" align="right">
			<input type="checkbox" name = "checkbox" value="checkbox">
		</td>
		<td valign="middle" align="left">
			<lable>查询所有</lable>
		</td>
		<script>
		   <#if checkbox?exists>
		     getObjByName('checkbox').checked=true;
		   </#if>
		</script>
  	</tr>
  </@inputTable>
<script>
	function checkInvalidParms() {
    	return true;
	}
</script>