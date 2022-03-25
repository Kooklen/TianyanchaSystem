<template>
  <div class="app">
    <div>
      <Header></Header>
    </div>
    <el-container>
    </el-container>
    <div class="main">
      <div class="main2 flex-col">
        <div class="layer1 flex-col">
          <div class="bd5 flex-row">

            <div @click="allSearch">
              <div class="main3 flex-col" v-show="all==1">
                <span class="txt1">全部</span>
              </div>
              <div class="main5 flex-col" v-show="all==0">
                <span class="txt6">全部</span>
              </div>
            </div>

            <div @click="enterpriseSearch">
              <div class="main3 flex-col" v-show="enterprise==1">
                <span class="txt1">企业名</span>
              </div>
              <div class="main5 flex-col" v-show="enterprise==0">
                <span class="txt6">企业名</span>
              </div>
            </div>

            <div @click="legalpSearch">
              <div class="main3 flex-col" v-show="legalp==1">
                <span class="txt1">法人名</span>
              </div>
              <div class="main5 flex-col" v-show="legalp==0">
                <span class="txt6">法人名</span>
              </div>
            </div>

          </div>

          <div class="bd2">
            <el-autocomplete
                v-model="state"
                :fetch-suggestions="querySearchAsync"
                placeholder="请输入企业的名称、法人、户表号"
                @select="handleSelect"
            >
              <div v-if="visible">未能找到相关企业，请提交查询需求</div>
            </el-autocomplete>
            <img src="@/img/upload.png" style="width: 40px;height: 40px;" class="upload" @click="toUpload">
            <input type="submit" @click="submit" value="查找" class="submit">
          </div>

          <span class="txt3" @click="toEnterpriseRegistration">无法找到相关企业?</span>
        </div>
      </div>

      <div class="history_result">
        <span class="txt4">企业报告查询记录</span>
        <div class="main4 flex-col"></div>
        <ul>
          <li @click="toReport">广州卖书世界有限公司</li>
          <li>佛山飞龙世家工业有限公司</li>
          <li>东莞长寿奶制品工业有限公司</li>
          <li>广州xx食品加工厂</li>
          <li class="more">查看更多历史记录</li>
        </ul>
      </div>
    </div>
    <div class="flex-col"></div>
  </div>

</template>

<script>
import Header from "@/components/header";

export default {
  name: "proSearch",
  components: {Header},
  data() {
    return {
      visible: false,
      company: [],
      state: '',
      timeout: null,
      //0未选中 1选中
      all: '1',
      enterprise: '0',
      legalp: '0',
      search: '0',
      tableData: [
        {
          date: '2016-05-02',
          name: 'John Smith',
          address: 'No.1518,  Jinshajiang Road, Putuo District',
        },
        {
          date: '2016-05-04',
          name: 'John Smith',
          address: 'No.1518,  Jinshajiang Road, Putuo District',
        },
        {
          date: '2016-05-01',
          name: 'John Smith',
          address: 'No.1518,  Jinshajiang Road, Putuo District',
        },
      ],
      loading: true,
      selectCompany: '',
    }
  },
  methods: {
    allSearch() {
      this.all = 1;
      this.enterprise = this.legalp = 0;
      console.log(1)
    },
    enterpriseSearch() {
      console.log(0)
      this.enterprise = 1;
      this.all = this.legalp = 0;
    },
    legalpSearch() {
      this.legalp = 1;
      this.all = this.enterprise = 0
    },
    toReport() {
      this.$router.push({  //核心语句
        path: '/report',   //跳转的路径
      })
    },
    toEnterpriseRegistration() {
      this.$router.push({  //核心语句
        path: '/enterpriseRegistration',   //跳转的路径
      })
    },
    toUpload() {
      this.$router.push({  //核心语句
        path: '/uploadEnterprise',   //跳转的路径
      })
    },
    submit() {
      console.log(this.selectCompany);
      this.$router.push({  //核心语句
        path: '/report',   //跳转的路径
      })
    },
    loadAll() {
      return [
        {"value": "广州奇越互联数据科技有限公司", link: 'https://http://192.168.1.105:8081/report'},
        {"value": "广州奇越网大数据有限公司", link: 'https://http://192.168.1.105:8081/report'},
      ];
    },
    querySearchAsync(queryString, cb) {
      this.visible = false;
      var company = this.company;
      var results = queryString ? company.filter(this.createStateFilter(queryString)) : company;
      console.log(results)
      // if (results.length == 0) {
      //   console.log('NULL')
      //   this.visible = true;
      //   // results = [{"value": "未能找到相关企业，请提交查询需求"}]
      // }
      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        if (results.length == 0) {
          console.log('NULL')
          this.visible = true;
          results = [{"value": ""}]
        }
        cb(results);
      }, 1000);
    },

    createStateFilter(queryString) {
      return (state) => {
        console.log(state)
        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        // return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) >= 1);

      };
    },

    handleSelect(item) {
      console.log(item);
      this.selectCompany = item.link;
    }
  },
  mounted() {
    this.company = this.loadAll();

  }
}
</script>

