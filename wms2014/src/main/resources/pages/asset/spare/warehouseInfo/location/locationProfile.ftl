<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('title')}">
	<@ww.form  name="'listFrom'" action="'saveLocation'" namespace="'/newLocation'" method="'post'" >
     	 <@ww.token name="saveLocationToken"/>
     	  <@inputTable>
     	  	<@ww.hidden name="'location.id'" value="'${(location.id?string('##################'))?if_exists}'"/>
     	  	<@ww.hidden name="'volume'" value="'${(location.volume?string('##################'))?if_exists}'"/>
     	  	<@ww.hidden name="'requestSourceType'" value="'${requestSourceType?if_exists}'"/>
     	  	<tr>
 			<@ww.textfield label="'${action.getText('code')}'" name="'location.code'" value="'${location.code?if_exists}'" cssClass="'underline'" required="true"/>
			 <@ww.select label="'${action.getText('仓库级别')}'" 
	                   required="true" 
	                   name="'storageGrade'"
	                   value="'${req.getParameter('storageGrade')?if_exists}'"  
    			       listKey="id" 
    			       listValue="value"
                       list="allStorageGrade" 
                       onchange="'wareHouseCascadeDWR(\"storageGrade\",\"warehouse\",${loginUser.id?if_exists},\"${action.getText('')}\",\"edit\");'"
                       emptyOption="true">
         </@ww.select>
 			<#-- 仓库 -->
       	 	<@ww.select 
				label="'${action.getText('warehouse')}'" 
				name="'warehouse'" 
				required="true" 
				value="'${req.getParameter('warehouse')?if_exists}'" 
				listKey="id" 
				listValue="name"
				list="allWarehouseName" 
				disabled="false"
				onchange="'WareCascadeRegionalDWR(\"warehouse\",\"regional\",\"${action.getText('')}\",\"edit\")'">
			</@ww.select>
		</tr>
 		<tr>
			<#-- 库区 -->
			<@ww.select 
				label="'${action.getText('regional')}'" 
				name="'regional'" 
				required="true" 
				value="" 
				listKey="id" 
				listValue="name"
				list="allRegional" 
				disabled="false">
			</@ww.select>
 			<@ww.select label="'${action.getText('locationType')}'"
	                name="'locationType'"
					list="allLocationType"
					listKey="id"
					listValue="value"
					value="'${req.getParameter('locationType')?if_exists}'"
	        />
 			<@ww.select label="'${action.getText('bearload')}'"
	                name="'bearload'"
					list="allBearload"
					listKey="id"
					listValue="value"
					value="'${req.getParameter('bearload')?if_exists}'"
	        />
		</tr>
  		<tr>
	        <@ww.select 
		        	label="'${action.getText('Mixmode')}'"
	            	name="'mixmode'"
					list="allMixmode"
					listKey="id"
					listValue="value"
					value="'${req.getParameter('mixmode')?if_exists}'"
            		required="false"
	        />
 			<@ww.select 
		        	label="'${action.getText('samestore')}'"
	            	name="'samestore'"
					list="allSamestore"
					listKey="id"
					listValue="value"
					value="'${req.getParameter('samestore')?if_exists}'"
            		required="false"
	        />
           <@ww.textfield label="'${action.getText('maxWeight')}'"  name="'location.maxWeight'" value="'${(location.maxWeight?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" />
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('length')}'"  name="'location.length'" value="'${(location.length?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" onchange="'changeVolume()'"/>
			<@ww.textfield label="'${action.getText('wide')}'"  name="'location.wide'" value="'${(location.wide?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" onchange="'changeVolume()'"/>
          	<@ww.textfield label="'${action.getText('hight')}'"  name="'location.hight'" value="'${(location.hight?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" onchange="'changeVolume()'"/>
		</tr>
		<tr>
         	<@ww.textfield label="'${action.getText('volume')}'"  name="'location.volume'" value="'${(location.volume?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" disabled="true"/>
 			<@ww.textfield label="'${action.getText('passageway')}'" name="'location.passageway'" value="'${location.passageway?if_exists}'" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'${action.getText('row')}'" name="'location.row'" value="'${location.row?if_exists}'" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
 			<@ww.textfield label="'${action.getText('level')}'" name="'location.level'" value="'${location.level?if_exists}'" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'${action.getText('site')}'" name="'location.site'" value="'${location.site?if_exists}'" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'${action.getText('status')}'" name="'location.status'" value="''" cssClass="'underline'" disabled="true"/>	
		</tr>
		<tr>
			<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('describe')}:
	        		</label>
	        </td>
	        <td colspan="4" >
	        	<textarea name="location.describe" rows="4" cols="95" >${location.describe?if_exists}</textarea>
	        </td>
		</tr>
 	  </@inputTable>
      	  <@buttonBar>
         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
          <#if location.id?exists>
            <@redirectButton value="继续新建" url="editLocation.html"/>
         </#if>
         <#if '${requestSourceType?if_exists}' == 'PopupWin'>
         	<#if location.id?exists>
		       <@vbutton name="'select'" class="button" value="${action.getText('select')}" onclick="javascript: returnDialog(new Array(#{location.id}, '${location.code}',#{location.warehouse.id},'${location.warehouse.name}'));"/>
		    </#if>
		    <@hrefButton value="${action.getText('back')}" url="${req.contextPath}/newLocation/locationSearcherCommon.html"/>
         <#else>
         <@redirectButton value="${action.getText('back')}" url="listLocation.html"/>
         </#if>
     </@buttonBar>	
    </@ww.form>
</@htmlPage>
<script language="javascript">
	<#if location.storageGrade?exists>
          getObjByName('storageGrade').value ='#{location.storageGrade.id?if_exists}';         
           DWREngine.setAsync(false); 
	    		//回调仓库级别的值后触发DWR 级联仓库
	      wareHouseCascadeDWR("storageGrade","warehouse",${loginUser.id?if_exists},"${action.getText('')}","edit")
	    		//重新设置为异步方式
	      DWREngine.setAsync(true);
    </#if>
    //计算体积
    function changeVolume(){
		if( validate()){
       		var length = document.forms[0].elements["location.length"].value;
	    	var wide = document.forms[0].elements["location.wide"].value;
	    	var hight = document.forms[0].elements["location.hight"].value;
	    	var volume=(parseFloat(length)*parseFloat(wide)*parseFloat(hight)).toFixed(2);
    		document.forms[0].elements["location.volume"].value=volume;
    		document.forms[0].elements["volume"].value=volume;
    	}
    }
	window.onload= function(){
  	//仓库级联库区
  	<#if location.warehouse?exists>
			getObjByName('warehouse').value='${location.warehouse.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	//获得仓库的值后触发DWR 级联库区 
			WareCascadeRegionalDWR("warehouse","regional","${action.getText('')}","edit")
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
	    <#else>
	    	<#if req.getParameter('warehouse')?exists>
	    		getObjByName('warehouse').value='${req.getParameter('warehouse')?if_exists}';
	    		//设置同步
	    		DWREngine.setAsync(false); 
	    		//获得仓库的值后触发DWR 级联库区 
				WareCascadeRegionalDWR("warehouse","regional","${action.getText('')}","edit");
	    		//重新设置为异步方式
	    		DWREngine.setAsync(true); 
	    	</#if>
    </#if>
    //库区
	    <#if location.regional?exists>
		    getObjByName('regional').value='${location.regional.id}';
		    <#elseif req.getParameter('regional')?exists>
		    getObjByName('regional').value='${req.getParameter('regional')}';
	    </#if>
	//库位类型
	    <#if location.locationType?exists>
		    getObjByName('locationType').value='${location.locationType.id}';
		    <#elseif req.getParameter('locationType')?exists>
		    getObjByName('locationType').value='${req.getParameter('locationType')}';
	    </#if>
	//承载类型 
	    <#if location.bearload?exists>
		    getObjByName('bearload').value='${location.bearload.id}';
		    <#elseif req.getParameter('bearload')?exists>
		    getObjByName('bearload').value='${req.getParameter('bearload')}'; 
	    </#if>
	//混放模式 
	    <#if location.mixmode?exists>
		    getObjByName('mixmode').value='${location.mixmode.id}';
		    <#elseif req.getParameter('mixmode')?exists>
		    getObjByName('mixmode').value='${req.getParameter('mixmode')}';  
	    </#if>
	//相同备件存放 
	    <#if location.samestore?exists>
		    getObjByName('samestore').value='${location.samestore.id}';
		    <#elseif req.getParameter('samestore')?exists>
		    getObjByName('samestore').value='${req.getParameter('samestore')}'; 
	    </#if>
	//状态��
		<#if location.status?exists>
		  	if('${location.status}'=="USED"){
		  		document.forms[0].elements["location.status"].value='${action.getText('USED')}';
		  	}else{
		  		document.forms[0].elements["location.status"].value='${action.getText('NON_USE')}';
		  	}
		</#if>
    }
	function validate(){
		//验证库位编号
		if(document.forms[0].elements["location.code"].value==""){
			alert('${action.getText('location.code.requiredstring')}');
			return false;
		}
		if(document.forms[0].elements["location.code"].value.length>50){
			alert('${action.getText('location.code.stringlength')}');
			return false;
		}
	    //验证仓库
	    //加入判断仓库不为空的情况，在页面刚加载的时候，如果没有可用的仓库则仓库列表的值为空 hjia 2010-5-19
	    if(document.forms[0].elements["warehouse"].value == -1 || document.forms[0].elements["warehouse"].value== "") {
	  		alert('${action.getText('location.warehouse.requiredstring')}');
			return false;
		}
		
		//验证库区
		//加入判断库区不为空的情况，在页面刚加载的时候，如果没有可用的库区则库区列表的值为空 hjia 2010-5-19
	    if(document.forms[0].elements["regional"].value == -1 || document.forms[0].elements["regional"].value == "") {
	  		alert('${action.getText('location.regional.requiredstring')}');
			return false;
		}
		//验证库位类型
	    if(document.forms[0].elements["locationType"].value== -1) {
	  		document.forms[0].elements["locationType"].value="";
		}
		//楠验证载重类型
	    if(document.forms[0].elements["bearload"].value== -1) {
	  		document.forms[0].elements["bearload"].value="";
		}
		//验证混放模式
	    if(document.forms[0].elements["mixmode"].value== -1) {
	  		document.forms[0].elements["mixmode"].value="";
		}
		//验证相同备件存放
	    if(document.forms[0].elements["samestore"].value== -1) {
	  		document.forms[0].elements["samestore"].value="";
		}
		

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
	      //验证编码长度�
	      if(!isValidLength(document.forms[0], "location.code", 1, 50)){
				alert("${action.getText('location.code.error')}");
				return  false;
		  }
		  //验证最大载重�
	      if(maxWeight!=null&&maxWeight!=""){
	        if (!isDoubleNumberCheck(maxWeight)){   			
		         		alert("${action.getText('location.maxWeight.error')}");
		         		return false;
	       			}else if(!isNumberBetween(maxWeight, 1000000001, 0)){	
	       				alert("${action.getText('location.maxWeight.error')}");
	       				return false;
	       			}
	       }
	      //验证长度�
	      if(length!=null&&length!=""){
	        if (!isDoubleNumberCheck(length)){
		         		alert("${action.getText('location.length.error')}");
		         		return false;
	       			}else if(!isNumberBetween(length, 1000000001, 0)){	
	       				alert("${action.getText('location.length.error')}");
	       				return false;
	       			}
	       } 

	      //验证宽度�
	      if(wide!=null&&wide!=""){
	        if (!isDoubleNumberCheck(wide)){ 
		         		alert("${action.getText('location.wide.error')}");
		         		return false;
	       			}else if(!isNumberBetween(wide, 1000000001, 0)){	
	       				alert("${action.getText('location.wide.error')}");
	       				return false;
	       			}
	       }
	     //验证高度�
	      if(hight!=null&&hight!=""){
	        if (!isDoubleNumberCheck(hight)){   			
		         		alert("${action.getText('location.hight.error')}");
		         		return false;
	       			}else if(!isNumberBetween(length, 1000000001, 0)){	
	       				alert("${action.getText('location.hight.error')}");
	       				return false;
	       			}
	       }
	      
	      return true;
     }
</script>