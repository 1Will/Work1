// JavaScript Document
window.onload = function() {
 dMarquee('scroll');
}

function dMarquee(id){
 var speed=30; //阃熷害
 var stop=2000; //锅沧镞堕棿 

 var ul = document.getElementById(id);
 var rows=ul.getElementsByTagName('li').length;
 var height = ul.getElementsByTagName('li')[0].offsetHeight;

 ul.innerHTML += ul.innerHTML;

 var timeID = false;
 
 var play = function() {
  ul.scrollTop++;

  if(ul.scrollTop==rows*height){
   ul.scrollTop=0;
  }

  if(ul.scrollTop%height==0) {
   timeID = setTimeout(play,stop);
  }else{
   timeID = setTimeout(play,speed);
  } 
 }

 timeID = setTimeout(play,stop);


 ul.onmouseover = function() {clearTimeout(timeID);}
 ul.onmouseout = play;
}

