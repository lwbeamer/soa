const { defineConfig } = require('@vue/cli-service')
const express = require('express');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8080,
    hot: false,
    liveReload: false,
    onAfterSetupMiddleware(devServer) {
      const expressApp = devServer.app;
      expressApp.use('/assets', express.static('src/assets'));
    }
    ,
    proxy: {
      '/api': {
        target: 'http://localhost:8090/',
        changeOrigin: true
      },
    }
  }
});