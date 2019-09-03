$(function() {
	layui.use(['table', 'laydate'], function() {
		var table = layui.table,
			laydate = layui.laydate;
		//时间范围
		laydate.render({
			elem: '#timeSection',
			type: 'date',
			range: true
		});
		
		table.render({
			elem: '#parameterTable',
			url: '../parameter/getParameters',
			cols: [[ //表头
				  {
						checkbox:true
					}
					, {
						field: 'id',
						title: 'id',
						name:'id',
						width: 150,
						sort :true
					}, {
						field: 'keyName',
						title: '键名',
						width: 150,
					}, {
						field: 'pattern',
						title: '模式',
						width: 150
					}, {
						field: 'parameterValue',
						title: '取值',
						width: 150
					}, {
						field: 'defaultValue',
						title: '默认取值',
						width: 150,
					},{
						field: 'describel',
						title: '描述',
						width: 400,
					}
				]],
			page: true,
			height: 420,
			width: 1208,
			parseData: function(res) { //将原始数据解析成 table 组件所规定的数据
				return {
					"code": 0, //解析接口状态
					"count": res.count, //解析数据长度
					"data": res.data //解析数据列表
				}
			}
		})
	})
	
	
})

$(document).ready(function() {
	var element = layui.element;
	var table = layui.table
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

		table.on('toolbar(parameterTable)', function(obj) {
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
		table.on('tool(parameterTable)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
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

//新增
function addParameter(){

    var keyName=$("#keyName").val().trim();
    var pattern=$("#pattern").children('option:selected').val(); 
    var parameterValue=$("#parameterValue").val().trim();
    var defaultValue=$("#defaultValue").val().trim();
    var describel=$("#describel").val().trim();
    
    if(keyName==null || keyName==""){
        layer.msg("键名不能为空")
        return false;
    }
    if(defaultValue==null || defaultValue==""){
        layer.msg("默认取值不能为空")
        return false;
    }
    if(describel==null || describel==""){
        layer.msg("描述不能为空")
        return false;
    }
    
    $.ajax({
        type:'post',
        traditional:true,
        url:'../parameter/addParameter',
        data: {'keyName':keyName,'pattern':pattern,'parameterValue':parameterValue,'defaultValue':defaultValue,'describel':describel},
        success:function (result) {
            if(result==1){
                location.href="../html/parameter.html"
            }else{
                layer.msg(result.msg)
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}

//编辑
//编辑信息回填
function selectParameterById() {
    var check =layui.table.checkStatus('parameterTable').data;
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
        url:'../parameter/queryParameterById',
        data: {'id':id},
        success:function (result) {
            if(result!=null){
                $("#unkeyName").val(result.keyName);
                $("#unpattern").val(result.pattern); 
                $("#unparameterValue").val(result.parameterValue);
                $("#undefaultValue").val(result.defaultValue);
                $("#undescribel").val(result.describel);
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

function updateParameter() {
	 var keyName=$("#unkeyName").val().trim();
	 var pattern=$("#unpattern").children('option:selected').val(); 
	 var parameterValue=$("#unparameterValue").val().trim();
	 var defaultValue=$("#undefaultValue").val().trim();
	 var describel=$("#undescribel").val().trim();
     var id=layui.table.checkStatus('parameterTable').data[0].id;
     if(keyName==null || keyName==""){
         layer.msg("键名不能为空")
         return false;
     }
     if(defaultValue==null || defaultValue==""){
         layer.msg("默认取值不能为空")
         return false;
     }
     if(describel==null || describel==""){
         layer.msg("描述不能为空")
         return false;
     }

    
    $.ajax({
        type:'post',
        url:'../parameter/updateParameterById',
        data: {'id':id,'keyName':keyName,'pattern':pattern,'parameterValue':parameterValue,'defaultValue':defaultValue,'describel':describel},
        success:function (result) {
            if(result==1){
                location.href="../html/parameter.html"
            }else{
                layer.msg(result.msg)
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

