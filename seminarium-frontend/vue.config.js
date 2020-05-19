// vue.config.js
module.exports = {
  // see https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    proxy: {
      '/': {
        target: 'http://localhost:8080', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
        ws: true,
        changeOrigin: true
      }
    }
  },
  // see https://cli.vuejs.org/config/
  outputDir: 'target/dist',
  assetsDir: 'static'
};
