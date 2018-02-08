<#--$Id: toolingSealedSelector.ftl 11002 2008-02-18 03:33:05Z wzou $ -->
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingsealedSelector.Query')}">
 <base target="_self">
	 <@ww.form name="'alteration'" action="'searchSelectToolingSealed'" method="'post'">
	   <@ww.token name="searchSelectToolingSealedToken"/>
	    <@ww.hidden name="'toolingSealed'" value="'1'"/>
	     <@ww.hidden name="'toolingSealedStaus'" value="'1'"/>
	 	<@inputTable>
	 		 <tr>
	        	<@ww.textfield label="'${action.getText('toolingsealed.sealedBillNo')}'" name="'sealedBillNo'" value="'${req.getParameter('sealedBillNo')?if_exists}'" cssClass="'underline'"  />
	        	<@ww.textfield label="'${action.getText('toolingsealed.sealedBillName')}'" name="'sealedBillName'" value="'${req.getParameter('sealedBillName')?if_exists}'" cssClass="'underline'"  />
	        </tr>
	 		<tr>
			 	<@ww.textfield label="'${action.getText('toolingsealed.asset.deviceNo')}'" name="'asset.deviceNo'" value="'${req.getParameter('asset.deviceNo')?if_exists}'" cssClass="'underline'"  />
			 	<@ww.textfield label="'${action.getText('toolingsealed.asset.name')}'" name="'asset.name'" value="'${req.getParameter('asset.name')?if_exists}'" cssClass="'underline'"  />
			 	<@ww.textfield label="'${action.getText('toolingsealed.asset.graphNo')}'" name="'asset.graphNo'" value="'${req.getParameter('asset.graphNo')?if_exists}'" cssClass="'underline'"  />
	        </tr>
	        <tr>
	        	<@ww.textfield label="'${action.getText('toolingsealed.sealedPeople')}'" name="'sealedPeople'" value="'${req.getParameter('sealedPeople')?if_exists}'" cssClass="'underline'"  />
	        	<@pp.dateRanger label="'${action.getText('toolingsealed.sealedDateTm')}'" name="'sealedDateTm'" 
		       value="'${req.getParameter('sealedDateTm_start')?if_exists}|${req.getParameter('sealedDateTm_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 	        	
	        </tr>
        </@inputTable> 
        <@buttonBar>
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return validateQuery()'" />
        </@buttonBar>
         <@list title="${action.getText('toolingsealed.list')}" 
         		includeParameters="sealedBillNo|sealedBillName|asset.deviceNo|asset.name|asset.graphNo|sealedPeople|sealedDateTm_start|sealedDateTm_end|toolingSealed" 
         		fieldMap="like:sealedBillNo|sealedBillName|asset.deviceNo|asset.name|asset.graphNo|sealedPeople|toolingSealed,date:sealedDateTm_start|sealedDateTm_end" >
            <@vcolumn title="${action.getText('toolingsealed.sealedBillNo')}" property="sealedBillNo" sortable="asc">
                <a href="javascript: returnDialog(new Array(#{object.id},'${object.sealedBillNo}','${object.sealedPeople}','${object.sealedDateTm?string('yyyy-MM-dd')}','${object.asset.deviceNo}','${object.asset.name}','${object.asset.graphNo}'));">${object.sealedBillNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingsealed.sealedBillName')}" property="sealedBillName" sortable="desc"/>
            <@vcolumn title="${action.getText('toolingsealed.asset.deviceNo')}" property="asset.deviceNo" />
            <@vcolumn title="${action.getText('toolingsealed.asset.name')}" property="asset.name" />
            <@vcolumn title="${action.getText('toolingsealed.asset.graphNo')}" property="asset.graphNo" />
            <@vcolumn title="${action.getText('toolingsealed.sealedPeople')}" property="sealedPeople"  />
            <@vcolumn title="${action.getText('toolingsealed.sealedDateTm')}" property="sealedDateTm" format="yyyy-MM-dd" />
            <@vcolumn title="${action.getText('toolingsealed.sealedComment')}" property="sealedComment" />
            <@vcolumn title="${action.getText('toolingsealed.sealedStatus')}" property="sealedStatus"  >
                <#if object.sealedStatus=="Noaudit">
                  ${action.getText('toolingsealed.noAudit')}
                <#else>
                  ${action.getText('toolingsealed.audit')}
                </#if>
            </@vcolumn>
        </@list>
     </@ww.form>
     <script>
       function validateDate(){
           if(isNotEmpty(alteration,"sealedDateTm_start")==true){
              if(isDate("sealedDateTm_start")==false){
                  alert("${action.getText('toolingsealed.startTime')}");
	              return false;
              }
           }
           if(isNotEmpty(alteration,"sealedDateTm_end")==true){
              if(isDate("sealedDateTm_end")==false){
                  alert("${action.getText('toolingsealed.endTime')}");
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