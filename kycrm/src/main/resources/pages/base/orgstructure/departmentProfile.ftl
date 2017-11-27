
<#include "../../includes/hco2011.ftl" />
<@fsPage title="${action.getText('department.title1')}">
     <@ww.form  name="'/orgstructure'" action="'saveDepartment'" method="'post'">
       <@ww.token name="saveDepartmentProfileToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@inputTable>
            <#if department.id?exists>
                <@ww.hidden name="'department.id'" value="#{department.id}"/>
            </#if>
            <#if instId?exists>
            	<@ww.hidden name="'institution.id'" value="#{instId}"/>
            <#else>
            	<#if parentId?exists>
	        		<@ww.hidden name="'parentid'" value="#{parentId}"/>
	        	</#if>
            </#if>
            <@ww.hidden name="'availableUserIds'" value=""/>
            <@ww.hidden name="'join'" value=""/>
            <@ww.hidden name="'grantedUserIds'" value=""/>
            <@ww.hidden name="'department.version'" value="#{department.version?if_exists}"/>
            <@ww.hidden name="'department.instId'" value="${req.getParameter('department.instId')?if_exists}"/>
            <@ww.hidden name="'department.pDeptId'" value="${req.getParameter('department.pDeptId')?if_exists}"/>
            
             <tr>
             	<#if department.new>
                 <@ww.textfield label="'${action.getText('deparment.code')}'" name="'department.code'" value="'${department.code?if_exists}'" cssClass="'underline'" required="true"/>
                <#else>
                	<@ww.textfield label="'${action.getText('deparment.code')}'" name="'department.code'" value="'${department.code?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" required="true"/>
                </#if>
                 <@ww.textfield label="'${action.getText('department.name')}'" name="'department.name'" value="'${department.name?if_exists}'" cssClass="'underline'" required="true"/>
             </tr>
             <tr>
				<@ww.textfield label="'${action.getText('department.leader')}'" name="'department.leader'" value="'${department.leader?if_exists}'" required="true" cssClass="'underline'"/>
             	<@ww.textfield label="'${action.getText('department.tel')}'" name="'department.tel'" value="'${department.tel?if_exists}'"  cssClass="'underline'"/>
             </tr>
         </@inputTable>
         
         <@buttonBar>
         <#if !(action.isReadOnly())>
		     <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>   
		     <#if department.id?exists>
		         <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('department.info')}?" />
	             <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
	                <#--<@ww.param name="'disabled'" value="${isParent?if_exists}"/>-->
	             </@vsubmit>
	             <@redirectButton value="${action.getText('添加下属部门')}" url="editDepartment.html?parentid=#{department.id}"/>
             </#if>
	     </#if>
         </@buttonBar>
         <#--
          <#if !department.new>
       <tr class="input">
         <td>
           <table style="text-align: center;width: 100%;">
             <tr>
                <td class="title" width="100%">
                    鐢ㄦ埛鐨勫姞鍏ヤ笌绂诲紑
                </td>
              </tr>
           </table>
         </td>
       </tr>
        <tr class="input">
          <td>
            <table class="defaultLook" width="100%">
              <tr width="100%">
                <td style="text-align:center" width="100%"><b>宸叉巿浜堢殑鐢ㄦ埛</b></td>
              </tr>
              <tr width="100%">
                <td style="VERTICAL-ALIGN:top;text-align:center;position:relative;top:0px;right:0px" width="100%">
                  
                  <select name="grantedUser.id" multiple size="20" style="width:100px">
                     <#list disUsers as user>
                      <option value="#{user.id}">${user.name}</option>
                    </#list>
                  </select>
                  <a href="javascript:user_OpenDialog();" style="position:absolute;top:130px;right:280px">
                    <img title="閫夋嫨鐢ㄦ埛" name="multiuserImage" src="${req.contextPath}/images/icon/files.gif" hspace=0 height=16 width=16 border=0 align=absmiddle style="margin-left:5px;" />
                  </a>
                  <@vsubmit name="'leave'" value="'${action.getText('绂诲紑->')}'" onclick="'return selectGrantedUser();'"/>
                  

                  <@htmlButton name="submit0"  style="position:absolute;top:180px;right:242px" value="寮�濮嬫帓搴�" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return keyDown();"/>
                  <@htmlButton name="submit01"  style="position:absolute;top:180px;right:242px; display=none" value="缁撴潫鎺掑簭" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return finish();"/>
                  <@htmlButton name="submit1"  style="position:absolute;top:205px;right:250px;display=none" value="涓婄Щ鈫�" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return Moveup();"/>
				  <@htmlButton name="submit2"  style="position:absolute;top:230px;right:250px;display=none" value="涓嬬Щ鈫�" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return Movedown();"/>

                </td>
              </tr>  
            </table>
          </td>
        </tr>
      	<#list department.users as user>
          <input type="hidden" name="filterUserIds" value="#{user.id}"/>
        </#list>
    </#if>
    -->
     </@ww.form>
     <script>
     	
       function confirmDelete(msg){
			var param = confirm(msg);
			if(param){
				parent.frames["dTreeFrame"].location.reload();
			}
			return param;
		}
       function storeValidation(){
	        if(document.getElementById("department.code").value==''){
		        alert('${action.getText('department.code.not.null')}');
		        return false;
		     }else{
		        if(!isValidLength(document.forms[0], "department.code", null, 20)){
					alert("${action.getText('department.code.length')}");
					return  false;
				   }
			}
			if(document.getElementById("department.name").value==''){
		        alert('${action.getText('department.name.not.null')}');
		        return false;
		     }else{
		        if(!isValidLength(document.forms[0], "department.name", null, 20)){
					alert("${action.getText('department.name.length')}");
					return  false;
				}   
			}

			if(document.getElementById("department.leader").value==''){
		        alert('${action.getText('department.leader.not.null')}');
		        return false;
		     }else{
		        if(!isValidLength(document.forms[0], "department.leader", null, 20)){
					alert("${action.getText('department.leader.length')}");
					return  false;
				   }   
			 }   
 
			if(document.getElementById("department.tel").value != ''){
			   var str = document.getElementById("department.tel").value
		       if(str.length>20){
		       		alert('${action.getText('department.tel.length')}');
		        	return false;
		       }
		     
			}
			parent.frames["dTreeFrame"].location.reload();
			return true;
      }
      	<#--
        //鐢ㄦ埛鐨勯�夋嫨
        function user_OpenDialog() {
          var url = "${req.contextPath}/popup/userSelector.html?multipleSelect=T";
          var filterUserIds = document.getElementsByName("filterUserIds");
	      var ary = new Array();
	      for (var i=0; i<filterUserIds.length; i++) {
	        ary.push(filterUserIds[i].value);
	      }
	      if (null != ary) {
	        url = url + '&filterUserIds=' + ary;
	      }
	      popupModalDialog(url, 800, 600, userSelectorHandler);
        }
        function userSelectorHandler(result) {
          if (null != result) {
            result = result.substring(0, result.lastIndexOf(","));
            user = result.split(",");
            var ary = new Array();
            for (var i=0; i<user.length; i=i+2) {
              ary.push(user[i]);
            }
            document.forms["department"].elements["availableUserIds"].value = ary;
            document.forms[0].elements["join"].value = "join";
            document.forms[0].submit();
	      }
        }
        
         //鐐瑰嚮"绂诲紑"鎸夐挳锛岃Е鍙�
        function selectGrantedUser() {
          var ary = new Array();
          var selector = document.all("grantedUser.id");
          groups = selector.options.length;
          for (var i=0; i<groups; i++) {
            if (selector.options[i].selected) {
               ary.push(selector.options[i].value);
            }
          }
          document.forms["department"].elements["grantedUserIds"].value = ary;
          if ('' != document.forms["department"].elements["grantedUserIds"].value) {
            return true;   
          }
          return false;
        }
        -->
     <#--   
         //涓婄Щ瑙﹀彂鐨刯s
     function Moveup(){
         var ary = new Array();
         var listObj = document.all("grantedUser.id");
           //閬嶅巻鍒楄〃椤�
           for(var i=0;i<listObj.options.length; i++){
              if(listObj.options[i].selected && listObj.options[i] != ""&& listObj.options[i] != listObj.options[0]){ 
                 var tmpval1 = listObj.options[i].value;                    //鑾峰彇褰撳墠椤圭殑鍊�   
                 var tmpval12 = listObj.options[i].text;                    //鑾峰彇褰撳墠椤圭殑鏂囨湰   
                 listObj.options[i].value = listObj.options[i-1].value;   //鑾峰彇涓婁竴椤圭殑鍊�   
                 listObj.options[i].text = listObj.options[i-1].text      //鑾峰彇涓婁竴椤圭殑鏂囨湰   
                 listObj.options[i-1].value = tmpval1;                      //瀹炵幇涓婁笅鍊肩殑浜掓崲   
                 listObj.options[i-1].text = tmpval12;                      //瀹炵幇涓婁笅鏂囨湰鐨勪簰鎹�   
                 listObj.options[i].selected=false;   
                 listObj.options[i-1].selected=true; 
                 var departmentid=document.forms["department"].elements["department.id"].value;
                 upMoveAjax(listObj.options[i].value,listObj.options[i-1].value,departmentid);
                 break;  
              } 
           }
        }	
     //涓嬬Щ瑙﹀彂鐨刯s    
     function Movedown(){
         var listObj = document.all("grantedUser.id");
        //閬嶅巻鍒楄〃椤�
        for(var i = 0; i < listObj.options.length; i++) {   
         if (listObj.options[i].selected && listObj.options[i] != "" && listObj.options[i+1] != listObj.options[listObj.options.length]) {   
            var tmpval1 = listObj.options[i].value;                 //鑾峰彇褰撳墠椤圭殑鍊�   
            var tmpval12 = listObj.options[i].text;                 //鑾峰彇褰撳墠椤圭殑鏂囨湰   
            listObj.options[i].value = listObj.options[i+1].value; //鑾峰彇涓嬩竴椤圭殑鍊�   
            listObj.options[i].text = listObj.options[i+1].text     //鑾峰彇涓嬩竴椤圭殑鏂囨湰   
            listObj.options[i+1].value = tmpval1;               //瀹炵幇涓婁笅鍊肩殑浜掓崲   
            listObj.options[i+1].text = tmpval12;               //瀹炵幇涓婁笅鏂囨湰鐨勪簰鎹�   
            listObj.options[i].selected=false;   
            listObj.options[i+1].selected=true; 
            var departmentid=document.forms["department"].elements["department.id"].value;
            downMoveAjax(listObj.options[i].value,listObj.options[i+1].value,departmentid);  
            break;   
          }   
       }   
    }
    //涓婄Щ瑙﹀彂鐨凙jax鍑芥暟
    function upMoveAjax(id,id1,departmentid){
		 		createxmlhttprequest();
		 		var url = '${req.contextPath}/base/department/upMoveUser.html?id='+id+'&id1='+id1+'&departmentid='+departmentid;
		 		xmlhttpreq.open("get",encodeURI(url),true);
		 		xmlhttpreq.onreadystatechange = processresponse;
		 		xmlhttpreq.send(null);
		 }
	//涓嬬Щ瑙﹀彂鐨凙jax	 
    function downMoveAjax(id,id1,departmentid){
               createxmlhttprequest();
		 		var url = '${req.contextPath}/base/department/downMoveUser.html?id='+id+'&id1='+id1+'&departmentid='+departmentid;
		 		xmlhttpreq.open("get",encodeURI(url),true);
		 		xmlhttpreq.onreadystatechange = processresponse;
		 		xmlhttpreq.send(null);
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
	 function processresponse () {
		 	if (xmlhttpreq.readystate == 4) {
		 		if (xmlhttpreq.status == 200){
		 			
		 		}else {
		 			window.alert("椤甸潰璇锋眰鏈夊紓甯�")
		 		}
		 	}
		 } 
		 
 	function keyDown(){
       //鐐瑰嚮鎺掑簭鏃舵樉绀衡�滀笂绉烩�� 鈥滀笅绉烩�� 鎸夐挳
        document.getElementById("submit0").style.display='none';
        document.getElementById("submit01").style.display='inline';
        document.getElementById("submit1").style.display='inline';
	    document.getElementById("submit2").style.display='inline';
	    //鐐瑰嚮鎺掑簭鏃� 绂佹select澶氶��
	    var obj=document.all("grantedUser.id");
	    obj.multiple=false;
	   }
   function finish(){
        document.getElementById("submit0").style.display='inline';
        document.getElementById("submit01").style.display='none';
        document.getElementById("submit1").style.display='none';
	    document.getElementById("submit2").style.display='none';
	    var obj=document.all("grantedUser.id");
	    obj.multiple=true;
	}
	-->
  </script>
</@fsPage>
