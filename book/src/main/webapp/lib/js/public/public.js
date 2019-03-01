$(document).ready(function(){
    /* 公共部分 header.html */
    $("#header").load("lib/public/header.html",function(){
        var sc =  document.createElement("script");
        sc.src= "lib/js/public/header.js";
        $("body").append(sc);
    });

    /* 公共部分 footer.html */
    $("#footer").load("lib/public/footer.html",function(){
        var sc =  document.createElement("script");
        sc.src= "lib/js/public/footer.js";
        $("body").append(sc);
    });


    $.ajax({ 
        url: "User/loadUserById.do", 
        type:"post",
        dataType:"json",
        data:{},
        success: function(result){ 
            if(result.status == 0){
                SetCodeUser(result);
            }else{
                $.ajax({ 
                    url: "Admin/findAdminID.do", 
                    type:"post",
                    dataType:"json",
                    data:{},
                    success: function(result){
                        if(result.status == 0){
                            SetCodeAdmin(result);
                            
                        }else{
                            SetCodeFalse();
                        }
                    },
                    error:function(result)
                    {
                        
                    }
                });
            }
        },
        error:function(result)
        {
            
        }
    });

    $("#header").on("click","#Signout",function(){
        $.ajax({ 
            url: "User/cleanSession.do", 
            type:"post",
            dataType:"json",
            data:{},
            success: function(result){
                location.reload(true);
            },
            error:function(result)
            {
                console.log("清除Session失败"); 
            }
        });
    })
    

    let SetCodeUser = (result) =>{
        $("#header .top .row").append("<div class=\"account grid col-xs-4\">"
                                    +"<a href=\"userinfo.html\"><img src=\"lib/images/"+result.data.user_img_id+"\" alt=\"账户图片\"></a>"
                                    +"<ul>"
                                    +"<li class=\"Codetitle\"><a href=\"#\">"+result.data.user_name+"</a></li>"
                                    +"<li><a href=\"#\">书架</a></li>"
                                    +"<li><a href=\"#\">阅读历史</a></li>"
                                    +"<li id=\"Signout\"><a href=\"javascript:;\">退出</a></li>"
                                    +"</ul>"
                                    +"</div>");
    }
    let SetCodeAdmin = (result) =>{
        $("#header .top .row").append("<div class=\"account grid col-xs-4\">"
                                    +"<ul>"
                                    +"<li class=\"Codetitle\"><a href=\"#\">"+result.data.admin_name+"</a></li>"
                                    +"<li><a href=\"lib/backstage/default.html\">后台主页</a></li>"
                                    +"<li id=\"Signout\"><a href=\"javascript:;\">退出</a></li>"
                                    +"</ul>"
                                    +"</div>");
    }
    let SetCodeFalse = () =>{
        $("#header>.top>.row").append("<div class=\"login grid col-xs-4\">"
                                    +"    <a href=\"#myModal\" data-toggle=\"modal\" data-target=\"#myModal\">登陆</a> | "
                                    +"    <a href=\"lib/public/register.html\">注册</a>"
                                    +"</div>");
    }
});