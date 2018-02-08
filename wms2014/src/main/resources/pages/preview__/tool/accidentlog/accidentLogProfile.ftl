<#include "../../includes/macros2.ftl" />
<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="事故记录维护">

<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='listAccidentLogHistories.html';
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
	  		//document.forms["accident"].elements["manager.id"].value  = result[0];
	  		document.forms["accident"].elements["manager.name"].value = result[1];
	  	}
	
</script>

	 <@ww.form name="'accident'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  	  <tr>
	 	  	  	<@ww.textfield label="'事故编号'" name="'area.code'" value="'0001'" cssClass="'underline'" disabled="true" readonly="true" />
	 	  	  	<@ww.textfield label="'事故名称'" name="'area.code'" value="" cssClass="'underline'" required="true" />
	 	  	  </tr>
		 	  <@toolingSelector/>
		 	  <tr>
		 	  	<@pp.datePicker label="'事故发生日期'" name="'err.date'"
	     				value="" cssClass="'underline'" size="15" required="true"/>
	     		<@ww.select label="'使用部门'"
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
            	<td align="right" valign="top"><span class="required">*</span><label class="label">负责人:</label></td>
            	<td>
		 			<input type="text" name="manager.name" class="underline"  value=""  maxlength="150" disabled="false"/>
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
            </tr>
		 	  <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
		 	  <tr>
		 	  	<@ww.textarea label="'事故详细'" 
					         name="'cause'" 
					         value="" rows="'3'" cols="'50'" 
					         required="true"
							 />
				<@ww.textarea label="'解决方案'" 
					         name="'cause'" 
					         value="" rows="'3'" cols="'50'" 
							 />
		 	  </tr>	 	 
		 	  <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
	           	<tr>
		    		<@eam2008_RecordLog creator="sa" createdTime="2007-01-01" 
			 			   	  lastOperator="sa" lastModifiedTime="2007-01-01"/>
		 	  	</tr> 
	 	  	
        </@inputTable>
	 	 
	 	  <@buttonBar>
	        	<@vbutton class="button" value="${action.getText('save')}" onclick="javascript:void(0);"/>
	        	<@redirectButton  value="返回" url="listAccidentLogs.html"/>
	     </@buttonBar>
	</@ww.form>
	
	<ul id="beautytab">
		<li><a id="planItems" onclick="activeTab(this);" href="listAccidentLogHistories.html" target="frame" class="selectedtab">事故历史记录</a></li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>