//设置当前是哪个输入框
var i = 0;
//提示的文字
var tip = "";
$(function(){
const button = document.querySelector('.button');
const submit = document.querySelector('.submit');
    //表单验证
    //手机号
    var formPhone =false;
    $("#user_phone").change(function(){
        i = 0;
        var phone = $.trim(this.value);
        var regPhone = /^1[34578]\d{9}$/;
        if(phone == ""){
            tips("不能为空!");
            error(i);
            show_tip();
            formPhone = false;
        }else if(!regPhone.test(phone)){
            tips("请填入正确的手机号!");
            error(i);
            show_tip();
            formPhone = false;
        }else{
            formPhone = true;
            hide_tip();
            success_ok(i);
        }
    });

    //账号
    var formAccount =false;
    $("#user_account").change(function(){
        i = 1;
        var account = $.trim(this.value);
        if(account == ""){
            tips("不能为空!");
            error(i);
            show_tip();
            formAccount = false;
        }else{
            formAccount = true;
            hide_tip();
            success_ok(i);
        }
    });

    //用户名
    var formName =false;
    $("#user_name").change(function(){
        i = 2;
        var name = $.trim(this.value);
        var regName = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
        if(name == ""){
            tips("不能为空!");
            error(i);
            show_tip();
            formName = false;
        }else if(!regName.test(name)){
            tips("字母开头,5~16位,允许字母数字下划线");
            error(i);
            formName = false;
            show_tip();
        }else{
            formName = true;
            hide_tip();
            success_ok(i);
        }
    });

    //密码
    var formPass =false;
    $("#user_pass").change(function(){
        i = 3;
        var pass = $.trim(this.value);
        if(pass == ""){
            tips("不能为空!");
            error(i);
            show_tip();
            formPass = false;
        }else if(pass.length<6 || pass.length >13){
            tips("密码长度为6~13位!");
            error(i);
            show_tip();
            formPass = false;
        }else{
            formPass = true;
            hide_tip();
            success_ok(i);
        }
    });

    //检查密码
    var formPass2 =false;
    $("#user_pass2").change(function(){
        i = 4;
        var pass2 = $.trim(this.value);
        if(pass2 != $.trim($("#user_pass").val())){
            tips("两次密码不相同！");
            error(i);
            show_tip();
            formPass2 = false;
        }else{
            formPass2 = true;
            hide_tip();
            success_ok(i);
        }
    });

    //检查邮箱
    var formEmail =false;
    $("#user_email").change(function(){
        i = 5;
        var email = $.trim(this.value);
        var regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if(email == ""){
            tips("不能为空");
            error(i);
            show_tip();
            formEmail = false;
        }else if(!regEmail.test(email)){
            tips("邮箱格式错误!");
            error(i);
            formEmail = false;
            show_tip();
        }else{
            formEmail = true;
            hide_tip();
            success_ok(i);
        }
    });

    //检查城市
    var formCity =false;
    $("#user_city").change(function(){
        i = 6;
        var city = $.trim(this.value);
        if(city == ""){
            tips("不能为空");
            error(i);
            show_tip();
            formCity = false;
        }else{
            formCity = true;
            hide_tip();
            success_ok(i);
        }
    });

    //注册按钮
    button.addEventListener('click',function(){
        if(formPhone && formAccount && formName && formPass && formPass2 && formEmail && formCity){
            this.classList.toggle('active');
        }else{
            alert("请填入");
        }
        
    });
    button.addEventListener('transitionend', function(){
        this.classList.toggle('active');
    });
    button.addEventListener('transitionend', function(){
        this.classList.add('finished');
        $.ajax({ 
            url: "../../User/registerUser.do", 
            type:"post",
            dataType:"json",
            data:{
                "user_phone":parseInt($.trim($("#user_phone").val())),
                "user_name":$.trim($("#user_name").val()),
                "user_account":$.trim($("#user_account").val()),
                "user_password":$.trim($("#user_pass").val()),
                "user_sex":$.trim($("[name='user_sex']").val()),
                "user_email":$.trim($("#user_email").val()),
                "user_city":$.trim($("#user_city").val()),
                "user_Introduce":$.trim($("#user_Introduce").val()),
            },
            success: function(data){
                alert("注册成功!");
                window.location = "../../index.html";
            },
            error:function()
            {
                alert("注册失败!");
                //错误处理
            }
        }); 
    });
    

});

//成功
function success_ok(i){
    $("#main form .form-group").eq(i).removeClass("has-error").addClass("has-success");
    $("#main form .form-group").eq(i).find(".glyphicon-remove").removeClass("glyphicon-remove").addClass("glyphicon-ok");
}

//失败
function error(i){
    $("#main form .form-group").eq(i).addClass("has-error").removeClass("has-success");
    $("#main form .form-group").eq(i).find(".glyphicon-ok").addClass("glyphicon-remove").removeClass("glyphicon-ok");
}

//提示
function tips(tip){
    $("#main form .form-group").eq(i).find(".tips").html(tip);
}
//显示
function show_tip(){
    $("#main form .form-group").eq(i).find(".tips").show();
}
//隐藏
function hide_tip(){
    $("#main form .form-group").eq(i).find(".tips").hide();
}