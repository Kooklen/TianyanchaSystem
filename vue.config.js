const path = require('path');

const webpack = require('webpack')
const CompressionPlugin = require('compression-webpack-plugin')
const zlib = require('zlib')
const isProduction = process.env.NODE_ENV === 'production'
module.exports = {
    configureWebpack: {
        module: {
            rules: [
                {
                    test: /\.mjs$/,
                    include: /node_modules/,
                    type: "javascript/auto"
                },
                {
                    test: /.md$/,
                    loader: "text-loader"
}
            ]
        }
    ,
        resolve: {
            alias: {
                '@': path.resolve(__dirname, './src'),
                '@i': path.resolve(__dirname, './src/assets'),
            }
        },
        plugins: [
            new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/),
            // 下面两项配置才是 compression-webpack-plugin 压缩配置
            // 压缩成 .gz 文件
            new CompressionPlugin({
                filename: '[path][base].gz',
                algorithm: 'gzip',
                test: /\.js$|\.css$|\.html$/,
                threshold: 10240,
                minRatio: 0.8
            }),
            // 压缩成 .br 文件，如果 zlib 报错无法解决，可以注释这段使用代码，一般本地没问题，需要注意线上服务器会可能发生找不到 zlib 的情况。
            new CompressionPlugin({
                filename: '[path][base].br',
                algorithm: 'brotliCompress',
                test: /\.(js|css|html|svg)$/,
                compressionOptions: {
                    params: {
                        [zlib.constants.BROTLI_PARAM_QUALITY]: 11
                    }
                },
                threshold: 10240,
                minRatio: 0.8
            })
        ]
    },
    // 基本路径
    publicPath:  './',
    productionSourceMap: false,
    // 输出文件目录
    /*outputDir: 'dist', // 默认dist
    // 用于嵌套生成的静态资产（js,css,img,fonts）目录
    // assetsDir: '',
    // 指定生成的 index.html 的输出路径 (相对于 outputDir)。也可以是一个绝对路径
    indexPath: 'index.html', // Default: 'index.html'
    filenameHashing: true,
    // 构建多页时使用
    pages: undefined,
    // eslint-loader是否在保存的时候检查
    lintOnSave: false,
    // 是否使用包含运行时编译器的Vue核心的构建
    runtimeCompiler: false,
    // 默认情况下 babel-loader 会忽略所有 node_modules 中的文件。如果你想要通过 Babel 显式转译一个依赖，可以在这个选项中列出来
    transpileDependencies: [],
    // 如果你不需要生产环境的 source map，可以将其设置为 false 以加速生产环境构建。
    productionSourceMap: false,
    lintOnSave: false,*/
    // publicPath: process.env.NODE_ENV === 'production' ? './' : '/',
    devServer: {
        proxy: {
            '/api': {
                target: 'http://192.168.1.104:9081/', //跨域接口域名
                changeOrigin: true,             //是否跨域
                ws: false,                       //是否代理 websockets
                secure: false,                   //是否https接口
                pathRewrite: {                  //路径重置
                    '^/api': ''
                }
            }
        },
        overlay: {
            warning: false,
            errors: false
        },
        port: 3000,
        https: false, // https:{type:Boolean}
        open: false, //配置后自动启动浏览器
        hotOnly: true, // 热更新
    }
};
