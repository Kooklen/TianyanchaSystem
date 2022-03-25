<template>
  <div class="app">
    <div>
      <Header @module="showmodule" showchange="1" @sendMessage="changetabs"></Header>
    </div>
    <div style="margin-top: 10px">
      <el-tabs type="card" @tab-click="handleClick" v-model="activeName">
        <el-tab-pane label="征信报告" name="first" :key="'first'">
          <div class="main">
            <div class="main2 flex-col">
              <div class="layer1 flex-col">
                <div class="bd5 flex-row">
                  <div>
                    <div class="main3 flex-col" v-show="legalp==1">
                      <!--                      <span class="txt1">法人名</span>-->
                    </div>
                    <div class="main5 flex-col" v-show="legalp==0">
                      <!--                      <span class="txt6">法人名</span>-->
                    </div>
                  </div>

                </div>


                <div class="bd2">
                  <el-autocomplete
                      v-model="state"
                      :fetch-suggestions="querySearchAsync"
                      :placeholder=inputword
                      @select="handleSelect"
                  >
                    <div v-if="visible">未能查到对应企业，请提交查询需求</div>

                  </el-autocomplete>

                  <img src="@/img/upload.png" style="width: 40px;height: 40px;" class="upload" @click="toUpload">

                  <input type="submit" @click="submit" value="点击查询" class="submit">
                </div>

                <span class="txt3" @click="toEnterpriseRegistration">未能查到对应企业?</span>
              </div>
            </div>


            <el-dialog
                v-model="authorizationDialog"
                title="提示"
                width="40%"
                :before-close="handleClose"
            >
              <span style="font-size: 20px">您是否已经获得正在查询企业的授权</span>
              <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleClose">没有</el-button>
        <el-button type="primary" @click="authorizationConfirm"
        >
          确认获得
        </el-button>
      </span>
              </template>
            </el-dialog>
          </div>

          <div class="area">
            <!--            <div>目前仅支持</div>-->
            <div>广州市、珠海市、汕头市、佛山市、韶关市、湛江市、肇庆市、江门市、茂名市、惠州市</div>
            <div>梅州市、汕尾市、河源市、阳江市、清远市、东莞市、中山市、潮州市、揭阳市、云浮市</div>
          </div>

        </el-tab-pane>

        <el-tab-pane label="贷后预警" v-if="userData.monitorf==true" name="second" :key="'second'"
        >
          <div class="inner">
          <sys-monitor ismonitor="1" iscancelmonitor="0" totrigger="1" lasttrigger="1"></sys-monitor>
          </div>
        </el-tab-pane>

        <el-tab-pane label="管理中心" name="third" :key="'third'">
          <back-stage showheader='0'></back-stage>
        </el-tab-pane>
        <el-tab-pane label="应用说明" name="four" :key="'four'">
          <manual></manual>
        </el-tab-pane>
        <el-dialog
            title="请上传授权文件"
            v-model="author"
            width="40%"
            :before-close="handleClose">
          <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :limit="1"
              drag
              :on-success="uploadSuccess"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传PDF/WORD文件，且不超过500kb</div>
          </el-upload>

          <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="uploadFile" v-show="!nextButton" disabled>
          下一步
        </el-button
        >
        <el-button type="primary" @click="uploadFile" v-show="nextButton">
          下一步
        </el-button
        >
      </span>
          </template>

        </el-dialog>


        <el-dialog
            v-model="downloadDialog"
            title="报告已生成"
            width="30%"
            :before-close="handleClose"
        >
          <span style="font-size: 20px">请选择处理方式</span>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="toReport"
        >预览报告</el-button
        >
        <el-button type="primary" @click="downloadDialog = false"
        >下载报告</el-button
        >
         <el-button type="primary" @click="downloadDialog = false"
         >下载数据</el-button
         >
      </span>
          </template>
        </el-dialog>
      </el-tabs>
    </div>


    <!--    <div class="flex-col"></div>-->
  </div>

</template>

<script>
import Header from "@/components/header";
import {ElMessageBox} from "element-plus";
import { ElNotification } from 'element-plus'
import {ref} from "vue";
import sysMonitor from "@/views/sysMonitor";
import backStage from "@/views/backStage";
import pdf from "vue3-pdf";
import {userMainStore} from "@/store"
import Manual from "@/components/manual";
import {addSearchRecord} from "@/js/api";

