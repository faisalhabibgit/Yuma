import React, {Component} from 'react';
import ApiToken from "../middleware/ApiToken";

class Dashboard extends Component {


  constructor(props) {
    super(props);

    const apiToken = new ApiToken();
    if (!apiToken.isAuthenticated()) {
      console.log('Check Authentification Dashboard: FAIL');
      this.props.history.push(`/Login`)
    } else {
      console.log('Check Authentification Dashboard: PASS');
    }
  }
  
  render() {
    return (
      <div style={{background: '#696969'}}>

        <div>

          <div className="card-deck" style={{padding: '12px', height: '300px'}}>
            <div className="card">
              <div className="card-header">Best Meal Combo</div>
              <div className="card-body">
              </div>
            </div>
          </div>
        </div>

        <div>
          <div className="card-deck" style={{padding: '12px', height: '300px'}}>
            <div className="card">
              <div className="card-header">Header</div>

              <div className="card-body">
              </div>

            </div>
            <div className="card">
              <div className="card-header">Header</div>
              <div className="card-body">
              </div>
            </div>

            <div className="card">
              <div className="card-header">Header</div>
              <div className="card-body">

              </div>

            </div>
          </div>


        </div>


        <div>
          <div className="card-deck" style={{padding: '12px', height: '200px'}}>
            <div className="card">
              <div className="card-header">Header</div>
              <div className="card-body">
              </div>
            </div>
            <div className="card">
              <div className="card-header">Header</div>
              <div className="card-body">
              </div>
            </div>

          </div>


        </div>

      </div>


    );

  }
}


export default Dashboard;
