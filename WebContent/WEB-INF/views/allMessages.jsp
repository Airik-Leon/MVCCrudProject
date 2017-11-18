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
                <div class="row justify-content-center" style="margin-top: 20px;">
                  <ul>
                    <c:forEach var="p" items="${posts}">
                      <form action="goToReply.do" method="Post">
                        <input type="hidden" name="postId" value="${p.postID}"/>
                        <input type="hidden" name="postUserName" value="${p.userName}"/>

                        <ul class="justify-container-center" style="margin: 20px;">
                          <li class="list-group-item">${p.userName} posted ${p.title } on ${p.postStamp }</li>
                          <li class="list-group-item">
                            <textarea rows="4" cols="100" placeholder="${p.message}" readonly="readonly"></textarea>
                          </li>
                          <li class="list-group-item">
                            <ul style="list-style:none;">
                              <c:forEach var="reply" items="${p.replies}">
                                <li class="list-group-item">
                                  <em style="font-size:8px;">${reply.title } on ${reply.postStamp }</em>
                                  <p>${reply.userName}: ${reply.message}
                                  </li>
                                </c:forEach>
                              </ul>
                            </li>
                            <li class="list-group-item">
                              <div class="btn-group">
                                <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  Reply</button>
                                <div class="dropdown-menu">
                                  <a class="dropdown-item">
                                    <textarea rows="2" cols="40" name="reply"></textarea>
                                  </a>
                                  <div class="dropdown-divider"></div>
                                  <div class="dropdown-item">
                                    <button type="submit" class="btn btn-primary">post message
                                    </button>
                                  </div>
                                </div>
                              </div>
                            </li>

                            <!-- <li class="list-group-item"><input type="submit" value="reply" class="btn btn-primary" /></li> -->

                          </ul>
                        </form>

                      </c:forEach>
                    </ul>
                  </div>
                  <%@ include file="SharedViews/Layout_footer.jsp"%>
                  </body>
                  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
                  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
                  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
                </html>
