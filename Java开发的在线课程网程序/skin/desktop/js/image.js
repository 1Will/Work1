// JavaScript Document
var Speed = 2; //速度(毫秒)
var Space = 12; //每次移动(px)
var fill = 0; //整体移位
var MoveLock;
var MoveTimeObj;
var Comp = 0;
var AutoPlayObj = null;
function scrollImg(objName,listOne,listTwo){
	MoveLock = false;
	GetObj(listTwo).innerHTML = GetObj(listOne).innerHTML;
	GetObj(objName).scrollLeft = fill;
	GetObj(objName).onmouseover = function(){clearInterval(AutoPlayObj);}
}
function GetObj(objName){
	if(document.getElementById){
		return eval('document.getElementById("'+objName+'")')
	}else{
		return eval('document.all.'+objName)
	}
}
function GoUp(objName,listOne){ //上翻开始
 	if(MoveLock) return;
 	clearInterval(AutoPlayObj);
 	MoveLock = true;
 	MoveTimeObj = setInterval("ScrUp('"+objName+"','"+listOne+"');",Speed);
}
function StopUp(objName,pageWidth){ //上翻停止
 	clearInterval(MoveTimeObj);
 	if(GetObj(objName).scrollLeft % pageWidth - fill != 0){
  		Comp = fill - (GetObj(objName).scrollLeft % pageWidth);
 		CompScr(objName);
 	}else{
  		MoveLock = false;
 	}
}
function ScrUp(objName,listOne){ //上翻动作
 	if(GetObj(objName).scrollLeft <= 0){
		GetObj(objName).scrollLeft = GetObj(objName).scrollLeft + GetObj(listOne).offsetWidth
	}
 	GetObj(objName).scrollLeft -= Space ;
}
function GoDown(objName,listOne){ //下翻
 	clearInterval(MoveTimeObj);
 	if(MoveLock) return;
 	clearInterval(AutoPlayObj);
 	MoveLock = true;
 	ScrDown(objName,listOne);
 	MoveTimeObj = setInterval("ScrDown('"+objName+"','"+listOne+"')",Speed);
}
function StopDown(objName,pageWidth){ //下翻停止
 	clearInterval(MoveTimeObj);
 	if(GetObj(objName).scrollLeft % pageWidth - fill != 0 ){
  		Comp = pageWidth - GetObj(objName).scrollLeft % pageWidth + fill;
  		CompScr(objName);
 	}else{
  		MoveLock = false;
 	}
}
function ScrDown(objName,listOne){ //下翻动作
    if(GetObj(objName).scrollLeft >= GetObj(listOne).scrollWidth){
		GetObj(objName).scrollLeft = GetObj(objName).scrollLeft - GetObj(listOne).scrollWidth;
	}
	GetObj(objName).scrollLeft += Space ;
}
function CompScr(objName){
 	var num;
 	if(Comp == 0){
		MoveLock = false;
		return;
	}
 	if(Comp < 0){ //上翻
  		if(Comp < -Space){
   			Comp += Space;
   			num = Space;
  		}else{
   			num = -Comp;
   			Comp = 0;
  		}
  		GetObj(objName).scrollLeft -= num;
  		setTimeout("CompScr('"+objName+"')",Speed);
 	}else{ //下翻
  		if(Comp > Space){
   			Comp -= Space;
   			num = Space;
  		}else{
  	 		num = Comp;
   			Comp = 0;
  		}
  		GetObj(objName).scrollLeft += num;
  		setTimeout("CompScr('"+objName+"')",Speed);
 	}
}
