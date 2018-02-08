<#include "../../includes/hco2011.ftl" />
 <script>
 //关闭模态窗口
  function returnBack(){
    window.close();
  }
</script>
<@htmlPage title="${action.getText('work.profile.title')}">
<@ww.form  name="'workExperienceProfile'" action="'saveWorkExperience'" namespace="'/personnelFile'" method="'post'" >
       <@ww.token name="saveWorkAndResumeToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       <#if workExperience.id?exists>
                <@ww.hidden name="'workExperience.id'" value="#{workExperience.id?if_exists}"/>
      </#if>
      
                <@ww.hidden name="'pf.id'" value="#{personnelFileId?if_exists}"/>
   <@inputTable>
   <tr> 
        <@datePickerRanger
				anothername="startTime"
        		label="${action.getText('work.startTime')}"
	           	name="workExperience.startTime"
	     		value="${(workExperience.startTime?string('yyyy-MM-dd'))?if_exists}" 
				cssClass="underline" 
				maxlength="10" 
				flag="true"
				required="true">
       </@datePickerRanger>
       <@datePickerRanger
				anothername="endTime"
	    		label="${action.getText('work.endTime')}"
	           	name="workExperience.endTime"
	     		value="${(workExperience.endTime?string('yyyy-MM-dd'))?if_exists}" 
				maxlength="10" 
				flag="true"
				required="false">
	   </@datePickerRanger>
   </tr>
   <tr>
         <@textfield name="workExperience.job" label="${action.getText('work.job')}" value="${workExperience.job?if_exists}" anothername="job" maxlength="20"/>
      	 <@textfield name="workExperience.inst" label="${action.getText('work.inst')}" value="${workExperience.inst?if_exists}"  required="true" anothername="inst" maxlength="20"/>
         	
    </tr>
    <tr>
	  <@textfield name="workExperience.dept" label="${action.getText('work.dept')}" value="${workExperience.dept?if_exists}" anothername="dept" maxlength="20" />
	  <@textarea name="workExperience.comment" label="${action.getText('work.comment')}" anothername="comment" maxLength="500" value="${workExperience.comment?if_exists}"/>
	       
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
	    getObjByName('workExperience.startTime').focus();
	    return false;
    }
   //终止时间是否存在
   if(getObjByName('workExperience.endTime').value!=''){
   	//终止时间验证
    if(!dateCheck_endTime()){
	    getObjByName('workExperience.endTime').focus();
	    return false;
    }
    //验证起始时间是否大于终止时间
    var star = getObjByName('workExperience.startTime').value;
    var end = getObjByName('workExperience.endTime').value;
    if(isDateLessThenOldDate(star,end)){
		alert('${action.getText('workExperience.time.error')}');
		getObjByName('workExperience.startTime').focus();
		return false;
	}
	}
    //部门验证
    if(!textfieldCheck_dept()){
	    getObjByName('workExperience.dept').focus();
	    return false;
    }
    //职务验证
    if(!textfieldCheck_job()){
	    getObjByName('workExperience.job').focus();
	    return false;
    }
    //单位验证
    if(!textfieldCheck_inst()){
	    getObjByName('workExperience.inst').focus();
	    return false;
    }
    //备注验证
    if(!textareaCheck_comment()){
	    getObjByName('workExperience.comment').focus();
	    return false;
    }
        return true;
  }
</script>