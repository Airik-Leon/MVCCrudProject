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
</head>
<body>
   <%@ include file="SharedViews/Layout_user.jsp" %>
    <div class="row justify-content-center">
        <div>
            <h2>Admin log-in</h2>
            <form action="log-in.do" method="GET">
                <label>Username:</label> <input type="text"
                    name="userName" /><br> <label>Password:
                </label> <input type="password" name="password" /><br> <input
                    class="btn btn-primary" type="submit" name="log-in"
                    value="Log In" />
            </form>
            <H3 style="color: red;">${NOT_EXIST}</H3>
        </div>
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


