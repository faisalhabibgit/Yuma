import BuildMeal from './objectBuilder/BuildMeal';

class Retriever {

    constructor(apiPath) {
        this.entityPromise;

        switch (apiPath) {
            case 'api/meals':
                const mealBuilder = new BuildMeal();
                this.entityPromise = mealBuilder.getMealPromiseObj();
                break;
            case 'aaa':
                // code block
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

