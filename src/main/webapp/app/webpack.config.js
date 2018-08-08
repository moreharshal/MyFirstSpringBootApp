const path = require('path');
const webpack = require('webpack');

module.exports = {
	entry : {
		system : [ "./scripts/index.jsx" ]
	},
	output : {
		filename: "[name].bundle.js",
		path : path.resolve(__dirname, 'dist')
	},
	module : {
		rules : [ {
			test : /\.jsx$/,
			exclude : /node_modules/,
			loader : "babel-loader",
			query : {
				presets : [ 'env', 'react' ]
			}
		} ]
	}
};