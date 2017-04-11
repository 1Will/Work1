/*
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
   shall not disclose such Confidential Information and shall use it only in
   accordance with the terms of the license agreement you entered into with
   YongJun.
   
   YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
   SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
   WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
   NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
   LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
   DERIVATIVES.
 */
/* $Id: global.js 11271 2008-03-11 12:52:15Z wzou $ */

noSelect = "\u8bf7\u9009\u62e9\u9700\u8981\u5220\u9664\u7684\u8bb0\u5f55!"
//noInvalid = "\u8bf7\u9009\u62e9\u9700\u8981\u5931\u6548\u7684\u5907\u4ef6"
noInvalid = "\u8bf7\u9009\u62e9\u9700\u8981\u5931\u6548\u7684\u8bb0\u5f55!"
noValid = "请选择需要有效的记录!"
noSelectLockedRecord="请选择需要锁定的记录!"
noSelectUnLockedRecord="请选择需要解锁的记录!"
selectChangeStatus="请选择需要改变状态的记录"

function hasChecked(boxName) {
    var selector = document.getElementsByName(boxName);
    if (!selector) {
        return false;
    }
    if (selector.length) {
        for (i = 0; i < selector.length; i++) {
            if (selector[i].checked) {
                return true;
            }
        }
        return false;
    } else {
        return selector.checked;
    }
}

function confirmDeletes(boxName, message) {
    if (hasChecked(boxName)) {
        return confirmDelete(message);
    } else {    	
        alert(noSelect);
        return false;
    }
}

function selectRecod(boxName, message) {
    if (hasChecked(boxName)) {
        return confirmDelete(message);
    } else {    	
        alert(selectChangeStatus);
        return false;
    }
}

function confirmLockeds(boxName, message) {
    if (hasChecked(boxName)) {
        return confirmDelete(message);
    } else {    	
        alert(noSelectLockedRecord);
        return false;
    }
}
function confirmUnLockeds(boxName, message) {
    if (hasChecked(boxName)) {
        return confirmDelete(message);
    } else {    	
        alert(noSelectUnLockedRecord);
        return false;
    }
}

function confirmDelete(message) {
    return confirm(message);
}

function confirmInvalids(boxName, message) {
    if (hasChecked(boxName)) {
        return confirmDelete(message);
    } else {    	
        alert(noInvalid);
        return false;
    }
}
function confirmValids(boxName, message) {
    if (hasChecked(boxName)) {
        return confirmDelete(message);
    } else {    	
        alert(noValid);
        return false;
    }
}
function popupModalDialog(url, width, height, eventHandler) {
    var wd = (width == null) ? 900 :width;
    var hg = (height == null) ? 600 :height;

    if (isIE()) {
        result = showModalDialog(url, self,
                "dialogHeight:" + height + "px;dialogWidth:" + width
                        + "px; center: yes; help: no; resizable: no; status: no;");                        
        if (eventHandler != null) {
            eventHandler(result);
        }
    }
    else {
        ModalDialogShow(url, wd, hg, eventHandler);
    }
}

function returnDialog(result, reload)
{
    if (isIE()) {
        window.returnValue = result;
    } else {
        window.opener.ModalDialog.value = result;
    }
    if ((reload == null)? false : reload) {
        if (isIE()) {
            window.dialogArguments.location.reload();
        } else {
            window.opener.location.reload();
        }
    }
    
    window.close();
}

function toggleVisibility(targetId) {
    if (document.getElementById) {
        target = document.getElementById(targetId);
        if (target.style.visibility == "hidden") {
            target.style.visibility = (document.getElementById || document.all)? "visible" : "show";
        } else {
            target.style.visibility = "hidden";
        }
    }
}

function isIE() {
    return  /msie/i.test(navigator.userAgent) &&
            !/opera/i.test(navigator.userAgent);
}


