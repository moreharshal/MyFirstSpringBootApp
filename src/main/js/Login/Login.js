import React, { Component } from 'react';
import '../../resources/static/login.css';

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


 /* handleClick = () => {
    console.log('this is:', this);
  } */
    
  render() {
    return (
      <div id="panel" class="container" >      
       <form class="form-signin" >
         <table>
         <tr>
              <td><h2 class="form-signin-heading" >Please Sign In</h2></td>
          </tr>             
           <tr>
             <td>
                <input type="text" name="username" id = "username" placeholder="Email Address" required="this is required" autoFocus="" class="form-control" />
             </td>
          </tr>
          <tr>
            <td><input type="password" name="password"  id = "password" placeholder="Password"  required="true"  autoFocus="" class="form-control" /></td>
          </tr>
            <tr>
              <td><button id="sendButton" type="button" >SIGN IN</button></td>
            </tr>
          </table>
       </form>
       </div>
    );
  }
}

export default Login;
