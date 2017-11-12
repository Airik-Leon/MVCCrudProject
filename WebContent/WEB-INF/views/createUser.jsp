<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

    <ul class="nav nav-tabs">
        <li class="nav-item"><a class="nav-link "
            href="goToAdmin.do">Admin Home</a>
        <li class="nav-item"><a class="nav-link active"
            href="goToCreateUser.do">Create User</a></li>
        <li class="nav-item"><a class="nav-link"
            href="goToUserInfo.do">Get User</a></li>
    </ul>
    <div class="row justify-content-center">
        <form:form action="createUser.do" method="POST"
            modelAttribute="user">
            <div class="form-group">
                <label>First Name </label>
                <form:input type="text" class="form-control"
                    name="firstName" path="firstName" id="firstName"
                    placeholder="First Name" />
                <form:errors path="firstName"></form:errors>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <form:input type="text" class="form-control"
                    path="lastName" id="lastName" name="lastName"
                    placeholder="Last Name" />
                <form:errors path="lastName"></form:errors>
            </div>
            <div class="form-group">
                <label for="userName">User Name</label>
                <form:input type="text" class="form-control"
                    path="userName" id="userName" name="userName"
                    placeholder="User Name" />
                <form:errors path="userName"></form:errors>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <form:input type="text" class="form-control"
                    id="password" path="password" name="password"
                    placeholder="Password" />
                <form:errors path="password"></form:errors>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
        <label>${error}</label>
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


