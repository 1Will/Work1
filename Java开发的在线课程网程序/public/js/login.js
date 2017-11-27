function loginname_onkeypress(){
  if(window.event.keyCode==13){
    window.event.keyCode=0;
    document.vodUserInfoActionForm.password.focus();
  }
}
function password_onkeypress(){
  if(window.event.keyCode==13){
    window.event.keyCode=0;
    userLoginAction();
  }
}

