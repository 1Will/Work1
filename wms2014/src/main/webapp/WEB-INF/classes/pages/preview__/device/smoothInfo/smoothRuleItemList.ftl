<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="润滑标准列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 <#if req.getParameter("selector")?exists>
	 		<@inputTable>	 		
	        <tr>
	        	<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" />
	        	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"  readonly="true" />
	        </tr>       	        
        </@inputTable> 
        <@buttonBar>
        	<input type="button" value="查询" class="button"/>
        </@buttonBar>
     </#if>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>项目号</th>
			 	<th>润滑部位</th>
			 	<th>润滑内容</th>
			 	<th>润滑标准</th>
				<th>润滑方法</th>
				<th>润滑周期(天)</th>
				<th>上次润滑日期</th>
				<th>润滑材质</th>
		  		<th>润滑计量</th>		  		
				<th>责任人</th>
				<#if req.getParameter("selector")?exists>
					<th>预计计划执行日期</th>
				</#if>
			</tr>				
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">1</a></td>
				<td style="text-align:left">主润滑油箱润滑油</td>
				<td style="text-align:left">润滑加油</td>
				<td style="text-align:left">是否达到标准油位</td>
				<td style="text-align:left">极压工业齿轮油L-CKC100-150</td>
				<td style="text-align:left">7</td>
				<td>2008-7-23</td>
		  		<td>极压工业齿轮油L-CKC100-150</td>
		  		<td/>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">操作工实施，维修工指导</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">2</a></td>
				<td style="text-align:left">主润滑油箱加热油</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">是否达到标准油位</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">30</td>				
				<td>2008-7-23</td>
		  		<td>抗燃液压油L-HFDR100</td>
		  		<td/>
		  		<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">维修工</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>		
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">3</a></td>
				<td style="text-align:left">气垫润滑</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">气垫油位在上下限刻度之间</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">30</td>				
				<td>2008-7-23</td>
		  		<td>抗磨液压油L-HM32</td>
		  		<td/>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">操作工</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">4</a></td>
				<td style="text-align:left">移动工作台夹紧油箱</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">是否达到标准油位</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">30</td>				
				<td>2008-7-23</td>
		  		<td>抗磨液压油L-HM68</td>
		  		<td/>
		  		<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">操作工实施，维修工指导</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>			
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">5</a></td>
				<td style="text-align:left">移动工作台托板升降油箱</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">是否达到标准油位</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">30</td>				
				<td>2008-7-23</td>
		  		<td>抗磨液压油L-HM69</td>
		  		<td/>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">操作工实施，维修工指导</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">6</a></td>
				<td style="text-align:left">滑块调整齿轮箱/移动工作台传动减速箱</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">是否达到标准油位</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">30</td>				
				<td>2008-7-23</td>
		  		<td>极压工业齿轮油L-CKC150</td>
		  		<td/>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">维修工</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">7</a></td>
				<td style="text-align:left">油雾器</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">是否达到标准油位</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">7</td>				
				<td>2008-7-23</td>
		  		<td>抗氧防锈工业齿轮油L-CKB68</td>
		  		<td/>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">操作工实施，维修工指导</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">8</a></td>
				<td style="text-align:left">液压过载保护系统</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">是否达到标准油位</td>
				<td style="text-align:left">加油</td>
				<td style="text-align:left">7</td>				
				<td>2008-7-23</td>
		  		<td>抗磨液压油L-HM68</td>
		  		<td/>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">操作工实施，维修工指导</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">9</a></td>
				<td style="text-align:left">移动工作台齿轮/其他调整齿轮</td>
				<td style="text-align:left">涂敷</td>
				<td style="text-align:left">定期加油</td>
				<td style="text-align:left">涂敷</td>
				<td style="text-align:left">120</td>				
				<td>2008-7-23</td>
		  		<td>1#锂基脂</td>
		  		<td/>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">维修工</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">10</a></td>
				<td style="text-align:left">电缆支架轴承/小车滚轮轴承/联轴节/气动夹紧器/其他分散润滑点</td>
				<td style="text-align:left">用油枪给油</td>
				<td style="text-align:left">定期加油</td>
				<td style="text-align:left">用油枪给油</td>
				<td style="text-align:left">120</td>				
				<td>2008-7-23</td>
		  		<td>1#锂基脂</td>
		  		<td/>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">维修工</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);return false;">11</a></td>
				<td style="text-align:left">主电动机</td>
				<td style="text-align:left">用油枪给油</td>
				<td style="text-align:left">定期加油</td>
				<td style="text-align:left">用油枪给油</td>
				<td style="text-align:left">30</td>				
				<td>2008-7-23</td>
		  		<td>1#锂基脂</td>
		  		<td/>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value=""/></td>
				<#else>
					<td style="text-align:left">操作工实施，维修工指导</td>
				</#if>
				<#if req.getParameter("selector")?exists>
					<td><input type="text" class="underline" value="2007-12-20"/></td>
				</#if>
			</tr>	
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	     		<#if req.getParameter("selector")?exists>
	     			<input type="button" value="保存" class="button" />
	     		<#else>
	    			<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothRuleItem.html',600,300);"/>
	    		</#if>
	    		<#if req.getParameter("selector")?exists>
	    			<input type="button" value="关闭" class="button" onclick="window.close();"/>
	    		<#else>
		    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
		    	</#if>
	    	</@buttonBar>  	
     </@ww.form>
</@framePage>