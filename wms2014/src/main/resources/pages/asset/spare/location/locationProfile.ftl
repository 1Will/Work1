<#--库位维护页面-->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('title')}">
<@ww.form namespace="'/spare'"  name="'locationProfile'" action="'saveLocation'" method="'post'">
    
    <@ww.token name="saveLocationToken"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       <#if location.status?exists>
         <@ww.hidden name="'status'" value="'${location.status}'"/>
       </#if>
       <#if location.id?exists>
		<@ww.hidden name="'location.id'" value="'#{location.id}'"/>
       </#if>
    <@inputTable>
        
        <tr>
        <#if location.id?exists>
           <@ww.textfield label="'${action.getText('code')}'"  name="'location.code'" value="'${location.code?if_exists}'" cssClass="'underline'" required="true" disabled="true"/>
        <#else>
            <@ww.textfield label="'${action.getText('code')}'"  name="'location.code'" value="'${location.code?if_exists}'" cssClass="'underline'" required="true" />
        </#if>
            <@ww.textfield label="'${action.getText('maxWeight')}'"  name="'location.maxWeight'" value="'${(location.maxWeight?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" />
        </tr>
        
		<tr>
			<@ww.textfield label="'${action.getText('length')}'"  name="'location.length'" value="'${(location.length?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" onchange="'changeVolume()'"/>
			<@ww.textfield label="'${action.getText('wide')}'"  name="'location.wide'" value="'${(location.wide?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" onchange="'changeVolume()'"/>
		</tr>
        
        <tr>
         	<@ww.textfield label="'${action.getText('hight')}'"  name="'location.hight'" value="'${(location.hight?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" onchange="'changeVolume()'"/>
         	<@ww.textfield label="'${action.getText('volume')}'"  name="'location.volume'" value="'${(location.volume?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" disabled="true"/>
        </tr>
        
        <tr>
         	<#assign status=''/>
        	<#if location.status?exists>
		       <#if '${location.status}' == 'NON_USE'>
		       <#assign status = "${action.getText('NON_USE')}"/>
		       <#elseif '${location.status}' == 'USED'>
		       <#assign status = "${action.getText('USED')}"/>
        	</#if>
        	</#if>
			<@ww.textfield label="'${action.getText('status')}'" name="'location.status'" value="'${status}'" cssClass="'underline'" disabled="true"/>	
			<@ww.textarea label="'${action.getText('describe')}'"  name="'location.describe'" value="'${location.describe?if_exists}'" rows="'3'" cols="'50'" />
		</tr>
		
    </@inputTable>
    
    <@buttonBar>
    	<#if !(action.isReadOnly())>
        	<@vsubmit name="'save'" value="'${action.getText('save')}'"  onclick="'return checkInput()'"/>
        	<#if (location.id)?exists>
	   	    	<@vbutton name="saveContinue"  class="button" value="${action.getText('saveContinue')}" onclick="return encodeUrl() "/>
	   	    </#if>
   	    </#if>
   	    <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/spare/listLocation.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
    
</@ww.form>

<script language="javascript">

    //改变库位体积
    function changeVolume(){
		if( checkInput()){
       		var length = document.forms[0].elements["location.length"].value;
	    	var wide = document.forms[0].elements["location.wide"].value;
	    	var hight = document.forms[0].elements["location.hight"].value;
	    	var volume=(parseFloat(length)*parseFloat(wide)*parseFloat(hight)).toFixed(2);
    		document.forms[0].elements["location.volume"].value=volume;
    	}
    }
    //保存时信息验证
    function checkInput(){
      var length = document.forms[0].elements["location.length"].value;
	  var wide = document.forms[0].elements["location.wide"].value;
	  var hight = document.forms[0].elements["location.hight"].value;
	  var maxWeight = document.forms[0].elements["location.maxWeight"].value;
	  var describe = document.forms[0].elements["location.describe"].value;
	  //验证描述
      if(!isValidLength(document.forms[0],"location.describe",null,255)&&describe!=''&&describe!=null){
			alert("${action.getText('location.describe.error')}");
			return  false;
	  }
      //验证库位号
      if(!isValidLength(document.forms[0], "location.code", 1, 50)){
			alert("${action.getText('location.code.error')}");
			return  false;
	  }
      //验证长
      if(length!=null&&length!=""){
        if (!isDoubleNumberCheck(length)){
	         		alert("${action.getText('location.length.error')}");
	         		return false;
       			}else if(!isNumberBetween(length, 1000000001, 0)){	
        alert(11);
       				alert("${action.getText('location.length.error')}");
       				return false;
       			}
       }
      //验证宽
      if(wide!=null&&wide!=""){
        if (!isDoubleNumberCheck(wide)){ 
	         		alert("${action.getText('location.wide.error')}");
	         		return false;
       			}else if(!isNumberBetween(wide, 1000000001, 0)){	
       				alert("${action.getText('location.wide.error')}");
       				return false;
       			}
       }
      //验证高
      if(hight!=null&&hight!=""){
        if (!isDoubleNumberCheck(hight)){   			
	         		alert("${action.getText('location.hight.error')}");
	         		return false;
       			}else if(!isNumberBetween(length, 1000000001, 0)){	
       				alert("${action.getText('location.hight.error')}");
       				return false;
       			}
       }
      //验证最大重量
      if(maxWeight!=null&&maxWeight!=""){
        if (!isDoubleNumberCheck(maxWeight)){   			
	         		alert("${action.getText('location.maxWeight.error')}");
	         		return false;
       			}else if(!isNumberBetween(maxWeight, 1000000001, 0)){	
       				alert("${action.getText('location.maxWeight.error')}");
       				return false;
       			}
       }
      return true;
    }
    
    //继续新建
    function encodeUrl(){
    <#if location.id?exists>
    	var url = '${req.contextPath}/spare/editLocation.html?location.id=#{location.id}&readOnly=${req.getParameter('readOnly')?if_exists}&saveContinue=RENEW';
    </#if>
    	return location=url;
    }
</script>
</@htmlPage>