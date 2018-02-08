<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->
<#-- $Id: -->

<#include "../../../includes/eam2008.ftl" />

<@framePage title="${action.getText('sapre.title')}">
     <@ww.form name="'spare'" action="'searchRepairSpares'" method="'post'">
     <@ww.token name="searchRepairSparesToken"/>
     	<@ww.hidden name="'faultRepair.id'" value="'#{faultRepair.id?if_exists}'"/>
     	<@ww.hidden name="'addSpares'" value="''"/>
     	<@ww.hidden name="'addSpareIds'" value="''"/>
     	<@ww.hidden name="'saveSpareIds'" value="''"/>
     	<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
     	<@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
     	<@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	    <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false
		       includeParameters="preYearFlag|faultRepair.id" 
        	  fieldMap="like:" >
         <input type="hidden" name="spareIds" value="#{object.id}"/>
         <input type="hidden" name="hiddenSpareIds" value="#{object.spare.id}"/>
         <@vlh.checkbox property="id" name="spareItemIds">
	       <@vlh.attribute name="width" value="30" />
	     </@vlh.checkbox>
		 <@vcolumn title="${action.getText('repairItem.serialNo')}">
		   #{itemNo}
		 </@vcolumn>
		 <#assign itemNo = itemNo+1/>
		 <@vcolumn title="${action.getText('spare.spareNo')}" property="spare.spareNo">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('spare.chName')}" property="spare.name">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('spare.enName')}" property="spare.enName">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('spare.specific')}" property="spare.modelSpecs">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('spare.category')}" property="spare.category.value">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('spare.allStock')}" property="spare.stocks">
	       <@alignRight/>
         </@vcolumn>
         <@vcolumn title="${action.getText('spare.unitPrice')}" property="spare.unitPrice">
	       <@alignRight/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairSpare.procUsedNum')}">
           <input type="text" name="repairSpare.procUsedNum" class="underline"  
                  value="${object.procUsedNum?if_exists}" />
         </@vcolumn>
         <#assign procAllPrice=0/>
         <#if object.procUsedNum?exists&&object.spare.unitPrice?exists>
           <#assign procAllPrice=object.spare.unitPrice*object.procUsedNum/>
         </#if>
         <@vcolumn title="${action.getText('repairSpare.procAllPrice')}">
           #{procAllPrice}
           <@alignRight/>
         </@vcolumn>
		</@list>
		<#if !first>
		  <@buttonBar>
            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="spare_openDialog();"/>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('spare')}?" />
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitSpareIds()'">
        	  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        	</@vsubmit>
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
              <@ww.param name="'onclick'" value="'return confirmDeletes(\"spareItemIds\", \"${confirmMessage}\");'"/>
              <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
          </@buttonBar>
        </#if>	    
     </@ww.form>
     <script language="javascript">
	      function spare_openDialog() {
	        var url = '${req.contextPath}/popup/spareSelector.html?toolingDevFlag=${req.getParameter('toolingDevFlag')?if_exists}';
	        <#if !valueListNoRecords>
	          var oldSpareIds = document.getElementsByName("hiddenSpareIds");
	          var ary = new Array();
	          for (var i=0; i<oldSpareIds.length; i++) {
	            ary.push(oldSpareIds[i].value);
	          }
	          url = url + '&oldSpareIds=' + ary;
	        </#if>
	        popupModalDialog(url,800,600,addNewSpare); 
	      }
	      function addNewSpare(result) {
	       if (result != undefined) {
	         spareIds = result.substring(0, result.lastIndexOf(","));
	         document.forms[0].elements["addSpareIds"].value = spareIds;
	         document.forms[0].elements["addSpares"].value = "addSpares";
	         document.forms[0].submit();
	       }
	      }
	      function submitSpareIds(){
	         var spareIdSelector=document.getElementsByName("spareIds");
	         var usedNumSelector = document.getElementsByName("repairSpare.procUsedNum");
	         var spareIds="";
	         if (spareIdSelector.length) {
	             for (var i = 0; i < spareIdSelector.length; i++){
	               if(!validatePlanUsedNum(usedNumSelector[i].value)){
	                   return false;
	               }
	               spareIds += spareIdSelector[i].value+",";
	               spareIds += formatDigital(usedNumSelector[i].value)+",";
	                 
	             }
	         }
	         spareIds=spareIds.substring(0,spareIds.lastIndexOf(","));
	         document.getElementById("saveSpareIds").value=spareIds;
	      }
	      function validatePlanUsedNum(usedNumValue){
		      usedNumValue = formatDigital(usedNumValue);
		      var control = isNumberBetween(usedNumValue,10000000,0);
			  if(control!=1){
	              alert("${action.getText('select.right.procUsedNum')}");
	              return false;
	          }
			  return true;
		  }
	    </script>
</@framePage>