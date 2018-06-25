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
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link rel="stylesheet" href="../css/manage.css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/blog.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script>
        function deleteCategory(id, count, type) {
            if (confirm("确定删除分类？")) {
                if (count > 0)
                    alert("该分类不为空，无法删除")
                else {
                    $.ajax({
                        type: "POST",
                        async: true,
                        url: "<%=context %>/servlet/CategoryEditServlet",
                        data: {
                            "action": "delete",
                            "type": type,
                            "id": id
                        },
                        success: function (data) {
                            alert(data);
                            window.location.reload();
                        },
                        error: function () {
                            alert("请求失败");
                        },
                        dataType: "text"
                    })
                }
            }
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
                        <li>写文章</li>
                    </a>
                    <a href="#">
                        <li class="current">分类</li>
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
        <div id="addCty" class="col-md-3 col-xs-12">
            <h3>添加</h3>
            <hr/>
            <div class="form-group">
                <form action="<%=context %>/servlet/CategoryEditServlet?action=add" method="post">
                    <h4>类别名称：</h4>
                    <input type="text" class="form-control" name="subcategory">
                    <h4>父节点：</h4>
                    <select class="form-control" name="maincategory">
                        <option value="0" selected="">无</option>
                        <c:forEach items="${mainCategory }" var="maincategory">
                            <option value="${maincategory.id }"> ${maincategory.name } </option>
                        </c:forEach>
                    </select>
                    <br/>
                    <button type="submit" class="btn btn-primary" style="float: right;">添加分类</button>
                </form>
            </div>
        </div>
        <div id="ciyList" class="col-md-7 col-xs-12">
            <h3>管理</h3>
            <hr/>
            <form class="form-inline" action="#" method="POST">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>分类名称</th>
                        <th>文章数</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--主分类--%>
                    <c:forEach items="${mainCategory }" var="maincategory">
                        <tr>
                            <td>${maincategory.name }</td>
                            <td>${maincategory.main_count }</td>
                            <td>
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#mainModal">修改
                                </button>
                                <button type="button" class="btn btn-danger"
                                        onclick="deleteCategory(${maincategory.id },${maincategory.main_count },'main')">
                                    删除
                                </button>
                                <input type="text" class="form-control" name="main_id" value="${maincategory.id }"
                                       style="display: none">
                            </td>
                        </tr>
                    </c:forEach>
                    <%--子分类--%>
                    <c:forEach items="${category }" var="subcategory">
                        <tr>
                            <td>${subcategory.sub_name }</td>
                            <td>${subcategory.sub_count }</td>
                            <td>
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#subModal">修改
                                </button>
                                <button type="button" class="btn btn-danger"
                                        onclick="deleteCategory(${subcategory.sub_id },${subcategory.sub_count },'sub')">
                                    删除
                                </button>
                                <input type="text" class="form-control"  name="main_id" value="${subcategory.main_id }"
                                       style="display: none">
                                <input type="text" class="form-control"  name="sub_id" value="${subcategory.sub_id }"
                                       style="display: none">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
        <%--修改主分类模态框--%>
        <div class="modal fade" id="mainModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                        <h4 class="modal-title">修改分类</h4>
                    </div>
                    <form action="<%=context %>/servlet/CategoryEditServlet?action=update" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="control-label">类别名称:</label>
                                <input type="text" class="form-control" name="maincategoryname" id="updatemainname">
                            </div>
                            <input type="text" class="form-control" name="main_id" id="updatemainid"
                                   style="display: none">
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">提交修改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%--修改子分类模态框--%>
        <div class="modal fade" id="subModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                        <h4 class="modal-title" id="exampleModalLabel">修改分类</h4>
                    </div>
                    <form action="<%=context %>/servlet/CategoryEditServlet?action=update" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="control-label">类别名称:</label>
                                <input type="text" class="form-control" name="subcategory" id="updatesubname">
                            </div>
                            <div class="form-group">
                                <label class="control-label">父节点:</label>
                                <select class="form-control" name="maincategory" id="updatemain">
                                    <option value="0" selected="">无</option>
                                    <c:forEach items="${mainCategory }" var="maincategory">
                                        <option value="${maincategory.id }"> ${maincategory.name } </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="text" class="form-control" name="main_id" id="updatemainid2"
                                   style="display: none">
                            <input type="text" class="form-control" name="sub_id" id="updatesubid2"
                                   style="display: none">
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">提交修改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            $('#mainModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget) // 触发事件的按钮
                var main_name = button.parent().prev().prev();
                var main_id = button.next().next();
                console.log(main_id.val());
                $('#updatemainname').val(main_name.text());
                $('#updatemainid').val(main_id.val());
            });
            $('#subModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget) // 触发事件的按钮
                var sub_name = button.parent().prev().prev();
                var main_id = button.next().next();
                var sub_id = button.next().next().next();
                console.log(main_id.val()+"-"+sub_id.val());
                $('#updatesubname').val(sub_name.text());
                $('#updatemainid2').val(main_id.val());
                $('#updatesubid2').val(sub_id.val());
            })
        </script>
    </div>
</div>
</body>

</html>