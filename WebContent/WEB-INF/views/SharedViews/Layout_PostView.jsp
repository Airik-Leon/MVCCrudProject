<container>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm-8">
            <c:forEach var="p" items="${posts}">
                <form class="post-component" action="goToReply.do"
                    method="Post">
                    <div class="form-group">
                        <input type="hidden" name="postId"
                            value="${p.postID}" /> <input type="hidden"
                            name="postUserName" value="${p.userId}" />
                        <label>${p.username} posted </label> ${p.title } on
                        ${p.postStamp }
                        <hr>
                        <div class="jumbotron jumbotron-fluid rounded">
                            <div class="container">
                                <p class="lead">${p.title }</p>
                                ${p.message }
                            </div>
                        </div>
                        <hr>
                        <c:forEach var="reply" items="${p.replies}">

                            <p class="post-reply">${reply.username}:
                                ${reply.message}<br>
                            </p>
                            <em>${reply.title } on
                                ${reply.postStamp }</em>
                            <hr>
                        </c:forEach>

                        <button type="button" class="btn btn-primary"
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
                                            class="btn btn-success" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </div>
    </div>
</div>
<container>