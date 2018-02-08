<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: subscribeDetails.ftl 11311 2008-03-13 13:19:59Z mwei $ -->
<#include "../../../includes/eam2008.ftl" />
  <style type="text/css">
    .noBorderLine{
       border-width :0px;
       border-style : none;
       outline-style : none;
       text-align:right;
       width:80%;
       }
    .definedLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        text-align:right;
        width:80%;
    }
  </style>
  <base target="_self">
<@framePage title="${action.getText('')}">	
	<@ww.form namespace="'/spare'" name="'listForm'" action="'searchMovePostionBillDetails'" method="'post'">
		<@ww.token name="searchSpareInBillDetailToken"/>
		<@ww.hidden name="'addSpareLocationDetailIds'" value=""/>
		<@ww.hidden name="'spareLocationAccountSelector'" value=""/>
		
		<@ww.hidden name="'allMovePostionBillDtlIds'" value=""/>
		<@ww.hidden name="'allLocationCodeValue'" value=""/>
		<@ww.hidden name="'allMovePostionDetailNumber'" value=""/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if movePostionBill.id?exists>
		<@ww.hidden name="'movePostionBill.id'" value="#{movePostionBill.id}"/>
		</#if>
		<#assign loopNum=0/>
	<@list title="" excel=false setupTable=false includeParameters="movePostionBill.id" fieldMap="like:">
			<@vlh.checkbox property="id" name="movePostionBillDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('spareNo')}" property="spareNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('spareName')}" property="spareName">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('modelSpec')}" property="modelSpec">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('beforeMoveWarehouse')}" property="beforeMoveWarehouse">
            	${(object.beforeMoveWarehouse)?if_exists}
            	<input type="hidden" name="beforeMoveWarehouse" 
			    		      class="noBorderLine"  value="${object.beforeMoveWarehouse?if_exists}"/>
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('beforeMoveRegional')}" property="beforeMoveRegional">
            	${(object.beforeMoveRegional)?if_exists}
            	<input type="hidden" name="beforeMoveRegional" 
			    		      class="noBorderLine"  value="${object.beforeMoveRegional?if_exists}"/>
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('beforeMoveLocationCode')}" property="beforeMoveLocationCode">
            	${(object.beforeMoveLocationCode)?if_exists}
            	<input type="hidden" name="beforeMoveLocationCode" 
			    		      class="noBorderLine"  value="${object.beforeMoveLocationCode?if_exists}"/>
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('beforeMoveStocks')}" property="beforeMoveStocks" >
             ${(object.beforeMoveStocks)?if_exists}	
          		          <input type="hidden" name="beforeMoveStocks" 
			    		      class="noBorderLine"  value="${object.beforeMoveStocks?if_exists}"  maxlength="250" size="7"/>
          	<@alignRight />
             </@vcolumn>
             <#--移位后仓库-->
             <#if object.afterMoveWarehouse?exists>
             	<@vcolumn title="${action.getText('afterMoveWarehouse')}" property="afterMoveWarehouse">
            		 <#--<input type="hidden" name="afterMoveWarehouse" 
			    		      class="noBorderLine"  value="${object.afterMoveWarehouse}"  maxlength="250" size="7"/>-->
                	<@alignLeft />
            	</@vcolumn>
            <#else>
            	 <@vcolumn title="${action.getText('afterMoveWarehouse')}" property="afterMoveWarehouse">
            	 	<input type="text" name="afterMoveWarehouse${loopNum}" class="underline"  value=""  maxlength="150" size="10"" readOnly="true"/>
            	 </@vcolumn>
            </#if>
             <#--移位后库区-->
           	<#if object.afterMoveRegional?exists>
             	<@vcolumn title="${action.getText('afterMoveRegional')}" property="afterMoveRegional">
            		 <#--<input type="hidden" name="afterMoveRegional" 
			    		      class="noBorderLine"  value="${object.afterMoveRegional}"  maxlength="250" size="7"/>-->
                	<@alignLeft />
            	</@vcolumn>
            <#else>
            	 <@vcolumn title="${action.getText('afterMoveRegional')}" property="afterMoveRegional">
            	 	<input type="text" name="afterMoveRegional${loopNum}" class="underline"  value=""  maxlength="150" size="10"" readonly="true"/>
            	 </@vcolumn>
            </#if>
             <#--移位后库位-->   
            <@vcolumn title="${action.getText('afterMoveLocationCode')}">
           	    <#assign LocationCode = ''/>
           	    <#assign LocationId = 0/>
				<#if object.afterMoveLocationCode?exists>
				  <#assign LocationCode = "${object.afterMoveLocationCode}" />
				  <#if object.location?exists>
				  	<#assign LocationId = "#{object.location.id}"/>
				  </#if>
				</#if>
					<input type="hidden" name="afterLocationCode" 
			    		      class="noBorderLine"  value="${LocationCode}"/>
            	<@alignLeft />
			<#if '${object.movePositionDtlStatus}'=='unMovePositionDtl'>
				<@eam2008_remoteGetLocation id="${LocationId}" code="${LocationCode}" loopNum="${loopNum}" inputName="location.code" disabled="false"/>
			<#else>
				<@eam2008_remoteGetLocation id="${LocationId}" code="${LocationCode}" loopNum="${loopNum}" inputName="location.code" disabled="true" />	         
			</#if>

	     	</@vcolumn>
	     	
	     	<#assign loopNum=loopNum+1/>
			<#if '${object.movePositionDtlStatus}'=='unMovePositionDtl'>
				<@vcolumn title="${action.getText('number')}">
					<input type="text" name="number" class="underline"  value="${object.number?if_exists}"  maxlength="50" size="8" style="text-align:right"/>           	
             		<@alignRight />
            	</@vcolumn>
			<#else>
				<@vcolumn title="${action.getText('number')}">
					<input type="text" name="number" class="underline"  value="${object.number?if_exists}"  maxlength="50" size="8" style="text-align:right" />           	
             		<@alignRight />
            	</@vcolumn>		         
			</#if>
            <#assign status=''/>
		       <#if '${object.movePositionDtlStatus}'=='unMovePositionDtl'>
		           <#assign status = "${action.getText('unMovePositionDtl')}"/>
		       <#else>
		         <#assign status = "${action.getText('movePositionedDtl')}"/>
        	   </#if>
         	<@vcolumn title="${action.getText('status')}" attributes="width='50'">
           			${status}
                 <@alignLeft/>
            </@vcolumn>
	</@list>
        <@buttonBar>
        	<#if !first>
        	<#if !(action.isReadOnly())>
            <@vbutton name="'selectSpare'"  class="button" value="${action.getText('fromSpare')}" onclick="open_selectSpareAccountDialog()"/>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitDetailIds()'">
            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('movePostionBillDetail')}?" />
           <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"movePostionBillDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
            </#if>
        </@buttonBar>
	</@ww.form>
