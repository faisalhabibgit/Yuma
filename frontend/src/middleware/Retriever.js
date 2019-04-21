import BuildMeal from './objectBuilder/BuildMeal';
import BuildUser from './objectBuilder/BuildUser';
import BuildMealCombo from './objectBuilder/BuildMealCombo';
import BuildDownload from './objectBuilder/BuildDownload';
import companyList from "./objectBuilder/CompanyList";

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
      case 'api/update/users':
        const userBuilderUpdate = new BuildUser();
        this.entityPromise = userBuilderUpdate.getUserPromiseObj(apiPath);
        break;
      case 'api/combinationreport/weeklycombo':
        const buildMealCombo = new BuildMealCombo();
        this.entityPromise = buildMealCombo.getMealComboPromiseObj(apiPath);
        break;
      case 'api/combinationreport/download/consumers.csv':
        const buildDownload = new BuildDownload();
        this.entityPromise = buildDownload.getCSVDownloadPromise(apiPath);
        break;
      case 'api/rest/listcompanies':
        const getComapnyList = new companyList();
        this.entityPromise = getComapnyList.getCompanyPromiseObj(apiPath);
        break;
      default:
        const userBuilderCompany = new BuildUser();
        this.entityPromise = userBuilderCompany.getUserPromiseObj(apiPath);
    }

  }

  getEntityPromise() {
    return this.entityPromise;
  }
}

export default Retriever;

