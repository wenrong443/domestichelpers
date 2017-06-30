<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wenro
  Date: 6/28/2017
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Feedback</title>
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
<c:import url="/headerforadmin"/>
<div class="container">
    <h3>List of Feedback Helpers
    </h3>
    <table class="table">
        <thead>
        <th>No</th>
        <th>Email</th>
        <th>Message</th>
        <th>Reply</th>
        </thead>
        <tbody>
        <c:forEach items="${feedbackDAOList}"
                   var="feedbackDAO" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>${feedbackDAO.f_email}</td>
                <td>${feedbackDAO.f_message}</td>
                <td>
                    <form action="/DomesticHelpers/replyfeedback" method="post">
                        <input type="text" name="id" value="${feedbackDAO.f_id}" hidden/>
                        <textarea name="replymessage" rows="5"></textarea>
                        <br>
                        <input type="submit" value="Reply" class="btn btn-primary"/>
                    </form>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
