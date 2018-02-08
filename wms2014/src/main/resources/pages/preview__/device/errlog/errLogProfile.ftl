<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="故障记录维护">

<style type="text/css" media="all">
#container
{//*设定包含列表的div的Box属性*//
width: 100%;
height: 100%;
/*padding: 30px;*/
border: 1px solid #ccc;
background: #fff;
}

#beautytab
{//*设定项目列表Ul元素的属性，其中background用来设定连贯于各个列表项目下的横线，否则它们会彼此分离，用了一张事先准备好的图片，让它放置在底部，水平重复*//
height: 20px;
margin: 0;
padding-left: 10px;
background: url('http://tech.tom.com/images/computer/2004/02/12/bottom.gif') repeat-x bottom;
}

#beautytab li
{//*设定各个列表项目的属性，display属性设定取消项目间的分行，list-style-type设定取消列表项目前的符号*//
margin: 0; 
padding: 0;
display: inline;
list-style-type: none;
}

#beautytab a:link, #beautytab a:visited
{//*设定标签卡中超链接的文字的属性*//
float: left;
background: #f3f3f3;
font-size: 12px;
line-height: 14px;
font-weight: bold;
padding: 2px 10px 2px 10px;
margin-right: 4px;
border: 1px solid #ccc;
text-decoration: none;
color: #666;
}

#beautytab a:link.selectedtab, #beautytab a:visited.selectedtab
{//*设定当前被选中的标签卡中超链接的属性*//
border-bottom: 1px solid #fff;
background-color: #fff;
color: #000;
}

#beautytab a:hover
{//*设定超链接鼠标浮动效果*//
background: #fff;
}



</style>

<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='listErrLogHistories.html';
 		getObjByNameRe("planItems").className = "selectedtab";
 	}
 	
	function activeTab(obj) {
	    var sfEls = getObjByNameRe("beautytab").getElementsByTagName("li");
	    for (var i=0; i<sfEls.length; i++) {
	    	//alert(sfEls[i].getElementsByTagName("a")[0].id == obj.id);
	      if (sfEls[i].getElementsByTagName("a")[0].id == obj.id) {
	        sfEls[i].getElementsByTagName("a")[0].className = "selectedtab";
	      } else {
	        sfEls[i].getElementsByTagName("a")[0].className = '';
	      }
	    }
	}
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',null,300);
	}
	function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',600,350);
	}
</script>

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  	  <tr>
	 	  	  	<@ww.textfield label="'故障记录编号'" name="'area.code'" value="'0001'" cssClass="'underline'" disabled="true" readonly="true" />
	 	  	  	<@ww.textfield label="'故障记录名称'" name="'area.code'" value="" cssClass="'underline'" required="true" />
	 	  	  </tr>
		 	  <@deviceSelector/>	 	 
		 	  <tr>
		 	  	<@pp.datePicker label="'故障开始时间'" name="'repair.estimate.time0'"
	     				value="" cssClass="'underline'" size="15" required="true"/>
	     		<@pp.datePicker label="'故障结束时间'" name="'repair.estimate.time0'"
	     				value="" cssClass="'underline'" size="15" required="true"/>
		 	  </tr>
		 	  <tr>
		 	  	<@ww.textfield label="'故障原因'" size="50" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  	<@ww.textfield label="'损失'" size="50" name="'area.code'" value="" cssClass="'underline'"/>
		 	  </tr>	
		 	   <@audit2/>	 	 
		 	  <tr>
		 	  	<@err1/>
		 	  	<@ww.textfield label="'备注'" size="50" name="'area.code'" value="" cssClass="'underline'" />
		 	  </tr>
	 	  </@inputTable>
	 	 
	 	  <@buttonBar>
	        	<@vbutton class="button" value="${action.getText('save')}" onclick="javascript:void(0);"/>
	        	<@vbutton class="button" value="提交" onclick="javascript:void(0);"/>
	        	<@redirectButton  value="返回" url="listErrLogs.html"/>
	     </@buttonBar>
	</@ww.form>
	
	<ul id="beautytab">
		<li><a id="planItems" onclick="activeTab(this);" href="listErrLogHistories.html" target="frame" class="selectedtab">故障历史记录</a></li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>