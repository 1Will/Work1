<#include "../../includes/eam2008.ftl" />

<script language="javascript">
	function queryDate(startElementName,endElementName, strStartMsg, strEndMsg){
		var strStartDate = new String(getObjByNameRe(startElementName).value);
		var strEndDate = new String(getObjByNameRe(endElementName).value);
		
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
    function validateDelete(delFun, checkFun) {
        if (delFun) {
        	checkFun;
         	return true;
         }
        return false;
    }
    function commonValidate(){
    	if (isEmpty(document.forms[0], "accidentBill.accidentOccurDateTm")) {
	      alert("${action.getText("select.accidentOccurDateTm")}");
	      return false;
	    } else {
	      if (!accidentOccurDateTmValidate()) {
	        return false;
	      }
	    }
	    if (isEmpty(document.forms[0], "manager.id")) {
	      alert("${action.getText("select.manager")}");
	      return false;
	    }
	    if (isNotEmpty(document.forms[0], "accidentBill.accidentDetailContent")) {
	      if (!isValidLength(document.forms[0], new Array("accidentBill.accidentDetailContent"), 0, 250)) {
	        alert("${action.getText("accidentDetailContent.length.overflow")}");
	        return false;
	      }
	    }
	    if (isNotEmpty(document.forms[0], "accidentBill.accidentSolution")) {
	      if (!isValidLength(document.forms[0], new Array("accidentBill.accidentSolution"), 0, 250)) {
	        alert("${action.getText("accidentSolution.length.overflow")}");
	        return false;
	      }
	    }
	    return true;
    }
    
</script>  