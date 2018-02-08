<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="设备润滑实施查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
	        	<@ww.textfield label="'润滑计划编号'" name="'area.code'" value="" cssClass="'underline'" />
	        	<@ww.textfield label="'润滑计划名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>
	        <tr>
	        	<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" />
	        	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>		      
	        <tr>
	             <@dept0/>
				 <@pp.dateRanger label="'编制日期'" name="'accidentOccurDateTm'" 
		       value="'${req.getParameter('accidentOccurDateTm_start')?if_exists}|${req.getParameter('accidentOccurDateTm_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
	        	
	        </tr>
	        <tr>
	           <@ww.select label="'状态'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}',
	                    			'${action.getText('未完成')}', 
	                    			'${action.getText('已完成')}'
	                    	  	  }"
	                    	value="selectedDevice"
	                    	required="false"
	        		/>
	        </tr>
	    </@inputTable>
        <@buttonBar>
	        	<@redirectButton value="${action.getText('search')}" url="#"/>
	        	<@redirectButton value="${action.getText('new')}" url="editSmoothPlanImpls.html"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            		<tr>
            			<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
		  				<th>润滑计划编号</th>
		  				<th>润滑计划名称</th>
		  				<th>设备编号</th>
		  				<th>设备名称</th>
		  				<th>部门</th>
		  				<th>编制人</th>
		  				<th>编制日期</th>
		  				<th>状态</th>
            		</tr>
            		<tr>
            			<td><input type="checkbox" name="checkbox" value="true"></td>
            			<td style="text-align:left"><a href="editSmoothPlanImpls.html">jac-RH-025</a></td>
		  				<td style="text-align:left">主润滑油箱润滑油</td>
		  				<td style="text-align:left">00123</td>
		  				<td style="text-align:left">机器人</td>
		  				<td style="text-align:left">机动部</td>
		  				<td style="text-align:left">张辉</td>
		  				<td>2007-09-22</td>
						<td style="text-align:left">未完成</td>
					</tr>
            	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    	</@buttonBar>  	
	    	  <script>
	    	function popup() {
	    		popupModalDialog("listSmoothPlanItems.html?selector=1", 1024, 800);
	    	}
	    </script>
     </@ww.form>
</@htmlPage>