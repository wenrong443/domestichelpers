<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wenro
  Date: 7/1/2017
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chit Chat</title>

    <script>

        var sender_id = "${sender_id}";
        var receiver_id = "";

        function sendingChitChat() {


            $.get("/DomesticHelpers/sendchitchat?sender=" + sender_id + "&receiver=" + receiver_id + "&message=" + document.getElementById("message").value,
                function (response) {
                    console.log("msg send from " + sender_id + " to " + receiver_id);
                });

            document.getElementById("myForm").reset();
            return false;
        }

        var myVar = setInterval(function () {
            $.get("/DomesticHelpers/retrievechitchatlist?id=" + sender_id,
                function (response) {
                    document.getElementById("chitchatlist").innerHTML = response;
                });
        }, 10000);

        window.addEventListener('load',
            function () {
                document.getElementById("submitBtn").disabled = true;
                $.get("/DomesticHelpers/retrievechitchatlist?id=" + sender_id,
                    function (response) {
                        document.getElementById("chitchatlist").innerHTML = response;
                    });
            }, false);

        function changeReceiverId(value) {
            receiver_id = value;
            document.getElementById("my_iframe").src = "/DomesticHelpers/retrievechitchathistory?sender_id=" + sender_id + "&receiver_id=" + receiver_id;
            console.log("/DomesticHelpers/retrievechitchathistory?sender_id=" + sender_id + "&receiver_id=" + receiver_id);
            document.getElementById("submitBtn").disabled = false;
        }

        var myVar2 = setInterval(function () {
            document.getElementById("my_iframe").src = "/DomesticHelpers/retrievechitchathistory?sender_id=" + sender_id + "&receiver_id=" + receiver_id;
            console.log("/DomesticHelpers/retrievechitchathistory?sender_id=" + sender_id + "&receiver_id=" + receiver_id);
        }, 5000);

    </script>
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<c:import url="/header"/>
<div class="container">
    <div class="row">
        <div class="col-md-3" id="chitchatlist">
            ${firsttimechitchatlist}
        </div>
        <div class="col-md-9">
            <div class="col-md-12">
                <iframe src="" class="col-md-12" height="300" id="my_iframe">

                </iframe>
            </div>
            <div class="col-md-12">
                <div class="col-md-12" style="visibility: hidden">
                    <iframe name="my_iframe2" height="0" width="0">
                    </iframe>
                </div>
                <div class="col-md-12">
                    <form action="/DomesticHelpers/sendchitchat" method="post" id="myForm"
                          onsubmit="return sendingChitChat()" class="col-md-12" style="margin-top: 12px;">
                        <input name="sender" id="sender" type="text" hidden>
                        <input name="receiver" id="receiver" type="text" hidden>
                        <input name="message" id="message" class="col-md-12" placeholder="Please type your message here">

                        <input value="Submit" type="submit" id="submitBtn" class="btn btn-primary btn-block" style="margin-top: 6px;">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
