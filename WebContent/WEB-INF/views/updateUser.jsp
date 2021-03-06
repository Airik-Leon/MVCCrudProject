<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin: Update User</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="SharedViews/Layout_StyleSheets.jsp" %>

</head>
<body>
    <%@ include file="SharedViews/Layout_AdminHome.jsp" %>
    <ul class="nav nav-tabs">
        <li class="nav-item"><a class="nav-link" href="splash.do">Home</a>
        </li>
        <li class="nav-item"><a class="nav-link"
            href="goToAdmin.do">Admin Home</a>
        <li class="nav-item"><a class="nav-link active"
            href="goToCreateUser.do">Create User</a></li>
        <li class="nav-item"><a class="nav-link active"
            href="goToUserInfo.do">Get User</a></li>
        <li class="nav-item"><a class="nav-link"
            href="goToCreatePost.do"> Create Post</a></li>
    </ul>
    <div class="row justify-content-center" style="margin-top: 50px;">
        <form:form action="updateUser.do" method="POST"
            modelAttribute="user">
            <ul class="list-group">
                <li class="list-group-item">User id:<form:input
                        type="text" name="id" path="id"
                        value="${result.id}" readonly="true" />
                </li>
                <li class="list-group-item">Username:<form:input
                        type="text" name="userName" path="userName"
                        value="${result.userName}" />
                </li>
                <li class="list-group-item">First Name:<form:input
                        type="text" name="FirstName" path="firstName"
                        value="${result.firstName}" />
                </li>
                <li class="list-group-item">Last Name: <form:input
                        type="text" name="lastName" path="lastName"
                        value="${result.lastName}" />
                </li>
                <li class="list-group-item">Password: <form:input
                        type="text" name="password" path="password"
                        value="${result.password}" />
                </li>
                <li class="list-group-item">Account Origin:
                    ${result.accountOrigin}</li>
                <li class="row justify-content-center"><input
                    class="btn btn-primary" type="submit"
                    value="Update user" id="btnAdminUpdateUser" /></li>
            </ul>
        </form:form>
    </div>
    <%@ include file="SharedViews/Layout_footer.jsp" %>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
    integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
    crossorigin="anonymous"></script>
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
    integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
    crossorigin="anonymous"></script>
</html>