/*** EAM2000 customize js **/
function activeTab(obj) {
	var sfEls = document.getElementById("beautytab").getElementsByTagName("li");
	for (var i=0; i<sfEls.length; i++) {
		if (sfEls[i].getElementsByTagName("a")[0].id == obj.id) {
			sfEls[i].getElementsByTagName("a")[0].className = "selectedtab";
		} else {
			sfEls[i].getElementsByTagName("a")[0].className = '';
		}
	}
}

/**
 * Read the JavaScript cookies tutorial at:
 *   http://www.netspade.com/articles/javascript/cookies.xml
 */

/**
 * Sets a Cookie with the given name and value.
 *
 * name       Name of the cookie
 * value      Value of the cookie
 * [expires]  Expiration date of the cookie (default: end of current session)
 * [path]     Path where the cookie is valid (default: path of calling document)
 * [domain]   Domain where the cookie is valid
 *              (default: domain of calling document)
 * [secure]   Boolean value indicating if the cookie transmission requires a
 *              secure transmission
 */
function setCookie(name, value, expires, path, domain, secure)
{
    document.cookie= name + "=" + escape(value) +
        ((expires) ? "; expires=" + expires.toGMTString() : "") +
        ((path) ? "; path=" + path : "") +
        ((domain) ? "; domain=" + domain : "") +
        ((secure) ? "; secure" : "");
}

/**
 * Gets the value of the specified cookie.
 *
 * name  Name of the desired cookie.
 *
 * Returns a string containing value of specified cookie,
 *   or null if cookie does not exist.
 */
function getCookie(name)
{
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1)
    {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    }
    else
    {
        begin += 2;
    }
    var end = document.cookie.indexOf(";", begin);
    if (end == -1)
    {
        end = dc.length;
    }
    return unescape(dc.substring(begin + prefix.length, end));
}

/**
 * Deletes the specified cookie.
 *
 * name      name of the cookie
 * [path]    path of the cookie (must be same as path used to create cookie)
 * [domain]  domain of the cookie (must be same as domain used to create cookie)
 */
function deleteCookie(name, path, domain)
{
    if (getCookie(name))
    {
            document.cookie = name + "=" + 
            ((path) ? "; path=" + path : "") +
            ((domain) ? "; domain=" + domain : "") +
            "; expires=Thu, 01-Jan-70 00:00:01 GMT";
    }
}

/**
	The function is to format the time
    Date: 04/23/2009
*/
Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth()+1,                  //month
        "d+" : this.getDate(),                     //day
        "h+" : this.getHours(),                    //hour
        "m+" : this.getMinutes(),                  //minute
        "s+" : this.getSeconds(),                  //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds()               //millisecond
    }
    if(/(y+)/.test(format))
    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}

function __isNumber__(e, msg){
	var num=document.getElementById(e).value;
	s = new String(num);
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1){
		return true;
	} else{
		alert(msg);
		return false;
	}
}


function __disableAllElements__(form, excepts) {
	var elements = form.elements;
	for (var i = 0; i < elements.length; i ++) {
		if (elements[i].type.toUpperCase()=="TEXT" ||
			elements[i].type.toUpperCase()=="INPUT" ||
			elements[i].type.toUpperCase()=="BUTTON" ||
			elements[i].type.toUpperCase()=="SUBMIT" ||
			elements[i].type.toUpperCase()=="LINK" ||
			elements[i].type.toUpperCase()=="SELECT-ONE" ||
			elements[i].type.toUpperCase()=="TEXTAREA" ) {
		if (__priv_containE(excepts, elements[i].name)) {
				continue;
			}
			elements[i].disabled="disabled";
			elements[i].readonly="true";
		}			
	}
	var a = document.all.tags("a");
	for (i = 0; i < a.length; i ++) {
	  if (__priv_containE(excepts, a[i].name)) {
	    continue;
	  }
	  a[i].onclick="";
	}
}

