<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('devicesealed.Query')}">
	 <@ww.form name="'alteration'" action="'searchDeviceSealeds'" method="'post'">
	   <@ww.token name="searchDeviceSealedsToken"/>
	 	 <#include "deviceSearcher.ftl"/>
        <@buttonBar>
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return validateQuery()'" />
        	<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/runmaintenance/alteration/editDeviceSealed.html?sealedFlag="+"T" />
        </@buttonBar>
         <@list title="${action.getText('deviceSealed.list')}" 
         		includeParameters="sealedBillNo|sealedBillName|unSealBillNo|unSealBillName|asset.deviceNo|asset.name|asset.graphNo|sealedPeople|sealedDateTm_start|sealedDateTm_end|unSealPeople|unSealDateTm_start|unSealDateTm_end" 
         		fieldMap="like:sealedBillNo|sealedBillName|unSealBillNo|unSealBillName|asset.deviceNo|asset.name|asset.graphNo|sealedPeople|unSealPeople,date:sealedDateTm_start|sealedDateTm_end|unSealPeople|unSealDateTm_start|unSealDateTm_end" >
            <@vlh.checkbox property="id" name="alterationIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('deviceSealed.sealedBillNo')}" property="sealedBillNo" sortable="asc">
                <a href="editDeviceSealed.html?alteration.id=#{object.id}&device.id=#{object.asset.id}&sealedFlag="+"T">${object.sealedBillNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceSealed.sealedBillName')}" property="sealedBillName" sortable="desc"/>
            <@vcolumn title="${action.getText('deviceSealed.asset.deviceNo')}" property="asset.deviceNo" />
            <@vcolumn title="${action.getText('deviceSealed.asset.name')}" property="asset.name" />
            <@vcolumn title="${action.getText('deviceSealed.asset.graphNo')}" property="asset.graphNo" />
            <@vcolumn title="${action.getText('deviceSealed.sealedPeople')}" property="sealedPeople"  />
            <@vcolumn title="${action.getText('deviceSealed.sealedDateTm')}" property="sealedDateTm" format="yyyy-MM-dd" />
            <@vcolumn title="${action.getText('deviceSealed.sealedComment')}" property="sealedComment" />
            <@vcolumn title="${action.getText('deviceUnSealed.unsealedPeople')}" property="unSealPeople"  />
            <@vcolumn title="${action.getText('deviceUnSealed.unsealedDateTm')}" property="unSealDateTm" format="yyyy-MM-dd" />
            <@vcolumn title="${action.getText('deviceUnSealed.unsealedComment')}" property="unSealComment" />
        </@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('deviceSealed')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"alterationIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
     </@ww.form>
     <script>
       function validateSealedDate(){
           if(isNotEmpty(alteration,"sealedDateTm_start")==true){
              if(isDate("sealedDateTm_start")==false){
                  alert("${action.getText('deviceSealed.startTime')}");
	              return false;
              }
           }
           if(isNotEmpty(alteration,"sealedDateTm_end")==true){
              if(isDate("sealedDateTm_end")==false){
                  alert("${action.getText('deviceSealed.endTime')}");
	              return false;
              }
           }
           return true;
       }
       
       function validateUnSealedDate(){
           if(isNotEmpty(alteration,"unSealDateTm_start")==true){
              if(isDate("unSealDateTm_start")==false){
                  alert("${action.getText('deviceUnSealed.unstartTime')}");
	              return false;
              }
           }
           if(isNotEmpty(alteration,"unSealDateTm_end")==true){
              if(isDate("unSealDateTm_end")==false){
                  alert("${action.getText('deviceUnSealed.unendTime')}");
	              return false;
              }
           }
           return true;
       }
       
       function validateQuery(){
          if(!validateSealedDate()){
             return false;
          }
          if(!validateUnSealedDate()){
             return false;
          }
       }
     
     </script>
</@htmlPage>