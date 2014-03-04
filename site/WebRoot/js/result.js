var keyword;
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
    } else {  
        return this.replace(reallyDo, replaceWith);  
    }  
};
$(document).ready(function(){
	var spans=$("td>.content");
	keyword=$("#keyword").text().trim();
	$("input[name=keyword]").val(keyword);
	for(var i=0;i<spans.length;i++)
	{
		if($(spans[i]).html().toLowerCase().indexOf(keyword.toLowerCase())!=-1)
			$(spans[i]).html($(spans[i]).html().replaceAll(keyword,"<em>"+keyword+"</em>",true));
	}
});
