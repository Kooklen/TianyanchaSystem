<template>
  <div>
    <router-view/>
    <el-row>
      <el-col :span="7">
        <img
            class="logo"
            referrerpolicy="no-referrer"
            src="../img/logo.png"
        />
      </el-col>

      <el-col :span="9">
      </el-col>

      <el-col :span="8">
      </el-col>
    </el-row>

    <div class="product">
        <span>您的账号还未开通功能</span>

      <div class="select">

        <div class="single" @click="toStandard">
          <img src="@/img/pro.png" alt="">
          <span>企业报告查询</span>
          <span class="notopen" v-show="userData.searchfunction==false">未开通</span>
        </div>


        <div class="single1" @click="toMonitor">
          <img src="@/img/secu.png" alt="">
          <span>企业异常监控</span>
          <span class="notopen" v-show="userData.monitorf==false">未开通</span>
        </div>

      </div>
    </div>

  </div>
</template>

<script>
import {getUserInfo} from "@/js/api";
import {formatDate} from "@/util/date"
export default {
  name: "router",
  props: ['title', 'searchBar'],
  created() {
    this.getData();
    this.$message({
      showClose: true,
      message: '登陆成功',
      type: 'success'
    });
  },
  data(){
    return{
      userData : []
    }
  },
  methods: {
    getData() {
      let token = localStorage.getItem("access-admin");
      getUserInfo()
          .then((response) => {
            console.log(response);
            if (response.code == "200") {
              localStorage.setItem('user-data', JSON.stringify(response.data))
              this.userData = JSON.parse(localStorage.getItem("user-data"));
              this.$message({
                showClose: true,
                message: '欢迎您 ' + response.data.username,
                type: 'success'
              })

              // this.$message({
              //   showClose: true,
              //   message: '您的账号到期时间为'+formatDate(this.userData.starttime, 'yyyy-MM-dd hh:mm'),
              //   type: 'success'
              // })

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
          });
    },
    // toCommon() {
    //   this.$router.push({  //核心语句
    //     path: '/search',   //跳转的路径
    //     query: {           //路由传参时push和query搭配使用 ，作用时传递参数
    //       // id:this.id ,
    //     }
    //   })
    // },
    toStandard() {
      if (this.userData.searchfunction==true) {
        this.$router.push({  //核心语句
          path: '/search',   //跳转的路径
          query: {           //路由传参时push和query搭配使用 ，作用时传递参数
            // id:this.id ,
          }
        })
      } else {
        this.$message({
          showClose: true,
          message: '您的账号未开通搜索功能，请联系管理员!',
          type: 'error'
        });
      }
    },
    toMonitor() {
      if (this.userData.monitorf==true) {
        this.$router.push({  //核心语句
          path: '/sysMonitor',   //跳转的路径
          query: {           //路由传参时push和query搭配使用 ，作用时传递参数
                             // id:this.id ,
          }
        })
      } else {
        this.$message({
          showClose: true,
          message: '您的账号未开通企业异常监控功能，请联系管理员!',
          type: 'error'
        });
      }
    },

  }
}
</script>

<style lang="less" scoped>
.notopen{
  font-size: 18px !important;
  color: red !important;
}

.logo {
  margin: 19px 0 0 52px;
  width: 208px;
  height: 82px;
}
.product{
  width: 920px;
  height: 307px;
  margin:auto;
  margin-top: 200px;

  span{
    display: block;
    width: 920px;
    height: 64px;
    font-size: 48px;
    color: #171717;
    line-height: 64px;
    text-align: center;
  }
}



  .single{
    width: 208px;
    height: 290px;
    display: flex;
    flex-direction: column;
    margin-right: 148px;

    span{
      width: 208px;
      height: 47px;
      font-size: 30px;
      color: #171717;
      line-height: 47px;
      text-align: center;
      margin-top: 15px;
    }
  }

.single1{
  width: 208px;
  height: 290px;
  display: flex;
  flex-direction: column;
  margin-right: 148px;

  span{
    width: 208px;
    height: 47px;
    font-size: 30px;
    color: #171717;
    line-height: 47px;
    text-align: center;
    margin-top: 15px;
  }
}

  img{
    width: 208px;
    height: 208px;
}


.select{
  margin-top: 35px;
  margin-left: 181px;
  display: flex;
}

</style>
