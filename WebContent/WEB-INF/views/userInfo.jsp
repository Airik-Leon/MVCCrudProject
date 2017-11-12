<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin: Create User</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
    integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
    crossorigin="anonymous">
<link rel="stylesheet" href="./css/site.css" />
</head>
<body>
    <nav class="navbar navbar-dark bg-primary">
        <a class="navbar-brand" href="http://airikleon.io"> <img
            class="rounded-circle" src="images/icons/GreekAlpha.ico" />
        </a>
        <button class="navbar-toggler" type="button"
            data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse"
            id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active"><a class="nav-link"
                    href="http://airikleon.io">home <span class="sr-only">(current)</span>
                </a></li>
                <li class="nav-item"><a class="nav-link"
                    href="about.html">about</a></li>
                <li><a href=""></a></li>
                <li class="nav-item"><a class="nav-link"
                    href="portfolio.html">portfolio</a></li>
                <li class="nav-item"><a class="nav-link"
                    href="splash.do">blog</a></li>
                <li class="nav-item"><a class="nav-link"
                    href="contact.html"> contact</a></li>
            </ul>
        </div>
    </nav>
    <ul class="nav nav-tabs">
        <li class="nav-item"><a class="nav-link "
            href="goToAdmin.do">Admin Home</a>
        <li class="nav-item"><a class="nav-link "
            href="goToCreateUser.do">Create User</a></li>
        <li class="nav-item"><a class="nav-link active" href="#">Get
                User</a></li>
    </ul>
    <div class="row justify-content-center">
        <form action="searchById.do" method="POST">
            <h6>Get user by Id:</h6>
            <input type="number" placeholder="start at 1" name="id"
                min="1" /> <input type="submit" class="btn btn-success"
                value="Go" />
        </form>
    </div>
    <div class="row justify-content-center">
        <form action="searchByUserName.do" method="POST">
            <h6>Get user by Username:</h6>
            <input type="text" placeholder="username" name="userName">
            <input type="submit" class="btn btn-success" value="Go">
        </form>
    </div>
    <hr />
    <div class="row justify-content-center">
        <h4>Results</h4>
    </div>
    <div class="row justify-content-center">
        <ul class="list-group">
            <c:choose>
                <c:when test="${not empty result}">
                    <li class="list-group-item">User id:<input
                        type="text" name="id" value="${result.id}"
                        readonly /><br>
                    </li>
                    <li class="list-group-item">Username:<input
                        type="text" name="userName"
                        value="${result.userName}" readonly />
                    </li>
                    <li class="list-group-item">First Name:<input
                        type="text" name="FirstName"
                        value="${result.firstName}" readonly />
                    </li>
                    <li class="list-group-item">Last Name: <input
                        type="text" name="lastName"
                        value="${result.lastName}" readonly />
                    </li>
                    <li class="list-group-item">Password: <input
                        type="text" name="password"
                        value="${result.password }" readonly />
                    </li>
                    <li class="list-group-item">Account Origin:<input
                        type="text" name="accountOrigin"
                        value="${result.accountOrigin }" readonly />
                    </li>
                    <div class="row justify-content-center">
                        <form action="goToUpdateUser.do" method="GET">
                            <input type="hidden" name="id"
                                value="${result.id}" /> <input
                                class="btn btn-secondary" type="submit"
                                value="Update user"
                                id="btnAdminUpdateUser" />
                        </form>
                        <form action="deleteUser.do" method="GET">
                            <input type="hidden" name="id"
                                value="${result.id}" /> <input
                                class="btn btn-danger" type="submit"
                                value="Delete user"
                                id="btnAdminDeleteUser">
                        </form>
                    </div>
                </c:when>
                <c:when test="${empty result}">
                    <p class="row justify-content">User does not
                        exist</p>
                </c:when>
            </c:choose>
        </ul>
        <c:choose>
            <c:when test="${not empty result}">
                <div class="row justify-content-center"></div>
            </c:when>
        </c:choose>
    </div>
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-sm">
                    <span class="text-muted"> &copy 2017 Airik
                        Leon</span>
                </div>
                <div class="col-sm">
                    <div class="container">
                        <a
                            href="https://www.linkedin.com/in/airik-leon-b0730a93/">
                            <img src="images/socialMedia/in.png" />
                        </a> <a href="https://github.com/Airik-Leon"> <img
                            src="images/socialMedia/github.png" />
                        </a> <a href="https://www.facebook.com/airik.leon">
                            <img src="images/socialMedia/fb.png" />
                        </a> <a href="tel:8168050627"> <img
                            src="images/socialMedia/phone.png" />
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </footer>
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



