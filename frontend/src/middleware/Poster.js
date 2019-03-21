import ApiToken from './ApiToken';

class Poster {

    constructor() {
        this.apiToken = new ApiToken();
    }

    //Make a post request and expect nothing in return
    selectMealCombo(data) {
        const API = 'api/combinationreport/weeklycombo/'+data.toString()-1;
        return fetch(API, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.apiToken.getCookie('yuma-token')
            }
        });
    }

    //Toggle the a meal's availability
    toggleMeal(mealId) {
        const API = 'api/meals/availability/'+mealId;
        return fetch(API, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.apiToken.getCookie('yuma-token')
            }
        });
    }

}

export default Poster;
