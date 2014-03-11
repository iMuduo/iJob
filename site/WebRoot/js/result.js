var keyword;
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
    } else {  
        return this.replace(reallyDo, replaceWith);  
    }  
};
//$(document).ready(function(){
//	var spans=$("td>.content");
//	keyword=$("#keyword").text().trim();
//	$("input[name=keyword]").val(keyword);
//	for(var i=0;i<spans.length;i++)
//	{
//		if($(spans[i]).html().toLowerCase().indexOf(keyword.toLowerCase())!=-1)
//			$(spans[i]).html($(spans[i]).html().replaceAll(keyword,"<em>"+keyword+"</em>",true));
//	}
//});
var index=0;
var tpl;
var on=false;
function loadPage(i){
	J.post('page',{keyword:$("#keyword").text().trim(),index:i},function(r){
		var list=eval("("+r+")");
		for(var i=0;i<list.length;i++)
		{
			var item=tpl;
			for(var p in list[i])
			{
				var kw=$("#keyword").text().trim();
				item=item.replace("{"+p+"}",list[i][p].replaceAll(kw,"<em>"+kw+"</em>",true));
			}
			$("#result").append(item);
		}
		on=true;
	});
}

$(function(){
	J.autoComplete("input[name='keyword']","hotword!autoComplete");
	$.get("item",function(r){
		tpl=r;
		loadPage(index++);
	});
	$("input[name=keyword]").val($("#keyword").text().trim());
	$(window).scroll(function(){
		if(($(document).height()-$(document).scrollTop()-window.innerHeight)<100 && on)
		{
			loadPage(index++);
			on=false;
		}
		if($(document).scrollTop()>600 && !$("#goTop").get(0))
		{
			$("body").append("<div id='goTop'></div>");
			$("#goTop").click(function(){
				$("#goTop").remove();
				$(document).scrollTop(0);
			});
		}
		if($(document).scrollTop()<600 && $("#goTop").get(0))
			$("#goTop").remove();
		if($("#goTop").get(0))
			$("#goTop").offset({top:window.innerHeight+$(document).scrollTop()-110,left:window.innerWidth-120});
	});
});
