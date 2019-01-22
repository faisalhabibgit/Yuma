class ApiToken {

    getToken(url = '', data = {}) {
        // Default options are marked with *
        return fetch(url, {
            method: "POST", // *GET, POST, PUT, DELETE, etc.
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                "Content-Type": "application/json",
                // "Content-Type": "application/x-www-form-urlencoded",
            },
            redirect: "follow", // manual, *follow, error
            referrer: "no-referrer", // no-referrer, *client
            body: JSON.stringify(data), // body data type must match "Content-Type" header
        })
            .then(response => response.json()) // parses response to JSON
            .then(data => data.accessToken)
            .then(token => this.set15mCookie(token)) // sets the token as cookie with a 15mins expiry
            .then(x => {return 'Token Process Complete'})
            .catch(error => console.error("Inside ApiToken: " + error));
    }

    set15mCookie(token) {
        console.log('Cookie is set');
        var d = new Date();
        d.setTime(d.getTime() + (900000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = "yuma-token=" + token.toString() + ";" + expires + ";path=/";
    }

    //'yuma-token' to get brearer token
    getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    clearCookie() {
        document.cookie = "yuma-token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    }

    isAuthenticated() {
        console.log(this.getCookie('yuma-token'));
        if(this.getCookie('yuma-token') === ""){
            return false;
        }else{
            return true;
        }
    }

}

export default ApiToken;