// test 페이지가 로드 되었을 때 실행
window.addEventListener("load", function(e){

    // 덮어쓸 타겟
    var editorTarget = document.querySelector("textarea");
    var parent = editorTarget.parentElement;


    // -------------- webEditor를 가져오는 작업---------------------------------------------    
    var width = window.getComputedStyle(editorTarget,null).getPropertyValue("width");
    var height = window.getComputedStyle(editorTarget,null).getPropertyValue("height");

    //null에 들어갈 값은 슈도

    var webEditor = document.createElement("div"); // 로드해야 하고
    // webEditor.contentEditable = "true";
    webEditor.style.background = "yellow";
    webEditor.style.width = width;
    webEditor.style.height = height;
    
    var request = new XMLHttpRequest();
    
    // template이  로드 되었을 때 실행
    request.onload = function(e){ 
        // webEditor를 editorTarget 바로 밑 동생으로 추가해야 한다.
    	
    	webEditor.innerHTML = request.responseText;        
        editorTarget.after(webEditor);
          
        var iframe = webEditor.querySelector("iframe");    
        var win = iframe.contentWindow; 
        
        // body.html 가 로드되었을 때
        win.addEventListener("load", function(e){    
            var doc = win.document;
        	var boldButton = webEditor.querySelector(".bold-button");
            boldButton.onclick = function(e){
                doc.execCommand("bold");  
            };
        });
        
        
    };
    
    request.open("GET", "template.html", true);
    request.send();
    
});