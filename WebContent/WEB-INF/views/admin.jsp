<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <%@ include file="SharedViews/Layout_AdminHome.jsp"%>
    <%@ include file="SharedViews/Layout_AdminNavBar.jsp"%>
    <br>
    <div class="row justify-content-center ">
        <div class="card" style="width: 20rem;">
            <div class="card-body">
                <button type="button" class="btn btn-primary">
                    Total Users <span class="badge badge-light">${userCount}</span>
                </button>
            </div>
        </div>
        <div class="card" style="width: 20rem;">
            <div class="card-body">
                <button type="button" class="btn btn-primary">
                    Total Posts <span class="badge badge-light">${postCount}</span>
                </button>
            </div>
        </div>
        <div class="card" style="width: 20rem">
            <div class="card-body">
                <h4 class="card-title">ActivityLog</h4>
                <c:forEach var="log" items="${activityLog}">
                    <p class="card-text">${log}</p>
                </c:forEach>
            </div>
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