function __priv_containE(ary, e) {
	if (null == ary) {
		return false;
	}
	for (var i = 0; i < ary.length; i ++) {
		if(ary[i] == e) {
			return true;
		}
	}
	return false;
}

function isNumber(e) {
	var num=document.getElementById(e).value;
	num = formatDigital(num);
	s = new String(num);
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1){
		return true;
	} else{
		return false;
	}
}
function isBlank(e) {
  var ele = document.getElementById(e).value;
  //s = new String(ele);
  //var regu = "^\[ \t]*$";
  //var re = new RegExp(regu);
  //alert(s.indexOf(regu))
  //var reg_text=new   RegExp("^\[ \t]*$");
  if (ele.indexOf(" ")>-1) { 
    return true;
  } else {
    return false;
  }
}
/*
 * ??e??????
*/
function isDoubleNumber(e) {
  	var num=document.getElementById(e).value;
  	num = formatDigital(num);
	s = new String(num);
	s = formatDigital(s);
	var regu = "^\\d+(\\.\\d+)?$";
	var re = new RegExp(regu);
	if (s.search(re) != -1){
		return true;
	} else{
		return false;
	}
}
/*
*验证
*/
function isDoubleNumberCheck(e) {
  	var num=e;
  	num = formatDigital(num);
	s = new String(num);
	s = formatDigital(s);
	var regu = "^[-]?\\d+(\\.\\d+)?$";
	var re = new RegExp(regu);
	if (s.search(re) != -1){
		return true;
	} else{
		return false;
	}
}
/*
 * 验证targetId是否在maxValue和minValue之间
 * 返回 true 表示在maxValue和minValue之间
 * 返回 false 表示不在maxValue和minValue之间
*/
function isDoubleNumberBetweenBoolean(targetId,maxValue,minValue) {
    var num = targetId;
    num = formatDigital(num);
    s = new String(num);
    var regu = "^\\d+(\\.\\d+)?$";
    var re = new RegExp(regu);
    if (s.search(re) != -1) {
        if(parseFloat(s)>=parseFloat(maxValue)||parseFloat(s)<parseFloat(minValue)){
        return false;
        }else{
          return true;
        }
    } else {
        return false;
    }
}
function isNumberBetween(targetId,maxValue,minValue) {
    var num = targetId;
    num = formatDigital(num);
    s = new String(num);
    var regu = "^[0-9]+$";
    var re = new RegExp(regu);
    if (s.search(re) != -1) {
        if(parseInt(s)>=parseInt(maxValue)||parseInt(s)<parseInt(minValue)){
        return 0;
        }else{
          return 1;
        }
    } else {
        return -1;
    }
}

function isNumberBetweenBoolen(targetId,maxValue,minValue) {
    var num = targetId;
    num = formatDigital(num);
    s = new String(num);
    var regu = "^[-]?[0-9]+$";
    var re = new RegExp(regu);
    if (s.search(re) != -1) {
        if(parseInt(s)>=parseInt(maxValue)||parseInt(s)<parseInt(minValue)||parseInt(s)==0){
        return false;
        }else{
          return true;
        }
    } else {
        return false;
    }
}
function isNumberBetween(targetId,maxValue,minValue) {
    var num = targetId;
    num = formatDigital(num);
    s = new String(num);
        if(parseInt(s)>=parseInt(maxValue)||parseInt(s)<parseInt(minValue)){
        return false;
        }else{
          return true;
        }
}
function isNumberOfAll(form, elements) {
	for (var i = 0; i < elements.length; i ++) {
		var n = form.elements[elements[i]].value;
		s = new String(n);
		var regu = "^[0-9]+$";
		var re = new RegExp(regu);
		if (s.search(re) != -1){
			continue;
		} else{
			return false;
		}
	}
}

function isNotEmpty(form, elementName) {
	var e = form.elements[elementName].value
	return e  != '' && e != null;
}

function isNotEmptyValue(elementValue){
   var e = elementValue;
   return e  != '' && e != null;
}

