<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: likeprodingo
  Date: 26.11.2020
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<main class="page landing-page">
    <c:forEach var="task" items="${taskList}" >
        <div class="row">
            <div class="col">${task}</div>
        </div>
    </c:forEach>
</main>
</body>
</html>
