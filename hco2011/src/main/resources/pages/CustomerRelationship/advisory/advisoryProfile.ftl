<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('advisoryProfile')}">
<@ww.form name="'listForm'" namespace="'/advisoryManager'" action="'saveAdvisory'" method="'post'">
	<@ww.token name="saveAdvisoryToken"/>
	<@inputTable>
	
			<#if advisory.id?exists>
                <@ww.hidden name="'advisory.id'" value="#{advisory.id}"/>
            </#if>
            <@ww.hidden name="'isNoB'" value="1"/>
            <#if advisory.customerServicePerson?exists>
            	<@ww.hidden name="'customerService'" value="'${advisory.customerServicePerson.id?if_exists}'"/>
            <#else>
            	<@ww.hidden name="'customerService'" value="''"/>
            </#if>
            <@ww.hidden name="'customerId'" id="'customerId'" value="''"/>
		<#--
		    <tr>
			<@ww.textfield label="'${action.getText('advisory.shortName')}'" name="'advisory.shortName'" value="'${advisory.shortName?if_exists}'" cssClass="'underline'"/>
		</tr>
		
		<tr>
			
			<@ww.textfield label="'${action.getText('advisory.fax')}'" name="'advisory.fax'" value="'${advisory.fax?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
			
			
			
			<td align="right"><label for="" class="label">${action.getText('advisory.isNoBack')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="advisory.isNoBack1" name="advisory.isNoBack" value=false checked>是
	        	<input type="radio" id="advisory.isNoBack2" name="advisory.isNoBack" value=true>否
	        	<script language="javascript">
		     		if(${(advisory.isNoBack?string)?if_exists}==true){
		     			getObjByName('advisory.isNoBack1').checked = true;
		     		}else if(${(advisory.isNoBack?string)?if_exists}==false){
		     			getObjByName('advisory.isNoBack2').checked = true;
		     		}
				</script>
			</td>
		</tr>
		<tr>	
		    <@ww.hidden name="'advisory.statue'" value=""/>
			<@ww.select label="'${action.getText('advisory.statue')}'"
						required="false"
						name="'statue'" 
						value="${req.getParameter('statue')?if_exists}"
						listKey="id"
						listValue="name"
					    list="allAdvisoryStatues"
					    emptyOption="false" 
					    disabled="true"/>
		    <script language="javascript">
		     	<#if advisory.statue?exists>
		     		getObjByName('statue').value = ${advisory.statue.id};
		     		getObjByName('advisory.statue').value = ${advisory.statue.id};
		     	</#if>
			</script>
		</tr>
		<#--
		<tr>
		<td align="right" valign="top">
	    		<label class="label">
	    			转入客户:
	    		</label>
	    	</td>
	    	<td>
	    		<input type="checkbox" name="isAddCustomer" value="1"  <#if isCustomer>disabled checked</#if>/>
	    	</td>
		</tr>
		
		
	    <tr><td colspan="8"><hr/></td></tr>
	    -->
	    
	    <!--重新排版页面wclin 11.7.5***********************************************************************************-->
	   <!--@@客户信息@@-->
	   <tr>
	   	<!--客户名称-->
	   	 	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('advisory.name')}:</label>
	     	</td>
            <td>
	            <input type="text" name="advisory.name" value="${advisory.name?if_exists}" class="underline" onkeyup="showAdvisory()" autocomplete="off">
	            <div id="show" style="background-color:#FFFFFF;border:1px solid; overflow:auto; display: none;z-index:2;position:absolute;left:20%;top:18%;"></div>
            </td>
	   	<!--行业-->
	   	<@ww.select 
			    		label="'${action.getText('advisory.industry_ID.id')}'"
						required="true"
						name="'industry'" 
						value="${req.getParameter('industry')?if_exists}" 
						listKey="id"
						listValue="name"
					    list="allIndustrys"
					    emptyOption="true" 
					    disabled="false"/>
			<script language="javascript">
		     	<#if advisory.industry?exists>
		     		getObjByName('industry').value = ${advisory.industry.id};
		     	</#if>
			</script>
	   	
	   </tr>
	   <tr>
	   	<!--企业性质-->
	   	<@ww.select 
	    		label="'${action.getText('advisory.enter_nature_ID.id')}'"
				required="true"
				name="'companyNature'" 
				value="${req.getParameter('companyNature')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allEnterNature"
			    emptyOption="true" 
			    disabled="false"/>
			<script language="javascript">
		     	<#if advisory.companyNature?exists>
		     		getObjByName('companyNature').value = ${advisory.companyNature.id};
		     	</#if>
			</script>
	   	<!--国家-->
	   	<@ww.select label="'${action.getText('advisory.country')}'" 
			name="'country.id'" 
			id="country.id" 
			value="'${req.getParameter('country.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allCountry"
			emptyOption="true" 
			disabled="false"
			required="true"
			onchange="'areaCascadeDWR(\"country.id\",\"province.id\",\"city.id\",1,\"${action.getText('')}\",\"edit\")'">
		</@ww.select>
		<script language="javascript">
	     	<#if advisory.country?exists>
	     		getObjByName('country.id').value = ${advisory.country.id};
	     	</#if>
		</script>
	   </tr>
	   <tr>
	   	<!--省份-->
	   	<@ww.select label="'${action.getText('advisory.province')}'" 
			name="'province.id'" 
			id="province.id" 
			value="'${req.getParameter('province.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allProvince"
			emptyOption="true" 
			disabled="false"
			required="true"
			onchange="'areaCascadeDWR(\"country.id\",\"province.id\",\"city.id\",2,\"${action.getText('')}\",\"edit\")'">
		</@ww.select>
		<script language="javascript">
	     	<#if advisory.province?exists>
	     		getObjByName('province.id').value = ${advisory.province.id};
	     	</#if>
		</script>
		<!--城市-->
		<@ww.select label="'${action.getText('advisory.city')}'" 
			name="'city.id'" 
			id="city.id" 
			value="'${req.getParameter('city.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allCity"
			emptyOption="false" 
			required="false"
			disabled="false">
		</@ww.select>
		<script language="javascript">
	     	<#if advisory.city?exists>
	     		getObjByName('city.id').value = ${advisory.city.id};
	     	</#if>
		</script>
	   </tr>
	   <tr>
	   	<!--企业法人-->
	   	<@ww.textfield label="'${action.getText('advisory.legal')}'" name="'advisory.legalPerson'" value="'${advisory.legalPerson?if_exists}'" cssClass="'underline'"/>
	   	<!--注册资本-->
	   	<@ww.textfield label="'${action.getText('注册资本')}'" name="'advisory.registeredCapital'" value="'${advisory.registeredCapital?if_exists}'" cssClass="'underline'"/>
	   </tr>
	   <tr>
	   	<!--员工人数-->
	   	<@ww.textfield label="'${action.getText('员工人数')}'" name="'advisory.personCount'" value="'${advisory.personCount?if_exists}'" cssClass="'underline'"/>
	   	
	   	<!--创立日期-->
	   	<@pp.datePicker 
				label="'${action.getText('创立日期')}'" 
				name="'advisory.setupTime'" 
	   			value="'${(advisory.setupTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="false"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			
	   </tr>
	   <tr>
	   	<!--客户状态-->
	   	<@ww.select label="'${action.getText('客户状态')}'" 
				name="'type.id'" 
				value="'${req.getParameter('type.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allTypes"
				required="true"
				disabled="true">
			</@ww.select>
			<script language="javascript">
			<#if advisory.customerType?exists>
				getObjByName('type.id').value='${advisory.customerType.id?if_exists}';
			<#elseif req.getParameter('type.id')?exists>
				getObjByName('type.id').value='${req.getParameter('type.id')}';
			</#if>
			</script>
	   	<#--
	   	<@ww.select label="'${action.getText('客户状态')}'"
						required="false"
						name="'advisory.customerType.id'" 
						value="${req.getParameter('advisory.customerType.id')?if_exists}"
						listKey="id"
						listValue="name"
					    list="allAdvisoryStatues"
					    emptyOption="false" 
					    disabled="false"/>
		    <script language="javascript">
		     	<#if advisory.statue?exists>
		     		getObjByName('statue').value = ${advisory.statue.id};
		     		getObjByName('advisory.statue').value = ${advisory.statue.id};
		     	</#if>
			</script>-->
	   	<!--客户等级-->
	   	<@ww.select label="'${action.getText('客户等级')}'" 
				name="'advisory.customer.step.id'" 
				value="'${req.getParameter('advisory.customer.step.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allSteps"
				disabled="true">
			</@ww.select>
	   	<#--
	   	<@ww.select label="'${action.getText('客户等级')}'" 
			name="'advisory.customer.step.id'" 
			value="'${req.getParameter('advisory.customer.step.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="AllSteps"
			emptyOption="true" 
			required="true"
			disabled="false">
		</@ww.select>
		<script language="javascript">
	     	<#if advisory.city?exists>
	     		getObjByName('city.id').value = ${advisory.city.id};
	     	</#if>
		</script>
		-->
	   </tr>
	   <tr>
	   	<!--地址-->
	   		<td align="right" valign="top">
	       		<label class="label">${action.getText('advisory.address')}:</label>
	     	</td>
			<td colspan="3">
				<input type="text" name="advisory.address" value="${advisory.address?if_exists}" class="underline" maxlength="120" size="120" ></input>
			</td>
	   	<!---->
	   </tr>
	   <tr>
	   	<!--职业简介-->
	   	<td align="right" valign="top">
			<span class="required"></span>
        		<label class="label">
        			${action.getText('企业简介')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="advisory.enterpriseSynopsis" rows="4" cols="150" >${advisory.enterpriseSynopsis?if_exists}</textarea>
	        </td>
	   	<!---->
	   </tr>
	   
	   <tr><td colspan="8"><hr/></td></tr>
	   <!--@@联系人信息@@-->
	   <tr>
	   	<!--联系人姓名-->
	   	<@ww.textfield label="'${action.getText('advisory.connectPeople')}'" name="'advisory.connectPeople'" value="'${advisory.connectPeople?if_exists}'" required="true" cssClass="'underline'"/>
	   	
	   	<!--性别-->
	   	<td align="right"><label for="" class="label">${action.getText('advisory.sex')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="man" name="advisorySex" value="0" />男
	        	<input type="radio" id="woman" name="advisorySex" value="1" />女
			</td>
	   </tr>
	   <tr>
	   	<!--部门-->
	   	<@ww.textfield label="'${action.getText('advisory.dept')}'" name="'advisory.dept'" value="'${advisory.dept?if_exists}'" cssClass="'underline'"/>
		<!--职位-->
		<@ww.textfield label="'${action.getText('advisory.duty')}'" name="'advisory.duty'" value="'${advisory.duty?if_exists}'" cssClass="'underline'"/>
	   </tr>
	   <tr>
	   	<!--办公电话-->
	   	<@ww.textfield label="'${action.getText('advisory.fixTel')}'" name="'advisory.officePhone'" value="'${advisory.officePhone?if_exists}'" required="false" cssClass="'underline'"/>
		<!--手机号码-->
		<@ww.textfield label="'${action.getText('advisory.moveTel')}'" name="'advisory.mobile'" value="'${advisory.mobile?if_exists}'"  cssClass="'underline'"/>
	   </tr>
	   <tr>
	   	<!--E-mail-->
	   	<@ww.textfield label="'${action.getText('advisory.email')}'" name="'advisory.email'" value="'${advisory.email?if_exists}'" cssClass="'underline'"/>
		<!--QQ号码-->
		<@ww.textfield label="'${action.getText('advisory.qq')}'" name="'advisory.qq'" value="'${advisory.qq?if_exists}'" cssClass="'underline'"/>
	   </tr>
	   <tr>
	   <!--印象描述-->
	   <td align="right" valign="top">
			<span class="required"></span>
        		<label class="label">
        			${action.getText('印象描述')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="advisory.effectDescribe" rows="4" cols="150" >${advisory.effectDescribe?if_exists}</textarea>
	        </td>
	   
	   <!---->
	   </tr>
	   <tr><td colspan="8"><hr/></td></tr>
	   
	   <!--@@业务员记录@@-->
	   <tr>
	   	<!--姓名-->
	   	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('advisory.customerService')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="customerServiceName" class="underline"  value="${advisory.customerServiceName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>	
	   	<!--部门-->
	   	<@ww.textfield label="'${action.getText('advisory.parlorDept')}'" name="'advisory.parlorDept'" value="'${advisory.parlorDept?if_exists}'" cssClass="'underline'"/>
	   	
	   </tr>
	   <tr>
	   	<!--信息来源-->
	   	<@ww.select 
	    		label="'${action.getText('advisory.infoSource')}'"
				required="false"
				name="'infoSource'" 
				value="${req.getParameter('infoSource')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allInfoOriginId"
			    emptyOption="false" 
			    disabled="false"/>
			<script language="javascript">
		     	<#if advisory.infoSource?exists>
		     		getObjByName('infoSource').value = ${advisory.infoSource.id};
		     	</#if>
			</script>
	   	<!--资料完整度-->
	  <@textfield label="${action.getText('资料完整度')}" name="advisory.customerInfoIntegrity"  value="${advisory.customerInfoIntegrity?if_exists}" required="false" cssClass="underline" maxlength="20" readonly="true"/>		
	   </tr>
	   <tr>
	   	<!--咨询时间-->
	   	<@pp.datePicker 
				label="'${action.getText('advisory.advisoryTime')}'" 
				name="'advisory.advisoryTime'" 
	   			value="'${(advisory.advisoryTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<script language="javascript">
				<#if advisory.id?exists>
				<#else>
					var advisoryDate = new Date();
					if(getObjByName("advisory.advisoryTime").value==''){
						getObjByName("advisory.advisoryTime").value = advisoryDate.format("yyyy-MM-dd");
					}
				</#if>
			</script>
	   </tr>
	   <tr>
	   	<!--咨询内容-->
	   	<td align="right" valign="top">
			<span class="required">*</span>
        		<label class="label">
        			${action.getText('advisory.advisoryContent')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="advisory.advisoryContent" rows="4" cols="150" >${advisory.advisoryContent?if_exists}</textarea>
	        </td>
	   	<!---->
	   </tr>
	   <!--***********************************************************************************-->
	    <#--
		<tr>
        	<td align="right" valign="top">
        		<label class="label">
        			${action.getText('advisory.comment')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="advisory.comment" rows="4" cols="150" >${advisory.comment?if_exists}</textarea>
	        </td>
	    </tr>
	    -->
	</@inputTable>
	<@buttonBar>
	<#if !(action.isReadOnly())>
	    <div style="float:left">
	            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'">
	            </@vsubmit>
	        &nbsp;
        </div>
        <div id="changeButton" style="display:none;float:left">
            <@vsubmit name="'changToCustomer'" value="'${action.getText('changToCustomer')}'" onclick="'return storeValidation();'">
            </@vsubmit>
            &nbsp;
        </div>
     </#if>
        <div style="float:left">
        <#if !(action.isReadOnly())>
        	<#-- 继续新建按钮   -->
			<#if advisory.id?exists>
			<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/advisoryManager/editAdvisory.html"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/advisoryManager/editAdvisory.html"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
			</#if>
        </#if>
        
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/advisoryManager/listAdvisory.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
        </div>
	</@buttonBar>
</@ww.form>
<script language="javascript">
	 var lastInfo = "";
	 var selectflag = 0;
	 var resultData;
	 function showAdvisory(){
	 	var obj = getObjByName('advisory.name').value;
	 	var obj = getObjByName('advisory.name').value;
			var re1 = /\d/;
		 	var re2 = /\w/;
		 	var re3 = /[\u4e00-\u9fa5]/;
		 	for(var i=0; i<obj.length; i++){
		 		if(!re2.test(obj.charAt(i)) && !re3.test(obj.charAt(i))){
		 			alert("请不要输入特殊字符");
		 			getObjByName('advisory.name').value="";
					getObjByName('advisory.name').focus();
		 			return false;
		 		}
		 	}
	 	if(obj != "" && obj != null && obj != lastInfo){
	 	//	getObjByName('industry').style.display="none";
		//	getObjByName('country.id').style.display="none";
			CustomerList.getCustomerList(
				obj,
				{
					callback:function(data){
						if(data.length > 0){
							selectflag = data.length;
							resultData = data;
							getObjByName('show').innerHTML = "";
							for(var i=0; i<data.length; i++){
								var tag = document.createElement("input");
								tag.setAttribute("type","text");
								var id = "option" + (i+1);
								tag.setAttribute("id",id);
							    if(data[i]["name"].length>8){
							      tag.setAttribute("value",data[i]["name"].substring(0,8)+"...");
							    }else{
							      tag.setAttribute("value",data[i]["name"]);
							    }
							    tag.setAttribute("title",data[i]["name"]);
								tag.setAttribute("size","20");
								tag.setAttribute("readOnly","readOnly");
								tag.style.border = "0px";
								tag.onmouseover=function(){
									mouseON(this);
								};
								tag.onmouseout=function(){
									mouseOUT(this);
								};
								tag.onkeypress=function(){
									DWRUtil.onReturn(event, storeValidation);
								};
								tag.onclick=function(){
									getObjByName('advisory.name').value = this.value;
									getObjByName('show').style.display = "none";
									var flag = this.id.substring(6,7)-1;
									autoCopyValue(flag);
								};
								getObjByName('show').appendChild(tag);
								getObjByName('show').appendChild(document.createElement('br'));
							}
							getObjByName('show').style.display = "block";
							//DWRUtil.selectRange("option1", 0, getObjByName('option1').value.length);

							var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
							 if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 ) {							 
								var textRange =getObjByName('advisory.name').createTextRange();//建立文本域
								textRange.moveStart('character',getObjByName('advisory.name').value.length);//获取文本域右侧文本
								textRange.collapse(true);//瓦解文本域
								textRange.select();
							 } else {
								getObjByName('advisory.name').setSelectionRange(getObjByName('advisory.name').value.length, getObjByName('advisory.name').value.length); 
								getObjByName('advisory.name').focus(); 
							 }
							
						}else{
							getObjByName('show').style.display = "none";
							getObjByName('industry').style.display="block";
			  				getObjByName('country.id').style.display="block";
			  				clean();
						}
					}			
				}
			);
			lastInfo = obj;
		}else if(obj != "" && obj != null && obj == lastInfo){
			
		}else if(obj == "" || obj == null){
			getObjByName('show').style.display = "none";
			getObjByName('industry').style.display="block";
			getObjByName('country.id').style.display="block";
			lastInfo = "";
			clean();
		}else{
			clean();
		}
	 }
	 
	 /**
	  * 鼠标移上焦点时触发事件
	  * 将焦点内容选中
	  */
	function mouseON(op){
		op.value = op.title;
		DWRUtil.selectRange(op.id, 0, op.size);
	}
	/**
	  * 鼠标移出焦点时触发事件
	  * 将焦点内容还原
	  */
	function mouseOUT(op){
		if(op.value.length>8){
			op.value = op.value.substring(0,8)+"...";
		}
	}
	/**
	 * 键盘上下键事件
	 */
	function myMethod(event){
		//alert(event.altKey);
		alert(event.keyCode);
		
		if(event.keyCode==38){
			if(getObjByName('show').style.display == "block"){
				for(var i=selectflag; i>0; i--){
					var logo = 'option' + i;
					if(document.activeElement.id==logo){
						if(getObjByName(logo).value.length>8){
							getObjByName(logo).value = getObjByName(logo).value.substring(0,8)+"...";
						}
						var nextLogo = 'option' + (i-1);
						if(getObjByName(nextLogo) != null){
							getObjByName(nextLogo).value = getObjByName(nextLogo).title;
							DWRUtil.selectRange(nextLogo, 0, 20);
						}
						return;
					}
				}
				DWRUtil.selectRange('option1', 0, 10);
			}
		}else if(event.keyCode==40){
			if(getObjByName('show').style.display == "block"){
				for(var i=1; i<selectflag+1; i++){
					var logo = 'option' + i;
					if(document.activeElement.id==logo){
						if(getObjByName(logo).value.length>8){
							getObjByName(logo).value = getObjByName(logo).value.substring(0,8)+"...";
						}
						var nextLogo = 'option' + (i+1);
						if(getObjByName(nextLogo) != null){
							getObjByName(nextLogo).value = getObjByName(nextLogo).title;
							DWRUtil.selectRange(nextLogo, 0, 20);
						}
						return;
					}
				}
				DWRUtil.selectRange('option1', 0, 10);
			}
		}else if(event.keyCode==13){
			if(document.selection.createRange().text != ""){
				getObjByName('advisory.name').value = document.selection.createRange().text;
			}
			getObjByName('show').style.display = "none";
			getObjByName('industry').style.display="block";
  			getObjByName('country.id').style.display="block";
		}
	}
	//document.onkeyup = myMethod;

	 /**
  	 * 页面点击事件源
  	 */
  	document.onclick = function(event){
   		checkdiv(event);
	}
	
	/**
	 * 源默认触发事件
	 */
	function checkdiv(event){
	    var event  = window.event || event;
	    var obj = event.srcElement ? event.srcElement : event.target;
	    if(obj){
			getObjByName('show').style.display = "none";
			getObjByName('industry').style.display="block";
			getObjByName('country.id').style.display="block";
	    }
	}
	
	/**
	 * 将控件自动赋值
	 */
	 function autoCopyValue(flag)
	 {
	 <#--
	    getObjByName('changeButton').style.display = "block";
	    -->
	    <#--
	    getObjByName('advisory.statue').value='';
	    -->
	    getObjByName('customerId').value=resultData[flag]['id'];
		getObjByName('advisory.name').value=resultData[flag]['name'];
		getObjByName('industry').style.display="block";
  		getObjByName('country.id').style.display="block";
		//getObjByName('advisory.shortName').value=resultData[flag]['abbreviations'];
		<#--
		getObjByName('customerType').value=resultData[flag]['customerType']['id'];
		-->
		getObjByName('industry').value=resultData[flag]['industry']['id'];
		getObjByName('companyNature').value=resultData[flag]['companyNature']['id'];
		getObjByName('advisory.legalPerson').value=resultData[flag]['legalPerson'];
		getObjByName('country.id').value=resultData[flag]['country']['id'];
		getObjByName('type.id').value=resultData[flag]['customerType']['id'];
		getObjByName('advisory.customer.step.id').value=resultData[flag]['step']['id'];
		//设置同步
		DWREngine.setAsync(false); 
		areaCascadeDWR("country.id","province.id","city.id",1,"${action.getText('')}","edit");
    	//重新设置为异步方式
    	DWREngine.setAsync(true);
	    getObjByName('province.id').value=resultData[flag]['province']['id'];
	    
	    //设置同步
    	DWREngine.setAsync(false); 
    	areaCascadeDWR("country.id","province.id","city.id",2,"${action.getText('')}","edit");
    	//重新设置为异步方式
    	DWREngine.setAsync(true);
		if(resultData[flag]['city']!=null){
		getObjByName('city.id').value=resultData[flag]['city']['id'];		
		}				
	    getObjByName('advisory.connectPeople').value=resultData[flag]['keyContacter'];
	    getObjByName('advisory.officePhone').value=resultData[flag]['telphone'];
	    getObjByName('advisory.mobile').value=resultData[flag]['mobilePhone'];
	    //getObjByName('advisory.fax').value=resultData[flag]['fax'];
	    getObjByName('advisory.email').value=resultData[flag]['email'];
	    getObjByName('advisory.qq').value=resultData[flag]['qq'];
	 }
	 /**
	 * 将控件值清空
	 */
	 function clean(){
	    getObjByName('customerId').value='';
		//getObjByName('advisory.shortName').value='';
		getObjByName('industry').value='';
		getObjByName('companyNature').value='';
		getObjByName('advisory.legalPerson').value='';
		getObjByName('country.id').value='';
		getObjByName('province.id').value='';
		getObjByName('city.id').value='';
		getObjByName('advisory.connectPeople').value='';
		getObjByName('advisory.officePhone').value='';
		getObjByName('advisory.mobile').value='';
		getObjByName('advisory.email').value='';
		getObjByName('advisory.qq').value='';
//		getObjByName('advisory.advisoryTime').value="";
	 }
	 //window.onload=function (){
		//职业简介
		<#if advisory.id?exists>
			<#if advisory.enterpriseSynopsis?exists>
				getObjByName('advisory.enterpriseSynopsis').value='${advisory.enterpriseSynopsis?if_exists}';
			<#elseif req.getParameter('advisory.enterpriseSynopsis')?exists>
				getObjByName('advisory.enterpriseSynopsis').value='${req.getParameter('advisory.enterpriseSynopsis')}';
			</#if>
		</#if>
			//客户等级
		<#if advisory.id?exists>
	    <#if advisory.customer?exists>
			getObjByName('advisory.customer.step.id').value='${advisory.customer.step.id?if_exists}';
			<#elseif req.getParameter('advisory.customer.step.id')?exists>
			getObjByName('advisory.customer.step.id').value='${req.getParameter('advisory.customer.step.id')}';
		</#if>
		</#if>
		<#if advisory.sex==false>
				getObjByName('man').checked=true;
			<#else>
			    getObjByName('woman').checked=true;
			</#if>
		 <#if !(action.isReadOnly())>
		    <#if advisory.statue?exists>
				<#if advisory.statue.name?exists>
					<#if advisory.statue.name = '新建'>
					    getObjByName('changeButton').style.display = "block";
				    </#if>
			    </#if>
			</#if>
		</#if>
	    
		//国家
		<#if advisory.country?exists>
			getObjByName('country.id').value='${advisory.country.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",1,"${action.getText('')}","edit");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
	    </#if>
	    //省份
		<#if advisory.province?exists>
			getObjByName('province.id').value='${advisory.province.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	areaCascadeDWR("country.id","province.id","city.id",2,"${action.getText('')}","edit");
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true);
	    </#if>
	    //城市
		<#if advisory.city?exists>
			getObjByName('city.id').value='${advisory.city.id?if_exists}';
		</#if>
	//}
	function storeValidation(){
		if(getObjByName('show').style.display == "block"){
			getObjByName('show').style.display="none";
			var flag = event.srcElement.id.substring(6,7)-1;
			autoCopyValue(flag);
			return false;
		}
		<#-- 客户名称 1 -->
		if(getObjByName('advisory.name').value==""){
			alert('${action.getText('advisory.name.not.null')}');
			getObjByName('advisory.name').focus();
			return false;
		}
		if(getObjByName('advisory.name').value != ""){
			var obj = getObjByName('advisory.name').value;
			var re1 = /\d/;
		 	var re2 = /\w/;
		 	var re3 = /[\u4e00-\u9fa5]/;
		 	for(var i=0; i<obj.length; i++){
		 		if(!re2.test(obj.charAt(i)) && !re3.test(obj.charAt(i))){
		 			alert("请不要输入特殊字符");
		 			getObjByName('advisory.name').value="";
					getObjByName('advisory.name').focus();
		 			return false;
		 		}
		 	}
		}
		if(!isValidLength(document.forms[0], "advisory.name", null, 20)){
			alert('${action.getText('advisory.name.length')}');
			getObjByName('advisory.name').value="";
			getObjByName('advisory.name').focus();
			return  false;
		}
		<#-- 客户缩写 2 
		if(!isValidLength(document.forms[0], "advisory.shortName", null, 20) && '' !=getObjByName('advisory.shortName').value){
		   alert("${action.getText('advisory.shortName.length')}");
		   getObjByName('advisory.shortName').value="";
		   getObjByName('advisory.shortName').focus();
		   return  false;
	    }-->
	    <#-- 客户类型 3 
		if(getObjByName('customerType').value==""){
			alert('${action.getText('cust_type_ID.id.not.null')}');
			getObjByName('customerType').focus();
			return false;
		}
		-->
		<#-- 行业 4 -->
		if(getObjByName('industry').value==""){
			alert('${action.getText('industry_ID.id.not.null')}');
			getObjByName('industry').focus();
			return false;
		}
		<#-- 企业性质 5 -->
		if(getObjByName('companyNature').value==""){
			alert('${action.getText('enter_nature_ID.id.not.null')}');
			getObjByName('companyNature').focus();
			return false;
		}
		
		<#--创立日期-->
		if(getObjByName('advisory.setupTime').value !=''){
			if(!isDate('advisory.setupTime')){
				alert("${action.getText('advisory.setupTime.type')}");
				getObjByName('advisory.setupTime').value="";
				getObjByName('advisory.setupTime').focus();
				return false;
			}
		}
		<#-- 企业法人 6 -->
		if(!isValidLength(document.forms[0], "advisory.legalPerson", null, 20) && '' !=getObjByName('advisory.legalPerson').value){
		   alert("${action.getText('advisory.legal.length')}");
		   getObjByName('advisory.legalPerson').value="";
		    getObjByName('advisory.legalPerson').focus();
		   return  false;
	    }
	    <#--员工人数-->
	    if(getObjByName('advisory.personCount').value !=''){
			if(!isDoubleNumberBetweenBoolean(formatDigital(getObjByName('advisory.personCount').value), 1000000001, -1)){
				alert('${action.getText('advisory.personCount.format')}');
				getObjByName('advisory.personCount').value="";
				getObjByName('advisory.personCount').focus();
				return false;
			}
		}
	    <#-- 国家 7 -->
	    
	    if(getObjByName('country.id').value==""){
			alert('${action.getText('country.id.not.null')}');
			getObjByName('country.id').focus();
			return false;
		}
	    <#-- 省份 8 -->
	    if(getObjByName('province.id').value=="" || getObjByName('province.id').value=="-1"){
			alert('${action.getText('province.id.not.null')}');
			getObjByName('province.id').focus();
			return false;
		}
		<#-- 城市 9 -->
		<#-- 联系人 10 -->
		if(getObjByName('advisory.connectPeople').value==""){
			alert('${action.getText('advisory.connectPeople.not.null')}');
			getObjByName('advisory.connectPeople').focus();
			return false;
		}
		if(!isValidLength(document.forms[0], "advisory.connectPeople", null, 20)){
			alert('${action.getText('advisory.connectPeople.length')}');
			getObjByName('advisory.connectPeople').value="";
			getObjByName('advisory.connectPeople').focus();
			return false;
		}
		<#-- 部门 11 
		if(!isValidLength(document.forms[0], "advisory.dept", null, 20) && '' !=getObjByName('advisory.dept').value){
		   alert("${action.getText('advisory.dept.length')}");
		   getObjByName('advisory.dept').value="";
			getObjByName('advisory.dept').focus();
		   return  false;
	    }-->
	    <#-- 职务 12 -->
		if(!isValidLength(document.forms[0], "advisory.duty", null, 20) && '' !=getObjByName('advisory.duty').value){
		   alert("${action.getText('advisory.duty.length')}");
		    getObjByName('advisory.duty').value="";
			getObjByName('advisory.duty').focus();
		   return  false;
	    }
	    
	   
		<#-- 办公电话 13 zsz 2010.03.23-->
		if(getObjByName('advisory.officePhone').value == '' && getObjByName('advisory.mobile').value == ''){
			alert('${action.getText('advisory.officePhoneOrMobile.not.null')}');
			getObjByName('advisory.officePhone').focus();
        	return false;
		}
        
        if(getObjByName('advisory.officePhone').value != '')
        {
		   var str = getObjByName('advisory.officePhone').value
	       if(str.length>20){
	       		alert('${action.getText('advisory.officePhone.error')}');
	       		getObjByName('advisory.officePhone').value="";
	       		getObjByName('advisory.officePhone').focus();
	        	return false;
	       }
		}
		//注册资本
		if(getObjByName('advisory.registeredCapital').value !=''){
			if(!isDoubleNumberBetweenBoolean(formatDigital(getObjByName('advisory.registeredCapital').value), 1000000001, -1)){
				alert('${action.getText('advisory.registeredCapital.format')}');
				getObjByName('advisory.registeredCapital').value="";
				getObjByName('advisory.registeredCapital').focus();
				return false;
			}
		}
		<#-- 手机 14 zsz 2010.03.23 -->
		if(getObjByName('advisory.mobile').value != ''){
		   var str = getObjByName('advisory.mobile').value
	       if(str.length>20){
	       		alert('${action.getText('advisory.mobile.error')}');
	       		getObjByName('advisory.mobile').value="";
	       		getObjByName('advisory.mobile').focus();
	       		return false;
	       }
		}
		<#-- 传真 15 
		if(getObjByName('advisory.fax').value != ''){
		   var str = getObjByName('advisory.fax').value
	       if(str.length>20){
	       		alert('${action.getText('advisory.fax.error')}');
	       		getObjByName('advisory.fax').value="";
	       		getObjByName('advisory.fax').focus();
	       		return false;
	       }
		}-->
		<#-- Email 16 -->
        if ('' != getObjByName('advisory.email').value) {
			var value = getObjByName('advisory.email').value;
            if (!value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.org)|(\.info)|(\.edu)|(\.mil)|(\.gov)|(\.biz)|(\.ws)|(\.us)|(\.tv)|(\.cc)|(\..{2,2}))$)\b/gi)) {
          		alert("${action.getText('advisory.email.email')}");
          		getObjByName('advisory.email').value="";
	       		getObjByName('advisory.email').focus();
          		return false;
          	}
          	if (!isValidLengthValue(getObjByName('advisory.email').value,0,50)) {
		       alert("${action.getText('advisory.email.maxLength')}");
		       getObjByName('advisory.email').value="";
	       		getObjByName('advisory.email').focus();
		       return false;
		    }
         }
        <#-- QQ 17 -->
		if(!isValidLength(document.forms[0], "advisory.qq", null, 20) && '' !=getObjByName('advisory.qq').value){
		   alert("${action.getText('advisory.qq.length')}");
		   getObjByName('advisory.qq').value="";
	       getObjByName('advisory.qq').focus();
		   return  false;
	    }
		<#-- 信息来源 18 -->
		if(getObjByName('infoSource').value==-1){
			getObjByName('infoSource').value="";
	    }
	    <#-- 客服人员 19 -->
	    if(getObjByName('customerServiceName').value == ''){
			alert('${action.getText('customerServiceName.not.null')}');
        	return false;
		}
	    <#-- 咨询时间 20 -->
		if(getObjByName('advisory.advisoryTime').value ==''){
		 		alert("${action.getText('advisory.advisoryTime.not.null')}");
		 		getObjByName('advisory.advisoryTime').focus();
		      	return false;   
		}else{
		 	if(!isDate('advisory.advisoryTime')){
				alert("${action.getText('advisory.advisoryTime.dateFormate.error')}");
				getObjByName('advisory.advisoryTime').value="";
	      	    getObjByName('advisory.advisoryTime').focus();
				return false;
			} 
			if(isDateLessThenCurrent(getObjByName('advisory.advisoryTime').value)){
				alert('${action.getText('advisory.advisoryTime.earlyError')}');
				getObjByName('advisory.advisoryTime').value="";
	       		getObjByName('advisory.advisoryTime').focus();
				return false;
			}
		}
		<#-- 回访 21
		if (document.forms[0].elements["advisory.isNoBack1"].checked) {
       		document.forms[0].elements["isNoB"].value=1;
	    } else if(document.forms[0].elements["advisory.isNoBack2"].checked){
	        document.forms[0].elements["isNoB"].value=0;
	    } -->
	    <#-- 地址 22 -->
		if(!isValidLength(document.forms[0], "advisory.address", null, 50) && '' !=getObjByName('advisory.address').value){
		   alert("${action.getText('advisory.address.length')}");
		   getObjByName('advisory.address').value="";
	       getObjByName('advisory.address').focus();
		   return  false;
	    }
		<#-- 咨询内容 23 -->
	    if(getObjByName('advisory.advisoryContent').value==""){
			alert('${action.getText('advisory.advisoryContent.not.null')}');
			getObjByName('advisory.advisoryContent').focus();
			return false;
		}
		if(!isValidLength(document.forms[0], "advisory.advisoryContent", null, 250) && '' !=getObjByName('advisory.advisoryContent').value){
		   alert("${action.getText('advisory.advisoryContent.length')}");
		    getObjByName('advisory.advisoryContent').value="";
	       getObjByName('advisory.advisoryContent').focus();
		   return  false;
	    }
	    <#-- 备注 24 
       if(!isValidLength(document.forms[0], "advisory.comment", null, 250) && '' !=getObjByName('advisory.comment').value){
		   alert("${action.getText('advisory.comment.length')}");
		     getObjByName('advisory.comment').value="";
	       getObjByName('advisory.comment').focus();
		   return  false;
	   }
	   -->
	   //document.forms[0].submit();
	   return true;
   }
   
   	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			getObjByName('customerService').value=result[0];
			document.forms[0].elements["customerServiceName"].value = result[2];
			document.forms[0].elements["advisory.parlorDept"].value = result[9];
		}
	}
</script>

<#if advisory.id?exists>
<ul id="beautytab">
	<li>
		<a id="additionalInformation" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?advisory.id=#{advisory.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >附件资料</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/applicationDocManager/listApplicationDoc.html?advisory.id=#{advisory.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="40%"/>
</#if>
</@htmlPage>
