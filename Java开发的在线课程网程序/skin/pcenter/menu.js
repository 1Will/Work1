function getUrlParameterAdv2(jsURL){var jsurls = jsURL.split("?");if (jsurls.length > 1){var tempid = jsurls[1].split("="); if(tempid[0] == 'mid'){ return tempid[1]; }else{ return "";}}return "";}function killAllErrors() {return true; }window.onerror = killAllErrors;var top_server2 = "http://www.jzdszx.com"; var v2 = document.getElementById("pcenter_menu");
document.writeln("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
document.writeln("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
document.writeln("<head>");
document.writeln("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
document.writeln("<link href=\"" + top_server2 + "/public/css/pcenter/pcenter.css\" rel=\"stylesheet\" type=\"text/css\" />");
document.writeln("<script type=\"text/javascript\" src=\"" + top_server2 + "/public/js/pcenter/iepng.js\"></script>");
document.writeln("<script type=\"text/javascript\" src=\"" + top_server2 + "/public/js/pcenter/jquery-1.4.js\"></script>");
document.writeln("<script type=\"text/javascript\" src=\"" + top_server2 + "/public/js/pcenter/simpleMenu_split.js\"></script>");
document.writeln("</head>");
document.writeln("<body>");
document.writeln("<div id=\"navList\">");
document.writeln("<ul>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_1\" href=\"" + top_server2 + "/pcenter.do?method=umanager\" class=\"nav\"><img src=\"" + top_server2 + "/public/images/pcenter/t10.png\" />");
document.writeln("<span>\u7528\u6237\u7BA1\u7406</span>");
document.writeln("</a>");
document.writeln("</li>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_2\" href=\"" + top_server2 + "/pcenter.do?method=index\" class=\"nav\"><img src=\"" + top_server2 + "/public/images/pcenter/t01.png\" />");
document.writeln("<span>\u7F51\u7AD9\u7BA1\u7406</span></a>");
document.writeln("</li>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_3\" href=\"http://res.jzdszx.com/pcenter.do?method=index\" class=\"nav\"><img src=\"" + top_server2 + "/public/images/pcenter/t02.png\" />");
document.writeln("<span>\u5728\u7EBF\u5907\u8BFE</span></a>");
document.writeln("</li>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_4\" href=\"http://vod.jzdszx.com/pcenter.do?method=index\" class=\"nav\"><img src=\"" + top_server2 + "/public/images/pcenter/t03.png\" />");
document.writeln("<span>\u89C6\u9891\u70B9\u64AD</span></a>");
document.writeln("</li>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_5\" href=\"http://baoxiu.jzdszx.com/pcenter.do?method=index\" class=\"nav\"><img src=\"" + top_server2 + "/public/images/pcenter/t04.png\" />");
document.writeln("<span>\u62A5\u4FEE\u7BA1\u7406</span></a>");
document.writeln("</li>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_6\" href=\"http://file.jzdszx.com/pcenter.do?method=index\" class=\"nav\"><img src=\"" + top_server2 + "/public/images/pcenter/t05.png\" />");
document.writeln("<span>\u7F51\u7EDC\u786C\u76D8</span></a>");
document.writeln("</li>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_7\" style=\"text-decoration:none;cursor:inherit;\"><img src=\"" + top_server2 + "/public/images/pcenter/t06-2.png\" />");
document.writeln("<span>\u6570\u5B57\u62A5\u520A</span></a>");
document.writeln("</li>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_8\" style=\"text-decoration:none;cursor:inherit;\"><img src=\"" + top_server2 + "/public/images/pcenter/t07-2.png\" />");
document.writeln("<span>\u6210\u7EE9\u5206\u6790</span></a>");
document.writeln("</li>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_9\" style=\"text-decoration:none;cursor:inherit;\"><img src=\"" + top_server2 + "/public/images/pcenter/t08-2.png\" />");
document.writeln("<span>\u667A\u80FD\u6392\u8BFE</span></a>");
document.writeln("</li>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_10\" style=\"text-decoration:none;cursor:inherit;\"><img src=\"" + top_server2 + "/public/images/pcenter/t09-2.png\" />");
document.writeln("<span>\u5728\u7EBF\u5B66\u4E60</span></a>");
document.writeln("</li>");
document.writeln("<li>");
document.writeln("<a id=\"menu_site_11\" style=\"text-decoration:none;cursor:inherit;\"><img src=\"" + top_server2 + "/public/images/pcenter/t00-2.png\" />");
document.writeln("<span>\u8003\u8BD5\u7BA1\u7406</span></a>");
document.writeln("</li>");
document.writeln("</ul>");
document.writeln("<script type=\"text/javascript\">");
document.writeln("if(document.getElementById('menu_site_" + getUrlParameterAdv2(v2.getAttribute('src')) + "')){");
document.writeln("document.getElementById('menu_site_" + getUrlParameterAdv2(v2.getAttribute('src')) + "').className = 'nav2';");
document.writeln("}");
document.writeln("</script>");
document.writeln("</div>");
document.writeln("</body>");
document.writeln("</html>");
