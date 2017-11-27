<%@ page contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function g(objid){
	return document.getElementById(objid);
}

function selectUserSubject(rowcount){
	var diag = new top.Dialog();
	diag.Title = "选择学科";
	diag.URL = '/eduSelectAction.do?method=selectUserSubject&unitid=${unitInfo.unitid}';
	diag.Width = 300;
	diag.Height = 400;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			g('subjectid'+rowcount).value = id;
			g('subjectname'+rowcount).value = name;
			
			g('gradeid'+rowcount).value = "";
			g('gradename'+rowcount).value = "";
			g('versionid'+rowcount).value = "";
			g('versionname'+rowcount).value = "";
		}
		diag.close();
	};
	diag.show();
}

function selectUserGrade(rowcount){
	var subjectid = g("subjectid"+rowcount).value;
	if(subjectid == ""){
		top.Dialog.alert("请先选择学科！");
		return;
	}
	var diag = new top.Dialog();
	diag.Title = "选择年级";
	diag.URL = '/eduSelectAction.do?method=selectUserGrade&unitid=${unitInfo.unitid}&subjectid=' + subjectid;
	diag.Width = 300;
	diag.Height = 400;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			g('gradeid'+rowcount).value = id;
			g('gradename'+rowcount).value = name;
			
			g('versionid'+rowcount).value = "";
			g('versionname'+rowcount).value = "";
		}
		diag.close();
	};
	diag.show();
}

function selectUserVersion(rowcount){
	var gradeid = g("gradeid"+rowcount).value;
	if(gradeid == ""){
		top.Dialog.alert("请先选择年级！");
		return;
	}
	var diag = new top.Dialog();
	diag.Title = "选择版本";
	diag.URL = '/eduSelectAction.do?method=selectUserVersion&unitid=${unitInfo.unitid}&gradeid=' + gradeid;
	diag.Width = 300;
	diag.Height = 400;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			g('versionid'+rowcount).value = id;
			g('versionname'+rowcount).value = name;
		}
		diag.close();
	};
	diag.show();
}

function selectSubject(){
	var diag = new top.Dialog();
	diag.Title = "选择学科";
	diag.URL = '/eduSelectAction.do?method=selectSubject';
	diag.Width = 300;
	diag.Height = 400;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			g('subjectid').value = id;
			g('subjectname').value = name;
			
			g('gradeid').value = "";
			g('gradename').value = "点击选择年级";
			g('versionid').value = "";
			g('versionname').value = "点击选择教材版本";
			g('columnid').value = "";
			g('columnname').value = "点击选择章节目录";
			g('knopointid').value = "";
			g('knopointname').value = "点击选择知识点";
			g('knopoint_div').style.display = "none";
		}
		diag.close();
	};
	diag.show();
}

function selectGrade(){
	var subjectid = g("subjectid").value;
	if(subjectid == ""){
		top.Dialog.alert("请先选择学科！");
		return;
	}
	var diag = new top.Dialog();
	diag.Title = "选择年级";
	diag.URL = '/eduSelectAction.do?method=selectGrade&subjectid=' + subjectid;
	diag.Width = 300;
	diag.Height = 400;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			g('gradeid').value = id;
			g('gradename').value = name;
			
			g('versionid').value = "";
			g('versionname').value = "点击选择教材版本";
			g('columnid').value = "";
			g('columnname').value = "点击选择章节目录";
			g('knopointid').value = "";
			g('knopointname').value = "点击选择知识点";
			g('knopoint_div').style.display = "";
		}
		diag.close();
	};
	diag.show();
}

function selectVersion(){
	var gradeid = g("gradeid").value;
	if(gradeid == ""){
		top.Dialog.alert("请先选择年级！");
		return;
	}
	var diag = new top.Dialog();
	diag.Title = "选择教材版本";
	diag.URL = '/eduSelectAction.do?method=selectVersion&gradeid=' + gradeid;
	diag.Width = 300;
	diag.Height = 400;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			g('versionid').value = id;
			g('versionname').value = name;
			
			g('columnid').value = "";
			g('columnname').value = "点击选择章节目录";
		}
		diag.close();
	};
	diag.show();
}

