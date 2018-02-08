<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="工装点检实施明细维护">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  <tr>
	 	  	<@ww.textfield label="'点检部位'" name="'area.code'" value=""  required="false" cssClass="'underline'" disabled="true"/>
	 	  	<@ww.textfield label="'点检内容'" name="'area.code'" value=""  required="false" cssClass="'underline'" disabled="true"/>
	 	  </tr>
	 	  <tr>
	 	  	<@ww.textfield label="'点检方法'" name="'area.code'" value=""  required="false" cssClass="'underline'" disabled="true"/>
	 	  	<@ww.textfield label="'点检计划费用(元)'" name="'area.code'" value=""  required="trfalseue" cssClass="'underline'" disabled="true"/>
	 	  </tr>
	 	  <tr>
	 	  	<@ww.textfield label="'点检判定标准'" name="'area.code'" value=""  required="false" cssClass="'underline'" disabled="true"/>
	 	  	<@ww.textfield label="'点检工具'" name="'area.code'" value=""  required="false" cssClass="'underline'" disabled="true"/>
	 	  </tr>
	 	  <tr>
	 	  		<@ww.select label="'点检结果'"
	                		name="device"
	                		headerKey="1" 
	                		headerValue="Select Month"
	                		list="{	
	                			   '正常',	
	                    			'异常' 
	                    		}"
	                 		value="selectedDevice"
	                 		disabled="false"
	                    	
	      		/>  
	      		<@ww.textfield label="'点检实施费用(元)'" name="'area.code'" value=""  required="true" cssClass="'underline'" />
	 	  </tr>
	 	  	<tr>
	        	 <td align="right" vlign="middle" rowspan="3">
                	<label  class="label">备注:</label>
            	</td>
            	<td rowspan="3" colspan="3">
                	<textarea name="deviceExtInfo.comment" rows="3" cols="60"></textarea>
            	</td>            	
			</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<input type="button" value="关闭" class="button" onclick="window.close();"
	     </@buttonBar>
	 </@ww.form>
</@htmlPage>