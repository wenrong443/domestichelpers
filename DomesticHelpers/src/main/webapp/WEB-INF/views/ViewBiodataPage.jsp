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
    <title>Domestic Helper's Biodata</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="footer, address, phone, icons"/>
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<c:import url="/header"/>

<div class="container" style="margin-top: 24px;">
    <div class="col-md-6">
        <div class="row">
            <h4><b>${helpersInfoDAO.hi_fullname}'s Biodata</b></h4>
        </div>
        <div class="row"  style="margin-top: 12px;">
            <img src="${helpersInfoDAO.hi_image}" height="220px" width="190px;"
                 alt="${helpersInfoDAO.hi_fullname}'s Image"/>
        </div>
        <div class="row">
            <table class="table">
                <tbody>
                <tr>
                    <td><b>Full Name:</b></td>
                    <td>${helpersInfoDAO.hi_fullname}</td>
                </tr>
                <tr>
                    <td><b>Helper Category:</b></td>
                    <td>${helpersInfoDAO.hi_category}</td>
                </tr>
                <tr>
                    <td><b>Language Known:</b></td>
                    <td>${helpersInfoDAO.hi_languageknown}</td>
                </tr>
                <tr>
                    <td><b>Job Experience:</b></td>
                    <td>${helpersInfoDAO.hi_jobexperience}</td>
                </tr>
                <tr>
                    <td><b>Date of Birth:</b></td>
                    <td>${helpersInfoDAO.hi_dateofbirth}</td>
                </tr>
                <tr>
                    <td><b>Place of Birth:</b></td>
                    <td>${helpersInfoDAO.hi_placeofbirth}</td>
                </tr>
                <tr>
                    <td><b>Races</b></td>
                    <td>${helpersInfoDAO.hi_races}</td>
                </tr>
                <tr>
                    <td><b>Religion:</b></td>
                    <td>${helpersInfoDAO.hi_religion}</td>
                </tr>
                <tr>
                    <td><b>Service Charge:</b></td>
                    <td>$ ${helpersInfoDAO.hi_servicecharge} /day</td>
                </tr>
                <form action="/DomesticHelpers/booking" method="post">
                    <input name="id" type="text" value="${helpersInfoDAO.hi_id}" hidden>

                    <tr>
                        <td><b>Start Date:</b></td>
                        <td><input name="startdate" type="date" class="col-md-10"/></td>
                    </tr>
                    <tr>
                        <td><b>Duration:</b></td>
                        <td><input name="totaldays" type="number" min="1" value="1" class="col-md-10"/><i>&nbsp;
                            Days</i></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Book" class="btn btn-primary col-md-12"/></td>
                    </tr>

                </form>
                </tbody>
            </table>
        </div>
        <div class="row">

        </div>
    </div>
</div>
<c:import url="/footer"/>
</body>
</html>
