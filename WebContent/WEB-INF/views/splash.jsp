<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Airik's blog</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="SharedViews/Layout_StyleSheets.jsp"%>

</head>
<body onload="setInterval()">
    <%@ include file="SharedViews/Layout_user.jsp"%>
        <div class="row justify-content-center">
            <h1 id="change" class=" text-dark p-5 rounded-circle m-2 ">
            </h1>
        </div>
        <div class="container justify-content-center" style="width:400px;">
            <c:if test="${not empty createPostFail}">
                <div class="alert alert-warning" role="alert">
                   ${createPostFail}                   
                </div>
            </c:if>
        </div>

    <div class="row justify-content-center align-middle">
        <div class="p-2 m-4 rounded bg-light" id="splashDiv">
            <form action="admin.do" method="GET">
                <input class="btn  btn-outline-dark" id="admin"
                    type="submit" name="admin" value="${admin}" />
            </form>
            <form action="browse.do" method="GET">
                <input class="btn  btn-outline-danger" id="guest"
                    type="submit" value="Browse posts" name="guest" />
            </form>
        </div>
    </div>
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