export default {
  name: "search",
  components: {Manual, backStage, sysMonitor, Header},
  data() {
    return {
      activeName: "first",
      url:"/pdf/intro.pdf",
      numPages: null, // pdf 总页数
      inputword: "请输入企业的名称",
      userData: [],
      nextButton: false,
      next: false,
      author: false,
      showheader: '0',
      activeIndex: '',
      activeIndex2: '',
      authorizationDialog: false,
      downloadDialog: false,
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
  created() {
    const store = userMainStore()
    console.log(store.count);
    this.handleClick(1,1)
    this.getNumPages()
    this.userData = JSON.parse(localStorage.getItem("user-data"))
    ElNotification({
      title: '警报',
      message: '您监控的企业"木木集团有限公司" 有异常情况，请尽快查看!',
      type: 'error',
      // duration: 0,
      offset: 170,
    });


  },
  mounted() {
    // this.getNumPages()
  }
  ,
  methods: {
    changetabs(data){
      this.activeName=data
      // console.log(data);
    },
    getNumPages() {
      let loadingTask = pdf.createLoadingTask(this.url)
      loadingTask.promise.then(pdf => {
        this.numPages = pdf.numPages
      }).catch(err => {
        console.error('pdf 加载失败', err);
      })
    }
    // ,
    // showmodule(data) {
    //   module = data.module
    // }
    ,
    uploadSuccess(files, fileList) {
      this.nextButton = true
    },
    authorizationConfirm() {
      this.authorizationDialog = false;
      this.author = true;
      // this.downloadDialog=true;
      addSearchRecord(
          {
            userid: this.userData.userid,
            username: this.userData.username,
            companyname:this.state,
          }
      ).then((response) => {
        if (response.data != null) {
          console.log(response);
        } else {
          console.log(response);
        }
    })
    },
    handleClick(tab, event) {
      // console.log(tab, event);
    },
    handleClose() {
      this.$confirm('确认退出？')
          .then(_ => {
            this.authorizationDialog = false;
            this.downloadDialog = false;
            this.author = false
          })
          .catch(_ => {
          });
    }
    ,
    startSearch() {
      this.search = 1;
      const state = reactive({
        loading: true,
      })
      return toRefs(state)
    },

    allSearch() {
      this.all = 1;
      this.enterprise = this.legalp = 0;
      this.inputword = "请输入企业的名称";
      // // console.log(1)
    },
    enterpriseSearch() {
      // // console.log(0)
      this.inputword = "请输入企业的户表号";
      this.enterprise = 1;
      this.all = this.legalp = 0;
    },
    legalpSearch() {
      this.legalp = 1;
      this.all = this.enterprise = 0
    },
    toReport() {
      // this.$router.push({  //核心语句
      //   path: '/report',   //跳转的路径
      // })
      let routeData = this.$router.resolve({path: '/report2'});
      window.open(routeData.href, '_blank');
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
      this.authorizationDialog = true

    },
    uploadFile() {
      this.author = false;
      this.downloadDialog = true;
    },
    loadAll() {
      return [
        {"value": "广州奇越互联数据科技有限公司", link: 'https://http://192.168.1.105:8081/report2'},
        {"value": "广州奇越网大数据有限公司", link: 'https://http://192.168.1.105:8081/report2'},
      ];
    },
    querySearchAsync(queryString, cb) {
      this.visible = false;
      var company = this.company;
      var results = queryString ? company.filter(this.createStateFilter(queryString)) : company;
      // // console.log(results)
      // if (results.length == 0) {
      // // //   console.log('NULL')
      //   this.visible = true;
      //   // results = [{"value": "未能找到相关企业，请提交查询需求"}]
      // }
      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        if (results.length == 0) {
          // // console.log('NULL')
          this.visible = true;
          results = [{"value": ""}]
        }
        cb(results);
      }, 1000);
    },

    createStateFilter(queryString) {
      return (state) => {
        // // console.log(state)
        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },

    handleSelect(item) {
      this.selectCompany = item.link;
    }
  },
  mounted() {
    this.company = this.loadAll();

  }
}
</script>

<style>
.el-notification__title{
  /*background-color: #42b983;*/
  font-size: 22px;
}

.el-notification__content{
  font-size: 15px;
}
</style>

<style scoped lang="less">
/deep/ .el-tabs__nav-wrap.is-scrollable {
  padding: 0 0;
  box-sizing: border-box;
}

/deep/ .el-tabs__header {
  width: 1px !important;
  height: 1px !important;
  background-color: white;
  color:white;
}

.inner /deep/ .el-tabs__header {
  width: 100% !important;
  height: 100% !important;
  background-color: white;
  color:black;
}

* {
  margin: 0;
  padding: 0;
}

.area {
  color: rgb(192, 196, 204);
  text-align: center;
  margin: 0 auto;
  margin-top: 50px;
  font-size: 15px;
}

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
}

/deep/ .el-upload-dragger {
  width: 730px;
  height: 200px;
}



/deep/ .el-tabs__item {
  //background-color: rgb(192, 196, 204);
}

.main {
  /deep/ .el-input__inner {
    width: 972px;
    height: 84px;
    border: none;
    font-size: 24px;
  }
}

/deep/ .el-button {
  width: 120px;
}

/deep/ .el-scrollbar {
  width: 500px;
  height: 1000px;
  font-size: 24px;
}

/deep/ .el-scrollbar__view el-autocomplete-suggestion__list {
  font-size: 20px;
}

/deep/ .el-tabs__header {
  margin: 0;
}

.main2 {
  z-index: 2;
  background-image: url("../img/bc_img.png");
  background-repeat: no-repeat;
  width: 100%;
  height: 500px;
  justify-content: flex-end;
  /*margin-top: 20px;*/
  /*padding-bottom: 33px;*/
  align-items: center;
  background-size: 100%;
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
  color: rgba(16, 105, 205, 1);
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
  border: none;
  background-color: rgba(255, 255, 255, 1);
  margin-top: 15px;
  width: 1152px;
  display: flex;
  flex-direction: row;
  /*align-items: flex-end;*/
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
  color: rgba(255, 255, 255, 1);
  font-size: 18px;
  white-space: nowrap;
  line-height: 25px;
  text-align: left;
  align-self: flex-end;
  margin-top: 14px;
}

.txt3:hover {
  border-bottom: 1px solid;
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
