/*绗竴绉嶅舰寮?绗簩绉嶅舰寮?镟存崲鏄剧ず镙峰纺*/
function setTab(name,cursel,n){
	for(i=1;i<=n;i++){
		var menu=document.getElementById(name+i);
		var con=document.getElementById("con_"+name+"_"+i);
		menu.className=i==cursel?"hover":"";
		con.style.display=i==cursel?"block":"none";
	}
}

function setMenu(){
	var url = '/sysUserMenuAction.do?method=main';
	showModalDialog(url,'蹇嵎凿滃崟璁剧疆',"dialogWidth:650px;dialogHeight:460px;scroll=no;border=thin;help=0;status=no");
	document.getElementById('usermenuFrame').src = '/pcenter.do?method=usermenu';
}

function setLocation(mproduct, url){
	document.getElementById('onclickmenu').value = url;
	document.menuForm.action = '/pcenter.do?method=index&mproduct=' + mproduct + '&msg=0';
	document.menuForm.submit();
}

function autoRead(url){
	document.getElementById('onclickmsg').value = url;
	document.msgForm.action = '/pcenter.do?method=index&mproduct=msg&msg=00';
	document.msgForm.submit();
}

function setSubject(){
	var url = '/sysUserInfoAction.do?method=userSubjectMain';
	showModalDialog(url,'璁剧疆瀛︾',"dialogWidth:470px;dialogHeight:260px;scroll=no;border=thin;help=0;status=no");
	document.getElementById('resFrame').src = '/pcenter.do?method=res';
}