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
    <title>List of Domestic Helpers</title>
    <script>
        function addNew() {
            window.location = "/DomesticHelpers/adddnewdomestichelper";
        }
    </script>
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<c:import url="/headerforadmin"/>
<div class="container">
    <h3>List of Domestic Helpers
        <button type="button" onclick="addNew()"><i class="fa fa-plus" aria-hidden="true"></i>
        </button>
    </h3>
    <table class="table">
        <thead>
        <th>No</th>
        <th>Helper's Full Name</th>
        <th>Category</th>
        <th>Job Experience</th>
        <th>Service Charge</th>
        <th>Modify?</th>
        <th>Delete?</th>
        </thead>
        <tbody>
        <c:forEach items="${helpersInfoDAOList}"
                   var="helpersInfoDAO" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>${helpersInfoDAO.hi_fullname}</td>
                <td>${helpersInfoDAO.hi_category}</td>
                <td>${helpersInfoDAO.hi_jobexperience}</td>
                <td>$ ${helpersInfoDAO.hi_servicecharge}</td>
                <td>
                    <form action="/DomesticHelpers/modifydomestichelperbiodata" method="post">
                        <input type="text" name="id" value="${helpersInfoDAO.hi_id}" hidden/>
                        <input type="submit" value="Modify" class="btn btn-primary"/>
                    </form>
                </td>
                <td>
                    <form action="/DomesticHelpers/deletedomestichelperbiodata" method="post">
                        <input type="text" name="id" value="${helpersInfoDAO.hi_id}" hidden/>
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
