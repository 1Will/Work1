<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />

<@htmlPage title="工作审批">

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
 		document.all.frame.src='listAuditHistories.html';
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

	function sampleDocDetail_openDialog() {
		popupModalDialog('${req.contextPath}/workspace/sampleDocument.html',600,350);
	}

</script>

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  	   <tr>
	 	  	  	<@ww.textfield label="'文档类型'"  name="'area.code'" value="'0001'" cssClass="'underline'"  readonly="true"/> 		
	 	  	  	<@ww.textfield label="'文档编号'"  name="'area.code'" value="'0001'" cssClass="'underline'"  readonly="true"/> 		
	 	  	  	<@ww.textfield label="'文档名称'"  name="'area.code'" value="'申购计划'" cssClass="'underline'" readonly="true"/> 		
	 	  	  </tr>
	 	  	  <tr>
	 			<@ww.textfield label="'部门'"  name="'area.code'" value="'机动部'" cssClass="'underline'"  readonly="true"/> 		
	 	  	  	<@ww.textfield label="'提交人'"  name="'area.code'" value="'TOM'" cssClass="'underline'"  readonly="true"/> 
	 	  	  	<@ww.textfield label="'提交时间'"  name="'area.code'" value="'2006-07-01'" cssClass="'underline'"  readonly="true"/>    
	 	  	  </tr>		
	 	  	  <tr>
            	<@ww.textarea label="'审批意见'" 
              				   name="'organization.remark'" 
                               value="" rows="'5'" cols="'50'" 
				               required="false"/>
				               
				 <td align="right" valign="top"><label class="label">选项:</label>
					 <td valign="top"><input type="radio" name="checkbox" value="true">同意
					 <input type="radio" name="checkbox" value="true">拒绝</td>
				 </td>
	 	  	  <#--	<@ww.select  label="'选项'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'', 
		                    			'${action.getText('同意')}', 
		                    			'${action.getText('拒绝')}'
		                    	  	  }"
		                    	value="selectedDevice"
		                    	/>-->
	 	  	  </tr>     
		</@inputTable>
	 	  <@buttonBar>
	        	<@vbutton class="button" value="提交" onclick="javascript:void(0);"/>
	        	<@vbutton class="button" value="文档详细" onclick="sampleDocDetail_openDialog();"/>
	        	<@redirectButton  value="返回" url="listUnAuditWorks.html"/>
	     </@buttonBar>
	</@ww.form>
	
	<ul id="beautytab">
		<li><a id="planItems" onclick="activeTab(this);" href="listAuditHistories.html" target="frame" class="selectedtab">审批历史</a></li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	
</@htmlPage>