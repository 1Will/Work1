  

  <TABLE  cellspacing="2" cellpadding="0" style="border: 1 solid #000000;">
  <TR height="100%">
  <TD align="center" style="border: 1 solid #000000;"><img src="${req.contextPath}/images/jac-banner_50.jpg"  border="0"/></TD>
  <TD ROWSPAN=2 width="30%" VALIGN="TOP" style="border: 1 solid #000000;">
  <TABLE width="100%">
  <@titleBar title="系统提醒"/>
   <@listTable>
   <tr>
     <td>
      您有<a href="${req.contextPath}/workspace/warnning/myWarnning/listWorkWarnnings.html?loginUser.id=#{loginUser.id?if_exists}&onlyUnRead=true&first=false"><strong>${numberOfUnRead?if_exists}</strong></a>个未读提醒
     </td>
   </tr>
   </@listTable>
   <@titleBar title="通知"/>
   <@listTable>
     <tr>
       <td>
         您有<a href="${req.contextPath}/notice/listReceviceNotices.html?loginUser.id=#{loginUser.id?if_exists}&readStatus=UNREAD&first=false"><strong>${numberOfUnReadNotice?if_exists}</strong></a>个未读通知
       </td>
     </tr>
   </@listTable>
   <@listTable>
     <tr >
       <td style="text-align:right" width="100%" height="100%">
         <marquee behavior="scroll" hspace=0 vspace=0 onMouseOut=this.start() onMouseOver=this.stop() height=300 direction=up scrollamount=1 scrolldelay=10>
           <#if unReadNotices?exists>
           <#list unReadNotices as notice>
			  <li>
                <a target="_self" href="${req.contextPath}/notice/editReceviceNotice.html?receviceNotice.id=#{notice.id?if_exists}"><strong>${notice.title?if_exists}</strong></a>
		      </li>
		      <br/>
           </#list>
           </#if>
         </marquee>
       </td>
     <tr>
   </@listTable>

   <#--
   <tr>
   <th>提醒名称</th>
   <th>日期</th>
   <th>状态</th>
   </tr>
   <tr>
   <td style="text-align:left"><a href="#">备件XXX小于安全库存</a></td>
   <td style="text-align:left">2007-10-05</td>
   <td style="text-align:left">未读</td>
   </tr>
   <tr>
   <td style="text-align:left"><a href="#">计划已过期</a></td>
   <td style="text-align:left">2007-10-05</td>
   <td style="text-align:left">未读</td>
   </tr>
   <tr>
   <td style="text-align:left"><a href="#">润滑计划已生成</a></td>
   <td style="text-align:left">2007-10-05</td>
   <td style="text-align:left">未读</td>
   </tr>
    -->

  </TABLE>
  </TD>
  </TR>
 
  <TR>
  <TD VALIGN="TOP" style="border: 1 solid #000000;">
  <TABLE width="100%">
  <@titleBar title="帮助手册下载"/>
   <@listTable>
   <#--
   <tr>
   <th>手册名称</th>
   <th>日期</th>
   </tr>
   <tr>
   <td style="text-align:left"><a href="#">程序手册</a></td>
   <td style="text-align:left">2007-10-01</td>
   </tr>
   <tr>
   <td style="text-align:left"><a href="#">制度手册</a></td>
   <td style="text-align:left">2007-10-05</td>
   </tr>
   <tr>
   <td style="text-align:left"><a href="#">未分类手册</a></td>
   <td style="text-align:left">2007-10-09</td>
   </tr>
   -->
     <tr>
       <th>${action.getText('helpManual.name')}</th>
       <th>${action.getText('helpManual.vesion')}</th>
       <th>${action.getText('helpManual.createdTime')}</th>
     </tr>
     <#list manualDocs as manualDoc>
        <tr>
            <td class="alignLeft">
              <a href="${req.contextPath}/base/manual/downloadManual.html?manual.id=#{manualDoc.id}" title="${action.getText('download')}">
                ${manualDoc.name?if_exists}
              </a>
            </td>
            <td class="alignLeft">
                ${manualDoc.manualVersion?if_exists}
            </td>
            <td class="alignCenter">${manualDoc.createdTime?string('yyyy-MM-dd')?if_exists}</td>
        </tr>
     </#list>
   </@listTable>
  </TABLE>
  </TD>
  </TR>
  </TABLE>
  <script>
  </script>
