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
    <title>List of Advertisement</title>
    <script>
        function addNew() {
            window.location = "/DomesticHelpers/adddnewadvertisement";
        }
    </script>
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<c:import url="/headerforadmin"/>
<div class="container">
    <h3>List of Advertisement&nbsp;<button type="button" onclick="addNew()"><i class="fa fa-plus" aria-hidden="true"></i></button>
    </h3>
    <table class="table">
        <thead>
        <th>No</th>
        <th>Title</th>
        <th>Content</th>
        <th>Image</th>
        <th>Modify?</th>
        <th>Delete?</th>
        </thead>
        <tbody>
        <c:forEach items="${advertisementDAOList}"
                   var="advertisementDAO">
            <tr>
                <td>${advertisementDAO.a_id}</td>
                <td>${advertisementDAO.a_title}</td>
                <td>${advertisementDAO.a_content}</td>
                <td><img src="${advertisementDAO.a_image}" height="100" width="100" /> </td>
                <td>
                    <form action="/DomesticHelpers/modifyadvertisement" method="post">
                        <input type="text" name="id" value="${advertisementDAO.a_id}" hidden/>
                        <input type="submit" value="Modify" class="btn btn-primary"/>
                    </form>
                </td>
                <td>
                    <form action="/DomesticHelpers/deleteadvertisement" method="post">
                        <input type="text" name="id" value="${advertisementDAO.a_id}" hidden/>
                        <input type="submit" value="Delete" class="btn btn-danger"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
