<#--$Id: checkPointPlanProfile.ftl 7923 2007-10-22 02:36:38Z qsun $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('checkPointReportProfile.title')}">
    <@ww.form name="'report'" action="'saveCheckPointReport'" method="'post'" validate="true">
    	<@ww.hidden name="'report.id'" value="'${req.getParameter('report.id')?if_exists}'"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
        <@ww.token name="saveCheckPointReportToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('report.no')}'"   name="'report.reportNo'" value="'${report.reportNo?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
                <@ww.textfield label="'${action.getText('report.name')}'" name="'report.name'"   value="'${report.name?if_exists}'" cssClass="'underline'" readonly="true" disabled="true"/>
            </tr>
            <tr>
            	<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    			 listKey="id" listValue="name"
		                list="departments" emptyOption="true" disabled="true">
		    	</@ww.select>
            	<@ww.textfield label="'${action.getText('report.month')}'" name="'report.month'" 
            	 		 value="'${(report.month)?if_exists}'" cssClass="'underline'" size="15"  
            	 		 disabled="true"/>
            </tr>
            <tr>
            	<#assign speakerName = ''/>
					<#if report.speaker?exists>
					  <#assign speakerName = "${report.speaker.name}" />
					<#elseif loginUser?exists>
					  <#assign speakerName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('report.speaker')}:</label></td>
	        	<td>
	        		<input type="text" name="speaker.name" 
	        			class="underline"  value="${speakerName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
	        	    <a onClick="speaker_OpenDialog();">
	        			<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	        		</a>
		        </td>
		        <#assign speakerId = ''/>
				<#if report.speaker?exists>
				 <#assign speakerId = "${report.speaker.id}" />
				<#elseif loginUser?exists>
				 <#assign speakerId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="speaker.id" value="${speakerId}" />
            	
            	
				<@ww.textfield label="'${action.getText('report.reportTime')}'" name="'report.reportTime'" 
            	 		 value="'${(report.reportTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"  disabled="true"/>			 
            
            </tr>
            <tr>	
            	<@ww.textarea label="'${action.getText('report.comment')}'" 
					         name="'report.comment'" 
					         value="'${report.comment?if_exists}'" rows="'3'" cols="'60'" 
							 />
            </tr>
            
        </@inputTable>
       	<@buttonBar>
       	  <#if !(action.isReadOnly())>
       		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
          </#if>
        	<@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/toolingDev/checkpoint/listCheckPointReport.html?readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>	
          <#if report.id?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_checkPointPdf('#{report.id}')"/>
	      	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_checkPointXls('#{report.id}')"/>
	      </#if>
        </@buttonBar>
    </@ww.form>
    <#if report.id?exists>
	<script language="JavaScript" type="text/JavaScript"> 
		window.onload = function () {
		    <#if report.department?exists>
	     		document.forms["report"].elements["department.id"].value=#{report.department.id?if_exists};
	     	</#if>
	     	<#if !(action.isReadOnly())>
	     	<#if report.submit>
				document.forms[0].elements["save"].disabled="true";
			</#if>
			</#if>
			var url = '${req.contextPath}/runmaintenance/toolingDev/checkpoint/listCheckPointReportDetail.html?report.id='+#{report.id}+"&readOnly=${req.getParameter('readOnly')?if_exists}";
	 		document.all.frame.src= url;
	 		getObjByNameRe("planItems").className = "selectedtab";
	 	}
	  function open_checkPointPdf(id){
       var reportid=id;
       var url='${req.contextPath}/reports/device/checkPointReport.pdf?reportid='+reportid;  
       url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
       }
      function open_checkPointXls(id){
       var reportid=id;
       var url='${req.contextPath}/reports/device/checkPointReport.xls?reportid='+reportid;  
       url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
      }
	 	function speaker_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, desigerSelectorHandler);
		  }
	  function desigerSelectorHandler(result) {
	    document.forms["report"].elements["speaker.id"].value = result[0];
	    document.forms["report"].elements["speaker.name"].value = result[1];
	  }
	  function validate(){
	  	var name =  document.forms["report"].elements["report.comment"].value;
	    if (!(name=="")){
	    	if(!isValidLength(document.forms[0], "report.comment", null, 250)){
				alert("${action.getText('report.comment.length')}");
				return false;
			}
	    }
	  	return true;
	  }
	</script>
	</#if>

	<#if report.id?exists>
		<ul id="beautytab">
			<li><a id="planItems" href="${req.contextPath}/runmaintenance/toolingDev/checkpoint/listCheckPointReportDetail.html?report.id=#{report.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" class="selectedtab">${action.getText('report.details')}</a></li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>