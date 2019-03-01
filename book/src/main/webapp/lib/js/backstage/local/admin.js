$(function(){
    layui.use('table', function(){
        //表格
        var table = layui.table;
        //执行渲染
        
        table.render({
            elem: '#admin' //指定原始表格元素选择器（推荐id选择器）
            // ,height: 1000 //容器高度
            ,toolbar: '#toolbarDemo'
            ,url: '../../../Admin/loadAdmin.do'  //数据内容
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
                ,{field: 'admin_id', title: 'ID',fixed: 'left',sort: true}
                ,{field: 'admin_name', title: '用户名' , sort: true}
                ,{field: 'admin_password', title: '密码' }
                ,{field: 'admin_level', title: '等级'}
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
                "data": res.data//解析数据列表
              };
            }
            ,done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);
                //得到当前页码
                console.log(curr);         
                //得到数据总量
                console.log(count);
                //提示文本
                layer.msg(res.msg); 
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