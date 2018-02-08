<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="供应商级别查询">	 
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
 		document.all.frame.src='../../pro/supplier/supplierLevelInfomation.html'  ; 
 		document.getElementById("ext").className = "selectedtab";
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
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">	
	 	<@inputTable>
		<tr>
		   <@ww.textfield label="'供应商编号'" name="'manufuture.name'" value="" cssClass="'underline'" />	 
	 	   <@ww.textfield label="'供应商名称'" name="'manufuture.address'" value="" cssClass="'underline'" />	
	 	</tr>
	 		<tr>
		    <@ww.select label="'供应商类别'"
	                    	name="suppliers"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'所有',
	                    	      '设备供应商',
	                    	       '工装供应商' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>
	        	<@ww.select label="'供应商等级'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'所有',
	                    	       '恶劣',
	                    	       '一般',
	                    	       '良好',
	                    	       '优秀' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>
	        	</tr>
	        	<tr>
	       <@status />
	       </tr>	
	</@inputTable>  

         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>   
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
			<th>供应商编号</th>
			<th>供应商名称</th>
			<th>供应商类别</th>
			<th>产品质量</th>
			<th>交货期</th>
			<th>交货量</th>
			<th>工作质量</th>
			<th>价格</th>
			<th>进货费用水平</th>
			<th>供应商等级</th>
			<th>状态</th>
			</tr>
			<tr>
		    <td style="text-align:left"><a href="${req.contextPath}/pro/supplier/editSupplierLevel.html">GYS001</a></td>
		    <td style="text-align:left">合肥永君有限公司</td>
		    <td style="text-align:left">工装供应商</td>
		    <td style="text-align:left">优秀</td>
		    <td style="text-align:left">2个月</td>
		    <td style="text-align:left">2500千克</td>
		    <td style="text-align:left">认真</td>
		    <td style="text-align:left">一般</td>
		    <td style="text-align:left">一般</td>
		    <td style="text-align:left">良好</td>
		    <td style="text-align:left">提交</td>
		    </tr>			
	     	</@listTable>
	     	</table>
	     	 <@buttonBar>
     	<@redirectButton value="新建" url="${req.contextPath}/pro/supplier/editSupplierLevel.html" />
     	<@vbutton value="删除" class="button" onclick="confirm('确认删除吗?')"/>
     </@buttonBar>
             </@ww.form>   
</@htmlPage>