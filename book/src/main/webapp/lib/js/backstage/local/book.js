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
            elem: '#books' //指定原始表格元素选择器（推荐id选择器）
            ,height: "full-100" //容器高度
            ,method:'post'
            ,toolbar: '#toolbarDemo'
            ,url: '../../../Book/loadBooks.do'  //数据内容
            //,cellMinWidth: 100 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
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
                ,{field: 'book_id', title: 'ID',fixed: 'left',sort: true}
                ,{field: 'book_title', title: '名称' , sort: true}
                ,{field: 'book_img', title: '图片' , sort: true,hide:true}
                ,{field: 'book_author', title: '作者' , sort: true,hide:true}
                ,{field: 'book_explain', title: '简介' ,sort: true}
                ,{field: 'book_type', title: '类型',sort: true}
                ,{field: 'book_count', title: '字数' ,sort: true}
                ,{field: 'book_update_time', title: '更新时间',hide:true}          
                ,{field: 'book_create_time', title: '创建时间', sort: true
                  ,templet: function(d){
                    return createTime(d.book_create_time);
                   }}
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
            var checkStatus = table.checkStatus('books');
            var count = checkStatus.data.length;
            var _list = [];
            var data = obj.data;
            //console.log(obj)
            
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
                            table.reload('readers', {
                                url: '../../../User/loadUser.do'
                                ,where: {user_type:0} //设定异步数据接口的额外参数
                                //,height: 300
                            });
                        },
                        error: function (error)
                        {
                            layer.msg(error.msg);
                        }
                    }) 
                   
                });
            } 
            else if(obj.event === 'edit'){
                index = layer.open({
		        	//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
		            type:1,
		            title:"修改用户信息",
		            area: ['85%','85%'],
		            content:$("#updateBook").html()
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
        popForm.on('submit(updateBook)', function(data){
            var param=data.field;//定义临时变量获取表单提交过来的数据，非json格式
            var books = "{book_id:"+param.book_id+","
                        + "book_title:\""+param.book_title+"\","
                        + "book_img:\""+param.book_img+"\","
                        + "book_author:\""+param.book_author+"\","
                        + "book_explain:\""+param.book_explain.replace(/\s+/g, "<br/>")+"\","
                        + "book_type:\""+param.book_type+"\","
                        + "book_count:"+param.book_count+","
                        + "book_update_time:"+Date.parse(new Date())+"}";
                        
            console.log(books);//测试是否获取到表单数据，调试模式下在页面控制台查看
             $.ajax({
                 url:"../../../Book/updateBook.do",
                 type:'post',//method请求方式，get或者post
                 dataType:'json',//预期服务器返回的数据类型
                 data:{books:books},//表格数据序列化
                 success:function(result){//res为相应体,function为回调函数
                     layer.msg(result.msg);
                     layer.close(index); 
                     table.reload('books', {
                         url: '../../../Book/loadBooks.do'
                         ,where: {} //设定异步数据接口的额外参数
                         //,height: 300
                     });
                 },
                 error:function(error){
                     layer.msg(error.msg);
                 }
             });
            return false;
        });//end form
    });

    //时间转换
    function createTime(v){
        let date = new Date(v);
        let y = date.getFullYear();
        let m = date.getMonth()+1;
        m = m<10?'0'+m:m;
        let d = date.getDate();
        d = d<10?("0"+d):d;
        let h = date.getHours();
        h = h<10?("0"+h):h;
        let M = date.getMinutes();
        M = M<10?("0"+M):M;
        let str = y+"-"+m+"-"+d+" "+h+":"+M;
        return str;
    }

    function setFormValue(data){
        popForm.val("formTestFilter", {
              "book_id":data.book_id 
             ,"book_title":data.book_title
             ,"book_img":data.book_img
             ,"book_author":data.book_author
             ,"book_explain":data.book_explain.replace(/<br\/>/g, " \n ")
             ,"book_type":data.book_type
             ,"book_count":data.book_count
            });
        popForm.render(null,'formTestFilter');
    }
    //数组排序
    function sortNumber(a,b)
    {
    return a - b
    }
   
});