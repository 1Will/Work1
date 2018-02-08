<#include "../../includes/macros2.ftl" />
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="工装变更维护">

<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='listChangeLogHistories.html';
 		getObjByNameRe("planItems").className = "selectedtab";
 	}
 	
	function activeTab(obj) {
	    var sfEls = getObjByNameRe("beautytab").getElementsByTagName("li");
	    for (var i=0; i<sfEls.length; i++) {
	    	//alert(sfEls[i].getElementsByTagName("a")[0].id == obj.id);
	      if (sfEls[i].getElementsByTagName("a")[0].id == obj.id) {
	        sfEls[i].getElementsByTagName("a")[0].className = "selectedtab";
	      } else {
	        sfEls[i].getElementsByTagName("a")[0].className = '';
	      }
	    }
	}
	function tooling_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/toolingSelector.html',600,350);
	}
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html', null, 300);
	}
	
	 function user_OpenDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler);
	  	}	
	function userSelectorHandler(result) {
	  		//document.forms["changeLog"].elements["manager.id"].value  = result[0];
	  		document.forms["changeLog"].elements["manager.name"].value = result[1];
	  	}
</script>

    <@ww.form namespace="'/base/area'" name="'changeLog'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'工装变更编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="false"/>
                <@ww.textfield label="'工装变更名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="true"/>
            </tr>
            <@toolingSelector/>
            <tr>
            	<@ww.textarea label="'修改原因'" 
					         name="'cause'" 
					         value="" rows="'3'" cols="'50'" 
							 />
				<@ww.textarea label="'修改方案'" 
					         name="'project'" 
					         value="" rows="'3'" cols="'50'" 
							 />
            </tr>
            <tr>
            	 <@ww.textfield label="'承接单位'" name="'department'" value="" cssClass="'underline'" readonly="true" required="false"/>
            </tr>
            <tr>
            	<@ww.textfield label="'承接人'"  name="'area.code'" value="" cssClass="'underline'" readonly="" required="false"/>
            	<@ww.textfield label="'委托人'"  name="'area.code'" value="" cssClass="'underline'" readonly="" required="false"/>
            </tr>
            <tr>
            	 <@pp.datePicker label="'计划完成时间'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15" required="true"/>
	     	</tr>   
	     	<tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
           	<tr>
	    		<@eam2008_RecordLog creator="sa" createdTime="2007-01-01" 
		 			   	  lastOperator="sa" lastModifiedTime="2007-01-01"/>
	 	  	</tr> 
	 	  	
        </@inputTable>
        
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="listChangeLogs.html"/>
        	<@redirectButton value="${action.getText('back')}" url="listChangeLogs.html"/>	
        </@buttonBar>
    </@ww.form>
    
    <ul id="beautytab">
		<li><a id="planItems" onclick="activeTab(this);" href="listChangeLogHistories.html" target="frame" class="selectedtab">变更历史记录</a></li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	
</@htmlPage>