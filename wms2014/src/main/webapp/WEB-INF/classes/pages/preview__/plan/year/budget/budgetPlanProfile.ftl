<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../includes/macros2.ftl" />
<@htmlPage title="设备预算计划维护">



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
 		document.all.frame.src='${req.contextPath}/preview/plan/year/budget/listBudgetPlanDetail.html';
 		document.getElementById("preRepairItems").className = "selectedtab";
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
	
		function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',null,300);
		}
		function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',750,400);
		}
	

</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>       
        <@inputTable>
        	<tr>
                <@ww.textfield label="'预算计划编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="false"/>
                <@ww.textfield label="'预算计划名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="true"/>
            </tr>
            <tr>
            </tr>
                <@dept1/>
                <@pp.datePicker readonly="true" label="'年度'" size="6" name="'month'" required="false" cssClass="'underline'" dateFormat="'%Y'" required="true"/> 
			<tr>
                <td align="right" valign="top"><label class="label"><font color="red">*</font>编制人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
                <@pp.datePicker label="'编制日期'"  name="'month'" required="false" value="'2007-12-23'" cssClass="'underline'"/> 
            </tr>  
            <tr>
                <@ww.textfield label="'总费用(元)'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="false"/>
            </tr>           
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="listPreRepairPlan.html"/>	
        	<@redirectButton value="${action.getText('back')}" url="listPreRepairPlan.html"/>	
        </@buttonBar>
     </@ww.form>
     <script>
	     function peopleMainManager_OpenDialog() {
		  		var url = "${req.contextPath}/popup/userSelector.html";
		  		popupModalDialog(url, 800, 600, userUnSealedSelectorHandler);
		 }	
		 
		function userUnSealedSelectorHandler(result) {
		  		document.forms[0].elements["alteration.unSealPeople"].value = result[1];
		  		document.forms[0].elements["alteration.unsealedPeopleDisplay"].value = result[1];
		}
         function changeSealedFaultState() {
    	   if (document.forms[0].elements["unusedAgree"].checked == true) {
			    document.getElementById("requestPeople").style.display='inline';
			    document.getElementById("requestIdea").style.display='inline';
	     }else{
			    document.getElementById("requestPeople").style.display='none';
			    document.getElementById("requestIdea").style.display='none';
	     }
	  }
     </script>
    <ul id="beautytab">
		<li><a id="preRepairItems" onclick="activeTab(this)" href="${req.contextPath}/preview/plan/year/budget/listBudgetPlanDetail.html" target="frame" class="selectedtab">计划明细</a></li>	    		  
		<#--
		<li><a id="preRepairSpareItems" onclick="activeTab(this)" href="../../device/repair/listRepairPlanSpare.html" target="frame">预防维修计划备件明细</a></li>	 
		<li><a id="preRepairRuleFee" onclick="activeTab(this)" href="../../device/repair/listPreRepairRuleFee.html" target="frame" >预防维修计划费用明细</a></li>	  
		<li><a id="preRepairRuleManHour" onclick="activeTab(this)" href="../../device/repair/listPreRepairRuleManHour.html" target="frame" >预防维修计划工时明细</a></li>	  
		<li><a id="preRepairRuleTool" onclick="activeTab(this)" href="../../device/repair/listPreRepairRuleTool.html" target="frame" >预防维修计划工具明细</a></li>	  
		-->
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   
 </@htmlPage>