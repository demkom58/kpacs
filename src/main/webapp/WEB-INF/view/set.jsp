<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>K-PAC Set Details</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/dhtmlx/grid.min.css">
    <link rel="stylesheet" type="text/css" href="https://unpkg.com/@phosphor-icons/web@2.1.1/src/bold/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/main.css">
    <script src="${pageContext.request.contextPath}/resources/dhtmlx/grid.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/grid.js"></script>
    <script>
        showLoadingIndicator();

        document.addEventListener("DOMContentLoaded", function () {
            const columns = [
                {width: 50, id: "id", header: [{text: "K-PAC ID"}]},
                {width: 200, id: "title", header: [{text: "Title"}]},
                {width: 400, id: "description", header: [{text: "Description"}]},
                {
                    width: 150, id: "creationDate", header: [{
                        text: "Creation Date", sortAs: timeFromDashedDDMMYYYY
                    }]
                }
            ];

            let dataEndpoint = `${pageContext.request.contextPath}/api/set/${id}`;
            let grid = initGrid("grid-container", {columns}, {
                url: dataEndpoint,
                loadingId: "loading-indicator",
                mapper: data => data.pacs,
            });

            fetchGridData(dataEndpoint)
                .then(data => {
                    document.getElementById("set-title").textContent = data.title;
                    grid.data.parse(dataConfig.mapper(data));
                }).finally(hideLoadingIndicator)
        });
    </script>
</head>
<body>
<div class="nav-container">
    <span>Navigation: </span>
    <a href="${pageContext.request.contextPath}/kpacs" class="nav-button">Kpacs</a>
    <a href="${pageContext.request.contextPath}/sets" class="nav-button">Kpac Sets</a>
</div>
<div>
    <span>Set Title: </span>
    <span id="set-title">Loading...</span>
</div>
<div id="grid-container"></div>
<div id="loading-indicator">Loading...</div>
</body>
</html>
