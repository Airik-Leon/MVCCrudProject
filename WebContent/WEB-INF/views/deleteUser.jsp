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
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
    integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
    crossorigin="anonymous">
<link rel="stylesheet" href="./css/site.css" />
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="nav-item"><a class="nav-link "
            href="goToAdmin.do">Admin Home</a>
        <li class="nav-item"><a class="nav-link "
            href="goToCreateUser.do">Create User</a></li>
        <li class="nav-item"><a class="nav-link active" href="#">Get
                User</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Update
                User</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Delete
                User</a></li>
    </ul>
    <div class="row justify-content-center" style="margin-top:50px;">
        <form:form action="updateUser.do" method="POST" modelAttribute="toBeDeleted">
            <ul class="list-group">
                <li class="list-group-item">User id:<form:input 
                    type="text" name="id" path="id" value="${user.id}" readonly="true"/>
                </li>
                <li class="list-group-item">Username:<form:input
                    type="text" name="userName" path="userName"
                    value="${user.userName}" readonly="true"/>
                </li>
                <li class="list-group-item">First Name:<form:input
                    type="text" name="FirstName" path="firstName"
                    value="${user.firstName}" readonly="true"/>
                </li>
                <li class="list-group-item">Last Name: <form:input
                    type="text" name="lastName" path="lastName"
                    value="${user.lastName}" readonly="true"/>
                </li>
                <li class="list-group-item">Password: <form:input
                    type="text" name="password" path="password"
                    value="${user.password}" readonly="true"/>
                </li>
                <li class="list-group-item">Account Origin:<form:input
                    type="text" name="accountOrigin" path="accountOrigin"
                    value="${user.accountOrigin}" readonly="true"/>
                </li>
                <li class="row justify-content-center"><input class="btn btn-danger" type="submit"
                    value="Delete user" id="btnAdminUpdateUser" /></li>
            </ul>
        </form:form>
    </div>

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



