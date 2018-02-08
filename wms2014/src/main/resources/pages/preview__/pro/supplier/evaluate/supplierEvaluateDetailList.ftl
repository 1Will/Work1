<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="随机设备查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>评定内容</th>
			 	<th>分值</th>
			 	<th>标准</th>
			 	<th>分值说明</th>
			 	<th>评分</th>
			 	<th>备注</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td>1</td>
				<td style="text-align:left">设计能力</td>
				<td>30</td>
				<td style="text-align:left">
					1、有敏锐的IE分析能力，根据现场要求，能够较快的现场设计画图，设计的产品精致好用； 					
				</td>
				<td>≥20</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td/>
				<td/>
				<td style="text-align:left"/>
				<td style="text-align:left">
					2、IE分析能力一般，根据现场要求，能够在相关人员的配合下，现场设计画图，设计的产品基本符合要求；					
				</td>
				<td>≥15</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td/>
				<td/>
				<td style="text-align:left"/>
				<td style="text-align:left">
					3、IE分析能力不强，不能较快领会使用人员意图，而且设计的产品笨拙。
				</td>
				<td><15</td>
				<td >
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td>2</td>
				<td style="text-align:left">制作能力（交货期）</td>
				<td>20</td>
				<td style="text-align:left">1、能够按时完成任务要求，一次验收合格率95%以上；</td>
				<td>≥15</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td/>
				<td style="text-align:left"></td>
				<td>20</td>
				<td style="text-align:left">2、能够按时完成任务要求，一次验收合格率80%以上；</td>
				<td>≥10</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td/>
				<td style="text-align:left"></td>
				<td/>
				<td style="text-align:left">3、不能够按时完成任务要求，一次验收合格率70%以上。</td>
				<td>＜10</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td>3</td>
				<td style="text-align:left">质量保证能力</td>
				<td>25</td>
				<td style="text-align:left">1、供货质量安全可靠，有完备的工艺控制手段；</td>
				<td>≥20</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td/>
				<td style="text-align:left"/>
				<td/>
				<td style="text-align:left">2、供货质量安全可靠，有一定的工艺控制手段；</td>
				<td>≥15</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td/>
				<td style="text-align:left"/>
				<td/>
				<td style="text-align:left">3、供货质量基本满足需求，工艺控制手段不强。</td>
				<td>＜15</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td>4</td>
				<td style="text-align:left">服务能力</td>
				<td>15</td>
				<td style="text-align:left">1、要求的时间内赶到现场，能够迅速解决问题；</td>
				<td>≥12</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td/>
				<td style="text-align:left"/>
				<td/>
				<td style="text-align:left">2、基本能够在要求的时间内赶到现场，能够很快解决问题；</td>
				<td>≥8</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td/>
				<td style="text-align:left"/>
				<td/>
				<td style="text-align:left">3、不能在要求的时间内赶到现场，解决问题能力一般。</td>
				<td>＜8</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td>5</td>
				<td style="text-align:left">成本控制能力</td>
				<td>10</td>
				<td style="text-align:left">1、能有效控制制作费用，根据要求降低制作成本；</td>
				<td>≥7</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td/>
				<td style="text-align:left"/>
				<td/>
				<td style="text-align:left">2、能够控制制作费用，但制作成本将辐较小。</td>
				<td>＜7</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="30"  maxlength="10" style="text-align:right"/>
				</td>
				<td>
				  <input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value=""  maxlength="150" />
				</td>	
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
        		<@vbutton class="button" value="保存" />	
	     	</@buttonBar>
     </@ww.form>
</@framePage>