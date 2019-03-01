

$(document).ready(function() {
    //设置top切换
    $("#main .middle .ranking_list .switch_content div").eq(0).show().siblings("div").hide();
    $("#main .middle .ranking_list .switch_top li").eq(0).css("color","orange").siblings("li").css("color","black");
    $("#main .middle .ranking_list .switch_top li").hover(function(){
        $(this).css("color","orange").siblings("li").css("color","black");
        $("#main .middle .ranking_list .switch_content div").eq($(this).index()).show().siblings("div").hide();
    },function(){
    });

    //3D旋转
    var LoopHeight = $("#main .middle .middle_first .modular").height() - $("#main .middle .middle_first .D3 #starsIF").height();
    $("#main .middle .middle_first .D3 #Loop").css("height",LoopHeight-20+"px");

    /*加载推荐*/
    $.ajax({ 
        url: "Book/loadBook.do", 
        type:"post",
        dataType:"json",
        success: function(data){      
            for(var i=0;i<10;i++){
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $("#main .top .hottest ul").append("<li><span class=\"item\"><a href=\"#\">"+data.data[num].book_type+"</a></span> <span class=\"title\"><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[num].book_title+"</a></span><span class=\"author\"><a href=\"#\">"+data.data[num].book_author+"</a></span></li>");
            }
        },
        error:function()
        {
            alert("加载推荐失败!");
            //错误处理
        }
    });
    /*加载*/
    $.ajax({ 
        url: "Book/loadBook.do", 
        type:"post",
        dataType:"json",
        success: function(data){      
            for(var i=0;i<4;i++){
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $(".carousel .list_pic").append(
                "<div class=\"pic\">"
                +"<img src=\"lib/images/default/0.jpg\" alt=\"图片\" class=\"img-responsive col-xs-4\">"
                +"<div class=\"col-xs-8\">"
                +"<h3><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[num].book_title+"</a></h3>"
                +"<p>作者：<a href=\"#\">"+data.data[num].book_author+"</a></p>"
                +"<p>"+data.data[num].book_explain+"</p>"
                +"</div>"
                +"</div>");
            }
            for(var i=0;i<3;i++){
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $("#LoopDiv #starsIF img").eq(i).attr("src","lib/images/"+data.data[num].book_img);
                $(".middle_first .D3").append("<div class=\"Loop\">"
                                              +"<h3><a href=\"javascript:;\">"+data.data[num].book_title+"</a></h3>"
                                              +"<p>"+data.data[num].book_count+"字 · 8.7分</p>"
                                              +"<p><span>40,000</span>人在看</p>"
                                              +"<p>"+data.data[num].book_explain+"</p>"
                                              +"<p><a href=\"details.html?book_id="+data.data[num].book_id+"\">书籍详情</a></p>"
                                              +"</div>");
            }
            $(".middle_first .D3 .Loop").eq(0).css("display","block").siblings(".Loop").css("display","none");
        },
        error:function()
        {
            alert("加载推荐、3D失败!");
            //错误处理
        }
    });
    $("#LoopDiv #starsIF img").click(function(){
        $(".middle_first .D3 .Loop").eq($(this).index()).css("display","block").siblings(".Loop").css("display","none");
    });
    //玄幻
    $.ajax({ 
        url: "Book/loadBooksByType.do", 
        type:"post",
        dataType:"json",
        data:{
            book_type:"玄幻",
            page:1,
            limit:6
        },
        success: function(data){      
            $(".modular .xh .content li").eq(0).find("img").attr("src","lib/images/"+data.data[0].book_img);
            $(".modular .xh .content li").eq(0).find("div").append("<h3><a href=\"details.html?book_id="+data.data[0].book_id+"\">"+data.data[0].book_title+"</a></h3>"
                                                                        +"<p>作者 : <a href=\"#\">"+data.data[0].book_author+"</a></p>"
                                                                        +"<p>书籍类型 : <a href=\"#\">"+data.data[0].book_type+"</a></p>"
                                                                        +"<p>内容介绍 : "+data.data[0].book_explain+"</p>");
            for(var i =1;i<6;i++){
                $(".modular .xh .content ul").append("<li>"
                                                          +"   <a href=\"#\">["+data.data[i].book_type+"]</a>"
                                                          +"   <a href=\"+details.html?book_id="+data.data[i].book_id+"\">"+data.data[i].book_title+"</a>"
                                                          +"    <span>"+data.data[i].book_update_time+"</span>"
                                                          +"  </li>");
            }
        },
        error:function()
        {
            alert("加载推荐失败!");
            //错误处理
        }
    });

    //同人
    $.ajax({ 
        url: "Book/loadBooksByType.do", 
        type:"post",
        dataType:"json",
        data:{
            book_type:"同人",
            page:1,
            limit:6
        },
        success: function(data){      
            $(".modular .tr .content li").eq(0).find("img").attr("src","lib/images/"+data.data[0].book_img);
            $(".modular .tr .content li").eq(0).find("div").append("<h3><a href=\"details.html?book_id="+data.data[0].book_id+"\">"+data.data[0].book_title+"</a></h3>"
                                                                        +"<p>作者 : <a href=\"#\">"+data.data[0].book_author+"</a></p>"
                                                                        +"<p>书籍类型 : <a href=\"#\">"+data.data[0].book_type+"</a></p>"
                                                                        +"<p>内容介绍 : "+data.data[0].book_explain+"</p>");
            for(var i =1;i<6;i++){
                $(".modular .tr .content ul").append("<li>"
                                                          +"   <a href=\"#\">["+data.data[i].book_type+"]</a>"
                                                          +"   <a href=\"+details.html?book_id="+data.data[i].book_id+"\">"+data.data[i].book_title+"</a>"
                                                          +"    <span>"+data.data[i].book_update_time+"</span>"
                                                          +"  </li>");
            }
        },
        error:function()
        {
            alert("加载推荐失败!");
            //错误处理
        }
    });
    //科幻
    $.ajax({ 
        url: "Book/loadBooksByType.do", 
        type:"post",
        dataType:"json",
        data:{
            book_type:"科幻",
            page:1,
            limit:6
        },
        success: function(data){      
            $(".modular .kh .content li").eq(0).find("img").attr("src","lib/images/"+data.data[0].book_img);
            $(".modular .kh .content li").eq(0).find("div").append("<h3><a href=\"details.html?book_id="+data.data[0].book_id+"\">"+data.data[0].book_title+"</a></h3>"
                                                                        +"<p>作者 : <a href=\"#\">"+data.data[0].book_author+"</a></p>"
                                                                        +"<p>书籍类型 : <a href=\"#\">"+data.data[0].book_type+"</a></p>"
                                                                        +"<p>内容介绍 : "+data.data[0].book_explain+"</p>");
            for(var i =1;i<6;i++){
                $(".modular .kh .content ul").append("<li>"
                                                          +"   <a href=\"#\">["+data.data[i].book_type+"]</a>"
                                                          +"   <a href=\"+details.html?book_id="+data.data[i].book_id+"\">"+data.data[i].book_title+"</a>"
                                                          +"    <span>"+data.data[i].book_update_time+"</span>"
                                                          +"  </li>");
            }
        },
        error:function()
        {
            alert("加载推荐失败!");
            //错误处理
        }
    });

    //排行榜
    $.ajax({ 
        url: "Book/loadBook.do", 
        type:"post",
        dataType:"json",
        success: function(data){
        for(var i=0;i<30;i++){
            if(i<10){
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $(".switch_content .update ul").eq(0).append("<li>"
                                                            +"<span>"+i+"</span>"
                                                            +"<span><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[i].book_title+"</a></span>"
                                                            +"<span>"+data.data[i].book_update_time+"</span>"
                                                            +"</li>");
            }else if(i<20){
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $(".switch_content .update ul").eq(1).append("<li>"
                                                            +"<span>"+i+"</span>"
                                                            +"<span><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[i].book_title+"</a></span>"
                                                            +"<span>"+data.data[i].book_update_time+"</span>"
                                                            +"</li>");
            }else{
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $(".switch_content .update ul").eq(2).append("<li>"
                                                            +"<span>"+i+"</span>"
                                                            +"<span><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[i].book_title+"</a></span>"
                                                            +"<span>"+data.data[i].book_update_time+"</span>"
                                                            +"</li>");
            }
        }
        for(var i=0;i<30;i++){
            if(i<10){
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $(".switch_content .newest ul").eq(0).append("<li>"
                                                            +"<span>"+i+"</span>"
                                                            +"<span><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[i].book_title+"</a></span>"
                                                            +"<span>"+data.data[i].book_update_time+"</span>"
                                                            +"</li>");
            }else if(i<20){
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $(".switch_content .newest ul").eq(1).append("<li>"
                                                            +"<span>"+i+"</span>"
                                                            +"<span><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[i].book_title+"</a></span>"
                                                            +"<span>"+data.data[i].book_update_time+"</span>"
                                                            +"</li>");
            }else{
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $(".switch_content .newest ul").eq(2).append("<li>"
                                                            +"<span>"+i+"</span>"
                                                            +"<span><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[i].book_title+"</a></span>"
                                                            +"<span>"+data.data[i].book_update_time+"</span>"
                                                            +"</li>");
            }
        }
        for(var i=0;i<30;i++){
            if(i<10){
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $(".switch_content .flower ul").eq(0).append("<li>"
                                                            +"<span>"+i+"</span>"
                                                            +"<span><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[i].book_title+"</a></span>"
                                                            +"<span>"+data.data[i].book_update_time+"</span>"
                                                            +"</li>");
            }else if(i<20){
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $(".switch_content .flower ul").eq(1).append("<li>"
                                                            +"<span>"+i+"</span>"
                                                            +"<span><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[i].book_title+"</a></span>"
                                                            +"<span>"+data.data[i].book_update_time+"</span>"
                                                            +"</li>");
            }else{
                let num = Math.ceil(Math.random()*(data.data.length-1));
                $(".switch_content .flower ul").eq(2).append("<li>"
                                                            +"<span>"+i+"</span>"
                                                            +"<span><a href=\"details.html?book_id="+data.data[num].book_id+"\">"+data.data[i].book_title+"</a></span>"
                                                            +"<span>"+data.data[i].book_update_time+"</span>"
                                                            +"</li>");
            }
        }

           
        },
        error:function()
        {
            alert("加载推荐失败!");
            //错误处理
        }
    });
 
    

});