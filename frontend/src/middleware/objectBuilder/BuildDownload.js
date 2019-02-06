import ApiToken from '../ApiToken';

class BuildDownload {

    constructor() {
        console.log("Start Download...");
    }

    getCSVDownloadPromise(apiPath) {
        const apiToken = new ApiToken();

        var obj = {
            method: 'GET',
            headers: {
                'cache-control': "no-cache",
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + apiToken.getCookie('yuma-token')
            }
        }

        return fetch(apiPath, obj)
            .then((response) => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error('Server response wasn\'t OK');
                }
            });
    }

}

export default BuildDownload;