function selectColumn(){
	var versionid = g("versionid").value;
	if(versionid == ""){
		top.Dialog.alert("请先选择教材版本！");
		return;
	}
	var diag = new top.Dialog();
	diag.Title = "选择章节目录";
	diag.URL = '/eduSelectAction.do?method=selectColumn&versionid=' + versionid;
	diag.Width = 500;
	diag.Height = 400;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			g('columnid').value = id;
			g('columnname').value = name;
		}
		diag.close();
	};
	diag.show();
}

function selectKnopoint(){
	var subjectid = g("subjectid").value;
	var gradeid = g("gradeid").value;
	if(subjectid == "" || gradeid == ""){
		top.Dialog.alert("请先选择学科和年级！");
		return;
	}
	var knopointid = g('knopointid').value;
	var diag = new top.Dialog();
	diag.Title = "选择知识点";
	diag.URL = '/eduSelectAction.do?method=selectKnopoint&subjectid=' + subjectid + '&gradeid=' + gradeid + '&knopointid=' + knopointid;
	diag.Width = 400;
	diag.Height = 500;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			g('knopointid').value = id;
			g('knopointidupdate').value = "1";
			if(name == ""){
				g('knopointname').value = "点击选择知识点";
			}else{
				g('knopointname').value = name;
			}
		}
		diag.close();
	};
	diag.show();
}

function selectType(type, ptypeid){
	if(type == '1'){
		if(ptypeid == ""){
	    	g("typeid").value = "";
	        g("sectype").style.display = "none";
	        return false;
	    }
	    
	    new Ajax.Request(
	    "/eduSelectAction.do?method=selectType",
	    {
	    method:"get",
	    parameters:"typeid=" + ptypeid+"&ram=" + Math.random(), 
	    asynchronous:false,//true为异步请求
	    onComplete:function(xhr){
	        var responseObj = xhr.responseText;
	        var str = responseObj.split("||");
	        if(str[1] == "0"){
	        	g("typeid").value = ptypeid;
	            document.getElementById("sectype").style.display="none";
	        }else {
	        	document.getElementById('sectype').innerHTML = str[0];
	            document.getElementById("sectype").style.display="";
	            g("typeid").value = str[1];
	        }
	    }
	    }
	  );
	}else{
		g("typeid").value = ptypeid;
	}
}

function selectAllSubject(rowcount){
	var diag = new top.Dialog();
	diag.Title = "选择学科";
	diag.URL = '/eduSelectAction.do?method=selectAllSubject';
	diag.Width = 300;
	diag.Height = 400;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			g('subjectid'+rowcount).value = id;
			g('subjectname'+rowcount).value = name;
			
			g('gradeid'+rowcount).value = "";
			g('gradename'+rowcount).value = "";
		}
		diag.close();
	};
	diag.show();
}

function selectAllGrade(rowcount){
	var subjectid = g("subjectid"+rowcount).value;
	if(subjectid == ""){
		top.Dialog.alert("请先选择学科！");
		return;
	}
	var diag = new top.Dialog();
	diag.Title = "选择年级";
	diag.URL = '/eduSelectAction.do?method=selectAllGrade&subjectid=' + subjectid;
	diag.Width = 300;
	diag.Height = 400;
	diag.CancelEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById('id')){
			var id = diag.innerFrame.contentWindow.document.getElementById('id').value;
			var name = diag.innerFrame.contentWindow.document.getElementById('name').value;
			g('gradeid'+rowcount).value = id;
			g('gradename'+rowcount).value = name;
		}
		diag.close();
	};
	diag.show();
}

/*屏蔽所有的js错误*/ 
function killerrors() {
	return true; 
} 
window.onerror = killerrors; 
</script>