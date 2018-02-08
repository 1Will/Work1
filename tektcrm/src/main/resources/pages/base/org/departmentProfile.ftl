
<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('department.title1')}">
     <@ww.form  name="'department'" action="'saveDepartment'" method="'post'" validate="true">
       <@ww.token name="saveDepartmentProfileToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@inputTable>
            <#if department.id?exists>
                <@ww.hidden name="'department.id'" value="#{department.id}"/>
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
                 <@ww.select label="'${action.getText('parent.department')}'" required="false" name="'parent.department'"  listKey="id" listValue="name"
                             list="parentDepts" emptyOption="false" disabled="false">                  
      		     </@ww.select>
	      		 <script language="javascript">
	      			<#if department.parentDept?exists>
	      				document.forms[0].elements["parent.department"].value = #{department.parentDept.id};
	      		    <#elseif !department.new>
	      		        document.forms[0].elements["parent.department"].value = '0';
	      			</#if>
	      		 </script>
                  
                  <@ww.select label="'${action.getText('department.inst')}'" required="false" name="'institution.id'" listKey="id" listValue="name"
                             list="parentInsts" emptyOption="true" disabled="false" required="true">                  
      		     </@ww.select>
      		     <script language="javascript">
	      			<#if department.inst?exists>
	      				document.forms[0].elements["institution.id"].value = #{department.inst.id};
	      			</#if>
	      		 </script>
             </tr>
             <tr>
				<@ww.textfield label="'${action.getText('department.leader')}'" name="'department.leader'" value="'${department.leader?if_exists}'" required="true" cssClass="'underline'"/>
             	  <@ww.textfield label="'${action.getText('department.tel')}'" name="'department.tel'" value="'${department.tel?if_exists}'"  cssClass="'underline'"/>
             </tr>
         </@inputTable>
         
         <@buttonBar>
         <#if !(action.isReadOnly())>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	     </#if>
	         <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/department/listDepartments.html"/>
         </@buttonBar>
         <#--
          <#if !department.new>
       <tr class="input">
         <td>
           <table style="text-align: center;width: 100%;">
             <tr>
                <td class="title" width="100%">
                    用户的加入与离开
                </td>
              </tr>
           </table>
         </td>
       </tr>
        <tr class="input">
          <td>
            <table class="defaultLook" width="100%">
              <tr width="100%">
                <td style="text-align:center" width="100%"><b>已授予的用户</b></td>
              </tr>
              <tr width="100%">
                <td style="VERTICAL-ALIGN:top;text-align:center;position:relative;top:0px;right:0px" width="100%">
                  
                  <select name="grantedUser.id" multiple size="20" style="width:100px">
                     <#list disUsers as user>
                      <option value="#{user.id}">${user.name}</option>
                    </#list>
                  </select>
                  <a href="javascript:user_OpenDialog();" style="position:absolute;top:130px;right:280px">
                    <img title="选择用户" name="multiuserImage" src="${req.contextPath}/images/icon/files.gif" hspace=0 height=16 width=16 border=0 align=absmiddle style="margin-left:5px;" />
                  </a>
                  <@vsubmit name="'leave'" value="'${action.getText('离开->')}'" onclick="'return selectGrantedUser();'"/>
                  

                  <@htmlButton name="submit0"  style="position:absolute;top:180px;right:242px" value="开始排序" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return keyDown();"/>
                  <@htmlButton name="submit01"  style="position:absolute;top:180px;right:242px; display=none" value="结束排序" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return finish();"/>
                  <@htmlButton name="submit1"  style="position:absolute;top:205px;right:250px;display=none" value="上移↑" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return Moveup();"/>
				  <@htmlButton name="submit2"  style="position:absolute;top:230px;right:250px;display=none" value="下移↓" class=btn1_mouseout onmouseover="this.className='btn1_mouseover'" onmouseout="this.className='btn1_mouseout'"  onclick="return Movedown();"/>

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
     	if('' != getObjByName('department.instId').value){
			getObjByName('institution.id').value = getObjByName('department.instId').value;
		}
		if('' != getObjByName('department.pDeptId').value){
			getObjByName('parent.department').value = getObjByName('department.pDeptId').value;
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
			if ('' == document.forms[0].elements["institution.id"].value) {
			    alert("${action.getText('institution.id.requiredstring')}");
			  	return false;
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
			 if ('' == document.forms[0].elements["parent.department"].value) {
			    alert("${action.getText('parent.department.requiredstring')}");
			  	return false;
			}   
			if(document.getElementById("department.tel").value != ''){
			   var str = document.getElementById("department.tel").value
		       if(str.length>20){
		       		alert('${action.getText('department.tel.length')}');
		        	return false;
		       }
		     
			}
      }
        //用户的选择
        function user_OpenDialog() {
          var url = "${req.contextPath}/popup/userSelector.html?multipleSelect=T&readOnly=${req.getParameter('readOnly')?if_exists}";
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
         //点击"离开"按钮，触发
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
        
     <#--   
         //上移触发的js
     function Moveup(){
         var ary = new Array();
         var listObj = document.all("grantedUser.id");
           //遍历列表项
           for(var i=0;i<listObj.options.length; i++){
              if(listObj.options[i].selected && listObj.options[i] != ""&& listObj.options[i] != listObj.options[0]){ 
                 var tmpval1 = listObj.options[i].value;                    //获取当前项的值   
                 var tmpval12 = listObj.options[i].text;                    //获取当前项的文本   
                 listObj.options[i].value = listObj.options[i-1].value;   //获取上一项的值   
                 listObj.options[i].text = listObj.options[i-1].text      //获取上一项的文本   
                 listObj.options[i-1].value = tmpval1;                      //实现上下值的互换   
                 listObj.options[i-1].text = tmpval12;                      //实现上下文本的互换   
                 listObj.options[i].selected=false;   
                 listObj.options[i-1].selected=true; 
                 var departmentid=document.forms["department"].elements["department.id"].value;
                 upMoveAjax(listObj.options[i].value,listObj.options[i-1].value,departmentid);
                 break;  
              } 
           }
        }	
     //下移触发的js    
     function Movedown(){
         var listObj = document.all("grantedUser.id");
        //遍历列表项
        for(var i = 0; i < listObj.options.length; i++) {   
         if (listObj.options[i].selected && listObj.options[i] != "" && listObj.options[i+1] != listObj.options[listObj.options.length]) {   
            var tmpval1 = listObj.options[i].value;                 //获取当前项的值   
            var tmpval12 = listObj.options[i].text;                 //获取当前项的文本   
            listObj.options[i].value = listObj.options[i+1].value; //获取下一项的值   
            listObj.options[i].text = listObj.options[i+1].text     //获取下一项的文本   
            listObj.options[i+1].value = tmpval1;               //实现上下值的互换   
            listObj.options[i+1].text = tmpval12;               //实现上下文本的互换   
            listObj.options[i].selected=false;   
            listObj.options[i+1].selected=true; 
            var departmentid=document.forms["department"].elements["department.id"].value;
            downMoveAjax(listObj.options[i].value,listObj.options[i+1].value,departmentid);  
            break;   
          }   
       }   
    }
    //上移触发的Ajax函数
    function upMoveAjax(id,id1,departmentid){
		 		createxmlhttprequest();
		 		var url = '${req.contextPath}/base/department/upMoveUser.html?id='+id+'&id1='+id1+'&departmentid='+departmentid;
		 		xmlhttpreq.open("get",encodeURI(url),true);
		 		xmlhttpreq.onreadystatechange = processresponse;
		 		xmlhttpreq.send(null);
		 }
	//下移触发的Ajax	 
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
		 			window.alert("页面请求有异常")
		 		}
		 	}
		 } 
		 
 	function keyDown(){
       //点击排序时显示“上移” “下移” 按钮
        document.getElementById("submit0").style.display='none';
        document.getElementById("submit01").style.display='inline';
        document.getElementById("submit1").style.display='inline';
	    document.getElementById("submit2").style.display='inline';
	    //点击排序时 禁止select多选
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
</@htmlPage>
