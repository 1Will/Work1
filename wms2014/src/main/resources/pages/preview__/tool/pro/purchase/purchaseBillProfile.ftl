<#include "../../../includes/macros2.ftl" />
<@htmlPage title="采购单维护">



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
 		document.all.frame.src='../../tool/purchase/listPurchaseBillItems.html';
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
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',600,300);
	}
	function supplier_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,380);
	}
	function requestPurchaseBill_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/requestPurchaseBillSelector.html',600,300);
	}	
	function requestBill_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/requestBillSelector.html',750,400);
	}	
</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@buttonBar>
	 			<@vbutton class="button" value="选择申购单" onclick="popupModalDialog('${req.contextPath}/popup/requestBillSelector.html', 700, 350)"/>
	 	</@buttonBar>
        <@inputTable>
            <tr>
                <@ww.textfield label="'采购单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'采购单名称'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
            </tr>
            
            <tr>
            	 <td align="right" valign="top"><label class="label">申购单编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" onblur="shipmentPlan_Vehicle_Code_RemotePick(this.value);"
	        			class="underline"  value=""  maxlength="150" size="30"/>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="requestBill_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            	<@ww.textfield label="'采购计划编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
           	</tr>
           	<tr>
                <td align="right" valign="top"><label class="label">预算编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" onblur="shipmentPlan_Vehicle_Code_RemotePick(this.value);"
	        			class="underline"  value="qs,sa,admin"  maxlength="150" size="30"/>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="people_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            </tr>
            </tr>
            <tr>
                <@ww.textfield label="'采购人'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
                <@pp.datePicker label="'采购日期'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
            </tr>
            <tr>
            	<@ww.textfield label="'采购数量(件)'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'总金额(元)'" name="'area.code'" value="10000" cssClass="'underline'" disabled="true" readonly="true"/>
            </tr>
            <@supplierSelector/>
            <tr>
            	<@ww.textfield label="'采购主要条款'" name="'area.code'" value="" cssClass="'underline'" />
            	<@ww.textfield label="'付款方式'" name="'area.code'" value="" cssClass="'underline'" />
            </tr>
            <tr>
            	<@ww.textfield label="'已付款额'" name="'area.code'" value="" cssClass="'underline'" />
            	<@ww.textfield label="'合同金额'" name="'area.code'" value="" cssClass="'underline'" />
            </tr>     
            <tr>
                <@ww.textfield label="'备注'" name="'area.code'" value="" size="50" cssClass="'underline'" />
            </tr>
             <@audit2 />
        </@inputTable>
      
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="${req.contextPath}/pro/purchase/listPurchaseBills.html"/>	
       		<@redirectButton value="${action.getText('submit')}" url="${req.contextPath}/pro/purchase/listPurchaseBills.html"/>	
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/preview/tool/purchase/listPurchaseBills.html"/>	
        </@buttonBar>
    </@ww.form>
    
    
	<ul id="beautytab">
		<li><a id="ruleItems" onclick="activeTab(this)" href="../../tool/purchase/listPurchaseBillItems.html" target="frame" >采购单明细</a></li>
		<li><a id="attach"  onclick="activeTab(this)"  href="../../tool/purchase/listPurchaseBillAttachs.html" target="frame" >采购单附件</a></li>
				<li><a id="check"  onclick="activeTab(this)"  href="../../pro/purchase/listPurchaseBillCheck.html" target="frame" >验收标准</a></li>	    		  
		<li><a id="contract"  onclick="activeTab(this)"  href="../../pro/purchase/editPurchaseBillContract.html" target="frame" >合同信息</a></li>
				<li><a id="supply"  onclick="activeTab(this)"  href="../../pro/supplier/supplierBaseInfomation.html" target="frame" >供应商信息</a></li>  
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
 
</@htmlPage>