import React, { Component } from 'react';
import axios from 'axios';

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
    // Verify session with backend instead of relying on localStorage alone
    axios.get('/myapp/authenticate/verify')
      .then(response => {
        if (response.data && response.data.success) {
          // Use authoritative server response for user state
          this.setState({
            username: response.data.username,
            loginTime: response.data.loginTime || ''
          });
          // Sync localStorage with backend state
          localStorage.setItem('login_username', response.data.username);
          localStorage.setItem('login_time', response.data.loginTime || '');
        } else {
          // Server says not authenticated, redirect to login
          this.props.history.replace('/myapp/');
        }
      })
      .catch(error => {
        // If server responds with unauthenticated, redirect to login
        console.error('Session verification failed:', error);
        this.props.history.replace('/myapp/');
      });
  }

  componentWillUnmount() {

  }

  handleLogout() {
    axios.post('/myapp/authenticate/logout')
      .finally(() => {
        localStorage.removeItem('login_username');
        localStorage.removeItem('login_time');
        this.props.history.replace('/myapp/');
      });
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
