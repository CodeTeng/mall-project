import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// 自动按需导入
import AutoImport from 'unplugin-auto-import/vite'

import Components from 'unplugin-vue-components/vite'
// Element-plus按需导入
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
// svg-icon的自动导入
import path from 'path'
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'



export default defineConfig({
  plugins: [
    vue(),
    AutoImport(
      {
        // 自动导入ref
        imports: ['vue'],
        dts: 'src/auto-import.d.ts',
        // 自动导入Element-plus
        resolvers: [ElementPlusResolver()],
      }
    ),

    Components({
      resolvers: [ElementPlusResolver()],
    }),

    // svg-icon-plugin
    createSvgIconsPlugin({
      // 指定要缓存的文件夹
      iconDirs: [path.resolve(process.cwd(), 'src/assets/svg')],
      // 指定symbolId格式
      symbolId: '[name]'
    })

  ],
  // 强制预构建插件包
  optimizeDeps: {
    include: ['axios'],
  },
  // 解决dev环境跨域
  server: {
    cors: true, // 默认启用并允许任何源
    open: true, // 在服务器启动时自动在浏览器中打开应用程序
    proxy: {
      '/api': {
        target: "http://localhost:8081",
        rewrite: (path) => path.replace(/^\/api/, '/api'),
        changeOrigin: true, //是否有跨域
        ws: true,  // 允许websocket代理
      }
    }
  },

  // 打包配置
  build: {
    target: 'modules',
    outDir: 'dist', //指定输出路径
    assetsDir: 'assets', // 指定生成静态资源的存放路径
    minify: 'terser' // 混淆器，terser构建后文件体积更小
  },


})
