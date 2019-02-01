import ApiToken from '../ApiToken';
import User from '../objects/User';


class BuildUser {

    constructor() {
        console.log("Building User...");
    }

    getUserPromiseObj(apiPath) {
        var userList = [];
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
                    return response.json();
                } else {
                    throw new Error('Server response wasn\'t OK');
                }
            })
            .then((myJson) => {
                for (var i = 0; i < myJson.length; i++) {

                    var obj = myJson[i];
                    var user = new User();
                    user.setUserId(obj['userId']);
                    user.setFirstName(obj['firstName']);
                    user.setLastName(obj['lastName']);
                    user.setEmail(obj['email']);
                    user.setPlan(obj['plan']);
                    user.setIsActive(obj['enabled']);
                    user.setTimestamp(obj['timestamp']);
                    user.setMealList(obj['mealList']); //TODO: change from JSON obj to Entity obj
                    user.setDislikesList(obj['dislikesList']);

                    userList.push(user);

                }
                return userList;
            });
    }


}

export default BuildUser;
