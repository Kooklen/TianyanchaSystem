<template>
  <div>
    <Header toback="0"></Header>
    <div class="bgc">
      <div class="block"></div>
      <div class="form flex-col">
        <div class="form_inside">
<!--          <div class="block1"></div>-->
        <div class="sign">企业登记</div>
        <div class="feedback">请登记您所需的企业信息，我们将尽快给予您反馈</div>
        <div>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
            <el-form-item prop="enterpriseName">
              <el-input prefix-icon="el-icon-office-building" placeholder="企业名称"  v-model="ruleForm.enterpriseName">
              </el-input>
            </el-form-item>

            <el-form-item prop="legalPeople">
              <el-input prefix-icon="el-icon-s-custom
" placeholder="法人姓名"  v-model="ruleForm.legalPeople">
              </el-input>
            </el-form-item>

<!--            <el-form-item prop="enterpriseCity">-->
<!--              <el-cascader-->

<!--                  size="large"-->

<!--                  :options="options"-->

<!--                  v-model="selectedOptions"-->

<!--                  @change="handleChange"-->

<!--                  style="width: 100%"-->

<!--                  placeholder="选择城市"-->
<!--              >-->
<!--              </el-cascader>-->
<!--            </el-form-item>-->

            <el-form-item prop="province">
              <el-input prefix-icon="el-icon-location
" placeholder="省份"  v-model="ruleForm.province">
              </el-input>
            </el-form-item>

            <el-form-item prop="city">
              <el-input prefix-icon="el-icon-location
" placeholder="城市"  v-model="ruleForm.city">
              </el-input>
            </el-form-item>

            <el-form-item prop="creditCode">
              <el-input prefix-icon="el-icon-bank-card" placeholder="统一社会信用代码"  v-model="ruleForm.creditCode">
              </el-input>
            </el-form-item>

            <el-form-item prop="enterpriseAddress">
              <el-input prefix-icon="el-icon-location
" placeholder="企业地址"  v-model="ruleForm.enterpriseAddress">
              </el-input>
            </el-form-item>

            <el-form-item prop="contact">
              <div class="flex-row" >
              <el-input prefix-icon="el-icon-phone" placeholder="联系方式"  v-model="ruleForm.contact">
              </el-input>
<!--              <span>（方便我们通知您处理结果）</span>-->
<!--                <span>123</span>-->
              </div>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">提交反馈</el-button>
            </el-form-item>
            <span class="note">
              （方便我们通知您处理结果）
            </span>
          </el-form>
        </div>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
import Header from "@/components/header";
import {regionData} from 'element-china-area-data'
import {enterpriseRegistration} from '@/js/api'

let user;
export default {
  name: "enterpriseRegistration",
  components: {Header},
  data() {
    return {
      iconColor: 'red',
      options: regionData,
      selectedOptions: [],
      ruleForm: {
        city:'',
        province:'',
        enterpriseName: '',
        enterpriseAddress: '',
        legalPeople: '',
        contact: '',
        creditCode: ''
      },
      rules: {
        enterpriseName: [
          {required: true, message: '请输入企业名称', trigger: 'blur'},
        ],
        enterpriseAddress: [
          {required: true, message: '请输入企业地址', trigger: 'blur'},
        ],
        legalPeople: [
          {
            required: true,
            message: '请输入法人姓名',
            trigger: 'blur',
            min: 2,
            max: 5
          },
        ],
        contact: [
          {
            required: true,
            pattern: /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/,
            message: "请输入合法手机号/电话号 电话格式为区号-电话号",
            trigger: "blur"
          }
        ],
        creditCode: [
          {
            // required: true,
            message: "请输入正确的企业统一社会信用代码",
            pattern: /^([0-9A-HJ-NPQRTUWXY]{2}\d{6}[0-9A-HJ-NPQRTUWXY]{10}|[1-9]\d{14})$/

          }
        ],
        enterpriseCity: [
          {required: true, message: '请选择企业所在省份和城市', trigger: 'blur'},
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        let user = JSON.parse(localStorage.getItem("user-data"));
        if (valid) {
          enterpriseRegistration({
            state:0,
            userid:user.userid,
            username:user.username,
            companyname:this.ruleForm.enterpriseName,
            legalpeople:this.ruleForm.legalPeople,
            province:this.ruleForm.province,
            city:this.ruleForm.city,
            creditcode:this.ruleForm.creditCode,
            contactphone:this.ruleForm.contact,
            createtime:"1639987358832"
          })
              .then((response) => {
            if (response.data!=null) {
              this.$message({
                showClose: true,
                message: "提交成功，请耐心等待处理。",
                type: 'success'
              })
              this.ruleForm=[];
            }else{
              this.$message({
                showClose: true,
                message: '网络错误',
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
        } else {
          console.log('error submit!!');
          return false;
        }
      })
    },

    handleChange(value) {

      console.log(value)

    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    }


  }


}

</script>

<style scoped>
.block{
  height: 1px;
  width: 100%;
}

.form {
  width: 940px;
  height: 600px;
  margin-top: 71px;
  margin-left: auto;
  margin-right: auto;
  background-color: rgb(255, 255, 255);
}

.form_inside{
  margin-left: 51px;
  margin-top: 17px;
}

/deep/ .el-input__icon{
  color: rgb(41, 163, 129);
  font-size: 20px;
}

/deep/ .el-form-item{
  width: 571px;
  text-indent: 2px;
}

.block1{
  height: 37px;
  width: 100%;
}

.bgc {
  background-color: #28A381;
  width: 100%;
  height: 734px;
  margin: auto;
  margin-top: 25px;
}


.sign {
  height: 47px;
  font-size: 36px;
  color: #46AE92;
  line-height: 47px;
  margin: 0 0 14px 0;
}

.feedback{
  margin: 0 0 21px 0;
  width: 308px;
  height: 19px;
  font-size: 14px;
  color: #171717;
  line-height: 19px;
}

/deep/ .el-button{
  background-color: rgb(40, 163, 129);
}
el-form{
  position: relative;
}

.note{
  position: absolute;
  right: 509px;
  bottom: 180px;
  color: #909399;
  font-size: 14px;
}

</style>
