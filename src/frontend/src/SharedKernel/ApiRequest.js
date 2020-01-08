export function getRequest(url) {
    return fetch('http://127.0.0.1:8080/' + url, {
        headers: {
            'Content-Type': 'application/json'
        },
    });
}

export function removeRequest(url) {
    return fetch('http://127.0.0.1:8080/' + url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
    });
}

export function postRequest(url, data) {
    return fetch('http://127.0.0.1:8080/' + url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        },
    });
}