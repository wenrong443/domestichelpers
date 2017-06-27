<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <title>Footer Page</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/DomesticHelpers/resources/footerrelated/css/demo.css">
    <link rel="stylesheet" href="/DomesticHelpers/resources/footerrelated/css/footer-distributed-with-address-and-phones.css">
    <link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">
</head>

<body>
<c:import url="/importwebpagelibrary" />
<footer class="footer-distributed">

    <div class="footer-left">

        <h3>Company<span>logo</span></h3>

        <p class="footer-company-name">${companyname} &copy; 2017</p>
    </div>

    <div class="footer-center">

        <div>
            <i class="fa fa-map-marker"></i>
            <p><span>88 Jalan Revolution</span> Malacca, Malaysia</p>
        </div>

        <div>
            <i class="fa fa-phone"></i>
            <p>+60 123 456 789</p>
        </div>

        <div>
            <i class="fa fa-envelope"></i>
            <p><a href="mailto:support@domestichelpers.com">support@domestichelpers.com</a></p>
        </div>

    </div>

    <div class="footer-right">
        <p class="footer-company-about">
            <span>About the company</span>
            Domestic helper is a company to help user can easily find domestic helper.
        </p>
    </div>

</footer>

</body>

</html>
