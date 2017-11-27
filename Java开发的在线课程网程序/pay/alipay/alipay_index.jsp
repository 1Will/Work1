<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>支付宝充值</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language=javascript>
function saveRecord(){
  document.pageForm.action="payAlipayInfoAction.do?method=api";
  document.pageForm.submit();
}
</script>
</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4>
<form name="pageForm"  method=post target="_blank">
	 <table width="660"  border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	     <td colspan="2" align="center" height="60">支付宝支付</td>
	   </tr>
	   <tr>
	     <td width="80" align="center">付款金额:</td>
	     <td align="left" style="padding-left:40px"><input size="10" name="WIDprice" value="0.01"/>&nbsp;元</td>
	   </tr>
       <tr>
         <td align="center" colspan="2" height="40">
           <input type="button"  name="btnsave" value="下一步" onclick="javascript:saveRecord()"/>
         </td>
       </tr>
       <input size="30" type="hidden" name="order_no" value="<bean:write name='orderno'/>"/>
       <input size="30" type="hidden" name="WIDseller_email" value="bjgaocl@sohu.com"/>
       <input size="30" type="hidden" name="WIDout_trade_no" value="<bean:write name='orderno'/>"/>
       <input size="30" type="hidden" name="WIDsubject" value="<bean:write name='orderno'/>"/>
       <input size="30" type="hidden" name="WIDbody" value="在线充值"/>
       <input size="30" type="hidden" name="WIDshow_url" value="http://school.wkmk.com/daisai/fs/index.shtml"/>
       <input size="30" type="hidden" name="WIDreceive_name" value="高丞梁"/>
       <input size="30" type="hidden" name="WIDreceive_address" value="北京市西城区新街口外大街8号金丰和写字楼B-205室"/>
       <input size="30" type="hidden" name="WIDreceive_zip" value="100088"/>
       <input size="30" type="hidden" name="WIDreceive_phone" value="010-84929209"/>
       <input size="30" type="hidden" name="WIDreceive_mobile" value="13401030303"/>
     </table>  
</form>
</body>

</html>