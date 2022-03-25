<template>
  <Header></Header>
  <div class="all" id="pdfDom">
    <div class="header flex-col">
      <span>报告版本：普惠版</span>
      <span>报告⽣成时间：2021.07.12</span>
      <span>报告编号：1008600202107012</span>
      <el-button class="button" type="primary" @click="handleDown">PDF下载</el-button>
    </div>
    <div id="demo1">
      <div class="report">
        普惠版信用报告
      </div>
      <div class="company flex-col">
        <span class="fs25">企业普惠版电力信用报告</span>
        <span class="fs30">江门XXXX智能工业设备有限公司</span>
        <span class="fs20">广州奇越网联数据科技有限公司</span>
        <span>报告日期：2021年7月12日</span>
        <span class="fs12">免责声明：本信用报告仅供使用者参考，不能用作诉讼依据。在任何i情况下，使用本报告引起的一切后果，本公司不承担任何责任</span>
      </div>

      <div class="report">
        01.电力信用总分评估
      </div>
      <el-row :gutter="20">

        <el-col :span="8" style="display:flex;justify-content: center">
          <div id="myChart" :style="{width: '500px', height: '400px'}">
          </div>
        </el-col>

        <el-col :span="12">
          <div class="risk">
            <div class="risk1">
        <span>
          低风险：100-80
        </span>
              <span>
              低于平均风险：80-60
        </span>

              <span>
        平均风险：60-50
        </span>
            </div>
            <div class="risk2">
        <span>
                    高于平均风险：50-30

        </span>
              <span>
                        中高风险：30-10

      </span>
              <span>高风险：10-0</span>
            </div>
          </div>
        </el-col>
      </el-row>
      <div class="report">
        02.企业基本信息
      </div>
      <div>
        <table class="basic" frame=void>
          <tr>
            <td>企业名称</td>
            <td>江门XXXX工业设备有限公司</td>
          </tr>
          <tr>
            <td>统一信用代码</td>
            <td>91440101MXXXX8E7C</td>
          </tr>
          <tr>
            <td>所属省份</td>
            <td>广东省</td>
          </tr>
          <tr>
            <td>所属城市</td>
            <td>江门市</td>
          </tr>
          <tr>
            <td>所属行业</td>
            <td>其他制造业</td>
          </tr>
          <tr>
            <td>合同容量</td>
            <td>500kw</td>
          </tr>
          <tr>
            <td>立户日期</td>
            <td>2020年9月27日</td>
          </tr>
          <tr>
            <td>送电日期</td>
            <td>2020年9月23日</td>
          </tr>
          <tr>
            <td>送电地址</td>
            <td>鹤山市XXXXXXXX</td>
          </tr>
        </table>
      </div>
      <div class="report">
        03.电力核心指标展示
      </div>

      <div>
        <table class="pure-table">
          <thead>
          <tr>
            <th>指标名称</th>
            <th>数值区间</th>
            <th>指标数值</th>
            <th>指标定义</th>
          </tr>
          </thead>

          <tbody>
          <tr class="pure-table-odd">
            <td>企业竞争力</td>
            <td>0-2</td>
            <td>1.963</td>
            <td>企业竞争力根据企业在其所属行业中的排
              名情况，反应企业生产能力在行业中的优
              势和地位
            </td>
          </tr>

          <tr>
            <td>生产稳定性</td>
            <td>0-8</td>
            <td>5.239</td>
            <td>反应企业在特定周期无时间系列干扰下企
              业生产水平的稳定情况
            </td>
          </tr>

          <tr class="pure-table-odd">
            <td>经营成长性</td>
            <td>0-3000</td>
            <td>2117.177</td>
            <td>通过分析企业的用电量，反应企业产量水
              平的增长情况
            </td>
          </tr>

          <tr>
            <td>履约风险</td>
            <td>0-100</td>
            <td>80</td>
            <td>根据企业的缴费及时度、逾期情况、欠费
              情况，分析企业用户的缴费意愿以及资金
              状况
            </td>
          </tr>

          <tr class="pure-table-odd">
            <td>违规风险</td>
            <td>0-1</td>
            <td>0</td>
            <td>通过对企业在经营过程中的违规用电行为、
              窃电行为的统计分析，映射企业在生产经
              营过程中的违规经营风险
            </td>
          </tr>

          <tr>
            <td>生产波动性</td>
            <td>0-1</td>
            <td>0.53</td>
            <td></td>
          </tr>
          </tbody>
        </table>
      </div>

      <div id="myChart2" :style="{width: '500px', height: '500px'}">
      </div>
      <div class="result">
        根据电力数据显示，本企业具备较强的企业竞争力，生产状况比较稳定，企业经营在不断的
        成长，没有什么违规的历史，违规风险较低，但该企业有数次电费逾期，因此履约风险较高
      </div>

      <div style="height: 100px;"></div>

    </div>
  </div>
</template>

<script>
import htmlToPdf from "@/js/htmlToPdf";
import Header from "@/components/header";

