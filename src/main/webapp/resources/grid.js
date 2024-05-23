let grid = null;
let loadingIndicator = null;
let defaultDataUrl = null;
let defaultDataMapper = null;

const showLoadingIndicator = () => {
    if (loadingIndicator) {
        loadingIndicator.style.display = 'block';
    }
};

const hideLoadingIndicator = () => {
    if (loadingIndicator) {
        loadingIndicator.style.display = 'none';
    }
};

const initGrid = (containerId, columns, dataUrl, deleteCallback, loadingId, dataMapper) => {
    grid = new dhx.Grid(containerId, {
        columns: columns,
        tooltip: false,
        headerRowHeight: 50,
    });

    if (loadingId) {
        loadingIndicator = document.getElementById(loadingId);
    }

    defaultDataUrl = dataUrl;
    defaultDataMapper = dataMapper == null ? (data) => data : dataMapper;
    return grid;
};

const reloadData = (dataUrl, mapper) => {
    if (grid === null) return;
    let url = dataUrl == null ? defaultDataUrl : dataUrl;
    if (url == null) return;

    grid.data.removeAll();
    showLoadingIndicator();

    fetchGridData(url)
        .then(async data => {
            grid.data.parse(mapper == null ? defaultDataMapper(data) : mapper(data));
        }).finally(() => hideLoadingIndicator());
};

const fetchGridData = (url) => {
    let fetchUrl = url == null ? defaultDataUrl : url;
    if (fetchUrl == null) return Promise.reject();

    return fetch(fetchUrl, {method: 'GET', headers: {'Content-Type': 'application/json'}})
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