<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: dTreePage.ftl 2009-11-30 9:50:35Z wliu $ -->

<#include "../../includes/macros.ftl" />

<@framePage title="${action.getText('area.manager')}">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
<table>
<tr width="100%">
  	<td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
		<SCRIPT LANGUAGE="JavaScript">
			var dt = new dTree('dt','${req.contextPath}/javascripts','cascadeForm');
			dt.add(0,-1,'【${action.getText('area.manager')}】<a href="editArea.html?flag=country&readOnly=${req.getParameter('readOnly')?if_exists}" target="cascade">${action.getText('area.newCountry')}</a>');
			<#if (allCountrys.size() > 0)>
			<#list allCountrys as country>
				dt.add(#{country.id},0,'${country.name}(${country.code})',#{country.id},'country','${req.contextPath}/areaManager/editArea.html?area.id=#{country.id}&readOnly=${req.getParameter('readOnly')?if_exists}','','cascade');//&flag="country"
			</#list>
			</#if>
			<#if (allProvinces.size() > 0)>
			<#list allProvinces as province>
				dt.add(#{province.id},#{province.parentArea.id},'${province.name}(${province.code})',#{province.id},'province','${req.contextPath}/areaManager/editArea.html?area.id=#{province.id}&readOnly=${req.getParameter('readOnly')?if_exists}','','cascade');//&flag="province"	
			</#list>
			</#if>
			<#if (allCitys.size() > 0)>
			<#list allCitys as city>
				dt.add(#{city.id},#{city.parentArea.id},'${city.name}(${city.code})',#{city.id},'city','${req.contextPath}/areaManager/editArea.html?area.id=#{city.id}&readOnly=${req.getParameter('readOnly')?if_exists}','','cascade');//&flag="city"	
			</#list>
			</#if>
			document.write(dt);
		</SCRIPT>
  	</td>
</tr>
</table>
</@framePage>