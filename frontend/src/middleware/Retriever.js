import BuildMeal from './objectBuilder/BuildMeal';

class Retriever {

    constructor(apiPath) {
        this.entityPromise = null;

        switch (apiPath) {
            case 'api/meals/all':
                const mealBuilder = new BuildMeal();
                this.entityPromise = mealBuilder.getMealPromiseObj(apiPath);
                break;
           case 'api/meals/availablemeals':
              this.entityPromise = mealBuilder.getMealPromiseObj(apiPath);
             break;
            default:
            // code block
        }

    }

    getEntityPromise(){
        return this.entityPromise;
    }
}

export default Retriever;

