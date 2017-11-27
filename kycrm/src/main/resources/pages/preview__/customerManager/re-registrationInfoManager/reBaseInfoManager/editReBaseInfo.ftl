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
<@ww.form namespace="'/reBaseInfoManager'" name="'reBaseInfo'" action="'editReBaseInfo_'" method="'post'">
 <@ww.token name="saveBaseInfoToken"/>
    <@inputTable>
	 <tr>
		<@ww.textfield label="'回访主题'" name="'id'" value="'业务谈判'" cssClass="'underline'" required="true"/>
   		<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">客户名称:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="customerName" 
			      class="underline"  value="丰登钢塑实业"  maxlength="140" size="20" />
		       <a onClick="scorePeople_OpenDialog();">
	    	     <img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0"/>
	    	   </a>
        </td>
        <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">联系人:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="comPerson" 
			      class="underline"  value="张三丰"  maxlength="140" size="20" />
		       <a onClick="scoreConPeople_OpenDialog();">
	    	     <img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0"/>
	    	   </a>
        </td>
	</tr>
	<tr>
		<@pp.datePicker label="'回访日期'" 
					name="'reDate'" 
   					value="'2008-09-07'"
					cssClass="'underline'" dateFormat="'%Y-%m-%d'" 
					required="true" />
		<@ww.textfield label="'花费时间'" name="'deTime'" value="'3小时'" cssClass="'underline'"/>
   		<@ww.select label="'回访方式'"
	                name="'meansView'"
	                headerKey="1"
	                headerValue="Select Department"
	                list="{	
							'电话回访',
							'登门回访',
							'视频回访',
							'网络回访'
	                    }"
	                 value="'登门回访'"
	      		/>
	  </tr>
	  <tr> 
	  	 <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">回访人:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="rePerson" 
			      class="underline"  value="刘大为"  maxlength="140" size="20"/>
		       <a onClick="scoreReviewPeople_OpenDialog();">
	    	     <img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0"/>
	    	   </a>
        </td>
   		 <@ww.select label="'继续回访'"
	                name="'conReview'"
	                headerKey="1"
	                headerValue="Select Department"
	                list="{	'否',	
							'是'
	                    }"
	                 value="'是'"
	      		/>
	     <@pp.datePicker label="'下次回访日期'" 
					name="'nextDate'" 
   					value="'2009-08-03'"
					cssClass="'underline'" dateFormat="'%Y-%m-%d'"/>
   	</tr>
   	<tr>
   		<@ww.textarea label="'回访内容'" name="'reviewNotes'" value="'确定结果'" rows='3' cols='30' cssClass="'underline'" required="true"/>
   		<@ww.textarea label="'客户反馈'" name="'customerNotes'" value="'很好'" rows='3' cols='30' cssClass="'underline'"/>
   	</tr>
   	<tr>
   		<@ww.textarea label="'后期注意'" name="'notes'" value="'谈判时间要注意'" rows='3' cols='30' cssClass="'underline'"/>
   		<@ww.textarea label="'备注'" name="'remarks'" value="'不要忘记'" rows='3' cols='30' cssClass="'underline'"/>
   	</tr>
    </@inputTable>
    <@buttonBar>
            <@vsubmit name="'save'" value="'${'保存'}'"/>
            <@redirectButton value="${'返回'}" url="${req.contextPath}/reBaseInfoManager/listReBaseInfo_.html"/>
    </@buttonBar>
</@ww.form>

<script type="text/javascript">
window.onload = function () {
			document.all.frame.src='${req.contextPath}/reBaseInfoManager/listAccessoriesInfo_.html';
 	}
	function scorePeople_OpenDialog(){
	   var url = "${req.contextPath}/reBaseInfoManager/selectPerson_.html";
	   popupModalDialog(url, 800, 600);
	 }
	 function scoreConPeople_OpenDialog(){
	   var url = "${req.contextPath}/reBaseInfoManager/selectConPerson_.html";
	   popupModalDialog(url, 800, 600);
	 }
	 function scoreReviewPeople_OpenDialog(){
	   var url = "${req.contextPath}/reBaseInfoManager/selectReviewPerson_.html";
	   popupModalDialog(url, 800, 600);
	 }
</script>
</@htmlPage>
<ul id="beautytab">
	<li>
		<a id="additionalInformation" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/reBaseInfoManager/listAccessoriesInfo_.html' target="frame" >附件资料</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>