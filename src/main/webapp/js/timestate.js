$(document).ready(function() {
    var element = layui.element;
    element.render();
})

layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider','form'], function() {
    var laydate = layui.laydate //日期
        ,
        laypage = layui.laypage //分页
        ,
        layer = layui.layer //弹层
        ,
        table = layui.table //表格
        ,
        carousel = layui.carousel //轮播
        ,
        upload = layui.upload //上传
        ,
        element = layui.element //元素操作
        ,
        slider = layui.slider //滑块
        ,form = layui.form


    //监听提交 lay-filter="search"
    form.on('submit(search)', function(data){
        var formData = data.field;
        var selectname = formData.selectname,
            url=formData.url,
            icon=formData.icon,
            parent_id=formData.parent_id;
        //执行重载
        table.reload('demo', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {//这里传参  向后台
                selectname: selectname
                //可传多个参数到后台...  ，分隔
            }
            , url: '../ringingTask/queryAllBellstate'//后台做模糊搜索接口路径
            , method: 'post'
        });
        return false;//false：阻止表单跳转  true：表单跳转
    });



    //监听Tab切换
    element.on('tab(demo)', function(data) {
        layer.tips('切换了 ' + data.index + '：' + this.innerHTML, this, {
            tips: 1
        });
    });

    //执行一个 table 实例
    table.render({
        elem: '#demo',
        height: 420,
        width:1000,
		url: '../ringingTask/queryAllBellstate',
        title: '打铃状态',
		/*page: true //开启分页
		 ,*/
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']//自定义分页布局
            ,limits:[2,3,5,10,20,30,50,100]
            ,first: false //不显示首页
            ,last: false //不显示尾页
        },
        limit:10,//要传向后台的每页显示条数
        //toolbar: 'default', //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        totalRow: true ,//开启合计行
        cols: [
            [ //表头
                {
                    field: 'id',
                    title: 'ID',
                    width: 100,
                    fixed: 'left'
                }, {
                field: 'listManagement.name',
                title: '教室名',
                width: 150,
                toolbar: "#bartimo"
            }, {
                field: 'devicestauts',
                title: '网络状态',
                width: 150,
                toolbar: "#timo1"
            }, {
                field: 'bellSwitch',
                title: '打铃开关',
                width: 150,
                toolbar: "#timo2"
            }, {
                field: 'ringingTask.ringmode',
                title: '打铃模式',
                width: 150,
                toolbar: "#timo3"
            }
                , {
                field: 'ringingTask.volume',
                title: '终端音量',
                width: 150,
                toolbar: "#bartimo1"
            }
                , {
                field: 'ringingTask.tid',
                title: '打铃任务',
                width: 150,
                toolbar: "#bartimo2"
            }
            ]
        ],done:function(res,curr,count){
     console.log(res)
            console.log(curr)

            console.log(count)
        }
    });

    //监听头工具栏事件
    table.on('toolbar(test)', function(obj) {
        var checkStatus = table.checkStatus(obj.config.id),
            data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.msg('添加');
                break;
            case 'update':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：' + checkStatus.data[0].id);
                }
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
                }
                break;
        };
    });

    //监听行工具事件
    table.on('tool(test)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,
            layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'detail') {
            layer.msg('查看操作');
        } else if (layEvent === 'del') {
            layer.confirm('真的删除行么', function(index) {
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if (layEvent === 'edit') {
            layer.msg('编辑操作');
        }
    });


   /* //分页
    laypage.render({
        elem: 'pageDemo' //分页容器的id
        ,
        count: 100 //总页数
        ,
        skin: '#1E9FFF' //自定义选中色值
        //,skip: true //开启跳页
        ,
        jump: function(obj, first) {
            if (!first) {
                layer.msg('第' + obj.curr + '页', {
                    offset: 'b'
                });
            }
        }
    });*/



    slider.render({
        elem: '#sliderDemo',
        input: true //输入框
    });

    //底部信息
    var footerTpl = lay('#footer')[0].innerHTML;
    lay('#footer').html(layui.laytpl(footerTpl).render({}))
        .removeClass('layui-hide');
});
layui.use('table', function() {
    var table = layui.table; //表格




    //监听 头 工具栏事件
    table.on('toolbar(test)', function(obj) {
        var checkStatus = table.checkStatus(obj.config.id),
            data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.msg('添加');
                break;
            case 'update':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：' + checkStatus.data[0].id);
                }
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
                }
                break;
        };
    });
    //监听 行 工具事件
    table.on('tool(test)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data, //获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'detail') {
            layer.msg('查看操作');
        } else if (layEvent === 'del') {
            layer.confirm('真的删除行么', function(index) {
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if (layEvent === 'edit') {
            layer.msg('编辑操作:<br>' + JSON.stringify(data));
        }
    });
});





layui.use('element', function() {
    var $ = layui.jquery,
        element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

    //触发事件
    var active = {
        tabAdd: function() {
            //新增一个Tab项
            element.tabAdd('demo', {
                title: '新选项' + (Math.random() * 1000 | 0) //用于演示
                ,
                content: '内容' + (Math.random() * 1000 | 0),
                id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
            })
        },
        tabDelete: function(othis) {
            //删除指定Tab项
            element.tabDelete('demo', '44'); //删除：“列表”


            othis.addClass('layui-btn-disabled');
        },
        tabChange: function() {
            //切换到指定Tab项
            element.tabChange('demo', '22'); //切换到：分组
        }
    };

    $('.site-demo-active').on('click', function() {
        var othis = $(this),
            type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });

    //Hash地址的定位
    var layid = location.hash.replace(/^#test=/, '');
    element.tabChange('test', layid);

    element.on('tab(test)', function(elem) {
        location.hash = 'test=' + $(this).attr('lay-id');
    });

});


function updatePwd(){
	var password=$("#unpassword").val().trim();
	var newPassword=$("#unnewPassword").val().trim();
	if(password==null||password==""){
		layer.msg("原密码不能为空");
		return false;
	}
	if(newPassword==null||newPassword==""){
		layer.msg("新密码不能为空");
		return false;
	}
	$.ajax({
        type:'post',
        traditional:true,
        url:'../sign/changePwd',
        data: {'passWord':password,'newPwd':newPassword},
        success:function (result) {
            if(result.code==0){
            	layer.msg(result.msg)
                location.href="../html/login.html"
            }else{
                layer.msg(result.msg)
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}