<style scoped>
.upload {
  position: absolute;
  right: 200px;
  top: 20px;
}

.app {
  margin: 0;
  padding: 0;
  position: absolute;
  left: 0;
  width: 100%;
}

.main {
  width: 100%;
  border: none;
}

.history_result {
  width: 1152px;
  margin: auto;
}


/deep/ .el-input__inner {
  width: 972px;
  height: 84px;
  border: none;
  font-size: 24px;
}


.main2 {
  z-index: 2;
  background-image: url("../img/bgc_standard.png");
  width: 100%;
  height: 490px;
  justify-content: flex-end;
  margin-top: 20px;
  align-items: center;
  background-size: 100%;
  border: none;
  /*border-bottom: rgb(141, 141, 141) 1px solid;*/
}

.layer1 {
  z-index: auto;
  margin-left: 0;
  /*width: 1152px;*/
  height: 358px;
}


.bd5 {
  z-index: auto;
  width: 314px;
  height: 57px;
  margin-top: 98px;
  justify-content: space-between;
}


.txt1 {
  z-index: 27;
  width: 95px;
  height: 33px;
  display: block;
  overflow-wrap: break-word;
  color: rgb(254, 184, 57);
  font-size: 24px;
  white-space: nowrap;
  line-height: 33px;
  text-align: center;
}

.main3 {
  z-index: 26;
  height: 57px;
  background: url(https://lanhu.oss-cn-beijing.aliyuncs.com/SketchPng46c637a8f6eb995d2050326dadce37838c106f57f35814a48ca4755ab8091155) 100% no-repeat;
  width: 95px;
  justify-content: flex-start;
  align-items: flex-start;
  padding: 7px 0 0 22px;
}

.main5 {
  z-index: 26;
  height: 57px;
  width: 95px;
  justify-content: flex-start;
  align-items: flex-start;
  padding: 7px 0 0 22px;
}

.txt6 {
  z-index: 27;
  width: 95px;
  height: 33px;
  display: block;
  overflow-wrap: break-word;
  color: white;
  font-size: 24px;
  white-space: nowrap;
  line-height: 33px;
  text-align: center;
}

.bd2 {
  position: relative;
  z-index: 6;
  height: 84px;
  border: 1px solid rgb(141, 141, 141);
  background-color: rgba(255, 255, 255, 1);
  margin-top: 15px;
  width: 1152px;
  display: flex;
  flex-direction: row;
}


.submit {
  background-color: rgb(230, 113, 19);
  width: 180px;
  height: 84px;
  border: none;
  font-size: 32px;
  color: white;
}


.txt3 {
  z-index: 30;
  width: 154px;
  height: 25px;
  display: block;
  overflow-wrap: break-word;
  color: rgb(148, 148, 148);
  font-size: 18px;
  white-space: nowrap;
  line-height: 25px;
  text-align: left;
  align-self: flex-end;
  margin-top: 14px;
}

.txt4 {
  z-index: 19;
  width: 192px;
  height: 33px;
  display: block;
  overflow-wrap: break-word;
  color: rgba(0, 0, 0, 1);
  font-size: 24px;
  white-space: nowrap;
  line-height: 33px;
  text-align: left;
  align-self: flex-start;
  margin: 35px 0 0 0;
}

.main4 {
  z-index: 20;
  width: 1139px;
  height: 1px;
  background: url(https://lanhu.oss-cn-beijing.aliyuncs.com/SketchPnga78345c234453b0489c0c9599308deaae3fec1af76732065ddbe85fbc39f7763) -1px -1px no-repeat;
  align-self: center;
  margin-top: 3px;
}


.flex-col {
  display: flex;
  flex-direction: column;
}

.flex-row {
  display: flex;
  flex-direction: row;
}

ul {
  list-style: none;
}

li {
  margin: 34px 32px;
  font-size: 21px;
}

.more {
  text-align: right;
}
</style>
