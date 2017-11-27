/**
 * Title: 用于自动检查表单的输入是否符合要求的JS文件
 * Description:
 1.使用方法(注意字母大小写)
 	在你要检查的控件上加上属性
		CK_TYPE 	要检查的类型，多种类型可以用逗号组合起来
		CK_NAME 	出错时，显示的出错字段名
		CK_MSG_XXXX  定制的出错信息,XXXX表示可以是任意要检查的类型

	举例(必须一次包含下列js文件):
	<SCRIPT language=javascript src="include/js/trimspace.js"></script>
	<Script language="JavaScript"  src="include/js/checkform.js"></Script>
	<Script language="JavaScript"  src="include/js/autocheckform.js"></Script>

		input name="postcode"  CK_TYPE="NotEmpty,Number,PostCode" CK_NAME="邮政编码"
		CK_MSG_NotEmpty = "邮政编码是无论如何都不可以为空的，请重新输入!"

	实际的测试样例是testjs目录下的Test_autoCheckForm.jsp

	目前可以使用的类型有:
	NotEmpty  	非空
	Number		数字
	Int		    数字    add by xq
	Date		日期
	Pselect      请选择   add by xq
	EMail		EMail地址
	Money        钱    add by xq
	Postcode	邮政编码
	Telphone	电话号码
        MobileNO  	手机号码
	NoSpace		不含空格
	Len_X		字符串长度要求为X,例如:Len_2 表示要求长度为2
        Float           可带小数点的数字。
	MaxLen_X	字符串长度要求最大为X,例如:MaxLen_2 表示要求长度最大为2
        MinLen_X	字符串长度要求最小为X,例如:MinLen_2 表示要求长度最小为2

  	如何修订和改进本文件:
	如果要添加新的类型，请在函数autoCheckForm(objForm)中添加相关的处理代码,并在上面的可用类型
	列表中给出说明即可
*/

