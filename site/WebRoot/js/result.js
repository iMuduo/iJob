var keyword;
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
    } else {  
        return this.replace(reallyDo, replaceWith);  
    }  
};
$(document).ready(function(){
	var tds=$("td");
	keyword=$("#keyword").text().trim();
	$("input[name=keyword]").val(keyword);
	for(var i=0;i<tds.length;i++)
	{
		if($(tds[i]).html().toLowerCase().indexOf(keyword.toLowerCase())!=-1)
			$(tds[i]).html($(tds[i]).html().replaceAll(keyword,"<em>"+keyword+"</em>",true));
	}
});
