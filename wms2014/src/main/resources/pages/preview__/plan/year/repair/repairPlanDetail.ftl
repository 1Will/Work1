<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="维修详细">


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
 		document.all.frame.src='../../../device/repair/listPreRepairPlanItemDetails.html';
 		getObjByNameRe("preRepairItemDetails").className = "selectedtab";
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
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',750,400);
		}
	

</script>

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<@deviceSelector/>
            <tr>
            <@ww.textfield label="'使用部门'" name="'area.code'" value="" cssClass="'underline'" />
            <@ww.select label="'类别'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('')}', 
	                    			'${action.getText('大修')}', 
	                    			'${action.getText('项修')}' 
	                    	  	  }"
	                    	value="selectedDevice"
	        	/>
	        </tr>  
            <tr>
            	<@ww.textfield label="'承接单位'" name="'area.code'" value="" cssClass="'underline'" />
            	<@pp.datePicker label="'计划修理时间（月）'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15" />
            	
            </tr>
            <tr>
	            <@ww.select label="'复杂系数'"
		                    	name="device"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{'${action.getText('')}', 
		                    			'${action.getText('机械')}', 
		                    			'${action.getText('电器')}' 
		                    	  	  }"
		                    	value="selectedDevice"
		        	/>
	        	<@ww.textfield label="'停歇天数'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>
	 		<tr>
            	 <@pp.datePicker label="'预计开工时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15" />
	     		<@pp.datePicker label="'预计完工时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15" />
	     	</tr>
	     	<tr>
            	<@ww.textfield label="'预计材料费用'" name="'area.code'" value="" cssClass="'underline'" />
            	<@ww.textfield label="'预计维修费用'" name="'area.code'" value="" cssClass="'underline'" />
	     	</tr>
	 		<tr>
	 			<@ww.textfield label="'备注'" name="'area.code'" value="" cssClass="'underline'"  size="50"/>
	 		</tr>
	 	</@inputTable>
	 </@ww.form>
	 
	 
	 <ul id="beautytab">	 
	 	<#if !req.getParameter("proc")?exists>
	 		<li><a id="preRepairItemDetails" onclick="activeTab(this)" href="../../../plan/year/repair/listRepairPlanItemDetails.html" target="frame">维修明细</a></li>
			<li><a id="preRepairSpareItems" onclick="activeTab(this)" href="../../../plan/year/repair/listRepairPlanSpare.html" target="frame">备件明细</a></li>	 
			<li><a id="preRepairRuleFee" onclick="activeTab(this)" href="../../../plan/year/repair/listRepairFee.html" target="frame" >费用明细</a></li>	  
			<li><a id="preRepairRuleManHour" onclick="activeTab(this)" href="../../../plan/year/repair/listRepairManHour.html" target="frame" >工时明细</a></li>	  
			<li><a id="preRepairRuleTool" onclick="activeTab(this)" href="../../../plan/year/repair/listRepairTool.html" target="frame" >工具明细</a></li>	  
		<#else>
			<li><a id="preRepairItemDetails" onclick="activeTab(this)" href="../../../plan/year/repair/listRepairPlanItemDetails.html?proc=1" target="frame">维修明细</a></li>
			<li><a id="preRepairSpareItems" onclick="activeTab(this)" href="../../../plan/year/repair/listRepairPlanSpare.html?proc=1" target="frame">备件明细</a></li>	 
			<li><a id="preRepairRuleFee" onclick="activeTab(this)" href="../../../plan/year/repair/listRepairFee.html?proc=1" target="frame" >费用明细</a></li>	  
			<li><a id="preRepairRuleManHour" onclick="activeTab(this)" href="../../../plan/year/repair/listRepairManHour.html?proc=1" target="frame" >工时明细</a></li>	  
			<li><a id="preRepairRuleTool" onclick="activeTab(this)" href="../../../plan/year/repair/listRepairTool.html" target="frame" >工具明细</a></li>	  
		</#if>
		<#--
		<li><a id="preRepairRuleTool" onclick="activeTab(this)" href="../../device/repair/listPreRepairRuleTool.html" target="frame" >工具明细</a></li>	  
		-->
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   
   
</@htmlPage>