import React from 'react';
import ReactDOM from 'react-dom';
import Routes from './Routes';
import App from './component/App';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
