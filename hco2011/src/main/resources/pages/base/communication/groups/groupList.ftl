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
<#--$Id: groupList.ftl 6148 2007-07-31 01:41:34Z qsun $ -->
<#include "../../../includes/macros.ftl" />
<@htmlPage >
  <@ww.form namespace="'/communication/groups'" name="'listForm'" action="'searchGroups'" method="'get'">
     <@ww.token name="searchGroupsToken"/>
        <@inputTable>
            <@ww.hidden name="'nonParentGroup'" value="${req.getParameter('nonParentGroup')?if_exists}"/>
            <tr>
                <@ww.textfield label="'${action.getText('group.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
                <@ww.textfield label="'${action.getText('group.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
                <@ww.select label="'${action.getText('parent.group')}'" required="false" name="'parent.group'"  listKey="id" listValue="name"
                            list="parentGroups" emptyOption="false" disabled="false" required="false" value="'${req.getParameter('parent.group')?if_exists}'">                  
      		    </@ww.select>
            </tr>
            
        </@inputTable>
         <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/communication/groups/editGroup.html"/>
        </@buttonBar>
         <@list title="${action.getText('list.title')}" includeParameters="code|name|nonParentGroup|parent.group" fieldMap="like:code|name" >
            <@vcolumn title="">
                <input type="checkbox" name="groupIds" value="#{object.id}" width="30" />
                <@vlh.attribute name="width" value="30" />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('group.code')}">
                <a href="${req.contextPath}/communication/groups/editGroup.html?group.id=#{object.id}">${object.code}</a>
                <@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('group.name')}" property="name">
            <@alignLeft/>
            </@vcolumn>
            
            <#assign parentGroup=""/>
            <#if object.parentGroup?exists>
              <#assign parentGroup="${object.parentGroup.name}"/>
            <#else>
              <#assign parentGroup="无"/>
            </#if>
            <@vcolumn title="${action.getText('parent.group')}" property="parentGroup" sortable="desc">
              ${parentGroup}
              <@alignLeft/>
            </@vcolumn>
         </@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('group')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"groupIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>	 
            </@vsubmit>
        </@buttonBar>
      </#if>
<script type="text/javascript">
  var selector = document.all("parent.group");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('parent.group')?exists>
    if (selector.options[i].value == "${req.getParameter('parent.group')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  var inputField;
  var completeTable;
  var completeDiv;
  var completeBody;
  function inputGetValue(){
          inputField=document.getElementById("name");
        
          completeTable= document.getElementById("complete_table");
          completeDiv=document.getElementById("popup");
          completeBody=document.getElementById("complete_body");
		 <#-- var groupName=document.getElementById("name").value;-->
		 	if(inputField.value.length >0){
		 		createxmlhttprequest();
		 		var url = "${req.contextPath}/security/checkGroupName.html?groupName="+(inputField.value);
		 		encodeURI
		 		xmlhttpreq.open("get",encodeURI(url),true);
		 		xmlhttpreq.onreadystatechange = processresponse;
		 		xmlhttpreq.send(null);
		 	}else {
                clearNames();
             }
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
		 // 生成与输入内容匹配行
		 function setNames(groupName){
		 clearNames();
		 var size=groupName.length;
		 setOffsets();
		 var row,cell,txtNode;
		     for(var i=0;i<size;i++){
		      var nextNode=groupName[i].firstChild.data;
		      //alert(nextNode);
		      row=document.createElement("tr");
		      cell=document.createElement("td");
		      cell.onmouseout=function(){this.className='mouseOver';};
		      //alert(this.className);
		      cell.onmouseover=function(){this.className='mouseOut';}; 
		      cell.setAttribute("bgcolor","#FFFAFA");
		      cell.setAttribute("border","0");
		      cell.onclick=function(){completeField(this);};
		      txtNode=document.createTextNode(nextNode);
		      cell.appendChild(txtNode);
		      row.appendChild(cell);
		      completeBody.appendChild(row);
		     }
		 }
		 
		 //设置显示位置
		function setOffsets(){
		   completeTable.style.width=inputField.offsetWidth;+"px";
		   var left=calculateOffset(inputField,"offsetLeft");
		   var top =calculateOffset(inputField,"offsetTop")+inputField.offsetHeight;
		   completeDiv.style.border="black 1px solid";
		   completeDiv.style.left=left+"px";
		   completeDiv.style.top=top+"px";
		 }
		 //计算显示位置
		 function calculateOffset(field,attr){
		   var offset=0;
		  // alert(offset);
		   while(field){
		     offset+=field[attr];
		     //alert("offset " + offset);
		     field=field.offsetParent;
		   }
		   return offset;
		 }
		 //填写输入框
		 function completeField(cell){
		  inputField.value=cell.firstChild.nodeValue;
		 }
		 
		 //清除自动完成行
		 function clearNames(){
		 var ind =completeBody.childNodes.length;
		 for(var i=ind-1;i>=0;i--){
		 completeBody.removeChild(completeBody.childNodes[i]);
		 }
		 completeDiv.style.border="none";
		 }
		 function processresponse () {
		 	if (xmlhttpreq.readystate == 4) {
		 		if (xmlhttpreq.status == 200){
		 			var res = xmlhttpreq.responseXML.getElementsByTagName("res");
		 			if(res.length>0){
		 			// alert('dddd');
		 			<#-- document.getElementById("name").value=res[0].firstChild.data;-->
		 			setNames(xmlhttpreq.responseXML.getElementsByTagName("res"));
		 			}
		 		}else {
		 			window.alert("页面请求有异常")
		 		}
		 	}
		 }
		 function checkInvalidParms() {
		   document.forms[0].elements["nonParentGroup"].value = '';
		   //上级组选"所有"
		   if (-1 == document.forms[0].elements["parent.group"].value) {
		     document.forms[0].elements["parent.group"].value = '';
		   } else if (0 == document.forms[0].elements["parent.group"].value) {  //上级组选"无"
		     document.forms[0].elements["nonParentGroup"].value = 'true';
		     document.forms[0].elements["parent.group"].value = '';
		   }
		   return true;
		 }
	</script>
    </@ww.form>
</@htmlPage>
