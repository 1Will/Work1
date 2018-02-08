<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: dutyProfile.ftl 2009-09-18 13:21:35Z wliu $ -->

<#include "../../includes/macros.ftl" />

<@htmlPage title="申购单明细信息维护">
<@ww.form namespace="'/prophase/business'"  action="'saveSubscribeDetailsTest'" method="'post'">
 <@ww.token name="saveSubscribeDetailsTestToken"/>
    <@inputTable>
	 	<tr>
			<@ww.textfield label="'图号'" name="'name'" value="" cssClass="'underline'" required="false"/>
			<@ww.textfield label="'品名'" name="'goods'" value="" cssClass="'underline'" required="true"/>
		</tr>
		<tr>
			<@ww.textfield label="'规格型号'" name="'inPrice'" value="" cssClass="'underline'" required="true"/>
	    	<@ww.select label="'种类'"
            name="'kind'"
            headerKey="1" 
            headerValue="Select Month"
            list="{
                   '',
                   '电器类', 
                    '机械类', 
                    '工具类',
                    '液压密封类',
                    '润滑类'
                   }"
            value="selectedDevice"
        	/>
	    </tr>	
	    <tr>
	    	<@ww.select label="'明细种类'"
            name="'detailKind'"
            headerKey="1" 
            headerValue="Select Month"
            list="{
                   '',
                   '轴承类', 
                    '齿轮类', 
                    '工位器具',
                    '热片类',
                    '法兰类'
                   }"
            value="selectedDevice"
        	/>
        	<@ww.select label="'单位'"
            name="'unit'"
            headerKey="1" 
            headerValue="Select Month"
            list="{
                   '',
                   '只', 
                    '个'
                   }"
            value="selectedDevice"
        	/>
	    </tr>
	    <tr>
			<@ww.textfield label="'数量'" name="'mount'" value="" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'单价'" name="'commission'" value="" cssClass="'underline'" required="false"/>
		</tr>
		<tr>
		   <@ww.textfield label="'总价'" name="'interest'" value="" cssClass="'underline'" required="false"/>
		   <@pp.datePicker label="'需要日期'" 
					name="'reDate'" 
   					value=""
   					required="true"
					cssClass="'underline'" dateFormat="'%Y-%m-%d'"/>
		</tr>
		<tr>
		   <@ww.textfield label="'供应商'" name="'mount'" value="" cssClass="'underline'" required="true"/>
		   <@ww.select label="'状态'"
            name="'status'"
            headerKey="1" 
            headerValue="Select Month"
            list="{
                   '',
                   '新建', 
                    '采购中', 
                    '已采购',
                    '入库中',
                    '已入库'
                   }"
            value="selectedDevice"
        	/>
		</tr>
		<tr>
			 <@ww.textarea label="'${action.getText('备注')}'" 
					         name="'subscribeDtl.comment'" 
					         value="" rows="'3'" cols="'60'" 
				 /> 
		</tr>
		<tr>
		    <@ww.textfield label="'采购数量'" name="'mount'" value="'100'" cssClass="'underline'" disabled="true"/>
			<@ww.textfield label="'到货数量'" name="'commission'" value="'100'" cssClass="'underline'" disabled="true"/>
		</tr>
		<tr>
			<@ww.textfield label="'到货日期'" name="'commission'" value="'2010-12-08'" cssClass="'underline'" disabled="true"/>
		</tr>
    </@inputTable>
    <@buttonBar>
            <@vsubmit name="'save'" value="'${'保存'}'"/>
            <@vsubmit name="'save'" value="'${'继续新建'}'"/>
            <@vsubmit  value="'${action.getText('返回')}'" onclick="'return closePage()'"/>
    </@buttonBar>
</@ww.form>
<ul id="beautytab">
	<li><a id="details" onclick="activeTab(this);" 
		href="listMatchingSpareParts.html" target="frame" >匹配备件列表</a>
	</li>			
</ul>
<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>

</@htmlPage>
<script language="javascript">
	function closePage(){
        	window.close();
        	return false;
        }
	</script>
