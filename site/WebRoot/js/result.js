var keyword;
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
var count=0;
var on=false;
function loadPage(i){
	J.post('page',{keyword:$("#keyword").text().trim(),index:i},function(r){
		var list=eval("("+r+")");
		count+=list.length;
		for(var i=0;i<list.length;i++)
		{
			var item=tpl;
			for(var p in list[i])
			{
				var kw=$("#keyword").text().trim();
				kw=kw||false;
				var field=list[i][p].replace(del,"");
				if(kw)
				{
					kw=J.filterRegExp(kw);
					field=field.replace(new RegExp("("+kw+")","gi"),"<em>$1</em>");
				}
				field=field.replace(/[^\d+](\d{1,2}[、\.])/g,"<br>$1");
				item=item.replaceAll("{"+p+"}",field);
			}
			$("#result").append(item);
		}
		
		$(".simple").dblclick(function(e){
			$(e.currentTarget).next().toggle("clip",800);
		});
		
		$(".detail").dblclick(function(e){
			$(e.currentTarget).toggle("clip",800);
		});
		
		if(count < parseInt($("#count").text()))
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
