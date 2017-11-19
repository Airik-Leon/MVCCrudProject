<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title></title>
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
        <li class="nav-item"><a class="nav-link"
            href="goToCreateUser.do">Create User</a></li>
        <li class="nav-item"><a class="nav-link"
            href="goToUserInfo.do">Get User</a></li>
        <li class="nav-item"><a class="nav-link active"
            href="goToCreatePost.do"> Create Post</a></li>
    </ul>
    <h1 class="row justify-content-center">Create a post</h1>
    <div class="container justify-content-center" style="margin-top:50px">
        <form:form action="createPost.do" method="POST" modelAttribute="post">
            <div class="form-group">
                <label for="title">Title
                    </label> <form:input type="text" class="form-control"
                    id="title" name="title" path="title"
                    placeholder="title"/>
            </div>
            <div class="form-group">
                <label for="category">Category
                    </label> <form:select class="form-control"
                    id="category" name="category" path="category">
                    <option>AfterThoughts</option>
                    <option>Articles</option>
                    <option>Photography</option>
                </form:select>
            </div>

            <div class="form-group">
                <label for="message">Message
                    </label>
                <form:textarea class="form-control" path="message"
                    id="message" name="message" rows="4"></form:textarea>
            </div>
            <input type="submit" class="btn btn-success justify-content-center" value="Post Message" />
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


