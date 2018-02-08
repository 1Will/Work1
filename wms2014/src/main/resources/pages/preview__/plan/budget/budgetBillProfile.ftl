<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="预算详细信息">

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

<@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@inputTable>
            <tr>
                <@ww.textfield label="'预算号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'预算名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'预算制定人'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'预算制定日期'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'预算实施部门'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'预算说明'" name="'area.code'" value="" size="50" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'预算状况'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'预算审核'" name="'area.code'" value="" size="50" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'预算费用'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'备注'" name="'area.code'" value="" size="50" cssClass="'underline'" readonly="true"/>
            </tr>
        </@inputTable>
      <@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="${req.contextPath}/plan/budget/listBudgePlanItems.html"/>	
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/plan/budget/listBudgePlanItems.html"/>	
        </@buttonBar>
    </@ww.form>
    
 </@htmlPage>