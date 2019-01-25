import BuildMeal from './objectBuilder/BuildMeal';

class Retriever {

    constructor(apiPath) {
        this.entityPromise = null;
      
        switch (apiPath) {
          
            case 'api/meals/all':
              const mealBuilderAll = new BuildMeal();
              this.entityPromise = mealBuilderAll.getMealPromiseObj(apiPath);
              break;
           case 'api/meals/availablemeals':
             const mealBuilderAvailable = new BuildMeal();
             this.entityPromise = mealBuilderAvailable.getMealPromiseObj(apiPath);
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

