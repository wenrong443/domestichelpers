<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wenro
  Date: 6/28/2017
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
    <script>
        function checkErrorMessage() {
            var parts = window.location.search.substr(1).split("&");
            var $_GET = {};
            for (var i = 0; i < parts.length; i++) {
                var temp = parts[i].split("=");
                $_GET[decodeURIComponent(temp[0])] = decodeURIComponent(temp[1]);
            }

            if ($_GET['msg'] != null) {
                alert($_GET['msg']);
            }
        }

        window.onload = function () {
            checkErrorMessage();
        }
    </script>
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<div class="container col-md-6 col-offset-md-3">
    <div class="col-md-12" style="text-align: center;">
        <h4 style="margin-top: 120px;">Admin Login</h4>
        <form action="/DomesticHelpers/adminloginprocess" method="post">
            <table class="table col-md-12">
                <tr><td><b>Username:</b></td><td><input type="text" name="username" required></td></tr>
                <tr><td><b>Password:</b></td><td><input type="password" name="password" required></td></tr>
                <tr><td></td><td><input type="submit" value="Login" class="btn btn-primary"></td></tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
