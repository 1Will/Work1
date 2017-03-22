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

<#-- $Id: dutySearcher.ftl 2009-09-18 13:32:35Z wliu $ -->

<#include "../../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'回访主题'" name="'id'" value="'${req.getParameter('id')?if_exists}'" cssClass="'underline'"/>
   		<@ww.textfield label="'客户名称'" name="'customerName'" value="'${req.getParameter('customerName')?if_exists}'" cssClass="'underline'"/>
   		<@ww.textfield label="'联系人'" name="'comPerson'" value="'${req.getParameter('comPerson')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
   		<@ww.select label="'回访方式'"
	                name="meansView"
	                headerKey="1"
	                headerValue="SelectDepartment"
	                list="{	'所有',	
							'电话回访',
							'登门回访',
							'视频回访',
							'网络回访'
	                    }"
	                 value="'${req.getParameter('meansView')?if_exists}'"
	      		/>
   		<@ww.select label="'继续回访'"
	                name="'conReview'"
	                headerKey="conReview"
	                headerValue="SelectDepartment"
	                list="{	
	                		'所有',
	                		'否',	
							'是'
	                    }"
	                 value="'${req.getParameter('conReview')?if_exists}'"
	      		/>
	   <@pp.datePicker label="'回访日期'" 
					name="'reDate'" 
   					value="'${req.getParameter('reDate')?if_exists}'"
					cssClass="'underline'" dateFormat="'%Y-%m-%d'"/>
   	</tr>
</@inputTable>