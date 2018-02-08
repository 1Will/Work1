/*
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
   shall not disclose such Confidential Information and shall use it only in
   accordance with the terms of the license agreement you entered into with
   YongJun.
   
   YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
   SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
   WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
   NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
   LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
   DERIVATIVES.
 */
/* $Id: menuExpandable.js 6119 2007-07-30 08:24:26Z qsun $ */

if (!document.getElementById) {
    document.getElementById = function() { return null; }
}

var menuCookie = "menusToExpand";
var itemCookie = "itemToHighlight";
var highlightMenu = "";

function initializeMenu(menuId, actuatorId) {
    var menu = document.getElementById(menuId);
    var actuator = document.getElementById(actuatorId);

    if (menu == null || actuator == null) return;

    //if (window.opera) return; // I'm too tired
    
    actuator.parentNode.style.backgroundImage = "url(images/menu/plus.gif)";
    actuator.onclick = function() {
        var display = menu.style.display;
        this.parentNode.style.backgroundImage =
            (display == "block") ? "url(images/menu/plus.gif)" : "url(images/menu/minus.gif)";
        menu.style.display = (display == "block") ? "none" : "block";
        
        // Begin custom code for remembering expanded menus with cookies
        var menusToExpand = getCookie(menuCookie);
        if (menu.style.display == "block") {
            // set a cookie to keep the menu expanded
            if (menusToExpand == null) {
                setCookie(menuCookie,menuId);
            } else if (menusToExpand.indexOf(menuId) == -1) {
                setCookie(menuCookie,menusToExpand+","+menuId);
            }
        } else {
            // remove it from the expanded cookie list
            if (menusToExpand.indexOf(menuId) != -1) {
                // check for comma after menu
                if (menusToExpand.indexOf(menuId+",") != -1) {
                    menusToExpand = menusToExpand.replace(menuId+",","");
                } else {
                    menusToExpand = menusToExpand.replace(menuId,"");
                }
                if (menusToExpand == "") {
                    deleteCookie(menuCookie);
                } else {
                    setCookie(menuCookie,menusToExpand);
                }
            }
        }
        // End custom code
        
        return false;
    }
}

// This function loops through all the <ul>'s in the document and
// initializes the menus for them if they have menu classes
function initializeMenus() {
    var menu = document.getElementById("menuDiv");
    if (menu == null) {
        return;
    }
    var links = menu.getElementsByTagName("a");
    var lists = document.getElementsByTagName("ul");

    var actuators = new Array();
    var nonActuators = new Array();
    // build an array of actuators
    for (i=0; i < links.length; i++) {
        if (links[i].className == "actuator") {
            actuators[actuators.length] = links[i];
        } else {
            nonActuators[nonActuators.length] = links[i];
            links[i].target = "mainFrame";
        }
    }

    var menus = new Array();
    // build an array of menus
    for (i=0; i < lists.length; i++) {
        if (lists[i].className == "menu" || lists[i].className == "submenu") {
            menus[menus.length] = lists[i];
        }
    }

    // initialize actuators and menus (number should be the same)
    for (i=0; i < actuators.length; i++) {
        initializeMenu(menus[i].id, actuators[i].id);
    }
    
    // Begin custom code to remember last item clicked (with cookies)
    // add an onclick event to set a cookie on the non-actuators
    for (i=0; i < nonActuators.length; i++) {
        nonActuators[i].onclick=function() {
            //new add modifor: zbzhang
            if (highlightMenu != "" || highlightMenu != undefined) {
              highlightMenu.className = "";
            }
            setCookie(itemCookie,this.href);
            highlightMenu = this;   //new add modifor: zbzhang
            this.className +="highlight";  //new add modifor: zbzhang
           
        }
    }

    // user must have cookies enabled for this to work
    expandMenus();
}


function openMenu(menuId) {
    var menu = document.getElementById(menuId);
    var actuatorId = menuId.substring(0, menuId.indexOf("Menu")) + "Actuator";
    var actuator = document.getElementById(actuatorId);
    if (menu != null) {
        var display = menu.style.display;
        menu.parentNode.style.backgroundImage = "url(images/menu/minus.gif)";
        menu.style.display = (display == "block") ? "none" : "block";
    }
}

function expandMenus() {
    var menusToExpand = getCookie(menuCookie);
    if (menusToExpand != null) {
        // if more than one menu has been menusToExpanded,
        // create an array of menusToExpanded menus
        if (menusToExpand.indexOf(",") != -1) {
            menuArray = menusToExpand.split(",");
            for (var i=0; i < menuArray.length; i++) {
                openMenu(menuArray[i]);
            }
         } else {
            openMenu(menusToExpand);
         }
    }
    var itemToHighlight = getCookie(itemCookie);
    var links = document.getElementsByTagName("a");
    // add an onclick event to set a cookie on the non-actuators
    for (i=0; i < links.length; i++) {
        if (links[i].href == itemToHighlight) {
            highlightMenu = links[i];    //new add modifor: zbzhang
            links[i].className += " highlight";
        }
    }
}

// =========================================================================
//                          Cookie functions 
// =========================================================================
/* This function is used to set cookies */
function setCookie(name,value,expires,path,domain,secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

/* This function is used to get cookies */
function getCookie(name) {
	var prefix = name + "=" 
	var start = document.cookie.indexOf(prefix) 

	if (start==-1) {
		return null;
	}
	
	var end = document.cookie.indexOf(";", start+prefix.length) 
	if (end==-1) {
		end=document.cookie.length;
	}

	var value=document.cookie.substring(start+prefix.length, end) 
	return unescape(value);
}

/* This function is used to delete cookies */
function deleteCookie(name,path,domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}