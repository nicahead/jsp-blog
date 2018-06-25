$(document).ready(function() {
    $(window).scroll(function() {
        var scrollY = $(document).scrollTop();
        if(scrollY > 0) {
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