<#include "../../includes/hco2011.ftl" /> 
<@htmlPage title="${action.getText('socialRelation.profile')}">
<@ww.form  name="'socialRelationProfile'" action="'saveSocialRelation'"  namespace="'/personnelFile'"  method="'post'" >
       <@ww.token name="saveSocialRelationToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       <#if socialRelation.id?exists>
                <@ww.hidden name="'socialRelation.id'" value="#{socialRelation.id?if_exists}"/>
      </#if>
                
                <#if personnelFileId?exists>
                <@ww.hidden name="'pf.id'" value="#{personnelFileId?if_exists}"/>
      </#if>
      
      <#if contactArchivesId?exists>
                <@ww.hidden name="'cr.id'" value="#{contactArchivesId?if_exists}"/>
      </#if>
   <@inputTable>
   <tr>
        <@textfield name="socialRelation.name" label="${action.getText('socialRelation.name')}" value="${socialRelation.name?if_exists}"  required="true" anothername="name" maxlength="20"/>
        <@textfield name="socialRelation.relations" label="${action.getText('socialRelation.relations')}" value="${socialRelation.relations?if_exists}"  required="true" anothername="relations" maxlength="20"/>  
   </tr>
   <tr>
      	 <@datePickerRanger
			anothername="birth"
    		label="${action.getText('socialRelation.birth')}"
           	name="socialRelation.birth"
     		value="${(socialRelation.birth?string('yyyy-MM-dd'))?if_exists}" 
			cssClass="underline" 
			maxlength="10" 
			flag="true">
		</@datePickerRanger>
      	 <@select 
			label="${action.getText('socialRelation.politice')}"
			name="socialRelation.politice"
			id="socialRelation.politice"
			anothername="politice"
			value="${req.getParameter('socialRelation.politice')?if_exists}"
			listKey="id"
			listValue="name"
			list="allPolitice"
			emptyOption="true"
			/>
	</tr>
	<tr>
	     <@textfield name="socialRelation.telphone" label="${action.getText('socialRelation.telphone')}" value="${socialRelation.telphone?if_exists}" required="true" anothername="telphone" maxlength="20"/>
         <@textfield name="socialRelation.literacy" label="${action.getText('socialRelation.literacy')}" value="${socialRelation.literacy?if_exists}" anothername="literacy" maxlength="20" />	
    </tr>
    <tr>
         <@textfield name="socialRelation.job" label="${action.getText('socialRelation.job')}" value="${socialRelation.job?if_exists}" anothername="job" maxlength="20"/>
      	 <@textfield name="socialRelation.inst" label="${action.getText('socialRelation.inst')}" value="${socialRelation.inst?if_exists}"  anothername="inst" maxlength="20" />
    </tr>
    <tr>
	     <@textarea name="socialRelation.comment" label="${action.getText('socialRelation.comment')}" value="${socialRelation.comment?if_exists}" anothername="comment" maxLength="500"/>
	</tr>
   </@inputTable>
   <@buttonBar>
  		 <#if !(action.isReadOnly())>
		 	<@vsubmit value="'${action.getText('save')}'" onclick="'return validate();'"/>
		 </#if>
	     <@vbutton value="${action.getText('close')}" onclick="closeThis()"/>
   </@buttonBar>
   </@ww.form>
</@htmlPage>
<script>

   window.onload=function(){
	    <#if socialRelation.politice?exists>
	    getObjByName('socialRelation.politice').value=${socialRelation.politice.id};
	    </#if>
   }
   //对各字段进行格式、是否必填验证
   function validate(){
      //姓名验证
	  if(!textfieldCheck_name()){
		  getObjByName('socialRelation.name').focus();
		  return false;
	  }
	  //与本人关系验证
	  if(!textfieldCheck_relations()){
		  getObjByName('socialRelation.relations').focus();
		  return false;
	  }
	  //出生日期验证
	  if(!dateCheck_birth()){
		  getObjByName('socialRelation.birth').focus();
		  return false;
	  }
	  //联系电话验证
	  if(!textfieldCheck_telphone()){
		  getObjByName('socialRelation.telphone').focus();
		  return false;
	  }
	  //文化程度验证
	  if(!textfieldCheck_literacy()){
		  getObjByName('socialRelation.literacy').focus();
		  return false;
	  }
	  //职务验证
	   if(!textfieldCheck_job()){
		   getObjByName('socialRelation.job').focus();
		   return false;
	  }
	  //单位验证
	   if(!textfieldCheck_inst()){
		   getObjByName('socialRelation.inst').focus();
		   return false;
	  }
	  //备注验证
	   if(!textareaCheck_comment()){
		   getObjByName('socialRelation.comment').focus();
		   return false;
	  }
	  return true;

  }
  //关闭模态窗口
  function returnBack(){
     window.close();
     return false;
  }

</script>