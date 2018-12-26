import BuildMeal from './objectBuilder/BuildMeal';

class Retriever {

    constructor(apiPath) {
        this.builtEntity;

        switch (apiPath) {
            case 'api/meals':
                const mealBuilder = new BuildMeal();
                mealBuilder.getMealPromiseObj()
                    .then((obj) => { console.log(obj) });
                break;
            case 'aaa':
                // code block
                break;
            default:
            // code block
        }

    }

    getEntity(){
        return this.builtEntity;
    }

    toString() {
        console.log("Inside the Retriever class, a call to toString");
        return "This is the toString function";
    }

}

export default Retriever;

