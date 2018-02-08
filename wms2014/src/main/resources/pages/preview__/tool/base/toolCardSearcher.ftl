<@inputTable>
	<@ww.hidden name="'invalid'" value="false"/>
	<@ww.hidden name="'includeAllType'" value="true"/>
    <tr>
        <@ww.textfield label="'工装编号'" name="'deviceNo'" value="''" cssClass="'underline'"/>
        <@ww.textfield label="'工装名称'" name="'name'" value="''" cssClass="'underline'"/>


    <tr>

       <@ww.textfield label="'工装图号'" name="'installPlace'" value="''" cssClass="'underline'"/>
        <@ww.textfield label="'产品名称'" name="'name'" value="''" cssClass="'underline'"/>
	</tr>
    <tr>

    	<@ww.select label="'工装类别'"
		            name="'toolCategory'"
		            headerKey="1" 
		            headerValue="Select Month"
		            list="{
		                   '所有',
		                   '模具', 
		                    '夹具', 
		                    '工位器具',
		                    '检具',
		                    '辅具'
		                   }"
		            value="selectedDevice"
		            onchange="'setcity()'"
		                    	
		        	/> 
		        	<#--
		        	<@ww.select 
		        				id="aaa"
		        				label="'类别'"
		                    	name="'category'"
		                   	 	headerKey="1" 
		                    	headerValue="Select Month"
		                    	list="{
		                    			
		                    	  	  }"
		                    	value="selectedDevice"
		                    	
		        	/> 
		        	-->
		        	 <td align="right" valign="middle">  <label id="cate" for="aa">分类:</label></td>
            		<td>
            			<select id="aa" name="category" >
					        <option value="所有" >所有</option>
            			</select>
            		</td>
            	
    </tr>
    <tr>
	        	<@ww.select label="'使用单位'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有',
	                    			'焊冲一厂', 
	                    			'总装一厂'
	                    	  	  }"
	                    	value="selectedDevice"
	                    	
	        	/> 
	         <@ww.select label="'状态'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'所有',
	                    			'正常', 
	                    			'使用中',
	                    			'维修中',
	                    			'已报废'
	                    	  	  }"
	                    	value="selectedDevice"
	                    	
	        	/>     

     </tr>

	</@inputTable>
	<script language="JavaScript" type="text/JavaScript"> 
		function setcity() { 
		
				switch (document.forms[0].elements["toolCategory"].value) { 
				
				case '模具' : 
				
				var labels = new Array("所有","冲头","冲套","固定座"); 
				
				var values = new Array("sjz","cz","ts"); 
				//document.getElementsByTagName("label").text="模具类别";
				getObjByNameRe("cate").innerText="模具分类:"
				
				break; 
				
				case '夹具' : 
				
				var labels = new Array("所有","A","B","C"); 
				
				var values = new Array("jn","qd","yt"); 
				//document.getElementsByTagName("label").text="夹具类别";
				getObjByNameRe("cate").innerText="夹具分类:"
				break;
				
				case '所有' : 
				
				var labels = new Array("所有"); 
				
				var values = new Array("jn","qd","yt"); 
				//document.getElementsByTagName("label").text="夹具类别";
				getObjByNameRe("cate").innerText="分类:"
				break;
				
				case '工位器具' : 
				
				var labels = new Array(""); 
				
				var values = new Array(""); 
				//document.getElementsByTagName("label").text="夹具类别";
				getObjByNameRe("cate").innerText="工位器具分类:"
				break;
				
				case '检具' : 
				
				var labels = new Array(""); 
				
				var values = new Array(""); 
				//document.getElementsByTagName("label").text="夹具类别";
				getObjByNameRe("cate").innerText="检具分类:"
				break;
			 
			 
				case '辅具' : 
				
				var labels = new Array(""); 
				
				var values = new Array(""); 
				//document.getElementsByTagName("label").text="夹具类别";
				getObjByNameRe("cate").innerText="辅具分类:"
				break;				
		
		} 
		document.forms[0].elements["category"].options.length = 0;
		

		// 从数组中添加内容 
		//alert(document.forms[0].elements["toolCategory"].parentNode.nextSibling)
		//alert(document.getElementsByTagName("label")[0].length);
		for(var i = 0; i < labels.length; i++) { 
		//alert(document.getElementsByTagName("label").for.value);
		//alert(document.all("saveArea_toolCategory").getElementsByTagName("label").text);
		document.forms[0].elements["category"].add(document.createElement("OPTION")); 
		
		document.forms[0].elements["category"].options[i].text=labels[i]; 
		
		document.forms[0].elements["category"].options[i].value=values[i]; 

		} 

	}


	</script>