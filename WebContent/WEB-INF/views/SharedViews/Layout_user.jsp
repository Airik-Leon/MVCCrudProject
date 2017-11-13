<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<container>

<nav class="navbar navbar-dark bg-primary">
    <a class="navbar-brand" href="http://airikleon.io"> <img
        class="rounded-circle" src="images/icons/GreekAlpha.ico" />
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false"
        aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active"><a class="nav-link"
                href="http://airikleon.io">home <span
                    class="sr-only">(current)</span>
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
<ul class="nav nav-pills" style="margin-top: 20px; margin-left: 5px;">
    <li class="nav-item"><a class="nav-link active"
        href="userLogIn.do">
        <c:choose>
        <c:when test="${empty user.userName }">
        Log-in
        </c:when>
        <c:otherwise>
        ${user.userName}
        </c:otherwise>
        </c:choose>        
        </a></li>
    <li class="nav-item"><a class="nav-link" href="splash.do">Home</a></li>
    <li class="nav-item"><a class="nav-link"
        href="goToUserAddAccount.do">Add account</a></li>
    <li class="nav-item"><a class="nav-link" href="userLogOut.do">Log
            out</a></li>
    <li class="nav-item"><a class="nav-link"
        href="goToUserCreatePost.do">Create Post</a></li>
</ul>
</container>
