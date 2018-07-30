import React, { Component } from 'react';
import { render } from 'react-dom';


class Login extends Component{
	 
	getInitialState: function() {
	    return { 
				 username:"",
				 password:""
			};
	},
	 
	componentDidMount: function(){

	},

	componentWillUnmount: function(){

	},

	 handleSubmit: function() { 
		
		//alert($("#username").val() +" " +  $("#password").val()); 	
		var data = {
	    		username : $("#username").val(),
	    		password :  $("#password").val()
	    	}
		
	   $.ajax({
	      url: "http://localhost:8090/myapp/test",
	      dataType: 'json',
	      type: 'POST',
		  contentType : "application/json",
	      data: JSON.stringify(data),
	      success: function(data) {
			//alert("Success" + data);
	        this.setState({data: data});
	      }.bind(this),
	      error: function(xhr, status, err) {
			alert( err.toString());
	       console.error(this.props.url, status, err.toString());
	      }.bind(this)
	    });
		 
		return false; 
	  },
	 
	 render () {
	    return (
	     <form onSubmit={this.handleSubmit} >
	       <input type="text" id="username" placeholder="username..." onChange={this.handleChange} required />
	       <br />
	       <input type="password" id="password" placeholder="password..." onChange={this.handleChange} required />
	       <br />
	       <input type="submit" value="Submit" />
	     </form>
	   );
	 }
	}