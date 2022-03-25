<template>
  <div id="nav">
    <router-view></router-view>
  </div>
</template>

<style lang="less">
body{
  margin: 0px;
  padding: 0;
}
</style>

<script>
export default {
  methods:{
    startLoading() {
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
},
    endLoading() {
      this.$loading.close();
    }
  }}
</script>
