function setTab(name,cursel,n,url){
	for(i=1;i<=n;i++){
		var menu=document.getElementById(name+i);	
		menu.className=i==cursel?"hover":"";		
	}
	tabFrame.location=url;
}