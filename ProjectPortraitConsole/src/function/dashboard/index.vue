<template>

  <div style="padding:30px;">
    <el-alert :closable="false" title="Welcome to Project Portrait" description="" type="success">
      <router-view/>
    </el-alert>
    <el-alert :closable="false" title="Welcome to Project Portrait" description="欢迎来到 项目画像（分析）系统" type="warn">
      <router-view/>
    </el-alert>
    <el-alert :closable="false" title="Welcome to Project Portrait" description="欢迎来到 项目画像（分析）系统" type="error">
      <router-view/>
    </el-alert>
    <el-alert :closable="false" title="Welcome to Project Portrait" description="欢迎来到 项目画像（分析）系统" type="warn">
      <router-view/>
    </el-alert>
    <el-alert :closable="false" title="Welcome to Project Portrait" description="欢迎来到 项目画像（分析）系统" type="success">
      <router-view/>
    </el-alert>
    <el-alert :closable="false" title="Welcome to Project Portrait" description="欢迎来到 项目画像（分析）系统" type="warn">
      <router-view/>
    </el-alert>
    <el-alert :closable="false" title="Welcome to Project Portrait" description="欢迎来到 项目画像（分析）系统" type="error">
      <router-view/>
    </el-alert>
    <!--<div class="dashboard-text">name:{{ name }}</div>-->
    <!--<div class="dashboard-text">roles:<span v-for="role in roles" :key="role">{{ role }}</span></div>-->
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: 'Dashboard',
    computed: {
      ...mapGetters([
        'name',
        'roles'
      ])
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .dashboard {
    &-container {
      margin: 30px;
    }
    &-text {
      font-size: 10px;
      line-height: 20px;
    }
  }
</style>