/**
*自动检查表单的输入是否符合要求
*@param objForm 要检查的窗体
*/
function  autoCheckForm(objForm){
  var i;
  for (i=0;i<objForm.elements.length;i++) {//遍历表单所有元素
    var objCheckItem;//要检查的项
    var strItemCKType;//要检查的格式列表，用逗号分隔    
    objCheckItem 	= objForm.elements[i];
	try{
	  strItemCKType = objCheckItem.getAttribute("CK_TYPE");
	}catch(e){
		//alert(e);
		continue;
	}

    if (strItemCKType!= null) {
      strItemCKType+= ","
      var iPosBegin = 0;
      var  iPosEnd = strItemCKType.indexOf(",",iPosBegin);

      while (iPosEnd > 0) {//遍历所有该字段要检查的格式要求
              var  sCKType ;
              sCKType = strItemCKType.substr(iPosBegin,iPosEnd - iPosBegin);

              if (sCKType  == "NotEmpty")	{//非空
                  if  (JsTrim(objCheckItem.value).length <=0) {
                      if (objCheckItem.getAttribute("CK_MSG_NotEmpty")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_NotEmpty"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_NotEmpty"));
                    	  }
                      }
                      else {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "不能为空（包括全为空格视为空）!");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "不能为空（包括全为空格视为空）!");
                    	  }
                      }
                      objCheckItem.focus();
                      return false;
                  }
              }
              else if (sCKType == "Number") {//数字
                  if (objCheckItem.value!=""&&fucCheckNUM(objCheckItem.value) == 0) {
                      if (objCheckItem.getAttribute("CK_MSG_Number")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Number"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_Number"));
                    	  }
                      }
                      else {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "必须是数字!");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "必须是数字!");
                    	  }
                      }
                      objCheckItem.select();
                      return false;
                  }
              }
              else if (sCKType == "Int") {//数字
                  if (objCheckItem.value!=""&&fucCheckInt(objCheckItem.value) == 0) {
                      if (objCheckItem.getAttribute("CK_MSG_Int")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Int"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_Int"));
                    	  }
                      }
                      else {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "必须是整数!");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "必须是整数!");
                    	  }
                      }
                      objCheckItem.select();
                      return false;
                  }
                  }
              else if (sCKType == "Money") {//数字
                  if (objCheckItem.value!=""&&checkMoney(objCheckItem.value) == -1) {
                      if (objCheckItem.getAttribute("CK_MSG_Money")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Int"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_Int"));
                    	  }
                      }
                      else {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "填写有误!");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "填写有误!");
                    	  }
                      }
                      objCheckItem.select();
                      return false;
                  }
              }
               else if (sCKType == "Double") {//双精度数（整数也可）
                      if (objCheckItem.value!=""&&fucCheckDouble(objCheckItem.value) == 0) {
                              if (objCheckItem.getAttribute("CK_MSG_Double")!=null) {
                            	  try{
                            		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Double"));
                            	  }catch(e){
                            		  alert(objCheckItem.getAttribute("CK_MSG_Double"));
                            	  }
                              } else {
                            	  try{
                            		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "必须是小数（整数也可）!");
                            	  }catch(e){
                            		  alert(objCheckItem.getAttribute("CK_NAME") + "必须是小数（整数也可）!");
                            	  }
                              }

                              objCheckItem.select();
                              return false;
                      }
                  //alert("检测是小数！");
              }
              else if (sCKType == "Float") {  //带小数点的数字
                if(objCheckItem.value!=""&&objCheckItem.value!=null){
                if (chknbr(objCheckItem.value,1,0) == 0) {
                      if (objCheckItem.getAttribute("CK_MSG_Float")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Float"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_Float"));
                    	  }
                      } else {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "必须是数字!");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "必须是数字!");
                    	  }
                      }

                      objCheckItem.select();
                      return false;
                      }
                }
              }
              else if (sCKType == "Date") {//日期
                  if (objCheckItem.value!=""&&chkdate(objCheckItem.value) == 0) {
                      if (objCheckItem.getAttribute("CK_MSG_Date")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Date"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_Date"));
                    	  }
                      }
                      else {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "的格式无效!正确格式：yyyy-mm-dd");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "的格式无效!正确格式：yyyy-mm-dd");
                    	  }
                      }
                      objCheckItem.select();
                      return false;
                  }
              }
              else if (sCKType == "English") { //英文字母和数字
                  if (isEnglish(objCheckItem.value)==0){
                      if (objCheckItem.getAttribute("CK_MSG_English")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_English"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_English"));
                    	  }
                      }
                      else {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "只能含有英文字母和数字!");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "只能含有英文字母和数字!");
                    	  }
                      }
                      objCheckItem.select();
                      return false;
                  }
              }
              else if (sCKType == "Pselect") {//选择
                  if (objCheckItem.value=="-1") {
                      if (objCheckItem.getAttribute("CK_MSG_Pselect")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Pselect"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_Pselect"));
                    	  }
                      }
                      else {
                    	  try{
                    		  top.Dialog.alert("请选择"+objCheckItem.getAttribute("CK_NAME"));
                    	  }catch(e){
                    		  alert("请选择"+objCheckItem.getAttribute("CK_NAME"));
                    	  }
                      }
                      objCheckItem.focus();
                      return false;
                  }
              }
              else if (sCKType == "EMail") {//EMail地址
                  if (objCheckItem.value!=""&&chkemail(objCheckItem.value) == 0) {
                      if (objCheckItem.getAttribute("CK_MSG_EMail")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_EMail"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_EMail"));
                    	  }
                      }
                      else {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "的格式无效!正确格式：1. 必须有@和.符号\n2. 不能包含空格且@前至少要有三位字符");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "的格式无效!正确格式：1. 必须有@和.符号\n2. 不能包含空格且@前至少要有三位字符");
                    	  }
                      }
                      objCheckItem.select();
                      return false;
                  }
              }
              else if (sCKType == "Postcode") {//邮政编码
                  if (objCheckItem.value!=""&&fucCheckPostcode(objCheckItem.value) == 0) {
                      if (objCheckItem.getAttribute("CK_MSG_Postcode")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Postcode"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_Postcode"));
                    	  }
                      }
                      else {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "的格式无效!");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "的格式无效!");
                    	  }
                      }
                      objCheckItem.select();
                      return false;
                  }
              }
              else if(sCKType=="Telphone"){//电话号码
                  if(objCheckItem.value!=""&&fucCheckTEL(objCheckItem.value)==0) {
                      if(objCheckItem.getAttribute("CK_MSG_Telphone")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Telphone"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_Telphone"));
                    	  }
                      }
                      else{
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "的格式无效!");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "的格式无效!");
                    	  }
                      }
                      objCheckItem.select();
                      return false;
                  }
              }
               else if(sCKType=="MobileNO"){//手机号码
                  if(objCheckItem.value!=""&&fucCheckNUM(objCheckItem.value)==0) {
                      if(objCheckItem.getAttribute("CK_MSG_Telphone")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Telphone"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_Telphone"));
                    	  }
                      }
                      else{
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "的格式无效!");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "的格式无效!");
                    	  }
                      }
                      objCheckItem.select();
                      return false;
                  }
              }
              else if (sCKType == "NoSpace") {//不含空格
                  if (chkspc(objCheckItem.value) == 0) {
                      if (objCheckItem.getAttribute("CK_MSG_NoSpace")!=null) {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_NoSpace"));
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_MSG_NoSpace"));
                    	  }
                      }
                      else {
                    	  try{
                    		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "不能含有空格符!");
                    	  }catch(e){
                    		  alert(objCheckItem.getAttribute("CK_NAME") + "不能含有空格符!");
                    	  }
                      }
                      objCheckItem.select();
                      return false;
                  }
              }
              else if (sCKType == "IDCardNum") {//身份证号

              }
              else if (sCKType.indexOf("MaxLen_") >=0 ) {//MaxLen_X 字符串长度要求最大为X,例如:MaxLen_2 表示要求长度最大为2
                      var iLen;
                      iLen = sCKType.substr(sCKType.indexOf("_")+1);

                      if (objCheckItem.value.length > iLen) {
                              if (objCheckItem.getAttribute("CK_MSG_Len_X")!=null) {
                            	  try{
                            		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_MaxLen_X"));
                            	  }catch(e){
                            		  alert(objCheckItem.getAttribute("CK_MSG_MaxLen_X"));
                            	  }
                              } else {
                            	  try{
                            		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "长度最大为" + iLen + "!");
                            	  }catch(e){
                            		  alert(objCheckItem.getAttribute("CK_NAME") + "长度最大为" + iLen + "!");
                            	  }
                              }
                              objCheckItem.select();
                              return false;
                      }
              }
              else if (sCKType.indexOf("MinLen_") >=0 ) {//MinLen_X 字符串长度要求最小为X,例如:MinLen_2 表示要求长度最小为2
                      var iLen;
                      iLen = sCKType.substr(sCKType.indexOf("_")+1);

                      if (objCheckItem.value.length < iLen) {
                              if (objCheckItem.getAttribute("CK_MSG_Len_X")!=null) {
                            	  try{
                            		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_MinLen_X"));
                            	  }catch(e){
                            		  alert(objCheckItem.getAttribute("CK_MSG_MinLen_X"));
                            	  }
                              }
                              else {
                            	  try{
                            		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "长度最小为" + iLen + "!");
                            	  }catch(e){
                            		  alert(objCheckItem.getAttribute("CK_NAME") + "长度最小为" + iLen + "!");
                            	  }
                              }
                              objCheckItem.select();
                              return false;
                      }
              }
              else if (sCKType.indexOf("Len_") >=0 ) {//Len_X 字符串长度要求为X,例如:Len_2 表示要求长度为2
                      var iLen;
                      iLen = sCKType.substr(sCKType.indexOf("_")+1);

                      if (objCheckItem.value.length != iLen) {
                              if (objCheckItem.getAttribute("CK_MSG_Len_X")!=null) {
                            	  try{
                            		  top.Dialog.alert(objCheckItem.getAttribute("CK_MSG_Len_X"));
                            	  }catch(e){
                            		  alert(objCheckItem.getAttribute("CK_MSG_Len_X"));
                            	  }
                              } else {
                            	  try{
                            		  top.Dialog.alert(objCheckItem.getAttribute("CK_NAME") + "长度不满足要求!");
                            	  }catch(e){
                            		  alert(objCheckItem.getAttribute("CK_NAME") + "长度不满足要求!");
                            	  }
                              }
                              objCheckItem.select();
                              return false;
                      }
              }
              else {
            	  try{
            		  top.Dialog.alert("没有定义此类型的检查函数！");
            	  }catch(e){
            		  alert("没有定义此类型的检查函数！");
            	  }
                  return false;
              }

              iPosBegin =  iPosEnd +1;
              iPosEnd = strItemCKType.indexOf(",",iPosBegin)
      		}
    	}
  	}
}