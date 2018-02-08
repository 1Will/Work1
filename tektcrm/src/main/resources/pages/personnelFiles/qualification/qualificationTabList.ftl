<#include "../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" action="'searchQualification'" method="'post'">
		<@ww.token name="searchQualificationToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <#if personnelFiles?exists>
	        <#if personnelFiles.id?exists>
	                <@ww.hidden name="'personnelFiles.id'" value="#{personnelFiles.id?if_exists}"/>
	        </#if>
        </#if>
        
         <#if institution?exists>
         	<#if institution.id?exists>
                <@ww.hidden name="'institution.id'" value="#{institution.id?if_exists}"/>
         	</#if>
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
			<#--
			<a href="###" onclick="editQualification('#{object.id}')">#{itemNo}</a>
			-->
			#{itemNo}
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
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('到期时间')}" property="endTime" sortable="desc" format="yyyy-MM-dd" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('管理部门')}" property="dept.name" sortable="desc" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('证书类型')}" property="type.name" sortable="desc" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('所属人')}" property="personnelFiles.name" sortable="desc" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('所属单位')}" property="institution.name" sortable="desc" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="15%" />
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
		<#--
 	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vbutton class="button" value="${action.getText('new')}" onclick="newQualification()"/>
			<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('资格证书')}?" />	 
			<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
			   <@ww.param name="'onclick'" value="'return confirmDeletes(\"qualificationIds\", \"${confirmMessage}\");'"/>
			</@vsubmit>
		</#if>
	</@buttonBar>
		-->
</@ww.form>
<script type="text/javascript">
	function editQualification(id){
		var url ='${req.contextPath}/personnelFile/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag&qualification.id='+id;
    	openNewWindow(url);
    	if(isIE()){self.location.reload();};
	}
	
	function newQualification(){
		<#if personnelFiles?exists>
			<#if personnelFiles.id?exists>
				var url ='${req.contextPath}/personnelFile/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag&personnelFiles.id=#{personnelFiles.id}';
			</#if>
		</#if>
		
		<#if institution?exists>
			<#if institution.id?exists>
				var url ='${req.contextPath}/personnelFile/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag&institution.id=#{institution.id}';
			</#if>
		</#if>
    	openNewWindow(url);
    	if(isIE()){self.location.reload();};
	}
</script>
</@framePage>
