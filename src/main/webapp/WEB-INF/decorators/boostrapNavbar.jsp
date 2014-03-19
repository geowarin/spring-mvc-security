<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="${pageContext.request.contextPath}/">Spring security example</a>
            <div class="nav-collapse collapse">
                <p class="navbar-text pull-right">

                    <sec:authorize access="authenticated">
                        <sec:authentication property="principal.username" var="userName" />
                        Logged in as <a href="${pageContext.request.contextPath}/userInfos" class="navbar-link">${userName}</a>
                    </sec:authorize>
                    <sec:authorize access="anonymous">
                        Not logged in <a href="${pageContext.request.contextPath}/login" class="navbar-link">Login</a>
                    </sec:authorize>
                </p>
                <ul class="nav">
                    <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
            </div> <!--/.nav-collapse -->
        </div>
    </div>
</div>

<sec:authorize access="authenticated">
    <div id="message" class="alert alert-info">
        You are authenticated <br />
        <a href="j_spring_security_logout">logout</a>
    </div>
</sec:authorize>

</body>
</html>