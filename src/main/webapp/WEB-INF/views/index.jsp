<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Welcome</title>
</head>
<body>

	<h1>Home Page</h1>
	<p>Anyone can view this page.</p>

    <p>
        If you try and use the admin action in the left menu, you will see it is called properly in your logs or
        in debug if you are identified.
        Otherwise, you will be redirected to the login page.
    </p>

	<sec:authorize access="authenticated" var="authenticated" />

    <hr/>

    <c:choose>
        <c:when test="${authenticated}">
            <sec:authentication property="principal.username" var="userName" />
            <p>You are logged in as ${userName}</p>
            <a href="${pageContext.request.contextPath}/userInfos">Go to your user infos</a>
        </c:when>
        <c:otherwise>
            <p>Not logged in</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
