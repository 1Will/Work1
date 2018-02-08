<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="点检标准维护">
	<@ww.form  namespace="'/runmaintenance/checkPoint'" name="'rule'" action="''" method="'post'">
		 <@ww.token name="saveRuleToken"/>
		 <@inputTable> 
		 	<tr>
               <@ww.textfield label="'点检标准编号'" name="'ruleNo'" value="'${req.getParameter('ruleNo')?if_exists}'" cssClass="'underline'"/>
               <@ww.textfield label="'点检标准名称'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
            </tr>
            <tr>
    	       <@ww.select label="'分类'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'工装', 
	                    			'设备',
	                    			'其它'
	                    	  	  }"
	                    	value="selectedDevice"
	           />
	           <@ww.select label="'部门'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有',
	                    			'机动部', 
	                    			'生产部'
	                    	  	  }"
	                    	value="selectedDevice"
	           />
	           <@ww.checkbox label="'默认为点检标准'"  />
            </tr> 
		 </@inputTable>	 
		 <@buttonBar>
		 	<@vsubmit value="'${action.getText('save')}'" />
        	<@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/tooling/checkpoint/listCheckPointRule.html"/>	
        </@buttonBar>
	</@ww.form>
	<script language="JavaScript" type="text/JavaScript"> 
	  window.onload = function () {
			var url = '${req.contextPath}/runmaintenance/tooling/checkpoint/listCheckPointRuleDetail.html';
	 		document.all.frame.src= url;
	 		getObjByNameRe("ruleItems").className = "selectedtab";
	 	}
   	</script>
		<ul id="beautytab">
			<li><a id="ruleItems" href="listCheckPointRuleDetail.html" target="frame" class="selectedtab">点检项目列表</a></li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>

</@htmlPage>