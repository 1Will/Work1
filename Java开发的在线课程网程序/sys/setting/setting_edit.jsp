<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.sys.bo.SysSettingInfo"%>
<%@page import="com.bzt.sys.util.Constants"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>folder_edit</TITLE>

<%@ include file="/public/jsp/meta.jsp"%>
<%@ include file="/public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<script type="text/javascript">
	function saveRecord(){
	  obj = document.all("sysSettingInfoActionForm");
	  if(autoCheckForm(obj)==false){
	    return false;
	  }
	  
	  obj.action="/sysSettingInfoAction.do?method=updateSave";
	  obj.submit();
	}
</script>
</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">积分财富规则</TD>
  </TR>
  <TR>
    <TD vAlign=top align=middle>
      <html:form action="/sysSettingInfoAction.do" method="post" >
       <TABLE width="530" border=0 cellpadding=2 cellspacing=2 align="center">
       		<tr>
              <td colspan="2" style="text-align:left;color:#000;">
                 文库积分财富规则：<br/>
               &nbsp;&nbsp;&nbsp;&nbsp;一、标价非0积分财富值文档被别人下载<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、积分财富:+标价/被下载1次，<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;每被下载1次，用户获得：系统奖励1分(具体根据以下设置为准)。<br/>	
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、每份文档可以通过文档被下载获得积分的总上限为500分。 <br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、每份文档可以获得积分奖励的总上限为1000分。<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;二、标价为0积分财富值文档被别人下载<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、积分财富:每被下载1次，用户获得：系统奖励1分(具体根据以下设置为准)。<br/>	
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、每份文档可以通过文档被下载获得积分奖励的总上限为500分。 <br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、每份文档可以获得积分奖励的总上限为1000分。<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;三、文档被收藏<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、当文档的收藏量大于100次时，一次性奖励50分。<br/>	
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、当文档的收藏量大于100次时，每增加一次收藏系统奖励1分(具体根据以下设置为准)。<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、每份文档可以通过文档被收藏获得积分的总上限为300分。 <br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、每份文档可以获得积分奖励的总上限为1000分。<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;四、当某个文档资源被管理员删除时，上传者从该文档资源获得的所有积分财富将都被扣除。<br/><br/>
			   微课积分财富规则：<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;五、标价非0积分财富值视频被别人下载<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、积分财富:+标价/被下载1次，<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;每被下载1次，用户获得：系统奖励1分(具体根据以下设置为准)。<br/>	
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、每个视频可以通过视频被下载获得积分的总上限为500分。 <br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;六、标价为0积分财富值视频被别人下载<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、积分财富:每被下载1次，用户获得：系统奖励1分(具体根据以下设置为准)。<br/>	
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、每个视频可以通过视频被下载获得积分奖励的总上限为500分。 <br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;七、微课被收藏<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、当微课的收藏量大于100次时，一次性奖励50分。<br/>	
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、当微课的收藏量大于100次时，每增加一次收藏系统奖励1分(具体根据以下设置为准)。<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、每个微课可以通过微课被收藏获得积分的总上限为300分。 <br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、每个微课可以获得积分奖励的总上限为2500分。<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;八、当某个微课被管理员删除时，上传者从该微课获得的所有积分财富将都被扣除。
           </td>
           </tr>
       	  <%
       	  	List list = (List)request.getAttribute("settinglist");
       	    String value = null;
       	  	for(int i=0; i<list.size(); i++){
       	  		SysSettingInfo model = (SysSettingInfo)list.get(i);
       	  		value = Constants.getCodeTypevalue("settingtype", model.getType());
       	  		if(value != null && !"".equals(value)){
       	  %>
          <tr>
            <td class="table_edit_right"><%=Constants.getCodeTypevalue("settingtype", model.getType()) %>：</td>
            <td class="table_edit_left">
            	<span style="display:none;">积分&nbsp;<a style="color:green;">+</a><input name="jifen<%=model.getType() %>" type="text" style="width:50px;padding-left:2px;" CK_NAME="<%=value %>积分" CK_TYPE="NotEmpty,Number" value='<%=model.getJifen() %>'></span>
            	积分财富值&nbsp;<a style="color:green;">+</a><input name="caifu<%=model.getType() %>" type="text" style="width:50px;padding-left:2px;" CK_NAME="<%=value %>财富" CK_TYPE="NotEmpty,Number" value='<%=model.getCaifu() %>'>
            </td>
          </tr>
          <%}} %>
          <tr>
              <td colspan="2" height="40" align="center">
                 <input type="button" value="保存" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
         </td>
        </tr>

       </TABLE>
      </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
