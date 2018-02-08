<#include "../../../includes/macros.ftl" />
<@htmlPage >
  <@ww.form namespace="'/communication/groups'" name="'listForm'" action="'searchGroupsWindow'" method="'get'">
     <@ww.token name="searchGroupsToken"/>
        <@inputTable>
            <@ww.hidden name="'nonParentGroup'" value="${req.getParameter('nonParentGroup')?if_exists}"/>
            <tr>
                <@ww.textfield label="'${action.getText('group.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
                <@ww.textfield label="'${action.getText('group.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
            </tr>
            <@ww.hidden name="'gIds'" value="'${req.getParameter('gIds')?if_exists}'"/>
        </@inputTable>
         <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        </@buttonBar>
         <@list title="${action.getText('list.title')}" includeParameters="code|name|gIds|nonParentGroup|parent.group" fieldMap="like:code|name" >
            <@vcolumn title="">
                <input type="checkbox" name="groupIds" value="#{object.id}" width="30" />
            </@vcolumn>
            <@vcolumn title="${action.getText('group.code')}">
                <a href="editGroup.html?group.id=#{object.id}">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('group.name')}" property="name"/>
         </@list>
        <#if !first>
		<@buttonBar>
			<@vsubmit name="'choose'" value="'${action.getText('choose')}'">
				<@ww.param name="'onclick'" value="'return confirmSelects();'"/>
				<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>	
			</@vsubmit>
		</@buttonBar>
		</#if>
		</@ww.form>
<script type="text/javascript">
		var groupIds = getObjByName('gIds').value;
		var ids =groupIds.split(",");
		var selector = document.getElementsByName("groupIds");
		for(var i =0 ;i<ids.length;i++){
			for(var j=0;j<selector.length;j++){
				if(selector[j].value == ids[i]){
					selector[j].checked = true;
				}
			}
		}
		document.onclick = function(){
			var groupIds = getObjByName('gIds').value;
			var ids;
			if(groupIds == ''){
				ids = new Array();
			}else{
				ids=groupIds.split(",");
			}
			var selector = document.getElementsByName("groupIds");
			if(selector.length){
				for(var i=0;i<selector.length;i++){
					var temp = selector[i].value;
					if(selector[i].checked == true){
						if(ids.indexOf(temp)<0){
							ids.push(temp);
						}
					}else{
						ids.remove(temp);
					}
				}
			}
			getObjByName('gIds').value = ids.toString();
		}
	
		function confirmSelects(boxname) {
			var groupIds = getObjByName('gIds').value;
			returnDialog(groupIds,false);
		}
</script>
</@htmlPage>
