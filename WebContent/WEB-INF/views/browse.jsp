<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="SharedViews/Layout_StyleSheets.jsp" %>
</head>
<body>
    <%@ include file="SharedViews/Layout_user.jsp"%>
    <%@ include file="SharedViews/Layout_browse.jsp"%>
    
    <div class="row justify-content-center ">
        <div class="card m-2" style="width: 18rem;">
            <img class="card-img-top" src="./images/project.png"
                alt="Card image cap" style="height: 300px;">
            <div class="card-body bg-light">
                <h4 class="card-title">Projects after thoughts</h4>
                <p class="card-text">My thoughts on the projects I
                    have worked on and how I feel about various software
                    engineering topics.</p>
                <a href="goToAfterThoughts.do" class="btn btn-primary">Go
                </a>
            </div>
        </div>
        <div class="card m-2" style="width: 18rem;">
            <img class="card-img-top" src="./images/cgpGrey.png"
                alt="Card image cap" style="height: 300px;">
            <div class="card-body bg-light">
                <h4 class="card-title">Articles</h4>
                <p class="card-text ">I first started college wanting
                    to do journalism. Somehow I ended studying Software
                    Engineering.</p>
                <a href="goToArticles.do" class="btn btn-primary">Go
                </a>
            </div>
        </div>
        <div class="card m-2" style="width: 18rem;">
            <img class="card-img-top" src="./images/photo.png"
                alt="Card image cap" style="height: 300px">
            <div class="card-body bg-light">
                <h4 class="card-title">Photography</h4>
                <p class="card-text">My work as a photographer. A
                    hobby I don't practice enough of but I will always
                    enjoy the landscapes when I can.</p>
                <a href="goToPhotography.do" class="btn btn-primary">Go
                </a>
            </div>
        </div>
        <div class="card m-2" style="width: 18rem;">
            <img class="card-img-top" src="./images/allMessages.png"
                alt="Card image cap" style="height: 300px">
            <div class="card-body bg-light">
                <h4 class="card-title">All Messages</h4>
                <p class="card-text">Everything in one place.</p>
                <a href="goToAllMessages.do" class="btn btn-primary">Go
                </a>
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


