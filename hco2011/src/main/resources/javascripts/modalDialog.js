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
 /* $Id: modalDialog.js 6119 2007-07-30 08:24:26Z qsun $ */
var ModalDialogWindow;
var ModalDialogInterval;
var ModalDialog = new Object;

ModalDialog.value = null;
ModalDialog.eventhandler = null;


function ModalDialogMaintainFocus()
{
  try
  {
    if (ModalDialogWindow.closed)
     {
        window.clearInterval(ModalDialogInterval);
        if (ModalDialog.eventhandler != null) {
            ModalDialog.eventhandler(ModalDialog.value);
        }
        return;
     }
    ModalDialogWindow.focus();
  }
  catch (everything) {   }
}

 function ModalDialogRemoveWatch()
 {
    ModalDialog.value = null;
    ModalDialog.eventhandler = null;
 }

 function ModalDialogShow(Href, width, height, EventHandler)
 {
   ModalDialogRemoveWatch();
   ModalDialog.eventhandler = EventHandler;

   var args='width='+ width +',height='+ height + ',top='+ (screen.height-height)/2;
       args+=',left=' + (screen.width-width)/2 + ',';
       args+='scrollbars=1,toolbar=0,location=0,status=0,menubar=0,resizable=0';

   ModalDialogWindow=window.open(Href,"",args);
   ModalDialogWindow.focus();
   ModalDialogInterval = window.setInterval("ModalDialogMaintainFocus()",5);
 }

 function SetModalDialogResult(controls)
 {
   if (ModalDialog.value) {
        SetResult(controls, ModalDialog.value);
   }
 }

  function SetResult(controls, result)
  {
       var length = controls.length;
       for (i=0; i<length; i++) {
         controls[i].value = "" + result[i];
       }
  }




