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
    <div class="card-header">Operations with services</div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" aria-describedby="Vet services">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Cost BR</th>
                    <c:if test="${role == \"ROLE_USER\"}">
                        <th scope="col">Date</th>
                    </c:if>
                    <c:if test="${role == \"ROLE_USER\"}">
                        <th scope="col">Doctor</th>
                    </c:if>
                    <c:if test="${role == \"ROLE_USER\"}">
                        <th scope="col">Pet</th>
                    </c:if>
                    <c:if test="${role == \"ROLE_USER\" || role == \"ROLE_ADMIN\"}">
                        <th scope="col">Operations</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:if test="${role == \"ROLE_ADMIN\"}">
                    <form:form action="/services" method="post" modelAttribute="vetService">
                        <tr>
                            <td>
                                <input type="text" required class="form-control" name="name"
                                       value="${vetService.name}" placeholder="Name for creating">
                                <span id="name.errors">
                                        <form:errors path="name" cssClass="d-block small mt-3"
                                                     cssStyle="color: white" element="span"/>
                            </td>
                            <td>
                                <input type="number" required class="form-control" name="cost"
                                       value="${vetService.cost}" placeholder="Cost for creating">
                                <span id="cost.errors">
                                        <form:errors path="cost" cssClass="d-block small mt-3"
                                                     cssStyle="color: white" element="span"/>
                            </td>
                            <td>
                                <button class="btn btn-success" name="btnProcedure" value="Create"
                                        type="submit">Create
                                </button>
                            </td>
                        </tr>
                    </form:form>
                </c:if>

                <c:forEach var="vetService" items="${services}">
                    <form:form action="/services" method="post" modelAttribute="vetService">
                        <tr>
                            <td>
                                <c:if test="${role == \"ROLE_ADMIN\"}">
                                <input type="hidden" required name="id" value="${vetService.id}">
                                <input type="text" required class="form-control" name="name"
                                       value="${vetService.name}">
                                <span id="name.errors">
                                    <form:errors path="name" cssClass="d-block small mt-3"
                                                 cssStyle="color: white" element="span"/>
                                </c:if>
                                <c:if test="${role != \"ROLE_ADMIN\"}">
                                    <input type="hidden" required name="id" value="${vetService.id}">
                                    <input type="hidden" required name="name" value="${vetService.name}">
                                    <input type="hidden" required name="cost" value="${vetService.cost}">
                                    ${vetService.name}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${role == \"ROLE_ADMIN\"}">
                                <input type="number" required class="form-control" name="cost"
                                       value="${vetService.cost}">
                                <span id="cost.errors">
                                    <form:errors path="cost" cssClass="d-block small mt-3"
                                                 cssStyle="color: white" element="span"/>
                                </c:if>

                                <c:if test="${role != \"ROLE_ADMIN\"}">
                                    ${vetService.cost} BR
                                </c:if>
                            </td>
                            <c:if test="${role == \"ROLE_ADMIN\"}">
                                <td>
                                    <button class="btn btn-primary" name="btnProcedure" value="Update" type="submit">
                                        Update
                                    </button>
                                    <button class="btn btn-danger" name="btnProcedure" value="Delete" type="submit">
                                        Delete
                                    </button>
                                </td>
                            </c:if>
                            <c:if test="${role == \"ROLE_USER\"}">
                                <td>
                                    <input type="date" required class="form-control" name="date">
                                    <span class="d-block small mt-3" style="color: white">
                                            ${errorServiceName == vetService.name ? errorDate : ""}
                                    </span>
                                </td>
                                <td>
                                    <select class="form-control" required name="doctorName">
                                        <option>Choose doctor for your pet</option>
                                        <c:forEach var="doctor" items="${doctors}">
                                            <option name="doctorName" value="${doctor.username}">
                                                    ${doctor.username}
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <span class="d-block small mt-3" style="color: white">
                                            ${errorServiceName == vetService.name ? errorDoctorName : ""}
                                    </span>
                                </td>
                                <td>
                                    <select class="form-control" required name="petName">
                                        <option>Choose your pet</option>
                                        <c:forEach var="pet" items="${pets}">
                                            <option name="petName" value="${pet.name}">${pet.name}</option>
                                        </c:forEach>
                                    </select>
                                    <span class="d-block small mt-3" style="color: white">
                                            ${errorServiceName == vetService.name ? errorPetName : ""}
                                    </span>
                                </td>
                                <td>
                                    <button class="btn btn-primary" name="btnProcedure" value="Order" type="submit">
                                        Order
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
