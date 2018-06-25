
/*
 *--------------字符串命名说明--------------------
 * introduce = 介绍
 * sty1 ==the first  style   第一种样式
 * trs1 = the first transition language   第一组过渡语
 * lingtheight == gramm lightheight     高亮语法
 * scrollRight == 源码版向右移动
 * drawBoard ==画板，用于盛放简历内容
 * resume == 简历内容
 * str == 总的拼接字符串
 * 
 */

/*字符拼接区*/
	var introduce='\/**\n * 嗨，我叫倪畅，来自山东滕州\n * 我今年20岁，YTU CS大三在读\n * 首先必须说明，本页面参考自http://strml.net/ \n * 我只是个小菜鸡~~\n **\/\n';
	var trs1 = '\n\/* 首先,给所有元素加上过渡效果 *\/';
	var sty1 = '\n * {\n  transition: all .3s;\n }\n';
	var trs2 = '\n\/* 然后换个背景 *\/';
	var sty2 = '\n html {\n  background: rgb(0,43,54);\n }\n';
	var trs3 = '\n\/* 让我们加个边框 *\/';
	var sty3 = '\n #sourceBoard {\n  background: rgb(48, 48, 48);\n  color:#fff;\n  font-family: monospace;\n  border:1px solid black;\n  width:49%;\n  max-height: 90%;\n  font-size:14px;\n }\n';
	var trs4 = '\n\/* 似乎有点单调，那么就让语法高亮吧 *\/';
    var lightheight = '\n .token.comment {\n  color:#857F6B;\n }\n .token.property {\n  color:#64D5EA;\n }\n .token.selector{\n  color:#690;\n }\n '; 
    var trs5 = '\/*接下来，我需要准备一下简历，先将刚才写的样式踢到一边儿去*\/'
    var scrollRight = '\n #sourceBoard{\n  -webkit-transform: translateX(95%); \n  } \n '
 //    var drawBoard = '#drawBoard{\n  color:#fff;\n  float:left;\n  position:relative;\n  top:-440px;\n  width:860px;\n  height:600px;\n  border:5px solid black;\n   border-radius:5px;\n  overflow:auto;\n  }'
 //    var resume= '\n  # <center>张强_前端开发简历</center>\n  ----------------------------------------------\n  ## 工作经历: ## \n  ----------------------------------------------\n  ### 1.青岛爱音悦艺术培训学校：音乐教师 ### \n   "虽然跟前端无关，但是简历一定要写出来"。By腾讯HR \n\n  ### 2. 中国兰州网 (政府媒体)：前端开发 ### \n  1.  专题设计\n  2.  频道维护\n  3.  对接后台搭建前端模版\n  4.  前端性能优化与前沿技术学习\n  5.  业务成就：\n  1.主导技术选型、方案设计、代码编写，完成中国兰州网二级子频道页面进行响应式改版。\n      >改版前 : http://news.lanzhou.cn/system/2018/01/27/011502919.shtml\n      >改版后 : http://news.lanzhou.cn/system/2018/01/11/011492028.shtml \n----------------------------------------------\n  ### 技术栈 ### \n----------------------------------------------\n      0. JavaScript/jQuery\n  1. Bootstrap\n  2. Nodejs\n  3. AngularJs\n  4. CSS3\n  5. Ajax\n  6. Webpack\n  ### 项目汇总 ### \n  [https://github.com/ZQ-jhon/-](https://github.com/ZQ-jhon/-)  \n  ### 博客\n  ----------------------------------------------\n  1. CSDN博客：[我的CSDN博客](http://blog.csdn.net/qq_20264891)\n  2. GitHub博客：[我的GitHub博客](https://ZQ-jhon.github.io)'
 //    var trs3 = '\n  \/*对了，这个简历是markdown语法，应该改成html才看着舒服。\n  *接下来变个魔术\n  *倒数3个数字\n  *3......\n  *2......\n  *1......\n  *OK,这就是为您准备的菜，祝享用愉快！ */'
	// var str = introduce.concat(sty1).concat(trs1).concat(lightheight).concat(trs2).concat(scrollRight).concat(drawBoard).concat(resume).concat(trs3);
	var str = introduce.concat(trs1).concat(sty1).concat(trs2).concat(sty2).concat(trs3).concat(sty3).concat(trs4).concat(lightheight).concat(trs5).concat(scrollRight);
	/*常规定义区*/
	var styleTag = document.getElementById('styleTag');
	var sourceBoard = document.getElementById('sourceBoard');
	var n = 0;

	 
/*源码版控制区*/	 
var controller= setInterval(put,10);
	 function put(){

			/*吐代码区域*/
			n++;
		
			if(n>0&&n<=929){
					sourceBoard.innerHTML =str.substring(0,n)
		
		
			    styleTag.innerHTML =str.substring(0,n);
	
			}
			
			/*溢出下拉*/
			if(n>=380){
					$('#sourceBoard').animate({
						scrollTop: 1000
						}, 50);
			}
			/*代码高亮*/
			if(n>=465&&n<=929){
				sourceBoard.innerHTML =  Prism.highlight(str.substring(0,n), Prism.languages.css);
			
			}
		
		/*创建pre简历板*/
			if(n>=672&&n<=929){
				
				if(document.getElementById('drawBoard')){
					console.log('drawBoard元素已经存在');
				
				}
				else{
					var drawBoard = document.createElement('pre');
				    drawBoard.id = 'drawBoard';
				    document.body.appendChild(drawBoard);
				  
				} 

			}
			
			/*简历板溢出下拉*/
			if(n>929&&n<1885){
			var drawBoard = document.getElementById('drawBoard');
				drawBoard.innerHTML =str.substring(929,n);
				
					$('#drawBoard').animate({
						scrollTop: 1000
						}, 50);
			}

		/*简历板写完之后，需要在sourceBoard中写入魔术代码*/	
	     if(n>=1885){
	     
	     	  sourceBoard.innerHTML =  Prism.highlight(str.substring(0,929), Prism.languages.css)+Prism.highlight(str.substring(1883,n), Prism.languages.css);
	     	  
	     	  
	     }
	     
	     /*魔术代码*/
	     if(n>=1977){
	     	var drawBoard = 	document.getElementById('drawBoard');
	     	drawBoard.innerHTML =marked(str.substring(929,1885));
	     	
	     		if(n>str.length){window.clearInterval(controller)}
	     }
				
		
				
		
			console.log(n)
			
			
		
	
};
