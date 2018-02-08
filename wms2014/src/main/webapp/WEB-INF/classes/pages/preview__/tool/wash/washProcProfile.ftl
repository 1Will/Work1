<#include "../../includes/macros2.ftl" />
<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="清洗实施维护">

<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='listWashProcItems.html';
 		document.getElementById("planItems").className = "selectedtab";
 	}
 	
	function activeTab(obj) {
	    var sfEls = document.getElementById("beautytab").getElementsByTagName("li");
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
	  		//document.forms["accident"].elements["manager.id"].value  = result[0];
	  		document.forms["accident"].elements["manager.name"].value = result[1];
	  	}
	
</script>

	 <@ww.form name="'accident'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  	  <tr>
	 	  	  	<@ww.textfield label="'清洗计划编号'" name="'area.code'" value="'0001'" cssClass="'underline'" disabled="true" readonly="true" />
	 	  	  	<@ww.textfield label="'清洗计划名称'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  	<@pp.datePicker label="'计划执行日期'" name="'err.date'"
	     				value="" cssClass="'underline'" size="15" required="true"/>	     		
		 	  </tr>	
		 	  <tr>
		 	  	<@ww.textfield label="'编制人'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  	<@pp.datePicker label="'编制日期'" name="'err.date'"
	     				value="" cssClass="'underline'" size="15" required="true"/>
	     		<@ww.select label="'部门'"
	                    	name="area.code"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'所有', '机动部','焊冲一厂','发动机部','技术部','三现办','综合部','总装','塑料件','涂装'	                    	  	  
	                    	}"
	                    	value="selectedPeople"
	                    	required="false"
	        		/>
		 	  </tr>	
		 	  <tr>
		 	  	<@ww.textfield label="'实施人'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  	<@pp.datePicker label="'实施日期'" name="'err.date'"
	     				value="" cssClass="'underline'" size="15" required="true"/>	     		
		 	  </tr>		 	  
        </@inputTable>	
          <@buttonBar>
	        	<@vbutton class="button" value="${action.getText('save')}" onclick="javascript:void(0);"/>
	        	<@redirectButton  value="返回" url="listWashProcs.html"/>
	     </@buttonBar> 	 
	</@ww.form>
	
	<ul id="beautytab">
		<li><a id="planItems" onclick="activeTab(this);" href="listWashProcItems.html" target="frame" class="selectedtab">清洗明细</a></li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>