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

<@framePage title="${action.getText('pivotalSpareList.title')}">
     <@ww.form name="'listPivotalspare'" action="'searchPivotalSpares'" method="'post'">
     <@ww.token name="searchPivotalSparesToken"/>
	 <#if runmaintainPlanDetail.id?exists>
	   <@ww.hidden name="'runmaintainPlanDetail.id'" value="'#{runmaintainPlanDetail.id?if_exists}'"/>
	 </#if>
     <@ww.hidden name="'addSpares'" value="''"/>
     <@ww.hidden name="'addSpareIds'" value="''"/>
     <@ww.hidden name="'saveSpareIds'" value="''"/>
     <@ww.hidden name="'allPivotalSpareIds'" value="''"/>
     <@ww.hidden name="'allUsedNums'" value="''"/>
     <@ww.hidden name="'allComments'" value="''"/>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 <#assign itemNo=1/>
	 <#assign loopNum=0/>
	 <#assign usedNumIdentify = 'usedNum' + '${loopNum}'/>
	 <@list title="" excel=false setupTable=false
		    includeParameters="runmaintainPlanDetail.id" 
        	fieldMap="like:" >
       <input type="hidden" name="spareIds" value="#{object.id}"/>
       <input type="hidden" name="hiddenSpareIds" value="#{object.spare.id}"/>
       <input type="hidden" name="hiddenSpareNo" value="${object.spare.spareNo}"/>
       <@vlh.checkbox property="id" name="pivotalSpareIds">
         <@vlh.attribute name="width" value="30" />
       </@vlh.checkbox>
	   <@vcolumn title="${action.getText('pivotalSpare.serialNo')}">
		   #{itemNo}
	   </@vcolumn>
	   <#assign itemNo = itemNo+1/>
		 <@vcolumn title="${action.getText('pivotalSpare.spareNo')}" property="spare.spareNo">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('pivotalSpare.chName')}" property="spare.name">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('pivotalSpare.enName')}" property="spare.enName">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('pivotalSpare.specific')}" property="spare.modelSpecs">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('pivotalSpare.category')}" property="spare.category.value">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('pivotalSpare.unitPrice')}" property="spare.unitPrice">
	       <@alignRight/>
         </@vcolumn>
         <@vcolumn title="${action.getText('pivotalSpare.usedNum')}">
           <input type="text" id="${usedNumIdentify}" name="pivotalSpare.usedNum" class="underline"  
                  value="${object.usedNum?if_exists}" maxlength="10"/>
         </@vcolumn>
         <#assign loopNum=loopNum+1/>
         <#assign usedNumIdentify = 'usedNum' + '${loopNum}'/>
	     <#assign planAllPrice=0/>
         <#if object.usedNum?exists&&object.spare.unitPrice?exists>
		   <#assign planAllPrice=object.spare.unitPrice*object.usedNum/>
		 </#if>
        <@vcolumn title="${action.getText('pivotalSpare.allPrice')}">
           #{planAllPrice}
           <@alignRight/>
        </@vcolumn>
        <@vcolumn title="${action.getText('pivotalSpare.comment')}">
           <input type="text" name="pivotalSpare.comment" class="underline"  
                  value="${object.comment?if_exists}" maxlength="250"/>
         </@vcolumn>
	 </@list>
     <#if !first>
       <@buttonBar>
       <#if !(action.isReadOnly())>
         <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="spare_openDialog();"/>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'">
             <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
           </@vsubmit>
           <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('pivotalSpare')}?" />
           <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
             <@ww.param name="'onclick'" value="'return confirmDeletes(\"pivotalSpareIds\", \"${confirmMessage}\");'"/>
             <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
           </@vsubmit>
           </#if>
         </@buttonBar>
      </#if>	    
    </@ww.form>
     <script language="javascript">
       window.onload = function () {
         <#--刷新总费用-->
         parent.document.forms["runmaintainPlanDetail"].elements["runmaintainPlanDetail.allFee"].value='${runmaintainPlanDetail.allFee?if_exists}';
         <#--刷新日常维修的值-->
	     parent.document.forms["runmaintainPlanDetail"].elements["runmaintainPlanDetail.pivotalSpareFee"].value='${runmaintainPlanDetail.pivotalSpareFee?if_exists}';
	   }
       /*
        * 触发弹出备件选择页面
       */
	   function spare_openDialog() {
	     var url = '${req.contextPath}/popup/spareSelector.html?toolingDevFlag=DEVICE';
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
      /*
       * 点击保存触发
      */
      function validate() {
        if (!validateUsedNumFormate()) {     //验证使用数量是否为数字格式
          return false;
        }
        retrievePivotalSpareIdText();           //获取列表中关键备件的ID
        retrieveUsedNumText();                  //获取列表中的使用数量的非空值
       if(!retrieveUsedNumText()){
          return false;
       }
        retrieveCommentText();                  //获取列表中的备注的非空值
        
      }
	      
      /*
       * 获取列表中关键备件的ID
      */
      function retrievePivotalSpareIdText() {
	    var allPivotalSpareIds = document.getElementsByName("pivotalSpareIds");
	    var ary = new Array();
	    for (var i=0; i<allPivotalSpareIds.length; i++) {
	      ary.push(allPivotalSpareIds[i].value);
	    }
	    document.forms[0].elements["allPivotalSpareIds"].value = ary;
      }
      /*
       * 验证使用数量的格式
      */ 
	function validateUsedNumFormate() {
         var allUsedNum = document.getElementsByName("pivotalSpare.usedNum");
         var allPivotalSpareIds = document.getElementsByName("pivotalSpareIds");
         var allHiddenSpareNo = document.getElementsByName("hiddenSpareNo")
         for (var i=0; i<allPivotalSpareIds.length; i++) {
           var usedNum = 'usedNum' + i;
           if ('' != allUsedNum[i].value) {
           var num= formatDigital(allUsedNum[i].value);
           if (!isDoubleNumberBetweenBoolean(num,1000000001, 0)) {
               alert("${action.getText('pivotalSpare.spareNo')} " + allHiddenSpareNo[i].value + " ${action.getText('usedNum.error')}");
               return false;
             }
           }
         }
		 return true;             
      }
      /*
       * 获取列表中的使用数量的非空值
      */
      function retrieveUsedNumText() {
         var allUsedNum = document.getElementsByName("pivotalSpare.usedNum");
         var allPivotalSpareIds = document.getElementsByName("pivotalSpareIds");
         var ary = new Array();
         for (var i=0; i<allPivotalSpareIds.length; i++) {
           if ('' != allUsedNum[i].value) {
                ary.push(allPivotalSpareIds[i].value);
                ary.push(formatDigital(allUsedNum[i].value));
           }
         }
         document.forms[0].elements["allUsedNums"].value = ary;
         return true;
      }
          
      /*
       * 获取列表中的备注的非空值
      */
      function retrieveCommentText() {
         var allComment = document.getElementsByName("pivotalSpare.comment");
         var allPivotalSpareIds = document.getElementsByName("pivotalSpareIds");
         var ary = new Array();
         for (var i=0; i<allPivotalSpareIds.length; i++) {
           if ('' != allComment[i].value) {
             ary.push(allPivotalSpareIds[i].value);
             ary.push(allComment[i].value);
           }
         }
         document.forms[0].elements["allComments"].value = ary;
      }
	</script>
</@framePage>