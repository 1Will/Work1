<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('权限仓库选择')}">
<table class="defaultLook">
<input type="hidden" id="userId" name="userId" value="#{user.id?if_exists}"/>
<input type="hidden" id="ids" name="ids" value=""/>
<a id="reload" href="${req.contextPath}/warehouseSelector/warehouseSelector.html?userId=#{user.id}" style="display:none"></a>
  <tr width="100%">
	<td style="text-align:center" width="40%"><b>可选权限仓库<b></td>
	<td width="20%"></td>
	<td style="text-align:center" width="40%"><b>已选权限仓库</b></td>
  </tr>
   <tr width="100%">
                <td style="VERTICAL-ALIGN:top;text-align:center" width="40%">
                  <select name="availableWarehouse.id" multiple size="20" style="width:150px">
                    <#list joinableWarehouses as joinableWarehouse>
                      <option value="#{joinableWarehouse.id}">${joinableWarehouse.name}</option>
                    </#list>
                  </select>
                </td>
                <td width="10%">
                  <br><input name="join" type="button" value="加入&gt;" size="10" onclick="return selectAvailableWarehouse();"></br>
                  <br><input name="leave" type="button" value="&lt;退出" size="10" onclick="return selectGrantedWarehouse();"></br>
                </td>
                <td style="VERTICAL-ALIGN:top;text-align:center" width="40%">
                  <select name="grantedWarehouse.id" multiple size="20" style="width:150px">                	  
                    <#list user.warehouses as ware>
                      <option value="${ware.id}">${ware.name}</option>
                    </#list>
                  </select>
                </td>
  </tr>
  <tr><td colspan=3>
  <input type="button" class="MyButton" value="关闭" onclick="window.close()">
  </td></tr>
</table>
</body>
<script type="text/javascript">
		function selectAvailableWarehouse(){
			var ids = new Array();
			var selector = document.all("availableWarehouse.id");
          	groups = selector.options.length;
            for (var i=0; i<groups; i++) {
            	if (selector.options[i].selected) {
              	ids.push(selector.options[i].value);
            	}
            }
			if(ids ==""){
				alert("请选择需要加入的仓库");
				return false;
			}
			//alert('ids='+ids);
			$('ids').value=ids;
			
			//createxmlhttprequest();
			var url="${req.contextPath}/warehouseSelector/addWarehouse.html";
			//alert("url="+url);
			var param = "ids="+ids+"&userId="+$('userId').value;
			//alert('param='+param);		
			//xmlhttpreq.open("get",encodeURI(url),false);
 			//xmlhttpreq.onreadystatechange = callback;
 			//xmlhttpreq.send(null);
 			var ajaxR = new Ajax.Request(
 			             url,
 			             {
 			              method:'get',
 			              parameters:param,
 			              onComplete:callback,
 			              asynchronous:false
 			             }
 			            );
		        	//$('reload').click();
		}
		
		function selectGrantedWarehouse(){
			var ids = new Array();
			var selector = document.all("grantedWarehouse.id");
          	groups = selector.options.length;
            for (var i=0; i<groups; i++) {
            	if (selector.options[i].selected) {
              	ids.push(selector.options[i].value);
            	}
            }
			if(ids ==""){
				alert("请选择需要退出的仓库");
				return false;
			}
			//alert('ids='+ids);
			$('ids').value=ids;
			
			//document.forms[0].submit();
			
			//createxmlhttprequest();
			var url="${req.contextPath}/warehouseSelector/quitWarehouse.html";
			var param = "ids="+ids+"&userId="+$('userId').value;
			//xmlhttpreq.open("get",encodeURI(url),false);
 			//xmlhttpreq.onreadystatechange = callback;
 			//xmlhttpreq.send(null);
 			 var ajaxR = new Ajax.Request(
 			             url,
 			             {
 			              method:'get',
 			              parameters:param,
 			              onComplete:callback,
 			              asynchronous:false
 			             }
 			            );	
 					
		}
		function createxmlhttprequest(){
			 	if (window.XMLHttpRequest) {
			 		xmlhttpreq = new XMLHttpRequest();
			 	}else if (window.ActiveXObject) {
			 		try {
			 			xmlhttpreq = new ActiveXObject("Msxml2.XMLHTTP");
			 		}catch (e) {
			 			try {
			 				xmlhttpreq = new ActiveXObject("microsoft.XMLHTTP");
			 			}catch (e) {}
			 		}
			 	}
		}
		
		function callback()   
		{   
		   alert('选择成功！');
		   $('reload').click();
		}  	
</script>
</@htmlPage>