<template>
  <div>
    <div class="main">
      <br>
      <el-container width="1000px" style="margin: 0">
        <el-header class="background">
          <div class="flex-row left">
            <span class="alert">贷后预警</span>
          </div>
        </el-header>
        <el-container>
          <el-main>
            <div class="mList">
              <div v-if="module==0" class="addA">
                <span>警报记录</span>
                <div class="right">
<!--                  <el-button type="success" class="success">监控名单</el-button>-->
                  <el-button type="success" class="success" @click="add = true">添加监控</el-button>
              </div>

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
              <span v-if="module==1">提交历史</span>
              <span v-if="module==2">查询历史</span>
              <span v-if="module==3">监控列表</span>
            </div>

            <enterprise-list v-if="module==0" v-bind:ismonitor="ismonitor" v-bind:iscancelmonitor="iscancelmonitor" v-bind:totrigger="totrigger" v-bind:lasttrigger="lasttrigger"></enterprise-list>
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
import {addMonitorController} from "@/js/api";

export default {
  name: "sysMonitor",
  // props:['header'],
  components: {
    History,
    SubmissionHistory,
    EnterpriseList,
    Header
  },
  props:['ismonitor','iscancelmonitor','totrigger','lasttrigger'],
  data() {
    return {
      ismonitor:this.ismonitor,
      iscancelmonitor:this.iscancelmonitor,
      totrigger:this.totrigger,
      lasttrigger:this.lasttrigger,
      userData: [],
      header:'1',
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
  created() {
    this.userData = JSON.parse(localStorage.getItem("user-data"));
    console.log(this.ismonitor,this.iscancelmonitor,this.totrigger,this.lasttrigger)

  },
  methods: {
    addMonitor() {
      addMonitorController({
        userid: this.userData.userid,
        username: this.userData.username,
        companyname: this.childMenu[0].menu,
        state: 1
      }).then((response) => {
        if (response.data != null) {
          // console.log(response);
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
          this.childMenu=null
        } else {
          this.$message({
            showClose: true,
            message: '系统错误',
            type: 'error'
          });
        }
      }).catch((error) => {
        // console.log(error);
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
      this.input_bussiness = '';
    },
  }
}
</script>

<style lang="less" scoped>

/deep/ .el-table__header{
  color: black;
  background-color: #42b983;
}

.background{
  background-image: url("../img/bc_img_no_logo.png");
}

/deep/ .el-container{
  margin: 0;
}

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

.alert{
  font-size: 50px;
  color: white;
  margin: auto;
  text-indent: 24px;
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
  margin-left: auto;
}

.main {
  background-color: rgb(234, 234, 234);
  width: 100%;
  height: 1000px;
}

.el-header {
  width: 1620px;
  height: 159px !important;
  color: black;
  //background-color: blue;
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
  width: 1620px;
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
  margin: auto;
  font-size: 24px;
  color: white;
}

.right {
  margin-left: auto;

  button{
    margin-left: 20px;
  }

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
