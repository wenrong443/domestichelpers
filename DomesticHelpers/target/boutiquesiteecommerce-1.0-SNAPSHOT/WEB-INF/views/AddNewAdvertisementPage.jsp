<%--
  Created by IntelliJ IDEA.
  User: wenro
  Date: 6/27/2017
  Time: 1:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add New Advertisement Page</title>
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<c:import url="/headerforadmin"/>
<div class="container">
    <div class="col-md-8">
        <h4>Add New Domestic Helper</h4>
        <form action="adddnewadvertisementprocess" method="post" autocomplete="off">
            <table class="table">
                <tr>
                    <td><b>Title:</b></td>
                    <td><input type="text" name="title" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Content:</b></td>
                    <td><input type="text" name="content" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Image:</b></td>
                    <td>
                        <input type="file" class="form-control"
                               id="ht_image_selector"
                               name="ht_image_selector"
                               value="" accept="image/jpeg"
                               onchange="loadImageFileAsURL()" class="col-md-12" required> <input type="text" name="ht_image" id="ht_image" hidden>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" class="btn btn-primary" value="Submit" /> &nbsp; <input type="reset" value="Reset" class="btn btn-danger" /></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script>
    function loadImageFileAsURL() {
        var filesSelected = document
            .getElementById("ht_image_selector").files;
        if (filesSelected.length > 0) {
            var fileToLoad = filesSelected[0];

            var fileReader = new FileReader();

            fileReader.onload = function (fileLoadedEvent) {
                var textAreaFileContents = document
                    .getElementById("ht_image");

                textAreaFileContents.value = fileLoadedEvent.target.result;
            };
            fileReader.readAsDataURL(fileToLoad);
        }
    }
</script>
</body>
</html>
