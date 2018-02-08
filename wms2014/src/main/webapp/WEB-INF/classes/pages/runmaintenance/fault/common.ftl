<#include "../../includes/eam2008.ftl" />

<script language="javascript">
	function queryDate(startElementName,endElementName, strStartMsg, strEndMsg){
		var strStartDate = new String(document.getElementById(startElementName).value);
		var strEndDate = new String(document.getElementById(endElementName).value);
		
		if(isNotEmpty(document.forms[0], startElementName)) {
			 if (!isDate(startElementName)) {
			 	alert(strStartMsg);
			    return false;
			 }
		}
		if(isNotEmpty(document.forms[0], endElementName)) {
			 if (!isDate(endElementName)) {
			 	alert(strStartMsg);
			    return false;
			 }
		}
		if (isNotEmpty(document.forms[0], startElementName) &&
		 	  isNotEmpty(document.forms[0], endElementName)) {
			 if (!isDateLessThenOtherDate(strStartDate, strEndDate)) {
			 	alert(strEndMsg);
			 	return false;
			 }
		 }
		return true;
    }
    function validateInvalid(delFun, checkFun) {
        if (delFun) {
        	checkFun;
         	return true;
         }
        return false;
    }
</script>  