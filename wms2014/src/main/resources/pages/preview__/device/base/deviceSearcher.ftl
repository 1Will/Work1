<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('device.code')}'" name="'area.code'" value="'K023'" cssClass="'underline'"  />
		<@ww.textfield label="'设备名称'" name="'area.code'" value="''" cssClass="'underline'"  />
		<@equimentLB0/>
	</tr>
	<tr>
		<@ww.textfield label="'使用部门'" name="'area.code'" value="''" cssClass="'underline'"  />
		<@ww.textfield label="'安装地点'" name="'area.code'" value="''" cssClass="'underline'"  />
	</tr>
	<tr>
		<@pp.datePicker label="'从建卡时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	     			
	    <@pp.datePicker label="'至建卡时间'" name="'time1'"
	     			value="" cssClass="'underline'" size="15"/>
	</tr>
</@inputTable>  