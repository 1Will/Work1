<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="设备保养实施维护">



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
 		document.all.frame.src='../../device/maintenance/listMaintenancePlanImplFees.html';
 		document.getElementById("fees").className = "selectedtab";
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

	
	function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',600,400);
	}
	
		
	    function peopleMaintenance_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/maintenancePeopleSelector.html',null,300);
	}
	
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',null,300);
	}
</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        <tr>
         <@vbutton value="选择保养计划" class="button" onclick="popupModalDialog('${req.contextPath}/device/maintenance/maintenancePlanBZ.html')"/>
        </tr>
        </@inputTable>
        <@inputTable>
        <tr>
           <@ww.textfield label="'保养实施编号'" name="'area.code'" value="" required="true"  cssClass="'underline'" readonly="true" />
	       <@ww.textfield label="'保养实施名称'" name="'area.code'" value="" required="true"  cssClass="'underline'" readonly="true" />
        </tr>
        <tr>
			 	<@ww.textfield label="'保养计划编号'" name="'area.code'" value="" required="true"  cssClass="'underline'" readonly="true" />
			 	<@ww.textfield label="'保养计划名称'" name="'area.code'" value="" required="true"  cssClass="'underline'" readonly="true" />
		</tr>
		
		<tr>
        	<@deviceSelector  />
        </tr>
            <tr>
		<@ww.textfield label="'保养计划内容'" name="'area.code'" value="" cssClass="'underline'" readonly="true"  required="true" />
		<@ww.textfield label="'保养计划要求'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="true"/>
		</tr>
		
		<tr>
			 <@ww.textfield label="'保养计划工时'" name="'area.code'" value="" required="true" cssClass="'underline'"  readonly="true" /> 
			<@ww.textfield label="'保养计划执行时间'" name="'area.code'" value="" required="true" cssClass="'underline'"  readonly="true" /> 
		</tr>
		
		<tr>
			<@ww.textfield label="'保养负责人'" name="'area.code'" value="" cssClass="'underline'" required="true" readonly="true" />
				<@ww.textfield label="'备注'" name="'area.code'" value=""  cssClass="'underline'" required="true" readonly="true" />
		</tr>
            
            <tr>
            	 <@dept1 />
            	  <td align="right" valign="top"><label class="label"><font color="red">*</font>保养人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMaintenance_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            </tr>
            
            <tr>
            <tr>
                  <@pp.datePicker label="'实际实施时间'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true"/>
            	<@ww.textfield label="'保养摘要'" name="'area.code'" value=""   cssClass="'underline'" />
            </tr>
           <tr>
           <@audit2 />
           </tr>
            
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="${req.contextPath}/device/maintenance/listMaintenancePlanImpls.html"/>
       		<@redirectButton value="提交" url="#"/>
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/device/maintenance/listMaintenancePlanImpls.html"/>	
        </@buttonBar>
    </@ww.form>
    
    
    
	<ul id="beautytab">
		<li><a id="fees" href="../../device/maintenance/listMaintenancePlanImplFees.html" target="frame" class="selectedtab">保养实施费用明细</a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>

 
</@htmlPage>