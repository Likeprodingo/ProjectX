<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="locale.pagecontent"/>
<head>
    <title>Teacher Task</title>
</head>

<body>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="model-title"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="controller?command=save_task" method="post">
                <input id="id-task" name="id" type="hidden" value="">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="text"><fmt:message key="task.title"/></label>
                        <input id="text" name="text" type="text" class="form-control" pattern="[\p{L}\s-,?:!\d]{1,300}"
                               placeholder="" required="required">
                    </div>
                    <div class="form-group">
                        <label for="answer"><fmt:message key="task.answer"/>: </label>
                        <textarea class="form-control" id="answer" rows="3" readonly="readonly"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="mark"><fmt:message key="task.mark"/></label>
                        <input id="mark" name="mark" type="number" min="0" max="10" class="form-control"
                               pattern="[0-10]"
                               required="required">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary btn-block"><fmt:message key="task.save"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<table class="table table-bordered">
    <thead class="thead-dark">
    <tr>
        <th><fmt:message key="task.name"/></th>
        <th><fmt:message key="task.answer"/></th>
        <th><fmt:message key="task.mark"/></th>
    </tr>
    </thead>
    <tbody>
    <tr class="custom-table" onclick="open_modal(
            '1',
            'Sasha',
            'Sasha',
            '8',
            'Her',
            'HER is?')"
    >
        <td>Sasha</td>
        <td>Her</td>
        <td>8</td>
    </tr>
    <c:forEach items="${tasks}" var="task">
        <tr class="custom-table" onclick="open_modal(
                '<c:out value="${task.student.getId()}"/>',
                '<c:out value="${task.student.getFirstName()}"/>',
                '<c:out value="${task.student.getSurname()}"/>',
                '<c:out value="${task.getMark()}"/>',
                '<c:out value="${task.getAnswer()}"/>',
                '<c:out value="${task.getText()}"/>')"
        >
            <td><fmt:message key="task.title"/> <c:out value="${task.student.getFirstName()}"/> <c:out value="${task.student.getSurname()}"/></td>
            <td>${task.getAnswer()}</td>
            <td>${task.getMark()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
