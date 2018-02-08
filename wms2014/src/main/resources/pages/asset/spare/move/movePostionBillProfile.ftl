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
<#--$Id: purchaseOrderProfile.ftl 11328 2008-09-25 09:39:30Z smzhang $ -->

<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('movePostionBillProfile.title')}">
	<@ww.form namespace="'/spare'"  name="'MovePostionBillFrom'" action="'saveMovePostionBill'" method="'post'">
		<@ww.token name="saveMovePostionBillToken"/>
       <#if movePostionBill.moveStatus?exists>
         <@ww.hidden name="'status'" value="'${movePostionBill.moveStatus}'"/>
       </#if>	
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@inputTable>
        <#if movePostionBill.id?exists>
            <@ww.hidden name="'movePostionBill.id'" value="#{movePostionBill.id}"/>
        </#if>
        <tr>
            <@ww.textfield label="'${action.getText('movePostionBillNo')}'"  name="'movePostionBill.billNo'" value="'${movePostionBill.billNo?if_exists}'" cssClass="'underline'" disabled="true"/>
            <@ww.textfield label="'${action.getText('movePostionBillName')}'"  name="'movePostionBill.billName'" value="'${movePostionBill.billName?if_exists}'" cssClass="'underline'" required="true"/>
        </tr>
        <tr>
	  	  <#-- 库存级别 -->
	  		 <@ww.select label="'${action.getText('storageGrade')}'" 
		                 name="'storageGrade.id'" 
					     listKey="id" 
					     listValue="value"
		                 list="allStorageGrade" 
		                 emptyOption="flase" 
		                 disabled="false"
		                 required="true" 
		                 onchange="'wareHouseCascadeDWR(\"storageGrade.id\",\"warehouse.id\",#{loginUser.id?if_exists},\"${action.getText('')}\");'">
		                     <#if movePostionBill.storageGrade?exists>
		                      <@ww.param name="'value'"  value="'#{movePostionBill.storageGrade.id}'" />
		                   </#if>
	                       
	          </@ww.select> 
		        <@ww.select 
					label="'${action.getText('warehouse')}'" 
					required="true" 
					name="'warehouse.id'" 
					value="'${req.getParameter('warehouse.id')?if_exists}'" 
					listKey="id" 
					listValue="name"
					list="allWarehouses" 
					emptyOption="true" 
					disabled="false">
				 </@ww.select>
       	 </tr>
        <tr>
            <@pp.remotePicker label="'${action.getText('MovePositionBillPeople')}'" name="'movePostionBill.movePeople'"
                    selectorName="'userSelectorAjax'" cssClass="'underline'" value="movePostionBill.user"
                    popup="'${req.contextPath}/popup/userSelector.html'" size="15" codeName="'loginName'" required="true"/>
            <@pp.datePicker label="'${action.getText('MovePositionBillDate')}'" name="'movePostionBill.moveTime'"
					value="'${(movePostionBill.moveTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" required="true" size="15"/>
        </tr>
        <tr>
          <#assign status=''/>
        	<#if movePostionBill.moveStatus?exists>
		       <#if '${movePostionBill.moveStatus}' == 'unMovePosition'>
		       <#assign status = "${action.getText('unMovePosition')}"/>
		       <#elseif '${movePostionBill.moveStatus}' == 'movePositioning'>
		       <#assign status = "${action.getText('movePositioning')}"/>
		       <#elseif '${movePostionBill.moveStatus}' == 'movePositioned'>
		       <#assign status = "${action.getText('movePositioned')}"/>
        	</#if>
        	</#if>
	    		<@ww.textfield label="'${action.getText('status')}'" name="'movePostionBill.moveStatus'" value="'${status}'" cssClass="'underline'" readonly="true"/>	
         	    <@ww.textarea label="'${action.getText('comment')}'"  name="'movePostionBill.comment'" value="'${movePostionBill.comment?if_exists}'" rows="'3'" cols="'50'" />
        </tr>
	</@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
        <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return checkInput()'"/>
   	    </#if>
   	    <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/spare/movePostionBill.html"/>
    </@buttonBar>
	</@ww.form>
