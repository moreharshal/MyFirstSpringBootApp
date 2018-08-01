const path = require('path');

module.exports = {
  module: {
	 entry: "./src/index.js",
	 output: {
		    path: path.resolve('dist'),
			filename : 'bundled.js'
		  },
     rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader"
        }
      }
    ]
  }
};