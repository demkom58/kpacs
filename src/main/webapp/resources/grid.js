let grid = null;
let dataConfig = {
    url: null,
    mapper: (data) => data,
    loadingId: null
}

const showLoadingIndicator = () => {
    if (dataConfig.loadingId) {
        document.getElementById(dataConfig.loadingId).style.display = 'block';
    }
};

const hideLoadingIndicator = () => {
    if (dataConfig.loadingId) {
        document.getElementById(dataConfig.loadingId).style.display = 'none';
    }
};

const initGrid = (containerId, gridConfig, miscConfig) => {
    grid = new dhx.Grid(containerId, {
        tooltip: false,
        headerRowHeight: 50,
        ...gridConfig
    });
    
    dataConfig = {
        ...dataConfig,
        ...miscConfig
    }

    return grid;
};

const reloadData = () => {
    if (grid === null) return;
    if (dataConfig.url == null) return;

    grid.data.removeAll();
    showLoadingIndicator();

    fetchGridData()
        .then(async data => grid.data.parse(dataConfig.mapper(data)))
        .finally(() => hideLoadingIndicator());
};

const fetchGridData = () => {
    if (dataConfig.url == null) return Promise.reject();

    return fetch(dataConfig.url, {method: 'GET', headers: {'Content-Type': 'application/json'}})
        .then(async response => {
            if (!response.ok) {
                alert((await response.json()).message);
                return Promise.reject();
            }
            return response.json();
        });
}

const deleteEntry = (id, deleteUrl, reloadUrl) => {
    if (grid === null) return;
    showLoadingIndicator();

    fetch(deleteUrl + id, {method: 'DELETE'})
        .then(async response => {
            hideLoadingIndicator();
            if (!response.ok) {
                alert((await response.json()).message);
            }
            reloadData(reloadUrl);
        })
        .catch(() => hideLoadingIndicator());
};

const createEntry = (formId, createUrl, reloadUrl) => {
    const form = document.getElementById(formId);
    const formData = new FormData(form);
    showLoadingIndicator();

    fetch(createUrl, {
        method: 'POST',
        body: JSON.stringify(Object.fromEntries(formData)),
        headers: {'Content-Type': 'application/json'}
    }).then(async response => {
        hideLoadingIndicator();
        if (!response.ok) {
            alert((await response.json()).message);
            return;
        }
        reloadData(reloadUrl);
        form.reset();
    }).catch(() => hideLoadingIndicator());
    return false;
};


const timeFromDashedDDMMYYYY = (date) => {
    const [day, month, year] = date.split('-');
    return new Date(year, month - 1, day);
}