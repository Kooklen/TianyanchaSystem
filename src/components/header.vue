<template>
  <div>
    <el-dialog
        v-model="dialogVisible"
        title="最近预警触发记录"
        width="50%"
        :before-close="handleClose"
    >
      <div>
        <!--       <el-tab-pane label="最近预警触发记录" v-if="totrigger==1">-->
        <el-table
            class="el-table"
            :data="triggerdata"
            :default-sort="{prop: 'triggertime', order: 'descending'}"
        >
          <el-table-column
              prop="companyname"
              label="触发企业名称"
          >
          </el-table-column>
          <el-table-column
              prop="pushway"
              label="触发原因"
          >
          </el-table-column>
          <el-table-column
              prop="triggertime"
              label="预警触发记录"
          >
          </el-table-column>

        </el-table>
        <!--       </el-tab-pane>-->
      </div>

      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogVisible = false"
        >我已知晓</el-button
        >
      </span>
      </template>
    </el-dialog>

    <el-row>
      <el-col :span="7">
        <img
            class="logo"
            referrerpolicy="no-referrer"
            src="../img/logo.png"
            @click="toIndex"
        />

      </el-col>

      <el-col :span="9">
        <div class="header_word" v-if="showchange">
          <span @click="report" :class="{click_color:select==1}">征信报告</span>
          <el-dropdown>
            <span @click="alert" :class="{click_color:select==2}">贷后预警</span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item :icon="CirclePlusFilled" @click="toMonitor">
                  本月预警记录
                </el-dropdown-item>

                <el-dropdown-item :icon="CirclePlusFilled" @click="toPast">
                  历史预警记录
                </el-dropdown-item>

                <el-dropdown-item :icon="Plus" @click="toTrigger">
                  监控企业名称
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>

          <el-dropdown>
            <span @click="center" :class="{click_color:select==3}">管理中心</span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item :icon="CirclePlusFilled" @click="toManager(1)">
                  账户管理
                </el-dropdown-item>

                <el-dropdown-item :icon="CirclePlusFilled" @click="toManager(2)">
                  查询历史
                </el-dropdown-item>

                <el-dropdown-item :icon="Plus" @click="toManager(3)">
                  监控记录
                </el-dropdown-item>

                <el-dropdown-item :icon="Plus" @click="toManager(4)">
                  提交记录
                </el-dropdown-item>

                <el-dropdown-item :icon="Plus" @click="toManager(5)">
                  我的下载
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>

          <span2 @click="infor" :class="{click_color:select==4}">应用说明</span2>
        </div>
      </el-col>

      <el-col :span="8">
        <div class="account">
          <i class="el-icon-warning"></i>
          <span class="word1" @click="send">贷后异常预警</span>
          <div class="bd1"></div>
          <div class="block">
            <el-avatar :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar>
          </div>

          <el-dropdown>
            <div>
        <span class=" info1">
         <span class="info2">{{ username }}</span>
          <div class="blockdd"></div>
          <!--          <el-icon class="el-icon&#45;&#45;right"><arrow-down/></el-icon>-->
        </span>
            </div>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item icon="el-icon-s-data" @click="toBackstage">
                  管理后台
                </el-dropdown-item>
                <el-dropdown-item icon="el-icon-warning
" @click="signout">
                  退出登录
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>


        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import {getUserInfo, logOut, triggerrecord} from "@/js/api";
