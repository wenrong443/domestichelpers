<%--
  Created by IntelliJ IDEA.
  User: wenro
  Date: 6/28/2017
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Success</title>
    <script>
        window.setTimeout(function(){

            // Move to a new location or you can do something else
            window.location.href = "/DomesticHelpers/index?msg=Payment%20success.";

        }, 5000);

    </script>
</head>
<body>
    <h4>Payment success, redirecting to index in 5 seconds ...</h4>
</body>
</html>
