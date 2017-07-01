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
            <li><a href='#' class="active">${companyname}</a></li>
            <li><a href='/DomesticHelpers/index'>HOME</a></li>
            <li class='has-sub'><a href='#'>CATEGORIES</a>
                <ul>
                    <c:forEach items="${helpersTypeDAOList}"
                               var="helpersTypeDAO" varStatus="loop">
                        <li class=''><a
                                href='/DomesticHelpers/helperscategories?s=${helpersTypeDAO.ht_type}'>${helpersTypeDAO.ht_type}</a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            <li><a href='/DomesticHelpers/displaychitchat'>Message</a></li>
            <li style="float: right;">
                <form action="/DomesticHelpers/logout" method="post">
                    <div class="row" style="margin-top: 8px;">
                        <input type="text" value="${ci_username}" readonly class="col-md-6"/>&nbsp;&nbsp;
                        <input type="submit" value="Logout" class="btn btn-primary  col-md-3"/>
                    </div>

                </form>
            </li>
        </ul>
    </div>
</div>

</body>
<html>


