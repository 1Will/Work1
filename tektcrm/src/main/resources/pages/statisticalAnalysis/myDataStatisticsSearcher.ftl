<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: contactArchivesSearcher.ftl 2009-12-08 11:00:35Z wliu $ -->
<#-- 两个echarts插件 -->
	<#--<script type="text/javascript" src="${req.contextPath}/javascripts/vintage.js" ></script>-->
	 <script type="text/javascript" src="${req.contextPath}/javascripts/json2.js"></script>
     <script type="text/javascript" src="${req.contextPath}/javascripts/echarts.min.js" ></script>
<@inputTable>
<#include "../includes/hco2011.ftl" />
<table class="wwFormTable" cellspacing="1" >
<tr class="input">
	<td>
 <table class="inputTable">
    <tr>
			<td align="right">
				 <select id="data.state" name="data.state" onChange="selectTime(this)" >
    				<option value="year">年度查询</option>
    				<option value="month" selected>月度查询</option>
    				<option value="randomTime" >历史区间查询</option>
    				<script type="text/javascript">
    				<#if sta?exists>
    				getObjByName('data.state').value='${sta?if_exists}';
    				</#if>
    				</script>
				</select>
			</td>
					 <@pp.dateRanger 
			 				name="'data.ciemdinghTime'" 
						    value="'${req.getParameter('data.ciemdinghTime_start')?if_exists}|${req.getParameter('data.ciemdinghTime_end')?if_exists}'"
						    cssClass="'underline'" 
						    dateFormat="'%Y-%m'"
						    maxlength="10"
						    />
				
					<@pp.dateRanger 
			 				name="'data.ciemdinghTime1'" 
						    value="'${req.getParameter('data.ciemdinghTime1_start')?if_exists}|${req.getParameter('data.ciemdinghTime1_end')?if_exists}'"
						    cssClass="'underline'" 
						    dateFormat="'%Y'"
						    maxlength="10"
						    />
		<script>
        	<#if first>
	        	if(getObjByName("data.ciemdinghTime_start").value==""){
		        	var date = new Date();
					getObjByName("data.ciemdinghTime_end").value = date.format("'%Y年%m'");
				}
			</#if>
		</script>
			
	  <script>
        	<#if first>
	        	if(getObjByName("data.ciemdinghTime1_start").value==""){
		        	var date = new Date();
					getObjByName("data.ciemdinghTime1_end").value = date.format("'%Y年'");
				}
			</#if>
		</script>
	   
		
        <@ww.select label="'${action.getText('data.businessType')}'" 
                name="'businessType.id'" 
				value="'${req.getParameter('businessType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allBusinessType"
				required="false"
				emptyOption="false" 
				disabled="false"
				onchange="'businessTypeDWR(\"businessType.id\",\"classification.id\",\"${action.getText('select.option.all')}\",\"edit\")'">
		</@ww.select>
		<@ww.select label="'${action.getText('data.classification')}'" 
				name="'classification.id'" 
				value="'${req.getParameter('classification.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allClassification"
				required="false"
				emptyOption="false" 
				disabled="false">
		</@ww.select>
			
	</tr>
</table>
	</td>
</tr>
</table>
</@inputTable>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/AllClassifications.js'></script>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/myData.js'></script>

<script language="javascript">




	//业务属性（军民品）
	var type_msg = "";
	var btype = "";
	var businessType_id = "";
	var classification_id = "";
	function businessTypeDWR(businessTypeId,classificationId,msg,flag){
		type_msg = msg;
		businessType_id = businessTypeId;
		classification_id = classificationId;
	if(flag == "edit"){
		btype = getObjByName(businessType_id).value;
			if(btype == ""){
				 getObjByName(classification_id).options.length = 0; 
			}else{
				AllClassifications.getAllClassifications(btype, classifications);
			}
		}
		
	}
	function classifications(data){
		if("" == data){
			getObjByName(classification_id).options.length = 0; 
			getObjByName(classification_id).options.add(new Option(type_msg,-1));
		}else if(null != data){
			if(data.length > 0){
				getObjByName(classification_id).options.length = 0;
				for(var i =0 ;i<data.length;i++){
					if(i == 0){
						getObjByName(classification_id).options.add(new Option(type_msg,-1));
						getObjByName(classification_id).options[i].value = -1;
						getObjByName(classification_id).options.add(new Option(data[i].name,data[i].id));
					}else{
						getObjByName(classification_id).options.add(new Option(data[i].name,data[i].id));
					}
				}
			}
		}
	}
		
		var typeSele= getObjByName("businessType.id");
	    var typeGroups=typeSele.options.length;
	    for(i=0;i<typeGroups;i++){
			<#if req.getParameter('businessType.id')?exists>
	        if(typeSele.options[i].value=="${req.getParameter('businessType.id')?if_exists}"){
	           typeSele.options[i].selected="true";
	        }
			</#if>
	    }
		var typeSele= getObjByName("classification.id");
	    var typeGroups=typeSele.options.length;
	    for(i=0;i<typeGroups;i++){
			<#if req.getParameter('classification.id')?exists>
	        if(typeSele.options[i].value=="${req.getParameter('classification.id')?if_exists}"){
	           typeSele.options[i].selected="true";
	        }
			</#if>
	    }
	   
	  //根据year/month/randomTime时间选择日期
	    function selectTime(sel){
	      var flag =  sel.options[sel.selectedIndex].value;
	      if (flag == "year"){
	        getObjByName("data.ciemdinghTime1_start").parentNode.style.display = "";
            getObjByName("data.ciemdinghTime_start").parentNode.style.display = "none";
            
	      }else if(flag =="randomTime"){
		        getObjByName("data.ciemdinghTime_start").parentNode.style.display = "";
	            getObjByName("data.ciemdinghTime1_start").parentNode.style.display = "none";
		     
	      }else{
		       if (flag == "month"){
		        getObjByName("data.ciemdinghTime_start").parentNode.style.display = "";
	            getObjByName("data.ciemdinghTime1_start").parentNode.style.display = "none";
	       
		      }
	      }
	    }
	    
	  
	    
		
</script>
 
