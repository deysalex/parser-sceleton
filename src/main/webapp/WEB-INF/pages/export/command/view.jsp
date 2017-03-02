<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="utf-8">
<title>Список клиентов : Тестовое задание: ЦФТ</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Le styles -->

    <jsp:include page="/WEB-INF/pages/base.jsp"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/exportCommand/action.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <div class="panel panel-default">
                <div class="panel-heading"><i class="fa fa-flag-o"></i> Вакансия<br></div>
            </div>

            <div class="panel-body">
                <div class="control-group">
                    <label class="control-label">Команда:</label>
                    <label class="control-label">${exportCommand.command}</label>
                </div>

                <div class="control-group">
                    <label class="control-label">Аргументы:</label>
                    <label class="control-label">${exportCommand.commandArgs}</label>
                </div>

                <div class="control-group">
                    <label class="control-label">Внешний url:</label>
                    <label class="control-label">${exportCommand.externalUrl}</label>
                </div>

                <div class="control-group">
                    <label class="control-label">Url для запроса:</label>
                    <label class="control-label">${exportCommand.requestUrl}</label>
                </div>
            </div>

        </div>
        <div class="col-lg-4">
            <ul class="list-group">
                <li class="list-group-item">
                    <a class="btn btn-success btn-block" href="${pageContext.request.contextPath}/export/command/new">Добавить вакансию</a>
                </li>
            </ul>
        </div>
    </div>
</div>

</div>    <hr>
    <footer>
        <p>&copy; 2015</p>
    </footer>
</div> <!-- /container -->
</body>
</html>
