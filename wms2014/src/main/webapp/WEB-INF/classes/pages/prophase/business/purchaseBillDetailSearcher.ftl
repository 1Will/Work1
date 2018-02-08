<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('purBill.billNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('purBill.name')}'" name="'billName'" value="'${req.getParameter('billName')?if_exists}'" cssClass="'underline'" />	
	</tr>	
	<tr>
		<@ww.textfield label="'${action.getText('备件编号')}'" name="'spareNo'" value="'${req.getParameter('spareNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('备件名称')}'" name="'spareName'" value="'${req.getParameter('spareName')?if_exists}'" cssClass="'underline'" />	
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('purBillDtl.model')}'" name="'model'" value="'${req.getParameter('model')?if_exists}'" cssClass="'underline'" />
		<#--
		<@ww.textfield label="'${action.getText('purBillDtl.specification')}'" name="'specification'" value="'${req.getParameter('specification')?if_exists}'" cssClass="'underline'" />
		-->
		<@ww.select label="'${action.getText('purBillDtl.department')}'" 
                   name="'department'" 
			       value="'${req.getParameter('department')?if_exists}'" 
			       listKey="id" 
			       listValue="name"
                   list="departments" 
                   emptyOption="false" 
                   disabled="false">
        </@ww.select>
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('purBillDtl.supplier')}'" name="'supplier'" value="'${req.getParameter('supplier')?if_exists}'" cssClass="'underline'" />
	    <@ww.textfield label="'${action.getText('purBillDtl.buyer')}'" name="'buyer'" value="'${req.getParameter('buyer')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>    
	    <@pp.dateRanger label="'${action.getText('purBillDtlDate')}'" name="'purBillDtlDate'" 
		       value="'${req.getParameter('purBillDtlDate_start')?if_exists}|${req.getParameter('purBillDtlDate_end')?if_exists}'"
		cssClass="'underline'" dateFormat="date"/>
	</tr>
</@inputTable>
<script language="javascript">
	var selector = document.all("department");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
    <#if req.getParameter('department')?exists>
    if (selector.options[i].value == "${req.getParameter('department')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
    }
    <#if spareInBill?exists>
      $('supplier').value="${spareInBill.supplier.name}";
    </#if>
	function checkInvalidParms(){
	  if(document.forms[0].elements["purBillDtlDate_start"].value!=""){
          if(!validateTime(document.forms[0].elements["purBillDtlDate_start"].value)){
               alert("${action.getText('purBillDtl.start_EndTimeMistake')}");
               return false;
          }
      }
      if(document.forms[0].elements["purBillDtlDate_end"].value!=""){
         if(!validateTime(document.forms[0].elements["purBillDtlDate_end"].value)){
               alert("${action.getText('purBillDtl.start_EndTimeMistake')}");
               return false;
          }
      }
      if (document.getElementById("department").value == -1) {
  	    document.getElementById("department").value = '';
      } 
      return true;
	}
</script>