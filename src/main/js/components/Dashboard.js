import React, { Component } from 'react';

class Dashboard extends Component {

  constructor(props){
    super(props);
    this.state = {
      username: '',
      loginTime: ''
    };
    this.handleLogout = this.handleLogout.bind(this);
  }

  componentDidMount() {
    var username = localStorage.getItem('login_username');
    var loginTime = localStorage.getItem('login_time');
    if(!username){
      this.props.history.replace('/myapp/');
      return;
    }

    this.setState({ username: username, loginTime: loginTime || '' });
  }

  componentWillUnmount() {

  }

  handleLogout() {
    localStorage.removeItem('login_username');
    localStorage.removeItem('login_time');
    this.props.history.replace('/myapp/');
  }

  render() {
    return (
      <div id="panel" >
            <h2>Dashboard</h2>
            <p>Welcome, {this.state.username}.</p>
            {this.state.loginTime && <p>Last login: {this.state.loginTime}</p>}
            <button onClick={this.handleLogout}>Logout</button>
        </div>
    );
  }
}

export default Dashboard;
