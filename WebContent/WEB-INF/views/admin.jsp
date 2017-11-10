<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html lang="en">
    <head>
        <title></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">        
    </head>
    <body>
        <div class="container col-sm-6">
        <c:if test="${loggedIn == false }">
        <h2>Admin log-in</h2>
            <form action="log-in.do" method="GET">
            <label>Username: </label>
                <input type="text" name="userName"/><br>
            <label>Password: </label>
                <input type="text" name="password"/><br>
                <input class="btn btn-primary" type="submit" name="log-in" value="Log In"/>
            </form>
        </c:if>
        <c:if test="${loggedIn == true }">
            <div class="btn-group-vertical">
                  <button type="button" class="btn btn-secondary">Create post</button>
                  <button type="button" class="btn btn-secondary">Get post</button>
                  <button type="button" class="btn btn-secondary">Update post</button>
                  <button type="button" class="btn btn-secondary">Delete post</button>
                  <br>
                  <button type="button" class="btn btn-secondary">Create user</button>
                  <button type="button" class="btn btn-secondary">Get user</button>
                  <button type="button" class="btn btn-secondary">Update user</button>
                  <button type="button" class="btn btn-secondary">Delete user</button>
            </div>
        </c:if>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
</html>


