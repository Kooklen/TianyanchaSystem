<template>
  <div>
    <el-tabs type="card" @tab-click="handleClick" v-model="activeName">

      <el-tab-pane label="本月预警记录" v-if="totrigger==1" name="first" :key="'first'">
        <el-table
            class="el-table"
            :data="triggerdata"
            style="width: 100%;color: black"
            :default-sort = "{prop: 'triggertime', order: 'descending'}"
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
      </el-tab-pane>

      <el-tab-pane label="历史预警记录" v-if="lasttrigger==1"  name="third" :key="'third'">
        <el-table
            class="el-table"
            :data="lasttriggerdata"
            style="width: 100%"
            :default-sort = "{prop: 'triggertime', order: 'descending'}"
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
      </el-tab-pane>

      <el-tab-pane label="监控企业名称" v-if="ismonitor==1" name="second" :key="'second'">
        <el-table
            class="el-table"
            :data="tableData"
            style="width: 100%"
            :default-sort = "{prop: 'date', order: 'descending'}"
        >
          <el-table-column
              prop="name"
              label="企业名称"
          >
          </el-table-column>
          <el-table-column
              prop="date"
              label="监控日期"
          >
          </el-table-column>

<!--          <el-table-column-->
<!--              label="状态 (红色表示企业有异常)">-->
<!--            <template #default="scope">-->
<!--              <div class="status" v-if="scope.row.status==true"></div>-->
<!--              &lt;!&ndash;        true异常&ndash;&gt;-->
<!--              <div class="status1" v-if="scope.row.status==false"></div>-->
<!--              &lt;!&ndash;        false正常&ndash;&gt;-->
<!--            </template>-->

<!--          </el-table-column>-->

          <el-table-column label="操作">
            <template #default="scope">
              <el-button type="text" size="small" @click="handleClick"
              >下载报告
              </el-button
              >
              <el-button type="text" size="small" @click="cancelM(scope)">取消监控</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane label="已经取消监控" v-if="iscancelmonitor==1">
        <el-table
            class="el-table"
            :data="tablefalseData"
            style="width: 100%"
            :default-sort = "{prop: 'canceltime', order: 'descending'}"
        >
          <el-table-column
              prop="name"
              label="企业名称"
          >
          </el-table-column>
          <el-table-column
              prop="canceltime"
              label="取消监控日期"
          >
          </el-table-column>
        </el-table>
      </el-tab-pane>



    </el-tabs>


  </div>



</template>

