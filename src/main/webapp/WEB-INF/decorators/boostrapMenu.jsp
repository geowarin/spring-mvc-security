<%--
  Created by IntelliJ IDEA.
  User: aziphael
  Date: 4/7/13
  Time: 8:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<div class="well sidebar-nav">
    <ul class="nav nav-list">
        <li class="nav-header"><spring:message code="layout.lang" /> </li>
        <li><a href="?lang=fr"><spring:message code="layout.lang.french" /></a></li>
        <li><a href="?lang=en"><spring:message code="layout.lang.english" /></a></li>
    </ul>
    <ul class="nav nav-list">
        <li class="nav-header">Admin</li>
        <li><a href="${root}/authenticatedAction">Action</a></li>
    </ul>
</div> <!--/.well -->

</body>
</html>