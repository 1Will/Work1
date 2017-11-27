<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('退房明细编辑')}">
<@ww.form name="'listForm'" namespace="'/productList'" action="'saveHouseListBack'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveHouseListToken"/>
	<#if houseListBack.id?exists>
		<@ww.hidden name="'houseListBack.id'" value="#{houseListBack.id?if_exists}"/>
	<#else>
		<@ww.hidden name="'houseListBack.id'" />
	</#if>
	
	<@ww.hidden id="contractManagement.id" name="'contractManagement.id'" value="#{contractManagement.id?if_exists}"/>
	<#if houseListBack.house?exists>
		<@ww.hidden id="house.id" name="'house.id'" value="#{houseListBack.house.id?if_exists}"/>
	<#else>
		<@ww.hidden id="house.id" name="'house.id'" value=""/>
	</#if>
	<@inputTable>
		<tr>
			<td align="right" valign="top">
	       		<label class="label"><font color="red">*</font>${action.getText('房间编码')}:</label>
	     	</td>
	     	<td>
	     		<#if houseListBack.house?exists>
		   			<input type="text" id="house.code" name="house.code" class="underline"  value="${houseListBack.house.code?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="house.code" name="house.code" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="houses_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<@ww.textfield label="'${action.getText('房间名称')}'" name="'houseListBack.house.name'"  cssClass="'underline'" readonly="true"/>
			<@ww.textfield label="'${action.getText('合同编码')}'" name="'contractManagement.code'"  cssClass="'underline'" readonly="true"/>
		</tr>
		<tr>
		 <@ww.textfield label="'${action.getText('企业名称')}'" name="'contractManagement.customerInfo.name'"  cssClass="'underline'" readonly="true"/>
		 <@ww.textfield label="'${action.getText('房间面积')}'" name="'houseListBack.house.square'"  cssClass="'underline'" readonly="true"/>
           <@ww.select 
	    		label="'${action.getText('退房类型')}'"
					required="false"
					name="'backType.id'" 
					value="${req.getParameter('backType.id')?if_exists}" 
					listKey="id"
					listValue="name"
				    list="allBackType"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
			</tr>	
			
		<tr>	
			<@pp.datePicker 
				label="'${action.getText('开始时间')}'" 
				name="'houseListBack.startTime'" 
	   			value="'${(houseListBack.startTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				onchange="'countDays()'"
				/>
		    <@ww.textfield label="'${action.getText('结束时间')}'" name="'houseListBack.endTime'"  cssClass="'underline'" />
		    <@ww.textfield label="'${action.getText('天数')}'" name="'houseListBack.days'"  cssClass="'underline'" readonly="true"/>
		</tr>

		<tr><td colspan="8"><hr/></td></tr>
		<tr>
		     <@ww.textfield label="'${action.getText('电表A上次示数')}'" name="'houseListBack.eleLastA'"    cssClass="'underline'" readonly="true" />
		     <@ww.textfield label="'${action.getText('电表A本次示数')}'" name="'houseListBack.eleNowA'" required="true" cssClass="'underline'"  />
		     <@ww.textfield label="'${action.getText('A表用电量')}'" name="'houseListBack.allEleA'"  cssClass="'underline'" readonly="true"/>
		</tr>
		<tr>
		     <@ww.textfield label="'${action.getText('电表B上次示数')}'" name="'houseListBack.eleLastB'"    cssClass="'underline'" readonly="true"/>
		     <@ww.textfield label="'${action.getText('电表B本次示数')}'" name="'houseListBack.eleNowB'" required="true" cssClass="'underline'" onblur="'countEleB()'"/>
		     <@ww.textfield label="'${action.getText('B表用电量')}'" name="'houseListBack.allEleB'"  cssClass="'underline'"  readonly="true"/>
		</tr>
		<tr>
	       	 <@ww.textfield label="'${action.getText('公摊电量')}'" name="'houseListBack.shareEle'" required="true"  cssClass="'underline'" />
		     <@ww.textfield label="'${action.getText('总电量')}'" name="'houseListBack.allEle'"  cssClass="'underline'"  readonly="true"/>
		     <@ww.textfield label="'${action.getText('总电费')}'" name="'houseListBack.allEleFee'"  cssClass="'underline'" />
		</tr>
		<tr><td colspan="8"><hr/></td></tr>
		<tr>
		     <@ww.textfield label="'${action.getText('水表上次示数')}'" name="'houseListBack.waterLast'"  cssClass="'underline'" readonly="true" />
		     <@ww.textfield label="'${action.getText('水表本次示数')}'" name="'houseListBack.waterNow'" required="true"  cssClass="'underline'" />
		     <@ww.textfield label="'${action.getText('公摊水量')}'" name="'houseListBack.shareWater'" required="true" cssClass="'underline'" />
		</tr>
		<tr>
		     <@ww.textfield label="'${action.getText('总水量')}'" name="'houseListBack.allWater'"  cssClass="'underline'" readonly="true"/>
		     <@ww.textfield label="'${action.getText('总水费')}'" name="'houseListBack.waterFee'"  cssClass="'underline'" />
		</tr>
		<tr><td colspan="8"><hr/></td></tr>
		<tr>
		    <@ww.textfield label="'${action.getText('空调上次示数')}'" name="'houseListBack.airLast'"  cssClass="'underline'" readonly="true" />
		     <@ww.textfield label="'${action.getText('空调本次示数')}'" name="'houseListBack.airNow'" required="true" cssClass="'underline'" />
		     <@ww.textfield label="'${action.getText('使用量')}'" name="'houseListBack.allAir'"  cssClass="'underline'" readonly="true"/>
		</tr>
		<tr> 
		     <@ww.textfield label="'${action.getText('空调总费用')}'" name="'houseListBack.airFee'"  cssClass="'underline'" />
		</tr>
		<tr><td colspan="8"><hr/></td></tr>
		<tr>
			<@ww.select 
	    		label="'${action.getText('退房状态')}'"
					required="false"
					name="'state.id'" 
					value="${req.getParameter('state.id')?if_exists}" 
					listKey="id"
					listValue="name"
				    list="AllState"
			    	emptyOption="false" 
			    	disabled="false">
		    	</@ww.select>
		     <@ww.textfield label="'${action.getText('网络费')}'" name="'houseListBack.netFee'"  cssClass="'underline'" />
		     <@ww.textfield label="'${action.getText('物业费')}'" name="'houseListBack.propertyFee'"  cssClass="'underline'" />
		</tr>
		<tr>
		     <@ww.textfield label="'${action.getText('房租金额')}'" name="'houseListBack.backRent'"  cssClass="'underline'"  required="true"  />
		     <@ww.textfield label="'${action.getText('水电金额')}'" name="'houseListBack.waterEleFee'"  cssClass="'underline'"  required="true"  onchange="'countAllFee()'"/>
		     <@ww.textfield label="'${action.getText('总金额')}'" name="'houseListBack.allFee'"  cssClass="'underline'" readonly="true" />
		</tr>
		<tr>
			<@textarea id="houseListBack.remark" name="houseListBack.remark"  label="${action.getText('说明')}" value="${houseListBack.remark?if_exists}" rows="4" />
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("houseListBack.remark").cols =width;
			</script>
		</tr>
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@vbutton class="button" value="${action.getText('close')}" onclick="closeAndRefresh();"/>
    </@buttonBar>