<script>
import {addMonitorController, editMonitorController, getUserInfo} from "@/js/api";
import {getmonitorcompany,triggerrecord} from "@/js/api";
import {userMainStore} from "@/store";
import Header from "@/components/header";
const store = userMainStore()
export default {
  name: "enterpriseList",
  components: {Header},
  created() {
    console.log(store.index);
    this.changetab()
    this.getlasttriggerdata()
    this.getData()
    this.getfalseData()
    this.gettriggerdata()
    let userData = JSON.parse(localStorage.getItem("user-data"));
    if(store.index==1){
      this.activeName="first"
    }else{
      this.activeName="second"
    }
  },
  props:[
      'ismonitor','iscancelmonitor','totrigger','lasttrigger'
  ],
  computed:{
    tagarr(){
      return store.index
    }
  },
  watch:{
    tagarr(newval,oldval){
      if(newval==1){
        this.activeName="first"
      }else if(newval==2) {
        this.activeName="second"
      }else{
        this.activeName="third"
      }
    }
  },
  methods:{
    changetab:function(data){
      this.activeName=data
      console.log(data);
    },
    gettriggerdata(){
      this.userdata = JSON.parse(localStorage.getItem("user-data"));
      triggerrecord({
        userid:this.userData.userid
      }).then((response) => {
        // // console.log(response)
        if (response!=null) {
          // this.triggerdata=response

          for(let i = 0;i<response.length;i++){
          //   console.log(response[i].triggertime)
            var oDate = new Date(response[i].triggertime)
            var now = new Date(); //当前日期
            var nowMonth = now.getMonth(); //当前月
            var nowYear = now.getFullYear(); //当前年
            var monthStartDate = new Date(nowYear, nowMonth, 1);
            var nDate = new Date(monthStartDate)
            if (oDate<nDate){
          //     console.log(true)
            }else {
              this.triggerdata.push(response[i])
          //     console.log(false);
            }
          // console.log(response);
          // this.triggerdata=response
          }
        }else{
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
    getlasttriggerdata(){
      let userdata = JSON.parse(localStorage.getItem("user-data"));
      triggerrecord({
        userid:userdata.userid
      }).then((response) => {
        // // console.log(response)
        if (response!=null) {
          for(let i = 0;i<response.length;i++){
            // console.log(response[i].triggertime)
            var oDate = new Date(response[i].triggertime)
            var now = new Date(); //当前日期
            var nowMonth = now.getMonth(); //当前月
            var nowYear = now.getFullYear(); //当前年
            var monthStartDate = new Date(nowYear, nowMonth, 1);
            var nDate = Date.parse(monthStartDate)
            // console.log(monthStartDate);

            if (oDate<nDate){
              // console.log(response[i])
              this.lasttriggerdata.push(response[i])
              // console.log(true)
            }else {
              // console.log(false);
            }
            // this.lasttriggerdata=response
          }
        }else{
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
    cancelM(scope){
      this.$alert("公司名称:  "+scope.row.name, '您确定要取消监控', {
        showCancelButton: true,
        confirmButtonText: '确定取消监控',
        cancelButtonText: '取消操作',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            this.userData = JSON.parse(localStorage.getItem("user-data"));
            editMonitorController({
               userid:this.userData.userid,
               companyname:scope.row.name
             }).then((response)=>{
               if (response.data!=null) {
                 // // console.log(response);
                 this.add=false
                 this.$message({
                   showClose: true,
                   message: '取消监控成功',
                   type: 'success'
                 }
                 )
                 done();
                 this.module=1
                 this.$nextTick(function (){
                   this.module=0
                 })
                 // $router.go(0)
               }else {
                 this.$message({
                   showClose: true,
                   message: '系统异常，请稍后重试',
                   type: 'error'
                 })
                 done();
               }
             })
        }
      // // console.log(scope.row.name)
    }
    })
    },
    handleClick(tab, event) {
      // console.log(tab, event);

    },
    getData(){
      this.userData = JSON.parse(localStorage.getItem("user-data"));
      getmonitorcompany({
        userid:this.userData.userid
      })
          .then((response) => {
              if (response!=null) {

                for(let i = 0 ; i < response.length; i++){
                  if(response[i].ismonitor==true)
                  this.tableData.push({
                    name:response[i].companyname,
                    date:response[i].monitortime,
                    status:response[i].state,
                    ismonitor:response[i].ismonitor
                  })
                }
              } else {
              }
            })
    },
    getfalseData(){
      let userdata = JSON.parse(localStorage.getItem("user-data"));
      getmonitorcompany({
        userid:userdata.userid
      })
          .then((response) => {
            if (response!=null) {

              for(var i = 0 ; i < response.length; i++){
                if(response[i].ismonitor==false)
                  this.tablefalseData.push({
                    name:response[i].companyname,
                    canceltime:response[i].canceltime,
                    status:response[i].state,
                    ismonitor:response[i].ismonitor
                  })
              }
              // // console.log(this.tablefalseData);

            } else {
            }
          })
    },
    // 重新添加监控
    addMonitor(index, row){
      // // console.log(index, row)
      let userData = JSON.parse(localStorage.getItem("user-data"));
      editMonitorController({
        userid:userData.userid,
        username:userData.username,
        companyname:row.companyname,
        ismonitor:true
      }).then((response) => {
        if (response.data!=null) {
          // // console.log(response);
          this.add=false
          this.$message({
            showClose: true,
            message: '添加成功',
            type: 'success'
          });

          this.module=1
          this.$nextTick(function (){
            this.module=0
          })

        }else{
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
      }
  },
  data() {
    return {
      ismonitor:this.ismonitor,
      iscancelmonitor:this.iscancelmonitor,
      totrigger:this.totrigger,
      lasttrigger:this.lasttrigger,
      // userData:[],
      triggerdata:[],
      tableData: [],
      tablefalseData:[],
      activeName: 'second',
      lasttriggerdata:[]
    }
  }
}
</script>

<style lang="less" scoped>
.el-table {
  padding-left: 26px;
  color: black;
}

.block {
  //margin: 50px 0 0 350px;
}

/deep/ .el-pagination{
  margin-top: 300px;
  text-align: center;
}

.status {
  height: 14px;
  width: 14px;
  border-radius: 50%;
  background-color: red;
}

.status1 {
  height: 14px;
  width: 14px;
  border-radius: 50%;
  background-color: greenyellow;
}

/deep/ .el-tabs__item{
  background-color: white;
}


</style>
