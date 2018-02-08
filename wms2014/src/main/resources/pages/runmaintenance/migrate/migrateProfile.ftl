<#include "../../includes/eam2008.ftl" />
<#include "../../asset/commonDepartmentProduction.ftl" />
<#assign migrateEditTitle = ''/>
<#if toolingDevFlag?exists>
  <#if toolingDevFlag == 'DEVICE'>
    <#assign migrateEditTitle = "${action.getText('deviceMigrate.edit')}"/>
  <#else>
   <#assign migrateEditTitle = "${action.getText('toolingMigrate.edit')}"/>
  </#if>
</#if>
<@htmlPage title="${migrateEditTitle}">
  <@ww.form namespace="'/runmaintenance/migrate'" name="'migrate'" action="'saveMigrate'" method="'post'">
    <@ww.token name="saveMigrateToken"/>
      <@inputTable>
        <#if migrate.id?exists>
          <@ww.hidden name="'migrate.id'" value="${migrate.id?if_exists}"/>
        </#if>
        <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
         <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
            <tr>
              <@ww.textfield label="'${action.getText('migrate.billNO')}'" name="'migrate.billNO'"   value="'${migrate.billNo?if_exists}'" cssClass="'underline'"  disabled="true" readonly="true"/>

              <@ww.textfield label="'${action.getText('migrate.billname')}'" name="'migrate.billName'"   value="'${migrate.billName?if_exists}'" cssClass="'underline'"  required="true"/>
            </tr>
            <tr>
               <tr> 
             <@ww.select label="'${action.getText('original.department')}'" 
                  required="false" name="'oldDepartment.id'" 
		    	  value="'${req.getParameter('oldDepartment.id')?if_exists}'" 
		    	  listKey="id" listValue="name"
		    	  onclick="'departmentValueChange(\"oldDepartment.id\",\"oldProductionLine.id\")'"
		          list="oldDepartments" emptyOption="true" disabled="false">
		      </@ww.select>
		          <@ww.select label="'${action.getText('now.department')}'"
		           required="false" name="'newDepartment.id'" 
		    	   value="'${req.getParameter('newDepartment.id')?if_exists}'"
		    	   listKey="id" listValue="name"
		    	   onclick="'departmentValueChange(\"newDepartment.id\",\"newProductionLine.id\")'"
		           list="newDepartments" emptyOption="true" disabled="false">
		          </@ww.select>  
            </tr>
            <tr>
                <@ww.select label="'${action.getText('original.productline')}'" required="false" name="'oldProductionLine.id'" 
		    		 value="'${req.getParameter('oldProductionLine.id')?if_exists}'" listKey="id" listValue="name"
		           list="oldProductionLines" emptyOption="true" disabled="false" >
		          </@ww.select>  
		           <@ww.select label="'${action.getText('now.productline')}'" required="false" name="'newProductionLine.id'" 
		    		 value="'${req.getParameter('newProductionLine.id')?if_exists}'" listKey="id" listValue="name"
		           list="newProductionLines" emptyOption="true" disabled="false">
		          </@ww.select>
            </tr>
            <tr>
                 <@ww.select label="'${action.getText('migrate.requestdepartment')}'" required="false" name="'requestDepartment.id'" 
		    		 value="'${req.getParameter('requestDepartment.id')?if_exists}'" listKey="id" listValue="name"
		           list="requestDepartments" emptyOption="true" disabled="false">
		          </@ww.select> 
		          <#assign migraterequestUserName = ''/>
					<#if migrate.requester?exists>
					 <#assign migraterequestUserName = "${migrate.requester.name}" />
			         <#elseif loginUser?exists>
			        <#assign migraterequestUserName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('migrate.requestpeople')}:</label></td>
	        	<td>
	        		<input type="text" name="requester.name" 
	        			class="underline"  value="${migraterequestUserName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		    <a onClick="migrateUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		        <#assign migraterequestUserId = ''/>
				<#if migrate.requester?exists>
				 <#assign migraterequestUserId = "${migrate.requester.id}" />
				  <#elseif loginUser?exists>
				  <#assign migraterequestUserId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="requester.id" value="${migraterequestUserId}" />
            </tr>
        <tr>
		  <@pp.datePicker label="'${action.getText('requestDate')}'" name="'migrate.requestDate'"
	     				  value="'${(migrate.requestDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				  required="true" maxlength="10"/>
        </tr>
        <tr>
          <@ww.textarea  label="'${action.getText('migrate.reason')}'" 
	        	         name="'migrate.migrateReason'" 
	        	         value="'${migrate.migrateReason?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'"/>
        </tr>
      </@inputTable>
      <@buttonBar>
      <#if !(action.isReadOnly())>
	    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick= "'return migrate_Vlidate();'"/>
	  </#if>
	    <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/migrate/listMigrate.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	  	<#if migrate.id?exists>
  	          <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_migratePdf('#{migrate.id}')"/>
  	          <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_migrateXls('#{migrate.id}')"/>
          </#if>
	  </@buttonBar>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript">
    window.onload = function() {
      <#if migrate.id?exists>
		   var url = 'listMigrateDetail.html?migrate.id=${migrate.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	          document.all.frame.src = url;
		      getObjByNameRe("repairItem").className = "selectedtab";
	    </#if>
         a = new Date();
		 var time=a.format("yyyy-MM-dd");
		 document.forms[0].elements["migrate.requestDate"].value=time;
        <#if migrate.oldDepartment?exists>
         getObjByNameRe("oldDepartment.id").value = #{migrate.oldDepartment.id};
        </#if>
        <#if loginUser.department?exists>
         getObjByNameRe("requestDepartment.id").value = #{loginUser.department.id};
        </#if>
        <#if migrate.newDepartment?exists>
         getObjByNameRe("newDepartment.id").value = #{migrate.newDepartment.id};
        </#if>
        <#if migrate.oldProductionLine?exists>
         getObjByNameRe("oldProductionLine.id").value = #{migrate.oldProductionLine.id};
        </#if>
        <#if migrate.newProductionLine?exists>
         getObjByNameRe("newProductionLine.id").value = #{migrate.newProductionLine.id};
        </#if>
        <#if migrate.requestDepartment?exists>
         getObjByNameRe("requestDepartment.id").value = #{migrate.requestDepartment.id};
        </#if>
        /**
        *  选择原生产部门之后 原生产线列表中只显示属于此生产部门的成产线 
        */
        toSortProductionLineByDepartment();	
	       if (-1 == document.forms["0"].elements["oldDepartment.id"].value) {
	          document.forms["0"].elements["oldProductionLine.id"].length=1;
	          
	          } else {
	     departmentValueChange("oldDepartment.id", "oldProductionLine.id");
	       <#if migrate.oldProductionLine?exists>
	         document.forms["0"].elements["oldProductionLine.id"].value = #{migrate.oldProductionLine.id?if_exists};
	      </#if>
	      }
	      /**
        *  选择现生产部门之后 现生产线列表中只显示属于此生产部门的成产线 
        */
	       <#--toSortProductionLineByDepartment();	-->
	       if (-1 == document.forms["0"].elements["newDepartment.id"].value) {
	          document.forms["0"].elements["newProductionLine.id"].length=1;
	          
	          } else {
	     departmentValueChange("newDepartment.id", "newProductionLine.id");
	       <#if migrate.newProductionLine?exists>
	         document.forms["0"].elements["newProductionLine.id"].value = #{migrate.newProductionLine.id?if_exists};
	      </#if>
	  }
    }
        /*
       * 检查人选择
      */
        function migrateUser_OpenDialog() {
        var url = "${req.contextPath}/popup/userSelector.html";
    	popupModalDialog(url, 800, 600, migrateSelectorHandler);
      }
        function migrateSelectorHandler(result) {
           if (null != result) {
		      document.forms[0].elements["requester.name"].value=result[1];
		      document.forms[0].elements["requester.id"].value=result[0];
		    }      
      }
    
     function open_migrateXls(id) {
				var migrateId=id;
				<#if toolingDevFlag=='DEVICE'>
			          var url='${req.contextPath}/reports/device/deviceMigrate.xls?migrateId='+migrateId;
		        <#else>
			          var url='${req.contextPath}/reports/tooling/toolingMigrate.xls?migrateId='+migrateId;
		        </#if>
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}      	
	  function open_migratePdf(id) {
				var migrateId=id;
				<#if toolingDevFlag=='DEVICE'>
			          var url='${req.contextPath}/reports/device/deviceMigrate.pdf?migrateId='+migrateId;
		        <#else>
			          var url='${req.contextPath}/reports/tooling/toolingMigrate.pdf?migrateId='+migrateId;
		        </#if>
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}    	
      
     function migrate_Vlidate(){
       if(getObjByNameRe("migrate.billName").value==''){
	        alert('${action.getText('migrate.billName.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "migrate.billName", null, 50)){
				alert("${action.getText('migrate.billName.length')}");
				return  false;
			   }
	     }
      
      if(getObjByNameRe("migrate.requestDate").value==''){
	        alert('${action.getText('migrateDate.not.null')}');
	        return false;
	      }
	    var date=getObjByNameRe("migrate.requestDate").value;
		if(!isDate("migrate.requestDate")){
		    alert("${action.getText('select.right.migrateDate')}");
			return false;
		  }
		if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.migrateDate')}");
		    return false;
		  }
		if(getObjByNameRe("requester.name").value==''){
	        alert('${action.getText('requester.name.not.null')}');
	        return false;
	       }
	    if(getObjByNameRe("migrate.migrateReason").value!=''){
		if(!isValidLength(document.forms[0], "migrate.migrateReason", null, 250)){
				alert("${action.getText('migrate.migrateReason.length')}");
				return  false;
			   }
			}
	 if (document.forms[0].elements["requestDepartment.id"].value == '-1') {
       document.forms[0].elements["requestDepartment.id"].value = '';
    	}	
			return true;
      }
      
    </script>
     <#if migrate.id?exists>
  	  <ul id="beautytab">
	    	<li><a id="repairItem" onclick="activeTab(this);"  href="${req.contextPath}/runmaintenance/migrate/listMigrateDetail.html?migrate.id=${migrate.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('migrateDetailItem')}</a></li>
	    	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
    </#if>
</@htmlPage>