</@ww.form>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/HouseListEndTime.js'></script>
<script language="JavaScript" type="text/JavaScript"> 
	<#-- 提交验证-->
	function storeValidation(){
	    
		if(getObjByName("house.id").value==""){
			alert("${action.getText('请选择房间')}");
		    return false;
		}
		if(getObjByName("backType.id").value==""){
			alert("${action.getText('请选择退房类型')}");
		    return false;
		}
		if(getObjByName("houseListBack.startTime").value==""){
			alert("${action.getText('请选择退房时间')}");
		    return false;
		}
		if( getObjByName("houseListBack.backRent").value==""){
		   alert("${action.getText('请输入本次房租金额')}");
		   return false;
		}
		if( getObjByName("houseListBack.waterEleFee").value==""){
		   alert("${action.getText('请输入水电金额')}");
		   return false;
		}
		if( getObjByName("houseListBack.eleNowA").value==""){
		   alert("${action.getText('请输入电表A的示数')}");
		   return false;
		}
		if( getObjByName("houseListBack.eleNowB").value==""){
		   alert("${action.getText('请输入电表B的示数')}");
		   return false;
		}
		if( getObjByName("houseListBack.shareWater").value==""){
		   alert("${action.getText('请输入公用电表的示数')}");
		   return false;
		}
		if( getObjByName("houseListBack.waterNow").value==""){
		   alert("${action.getText('请输入本次水表的示数')}");
		   return false;
		}
		if( getObjByName("houseListBack.shareWater").value==""){
		   alert("${action.getText('请输入公摊水表的示数')}");
		   return false;
		}
		if( getObjByName("houseListBack.airNow").value==""){
		   alert("${action.getText('请输入本次空调的示数')}");
		   return false;
		}
		if(getObjByName("state.id").value==""){
			alert("${action.getText('请选择退房状态')}");
		    return false;
		}
	}
	
	//弹出房间查询模态窗体
	function houses_OpenDialog(){
	   var url = "${req.contextPath}/house/listHouseWindow.html?contractManagement.id=#{contractManagement.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
		getObjByName("house.id").value=result[0];
		getObjByName("house.code").value=result[1];
		getObjByName("houseListBack.house.name").value=result[2];
		getObjByName("houseListBack.house.square").value=result[4];
		getObjByName("houseListBack.waterLast").value=result[5];
		getObjByName("houseListBack.eleLastA").value=result[6];
		getObjByName("houseListBack.eleLastB").value=result[7];
		getObjByName("houseListBack.airLast").value=result[8];
		DWREngine.setAsync(false); 
 		HouseListEndTime.getHouseListEndTime(result[0],creatEndTime);
 		DWREngine.setAsync(true);
		}
	}
	function creatEndTime(data){
	    if (null != data) {
        getObjByName("houseListBack.endTime").value=data;
        }
	}
	
	
	//计算两个日期之间相差几天
	function countDays(){
       var  aDate,  oDate1,  oDate2,  iDays ; 
       var sDate1=getObjByName("houseListBack.startTime").value;
       var sDate2=getObjByName("houseListBack.endTime").value;
       if(sDate1!=''&& sDate2!=''){
        aDate  =  sDate1.split("-")  
        oDate1 =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式  
        aDate  =  sDate2.split("-")  
        oDate2 =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
        iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数  
        getObjByName("houseListBack.days").value=iDays;
       }
	}
	//计算总金额
	function countAllFee(){
	   var backRent = getObjByName("houseListBack.backRent").value; 
	    var waterEleFee = getObjByName("houseListBack.waterEleFee").value; 
	    if(backRent!='' && isNaN(backRent)){
	       alert("房租必须是数字");
	       getObjByName("houseListBack.backRent").value='';
	    }
	    if(waterEleFee!='' && isNaN(waterEleFee)){
	       alert("水电费必须是数字");
	       getObjByName("houseListBack.waterEleFee").value='';
	    }
	    if(backRent!='' && waterEleFee!='' && !isNaN(backRent) && !isNaN(waterEleFee)){
	    getObjByName("houseListBack.allFee").value =backRent*1+waterEleFee*1;
	    } 
	}
	//计算A表总电量
	function countEleA(){
	    var eleLastA = getObjByName("houseListBack.eleLastA").value; 
	    var eleNowA = getObjByName("houseListBack.eleNowA").value; 
	     if(eleNowA!='' && isNaN(eleNowA)){
	       alert("电表示数必须是数字");
	       getObjByName("houseListBack.eleNowA").value='';
	    }
	    if(eleLastA!='' && eleNowA!='' && !isNaN(eleLastA) && !isNaN(eleNowA)){
	    getObjByName("houseListBack.allEleA").value =eleNowA-eleLastA;
	    } 
	}
	//计算B表总电量
	function countEleB(){
	    var eleLastB = getObjByName("houseListBack.eleLastB").value; 
	    var eleNowB = getObjByName("houseListBack.eleNowB").value; 
	    if(eleNowB!='' && isNaN(eleNowB)){
	       alert("电表示数必须是数字");
	       getObjByName("houseListBack.eleNowB").value='';
	    }
	    if(eleLastB!='' && eleNowB!='' && !isNaN(eleLastB) && !isNaN(eleNowB)){
	    getObjByName("houseListBack.allEleB").value =eleNowB-eleLastB;
	    } 
	}
	//计算总电量
	function countAllEle(){
	 var eleA=getObjByName("houseListBack.allEleA").value;
	 var eleB=getObjByName("houseListBack.allEleB").value;
	 var eleC=getObjByName("houseListBack.shareEle").value;
	 if(eleC!='' && isNaN(eleC)){
	       alert("电表示数必须是数字");
	       getObjByName("houseListBack.shareEle").value='';
	    }
	 if(eleA!='' && eleB!='' && eleC!=''&& !isNaN(eleC)){
	     getObjByName("houseListBack.allEle").value=eleA*1+eleB*1+eleC*1;
	 }
	}
	//计算总水量    
	function countAllWater(){
	 var watA=getObjByName("houseListBack.waterLast").value;
	 var watB=getObjByName("houseListBack.waterNow").value;
	 var watC=getObjByName("houseListBack.shareWater").value;
    if(watB!='' && isNaN(watB)){
       alert("水表示数必须是数字");
       getObjByName("houseListBack.waterNow").value='';
    }
     if(watC!='' && isNaN(watC)){
       alert("水表示数必须是数字");
       getObjByName("houseListBack.shareWater").value='';
    }
	 if(watA!='' && watB!='' && watC!=''&& !isNaN(watC) && !isNaN(watB)&& !isNaN(watA)){
	     getObjByName("houseListBack.allWater").value=watB-watA+watC;
	 }
	}
	//计算空调使用量
	function countAir(){
	   var airLast = getObjByName("houseListBack.airLast").value; 
	    var airNow = getObjByName("houseListBack.airNow").value; 
	 if(airNow!='' && isNaN(airNow)){
       alert("空调示数必须是数字");
       getObjByName("houseListBack.airNow").value='';
    }
	    if(airLast!='' && airNow!='' && !isNaN(airLast) && !isNaN(airNow)){
	    getObjByName("houseListBack.allAir").value =airNow-airLast;
	    } 
	}
	document.onclick=function(){
	countAllFee();
	countEleA();
	countEleB();
	countAllEle();
	countAllWater();
	countAir();
	}
</script>
<script type='text/javascript'>
	<#if houseListBack.state?exists>
		getObjByName("state.id").value="#{houseListBack.state.id?if_exists}";
	</#if>
	<#if houseListBack.backType?exists>
		getObjByName("backType.id").value="#{houseListBack.backType.id?if_exists}";
	</#if>	
		
	</script>
</@htmlPage>
