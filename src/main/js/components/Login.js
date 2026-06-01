'use strict';
import React, { Component } from 'react';
import styles from '../../resources/static/login.css';
import axios from 'axios';
import Dashboard from "./Dashboard";
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
 

class Login extends Component {

  constructor(props){
    super(props);
    this.state = {
      'username': '',
      'password': '',
      'errorMessage': '',
      'isSubmitting': false
    }
    
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    this._isMounted = true;
  }

  componentWillUnmount() {
    this._isMounted = false;
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
      this.setState({ errorMessage: '', isSubmitting: true });
      let axiosConfig = {
    		  headers: {
    		      'Content-Type': 'application/json',
    		      "Access-Control-Allow-Origin": "*",
    		  }
    		};

      // Send only auth fields to avoid accidental contract drift
      const payload = {
        username: this.state.username,
        password: this.state.password
      };

      axios.post("/myapp/authenticate", payload, axiosConfig)
          .then(response => {
            if (response.data && response.data.success) {
              localStorage.setItem('login_username', response.data.username || this.state.username);
              localStorage.setItem('login_time', response.data.loginTime || '');
              this.props.history.push('/myapp/dashboard');
              return;
            }

            // Check mount status before setState
            if (this._isMounted) {
              this.setState({ errorMessage: 'Login failed. Please try again.' });
            }
          })
          .catch(error => {
            var apiMessage = error && error.response && error.response.data && error.response.data.message;
            // Check mount status before setState
            if (this._isMounted) {
              this.setState({ errorMessage: apiMessage || 'Unable to login. Please check credentials.' });
            }
          })
          .then(() => {
            // Check mount status before setState to prevent setState-after-unmount
            if (this._isMounted) {
              this.setState({ isSubmitting: false });
            }
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
          {this.state.errorMessage &&
            <tr>
              <td style={{ color: '#c62828', paddingBottom: '10px' }}>{this.state.errorMessage}</td>
            </tr>
          }
           <tr>
             <td>
                <input type="text" name="username" id = "username" placeholder="Email Address" required="this is required" autoFocus="" onChange={this.handleChange} className="form-control" />
             </td>
          </tr>
          <tr>
            <td><input type="password" name="password"  id = "password" placeholder="Password"  required="true"  autoFocus=""  onChange={this.handleChange} className="form-control"  /></td>
          </tr>
            <tr>
              <td><button disabled={this.state.isSubmitting}>{this.state.isSubmitting ? 'SIGNING IN...' : 'SIGN IN'}</button></td>
            </tr>
          </table>
       </form>
       </div>
    );
  }
}

export default Login;