</@framePage>
<script language="javascript">
window.onload=function(){

    <#if req.getParameter('errorFlag')?has_content>
   	       alert("${action.getText('delete.spareInDtl.failure')}");
    </#if>
		<#if movePostionBill.moveStatus?exists>
   		    <#assign moveStatus = ''/>
   			<#if '${movePostionBill.moveStatus}' == 'unMovePosition'>
   			<#assign moveStatus = "${action.getText('unMovePosition')}"/>
   			<#elseif '${movePostionBill.moveStatus}' == 'movePositioning'>
   			<#assign moveStatus = "${action.getText('movePositioning')}"/>
   			<#elseif '${movePostionBill.moveStatus}' == 'movePositioned'>
   			<#assign moveStatus = "${action.getText('movePositioned')}"/>
   			</#if>
   			parent.document.forms["MovePostionBillFrom"].elements["movePostionBill.moveStatus"].value = '${moveStatus}';
   			parent.document.forms["MovePostionBillFrom"].elements["status"].value = '${movePostionBill.moveStatus}';
   	</#if>
}
	//从备件台帐选择
	function open_selectSpareAccountDialog(){
	   //var url = '${req.contextPath}/spareLocation/spareLocationSearcherCommon.html?inOutFlag=out';
	   //var url = '${req.contextPath}/newLocation/listLocation.html?inOutFlag=out';
	   var url = url = '${req.contextPath}/spareLocation/spareLocationSearcherCommon.html?roleWarehouseId=#{movePostionBill.warehouse.id}';	
	  popupModalDialog(url, 800, 600,choose_spareAccount_information)
	}
	function choose_spareAccount_information(result){
      if (null != result) {
        var spareLocationDetailIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addSpareLocationDetailIds"].value = spareLocationDetailIds;
        document.forms[0].elements["spareLocationAccountSelector"].value = "spareLocationAccount";
        document.forms[0].submit();
      }
	}
	
	function submitDetailIds(){
	  /* 判断是否输入了移位数量 */
	  if(!checkInputState()){
	     return false;
	  }
	  /* 判断移位数量是否比总库存量大 */
	  if(!checkStockValue()){
	     return false;
	  }
	  /*判断移位后的库位号与移位前的库位号是否相同*/
	   if(!checkBeforeAndAfter()){
	    	return false;
	   }
	retrieveMovePostionBillDetailIdText();  
	<#--
	retrieveMovePostionBillDetailLocationText();
	-->
	retrieveLocationCodeText();
	retrieveMovePostionBillDetailNumberText();
	return true;	  
	  
	}
	<#--function checkBeforeAndAfter(){
	    	  var beforeMoveLocationCode = document.getElementsByName("beforeMoveLocationCode");
	  var afterMoveLocationCode = document.getElementsByName("afterLocationCode");
		  var selector = document.getElementsByName("movePostionBillDtlIds");
	}-->
	/*判断移位后的库位号与移位前的库位号是否相同*/
	function checkBeforeAndAfter(){ 
	  var beforeMoveLocationCode = document.getElementsByName("beforeMoveLocationCode");
	  var afterMoveLocationCode = document.getElementsByName("afterLocationCode");
	  var selector = document.getElementsByName("movePostionBillDtlIds");
	  if (selector.length) {
	    for (var i = 1; i < selector.length+1; i++) {
		 var s="location.code";
		 var s_flag="hidden.location.code";
		 var n=i-1;
		 var t=s+n;
		 var t_flag=s_flag+n;
		 if(getObjByNameRe(t_flag).value=='true'){
		  if(getObjByNameRe(t).value==''){
		   		alert("${action.getText('row')}"+(i)+"${action.getText('movePostionBillDtl.after.error')}");
		   		return false;		    
		  }		 
		  if(beforeMoveLocationCode[i-1].value!=""){
		   	if(beforeMoveLocationCode[i-1].value==getObjByNameRe(t).value){
		   	    alert("${action.getText('row')}"+(i)+"${action.getText('movePostionBillDtl.beforeAndAfter.error')}");
		   		return false;
		    }
		  }
		 }if(getObjByNameRe(t_flag).value=='false'){
		   alert("${action.getText('row')}"+(i)+"${action.getText('movePostionBillDtl.not.exist')}");
		   return false;
		 }
	    }
	  } 
	  return true;	
	}
	//获得入库明细的所有ID
	function retrieveMovePostionBillDetailIdText(){
		var allMovePostionBillDtlId = document.getElementsByName("movePostionBillDtlIds");
		var allIds = "";
        for (var i=0; i<allMovePostionBillDtlId.length; i++) {
			allIds += allMovePostionBillDtlId[i].value +"##"
        }
        allIds = allIds.substring(0,allIds.lastIndexOf("##"));
        document.forms[0].elements["allMovePostionBillDtlIds"].value = allIds;
	}	
	//获取入库明细的所有库位
	function retrieveMovePostionBillDetailLocationText(){
	    var allMovePostionBillDtlId = document.getElementsByName("movePostionBillDtlIds");
        var ary = new Array();
        for (var i=0; i<allMovePostionBillDtlId.length; i++) {
          ary.push(allMovePostionBillDtlId[i].value);
          ary.push(getObjByNameRe("moveId.location.code"+i).value);
        }
        document.forms[0].elements["allLocationCodeValue"].value=ary;
	}
     /*
       * 获取列表中库位号的所有值
      */
      function retrieveLocationCodeText() {
        var objDetailId = document.getElementsByName("movePostionBillDtlIds");
        var ary = new Array();
        for (var i=0; i<objDetailId.length; i++) {
          ary.push(objDetailId[i].value);
          ary.push(getObjByNameRe("location.code"+i).value);
        }
        document.forms[0].elements["allLocationCodeValue"].value=ary;
      }	
	
	function retrieveMovePostionBillDetailNumberText(){
		var allMovePostionBillDtlId = document.getElementsByName("movePostionBillDtlIds");
		var number = document.getElementsByName("number");
		var allNumbers = "";
	  		for (var i = 0; i < allMovePostionBillDtlId.length; i++){
	  			allNumbers += allMovePostionBillDtlId[i].value+"##";
	            allNumbers += formatDigital(number[i].value)+"##";
	  		}
	  	allNumbers = allNumbers.substring(0,allNumbers.lastIndexOf("##"));
	  	getObjByNameRe("allMovePostionDetailNumber").value=allNumbers;
	}
 	/* 判断是否输入了出存数量 */
 	function checkInputState(){
	    var selector = document.getElementsByName("movePostionBillDtlIds");
	    var valueselector = document.getElementsByName("number");
	    if (selector.length) {
		   for (var i = 1; i < selector.length+1; i++) {
		   	if(valueselector[i-1].value!=""){
           		if (!isDoubleNumberCheck(valueselector[i-1].value)){   			//验证输入的入库数量的格式
		         		alert("${action.getText('row')}"+(i)+"${action.getText('movePostionBillDtl.formate.error')}");
		         		return false;
       				}else if(!isNumberBetweenBoolen(valueselector[i-1].value, 1000000001, -1000000000)){		//验证输入的入库数量的范围
	       				alert("${action.getText('row')}"+(i)+"${action.getText('movePostionBillDtl.number.maxLength')}");
	       				return false;
       				}
            }else if(valueselector[i-1].value==""){
            	   alert("${action.getText('row')}"+(i)+"${action.getText('movePostionBillDtl.number.isnull')}");
            	   return false;
            }
         	}
		} 
	return true;
	}
	function checkStockValue(){
	   var selector = document.getElementsByName("movePostionBillDtlIds");
	   var numberselector = document.getElementsByName("number");
	   var beforeValueselector = document.getElementsByName("beforeMoveStocks");
   	   if (selector.length) {
          for (var i = 1; i < selector.length+1; i++) {
            if(numberselector[i-1].value!=""){
               if(parseInt(numberselector[i-1].value)>parseInt(beforeValueselector[i-1].value)){
               	alert("${action.getText('row')}"+(i)+"${action.getText('movePostionBillDtl.lowStock')}");
            	return false;
            	}
             }else{
            	return true;
             }
           }
		    return true;
		}else{
		 	return false;
		}
 	}
</script>