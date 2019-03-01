$(function(){
    var popForm;
    //喜欢类型数组
    var user_like_arr=[];

    layui.use(['laypage','form','table'], function(){
        //表格
        var table = layui.table;
        //表单
        popForm=layui.form;
        //当前层
        var index;
        //分页
        var laypage = layui.laypage;
        //执行渲染
        table.render({
            elem: '#writer' //指定原始表格元素选择器（推荐id选择器）
            ,method:'post'	
            ,height: "full-150" //容器高度
            ,toolbar: '#toolbarDemo'
            ,url: '../../../User/loadUser.do'  //数据内容
            ,where:{user_type:1}
            ,cellMinWidth: 100 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,loading:true
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']//自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                ,groups: 2 //只显示 1 个连续页码
                ,first: "首页" //不显示首页
                ,last: "尾页" //不显示尾页
            }
            ,cols: [[    //设置表头
                {type: 'checkbox', fixed: 'left',unresize: true}
                ,{field: 'user_id', title: 'ID',fixed: 'left',sort: true}
                ,{field: 'user_phone', title: '手机号' , sort: true}
                ,{field: 'user_name', title: '用户名' , sort: true}
                ,{field: 'user_account', title: '账号' , sort: true,hide:true}
                ,{field: 'user_sex', title: '性别' ,sort: true,templet: '#sexTpl'}
                ,{field: 'user_email', title: '邮箱',hide:true}
                ,{field: 'user_like', title: '喜欢'
                    ,templet: function(d){
                        return transformation(d.user_like);
                    }}
                ,{field: 'user_type', title: '身份',templet: '#typeTpl',hide:true}
                ,{field: 'user_city', title: '城市', sort: true}
                ,{field: 'user_Introduce', title: '自我介绍',hide:true}          
                ,{field: 'user_create_time', title: '创建时间', sort: true
                    ,templet: function(d){
                    return createTime(d.user_create_time);
                  }
                }
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:155}
            ]]
            // ,request: {
            //     pageName: 'curr' //页码的参数名称，默认：page
            //     ,limitName: 'nums' //每页数据量的参数名，默认：limit
            // }
            ,parseData: function(res){ //res 即为原始返回的数据
              return {
                "code": res.status, //解析接口状态
                "msg": res.msg, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
              };
            }
            ,done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                // console.log(res);
                //得到当前页码
                // console.log(curr); 
                //得到数据总量
                // console.log(count);
                //提示文本
                // layer.msg(res.msg); 
            }
            //,…… //其他参数
        });


        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选': '未全选');
            break;
            };
        });
    
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var checkStatus = table.checkStatus('writer');
            var count = checkStatus.data.length;
            var _list = [];
            var data = obj.data;
            
            if(obj.event === 'del'){
                //判断选中的长度，如果是0则表示直接删除，如果大于0则表示使用复选框，将选择的数据的id放进list集合中传递到后台处理
                if(count == 0){
                    _list.push(data.user_id);
                }else{
                    for (var i = 0; i < count; i++) {  
                        _list.push(checkStatus.data[i].user_id); 
                        //设置对象的key=>value键值对，即类似于a[0]=0的内容塞入对象_list中，对于后台接收来说，就相当于List内容了
                    } 
                }
                
                layer.confirm(count<=1?'真的删除这条记录吗?':'真的删除这'+count+'条记录吗?', function(index){
                    layer.msg('删除中...', {icon: 16,shade: 0.3,time:3000});
                    //向服务器请求删除记录
                    $.ajax({
                        url: '../../../User/removeUsers.do',
                        type: "post",
                        data:JSON.stringify(_list),
                        contentType:"application/json;charset=utf-8",
                        dataType: "json",
                        success: function (result)
                        {
                            layer.msg(result.msg);
                            table.reload('writer', {
                                url: '../../../User/loadUser.do'
                                ,where: {user_type:1} //设定异步数据接口的额外参数
                                //,height: 300
                            });
                        },
                        error: function (error)
                        {
                            layer.msg(error.msg);
                        }
                    }) ;
                   
                });
            } 
            //更新记录
            else if(obj.event === 'edit'){
                index = layer.open({
		        	//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
		            type:1,
		            title:"修改用户信息",
		            area: ['85%','85%'],
		            content:$("#updateUser").html()
                });
                setFormValue(data);//动态向表单赋值
            }
        });


        //监听复选框事件
        popForm.on('checkbox()', function(data){
            //console.log(data.value); //复选框value值，也可以通过data.elem.value得到
            if(data.elem.checked && user_like_arr.indexOf(data.value) == -1){
                user_like_arr.push(data.value);
            }else{
                var index = user_like_arr.indexOf(data.value);
                user_like_arr.splice(index, 1);
            }
            user_like_arr.sort(sortNumber);
        });

        //监听form表单提交事件
        popForm.on('submit(updateUser)', function(data){
            var param=data.field;//定义临时变量获取表单提交过来的数据，非json格式
            //console.log(param);//测试是否获取到表单数据，调试模式下在页面控制台查看
            
            //将数组转换为字符串
            param.user_like = user_like_arr.join(",");

            $.ajax({
                url:"../../../User/updateUser.do",
                type:'post',//method请求方式，get或者post
                dataType:'json',//预期服务器返回的数据类型
                data:{
                    user_id:param.user_id,
                    user_phone:param.user_phone,
                    user_name:param.user_name,
                    user_account:param.user_account,
                    user_sex:param.user_sex,
                    user_email:param.user_email,
                    user_type:param.user_type,
                    user_like:param.user_like,
                    user_city:param.user_city,
                    user_Introduce:param.user_Introduce
                },//表格数据序列化
                success:function(result){//res为相应体,function为回调函数
                    layer.msg(result.msg);
                    layer.close(index); 
                    table.reload('writer', {
                        url: '../../../User/loadUser.do'
                        ,where: {user_type:1} //设定异步数据接口的额外参数
                        //,height: 300
                    });
                },
                error:function(error){
                    layer.msg(error.msg);
                }
            });
            return false;
        });//end form

        //加载页面后先加载表格的user_like
        layui.jquery.ajax({
            url:"../../json/book_type.json",
            type:'post',//method请求方式，get或者post
            dataType:'json',//预期服务器返回的数据类型
            success:function(result){//res为相应体,function为回调函数
                for(let i=0;i<result.length;i++){
                    $("#user_like").append('<input type="checkbox" name="user_like" value="'+result[i].id +'"title="'+result[i].name+'"/>');
                }
            },
            error:function(error){
                console.log(error);
            }
        });
    });
    
    //时间转换
    function createTime(v){
        var date = new Date(v);
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        m = m<10?'0'+m:m;
        var d = date.getDate();
        d = d<10?("0"+d):d;
        var h = date.getHours();
        h = h<10?("0"+h):h;
        var M = date.getMinutes();
        M = M<10?("0"+M):M;
        var str = y+"-"+m+"-"+d+" "+h+":"+M;
        return str;
    }

   //数字转汉字
    function transformation(v){
        let arr=""
        if(v != null || v != ""){
            arr = v.split(",");
        }
        let str = "";
        for(let i=0;i<arr.length;i++){
            switch(parseInt(arr[i])){
                case 0:
                    str+= turn("玄幻奇幻");
                break;
                case 1:
                    str+= turn("仙侠武侠");
                break;
                case 2:
                    str+= turn("都市小说");
                break;
                case 3:
                    str+= turn("历史军事");
                break;
                case 4:
                    str+= turn("游戏竞技");
                break;
                case 5:
                    str+= turn("科幻末世");
                break;
                case 6:
                    str+= turn("古装言情");
                break;
                case 7:
                    str+= turn("都市言情");
                break;
                case 8:
                    str+= turn("浪漫青春");
                break;
                case 9:
                    str+= turn("幻想言情");
                break;
                case 10:
                    str+= turn("悬疑小说");
                break;
                case 11:
                    str+= turn("情感小说");
                break;
                case 12:
                    str+= turn("二次元");
                break;
            }
        }
        return str;
    }

    function setFormValue(data){
        popForm.val("formTestFilter", {
              "user_id":data.user_id 
             ,"user_phone":data.user_phone
             ,"user_name":data.user_name
             ,"user_account":data.user_account
             ,"user_sex":data.user_sex
             ,"user_email":data.user_email
             ,"user_type":data.user_type
             ,"user_like":data.user_like
             ,"user_city":data.user_city
             ,"user_Introduce":data.user_Introduce
            });
        //判断男女单项
        $("input[name=user_sex][value=0]").attr("checked", data.user_sex == 0 ? true : false);
        $("input[name=user_sex][value=1]").attr("checked", data.user_sex == 1 ? true : false);
        //判断身份
        $("input[name=user_type][value=0]").attr("checked", data.user_type == 0 ? true : false);
        $("input[name=user_type][value=1]").attr("checked", data.user_type == 1 ? true : false);
        //判断爱好
        if(data.user_like!="" && data.user_like!=null){
            var like = data.user_like.split(",");
            user_like_arr = like.slice().sort(sortNumber);
            
            //先将之前的所有框清除选中
            for(var i=0;i<$("#user_like input").length;i++){
                $("#user_like input").eq(i).prop("checked", false);
                $("#user_like input").eq(i).next().removeClass("layui-form-checked");
            }
            if(like.length > 0 && like != "" && like != null){
                for(var i=0;i<like.length;i++){
                    $(".layui-layer-page input[name=user_like][value="+like[i]+"]").prop("checked", true);
                    $(".layui-layer-page input[name=user_like][value="+like[i]+"]").next().addClass("layui-form-checked");
                }
            }
        }
        popForm.render(null,'formTestFilter');
    }

    //数组排序
    function sortNumber(a,b)
    {
    return a - b
    }

    //定义user_like样式
    function turn(s){
        return "<span class='layui-word-aux'>"+s+"</span>";
    }

});