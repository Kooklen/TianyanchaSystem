<template>
  <!--  <div>{{count}}</div>-->
  <h3>获取鼠标位置</h3>
</template>
<script>
import {onMounted} from 'vue'
export default {
  name: "new_index",
  created() {
    console.log("created")
  },
  setup() {
    const position = {
      x: 0,
      y: 0
    }

    const handler = () =>{
      console.log('handler')
    }

  onMounted(()=>{
      window.addEventListener('mouseover', hanlder)
    })


  }
}
</script>
