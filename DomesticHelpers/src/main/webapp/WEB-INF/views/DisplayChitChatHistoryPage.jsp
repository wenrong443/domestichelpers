<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wenro
  Date: 7/1/2017
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        window.onload = function () {
            window.location = '#bottom';
        }
    </script>
</head>
<body>
<c:import url="/importwebpagelibrary"/>
    <table class="col-md-12">
        ${msg}
    </table>
    <div id="bottom"></div>
</body>
</html>
