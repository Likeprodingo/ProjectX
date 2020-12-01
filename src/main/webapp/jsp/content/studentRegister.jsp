<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="locale.pagecontent"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="register.register"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .login-form {
            width: 340px;
            margin: 50px auto;
            font-size: 15px;
        }
        .login-form form {
            margin-bottom: 15px;
            background: #f7f7f7;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }
        .login-form h2 {
            margin: 0 0 15px;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn {
            font-size: 15px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="login-form">
    <form action="controller?command=student_registration" method="post">
        <h2 class="text-center"><fmt:message key="register.register"/></h2>
        <div class="form-group">
            <input name = "name" type="text" class="form-control"  pattern="[\p{L}\s-]{1,20}" placeholder="<fmt:message key="register.name"/>" required="required">
        </div>
        <div class="form-group">
            <input name = "surname" type="text" class="form-control" pattern="[\p{L}\s-]{1,20}" placeholder="<fmt:message key="register.surname"/>" required="required">
        </div>

        <div class="form-group">
            <input name = "login" type="text" class="form-control" pattern="^(?=.*[A-Za-z0-9]$)[a-zA-Z][a-zA-Z0-9._-]+" minlength="4" maxlength="20" placeholder="<fmt:message key="register.login"/> " required="required">
        </div>

        <div class="form-group">
            <input name = "password" type="password" class="form-control" pattern="^(?=.*[a-z])(?=.*[A-Z])[A-Za-z\d@$!%*?&]+" minlength="6" maxlength="20" placeholder="<fmt:message key="register.password"/>" required="required">
        </div>

        <div class="form-group">
            <input name = "group" type="number" class="form-control" pattern="^\d{3,10}" placeholder="<fmt:message key="register.group"/>" required="required">
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block"><fmt:message key="register.complete"/></button>
            ${wrongLoginMessage}
        </div>
    </form>
    <p class="text-center"><a href="controller?command=login_page"><fmt:message key="register.back"/></a></p>
</div>
</body>
</html>