function isEmptyValue(elementValue){
   return !isNotEmptyValue(elementValue);
}

function isEmpty(form, elementName) {
	return !isNotEmpty(form, elementName);
}

/*
 * 验证日期格式yyyy-MM-dd
*/
function isDate(eId) {
	var date = document.getElementById(eId).value;
	s = new String(date);
	var regu = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
	var re = new RegExp(regu);
	if (!(re.test(s))){
		return false;
	}
	return true;
}
/*
 * 验证日期格式yyyy-MM
*/
function isDateOfNotDay(eId) {
  var dateOfNotDay = document.getElementById(eId).value;
  s = new String(dateOfNotDay);
  var regu = "^\\d{4}-(1[0-2]|0[1-9])$";
  var re = new RegExp(regu);
  if (!(re.test(s))){
    return false;
  }
  return true;
}

/*
 * 验证日期格式yyyy-MM-dd HH:mm
*/
function isDate_Hour_Min(eId) {
	var date = document.getElementById(eId).value;
	s = new String(date);
	var regu = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
	var re = new RegExp(regu);
	if (!(re.test(s))){
		return false;
	}
	if (!isNumber_String(date.substr(11,2)) || !(date.substr(11,2)>=0 && date.substr(11,2)<24)){
		return false;
	}
	if (!isNumber_String(date.substr(14,2)) || date.substr(11,2)>=60 || date.substr(11,2)<0){
		return false;
	}
	return true;
}
function isNumber_String(s){
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	if(!regu.test(s)){					
		return false;
	}
	return true;
}

function isDateLessThenCurrent(dateVar) {
	var time = new Date();
	var year = time.getFullYear();
	var month = time.getMonth() + 1;
	var day = time.getDate();
	var hour = time.getHours();
	var second = time.getSeconds();
	
	/* yyyy-mm-dd */
	var scheduleYear = dateVar.substr(0,4);
	var scheduleMonth = dateVar.substr(5,2);
	var scheduleDay = dateVar.substr(8,2);
	var scheduleHour = dateVar.substr(11,2);
	var scheduleSecond = dateVar.substr(14,2);
	
	if(year > scheduleYear){
		return false;
	}else if(year == scheduleYear && month > scheduleMonth ){
		return false;
	}else if(year == scheduleYear && month == scheduleMonth && day >= scheduleDay){
		return false;
	}
	return true;
}

function isDateLessEqualThenCurrent(dateVar) {
	var time = new Date();
	var year = time.getYear();
	var month = time.getMonth() + 1;
	var day = time.getDate();
	var hour = time.getHours();
	var second = time.getSeconds();
	
	/* yyyy-mm-dd */
	var scheduleYear = dateVar.substr(0,4);
	var scheduleMonth = dateVar.substr(5,2);
	var scheduleDay = dateVar.substr(8,2);
	var scheduleHour = dateVar.substr(11,2);
	var scheduleSecond = dateVar.substr(14,2);
	
	if(year > scheduleYear){
		return false;
	}else if(year == scheduleYear && month > scheduleMonth ){
		return false;
	}else if(year == scheduleYear && month == scheduleMonth && day > scheduleDay){
		return false;
	}
	return true;
}

function isDateLessThenOldDate(dateVar,oldDate) {
	var time = new Date();
	var year = time.getYear();
	var month = time.getMonth() + 1;
	var day = time.getDate();
	var hour = time.getHours();
	var second = time.getSeconds();
	
	var oldYear = oldDate.substr(0,4);
	var oldMonth = oldDate.substr(5,2);
	var oldDay = oldDate.substr(8,2);
	var oldHour = oldDate.substr(11,2);
	var oldSecond = oldDate.substr(14,2);
	
	/* yyyy-mm-dd */
	var scheduleYear = dateVar.substr(0,4);
	var scheduleMonth = dateVar.substr(5,2);
	var scheduleDay = dateVar.substr(8,2);
	var scheduleHour = dateVar.substr(11,2);
	var scheduleSecond = dateVar.substr(14,2);
	
	if(oldYear > scheduleYear){
		return false;
	}else if(oldYear == scheduleYear && oldMonth > scheduleMonth ){
		return false;
	}else if(oldYear == scheduleYear && oldMonth == scheduleMonth && oldDay > scheduleDay){
		return false;
	}
	return true;
}

