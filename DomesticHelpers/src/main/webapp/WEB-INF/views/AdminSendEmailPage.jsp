<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Wen Rong
  Date: 29/6/2017
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Broadcast  Email To All Customer</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<c:import url="/headerforadmin"/>
<div class="container">
    <div class="col-md-12 col-centered">
        <h2 style="text-align: center">Broadcast  Email To All Customer</h2>
        <form action="/DomesticHelpers/sendemailtocustomerprocess" method="post">
            <table class="col-md-12 col-centered" style="margin-top: 30px;">
                <tr>
                    <td style="height: 40px;"><b>Title:</b></td>
                </tr>
                <tr>
                    <td style="height: 40px;"><input type="text" name="title" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td style="height: 40px;"><b>Message:</b></td>
                </tr>
                <tr>
                    <td style="height: 40px;"><textarea name="message" style="height: 120px;" class="col-md-12" required></textarea></td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td style="height: 40px;"><input type="submit" value="Send"
                                                     class="btn btn-primary active btn-block" style="margin-top: 12px;">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<c:import url="/footer"/>
</body>
</html>
