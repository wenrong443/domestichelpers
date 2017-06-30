<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wenro
  Date: 6/28/2017
  Time: 12:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<c:import url="/header" />
<form action="/DomesticHelpers/registerprocess" method="POST">
    <div class="container">

        <div class="row">
            <div class="col-md-6 col-centered">
                <p style="font-size: 1.8em;">
                    <b>Register New Account</b>
                </p>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-md-6 col-centered">
                <table class="col-md-12">
                    <tbody style="font-size: 0.8em;">
                    <tr>
                        <td colspan="2"><hr></td>
                    </tr>

                    <!-- Email & Verify Email session -->
                    <tr>
                        <td style="height: 30px;"><b style="float: right;">FULL NAME:</b></td>
                        <td><input type="text" name="fullname" id="fullname"
                                   style="margin-left: 12px;" required /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><hr></td>
                    </tr>

                    <!-- First Name & Last Name session -->
                    <tr>
                        <td style="height: 30px;"><b style="float: right;">USERNAME:</b></td>
                        <td><input type="text" name="username" id="username"
                                   style="margin-left: 12px;" required /></td>
                    </tr>
                    <tr>
                        <td style="height: 30px;"><b style="float: right;">PASSWORD:</b></td>
                        <td><input type="password" name="password" id="password"
                                   style="margin-left: 12px;" required /></td>
                    </tr>
                    <tr>
                        <td style="height: 30px;"><b style="float: right;">Email:</b></td>
                        <td><input type="text" name="email" id="email"
                                   style="margin-left: 12px;" required /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><div style="float: right;">
                            <input type="submit" value="Register"
                                   class="btn btn-primary active" />
                        </div></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form>
<c:import url="/footer" />
</body>
</html>
