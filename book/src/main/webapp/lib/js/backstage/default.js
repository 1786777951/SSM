$(function(){ 
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
        function checkLastItem(arr, i) {
            return arr.length == (i + 1);
        }
        
        function getAhtml(obj){
            return "<a href=\"javascript:;\" onclick=\"addTab('" + obj.name + "','" + obj.url + "')\" ><i class=\"layui-icon "+obj.logo+"\"></i>" + obj.name + "</a>";
        }
        //三级动态菜单
        layui.jquery.ajax({
            url: "../json/menu.json",
            method: 'POST',
            dataType : "json",
            success: function(res) {
                var html = "";
                for(var i = 0; i < res.length; i++) {
                    var strli = "<li class=\"layui-nav-item lay-unselect \" >";
                    //如果网址为空则表示为有下拉选项
                    if (res[i].url =='#' && res[i+1].pId != "2"){
                        strli = strli + "<a href=\"javascript:;\"><i class=\"layui-icon "+res[i].logo+"\"></i>" + res[i].name + "</a>";
                    }else{//网址不为空则直接跳转
                        strli = strli + getAhtml(res[i]);                    
                    }
                    //如果有下拉选项则添加里面的选项按钮,没有则直接结束
                    //pId为0,不超出对象数据范围,下一个pId,不为0
                    //判断pId是否等于0,如果有0则为1级菜单
                    if(res[i].pId == "0" && !checkLastItem(res, i) && res[i + 1].pId != "0") {//pId = 0
                        strli = strli + "<dl class=\"layui-nav-child\" >";             
                        
                        for(; !checkLastItem(res, i) && res[i+1].pId != "0"; i++) { 
                                
                            //判断pId下一个是否等于1,如果有1则为2级菜单
                            if(!checkLastItem(res, i) && res[i].pId >= "2" ||  res[i+1].pId >= "2" || res[i+2].pId >= "2"){//pId = 1
                                    if(res[i].pId == "1"){
                                        strli = strli + "<dd>";
                                        strli = strli + "<a href=\"javascript:;\">" + res[i].name + "</a>";
                                        strli = strli + "<dl class=\"layui-nav-child\" >";
                                        for(; !checkLastItem(res, i+1) && res[i+1].pId =="2"; i++) {   
                                            strli = strli + "<dd>"+getAhtml(res[i+1])+"</dd>";
                                            //表名最后一个
                                            if(res[i+1].id == res.length-1){
                                                strli = strli + "<dd>"+getAhtml(res[res.length-1])+"</dd>";
                                            }else{  
                                            }
                                        }
                                        strli = strli + "</dl>";
                                        strli = strli + "</dd>";
                                    }
                            }else{   
                                strli = strli + "<dd>"+getAhtml(res[i+1])+"</dd>";
                            }
                        }
                        strli = strli + "</dl>";             
                    }
                    strli = strli + "</li>";
                    html = html + strli;
                }
                layui.jquery("#memus").append(html);
                layui.element.init(); //一定初始化一次
            }
        });
        //点击选项卡时刷新 <tab lay-filter="outNav">
        element.on('tab(tabDemo)', function(data){
            var src=$(".layui-tab-item.layui-show").find("iframe").attr("src");
            $(".layui-tab-item.layui-show").find("iframe").attr("src",src);
        });
        
    });

    /*加载首页*/
    $.ajax({ 
        url: "local/index.html", 
        type:"post",
        dataType:"html",
        success: function(data){
            $(".layui-tab-item").eq(0).html(data);
        },
        error:function()
        {
            alert("加载数据失败!");
            //错误处理
        }
    });

    $.ajax({ 
        url: "../../Admin/findAdminID.do", 
        type:"post",
        dataType:"json",
        data:{},
        success: function(result){
            console.log(result.data.admin_name);
            if(result.status == 0){
                $("#Admininfo>a").append("你好!"+result.data.admin_name+"<img src=\"http://t.cn/RCzsdCq\" class=\"layui-nav-img\"></img>");
                $("#memus>div>div").eq(1).find("p").html(result.data.admin_name);
            }else{
                console.log(result);
            }
        },
        error:function(result)
        {
            
        }
    });

    

});

//添加选项卡
function addTab(name, url) {
	if(layui.jquery(".layui-tab-title li[lay-id='" + name + "']").length > 0) {
		//选项卡已经存在
        layui.element.tabChange('tabDemo', name);
		// layer.msg('切换-' + name);
	} else {
		//动态控制iframe高度
		var tabheight = layui.jquery(window).height() - 95;
		contentTxt = '<iframe src="' + url + '" scrolling="no" width="100%" frameborder="0" height="' + (tabheight) + 'PX"></iframe>';
		//新增一个Tab项
		layui.element.tabAdd('tabDemo', {
			title: " <i class=\"layui-icon layui-icon-layer\"></i>"+name,
			content: contentTxt,
			id: name
		})
		//切换刷新
		layui.element.tabChange('tabDemo', name);
		// layer.msg('新增-' + name);
	}
}