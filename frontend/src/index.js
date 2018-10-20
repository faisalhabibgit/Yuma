import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import LoginCaterer from './LoginCaterer';
import registerServiceWorker from './registerServiceWorker';
import './index.css';

ReactDOM.render(<App />, document.getElementById('root'));
ReactDOM.render(<LoginCaterer />, document.getElementById('root'));
registerServiceWorker();
