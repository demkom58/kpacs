<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>K-PAC List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/dhtmlx/grid.min.css">
    <link rel="stylesheet" type="text/css" href="https://unpkg.com/@phosphor-icons/web@2.1.1/src/bold/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/main.css">
    <script src="${pageContext.request.contextPath}/resources/dhtmlx/grid.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/grid.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const columns = [
                {width: 50, id: "id", header: [{text: "ID"}, {content: "inputFilter" }]},
                {width: 200, id: "title", header: [{text: "Title"}, {content: "inputFilter" }]},
                {width: 400, id: "description", header: [{text: "Description"}, {content: "inputFilter" }]},
                {
                    width: 150, id: "creationDate", header: [{
                        text: "Creation Date", sortAs: timeFromDashedDDMMYYYY
                    }, {content: "inputFilter" }]
                },
                {
                    width: 100,
                    id: "delete",
                    header: [{text: "Delete", headerSort: false}],
                    htmlEnable: true,
                    template: (_id, row) =>
                        `<a onclick="deleteEntry('\${row.id}',
                        '${pageContext.request.contextPath}/api/kpacs/',
                        '${pageContext.request.contextPath}/api/kpacs')"
                            title="Delete"
                            href="javascript:void(0);"
                            class="icon-button">
                                <i class="ph-bold ph-trash"></i>
                        </a>`
                }
            ];

            initGrid("grid-container", {columns}, {
                url: `${pageContext.request.contextPath}/api/kpacs`,
                loadingId: "loading-indicator"
            });

            reloadData();
        });
    </script>
</head>
<body>
<div class="nav-container">
    <span>Navigation: </span>
    <a href="${pageContext.request.contextPath}/sets" class="nav-button">Kpac Sets</a>
</div>

<div class="form-container">
    <form id="create-form"
          onsubmit="return createEntry('create-form',
                  '${pageContext.request.contextPath}/api/kpacs',
                  '${pageContext.request.contextPath}/api/kpacs')
                  ">
        <label>Title: <input type="text" name="title" placeholder="Title" required></label>
        <label>Description: <input type="text" name="description" placeholder="Description" required></label>
        <button type="submit">Add K-PAC</button>
    </form>
</div>
<div id="grid-container"></div>
<div id="loading-indicator">Loading...</div>
</body>
</html>
