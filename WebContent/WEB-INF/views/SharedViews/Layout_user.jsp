<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<container> 
  <nav class="navbar navbar-expand-lg navbar-dark fixed-top">
    <a class="navbar-brand" href="index.html"><img width="50px" height="25px"  src="images/O_Java-SE-8-OCA-clr.gif"/>Airik | Developer</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-item nav-link active" href="http://airikleon.io">Home <span class="sr-only">(current)</span></a>
        <a class="nav-item nav-link" href="http://airikleon.io/about.html">about</a>
        <a class="nav-item nav-link" href="http://airikleon.io:8080/Blog/splash.do">blog</a>
        <a class="nav-item nav-link" href="http://airikleon.io/portfolio.html">portfolio</a>
        <a class="nav-item nav-link" href="http://airikleon.io/contact.html">contact</a>
      </div>
    </div>
  </nav>
  <br>
  <br>
  <br>
<ul class="nav nav-pills bg-light p-2">
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
    <li class="nav-item"><a class="nav-link disabled"
        href="goToUserCreatePost.do">Create Post</a></li>
</ul>
</container>
