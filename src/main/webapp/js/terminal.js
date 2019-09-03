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
		// 日志信息
		table.render({
			elem: '#demo',
			url: '../terminal/getTerminal',
			cols: [
				[ //表头
                    {checkbox:true},
					{
						field: 'tid',
						title: 'ID',
						width: 200,
						sort: true,
						fixed: 'left'
					}, {
						field: 'd.listManagement.name',
						title: '教室名',
						width: 200,
						templet:'<div>{{d.listManagement ? d.listManagement.name: d.groupManagement.name}}</div>'
					}, {
						field: 'devicestauts',
						title: '设备状态',
						width: 200,
                    toolbar: "#timo1"
					}, {
						field: 'location',
						title: '所在地区',
						width: 200
					}, {
						field: 'display',
						title: '显示方式',
						width: 200,
                    toolbar: "#timo2"
					}
				]
			],
			page: true,
			height: 472,
			width: 1000
		})
	})
})

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


});

//删除判断
function deleteTerminal() {
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

//删除终端
function deleteMaintens() {
    var check =layui.table.checkStatus('demo').data;

    var ids=[];
    for (var i = 0; i < check.length; i++) {
        ids.push(check[i].tid);
    }

    $.ajax({
        type:'post',
        traditional:true,
        url:'../terminal/deleteTerminalById',
        data: {'ids':ids},
        success:function (result) {
            if(result==1){
                location.href="../html/terminal.html"
            }else{
                layer.msg("删除失败")
            }
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}


//新增
function addMaintens(){
    var equipment=$("#equipment").val().trim();
    var timemode=$("#timemode").val().trim();
    var Ipaddress=$("#Ipaddress").val().trim();
    var Macaddress=$("#Macaddress").val().trim();
    var timingcycle=$("#timingcycle").val().trim();
    var centreno=$("#centreno").val().trim();
    var location=$("#location").val().trim();
    var display=$('#display option:selected').val();
    var mid=$("#locationId").val().trim();
    var gid=$("#groupId").val().trim();
    if(equipment==null || equipment==""){
        layer.msg("设备编号不能为空")
        return false;
    }
    if(timemode==null || timemode==""){
        layer.msg("授时方式不能为空")
        return false;
    }
    if(Ipaddress==null || Ipaddress==""){
        layer.msg("ip地址不能为空")
        return false;
    }
    if(Macaddress==null || Macaddress==""){
        layer.msg("Mac地址不能为空")
        return false;
    }
    if(timingcycle==null || timingcycle==""){
        layer.msg("授时周期不能为空")
        return false;
    }
    if(centreno==null || centreno==""){
        layer.msg("考场编号不能为空")
        return false;
    }
    if(location==null || location==""){
        layer.msg("所在区域不能为空")
        return false;
    }
    if(display==null || display==""){
        layer.msg("显示模式不能为空")
        return false;
    }

    var terminal={
        equipment:equipment,
        timemode:timemode,
        Ipaddress:Ipaddress,
        Macaddress:Macaddress,
        timingcycle:timingcycle,
        centreno:centreno,
        location:location,
        display:display,
        mid:mid,
        gid:gid
    }

    $.ajax({
        type:'post',
        url:'../terminal/addTerminal',
        data: terminal,
        success:function (result) {
            console.log(result)
                 if(result==1){
                 	window.location.reload();
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
    var id=check[0].tid;

    $.ajax({
        type:'post',
        url:'../terminal/queryInfoById',
        data: {'tid':id},
        success:function (result) {
            if(result!=null){
                $("#reequipment").val(result.equipment);
               $("#retimemode").val(result.timemode);
               $("#reIpaddress").val(result.ipaddress);
               $("#reMacaddress").val(result.macaddress);
                $("#retimingcycle").val(result.timingcycle);
                $("#recentreno").val(result.centreno);
               $("#relocation").val(result.location);
                var obj=document.getElementsByName("redisplay");
                for(k in obj){
                    if(result.display==obj[k].value){
                        $(obj[k]).attr("selected", "selected");
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
    var equipment=$("#reequipment").val().trim();
    var timemode=$("#retimemode").val().trim();
    var Ipaddress=$("#reIpaddress").val().trim();
    var Macaddress=$("#reMacaddress").val().trim();
    var timingcycle=$("#retimingcycle").val().trim();
    var centreno=$("#recentreno").val().trim();
    var location=$("#relocation").val().trim();
    var display=$('#redisplay option:selected').val();
    if(equipment==null || equipment==""){
        layer.msg("设备编号不能为空")
        return false;
    }
    if(timemode==null || timemode==""){
        layer.msg("授时方式不能为空")
        return false;
    }
    if(Ipaddress==null || Ipaddress==""){
        layer.msg("ip地址不能为空")
        return false;
    }
    if(Macaddress==null || Macaddress==""){
        layer.msg("Mac地址不能为空")
        return false;
    }
    if(timingcycle==null || timingcycle==""){
        layer.msg("授时周期不能为空")
        return false;
    }
    if(centreno==null || centreno==""){
        layer.msg("考场编号不能为空")
        return false;
    }
    if(location==null || location==""){
        layer.msg("所在区域不能为空")
        return false;
    }
    if(display==null || display==""){
        layer.msg("显示模式不能为空")
        return false;
    }

    var terminal={
        tid:layui.table.checkStatus('demo').data[0].tid,
        equipment:equipment,
        timemode:timemode,
        Ipaddress:Ipaddress,
        Macaddress:Macaddress,
        timingcycle:timingcycle,
        centreno:centreno,
        location:location,
        display:display,
    }

    $.ajax({
        type:'post',
        url:'../terminal/updateTerminalById',
        data: terminal,
        success:function (result) {
                 window.location.reload();
        },
        error:function () {
            layer.msg("ajax错误")
        }
    })
}

//区域
$(function() {
	layui.use(['table', 'laydate'], function() {
		var table = layui.table,
			laydate = layui.laydate;

		table.render({
			elem: '#locationTable',
			url: '../grouplist/getManagements',
			cols: [[ //表头
				  {
						checkbox:true
					}
					, {
						field: 'id',
						title: 'id',
						name:'id',
						width: 50,
						sort :true
					}, {
						field: 'name',
						title: '名称',
						width: 150
					},{
						field: 'isHaveTerminal',
						title: '是否拥有终端',
						width: 150,
						templet:'#templet1'
					}
				]],
			page: false,
			height: 420,
			width: 400,
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

//分组
$(function() {
	layui.use(['table', 'laydate'], function() {
		var table = layui.table,
			laydate = layui.laydate;

		table.render({
			elem: '#groupTable',
			url: '../grouplist/getGroups',
			cols: [[ //表头
				  {
						checkbox:true
					}
					, {
						field: 'id',
						title: 'id',
						name:'id',
						width: 50,
						sort :true
					}, {
						field: 'name',
						title: '名称',
						width: 150
					},{
						field: 'isHaveTerminal',
						title: '是否拥有终端',
						width: 150,
						templet:'#templet1'
					}
				]],
			page: false,
			height: 420,
			width: 400,
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

function addLocation() {
    var check =layui.table.checkStatus('locationTable').data;
    
    if(check.length>1){
        layer.msg('只能选中一项');
        return false;
    }
    
    
    
    for (var i = 0; i < check.length; i++) {
    	if(check[i].isHaveTerminal==1){
    		layer.msg('该区域已拥有终端');
    		return false;
    	}
    	$("#locationDiv").append("<input type='text' id='location' class='form-control' readonly='readonly' value='"+check[i].name+"'></input>");
    	$("#locationDiv").append("<input type='hidden' id='locationId' class='form-control' readonly='readonly' value='"+check[i].id+"'></input>");
    }
    
    $("#return").trigger("click");
}

function addGroup() {
    var check =layui.table.checkStatus('groupTable').data;
    
    if(check.length>1){
        layer.msg('只能选中一项');
        return false;
    }
    
   
    
    for (var i = 0; i < check.length; i++) {
    	if(check[i].isHaveTerminal==1){
    		layer.msg('该分组已拥有终端');
    		return false;
    	}
    	$("#groupDiv").append("<input type='text' id='group' class='form-control' readonly='readonly' value='"+check[i].name+"'></input>");
    	$("#groupDiv").append("<input type='hidden' id='groupId' class='form-control' readonly='readonly' value='"+check[i].id+"'></input>");
    }
    
    $("#return2").trigger("click");
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

