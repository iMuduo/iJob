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
function getParams(i){
	var ps=$(".params");
	var params={};
	for(var i=0;i<ps.length;i++){
		params[$(ps[i]).attr("name")]=$(ps[i]).text().trim();
	}
	params['index']=i;
	return params;
}
function loadPage(i){
	J.post('page',getParams(i),function(r){
		var list=eval("("+r+")");
		count+=list.length;
		for(var i=0;i<list.length;i++)
		{
			var item=tpl;
			for(var p in list[i])
			{
				var field=list[i][p];
				var em;
				if(p=="jbnm")
					em=$(".params[name='keyword']");
				else
					em=$(".params[name='"+p+"']");
				if(em){
					var k=em.text().trim();
					if((k!=""))
						field=field.replace(new RegExp("("+J.filterRegExp(k)+")","gi"),"<em>$1</em>");
				}
				if(p=="wkrq" ||p=="jbdesc"||p=="benefit"||p=="cpinfo")
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
	$("input[name=keyword]").val($(".params[name='keyword']").text().trim());
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
