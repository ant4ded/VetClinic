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
    <div class="card-header">Operations with pets</div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" aria-describedby="Pets">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Kind</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <form:form action="/my_pets" method="post" modelAttribute="pet">
                    <tr>
                        <td>
                            <input type="text" required class="form-control" name="name"
                                   value="${pet.name}" placeholder="Name for creating">
                            <span class="d-block small mt-3"
                                  style="color: white">${errorPetId == null ? errorPetName : ""}
                            </span>
                        </td>
                        <td>
                            <input type="text" required class="form-control" name="petType"
                                   value="${pet.petType}" placeholder="Pet kind">
                            <span class="d-block small mt-3"
                                  style="color: white">${errorPetId == null ? errorKind : ""}
                            </span>
                        </td>
                        <td>
                            <button class="btn btn-success" name="btnProcedure" value="Create"
                                    type="submit">Create
                            </button>
                        </td>
                    </tr>
                </form:form>
                <c:forEach var="pet" items="${pets}">
                    <form:form action="/my_pets" method="post" modelAttribute="pet">
                        <tr>
                            <td>
                                <input type="hidden" required name="id" value="${pet.id}">
                                <input type="hidden" required name="username" value="${pet.owner.username}">
                                <input type="text" required class="form-control" name="name"
                                       value="${pet.name}">
                                <span class="d-block small mt-3"
                                      style="color: white">${pet.id == errorPetId ? errorPetName : ""}
                                </span>
                            </td>
                            <td>
                                <input type="text" required class="form-control" name="petType"
                                       value="${pet.petType}">
                                <span class="d-block small mt-3"
                                      style="color: white">${pet.id == errorPetId ? errorKind : ""}
                                </span>
                            </td>
                            <td>
                                <button class="btn btn-primary" name="btnProcedure" value="Update" type="submit">
                                    Update
                                </button>
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
