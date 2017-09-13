/**
 * Created by longhao on 2017/9/4.
 */
function showinfo(id){
    var right_tab=document.getElementById('myTabs');
    var home=document.getElementById('home');
    var profile=document.getElementById('profile');
    right_tab.firstElementChild.className="";
    right_tab.lastElementChild.className="am-active";
    home.className="am-tab-panel";
    profile.className="am-tab-panel  am-active am-in";

    //创建request对象
    var request = new XMLHttpRequest();
    //
    // 发送请求:
    request.open('GET', '/segments/'+id,true);
    //注册相关事件回调处理函数
    request.onreadystatechange=function () {
        if (request.readyState === 4) { // 成功完成
            // 判断响应结果:
            if (request.status === 200) {
                // 成功，通过responseText拿到响应的文本:
                   return success(request.responseText);
            } else {
                // 失败，根据响应码判断失败原因:
                return fail(request.status);
            }
        } else {
            // HTTP请求还在继续...
        }
    }
    request.send();

    function success(text) {
        var jsonData=JSON.parse(text);
        var element = document.getElementById('profile');
        element.innerHTML=null;
        for(var i=0;i<jsonData.length;i++){
            var json=jsonData[i];
            var projectName=json.projectName;
            var segmentContent=json.segmentContent;
            var div1=document.createElement("div");
            div1.setAttribute("class","am-panel am-panel-default");
            var div2=document.createElement("div");
            div2.setAttribute("class","am-panel-hd");
            var div3=document.createElement("div");
            div3.setAttribute("class","am-panel-bd");
            div3.innerHTML=segmentContent;
            var a1=document.createElement("a");
            a1.setAttribute("class","am-text-truncate");
            a1.innerHTML=projectName;
            div2.appendChild(a1);
            div1.appendChild(div2);
            div1.appendChild(div3);
            element.appendChild(div1);
        }


    }

    function fail(code) {
        var textarea = document.getElementById('profile');
        textarea.innerText = 'Error code: ' + code;
    }
};
