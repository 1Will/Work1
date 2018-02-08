<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="维修申请维护">



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
 		document.all.frame.src='../../device/repair/listPreRepairRuleItems.html';
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
                <@ww.textfield label="'维修申请编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="true"/>
                <@ww.textfield label="'维修申请名称'" name="'area.code'" value="" cssClass="'underline'" readonly="false" required="true"/>
            </tr>
            <tr>
				<td align="right" valign="top"><label class="label"><font color="red">*</font>设备编号:</label></td>
				        	<td>
				        		<input type="text" name="shipmentPlan.vehicle.code" 
				        			class="underline"  value="" readonly maxlength="150" size="20"/>
				        		<label id=""></label>
				        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
					    		<a onClick="device_OpenDialog();">
					        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
					        	</a>
					        </td>
					        <@ww.textfield label="'设备名称'" name="'area.code'" value=""  cssClass="'underline'" readonly="true"/>
			</tr>	
			<tr>				
				<@ww.textfield label="'所属部门'" name="'area.code'" value="" cssClass="'underline'"  readonly="true"/>
				 <td align="right" valign="top"><span class="required"></span><label class="label">是否外协:</label></td>
            	<td>
		 			<input type="checkbox" name="outHelp" value="0151"/>
		 		</td>
				
				<#--<@dept1/>-->
			</tr>	
			<tr>
			    <@ww.textarea label="'维修原因'" 
					           name="'organization.remark'" 
					           value="" rows="3" cols="'60'" 
								required="false"/>
				<@ww.textarea label="'外协原因'" 
					           name="'organization.remark'" 
					           value="" rows="3" cols="'60'" 
								required="false"/>
			
			</tr>		
			<tr>
                <@ww.textfield label="'申请人'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@pp.datePicker label="'申请时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>	 
			</tr>		
			<tr>
            	<td align="right" valign="top"><span class="required"></span><label class="label">审批状态:</label></td>
            	<td>
		 			<input type="checkbox" name="unusedAgree" value="0151" onclick="changeSealedFaultState()"/>批准
		 		</td>
		 	</tr>	
			<tr id="requestPeople" style="display:none">
			    <@ww.textfield label="'审核人'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="true"/>
                <@ww.textfield label="'状态'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="true"/> 
			</tr>	
			<tr id="requestIdea"  style="display:none">
				<@ww.textarea label="'审核说明'" 
					           name="'organization.remark'" 
					           value="" rows="3" cols="'60'" 
								required="false"/>
				
				<#--<@dept1/>-->
			</tr>	
            <#--<@audit2/>-->

        </@inputTable>
        
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="listReqRepairs.html"/>	        	
        	<@redirectButton value="${action.getText('back')}" url="listReqRepairs.html"/>	
        </@buttonBar>
     </@ww.form>
      <script>
	  
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
     
     <#--
    <ul id="beautytab">
		<li><a id="preRepairItems" onclick="activeTab(this)" href="../../device/repair/listPreRepairRuleItems.html" target="frame" >预防性维修定标项目</a></li>	    		  
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   -->
 </@htmlPage>