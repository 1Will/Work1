var lastStyle;
  /*
   *  ?????valuelist?????????
   *
  */
  function toggle1(object) {
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
  function mouseout1(object){
   object.className = lastStyle;
  }