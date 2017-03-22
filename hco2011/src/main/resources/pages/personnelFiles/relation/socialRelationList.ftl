<#include "../../includes/hco2011.ftl" /> 
<@framePage > 
<@ww.form name="'listForm'" action="'searchSocialRelation'"  namespace="'/personnelFile'" method="'post'">
      <@ww.token name="searchSocialRelationToken"/>
      <#if personnelFileId?exists>
       <@ww.hidden name="'pf.id'" value="#{personnelFileId?if_exists}"/>
      </#if>
     
      <#if contactArchivesId?exists>
      <@ww.hidden name="'cr.id'" value="#{contactArchivesId?if_exists}"/>
      </#if>
       
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
      <#assign i=1/>
      <@list title="" includeParameters="pf.id|cr.id">
      		<#if !(action.isReadOnly())>
		        <@vlh.checkbox property="id" name="socialRelationIds">
		            <@vlh.attribute name="width" value="30" />
		        </@vlh.checkbox>
	        </#if>
	        <@vcolumn title="${action.getText('socialRelation.no')}" >
            	<a href="#" onclick="editSocialRelation(#{object.id},${req.getParameter('readOnly')?if_exists})">${i}</a>
            	<@alignCenter/>
         	</@vcolumn>
         	<#assign i=i+1 />
            <@vcolumn title="${action.getText('socialRelation.name')}" property="name">
            	<@alignLeft/>
         	</@vcolumn> 
	         <@vcolumn title="${action.getText('socialRelation.relations')}" property="relations" >
	            	<@alignLeft/>
	         </@vcolumn>
	         <@vcolumn title="${action.getText('socialRelation.telphone')}" property="telphone" >
	            	<@alignLeft/>
	         </@vcolumn>
        </@list>
       <@buttonBar>
       <#if !(action.isReadOnly())>
		  <@vbutton class="button" value="${action.getText('new')}" onclick="newSocialRelation()"/>
		  	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('socialRelation.relation')}?" />	 
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	               <@ww.param name="'onclick'" value="'return confirmDeletes(\"socialRelationIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	     </#if>
		</@buttonBar>
	 </@ww.form>
<script language="javascript">
  //打开编辑社会关系模态窗口
  function editSocialRelation(v,readOnly){
  
  <#if personnelFileId?exists>
         popupModalDialog('editSocialRelation.html?pf.id=#{personnelFileId}&socialRelation.id='+v+'&readOnly='+readOnly,800,600);
      </#if>
     
      <#if contactArchivesId?exists>
        popupModalDialog('editSocialRelation.html?cr.id=#{contactArchivesId}&socialRelation.id='+v+'&readOnly='+readOnly,800,600);
      </#if>
     
      self.location.reload();
  }
  //打开新建社会关系模态窗口
  function newSocialRelation(){
   <#if personnelFileId?exists>
        popupModalDialog('editSocialRelation.html?pf.id=#{personnelFileId}',800,600);
      </#if>
     
      <#if contactArchivesId?exists>
       popupModalDialog('editSocialRelation.html?cr.id=#{contactArchivesId}',800,600);
      </#if>
	  self.location.reload();
  }
</script>
</@framePage>