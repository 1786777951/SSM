$(document).ready(function() {
    let Acode = "";
    //验证码插件
    $.fn.code_Obj = function (o) {
        var _this = $(this);
        var options = {
            code_l: o.codeLength,//验证码长度
            codeChars: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            ],
            codeColors: ['#f44336', '#009688', '#cddc39', '#03a9f4', '#9c27b0', '#5e4444', '#9ebf9f', '#ffc8c4', '#2b4754', '#b4ced9', '#835f53', '#aa677e'],
            code_Init: function () {
                var code = "";
                var codeColor = "";
                var checkCode = _this.find("#data_code");
                for (var i = 0; i < this.code_l; i++) {
                    var charNum = Math.floor(Math.random() * 52);
                    code += this.codeChars[charNum];
                }
                for (var i = 0; i < this.codeColors.length; i++) {
                    var charNum = Math.floor(Math.random() * 12);
                    codeColor = this.codeColors[charNum];
                }
                Acode = code.toLowerCase();
                if (checkCode) {
                    checkCode.css('color', codeColor);
                    checkCode.className = "code";
                    checkCode.text(code);
                    checkCode.attr('data-value', code);
                }
            }
        };

        options.code_Init();/*初始化验证码*/
        _this.find("#data_code").bind('click', function () {
            options.code_Init();
        });
    };

    $("#header").on("click","#searchBooks",function(){
        let book_title = $("#booksTitle").val();
        window.location.replace("search.html?book_title="+book_title);
    });

    /*登录 事件委托*/
    $("#header").on("click","#loginInHeader",function(){
        let user_login = $("#user_login").val();
        let user_password = $("#user_password").val();
        let type = "";
        let user_phone = "";
        let user_account = "";
        let user_email = "";
        let admin_account = "";
        let admin_password = "";
        //手机号
        let regPhone = /^1[34578]\d{9}$/;
        //邮箱
        let regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        //验证码
        let code = $("#code").val().toLowerCase();

        //判断
        if(user_login == "" || user_login == null){
            spop({
                template: '请输入用户名|手机号|邮箱',
                group: 'submit-satus',
                position  : 'top-center',
                style: 'error'
            });
            return "";
        }else if(user_password =="" || user_password == null){
            spop({
                template: '请输入密码',
                group: 'submit-satus',
                position  : 'top-center',
                style: 'error',
                autoclose: 2000
            });
            return "";
        }else if(code =="" || code == null){
            spop({
                template: '请输入验证码',
                group: 'submit-satus',
                position  : 'top-center',
                style: 'error',
                autoclose: 2000
            });
            return "";
        }else if(code != Acode){
            spop({
                template: '验证码错误',
                group: 'submit-satus',
                position  : 'top-center',
                style: 'error',
                autoclose: 2000
            });
            return "";
        }else{
             //如果是手机号
            if(regPhone.test(user_login)){
                user_phone = user_login;
                type="phone";
            }else if(regEmail.test(user_login)){
                user_email = user_login;
                type="email";
            }else{
                user_account = user_login;
                type="account";
            }
        }

        if(user_login == "admin"){
            $.ajax({ 
                url: "Admin/findAdminAccount.do", 
                type:"post",
                dataType:"json",
                data:{
                    admin_account:user_login,
                    admin_password:user_password
                },
                success: function(data){     
                    if(data.status == 0){
                        spop({
                            template: '登录成功',
                            group: 'submit-satus',
                            position  : 'top-center',
                            style: 'success',
                            autoclose: 1000,
                            onClose: function() {
                                window.location.href="index.html";
                            }
                        });
                    }else{
                        spop({
                            template: '输入错误！',
                            group: 'submit-satus',
                            position  : 'top-center',
                            style: 'error',
                            autoclose: 2000
                        });
                    }
                },
                error:function(data)
                {
                    spop({
                        template: '登录失败请重试！',
                        group: 'submit-satus',
                        position  : 'top-center',
                        style: 'error',
                        autoclose: 2000
                    });
                    //错误处理
                }
            });
        }else{
            $.ajax({ 
                url: "User/loginUser.do", 
                type:"post",
                dataType:"json",
                data:{
                    user_phone:user_phone,
                    user_account:user_account,
                    user_email:user_email,
                    user_password:user_password,
                    type:type
                },
                success: function(data){
                    if(data.status == 0){
                        spop({
                            template: '登录成功',
                            group: 'submit-satus',
                            position  : 'top-center',
                            style: 'success',
                            autoclose: 1000,
                            onClose: function() {
                                window.location.href="index.html";
                            }
                        });
                    }else{
                        spop({
                            template: '输入错误！',
                            group: 'submit-satus',
                            position  : 'top-center',
                            style: 'error',
                            autoclose: 2000
                        });
                    }
                    
                },
                error:function(data)
                {
                    spop({
                        template: '登录失败请重试！',
                        group: 'submit-satus',
                        position  : 'top-center',
                        style: 'error',
                        autoclose: 2000
                    });
                    //错误处理
                }
            });
        }

        
    });
    
    

    /**
         * 验证码
         * codeLength:验证码长度
    */
    $('#check-code').code_Obj({
        codeLength: 5
    });
});

