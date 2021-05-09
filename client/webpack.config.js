const webpack = require("webpack");
const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

const Dotenv = require("dotenv-webpack");

process.env.NODE_ENV = "development";

module.exports = {
    mode: "development",
    target: "web",
    devtool: "cheap-module-source-map",
    entry: "./src/index",
    output: {
        path: path.resolve(__dirname, "build"),
        publicPath: "/",
        filename: "bundle.js",
    },
    devServer: {
        stats: "minimal",
        overlay: true,
        historyApiFallback: true,
        disableHostCheck: true,
        headers: {"Access-Control-Allow-Origin": "*"},
        https: false,
    },
    plugins: [
        new Dotenv({
            path: "./.env", // Path to .env file (this is the default)
            safe: true, // load .env.example (defaults to "false" which does not use dotenv-safe)
            systemvars: false,
            silent: false,
        }),
        new HtmlWebpackPlugin({
            template: "src/index.html",
        }),
        new webpack.EnvironmentPlugin({
            REACT_APP_AUTH0_CLIENT_ID: null,
            REACT_APP_AUTH0_DOMAIN: null,
            REACT_APP_ROOT_ENDPOINT: null,
            REACT_APP_API_ROOT_ENDPOINT: null,
        }),
    ],
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: ["babel-loader", "eslint-loader"],
            },
            {
                test: /\.(s*)css$/,
                use: ["style-loader", "css-loader"],
            },
            {
                test: /\.(png|woff|woff2|eot|ttf|svg)$/,
                loader: "url-loader?limit=100000",
            },
            {
                test: /\.(ico|jpe?g|png|gif|webp|svg|mp4|webm|wav|mp3|m4a|aac|oga)(\?.*)?$/,
                loader: "file-loader",
            },
        ],
    },
};
