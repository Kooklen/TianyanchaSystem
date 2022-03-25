<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item
          v-for="(data, index) in companyData"
          :key="index"
          :timestamp="data.date" placement="top"
          style="height: 95px">
        <el-card>
          <h1>{{ data.company }}</h1>
          <div class="kong"></div>
          <span>法人：{{ data.legalPeople }}</span>
          <span>企业地址：{{ data.enterpriseAddress }}</span>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>

<!--  <div class="block1">-->
<!--    <el-pagination-->
<!--        @size-change="handleSizeChange"-->
<!--        @current-change="handleCurrentChange"-->
<!--        :current-page="currentPage4"-->
<!--        :page-sizes="[100, 200, 300, 400]"-->
<!--        :page-size="100"-->
<!--        layout="total, sizes, prev, pager, next, jumper"-->
<!--        :total="400">-->
<!--    </el-pagination>-->
<!--  </div>-->
  <br>
</template>

<script>
export default {
  name: "history",
  data() {
    return {
      companyData: [{
        index: 0,
        company: '广州奇越网联数据科技有限公司',
        date: '2021-07-15',
        legalPeople: '韩英伟',
        enterpriseAddress: '广州市南沙区黄阁镇市南公路黄阁段230号（自编三栋）105-51房',

      },
        {
          index: 1,
          company: '佛山市顺德区马银焊接材料有限公司',
          date: '2021-07-20',
          legalPeople: '罗宇恒',
          enterpriseAddress: '佛山市顺德区容桂街道马岗新工业区',

        },
        {
          index: 1,
          company: '佛山市顺德区马银焊接材料有限公司',
          date: '2021-07-20',
          legalPeople: '罗宇恒',
          enterpriseAddress: '佛山市顺德区容桂街道马岗新工业区',

        },
        {
          index: 1,
          company: '佛山市顺德区马银焊接材料有限公司',
          date: '2021-07-20',
          legalPeople: '罗宇恒',
          enterpriseAddress: '佛山市顺德区容桂街道马岗新工业区',

        },
        {
          index: 1,
          company: '佛山市顺德区马银焊接材料有限公司',
          date: '2021-07-20',
          legalPeople: '罗宇恒',
          enterpriseAddress: '佛山市顺德区容桂街道马岗新工业区',

        }]
    }
  }
}
</script>

<style scoped>
.block {
  margin: 21px 0 0 51px;
  width: 934px;
}

h1{
  font-size: 16px;
}

span{
  font-size: 16px;
  margin: 0 53px 0 0;
}

.kong{
  height: 3px;
}

.block1{
  margin: 50px 0 0 300px;
}
</style>
