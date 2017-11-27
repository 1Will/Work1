<#include "../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" action="'searchRankAndGrade'" method="'post'">
		<@ww.token name="searchRankAndGradeToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        
        <#assign returnName='replaceWord'>
        <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false includeParameters="personnelFiles.id|id|createdTime|" fieldMap="like:" >
			<@vcolumn title="${action.getText('序号')}">
				#{itemNo}
			<@vlh.attribute name="width" value="5%" />
			<@alignLeft />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
	 
			<@vcolumn title="${action.getText('变更前等级')}" property="beforeGrade.name" >
			<@vlh.attribute name="width" value="8%" />
			<@alignLeft/>
			</@vcolumn>  
			<@vcolumn title="${action.getText('变更前职级')}" property="beforeRank.name" >
			<@vlh.attribute name="width" value="8%" />
			<@alignRight/>
			</@vcolumn>  
			
			<@vcolumn title="${action.getText('变更后等级')}" property="newGrade.name" >
			<@vlh.attribute name="width" value="8%" />
			<@alignLeft/>
			</@vcolumn>  
			
			<@vcolumn title="${action.getText('变更后职级')}" property="newRank.name" >
			<@vlh.attribute name="width" value="8%" />
			<@alignRight/>
			</@vcolumn>  
			
			<@vcolumn title="${action.getText('变更时间')}" property="createdTime" sortable="desc" format="yyyy-MM-dd" >
            <@alignLeft/>
            <@vlh.attribute name="width" value="10%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('变更说明')}" property="explain" >
			<#assign returnName=returnName +'replaceWord'>
            <@ww.hidden name="'${returnName}'" value="'${object.explain?if_exists}'"/>
            <span title="${object.explain?if_exists}">
	            <script>
	            	var s = getObjByName('${returnName}').value;
	            	s=s.replace(/[\r\n]/g, "");
	            	document.write(s.slice(0,18)+"...");
	            </script>
            </span>
			</@vcolumn>
		</@list>
    </@ww.form>
    
<script type="text/javascript">
</script>
</@framePage>
