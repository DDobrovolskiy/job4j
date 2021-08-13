<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Работа мечты</title>    
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
    <script>
        $(document).ready(function () {
            $.ajax({
                type: 'GET',
                crossdomain: true,
                url: 'http://localhost:8080/dreamjob/city.do',
                dataType: 'json'
            }).done(function(out) {
                let cities = $('span.city');
                let map = new Map();
                out.forEach(el => map.set(el.id, el.name));
                for (let i = 0; i < cities.length; i++) {
                    cities[i].innerText = map.get(Number(cities[i].getAttribute('name')));
                }
            }).fail(function(err){
                alert(err);
            });
        });
        </script>
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/index.do">В начало</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> | Выйти</a>
            </li>
        </ul>
    </div>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Кандидаты
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Наименование</th>
                        <th scope="col">Город</th>
                        <th scope="col">Фото</th>
                        <th scope="col">Дополнить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${candidates}" var="candidate">
                         <tr>
                            <td>
                                <a href='<c:url value="/candidate.do?id=${candidate.id}"/>'>
                                    <i class="fa fa-edit mr-3"></i>
                                </a>
                                <c:out value="${candidate.name}"/>
                            </td>
                            <td>
                                <span class="city" name='<c:out value="${candidate.cityId}"/>'></span>
                            </td>
                            <td>
                                <img src="<c:url value='/download?name=${candidate.id}'/>" width="100px" height="100px"/>
                            </td>
                            <td>
                            <a href='<c:url value="/upload?id=${candidate.id}"/>'>
                                <button type="submit" class="btn btn-primary">Загрузить новое фото</button>
                            </a>
                            <a href='<c:url value="/delete?id=${candidate.id}"/>'>
                                <button type="submit" class="btn btn-primary">Удалить</button>
                            </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>