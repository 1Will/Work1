
/**************************************textfield**************************************/
/*
* 函数名: textfieldCheck
* 作  用: 检验输入域
* 参  数: required ---- 是否必填项,name ---- 输入域的名称,type ---- 输入域的类型(默认代表不坐验证、N代表数字验证、IDCARD代表身份证验证、E代表EAMIL验证),
*        label ---- 提示的字段名称
* 返回值: true ----通过、false ---- 不通过
*/
function textfieldCheck(required,name,type,label){                        
	if(required){                                                         
		//默认情况下代表字符串                                                      
		if(!isNotEmptyValue(getObjByName(name).value)){                                            
			alert('\u8bf7\u8f93\u5165'+label);                                         
			return false;                                                 
		}                                                                 
		//验证输入域的类型                                                          
		return checkFormat(name,type,label);                              
	}else{
		if(isNotEmptyValue(getObjByName(name).value)){
			//验证输入域的类型
			return checkFormat(name,type,label);
		}
	}
	return true;
}

//验证输入域的类型
function checkFormat(name,type,label){
	//代表数字验证
	if(type=='N'){
		if(!isNumber(name)){
			alert('\u8bf7\u8f93\u5165'+label+'在0-1000000000内数字;')
			return false;
		}
	}
	//身份证验证
	if(type=='IDCARD'){
		if(!checkIdcard(getObjByName(name).value)){
			alert(label+'格式错误')
			return false;
		}
	}
	//EMAIL验证
	if(type=='E'){
		if(!checkEmail(getObjByName(name).value)){
			alert(label+'格式错误')
			return false;
		}
	}
	//数字格式验证
	if(type=='P'){
		if(!isNumber(name)){
			alert(label+'格式错误')
			return false;
		}
	}
		//double格式验证
	if(type=='D'){
		if(!isDoubleNumber(name)){
		 	alert(label+'格式错误')
			return false;}
		}
	return true;
}

/*************************************date****************************************/
<!-- date -->
/*
* 函数名: dateCheckPicker
* 作  用: 拥有一个输入域的验证
* 参  数: required ---- 是否必填项,name ---- 输入域的名称、label ---- 提示的字段名称、
*        dateFormat ---- 代表日期的格式(%Y代表年、%Y-%M代表年月、%Y-%m-%d代表年月日、%Y-%m-%d %H:%M代表年月日时分秒)
* 返回值: true ----通过、false ---- 不通过
*/	
	function dateCheckPicker(required,name,label,dateFormat){
		if(required){
			//默认情况下代表字符串
			if(!isNotEmptyValue(getObjByName(name).value)){
				alert('请选择'+label);
				return false;
			}else if(!validateTime(getObjByName(name).value)){
				alert(label+'格式错误,正确格式为yyyy-MM-dd');
				return false;
			}
		}else{
			//如果不为空的时候，判断其格式
			if(isNotEmptyValue(getObjByName(name).value)){
				if(dateFormat =='%Y'||dateFormat =='%y'){
					if(!isYear(getObjByName(name).value)){
						alert(label+'格式错误,正确格式为yyyy');
						return false;
					}
				}else if(dateFormat =='%Y-%m'){
					if(!isYearMonth(getObjByName(name).value)){
						alert(label+'格式错误,正确格式为yyyy-MM');
						return false;
					}
				}
				else if(!validateTime(getObjByName(name).value)){
					alert(label+'格式错误,正确格式为yyyy-MM-dd');
					return false;
				}
			}
		}
		return true;
	}
	
