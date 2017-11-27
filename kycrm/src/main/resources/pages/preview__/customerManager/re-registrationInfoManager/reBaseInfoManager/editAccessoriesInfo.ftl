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

<#include "../../../../includes/macros.ftl" />

<@htmlPage title="回访基本信息维护">
<@ww.form namespace="'/reBaseInfoManager'" name="'reBaseInfo'" action="'editAccessoriesInfo_'" method="'post'">
 <@ww.token name="saveBaseInfoToken"/>
    <@inputTable>
	 <tr>
		<@ww.textfield label="'资料名称'" name="'id'" value="'技术方案书'" cssClass="'underline'" required="true"/>
		<@ww.textfield label="'上传人'" name="'deTime'" value="'传小文'" cssClass="'underline'"/>
		<@pp.datePicker label="'上传时间'" 
					name="'reDate'" 
   					value="'2008-09-07'"
					cssClass="'underline'" dateFormat="'%Y-%m-%d'" 
					required="true" />
	</tr>
	<tr>
		<td align="right" valign="top">
        	<label class="label">
        		<font color=red>*</font>上传文件:
        	</label>
		  </td>
		<td colspan="8">
			<input type="file" size="50"/>
		</td>
    </tr>
	<tr>
	   <td align="right" valign="top">
    		<label class="label">
    			备注:
    		</label>
    	</td>
        <td colspan="10">
        	<textarea name="comment" rows="4" >关于技术方案的设计</textarea>
        </td>
        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("comment").cols =width;
			</script>
	</tr>
    </@inputTable>
    <@buttonBar>
            <@vsubmit name="'save'" value="'${'保存'}'"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>