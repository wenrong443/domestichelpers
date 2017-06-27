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
    <title>Title</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="footer, address, phone, icons"/>
    <script>
        function viewBiodata(value) {
            window.location = "/DomesticHelpers/viewbiodata?id=" + value;
        }
    </script>
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<c:import url="/header"/>

<div class="container" style="margin-top: 24px;">
    <h5><b>${title}</b></h5>
    <div class="row">

        <c:forEach items="${helpersInfoDAOList}"
                   var="helpersInfoDAO" varStatus="loop">
            <div class="col-md-4" style="text-align: center; margin-top: 16px;">
                <img src="${helpersInfoDAO.hi_image}" height="220px" width="190px;" alt="${helpersInfoDAO.hi_fullname}'s Image">
                <p class="col-md-12"><b>${helpersInfoDAO.hi_fullname}</b></p>
                <p class="col-md-12" style="margin-top: -16px;"><i>${helpersInfoDAO.hi_category} (${helpersInfoDAO.hi_jobexperience})</i></p>
                <p class="col-md-12" style="margin-top: -18px;"><i>Can communicate with ${helpersInfoDAO.hi_languageknown}</i></p>
                <p class="col-md-12" style="margin-top: -4px;"><button type="button" onclick="viewBiodata(${helpersInfoDAO.hi_id})" class="btn btn-primary"> View Profile</button></p>

            </div>
        </c:forEach>
    </div>
</div>
<c:import url="/footer"/>
</body>
</html>
