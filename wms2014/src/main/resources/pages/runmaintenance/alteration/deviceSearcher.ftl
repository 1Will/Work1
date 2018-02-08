<#include "../../includes/eam2008.ftl" />
<@inputTable>
	 	  <@ww.hidden name="'toolingDevFlag'" value=""/>
	 		 <tr>
	        	<@ww.textfield label="'${action.getText('deviceSealed.sealedBillNo')}'" name="'sealedBillNo'" value="'${req.getParameter('sealedBillNo')?if_exists}'" cssClass="'underline'"  />
	        	<@ww.textfield label="'${action.getText('deviceSealed.sealedBillName')}'" name="'sealedBillName'" value="'${req.getParameter('sealedBillName')?if_exists}'" cssClass="'underline'"  />
	        </tr>	        
	 		<tr>
			 	<@ww.textfield label="'${action.getText('device.no')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'" />
	 			<@ww.textfield label="'${action.getText('device.name')}'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
	        </tr>
	        <tr>
	        	<@ww.textfield label="'${action.getText('device.model')}'" name="'deviceModel'" value="'${req.getParameter('deviceModel')?if_exists}'" cssClass="'underline'"/>
	        	<@pp.dateRanger label="'${action.getText('deviceSealed.sealedDateTm')}'" name="'sealedDateTm'" 
		       value="'${req.getParameter('sealedDateTm_start')?if_exists}|${req.getParameter('sealedDateTm_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 	        	
	        </tr>	      
</@inputTable> 
<script language="javascript">
	getObjByNameRe("toolingDevFlag").value = 'DEVICE';
</script>