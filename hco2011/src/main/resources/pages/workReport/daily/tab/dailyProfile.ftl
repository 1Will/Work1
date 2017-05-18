<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: dailyTAbProfile.ftl 2010-02-25 zsz $ -->
<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('daily.profile')}">
		<#--JS	辅助数据，不用传入后台-->
		<@ww.hidden name="'copyCurrentDate'"  value=""/>
		<#if daily.currentDate?exists>
			<input type="hidden" name="copyExistsDate" value="${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}" />
		</#if>
		<#if daily.backVisitContext?exists>
			<input type="hidden" name="copyBackVisitContext" value="${daily.backVisitContext?if_exists}" />
		</#if>
		<#if daily.weekDate?exists>
			<input type="hidden" name="copyWeek" value="${daily.weekDate?if_exists}" />
		</#if>
	<@ww.form action="'saveDaily'" namespace="'/workReport'" method="'post'">
		<@ww.token name="'saveDaily'"/>
		<@ww.hidden name="'daily.id'" value="${req.getParameter('daily.id')?if_exists}"/>
		<@ww.hidden name="'weekly.id'" value="'${weeklyId?if_exists}'"/>
		<@ww.hidden name="'isSaved'" value=""/>
		 <#if popWindowFlag?exists>
		 	<@ww.hidden name="'popWindowFlag'"  value="${popWindowFlag}"/>
		 </#if>
		 <#if daily.rapporteur?exists>
		 	<@ww.hidden name="'personId'" value="${daily.rapporteur.id?if_exists}"/>
		 <#else>
		 	<@ww.hidden name="'personId'" value="${user.id?if_exists}"/>
		 </#if>
		 
		 <#if daily.inst?exists>
		 	<@ww.hidden name="'inst.id'" value="${daily.inst.id?if_exists}"/>
		 <#else>
		 	<@ww.hidden name="'inst.id'" value="${user.institustion.id?if_exists}"/>
		 </#if>
		 
		 <#if daily.dept?exists>
		 	<@ww.hidden name="'dept.id'" value="${daily.dept.id?if_exists}"/>
		 <#else>
  	  	 	<@ww.hidden name="'dept.id'" value="${user.department.id?if_exists}"/>
		 </#if>
		 
		 <#if daily.duty?exists>
		 	<@ww.hidden name="'duty.id'" value="${daily.duty.id?if_exists}"/>
		 <#else>
		 	<#if peronnelF.duty?exists>
			<@ww.hidden name="'duty.id'" value="${peronnelF.duty.id?if_exists}"/>
  		 	<#else>
  		 	<@ww.hidden name="'duty.id'" value=""/>
		 	</#if>
		 </#if>
		 <@ww.hidden name="'backVisitIds'" value=""/>
		 
		<@inputTable>
		  <tr>
		   <@datePickerRanger
			anothername="currentDate"
    		label="${action.getText('daily.currentDate')}"
           	name="daily.currentDate"
     		value="${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}" 
			cssClass="underline" 
			flag="true"
			required="true"/>
			<@ww.select label="'${action.getText('daily.week')}'" 
				name="'daily.week'"
				value="'${daily.weekDate?if_exists}'"
				list="{'','一','二','三','四','五','六','日'}"
				required="true"
				/>
		   <#include "time.ftl"/>
		  </tr>
		  <tr>
 			<#if daily.rapporteur?exists>
 				<@textfield label="${action.getText('daily.rapporteur')}" name="user.name"  value="${daily.rapporteur.name?if_exists}" required="true" cssClass="underline" disabled="true" />
 			<#else>
 				<@textfield label="${action.getText('daily.rapporteur')}" name="user.name"  value="${user.name?if_exists}" required="true" cssClass="underline" disabled="true" />
 			</#if>
 			
 			<#if daily.inst?exists>
 				<@textfield label="${action.getText('daily.inst')}" name="user.institustion.name" value="${daily.inst.name?if_exists}" required="true" cssClass="underline" disabled="true" />
 			<#else>
 				<@textfield label="${action.getText('daily.inst')}" name="user.institustion.name" value="${user.institustion.name?if_exists}" required="true" cssClass="underline" disabled="true" />
 			</#if>
 			
 			<#if daily.dept?exists>
 				<@textfield label="${action.getText('daily.dept')}" name="user.department.name" value="${daily.dept.name?if_exists}" required="true" cssClass="underline" disabled="true" />
 			<#else>
 				<@textfield label="${action.getText('daily.dept')}" name="user.department.name" value="${user.department.name?if_exists}" required="true" cssClass="underline" disabled="true" />
 			</#if>
 			</tr>
 			
 			<tr>
 			<#if daily.duty?exists>
 				<@textfield label="${action.getText('daily.duty')}" name="peronnelF.duty.name" value="${daily.duty.name?if_exists}" required="true" cssClass="underline" disabled="true" />
		  	<#else>
		  		<#if peronnelF.duty?exists>
 					<@textfield label="${action.getText('daily.duty')}" name="peronnelF.duty.name" value="${peronnelF.duty.name?if_exists}" required="true" cssClass="underline" disabled="true" />
  		 		<#else>
 					<@textfield label="${action.getText('daily.duty')}" name="peronnelF.duty.name" value="" required="true" cssClass="underline" disabled="true" />
		 		</#if>
		  	</#if>
		  	</tr>

		  <#-- 
		  <@textarea id="daily.backVisitContext" name="daily.backVisitContext" label="${action.getText('拜访工作内容')}" value="${daily.backVisitContext?if_exists}" cols="120" rows="4"/>
	     <script>
	     	getObjByName('daily.backVisitContext').readOnly="readOnly";
	     </script>
	   --> 
	   
	     <tr>
	    <td colspan="6" valign="top" >
	     <#if daily.rapporteur?exists>
	     <ul id="beautytab">
			<li>
				<a id="backvisit" class="selectedtab" onclick="activeTab(this);" href='${req.contextPath}/backvisit/listBackVisitByContact.html?employee=${daily.rapporteur.name?if_exists}&backVisitDate_start=${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}&backVisitDate_end=${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}' target="framee" >${action.getText('回访记录')}</a>
			</li>
		 </ul>
			<iframe id="framee" name="framee" frameborder="0" scrolling="auto" frameborder="0"  style="height:100%; width:100%;" src="${req.contextPath}/backvisit/listBackVisitByContact.html?employee=${daily.rapporteur.name?if_exists}&backVisitDate_start=${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}&backVisitDate_end=${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" ></iframe>
		</#if> 
	     </td>
	     </tr>
	     
	     
	      <tr>
		  <td align='right'><lable>新增客户数:</lable></td>
		  <td id='customerNum'></td>
		  <td align='right'><lable>新增联系人数:</lable></td>
		  <td id='contentNum'></td>
		  <td align='right'><lable>新增项目数:</lable></td>
		  <td id='projectNum'></td>
		  </tr>
		  <tr>
		  <@textarea id="daily.workContext" name="daily.workContext" anothername="workContext" label="${action.getText('daily.workContext')}" value="${daily.workContext?if_exists}" rows="6" cols="160"/>
	      <script language="JavaScript" type="text/JavaScript">
				getObjByName('daily.workContext').onclick=function(){
					var con = getObjByName('daily.workContext').value
					if(con=='请输入非客户拜访类的工作内容'){
						getObjByName('daily.workContext').value='';
						getObjByName('daily.workContext').style.color='000000';
					}
				};
				getObjByName('daily.workContext').onblur=function(){
					var con = getObjByName('daily.workContext').value
					if(con==''){
						getObjByName('daily.workContext').value='请输入非客户拜访类的工作内容';
						getObjByName('daily.workContext').style.color='999999';
					}
				};

		  </script>
	     </tr>
		 <tr>
		    <@textarea id="daily.questions" name="daily.questions" anothername="questions" label="${action.getText('收获/问题/建议')}" value="${daily.questions?if_exists}" rows="6" cols="160"/>
	     		<script>
	     				getObjByName('daily.questions').onclick=function(){
					var question = getObjByName('daily.questions').value
					if(question=='请输入今日收获、问题、建议'){
						getObjByName('daily.questions').value='';
						getObjByName('daily.questions').style.color='000000';
					}
				};
				getObjByName('daily.questions').onblur=function(){
					var question = getObjByName('daily.questions').value
					if(question==''){
						getObjByName('daily.questions').value='请输入今日收获、问题、建议';
						getObjByName('daily.questions').style.color='999999';
					}
				};
	     		</script>
	     </tr>
	  <#--  
	    <tr>
		   <@textarea id="daily.leaderIdea" name="daily.leaderIdea" anothername="leaderIdea" label="${action.getText('daily.leaderIdea')}" value="${daily.leaderIdea?if_exists}"/>
	    </tr>
	     <tr>
           <@textarea id="daily.solutions" name="daily.solutions" anothername="solutions" label="${action.getText('daily.solutions')}" value="${daily.solutions?if_exists}"/> 
         </tr>
       
	     <tr>
		   <@textarea id="daily.tomorrowPlan" name="daily.tomorrowPlan" anothername="tomorrowPlan" label="${action.getText('daily.tomorrowPlan')}" value=" ${daily.tomorrowPlan?if_exists}"/>
	    </tr>
	   
	    <tr>
		  <@textarea id="daily.comment" name="daily.comment" anothername="comment" label="${action.getText('daily.comment')}" value="${daily.comment?if_exists}"/>
	    </tr>
	    -->
		</@inputTable>
	<@buttonBar>
	    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return check();'"/>
	    
	    <#if daily.isSaved?exists &&daily.isSaved=='0' >
	    	<@vsubmit name="'submit'" value="'${action.getText('提交')}'" onclick="'return submitt();'"/>
	    <#else>
	    	<@vsubmit name="'submit'" value="'${action.getText('提交')}'" onclick="'return submitt();'" disabled="true"/>
	    </#if>
	    
	    <#-- 继续新建按钮   -->
			<#if daily.id?exists>
			<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/workReport/editDaily.html"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/workReport/editDaily.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
			</#if>
	    
	    <#if popWindowFlag?exists>
	   		<@vbutton value="${action.getText('close')}" class="button" onclick="javascript:closeThis();"/>
		<#else>
	    	<@redirectButton value="${action.getText('back')}" url="listDaily.html"/>
	    </#if>
	</@buttonBar>
	</@ww.form>
	<script type='text/javascript' src='${req.contextPath}/dwr/interface/DailyByDate.js'></script>
	<script type='text/javascript' src='${req.contextPath}/dwr/interface/AllByDateAndName.js'></script>
	<script>
	//定义全局变量
		var content =new Array();
		var stop =false;
		var alertflag =true;
		
	    function check(){
	     if(!dateCheck_currentDate()){
	     	getObjByName('daily.currentDate').focus();
	     	return false;
	     }
	     var a = getObjByName('daily.workContext').value;
		 var b = getObjByName('daily.questions').value
		 if(b=='请输入今日收获、问题、建议'){
			getObjByName('daily.questions').value='';
		 }
	     if(a=='请输入非客户拜访类的工作内容'){
	     	getObjByName('daily.workContext').value='';
	     }
	     getObjByName('isSaved').value="0";
	     return true;
	    }
	    
	    function submitt(){
	     if(!dateCheck_currentDate()){
	     	getObjByName('daily.currentDate').focus();
	    	return false;
	     }
	     var a = getObjByName('daily.workContext').value;
	     var b = getObjByName('daily.questions').value
		 if(b=='请输入今日收获、问题、建议'){
			getObjByName('daily.questions').value='';
		 }
	     if(a=='请输入非客户拜访类的工作内容'){
	     	getObjByName('daily.workContext').value='';
	     }
	     getObjByName('isSaved').value="1";
	     return true;
	    }
	    
	    document.onclick = function (){
	    	checkDate();
	    }
	    
	    function checkDate(){
			var mydate = getObjByName('daily.currentDate').value;
			var copydate = getObjByName('copyCurrentDate').value;
		    re = /^(\d{2,4})-(\d{1,2})-(\d{1,2})$/g;
		    if((mydate!="")&&(re.test(mydate)))   
		    { 
		    	if(isDateLessThenCurrent(mydate)){
					alert("日期超前，请重新选择！");
				<#if daily.rapporteur?exists>
					getObjByName('daily.currentDate').value = getObjByName('copyExistsDate').value;
					//getObjByName('daily.backVisitContext').value = getObjByName('copyBackVisitContext').value;
					getObjByName('daily.week').value = getObjByName('copyWeek').value;
					alertflag = false;
					return;
				<#else>
					getObjByName('daily.currentDate').value='';
					//getObjByName("daily.backVisitContext").value='';
					getObjByName('daily.week').value = '';
					return;
				</#if>
				}
		        year1   =   mydate.replace(re,"$1");   
		        month1  =   mydate.replace(re,"$2");   
		        day1    =   mydate.replace(re,"$3");
		        getObjByName('daily.week').value="日一二三四五六".charAt(new Date(year1+'/'+month1+'/'+day1).getDay()); 
		        <#-- 编辑页面 -->
		    <#if daily.rapporteur?exists>
		    	<#-- 判断是不是当事人操作 -->
			        if(mydate!= copydate){
			        	returnBackVisite(mydate) ;
			       		getObjByName("copyCurrentDate").value = mydate;
			        }
		    	<#if daily.rapporteur.name==user.name>
		        </#if>
		        <#-- 新建页面 -->
		    <#else>
		    	if(mydate!= copydate){
		        	returnBackVisite(mydate) ;
		       		getObjByName("copyCurrentDate").value = mydate;
		        }
		    </#if>
		    }  
		} 
		function returnBackVisite(mydate){
		<#if daily.rapporteur?exists>
			var per =${daily.rapporteur.id?if_exists};
		<#else>
			var per =${user.id?if_exists};
		</#if>
			<!-- 通过日期在数据库中查询数据，异步方式 -->
			DWREngine.setAsync(false);
			<!--判断当日有没有创建日报  -->
			DailyByDate.loadDailyByDate(mydate,setDaily);
			if(stop){
				return;
			}
			<!-- 返回值顺序为 backVisitList、customerInfoList、contactArchivesList、projectList-->
			AllByDateAndName.loadDailyNeed(per,mydate,setAll);
			DWREngine.setAsync(true); 
			<!--为文本框添加后台查询内容-->
			<#--
			var doContent="";
			for(var i=0;i<content.length;i++){
				if(isIE()){
				doContent+=(i+1)+"、"+content[i]+"\r\n";
				}else{
				doContent+=(i+1)+"、"+content[i]+"\n";
				}
			}
			var existsContent =getObjByName("daily.backVisitContext").value;
			if(existsContent!=doContent){
				<#-- 编辑页面 --
				<#if daily.rapporteur?exists>
				if(getObjByName("copyExistsDate").value==getObjByName('daily.currentDate').value){
					if(alertflag){
						alert("工作内容已发生变化，请重新保存");
					}
				}
				</#if>
				getObjByName("daily.backVisitContext").value=doContent;
			}
			content.length=0;
			-->
		}
		function setAll(data){
			if(data[0].length!=0){
				setBackVisit(data[0][0]);
			}
			setCustomer(data[1]);
			setContactArchives(data[2]);
			setProject(data[3]);
		}
		<!--处理判断当日有没有创建日报 -->
		function setDaily(data){
			if(data>0){
			<#if daily.rapporteur?exists>
				if(getObjByName("copyExistsDate").value!=getObjByName('daily.currentDate').value){
					alert('该日报已存在，请重新选择日期！');
					//getObjByName("save").disabled='true';
					getObjByName('daily.currentDate').value = getObjByName('copyExistsDate').value;
					//getObjByName('daily.backVisitContext').value = getObjByName('copyBackVisitContext').value;
					getObjByName('daily.week').value = getObjByName('copyWeek').value;
					alertflag=false;
					stop = true;
				}else{
					//getObjByName("save").removeAttribute('disabled');
					stop = false;
				}
			<#else>
			alert('该日报已存在，请重新选择日期！');
			//getObjByName("save").disabled='true';
			//getObjByName("daily.backVisitContext").value='';
			getObjByName('daily.currentDate').value='';
			getObjByName('daily.week').value = '';
			stop = true;
			</#if>
			}else{
				//getObjByName("save").removeAttribute('disabled');
				stop = false;
			}
		}
		
		<!--处理回访记录-->
		function setBackVisit(data){
			var ids='';
			for(var i=0;i<data.length;i++){
				if(ids==''){
					ids +=data[i].id;
				}else{
					ids += ("-" + data[i].id);
				}
			<#--
				if(data[i].projectName!=null&&data[i].projectName!='undefined'){
					//content[i]="客户名称："+data[i].customerName+"； 联系人："+data[i].caName+"； 项目名称："+data[i].projectName+"； 回访内容："+data[i].backVisitContent+"；";
				
				}else{
					//content[i]="客户名称："+data[i].customerName+"； 联系人："+data[i].caName+"； 项目名称："+" "+"； 回访内容："+data[i].backVisitContent+"；";
				
				}
			-->
			}
			getObjByName("backVisitIds").value=ids;
		}
		
		<!--处理创建客户数量-->
		function setCustomer(data){
			//content[content.length]="新增客户数："+data+"个；";
			//getObjByName("customerNum").value=data;
			var htm="<a href='javascript:customer_OpenDialog()'>"+data+"</a><laber>个</laber>";
			getObjByName("customerNum").innerHTML=htm;
		}
		
		<!--处理创建联系人数量-->
		function setContactArchives(data){
			//content[content.length]="新增联系人数："+data+"个。";
			//getObjByName("contentNum").value=data;
			var htm="<a href='javascript:contact_OpenDialog()'>"+data+"</a><laber>个</laber>";
			getObjByName("contentNum").innerHTML= htm;
		}
		
		<!--处理创建项目数量-->
		function setProject(data){
			var htm="<a href='javascript:project_OpenDialog()'>"+data+"</a><laber>个</laber>";
			getObjByName("projectNum").innerHTML= htm;
		}
		
		<!-- 联系人弹窗 -->
		function contact_OpenDialog(){
		<#if daily.rapporteur?exists>
			var url="${req.contextPath}/customerRelationship/listContactArchives.html?contactArchives.creator=${daily.rapporteur.name?if_exists}&contactArchives.createdTime=${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}"
		<#else>
			var date =getObjByName('daily.currentDate').value;
			var url="${req.contextPath}/customerRelationship/listContactArchives.html?contactArchives.creator=${user.name?if_exists}&contactArchives.createdTime="+date;
		</#if>	
			openNewWindow(url);
		}
		<!-- 客户档案弹窗 -->
		function customer_OpenDialog(){
		<#if daily.rapporteur?exists>
			var url="${req.contextPath}/customerRelationship/listCustomerInfo.html?customerInfo.salesman=${daily.rapporteur.name?if_exists}&customerInfo.createdTime=${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}";
		<#else>
			var date =getObjByName('daily.currentDate').value;
			var url="${req.contextPath}/customerRelationship/listCustomerInfo.html?customerInfo.salesman=${user.name?if_exists}&customerInfo.createdTime="+date;
		</#if>		
			openNewWindow(url);
		}
		
		<!-- 项目弹窗 -->
		function project_OpenDialog(){
		<#if daily.rapporteur?exists>
			var url="${req.contextPath}/projectInfo/listProjectInfo.html?projectInfo.creator=${daily.rapporteur.name?if_exists}&projectInfo.createdTime=${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}";
		<#else>
			var date =getObjByName('daily.currentDate').value;
			var url="${req.contextPath}/projectInfo/listProjectInfo.html?projectInfo.creator=${user.name?if_exists}&projectInfo.createdTime="+date;
		</#if>		
			openNewWindow(url);
		}
		
		
	    window.onload=function (){
	    	var con = getObjByName('daily.workContext').value
			var question = getObjByName('daily.questions').value
				if(con==''){
					getObjByName('daily.workContext').value='请输入非客户拜访类的工作内容';
					getObjByName('daily.workContext').style.color='999999';
				}
				if(question==''){
					getObjByName('daily.questions').value='请输入今日收获、问题、建议';
					getObjByName('daily.questions').style.color='999999';
				}
			checkDate();
	    }
	    
	</script>
<#if daily.rapporteur?exists>
<ul id="beautytab">
	<li>
		<a id="replyDaily" class="selectedtab" onclick="activeTab(this);" href='${req.contextPath}/workReport/listReplyDailyTab.html?daily.id=#{daily.id?if_exists}' target="msgframe" >${action.getText('消息回复')}</a>
	</li>
	<#-- 
	<li>
		<a id="backvisit" class="selectedtab" onclick="activeTab(this);" href='${req.contextPath}/backvisit/listBackVisitByContact.html?employee=${daily.rapporteur.name?if_exists}&backVisitDate_start=${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}&backVisitDate_end=${(daily.currentDate?string('yyyy-MM-dd'))?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}' target="msgframe" >${action.getText('回访记录')}</a>
	</li>
	-->
</ul>
<iframe name="msgframe" frameborder="0.5" src="${req.contextPath}/workReport/listReplyDailyTab.html?daily.id=#{daily.id?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="65%"/>
</#if>

</@htmlPage>