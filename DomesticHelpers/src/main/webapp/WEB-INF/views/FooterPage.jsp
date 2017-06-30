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
    <link rel="stylesheet" href="/DomesticHelpers/resources/footerrelated/css/footer-distributed-with-contact-form.css">
    <link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">

    <script>
        function myFormSubmit() {
            document.getElementById("submitBtn").disabled = true;
            return true;
        }
    </script>
</head>

<body>
<c:import url="/importwebpagelibrary" />
<footer class="footer-distributed">

    <div class="footer-left">

        <h3>${companyname}</h3>

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
            <p><a href="mailto:support@domestichelpers.com">${email}</a></p>
        </div>

    </div>

    <div class="footer-right">

        <p>Contact Us</p>

        <form action="/DomesticHelpers/contactusprocess" method="post" onsubmit="myFormSubmit()">

            <input type="text" name="email" placeholder="Email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="abc@example.com"/>
            <textarea name="message" placeholder="Message"></textarea>
            <button type="submit" id="submitBtn">Send</button>

        </form>

    </div>

</footer>

</body>

</html>
