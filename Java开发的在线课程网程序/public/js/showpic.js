var picSrcArray=new Array();
var picLinkArray=new Array();
var picAltArray = new Array();
var adNum=0;
var cycle=5000;

function loadpic(img,imglink,imgalter){
  picSrcArray = img;
  picLinkArray = imglink;
  picAltArray = imgalter;
  var preloadedimages=new Array();
  for (i=1;i<picSrcArray.length;i++){
   preloadedimages[i]=new Image();
   preloadedimages[i].src=picSrcArray[i];
  }
}

function setTransition(){
  if (document.all){
    picName.filters.revealTrans.Transition=Math.floor(Math.random()*23);
    picName.filters.revealTrans.apply();
  }
}

function playTransition(){
  if (document.all)
    picName.filters.revealTrans.play()
  }

function nextIMG(){
  imgsrcarray()
  if(adNum<picSrcArray.length-1)
    adNum++ ;
  else adNum=0;

  setTransition();
  document.images.picName.src=picSrcArray[adNum];
  document.images.picName.alt=picAltArray[adNum];
  document.all.picdisplayname.innerHTML=picAltArray[adNum];
  playTransition();
  theTimer=setTimeout("nextIMG()", cycle);
}

function jump2url(){
  jumpUrl=picLinkArray[adNum];
  jumpTarget='_blank';
  if (jumpUrl != ''){
  if (jumpTarget != '')window.open(jumpUrl,jumpTarget);
    else location.href=jumpUrl;
   }
}
function displayStatusMsg() {
 status=picLinkArray[adNum];
}
