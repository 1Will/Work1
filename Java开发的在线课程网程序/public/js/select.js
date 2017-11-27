
function clearSelect(whichSelect) {
    var selectLength=whichSelect.length;
    for (i=selectLength;i>0;i--)
	whichSelect.options[i-1]=null;	

}

function delSelectedOpt(whichSelect)
{
      var optLen = whichSelect.length;	
      for(i=optLen;i>0;i--)
      {
           var opt = whichSelect.options[i-1];
           if(opt.selected)whichSelect.options[i-1]=null;	
      }
  } 
  

function addOption(whichSelect,optText,optValue) {
      var curOption=whichSelect.length;
      if(!isOptInSelect(whichSelect,optValue))
      {
      	whichSelect.options[curOption]=new Option(optText);
      	whichSelect.options[curOption].value=optValue;  	
      }
}

function copySelectedOpt(select1,select2)
{
     
     var optLen = select1.length;	
      for(i=optLen;i>0;i--)
     {
           var opt = select1.options[i-1];
           if(opt.selected)
           	addOption(select2,opt.text,opt.value);
     }
}

function getSelectString(select,onlySel)
{
      var str = "";
      var optLen = select.length;	
      for(i=0;i<optLen;i++)
     {
           var opt = select.options[i];
           if(!onlySel)
           {
           	str+=(opt.text+"["+opt.value+"];");
           }else if(opt.selected)
           	str+=(opt.text+"["+opt.value+"];");
           	
     }	
     return str;
}



function addOptionByString(select,str)
{
       var  pot1 = 0;
       var  pot2;
       if(str==null||str=="")
       	 pot2=0;
       else
         pot2= str.indexOf(';');
       while(pot2>0)
       {
       	var temp = str.substring(pot1,pot2);
       	var tpot1 = temp.indexOf('[');
       	var tpot2 = temp.indexOf(']');
       	addOption(select,temp.substring(0,tpot1),temp.substring(tpot1+1,tpot2));
       	
       	pot1 = pot2+1;
       	if(pot1>=str.length)break;
       	pot2 = str.indexOf(';',pot1);
       	
       }	
}

function copyAllOpt(select1,select2)
{
       var optLen = select1.length;	
      for(i=0;i<optLen;i++)
      {
           opt = select1.options[i];
           addOption(select2,opt.text,opt.value);
       }
 
 }


function isOptInSelect(whichSelect,optValue)
{
    
    var selectLength=whichSelect.length;
    for (t=selectLength;t>0;t--)
    {
	if(whichSelect.options[t-1].value==optValue)return true;
    }
    return false;		
}