function isDate_Hour_Min_LessThenOldDate_Hour_Min(dateVar,oldDate) {
	
	var oldYear = oldDate.substr(0,4);
	var oldMonth = oldDate.substr(5,2);
	var oldDay = oldDate.substr(8,2);
	var oldHour = oldDate.substr(11,2);
	var oldSecond = oldDate.substr(14,2);
	
	/* yyyy-mm-dd */
	var scheduleYear = dateVar.substr(0,4);
	var scheduleMonth = dateVar.substr(5,2);
	var scheduleDay = dateVar.substr(8,2);
	var scheduleHour = dateVar.substr(11,2);
	var scheduleSecond = dateVar.substr(14,2);
	
	if(oldYear > scheduleYear){
		return false;
	}else if(oldYear == scheduleYear && oldMonth > scheduleMonth ){
		return false;
	}else if(oldYear == scheduleYear && oldMonth == scheduleMonth && oldDay > scheduleDay){
		return false;
	} else if (oldYear == scheduleYear && oldMonth == scheduleMonth && oldDay == scheduleDay && oldHour > scheduleHour) {
	  return false;
	} else if (oldYear == scheduleYear && oldMonth == scheduleMonth && oldDay == scheduleDay && oldHour == scheduleHour && oldSecond > scheduleSecond) {
	  return false;
	}
	return true;
}

function isDateLessThenOtherDate(mindateVar,maxdateVar){
   /* yyyy-mm-dd */
	var year = maxdateVar.substr(0,4);
	var month = maxdateVar.substr(5,2);
	var day = maxdateVar.substr(8,2);
	var hour = maxdateVar.substr(11,2);
	var second = maxdateVar.substr(14,2);

   /* yyyy-mm-dd */
	var scheduleYear = mindateVar.substr(0,4);
	var scheduleMonth = mindateVar.substr(5,2);
	var scheduleDay = mindateVar.substr(8,2);
	var scheduleHour = mindateVar.substr(11,2);
	var scheduleSecond = mindateVar.substr(14,2);

   if(year > scheduleYear){
		return true;
	}else if(year == scheduleYear && month > scheduleMonth ){
		return true;
	}else if(year == scheduleYear && month == scheduleMonth && day > scheduleDay){
		return true;
	}
	return false;

}
			
function isValidLength(form, elementName, minVar, maxVar) {
	if (isEmpty(form, elementName)) {
		return false;	
	}
	var value = form.elements[elementName].value;
	value = formatDigital(value);
	var len = value.length;
	if (null == minVar && null == maxVar) {
		return true;
	}
	else if (null == minVar) {
		return len <= maxVar;
	}
	else if (null == maxVar) {
		return len >= minVar;
	}
	else {
		return len >= minVar && len <= maxVar;
	}
	return true;
}

function isValidLengthCheck(form, elementName, minVar, maxVar) {
	var value = elementName;
	value = formatDigital(value);
	var len = value.length;
	if (null == minVar && null == maxVar) {
		return true;
	}
	else if (null == minVar) {
		return len <= maxVar;
	}
	else if (null == maxVar) {
		return len >= minVar;
	}
	else {
		return len >= minVar && len <= maxVar;
	}
	return true;
}

function isValidLengthValues( element, minVar, maxVar) {
	var value = element;
	var len = value.length;
	if (null == minVar && null == maxVar) {
		return true;
	}
	else if (null == minVar) {
		return len <= maxVar;
	}
	else if (null == maxVar) {
		return len >= minVar;
	}
	else {
		return len >= minVar && len <= maxVar;
	}
	return true;
}
			
