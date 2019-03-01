//获取全部书籍
$.ajax({ 
    url: "../../Book/loadBook.do", 
    type:"post",
    dataType:"json",
    data:{},
    success: function(result){ 
        if(result.status == 0){
            $(".index_top .card_body").eq(0).find("h3").html(result.data.length);
        }else{
            $(".index_top .card_body").eq(0).find("h3").html("获取失败！");
        }
    },
    error:function(result)
    {
        console.log(result);
    }
});

//获取全部书籍
$.ajax({ 
    url: "../../Book/loadBookDay.do", 
    type:"post",
    dataType:"json",
    data:{},
    success: function(result){ 
        if(result.status == 0){
            $(".index_top .card_body").eq(1).find("h3").html(result.data.length);
        }else{
            $(".index_top .card_body").eq(1).find("h3").html("获取失败！");
        }
    },
    error:function(result)
    {
        console.log(result);
    }
});


//获取全部用户
$.ajax({ 
    url: "../../User/loadUserAll.do", 
    type:"post",
    dataType:"json",
    data:{},
    success: function(result){ 
        if(result.status == 0){
            $(".index_top .card_body").eq(2).find("h3").html(result.data.length);
        }else{
            $(".index_top .card_body").eq(2).find("h3").html("获取失败！");
        }
    },
    error:function(result)
    {
        console.log(result);
    }
});

//获取全部用户
$.ajax({ 
    url: "../../User/loadUserReader.do", 
    type:"post",
    dataType:"json",
    data:{
        user_type:0
    },
    success: function(result){ 
        if(result.status == 0){
            $(".index_top .card_body").eq(3).find("h3").html(result.data.length);
        }else{
            $(".index_top .card_body").eq(3).find("h3").html("获取失败！");
        }
    },
    error:function(result)
    {
        console.log(result);
    }
});

//图表
 // 基于准备好的dom，初始化echarts实例
 var myBook = echarts.init($("#books")[0]);
 var myUser = echarts.init($("#users")[0]);
 // 指定图表的配置项和数据
 //书
 myBook.setOption({
    title: {
        text: '小说',
        left: 'center',
        top: 20,
        textStyle: {
            color: '#ccc'
        }
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        textStyle:{//图例文字的样式
            color:'#fff',
            fontSize:16
        },
        left: 'left',
        data: []
    },
    series : [
        {
            name:'小说类型',
            type:'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:[],
            roseType: 'radius',
            label: {
                normal: {
                    textStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: 'rgba(255, 255, 255, 1)'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            itemStyle: {
                normal: {
                    color: function (value){return "#"+("00000"+((Math.random()*16777215+0.5)>>0).toString(16)).slice(-6);},
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },
            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
                return Math.random() * 200;
            }
        }
    ]
 });
 //用户
 myUser.setOption({
    title: {
        text: '用户',
        left: 'center',
        top: 20,
        textStyle: {
            color: '#ccc'
        }
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        textStyle:{//图例文字的样式
            color:'#fff',
            fontSize:16
        },
        color: "#fff",
        data: []
    },
    series : [
        {
            name:'用户类型',
            type:'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:[],
            roseType: 'radius',
            label: {
                normal: {
                    textStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: 'rgba(255, 255, 255, 1)'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            itemStyle: {
                normal: {
                    color: function (value){return "#"+("00000"+((Math.random()*16777215+0.5)>>0).toString(16)).slice(-6);},
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            },
            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
                return Math.random() * 200;
            }
        }
    ]
 });
//异步加载数据
//书
var book_type = [];
var count = [];
$.ajax({ 
    url: "../../Book/findGROUPBY.do", 
    type:"post",
    dataType:"json",
    data:{},
    async : true,
    success: function(result){    
        if(result.status == 0){
            $.each(result.data, function (index, item) {
                book_type.push(item.book_type);    //挨个取出类别并填入类别数组 
                count.push({
                    name: item.book_type,
                    value: item.count
                });
            });
            myBook.setOption({        //加载数据图表                
                legend: {                    
                    data: book_type
                },
                series: [{                    
                    data: count
                }]
            });
        }else{
            console.log(result);
        }
        
    },
    error:function(result)
    {
        console.log(result);
    }
});

//用户
var user_type = [];
var countUser = [];
$.ajax({ 
    url: "../../User/findGROUPBYUser.do", 
    type:"post",
    dataType:"json",
    data:{},
    async : true,
    success: function(result){ 
        if(result.status == 0){
            $.each(result.data, function (index, item) {
                user_type.push(item.user_type=="0"?"读者":"作家");    //挨个取出类别并填入类别数组 
                countUser.push({
                    name: item.user_type=="0"?"读者":"作家",
                    value: item.count
                });
            });

            console.log(user_type);
            console.log(countUser);
            myUser.setOption({        //加载数据图表                
                legend: {                    
                    data: user_type
                }, 
                series: [{                    
                    data: countUser
                }]
            });
        }else{
            console.log(result);
        }
        
    },
    error:function(result)
    {
        console.log(result);
    }
});
