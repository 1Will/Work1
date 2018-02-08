<#include "../../includes/macros2.ftl" />

<@htmlPage title="工装润滑实施明细维护">

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
 		document.all.frame.src='../../device/smoothInfo/listSmoothPlanImplItems.html';
 		getObjByNameRe("ruleItems").className = "selectedtab";
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
	
	function tooling_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/toolingSelector.html',600,350);
	}
	
	function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',600,400);
	}
	function checkPointPlan_openDialog() {
		popupModalDialog('${req.contextPath}/popup/toolSmoothPlanSelector.html',600,400);
	}
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html', null, 300);
	}

</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@buttonBar>
       		<@vbutton class="button" value="选择润滑计划" onclick="checkPointPlan_openDialog();"/>	
        </@buttonBar>
        <@inputTable>
             <tr>
                <@ww.textfield label="'润滑实施编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'润滑实施名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="true"/>
            </tr>
            <tr>    
                <@toolingSelector/>
            </tr>
            <tr>	
            	<@ww.textfield label="'实施部门'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            	<@ww.textfield label="'实施负责人'" name="'area.code'" value="" cssClass="'underline'" />
            </tr>
            <tr>
                 <@ww.textfield label="'实施创建日期'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            	 <@ww.textfield label="'备注'" name="'area.code'" size="50" value="" cssClass="'underline'" />
            </tr>
            <@audit2 />
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="${req.contextPath}/preview/tool/smoothInfo/listSmoothPlanImpls.html"/>	
        	<@vbutton class="button" value="提交" onclick="javascript:void(0);"/>
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/preview/tool/smoothInfo/listSmoothPlanImpls.html"/>	
        </@buttonBar>
    </@ww.form>
    
    
    
	<ul id="beautytab">
	    	<li><a id="ruleItems" href="../../device/smoothInfo/listSmoothPlanImplItems.html" target="frame" class="selectedtab">工装润滑实施明细</a></li>	    		  
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>

 
</@htmlPage>