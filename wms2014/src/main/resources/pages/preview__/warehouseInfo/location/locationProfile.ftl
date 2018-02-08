<#include "../../includes/macros2.ftl" />

<@htmlPage title="库位维护">
	<@ww.form  name="'listFrom'" action="'saveLocation_'" namespace="'/location_'" method="'post'" >
     	 <@ww.token name="saveWeekly_Token"/>
     	  <@inputTable>
     	  	<tr>
 			<@ww.textfield label="'库位编码'" name="'code'" value="'0001'" cssClass="'underline'" required="true"/>
 			<@ww.select 
 				label="'仓库'"
            	value="'一号仓库'"
            	list="{
            		'',
            		'一号仓库',
            		'二号仓库',
            		'三号仓库'	                    	  	  
            	}"
            	required="true"
	        />
	        <@ww.select 
	        	label="'库区'"
            	name="area.code"
            	value="'收货库区'"
            	list="{
            		'',
            		'收货库区',
            		'存货库区',
            		'拣货库区'	                    	  	  
            	}"
            	required="true"
	        />
		</tr>
 		<tr>
 			<@ww.select 
 				label="'库位类型'"
            	name="area.code"
            	value="'收获'"
            	list="{
            		'',
            		'收获',
            		'存货',
            		'发货',
            		'质损',
            		'补货墙'	                    	  	  
            	}"
            	required="false"
	        />
 			<@ww.select 
 				label="'承载类型'"
            	name="area.code"
            	value="'平库'"
            	list="{
            		'',
            		'拣货货架',
            		'存货货架',
            		'平库'	                    	  	  
            	}"
            	required="false"
	        />
	        <@ww.select 
	        	label="'混放模式'"
            	name="area.code"
            	value="'不混放'"
            	list="{
            		'',
            		'不混放',
            		'同类混放',
            		'可以混放'	                    	  	  
            	}"
            	required="false"
	        />
		</tr>
  		<tr>
 			<@ww.select 
 				label="'相同备件存放'"
            	name="area.code"
            	value="'不同属性混放'"
            	list="{
            		'',
            		'不同属性混放',
            		'不同属性不混放'
            	}"
            	required="false"
	        />
 			<@ww.textfield label="'最大承重量(kg)'" name="'device.code'" value="100" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'长度(cm)'" name="'device.code'" value="10" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
 			<@ww.textfield label="'高度(cm)'" name="'device.code'" value="10" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'宽度(cm)'" name="'device.code'" value="10" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'体积(cm3)'" name="'device.code'" value="1000" cssClass="'underline'" required="false" disabled="true"/>
		</tr>
		<tr>
 			<@ww.textfield label="'过道'" name="'device.code'" value="''" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'过道排位'" name="'device.code'" value="''" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'货架层次'" name="'device.code'" value="''" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
 			<@ww.textfield label="'货架格位'" name="'device.code'" value="''" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'状态'" name="'device.code'" value="'未用'" cssClass="'underline'" required="false" disabled="true"/>
		</tr>
		<tr>
			<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('描述')}:
	        		</label>
	        </td>
	        <td colspan="4" >
	        	<textarea name="education.comment" rows="4" cols="95" ></textarea>
	        </td>
		</tr>
 	  </@inputTable>
      	  <@buttonBar><@redirectButton value="${action.getText('save')}" url="#"/>
 	  <#--
         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>-->
         <@redirectButton value="${action.getText('back')}" url="listLocation_.html"/>
     </@buttonBar>	
    </@ww.form>
</@htmlPage>