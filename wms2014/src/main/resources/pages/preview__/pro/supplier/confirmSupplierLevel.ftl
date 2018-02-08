 <#include "../../includes/macros2.ftl" />
<@htmlPage title="供应商级别评审">

 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

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
 		document.all.frame.src='../../pro/supplier/listSupplierLevelHistory.html';
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
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html');
	}

</script>
    <@inputTable>
    <tr>
    <@ww.textfield label="'申请信用级别'" name="'suppliers.level'" value="" cssClass="'underline'"/>
    </tr>
    <tr>	
    <@ww.select label="'调整信用等级'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       '恶劣',
	                    	       '一般',
	                    	       '良好',
	                    	       '优秀' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>
	 <@ww.checkbox label="'确认再次调整'" />
    </tr>
    <tr>
     <@ww.textarea label="'原因'" name="'organization.remark'" value="" rows="3" cols="60" required="false"/>
    </tr>
    <tr>
	        	<td align="right" valign="top"><label class="label">审核人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" onblur="shipmentPlan_Vehicle_Code_RemotePick(this.value);"
	        			class="underline"  value="qs,sa,admin"  maxlength="150" size="50"/>
	        		<label id="">选择</label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="people_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
    		</tr>
    		<tr>
    		</tr>
	       	<tr>
    	        <@ww.textfield label="'审核意见'" name="'area.code'" value="" size="50" cssClass="'underline'" readonly="true"/>
        	</tr>
     <@buttonBar>
     	<@redirectButton url="${req.contextPath}/pro/supplier/listSupplierLevel.html" value="审批" />
     </@buttonBar>
    </@inputTable>
    	 </@ww.form>
    <ul id="beautytab">
		<li><a id="ruleItems" onclick="activeTab(this)" href="../../pro/supplier/listSupplierLevelHistory.html" target="frame">供应商级别评审历史记录</a></li>	    
		<li><a id="baseInfo" onclick="activeTab(this)" href="../../pro/supplier/supplierLevelBaseInfo.html" target="frame">供应商基本信息</a></li>	
		<li><a id="elseInfo" onclick="activeTab(this)" href="../../pro/supplier/supplierLevelElseInfo.html" target="frame">供应商附加信息</a></li>			  
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</@htmlPage>