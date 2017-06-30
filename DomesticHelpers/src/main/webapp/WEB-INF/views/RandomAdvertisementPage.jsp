<%--
  Created by IntelliJ IDEA.
  User: wenro
  Date: 6/26/2017
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="refresh" content="5">
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<div class="container">
    <div class="row">
        <div class="col-xs-5">
            <img src="${advertisementDAO.a_image}" height="90" width="90"/>

        </div>
        <div class="col-xs-5">
            <p style="text-align: center"><b>${advertisementDAO.a_title}</b></p>

        </div>
        <div class="col-xs-12">
            <div style="white-space: normal;"><small>${advertisementDAO.a_content}</small></div>
        </div>
    </div>
</div>
</body>
</html>
