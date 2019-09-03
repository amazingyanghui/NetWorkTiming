$(document).ready(function() {
	var element = layui.element;
	
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
			url: '../file/getFile?fileType=1',
			cols: [
				[ //表头
					{type:'checkbox'},
					{
						field: 'fid',
						title: '序号',
						width: 200,
						sort: true,
						fixed: 'left'
					}, {
						field: 'filename',
						title: '文件名',
						width: 200
					}, {
						field: 'loadtime',
						title: '上传时间',
						width: 200,
						sort: true
					}, {
						field: 'username',
						title: '上传人',
						width: 200
					}, {
						field: 'fileurl',
						title: '文件路径',
						width: 200
					}
				]
			],
			page: true,
			height: 472,
			width: 1000,
			parseData: function(res) { //将原始数据解析成 table 组件所规定的数据
				return {
					"code": 0, //解析接口状态
					"count": res.count, //解析数据长度
					"data": res.data //解析数据列表
				}
			}
		})
	})

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
			elem: '#demo1',
			url: '../file/getFile?fileType=2',
			cols: [
				[ //表头
					{type:'checkbox'},
					{
						field: 'fid',
						title: '序号',
						width: 200,
						sort: true,
						fixed: 'left'
					}, {
						field: 'filename',
						title: '文件名',
						width: 200
					}, {
						field: 'loadtime',
						title: '上传时间',
						width: 200,
						sort: true
					}, {
						field: 'username',
						title: '上传人',
						width: 200
					}, {
						field: 'fileurl',
						title: '文件路径',
						width: 200
					}
				]
			],
			page: true,
			height: 472,
			width: 1000,
			parseData: function(res) { //将原始数据解析成 table 组件所规定的数据
				return {
					"code": 0, //解析接口状态
					"count": res.count, //解析数据长度
					"data": res.data //解析数据列表
				}
			}
		})
	})

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
				element.tabDelete('demo', '44'); //删除：“商品管理”


				othis.addClass('layui-btn-disabled');
			},
			tabChange: function() {
				//切换到指定Tab项
				element.tabChange('demo', '22'); //切换到：用户管理
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


});

//删除区域1
function deleteMaintens() {
	var check = layui.table.checkStatus('demo').data;
	if (check.length < 1) {
		layer.msg('请选中一项');
		return false;
	}
	var ids = [];
	for (var i = 0; i < check.length; i++) {
		ids.push(check[i].fid);
	}
	$.ajax({
		type: 'post',
		traditional: true,
		url: '../file/delFileById',
		data: {
			'ids': ids
		},
		success: function(result) {
			if(result==1){
	    		layer.msg("文件删除成功！",{
	    			 btn: ['知道了']
 		       ,yes: function(index, layero){
 		    	   location.href="../html/upload.html"
 		       }
 		});
	    	}else{
	    		layer.msg("文件删除失败！");
	    	}
		},
		error: function() {
			layer.msg("ajax错误")
		}
	})


}

//删除区域
function deletefiles() {
	var check = layui.table.checkStatus('demo1').data;
	if (check.length < 1) {
		layer.msg('请选中一项');
		return false;
	}
	var ids = [];
	for (var i = 0; i < check.length; i++) {
		ids.push(check[i].fid);
	}
	$.ajax({
		type: 'post',
		traditional: true,
		url: '../file/delFileById',
		data: {
			'ids': ids
		},
		success: function(result) {
			if(result==1){
	    		layer.msg("文件删除成功！",{
	    			 btn: ['知道了']
 		       ,yes: function(index, layero){
 		    	   location.href="../html/upload.html"
 		       }
 		});
	    	}else{
	    		layer.msg("文件删除失败！");
	    	}
		},
		error: function() {
			layer.msg("ajax错误")
		}
	})


}



layui.use(['form', 'layedit', 'laydate', 'element', 'jquery'], function() {
	var form = layui.form,
		layer = layui.layer,
		element = layui.element,
		$ = layui.jquery;
		form.on
	$(document).on('click', '#sub', function() {
		var formdata = new FormData($("#frm"));
		var filename = $.trim($("#filename").val());
		var fileurl = $.trim($("#fileurl").val());
		//var formData=new FormData($("#frm")[1]);
		//layer.msg(filename);
		//layer.msg(fileurl);
		
		layer.msg(formData);
		if (filename == null || filename == "") {
			layer.msg("文件名不能为空")
			return false;
		}
		if (fileurl == null || fileurl == "") {
			layer.msg("文件路径不能为空")
			return false;
		}
		var maintenance={
			    filename:filename,
			    file:fileurl
			}
	
		$.ajax({
			type: 'post',
			traditional: true,
			url: '../file/upload',
			data: maintenance,
			success: function(res) {
				layer.msg(res.data)
			},
			error: function() {
				layer.msg("ajax错误")
			}
		})
	});
});
layui.use('form', function(){
  var form = layui.form;
  
  //监听提交
  form.on('submit(sub)', function(data){
   // layer.msg(JSON.stringify(data.field));
	var file = new FormData($("#uploadForm")[0]);
	//formData.append("file",data.field.file)
	//formData.append("filename",data.field.filename)
	//alert(file);
		$.ajax({
		url: '../file/uploadgb',
		type: 'post',
		processData:false,  
		contentType:false, 
		data : file,
		success: function(res) {
			if(res.code==0){
	    		layer.msg("上传文件成功！",{
	    			 btn: ['知道了']
 		       ,yes: function(index, layero){
 		    	   location.href="../html/upload.html"
 		       }
 		});
	    	}else{
	    		layer.msg("上传文件失败！");
	    	}
		},
		error: function() {
			layer.msg("ajax错误")
		}
	})
  });

});



layui.use('form', function(){
	  var form = layui.form;
	  
	  //监听提交
	  form.on('submit(sum)', function(data){
		var file = new FormData($("#uploadFm")[0]);
			$.ajax({
			url: '../file/uploadgb',
			type: 'post',
			processData:false,  
			contentType:false, 
			data : file,
			success: function(res) {
				if(res.code==0){
		    		layer.msg("上传文件成功！",{
		    			 btn: ['知道了']
	 		       ,yes: function(index, layero){
	 		    	   location.href="../html/upload.html"
	 		       }
	 		});
		    	}else{
		    		layer.msg("上传文件失败！");
		    	}
			},
			error: function() {
				layer.msg("ajax错误")
			}
		})
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
//function upload(){
//	// 构造formData
//	var formData = new FormData($("#form")[0]);
//	alert(formData);
//	// 发送请求
//	$.ajax({
//		url:'../../file/upload',
//		type:'post',
//		data:formData,
//		processData:false,  
//		contentType:false,  
//		success:function(res){
//			if(res.code==0){
//				location.href = "../html/upload.html"	
//			}
//		},
//		error:function(){}
//	})
//}



