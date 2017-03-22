<#include "../../../includes/hco2011.ftl" />
 <script>
 //关闭模态窗口
  function returnBack(){
    window.close();
  }
</script>
<@htmlPage title="${action.getText('work.profile.title')}">
<@ww.form  name="'contactsJobResumeProfile'" action="'saveContactsJobResume'" namespace="'/customerRelationship'" method="'post'" >
       <@ww.token name="saveContactsJobResumeToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       <#if contactsJobResume.id?exists>
                <@ww.hidden name="'contactsJobResume.id'" value="#{contactsJobResume.id?if_exists}"/>
      </#if>
      
                <@ww.hidden name="'cr.id'" value="#{contactId?if_exists}"/>
   <@inputTable>
   <tr> 
        <@datePickerRanger
				anothername="startTime"
        		label="${action.getText('work.startTime')}"
	           	name="contactsJobResume.startTime"
	     		value="${(contactsJobResume.startTime?string('yyyy-MM-dd'))?if_exists}" 
				cssClass="underline" 
				maxlength="10" 
				flag="true"
				required="true">
       </@datePickerRanger>
       <@datePickerRanger
				anothername="endTime"
	    		label="${action.getText('work.endTime')}"
	           	name="contactsJobResume.endTime"
	     		value="${(contactsJobResume.endTime?string('yyyy-MM-dd'))?if_exists}" 
				maxlength="10" 
				flag="true"
				required="true">
	   </@datePickerRanger>
   </tr>
   <tr>
         <@textfield name="contactsJobResume.job" label="${action.getText('work.job')}" value="${contactsJobResume.job?if_exists}" anothername="job" maxlength="20"/>
      	 <@textfield name="contactsJobResume.inst" label="${action.getText('work.inst')}" value="${contactsJobResume.inst?if_exists}"  required="true" anothername="inst" maxlength="20"/>
         	
    </tr>
    <tr>
	  <@textfield name="contactsJobResume.dept" label="${action.getText('work.dept')}" value="${contactsJobResume.dept?if_exists}" anothername="dept" maxlength="20" />
	  <@textarea name="contactsJobResume.comment" label="${action.getText('work.comment')}" anothername="comment" maxLength="500" value="${contactsJobResume.comment?if_exists}"/>
	       
	</tr>
   </@inputTable>
   <@buttonBar>
   		 <#if !(action.isReadOnly())>
		 	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
		 </#if>
	     <@vbutton class="button"  value="${action.getText('close')}" onclick="returnBack();"/>
   </@buttonBar>
   </@ww.form>
</@htmlPage>

<script>
   //对各字段进行格式、是否必填验证
  function validate(){
    //起始时间验证
    if(!dateCheck_startTime()){
	    getObjByName('contactsJobResume.startTime').focus();
	    return false;
    }
   //终止时间验证
    if(!dateCheck_endTime()){
	    getObjByName('contactsJobResume.endTime').focus();
	    return false;
    }
    //验证起始时间是否大于终止时间
    var star = getObjByName('contactsJobResume.startTime').value;
    var end = getObjByName('contactsJobResume.endTime').value;
    if(isDateLessThenOldDate(star,end)){
		alert('${action.getText('workExperience.time.error')}');
		getObjByName('contactsJobResume.startTime').focus();
		return false;
	}
    //部门验证
    if(!textfieldCheck_dept()){
	    getObjByName('contactsJobResume.dept').focus();
	    return false;
    }
    //职务验证
    if(!textfieldCheck_job()){
	    getObjByName('contactsJobResume.job').focus();
	    return false;
    }
    //单位验证
    if(!textfieldCheck_inst()){
	    getObjByName('contactsJobResume.inst').focus();
	    return false;
    }
    //备注验证
    if(!textareaCheck_comment()){
	    getObjByName('contactsJobResume.comment').focus();
	    return false;
    }
        return true;
  }
</script>