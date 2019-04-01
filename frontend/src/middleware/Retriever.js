import BuildMeal from './objectBuilder/BuildMeal';
import BuildUser from './objectBuilder/BuildUser';
import BuildMealCombo from './objectBuilder/BuildMealCombo';
import BuildDownload from './objectBuilder/BuildDownload';

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
            case 'api/rest/all':
                const userBuilderAll = new BuildUser();
                this.entityPromise = userBuilderAll.getUserPromiseObj(apiPath);
                break;
            case 'api/combinationreport/weeklycombo':
                const buildMealCombo = new BuildMealCombo();
                this.entityPromise = buildMealCombo.getMealComboPromiseObj(apiPath);
                break;
            case 'api/combinationreport/download/consumers.csv':
                const buildDownload = new BuildDownload();
                this.entityPromise = buildDownload.getCSVDownloadPromise(apiPath);
                break;
            default:
            // code block
        }

    }

    getEntityPromise() {
        return this.entityPromise;
    }
}

export default Retriever;

