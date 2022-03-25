<template>
  <div>
    <div v-show="showheaders==1" style="border-bottom: 1px solid;">
      <Header title="企业异常监控" search-bar="0" toback="0"></Header>
      <div style="height: 20px;"></div>
    </div>
    <div class="main">
      <br>
      <el-container width="1000px" style="margin: 0">
        <el-header>

          <div class="flex-row left">
            <div class="header_word flex-col">
              <span>{{ userData.username }}</span>
            </div>
          </div>


        </el-header>
        <el-container>
          <el-aside>
            <div @click="module=3">账户管理</div>
            <div @click="module=2">查询历史</div>
            <div @click="module=0">监控记录</div>
            <div @click="module=1">提交记录</div>
            <div>我的下载</div>
          </el-aside>
          <el-main>
            <div class="mList">
              <div v-show="module==0" class="addA">
                <span>监控记录</span>
<!--                <span>触发记录</span>-->
                <el-button type="success" class="success" @click="add = true">添加监控</el-button>

                <el-dialog v-model="add" title="添加监控">
                  <div>
                    <el-input
                        v-model="input_bussiness"
                        placeholder="请输入您需要监控的公司"
                        prefix-icon="el-icon-search"
                        @keyup.enter="addBusiness"
                    />
                    <div class="undefined"></div>

                    <el-checkbox-group v-model="checkList">

                      <el-checkbox
                          v-for="data in childMenu"
                          :label="data.menu"
                          :key="data.menu"
                          checked="checked"
                      >
                        {{ data.menu }}
                      </el-checkbox>

                    </el-checkbox-group>

                    <span class="undefined">未能找到相关企业，<em @click="toEnterpriseRegistraion">点击提交查询需求</em></span>
                  </div>
                  <template #footer>
                    <div class="dialog-footer">
                      <el-button @click="addMonitor" type="primary" style="width: 100px">添加监控</el-button>
                      <el-button @click="add = false" style="width: 100px;">取消</el-button>
                    </div>
                  </template>
                </el-dialog>

              </div>
              <span v-show="module==1">提交历史</span>
              <span v-show="module==2">查询历史</span>
              <span v-show="module==3">账户管理</span>
              <!--              <span v-show="module==0">正在监控</span>-->
            </div>
            <enterprise-list v-if="module==0" lasttrigger="1"></enterprise-list>
            <submission-history v-if="module==1"></submission-history>
            <history v-if="module==2" v-show="reWrite"></history>
            <account-management v-show="module==3"></account-management>
          </el-main>
        </el-container>
      </el-container>
    </div>

  </div>
</template>

<script>
import Header from "@/components/header";
import EnterpriseList from "@/components/enterpriseList";
import SubmissionHistory from "@/components/submissionHistory";
import History from "@/components/history";
import AccountManagement from "@/components/accountManagement";
import {addMonitorController} from "@/js/api";

export default {
  name: "backStage",
  props: ['showheader'],
  components: {
    AccountManagement,
    History,
    SubmissionHistory,
    EnterpriseList,
    Header
  },
  created() {
    this.userData = JSON.parse(localStorage.getItem("user-data"));
    if (this.showheader != null) {
      this.showheaders = this.showheader;
    }
  },
  data() {
    return {
      reWrite: false,
      userData: [],
      showheaders: 1,
      checkList: ["广州市奇越互联有限公司", "广州市奇越互联有限公司"],
      module: '0',
      add: false,
      rules: false,
      input_bussiness: '',
      childMenu: [],
      list: [{
        idc_id: '',
        addr: ''
      }],
      value: ''
    }
  },
  methods: {
    addMonitor() {
      // this.userData = JSON.parse(localStorage.getItem("user-data"));
      addMonitorController({
        userid: this.userData.userid,
        username: this.userData.username,
        companyname: this.childMenu[0].menu,
        state: 1
      }).then((response) => {
        if (response.data != null) {
          console.log(response);
          this.add = false
          this.$message({
            showClose: true,
            message: '添加成功',
            type: 'success'
          });

          this.module = 1
          this.$nextTick(function () {
            this.module = 0
          })
        } else {
          this.$message({
            showClose: true,
            message: '系统错误',
            type: 'error'
          });
        }
      }).catch((error) => {
        console.log(error);
        this.$message({
          showClose: true,
          message: '网络错误',
          type: 'error'
        });
      })
    },
    toEnterpriseRegistraion() {
      this.$router.push({  //核心语句
        path: '/enterpriseRegistration',   //跳转的路径
        query: {           //路由传参时push和query搭配使用 ，作用时传递参数
          // id:this.id ,
        }
      })
    },
    addBusiness() {
      let Edata = this.input_bussiness;
      this.childMenu.push({"menu": Edata});
      console.log(this.childMenu)
      this.input_bussiness = '';
    },
  }
}
</script>

