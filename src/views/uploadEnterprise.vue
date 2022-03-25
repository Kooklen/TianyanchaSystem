<template>
  <div>
    <Header toback="0"></Header>
    <div class="bgc">
      <div class="query">批量查询企业</div>
      <div class="load">
        <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple>
          <div class="upload_logo">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">点击或拖拽上传文件
              <div class="el-upload__text1" slot="tip">支持扩展名: .xlxs或.xlsx</div>
              <em>模板下载</em>
            </div>
          </div>
        </el-upload>
      </div>
    </div>

  </div>
</template>

<script>
import Header from "@/components/header";

export default {
  name: "uploadEnterprise",
  components: {Header},
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      })
    },

    handleChange(value) {

      console.log(value)

    }


  }


}

</script>

<style lang="less" scoped>

* {
  margin: 0;
  padding: 0;
}

.query{
  width: 216px;
  height: 47px;
  font-size: 36px;
  color: #FFFFFF;
  line-height: 47px;
  padding: 32px 0 20px 324px;
}

/deep/ .el-icon-upload{
  color: #28A381;
  font-size: 100px;
  margin-bottom: 50px;
}

#id{
  margin: 0;
  padding: 0;
}

.form {
  width: 1000px;
  height: 750px;
  margin: auto;
  margin-top: 50px;
}

.bgc {
  margin-top: 25px;
  background-color: #28A381;
  width: 100%;
  height: 734px;
}

.load{
  width: 1271px;
  height: 528px;
  background-color: white;
  margin-left: 324px;
}

h1 {
  color: #303133;
  margin: 50px 0 0 200px;
  padding-top: 100px;
}

/deep/ .el-upload-dragger{
  width: 1162px;
  height: 465px;
  margin:31px 55px
}

.upload_logo {
  margin-top: 100px;
}

/deep/ .upload-demo{
  margin-left: auto;
}

.el-upload__text {
  color: #171717;
  font-size: 48px;

  em{
    font-size: 21px;
    //color: #0E68CC;
  }
}

.el-upload__text1{
  font-size: 21px;
  margin-top: 10px;
}
</style>
