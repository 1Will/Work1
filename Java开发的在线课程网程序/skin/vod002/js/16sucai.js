// JavaScript Document
var aA = null;
var aTimer = [];
var aAlpha = [];
window.onload=function()
{
	var i=0;
	aA = document.getElementsByTagName("a");
	
	for(i=0;i<aA.length;i++)
	{
		aA[i].miaovIndex=i;
		aA[i].onmouseover = function (){startTimer(true, this.miaovIndex);};
		aA[i].onmouseout = function (){startTimer(false, this.miaovIndex);};
		
		aTimer[i]=null;
		aAlpha[i]=70;
	}
};
function startTimer(bShow, index)
{
	if(aTimer[index])
	{
		clearInterval(aTimer[index]);
		aTimer[index]=null;
	}
	aTimer[index] = setInterval("show("+bShow+", "+index+")", 35);
}
function show(bShow, index)
{
	var iTarget=0;
	var iNum=0;
	
	if(bShow)
	{
		iTarget=100;
		iNum=10;
	}
	else
	{
		iTarget=70;
		iNum=-10;
	}
	
	if(aAlpha[index]!=iTarget)
	{
		aAlpha[index]+=iNum;
		aA[index].style.filter = "alpha(opacity="+aAlpha[index]+")";
		aA[index].style.opacity = aAlpha[index]/100;
	}
	else
	{
		clearInterval(aTimer[index]);
		aTimer[index]=null;
	}
}
/* 代码整理：一流素材网www.16sucai.com */