function isValidLengthValue( element, minVar, maxVar) {
	if (isEmptyValue(element)) {
		return false;	
	}
	var value = element;
	var len = value.length;
	if (null == minVar && null == maxVar) {
		return true;
	}
	else if (null == minVar) {
		return len <= maxVar;
	}
	else if (null == maxVar) {
		return len >= minVar;
	}
	else {
		return len >= minVar && len <= maxVar;
	}
	return true;
}

function disableElements(form, elements, disabled) {
	for (var i = 0; i < elements.length; i ++) {
		form.elements[elements[i]].disabled = disabled
	}
}

function disableCalendars(form, elementNames, disabled) {
	for (var i = 0; i < elementNames.length; i ++) {
		var x = form.name + "_" + elementNames[i] + "_trigger";
		form.elements[x].disabled= disabled;
	}
}

function hideCalendarsImg(form, elementNames, hide) {
	for (var i = 0; i < elementNames.length; i ++) {
		var x = form.name + "_" + elementNames[i] + "_trigger";
		form.elements[x].style.display = hide;
	}
}

function hideElements(form, elements, hide) {
	for (var i = 0; i < elements.length; i ++) {
		form.elements[elements[i]].style.dispaly == hide ? "none" : "";
	}
}

function formatDigital(str) {
	var regS = new RegExp(",","g");
	str=str.replace(regS,'');
	return str;
}

function isInteger(form, elementName){
	var str = form.elements[elementName].value;
	str=formatDigital(str);
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	if(!regu.test(str)){					
		return false;
	}
	return true;
}

function isIntegerLength(form, elementName, minLen, maxLen){
  	var str = form.elements[elementName].value;
  	str=formatDigital(str);
	if(minLen == null && maxLen == null) {
		 return false;
	}
	if(minLen !=null && maxLen !=null){
		return (str.length <= maxLen && str.length >= minLen);
	}
	if (minLen == null && maxLen !=null) {
		return str.length<=maxLen;
	}
	if (minLen !=null && maxLen ==null) {
		return str.length >= minLen;
	}
	return false;
}

function isMoney(form, elementName){
	var str = form.elements[elementName].value; 
	var regu = "^[0-9]+[\.][0-9]{0,3}$"; 
	var re = new RegExp(regu); 
	if (!re.test(str)) { 
		return false; 
	}
	return true;
} 

function getFileNameByFile(form, elementsName) {
  var filename = form.elements["file"].value;
  var ary = filename.split("\\");
  var ary1 = ary[ary.length-1].split("\.");
  form.elements[elementsName].value=ary1[0];
}

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
	return true;
}
/*
 * ????1970-01-01????
 * 
*/
Date.prototype.DateDiff = function(strInterval, dtEnd) {   
  var dtStart = this; 
  if (typeof dtEnd == 'string' )//????????????  
  {   
    dtEnd = StringToDate(dtEnd);  
  }  
  switch (strInterval) {   
    case 's' :return parseInt((dtEnd - dtStart) / 1000);  
    case 'n' :return parseInt((dtEnd - dtStart) / 60000);  
    case 'h' :return parseInt((dtEnd - dtStart) / 3600000);  
    case 'd' :return parseInt((dtEnd - dtStart) / 86400000);  
    case 'w' :return parseInt((dtEnd - dtStart) / (86400000 * 7));  
    case 'm' :return (dtEnd.getMonth()+1)+((dtEnd.getFullYear()-dtStart.getFullYear())*12) - (dtStart.getMonth()+1);  
    case 'y' :return dtEnd.getFullYear() - dtStart.getFullYear();  
  }  
}
/*
 *????????????
 * DateStr : "yyyy-MM-dd"
*/
function StringToDate(DateStr) {   
  var converted = Date.parse(DateStr);  
  var myDate = new Date(converted);  
  if (isNaN(myDate))  
  {   
    //var delimCahar = DateStr.indexOf('/')!=-1?'/':'-';  
   var arys= DateStr.split('-');  
   myDate = new Date(arys[0],--arys[1],arys[2]);  
  }  
  return myDate;  
}

