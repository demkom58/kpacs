<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>K-PAC Sets</title>
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
                {
                    width: 100,
                    id: "delete",
                    header: [{text: "Delete", headerSort: false}],
                    htmlEnable: true,
                    template: (_id, row) =>
                        `<a onclick="deleteEntry('\${row.id}',
                        '${pageContext.request.contextPath}/api/sets/',
                        '${pageContext.request.contextPath}/api/sets')"
                            title="Delete"
                            href="javascript:void(0);"
                            class="icon-button">
                                <i class="ph-bold ph-trash"></i>
                        </a>`
                }
            ];

            const grid = initGrid("grid-container", {columns, rowCss: _ => 'link-row'}, {
                url: `${pageContext.request.contextPath}/api/sets`,
                loadingId: "loading-indicator"
            });

            grid.events.on("CellClick", (row, col) => {
                if (col.id === 'delete') return
                window.location.href = `${pageContext.request.contextPath}/set/\${row.id}`;
            });

            reloadData();
        });
    </script>
</head>
<body>
<div class="nav-container">
    <span>Navigation: </span>
    <a href="${pageContext.request.contextPath}/kpacs" class="nav-button">Kpacs</a>
</div>

<div class="form-container">
    <form id="create-form"
          onsubmit="return createEntry('create-form',
                  '${pageContext.request.contextPath}/api/sets',
                  '${pageContext.request.contextPath}/api/sets')">
        <label>Title:
            <input type="text" name="title" placeholder="Title" required>
        </label>
        <button type="submit">Add K-PAC Set</button>
    </form>
</div>
<div id="grid-container"></div>
<div id="loading-indicator">Loading...</div>
</body>
</html>
