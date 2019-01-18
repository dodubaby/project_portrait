<template>
  <div style="padding:30px;">
    <el-alert :closable="false" title="【控制台-Log】功能搭建中" type="info">
      <router-view/>
    </el-alert>
  </div>
</template>

<script>
export default {
  methods: {
    buttonClick(){
    }
  }
}
</script>
