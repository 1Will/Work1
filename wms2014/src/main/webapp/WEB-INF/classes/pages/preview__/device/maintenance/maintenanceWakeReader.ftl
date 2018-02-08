<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="保养工作提醒">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <@inputTable>
            <tr>
            <@ww.select label="'使用部门'"
	                    	name="nation"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'全部',
	                    	       '总装一厂',
	                    	       '总装二厂'
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>
            </tr>
        	<tr>
        		<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'资产编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'设备型号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'设备规格'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'设备类别'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'工单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            	<@ww.textfield label="'保养级别'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'计划开工日期'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>    
            	<@ww.textfield label="'计划完工日期'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'保养负责人'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            	<@ww.textfield label="'保养工时'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>     
            <tr>
            	<@ww.textfield label="'保养内容'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'提醒信息'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'保养内容'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
	  		</tr>
	  		<tr>
	  		<@pp.datePicker label="'读取时间'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	  		</tr>
        </@inputTable>
	     	  <@buttonBar>
	    		<@vbutton value="保存" class="button" onclick="confirm('要删除提醒信息吗?')"/>
	    		<@vbutton value="关闭" class="button" onclick="javascript:window.close()"/>
	    	</@buttonBar>  	
	   </@ww.form>   
</@framePage>		 