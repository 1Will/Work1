<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="随机设备维护">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
		 	  <tr>
		 	  	<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  	<@ww.textfield label="'资产编号'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  </tr>
		 	  <tr>
		 	  	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  	<@ww.textfield label="'设备型号'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  </tr>
		 	  <tr>
		 	  	<@ww.textfield label="'设备规格'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  	<@ww.textfield label="'设备类别'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  </tr>
		 	  <tr>
		 	  	<@ww.textfield label="'使用部门'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  	<@ww.textfield label="'安装地点'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  </tr>
		 	  <tr>
		 	  	<@pp.datePicker label="'建卡时间'" name="'repair.estimate.time0'"
	     				value="" cssClass="'underline'" size="15" required="true"/>
		 	  </tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<input type="button" value="关闭" class="button" onclick="window.close();"
	     </@buttonBar>
	</@ww.form>
</@htmlPage>