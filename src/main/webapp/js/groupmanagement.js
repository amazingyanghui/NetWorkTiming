$(document).ready(function() {
	var element = layui.element;
	element.render();
})

layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function() {
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
		width:1050,
		url: '../maintenance/getMaintenance' //数据接口
			,
		title: '区域维护',
		page: true //开启分页
			,
		//toolbar: 'default', //开启工具栏，此处显示默认图标，可以自定义模板，详见文档

		totalRow: true //开启合计行
			,
		cols: [
			[ //表头
				{checkbox:true}
				/*{
					type: 'checkbox',
					name:'one',
					fixed: 'left'
				}*/, {
					field: 'mid',
					title: '区域id',
					name:'mid',
					width: 110
				}, {
					field: 'regname',
					title: '区域名称',
					width: 220,
				}, {
					field: 'regarea',
					title: '区域面积',
					width: 240,
					sort: true
				}, {
					field: 'seatnum',
					title: '座位数',
					width: 150,
					sort: true,
				}, {
					field: 'region',
					title: '所属区域',
					width: 170
				}, {
					field: 'sortnum',
					title: '排序号',
					sort: true,
					width: 105
				}
			]
		]
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

/*	//执行一个轮播实例
	carousel.render({
		elem: '#test1',
		width: '100%' //设置容器宽度
			,
		height: 200,
		arrow: 'none' //不显示箭头
			,
		anim: 'fade' //切换动画方式
	});*/

/*	//将日期直接嵌套在指定容器中
	var dateIns = laydate.render({
		elem: '#laydateDemo',
		position: 'static',
		calendar: true //是否开启公历重要节日
			,
		mark: { //标记重要日子
			'0-10-14': '生日',
			'2018-08-28': '新版',
			'2018-10-08': '神秘'
		},
		done: function(value, date, endDate) {
			if (date.year == 2017 && date.month == 11 && date.date == 30) {
				dateIns.hint('一不小心就月底了呢');
			}
		},
		change: function(value, date, endDate) {
			layer.msg(value)
		}
	});*/

	//分页
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
	});

/*	//上传
	upload.render({
		elem: '#uploadDemo',
		url: '' //上传接口
			,
		done: function(res) {
			console.log(res)
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

	//向世界问个好
	layer.msg('Hello World');

	/*//执行一个 table 实例
	table.render({
		elem: '#demo',
		width: 600,
		height: 600,
		url: 'new1.json', //数据接口
		title: '用户表',
		page: true, //开启分页
		toolbar: 'default', //开启工具栏，此处显示默认图标，可以自定义模板，详见文档,
		totalRow: true, //开启合计行
		cols: [ //表头
			[{
					type: 'checkbox',
					fixed: 'left'
				},
				{
					field: 'id',
					title: 'ID',
					width: 80,
					sort: true
				},
				{
					field: 'username',
					title: '用户名',
					width: 80
				},
				{
					field: 'sex',
					title: '性别',
					width: 80,
					sort: true
				},
				{
					field: 'city',
					title: '城市',
					width: 80
				},
				{
					field: 'sign',
					title: '签名',
					width: 80
				},
				{
					field: 'experience',
					title: '积分',
					width: 80,
					sort: true
				},
				{
					field: 'score',
					title: '评分',
					width: 80,
					sort: true
				},
				{
					field: 'classify',
					title: '职业',
					width: 80
				},
				{
					field: 'wealth',
					title: '财富',
					width: 80,
					sort: true
				},
				{
					fixed: 'right',
					width: 200,
					align: 'center',
					toolbar: '#barDemo'
				}
			]
		]
	});*/

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


function judge() {
	if($("#area").val().trim()==null || $("#area").val().trim()==""){
        layer.msg("请选择要操作的区域信息")
		return false;
	}else{
        var id=$("#area").val().trim();
        $.ajax({
            type:'post',
            url:'../grouplist/queryGroupManagementById',
            data: {'id':id},
            success:function (result) {
                if(result.level==4){
                    layer.msg("目前暂不支持教室级别新增子区域")
                }else{
                    $("#fade").val(result.level);
                    setTimeout(function() {
                        document.getElementById("add").click();
                    })
                }
            },
            error:function () {
                layer.msg("ajax错误")
            }
        })

    }
}

//新增
function addMaintens(){
    var name=$("#regname").val().trim();
    var parentId=$("#area").val().trim();
    var sortnum=$("#sortnum").val().trim();
    var level=$("#fade").val().trim();

    if(name==null || name==""){
        layer.msg("分组名称不能为空")
        return false;
    }
    if(sortnum==null || sortnum==""){
        layer.msg("排序号不能为空")
        return false;
    }


    var groupmanagement={
        name:name,
        parentId:parentId,
        sortnum:sortnum,
        level:level
    }
    $.ajax({
        type:'post',
        url:'../grouplist/addGroupManagement',
        data: groupmanagement,
        success:function (result) {
            if(result==1){
                location.href="../html/groupmanagement.html"
            }else{
                layer.msg("增加失败！")
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}

//编辑
//编辑信息回填
function selectMaintens() {
    if($("#area").val().trim()==null || $("#area").val().trim()==""){
        layer.msg("请选择要操作的区域信息")
        return false;
    }
	var id=$("#area").val().trim();

    $.ajax({
        type:'post',
        url:'../grouplist/queryGroupManagementById',
		async:false,
        data: {'id':$("#areaparent").val().trim()},
        success:function (result) {
            if(result!=null){
                $("#unregion").val(result.name);
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })

    $.ajax({
        type:'post',
        url:'../grouplist/queryGroupManagementById',
        data: {'id':id},
        success:function (result) {
            if(result!=null){
                $("#unregname").val(result.name);
                $("#unsortnum").val(result.sortnum);
                setTimeout(function() {
                        document.getElementById("select").click();
                    })
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}

//修改分组
function updateMaintens() {
    var name=$("#unregname").val().trim();
    var parentId=$("#areaparent").val().trim();
    var sortnum=$("#unsortnum").val().trim();
    var id=$("#area").val().trim();
    if(name==null || name==""){
        layer.msg("分组名称不能为空")
        return false;
    }
    if(sortnum==null || sortnum==""){
        layer.msg("排序号不能为空")
        return false;
    }

    var groupmanagement={
    	id:id,
        name:name,
		parentId:parentId,
        sortnum:sortnum
    }
    $.ajax({
        type:'post',
        url:'../grouplist/updateGroupManagement',
        data: groupmanagement,
        success:function (result) {
            if(result==1){
                location.href="../html/groupmanagement.html"
            }else{
                layer.msg("编辑失败！")
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
    }



function deleteQuery() {
    if($("#area").val().trim()==null || $("#area").val().trim()==""){
        layer.msg("请选择要操作的区域信息")
        return false;
    }
    var id=$("#area").val().trim();

    $.ajax({
        type:'post',
        traditional:true,
        url:'../grouplist/queryGroupManagementByParentId',
        data: {'id':id},
        success:function (result) {
            if(result==0){
                layer.msg("该分组下有子分组，无法删除")
            }else{
                setTimeout(function() {
                    document.getElementById("delete").click();
                })
			}
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}

//删除分组
function deleteMaintens() {
    var id=$("#area").val().trim();

    $.ajax({
        type:'post',
        traditional:true,
        url:'../grouplist/deleteGroupManagement',
        data: {'id':id},
        success:function (result) {
            if(result==1){
                location.href="../html/groupmanagement.html"
            }else{
                layer.msg("删除失败")
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}

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
