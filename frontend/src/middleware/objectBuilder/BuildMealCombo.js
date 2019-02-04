import ApiToken from '../ApiToken';
import MealCombo from '../objects/MealCombo';
import Meal from '../objects/Meal';
import Ingredients from '../objects/Ingredients';
import User from '../objects/User';


class BuildMealCombo {

    constructor() {
        console.log("Building Meal Combo...");
        this.availableCombo = 0;
        
    }

    getMealComboPromiseObj(apiPath) {
        var mealComboList = [];
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
                this.availableCombo = myJson.length;
                for (let i = 0; i < this.availableCombo; i++) {
                    var obj = myJson[i];
                    var mealcombo = new MealCombo();
                    var userList = [];

                    mealcombo.setNumberOfBlanks(obj['numberOfBlanks']);
                    mealcombo.setCombinationScore(obj['combinationScore']);
                    console.log(myJson);
                    var consumerList = obj['userTOS'];
                    for (let j = 0; j < consumerList.length; j++) {
                        var user = new User();
                        var consumerInfo = consumerList[j];

                        user.setUserId(consumerInfo['userId']);
                        user.setFirstName(consumerInfo['firstName']);
                        user.setLastName(consumerInfo['lastName']);
                        user.setEmail(consumerInfo['email']);
                        user.setPlan(consumerInfo['plan']);
                        user.setIsActive(consumerInfo['enabled']);
                        user.setTimestamp(consumerInfo['timestamp']);
                        user.setMealList(consumerInfo['mealList']); //TODO: change from JSON obj to Entity obj
                        user.setDislikesList(consumerInfo['dislikesList']);
                        
                        userList.push(user);
                    }
                    mealcombo.setConsumerList(userList);

                    mealcombo.setMealsList(obj['mealTOS']); //TODO: change from JSON obj to Entity obj

                    mealComboList.push(mealcombo);

                }
                return mealComboList;
            });
    }

    getComboAmmount(){
        return this.availableCombo;
    }

}

export default BuildMealCombo;
