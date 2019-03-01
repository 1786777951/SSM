$(function(){
    var book_id=GetQueryString("book_id");
    var bookdetails_id=GetQueryString("bookdetails_id");

    if(book_id !=null && book_id.toString().length>0)
    { 
        $.ajax({
            url: 'Bookdetails/loadBookdetails_content.do',
            type: "post",
            data:{
                "book_id":book_id,
                "bookdetails_id":bookdetails_id
            },
            dataType: "json",
            success: function (result)
            {
                console.log(result);
                let bookdetails_content = "<p>"+result.data[0].bookdetails_content.replace(/\s+/g, "</p><p>")+"</p>";
                $("#main .details .title h2").html(result.data[0].bookdetails_chapter_title);
                $("#main .details .title h4").html(result.data[0].bookdetails_update_time);
                $("#main .details .content").html(bookdetails_content);
            },
            error: function (error)
            {
                console.log(error);
            }
        });
    }else{
        console.log(book_id);
    }
    /*获取书籍所有章节*/
    $.ajax({ 
        url: "Bookdetails/loadBookdetails.do", 
        type:"post",
        dataType:"json",
        data:{
            "book_id":book_id
        },
        success: function(data){      
            console.log(data);
            for(var i=0;i<data.data.length;i++){
                if(data.data[i].bookdetails_id == bookdetails_id) {
                    if(data.data[i-1]!=null){
                        $("#main .details .next a").eq(0).attr("href","reader.html?book_id="+book_id+"&bookdetails_id="+data.data[i-1].bookdetails_id);
                    }else {
                        $("#main .details .next a").eq(0).attr("href","details.html?book_id="+data.data[i].book_id);
                    }
                    
                    $("#main .details .next a").eq(1).attr("href","details.html?book_id="+data.data[i].book_id);  
                    
                    if(data.data[i+1]!=null){
                        $("#main .details .next a").eq(2).attr("href","reader.html?book_id="+book_id+"&bookdetails_id="+data.data[i+1].bookdetails_id);
                    }else{
                        $("#main .details .next a").eq(2).attr("href","details.html?book_id="+data.data[i].book_id);
                    }
                    
                    
                }
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