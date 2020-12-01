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
    <title><fmt:message key="login.login"/></title>
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
    <form action="controller?command=login" method="post">
        <h2 class="text-center"><fmt:message key="login.login"/></h2>
        <div class="form-group">
            <input name = "login" type="text" class="form-control" placeholder="<fmt:message key="login.username"/>" required="required">
        </div>
        <div class="form-group">
            <input name = "password" type="password" class="form-control" placeholder="<fmt:message key="login.password"/>" required="required">
            ${wrongLoginMessage}
        </div>

        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="role" id="teacherRadioButton" value="teacher" required = required>
                <label class="form-check-label" for="teacherRadioButton"><fmt:message key="login.teacher"/></label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="role" id="studentRadioButton" value="student" required = required>
                <label class="form-check-label" for="studentRadioButton"><fmt:message key="login.student"/></label>
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block"><fmt:message key="login.enter"/></button>
        </div>
    </form>
    <p class="text-center"><a href="controller?command=teacher_registration_page"><fmt:message key="login.teacherRegister"/></a></p>
    <p class="text-center"><a href="controller?command=student_registration_page"><fmt:message key="login.studentRegister"/></a></p>

</div>
</body>
</html>

