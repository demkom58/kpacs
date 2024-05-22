<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>K-PAC List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/dhtmlx/grid.min.css">
    <script src="resources/dhtmlx/grid.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://unpkg.com/@phosphor-icons/web@2.1.1/src/bold/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/main.css"/>

    <script>
        let grid = null;

        const deleteKPac = (id) => {
            if (grid === null) return;
            if (id == null || id === "") return;

            fetch(`${pageContext.request.contextPath}/api/kpacs/\${id}`, {method: 'DELETE'}).then(async response => {
                if (!response.ok) {
                    alert((await response.json()).message)
                }

                reloadData();
            });
        }

        const reloadData = () => {
            if (grid === null) return;
            grid.data.removeAll();

            fetch("${pageContext.request.contextPath}/api/kpacs", {
                method: 'GET',
                headers: {'Content-Type': 'application/json'}
            }).then(async response => {
                if (!response.ok) {
                    alert((await response.json()).message)
                    return Promise.reject();
                }

                return response.json()
            }).then(data => grid.data.parse(data));
        }

        const createKPac = () => {
            const form = document.getElementById("create-form");
            const formData = new FormData(form);

            fetch("${pageContext.request.contextPath}/api/kpacs", {
                method: 'POST',
                body: JSON.stringify(Object.fromEntries(formData)),
                headers: {'Content-Type': 'application/json'}
            }).then(async response => {
                if (!response.ok) {
                    alert((await response.json()).message);
                    return
                }

                reloadData();
                form.reset();
            });

            return false;
        }

        function initGrid() {
            // creating DHTMLX Grid
            grid = new dhx.Grid("grid_container", {
                columns: [
                    {width: 50, id: "id", header: [{text: "ID"}]},
                    {width: 200, id: "title", header: [{text: "Title"}]},
                    {width: 400, id: "description", header: [{text: "Description"}]},
                    {width: 150, id: "creationDate", header: [{text: "Creation Date", sortAs: val => {
                        let [day, month, year] = val.split("-");
                        return new Date(year, month - 1, day).getTime();
                    }}]},
                    {width: 100, id: "delete", header: [{text: "Delete", headerSort: false}], htmlEnable: true,
                        template: (_id, row) => `
                        <a onclick="deleteKPac('\${row.id}')"
                            title="Delete"
                            href="javascript:void(0);"
                            class="icon-button"
                        >
                            <i class="ph-bold ph-trash"></i>
                        </a>`
                    },
                ],
                tooltip: false,
                headerRowHeight: 50,
            });

            // Load data from the server
            reloadData();
        }

        document.addEventListener("DOMContentLoaded", function () {
            initGrid();
        });
    </script>
</head>

<body>
    <form id="create-form" onsubmit="return createKPac()">
        <label>
            Title:
            <input type="text" name="title" placeholder="Title" required>
        </label>
        <label>
            Description:
            <input type="text" name="description" placeholder="Description" required>
        </label>
        <button type="submit">Add K-PAC</button>
    </form>
    <div id="gridbox" style="width: 900px; height:1px;"></div>
</body>

</html>
