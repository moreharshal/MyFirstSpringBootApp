'use strict';
import React, { Component } from 'react';
import styles from '../../resources/static/login.css';
import axios from 'axios';
import Dashboard from "./Dashboard";
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';

class Login extends Component {

  constructor(props){
    super(props);
    this.state={'username':'', 'password':''}    
    
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {

  }

  componentWillUnmount() {

  }

  handleChange(event){	  
      var value = event.target.value;
      var source = event.target.id;
      if(source=='username'){
    	  this.setState({'username': value});  
      }else{
    	  this.setState({'password': value});  
      }
  }

  handleSubmit(event){
	  event.preventDefault();
      let axiosConfig = {
    		  headers: {
    		      'Content-Type': 'application/json',
    		      "Access-Control-Allow-Origin": "*",
    		  }
    		};

      axios.post("/myapp/authenticate",this.state,axiosConfig)
          .then(response => {

            alert("response" + response);
            <Route path="/dashboard" component={Dashboard} />
              //  <Redirect push to="/dashboard"/>
        	  // this.props.history.push('/dashboard');

            alert(" This is last line at response");
          })
          .catch(error => {
        	  alert("error   " + error.response.data.message);
          });
  }
    
  render() {
    return (
      <div id="panel"   >
       <form  className={styles["form-signin"]} method="post" onSubmit={this.handleSubmit}>
         <table>
         <tr>
              <td><h2  className={styles["form-signin-heading"]} >Please Sign In</h2></td>
          </tr>             
           <tr>
             <td>
                <input type="text" name="username" id = "username" placeholder="Email Address" required="this is required" autoFocus="" onChange={this.handleChange} className="form-control" />
             </td>
          </tr>
          <tr>
            <td><input type="password" name="password"  id = "password" placeholder="Password"  required="true"  autoFocus=""  onChange={this.handleChange} className="form-control"  /></td>
          </tr>
            <tr>
              <td><button>SIGN IN</button></td>
            </tr>
          </table>
       </form>
       </div>
    );
  }
}

export default Login;
