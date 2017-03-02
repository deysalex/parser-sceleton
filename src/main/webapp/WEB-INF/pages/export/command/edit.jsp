<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="utf-8">
<title>Список городов : Тестовое задание: ЦФТ</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

    <jsp:include page="/WEB-INF/pages/base.jsp"/>

</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<div class="container">
    <div class="row">
    <div class="col-md-offset-2 col-md-8">
        <h1>Редактирование города</h1>

        <form:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/export/command/editsubmit" commandName="exportCommandForm">
            <fieldset>

                <!-- Form validation -->
                <div class="widget">
                    <div class="row-fluid">

                        <form:input path="id" type="hidden" id="id"/>

                        <div class="control-group">
                            <label class="control-label">Команда:</label>
                            <div class="controls">
                                <span class="text-error" id="nameError"><form:errors path="command"/></span>
                                <form:input path="command" cssClass="form-control span12" id="command"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Аргументы:</label>
                            <div class="controls">
                                <span class="text-error" id="nameError"><form:errors path="commandArgs"/></span>
                                <form:input path="commandArgs" cssClass="form-control span12" id="commandArgs"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Внешний url:</label>
                            <div class="controls">
                                <span class="text-error" id="nameError"><form:errors path="externalUrl"/></span>
                                <form:input path="externalUrl" cssClass="form-control span12" id="externalUrl"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Url для запроса:</label>
                            <div class="controls">
                                <span class="text-error" id="nameError"><form:errors path="requestUrl"/></span>
                                <form:input path="requestUrl" cssClass="form-control span12" id="requestUrl"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <div class="controls">
                                <button type="submit" class="btn btn-info">Сохранить</button>
                                <a href="${pageContext.request.contextPath}/export/command/" class="btn btn-danger" id="back">Отмена</a>
                            </div>
                        </div>

                    </div>

                </div>
                <!-- /form validation -->
            </fieldset>
        </form:form>

    </div>
</div>

</div>    <hr>
    <footer>
        <p>&copy; 2015</p>
    </footer>
</div> <!-- /container -->
</body>
</html>
