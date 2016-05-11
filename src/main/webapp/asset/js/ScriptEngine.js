/**
 * 
 */

function getRootPath(){
    var strFullPath=window.document.location.href;
    var strPath=window.document.location.pathname;
    var pos=strFullPath.indexOf(strPath);
    var prePath=strFullPath.substring(0,pos);
    var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
    return(prePath+postPath);
}
function ScriptEngine(){
    this.name = "";
    this.method = "";
    this.setClass = function(n){
        name = "class=" + n;
    }
    this.setMethod = function(m){
        method = "method=" + m;
    }
    this.invoke = function(){
        var result;
        var parames = "";
        if(arguments.length > 0){
            for(i = 0; i < arguments.length; i++){
            	if (i == (arguments.length-1)) {
            		parames+=arguments[i];
            	}
            	else{
            		parames+=arguments[i]+';';
            	} 
            }
        }
        $.ajax( {
            type : "POST",
            url : getRootPath() + "/api/v1/ztree/ztreenode",
            cache : false,
            async : false,
            data : name + "&" + method + "&args=" + parames,
            success : function(data) {
                result = data;
            },
            error :function(data){
                result= data;
            }
        });
        return result;
    }
}