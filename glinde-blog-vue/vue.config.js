let proxyObj={}

proxyObj['/'] = {
    //目标地址
    target: 'http://localhost:8080',
    //发送请求头host会被设置为target
    changeOrigin: true,
    //不重写请求地址
    pathRewrite: {
        '^/': '/'
    }
}


module.exports = {
    publicPath : "./",
    devServer: {
        host: 'localhost',
        port: 80,
        proxy: proxyObj
    }
}