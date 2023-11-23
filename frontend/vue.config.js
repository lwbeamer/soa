const { defineConfig } = require('@vue/cli-service')
module.exports = {
  transpileDependencies: true,
  publicPath: process.env.NODE_ENV === 'prod' ? '/~s312200/soa-frontend/' : '/',
  devServer: {
    https: true,
    port: 8443,
    hot: true,
    liveReload: true,
    proxy: {
      '/api/auth': {
        target: 'http://localhost:9999/',
        changeOrigin: true
      },
    }
  },
};