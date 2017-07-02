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
    <title>List of Booking</title>
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<c:import url="/headerforadmin"/>
<div class="container">
    <h3>List of Booking
    </h3>
    <table class="table">
        <thead>
        <th>No</th>
        <th>Helper's Info</th>
        <th>Customer's Info</th>
        <th>Duration</th>
        </thead>
        <tbody>
        <c:forEach items="${bookingDAOList}"
                   var="bookingDAO" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>
                        ${bookingDAO.helper_fullname} <br>
                        <small>Username: ${bookingDAO.helper_username}</small>
                </td>
                <td>
                        <b>${bookingDAO.b_personalname}</b><br><br>
                        ${bookingDAO.b_address}<br>
                        ${bookingDAO.b_state}<br>
                        ${bookingDAO.b_city}<br>
                        ${bookingDAO.b_zipcode}<br>
                        ${bookingDAO.b_country}<br>
                </td>
                <td>Start Date: ${bookingDAO.b_startdate}<br>End Date: ${bookingDAO.b_enddate} <br> Duration: ${bookingDAO.b_duration} day(s)</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
