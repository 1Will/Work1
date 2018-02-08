<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingunsealed.Query')}">
	 <@ww.form name="'ToolingUnSeals'" action="'searchToolingUnSeals'" method="'post'">
	   <@ww.token name="searchToolingUnSealsToken"/>
	 	<@inputTable>
	 	 <@ww.hidden name="'untoolingSealed'" value="'1'"/>
	        <tr>
	        	<@ww.textfield label="'${action.getText('toolingunsealed.unsealedBillNo')}'" name="'unSealBillNo'" value="'${req.getParameter('unSealBillNo')?if_exists}'" cssClass="'underline'"  />
	        	<@ww.textfield label="'${action.getText('toolingunsealed.unsealedBillName')}'" name="'unSealBillName'" value="'${req.getParameter('unSealBillName')?if_exists}'" cssClass="'underline'"  />
	        </tr>
	        <tr>
			 	<@ww.textfield label="'${action.getText('toolingsealed.asset.deviceNo')}'" name="'asset.deviceNo'" value="'${req.getParameter('asset.deviceNo')?if_exists}'" cssClass="'underline'"  />
			 	<@ww.textfield label="'${action.getText('toolingsealed.asset.name')}'" name="'asset.name'" value="'${req.getParameter('asset.name')?if_exists}'" cssClass="'underline'"  />
			 	<@ww.textfield label="'${action.getText('toolingsealed.asset.graphNo')}'" name="'asset.graphNo'" value="'${req.getParameter('asset.graphNo')?if_exists}'" cssClass="'underline'"  />
	        </tr>
	        <tr>
	        	<@ww.textfield label="'${action.getText('toolingunsealed.unsealedPeople')}'" name="'unSealPeople'" value="'${req.getParameter('unSealPeople')?if_exists}'" cssClass="'underline'"  />
	        	<@pp.dateRanger label="'${action.getText('toolingunsealed.unsealedDateTm')}'" name="'unSealDateTm'" 
		       value="'${req.getParameter('unSealDateTm_start')?if_exists}|${req.getParameter('unSealDateTm_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 	
	        </tr>
        </@inputTable>
        <@buttonBar>
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return validateQuery()'" />
        	<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/alteration/editToolingUnSeal.html?sealedFlag="+"F"/>
        </@buttonBar>
         <@list title="${action.getText('toolingunsealed.list')}" 
         		includeParameters="unSealBillNo|unSealBillName|asset.deviceNo|asset.name|asset.graphNo|unSealPeople|unSealDateTm_start|unSealDateTm_end|untoolingSealed" 
         		fieldMap="like:unSealBillNo|unSealBillName|asset.deviceNo|asset.name|asset.graphNo|unSealPeople|untoolingSealed,date:unSealDateTm_start|unSealDateTm_end" >
            <@vlh.checkbox property="id" name="alterationIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('toolingunsealed.unsealedBillNo')}" property="unSealBillNo" sortable="asc">
                <a href="editToolingUnSeal.html?alteration.id=#{object.id}&sealedFlag="+"F">${object.unSealBillNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingunsealed.unsealedBillName')}" property="unSealBillName" sortable="desc"/>
            <@vcolumn title="${action.getText('toolingsealed.asset.deviceNo')}" property="asset.deviceNo" />
            <@vcolumn title="${action.getText('toolingsealed.asset.name')}" property="asset.name" />
            <@vcolumn title="${action.getText('toolingsealed.asset.graphNo')}" property="asset.graphNo" />
            <@vcolumn title="${action.getText('toolingunsealed.unsealedPeople')}" property="unSealPeople"  />
            <@vcolumn title="${action.getText('toolingunsealed.unsealedDateTm')}" property="unSealDateTm" format="yyyy-MM-dd" />
            <@vcolumn title="${action.getText('toolingunsealed.unsealedStatus')}" property="unSealStatus"  >
              <#if object.unSealStatus=="Noaudit">
                  ${action.getText('toolingunsealed.noAudit')}
                <#else>
                  ${action.getText('toolingunsealed.audit')}
                </#if>
           </@vcolumn>
        </@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('toolingsealed')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"alterationIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
     </@ww.form>
     <script>
        function validateDate(){
           if(isNotEmpty(ToolingUnSeals,"unSealDateTm_start")==true){
              if(isDate("unSealDateTm_start")==false){
                  alert("${action.getText('toolingunsealed.unstartTime')}");
	              return false;
              }
           }
           if(isNotEmpty(ToolingUnSeals,"unSealDateTm_end")==true){
              if(isDate("unSealDateTm_end")==false){
                  alert("${action.getText('toolingunsealed.unendTime')}");
	              return false;
              }
           }
           return true;
       }
       
       function validateQuery(){
          if(!validateDate()){
             return false;
          }
       }
     
     </script>
</@htmlPage>