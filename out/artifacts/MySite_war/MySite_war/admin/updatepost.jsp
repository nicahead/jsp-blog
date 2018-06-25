<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link rel="stylesheet" href="../css/manage.css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/editormd.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/blog.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/editormd.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                dataType: "json",    //数据类型为json格式
                type: "GET",
                url: "<%=context %>/servlet/CategoryServlet?action=ajaxmain",
                success: function (data, textStatus) {
                    var main_id = $("#main_id");
                    main_id.empty();
                    main_id.append('<option value="0">一级分类</option>');
                    $.each(data, function (key, val) {
                        main_id.append('<option value="' + key + '">' + val + '</option>');
                    })
                },
                error: function (xhr, status, errMsg) {
                    alert("获取分类失败");
                }
            })
        });

        function getSubCatagory() {
            var main_id = $("#main_id").val();
            $.ajax({
                dataType: "json",    //数据类型为json格式
                type: "GET",
                url: "<%=context %>/servlet/CategoryServlet?action=ajaxsub",
                data: {"main_id": main_id},
                success: function (data, textStatus) {
                    var sub_id = $("#sub_id");
                    sub_id.empty();
                    sub_id.append('<option value="0">二级分类</option>');
                    $.each(data, function (key, val) {
                        sub_id.append('<option value="' + key + '">' + val + '</option>');
                    })
                },
                error: function (xhr, status, errMsg) {
                    alert("获取分类失败");
                }
            })
        };

        function beforeSubmit(form) {
            if (form.title.value == '') {
                alert('文章标题不能为空！');
                form.title.focus();
                return false;
            }
            if (form.test - editormd - markdown - doc.value == 0) {
                alert('文章内容不能为空！');
                form.category.focus();
                return false;
            }
            if (form.category.value == 0) {
                alert('文章类别不能为空！');
                form.category.focus();
                return false;
            }
            if (form.subtitle.value == '') {
                alert('文章摘要不能为空！');
                form.subtitle.focus();
                return false;
            }
            if (form.main_id.value == '') {
                alert('文章主分类不能为空！');
                form.subtitle.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div id="left-nav" class="col-md-2">
            <div class="author-nav">
                <img src="../img/avatar.jpg" alt="个人头像">
            </div>
            <div class="main-nav">
                <ul>
                    <a href="<%=context %>/servlet/PostlistServlet?role=1">
                        <li>所有文章</li>
                    </a>
                    <a href="<%=context %>/admin/addpost.jsp">
                        <li class="current">写文章</li>
                    </a>
                    <a href="<%=context %>/servlet/CategoryServlet?action=getall">
                        <li>分类</li>
                    </a>
                    <a href="https://changyan.kuaizhan.com/">
                        <li>评论</li>
                    </a>
                    <a href="https://changyan.kuaizhan.com/">
                        <li>留言</li>
                    </a>
                    <a href="<%=context %>/index.html">
                        <li>返回首页</li>
                    </a>
                </ul>
            </div>
        </div>
<c:forEach items="${result.dataList }" var="article">
        <form class="form-inline" action="<%=context %>/servlet/PosteditServlet?action=update&id=${article.id }" method="post"
              onSubmit="return beforeSubmit(this);">
            <div id="edit" class="col-md-8 col-xs-12">
                <h3>修改文章</h3>
                <hr/>
                <input type="text" id="article-title" name="title" class="form-control" placeholder="在此处输入标题"
                       required="" autofocus="" autocomplete="off" style="width:100%;" value="${article.title }">
                <div class="editormd" id="test-editormd">
                    <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc">${article.md_content }</textarea>
                    <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
                    <textarea class="editormd-html-textarea" name="text"></textarea>
                </div>
                <script type="text/javascript">
                    $(function () {
                        editormd("test-editormd", {
                            width: "100%",
                            height: 650,
                            syncScrolling: "single",
                            //你的lib目录的路径，我这边用JSP做测试的
                            path: "${context }/admin/lib/",
                            //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
                            saveHTMLToTextarea: true
                        });
                    });
                </script>
            </div>
            <div id="operate" class="col-md-2 col-xs-12">
                <h3>操作</h3>
                <hr/>
                <div class="publish">
                    <h5><input type="checkbox" class="input-control" name="top" value="${article.top }">&nbsp;将该文章置顶</h5>
                    <h4>发布时间：</h4>
                    <input type="text" name="year" size="4" maxlength="4" style="margin: 5px 0;" value="${fn:substring(article.createdate,0,4)}">
                    - <input type="text" name="month" size="2" maxlength="2" style="margin: 5px 0;" value="${fn:substring(article.createdate,5,7)}">
                    - <input type="text" name="day" size="2" maxlength="2" style="margin: 5px 0;" value="${fn:substring(article.createdate,8,10)}"><br/>
                    @ <input type="text" name="hour" size="2" maxlength="2" autocomplete="off" value="${fn:substring(article.createdate,11,13)}">
                    ： <input type="text" name="minute" size="2" maxlength="2" autocomplete="off" value="${fn:substring(article.createdate,14,16)}"><br/>
                </div>
                <h4>摘要：</h4>
                <textarea name="subtitle" rows="7" class="form-control" >${article.subtitle }</textarea>
                <h4>分类：</h4>
                <select class="form-control" id="main_id" name="main_id" style="margin:5px;"
                        onchange="getSubCatagory()">
                    <option value="0">一级分类</option>
                </select>
                <select class="form-control" id="sub_id" name="sub_id" style="margin:5px;">
                    <option value="0">二级分类</option>
                </select>
                <button type="submit" class="btn btn-primary" style="float: right;margin:5px;">发布</button>
            </div>
        </form>
</c:forEach>
    </div>
</div>
</body>
</html> 
