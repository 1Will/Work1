
var lastStyle;
  /*
   *  ?????valuelist?????????
   *
  */
  function toggle(object) {
    var lastId;
    if( lastId != undefined ) {
      document.getElementById(lastId).className = lastStyle;
    }
    lastId = object.id;
    lastStyle = object.className;
    previousClass = this.className;
    object.className = "selected1";
  }
  
  /*
   * ?????valuelist??????????
  */
  function mouseout(object){
   object.className = lastStyle;
  }