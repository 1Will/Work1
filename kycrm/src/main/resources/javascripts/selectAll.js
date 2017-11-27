function selectedAll()
{
    var obj=document.all.checkbox;
    for(i=1;i<obj.length;i++)
    {
       obj[i].checked=window.event.srcElement.checked?true:false;
    }
}
