<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="工装点检标准维护">
<script language="JavaScript" type="text/JavaScript"> 

 	window.onload = function () {
	    		//var url = '../../preview/tool/editToolExtInfo.html';
				document.all.frame.src= '../../tool/checkpoint/listCheckPointRuleItems.html';
				getObjByNameRe("extInfo").className = "selectedtab";
				}
	function supplier_OpenDialog() {
			popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,400);
		}	
	function peopleMainManager_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/maintenanceManagerSelector.html',null, 300);
	}
	</script>
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
	        	<@ww.textfield label="'点检标准编号'" name="'area.code'" value=""  required="true" cssClass="'underline'" />
	        	<@ww.textfield label="'点检标准名称'" name="'area.code'" value=""  required="true" cssClass="'underline'" />
	        </tr>
	        <tr>
	        	<@ww.textarea label="'点检说明'" name="" value="'1.  1、2、3、4、5、内容必须在开机半小时内进行；2. 6内容在下班前填写;3. 检查方法可采用：试、看、听、摸等方法； 4. 操作工如发现设备有异常应及时记录,必要时填写报修单; 5. 正常，故障×修复'" rows="5" cols="60"/>
	        </tr>
			<@oneLine/>
  		</@inputTable>
         <@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/tool/checkpoint/listCheckPointRules.html"/>
        </@buttonBar>

    </@ww.form>
   <ul id="beautytab">
	  <li><a id="extInfo" onclick="activeTab(this);"  class="selectedtab" href="listCheckPointRuleItems.html" target="frame" >点检标准明细</a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>