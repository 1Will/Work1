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

<#include "../../../includes/macros.ftl" />

<@framePage>
<table>
<tr width="100%">
  	<td style="VERTICAL-ALIGN:top;text-align:left" width="40%">
		<SCRIPT LANGUAGE="JavaScript">
			var dt = new dTree('dt','${req.contextPath}/javascripts','addForm');
			dt.add(0,-1,'区域管理');
			dt.add(1,0,'中国',1,'国家');
			dt.add(2,0,'美国',2,'国家');
			dt.add(3,1,'安徽省',3,'省份');
			dt.add(4,1,'湖南省',4,'省份');
			dt.add(5,3,'合肥市',5,'城市');
			dt.add(6,3,'马鞍山市',6,'城市');
			document.write(dt);
		</SCRIPT>
  	</td>
</tr>
</table>
</@framePage>