'use strict';
import Login from "./components/Login";
import Error from "./components/Error";
import Dashboard from "./components/Dashboard";
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';

const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {employees: []};
	}

	componentDidMount() {
		 

	}

	render() {		
		return (
			<BrowserRouter>				 
			 	<Switch>
				 	 <Route path="/myapp/Error" component={Error} exact />
				 	 <Route path="/myapp/dashboard" component={Dashboard} exact />
				 	 <Route component={Login} />
			 	 </Switch>			
			</BrowserRouter>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)


