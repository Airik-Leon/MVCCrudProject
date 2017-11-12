<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title></title>
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
        <li class="nav-item"><a class="nav-link active" href="goToAdmin.do" >Admin Home</a>
        <li class="nav-item"><a class="nav-link" href="goToCreateUser.do">Create User</a>
        </li>
        <li class="nav-item"><a class="nav-link" href="goToUserInfo.do">Get User</a>
        </li>
        <li class="nav-item"><a class="nav-link" href="goToUpdateUser.do">Update User</a>
        </li>
        <li class="nav-item"><a class="nav-link" href="goToDeleteUser.do">Delete User</a>
        </li>
    </ul>
    <div class="row justify-content-center" id="totalUsers">
        <h6>Total Users: </h6><br>
        <p>${userCount}</p>
    </div>
    <div class="row justify-content-center" id="totalPosts">
        <h6>Total Posts: </h6><br>
        <p>${postCount}</p>
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


