<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>User info</title>
</head>
<body>
    <sec:authorize access="authenticated" var="authenticated" />

    <h1>User infos : ${userName}</h1>
    <p>Displays your user infos</p>

    <c:if test="${authenticated}">
        <sec:authentication property="principal.username" var="userName" />
        <p>You are currently authenticated</p>
        <dl>
            <dt>HttpServletRequest.getRemoteUser()</dt>
            <dd>
                <c:out value="${remoteUser}" />
            </dd>
            <dt>HttpServletRequest.getUserPrincipal()</dt>
            <dd>
                <c:out value="${userPrincipal}" />
            </dd>
            <dt>Authentication</dt>
            <dd>
                <c:out value="${authentication}" />
            </dd>
        </dl>
    </c:if>
    <ul>
        <li><a href="authenticate">HttpServletRequest.authenticate(HttpServletResponse)</a>
            - if you are authenticated already will simply return true. Otherwise, will redirect you to the log in page configured in your Spring Security configuration.</li>
        <li><a href="async">AsyncContext.start(Runnable)</a>
            - will automatically transfer the current SecurityContext to the new Thread</li>
        <c:choose>
            <c:when test="${authenticated}">
                <li><a href="logout">HttpServletRequest.logout()</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="login">Fill out log in form</a> - allows the user to invoke HttpServletRequest.login(String,String)</li>
            </c:otherwise>
        </c:choose>
    </ul>
</body>
</html>
