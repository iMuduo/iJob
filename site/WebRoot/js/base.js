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
