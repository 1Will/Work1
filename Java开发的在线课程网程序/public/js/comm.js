function setState(flag) {
	if(num>0){
		if (num>1){
			for(i=0;i<num;i++)	{
				if(document.pageForm.checkid[i].disabled!=true){
					document.pageForm.checkid[i].checked = flag;
				}
			}
		}else{
			if(document.pageForm.checkid.disabled!=true){
				document.pageForm.checkid.checked = flag;
			}
		}
	}else{
		try{
  		  	top.Dialog.alert("未找到可操作记录!");
  	  	}catch(e){
  	  		alert("未找到可操作记录!");
  	  	}
	}
}

function delRecord(url){
	if(num>0){
		var str="此操作不可恢复，真的要继续么？";
		var checkids="";
		if (num>1){
			for(i=0;i<num;i++){
				if (document.pageForm.checkid[i].checked==true){
					checkids=checkids+document.pageForm.checkid[i].value+",";
				}
			}
		}else{
			if (document.pageForm.checkid.checked==true)	{
				checkids=document.pageForm.checkid.value;
			}
		}
		if (checkids=="") {
			try{
	  		  	top.Dialog.alert("您还没有选择要操作的记录呢!");
	  	  	}catch(e){
	  	  		alert("您还没有选择要操作的记录呢!");
	  	  	}
		}else{
			try{
				top.Dialog.confirm(str,function(){
					document.pageForm.action=url;
	   				document.pageForm.submit();
				});
	  	  	}catch(e){
		  	  	if(confirm(str)){
					document.pageForm.action=url;
					document.pageForm.submit();
				}
	  	  	}
		}
	}else{
		try{
  		  	top.Dialog.alert("未找到可操作记录!");
  	  	}catch(e){
  	  		alert("未找到可操作记录!");
  	  	}
	}
}

function delRecord0(url,str){
	if(num>0){
		var checkids="";
		if (num>1){
			for(i=0;i<num;i++){
				if (document.pageForm.checkid[i].checked==true){
					checkids=checkids+document.pageForm.checkid[i].value+",";
				}
			}
		}else{
			if (document.pageForm.checkid.checked==true)	{
				checkids=document.pageForm.checkid.value;
			}
		}
		if (checkids=="") {
			try{
	  		  	top.Dialog.alert("您还没有选择要操作的记录呢!");
	  	  	}catch(e){
	  	  		alert("您还没有选择要操作的记录呢!");
	  	  	}
		}else{
			try{
				top.Dialog.confirm(str,function(){
					document.pageForm.action=url;
	   				document.pageForm.submit();
				});
	  	  	}catch(e){
		  	  	if(confirm(str)){
					document.pageForm.action=url;
					document.pageForm.submit();
				}
	  	  	}
		}
	}else{
		try{
  		  	top.Dialog.alert("未找到可操作记录!");
  	  	}catch(e){
  	  		alert("未找到可操作记录!");
  	  	}
	}
}

function delThisRecord(url,objid){
	var str="此操作不可恢复，真的要删除本条记录么？";
	//如果当前记录不可删除
	if(num>0){
		if (num>1){
		    for(i=0;i<num;i++){
		    	if (document.pageForm.checkid[i].disabled==true&&document.pageForm.checkid[i].value==objid){
		    		try{
			  		  	top.Dialog.alert("此记录不可删除,请核对后重试!");
			  	  	}catch(e){
			  	  		alert("此记录不可删除,请核对后重试!");
			  	  	}
		    		return;
		    	}
		    }
		}else{
			if (document.pageForm.checkid.disabled==true&&document.pageForm.checkid.value==objid)	{
				try{
		  		  	top.Dialog.alert("此记录不可删除,请核对后重试!");
		  	  	}catch(e){
		  	  		alert("此记录不可删除,请核对后重试!");
		  	  	}
				return;
			}
		}
	}
	try{
		top.Dialog.confirm(str,function(){
			document.pageForm.action=url+"?method=delRecord&objid="+objid;
  			document.pageForm.submit();
		});
	}catch(e){
  		if(confirm(str)){
  			document.pageForm.action=url+"?method=delRecord&objid="+objid;
  			document.pageForm.submit();
  		}
	}
}

function editThisRecord(url,objid){
    document.pageForm.action=url+"?method=beforeUpdate&objid="+objid;
    document.pageForm.submit();
}

function previewThisRecord(url,objid){
    document.pageForm.action=url+"?method=preview&objid="+objid;
    document.pageForm.submit();
}

function showDialog(url,name,dwidth,dheight){
	var arr=showModalDialog(url,name,"dialogWidth:"+dwidth+";dialogHeight:"+height+";scroll=auto;border=thin;help=0;status=no");
}

function MM_openBrWindow(theURL,winName,features) { //v2.0
	window.open(theURL,winName,features);
}

function OpenFullWindow(filename,cname,cscrollbars){
	var newWindow = window.open(filename,cname,cscrollbars);
}

function gotoPage(url){
	document.pageForm.action=url+document.all.gopage.value;
	document.pageForm.submit();
}

function gotoPageTxt(url){
	if(num>0){
		var gopage = new Number(document.all.gopage.value);//要跳往的页数
		var totalPages = new Number(document.all.totalPages.value);//总分页数
		var page_size = new Number(document.all.page_size.value);//每页显示数量
		if(!isNaN(gopage) && gopage != ""){
			if(gopage > totalPages){
				try{
		  		  	top.Dialog.alert("您输入的页数过大.");
		  	  	}catch(e){
		  	  		alert("您输入的页数过大.");
		  	  	}
				return ;
			}else{
				var startcount = (gopage-1)*page_size;
				document.pageForm.action=url+startcount;
				document.pageForm.submit();
			}
		}else{
			return;
		}
	}
}

function turnPage(url){
	document.pageForm.action=url;
	document.pageForm.submit();
}

function MM_swapImgRestore() { //v3.0
	var i,x,a=document.MM_sr; 
	for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++){
		x.src=x.oSrc;
	}
}

function MM_preloadImages() { //v3.0
	var d=document; 
	if(d.images){
		if(!d.MM_p) d.MM_p=new Array();
		var i,j=d.MM_p.length,a=MM_preloadImages.arguments; 
		for(i=0; i<a.length; i++){
			if (a[i].indexOf("#")!=0){ 
				d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];
			}
		}
	}
}

function MM_showHideLayers() { //v3.0
	var i,p,v,obj,args=MM_showHideLayers.arguments;
	for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v='hide')?'hidden':v; }
    obj.visibility=v; }
}

function MM_findObj(n, d) { //v4.01
	var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
	var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
		if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function chgTDbg(obj,status){
	if (status=="on"){
		tdBgColor="#cccccc"
	}else{
		tdBgColor="#ffffff"
	}
	obj.style.background=tdBgColor
	obj.style.cursor="pointer"
}
function init(){
	frames.message.document.body.innerHTML=document.getElementById('htmlcontent').value;
}

function chgMenubg(obj,status){
	if (status=="on"){
		tdBgColor="#ccc"
	}else{
		tdBgColor="#167873"
	}
	obj.style.background=tdBgColor
	obj.style.cursor="pointer"
}
