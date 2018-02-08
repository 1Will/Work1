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

<#include "../../../includes/eam2008.ftl" />
<#--$Id: lubricationRuleItemList.ftl 11192 2008-03-04 01:46:57Z zbzhang $ -->


<@framePage title="${action.getText('sparePurchaseDetail.title')}">
  <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>  
  <@ww.form namespace="'/year/tooling/quarterPlan'" name="'listSparePurchaseDetails'" action="'searchSparePurchaseDetails'" method="'post'">
    <@ww.token name="searchSparePurchaseDetailsToken"/>
    <#if yearPlan?exists>
      <@ww.hidden name="'yearPlan.id'" value="'#{yearPlan.id?if_exists}'"/>
    </#if>
    <#if quarterPlan?exists>
      <@ww.hidden name="'quarterPlan.id'" value="'#{quarterPlan.id?if_exists}'"/>
    </#if>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <@ww.hidden name="'addSpares'" value="''"/>
    <@ww.hidden name="'addSpareIds'" value="''"/>
    <@ww.hidden name="'yearQuarterFlag'" value="'${yearQuarterFlag?if_exists}'"/>
    <@ww.hidden name="'yearSparePurchaseDetailIds'" value="''"/>
    <@ww.hidden name="'allNumber'" value="''"/>
    <@ww.hidden name="'allRequestDate'" value="''"/>
    <@ww.hidden name="'allSparePurchaseDetailId'" value="''"/>
    <@ww.hidden name="'allRequestReason'" value="''"/>
    <@ww.hidden name="'allComment'" value="''"/>
    <@ww.hidden name="'allUnitPrice'" value="''"/>
    <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
	<#assign itemNo=1/>
	<#assign requestDateIdentityName = 'requestDate' + '${itemNo}'/>
    <#assign requestDateDateImgIdentity = 'listSparePurchaseDetails_' + '${requestDateIdentityName}' + '_trigger'/>
    <@list title="" excel=false setupTable=false 
     	   includeParameters="yearPlan.id|quarterPlan.id"  fieldMap="like:" >
      <#if object.spare?exists>
        <input type="hidden" name="hiddenSpareIds" value="#{object.spare.id}"/>
      </#if>
 	  <@vlh.checkbox property="id" name="sparePurchaseDetailIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('serialNo')}">
       <#--
        <a href="#" onclick="open_detailDialog(#{quarterPlan.id}, #{object.id});return false;">#{itemNo}</a>
       -->
        #{itemNo}
        <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
      <@vcolumn title="${action.getText('graphNo')}" property="graphNo">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('name')}" property="name">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('category')}" property="categoryName">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('detailCategory')}" property="detailCategoryName">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('model')}" property="model">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('specification')}" property="specification">
        <@alignLeft />
      </@vcolumn>
         <#--
      <@vcolumn title="${action.getText('sparePurchaseDetail.unitPrice')}" property="unitPrice">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('sparePurchaseDetail.number')}" property="number">
        <@alignRight />
      </@vcolumn>
      -->
     <@vcolumn title="${action.getText('unit')}" property="calUnit.value" >
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('unitPrice')}">
	    <input type="text" name="sparePurchaseDetail.unitPrice" class="underline"  
	           value="${(object.unitPrice?string('#,###,##0.00'))?if_exists}" size="10" maxlength="10" style="text-align:right"/>
      </@vcolumn>
      <@vcolumn title="${action.getText('number')}">
	    <input type="text" name="sparePurchaseDetail.number" class="underline"  
	           value="${object.number?if_exists}" size="10" maxlength="10" style="text-align:right"/>
      </@vcolumn>
      <@vcolumn title="${action.getText('allPrice')}" property="allPrice" format="${action.getText('currencyFormat')}">
        <@alignRight />
      </@vcolumn>
      <#--
      <@vcolumn title="${action.getText('sparePurchaseDetail.requestDate')}" property="requestDate" format="yyyy-MM-dd">
        <@alignCenter />
      </@vcolumn>
      -->
      <@vcolumn title="${action.getText('requestDate')}">
	    <#assign requestDate = ''/>
		<#if object.requestDate?exists>
		  <#assign requestDate = "${(object.requestDate?string('yyyy-MM-dd'))}"/>
		</#if>
		<@eam2008_dataPicker inputName="${requestDateIdentityName}" inputId="${requestDateIdentityName}" inputValue="${requestDate}" imgId="${requestDateDateImgIdentity}" formName="listSparePurchaseDetails" />
	    <#assign requestDateIdentityName = 'requestDate' + '${itemNo}'/>
		<#assign requestDateDateImgIdentity = 'listSparePurchaseDetails_' + '${requestDateIdentityName}' + '_trigger'/>
	  </@vcolumn>
	  <@vcolumn title="${action.getText('requestReason')}">
	    <input type="text" name="requestReason" 
			   class="underline"  value="${object.requestReason?if_exists}"  maxlength="250"/>
	  </@vcolumn>
	  <@vcolumn title="${action.getText('comment')}">
	    <input type="text" name="comment" 
			   class="underline"  value="${object.comment?if_exists}"  maxlength="250"/>
	  </@vcolumn>
	  <#assign createPurchaseFlagValue=''/>
     <#if object.createPurchaseFlag>
       <#assign createPurchaseFlagValue="${action.getText('yes')}"/>
     <#else>
       <#assign createPurchaseFlagValue="${action.getText('no')}"/>
     </#if>
      <@vcolumn title="${action.getText('createPurchaseBill')}">
        ${createPurchaseFlagValue}
        <@alignLeft />
      </@vcolumn>
      <#--
      <@vcolumn title="${action.getText('sparePurchaseDetail.usedTooling')}" property="usedTooling">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('sparePurchaseDetail.requestReason')}" property="requestReason">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('sparePurchaseDetail.comment')}" property="comment">
        <@alignLeft />
      </@vcolumn>
      -->
    </@list>
    <#if !first>
    <#if !(action.isReadOnly())>
 	  <@buttonBar>
 	  <#--
        <#if yearQuarterFlag?exists>
          <#if yearQuarterFlag=='YEAR_PLAN'>
 	        <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{yearPlan.id}, null)"/>
          <#else>
            <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{quarterPlan.id}, null)"/>
          </#if>
        </#if>
        -->
        <#--
        <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{quarterPlan.id}, null)"/>
        -->
        <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="spare_openDialog()"/>
        <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return saveSparePurchaseDetail();'">
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
        <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('yearPlan.sparePurchaseDetail')}?" />
        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
          <@ww.param name="'onclick'" value="'return confirmDeletes(\"sparePurchaseDetailIds\", \"${confirmMessage}\");'"/>
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
        <#--
        <input type="button" class="button" name="new" value="${action.getText('select.spare')}" onclick="spare_openDialog()"/>
        -->
        <@vsubmit name="'copy'" value="'${action.getText('select.yearPlanDetail')}'" onclick="'return yearSparePurchaseDetail_OpenDialog();'"/>
      </@buttonBar>
    </#if>
    </#if>
    </@ww.form>
    <script language="javascript">
      window.onload = function () {
        <#if quarterPlan?exists>
          parent.document.forms["quarterPlan"].elements["quarterPlan.planAllFee"].value='${(quarterPlan.planAllFee?string('#,###,##0.00'))?if_exists}';
        </#if>
        <#if req.getParameter('errorFlag')?has_content>
          alert("${action.getText('delete.sparePurchaseDetail.failure')}");
        </#if>
        <#if overBudgetFee?exists>
          if (#{overBudgetFee} > 0) {
            parent.document.getElementById("warnningMessage").innerText="";
            parent.document.getElementById("warnningMessage").innerText='${quarterPlan.department.name?if_exists}' + "备件采购费用已超出预算" + '#{overBudgetFee}' + "元";
          }
        <#else>
          parent.document.getElementById("warnningMessage").innerText="";
        </#if>
	  }
      function open_detailDialog(planId, sparePurchaseDetailId) {
	    var url = '${req.contextPath}/popup/editQuarterSparePurchaseDetail.html?quarterPlan.id='+planId;	      		   		
	    if (sparePurchaseDetailId != null) {
	      url = url + "&sparePurchaseDetail.id=" + sparePurchaseDetailId;
	    }
	    url = url + "&yearQuarterFlag=" + '${yearQuarterFlag?if_exists}';
	    popupModalDialog(url,800,600);
	    var reloadUrl = "${req.contextPath}/year/tooling/quarterPlan/listSparePurchaseDetails.html?quarterPlan.id=#{quarterPlan.id}&yearQuarterFlag=QUARTER_PLAN";
	    //如果是点击项目号进入维护页面，保存当前页，已使在维护页面点击关闭，会回到当前页
        if (null != sparePurchaseDetailId) {
        	reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
        }
	    self.location.href = reloadUrl;
	  }
	  //点击"从年度计划中选择"按钮,触发备件采购选择
	  function yearSparePurchaseDetail_OpenDialog() {
	    var url = '${req.contextPath}/popup/sparePurchaseDetailSelector.html';
        popupModalDialog(url, 800, 600, refresh_sparePurchaseDetail_information);
        return true;	 
	  }
	  function refresh_sparePurchaseDetail_information(result) {
	    if (null != result) {
	      var yearSparePurchaseDetailIds = result.substring(0, result.lastIndexOf(","));
	      document.forms[0].elements["yearSparePurchaseDetailIds"].value=yearSparePurchaseDetailIds;
	    }
	  }
	  //点击保存触发
	  function saveSparePurchaseDetail() {
	    //验证单价的格式,以及获取列表中的单价
        if (!retrieveUnitPriceText()) {
          return false;
        }
	    //验证数量的格式,以及获取列表中的数量
	    if (!retrieveNumberText()) {
          return false;
        }
        //获取备件采购明细id
        retrieveSparePurchaseIdText();
        //获取列表中的需求日期
        retrieveRequestDateText();
        //获取列表中的需求原因
        retrieveRequestReasonText();
        //获取列表中的备注
        retrievCommentText();
        return true;
	  }
	  //新建选择备件
	  function spare_openDialog() {
	    var url = '${req.contextPath}/popup/spareSelector.html?toolingDevFlag=TOOLING';
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
	  //获取列表中的备件采购id
	  function retrieveSparePurchaseIdText() {
        var allSparePurchaseDetailId = document.getElementsByName("sparePurchaseDetailIds");
		var ary = new Array();
		for (var i=0; i<allSparePurchaseDetailId.length; i++) {
		  ary.push(allSparePurchaseDetailId[i].value);
		}
		document.forms[0].elements["allSparePurchaseDetailId"].value = ary;
	  }
	  //获取列表中的单价
	  function retrieveUnitPriceText() {
        var allUnitPrice = document.getElementsByName("sparePurchaseDetail.unitPrice");
        var allSparePurchaseDetailId = document.getElementsByName("sparePurchaseDetailIds");
        var ary = new Array();
        for (var i=0; i<allSparePurchaseDetailId.length; i++) {
          if ('' != allUnitPrice[i].value) {
            if (!validateDoubleUnit(allUnitPrice[i].value,i+1,"${action.getText('sparePurchaseDetail.unitPrice.format.error')}")) {
              return false;
            }
            ary.push(allSparePurchaseDetailId[i].value);
            ary.push(formatDigital(allUnitPrice[i].value));
          }
        }
        document.forms[0].elements["allUnitPrice"].value = ary;
        return true;
      }
	  //获取列表中的数量
	  function retrieveNumberText() {
        var allNumber = document.getElementsByName("sparePurchaseDetail.number");
        var allSparePurchaseDetailId = document.getElementsByName("sparePurchaseDetailIds");
        var ary = new Array();
        for (var i=0; i<allSparePurchaseDetailId.length; i++) {
          if ('' != allNumber[i].value) {
            if (!validateNumber(allNumber[i].value,i+1,"${action.getText('sparePurchaseDetail.number.format.error')}")) {
              return false;
            }
            ary.push(allSparePurchaseDetailId[i].value);
            ary.push(formatDigital(allNumber[i].value));
          }
        }
        document.forms[0].elements["allNumber"].value = ary;
        return true;
      }
      //获取列表中的需求日期
       function retrieveRequestDateText() {
         var allSparePurchaseDetailId = document.getElementsByName("sparePurchaseDetailIds");
         var ary = new Array();
         for (var i=0; i<allSparePurchaseDetailId.length; i++) {
           var requestDateIdentityName = 'requestDate';
           requestDateIdentityName = requestDateIdentityName + (i+1); 
           if ('' != document.forms[0].elements[requestDateIdentityName].value) {
             ary.push(allSparePurchaseDetailId[i].value);
             ary.push(document.forms[0].elements[requestDateIdentityName].value);
           }
         }
         document.forms[0].elements["allRequestDate"].value = ary;
       }
       //获取列表需求原因
       function retrieveRequestReasonText() {
         var allReason = document.getElementsByName("requestReason");
         var allSparePurchaseDetailId = document.getElementsByName("sparePurchaseDetailIds");
         var ary = new Array();
         for (var i=0; i<allSparePurchaseDetailId.length; i++) {
           if ('' != allReason[i].value) {
             ary.push(allSparePurchaseDetailId[i].value);
             ary.push(allReason[i].value);
           }
         }
         document.forms[0].elements["allRequestReason"].value = ary;
      }
      //获取列表备注
      function retrievCommentText() {
        var allComment = document.getElementsByName("comment");
        var allSparePurchaseDetailId = document.getElementsByName("sparePurchaseDetailIds");
        var ary = new Array();
        for (var i=0; i<allSparePurchaseDetailId.length; i++) {
          if ('' != allComment[i].value) {
            ary.push(allSparePurchaseDetailId[i].value);
            ary.push(allComment[i].value);
          }
        }
        document.forms[0].elements["allComment"].value = ary;
      }
      //验证数量格式     
	  function validateNumber(usedNumValue,row,message){
        usedNumValue = formatDigital(usedNumValue);
		var control = isNumberBetween(usedNumValue,1000000001,0);
	    if(control!=1){
	      alert("${action.getText('di')}"+row+"${action.getText('row')}"+message);
	      return false;
	    }
	    return true;
      } 
     //验证单价格式
     function validateDoubleUnit(usedUnit,row,message){
      usedUnit=formatDigital(usedUnit);
      var control=isDoubleNumberBetweenBoolean(usedUnit,1000000001,0);
       if(control!=1){
	      alert("${action.getText('di')}"+row+"${action.getText('row')}"+message);
	      return false;
	    }
	    return true;
     }
    </script>
     
</@framePage>