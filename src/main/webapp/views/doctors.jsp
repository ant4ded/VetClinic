<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Doctors</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<div class="card mt-5">
    <div class="card-header">
        Operations with doctors
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" aria-describedby="Doctors">
                <thead>
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Email</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="doctor" items="${doctors}">
                    <form:form action="/doctors" method="post" modelAttribute="doctor">
                        <tr>
                            <td>
                                <input type="hidden" required name="id" value="${doctor.id}">
                                    ${doctor.username}
                            </td>
                            <td>
                                    ${doctor.email}
                            </td>
                            <td>
                                <button class="btn btn-danger" name="btnProcedure" value="Delete" type="submit">
                                    Delete
                                </button>
                            </td>
                        </tr>
                    </form:form>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
