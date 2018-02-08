<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('department.title')}">
    <@ww.form name="'listForm'" action="'searchDepartmentsWindow'" namespace="'/base/department'" method="'post'">
        <@ww.token name="searchDepartmentsToken"/>
        <#include "departmentSearcher.ftl"/>
        <@ww.hidden name="'dIds'" value="'${req.getParameter('dIds')?if_exists}'"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar> 
	 		 <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        </@buttonBar>
        <@list title="${action.getText('department.list')}" includeParameters="id|name|code|dIds|leader|onlyInvalid|onlyValid" fieldMap="like:name|code|leader">
             <@vlh.checkbox property="id" name="deptIds">
                 <@vlh.attribute name="width" value="30"/>
             </@vlh.checkbox>
             <@vcolumn title="${action.getText('deparment.code')}" property="code" sortable="desc">  
             	<@alignLeft/>
             </@vcolumn> 
             
             <@vcolumn title="${action.getText('department.name')}" property="name" sortable="desc" />
             <#assign parentDept=""/>
             <#if object.parentDept?exists>
               <#assign parentDept="${object.parentDept.name}"/>
             <#else>
               <#assign parentDept="无"/>
             </#if>
             
             <@vcolumn title="${action.getText('parent.department')}" property="parentDept.name" sortable="desc" >
             	<@alignLeft/>
             </@vcolumn> 
             <@vcolumn title="${action.getText('department.leader')}" property="leader" sortable="desc">
             	<@alignLeft/>
             </@vcolumn>   
              <@vcolumn title="${action.getText('department.inst')}" property="inst.name" sortable="desc">
              	<@alignLeft/>
             </@vcolumn>   
        </@list>
       <#if !first>
       <#if !(action.isReadOnly())>
			<@buttonBar>
				<@vsubmit name="'choose'" value="'${action.getText('choose')}'">
					<@ww.param name="'onclick'" value="'return confirmSelects();'"/>
					<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>	
		      </@vsubmit>
		  </@buttonBar>
       </#if>
       </#if>
    </@ww.form>
    <script type="text/javascript">
		var deptIds = getObjByName('dIds').value;
		var ids =deptIds.split(",");
		var selector = document.getElementsByName("deptIds");
		for(var i =0 ;i<ids.length;i++){
			for(var j=0;j<selector.length;j++){
				if(selector[j].value == ids[i]){
					selector[j].checked = true;
				}
			}
		}
		document.onclick = function(){
			var deptIds = getObjByName('dIds').value;
			var ids;
			if(deptIds == ''){
				ids = new Array();
			}else{
				ids=deptIds.split(",");
			}
			var selector = document.getElementsByName("deptIds");
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
			getObjByName('dIds').value = ids.toString();
		}
	
		function confirmSelects(boxname) {
			var deptIds = getObjByName('dIds').value;
			returnDialog(deptIds,false);
		}
</script>
</@htmlPage>