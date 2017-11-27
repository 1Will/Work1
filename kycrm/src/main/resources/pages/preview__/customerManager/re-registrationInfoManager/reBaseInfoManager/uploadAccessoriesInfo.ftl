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

<@htmlPage title="上传页面">
<@ww.form namespace="'/reBaseInfoManager'" name="'reBase'" action="'uploadAccessoriesInfo_'" method="'post'">
 <@ww.token name="reBaseToken"/>
    <@inputTable>
      
	 <tr>
		<@ww.textfield label="'资料名称'" name="'id'" value="''" cssClass="'underline'" required="true"/>
		<@ww.textfield label="'上传人'" name="'deTime'" value="''" cssClass="'underline'"/>
		<@pp.datePicker label="'上传时间'" 
					name="'reDate'" 
   					value="''"
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
        	<textarea name="comment" rows="4" ></textarea>
        </td>
        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("comment").cols =width;
			</script>
	</tr>
    </@inputTable>
    <@buttonBar>
            <@vsubmit name="'upload'" value="'${'上传'}'"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>