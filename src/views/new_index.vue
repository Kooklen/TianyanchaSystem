<template>
  <div class="bgc">
    <login class="login"></login>
  </div>
</template>

<script>
import Login from "@/components/login";

export default {
  name: "new_index",
  components: {Login}
}
</script>

<style scoped>

.bgc {
  position: fixed;
  top: 0;
  left: 0;
  background-image: url("../../src/img/new_background.f6c21280.jpg");
  height: 100vh;
  width: 100vw;
  background-repeat: no-repeat;
  background-size: cover;
}

.login {
  width: 901px;
  height: 460px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

</style>
