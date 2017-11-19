<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!DOCTYPE html>
    <html lang="en">
      <head>
        <title>All messages</title>
        <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
              <link rel="stylesheet" href="./css/site.css"/>
            </head>
            <body>
              <%@ include file="SharedViews/Layout_user.jsp"%>
                <ul class="nav nav-pills nav-fill" style="margin-top: 20px;">
                  <li class="nav-item">
                    <a class="nav-link" href="browse.do">Browse</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="goToAfterThoughts.do">After thoughts
                    </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="goToArticles.do">Articles</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="goToPhotography.do">Photography</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" href="goToAllMessages.do">All Messages</a>
                  </li>
                </ul>
                 <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <c:forEach var="p" items="${posts}">
                    <form action="goToReply.do" method="Post" style="background-color:#E9ECEF;padding:10px; margin:10px; border-radius:5px;">
                        <div class="form-group">
                            <input type="hidden" name="postId"
                                value="${p.postID}" /> <input
                                type="hidden" name="postUserName"
                                value="${p.userId}" /> <label>posted</label>
                            ${p.title } on ${p.postStamp }
                            <hr>
                            <div class="jumbotron jumbotron-fluid" style="background-color:white; border-raidus:5px;">
                                <div class="container" style=" padding:5px;">
                                    <p class="lead">${p.title }</p>
                                    ${p.message }
                                </div>
                            </div>
                            <hr>
                            <c:forEach var="reply" items="${p.replies}">
       
                                <p style="background-color:#007BFF; border-radius:5px; padding:5px; width:auto; height:50px;color:white;">${reply.username}:
                                    ${reply.message}<br>
                               </p>
                                     <em style="font-size: 8px;">${reply.title }
                                    on ${reply.postStamp }</em>
                                    <hr>
                            </c:forEach>

                            <button type="button"
                                class="btn btn-primary"
                                data-toggle="modal"
                                data-target=".bd-example-modal-lg">Reply
                                </button>
                            <div class="modal fade bd-example-modal-lg"
                                tabindex="-1" role="dialog"
                                aria-labelledby="myLargeModalLabel"
                                aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <a class="dropdown-item"> <textarea
                                                class="form-control"
                                                rows="2" cols="40"
                                                name="reply"></textarea>
                                        </a>
                                        <div class="dropdown-divider"></div>
                                        <div class="dropdown-item">
                                            <input type="submit"
                                                value="post message"
                                                class="btn btn-primary" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </div>
                    </form>
                </c:forEach>
            </div>
            <!--             <div class="col-sm">One of three columns</div>
 -->
        </div>
    </div>
                  <%@ include file="SharedViews/Layout_footer.jsp"%>
                  </body>
                  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
                  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
                  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
                </html>
