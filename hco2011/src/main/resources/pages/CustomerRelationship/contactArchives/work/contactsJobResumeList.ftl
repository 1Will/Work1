<#include "../../../includes/hco2011.ftl" />

<@framePage> 
<@ww.form name="'listForm'" action="'searchContactsJobResume'" method="'post'">
<@ww.token name="searchContactsJobResumeToken"/>
	  <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
      <#if contactId?exists>
      	<@ww.hidden name="'cr.id'" value="#{contactId}"/>
      </#if>
      <#assign itemNo=1/>
      <@list title="" includeParameters="cr.id" fieldMap="like:" >
      		<#if !(action.isReadOnly())>
		        <@vlh.checkbox property="id" name="contactsJobResumeIds">
		            <@vlh.attribute name="width" value="30" />
		        </@vlh.checkbox>
	        </#if>
	        <@vcolumn title="${action.getText('work.no')}" >
            	<a href="###" onclick="editContactsJobResume(#{object.id},${req.getParameter('readOnly')?if_exists})">#{itemNo}</a>
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
		  <@vbutton class="button" value="${action.getText('new')}" onclick="newContactsJobResume()"/>
				<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('work.profile')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                	<@ww.param name="'onclick'" value="'return confirmDeletes(\"contactsJobResumeIds\", \"${confirmMessage}\");'"/>
            		<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            	</@vsubmit>
            	</#if>
		</@buttonBar>
	 </@ww.form>
	 <script language="javascript">
	 //打开编辑工作简历模态窗口
	 function editContactsJobResume(objectId,readOnly){
	 	var url='${req.contextPath}/customerRelationship/editContactsJobResume.html?cr.id=${req.getParameter('cr.id')?if_exists}'+'&contactsJobResume.id='+objectId+'&readOnly='+readOnly;
	    popupModalDialog(url,800,600);
	    if(isIE()){self.location.reload();};
	  }
	 //打开新建工作简历模态窗口
	 function newContactsJobResume(){
		var url='${req.contextPath}/customerRelationship/editContactsJobResume.html?cr.id=${req.getParameter('cr.id')?if_exists}';
		popupModalDialog(url,800,600);
		if(isIE()){self.location.reload();};
	  }
	</script>
</@framePage>