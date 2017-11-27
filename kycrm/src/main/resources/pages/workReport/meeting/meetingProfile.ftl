<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('meeting.New')}">
     <@ww.form  name="'saveMeetingPlan'" action="'saveMeeting'" namespace="'/workReport'" method="'post'" >
     <@ww.token name="saveMeetingPlanToken"/>
     	  	<@inputTable>
     	  	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     	  	<@ww.hidden name="'meetingMonth.id'" value="'${meetingMonth.id?if_exists}'"/>
     	  	<@ww.hidden name="'meetingMonth.weekmeeting'" value="'${meetingMonth.weekmeeting?if_exists}'"/>
     	  	<@ww.hidden name="'meetingOfMorning.isSaved'" value="''"/>
			    
		     <tr>
		        <#if meetingMonth.publisher?exists>
			        <@textfield label="${action.getText('meeting.month')}" name="meetingMonth.date1"  value="${meetingMonth.date?if_exists}" required="true" cssClass="underline" disabled="true" />
		        <#else>
			        <@pp.datePicker 
					label="'${action.getText('meeting.month')}'" 
					name="'meetingMonth.date'" 
		   			value="'${req.getParameter('meetingMonth.date')?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="'%Y-%m'"
					maxlength="10"/>
		        </#if>
		        
		        <#if meetingMonth.publisher?exists>
 				  <@textfield label="${action.getText('meeting.publisher')}" name="publisher.name"  value="${meetingMonth.publisher.name?if_exists}" required="true" cssClass="underline" disabled="true" />
 			    <#else>
 				  <@textfield label="${action.getText('meeting.publisher')}" name="publisher.name"  value="${publisher.name?if_exists}" required="true" cssClass="underline" disabled="true" />
 			    </#if>
			</tr>	
			
			<tr>
			<td align="right" valign="top">
				 <span class="required">*</span>
	        		<label class="label">
	        			${action.getText('meeting.theme')}:
	        		</label>
	        	</td>
		        <td colspan="10">
			<#if meetingMonth.theme?exists>
		        	<textarea  name="meetingMonth.theme" rows="4" value="${meetingMonth.theme?if_exists}">${meetingMonth.theme?if_exists}</textarea>
			<#else>
		        	<textarea  name="meetingMonth.theme" rows="4"  value="${meetingMonth.theme?if_exists}">${meetingMonth.theme?if_exists}</textarea>
			</#if>
		        </td>
		        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("meetingMonth.theme").cols =width;
			</script>
			</tr>
			
			<tr>
			   <td align="right" valign="top">
				    <span class="required">*</span>
	        		<label class="label">
	        			${action.getText('早会星期')}:
	        		</label>
	        	</td>
	        	<td>
			      <label><input name="weekday" type="checkbox" value="1"  />星期一 </label> 
			      <label><input name="weekday" type="checkbox" value="2"  />星期二</label> 
			      <label><input name="weekday" type="checkbox" value="3"  />星期三 </label> 
			      <label><input name="weekday" type="checkbox" value="4"  />星期四 </label> 
			      <label><input name="weekday" type="checkbox" value="5"  />星期五</label> 
			      <#if meetingMonth.weekmeeting?exists>
			      <script>
				    var weekmeetings ='${meetingMonth.weekmeeting?if_exists}';
				    var result=weekmeetings.split(",");
				    var checkArry = document.getElementsByName("weekday");
				    for(var i=0;i<checkArry.length;i++){
				       checkArry[i].disabled="true";
				    }
				    for(var i=0;i<result.length;i++){
				       var j=parseInt(result[i])-1;
				       checkArry[j].checked="true";
				    }
				   </script>
				   <#else>
				    <script>
				        var checkArry = document.getElementsByName("weekday");
					    checkArry[0].checked="true";
					    checkArry[4].checked="true";
					</script>
				  </#if>
			    </td>
          </tr>
     	  </@inputTable>
     	  <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
		     <#-- 继续新建按钮   -->
		     <#if meetingMonth.date?exists>
			      <@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/workReport/editMeeting.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
		     <#else>
			      <@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/workReport/editMeeting.html？readOnly=${req.getParameter('readOnly')?if_exists}"/>
			 <script language="JavaScript" type="text/JavaScript"> 
			    getObjByName("newNext").disabled="true";
			 </script>
		     </#if>
	         <@redirectButton value="${action.getText('back')}" url="listMeetingMonth.html"/>
         </@buttonBar>	
     </@ww.form>
     
</@htmlPage>

 <script type='text/javascript' src='${req.contextPath}/dwr/interface/MeetingMonth.js'></script>

<script>
    document.onclick = function (){
    if (getObjByName('meetingMonth.id').value==''){
    	var time = getObjByName('meetingMonth.date').value;
        //判断该月是否已创建晨会
    	<!-- 通过日期在数据库中查询数据，异步方式 -->
		DWREngine.setAsync(false);
		<!--判断当月有没有创建晨会  -->
		MeetingMonth.loadByDate(time,meetingMonth);
		DWREngine.setAsync(true);
		}
    }
    //验证各属性是否必填、格式
    function validate(){
    var count = 0;
    var checkArry = document.getElementsByName("weekday");
    for (var i = 0; i < checkArry.length; i++) { 
        if(checkArry[i].checked == true){
            count++; 
        }
    }
    if( count == 0 ){
        alert("星期不能为空！")
        return false;
    }
    
    if (getObjByName('meetingMonth.id').value==''){
        var reg=new RegExp(/^\d{4}-(0[1-9]|1[0-2])$/);
        var time = getObjByName('meetingMonth.date').value;
        //日期
		if(time==''){
			alert('${action.getText('meetingDate.NotNull')}');
			return false;
		}
		if(!reg.test(time)){
		   alert('${action.getText('Wrong.date.format')}');
		   return false;
		}
		}
		//主题
		if(getObjByName('meetingMonth.theme').value==''){
			alert('${action.getText('Theme.NotNull')}');
			return false;
		}
	}
	function meetingMonth(data){
	   if(data){
	     alert('${action.getText('meetings.exists')}');
	     return false;
	   }
    }
</script>



<#if meetingMonth.id?exists>
<#--
<ul id="beautytab">
	<li>
		<a id="meetingDayList" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/workReport/listMeetingDay.html?meetingMonth.id=#{meetingMonth.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('早会详细')}</a>
	</li>
</ul>
-->
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/workReport/listMeetingDay.html?meetingMonth.id=#{meetingMonth.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="60%"/>
</#if>










