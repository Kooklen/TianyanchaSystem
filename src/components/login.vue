<template>
  <div class="content">
    <div class="flex-row">

      <div class="left">
        <div class="flex-row left0">
          <img src="../img/no_logo.png" style="">
          <div>
            <span class="dyc">X眼查</span>
            <br>
            <span class="inyong">XX数据征信应用</span>
          </div>

        </div>
        <div class="report">信用报告 · 贷后预警</div>
      </div>

      <div class="right">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
          <el-input v-model="ruleForm.account" prop="account" placeholder="用户名" prefix-icon="el-icon-user">
          </el-input>
          <br>
          <el-input
              type="password"
              prop="pass"
              placeholder="密码"
              prefix-icon="el-icon-lock"
              v-model="ruleForm.password">
          </el-input>
        </el-form>

        <div class="choose">
        <div>
          <el-checkbox v-model="oneweek" label="一周内免登录" size="large"></el-checkbox>
          </div>
        <div>
          <el-checkbox v-model="autoLogin" label="记住密码" size="large"></el-checkbox>
        </div>
      </div>
        <button class="login_btn" @click="login">登录</button>
        <div class="copyright">Copyright© 广州奇越网联数据科技有限公司</div>

      </div>
      <div>

      </div>
    </div>

  </div>
</template>

<script>
import {getUserInfo, login} from '@/js/api'

export default {
  name: "login",
  data() {
    return {
      autoLogin:"",
      oneweek:"",
      userid: "",
      password: "",
      selected: 1,
      selected1: 1,
      ruleForm: {
        account: "",
        password: "",
      },
      rules: {
        account: [
          {message: '请输入账号', trigger: 'blur'},
          {min: 8, max: 10, message: '请输入正确格式的账号', trigger: 'blur'}
        ],
        pass: [
          {message: '密码', trigger: 'blur'},
          {min: 3, max: 10, message: '请输入正确格式的密码', trigger: 'blur'}
        ],
      }
    }
  },
  created() {
    window.localStorage.removeItem("access-admin")
    window.localStorage.removeItem("token")
  },
  methods: {
    setCookie(c_name, c_pwd, exdays) {undefined

      var exdate = new Date(); //获取时间

      exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * exdays); //保存的天数

      //字符串拼接cookie

      window.document.cookie =

          "userName" + "=" + c_name + ";path=/;expires=" + exdate.toGMTString();

      //expires是设置cookie的过期时间，toGMTString是将日期转为GMT的字符串进行拼接

      window.document.cookie =

          "password" + "=" + c_pwd + ";path=/;expires=" + exdate.toGMTString();

    },

    //读取cookie  将用户名和密码回显到input框中喽~~

    getCookie: function() {undefined

      if (document.cookie.length > 0) {undefined

        var arr = document.cookie.split("; "); //这里显示的格式需要切割一下自己可输出看下

        for (var i = 0; i < arr.length; i++) {undefined

          var arr2 = arr[i].split("="); //再次切割

          //判断查找相对应的值

          if (arr2[0] == "userName") {undefined

            this.username = arr2[1]; //保存到保存数据的地方 （data中）

          } else if (arr2[0] == "password") {undefined

            this.password = arr2[1];

          }

        }

        console.log(this.username,this.password)

      }

    },

    //清除cookie

    clearCookie: function() {undefined

      this.setCookie("", "", -1); //修改2值都为空，天数为负1天就好了

    },

    login() {
      if (this.autoLogin) {    //如果选中了记住密码的选项

        this.setCookie(ruleForm.account, ruleForm.password, 7);  //则去设置cookie,username是要提交的用户名，password是密码，7是要存储的天数

      } else {undefined

        this.clearCookie()  //如果没有选中记住密码，那就清除cookie

      }

      if (this.ruleForm == null) {
        this.$message({
          showClose: true,
          message: '请输入正确格式账号和密码',
          type: 'error'
        });
      } else {
        login(
            {
              userid: this.ruleForm.account,
              password: this.ruleForm.password
            }
        ).then((response) => {
          if (response.data != null) {
            console.log(response);
            localStorage.setItem('access-admin', response.data)

            this.getData();
          } else {
            this.$message({
              showClose: true,
              message: '您输入的账号或密码有误',
              type: 'error'
            });
          }
        }).catch((error) => {
          console.log(this.ruleForm.account + this.ruleForm.password);
          console.log(error);
          this.$message({
            showClose: true,
            message: '网络错误1',
            type: 'error'
          });
        });
      }
    },
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
              this.$router.push('/search')

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
    }
  }
}
</script>

<style lang="less" scoped>

.copyright{
  width: 363px;
  height: 26px;
  font-size: 12px;
  color: #c5c5c5;
  line-height: 14px;
  text-align: center;
  margin: auto;
  margin-top: 30px;
}

* {
  margin: 0;
  padding: 0;
}

.left {
  background-color: #D8D8D8;
  width: 386px;
  height: 460px;

  .span {
    color: black !important;
  }

  .dyc {
    font-size: 54px;
  }

  .inyong {
    font-size: 26px;
  }


}

.left0 {
  margin-left: 40px;
  padding-top: 100px;
}

.report {
  font-size: 26px;
  margin-top: 40px;
  margin-left: 70px;

}

.right {
  width: 520px;
  height: 460px;
  background-color: white;
}

/deep/ .el-input__inner {
  margin-top: 30px;
  margin-left: 26px;
  width: 467px;
  height: 67px;
  font-size: 20px;
  text-indent: 12px;
}

/deep/ .el-input__icon {
  height: 131px;
  width: 87px;
  font-size: 24px;
  padding-right: 10px;
}

.choose{
  margin: 20px 20px 30px 35px ;
}


.content {
  //background-color: pink;
  display: flex;
  flex-direction: column;
}

.content span {
  margin-bottom: 29px;
}

el-input {
  margin-bottom: 62px;
}

.cannot_login {
  display: flex;
  justify-content: space-between;
}

.input_kuang {
  width: 464px;
  height: 27.81px;
  opacity: 0.29;
  border: none;
  border-bottom: 1px solid #000000;
}

.login_btn {
  margin-left: 91px;
  width: 341px;
  height: 58px;
  background: #1069CD;
  border: white;
  border-radius: 5px;
  color: #FFF;
}

/deep/ .el-checkbox__label{
  //background: #1069CD;
  color: #1069CD;
}

.changeColor {
  margin-left: 50px;
  color: #4472C4;
  outline: none;
  font-size: 24px;
  font-weight: 400;
  line-height: 29px;
  opacity: 1;
  border-color: #4472C4;
}

.enable_lg {
  width: 104px;
  height: 34px;
  font-size: 16px;
  font-weight: 400;
  line-height: 22px;
}

input::-webkit-input-placeholder {
  /* WebKit browsers */
  color: #ccc;
}

.box4 {
  position: absolute;
  bottom: 100px;
  left: 80px;
  font-size: 16px;
}

.fs16 {
  font-size: 16px;
  text-align: center;
}
</style>
