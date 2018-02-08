<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="设备清查">


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
		//		document.forms["area"].elements["details"].click();
 		document.all.frame.src='${req.contextPath}/preview/device/inventory/deviceInventoryDetails.html'  ; 
 		document.getElementById("details").className = "selectedtab";
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

</script>

    <@ww.form namespace="'/device/inventory'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'清查编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'清查名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@dept1/>
            	<td align="right" valign="top"><label class="label"><font color="red">*</font>清查人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		     </tr>
		     <tr>
            	<@pp.datePicker label="'清查日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
	     		<@ww.textarea  label="'备注'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
            </tr>    
            <#--
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            	<td align="right" valign="top"><span class="required"></span><label class="label">审批状态:</label></td>
            	<td>
		 			<input type="checkbox" name="unusedAgree" value="0151" onclick="changeSealedFaultState()"/>批准
		 		</td>
		 		<script language="javascript">
		 			function changeSealedFaultState() {
		 				if (document.forms[0].elements["unusedAgree"].checked == true) {	
		 					document.getElementById("requestPeople").style.display='inline';	
		 					document.getElementById("requestIdea2").style.display='inline';	
		 				}else{
		 					document.getElementById("requestPeople").style.display='none';	
		 					document.getElementById("requestIdea2").style.display='none';
		 				}
		 			}				    
		 		</script>
		 	<tr id="requestPeople" style="display:none">
            	<td align="right" valign="top"><label class="label"><font color="red">*</font>批准人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            	<@pp.datePicker label="'批准日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
            </tr> 
            <tr id="requestIdea2" style="display:none">
			 	<td align="right" vlign="middle" rowspan="3">
                	<label  class="label">批准意见:</label>
            	</td>
            	<td rowspan="3" colspan="3">
                	<textarea name="deviceExtInfo.comment" rows="3" cols="50"></textarea>
            	</td>  
			 </tr>
			 -->
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="listDeviceInventories.html"/>
        </@buttonBar>
        
        <script language="javascript">
	    function peopleMainManager_OpenDialog() {
		  		var url = "${req.contextPath}/popup/userSelector.html";
		  		popupModalDialog(url, 800, 600, userUnSealedSelectorHandler);
		 }	
		 
		function userUnSealedSelectorHandler(result) {
		  		document.forms[0].elements["alteration.unSealPeople"].value = result[1];
		  		document.forms[0].elements["alteration.unsealedPeopleDisplay"].value = result[1];
		}
	    
    </script>
    
    </@ww.form>
    
    <ul id="beautytab">
		<li>
			<a id="details"  onclick="activeTab(this);" href="${req.contextPath}/preview/device/inventory/deviceInventoryDetails.html" target="frame" >清查明细</a>
		</li>	   
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>