import {
  ArrowDown,
  CircleCheck,
  Check,
  CirclePlus,
  CirclePlusFilled,
  Plus,
} from '@element-plus/icons-vue'
import {userMainStore} from "@/store";
const store = userMainStore()
export default {
  name: "Header",
  props: ['title', 'searchBar', 'showchange','number'],
  data: function () {
    return {
      triggerdata: [],
      dialogVisible: false,
      bar_title: this.title,
      showSearch: this.searchBar,
      activeIndex: '1',
      activeIndex2: '1',
      username: '',
      select: '1',
      showchange: ''
    }
  },
  created() {
    this.username = JSON.parse(localStorage.getItem("user-data")).username
    this.gettriggerdata()
  },
  emits: ['sendMessage','changealert','changetabs'],
  methods: {
    toManager(data){
      console.log(data);
      if(data==1){
        store.$patch({
          index : store.manager=1
        })
      }else if(data==2){
        store.$patch({
          index : store.manager=2
        })
      }else if(data==3){
        store.$patch({
          index : store.manager=3
        })
      }else if(data==4){
        store.$patch({
          index : store.manager=4
        })
      }else {
        store.$patch({
          index : store.manager=5
        })
      }
      this.$emit('sendMessage', "third")

    },
    toMonitor(){
      store.$patch({
        index : store.index=1
      })
      // console.log(store.index);
      this.$emit('sendMessage', "second")
      // this.$emit('changetabs', "first")

      this.select=2
    },
    toPast(){
      store.$patch({
        index : store.index=3
      })
      // console.log(store.index);
      this.$emit('sendMessage', "second")
      // this.$emit('changetabs', "first")

      this.select=2
    },
    toTrigger(){
      store.$patch({
        index : store.index=2
      })
      // console.log(store.index);
      this.$emit('sendMessage', "second")
      // this.$emit('changetabs', "second")

      this.select=2

    },
    gettriggerdata() {
      this.userdata = JSON.parse(localStorage.getItem("user-data"));
      triggerrecord({
        userid: 88888888
      }).then((response) => {
        // console.log(response)
        if (response != null) {
          for (let i = 0; i < response.length; i++) {
            // console.log(response[i].triggertime)
            var oDate = new Date(response[i].triggertime)
            var now = new Date(); //当前日期
            var nowMonth = now.getMonth(); //当前月
            var nowYear = now.getFullYear(); //当前年
            var monthStartDate = new Date(nowYear, nowMonth, 1);
            var nDate = new Date(monthStartDate)
            if (oDate < nDate) {
              // console.log(true)
            } else {
              this.triggerdata.push(response[i])
              // console.log(false);
            }
            // console.log(response);
            // this.triggerdata=response
          }
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
    report() {
      this.select = 1;
      this.$emit('sendMessage', "first")
    },
    alert() {
      this.select = 2;
      this.$emit('sendMessage', "second")
    },
    center() {
      this.select = 3;
      this.$emit('sendMessage', "third")
    },
    infor() {
      this.select = 4;
      this.$emit('sendMessage', "four")
    },

    send() {
      this.dialogVisible = true
      // console.log(1);
      // this.$router.push({  //核心语句
      //   path: '/backStage',   //跳转的路径
      //   query: {           //路由传参时push和query搭配使用 ，作用时传递参数
      //     // id:this.id ,
      //   }
      // })

    },
    signout() {
      logOut()
          .then((response) => {
            // console.log(response);
            if (response.code == 200) {
              localStorage.clear()
              this.$message({
                showClose: true,
                message: "账号已退出",
                type: 'success'
              })
              this.$router.push({  //核心语句
                path: '/',   //跳转的路径
                query: {           //路由传参时push和query搭配使用 ，作用时传递参数
                }
              })
            } else {
              this.$message({
                showClose: true,
                message: '网络异常',
                type: 'error'
              });
            }
          })
          .catch((error) => {
            this.$message({
              showClose: true,
              message: '网络错误',
              type: 'error'
            });
          })
    },

    handleSelect(key, keyPath) {
      // console.log(key, keyPath);
    },
    toIndex() {
      this.$router.push({  //核心语句
        path: '/search',   //跳转的路径
        query: {           //路由传参时push和query搭配使用 ，作用时传递参数
          // id:this.id ,
        }
      })
    },
    toBackstage() {
      this.$router.push({  //核心语句
        path: '/backStage',   //跳转的路径
        query: {           //路由传参时push和query搭配使用 ，作用时传递参数
          // id:this.id ,
        }
      })
    },
    toSearch() {
      this.$router.push({  //核心语句
        path: '/search',   //跳转的路径
        query: {           //路由传参时push和query搭配使用 ，作用时传递参数
          // id:this.id ,
        }
      })
    },
    toProSearch() {
      this.$router.push({  //核心语句
        path: '/prosearch',   //跳转的路径
        query: {           //路由传参时push和query搭配使用 ，作用时传递参数
          // id:this.id ,
        }
      })
    },
    toRouter() {
      this.$router.push({  //核心语句
        path: '/router',   //跳转的路径
        query: {           //路由传参时push和query搭配使用 ，作用时传递参数
          // id:this.id ,
        }
      })
    }
  }
}
</script>
<style lang="less" scoped>

/deep/ .el-dropdown-menu{
  width: 150px;
  height: 150px;
}

/deep/ .el-dropdown-menu__item{
   //width: 150px;
   height: 40px;
   //text-align: center;
   padding: 10px 20px;
   font-size: 18px;
 }

.click_color {
  color: #1A5493;
  font-size: 24px;
}

/deep/ .el-dropdown-selfdefine {
  font-size: 24px;
  color: black;
  border: none;
}

/deep/ .el-dropdown {
  margin: auto;
  font-size: 24px;
  color: black;
  border: none;

}

.header_word {
  font-size: 24px !important;
  text-align: center;
  margin-top: 50px;
  border:none;

  span {
    padding: 0 24px ;
    width: 137px;
    height: 60px;
    border-right: 1px #C3C3C3 solid;
  }

  span2 {
    padding: 24px;
    width: 137px;
    height: 60px;
  }
}

/deep/ .el-tabs__header {
  width: 100px !important;
  height: 100px !important;
  background-color: white;
  color: white;
}


.block {
  height: 9px;
  width: 9px;
}

.info1 {
  width: 109px;
  height: 24px;
  margin-top: auto;
  //margin-left: 12px;
  text-align: center;
}

.info2 {
  //padding-top: 30px;
  padding-bottom: 20px;
  padding-left: 30px;
  height: 80px;
  font-size: 14px;
  color: black;
  //margin: 50px;
  //margin-top: 10px;
  margin-bottom: 20px;
  text-align: center;
}

.blockdd{
  height: 10px;
  width: 1px;
}


.logo {
  border: none;
  list-style: none;
  margin: 19px 0 0 52px;
  //width: 208px;
  //height: 82px;
}

.searchBar {
  width: 200px;
  height: 67px;
}

.el-icon-warning {
  margin: auto;
  font-size: 22px;
  text-align: center;
  align-items: center;
  color: red;
}


.account {
  display: flex;
  margin: 40px 0 0 0;
  margin-left: auto;
  width: 350px;
  height: 61px;
  line-height: 61px;
}


.word1 {
  width: 80px;
  height: 33px;
}



.bd1 {
  z-index: 17;
  width: 2px;
  height: 40px;
  background: url(https://lanhu.oss-cn-beijing.aliyuncs.com/SketchPng77b1163e8c700e499c0b21cc537cca8d2602755b4f1b8674d77709af03b5ee27) 0px 0px no-repeat;
  margin: 10px 20px;
}


.search {
  width: 626px;
  height: 67px;
  //border-collapse: collapse;
  margin-top: 35px;
  //background-color: pink;
  display: flex;
  flex-direction: row;
}

.search_kuang {
  outline: none;
  padding: 0;
  width: 460px;
  height: 67px;
  border: 1px solid #1069CD;
  border-right: none;
  text-indent: 10px;
}

.submit {
  width: 160px;
  height: 69px;
  border: 1px solid #0E68CC;
  font-size: 26px;
  font-weight: 500;
  color: #FFFFFF;
  line-height: 37px;
  background-color: #0E68CC;
}

.stantard {
  display: inline-block;
  width: 132px;
  font-size: 15px;
  color: black;
  margin-left: 10px;
}

.pro {
  display: inline-block;
  width: 132px;
  font-size: 20px;
  color: #E67113;
  margin-left: 10px;
}


</style>