/*
 * 获取当前日期
 * form ：表单名 例如： document.forms[0]
 * elementsName : 控件名 例如："creator"
*/
function getCurrentlyDate(form,elementsName){
	date = new Date();
	var time=date.format("yyyy-MM-dd");
	form.elements[elementsName].value=time;
}

/*
 * 给标识为"elementsName"控件的赋予下个月1号的日期
 * form ：表单名 例如： document.forms[0]
 * elementsName : 控件名 例如："creator"
*/
function getDateForNextMonthFirstDay(form,elementsName) {
	var currentDate = new Date();
	var currentYear = currentDate.getYear();
	var currentMonth = currentDate.getMonth();
	var nextMonth;
	if (currentMonth >= 11) {
		nextMonth = 0;
		currentYear = currentYear + 1;
	} else {
		nextMonth = currentMonth + 1;
	}
	var nextDate = new Date(currentYear, nextMonth, "01");
	var time = nextDate.format("yyyy-MM-dd");
	form.elements[elementsName].value=time;
}
/*
 * 获得下一年
 * form ：表单名 例如： document.forms[0]
 * elementsName : 控件名 例如："creator"
*/
function getNextYear(form, elementsName) {
  var currentDate = new Date();
  var nextYear = currentDate.getYear() + 1;
  form.elements[elementsName].value=nextYear;
}
/*
 * 获得当前年
 * form ：表单名 例如： document.forms[0]
 * elementsName : 控件名 例如："creator"
*/
function getCurrentYear(form, elementsName) {
  var currentDate = new Date();
  var currentYear = currentDate.getYear();
  form.elements[elementsName].value=currentYear;
}
/*
 *  判断传入的参数是否为YYYY格式
 *  year: 年的数
*/
function isYear(year) {
  if (isNaN(parseInt(year))) {
    return false;
  }
  return true;
}
/*
 * 处理valueList中不选中失效的checkbox
*/
function notSelectedInvalid(elementName1, elementName2) {
  var allInvalid = document.getElementsByName(elementName1);
  var allUnInvslid = document.getElementsByName(elementName2);
  for (var i=0; i<allInvalid.length; i++) {
    if (allInvalid[i].checked) {
      allInvalid[i].checked = false;
    }
  }
  if (allUnInvslid) {
    for (var i=0; i<allUnInvslid.length; i++) {
      allUnInvslid[i].checked = window.event.srcElement.checked?true:false;
    }
  }
}

//查询时日期验证
function validateTime(s){ 
    if(!(s=='')){
    		var regu="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
    		var re =new RegExp(regu);
    		if (!re.test(s)){
    			return false;
    		}
    		else{
    		   if(!validateProcTime(s)){
    		      return false;
    		   }
    		}
    	}
	     return true;
}

function validateProcTime(validateTime){
                var timeYear=validateTime.substring(0,validateTime.indexOf('-')).length;
                var timeDay=validateTime.substring(validateTime.lastIndexOf('-')+1,validateTime.length).length;
                if(timeYear>4)
                {
                    return false;
                }
                if(timeDay>2)
                {
                    return false;
                }
                return true;

 }
