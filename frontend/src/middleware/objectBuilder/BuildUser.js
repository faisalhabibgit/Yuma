import ApiToken from '../ApiToken';
import User from '../objects/User';
import Plan from '../objects/Plan';
import BuildMeal from '../objectBuilder/BuildMeal';
import CustomLogging from '../../CustomLogging';


class BuildUser {

    constructor() {
        CustomLogging.info("Building User...");
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
                    userList.push(this.JSONobjToUser(myJson[i]));
                }
                return userList;
            });
    }

    JSONobjToUser(obj){

        var buildMeal = new BuildMeal();

        var user = new User();
        user.setUserId(obj['userId']);
        user.setFirstName(obj['firstName']);
        user.setLastName(obj['lastName']);
        user.setCompany(obj['company'])
        user.setEmail(obj['email']);
        user.setPlan(this.JSONobjToPlan(obj['plan']));
        user.setIsActive(obj['enabled']);
        user.setTimestamp(obj['timestamp']);
        user.setCompany(obj['company']);

        var mealList = [];
        for (let index = 0; index < obj['mealList'].length; index++) {
            mealList.push(buildMeal.JSONobjToMeal(obj['mealList'][index]));
        }

        var allergies = [];
        for (let index = 0; index < obj['allergies'].length; index++) {
            allergies.push(obj['allergies'][index]);
        }

        user.setMealList(mealList);
        user.setDislikesList(obj['dislikesList']);

        return user;
    }

    JSONobjToPlan(obj){
        var plan = new Plan();
        plan.setNumOfMeal(obj.numOfMeals);
        plan.setDetails(obj.details);
        plan.setSpecialRequests(obj.specialRequests);
        plan.setRequestedProteinTypes(obj.requestedProteinTypes);
        plan.setDiet(obj.diet);
        return plan;
    }


}

export default BuildUser;
