export function getRequest(url) {
    return fetch('http://homelibrary-env.3wt3cbviwk.eu-central-1.elasticbeanstalk.com/' + url, {
        headers: {
            'Content-Type': 'application/json'
        },
    });
}

export function removeRequest(url) {
    return fetch('http://homelibrary-env.3wt3cbviwk.eu-central-1.elasticbeanstalk.com/' + url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
    });
}

export function postRequest(url, data) {
    return fetch('http://homelibrary-env.3wt3cbviwk.eu-central-1.elasticbeanstalk.com/' + url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        },
    });
}