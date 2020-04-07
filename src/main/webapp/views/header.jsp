<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Custom fonts for this template -->
    <link href="https://fonts.googleapis.com/css?family=Raleway:
    100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link type="text/css" href="css/business-casual.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
    <div class="container">
        <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="/">Vet Clinic</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav mx-auto">
                <li class="nav-item px-lg-4">
                    <a class="nav-link text-uppercase text-expanded" href="/">Home</a>
                </li>
                <li class="nav-item px-lg-4">
                    <a class="nav-link text-uppercase text-expanded" href="/services">Services</a>
                </li>
                <c:if test="${role != \"ROLE_ADMIN\" && role != \"ROLE_USER\" && role != \"ROLE_DOCTOR\"}">
                    <li class="nav-item px-lg-4">
                        <a class="nav-link text-uppercase text-expanded" href="/login">Login</a>
                    </li>
                    <li class="nav-item px-lg-4">
                        <a class="nav-link text-uppercase text-expanded" href="/registration">Registration</a>
                    </li>
                </c:if>
                <c:if test="${role == \"ROLE_ADMIN\"}">
                    <li class="nav-item px-lg-4">
                        <div class="btn-group">
                            <a class="nav-link text-uppercase text-expanded" href="/profile">${user.username}</a>
                            <button type="button" class="nav-item btn btn-menu dropdown-toggle dropdown-toggle-split"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="/services">Operations with services</a>
                                <a class="dropdown-item" href="/create_account">Create account for doctor</a>
                                <a class="dropdown-item" href="/doctors">Delete account for doctor</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/logout">Logout</a>
                            </div>
                        </div>
                    </li>
                </c:if>
                <c:if test="${role == \"ROLE_USER\"}">
                    <li class="nav-item active px-lg-4">
                        <div class="btn-group">
                            <a class="nav-link text-uppercase text-expanded" href="/profile">${user.username}
                                <span class="sr-only">(current)</span>
                            </a>
                            <button type="button" class="nav-item btn btn-menu dropdown-toggle dropdown-toggle-split"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="/services">Order service</a>
                                <a class="dropdown-item" href="/my_pets">My pets</a>
                                <a class="dropdown-item" href="/ordered_services">My services</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/logout">Logout</a>
                            </div>
                        </div>
                    </li>
                </c:if>
                <c:if test="${role == \"ROLE_DOCTOR\"}">
                    <li class="nav-item active px-lg-4">
                        <div class="btn-group">
                            <a class="nav-link text-uppercase text-expanded" href="/profile">${user.username}
                                <span class="sr-only">(current)</span>
                            </a>
                            <button type="button" class="nav-item btn btn-menu dropdown-toggle dropdown-toggle-split"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="/ordered_services">My services</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/logout">Logout</a>
                            </div>
                        </div>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
