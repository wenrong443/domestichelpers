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
    <title>Add New Domestic Helper Page</title>
</head>
<body>
<c:import url="/importwebpagelibrary"/>
<c:import url="/headerforadmin"/>
<div class="container">
    <div class="col-md-8">
        <h4>Add New Domestic Helper</h4>
        <form action="addnewdomestichelperprocess" method="post" autocomplete="off">
            <table class="table">
                <tr>
                    <td><b>Full Name:</b></td>
                    <td><input type="text" name="ht_fullname" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Date of Birth:</b></td>
                    <td><input type="date" name="ht_dateofbirth" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Place of Birth:</b></td>
                    <td><input type="text" name="ht_placeofbirth" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Language known:</b></td>
                    <td><input type="text" name="ht_languageknown" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Job Experience:</b></td>
                    <td><input type="text" name="ht_jobexperience" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Races:</b></td>
                    <td><input type="text" name="ht_races" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Religion:</b></td>
                    <td><input type="text" name="ht_religion" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Service Charge:</b></td>
                    <td>$ &nbsp;<input type="number" step="0.1" value="50.0" name="ht_servicecharge" class="col-md-11" required></td>
                </tr>
                <tr>
                    <td><b>Profile Picture:</b></td>
                    <td>
                        <input type="file" class="form-control"
                               id="ht_image_selector"
                               name="ht_image_selector"
                               value="" accept="image/jpeg"
                               onchange="loadImageFileAsURL()" class="col-md-12" required> <input type="text" name="ht_image" id="ht_image" hidden>
                    </td>
                </tr>
                <tr>
                    <td><b>Login Username:</b></td>
                    <td><input type="text" name="ht_username" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Login Password:</b></td>
                    <td><input type="password" name="ht_password" class="col-md-12" required></td>
                </tr>
                <tr>
                    <td><b>Helper Category:</b></td>
                    <td>
                        <select name="ht_category">
                            <c:forEach items="${helpersTypeDAOList}"
                                       var="helpersTypeDAO" varStatus="loop">
                                <option vale="${helpersTypeDAO.ht_type}">${helpersTypeDAO.ht_type}</option>
                            </c:forEach>
                        </select>
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
