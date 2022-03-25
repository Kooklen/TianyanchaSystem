<template>
  <div class="id">
    <el-row :gutter="24">
      <el-col :span="8">
        <login class="login"></login>

      </el-col>
      <el-col :span="16">
        <img src="../img/indexImage.png" alt="">
      </el-col>
    </el-row>

  </div>
</template>

<script>
import Login from "@/components/login";

export default {
  name: 'index',
  components: {Login},
  props: {}
}


</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

.id{
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

.login {
  padding: 243px 0 0 85px;
}

img {
  width: 100%;
  height: 100%;
  background-size: 100%;
  margin: 0;
  padding: 0;
}
</style>
