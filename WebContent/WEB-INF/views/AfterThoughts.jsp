<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="./css/theme.css" />
</head>
<body>
    <%@ include file="SharedViews/Layout_user.jsp"%>
<ul class="nav nav-pills nav-fill  p-2" style="background-color: #232F3E;">
    <li class="nav-item"><a type="button" class="btn btn-outline-info m-1"
        href="browse.do">Browse</a></li>
    <li class="nav-item"><a type="button" class="btn btn-outline-info active m-1"
        href="goToAfterThoughts.do">After thoughts </a></li>
    <li class="nav-item"><a type="button" class="btn btn-outline-info m-1" href="goToArticles.do">Articles</a></li>
    <li class="nav-item"><a type="button" class="btn btn-outline-info m-1"
        href="goToPhotography.do">Photography</a></li>
    <li class="nav-item"><a type="button" class="btn btn-outline-info m-1"
        href="goToAllMessages.do">All Messages</a></li>
</ul>
    <%@ include file="SharedViews/Layout_PostView.jsp" %>
    <%@ include file="SharedViews/Layout_footer.jsp"%>

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


