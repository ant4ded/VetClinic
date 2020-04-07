<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Services</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<div class="card mt-5">
    <div class="card-header">
        Operations with services
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" aria-describedby="Ordered services">
                <c:if test="${role == \"ROLE_DOCTOR\"}">
                    <form:form action="/ordered_services" method="post">
                        <thead>
                        <tr>
                            <th style="border-right: 0" scope="col">
                                <div class="card card-login form-label-group">
                                    <input type="date" id="date" name="date" class="form-control"
                                           placeholder="Date" required autofocus="autofocus">
                                    <label for="date">Date</label>
                                </div>
                            </th>
                            <th style="border-left: 0" colspan="3" scope="col">
                                <div class="card card-login form-label-group">
                                    <button class="btn btn-primary" name="btnProcedure" value="Find" type="submit">
                                        Find services by date
                                    </button>
                                </div>
                            </th>
                        </tr>
                        </thead>
                    </form:form>
                </c:if>
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Date</th>
                    <c:if test="${role == \"ROLE_USER\"}">
                        <th scope="col">Doctor</th>
                        <th scope="col">Pet</th>
                        <th scope="col"></th>
                    </c:if>
                    <c:if test="${role == \"ROLE_DOCTOR\"}">
                        <th colspan="2" scope="col">Pet</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="vetServiceOfUser" items="${services}">
                    <form:form action="/ordered_services" method="post" modelAttribute="vetServiceOfUser">
                        <tr>
                            <td>
                                <input type="hidden" required name="id" value="${vetServiceOfUser.id}">
                                <input type="hidden" required name="id_user"
                                       value="${vetServiceOfUser.serviceOwner.id}">
                                <input type="hidden" required name="name_service"
                                       value="${vetServiceOfUser.vetService.name}">
                                    ${vetServiceOfUser.vetService.name}
                            </td>
                            <td>
                                <c:if test="${role == \"ROLE_USER\"}">
                                    <input type="date" required class="form-control" name="date"
                                           value="${vetServiceOfUser.date}">
                                    <span class="d-block small mt-3"
                                          style="color: white">${vetServiceOfUser.id == errorServiceId ? errorDate : ""}
                                    </span>
                                </c:if>
                                <c:if test="${role == \"ROLE_DOCTOR\"}">
                                    ${vetServiceOfUser.date}
                                </c:if>
                            </td>
                            <c:if test="${role == \"ROLE_DOCTOR\"}">
                                <td colspan="2">
                                        ${vetServiceOfUser.pet.name}
                                </td>
                            </c:if>
                            <c:if test="${role == \"ROLE_USER\"}">
                                <td>
                                    <select class="form-control" required name="doctorName">
                                        <option name="doctorName" value="${vetServiceOfUser.doctor.username}">
                                                ${vetServiceOfUser.doctor.username}
                                        </option>
                                        <c:forEach var="doctor" items="${doctors}">
                                            <c:if test="${!doctor.username.equals(vetServiceOfUser.doctor.username)}">
                                                <option name="doctorName" value="${doctor.username}">
                                                        ${doctor.username}
                                                </option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <span class="d-block small mt-3"
                                          style="color: white">${vetServiceOfUser.id == errorServiceId ? errorDoctorName : ""}
                                    </span>
                                </td>
                                <td>
                                    <select class="form-control" name="petName" required>
                                        <option name="petName" value="${vetServiceOfUser.pet.name}">
                                                ${vetServiceOfUser.pet.name}
                                        </option>
                                        <c:forEach var="pet" items="${pets}">
                                            <c:if test="${!pet.name.equals(vetServiceOfUser.pet.name)}">
                                                <option name="petName">${pet.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <span class="d-block small mt-3"
                                          style="color: white">${vetServiceOfUser.id == errorServiceId ? errorPetName : ""}
                                    </span>
                                </td>
                            </c:if>
                            <c:if test="${role == \"ROLE_USER\"}">
                                <td>
                                    <button class="btn btn-primary" name="btnProcedure" value="Relocate" type="submit">
                                        Relocate
                                    </button>
                                    <button class="btn btn-danger" name="btnProcedure" value="Refuse" type="submit">
                                        Refuse
                                    </button>
                                </td>
                            </c:if>
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
