var path = require('path');

module.exports = {
    entry: './src/main/js/app.js',
    devtool: 'sourcemaps',
    mode: "development",
    cache: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test : /\.js$/,
                exclude: /(node_modules)/,
                loader : "babel-loader",
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            },
            {
                test : /\.css$/,
                use: ['style-loader', 'css-loader?modules=true']
            }
        ]
    }
};