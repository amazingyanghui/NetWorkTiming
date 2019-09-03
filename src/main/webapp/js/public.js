// 变量
var urlName = {
     url:'http://192.168.1.31:8080/TTMS_4_0/'//杨辉
};

// 监听滚动体的变化
var tabBac = window.parent.document.getElementById('layuiMenu');
$(tabBac).removeAttr('style');
$(window.document).scroll(function (e) {
    if ($(this).scrollTop() > 1) {
        $(tabBac).attr('style', 'border-bottom: 1px solid #aaaaaa;')
    } else {
        $(tabBac).removeAttr('style')
    }
});
// 数组去重
function uniq(array){
    var temp = []; //一个新的临时数组
    for(var i = 0; i < array.length; i++){
        if(temp.indexOf(array[i]) == -1){
            temp.push(array[i]);
        }
    }
    return temp;
}
// 去掉空格
function Trim(str,is_global) {
    var result;
    result = str.replace(/(^\s+)|(\s+$)/g,"");
    if(is_global.toLowerCase()=="g") {
        result = result.replace(/\s/g,"");
    }
    return result;
}
// 加载底部版本号
function footerFun() {
    setTimeout(function () {
        var footer = $('#footer');
        footer.load('foot.html');
        footer.attr('style', 'position: absolute; bottom: 0;left: 0; width: 100%;');
        if ((footer.offset().top) - 20 < $('body').height()) {
            footer.removeAttr('style')
        } else {
            footer.attr('style', 'position: absolute; bottom: 0;left: 0; width: 100%;');
        }
    }, 500)
}
footerFun();
window.onresize = function () {
    footerFun();
};
setTimeout(function () {
    $('table').bind('DOMNodeInserted', function(e) {
        footerFun()
    })
}, 1000);