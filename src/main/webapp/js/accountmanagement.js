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
            , url: '../login/user'//后台做模糊搜索接口路径
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
		//width:1050,
		url: '../login/user' //数据接口
			,
		title: '区域维护',
		/*page: true //开启分页
			,*/
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']//自定义分页布局
            ,limits:[3,5,10,20,30,50,100]
            ,first: false //不显示首页
            ,last: false //不显示尾页
        },
        limit:10,//要传向后台的每页显示条数
		//toolbar: 'default', //开启工具栏，此处显示默认图标，可以自定义模板，详见文档

		totalRow: true //开启合计行
			,
		cols: [
			[ //表头
				{checkbox:true},
				{
					field: 'id',
                    title: '用户id',
                    name:'id',
				}, {
					field: 'username',
					title: '用户名',
					width: 220,
				}, {
					field: 'name',
					title: '姓名',
					width: 200,
				}, {
					field: 'password',
					title: '密码',
					width: 200,
				}, {
                field: 'authorityList',
                title: '权限',
                width: 655,
                toolbar: "#bartimo"
            }
			]
		],done:function(res,curr,count){
            // 隐藏列
            $(".layui-table-box").find("[data-field='id']").css("display","none");
            console.log(res)

            var obj=document.getElementsByName("show");
            for(var i=0;i<res.data.length;i++) {
                for(var m=0;m<res.data[i].authorityList.length;m++) {
                    for (k in obj) {
                        if (res.data[i].authorityList[m].id == obj[k].value) {

                            $("[lay-id='demo'] tbody tr[data-index="+i+"]").find(obj[k]).next(':first').addClass('layui-form-checked');
                             //$(obj[k]).next(':first').addClass('layui-form-checked');
                        }
                    }
                }
            }
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

//新增
function addMaintens(){
    var username=$("#username").val().trim();
    var name=$("#name").val().trim();
    var password=$("#password").val().trim();
    var repassword=$("#repassword").val().trim();
    var role=$("#role").val().trim();
    if(username==null || username==""){
        layer.msg("用户名不能为空")
        return false;
    }
    if(name==null || name==""){
        layer.msg("姓名不能为空")
        return false;
    }
    if(password==null || password==""){
        layer.msg("密码不能为空")
        return false;
    }
    if(repassword==null || repassword==""){
        layer.msg("确认密码不能为空")
        return false;
    }
    if(role==null || role==""){
        layer.msg("角色不能为空")
        return false;
    }
    if(password != repassword){
        layer.msg("两次密码不一致")
        return false;
    }

    var ids=[];
    var obj=document.getElementsByName("add");
    for(k in obj){
    	if(obj[k].checked){
            ids.push(obj[k].value);
        }
	}

    var user={
        username:username,
        name:name,
        password:password,
        role:role,
    }

    $.ajax({
        type:'post',
        url:'../login/addUser',
        data: user,
        success:function (result) {
            if(result!=0){

                $.ajax({
                    type:'post',
                    url:'../login/addAuthority',
                    traditional:true,
                    data: {"ids":ids,"userId":result},
                    success:function (result) {
                        if(result==1){
                            location.href="../html/accountmanagement.html"
                        }else{
                            layer.msg("增加失败！")
                        }
                    },
                    error:function () {
                        layer.msg("ajax错误")
                    }
                })

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
    var check =layui.table.checkStatus('demo').data;
console.log(layui.table.checkStatus('demo'))
    if(check.length>1){
        layer.msg('只能同时编辑一个');
        return false;
    }
    if(check.length<1){
        layer.msg('请选中一项');
        return false;
    }
   var id=check[0].id;

    $.ajax({
        type:'post',
        url:'../login/queryUserById',
        data: {'id':id},
        success:function (result) {
            if(result!=null){
                $("#unusername").val(result.username);
                $("#unname").val(result.name);
                $("#unpassword").val(result.password);
                $("#unrepassword").val(result.password);
                $("#rerole").val(result.role);
                var ids=[];
                var obj=document.getElementsByName("update");

                for(var i=0;i<result.authorityList.length;i++) {
                    for(k in obj){
                        if(result.authorityList[i].id==obj[k].value){
                            obj[k].checked=true;
                        }
                    }
                }
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

//修改
function updateMaintens() {
    var username=$("#unusername").val().trim();
    var name=$("#unname").val().trim();
    var password=$("#unpassword").val().trim();
    var repassword=$("#unrepassword").val().trim();
    var role=$("#rerole").val().trim();
    var userId =layui.table.checkStatus('demo').data[0].id;
	console.log(userId)
    if(username==null || username==""){
        layer.msg("用户名不能为空")
        return false;
    }
    if(name==null || name==""){
        layer.msg("姓名不能为空")
        return false;
    }
    if(password==null || password==""){
        layer.msg("密码不能为空")
        return false;
    }
    if(repassword==null || repassword==""){
        layer.msg("确认密码不能为空")
        return false;
    }
    if(role==null || role==""){
        layer.msg("角色不能为空")
        return false;
    }
    if(password != repassword){
        layer.msg("两次密码不一致")
        return false;
    }

    var ids=[];
    var obj=document.getElementsByName("update");
    for(k in obj){
        if(obj[k].checked){
            ids.push(obj[k].value);
        }
    }

    var user={
    	id:userId,
        username:username,
        name:name,
        password:password,
        role:role,
    }

    $.ajax({
        type:'post',
        url:'../login/updateUser',
        data: user,
        success:function (result) {
            if(result==1){

                $.ajax({
                    type:'post',
                    url:'../login/updateAuthority',
                    traditional:true,
                    data: {"ids":ids,"userId":userId},
                    success:function (result) {
                        if(result==1){
                            location.href="../html/accountmanagement.html"
                        }else{
                            layer.msg("修改失败！")
                        }
                    },
                    error:function () {
                        layer.msg("ajax错误")
                    }
                })

            }else{
                layer.msg("修改失败！")
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}



//删除区域
function deleteMaintens() {
    var check =layui.table.checkStatus('demo').data;
    if(check.length<1){
        layer.msg('请选中一项');
        return false;
    }
    var ids=[];
    for (var i = 0; i < check.length; i++) {
        ids.push(check[i].id);
    }
    $.ajax({
        type:'post',
        traditional:true,
        url:'../login/deleteUserById',
        data: {'ids':ids},
        success:function (result) {
            if(result==1){
                location.href="../html/accountmanagement.html"
            }else{
                layer.msg("删除失败")
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}



//责任教室复选框回填
function queryclass() {
    var check =layui.table.checkStatus('demo').data;
    console.log(layui.table.checkStatus('demo'))
    if(check.length>1){
        layer.msg('只能选中一个');
        return false;
    }
    if(check.length<1){
        layer.msg('请选中一项');
        return false;
    }
    var id=check[0].id;

    $.ajax({
        type:'post',
        url:'../login/queryClassByUserId',
        data: {'id':id},
        success:function (result) {
            layui.config({
                base: '../module/'
            }).extend({
                treetable: 'treetable-lay/treetable'
            }).use(['layer', 'table', 'treetable'], function () {
                var $ = layui.jquery;
                var table = layui.table;
                var layer = layui.layer;
                var treetable = layui.treetable;

                // 渲染表格
                var renderTable = function () {
                    // layer.load(2);
                    $.get('../grouplist/queryListClass', function (res) {
                        treetable.render({
                            treeColIndex: 1,
                            treeSpid: -1,
                            treeIdName: 'id',
                            treePidName: 'parentId',
                            treeDefaultClose: true,
                            treeLinkage: false,
                            elem: '#table1',
                            data: res.data,
                            page: false,
                            cols: [[
                                {type: 'numbers'},
                                {field: 'name', title: '名称',width: 456,},
                                {checkbox: true},
                            ]],
                            done: function (res) {
                                layer.closeAll('loading');
                                for(var i=0;i<result.length;i++) {
                                        for (var m=0;m<res.data.length;m++) {
                                            if (result[i].listId == res.data[m].id) {
                                                console.log(res.data[m].name)

                                                $("[lay-id='table1'] tbody tr td[data-content="+res.data[m].name+"]").next("td").find("div.layui-form-checkbox").addClass('layui-form-checked');
                                                //$(this).checked=true;
                                            }
                                        }
                                    }
                            }
                        });
                    }, 'json');
                };


                // 渲染表格
                var renderTable1 = function () {
                    // layer.load(2);
                    $.get('../grouplist/queryGroupClass', function (res) {
                        treetable.render({
                            treeColIndex: 1,
                            treeSpid: -1,
                            treeIdName: 'id',
                            treePidName: 'parentId',
                            treeDefaultClose: true,
                            treeLinkage: false,
                            elem: '#table2',
                            data: res.data,
                            page: false,
                            cols: [[
                                {type: 'numbers'},
                                {field: 'name', title: '名称',width: 456,},
                                {checkbox: true},
                            ]],
                            done: function (res) {
                                layer.closeAll('loading');
                                for(var i=0;i<result.length;i++) {
                                    for (var m=0;m<res.data.length;m++) {
                                        if (result[i].listId == res.data[m].id) {
                                            console.log(res.data[m].name)

                                            $("[lay-id='table1'] tbody tr td[data-content="+res.data[m].name+"]").next("td").find("div.layui-form-checkbox").addClass('layui-form-checked');
                                            //$(this).checked=true;
                                        }
                                    }
                                }
                            }
                        });
                    }, 'json');
                };

                renderTable1();
                renderTable();

                $('#btn-expand').click(function () {
                    treetable.expandAll('#table1');
                });

                $('#btn-fold').click(function () {
                    treetable.foldAll('#table1');
                });

                $('#btn-refresh').click(function () {
                    renderTable();
                });

                $('#expand').click(function () {
                    treetable.expandAll('#table2');
                });

                $('#fold').click(function () {
                    treetable.foldAll('#table2');
                });

                $('#refresh').click(function () {
                    renderTable();
                });

                //监听工具条
                table.on('tool(table1)', function (obj) {
                    var data = obj.data;
                    var layEvent = obj.event;

                    if (layEvent === 'del') {
                        layer.msg('删除' + data.id);
                    } else if (layEvent === 'edit') {
                        layer.msg('修改' + data.id);
                    }
                });
            });

            setTimeout(function() {
                document.getElementById("queryclass").click();
            })
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}


//保存责任教室
function queryclassMaintens() {
    var userId =layui.table.checkStatus('demo').data[0].id;
    var ids=[];
    var obj=layui.table.checkStatus('table1').data;
    for(k in obj){
            ids.push(obj[k].id);
    }
    $.ajax({
        type:'post',
        url:'../login/updateClassByUserId',
        traditional:true,
        data: {"userId":userId,"listIds":ids},
        success:function (result) {
            if(result==1){
                      location.href="../html/accountmanagement.html"
            }else{
                layer.msg("保存失败！")
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}


//删除判断
function deleteUser() {
    var check = layui.table.checkStatus('demo').data;
    console.log(layui.table.checkStatus('demo'))
    if (check.length < 1) {
        layer.msg('至少选中一项');
        return false;
    }
    setTimeout(function() {
        document.getElementById("delete").click();
    })
}


function jump() {
    window.location.href="../login/tologin";
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