<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wenro
  Date: 6/28/2017
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <script>
        function myFormSubmit() {
            document.getElementById("submitBtn").disabled = true;
            return true;
        }
    </script>
</head>
<body>
<c:import url="/importwebpagelibrary"/>

    <div class="container col-md-6">
        <h4><b style="text-align: center; margin-top: 16px;">PayPal</b></h4>
        <form action="/DomesticHelpers/checkoutprocess" method="post"  onsubmit="myFormSubmit()">
            <input type="text" name="id" value="${id}" hidden />
            <input type="text" name="startdate" value="${startdate}" hidden />
            <input type="text" name="totaldays" value="${totaldays}" hidden />
            <input type="text" name="email" value="${email}" hidden />
            <input type="text" name="total" value="${total}" hidden />
            <input type="text" name="personalname" value="${personalname}" hidden />
            <input type="text" name="address" value="${address}" hidden />
            <input type="text" name="country" value="${country}" hidden />
            <input type="text" name="state" value="${state}" hidden />
            <input type="text" name="city" value="${city}" hidden />
            <input type="text" name="zipcode" value="${zipcode}" hidden />

            <table class="table">
                <tr><td><b>Card Number:</b></td><td><input type="text" name="cardno" placeholder="Card Number" required></td></tr>
                <tr><td><b>Expire Date:</b></td><td><input type="text" name="expiredate" placeholder="mm/yy" required></td></tr>
                <tr><td><b>CVC Number:</b></td><td><input type="text" name="cvc" placeholder="xxx" required></td></tr>
                <tr><td><b>Payment Amount:</b></td><td><input type="text" name="paymentamount" value="$ ${total}" readonly></td></tr>
                <tr><td></td><td><button type="submit" class="btn btn-primary active"  id="submitBtn">
                    <i class="fa fa-paypal" aria-hidden="true"></i>&nbsp;&nbsp;Pay
                </button></td></tr>
            </table>

        </form>
    </div>
</body>
</html>
