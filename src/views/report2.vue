<template>
  <Header @module="showmodule"></Header>
<!--  <el-button type="primary">下载报告</el-button>-->
<!--  <el-button type="primary">下载数据</el-button>-->
  <div style="width: 70vw;margin: auto">
    <pdf
        ref="pdf"
        v-for="i in numPages"
        :key="i"
        :src="url"
        :page="i"
    ></pdf>
  </div>
</template>

<script>
import pdf from 'vue3-pdf'
import Header from "@/components/header";
// import {h} from 'vue'
export default {
  components:{
    Header,
    pdf
  },
  created() {
  },
  data(){
    return {
      url:"/pdf/test.pdf",
      numPages: null, // pdf 总页数
    }
  },
  created() {
    this.getNumPages()
  },
  methods: {
// # 计算pdf页码总数
    getNumPages() {
      let loadingTask = pdf.createLoadingTask(this.url)
      loadingTask.promise.then(pdf => {
        this.numPages = pdf.numPages
        console.log("success")
      }).catch(err => {
        console.error('pdf 加载失败', err);
      })
    },
  }
}
</script>

<style scoped>

</style>
