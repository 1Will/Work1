<#include "../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" action="'searchQualification'" method="'post'">
		<@ww.token name="searchQualificationToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <#if personnelFiles?exists>
                <@ww.hidden name="'personnelFiles.id'" value="#{personnelFiles.id?if_exists}"/>
        </#if>
        <#assign returnName='replaceWord'>
        <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false includeParameters="personnelFiles.id|id|starTime|endTime" fieldMap="like:" >
		<#if !(action.isReadOnly())>
			<@vlh.checkbox property="id" name="qualificationIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
        </#if>
			<@vcolumn title="${action.getText('序号')}">
			<a href="###" onclick="editQualification('#{object.id}')">#{itemNo}</a>
			<@vlh.attribute name="width" value="5%" />
			<@alignCenter />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
	 
			<@vcolumn title="${action.getText('证书名称')}" property="name" >
			<@vlh.attribute name="width" value="8%" />
			<@alignLeft/>
			</@vcolumn>  
			
			<@vcolumn title="${action.getText('开始时间')}" property="starTime" sortable="desc" format="yyyy-MM-dd" >
            <@alignLeft/>
            <@vlh.attribute name="width" value="10%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('到期时间')}" property="endTime" sortable="desc" format="yyyy-MM-dd" >
            <@alignLeft/>
            <@vlh.attribute name="width" value="10%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('说明')}" property="explain" >
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
 	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vbutton class="button" value="${action.getText('new')}" onclick="newQualification()"/>
			<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('资格证书')}?" />	 
			<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
			   <@ww.param name="'onclick'" value="'return confirmDeletes(\"qualificationIds\", \"${confirmMessage}\");'"/>
			</@vsubmit>
		</#if>
	</@buttonBar>
</@ww.form>
<script type="text/javascript">
	function editQualification(id){
		var url ='${req.contextPath}/personnelFile/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}&qualification.id='+id;
    	openNewWindow(url);
    	if(isIE()){self.location.reload();};
	}
	
	function newQualification(){
		var url ='${req.contextPath}/personnelFile/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}&personnelFiles.id=#{personnelFiles.id}';
    	openNewWindow(url);
    	if(isIE()){self.location.reload();};
	}
</script>
</@framePage>
