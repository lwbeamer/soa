module.exports = {
  transpileDependencies: true,
  publicPath: process.env.NODE_ENV === 'prod' ? '/~s312200/soa-frontend/' : '/',
  devServer: {
    port: 8080,
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