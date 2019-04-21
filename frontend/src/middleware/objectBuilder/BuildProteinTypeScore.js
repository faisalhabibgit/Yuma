import ApiToken from '../ApiToken';
import CustomLogging from '../../CustomLogging';
import ProteinType from "../objects/ProteinType";


class BuildProteinTypeScore {

  constructor() {
    CustomLogging.info("Building Protein Score...");
  }

  getProteinTypePromiseObj(apiPath) {
    var proteinList = [];
    var api;
    const apiToken = new ApiToken();

    var obj = {
      method: 'GET',
      headers: {
        'cache-control': "no-cache",
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + apiToken.getCookie('yuma-token')
      }
    }

    switch (apiPath) {


      case 'api/score/proteinType/percentage':

        api = 'api/score/proteinType/percentage';
        break;
      default:

    }

    return fetch(api, obj)
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Server response wasn\'t OK');
        }
      })
      .then((myJson) => {
        debugger
        console.log("logging myjson"+myJson);
          var obj = myJson;
        proteinList.push(this.JSONobjToProteinType(obj));
        return proteinList;
      });
  }
  
  JSONobjToProteinType(obj) {
   
    var proteinType = new ProteinType();
    proteinType.setBeef(obj.BEEF);
    proteinType.setPoultry(obj.POULTRY);
    proteinType.setFish(obj.FISH);
    proteinType.setLamb(obj.LAMB);
    console.log(proteinType);

    return proteinType;
  }

}

export default BuildProteinTypeScore;
