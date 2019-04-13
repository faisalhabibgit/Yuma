import ApiToken from '../ApiToken';
import MealCombo from '../objects/MealCombo';
import BuildMeal from '../objectBuilder/BuildMeal';
import BuildUser from '../objectBuilder/BuildUser';
import CustomLogging from '../../CustomLogging';


class BuildMealCombo {

    constructor() {
        CustomLogging.info("Building Meal Combo...");
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
                    var buildMeal = new BuildMeal();
                    var obj = myJson[i];
                    var mealcombo = new MealCombo();
                    var userList = [];

                    mealcombo.setNumberOfBlanks(obj['numberOfBlanks']);
                    mealcombo.setCombinationScore(obj['combinationScore']);

                    var consumerList = obj['consumerTOS'];
                    var buildUser = new BuildUser();
                    for (let j = 0; j < consumerList.length; j++) {
                        var consumerInfo = consumerList[j];
                        userList.push(buildUser.JSONobjToUser(consumerInfo));
                    }

                    mealcombo.setConsumerList(userList);

                    var mealListCombo = []
                    for (let index = 0; index < obj['mealTOS'].length; index++) {
                        mealListCombo.push(buildMeal.JSONobjToMeal(obj['mealTOS'][index]));
                    }
                    mealcombo.setMealsList(mealListCombo);

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