/*
* 函数名: dateCheckRanger
* 作  用: 拥有两个输入域的验证
* 参  数: required ---- 是否必填项,name ---- 输入域的名称、label ---- 提示的字段名称、
*        dateFormat ---- 代表日期的格式(%Y代表年、%Y-%M代表年月、%Y-%m-%d代表年月日、%Y-%m-%d %H:%M代表年月日时分秒)
* 返回值: true ----通过、false ---- 不通过
*/
	function dateCheckRanger(name,label,valueStart,valueEnd,dateFormat){
		var strStartDate = new String(document.getElementById(valueStart).value);
		var strEndDate = new String(document.getElementById(valueEnd).value);
		//验证'%y'格式的年份
		if(dateFormat =='%Y'||dateFormat =='%y'){
			if(isNotEmptyValue(strStartDate)){
				if(!isYear(strStartDate)){
					alert(label+'格式错误,正确格式为yyyy');
					return false;
				}
			}
			if(isNotEmptyValue(strEndDate)){
				if(!isYear(strEndDate)){
					alert(label+'格式错误,正确格式为yyyy');
					return false;
				}
			}
		//验证'%Y-%m'的年月
		}else if(dateFormat =='%Y-%m'){
			if(isNotEmptyValue(strStartDate)){
				if(!isYearMonth(strStartDate)){
					alert(label+'格式错误,正确格式为yyyy-MM');
					return false;
				}
			}
			if(isNotEmptyValue(strEndDate)){
				if(!isYearMonth(strEndDate)){
					alert(label+'格式错误,正确格式为yyyy-MM');
					return false;
				}
			}
		//验证'%Y-%m-%d'的年月日
		}else if(dateFormat == '%Y-%m-%d'){
			if(isNotEmptyValue(strStartDate)){
				if(!isYearMonthDay(strStartDate)){
					alert(label+'格式错误,正确格式为yyyy-MM-dd');
					return false;
				}
			}
			if(isNotEmptyValue(strEndDate)){
				if(!isYearMonthDay(strEndDate)){
					alert(label+'格式错误,正确格式为yyyy-MM-dd');
					return false;
				}
			}
		//验证'%Y-%m-%d %H:%M'的年月日时分
		}else if(dateFormat == '%Y-%m-%d %H:%M'){
			if(isNotEmptyValue(strStartDate)){
				if(!isYearMonthdayHourMinute(strStartDate)){
					alert(label+'格式错误,正确格式为yyyy-MM-dd HH:mm');
					return false;
				}
			}
			if(isNotEmptyValue(strEndDate)){
				if(!isYearMonthdayHourMinute(strEndDate)){
					alert(label+'格式错误,正确格式为yyyy-MM-dd HH:mm');
					return false;
				}
			}
		}
		return true;
	}
	
/*******************************select****************************************/	
/*
* 函数名: selectCheck
* 作  用: 下拉列表验证
* 参  数: required ---- 是否必填项,name ---- 输入域的名称、label ---- 提示的字段名称、
* 返回值: true ----通过、false ---- 不通过
*/
	function selectCheck(required,name,label){
		if(required){
			if(getObjByName(name).value==''||getObjByName(name).value=='0'||getObjByName(name).value=='-1'||getObjByName(name).value=='-2'){
				alert('请选择'+label);
				return false;
			}
		}
		return true;
	}

/******************************textarea*****************************************/
/*
* 函数名: textareaCheck
* 作  用: 文本域验证
* 参  数: required ---- 是否必填项,name ---- 输入域的名称、maxLength ---- 长度、label ---- 提示的字段名称、
* 返回值: true ----通过、false ---- 不通过
*/
	function textareaCheck(required,name,maxLength,label){
		if(required){
			if(!isNotEmptyValue(getObjByName(name).value)){
				alert('请输入:'+label);
				return false;
			}else{
				if(!isValidLength(document.forms[0], name, null, maxLength)){
					alert(label + '最大长度为:' + maxLength + '个字符;');
					return  false;
				}				
			}
		}else{	
			if(getObjByName(name).value == ''){
			}else if(!isValidLength(document.forms[0], name, null, maxLength)&& !getObjByName(name).value == ''){
				alert(label + '最大长度为:' + maxLength + '个字符;');
				return  false;
			}
		}
		return true;
	}
