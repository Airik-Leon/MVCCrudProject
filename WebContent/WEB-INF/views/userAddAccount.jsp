<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>User: add Account</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="SharedViews/Layout_StyleSheets.jsp" %>

</head>
<body>
<%@ include file="SharedViews/Layout_user.jsp" %>
    <div class="row justify-content-center">
        <form:form action="addUserAccount.do" method="POST"
            modelAttribute="user">
            <div class="form-group">
                <label>First Name </label>
                <form:input type="text" class="form-control"
                    name="firstName" path="firstName" id="firstName"
                    placeholder="First Name" required="true"/>
                <form:errors path="firstName"></form:errors>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <form:input type="text" class="form-control"
                    path="lastName" id="lastName" name="lastName"
                    placeholder="Last Name" required="true" />
                <form:errors path="lastName"></form:errors>
            </div>
            <div class="form-group">
                <label for="userName">User Name</label>
                <form:input type="text" class="form-control"
                    path="userName" id="userName" name="userName"
                    placeholder="User Name" required="true"/>
                <form:errors path="userName"></form:errors>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <form:input type="text" class="form-control"
                    id="password" path="password" name="password"
                    placeholder="Password" required="true"/>
                <form:errors path="password"></form:errors>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
        <label>${error}</label>
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