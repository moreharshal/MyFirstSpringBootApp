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
      event.preventDefault();
       var body = {
          userName: document.getElementById("username"),
          password: document.getElementById("password")
      }
      alert(body);
      axios.post({url:"http://localhost:8080/myapps/authenticate",data:body,
          config: { headers: {'Content-Type': 'application/json'}}})
          .then(response => {
            console.log(response);
          })
  }
    
  render() {
    return (
      <div id="panel"  onSubmit={this.handleClick} >
       <form  class={styles["form-signin"]} >
         <table>
         <tr>
              <td><h2  class={styles["form-signin-heading"]} >Please Sign In</h2></td>
          </tr>             
           <tr>
             <td>
                <input type="text" name="username" id = "username" placeholder="Email Address" required="this is required" autoFocus="" onChange={this.handleChange(this)}  class="form-control" />
             </td>
          </tr>
          <tr>
            <td><input type="password" name="password"  id = "password" placeholder="Password"  required="true"  autoFocus="" class="form-control" /></td>
          </tr>
            <tr>
              <td><button id="sendButton" type="submit" >SIGN IN</button></td>
            </tr>
          </table>
       </form>
       </div>
    );
  }
}

export default Login;
