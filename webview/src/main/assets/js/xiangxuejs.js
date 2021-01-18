var xiangxuejs = {};
xiangxuejs.os = {};
xiangxuejs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
xiangxuejs.os.isAndroid = !xiangxuejs.os.isIOS;
xiangxuejs.callbacks = {}

xiangxuejs.callback = function (callbackname, response) {
   var callbackobject = xiangxuejs.callbacks[callbackname];
   console.log("xxxx"+callbackname);
   if (callbackobject !== undefined){
       if(callbackobject.callback != undefined){
          console.log("xxxxxx"+response);
            var ret = callbackobject.callback(response);
           if(ret === false){
               return
           }
           delete xiangxuejs.callbacks[callbackname];
       }
   }
}

xiangxuejs.postMessage = function(commandname, parameters){
    console.log("xiangxuejs postMessage")
    var request = {};
    request.name = commandname;
    request.params = parameters;
    if(window.xiangxuejs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.JsInjector.postMessage(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.JsInjector.postMessage(JSON.stringify(request))
    }
}

xiangxuejs.postMessageWithCallback = function(commandname, parameters, callback) {
    var callbackname = "nativetojs_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    xiangxuejs.callbacks[callbackname] = {callback:callback};

    var request = {};
    request.name = commandname;
    request.params = parameters;
    request.params.callbackname = callbackname;
    if(window.xiangxuejs.os.isAndroid){
        window.JsInjector.postMessage(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.JsInjector.postMessage(JSON.stringify(request))
    }
}

window.xiangxuejs = xiangxuejs;
