function b(){
	h = jQuery(window).height();
	//alert("h -- "+h);
	t = document.documentElement.scrollTop + document.body.scrollTop;
	//alert("t -- "+t);
	if(t > (h-600)){
		jQuery('#gotop').show();
	}else{
		jQuery('#gotop').hide();
	}
}
$(document).ready(function(e) {
	b();
	jQuery('#gotop').click(function(){
		//jQuery(document).scrollTop(0);	
		document.documentElement.scrollTop = 0
		document.body.scrollTop=0
	})
	jQuery('#code').hover(function(){
			jQuery(this).attr('id','code_hover');
			jQuery('#code_img').show();
		},function(){
			jQuery(this).attr('id','code');
			jQuery('#code_img').hide();
	})
	
});

jQuery(window).scroll(function(e){
	b();		
})