</@htmlPage>
<script language="javascript">
		<#if movePostionBill.storageGrade?exists>
	    	getObjByName('storageGrade.id').value = #{movePostionBill.storageGrade.id?if_exists};
	    </#if>
        cascadeWarehouseByStorageGrade();

	window.onload=function()
	{

		a = new Date();
		var time=a.format("yyyy-MM-dd");
		document.forms[0].elements["movePostionBill.moveTime"].value=time;
		<#if movePostionBill.id?exists>
		var url = '${req.contextPath}/spare/listMovePostionBillDetails.html?movePostionBill.id=#{movePostionBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
	    document.all.frame.src = url;
		getObjByNameRe("details").className = "selectedtab";
		</#if>
			    		        	//仓库
		<#if movePostionBill.warehouse?exists>
			getObjByName('warehouse.id').value=#{movePostionBill.warehouse.id?if_exists};
	    <#else>
	    	<#if req.getParameter('warehouse.id')?exists>
	    		getObjByName('warehouse.id').value='${req.getParameter('warehouse.id')?if_exists}';
	    	</#if>
	    </#if> 

	}
	//根据仓库级别级联带出仓库
	function cascadeWarehouseByStorageGrade(){
	    DWREngine.setAsync(false); 
	    //回调种类的值后触发DWR 级联明细种类  
	    wareHouseCascadeDWR("storageGrade.id","warehouse.id",#{loginUser.id?if_exists},"${action.getText('')}")
	    //重新设置为异步方式
	    DWREngine.setAsync(true);
	}
<#--
	window.onload=function()
	{
		<#if spareInBill.id?exists>
		var url = 'listSpareInBillDetails.html?spareInBill.id=#{spareInBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
	    document.all.frame.src = url;
		getObjByNameRe("details").className = "selectedtab";
		</#if>
		<#if spareInBill.id?exists>
		<#else>
			a = new Date();
		  	var time=a.format("yyyy-MM-dd");
		  	document.forms[0].elements["spareInBill.inDate"].value=time;
		</#if>
  		<#if !(action.isReadOnly())>
  		<#if spareInBill.id?exists>
			<#if spareInBill.submit==false>
				document.forms[0].elements["submitRecord"].disabled="true";
			<#else>
			document.forms[0].elements["submitRecord"].disabled="false";
			</#if>
  		</#if>
  		</#if>
	}
	-->
    //信息验证
    function checkInput(){
      //验证入库单名称
      if(document.forms[0].elements["movePostionBill.billName"].value==""){
         alert('${action.getText('movePostionBill.billName.null')}');
         return false;
      }else {
		if (!isValidLengthValue(document.forms[0].elements["movePostionBill.billName"].value,0,50)) {
			 alert("${action.getText('movePostionBill.billName.stringlength')}");
			 return false;
		}
	  }
      //验证入库人
      if(document.forms[0].elements["movePostionBill.movePeople.id"].value==""){
         alert('${action.getText('movePostionBill.movePeople.null')}');
         return false;
      }
      //验证日期
	  if(getObjByNameRe("movePostionBill.moveTime").value==''){
        alert('${action.getText('movePostionBill.moveTime.null')}');
        return false;
	  }
	  var date=getObjByNameRe("movePostionBill.moveTime").value;
	  if(!isDate("movePostionBill.moveTime")){
	    alert("${action.getText('movePostionBill.moveTime.inDate')}");
		return false;
	  }
	  //验证备注
	  var comment=document.forms[0].elements["movePostionBill.comment"].value;
	  if(comment!=null&&comment!=''){
	   if (!isValidLengthValue(comment,0,50)) {
			 alert("${action.getText('movePostionBill.comment.error')}");
			 return false;
		}
	  }
    }
</script>
    <#if movePostionBill.id?exists>
		<ul id="beautytab">
			<li><a id="details" onclick="activeTab(this);"  
				href="${req.contextPath}/spare/listMovePostionBillDetails.html?movePostionBill.id=#{movePostionBill.id}" target="frame" >${action.getText('移位单明细')}</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>