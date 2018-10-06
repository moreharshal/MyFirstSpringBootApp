import React, { Component } from 'react';
import styles from '../../resources/static/login.css';
import axios from 'axios';

class Login extends Component {

  constructor(props){
    super(props);
    this.state={
    username:'',
    password:''
    }
  }

  componentDidMount() {

  }

  componentWillUnmount() {

  }

  handleChange(event){
      const {name,value} =event.target();
      alert(name + " "+ value);
  }

  handleSubmit (){

      var jsonData = {};
      jsonData['username'] =document.getElementById("username").value;
      jsonData['password'] =document.getElementById("password").value;

      let axiosConfig = {
    		  headers: {
    		      'Content-Type': 'application/json',
    		      "Access-Control-Allow-Origin": "*",
    		  }
    		};

      axios.post("/myapp/authenticate",jsonData,axiosConfig)
          .then(response => {
        	   alert("response" + response);
          })
          .catch(error => {
        	  alert("error   " + error.response.data.message);
          });
  }
    
  render() {
    return (
      <div id="panel"   >
       <form  className={styles["form-signin"]} method="post" >
         <table>
         <tr>
              <td><h2  className={styles["form-signin-heading"]} >Please Sign In</h2></td>
          </tr>             
           <tr>
             <td>
                <input type="text" name="username" id = "username" placeholder="Email Address" required="this is required" autoFocus=""  className="form-control" />
             </td>
          </tr>
          <tr>
            <td><input type="password" name="password"  id = "password" placeholder="Password"  required="true"  autoFocus="" className="form-control" /></td>
          </tr>
            <tr>
              <td><button onClick={this.handleSubmit}>SIGN IN</button></td>
            </tr>
          </table>
       </form>
       </div>
    );
  }
}

export default Login;
