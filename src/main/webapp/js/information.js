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
			elem: '#informationTable',
			url: '../information/getInformation',
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
						field: 'title',
						title: '标题',
						width: 150,
					}, {
						field: 'content',
						title: '内容',
						width: 500
					}, {
						field: 'publisher',
						title: '发布人',
						width: 150
					}, {
						field: 'releaseTime',
						title: '发布时间',
						width: 150,
						sort :true
					}
				]],
			page: true,
			height: 420,
			width: 1195,
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
	
	$("#addReturn").click(function(){
    	$("#terminalDiv").html("");
	})
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

$(function() {
	layui.use(['table', 'laydate'], function() {
		var table = layui.table,
			laydate = layui.laydate;

		table.render({
			elem: '#terminalTable',
			url: '../grouplist/getManagements',
			cols: [[ //表头
				  {
						checkbox:true
					}
					, {
						field: 'id',
						title: 'id',
						name:'id',
						width: 70,
						sort :true
					},, {
						field: 'name',
						title: '名称',
						width: 90
					}
				]],
			page: false,
			height: 420,
			width: 190,
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


//新增
function addMaintens(){

    var title=$("#title").val().trim();
    var content=$("#content").val().trim();
    var interest=$("#interest").children('option:selected').val(); 
    var releaseTime=null;
    var releaseMethod=null;
    if(interest=="timing"){
    	 releaseTime=$("#releaseTime").val().trim(); 
    	 releaseMethod=0;
    }else{
    	 var time=new Date();
    	 releaseTime=time.getFullYear() + '-' + (time.getMonth() + 1) + '-' + time.getDate();
    	 releaseMethod=1;//立刻发布,releaseMethod默认为1
    }
    var checkId=[];
    $("input[name='terminals']:checked").each(function(i){
    	checkId[i]=$(this).val();
    })
    if(title==null || title==""){
        layer.msg("标题不能为空")
        return false;
    }
    if(content==null || content==""){
        layer.msg("内容不能为空")
        return false;
    }
    
    if(checkId.length<1){
        layer.msg("请选择终端")
        return false;
    }

    $.ajax({
        type:'post',
        traditional:true,
        url:'../information/addInformation',
        data: {'title':title,'content':content,'releaseTime':releaseTime,'releaseMethod':releaseMethod,'checkId':checkId},
        success:function (result) {
            if(result==1){
                location.href="../html/informationList.html"
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
function selectMaintens() {
    var check =layui.table.checkStatus('informationTable').data;
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
        url:'../information/queryInformationById',
        data: {'id':id},
        success:function (result) {
            if(result!=null){
                $("#untitle").val(result.title);
                $("#uncontent").val(result.content);
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

function updateMaintens() {
    var title=$("#untitle").val().trim();
    var content=$("#uncontent").val().trim();
    var id=layui.table.checkStatus('informationTable').data[0].id;
 /*   if(title==null || title==""){
        layer.msg("标题不能为空")
        return false;
    }
    if(content==null || content==""){
        layer.msg("内容不能为空")
        return false;
    } */
    

    
    $.ajax({
        type:'post',
        url:'../../information/updateInformationById',
        data: {'title':title,'content':content,'id':id},
        success:function (result) {
            if(result==1){
                location.href="../html/informationList.html"
            }else{
                layer.msg(result.msg)
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
    }


//删除区域
function deleteMaintens() {
    var check =layui.table.checkStatus('informationTable').data;
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
        url:'../information/deleteInformationById',
        data: {'ids':ids},
        success:function (result) {
            if(result==1){
                location.href="../html/informationList.html"
            }else{
                layer.msg("删除失败")
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}

function addTerminal() {
    var check =layui.table.checkStatus('terminalTable').data;
    if(check.length<1){
        layer.msg('请选中一项');
        return false;
    }
    
    $("#terminalDiv").html("");
    
    for (var i = 0; i < check.length; i++) {
        $("#terminalDiv").append("<input type='checkbox' checked name='terminals' value='"+check[i].id+"'>"+check[i].name+"</input>");
    }
    
    $("#return").trigger("click");
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

