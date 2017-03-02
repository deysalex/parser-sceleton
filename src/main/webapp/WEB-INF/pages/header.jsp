<%@ page contentType="text/html; charset=UTF-8" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/export/command/list">Команды</a></li>
                <li><a href="${pageContext.request.contextPath}/vacancy/list/">Вакансии</a></li>
                <li><a href="${pageContext.request.contextPath}/estate/list/">Недвижимость</a></li>
                <li><a href="${pageContext.request.contextPath}/seo/info">SEO</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
