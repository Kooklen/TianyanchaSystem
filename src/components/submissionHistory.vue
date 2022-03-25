<template>
  <el-table
      class="el-table"
      :data="tableData"
      style="width: 100%">
    <el-table-column
        prop="company"
        label="企业名称"
    >
    </el-table-column>
    <el-table-column
        prop="date"
        label="监控日期"
    >
    </el-table-column>
    <el-table-column
        prop="status"
        label="状态">
    </el-table-column>
  </el-table>

  <div class="block">
  </div>
</template>

<script>
export default {
  name: "submissionHistory",
  data() {
    return {
      tableData: [{
        company: '广州奇越网联数据科技有限公司',
        date: '2021-10-12',
        status: '审核中',
      },
        {
          company: '广州奇越网联数据科技有限公司',
          date: '2021-10-12',
          status: '审核中',
        }]
    }
  }
}
</script>

<style scoped>
.el-table{
  padding-left: 26px;
}

.block{
  margin: 50px 0 0 350px;
}
</style>
