<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('noticeProfile.title')}">
    <@ww.form namespace="'/notice'" name="'notice'" action="'saveReceviceNotice'" enctype="'multipart/form-data'" method="'post'">
        <@ww.token name="saveReceviceNotice"/>
        <@inputTable>
            <#if receviceNotice.id?exists>
                <@ww.hidden name="'receviceNotice.id'" value="#{receviceNotice.id}"/>
            </#if>
		  <tr>
		  <#--
		     <td align="right" valign="top">
		       <label class="label">${action.getText('notice.title')}:</label>
		     </td>
		     <td align="left">
		       
		        <input type="text" name="receviceNotice.title" class="underline"  value="${receviceNotice.title?if_exists}" size="34" style="width:525;FONT－FAMILY: 宋体;font-size:10pt;font-weight:900"/>
		     </td>
		     -->
		     <#--
		     <@ww.textfield label="'${action.getText('notice.title')}'" name="'receviceNotice.title'" value="'${receviceNotice.title?if_exists}'" 
		                    cssClass="'underline'"  size="'100'" required="false" cssStyle="FONT－FAMILY: 宋体;font-size:50pt;font-weight:900"/>
		     -->
		    <td align="right" valign="top">
		       <label class="label"></label>
		    </td>
		    <td align="center" style="FONT－FAMILY: 宋体;font-size:20pt;font-weight:900">
		      ${receviceNotice.title?if_exists}
		    </td>
		  </tr>
		  <tr>
		  </tr>
		  <tr>
		  </tr>
		  <tr>
		  <#--
		     <@ww.textarea  label="'${action.getText('notice.conent')}'" 
	        	            name="'receviceNotice.content'" 
	        	            value="'${receviceNotice.content?if_exists}'"  
	        	            rows="7" cols="100" cssClass="'underline'" />
	       -->
	       	<td align="right" valign="top">
		       <label class="label"></label>
		    </td>
		    <td align="left" style="FONT－FAMILY: 宋体;font-size:10pt;font-weight:200">
	       		<pre>${receviceNotice.content?if_exists}</pre>
	        </td>
		  </tr>
	      <tr>
		     <td align="right" valign="top">
		       <label class="label">${action.getText('notice.doc')}:</label>
		     </td>
		     <td align="left" style="border:1px">
		         <a   cssClass="underline"  title="${action.getText('download')}" href="downloadNoticeDoc.html?downloadDoc.id=#{receviceNotice.id?if_exists}" >
		             ${receviceNotice.name?if_exists}
		         </a>
		     </td>
	         <#--
	         <@ww.textfield label="'${action.getText('toolingChangeDoc.name')}'" size="50" name="'sendNotice.fileName'" value="'${sendNotice.fileName?if_exists}'" cssClass="'underline'" required="true"/>
	         -->
	      </tr>
        </@inputTable>
        <@ww.hidden name="'origFileName'" value=""/>
        <@buttonBar>
            <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/notice/listReceviceNotices.html"/>
        </@buttonBar>
    </@ww.form>
</@htmlPage>
<script type="text/javascript">
function getName() {
		      var filename = document.forms["notice"].elements["file"].value;
		      var ary = filename.split("\\");
		      var ary1 = ary[ary.length-1].split("\.");
		      document.forms["notice"].elements["sendNotice.fileName"].value=ary1[0];
		      document.forms["notice"].elements["origFileName"].value=ary[ary.length-1];
		      return true;
		    }
 function user_OpenDialog() {
          var url = "${req.contextPath}/popup/userSelector.html?multipleSelect=T";
	      popupModalDialog(url, 800, 600, userSelectorHandler);
        }
        function userSelectorHandler() {
          if (null != result) {
            user = result.substring(0, result.lastIndexOf(","));
            var userArray = user.split(",");
            var obj = getObjByNameRe("availableUser.id");
            for (var i=0; i<userArray.length; i=i+2) {
              var opt = new Option(userArray[i+1], userArray[i]);
		      eval("obj.options[obj.options.length]=opt");
            }
	      }

      } 
      
      function selectAvailableUser() {
      alert('ddddddddddddddddddddddddddddddd');
          var ary = new Array();
          var selector = document.all("availableUser.id");
          groups = selector.options.length;
          alert(groups);
          for (var i=0; i<groups; i++) {
              ary.push(selector.options[i].value);
          }
          document.forms["notice"].elements["availableUserIds"].value = ary;
        }
  </script>      