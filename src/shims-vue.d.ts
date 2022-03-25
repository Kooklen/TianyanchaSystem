/* eslint-disable */
declare module '@kangc/v-md-editor/lib/theme/vuepress.js';

declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}
//此处用来声明第三方库的东西
declare module '*.vue' {
  import Vue from 'vue'
  export default Vue
}
declare module 'qs' {
  const qs: any
  export default qs
}
declare module 'store' {
  const store: any
  export default store
}
