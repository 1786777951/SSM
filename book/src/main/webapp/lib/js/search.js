    // 调用方法
    //获取路径上的书名、书类型
    let book_type = GetQueryString("book_type");
    let book_title = decodeURI(GetQueryString("book_title"));
    //搜索框里保留书名
    $("#SearchBooks").val(book_title);
    //切换视图时变化搜索数量
    let grid_limit = 10;
    let list_limit = 25;
    //获取书本字数范围
    let record = 0;
    //字数
    let wordCount = 0;
    let wordCounts = 100000000;
    //默认页为1，数量10条
    let page = 1;
    let limit = grid_limit;
    //搜索页切换网格与表格视图
    var grid = true;
    var list = false;
    $("#grid").click(function(){
        if(grid){
        }else{
            $("#book_grid").css("display","block");
            $("#book_list").css("display","none");
            grid = true;
            list = false;
            page = 1;
            limit = grid_limit;
            getAjax($("#SearchBooks").val(),page,limit,wordCount,wordCounts,record);
        }
    });
    $("#list").click(function(){
        if(list){
        }else{
            $("#book_grid").css("display","none");
            $("#book_list").css("display","block");
            grid = false;
            list = true;
            page = 1;
            limit = list_limit;
            getAjax($("#SearchBooks").val(),page,limit,wordCount,wordCounts,record);
        }
    });

    //所有页面的搜索小说
    $(function(){
        getAjax($("#SearchBooks").val(),page,limit,wordCount,wordCounts,record);
    });
    

    //搜索页里的搜索
    $("#main").on("click","#SearchinBooks",function(){
        page = 1;
        getAjax($("#SearchBooks").val(),page,limit,wordCount,wordCounts,record);
    });

     //上一页分页下一页
     $("#main").on("click",".middle .paging nav ul li",function(){
        if($(this).hasClass("clickPage")){
            page = $(this).clone().find(':nth-child(1)').remove().html();
        }else if($(this).hasClass("prew")){
            page-=1;
        }else if($(this).hasClass("next")){
            page+=1;
        }
        getAjax($("#SearchBooks").val(),page,limit,wordCount,wordCounts);
        $('html,body').animate({scrollTop: ($(".right").offset().top -75 )},500);
    });


    //列表的分类
    $("#main").on("click",".classification li",function(){
        book_type=$(this).html()=="全部"?" ":$(this).html();
        getAjax(book_type,1,limit,wordCount,wordCounts);
        $(this).addClass("checkedBook").siblings("li").removeClass("checkedBook");
    })

    //列表的字数
    $("#main").on("click",".wordNumber li",function(){
        let index = $(this).index();
        switch(index){
            case 1:
            wordCount = 0;
            wordCounts = 10000000;
            break;
            case 2:
            wordCount = 0;
            wordCounts = 300000;
            break;
            case 3:
            wordCount = 300000;
            wordCounts = 500000;
            break;
            case 4:
            wordCount = 500000;
            wordCounts = 1000000;
            break;
            case 5:
            wordCount =1000000;
            wordCounts = 2000000;
            break;
            case 6:
            wordCount = 2000000;
            wordCounts = 10000000;
            break;
            default:
            break;
        }
        getAjax(book_type,1,limit,wordCount,wordCounts,record);
        $(this).addClass("checkedBook").siblings("li").removeClass("checkedBook");
    })

    //更新时间
    $("#main").on("click",".updateTime li",function(){
        let index = $(this).html();
        switch(index){
            case "全部":
            record = 0;
            break;
            case "三日内":
            record = 3;
            break;
            case "七日内":
            record = 7;
            break;
            case "半月内":
            record = 15;
            break;
            case "一个月内":
            record = 30;
            break;
            case "六个月内":
            record = 180;
            break;
            default:
            break;
        }
        getAjax(book_type,1,limit,wordCount,wordCounts,record);
        $(this).addClass("checkedBook").siblings("li").removeClass("checkedBook");
    })

    //获取地址栏信息
    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  decodeURI(r[2]); return "";
    }

    //ajax请求
    let getAjax = (book_title,page,limit,wordCount,wordCounts,record) =>{
        $.ajax({
            url: 'Book/loadBooksByTitle.do',
            type: "post",
            data:{
                "book_title":book_title,
                "page":page,
                "limit":limit,
                "wordCount":wordCount,
                "wordCounts":wordCounts,
                "record":record
            },
            dataType: "json",
            
            success: function (result)
            {console.log(result);
                //填充数据
                setResult(result);
            },
            error: function (error)
            {
                console.log(error);
            }
        });
    }

    //填充数据函数
    let setResult =(result) =>{
        //获取查询后的所有记录数
        $("#main .middle .right .sort p .count").html(result.count);
        let allpage = Math.ceil(result.count/limit);
        //先清空数据
        $("#main .book #book_grid").html("");
        $("#main .book #book_list tbody").html("");
        $("#main .paging nav ul").html("");
        //如果没有数据则直接返回
        if(result.count <= 0){
            $("#main .book #book_grid").append("没有查询到相关书籍!");
            $("#main .book #book_list tbody").append("没有查询到相关书籍!");
            return;
        }
        
        //查询到数据将其填充到表格和列表
        for(var i = 0;i<result.data.length;i++){
            let book_img = result.data[i].book_img;
            let book_title = result.data[i].book_title;
            let book_id = result.data[i].book_id;
            let book_author = result.data[i].book_author;
            let book_type = result.data[i].book_type;
            let book_explain = result.data[i].book_explain;
            let book_count = result.data[i].book_count;
            let book_update_time = result.data[i].book_update_time;

            $("#main .book #book_grid").append("<div class=\"grid\">"
                                                +"<img src=\"lib/images/"+book_img+"\" alt=\"图片\">"
                                                +"<div class=\"grid_word\">"
                                                +"    <h4><a href=\"details.html?book_id="+book_id+"\">"+book_title+"</a></h4>"
                                                +"     <div class=\"author\">"
                                                +"         <span><a href=\"#\">"+book_author+"</a></span>"
                                                +"         <p> "
                                                +"             <span>类型 · <a href=\"#\">"+book_type+"</a></span> | "
                                                +"             <span>状态</span>"
                                                +"         </p>"
                                                +"     </div>"
                                                +"     <p class=\"introduction\">"+book_explain+"</p>"
                                                +"     <p class=\"word_number\">"+book_count+"字</p>"
                                                +" </div>"
                                                +" </div>");
            $("#main .book #book_list tbody").append("<tr>"
                                                +"     <td>"+book_type+"</td>"
                                                +"     <td><a href=\"details.html?book_id="+book_id+"\">"+book_title+"</a></td>"
                                                +"     <td>最新章节</td>"
                                                +"     <td>"+book_count+"</td>"
                                                +"     <td>"+book_author+"</td>"
                                                +"     <td>"+book_update_time+"</td>"
                                                +"   </tr>");
        }
        let ulDom = $("#main .paging nav ul");
        //分页的填入
        //上一页
        if(page == 1){
            ulDom.append("<li class=\"disabled\"><span><span aria-hidden=\"true\">&laquo;</span></span></li>");
        }else{
            ulDom.append("<li class=\"prew\"><span><span aria-hidden=\"true\">&laquo;</span></span></li>");
        }
        //中间的页数
        //当页数大于5时
        if(allpage > 5){
            for(var i=1;i<=5;i++){
                //当页前不足2页
                if(page - 2 == 0 || page - 1 ==0){
                    if(page == i){
                        ulDom.append("<li class=\"active\"><span>"+i+"<span class=\"sr-only\">(current)</span></span></li>");
                    }else{
                        ulDom.append("<li class=\"clickPage\"><span>"+i+"<span class=\"sr-only\">(current)</span></span></li>");
                    }
                //当页后不足2页
                }else if((allpage-page == 0) || (allpage-page==1) ){ 
                    //当最后一页
                    if((allpage-page) == 0 && (i==5)){ 
                        ulDom.append("<li class=\"active\"><span>"+(page-5+i)+"<span class=\"sr-only\">(current)</span></span></li>");
                    //倒数第二页
                    }else if((allpage-page) == 1 && (i==4)){
                        ulDom.append("<li class=\"active\"><span>"+(page-4+i)+"<span class=\"sr-only\">(current)</span></span></li>");
                    }else{
                        if((allpage-page) == 1){
                            ulDom.append("<li class=\"clickPage\"><span>"+(page-4+i)+"<span class=\"sr-only\">(current)</span></span></li>");
                        }else{
                            ulDom.append("<li class=\"clickPage\"><span>"+(page-5+i)+"<span class=\"sr-only\">(current)</span></span></li>");
                        }
                       
                    }
                //当在中间时
                }else{
                    if(i == 3){
                        ulDom.append("<li class=\"active\"><span>"+(page-3+i)+"<span class=\"sr-only\">(current)</span></span></li>");
                    }else{
                        ulDom.append("<li class=\"clickPage\"><span>"+(page-3+i)+"<span class=\"sr-only\">(current)</span></span></li>");
                    }
                }
            }
            
        //当页数小于5
        }else{
            for(var i=1;i<=allpage;i++){
                if(page == i){
                    ulDom.append("<li class=\"active\"><span>"+i+"<span class=\"sr-only\">(current)</span></span></li>");
                }else{
                    ulDom.append("<li class=\"clickPage\"><span>"+i+"<span class=\"sr-only\">(current)</span></span></li>");
                }
                
            }
        }
                 
        //下一页
        if(page == allpage){
            ulDom.append("<li class=\"disabled\"><span><span aria-hidden=\"true\">&raquo;</span></span></li>");
        }else{
            ulDom.append("<li class=\"next\"><span><span aria-hidden=\"true\">&raquo;</span></span></li>");
        }
    }
