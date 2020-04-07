<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Profile</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">My profile</div>
        <div class="card-body">
            <form:form action="/profile" method="post" modelAttribute="user">
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="hidden" required name="id" value="${user.id}">
                        <input type="hidden" required name="email" value="${user.email}">
                        <input type="text" id="username" name="username" class="form-control"
                               placeholder="Username" required autofocus="autofocus"
                               value="${user.username != "anonymousUser"? user.username : ""}">
                        <label for="username">Username</label>
                        <span class="d-block small mt-3" style="color: white">${errorUsername}</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="oldPassword" name="oldPassword" class="form-control"
                               placeholder="Old password" required autofocus="autofocus">
                        <label for="oldPassword">Old password</label>
                        <span class="d-block small mt-3" style="color: white">${errorOldPassword}</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="password" name="password" class="form-control"
                               placeholder="Password" required autofocus="autofocus"
                               value="${user.password != null ? "" : user.password}">
                        <label for="password">Password</label>
                        <span class="d-block small mt-3" style="color: white">${errorPassword}</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control"
                               placeholder="Confirm password" required autofocus="autofocus"
                               value="${user.confirmPassword}">
                        <label for="confirmPassword">Confirm Password</label>
                        <span class="d-block small mt-3" style="color: white">${errorConfirmPassword}</span>
                        <span class="d-block small mt-3" style="color: white">${message}</span>
                    </div>
                </div>
                <button class="btn btn-primary btn-block" type="submit">Apply settings</button>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
