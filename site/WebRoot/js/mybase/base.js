J={};
J.escape=function(str){
	var re="";
	for(var i=0;i<str.length;i++)
		re=re.concat(this.nFormat(parseInt(str.charCodeAt(i) & 0xFFFF).toString(16),4));
	return re;
};

J.unescape=function(str){
	var re="";
	if(str.length & 3)
		return re;
	for(var i=0;i<str.length;i=i+4)
		re=re.concat(String.fromCharCode(parseInt(str.substring(i,i+4),16)));
	return re;
};

J.nFormat=function(str,n,f){
	var re="";
	f=f||"0";
	for(var i=0;i<n-str.length;i++)
		re=re.concat(f);
	return re+str;
};

J.post=function(url,data,fn){
	$.ajax({
		type:'post',
		url:url,
		data:data,
		contentType:'application/x-www-form-urlencoded; charset=utf-8',
		dataType:'text',
		success:fn
	});
};

J.autoComplete=function(sel,url){
	var kw,list;
	$(sel).keypress(function(e) {
		kw = $(e.target).val();
	});
	
	$(sel).keyup(
		function(e) {
			if ($(e.target).val() != kw)
				J.post(url,{keyword:$(e.target).val()}, function(r) {
					list = eval("(" + r + ")");
					$(sel).autocomplete({
						source:function(request,respone){
							respone($.grep(list,function(item){
								return true;
							}));
						},
						select:function(event,ui){
							$(sel).val(ui.item.label);
							$("form").submit();
						}
				});
		});
	});
};

J.toJson=function(r){
	return eval("("+r+")");
};

J.each=function(l,f){
	for(var i=0;i<l.length;i++)
		f(l[i],i);
};
