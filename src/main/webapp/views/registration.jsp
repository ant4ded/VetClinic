<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Registration</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<div class="container">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">Register an Account</div>
        <div class="card-body">
            <form:form action="/registration" method="post" modelAttribute="user">
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <div class="form-label-group">
                                <input type="text" id="username" name="username" class="form-control"
                                       placeholder="Username" required autofocus="autofocus"
                                       value="${user.username != "anonymousUser" ? user.username : ""}">
                                <label for="username">Username</label>
                                <span class="d-block small mt-3" style="color: white">${errorUsername}</span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-label-group">
                                <input type="email" id="email" name="email" class="form-control"
                                       placeholder="Email" required autofocus="autofocus"
                                       value="${user.email}">
                                <label for="email">Email</label>
                                <span class="d-block small mt-3" style="color: white">${errorEmail}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <div class="form-label-group">
                                <input type="password" id="password" name="password" class="form-control"
                                       placeholder="Password" required autofocus="autofocus"
                                       value="${user.password}">
                                <label for="password">Password</label>
                                <span class="d-block small mt-3" style="color: white">${errorPassword}</span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-label-group">
                                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control"
                                       placeholder="Confirm password" required autofocus="autofocus"
                                       value="${user.confirmPassword}">
                                <label for="confirmPassword">Confirm password</label>
                                <span class="d-block small mt-3" style="color: white">${errorConfirmPassword}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary btn-block" type="submit">Register</button>
            </form:form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/login">Login Page</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>