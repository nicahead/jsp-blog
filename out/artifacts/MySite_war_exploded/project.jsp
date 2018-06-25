<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>NIC|PROJECT</title>
		<%
			String context = request.getContextPath();
		%>
		<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
		<meta name="keywords" content="倪畅,烟台大学,计算机,学生,个人主页,HTML,CSS,PHP,JavaScript,jQuery,XML,AJAX,,SQL,bootstrap,Python" />
		<meta name="description" content="倪畅的个人主页" />
		<link rel="shortcut icon" href="<%=context %>/img/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="<%=context %>/css/blog.css" />
		<link rel="stylesheet" type="text/css" href="<%=context %>/css/project.css" />
		<link rel="stylesheet" type="text/css" href="<%=context %>/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=context %>/css/font-awesome.min.css">
		<script src="<%=context %>/js/jquery.min.js"></script>
		<script src="<%=context %>/js/blog.js"></script>
		<script src="<%=context %>/js/bootstrap.min.js"></script>
		<style type="text/css">
			a:link {
				text-decoration: none;
			}

			a:visited {
				text-decoration: none;
			}

			a:hover {
				text-decoration: none;
			}

			a:active {
				text-decoration: none;
			}
		</style>
	</head>
	<body>
		<div id="bar" class="scrollbar"></div>
		<div id="gotop"></div>
		<div id="switch">
			<div id="iconfixed">
				<div class="icon"></div>
			</div>
		</div>
		<div id="left-nav">
			<div class="author-nav">
				<img src="<%=context %>/img/avatar.jpg" alt="个人头像">
			</div>
			<div class="main-nav">
				<ul>
					<a href="<%=context %>/index.html">
						<li>返回主页</li>
					</a>
					<a href="<%=context %>/servlet/PostlistServlet?role=0">
						<li>博客</li>
					</a>
					<a href="<%=context %>/servlet/PostlistServlet?role=4&main_id=1">
						<li>工程</li>
					</a>
					<a href="<%=context %>/about.html">
						<li>关于我</li>
					</a>
					<a href="<%=context %>/contact.html">
						<li>联系我</li>
					</a>
					<a href="<%=context %>/servlet/PostlistServlet?role=1">
						<li>控制台</li>
					</a>
				</ul>
			</div>
		</div>
		<div id="wrap">
			<div id="project-top">
				<div class="info">
					<div class="title">
						#Project
					</div>
				</div>
			</div>
			<div id="main">
				<div class="container main-inner">
					<div class="row">
						<div class="col-md-10 col-md-offset-1">
							<c:forEach items="${result.dataList }" var="article">
								<div class="col-md-4 .col-sm-6 .col-xs-12">
									<div class="card color${article.id%6}">
										<div class="card-wrap">
											<div class="card-content">
												<a href="<%=context %>/servlet/PostlistServlet?role=2&id=${article.id}"><h3>${article.title}</h3></a>
											</div>
											<div class="card-ft">
												<div class="col-xs-6 text-left">${fn:substring(article.createdate,0,10)}</div>
												<div class="col-xs-6 text-right"><a href="<%=context %>/servlet/PostlistServlet?role=2&id=${article.id}" target="_blank">详细介绍</a></div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<footer>
				<div id="block">
					<span id="beian">鲁ICP备18011092号 · </span>
					<span id="demo"></span>
				</div>
				Copyright © 2018 nichang.site <span>托管于阿里云</span>
			</footer>
		</div>
	</body>
</html>