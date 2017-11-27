jQuery.fn.rater	= function(options) {
		
	// 榛樿鍙傛暟
	var settings = {
		enabled	: true,
		url		: '',
		method	: 'post',
		min		: 1,
		max		: 5,
		step	: 1,
		value	: null,
		after_click	: null,
		before_ajax	: null,
		after_ajax	: null,
		title_format	: null,
		image	: '/skin/vod/images/star2.gif',
		width	: 16,
		height	: 16
	}; 
	
	// 镊畾涔夊弬鏁?
	if(options) {  
		jQuery.extend(settings, options); 
	}
	
	// 涓诲鍣?
	var content	= jQuery('<ul class="rater-star"></ul>');
	content.css('background-image' , 'url(' + settings.image + ')');
	content.css('height' , settings.height);
	content.css('width' , (settings.width*settings.step) * (settings.max-settings.min+settings.step)/settings.step);
	
	// 褰揿墠阃変腑镄?
	var item	= jQuery('<li class="rater-star-item-current"></li>');
	item.css('background-image' , 'url(' + settings.image + ')');
	item.css('height' , settings.height);
	item.css('width' , 0);
	item.css('z-index' , settings.max / settings.step + 1);
	if (settings.value) {
		item.css('width' , ((settings.value-settings.min)/settings.step+1)*settings.step*settings.width);
	}
	
	content.append(item);
	
	// 鏄熸槦
	for (var value=settings.min ; value<=settings.max ; value+=settings.step) {
		item	= jQuery('<li class="rater-star-item"></li>');
		
		if (typeof settings.title_format == 'function') {
			item.attr('title' , settings.title_format(value));
		}
		else {
			item.attr('title' , value);
		}
		item.css('height' , settings.height);
		item.css('width' , (value-settings.min+settings.step)*settings.width);
		item.css('z-index' , (settings.max - value) / settings.step + 1);
		item.css('background-image' , 'url(' + settings.image + ')');
		
		if (!settings.enabled) {	// 鑻ユ槸涓嶈兘镟存敼锛屽垯闅愯棌
			item.hide();
		}
		
		content.append(item);
	}
	
	content.mouseover(function(){
		if (settings.enabled) {
			jQuery(this).find('.rater-star-item-current').hide();
		}
	}).mouseout(function(){
			jQuery(this).find('.rater-star-item-current').show();
	})
	
	// 娣诲姞榧犳爣镇仠/镣瑰向浜嬩欢
	content.find('.rater-star-item').mouseover(function() {
		jQuery(this).attr('class' , 'rater-star-item-hover');
	}).mouseout(function() {
		jQuery(this).attr('class' , 'rater-star-item');
	}).click(function() {
		jQuery(this).prevAll('.rater-star-item-current').css('width' , jQuery(this).width());
		
		var star_count		= (settings.max - settings.min) + settings.step;
		var current_number	= jQuery(this).prevAll('.rater-star-item').size()+1;
		var current_value	= settings.min + (current_number - 1) * settings.step;
		var data	= {
			value	: current_value,
			number	: current_number,
			count	: star_count,
			min		: settings.min,
			max		: settings.max
		}
		
		// 澶勭悊锲炶皟浜嬩欢
		if (typeof settings.after_click == 'function') {
			settings.after_click(data);
		}
		
		// 澶勭悊ajax璋幂敤
		if (settings.url) {
			
			jQuery.ajax({
				data		: data,
				type		: settings.method,
				url			: settings.url,
				beforeSend	: function() {
					if (typeof settings.before_ajax == 'function') {
						settings.before_ajax(data);
					}
				},
				success		: function(ret) {
					if (typeof settings.after_ajax == 'function') {
						data.ajax	= ret;
						settings.after_ajax(data);
					}
				}
			});
			
		}
	})
	
	jQuery(this).html(content);
	
}
