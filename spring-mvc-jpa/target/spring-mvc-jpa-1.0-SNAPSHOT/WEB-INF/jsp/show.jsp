<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>数据展示</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<button type="button" class="btn btn-blue nav-external animated hiding" id="add" hidden="true">点击新增</button>
<body>
<div class="modal fade" id="addResume" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h4 class="modal-title" id="myModalLabel">新增resume</h4>
            </div>
            <div class="modal-body">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-name">姓名：</span>
                    <input type="text" class="input-sm" id="name" aria-describedby="basic-name">
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-address">地址：</span>
                    <input type="text" class="input-sm" id="address" aria-describedby="asic-address">
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-phone">电话：</span>
                    <input type="text" class="input-sm" id="phone" aria-describedby="basic-phone">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="submit">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="updateResume" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
            </div>
            <div class="modal-body">
                <input type="text" class="input-sm" id="idupdate" hidden="hidden" aria-describedby="basic-name">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-name-up">姓名：</span>
                    <input type="text" class="input-sm" id="nameupdate" aria-describedby="basic-name">
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-address-up">地址：</span>
                    <input type="text" class="input-sm" id="addressupdate" aria-describedby="asic-address">
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-phone-up">电话：</span>
                    <input type="text" class="input-sm" id="phoneupdate" aria-describedby="basic-phone">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="submitupdate">确定</button>
            </div>
        </div>
    </div>
</div>

<table class="table table-bordered">
    <caption>边框表格布局</caption>
    <thead>
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>地址</th>
        <th>电话</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${data}" var="ds">
        <tr id="${ds.id}">
            <td>${ds.id}</td>
            <td>${ds.name}</td>
            <td>${ds.address}</td>
            <td>${ds.phone}</td>
            <td>
                <button type="button" id="${ds.id}update" class="btn btn-default update">修改</button>
                <button type="button" id="${ds.id}delete" class="btn btn-default delete">删除</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript">

    $(document).ready(function () {
        document.getElementById("add").onclick = function () {
            $('#addResume').modal('show');
        }
        document.getElementById("submit").onclick = function () {
            $('#addResume').modal('hide');

            var name = $('#name').val();
            console.log(name)
            var address = $('#address').val();
            var phone = $('#phone').val();
            $.ajax(
                {
                    url: "http://localhost:8080/resume/conservation",
                    data: {"name": name, "address": address, "phone": phone},
                    type: "post",
                    success: function (data) {
                        alert('操作成功');
                    },
                    error: function () {
                        alert('请求出错');
                    },
                    complete: function () {
                    }
                });
        }
    });

    $(document).ready(function () {
        document.getElementsByClassName("update").onclick = function () {
            $('#updateResume').modal('show');
        }
        document.getElementById("submitupdate").onclick = function () {
            var id = $("#idupdate").val()
            $('#updateResume').modal('hide');
            var name = $('#nameupdate').val();
            console.log(name)
            var address = $('#addressupdate').val();
            var phone = $('#phoneupdate').val();
            $.ajax(
                {
                    url: "http://localhost:8080/resume/conservation",
                    data: {"id": id, "name": name, "address": address, "phone": phone},
                    type: "post",
                    success: function (data) {
                        alert('操作成功');
                        window.location.reload(true);
                    },
                    error: function () {
                        alert('请求出错');
                    },
                    complete: function () {
                    }
                });

        }
    });


    window.onload = function () {
        var oLis1 = document.getElementsByClassName("update");
        for (var index = 0; index < oLis1.length; index++) {
            oLis1[index].onclick = function () {
                var id = this.id.replace(/update/, "");
                var element = document.getElementById(id);
                console.log(element)
                var node = element.getElementsByTagName("td");
                var name = node[1].innerHTML;
                var address = node[2].innerHTML;
                var phone = node[3].innerHTML;
                $("#idupdate").attr("value", id);
                $("#nameupdate").attr("value", name);
                $("#addressupdate").attr("value", address);
                $("#phoneupdate").attr("value", phone);
                $('#updateResume').modal('show');
            }
        }

        var oLis = document.getElementsByClassName("delete");
        for (var index = 0; index < oLis.length; index++) {
            oLis[index].onclick = function () {
                var id = this.id.replace(/delete/, "");
                console.log(id)
                $.ajax(
                    {
                        url: "http://localhost:8080/resume/" + id,
                        type: "get",
                        success: function (data) {
                            alert('操作成功');
                            window.location.reload(true);
                        },
                        error: function () {
                            alert('请求出错');
                        },
                        complete: function () {
                        }
                    });
            }
        }
    }


</script>
</body>
</html>