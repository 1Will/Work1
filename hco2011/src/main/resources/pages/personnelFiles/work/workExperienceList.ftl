<#include "../../includes/hco2011.ftl" />

<@framePage> 
<@ww.form name="'listForm'" action="'searchWorkExperience'" method="'post'">
<@ww.token name="searchWorkExperienceToken"/>
	  <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
      <#if personnelFileId?exists>
      	<@ww.hidden name="'pf.id'" value="#{personnelFileId?if_exists}"/>
      </#if>
      <#assign itemNo=1/>
      <@list title="" includeParameters="pf.id" fieldMap="like:" >
      		<#if !(action.isReadOnly())>
		        <@vlh.checkbox property="id" name="workExperienceIds">
		            <@vlh.attribute name="width" value="30" />
		        </@vlh.checkbox>
	        </#if>
	        <@vcolumn title="${action.getText('work.no')}" >
            	<a href="###" onclick="editWorkExperience(#{object.id},${req.getParameter('readOnly')?if_exists})">#{itemNo}</a>
            	<@alignCenter/>
         	</@vcolumn>
         	<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('work.startTime')}"  format="yyyy-MM-dd" property="startTime" >
            	<@alignCenter/>
         	</@vcolumn> 
         <@vcolumn title="${action.getText('work.endTime')}"  format="yyyy-MM-dd" property="endTime" >
            	<@alignCenter/>
         </@vcolumn>
         <@vcolumn title="${action.getText('work.dept')}" property="dept" >
            	<@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('work.job')}" property="job" >
            	<@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('work.inst')}" property="inst" >
         	<@alignLeft/>
         </@vcolumn>
        </@list>
        <@buttonBar>
        <#if !(action.isReadOnly())>
		  <@vbutton class="button" value="${action.getText('new')}" onclick="newWorkExperience()"/>
				<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('work.profile')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                	<@ww.param name="'onclick'" value="'return confirmDeletes(\"workExperienceIds\", \"${confirmMessage}\");'"/>
            		<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            	</@vsubmit>
         </#if>
		</@buttonBar>
	 </@ww.form>
	 <script language="javascript">
	 //打开编辑工作简历模态窗口
	 function editWorkExperience(objectId,readOnly){
	 	var url='${req.contextPath}/personnelFile/editWorkExperience.html?pf.id=${req.getParameter('pf.id')?if_exists}'+'&workExperience.id='+objectId+'&readOnly='+readOnly;
	    popupModalDialog(url,800,600);
	    if(isIE()){self.location.reload();};
	  }
	 //打开新建工作简历模态窗口
	 function newWorkExperience(){
		var url='${req.contextPath}/personnelFile/editWorkExperience.html?readOnly=${req.getParameter('readOnly')?if_exists}&pf.id=${req.getParameter('pf.id')?if_exists}';
		popupModalDialog(url,800,600);
		if(isIE()){self.location.reload();};
	  }
	</script>
</@framePage>