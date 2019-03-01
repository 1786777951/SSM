//登录状态
let loginstate = false;

//个人信息
var i = 0;
$("#main .left_list li").click(function(){
    i = $(this).index();
    showinfo(i);
});
//显示右边的信息
let showinfo = (i) =>{
    $("#main .left_list ul li").eq(i-1).css("background-color","#ddd").siblings("li").css("background-color","#eee");
    $("#main .right_form .right_content").eq(i-1).css("display","block").siblings(".right_content").css("display","none");
}

//获取json数据
    $.ajax({
        url:"lib/json/book_type.json",
        type:'post',//method请求方式，get或者post
        dataType:'json',//预期服务器返回的数据类型
        success:function(result){//res为相应体,function为回调函数
            for(var i=0;i<result.length;i++){
                $("#likecheck").append("<label class=\"checkbox-inline\"><input type=\"checkbox\" id=\"checkbox"+i+"\" value=\""+result[i].id+"\">"+ result[i].name+"</label>");
            }
        },
        error:function(error){
            console.log(error);
        }
    })

var user_id;
var user_type;
var user_collection;
var _list = [];
//获取用户信息
$.ajax({ 
    url: "User/loadUserById.do",
    type:"post",
    dataType:"json",
    data:{},
    success: function(result){
        if(result.status == 0){
            loginstate = true;
            user_id = result.data.user_id;
            user_type =  result.data.user_type;
            user_collection = result.data.user_collection;
            $("#user_phone").val(result.data.user_phone);
            $("#user_name").val(result.data.user_name);
            $("#user_email").val(result.data.user_email);
            $("#user_city").val(result.data.user_city);
            $("#user_introduce").val(result.data.user_introduce);
            $("#upload").attr("src","./lib/images/"+result.data.user_img_id);
            if(result.data.user_sex == 0){
                $("#user_sex1").prop("checked", true);
            }else{
                $("#user_sex2").prop("checked", true);
            }
            if((result.data.user_like.indexOf(",") != -1) && result.data.user_like.length>1){
                let like = result.data.user_like.split(",");
                for(var i=0;i<like.length;i++){
                    $("#likecheck label").eq(like[i]).find("input").prop("checked",true);
                }
            }else if(result.data.user_like.length == 1){
                let like = result.data.user_like;
                $("#likecheck label").eq(like[0]).find("input").prop("checked",true);
            }else{
                
            }

            //将收藏字符串类型改为list
            if(user_collection==null){
                return;
            }
            _list = user_collection.split(",");
            //查询书架
            $.ajax({ 
                url: "Book/loadBookByIds.do",
                type:"post",
                dataType:"json",
                data:JSON.stringify(_list),
                contentType:"application/json;charset=utf-8",
                success: function(result){      
                    if(result.status == 0){
                        for(var i=0;i<result.data.length;i++){
                            $(".bookshelf").append("<div class=\"books\">"
                                                   +" <div class=\"book_img\">"
                                                   +"     <img src=\"./lib/images/"+result.data[i].book_img+"\" alt=\"小说图片\">"
                                                   +" </div>"
                                                   +" <div class=\"book_info\">"
                                                   +"     <h3 class=\"book_title\">"+result.data[i].book_title+"</h3>"
                                                   +"     <h5 class=\"update_time\">"+result.data[i].book_update_time+"</h5>"
                                                   +" </div>"
                                                   +" <div class=\"reader\">"
                                                   +"     <button class=\"btn btn-info\">继续阅读</button>"
                                                   +"     <button class=\"btn btn-danger\" onclick=\"Nocollection("+result.data[i].book_id+")\">取消收藏</button>"
                                                   +" </div>"
                                                   +"</div>");
                        }
                    }
                },
                error:function(result)
                {
                    
                } 
            });
        }else{
            spop({
                template: '请登录！',
                group: 'submit-satus',
                position  : 'top-center',
                style: 'error',
                autoclose: 1000,
                onClose: function() {
                    window.location.href="index.html";
                }
            });
        }
    },
    error:function(result)
    {
        
    } 
});


//取消收藏
let Nocollection = (book_id) =>{
    let collection = user_collection.split(",");
    for(var i=0;i<collection.length;i++){
        if(book_id == collection[i]){
            collection.splice(i,1);
        }else{
            
        }
    }
    
    //ajax发送请求
    $.ajax({ 
        url: "User/updateCollection.do", 
        type:"post",
        dataType:"json",
        data:{
            user_id:user_id,
            user_collection:collection.join(",")
        },
        success: function(result){     
            if(result.status == 0){
                spop({
                    template: '取消收藏!',
                    group: 'submit-satus',
                    position  : 'top-center',
                    style: 'success',
                    autoclose: 2000,
                    onClose: function() {
                        location.reload();
                    }
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


$("#update").click(function(){
    let a = 0;
    let likeArr = [];
    $("#likecheck label").find('input:checkbox').each(function() { //遍历所有复选框
          if ($(this).prop('checked') == true) {
            likeArr.push($(this).val());
          }
    });
    //数组转换字符串
    let like = likeArr.join(",");
    //用户修改信息
    $.ajax({ 
        url: "User/updateUser.do",
        type:"post",
        dataType:"json",
        data:{
            user_id:user_id,
            user_phone:$("#user_phone").val(),
            user_name:$("#user_name").val(),
            user_account:$("#user_account").val(),
            user_sex:$('input:radio[name="user_sex"]:checked').val(),
            user_email:$("#user_email").val(),
            user_type:user_type,
            user_city:$("#user_city").val(),
            user_like:like,
            user_Introduce:$("#user_introduce").val()
        },
        success: function(result){      
            if(result.status == 0){
                spop({
                    template: '修改成功!',
                    group: 'submit-satus',
                    position  : 'top-center',
                    style: 'success',
                    autoclose: 2000,
                    onClose: function() {
                        window.location.href="userinfo.html";
                    }
                });
            }else{
                spop({
                    template: '修改失败，请重新修改！',
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
})

//修改头像
$('#upload').on('click', function() {
    $("#fileToUpload").click();
});

$("#fileToUpload").change(function(){
    var myform = new FormData(document.getElementById("myform"));
    if("undefined" != typeof(myform) && myform != null && myform != ""){
        $.ajax({
            url: "User/uploader.do",
            type: "post",
            data:myform,
            async: false,  
            cache: false, 
            contentType: false, //不设置内容类型
            processData: false, //不处理数据
            success: function(result) {
                if($.parseJSON(result).res == 1){
                    $.ajax({ 
                        url: "User/registerUserImg.do",
                        type:"post",
                        dataType:"json",
                        data:{
                            user_id:user_id,
                            user_img:$.parseJSON(result).user_img
                        },
                        success: function(result){      
                            if(result.status == 0){
                                spop({
                                    template: '修改成功!',
                                    group: 'submit-satus',
                                    position  : 'top-center',
                                    style: 'success',
                                    autoclose: 2000,
                                    onClose: function() {
                                        window.location.href="userinfo.html";
                                    }
                                });
                            }else{
                                spop({
                                    template: '修改失败，请重新修改！',
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
                }else{
                    spop({
                        template: '修改失败，请重新修改！',
                        group: 'submit-satus',
                        position  : 'top-center',
                        style: 'error',
                        autoclose: 2000
                    });
                }

            },
            error:function(result){
                console.log(result);
            }
        });
    }else{
        console.log("选择的文件无效！请重新选择");
    }
})