export default {
  name: "report",
  components: {Header},
  mounted() {
    this.drawLine()
    this.drawLine2()
  },
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart'))
      // 绘制图表
      let option = {
        series: [
          {
            type: 'gauge',
            startAngle: 180,
            endAngle: 0,
            min: 0,
            max: 1,
            splitNumber: 8,
            axisLine: {
              lineStyle: {
                width: 6,
                color: [
                  [0.25, '#FF6E76'],
                  [0.5, '#FDDD60'],
                  [0.75, '#58D9F9'],
                  [1, '#7CFFB2']
                ]
              }
            },
            pointer: {
              icon: 'path://M12.8,0.7l12,40.1H0.7L12.8,0.7z',
              length: '12%',
              width: 20,
              offsetCenter: [0, '-60%'],
              itemStyle: {
                color: 'auto'
              }
            },
            axisTick: {
              length: 12,
              lineStyle: {
                color: 'auto',
                width: 2
              }
            },
            splitLine: {
              length: 20,
              lineStyle: {
                color: 'auto',
                width: 5
              }
            },
            axisLabel: {
              color: '#464646',
              fontSize: 20,
              distance: -60,
              formatter: function (value) {
                if (value === 0.875) {
                  return 'A';
                } else if (value === 0.625) {
                  return 'B';
                } else if (value === 0.375) {
                  return 'C';
                } else if (value === 0.125) {
                  return 'D';
                }
                return '';
              }
            },
            title: {
              offsetCenter: [0, '-20%'],
              fontSize: 30
            },
            detail: {
              fontSize: 50,
              offsetCenter: [0, '0%'],
              valueAnimation: true,
              formatter: function (value) {
                return Math.round(value * 100) + '分';
              },
              color: 'auto'
            },
            data: [
              {
                value: 0.7,
                name: '低于平均风险'
              }
            ]
          }
        ]
      };
      myChart.setOption(option);
    },
    drawLine2() {
      // 基于准备好的dom，初始化echarts实例
      let myChart2 = this.$echarts.init(document.getElementById('myChart2'))
      // 绘制图表
      let option2 = {
        // title: {
        //   text: 'Basic Radar Chart'
        // },
        legend: {
          data: ['Allocated Budget', 'Actual Spending']
        },
        radar: {
          // shape: 'circle',
          indicator: [
            {name: '企业竞争力', max: 2},
            {name: '生产波动性', max: 1},
            {name: '生产安全性', max: 1},
            {name: '履约能力', max: 100},
            {name: '经营成长性', max: 3000},
            {name: '生产稳定性', max: 8}
          ]
        },
        series: [
          {
            type: 'radar',
            data: [
              {
                //企业竞争力 生产波动性 生产安全性 履约能力
                value: [2, 1, 1, 100, 3000, 8],
                // name: 'Allocated Budget'
              },
              {
                value: [1.6, 0.8, 0.7, 70, 2600, 6],
                // name: 'Actual Spending'
              }
            ]
          }
        ]
      };
      myChart2.setOption(option2);
    },
    handleDown () {

      htmlToPdf.downloadPDF(document.querySelector('#demo1'), 'test')
    }}
}

</script>

<style lang="less" scoped>
* {
  margin: 0;
  padding: 0;
}

.button{
  width: 300px;
  border: none;
  border-radius: 0;
}

.flex-col {
  display: flex;
  flex-direction: column;
}

.flex-row {
  display: flex;
  flex-direction: row;
}

.header {
  font-size: 12px;
  margin-left: 5px;
  text-indent: 0;
}

.report {
  margin: 0 0 0 5px;
  font-size: 20px;
  line-height: 50px;
  color: white;
  width: 99%;
  height: 50px;
  background-color: rgb(68, 114, 196);
}

.company {
  text-align: center;
}

.fs12 {
  font-size: 14px;
  margin-top: 100px;
  margin-bottom: 30px;
}

.fs20 {
  font-size: 25px;
  margin-top: 200px;
}

.fs25 {
  font-size: 30px;
  margin-top: 100px;
}

.fs30 {
  font-size: 40px;
}

.risk {
  margin: 10% 20%;
  height: 200px;
  width: 100%;
  background-color: rgb(189, 215, 238);
  font-size: 18px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

#myChart {
  margin-left: 10%;
}

span {
  display: block;
}

.risk1 {
  width: 50%;

  span {
    padding-top: 25px;
    text-align: center;
  }
}

.risk2 {
  width: 50%;

  span {
    padding-top: 25px;
    text-align: center;
  }
}

table {
  width: 90%;
  margin: auto;
  text-align: center;
  margin-top: 30px;
  margin-bottom: 30px;
}

td {
  border-top: 1px solid;
  font-size: 20px;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
}

td, th {
  padding: 0;
}

.pure-table {
  border-collapse: collapse;
  border-spacing: 0;
  empty-cells: show;
  border: 1px solid #cbcbcb;
}

.pure-table caption {
  color: #000;
  font: italic 85%/1 arial, sans-serif;
  padding: 1em 0;
  text-align: center;
}

.pure-table td, .pure-table th {
  border-left: 1px solid #cbcbcb;
  border-width: 0 0 0 1px;
  font-size: inherit;
  margin: 0;
  overflow: visible;
  padding: .5em 1em;
}

.pure-table thead {
  background-color: #e0e0e0;
  color: #000;
  text-align: left;
  vertical-align: bottom;
}

.pure-table td {
  background-color: transparent;
}

.pure-table-odd td {
  background-color: #f2f2f2;
}

#myChart2 {
  margin: auto;
}

.result {
  width: 90%;
  background-color: rgb(189, 215, 238);
  margin: auto;
  font-size: 17px;
}

.all {
  margin: 0px 180px;
  text-indent: 30px;
}
</style>
