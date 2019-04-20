import ApiToken from '../ApiToken';
import CustomLogging from '../../CustomLogging';


class companyList {

  constructor() {
    CustomLogging.info("Getting company list...");
  }

  getCompanyPromiseObj(apiPath) {
    var companyList = [];
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
          companyList.push((myJson[i]));
        }
        return companyList;
      });
  }
}

export default companyList;
