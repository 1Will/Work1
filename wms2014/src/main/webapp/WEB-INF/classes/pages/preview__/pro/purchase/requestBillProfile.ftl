<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="申购单维护">



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
 		document.all.frame.src='../../pro/purchase/listRequestBillItems.html';
 		document.getElementById("ruleItems").className = "selectedtab";
 	}
 	
	function activeTab(obj) {
	    var sfEls = document.getElementById("beautytab").getElementsByTagName("li");
	    for (var i=0; i<sfEls.length; i++) {
	    	//alert(sfEls[i].getElementsByTagName("a")[0].id == obj.id);
	      if (sfEls[i].getElementsByTagName("a")[0].id == obj.id) {
	        sfEls[i].getElementsByTagName("a")[0].className = "selectedtab";
	      } else {
	        sfEls[i].getElementsByTagName("a")[0].className = '';
	      }
	    }
	}
	
	function budget_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/budgetSelector.html',600,350);
	}
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',600,300);
	}	

</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'申购单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'申购单名称'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'申购人'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
                <@pp.datePicker label="'申购日期'" name="'repair.estimate.time0'" required="true"
	     			value="" cssClass="'underline'" size="15"/>
            </tr>
            <@budgetSelector/>   
            <tr>
            	<@ww.select label="'部门'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'所有',		
	                    	'机动部', 
	                    	'技术部',
	                    	'品管部',
	                    	'生产部',
	                    	'冲压'
	                    }"
	                 value="selectedDevice"
	      		/>  
	      	</tr>
            <tr>
                <@ww.textarea  label="'申购原因'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	        	         
                <@ww.textarea  label="'备注'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
            </tr>
        </@inputTable>

        
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="listRequestBillsDEVICE.html"/>	
        	<@redirectButton value="${action.getText('back')}" url="listRequestBillsDEVICE.html"/>	
        </@buttonBar>
    </@ww.form>
    
	<ul id="beautytab">
		<li><a id="ruleItems" onclick="activeTab(this)" href="../../pro/purchase/listRequestBillItems.html" target="frame" >申购单明细</a></li>	    		  
		<li><a id="attachment" onclick="activeTab(this)" href="../../pro/purchase/listRequestBillAttachs.html" target="frame" >申购单附件</a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>