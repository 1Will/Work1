<div id="demo1"></div>
<link rel="stylesheet" type="text/css" href="/skin/wkmk/css/jPaginate.css" media="screen"/>
<script src="/skin/wkmk/js/jquery.paginate.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$("#demo1").paginate({
		count 		: 100,
		start 		: 1,
		display     : 8,
		border					: true,
		border_color			: '#fff',
		text_color  			: '#fff',
		background_color    	: 'black',	
		border_hover_color		: '#ccc',
		text_hover_color  		: '#000',
		background_hover_color	: '#fff', 
		images					: false,
		mouse					: 'press'
	});
});
</script>