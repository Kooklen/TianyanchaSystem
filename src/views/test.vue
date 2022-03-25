<template>
  <!--  <el-button type="primary">下载报告</el-button>-->
  <!--  <el-button type="primary">下载数据</el-button>-->
  <div class="tools">
    <div style="margin-left: 550px">
    <el-button :theme="'default'"  :title="'基础按钮'" @click.stop="prePage" class="mr10"> 上一页</el-button>
    <el-button :theme="'default'"  :title="'基础按钮'" @click.stop="nextPage" class="mr10"> 下一页</el-button>
      </div>
<!--    <span class="page">{{pageNum}}/{{pageTotalNum}} </span>-->
    <div style="width: 1000px;margin: auto">
      <pdf ref="pdf"
           :src="url"
           :page="pageNum"
           :rotate="pageRotate"
           @progress="loadedRatio = $event"
           @page-loaded="pageLoaded($event)"
           @num-pages="pageTotalNum=$event"
           @error="pdfError($event)"
           @link-clicked="page = $event"

      >
      </pdf>
    </div>
  </div>
</template>

<script>
import pdf from 'vue3-pdf'
import Header from "@/components/header";
export default {
  components: {
    Header,
    pdf
  },
  created() {
  },
  data() {
    return {
      url: "/pdf/test.pdf",
      pageNum: 1,
      pageTotalNum: 10,
      pageRotate: 0,
      // 加载进度
      loadedRatio: 0,
      curPageNum: 0,
    }
  },
  created() {},
  // function() {}  },
  methods: {
    // 上一页函数，
    prePage() {
      var page = this.pageNum
      page = page > 1 ? page - 1 : this.pageTotalNum
      this.pageNum = page
    },
    // 下一页函数
    nextPage() {
      var page = this.pageNum
      page = page < this.pageTotalNum ? page + 1 : 1
      this.pageNum = page
    },
    // 页面顺时针翻转90度。
    clock() {
      this.pageRotate += 90
    },
    // 页面逆时针翻转90度。
    counterClock() {
      this.pageRotate -= 90
    },
    // 页面加载回调函数，其中e为当前页数
    pageLoaded(e) {
      this.curPageNum = e
    },
    // 其他的一些回调函数。
    pdfError(error) {
      console.error(error)
    },
  }
}
</script>
