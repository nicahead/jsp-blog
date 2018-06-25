$(document).ready(function() {
    var winHeight = $(document).scrollTop();
    $(window).scroll(function() {
        var scrollY = $(document).scrollTop(); // 获取垂直滚动的距离，即滚动了多少
        if(scrollY > 0) { //如果向下滚动则隐藏，否则删除隐藏类
            $('.info').hide();
        } else {
            $('.info').show();
        }
        if(scrollY > winHeight) { //如果没滚动到顶部，删除显示类，否则添加显示类
            $('.info').hide();
        } else {
            $('.info').show();
        }
    });

	$("#switch").click(function(event) {
		if ($("#wrap").hasClass('open')) {
			$('#wrap,#left-nav').removeClass('open');
			$(this).removeClass('botton-open');
		} else {
			$('#wrap,#left-nav').addClass('open');
			$(this).addClass('botton-open');
		}
	});

	$(".havasub").click(function(event) {
		$(this).next(".submenu").slideToggle(); //实现二级菜单的展开收缩功能
		$(this).find("i").toggleClass("fa fa-angle-down"); //实现菜单点击时图标的转换效果
		$(this).find("i").toggleClass("fa fa-angle-up");
	});

	
	// var $editor = $("#editor");
	// $editor.markdown({
	// 	height: 300,
	// 	onShow: function() {}
	// });
});