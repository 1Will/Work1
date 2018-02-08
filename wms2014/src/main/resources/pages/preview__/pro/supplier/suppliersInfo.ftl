<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="供应商查询">
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


 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
   <@ww.token name="saveAreaToken"/>
	 	<@inputTable>
		<tr>
		   <@ww.textfield label="'供应商编号'" name="'manufuture.name'" value="" cssClass="'underline'" />	 
	 	   <@ww.textfield label="'供应商名称'" name="'manufuture.address'" value="" cssClass="'underline'" />	
	 	</tr>
	 	<tr>
	 		<@ww.select label="'供应商类别'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'所有',
	                    	        '设备供应商',
	                    	       '工装供应商'
	                    	  	  }"
	                    	value="selectedDevice"
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
	          <@ww.textfield label="'所属国家'" name="'manufuture.name'" value="" cssClass="'underline'"  />	
	            <@ww.textfield label="'地区'" name="'manufuture.name'" value="" cssClass="'underline'"  />	 
	       </tr>
	        	<tr>
	        	   
	        	  	 <@ww.select label="'注册资金'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'所有',
	                    	       '10~100万元',
	                    	       '100~1000万元',
	                    	      '1000万元以上'
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>	
		<@workflow  />
       </tr>
	</@inputTable> 
                 <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>  
        
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
               <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>供应商编号</th>
                <th>供应商名称</th>
                <th>供应商类别</th>
                <th>供应商等级</th>
                <th>所属国家</th>
			 	<th>地区</th>
			 	<th>公司性质</th>
			 	<th>注册资金</th>
			 	<th>法人</th>
			 	<th>状态</th>

			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td  style="text-align:left"><a href="${req.contextPath}/preview/pro/supplier/listSuppliersItem.html">GYS001</a></td>
				<td  style="text-align:left">合肥永君数码</td>
				<td  style="text-align:left">设备供应商</td>
				<td  style="text-align:left">优秀</td>
				<td  style="text-align:left">中国</td>
				<td  style="text-align:left">安徽</td>
				<td  style="text-align:left">私营</td>
				<td  style="text-align:right">2000000</td>
				<td  style="text-align:left">李先生</td>
                <td  style="text-align:left">提交</td>
			</tr>
	     	</@listTable>
	     	</table> 
	     	 <@buttonBar>
     	<@redirectButton value="新建"  url="${req.contextPath}/preview/pro/supplier/listSuppliersItem.html" />
     	<@vbutton value="删除" class="button" onclick="confirm('确认删除吗？')"/>
     </@buttonBar>
              </@ww.form> 
</@htmlPage>