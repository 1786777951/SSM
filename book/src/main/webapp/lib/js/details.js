 $(function(){
     //第一章id
    let firstID;
    //收集数组
    let collectionBooks;
    //当前用户ID
    let user_id;
    //登录状态
    let loginstate = false;
    // 调用方法
    var book_id=GetQueryString("book_id");
        if(book_id !=null && book_id.toString().length>0)
        {
            //填充小说内容
            $.ajax({
                url: 'Book/loadBookById.do',
                type: "post",
                data:{
                    "book_id":book_id
                },
                dataType: "json",
                success: function (result)
                {
                    $("#main .details .top .left").append("<img src=\"lib/images/"+result.data[0].book_img+"\" alt=\"封面\">");
                    $("#main .details .top .right .title").append("<a href=\"#\">"+result.data[0].book_title+"</a><a href=\"#\">"+result.data[0].book_author+" · 著</a>");
                    $("#main .details .top .right .tag").append("<span>"+result.data[0].book_type+"</span>");
                    $("#main .details .top .right .achievements").append("<span><b>"+result.data[0].book_count+"</b>字</span>|<span><b>0w</b>总点击</span>|<span><b>0</b>总推荐</span>");
                    $("#main .details .middle .tab .box .right .scroll .tab_right").eq(0).html(result.data[0].book_explain);
                },
                error: function (error)
                {
                    console.log(error);
                }
        });

    }else{
        console.log(book_id);
    }

    $("#main .top .read button").eq(0).hover(function(){
        $(this).addClass("movercolor");
    },function(){
        $(this).removeClass("movercolor");
    });
    $("#main .top .read button").eq(1).hover(function(){
        if($(this).html() == "已收藏"){
            $(this).addClass("movercolor").html("取消收藏？");
        }else{
            $(this).addClass("movercolor");
        }
    },function(){
        if($(this).html() == "取消收藏？"){
            $(this).addClass("collection").html("已收藏").removeClass("movercolor");
        }else{
            $(this).removeClass("movercolor");
        }
    });

    
        $.ajax({ 
            url: "User/loadUserById.do", 
            type:"post",
            dataType:"json",
            data:{},
            success: function(result){     
                if(result.status == 0){
                    user_id = result.data.user_id;
                    loginstate = true;
                    if(result.data.user_collection.indexOf(",") != -1 && result.data.user_collection.length>1){
                        collectionBooks = result.data.user_collection.split(",");
                    }else if(result.data.user_collection.length == 1){
                        collectionBooks = result.data.user_collection;
                    }else{

                    }
                    for(var i=0;i<collectionBooks.length;i++){
                        if(book_id == collectionBooks[i]){
                        $("#main .top .read button").eq(1).addClass("collection").html("已收藏");
                        }
                    }
            }
            },
            error:function(result)
            {
                
            }
        });
  

    //立即阅读
    $("#main .top .read button").eq(0).click(function(){
        window.location.href="reader.html?book_id="+book_id+"&bookdetails_id="+firstID;
    });

    
        //收藏
        $("#main .top .read button").eq(1).click(function(){
            if(loginstate){
                if($(this).html() == "加入收藏"){
                    for(var i=0;i<collectionBooks.length;i++){
                        if(book_id == collectionBooks[i]){
                            spop({
                                template: '收藏夹已经有此书!',
                                group: 'submit-satus',
                                position  : 'top-center',
                                style: 'error',
                                autoclose: 2000
                            });
                            return;
                        }
                    }
                    collectionBooks.push(book_id);
                    let collection = collectionBooks.join(",");
                    $.ajax({ 
                        url: "User/updateCollection.do", 
                        type:"post",
                        dataType:"json",
                        data:{
                            user_id:user_id,
                            user_collection:collection
                        },
                        success: function(result){     
                            spop({
                                template: '收藏成功!',
                                group: 'submit-satus',
                                position  : 'top-center',
                                style: 'success',
                                autoclose: 2000
                            });
                            $("#main .top .read button").eq(1).addClass("collection").html("已收藏");
                        },
                        error:function(result)
                        {
                            
                        }
                    });
                }else{
                    for(var i=0;i<collectionBooks.length;i++){
                        if(book_id == collectionBooks[i]){
                            collectionBooks.splice(i,1);
                        }else{

                        }
                    }
                    let collection = collectionBooks.join(",");
                    $.ajax({ 
                        url: "User/updateCollection.do", 
                        type:"post",
                        dataType:"json",
                        data:{
                            user_id:user_id,
                            user_collection:collection
                        },
                        success: function(result){     
                            if(result.status == 0){
                                $("#main .top .read button").eq(1).removeClass("collection").html("加入收藏");
                                spop({
                                    template: '取消收藏!',
                                    group: 'submit-satus',
                                    position  : 'top-center',
                                    style: 'success',
                                    autoclose: 2000
                                });
                            }else{
                                spop({
                                    template: '取消收藏失败!',
                                    group: 'submit-satus',
                                    position  : 'top-center',
                                    style: 'error',
                                    autoclose: 2000
                                });
                            }
                            
                        },
                        error:function(result)
                        {
                            
                        }
                    });
                    
                }

            }else{
                spop({
                    template: '请登录后再收藏！',
                    group: 'submit-satus',
                    position  : 'top-center',
                    style: 'error',
                    autoclose: 2000
                });
            }
        });


    /*获取书籍所有章节*/
    $.ajax({ 
        url: "Bookdetails/loadBookdetails.do", 
        type:"post",
        dataType:"json",
        data:{
            "book_id":book_id
        },
        success: function(data){      
            firstID = data.data[0].bookdetails_id;
            for(var i=0;i<data.data.length;i++){
                $("#main .details .middle .tab .box .right .scroll .tab_right").eq(1).append("<a href=\"reader.html?book_id="+book_id+"&bookdetails_id="+data.data[i].bookdetails_id+"\"><span>"+data.data[i].bookdetails_chapter_title+"</span></a>");
            }
        },
        error:function()
        {
            alert("加载推荐失败!");
            //错误处理
        }
    });

    //获取地址栏信息
    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }
});