<style lang="less" scoped>
/deep/ .el-checkbox {
  margin-right: 10px;
}

/deep/ .el-form-item__label {
  padding-left: 50px;
}

/deep/ .el-form-item {
  margin-bottom: 20px;
}

.setting1 {
  /deep/ .el-input__inner {
    width: 260px;
  }
}

.left {
  width: 100%;
  background-image: url("../img/bc_img_no_logo.png");
}

.setting {
  span {
    width: 53px;
    height: 21px;
    font-size: 16px;
    color: #171717;
    line-height: 21px;
    margin-left: 80px;
  }

  .rules {
    margin: 16px;
    width: 260px;
    height: 43px;
  }

  /deep/ .el-col3 {
    margin-top: 2px;
  }
}

/deep/ .el-tabs__item {
  //background-color: #42b983;
}

* {
  margin: 0;
  padding: 0;
}

.undefined {
  height: 10px;
}

em {
  color: rgb(14, 104, 204);
}

.addA {
  display: flex;
  width: 98%;
}

.success {
  width: 95px;
  height: 40px;
  margin-left: auto;
}

.main {
  background-color: rgb(234, 234, 234);
  //margin-top: 20px;
  //padding-top: 30px;
  width: 100%;
  height: 1000px;
}

.el-header {
  width: 1620px;
  height: 159px !important;
  background-color: white;
  margin: auto;
  display: flex;
  //border:1px solid;
}

.el-aside {
  background-color: white;
  width: 409px !important;
  height: 656px;
  //background-color: #8E5CB6;
  margin-right: 23px;
  border: 1px solid white;

  div {
    font-size: 26px;
    background-color: white;
    width: 100%;
    height: 92px;
    line-height: 92px;
    border-bottom: 3px rgb(234, 234, 234) solid;
    text-align: center;
    //margin-bottom: 5px;
  }

  .block {
    height: auto;
    width: 100%;
  }
}

.el-main {
  width: 1188px;
  height: 656px;
  //border:1px solid;

  background-color: white;

  .mList {
    height: 70px;
    font-size: 30px;
    padding: 23px 0 0 27px;
    border-bottom: 3px rgb(234, 234, 234) solid;
  }

  .list {
    font-size: 26px;
    margin: auto;
    margin-bottom: 30px;
    padding-top: 30px;
    display: flex;
    flex-direction: row;
    align-content: space-between;
    width: 100%;
  }
}

.el-container {
  margin: auto;
  margin-top: 10px;

}

.header_img {
  width: 85px;
  height: 89px;
  margin: auto 26px auto 57px;
}

.header_word {
  margin-top: auto;
  margin-bottom: auto;
  text-indent: 50px;
  font-size: 50px;
  color: white;
}

.right {
  margin-left: 612px;
  font-size: 24px;

  .number {
    font-size: 35px;
    text-align: center;
  }

  .module {
    width: 96px;
    height: 80px;
    margin: auto;
    margin-right: 26px;
    display: flex;
    flex-direction: column;
  }

  .el-icon-setting {
    margin: auto;
    padding-left: 20px;
    font-size: 35px;
  }

  .el-table {
    //margin-top: 20px;

  }

}
</style>
