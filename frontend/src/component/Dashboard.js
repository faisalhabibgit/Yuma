import React, {Component} from 'react';

class Dashboard extends Component {


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
