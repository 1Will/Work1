function SetHome(obj, homepage){
	try{
		obj.style.behavior='url(#default#homepage)';
		obj.setHomePage(homepage);
	}catch(e){
		if(window.netscape){
			try{
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			}catch(e){
				//alert("鎶辨瓑锛屾鎿崭綔琚祻瑙埚櫒鎷掔粷锛乗n\n璇峰湪娴忚鍣ㄥ湴鍧€镙忚緭鍏モ€渁bout:config钬濆苟锲炶溅铹跺悗灏哰signed.applets.codebase_principal_support]璁剧疆涓?true'");
				alert("\u62B1\u6B49\uFF0C\u6B64\u64CD\u4F5C\u88AB\u6D4F\u89C8\u5668\u62D2\u7EDD\uFF01\n\n\u8BF7\u5728\u6D4F\u89C8\u5668\u5730\u5740\u680F\u8F93\u5165\u201Cabout\:config\u201D\u5E76\u56DE\u8F66\u7136\u540E\u5C06[signed.applets.codebase_principal_support]\u8BBE\u7F6E\u4E3A'true'");
			};
		}else{
			//alert("鎶辨瓑锛屾偍镓€浣跨敤镄勬祻瑙埚櫒镞犳硶瀹屾垚姝ゆ搷浣溿€俓n\n镇ㄩ渶瑕佹坠锷ㄨ缃负棣栭〉銆?);
			alert("\u62B1\u6B49\uFF0C\u60A8\u6240\u4F7F\u7528\u7684\u6D4F\u89C8\u5668\u65E0\u6CD5\u5B8C\u6210\u6B64\u64CD\u4F5C\u3002\n\n\u60A8\u9700\u8981\u624B\u52A8\u8BBE\u7F6E\u4E3A\u9996\u9875\u3002");
		};
	};
}
function AddFavorite(sURL,sTitle){
	try{
		window.external.addFavorite(sURL, sTitle);
	}catch (e){
		try{
			window.sidebar.addPanel(sTitle, sURL, "");
		}catch (e){
			//alert("锷犲叆鏀惰棌澶辫触锛岃浣跨敤Ctrl+D杩涜娣诲姞銆?);
			alert("\u52A0\u5165\u6536\u85CF\u5931\u8D25\uFF0C\u8BF7\u4F7F\u7528Ctrl+D\u8FDB\u884C\u6DFB\u52A0\u3002");
		}
	}
}