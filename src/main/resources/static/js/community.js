/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    commenttarget(questionId, 1, content);
}

function commenttarget(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                if (response.code === 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=20e76e5214c79bc4a3aa&redirect_uri=http://localhost:8887/login&scope=use&state=1r");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    commenttarget(commentId, 2, content);
}

function post2() {
    var articleId = $("#article_id").val();
    var content2 = $("#comment2_content").val();
    comment2target(articleId, 1, content2);
}

function comment2target(targetId, type, content2) {
    if (!content2) {
        alert("不能回复空内容~~~");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment2",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content2,
            "type": type
        }),
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                if (response.code === 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=20e76e5214c79bc4a3aa&redirect_uri=http://localhost:8887/login&scope=use&state=1r");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

function comment2(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}
/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-toggle");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("show");
        e.removeAttribute("data-toggle");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("show");
            // 标记二级评论展开状态
            e.setAttribute("data-toggle", "collapse");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data2) {
                $.each(data2.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media"
                    }).append($("<img/>", {
                        "class": "mr-3 media-object rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "mr-0",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "float-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("show");
                // 标记二级评论展开状态
                e.setAttribute("data-toggle", "collapse");
                e.classList.add("active");
            });
        }
    }
}

function collapseComments2(e) {
    var id = e.getAttribute("data-id");
    var comments2 = $("#comment-" + id);
    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-toggle");
    if (collapse) {
        // 折叠二级评论
        comments2.removeClass("show");
        e.removeAttribute("data-toggle");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments2.addClass("show");
            // 标记二级评论展开状态
            e.setAttribute("data-toggle", "collapse");
            e.classList.add("active");
        } else {
            $.getJSON("/comment2/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment2) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media"
                    }).append($("<img/>", {
                        "class": "mr-3 media-object rounded",
                        "src": comment2.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "mr-0",
                        "html": comment2.user.name
                    })).append($("<div/>", {
                        "html": comment2.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "float-right",
                        "html": moment(comment2.gmtCreate).format('YYYY-MM-DD HH:mm')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments2.addClass("show");
                // 标记二级评论展开状态
                e.setAttribute("data-toggle", "collapse");
                e.classList.add("active");
            });
        }
    }
}


function showSelectTag1() {
    $("#select-tag1").show();
}

function showSelectTag2() {
    $("#select-tag2").show();
}

function showSelectTag3() {
    $("#select-tag3").show();
}

function selectTag1(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

function selectTag2(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#Etag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#Etag").val(previous + ',' + value);
        } else {
            $("#Etag").val(value);
        }
    }
}

function selectTag3(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#Wtag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#Wtag").val(previous + ',' + value);
        } else {
            $("#Wtag").val(value);
        }
    }
}


