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
            //var boldButton = webEditor.querySelector(".bold-button");
            
            var toolbar = webEditor.querySelector(".toolbar");

            toolbar.onclick = function(e){
                var name = e.target.dataset.name;
                
                switch(name){
                case "bold":
                	// 선택 영영을 제공해 주고 있습니다. 선택된 영역을 얻어주세요 그리고 ?에 넣어주세요;
                    var selObj = doc.getSelection(); 
                    doc.execCommand("bold"); 
                    //doc.execCommand("insertHTML", false, "<strong>"+selObj+"</strong>");
                    break;  
                case "italic":
                    var selObj = doc.getSelection(); 
                    doc.execCommand("italic"); 
                    break;  
                case "color":
                	doc.execCommand("foreColor", false, "#979797");
                    break;
                case "image":
                    var fileInput = toolbar.querySelector("input[type='file']");

                    var event = new MouseEvent("click",{
                        "view" : window,
                        "bubbles" : true,
                        "cancelable" : true
                    });
                    fileInput.dispatchEvent(event);
                    fileInput.onchange = function(e){
            			var file = fileInput.files[0];
            			// 선택한 파일에 대한 조건 제어
            			for(var p in file){
            				console.log(p);
            			}
            			
            			console.log(file.type); //image/jpeg
            			
            			if(file.type.indexOf("image/") < 0){
            				alert("이미지가 아닙니다.");
            				return;
            			}
            			
            			if(file.size > 1024*1024*10){
            				alert("죄송합니다. 10MB를 초과할 수 없습니다.")
            				return;
                        }
                        

            			// file.size;
            			// file.type;
            			// url encoded / multipart.form
                        
                        var formData = new FormData();
                        formData.append("file", file);

                        var request = new XMLHttpRequest();

                        request.onload = function(e){
                            // ?? 업로드된 사진을 편집 영역에 붙여 넣기를 해야 한다.
                            if(request.status == 200){
                                var path = request.responseText;
                                console.log(path);
                            } else{
                                alert("에러났어 바보야");
                            }
                        }
                        request.open("POST", "/academy/upload-ajax", true);
                        request.send(formData);
                        
                    }
                    var selObj = doc.getSelection(); 
                    doc.execCommand("italic"); 
                    break;
                }
                editorTarget.value = doc.body.innerHTML;
            }
 
            doc.body.onkeyup = function(e){
                editorTarget.value = doc.body.innerHTML;
            }
            
        });
        
        
    };
    
    request.open("GET", "template.html", true);
    request.send();
    
});