//获取当前天的前一天
function getCurrentDayNextDay(createTime_start,n0,y0,d0,h0,m0,s0){
   var DaysToAdd=0;//距离当前日期的天数 
   DaysToAdd=DaysToAdd-1;//前一天为天数减一 
   var newdate=new Date(n0,y0,d0,h0,m0,s0);//当前时间 
   var newtimems=newdate.getTime()+(DaysToAdd*24*60*60*1000); //取得前一天的毫秒数 
   newdate.setTime(newtimems); 
   var xdate=newdate;  
   var month=xdate.getMonth(); 
   var xday=xdate.getDate(); 
   if(month<10) month="0"+month; 
   if(xday<10) xday="0"+xday; 
   document.forms[0].elements["createTime_start"].value=xdate.getYear()+"-"+month+"-"+xday;
}
//获得当前月
function getCurrentMonth(month,n0,y0,d0,h0,m0,s0){
   var DaysToAdd=0;//距离当前日期的天数 
   DaysToAdd=DaysToAdd-1;//前一天为天数减一 
   var newdate=new Date(n0,y0,d0,h0,m0,s0);//当前时间 
   var newtimems=newdate.getTime()+(DaysToAdd*24*60*60*1000); //取得前一天的毫秒数 
   newdate.setTime(newtimems); 
   var xdate=newdate;  
   var month=xdate.getMonth(); 
   var xday=xdate.getDate(); 
   if(month<10) month="0"+month; 
   if(xday<10) xday="0"+xday; 
   document.forms[0].elements["month"].value=xdate.getYear()+"-"+month;
}

//身份证验证
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古:",21:"辽宁",22:"吉林",23:"黑龙江:",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}  
function checkIdcard(sId){
if(sId.length==15){
if ( (parseInt(sId.substr(6,2))+1900) % 4 == 0 || ((parseInt(sId.substr(6,2))+1900) % 100 == 0 && (parseInt(sId.substr(6,2))+1900) % 4 == 0 )){ 
			ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性 
	} else { 
			ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性 
	} 
	if(ereg.test(sId)) return true; 
	else return false;
	
}else{
	var iSum=0
	var info=""
	if(!/^\d{17}(\d|x)$/i.test(sId))return false;
	sId=sId.replace(/x$/i,"a");
	//地区验证
	if(aCity[parseInt(sId.substr(0,2))]==null)return false;
	sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
	//生日验证
	var d=new Date(sBirthday.replace(/-/g,"/"))
	if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return false;
	//号码验证
	for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11)
	if(iSum%11!=1)return false;
	return true; 
}
}	

 //判断是中文 还是英文
 function checkCharacter(charValue){
 	   var name = charValue;
       var regu = "^[\u4e00-\u9fa5]+$";
       var re = new RegExp(regu);
       if (name.search(re) == -1) {
         return false;
       }
       return true;
}	

 
//通过name标签取得控件
function getObjByName(name){
	 var ret = ""; 
	 var arr = document.getElementsByName(name);
	 if (arr && arr.length > 0) {
		   ret = arr[0];
	 } else {
		 ret = document.getElementById(name);
	 }
	 return ret;
}

 //通过name标签取得属性值
 function getObjValueByName(name){
	 
 	 return getObjByName(name).value;
}
 function closeThis(){
	 window.opener.location.reload();
	 window.close();
	
}
 function openNewWindow(url,height,width){
	 var wd = (width == null) ? 900 :width;
	 var hg = (height == null) ? 600 :height;
	 var top= (window.screen.availHeight - hg)/2;
	 var left= (window.screen.availWidth - wd)/2;
	 var windowProperty ='height='+ hg +', width='+ wd +', top='+ top +', left='+left+', toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no' ;
	 window.open (url, '新窗口', windowProperty )
 }
 
// function openWin(url,height,width){  
//	 var wd = (width == null) ? 900 :width;
//	 var hg = (height == null) ? 600 :height;
//	 var top= (window.screen.availHeight - hg)/2;
//	 var left= (window.screen.availWidth - wd)/2;
//	 var windowProperty ='height='+ hg +', width='+ wd +', top='+ top +', left='+left+', toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no' ;
//	    var winObj = window.open(url,'新窗口',windowProperty);  
//	    var loop = setInterval(function() {       
//	        if(winObj.closed) {      
//	            clearInterval(loop);      
//	            window.parent.location.reload();   
//	        }      
//	    }, 1);     
//	}  

