<%--
  Created by IntelliJ IDEA.
  User: wenro
  Date: 6/26/2017
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/DomesticHelpers/resources/headerrelated/styles.css">
    <script src="/DomesticHelpers/resources/headerrelated/script.js"></script>
    <title>Header Page</title>
</head>

<body>
<c:import url="/importwebpagelibrary"/>

<div class="col-md-12">
    <div id='cssmenu' style="z-index: 999">
        <ul>
            <li><a href='#' class="active">${companyname}'s Admin Control</a></li>
            <li><a href='/DomesticHelpers/listofdomestichelpers'>List of Domestic Helper</a></li>
            <li><a href='/DomesticHelpers/listoffeedback'>List of Feedback</a></li>
            <li><a href='/DomesticHelpers/listofadvertisement'>List of Advertisement</a></li>
            <li><a href='/DomesticHelpers/sendemailtocustomer'>Broadcast  Email To All Customer</a></li>
            <li><a href='/DomesticHelpers/adminlogout'>Logout</a></li>

        </ul>
    </div>
</div>

</body>
<html>


