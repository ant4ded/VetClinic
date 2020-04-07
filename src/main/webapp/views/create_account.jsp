<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Create new user</title>
    <jsp:include page="header.jsp"/>
</head>
<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Create doctor</div>
        <div class="card-body">
            <form:form action="/create_account" method="post" modelAttribute="account">
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" id="username" name="username" class="form-control"
                               placeholder="Username" required autofocus="autofocus"
                               value="${account.username}">
                        <label for="username">Username</label>
                        <span class="d-block small mt-3" style="color: white">${errorUsername}</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="email" id="email" name="email" class="form-control"
                               placeholder="Email" required autofocus="autofocus"
                               value="${account.email}">
                        <label for="email">Email</label>
                        <span class="d-block small mt-3" style="color: white">${errorEmail}</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="password" name="password" class="form-control"
                               placeholder="Password" required autofocus="autofocus"
                               value="${account.password}">
                        <label for="password">Password</label>
                        <span class="d-block small mt-3" style="color: white">${errorPassword}</span>
                        <span class="d-block small mt-3" style="color: white">${message}</span>
                    </div>
                </div>
                <button class="btn btn-primary btn-block" type="submit">Create doctor</button>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
