import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as echarts from 'echarts';
import 'lib-flexible'
import './css/style.css'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import * as ElIconModules from '@element-plus/icons-vue'
import { createPinia } from 'pinia'

// @ts-ignore
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

// @ts-ignore
import Prism from 'prismjs';

VueMarkdownEditor.use(vuepressTheme, {
    Prism,
});

// @ts-ignore
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
// @ts-ignore
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// highlightjs
import hljs from 'highlight.js';

VMdPreview.use(githubTheme, {
    Hljs: hljs,
});

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

const app = createApp(App)
for(let iconName in ElIconModules){
    // @ts-ignore
    app.component(iconName,ElIconModules[iconName])
}
app.use(createPinia())
app.config.globalProperties.$echarts = echarts
app.use(ElementPlus)
app.use(mavonEditor)
app.use(VMdPreview);

app.use(router).mount('#app')
app.use(VueMarkdownEditor);
