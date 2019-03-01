$(function(){
    layui.use('table', function(){
        //表格
        var table = layui.table;
        //执行渲染
        table.render({
            elem: '#choreographer' //指定原始表格元素选择器（推荐id选择器）
            // ,height: 1000 //容器高度
            ,toolbar: '#toolbarDemo'
            // ,url: ''  //数据内容
            ,cellMinWidth: 100 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                ,groups: 1 //只显示 1 个连续页码
                ,first: false //不显示首页
                ,last: false //不显示尾页
            }
            ,cols: [[    //设置表头
                {type: 'checkbox', fixed: 'left',unresize: true}
                ,{field: 'user_id', title: 'ID',fixed: 'left',sort: true}
                ,{field: 'user_name', title: '用户名' , sort: true}
                ,{field: 'user_sex', title: '性别' ,sort: true,templet: '#sexTpl'}
                ,{field: 'user_email', title: '邮箱'}
                ,{field: 'user_type', title: '身份', sort: true}
                ,{field: 'user_img_id', title: '图像'} 
                ,{field: 'user_Introduce', title: '自我介绍'}
                ,{field: 'user_create_time', title: '创建时间', sort: true}
                ,{field: 'user_city', title: '城市', sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,data: [{
                "user_id": "10001"
                ,"user_name": "杜甫"
                ,"user_email": "xianxin@layui.com"
                ,"user_sex": "男"
                ,"user_city": "浙江杭州"
                ,"user_Introduce": "人生恰似一场修行"
                ,"user_create_time": "2016-10-14"
            }, {
                "user_id": "10001"
                ,"user_name": "杜甫"
                ,"user_email": "xianxin@layui.com"
                ,"user_sex": "男"
                ,"user_city": "浙江杭州"
                ,"user_Introduce": "人生恰似一场修行"
                ,"user_create_time": "2016-10-14"
            }]
            // ,request: {
            //     pageName: 'curr' //页码的参数名称，默认：page
            //     ,limitName: 'nums' //每页数据量的参数名，默认：limit
            // }
            // ,parseData: function(res){ //res 即为原始返回的数据
            //   return {
            //     "code": res.status, //解析接口状态
            //     "msg": res.message, //解析提示文本
            //     "count": res.total, //解析数据长度
            //     "data": res.data.item //解析数据列表
            //   };
            // }
            // ,done: function(res, curr, count){
            //     //如果是异步请求数据方式，res即为你接口返回的信息。
            //     //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            //     console.log(res);
                
            //     //得到当前页码
            //     console.log(curr); 
                
            //     //得到数据总量
            //     console.log(count);
            // }
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
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
            });
            } else if(obj.event === 'edit'){
            layer.prompt({
                formType: 2
                ,value: data.email
            }, function(value, index){
                obj.update({
                email: value
                });
                layer.close(index);
            });
            }
        });

    });



});