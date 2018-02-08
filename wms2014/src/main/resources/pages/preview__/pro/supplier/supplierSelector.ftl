<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="供应商列表">

 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
   <@ww.token name="saveAreaToken"/>
	 	<@inputTable>
		<tr>
		    <@ww.select label="'供应商类别'"
	                    	name="suppliers"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'全部',
	                    	      '设备供应商',
	                    	       '工装供应商' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>
		 <@ww.select label="'供应商所属国家'"
	                    	name="nation"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'全部',
	                    	       '中国',
	                    	       '韩国',
	                    	       '美国' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>
	        	</tr>
	        	<tr>
	        	 <@ww.select label="'信用等级'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'全部',
	                    	       '恶劣',
	                    	       '一般',
	                    	       '良好',
	                    	       '优秀' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>
	        	 <@ww.select label="'注册资金'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'10~100万元',
	                    	       '100~1000万元',
	                    	      '1000万元以上'
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>
		</tr>	
		<tr>
		   <@ww.textfield label="'供应商名称'" name="'manufuture.name'" value="" cssClass="'underline'" />	 
	 	   <@ww.textfield label="'供应商地址'" name="'manufuture.address'" value="" cssClass="'underline'" />	
	 	</tr>
	 	<tr>
	 	   <@ww.textfield label="'注册资金'" name="'manufuture.name'" value="" cssClass="'underline'"  />	 
	 	   <@ww.textfield label="'地区'" name="'manufuture.ozone'" value="" cssClass="'underline'"  />	  			
		</tr>
	</@inputTable> 
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>  
        
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
               <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>供应商类别</th>
                <th>供应商所属国家</th>
                <th>地区</th>
                <th>供应商编号</th>
			 	<th>公司名称</th>
			 	<th>公司地址</th>
			 	<th>性质</th>
			 	<th>注册资金</th>
			 	<th>法人</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">设备供应商</td>
				<td style="text-align:left">中国</td>
				<td style="text-align:left">安徽</td>
				<td style="text-align:left"><a href="${req.contextPath}/pro/supplier/listSuppliersItem.html">GYS001</a></td>
				<td style="text-align:left">中天</td>
				<td style="text-align:left">天元路</td>
				<td style="text-align:left">私营</td>
				<td style="text-align:left">200万元</td>
				<td style="text-align:left">李先生</td>
			</tr>
	     	</@listTable>
	     	</table> 
	 <@buttonBar>
     	<@vbutton class="button" value="选择"  onclick="javascript:void(0);"/>
     	<@vbutton class="button" value="关闭" onclick="javascript:window.close();"/>
     </@buttonBar>
              </@ww.form> 
</@htmlPage>