
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
		,
		form=layui.form

});


layui.use('form', function(){
    var form = layui.form;
    form.render();
});


querystate();

function querystate(){
    var stage=$('#stage option:selected').val();
    $("#statetable tbody").html("");
    $.ajax({
        type: "post",
        dataType: "json",
        url: "../terminal/queryTerminalsByDevicestauts",
        data: {
            stage: stage
        },
        success: function (data) {
            var str = "";
            var nums=0;
            var counts=0;
            var nb=0;
            for(var i=0;i<data.length;i++){
            	if(nb==0){
                    str += '<tr>';
                }
                if (data[i].devicestauts == 1) {
                    str += '<td><img src="../image/u1883.png" style="width: 70px;height: 70px;margin: 20px" alt="' + data[i].tid + '"></td>';
                    nums++;
                } else {
                    str += '<td><img src="../image/u1882.png" style="width: 80px;height: 80px;margin: 20px" alt="' + data[i].tid + '" onclick="queryinfo('+data[i].tid+')"></td>';
                    counts++;
                }
                nb++;
            	if(nb==10){
                    str += '</tr>';
                    nb==0;
                }
            }
            $("#statetable tbody").prepend(str);
            $("#count").text(data.length)
            $("#num").text(nums)
            $("#nums").text(counts)
        },
        error: function (data) {
        }
    });
}



function selectstage() {
    querystate();
}


function queryinfo(id) {
    $.ajax({
        type: "post",
        dataType: "json",
        url: "../terminal/queryInfoById",
        data: {
            tid: id
        },
        success: function (data) {
        	$("#equipment").text(data.equipment);
            $("#Ipaddress").text(data.ipaddress);
            $("#timingcycle").text(data.timingcycle);
            $("#location").text(data.location);
            if(data.devicestauts==2){
                $("#devicestauts").text("离线");
			}else{
                $("#devicestauts").text("ip冲突");
            }

			$("#jump").click();
        }
    });
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

