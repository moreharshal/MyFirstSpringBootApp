import React, { Component } from 'react';
import axios from 'axios';

class Dashboard extends Component {

  constructor(props){
    super(props);
    this.state = {
      username: '',
      loginTime: '',
      isValidating: true,
      isAuthenticated: false
    };
    this.handleLogout = this.handleLogout.bind(this);
  }

  componentDidMount() {
    // Validate session with backend instead of trusting localStorage
    var token = localStorage.getItem('auth_token');
    if(!token){
      this.props.history.replace('/myapp/');
      return;
    }

    // Call backend to validate the token
    axios.get('/myapp/session/validate', {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    })
    .then(response => {
      if (response.data && response.data.valid) {
        // Token is valid - populate username from server response
        this.setState({
          username: response.data.username,
          loginTime: new Date().toLocaleString(),
          isValidating: false,
          isAuthenticated: true
        });
      } else {
        // Token invalid - redirect to login
        localStorage.removeItem('auth_token');
        this.props.history.replace('/myapp/');
      }
    })
    .catch(error => {
      // Authentication failed - redirect to login
      localStorage.removeItem('auth_token');
      this.props.history.replace('/myapp/');
    });
  }

  componentWillUnmount() {

  }

  handleLogout() {
    var token = localStorage.getItem('auth_token');
    if (token) {
      // Call backend to invalidate the token
      axios.post('/myapp/session/logout', {}, {
        headers: {
          'Authorization': 'Bearer ' + token
        }
      })
      .catch(error => {
        // Even if logout fails on backend, clear local token
        console.error('Logout error:', error);
      })
      .finally(() => {
        localStorage.removeItem('auth_token');
        this.props.history.replace('/myapp/');
      });
    } else {
      localStorage.removeItem('auth_token');
      this.props.history.replace('/myapp/');
    }
  }

  render() {
    if (this.state.isValidating) {
      return (
        <div id="panel">
          <p>Validating session...</p>
        </div>
      );
    }

    if (!this.state.isAuthenticated) {
      return null; // Will redirect in componentDidMount
    }

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
