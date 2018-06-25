<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>NIC|BLOG</title>
    <%
        String context = request.getContextPath();
    %>
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
          name="viewport">
    <meta name="keywords" content="倪畅,烟台大学,计算机,学生,个人主页,HTML,CSS,PHP,JavaScript,jQuery,XML,AJAX,,SQL,bootstrap,Python" />
    <meta name="description" content="倪畅的个人主页" />
    <link rel="shortcut icon" href="<%=context %>/img/favicon.ico"/>
    <link rel="stylesheet" href="<%=context %>/css/blog.css"/>
    <link rel="stylesheet" href="<%=context %>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=context %>/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=context %>/css/pagination.css">
    <script src="<%=context %>/js/jquery.min.js"></script>
    <script src="<%=context %>/js/blog.js"></script>
    <script src="<%=context %>/js/bootstrap.min.js"></script>
    <script src="<%=context %>/js/jquery.pagination.js"></script>
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
    <script>
        // 点击分页按钮以后触发的动作
        function handlePaginationClick(new_page_index, pagination_container) {
            $("#postForm").attr("action", "<%=context %>/servlet/PostlistServlet?role=0&pageNum=" + (new_page_index + 1));
            $("#postForm").submit();
            return false;
        };
        $(function () {
            $("#News-Pagination").pagination(${result.totalRecord}, {
                items_per_page:${result.pageSize}, // 每页显示多少条记录
                current_page: ${result.currentPage} -1, // 当前显示第几页数据
                num_display_entries: 3, // 分页显示的条目数
                next_text: "下一页",
                prev_text: "上一页",
                num_edge_entries: 2, // 连接分页主体，显示的条目数
                callback: handlePaginationClick
            });
        })
    </script>
</head>

<body style="background: #e2e2e2 url(<%=context %>/img/home.jpg) no-repeat fixed center;
        background-size: cover;">
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
            <a href="#">
                <li>博客首页</li>
            </a>
            <c:forEach items="${mainCategory}" var="maincatetory">
                <c:if test="${maincatetory.name!='工程'}">
                    <a href="javascript:void(0)" class="havasub">
                        <li>${maincatetory.name }</li>
                    </a>
                    <ul class="submenu">
                        <c:forEach items="${maincatetory.sublist}" var="subcatetory">
                            <a href="PostlistServlet?role=0&sub_id=${subcatetory.id}">
                                <li>
                                        ${subcatetory.name}
                                </li>
                            </a>
                        </c:forEach>
                    </ul>
                </c:if>
            </c:forEach>
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
    <div id="top">
        <div class="info">
            <div class="bg-title">
                Nic's Blog
            </div>
            <div class="md-title">
                DEBUG THE WORLD
            </div>
        </div>
    </div>
    <div id="main">
        <form id="postForm" method="POST" action="<%=context %>/servlet/PostlistServlet">
            <div class="container main-inner">
                <div class="row">
                    <div class="article-wrap col-md-10 col-md-offset-1 col-xs-12">
                        <c:forEach items="${result.dataList }" var="article">
                            <article class="index-article">
                                <div class="post-info">
                                    <h2>
                                        <a href="<%=context %>/servlet/PostlistServlet?role=2&id=${article.id}">${article.title }</a>
                                    </h2>
                                    <div class="post-detial">
                                        <span>${article.sname}</span>
                                        <span>${fn:substring(article.createdate,0,10)}</span>
                                    </div>
                                </div>
                                <p>${article.subtitle }</p>
                                <center>
                                    <button class="more"><a
                                            href="<%=context %>/servlet/PostlistServlet?role=2&id=${article.id}"
                                            style="color: #000;">Read More</a></button>
                                </center>
                            </article>
                        </c:forEach>
                        <div id="News-Pagination" ></div>
                    </div>
                </div>
            </div>
        </form>
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